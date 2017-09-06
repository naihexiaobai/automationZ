package com.wap.model;

public class User {
    private Integer id;

    private String phone;

    private String name;

    private String department;

    private String jobnumber;

    private String position;

    private Integer positioncode;

    private String username;

    private String userpassword;

    private String createtime;

    private String leavetime;

    private String remark;
    /**
     * 状态默认0 打开，1禁用
     */
    private Byte status;

    public User(Integer id, String phone, String name, String department, String jobnumber, String position, Integer positioncode, String username, String userpassword, String createtime, String leavetime, String remark, Byte status) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.department = department;
        this.jobnumber = jobnumber;
        this.position = position;
        this.positioncode = positioncode;
        this.username = username;
        this.userpassword = userpassword;
        this.createtime = createtime;
        this.leavetime = leavetime;
        this.remark = remark;
        this.status = status;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber == null ? null : jobnumber.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getPositioncode() {
        return positioncode;
    }

    public void setPositioncode(Integer positioncode) {
        this.positioncode = positioncode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime == null ? null : leavetime.trim();
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