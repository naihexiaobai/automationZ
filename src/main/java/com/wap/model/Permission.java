package com.wap.model;

public class Permission {
    private Integer id;

    private String menulistcode;

    private Integer menuid;

    private Byte positioncode;

    private String remark;

    public Permission(Integer id, String menulistcode, Integer menuid, Byte positioncode, String remark) {
        this.id = id;
        this.menulistcode = menulistcode;
        this.menuid = menuid;
        this.positioncode = positioncode;
        this.remark = remark;
    }

    public Permission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenulistcode() {
        return menulistcode;
    }

    public void setMenulistcode(String menulistcode) {
        this.menulistcode = menulistcode == null ? null : menulistcode.trim();
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Byte getPositioncode() {
        return positioncode;
    }

    public void setPositioncode(Byte positioncode) {
        this.positioncode = positioncode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}