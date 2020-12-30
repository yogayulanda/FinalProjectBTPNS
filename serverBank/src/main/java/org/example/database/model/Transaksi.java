package org.example.database.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_transaksi")
    private int id_pelanggan;

    @Column(name = "total_transaksi")
    private int total_transaksi;

    @Column(name = "idNasabah")
    private int idNasabah;

    @CreationTimestamp
    @Column(name = "transaction_date", insertable = false, updatable = false, nullable = false)
    private Date transaction_date;

    public Transaksi() {
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public int getTotal_transaksi() {
        return total_transaksi;
    }

    public void setTotal_transaksi(int total_transaksi) {
        this.total_transaksi = total_transaksi;
    }

    public int getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(int idNasabah) {
        this.idNasabah = idNasabah;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }
}
