package com.wap.model;

public class Position {
    private Integer id;

    private String position;

    private Byte positioncode;

    private Byte status;

    private String createtime;

    private String remark;

    public Position(Integer id, String position, Byte positioncode, Byte status, String createtime, String remark) {
        this.id = id;
        this.position = position;
        this.positioncode = positioncode;
        this.status = status;
        this.createtime = createtime;
        this.remark = remark;
    }

    public Position() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Byte getPositioncode() {
        return positioncode;
    }

    public void setPositioncode(Byte positioncode) {
        this.positioncode = positioncode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}