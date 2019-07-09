package com.skrein.hadoop.mrserialize;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/8 16:56
 * @since :1.8
 */
public class PhoneMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(PhoneMain.class);
        job.setMapperClass(PhoneMapper.class);
        job.setReducerClass(PhoneReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Phone.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Phone.class);

        FileInputFormat.setInputPaths(job,new Path("d:/phone-input"));
        FileOutputFormat.setOutputPath(job,new Path("d:/phone-output4"));

        job.waitForCompletion(true);

    }
}
