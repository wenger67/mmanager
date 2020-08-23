package com.vinson.mmanager.model.request;

public class BaseListParams {
    int page;
    int pageSize;

    public BaseListParams() {
        this(1, 10);
    }

    public BaseListParams(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
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
}
