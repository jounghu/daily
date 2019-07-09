package com.skrein.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/8 12:00
 * @since :1.8
 */
public class AppWordCount {


//    static {
//        try {
//            System.load("F:\\DevTool\\hadoop-2.7.2\\bin\\hadoop.dll");
//        } catch (UnsatisfiedLinkError e) {
//            System.err.println("Native code library failed to load.\n" + e);
//            System.exit(1);
//        }
//    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        // 1. 获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2. 设置Jar存储位置
        job.setJarByClass(AppWordCount.class);

        // 3. 关联map和reduce
        job.setMapperClass(TextMapper.class);
        job.setReducerClass(TextReducer.class);

        // 4. 设置map输出key和value的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);


        // 5. 设置最终key和value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 6. 设置输入输出的目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7. 提交Job
        job.waitForCompletion(true);
        System.exit(-1);
    }
}
