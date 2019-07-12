package com.skrein.hadoop.baseapi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

/**
 * @author :hujiansong
 * @date :2019/7/5 14:38
 * @since :1.8
 */
public class HDFSApi {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration(true);
        FileSystem fileSystem = FileSystem.get(conf);
        Path path = new Path("/hjs/hadoop2");
        fileSystem.mkdirs(path);

        fileSystem.close();
    }

    /**
     * upload
     * @throws IOException
     */
    @Test
    public void upload() throws IOException {
        Configuration conf = new Configuration(true);
        FileSystem fileSystem = FileSystem.get(conf);
        Path path = new Path("/hjs/hadoop");
        fileSystem.copyFromLocalFile(new Path("D:\\hadoop-dev\\src\\main\\java\\com\\skrein\\hadoop\\demo\\HDFSApi.java"),path);
        fileSystem.close();
    }

    @Test
    public void writePath()throws Exception{
        Configuration conf = new Configuration(true);
        FileSystem fileSystem = FileSystem.get(conf);
        Path path = new Path("d://2019//22/22","12313");
        FSDataOutputStream fsDataOutputStream = fileSystem.create(path);
        fsDataOutputStream.write("hjs".getBytes());
        fsDataOutputStream.close();
    }


    /**
     * 文件下载
     */
    @Test
    public void download() throws Exception {
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hdp1:9000"),conf,"hdp");
        Path path = new Path("/hjs/hadoop/HDFSApi.java");
        fileSystem.copyToLocalFile(false,path,new Path("D:\\A.java"),true);
        fileSystem.close();
    }

    @Test
    public void ioUpload() throws Exception{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hdp1:9000"),conf,"hdp");
        FSDataOutputStream out = fs.create(new Path("/hjs/hadoop/a.txt"));
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.write("hjs\n".getBytes());
        out.flush();
        out.close();
        fs.close();

    }

    @Test
    public void ioDownload()throws Exception{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hdp1:9000"),conf,"hdp");
        FSDataInputStream open = fs.open(new Path("/hjs/hadoop/a.txt"));
        FileOutputStream fos = new FileOutputStream("d://a.txt");
        IOUtils.copyBytes(open,fos,1024);
        fos.close();
        open.close();
        fs.close();
    }

}



