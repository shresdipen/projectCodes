package stripe;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import datastructure.Stripe;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by dipen on 4/5/16.
 */
public class StripeInMapperApproach {
    public static class StripeMapper extends Mapper<Object, Text, Text, Stripe> {

        private final IntWritable one = new IntWritable(1);
        private Map<Text, Stripe> H;

        @Override
        public void setup(Context context){
            H = new HashMap<>();
        }

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] ws = value.toString().split(" ");
            int indexW = 0;
            while (indexW < ws.length) {
                Text w = new Text(ws[indexW].trim());
                int indexU = indexW + 1;
                Stripe Hw = new Stripe();
                while(indexU < ws.length){
                    Text u = new Text(ws[indexU].trim());
                    if(w.equals(u))
                        break;
                    if(Hw.get(u)==null){
                        Hw.put(u, one);
                    }else {
                        Hw.put(u, new IntWritable(((IntWritable)Hw.get(u)).get()+1));
                    }
                    indexU++;
                }
                if(H.get(w)==null){
                    H.put(w, Hw);
                }else {
                    for(Writable u : Hw.keySet()){
                        if(H.get(w).get(u)==null){
                            H.get(w).put(u, one);
                        }else {
                            H.get(w).put(u, new IntWritable(((IntWritable)Hw.get(u)).get()+((IntWritable)H.get(w).get(u)).get()));
                        }
                    }
                }
                indexW++;
            }



        }

        @Override
        public void cleanup(Context context) throws IOException, InterruptedException {
            for(Text key : H.keySet()){
                context.write(key, H.get(key));
            }
        }
    }



    public static class StripeReducer
            extends Reducer<Text, Stripe, String, Stripe> {

        private Text w;
        private Stripe result;
        private int total;

        public void reduce(Text key, Iterable<Stripe> values,
                           Context context
        ) throws IOException, InterruptedException {
            w = key;
            for (Stripe val : values) {
                result = new Stripe();
                total = 0;
                for(Writable keyVal : val.keySet()){
                    total += ((IntWritable)val.get(keyVal)).get();
                    if(result.get(keyVal)==null){
                        result.put(keyVal, val.get(keyVal));
                    }else {
                        result.put(keyVal, new IntWritable(((IntWritable)result.get(keyVal)).get()+((IntWritable)val.get(keyVal)).get()));
                    }
                }
            }
            for(Writable key1 : result.keySet()){
                result.put(key1, new DoubleWritable(((IntWritable)result.get(key1)).get()*1.0/total));
            }
            context.write(w.toString(), result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        conf.set("mapreduce.framework.name", "yarn");

        Job job = Job.getInstance(conf, "Stripe Approach InMapper");
        job.setJarByClass(StripeInMapperApproach.class);
        job.setMapperClass(StripeMapper.class);
        job.setReducerClass(StripeReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Stripe.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

