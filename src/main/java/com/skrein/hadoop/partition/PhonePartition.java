package com.skrein.hadoop.partition;

import com.skrein.hadoop.mrserialize.Phone;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author :hujiansong
 * @date :2019/7/9 16:08
 * @since :1.8
 */
public class PhonePartition extends Partitioner<Text, Phone> {
    @Override
    public int getPartition(Text text, Phone phone, int numPartitions) {
        String phoneNum = text.toString();
        String prefix = phoneNum.substring(0, 3);
        int partition;
        if (prefix.equals("136")) {
            partition = 0;
        } else if (prefix.equals("137")) {
            partition = 1;
        } else if (prefix.equals("138")) {
            partition = 2;
        } else if (prefix.equals("139")) {
            partition = 3;
        } else {
            partition = 4;
        }
        return partition;
    }
}
