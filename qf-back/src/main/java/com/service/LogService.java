package com.service;

import com.alibaba.fastjson.JSONObject;
import com.pojo.Log;
import com.pojo.request.AddLogRequest;
import com.pojo.request.LogRequest;

import java.util.List;

/**
 * TODO *
 *
 * @author cheng *
 * @version 1.0 *
 * @date 2021-6-30 16:47
 */

public interface LogService {

    /**
     * 录入登录日志
     *
     * @return boolean
     * @author cheng
     * @date 2021-6-30 16:48
     */
    boolean insertLoginLog(String name, String ip, String browser, String operatingSystem);


    List<Log> selectLogList(LogRequest logRequest);


    List<Object> traffic();



    List<JSONObject> weekList();
}
