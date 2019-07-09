package com.skrein.hadoop.mrserialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/8 16:40
 * @since :1.8
 */
@Data
@NoArgsConstructor
public class Phone implements Writable, WritableComparable<Phone> {

    private long upFlow;
    private long downFlow;
    private long totalFlow;


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(totalFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.totalFlow = in.readLong();
    }

    @Override
    public String toString() {
        return upFlow +
                "\t" + downFlow +
                "\t" + totalFlow;
    }


    /**
     *
     * @param o
     * @return -1 倒序 ， 1正序， 0默认
     */
    @Override
    public int compareTo(Phone o) {
        if (this.totalFlow > o.getTotalFlow()) {
            return -1;
        } else if (this.totalFlow < o.getTotalFlow()) {
            return 1;
        }
        return 0;
    }
}
