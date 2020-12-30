package org.example.database;

import org.example.database.rabbitmq.DatabaseReceiver;

public class DatabaseMain {
    public static DatabaseReceiver receive = new DatabaseReceiver();

    public static void main(String[] args) {
        try{
            System.out.println(" [*] Waiting for messages..");
            receive.addNasabah();
            receive.loginNasabah();
            receive.logoutNasabah();
            receive.checkSaldo();
            receive.getTagihan();
            receive.getTagihanPdamFinal();
            receive.addTransaksi();
            receive.getNasabah();
        }catch (Exception e){
            System.out.println("Error DatabaseMain = " + e);
        }
    }
}
