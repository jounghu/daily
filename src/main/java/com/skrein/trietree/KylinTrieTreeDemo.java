//package com.skrein.trietree;
//
//import com.google.common.base.Preconditions;
//import org.apache.kylin.dict.StringBytesConverter;
//import org.apache.kylin.dict.TrieDictionary;
//import org.apache.kylin.dict.TrieDictionaryBuilder;
//
//import java.io.*;
//
///**
// * @Author: hujiansong
// * @Date: 2019/6/4 15:42
// * @since: 1.8
// */
//public class KylinTrieTreeDemo {
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        TrieDictionaryBuilder<String> b = new TrieDictionaryBuilder<String>(new StringBytesConverter());
//        b.addValue("part");
//        b.print();
//        b.addValue("part");
//        b.print();
//        b.addValue("par");
//        b.print();
//        b.addValue("partition");
//        b.print();
//        b.addValue("party");
//        b.print();
//        b.addValue("parties");
//        b.print();
//        b.addValue("paint");
//        b.print();
//        TrieDictionary<String> dict = b.build(0);
//
////        ByteArrayOutputStream baos = new ByteArrayOutputStream();
////        new ObjectOutputStream(baos).writeObject(dict);
//
////        TrieDictionary<String> dict2 = (TrieDictionary<String>) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
////        Preconditions.checkArgument(dict.contains(dict2));
////        Preconditions.checkArgument(dict2.contains(dict));
////        Preconditions.checkArgument(dict.equals(dict2));
//
//        //dict2.enableIdToValueBytesCache();
////        for (int i = 0; i <= dict.getMaxId(); i++) {
////            System.out.println(Bytes.toString(dict.getValueByteFromId(i)));
////        }
//
//
//        System.out.println(dict.getIdFromValue("paint"));
//        System.out.println(dict.getIdFromValue("par"));
//        System.out.println(dict.getIdFromValue("part"));
//        System.out.println(dict.getIdFromValue("parties"));
//        System.out.println(dict.getIdFromValue("partition"));
//        System.out.println(dict.getIdFromValue("party"));
//    }
//}
