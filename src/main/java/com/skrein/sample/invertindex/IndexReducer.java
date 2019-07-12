package com.skrein.sample.invertindex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/12 16:26
 * @since :1.8
 */
public class IndexReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private Text k = new Text();
    private IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        k.set(key);
        v.set(sum);
        context.write(k, v);
    }
}
