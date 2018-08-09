package com.hao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
//发布者
public class ActiveMQPub_Main {
    public static void main(String[] args)throws Exception {
        String url = "tcp://39.104.165.25:61616";
        //1、创建工厂
        ConnectionFactory factory=new ActiveMQConnectionFactory(url);
        ((ActiveMQConnectionFactory) factory).setTrustAllPackages(true);
        //2、创建连接
        Connection connection=factory.createConnection();
        //3、开启连接
        connection.start();
        //4、获取会话
        //参数说明：1、是否使用事务  2、应答模式
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5、创建主题 存储消息的容器
        Topic topic=session.createTopic("money");
        //6、创建发布者
        MessageProducer producer=session.createProducer(topic);
        //7、创建并消息
         //producer.send(session.createTextMessage("月薪1000"));
       producer.send(session.createObjectMessage(new Offer(101,"简自豪","RNG",3000000)));


    }
}
