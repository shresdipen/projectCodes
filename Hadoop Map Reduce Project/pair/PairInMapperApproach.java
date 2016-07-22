package pair;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import datastructure.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by dipen on 4/5/16.
 */
public class PairInMapperApproach {
    public static class PairMapper extends Mapper<Object, Text, Pair, IntWritable> {

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
                    Pair checkPair = new Pair(w, "*");

                    if(H.get(pair)==null){
                        H.put(pair, one);
                    }else {
                        H.put(pair, new IntWritable(H.get(pair).get()+1));
                    }
                    if(H.get(checkPair)==null){
                        H.put(checkPair, one);
                    }else {
                        H.put(checkPair, new IntWritable(H.get(checkPair).get()+1));
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



    public static class PairReducer
            extends Reducer<Pair, IntWritable, Pair,DoubleWritable> {
        private DoubleWritable result = new DoubleWritable();
        private int total = 0;
        public void reduce(Pair key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            if(key.getRight().equals("*"))
                total = sum;
            else {
                result.set(sum*1.0/total);
                context.write(key, result);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        conf.set("mapreduce.framework.name", "yarn");

        Job job = Job.getInstance(conf, "Pair Approach InMapper");
        job.setJarByClass(PairInMapperApproach.class);
        job.setMapperClass(PairMapper.class);
        job.setReducerClass(PairReducer.class);
        job.setOutputKeyClass(Pair.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

