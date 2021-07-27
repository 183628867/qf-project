package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dao.LogMapper;
import com.pojo.Log;
import com.pojo.request.AddLogRequest;
import com.pojo.request.LogRequest;
import com.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO *
 *
 * @author cheng *
 * @version 1.0 *
 * @date 2021-6-30 16:48
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;


    @Override
    public boolean insertLoginLog(String name,String ip,String browser,String systemMc) {
        return logMapper.insertLoginLog(name,ip,browser,systemMc);
    }

    @Override
    public List<Log> selectLogList(LogRequest logRequest) {
        return logMapper.selectLogList(logRequest);
    }


    @Override
    public List<Object> traffic() {
        return logMapper.traffic();
    }


    @Override
    public List<JSONObject> weekList() {
        return logMapper.weekList();
    }
}
