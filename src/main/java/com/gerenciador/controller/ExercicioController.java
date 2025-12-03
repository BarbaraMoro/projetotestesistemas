package com.gerenciador.controller;

import com.gerenciador.service.AlunoService;
import com.gerenciador.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/")
public class ExercicioController {
    
    @Autowired
    private ExercicioService exercicioService;
    
    @Autowired
    private AlunoService alunoService;
    
    @GetMapping
    public String index(Model model) {
        exercicioService.inicializarExercicios();
        model.addAttribute("exercicios", exercicioService.listarTodos());
        model.addAttribute("alunos", alunoService.listarTodos());
        return "index";
    }
    
    @PostMapping("/exercicio/atribuir")
    public String atribuirExercicio(@RequestParam Long exercicioId, 
                                     @RequestParam(required = false) Long alunoId,
                                     RedirectAttributes redirectAttributes) {
        try {
            exercicioService.atribuirAluno(exercicioId, alunoId);
            redirectAttributes.addFlashAttribute("mensagem", "Exercício atribuído com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "success");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensagem", e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "error");
        }
        return "redirect:/";
    }
    
    @GetMapping("/exportar")
    public ResponseEntity<byte[]> exportarDistribuicao() {
        StringBuilder sb = new StringBuilder();
        sb.append("DISTRIBUIÇÃO DE EXERCÍCIOS\n");
        sb.append("=".repeat(50)).append("\n\n");
        
        exercicioService.listarTodos().forEach(ex -> {
            sb.append(ex.getTitulo()).append("\n");
            sb.append("Responsável: ")
              .append(ex.getAluno() != null ? ex.getAluno().getNome() : "NÃO ATRIBUÍDO")
              .append("\n");
            sb.append("-".repeat(50)).append("\n\n");
        });
        
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "distribuicao-exercicios.txt");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }
}