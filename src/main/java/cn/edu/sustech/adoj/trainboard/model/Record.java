package cn.edu.sustech.adoj.trainboard.model;

import java.util.Date;

public class Record {
    private Integer status;
    private Date judgeAt;


    public Record(Integer status, Date judgeAt) {
        this.status = status;
        this.judgeAt = judgeAt;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getJudgeAt() {
        return judgeAt;
    }
}
