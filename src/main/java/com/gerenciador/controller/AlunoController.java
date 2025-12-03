package com.gerenciador.controller;

import com.gerenciador.model.Aluno;
import com.gerenciador.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        model.addAttribute("aluno", new Aluno());
        return "alunos/lista";
    }
    
    @PostMapping("/adicionar")
    public String adicionar(@RequestParam String nome, RedirectAttributes redirectAttributes) {
        try {
            alunoService.adicionar(nome);
            redirectAttributes.addFlashAttribute("mensagem", "Aluno adicionado com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "success");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensagem", e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "error");
        }
        return "redirect:/alunos";
    }
    
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return alunoService.buscarPorId(id)
                .map(aluno -> {
                    model.addAttribute("aluno", aluno);
                    return "alunos/editar";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensagem", "Aluno não encontrado!");
                    redirectAttributes.addFlashAttribute("tipoMensagem", "error");
                    return "redirect:/alunos";
                });
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @RequestParam String nome, RedirectAttributes redirectAttributes) {
        try {
            Aluno aluno = alunoService.buscarPorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
            aluno.setNome(nome);
            alunoService.adicionar(nome);
            redirectAttributes.addFlashAttribute("mensagem", "Aluno atualizado com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar aluno");
            redirectAttributes.addFlashAttribute("tipoMensagem", "error");
        }
        return "redirect:/alunos";
    }
    
    @PostMapping("/remover/{id}")
    public String remover(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            alunoService.remover(id);
            redirectAttributes.addFlashAttribute("mensagem", "Aluno removido com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao remover aluno");
            redirectAttributes.addFlashAttribute("tipoMensagem", "error");
        }
        return "redirect:/alunos";
    }
}