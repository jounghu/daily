package com.skrein.hadoop.sort;

import com.skrein.hadoop.mrserialize.Phone;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/8 16:37
 * @since :1.8
 */
public class PhoneSortedMapper extends Mapper<LongWritable, Text, Phone, Text> {

    private Text k = new Text();
    private Phone v = new Phone();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String input = value.toString();
        String[] words = input.split("\t");
        long upFlow = Long.parseLong(words[1]);
        long downFlow = Long.parseLong(words[2]);
        v.setUpFlow(upFlow);
        v.setDownFlow(downFlow);
        v.setTotalFlow(Long.parseLong(words[3]));
        k.set(words[0]);
        context.write(v, k);
    }
}
