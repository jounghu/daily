package com.skrein.hadoop.inputformat.customerinput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author :hujiansong
 * @date :2019/7/9 14:09
 * @since :1.8
 */
public class WholeRecordReader extends RecordReader<Text, BytesWritable> {

    private FileSplit split;

    private Configuration conf;

    private Text key;

    private BytesWritable value;

    private boolean isProcessed = true;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        this.split = (FileSplit) split;
        this.conf = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (isProcessed) {
            Path path = split.getPath();
            FileSystem fileSystem = path.getFileSystem(conf);
            FSDataInputStream fis = fileSystem.open(path);
            byte[] bytes = new byte[(int) split.getLength()];
            IOUtils.readFully(fis, bytes, 0, bytes.length);
            setKeyAndValue(path, bytes);
            isProcessed = false;
            return true;
        }
        return false;
    }

    private void setKeyAndValue(Path path, byte[] bytes) {
        if (key == null) {
            key = new Text();
        }

        if (value == null) {
            value = new BytesWritable();
        }

        key.set(path.toString());
        value.set(bytes, 0, bytes.length);
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
