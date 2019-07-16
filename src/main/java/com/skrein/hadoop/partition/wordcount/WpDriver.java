package com.skrein.hadoop.partition.wordcount;

import com.skrein.hadoop.mr.AppWordCount;
import com.skrein.hadoop.mr.TextMapper;
import com.skrein.hadoop.mr.TextReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WpDriver {
    public static void main(String[] args) throws Exception {
        // 1. 获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2. 设置Jar存储位置
        job.setJarByClass(WpDriver.class);

        // 3. 关联map和reduce
        job.setMapperClass(WpMapper.class);

        // 4. 设置map输出key和value的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(2);
        job.setPartitionerClass(WpPartition.class);

        // 6. 设置输入输出的目录
        FileInputFormat.setInputPaths(job, new Path("G:\\mr\\input"));
        FileOutputFormat.setOutputPath(job, new Path("G:\\mr\\output1314"));

        // 7. 提交Job
        job.waitForCompletion(true);
        System.exit(-1);
    }
}
