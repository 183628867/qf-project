package com.pojo;

/**
 * TODO *
 *
 * @author cheng *
 * @version 1.0 *
 * @date 2021-7-8 14:36
 */

public class Log {

    private int id;
    private String name;
    private String ip;
    private String browser;
    private String systemMc;
    private String insertDt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSystemMc() {
        return systemMc;
    }

    public void setSystemMc(String systemMc) {
        this.systemMc = systemMc;
    }

    public String getInsertDt() {
        return insertDt;
    }

    public void setInsertDt(String insertDt) {
        this.insertDt = insertDt;
    }
}
