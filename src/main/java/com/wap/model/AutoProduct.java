package com.wap.model;

public class AutoProduct {
    private Integer id;

    private String name;

    private String specifications;

    private String createtime;

    private String updatetime;

    private String agent;

    private String createuser;

    private String remark;

    private Integer createuserid;
    /**
     * 状态，0默认，1未提交，2已提交，3审核通过，4审核不通过，5待发货，6已发货，7确认收货，8厂家使用中
     */
    private Byte status;

    public AutoProduct(Integer id, String name, String specifications, String createtime, String updatetime, String agent, String createuser, String remark, Integer createuserid, Byte status) {
        this.id = id;
        this.name = name;
        this.specifications = specifications;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.agent = agent;
        this.createuser = createuser;
        this.remark = remark;
        this.createuserid = createuserid;
        this.status = status;
    }

    public AutoProduct() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}