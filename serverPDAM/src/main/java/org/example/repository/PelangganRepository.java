package org.example.repository;

import org.example.model.Pdam;
import org.example.model.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PelangganRepository extends JpaRepository<Pelanggan, Long> {
    List<Pelanggan> findAll();
}
