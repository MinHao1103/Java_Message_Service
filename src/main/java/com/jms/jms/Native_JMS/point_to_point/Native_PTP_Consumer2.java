package com.jms.jms.Native_JMS.point_to_point;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 點對點模式：訊息消費者
 */
public class Native_PTP_Consumer2 {

    /**
     * 第二種消費方式
     */
    public static void main(String[] args) throws JMSException {

        // 1. 創建連線工廠
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.11.81:61616"); // ActiveMQ TCP 默認 Port：61616

        // 2. 創建連線
        Connection connection = factory.createConnection();

        // 3. 開啟連線
        connection.start();

        // 4. 創建 session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5. 確認目的地 (Queue：點對點，Topic：發佈訂閱)
        Queue queue = session.createQueue("queue01");

        // 6. 創建消費者
        MessageConsumer consumer = session.createConsumer(queue);

        // 7. 設置訊息監聽器讓消費者接收訊息
        consumer.setMessageListener(new MessageListener() {
            // 處理訊息
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("接收的訊息：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
