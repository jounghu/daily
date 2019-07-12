package com.skrein.hadoop.group;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GroupMapper extends Mapper<LongWritable, Text, Order, NullWritable> {

    private Order v = new Order();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String orderLine = value.toString();
        String[] orderDetails = orderLine.split(" ");
        v.setOrderId( Integer.parseInt(orderDetails[0]));
        v.setPrice(Double.parseDouble(orderDetails[1]));
        context.write(v, NullWritable.get());
    }
}
