package com.jms.jms.SpringBoot_JMS;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMQConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setClientID(String.valueOf(Math.random()));
        return connectionFactory;
    }

    @Bean
    public JmsTemplate pointToPointJmsTemplateBean() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setPubSubDomain(false);
        return jmsTemplate;
    }

    @Bean(name = "pointToPointJmsTemplate")
    public JmsMessagingTemplate pointToPointJmsTemplate() {
        return new JmsMessagingTemplate(pointToPointJmsTemplateBean());
    }

    @Bean
    public JmsTemplate publishSubscribeJmsTemplateBean() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    @Bean(name = "publishSubscribeJmsTemplate")
    public JmsMessagingTemplate publishSubscribeJmsTemplate() {
        return new JmsMessagingTemplate(publishSubscribeJmsTemplateBean());
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true); // 使用 Topic
        factory.setConcurrency("3-10"); // 設置併發消費者的數量
        return factory;
    }

}