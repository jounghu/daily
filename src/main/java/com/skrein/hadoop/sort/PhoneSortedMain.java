package com.skrein.hadoop.sort;


import com.skrein.hadoop.mrserialize.Phone;
import com.skrein.hadoop.mrserialize.PhoneMapper;
import com.skrein.hadoop.mrserialize.PhoneReducer;
import com.skrein.hadoop.partition.PhonePartition;
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
public class PhoneSortedMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(PhoneSortedMain.class);
        job.setMapperClass(PhoneSortedMapper.class);
        job.setReducerClass(PhoneSortedReducer.class);

        job.setMapOutputKeyClass(Phone.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Phone.class);

        FileInputFormat.setInputPaths(job, new Path("g:\\mr\\phoneinput"));
        FileOutputFormat.setOutputPath(job, new Path("g:/mr/phone-output439"));

        job.waitForCompletion(true);

    }
}
