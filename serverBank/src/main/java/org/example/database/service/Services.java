package org.example.database.service;

import com.google.gson.Gson;
import org.example.database.model.Nasabah;
import org.example.database.model.Register;
import org.example.database.model.User;
import org.example.database.repositories.NasabahDao;
import org.example.database.repositories.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Services {
    private NasabahDao nasabahDao;
    private UserDao userDao;
    private EntityManager entityManagerNasabah;
    private EntityManager entityManagerUser;

    public void connectJPANasabah(){
        this.entityManagerNasabah = Persistence
                .createEntityManagerFactory("ibanking-unit")
                .createEntityManager();
        nasabahDao = new NasabahDao(entityManagerNasabah);
        try {
            entityManagerNasabah.getTransaction().begin();
        } catch (IllegalStateException e) {
            entityManagerNasabah.getTransaction().rollback();
        }
    }

    public void connectJPAUser(){
        this.entityManagerUser = Persistence
                .createEntityManagerFactory("ibanking-unit")
                .createEntityManager();
        userDao = new UserDao(entityManagerUser);
        try {
            entityManagerUser.getTransaction().begin();
        } catch (IllegalStateException e) {
            entityManagerUser.getTransaction().rollback();
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

    public void registerNasabah(String register){
        Register regis = new Gson().fromJson(register, Register.class);
        Nasabah nasabah = new Nasabah();
        nasabah.setIdNasabah(regis.getIdNasabah());
        nasabah.setFullName(regis.getFullName());
        nasabah.setAddress(regis.getAddress());
        nasabah.setGender(regis.getGender());
        nasabah.setSaldo(1000000);
        nasabah.setEmail(regis.getEmail());
        nasabah.setTglLahir(regis.getTglLahir());
        nasabah.setNoTelp(regis.getNoTelp());

        connectJPANasabah();
        int idNasabah = nasabahDao.addNasabah(nasabah);
        commitJPA(entityManagerNasabah);

        User user = new User();
        user.setUsername(regis.getUsername());
        user.setPassword(regis.getPassword());
        user.setIdNasabah(idNasabah);
        user.setIsLoggedIn("false");
        connectJPAUser();
        int userId = userDao.addUser(user);
        commitJPA(entityManagerUser);
    }

    public int loginUser(String dataUser){
        connectJPAUser();
        User user = new Gson().fromJson(dataUser, User.class);
        int cek =userDao.login(user);
        commitJPA(entityManagerUser);
        System.out.println("Cek: "+cek);
        return cek;
    }

    public int logoutUser(String dataUser){
        connectJPAUser();
        User user = new Gson().fromJson(dataUser, User.class);
        int cek =userDao.checkUser(user);
        commitJPA(entityManagerUser);
        System.out.println("Cek: "+cek);
        return cek;
    }

    //cek status user
    public int checkUser(String dataUser){
        connectJPAUser();
        int cek =userDao.checkUserSaldo(dataUser);
        commitJPA(entityManagerUser);
        System.out.println("Cek: "+cek);
        return cek;
    }


    public void updateStatusLogin(User user,String status){
        connectJPAUser();
        userDao.updateStatusLogin(user,status);
        commitJPA(entityManagerUser);
    }

    public int checkSaldo(Nasabah nasabah){
        connectJPANasabah();
        int saldo=nasabahDao.checkSaldoId(nasabah);
        System.out.println("Method Check Saldo: "+saldo);
        commitJPA(entityManagerNasabah);
        return saldo;
    }

}

