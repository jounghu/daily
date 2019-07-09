package com.skrein.hadoop.group;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class GroupMain {
    public static void main(String[] args) throws Exception {
        // 1. 获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2. 设置Jar存储位置
        job.setJarByClass(GroupMain.class);

        // 3. 关联map和reduce
        job.setMapperClass(GroupMapper.class);
        job.setReducerClass(GroupReducer.class);

        // 4. 设置map输出key和value的类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Order.class);


        // 5. 设置最终key和value的类型
        job.setOutputKeyClass(Order.class);
        job.setOutputValueClass(NullWritable.class);

        // 6. 设置输入输出的目录
        FileInputFormat.setInputPaths(job, new Path("G:\\mr\\groupinput"));
        FileOutputFormat.setOutputPath(job, new Path("G:\\mr\\groupouput7"));

        // 7. 提交Job
        job.waitForCompletion(true);
        System.exit(-1);
    }
}
