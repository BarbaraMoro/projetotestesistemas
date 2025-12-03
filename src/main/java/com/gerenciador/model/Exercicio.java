package com.gerenciador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exercicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    
    public Exercicio(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
