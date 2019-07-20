package com.skrein.hadoop.habse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HBaseAPI {

    Admin admin;
    Connection connection;

    @Before
    public void before() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.addResource("hbase-site.xml");
        conf.addResource("core-site.xml");
        connection = ConnectionFactory.createConnection(conf);
        admin = connection.getAdmin();

    }

    @Test
    public void existTable() throws IOException {
        boolean student = admin.tableExists(TableName.valueOf("student"));
        System.out.println(student);
    }

    @Test
    public void getValue() throws IOException {
        Table student = connection.getTable(TableName.valueOf("student"));
        Result result = student.get(new Get("1001".getBytes()));
        byte[] value = result.getValue("info".getBytes(), "age".getBytes());
        String age = new String(value);
        System.out.println(age);
    }

    @Test
    public void getValue1() throws IOException {
        Table student = connection.getTable(TableName.valueOf("student"));
        Result result = student.get(new Get("1005".getBytes()));
        byte[] value = result.getValue("info".getBytes(), "name".getBytes());
        String age = new String(value);
        System.out.println(age);
    }

    @Test
    public void insertValue() throws IOException {
        Table student = connection.getTable(TableName.valueOf("student"));
        student.put(new Put("1005".getBytes()).addColumn("info".getBytes(),"name".getBytes(),"hjs".getBytes()));
    }


    @Test
    public void deleteValue() throws IOException {
        Table student = connection.getTable(TableName.valueOf("student"));
        student.delete(new Delete("1005".getBytes()));
    }
}
