package com.hao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//发送端
public class ActiveMQrev_Main1 {
    public static void main(String[] args)throws  Exception {
        String url = "tcp://39.104.165.25:61616";
        //1.创建工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        ((ActiveMQConnectionFactory)factory).setTrustAllPackages(true);
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.开启连接
        connection.start();
        //4.获取会话
        //参数说明: 1.是否使用事务 2.应答模式
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5.创建 队列
        Queue queue = session.createQueue("qqq");
        //6.消息消费者
        MessageConsumer consumer = session.createConsumer(queue);

        //接受消息 可以使用receive 也可以使用MessageListener
        consumer.setMessageListener((message)-> {
            try {
                if (message instanceof TextMessage) {
                    System.err.println("文本消息：" + ((TextMessage) message).getText());
                }else if(message instanceof ObjectMessage){
                    System.err.println("对象消息："+((ObjectMessage)message).getObject());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });


      //  TextMessage message = (TextMessage) consumer.receive();
        //System.err.println("接收："+message.getText());
        //7.关闭
        //session.close();
       // connection.close();
    }


}
