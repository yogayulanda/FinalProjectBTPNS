package org.example.database.service;


import org.example.database.model.Tagihan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PdamDAO {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public PdamDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public void setIdPelanggan(String nbStr) {

    }

    public int getIdPelanggan(String mhsString){
        String select = "SELECT total_tagihan FROM Tagihan WHERE id_pelanggan=:id_pelanggan";
        Query query = entityManager.createQuery(select);
        query.setParameter("id_pelanggan", mhsString);
        if (query.getResultList().size()!=0){
            System.out.println("masuk 12");
            return (int)query.getResultList().get(0);
        } else {
            System.out.println("masuk 22");
            return query.getResultList().size();
        }
    }

//    public int getTagihan(int nb) {
//        String query = "SELECT n FROM Tagihan n WHERE n.id_pelanggan=:id_pelanggan";
//        Query q = entityManager.createQuery(query);
//        q.setParameter("id_pelanggan", nb);
//        if (q.getResultList().size()!=0){
//            return (int)q.getResultList().get(0);
//        } else {
//            return q.getResultList().size();
//        }
//    }

//    public String getNamaPelanggan (String nb) {
//        String query = "SELECT nama_pelanggan FROM Tagihan WHERE id_pelanggan=:id_pelanggan";
//        Query q = entityManager.createQuery(query);
//        q.setParameter("id_pelanggan", nb);
//        return String.valueOf(q.getResultList());
//    }

    public Tagihan getTagihan(String userString) {
        Tagihan mhs;
        try {
            mhs = entityManager.createQuery("SELECT a FROM Tagihan a where a.id_pelanggan ='"+userString+"'", Tagihan.class).getSingleResult();
        } catch (NoResultException e){
            System.out.println("no Result");
            mhs = null;

        }
        return mhs;
    }
}
