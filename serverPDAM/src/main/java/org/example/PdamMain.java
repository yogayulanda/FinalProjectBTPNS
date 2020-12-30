package org.example;

import org.example.database.rabbitmq.PdamReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdamMain {
    public static PdamReceiver recv = new PdamReceiver();

    public static void main(String[] args) {
        SpringApplication.run(PdamMain.class, args);
        try {
            recv.getTagihanPdam();
        } catch (Exception e) {
            System.out.println("Error DatabaseMain = " + e);
        }
    }
}
