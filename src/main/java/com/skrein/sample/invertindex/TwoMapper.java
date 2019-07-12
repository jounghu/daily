package com.skrein.sample.invertindex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * @author :hujiansong
 * @date :2019/7/12 16:19
 * @since :1.8
 */
public class TwoMapper extends Mapper<Text, IntWritable, Text, Text> {

    @Override
    protected void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
        String[] split = key.toString().split("\001");
        String word = split[0];
        String filename = split[1];
        context.write(new Text(word), new Text(filename + "\t" + value.get()));
    }
}
