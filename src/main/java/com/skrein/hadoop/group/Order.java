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
        if (this.orderId == o.getOrderId()) {
            return -Double.compare(this.price, o.getPrice());
        } else {
            return Integer.compare(this.orderId, o.getOrderId());
        }
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

    @Override
    public String toString() {
        return orderId + "\t" + price;
    }
}
