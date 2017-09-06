package com.wap.model;

public class BaseDats {
    private Integer id;

    private String levelcode;

    private String shortname;

    private String fullname;

    private String fatherlevelcode;

    private String specifications;

    private Byte attribute;

    private String remark;

    public BaseDats(Integer id, String levelcode, String shortname, String fullname, String fatherlevelcode, String specifications, Byte attribute, String remark) {
        this.id = id;
        this.levelcode = levelcode;
        this.shortname = shortname;
        this.fullname = fullname;
        this.fatherlevelcode = fatherlevelcode;
        this.specifications = specifications;
        this.attribute = attribute;
        this.remark = remark;
    }

    public BaseDats() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelcode() {
        return levelcode;
    }

    public void setLevelcode(String levelcode) {
        this.levelcode = levelcode == null ? null : levelcode.trim();
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getFatherlevelcode() {
        return fatherlevelcode;
    }

    public void setFatherlevelcode(String fatherlevelcode) {
        this.fatherlevelcode = fatherlevelcode == null ? null : fatherlevelcode.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public Byte getAttribute() {
        return attribute;
    }

    public void setAttribute(Byte attribute) {
        this.attribute = attribute;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}