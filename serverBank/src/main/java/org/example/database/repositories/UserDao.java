package org.example.database.repositories;

import org.example.database.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class UserDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public int addUser(User user){
        entityManager.persist(user);
        return user.getIdNasabah();
    }

    public int login(User user){
        String select = "SELECT userId FROM User WHERE username=:username and password=:password";
        Query query = entityManager.createQuery(select);
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        if (query.getResultList().size()!=0){
            System.out.println("masuk 1");
            return (int)query.getResultList().get(0);
        } else {
            System.out.println("masuk 2");
            return query.getResultList().size();
        }

    }

    public int updateStatusLogin(User user,String status){
        User user1 = entityManager.find(User.class,user.getUserId());
        user1.setIsLoggedIn(status);
        entityManager.merge(user1);
        return 1;
    }

    // function ambil userId
    public int checkUser(User user){
        String select = "SELECT userId FROM User WHERE username=:username";
        Query query = entityManager.createQuery(select);
        query.setParameter("username", user.getUsername());
        if (query.getResultList().size()!=0){
            System.out.println("masuk 12");
            return (int)query.getResultList().get(0);
        } else {
            System.out.println("masuk 22");
            return query.getResultList().size();
        }
    }

    //function kondisi ambil status login dengn no telp
    public int checkUserSaldo(String username){
        String select = "SELECT idNasabah FROM User WHERE username=:username AND isLoggedIn=:isLoggedIn";
        Query query = entityManager.createQuery(select);
        query.setParameter("username", username);
        query.setParameter("isLoggedIn", "true");
        if (query.getResultList().size()!=0){
            System.out.println("masuk 12");
            return (int)query.getResultList().get(0);
        } else {
            System.out.println("masuk 22");
            return query.getResultList().size();
        }
    }
}


