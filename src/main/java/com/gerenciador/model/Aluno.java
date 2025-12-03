package com.gerenciador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alunos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Exercicio> exercicios = new ArrayList<>();
    
    public Aluno(String nome) {
        this.nome = nome;
    }
}