package org.example.database.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.example.database.model.Nasabah;
import org.example.database.model.User;
import org.example.database.service.Services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class DatabaseReceiver {
    DatabaseSender send = new DatabaseSender();
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private Services services = new Services();

    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void addNasabah() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("addNasabah", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + mhsString + "'");
                services.registerNasabah(mhsString);
            };
            channel.basicConsume("addNasabah", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Add Nasabah = " + e);
        }
    }

    public void loginNasabah() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("loginNasabah", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + mhsString + "'");
                int res =services.loginUser(mhsString);
                if (res!=0){
                    User user  = new User();
                    user.setUserId(res);
                    System.out.println("Cek Update Status");
                    services.updateStatusLogin(user,"true");
                }
                try {
                    send.sendToRestApi(String.valueOf(res));
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("loginNasabah", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Login Nasabah = " + e);
        }
    }

    public void logoutNasabah() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("logoutNasabah", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + mhsString + "'");
                int res =services.logoutUser(mhsString);
                if (res!=0){
                    User user  = new User();
                    user.setUserId(res);
                    System.out.println("Cek Update Status");
                    services.updateStatusLogin(user,"false");
                }
                try {
                    send.sendToRestApi(String.valueOf(res));
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("logoutNasabah", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Login Nasabah = " + e);
        }
    }
    public void checkSaldo() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("checkSaldo", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + mhsString + "'");
                int res =services.checkUser(mhsString);
                String nilaiSaldo="";
                if (res!=0){
                    Nasabah nasabah = new Nasabah();
                    nasabah.setIdNasabah(res);
                    System.out.println("Cek Saldo Status: "+res);

                    int saldoTotal = services.checkSaldo(nasabah);
                    System.out.println("isi saldo total: "+saldoTotal);
                    nilaiSaldo=String.valueOf(saldoTotal);
                } else {
                    nilaiSaldo="0";
                }
                try {
                    send.sendToRestApi(nilaiSaldo);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("checkSaldo", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error CheckSaldo = " + e);
        }
    }
}