package com.gerenciador.service;

import com.gerenciador.model.Aluno;
import com.gerenciador.model.Exercicio;
import com.gerenciador.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {
    
    @Autowired
    private ExercicioRepository exercicioRepository;
    
    @Autowired
    private AlunoService alunoService;
    
    public List<Exercicio> listarTodos() {
        return exercicioRepository.findAll();
    }
    
    public Optional<Exercicio> buscarPorId(Long id) {
        return exercicioRepository.findById(id);
    }
    
    public List<Exercicio> listarPorAluno(Long alunoId) {
        return exercicioRepository.findByAlunoId(alunoId);
    }
    
    @Transactional
    public Exercicio criar(String titulo, String descricao) {
        Exercicio exercicio = new Exercicio(titulo, descricao);
        return exercicioRepository.save(exercicio);
    }
    
    @Transactional
    public Exercicio atribuirAluno(Long exercicioId, Long alunoId) {
        Exercicio exercicio = exercicioRepository.findById(exercicioId)
                .orElseThrow(() -> new IllegalArgumentException("Exercício não encontrado"));
        
        if (alunoId == null) {
            exercicio.setAluno(null);
        } else {
            Aluno aluno = alunoService.buscarPorId(alunoId)
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
            exercicio.setAluno(aluno);
        }
        
        return exercicioRepository.save(exercicio);
    }
    
    @Transactional
public void inicializarExercicios() {
    if (exercicioRepository.count() == 0) {
        criar("1. Relacionamento (Paciente - Consulta – Médico)",
            "Crie um modelo ER para o relacionamento entre as entidades Paciente, Consulta e Médico, onde a Consulta é a entidade que relaciona Paciente e Médico. Defina a cardinalidade de cada relacionamento (um paciente pode ter várias consultas, e cada consulta é com um médico específico).\n\nDesafio: Realize uma consulta SQL para listar todos as consultas de um paciente específico, com os médicos detalhados em cada consulta.");
        
        criar("2. Relacionamento (Cliente - Pedido – Produto)",
            "Crie um banco de dados com três tabelas: Cliente, Pedido e Produto. A tabela Pedido será o relacionamento entre Cliente e Produto, onde um pedido pode conter vários produtos e um cliente pode fazer vários pedidos. Insira pelo menos 5 clientes, 3 produtos e 4 pedidos.\n\nDesafio: Realize uma consulta SQL para listar todos os pedidos de um cliente específico, com os produtos detalhados em cada pedido.");
        
        criar("3. Relacionamento (Funcionário - Projeto – Departamento)",
            "Desenhe um diagrama ER onde Projeto representa o relacionamento entre as entidades Funcionário e Departamento. Um projeto pode envolver vários funcionários e estar associado a um departamento específico. Insira alguns dados no banco para associar 4 funcionários a dois departamentos diferentes.\n\nDesafio: Escreva uma consulta SQL que mostre quais funcionários estão alocados em um determinado departamento e em quais projetos estão envolvidos.");
        
        criar("4. Relacionamento (Aluno - Atividade Extracurricular - Professor)",
            "Crie as tabelas para um sistema onde Atividade Extracurricular representa o relacionamento entre Aluno e Professor. Cada professor orienta várias atividades, e cada aluno pode se inscrever em diversas atividades extracurriculares.\n\nDesafio: Insira dados e liste quais alunos estão inscritos nas atividades orientadas por um determinado professor.");
        
        criar("5. Relacionamento Motorista - Corrida - Aplicativo de Transporte",
            "Modelar as tabelas Motorista, Corrida e Aplicativo. A entidade Corrida deve armazenar os dados de cada viagem, relacionando um motorista a uma corrida específica feita através de um aplicativo de transporte.\n\nDesafio: Liste todas as corridas feitas por um motorista em um determinado mês e o total arrecadado por ele.");

        criar("6. Relacionamento (Estudante - Inscrição – Disciplina)",
            "Modele o relacionamento entre as entidades Estudante e Disciplina, usando Inscrição como o relacionamento. Cada estudante pode se inscrever em várias disciplinas, e cada disciplina tem vários estudantes.\n\nDesafio: Crie uma consulta SQL que mostre em quais disciplinas cada estudante está inscrito.");

        criar("7. Relacionamento (Jogador - Partida – Time)",
            "Crie o modelo onde Partida relaciona as entidades Jogador e Time. Um jogador pode participar de várias partidas e, em cada partida, ele joga para um time específico.\n\nDesafio: Insira dados e escreva uma consulta SQL para listar todas as partidas de um jogador e o time para o qual ele jogou em cada uma.");

        criar("8. Relacionamento (Hóspede - Reserva – Hotel)",
            "Crie o relacionamento entre as entidades Hóspede e Hotel, onde Reserva é a entidade intermediária. Um hóspede pode fazer várias reservas e cada reserva é associada a um hotel específico.\n\nDesafio: Liste todas as reservas de um hóspede em um determinado hotel.");

        criar("9. Relacionamento (Usuário - Compra - Loja Online)",
            "Modelar um sistema de compras online onde Compra é o relacionamento entre Usuário e Loja Online. Um usuário pode fazer várias compras, e cada compra está associada a uma loja específica.\n\nDesafio: Escreva uma consulta SQL para mostrar o histórico de compras de um usuário, incluindo a loja e a data de cada compra.");

        criar("10. Relacionamento (Artista - Álbum – Gravadora)",
            "Modele o relacionamento entre Artista e Gravadora, usando a entidade Álbum como intermediária. Um artista pode lançar vários álbuns, e cada álbum é distribuído por uma gravadora.\n\nDesafio: Insira dados e faça uma consulta SQL para listar todos os álbuns de um artista, incluindo a gravadora responsável por cada lançamento.");

        criar("11. Relacionamento (Paciente - Exame – Laboratório)",
            "Modele um relacionamento onde Exame é a entidade intermediária entre Paciente e Laboratório. Um paciente pode realizar vários exames, e cada exame é feito em um laboratório específico.\n\nDesafio: Liste todos os exames que um paciente realizou em um determinado laboratório.");

        criar("12. Relacionamento (Fornecedor - Pedido de Compra - Produto)",
            "Crie um relacionamento onde Pedido de Compra relaciona as entidades Fornecedor e Produto. Um fornecedor pode fornecer vários produtos, e um pedido pode incluir produtos de diferentes fornecedores.\n\nDesafio: Insira dados e crie uma consulta SQL que mostre quais produtos foram fornecidos por cada fornecedor.");

        criar("13. Relacionamento (Professor - Orientação – Aluno)",
            "Crie o modelo onde Orientação é a entidade que relaciona Professor e Aluno. Cada professor pode orientar vários alunos em seus trabalhos acadêmicos, e cada aluno pode ser orientado por um único professor.\n\nDesafio: Liste todos os alunos orientados por um professor específico, assim como os temas dos trabalhos orientados.");

        criar("14. Relacionamento (Ator - Papel – Filme)",
            "Modele o relacionamento onde Papel é a entidade intermediária entre Ator e Filme. Um ator pode desempenhar vários papéis em diferentes filmes, e um filme pode ter vários atores.\n\nDesafio: Insira dados e crie uma consulta SQL que mostre todos os papéis desempenhados por um ator em filmes diferentes.");

        criar("15. Relacionamento (Autor - Publicação – Editora)",
            "Crie o modelo onde Publicação é o relacionamento entre Autor e Editora. Um autor pode publicar vários livros, e cada publicação está associada a uma editora.\n\nDesafio: Liste todas as publicações de um autor e a editora correspondente a cada publicação.");

        criar("16. Relacionamento (Piloto - Voo - Companhia Aérea)",
            "Crie um relacionamento onde Voo é a entidade intermediária entre Piloto e Companhia Aérea. Um piloto pode realizar vários voos, e cada voo é operado por uma companhia aérea específica.\n\nDesafio: Liste todos os voos de um piloto para uma determinada companhia aérea.");

        criar("17. Relacionamento (Cliente - Aluguel – Imóvel)",
            "Crie um modelo onde Aluguel é o relacionamento entre Cliente e Imóvel. Um cliente pode alugar vários imóveis, e um imóvel pode ser alugado por diferentes clientes ao longo do tempo.\n\nDesafio: Insira dados e crie uma consulta SQL para mostrar o histórico de aluguéis de um imóvel específico, com as datas de cada aluguel e o cliente associado.");
    }
}
}