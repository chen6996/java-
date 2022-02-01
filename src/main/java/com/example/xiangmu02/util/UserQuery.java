package com.example.xiangmu02.util;

public class UserQuery {
    private Integer begin = 0;
    private Integer currentPage = 1;
    private Integer pageSize = 10;
    private String name;
    private String be;

    /**
     * .....
     *
     * @return
     */
    public Integer getBegin() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public String getBe() {
        return be;
    }

    public void setBe(String be) {
        this.be = be;
    }
}
