package com.hao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

//接受端
public class ActiveMQSend_Main1 {
    public static void main(String[] args)throws Exception {
        String url = "tcp://39.104.165.25:61616";
        //1.创建工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        ((ActiveMQConnectionFactory)factory).setTrustAllPackages(true);
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.开启连接
        connection.start();
        //4.获取会话
        //参数声明: 1.是否使用事务 2.应答模式
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5.创建队列
        Queue queue = session.createQueue("qqq");
        //6.创建消息提供者
        MessageProducer producer = session.createProducer(queue);
        //7.创建文本消息
      //  TextMessage message = session.createTextMessage("一定要坚持");
        //创建对象消息
        ObjectMessage objectMessage=session.createObjectMessage( new Offer(1,"简自豪","RNG",10000000));
        //8.发送消息
        producer.send(objectMessage);
        //9.关闭销毁
        session.close();
        connection.close();















    }
}
