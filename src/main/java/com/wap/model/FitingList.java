package com.wap.model;

public class FitingList {
    private Integer id;

    private Integer basedatsid;

    private Integer numbers;

    private String createuser;

    private String createtime;

    private Byte status;

    private Integer autoproductid;

    private String bdname;

    private String bdspecifications;

    private String remark;

    public FitingList(Integer id, Integer basedatsid, Integer numbers, String createuser, String createtime, Byte status, Integer autoproductid, String bdname, String bdspecifications, String remark) {
        this.id = id;
        this.basedatsid = basedatsid;
        this.numbers = numbers;
        this.createuser = createuser;
        this.createtime = createtime;
        this.status = status;
        this.autoproductid = autoproductid;
        this.bdname = bdname;
        this.bdspecifications = bdspecifications;
        this.remark = remark;
    }

    public FitingList() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBasedatsid() {
        return basedatsid;
    }

    public void setBasedatsid(Integer basedatsid) {
        this.basedatsid = basedatsid;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getAutoproductid() {
        return autoproductid;
    }

    public void setAutoproductid(Integer autoproductid) {
        this.autoproductid = autoproductid;
    }

    public String getBdname() {
        return bdname;
    }

    public void setBdname(String bdname) {
        this.bdname = bdname == null ? null : bdname.trim();
    }

    public String getBdspecifications() {
        return bdspecifications;
    }

    public void setBdspecifications(String bdspecifications) {
        this.bdspecifications = bdspecifications == null ? null : bdspecifications.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}