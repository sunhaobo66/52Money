package com.hao.domain.loan;

//账户表
public class Account {
    private Integer id;

    private Integer uid;

    private Integer redpackage;//红包金额

    private Integer totalmoney;//可用余额;

    private  Integer carditmoney;//信用额度

    private Integer forcemoney;//冻结金额

    private Integer flag; //0 有效 1 无效

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRedpackage() {
        return redpackage;
    }

    public void setRedpackage(Integer redpackage) {
        this.redpackage = redpackage;
    }

    public Integer getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Integer totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Integer getCarditmoney() {
        return carditmoney;
    }

    public void setCarditmoney(Integer carditmoney) {
        this.carditmoney = carditmoney;
    }

    public Integer getForcemoney() {
        return forcemoney;
    }

    public void setForcemoney(Integer forcemoney) {
        this.forcemoney = forcemoney;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
