package com.jms.jms.SpringBoot_JMS.publish_subscribe;

import com.jms.jms.JmsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@SpringBootTest(classes = JmsApplication.class)
class SpringBoot_PS_MsgListenerTest {

    private CountDownLatch latch = new CountDownLatch(10);

    @Test
    void testSendAndReceiveMessage() throws InterruptedException, JMSException {
        boolean received = latch.await(90, TimeUnit.SECONDS);
        assert received : "Timeout waiting for message";
    }

    /**
     * 用於監聽訊息的方法
     * destination：Queue 或 Topic 名稱
     * 備註：使用 @SpringBootTest 測試類，Spring Boot 會掃描所有的 bean (包括 JMS 的 listener bean)
     */
    @JmsListener(destination = "springboot_topic", containerFactory = "jmsTopicListenerContainerFactory")
    public void ps_receiveMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("接收的訊息：" + textMessage.getText());
                latch.countDown();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}