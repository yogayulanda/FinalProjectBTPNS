package org.example.serverPdam.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PdamSender {

    public void getTagihan(String id_pelanggan) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection con = factory.newConnection();
             Channel channel = con.createChannel()) {
            channel.queueDeclare("getdataDummy", false, false, false, null);
            channel.basicPublish("", "getdataDummy", null, id_pelanggan.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] SEND CHECK data Dummy '" + id_pelanggan + "'");
        }
    }
}
