package org.example.database.repositories;

import org.example.database.model.Nasabah;
import org.example.database.model.Tagihan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class NasabahDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public NasabahDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public int addNasabah(Nasabah nasabah){
        entityManager.persist(nasabah);
        return nasabah.getIdNasabah();
    }

    //ambil id saldo
    public int checkSaldoId(Nasabah nasabah){
        String select = "SELECT saldo FROM Nasabah WHERE idNasabah=:idNasabah";
        Query query = entityManager.createQuery(select);
        query.setParameter("idNasabah", nasabah.getIdNasabah());
        if (query.getResultList().size()!=0){
            System.out.println("masuk cek saldo 12");
            return (int)query.getResultList().get(0);
        } else {
            System.out.println("masuk cek saldo 22");
            return query.getResultList().size();
        }
    }

    public Nasabah getNasabah(String userString) {
        Nasabah mhs;
        try {
            mhs = entityManager.createQuery("SELECT a FROM Nasabah a where a.idNasabah ='"+userString+"'", Nasabah.class).getSingleResult();
        } catch (NoResultException e){
            System.out.println("no Result");
            mhs = null;

        }
        return mhs;
    }
}
