package com.skrein.hadoop.guliETL;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    Text text = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String etlLine = etlLine(line);
        if (StringUtils.isNotEmpty(etlLine)) {
            text.set(etlLine);
            context.write(text, NullWritable.get());
        }
    }

    private String etlLine(String line) {
        if (StringUtils.isEmpty(line)) {
            return null;
        }
        String[] lines = line.split("\t");
        // 过滤脏数据
        if (lines.length < 9) {
            return null;
        }

        // category的空格替换掉，并把&替换成,
        String category = lines[3];
        category = category.replace(" ", "").replace("&", ",");
        lines[3] = category;

        // relateIds可能为空
        String relateIds = null;
        if (lines.length > 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 9; i < lines.length; i++) {
                if (i > 9) {
                    sb.append(",");
                }
                sb.append(lines[i]);
            }
            relateIds = sb.toString();
            lines[9] = relateIds;
        }


        // build text
        StringBuilder stringBuilder = new StringBuilder();
        int iterLen = lines.length > 9 ? 10 : lines.length;
        for (int i = 0; i < iterLen; i++) {
            stringBuilder.append(lines[i]).append("\t");
        }

        return stringBuilder.toString();
    }
}
