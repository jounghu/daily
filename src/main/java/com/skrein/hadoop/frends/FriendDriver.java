package com.skrein.hadoop.frends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import javax.xml.soap.Text;
import java.net.URI;

public class FriendDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setNumReduceTasks(0);

        job.setMapperClass(FriendMapper.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setJarByClass(FriendDriver.class);
        job.addCacheFile(new Path("G:/mr/friendinput/friends.txt").toUri());

        FileInputFormat.setInputPaths(job, new Path("G:\\mr\\friendinput"));
        FileOutputFormat.setOutputPath(job, new Path("G:\\mr\\friendoutput13"));

        job.waitForCompletion(true);
    }
}
