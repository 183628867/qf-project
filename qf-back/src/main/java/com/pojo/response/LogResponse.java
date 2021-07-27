package com.pojo.response;

import com.pojo.Log;
import com.pojo.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO *
 *
 * @author cheng *
 * @version 1.0 *
 * @date 2021-7-8 14:40
 */

public class LogResponse extends PageResponse {

    private List<Log> logList;
    private int counts;

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public List<Log> getLogList() {
        return logList;
    }

    @SuppressWarnings("unchecked")
    public void setLogList(List<Log> logList) {
        try {
            this.logList = (List<Log>) logList.get(0);
            List<Map<String, Object>> list = new ArrayList<>();
            list = (List<Map<String, Object>>) logList.get(1);
            this.counts = Integer.parseInt(list.get(0).get("counts").toString());
        } catch (Exception e) {
            this.logList = logList;

        }
    }
}
