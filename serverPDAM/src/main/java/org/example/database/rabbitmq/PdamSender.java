package org.example.database.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PdamSender {
    public void sendTagihanPdamFinal(String nbstring) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection con = factory.newConnection();
             Channel channel = con.createChannel()) {
            channel.queueDeclare("sendTagihantoPdam", false, false, false, null);
            channel.basicPublish("", "sendTagihantoPdam", null, nbstring.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] SEND Tagihan Ke Pdam: '" + nbstring + "'");
        }
    }
}
