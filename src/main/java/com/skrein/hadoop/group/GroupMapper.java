package com.skrein.hadoop.group;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GroupMapper extends Mapper<LongWritable, Text, IntWritable, Order> {

    private Order v = new Order();

    private IntWritable k = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String orderLine = value.toString();
        String[] orderDetails = orderLine.split(" ");
        int orderId = Integer.parseInt(orderDetails[0]);
        v.setOrderId(orderId);
        v.setPrice(Double.parseDouble(orderDetails[1]));
        k.set(orderId);
        context.write(k,v);
    }
}
