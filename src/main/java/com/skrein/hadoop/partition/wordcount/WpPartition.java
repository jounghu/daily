package com.skrein.hadoop.partition.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WpPartition extends Partitioner<Text, IntWritable> {

    @Override
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        String word = text.toString();
        if (word.equals("a")) {
            return 0;
        } else {
            return 1;
        }

    }
}
