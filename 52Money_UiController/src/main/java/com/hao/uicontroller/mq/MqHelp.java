package com.hao.uicontroller.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
//发送消息
@Service
public class MqHelp {
    @Autowired
    private JmsTemplate template;

    //要发队列
    @Autowired
    private ActiveMQQueue queue;

    //发送消息
    public void sendMsg(String msg){
        template.send(queue,(session) -> {
            return session.createTextMessage(msg);
        });
    }

}
