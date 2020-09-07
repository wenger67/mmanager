package com.vinson.mmanager.model.request;

public class BaseListParams {
    public int page;
    public int pageSize;

    public BaseListParams() {
        this(1, 10);
    }

    public BaseListParams(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
}
