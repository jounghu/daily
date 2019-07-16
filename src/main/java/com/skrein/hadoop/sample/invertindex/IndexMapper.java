package com.skrein.hadoop.sample.invertindex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;


/**
 * @author :hujiansong
 * @date :2019/7/12 16:19
 * @since :1.8
 */
public class IndexMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private String fileName;

    private Text k = new Text();
    private IntWritable v = new IntWritable();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        fileName = fileSplit.getPath().toString();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String words = value.toString();
        String[] s = words.split(" ");
        for (String s1 : s) {
            k.set(s1 + "\001" + fileName);
            v.set(1);
            context.write(k, v);
        }
    }
}
