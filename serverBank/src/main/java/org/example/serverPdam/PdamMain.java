package org.example.serverPdam;

import org.example.serverPdam.rabbitmq.PdamReceiver;
import org.springframework.boot.SpringApplication;

public class PdamMain {
    public static PdamReceiver recv = new PdamReceiver();

    public static void main(String[] args) {
        SpringApplication.run(PdamMain.class, args);
        try {
            recv.getTagihan();
        } catch (Exception e) {
            System.out.println("Error DatabaseMain = " + e);
        }
    }
}