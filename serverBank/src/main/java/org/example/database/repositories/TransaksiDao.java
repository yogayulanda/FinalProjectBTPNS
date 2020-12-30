package org.example.database.repositories;

import com.google.gson.Gson;
import org.example.database.model.Nasabah;
import org.example.database.model.Transaksi;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransaksiDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public TransaksiDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public void addTransaksi(String transaksi){
       Transaksi transaksinew= new Gson().fromJson(transaksi, Transaksi.class);
       entityManager.persist(transaksinew);
    }
}
