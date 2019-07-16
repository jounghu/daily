package com.skrein.hadoop.sample.invertindex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author :hujiansong
 * @date :2019/7/12 16:47
 * @since :1.8
 */
public class IndexTwoDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(IndexTwoDriver.class);
        job.setMapperClass(TwoMapper.class);
        job.setReducerClass(TwoReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path("G:\\mr//indexoutput"));
        FileOutputFormat.setOutputPath(job, new Path("G:\\mr//indexoutput2"));


        job.waitForCompletion(true);

    }
}
