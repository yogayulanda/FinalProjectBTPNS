package org.example.database.model;

import javax.persistence.*;

@Entity
@Table(name = "pelanggan")
public class Pelanggan {

    @Id
    @GeneratedValue
    @Column(name = "idPelanggan")
    private long idPelanggan;

    @Column(name = "idPdam")
    private long idPdam;

    @Column(name = "namaPelanggan")
    private String namaPelanggan;

    @Column(name = "totalTagihan")
    private int totalTagihan;

    @Column(name = "statusPembayaran")
    private String statusPembayaran;

    public long getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(long idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public long getIdPdam() {
        return idPdam;
    }

    public void setIdPdam(long idPdam) {
        this.idPdam = idPdam;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public int getTotalTagihan() {
        return totalTagihan;
    }

    public void setTotalTagihan(int totalTagihan) {
        this.totalTagihan = totalTagihan;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }
}
