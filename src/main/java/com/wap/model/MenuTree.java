package com.wap.model;

public class MenuTree {
    private Integer id;

    private String name;

    private String url;

    private String listfathercode;

    private String listcode;

    private String remark;

    private Byte status;

    public MenuTree(Integer id, String name, String url, String listfathercode, String listcode, String remark, Byte status) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.listfathercode = listfathercode;
        this.listcode = listcode;
        this.remark = remark;
        this.status = status;
    }

    public MenuTree() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getListfathercode() {
        return listfathercode;
    }

    public void setListfathercode(String listfathercode) {
        this.listfathercode = listfathercode == null ? null : listfathercode.trim();
    }

    public String getListcode() {
        return listcode;
    }

    public void setListcode(String listcode) {
        this.listcode = listcode == null ? null : listcode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}