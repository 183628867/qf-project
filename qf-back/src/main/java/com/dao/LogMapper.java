package com.dao;

import com.alibaba.fastjson.JSONObject;
import com.pojo.Log;
import com.pojo.request.AddLogRequest;
import com.pojo.request.LogRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO *
 *
 * @author cheng *
 * @version 1.0 *
 * @date 2021-6-30 16:39
 */
@Mapper
public interface LogMapper {

    /**
     * 录入登录日志
     *
     * @return boolean
     * @author cheng
     * @date 2021-6-30 16:43
     */
    boolean insertLoginLog(String name, String ip, String browser, String systemMc);


    List<Log> selectLogList(LogRequest logRequest);


    List<Object> traffic();

    List<JSONObject> weekList();
}
