package org.example.database.model;

import javax.persistence.*;

@Entity
@Table(name = "pdam")
public class Pdam {

    @Id
    @GeneratedValue
    @Column(name = "idPdam")
    private long idPdam;

    @Column(name = "namaPdam")
    private String namaPdam;

    public long getIdPdam() {
        return idPdam;
    }

    public void setIdPdam(long idPdam) {
        this.idPdam = idPdam;
    }

    public String getNamaPdam() {
        return namaPdam;
    }

    public void setNamaPdam(String namaPdam) {
        this.namaPdam = namaPdam;
    }
}