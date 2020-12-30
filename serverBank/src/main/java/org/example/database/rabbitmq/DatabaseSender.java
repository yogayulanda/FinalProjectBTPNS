package org.example.database.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class DatabaseSender {
    public void sendToRestApi(String message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("messageFromDatabase", false, false, false, null);
            channel.basicPublish("", "messageFromDatabase", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e){
            System.out.println("Gagal mengirim pesan ke RestApi.." + e);
        }
    }

    public void sendToPdam (String message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("sendToPdam", false, false, false, null);
            channel.basicPublish("", "sendToPdam", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println("Gagal mengirim Data Transfer ke Dummy :(" + e);
        }
    }

    public void sendToApiReceive (String message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("sendToApiReceive", false, false, false, null);
            channel.basicPublish("", "sendToApiReceive", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println("Gagal mengirim Data Transfer ke Dummy :(" + e);
        }
    }
    public void getNasabah (String message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("getNasabah", false, false, false, null);
            channel.basicPublish("", "getNasabah", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println("Gagal mengirim Data Transfer ke Dummy :(" + e);
        }
    }
}