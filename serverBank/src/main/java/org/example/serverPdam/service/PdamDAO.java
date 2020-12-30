package org.example.serverPdam.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PdamDAO {
    private static EntityManager entityManager;
    public EntityTransaction entityTransaction;

    public PdamDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public static int getSaldo(String nb) {
        String query = "SELECT * FROM tagihan WHERE no_tagihan=:not_tagihan";
        Query q = entityManager.createQuery(query);
        q.setParameter("no_rek", nb);
        if (q.getResultList().size()!=0){
            return (int)q.getResultList().get(0);
        } else {
            return q.getResultList().size();
        }
    }
}
