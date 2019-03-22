package cn.edu.sustech.adoj.trainboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecordDisplay {
    private Integer uid;
    private String status;
    private String time;
    private String lang;

    private RecordDisplay(Integer uid, Record record) {
        this.uid = uid;
        this.status = parseStatus(record.getStatus());
        this.time = record.getJudgeAt().toString();
        this.lang = record.getLang();
    }

    private static String parseStatus(Integer status) {
        if (status == 1) {
            return "passed";
        }
        return "failed";
    }

    public static List<RecordDisplay> recordsToList(Map<Integer, Record> records) {
        List<RecordDisplay> list = new ArrayList<>();
        for (Map.Entry<Integer, Record> entry : records.entrySet()) {
            list.add(new RecordDisplay(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public Integer getUid() {
        return uid;
    }

    public String getLang() {
        return lang;
    }
}
