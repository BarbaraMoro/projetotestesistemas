package com.gerenciador.service;

import com.gerenciador.model.Aluno;
import com.gerenciador.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    // Cria um mock do repositório de aluno
    @Mock
    private AlunoRepository alunoRepository;

    // Injeta os mocks automaticamente dentro do serviço
    @InjectMocks
    private AlunoService alunoService;

    // Executa antes de cada teste, inicializando os mocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // CENÁRIO 1
    @Test
    void deveCriarAlunoComSucesso() {
        // Define o nome de entrada
        String nome = "Maria";

        // Simula que nenhum aluno com esse nome existe ainda
        when(alunoRepository.existsByNome("Maria")).thenReturn(false);

        // Cria um objeto esperado de retorno do repositório
        Aluno alunoSalvo = new Aluno(1L, "Maria", new ArrayList<>());

        // Configura o mock para retornar o aluno salvo ao chamar save
        when(alunoRepository.save(any(Aluno.class))).thenReturn(alunoSalvo);

        // Executa o método que queremos testar
        Aluno resultado = alunoService.adicionar(nome);

        // Verifica se o retorno não é nulo
        assertNotNull(resultado);

        // Garante que o nome foi salvo corretamente
        assertEquals("Maria", resultado.getNome());

        // Garante que o método save foi chamado exatamente 1 vez
        verify(alunoRepository, times(1)).save(any(Aluno.class));
    }


    // CENÁRIO 3
    @Test
    void deveVincularAlunoAoExercicio() {
        // IDs de entrada
        Long exercicioId = 10L;
        Long alunoId = 20L;

        // Cria objetos simulados
        Exercicio exercicio = new Exercicio(exercicioId, "Titulo", "Desc", null);
        Aluno aluno = new Aluno(alunoId, "João", new ArrayList<>());

        // Simula encontrar o exercício
        when(exercicioRepository.findById(exercicioId)).thenReturn(Optional.of(exercicio));

        // Simula encontrar o aluno
        when(alunoService.buscarPorId(alunoId)).thenReturn(Optional.of(aluno));

        // Simula o save
        when(exercicioRepository.save(any(Exercicio.class))).thenReturn(exercicio);

        // Executa o método
        Exercicio resultado = exercicioService.atribuirAluno(exercicioId, alunoId);

        // Verifica se o aluno foi vinculado corretamente
        assertEquals("João", resultado.getAluno().getNome());

        // Verifica se save foi chamado
        verify(exercicioRepository, times(1)).save(exercicio);
    }

    // CENÁRIO 4
    @Test
    void deveLancarErroAoCriarAlunoJaExistente() {
        // Nome repetido
        String nome = "Maria";

        // Simula que aluno já existe
        when(alunoRepository.existsByNome("Maria")).thenReturn(true);

        // Valida se a exception foi lançada
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> alunoService.adicionar(nome)
        );

        // Verifica mensagem da exception
        assertEquals("Aluno já cadastrado", exception.getMessage());

        // Garante que o save não foi chamado
        verify(alunoRepository, never()).save(any(Aluno.class));
    }
}
