package org.example.database.rabbitmq;


import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.example.database.service.PdamDAO;
import org.example.database.model.Tagihan;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PdamReceiver {
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private EntityManager entityManager;
    private PdamDAO pdamDAO;

    @Autowired
    private PdamSender send = new PdamSender();
    private Object Tagihan;

    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void conD() {
        this.entityManager = Persistence
                .createEntityManagerFactory("user-unit")
                .createEntityManager();
        pdamDAO = new PdamDAO(entityManager);
        try {
            entityManager.getTransaction().begin();
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void com() {
        try {
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void getTagihanPdam() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("sendToPdam", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String rekening = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received Cek Saldo Dummy: '" + rekening + "'");
                conD();
//                int i = Integer.parseInt(rekening);
                try {
                    Tagihan dumdum = pdamDAO.getTagihan(rekening);
                    String tagihan =new Gson().toJson(dumdum);
                    System.out.println(tagihan);
                    send.sendTagihanPdamFinal(tagihan);
                } catch (Exception e) {
                    System.out.println("Error send Check Saldo Dummy = " + e);
                }
                com();
            };
            channel.basicConsume("sendToPdam", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error checkSaldoDummy= " + e);
        }
    }
}
