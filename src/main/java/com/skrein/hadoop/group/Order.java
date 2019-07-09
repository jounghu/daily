package com.skrein.hadoop.group;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@NoArgsConstructor
public class Order implements WritableComparable<Order> {

    private int orderId;

    private double price;

    @Override
    public int compareTo(Order o) {
        // 先按照id升序，然后按照
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(orderId);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readInt();
        this.price = in.readDouble();
    }
}
