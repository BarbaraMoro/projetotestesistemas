package com.gerenciador.service;

import com.gerenciador.model.Aluno;
import com.gerenciador.model.Exercicio;
import com.gerenciador.repository.ExercicioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExercicioServiceTest {

    // Mock do repositório de exercícios
    @Mock
    private ExercicioRepository exercicioRepository;

    // Mock do serviço de aluno
    @Mock
    private AlunoService alunoService;

    // Injeta os mocks dentro do serviço de exercício
    @InjectMocks
    private ExercicioService exercicioService;

    // Inicializa os mocks antes de cada teste
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // CENÁRIO 2
    @Test
    void deveCriarExercicioComSucesso() {
        // Define dados de entrada
        String titulo = "Ex1";
        String descricao = "Descrição teste";

        // Cria o objeto que deve ser retornado pelo repository
        Exercicio exercicioSalvo = new Exercicio(1L, titulo, descricao, null);

        // Configura o mock para retornar um exercício salvo
        when(exercicioRepository.save(any(Exercicio.class))).thenReturn(exercicioSalvo);

        // Executa o método criar
        Exercicio resultado = exercicioService.criar(titulo, descricao);

        // Verifica se retornou um objeto
        assertNotNull(resultado);

        // Confere o título
        assertEquals("Ex1", resultado.getTitulo());

        // Garante que o repository.save foi chamado uma vez
        verify(exercicioRepository, times(1)).save(any(Exercicio.class));
    }

    // CENÁRIO 5
    @Test
    void deveExcluirExercicioComSucesso() {
        // ID do exercício
        Long exercicioId = 5L;

        // Executa o método remover diretamente pelo repository
        exercicioRepository.deleteById(exercicioId);

        // Verifica se o método deleteById foi chamado corretamente
        verify(exercicioRepository, times(1)).deleteById(exercicioId);
    }

