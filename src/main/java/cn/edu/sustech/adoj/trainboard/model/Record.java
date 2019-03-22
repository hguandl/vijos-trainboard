package cn.edu.sustech.adoj.trainboard.model;

import java.util.Date;

public class Record {
    private Integer status;
    private Date judgeAt;
    private String lang;


    public Record(Integer status, Date judgeAt, String lang) {
        this.status = status;
        this.judgeAt = judgeAt;
        this.lang = langName(lang);
    }

    private String langName(String lang) {
        switch (lang) {
            case "cc":
                return "C++";
            case "c":
                return "C";
            case "java":
                return "Java";
            case "py3":
                return "Python 3";
            case "py":
                return "Python";
            case "js":
                return "JavaScript";
            case "go":
                return "Go";
        }
        return "Unknown";
    }

    public Integer getStatus() {
        return status;
    }

    public Date getJudgeAt() {
        return judgeAt;
    }

    public String getLang() { return lang; }
}
