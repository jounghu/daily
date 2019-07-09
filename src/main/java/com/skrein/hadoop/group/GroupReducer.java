package com.skrein.hadoop.group;

import org.apache.commons.collections.IteratorUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class GroupReducer extends Reducer<IntWritable, Order, Order, NullWritable> {

    Order v = new Order();

    @Override
    protected void reduce(IntWritable key, Iterable<Order> values, Context context) throws IOException, InterruptedException {

        double max = 0;
        for (Order value : values) {
            if (value.getPrice() > max) {
                max = value.getPrice();
            }
        }
        v.setOrderId(key.get());
        v.setPrice(max);
        context.write(v, NullWritable.get());

    }
}
