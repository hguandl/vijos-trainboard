package cn.edu.sustech.adoj.trainboard.dao;

import cn.edu.sustech.adoj.trainboard.model.Record;
import cn.edu.sustech.adoj.trainboard.model.RecordDisplay;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MongoService {
    private static MongoDatabase mongoDatabase;

    private static MongoDatabase connectMongo() {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            return mongoClient.getDatabase("mongo");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MongoService() {
        mongoDatabase = connectMongo();
    }

    public List<RecordDisplay> getRecordList(String pid) {
        BasicDBObject condition = new BasicDBObject();
        condition.append("type", new BasicDBObject("$eq", 0L))
                .append("pid", new ObjectId(pid));

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
        return RecordDisplay.recordsToList(records);
    }
}
