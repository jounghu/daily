//package com.skrein.trietree;
//
//import org.apache.kylin.dict.AppendTrieDictionary;
//import org.apache.kylin.dict.global.AppendTrieDictionaryBuilder;
//
//import java.io.IOException;
//
///**
// * @author :hujiansong
// * @date :2019/6/25 14:13
// * @since :1.8
// */
//public class AppendDictDemo {
//
//    private static final String BASE_DIR = "/hujiansong/";
//    private AppendTrieDictionary dict = getDict();
//    private AppendTrieDictionaryBuilder builder = createBuilder();
//
//
//    public void mkdict() throws IOException {
//        builder.addValue("abc");
//        builder.addValue("abcd");
//        builder.addValue("ab");
//        builder.addValue("abd");
//        builder.build(0);
//    }
//
//    public void getId() {
//        System.out.println(dict.getIdFromValue("ab"));
//    }
//
//
//    private AppendTrieDictionaryBuilder createBuilder() {
//        int maxEntriesPerSlice = 3;
//        try {
//            return new AppendTrieDictionaryBuilder(BASE_DIR, maxEntriesPerSlice, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private AppendTrieDictionary getDict() {
//        beforeTest();
//
//        System.setProperty("kylin.metadata.url", BASE_DIR);
//        AppendTrieDictionary dict = new AppendTrieDictionary();
//        try {
//            dict.init("/");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dict;
//    }
//
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        AppendDictDemo appendDictDemo = new AppendDictDemo();
//        appendDictDemo.mkdict();
//
//        Thread.sleep(3000);
//        appendDictDemo.getId();
//
//    }
//
//
//    public static void beforeTest() {
//        // staticCreateTestMetadata();
//        System.setProperty("hadoop.home.dir", "F:\\DevTool\\hadoop-bin");
//        if (System.getProperty(KylinConfig.KYLIN_CONF) == null && System.getenv(KylinConfig.KYLIN_CONF) == null) {
//            System.setProperty(KylinConfig.KYLIN_CONF, "D:\\hadoop-dev\\src\\main\\resources\\");
//        }
//
//        KylinConfig config = KylinConfig.getInstanceFromEnv();
//        // metadata存放路径
//        // config.setMetadataUrl(LOCALMETA_TEMP_DATA);
//        // hdfs 工作目录
//        config.setProperty("kylin.env.hdfs-working-dir", BASE_DIR + "/resources/GlobalDict/dict/");
//
//
//        KylinConfig.getInstanceFromEnv().setProperty("kylin.dictionary.append-entry-size", "1000000000");
//        // BASE_DIR = KylinConfig.getInstanceFromEnv().getHdfsWorkingDirectory() + "/resources/GlobalDict/dict/append_dict_test/dict/";
//        // LOCAL_BASE_DIR = getLocalWorkingDirectory() + "/resources/GlobalDict" + RESOURCE_DIR + "/";
//
//        // System.out.println("BASE_DIR:  " + BASE_DIR);
//        // System.out.println("LOCAL_BASE_DIR:" + LOCAL_BASE_DIR);
//    }
//}
