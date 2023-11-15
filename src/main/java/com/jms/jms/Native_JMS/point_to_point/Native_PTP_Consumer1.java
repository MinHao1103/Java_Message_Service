package com.jms.jms.Native_JMS.point_to_point;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 點對點模式：訊息消費者
 */
public class Native_PTP_Consumer1 {

    /**
     * 第一種消費方式
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

        // 7. 消費者接收訊息
        while (true) {
            Message message = consumer.receive();
            // 如果沒有訊息，則退出
            if (message == null) {
                break;
            }
            // 如果有訊息，可判斷什麼類型的訊息
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("接收的訊息：" + textMessage);
            }
        }
    }
}
