package com.skrein.hadoop.mrserialize;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/8 16:37
 * @since :1.8
 */
public class PhoneMapper extends Mapper<LongWritable, Text, Text, Phone> {

    private Text k = new Text();
    private Phone v = new Phone();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String input = value.toString();
        String[] words = input.split("\t");
        long upFlow = Long.parseLong(words[4].replace(" ",""));
        long downFlow = Long.parseLong(words[5].replace(" ",""));
        v.setUpFlow(upFlow);
        v.setDownFlow(downFlow);
        v.setTotalFlow(upFlow + downFlow);
        k.set(words[1]);
        context.write(k, v);
    }
}
