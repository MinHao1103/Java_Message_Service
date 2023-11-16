package com.jms.jms.SpringBoot_JMS.publish_subscribe;

import com.jms.jms.JmsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;


@SpringBootTest(classes = JmsApplication.class)
class SpringBoot_PS_MsgListenerTest {

    @Test
    void testSendAndReceiveMessage() throws InterruptedException {
        while (true) {

        }
    }

    @JmsListener(destination = "springboot_topic", containerFactory = "jmsTopic1ListenerContainerFactory")
    public void ps_receiveMessage1(Message message) {
        processMessage(message);
    }

    @JmsListener(destination = "springboot_topic", containerFactory = "jmsTopic2ListenerContainerFactory")
    public void ps_receiveMessage2(Message message) {
        processMessage(message);
    }

    public void processMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("接收的訊息：" + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}