package org.example.controller;

import org.example.model.Pdam;
import org.example.model.Pelanggan;
import org.example.repository.PdamRepository;
import org.example.repository.PelangganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PdamController {

    @Autowired
    PdamRepository pdamRepository;

    @Autowired
    PelangganRepository pelangganRepository;


    @GetMapping("/pdam")
    public ResponseEntity<List<Pdam>> getEmployees(@RequestParam(required = false) String args){
        List<Pdam> employees = new ArrayList<Pdam>();

        pdamRepository.findAll().forEach(employees::add);

        if(employees.isEmpty()){
            return new ResponseEntity<>(employees, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/addPdam")
    public ResponseEntity<Pdam> createPdam(@RequestBody Pdam pdam) {
        Pdam addPdam = pdamRepository.save(pdam);
        return new ResponseEntity<>(addPdam, HttpStatus.CREATED);
    }

    @GetMapping("/pelanggan")
    public ResponseEntity<List<Pelanggan>> getPelanggan(@RequestParam(required = false) String args){
        List<Pelanggan> pelanggan = new ArrayList<Pelanggan>();

        pelangganRepository.findAll().forEach(pelanggan::add);

        if(pelanggan.isEmpty()){
            return new ResponseEntity<>(pelanggan, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pelanggan, HttpStatus.OK);
    }

    @PostMapping("/addPelanggan")
    public ResponseEntity<Pelanggan> createPelanggan(@RequestBody Pelanggan pelanggan) {
        Pelanggan addPelanggan = pelangganRepository.save(pelanggan);
        return new ResponseEntity<>(addPelanggan, HttpStatus.CREATED);
    }

    @GetMapping("/pelanggan/{idPelanggan}")
    public ResponseEntity<Pelanggan> getTutorialById(@PathVariable("idPelanggan") long idPelanggan) {
        Optional<Pelanggan> pelangganData = pelangganRepository.findById(idPelanggan);

        if (pelangganData.isPresent()) {
            return new ResponseEntity<>(pelangganData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
