package org.example.database.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tagihan")
public class Tagihan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_transaksi")
    private int id_transaksi;

    @Column(name = "nama_pelanggan")
    private String nama_pelanggan;

    @Column(name = "total_tagihan")
    private String total_tagihan;

    @CreationTimestamp
    @Column(name = "transaction_date", insertable = false, updatable = false, nullable = false)
    private Date transaction_date;

    public Tagihan() {
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTotal_tagihan() {
        return total_tagihan;
    }

    public void setTotal_tagihan(String total_tagihan) {
        this.total_tagihan = total_tagihan;
    }
}


