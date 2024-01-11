package com.treeNode.util.result;

public class PageJsonResult<T> extends Result<T>{
    private static final long serialVersionUID = -141515153538L;
    //当前页
    private int page = 1;
    //每页条数
    private int pageSize = 10;
    //总页数
    private int totalPage;
    //总条数
    private int totalSize;

    private int start() {
        return this.page > 1 ? (this.page - 1) * this.pageSize : 0;
    }

    public void calculate(int page, int pageSize, int totalSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        if (totalSize == 0) {
            this.totalPage = 0;
        } else {
            this.totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
