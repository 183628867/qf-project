package com.pojo;

/**
 * @author chengst
 * @date 2020/3/25
 **/
public class Page {

    private int startTemp;

    private int page;

    private int limit;


    public int getStartTemp() {
        return startTemp;
    }

    public void setStartTemp(int startTemp) {
        this.startTemp = startTemp;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
