package com.skrein.hadoop.guliETL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ETLDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(ETLDriver.class);
        job.setMapperClass(ETLMapper.class);
        job.setOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path("G:\\mr\\guli"));
        FileOutputFormat.setOutputPath(job,new Path("G:\\mr\\guliouput"));

        job.waitForCompletion(true);

    }
}
