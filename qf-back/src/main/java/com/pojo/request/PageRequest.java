package com.pojo.request;

/**
 * @Auth chengst
 * @Date Created in  16:38 2020/6/17
 */
public class PageRequest {

    private int page;

    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = (page - 1) * limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
