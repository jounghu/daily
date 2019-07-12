package com.skrein.hadoop.group;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author :hujiansong
 * @date :2019/7/10 15:15
 * @since :1.8
 */
public class GroupCompare extends WritableComparator {

    public GroupCompare() {
        super(Order.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Order o1 = (Order) a;
        Order o2 = (Order) b;
        return Integer.compare(o1.getOrderId(), o2.getOrderId());
    }
}
