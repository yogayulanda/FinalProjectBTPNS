package org.example.database.rabbitmq;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.example.database.model.Nasabah;
import org.example.database.model.Tagihan;
import org.example.database.model.Transaksi;
import org.example.database.model.User;
import org.example.database.repositories.NasabahDao;
import org.example.database.repositories.TransaksiDao;
import org.example.database.repositories.UserDao;
import org.example.database.service.Services;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class DatabaseReceiver {
    DatabaseSender send = new DatabaseSender();
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private Services services = new Services();
    private EntityManager entityManagerNasabah;
    private UserDao userDao;
    private NasabahDao nasabahDao;
    private TransaksiDao transaksiDao;


    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void connectJPANasabah(){
        this.entityManagerNasabah = Persistence
                .createEntityManagerFactory("ibanking-unit")
                .createEntityManager();
        nasabahDao = new NasabahDao(entityManagerNasabah);
        userDao = new UserDao(entityManagerNasabah);
        try {
            entityManagerNasabah.getTransaction().begin();
        } catch (IllegalStateException e) {
            entityManagerNasabah.getTransaction().rollback();
        }
    }

    public void commitJPA(EntityManager entity){
        try {
            entity.getTransaction().commit();
            entity.close();
        } catch (IllegalStateException e) {
            entity.getTransaction().rollback();
        }
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
                    connectJPANasabah();
                    try{
                        User dumdum = userDao.getUser(String.valueOf(res)); //get user
                        String tagihan =new Gson().toJson(dumdum);
                        send.sendToRestApi(tagihan); //login
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
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

    public void getTagihan() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("getTagihan", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String nbString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] 'Receive from user'" + nbString + "'");
                try{
                    send.sendToPdam(nbString);
                } catch (Exception e) {
                    System.out.println("Error send transfer: " + e);
                }
            };
            channel.basicConsume("getTagihan", true, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            System.out.println("ERROR REGISTRASI NASABAH = " + e);
        }
    }

    public void getTagihanPdamFinal() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("sendTagihantoPdam", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String nbString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] 'Receive from Pdam'" + nbString + "'");
                try{
                    send.sendToApiReceive(nbString);
                } catch (Exception e) {
                    System.out.println("Error send transfer: " + e);
                }
            };
            channel.basicConsume("sendTagihantoPdam", true, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            System.out.println("ERROR REGISTRASI NASABAH = " + e);
        }
    }

    public void addTransaksi() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("addTransaksi", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + mhsString + "'");
                transaksiDao.addTransaksi(mhsString);
            };
            channel.basicConsume("addTransaksi", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Add Transaksi = " + e);
        }
    }

    public void getNasabah() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("getNasabah1", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String nbString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] 'Receive from user'" + nbString + "'");
                connectJPANasabah();
                try{
                    Nasabah dumdum = nasabahDao.getNasabah(nbString);
                    String tagihan =new Gson().toJson(dumdum);
                    send.getNasabah(tagihan);
                    System.out.println(tagihan);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error send transfer: " + e);
                }
            };
            channel.basicConsume("getNasabah1", true, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            System.out.println("ERROR REGISTRASI NASABAH = " + e);
        }
    }
}