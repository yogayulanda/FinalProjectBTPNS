package org.example.database.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idNasabah")
    int idNasabah;

    @Column(name = "pelanggan")
    private int id_pelanggan;

    @Column(name = "noTelp")
    String noTelp;

    @Column(name = "fullname")
    String fullName;

    @Column(name = "address")
    String address;

    @Column(name = "gender")
    String gender;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "tglLahir")
    LocalDate tglLahir;
    int saldo;

    public Register() {
    }

    public int getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(int idNasabah) {
        this.idNasabah = idNasabah;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(LocalDate tglLahir) {
        this.tglLahir = tglLahir;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

}

