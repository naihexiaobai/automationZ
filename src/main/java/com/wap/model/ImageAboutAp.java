package com.wap.model;

public class ImageAboutAp {
    private Integer id;

    private Integer apId;

    private String imgUp;

    private String imgDown;

    private String imgLeft;

    private String imgRight;

    private String imgFront;

    private String imgBehind;

    private String imgElectrics;

    private String imgMachine;

    private String imgOther;

    private String remark;

    public ImageAboutAp(Integer id, Integer apId, String imgUp, String imgDown, String imgLeft, String imgRight, String imgFront, String imgBehind, String imgElectrics, String imgMachine, String imgOther, String remark) {
        this.id = id;
        this.apId = apId;
        this.imgUp = imgUp;
        this.imgDown = imgDown;
        this.imgLeft = imgLeft;
        this.imgRight = imgRight;
        this.imgFront = imgFront;
        this.imgBehind = imgBehind;
        this.imgElectrics = imgElectrics;
        this.imgMachine = imgMachine;
        this.imgOther = imgOther;
        this.remark = remark;
    }

    public ImageAboutAp() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApId() {
        return apId;
    }

    public void setApId(Integer apId) {
        this.apId = apId;
    }

    public String getImgUp() {
        return imgUp;
    }

    public void setImgUp(String imgUp) {
        this.imgUp = imgUp == null ? null : imgUp.trim();
    }

    public String getImgDown() {
        return imgDown;
    }

    public void setImgDown(String imgDown) {
        this.imgDown = imgDown == null ? null : imgDown.trim();
    }

    public String getImgLeft() {
        return imgLeft;
    }

    public void setImgLeft(String imgLeft) {
        this.imgLeft = imgLeft == null ? null : imgLeft.trim();
    }

    public String getImgRight() {
        return imgRight;
    }

    public void setImgRight(String imgRight) {
        this.imgRight = imgRight == null ? null : imgRight.trim();
    }

    public String getImgFront() {
        return imgFront;
    }

    public void setImgFront(String imgFront) {
        this.imgFront = imgFront == null ? null : imgFront.trim();
    }

    public String getImgBehind() {
        return imgBehind;
    }

    public void setImgBehind(String imgBehind) {
        this.imgBehind = imgBehind == null ? null : imgBehind.trim();
    }

    public String getImgElectrics() {
        return imgElectrics;
    }

    public void setImgElectrics(String imgElectrics) {
        this.imgElectrics = imgElectrics == null ? null : imgElectrics.trim();
    }

    public String getImgMachine() {
        return imgMachine;
    }

    public void setImgMachine(String imgMachine) {
        this.imgMachine = imgMachine == null ? null : imgMachine.trim();
    }

    public String getImgOther() {
        return imgOther;
    }

    public void setImgOther(String imgOther) {
        this.imgOther = imgOther == null ? null : imgOther.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}