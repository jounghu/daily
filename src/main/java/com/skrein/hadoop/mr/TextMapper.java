package com.skrein.hadoop.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/8 11:07
 * @since :1.8
 */
public class TextMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private IntWritable intWritable = new IntWritable(1);

    private Text text = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(" ");
        for (String word : words) {
            text.set(word);
            context.write(text, intWritable);
        }

    }
}
