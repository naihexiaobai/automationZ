package com.wap.model;

/**
 * Created by admin on 2017/8/2.
 */
public class AutoProductSelectPage {
    private Integer id;

    private String name;

    private String specifications;

    private String createtime;

    private String updatetime;

    private String agent;

    private String createuser;

    private String remark;

    private Integer createuserid;

    private Byte status;

    private int nowPage;

    private int maxPage;

    private int pageLimit = 10;

    private int pageSize;

    private int totalSize;

    private long startLimit;

    private long endLimit;

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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
        this.name = name;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
        this.maxPage = this.totalSize % this.pageLimit > 0 ? this.totalSize / this.pageLimit + 1 : this.totalSize / this.pageLimit;
    }

    public long getStartLimit() {
        return startLimit;
    }

    public void setStartLimit(long startLimit) {
        this.startLimit = startLimit;
    }

    public long getEndLimit() {
        return endLimit;
    }

    public void setEndLimit(long endLimit) {
        this.endLimit = endLimit;
    }

    public String getAgent() {
        return agent;

    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public void setData(int nowPage) {
        startLimit = (nowPage - 1) * pageLimit;
        endLimit = (nowPage) * pageLimit;
    }

}
