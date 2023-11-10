package com.jms.jms.point_to_point;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 點對點模式：訊息生產者
 */
public class Producer {
    public static void main(String[] args) throws JMSException {

        // 1. 創建連線工廠
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.11.81:61616"); // ActiveMQ TCP 默認 Port：61616

        // 2. 創建連線
        Connection connection = factory.createConnection();

        // 3. 開啟連線
        connection.start();

        // 4. 創建 session
        // 參數一：是否開啟事務
        // 參數二：訊息機制
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5. 確認目的地 (Queue：點對點，Topic：發佈訂閱)
        Queue queue = session.createQueue("queue01");

        // 6. 創建生產者
        MessageProducer producer = session.createProducer(queue);

        // 7. 創建訊息 (訊息有多種，createTextMessage 只是其中一種)
        TextMessage textMessage = session.createTextMessage("Text Message.");

        // 8. 生產者發送訊息
        producer.send(textMessage);

        System.out.println("訊息發送完成!");

        // 9. 釋放資源
        session.close();
        connection.close();

    }
}
