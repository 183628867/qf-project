package com.pojo.request;

import lombok.Data;

/**
 * TODO *
 *
 * @author cheng *
 * @version 1.0 *
 * @date 2021-6-30 16:38
 */
public class AddLogRequest {

    private String ip;
    private String name;
    private String browser;
    private String systemMc;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
