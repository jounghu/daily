package com.skrein.hadoop.sort;

import com.skrein.hadoop.mrserialize.Phone;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/8 16:37
 * @since :1.8
 */
public class PhoneSortedReducer extends Reducer<Phone, Text, Text, Phone> {

    @Override
    protected void reduce(Phone key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value, key);
        }
    }
}
