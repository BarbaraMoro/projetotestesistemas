package com.gerenciador.repository;

import com.gerenciador.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByNome(String nome);
    boolean existsByNome(String nome);
}