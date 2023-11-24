package com.jms.jms.SpringBoot_JMS.point_to_opint;

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
class SpringBoot_PTP_MsgListenerTest {

    private CountDownLatch latch = new CountDownLatch(6); // 測試監聽 6 筆訊息就結束

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
    @JmsListener(destination = "${springboot.queue.name}", containerFactory = "jmsQueueListenerContainerFactory")
    public void ptp_receiveMessage(Message message) {
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