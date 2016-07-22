package hybrid;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import datastructure.Pair;
import datastructure.Stripe;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by dipen on 4/5/16.
 */
public class HybridInMapperApproach {
    public static class HybridMapper extends Mapper<Object, Text, Pair, IntWritable> {

        private final IntWritable one = new IntWritable(1);
        private Map<Pair, IntWritable> H;

        @Override
        public void setup(Context context){
            H = new HashMap<>();
        }

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] ws = value.toString().split(" ");
            int indexW = 0;
            while (indexW < ws.length) {
                String w = ws[indexW].trim();
                int indexU = indexW + 1;
                while(indexU < ws.length){
                    String u = ws[indexU].trim();
                    if(w.equals(u))
                        break;
                    Pair pair = new Pair(w,u);
                    if(H.get(pair)==null){
                        H.put(pair, one);
                    }else {
                        H.put(pair, new IntWritable(H.get(pair).get()+1));
                    }
                    indexU++;
                }
                indexW++;
            }



        }

        @Override
        public void cleanup(Context context) throws IOException, InterruptedException {
            for(Pair key : H.keySet()){
                context.write(key, H.get(key));
            }
        }
    }



    public static class HybridReducer
            extends Reducer<Pair, IntWritable, Text, Stripe> {
        private Stripe result;
        private int total;
        private Text w;
        private String curW;

        public void setup(Context context){
            result = new Stripe();
            curW = "*";
            total= 0;
        }


        public void reduce(Pair key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            Text w = new Text(key.getLeft());
            Text u = new Text(key.getRight());
            for (IntWritable val : values) {
                if(!curW.equals("*") && !curW.equals(w.toString())){
                    emit(context);
                }
                total+=val.get();
                if(result.get(u)==null){
                    result.put(u, new IntWritable(val.get()));
                }
                else {
                    result.put(u, new IntWritable(((IntWritable)result.get(u)).get()+val.get()));
                }
                curW = w.toString();
            }

        }


        @Override
        public void cleanup(Context context) throws IOException, InterruptedException {
            emit(context);
        }

        public void emit(Context context) throws IOException, InterruptedException {
            for(Writable key : result.keySet()){
                result.put(key, new DoubleWritable(((IntWritable)result.get(key)).get()*1.0/total));
            }
            context.write(new Text(curW), result);
            setup(context);
        }

    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        conf.set("mapreduce.framework.name", "yarn");

        Job job = Job.getInstance(conf, "Hybrid Approach InMapper");
        job.setJarByClass(HybridInMapperApproach.class);
        job.setMapperClass(HybridMapper.class);
        job.setReducerClass(HybridReducer.class);
        job.setOutputKeyClass(Pair.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

