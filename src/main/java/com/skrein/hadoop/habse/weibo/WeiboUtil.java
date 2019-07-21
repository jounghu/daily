package com.skrein.hadoop.habse.weibo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeiboUtil {

    private static Configuration conf;

    /**
     * weibo
     */
    private static final String NAMESPACE = "weibo";


    /**
     * CONTENT
     */
    private static final String TB_CONTENT = "weibo:content";

    /**
     * RELATION
     */
    private static final String TB_RELATION = "weibo:relation";

    /**
     * INBOX
     */
    private static final String TB_INBOX = "weibo:inbox";


    static {
        conf = HBaseConfiguration.create();
        conf.addResource("hbase-site.xml");
        conf.addResource("core-site.xml");
    }


    // 创建ns
    public static void createNamespace() throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        admin.createNamespace(NamespaceDescriptor.create(NAMESPACE).build());
        connection.close();
        admin.close();
    }


    /**
     * 创建 weibo:content weibo:relation weibo:inbox
     *
     * @param tbName
     * @param version inbox保存用户最近三条数据
     * @param cfs
     */
    public static void createTables(String tbName, int version, String... cfs) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        HTableDescriptor contentTb = new HTableDescriptor(TableName.valueOf(tbName));


        for (String cf : cfs) {
            HColumnDescriptor cfDesc = new HColumnDescriptor(cf);
            // TODO 这里应该可以指定对应cf对应的version，而不是所有的cf都是同一个version
            cfDesc.setMaxVersions(version);
            contentTb.addFamily(cfDesc);
        }

        admin.createTable(contentTb);
    }


    /**
     * post weibo to habse table (weibo:content)
     * <p>
     * key: uid + sys.currentMills()
     * cf: content
     * column: text
     * value: content_value
     *
     * @param uid
     * @param content
     */
    public static void postWeibo(String uid, String content) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);

        Table contentTable = connection.getTable(TableName.valueOf(TB_CONTENT));
        Table relationTable = connection.getTable(TableName.valueOf(TB_RELATION));
        Table inboxTable = connection.getTable(TableName.valueOf(TB_INBOX));

        // 插入 weibo:content表中
        String contentKey = uid + "_" + System.currentTimeMillis();
        Put put = new Put(Bytes.toBytes(contentKey))
                .addColumn(Bytes.toBytes("content"), Bytes.toBytes("text"), Bytes.toBytes(content));
        contentTable.put(put);

        // 从 weibo:relation中找出相应的粉丝，然后插入到 weibo:inbox表中
        Result result = relationTable.get(new Get(Bytes.toBytes(uid)));
        List<Cell> cells = result.listCells();
        if (cells == null || cells.size() == 0) {
            return;
        }

        // 找到对应的uid，put对应的cell
        List<Put> puts = new ArrayList<>();
        for (Cell cell : cells) {
            // fansId
            byte[] fansId = CellUtil.cloneQualifier(cell);
            Put posts = new Put(fansId).addColumn(Bytes.toBytes("posts"), Bytes.toBytes(uid), Bytes.toBytes(content));
            puts.add(posts);
        }
        inboxTable.put(puts);
    }

    /**
     * 获取粉丝
     *
     * @param uid
     * @return
     * @throws IOException
     */
    public static List<String> getFans(String uid) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Table relationTable = connection.getTable(TableName.valueOf(TB_RELATION));
        Result result = relationTable.get(new Get(Bytes.toBytes(uid)).addFamily(Bytes.toBytes("fans")));
        List<Cell> cells = result.listCells();

        List<String> fans = new ArrayList<>();
        if (cells == null || cells.size() == 0) {
            return null;
        }

        for (Cell cell : cells) {
            fans.add(Bytes.toString(cell.getValueArray()));
        }
        return fans;
    }


    /**
     * @param uid     本ID
     * @param otherId 其他ID
     */
    public static void follow(String uid, String otherId) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Table relationTable = connection.getTable(TableName.valueOf(TB_RELATION));
        relationTable.put(new Put(Bytes.toBytes(otherId)).addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid), Bytes
                .toBytes(uid)));

        // 同时查询三条最近的数据放入inbox中
        Table contentTable = connection.getTable(TableName.valueOf(TB_CONTENT));
        Scan scan = new Scan(Bytes.toBytes(otherId), Bytes.toBytes(otherId + "|"));
        ResultScanner scanner = contentTable.getScanner(scan);
        Put posts = new Put(Bytes.toBytes(uid));
        for (Result result : scanner) {
            byte[] row = result.getRow();
            System.out.println("otherId: "+otherId+"-----------------------add-----------"+new String(row));
            posts.addColumn(Bytes.toBytes("posts"), Bytes.toBytes(otherId), row);
        }

        Table inboxTable = connection.getTable(TableName.valueOf(TB_INBOX));
        inboxTable.put(posts);

    }


    @Test
    public void testPut() throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Table inboxTable = connection.getTable(TableName.valueOf(TB_INBOX));
        Put posts = new Put(Bytes.toBytes("1003"));
        posts.addColumn(Bytes.toBytes("posts"),"1001".getBytes(),System.currentTimeMillis(),"test2342421".getBytes());
        posts.addColumn(Bytes.toBytes("posts"),"1001".getBytes(),System.currentTimeMillis()+1,"test2324324232".getBytes());
        posts.addColumn(Bytes.toBytes("posts"),"1001".getBytes(),System.currentTimeMillis()+2,"test333423423423324432234".getBytes());
        inboxTable.put(posts);
        inboxTable.close();
    }


    /**
     * 取关用户，删除用户
     *
     * @param oriId 源用户
     * @param uid   目标用户
     */
    public static void unFollow(String oriId, String uid) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Table relationTable = connection.getTable(TableName.valueOf(TB_RELATION));
        relationTable.delete(new Delete(Bytes.toBytes(uid)).addColumn(Bytes.toBytes("fans"), Bytes.toBytes(oriId)));

        // 删除inbox
        Table inboxTable = connection.getTable(TableName.valueOf(TB_INBOX));
        inboxTable.delete(new Delete(Bytes.toBytes(oriId)).addColumn(Bytes.toBytes("posts"), Bytes.toBytes(uid)));
    }


    @Test
    public void init() throws IOException {
//        createNamespace();
        createTables(TB_CONTENT, 1, "content");
        createTables(TB_RELATION, 1, "fans");
        createTables(TB_INBOX, 3, "posts");
    }


    @Test
    public void postContent() throws IOException {
        postWeibo("1001", "four weibo1");
        postWeibo("1001", "four weibo3");
        postWeibo("1001", "four weibo4");
        postWeibo("1001", "four weibo5");
        postWeibo("1001", "four weibo6");
    }

    @Test
    public void testFollow() throws IOException {
        follow("1003", "1001");
    }


}
