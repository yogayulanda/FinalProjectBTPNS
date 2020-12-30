package org.example.serverPdam.rabbitmq;



import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.example.database.model.Tagihan;
import org.example.serverPdam.service.PdamDAO;

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

    public void conD() {
        this.entityManager = Persistence
                .createEntityManagerFactory("nasabah-unit")
                .createEntityManager();
        pdamDAO = new PdamDAO(entityManager);
        try {
            entityManager.getTransaction().begin();
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        connection = factory.newConnection();
    }

    public void com() {
        try {
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void getTagihan() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("transferDummy", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String nbString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received Transfer'" + nbString + "'");
                Tagihan tr = new Gson().fromJson(nbString, Tagihan.class);
                conD();
                pdamDAO.getSaldo(nbString);
                com();
            };
            channel.basicConsume("transferDummy", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("ERROR REGISTRASI NASABAH = " + e);
        }
    }
}
