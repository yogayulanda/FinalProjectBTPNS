package com.example.pdamapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Nasabah {
    @SerializedName("idNasabah")
    @Expose
    int idNasabah;

    @SerializedName("noTelp")
    @Expose
    String noTelp;

    @SerializedName("fullname")
    @Expose
    String fullname;

    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("gender")
    @Expose
    String gender;
    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("saldo")
    @Expose
    int saldo;
    @SerializedName("tglLahir")
    @Expose
    LocalDate tglLahir;

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public LocalDate getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(LocalDate tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

