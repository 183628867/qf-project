package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.enums.ResponseEntity;
import com.enums.RetResponse;
import com.enums.RetResult;
import com.pojo.Log;
import com.pojo.request.LogRequest;
import com.pojo.response.LogResponse;
import com.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO *
 *
 * @author cheng *
 * @version 1.0 *
 * @date 2021-7-8 11:50
 */
@RestController
@RequestMapping("api/log/")
@Api(tags = "日志")
public class LogController {


    @Resource
    private LogService logService;



    @RequestMapping("LoginLogList")
    @RequiresPermissions("LoginLogList")
    @ApiOperation("查询登录日志")
    public LogResponse selectLogList(LogRequest logRequest) {
        LogResponse logResponse = new LogResponse();
        List<Log> logs = logService.selectLogList(logRequest);
        logResponse.setLogList(logs);
        logResponse.setResponseEntity(ResponseEntity.CM_OK);
        return logResponse;
    }



    @RequestMapping("traffic")
    @ApiOperation("查询首页访问量")
    public RetResult traffic() {
        List<Object> list1 = logService.traffic();
        List<JSONObject> list2 = logService.weekList();
        System.out.println(list2);
        Map map = new HashMap<>(16);
        map.put("traffic",list1);
        map.put("weekList",list2);
        return RetResponse.resultData(map);
    }

}
