package com.example.easynotes.repository;

import com.example.easynotes.model.Aquisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AquisicaoRepository extends JpaRepository<Aquisicao, Long> {
}
