package com.gerenciador.service;

import com.gerenciador.model.Aluno;
import com.gerenciador.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
    
    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }
    
    public Optional<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }
    
    @Transactional
    public Aluno adicionar(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do aluno não pode ser vazio");
        }
        
        if (alunoRepository.existsByNome(nome.trim())) {
            throw new IllegalArgumentException("Aluno já cadastrado");
        }
        
        Aluno aluno = new Aluno(nome.trim());
        return alunoRepository.save(aluno);
    }
    
    @Transactional
    public void remover(Long id) {
        alunoRepository.deleteById(id);
    }
}
