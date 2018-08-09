package com.hao.domain.loan;

//风险 风控表
public class Risk {
    private Integer id;

    private Integer aid; //账户ID

    private Integer type; //0房产1车2股票3保单4工作证明

    private Integer flag;// '0未审核 1审核通过 2审核拒绝

    private Integer score; //安全系数分数 100

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
