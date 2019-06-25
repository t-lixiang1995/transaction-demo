package com.kljs.rabbitmq.order.util;


import com.rabbitmq.client.ConnectionFactory;

public class MyConnectionFactoryUtil {

    public static ConnectionFactory getConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    public static ConnectionFactory getAliyunConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("140.143.236.208");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("mozhu");
        connectionFactory.setPassword("123456");
        return connectionFactory;
    }

}
