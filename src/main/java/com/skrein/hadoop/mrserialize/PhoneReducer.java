package com.skrein.hadoop.mrserialize;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/8 16:49
 * @since :1.8
 */
public class PhoneReducer extends Reducer<Text, Phone, Text, Phone> {

    private Phone v = new Phone();

    @Override
    protected void reduce(Text key, Iterable<Phone> values, Context context) throws IOException, InterruptedException {
        long upFlow = 0;
        long downFlow = 0;
        for (Phone value : values) {
            upFlow += value.getUpFlow();
            downFlow += value.getDownFlow();
        }
        v.setUpFlow(upFlow);
        v.setDownFlow(downFlow);
        v.setTotalFlow(upFlow + downFlow);
        context.write(key, v);
    }
}
