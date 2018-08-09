package com.hao.domain.loan;

import java.util.Date;

//借款表
public class Loan {

    private Integer id;

    private Integer type;   // 借款类型 0信用标 1抵押标 2征信标 3债券

    private Integer money; //借款金额

    private Float rate; //比率

    private Integer monthes;

    private Integer returntype; //0 按月分期 1到期还款'

    private Integer minmoney; //借款最小钱数

    private Integer disabledays;//招标天数

    private String title;//标题

    private Integer aid; //账户表ID

    private Date createdate;

    private Integer flag; //0 未审核 1审核通过 2审核拒绝 3结束 4删除

    private Date authdate;//审核日期

    private String description;//说明

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Integer getMonthes() {
        return monthes;
    }

    public void setMonthes(Integer monthes) {
        this.monthes = monthes;
    }

    public Integer getReturntype() {
        return returntype;
    }

    public void setReturntype(Integer returntype) {
        this.returntype = returntype;
    }

    public Integer getMinmoney() {
        return minmoney;
    }

    public void setMinmoney(Integer minmoney) {
        this.minmoney = minmoney;
    }

    public Integer getDisabledays() {
        return disabledays;
    }

    public void setDisabledays(Integer disabledays) {
        this.disabledays = disabledays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getAuthdate() {
        return authdate;
    }

    public void setAuthdate(Date authdate) {
        this.authdate = authdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
