package com.skrein.hadoop.group;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author :hujiansong
 * @date :2019/7/10 16:53
 * @since :1.8
 */
public class OrderIdPartition extends Partitioner<Order, NullWritable> {
    @Override
    public int getPartition(Order order, NullWritable nullWritable, int numPartitions) {
        return order.getOrderId() % numPartitions;
    }
}
