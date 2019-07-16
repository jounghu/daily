package com.skrein.hadoop.frends;

import com.google.common.collect.Sets;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FriendMapper extends Mapper<LongWritable, Text, Text, Text> {

    private Map<String, Set<String>> personAndFollowerMap = new HashMap<>(16);

    private Set<String> dumplicatePerson = new HashSet<>();

    private Text K = new Text();
    private Text V = new Text();

    @Override
    protected void setup(Context context) throws IOException {
        URI[] cacheFiles = context.getCacheFiles();
        URI cacheFile = cacheFiles[0];
        String path = cacheFile.getPath();
        FileInputStream is = new FileInputStream(path);
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = bf.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            String[] personAndFollower = line.split(":");
            String followers = personAndFollower[1];
            String[] allFollowers = followers.split(",");
            personAndFollowerMap.put(personAndFollower[0], Sets.newHashSet(allFollowers));
        }
        IOUtils.closeStream(bf);
        IOUtils.closeStream(is);
    }


    @Override
    protected void map(LongWritable key, Text value, Context context) {
        if(value.toString().equals("")){
            return;
        }
        String[] personAndFollower = value.toString().split(":");
        String person = personAndFollower[0];
        String followers = personAndFollower[1];
        String[] allFollowers = followers.split(",");
        personAndFollowerMap.forEach((k, v) -> {
            if (!StringUtils.equalsIgnoreCase(k, person)) {
                String dkey = person + "-" + k;
                String rkey = k + "-" + person;
                if (!dumplicatePerson.contains(dkey) && !dumplicatePerson.contains(rkey)) {
                    Set<String> commonFriends = findCommonFriends(allFollowers, v);
                    String commonFriend = buildWritableText(commonFriends);
                    K.set(dkey);
                    V.set(commonFriend);
                    try {
                        context.write(K, V);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    dumplicatePerson.add(dkey);
                    dumplicatePerson.add(rkey);
                }
            }
        });
    }

    private String buildWritableText(Set<String> commonFriends) {
        StringBuilder sb = new StringBuilder();
        if (commonFriends != null) {
            commonFriends.forEach(c -> sb.append(c).append(","));
        }
        return sb.toString();
    }

    private Set<String> findCommonFriends(String[] allFollowers, Set<String> followers) {
        Set<String> common = new HashSet<>();
        for (String allFollower : allFollowers) {
            if (followers.contains(allFollower)) {
                common.add(allFollower);
            }
        }
        return common;
    }
}
