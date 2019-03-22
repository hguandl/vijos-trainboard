package cn.edu.sustech.adoj.trainboard;

import cn.edu.sustech.adoj.trainboard.model.Record;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainboardApplicationTests {
    private static MongoDatabase mongoDatabase;

    @BeforeClass
    public static void connectMongo() {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            mongoDatabase = mongoClient.getDatabase("mongo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCollection() {
        BasicDBObject condition = new BasicDBObject();
        condition.append("type", new BasicDBObject("$eq", 0L))
                 .append("pid", new ObjectId("5c739cff90d083095abb78da"));

        MongoCollection<Document> collection = mongoDatabase.getCollection("record");
        FindIterable<Document> findIterable = collection.find(condition);
        Map<Integer, Record> records = new HashMap<>();
        for (Document document : findIterable) {
            Integer uid = document.getInteger("uid");
            if (!records.containsKey(uid)) {
                records.put(uid, new Record(
                        document.getInteger("status"),
                        document.getDate("judge_at"),
                        document.getString("lang")));
            } else {
                if (document.getInteger("status") < records.get(uid).getStatus()) {
                    records.put(uid, new Record(
                            document.getInteger("status"),
                            document.getDate("judge_at"),
                            document.getString("lang")));
                }
            }
        }

        for (Map.Entry<Integer, Record> entry : records.entrySet()) {
            System.out.print(entry.getKey() + ":");
            if (entry.getValue().getStatus() == 1) {
                System.out.println("passed");
            } else {
                System.out.println("failed");
            }
        }
    }
}
