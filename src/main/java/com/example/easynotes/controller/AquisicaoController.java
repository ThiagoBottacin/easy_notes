package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Aquisicao;
import com.example.easynotes.repository.AquisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AquisicaoController {

    @Autowired
    AquisicaoRepository aquisicaoRepository;

    // Get All Aquisicoes
    @GetMapping("/aquisicoes")
    public List<Aquisicao> getAllAquisicoes(){
        return aquisicaoRepository.findAll();
    }

    // Create a new Aquisicao
    @PostMapping("/aquisicoes")
    public Aquisicao createAquisicao(@Valid @RequestBody Aquisicao aquisicao) {
        return aquisicaoRepository.save(aquisicao);
    }

    // Get a Single Aquisicao
    @GetMapping("/aquisicoes/{id}")
    public Aquisicao getAquisicaoById(@PathVariable(value = "id") Long aquisicaoId) {
        return aquisicaoRepository.findById(aquisicaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aquisicao", "id", aquisicaoId));
    }

    // Update a Aquisicao
    @PutMapping("/aquisicoes/{id}")
    public Aquisicao updateAquisicao(@PathVariable(value = "id") Long aquisicaoId,
                           @Valid @RequestBody Aquisicao aquisicaoDetails) {

        Aquisicao aquisicao = aquisicaoRepository.findById(aquisicaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aquisicao", "id", aquisicaoId));

        aquisicao.setTitle(aquisicaoDetails.getTitle());

        Aquisicao updatedAquisicao = aquisicaoRepository.save(aquisicao);
        return updatedAquisicao;
    }

    // Delete a Aquisicao
    @DeleteMapping("/aquisicoes/{id}")
    public ResponseEntity<?> deleteAquisicao(@PathVariable(value = "id") Long aquisicaoId) {
        Aquisicao aquisicao = aquisicaoRepository.findById(aquisicaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aquisicao", "id", aquisicaoId));

        aquisicaoRepository.delete(aquisicao);

        return ResponseEntity.ok().build();
    }
}
