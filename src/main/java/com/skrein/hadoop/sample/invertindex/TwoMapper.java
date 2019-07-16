package com.skrein.hadoop.sample.invertindex;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * @author :hujiansong
 * @date :2019/7/12 16:19
 * @since :1.8
 */
public class TwoMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        String[] word = split[0].split("\001");

        context.write(new Text(word[0]), new Text(word[1] + "\t" + split[1]));
    }
}
