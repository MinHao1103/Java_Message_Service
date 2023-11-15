package com.jms.jms.SpringBoot_JMS.point_to_opint;

import com.jms.jms.JmsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

@SpringBootTest(classes = JmsApplication.class)
class SpringBoot_PTP_ProducerTest {

    @Autowired
    @Qualifier("pointToPointJmsTemplate")
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    void ptpSender() {
        for (int i = 0; i < 3; i++) {
            jmsMessagingTemplate.convertAndSend("springboot_queue", "SpringBoot_PTP_Producer.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}