package com.hao.controller;

import com.hao.core.vo.R;
import com.hao.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//统一中心 消息处理
/**
 *@Author feri
 *@Date Created in 2018/8/3 10:31
 */
//只干消息监听

@RestController
public class MQController {

    @Autowired
    private MqService service;
    @GetMapping("/sendmsg")
    public R send(int type,String msg){
        return service.sendMsg(type,msg);
    }

}
