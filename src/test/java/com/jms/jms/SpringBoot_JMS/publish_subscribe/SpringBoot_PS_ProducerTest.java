package com.jms.jms.SpringBoot_JMS.publish_subscribe;

import com.jms.jms.JmsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

@SpringBootTest(classes = JmsApplication.class)
class SpringBoot_PS_ProducerTest {

    @Autowired
    @Qualifier("publishSubscribeJmsTemplate")
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    void psSender() {
        for (int i = 0; i < 3; i++) {
            jmsMessagingTemplate.convertAndSend("springboot_topic", "Springboot Message.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}