package br.com.sgv.controller;

import br.com.sgv.model.Solicitacao;
import br.com.sgv.repository.SolicitacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SolicitacaoController {
    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @GetMapping("/solicitacao")
    public String listar(Model model) {
        model.addAttribute("listaSolicitacoes", solicitacaoRepository.findAll());
        return "gerenciar_solicitacoes";
    }

    @GetMapping("/solicitacao/novo")
    public String novo(Model model) {
        model.addAttribute("solicitacao", new Solicitacao());
        return "editar_solicitacao";
    }

    @GetMapping("/solicitacao/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);
        model.addAttribute("solicitacao", solicitacao.get());
        return "editar_solicitacao";
    }

    @PostMapping("/solicitacao")
    public String salvar(@Valid Solicitacao solicitacao, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_solicitacao";
        }

        solicitacaoRepository.save(solicitacao);
        return "redirect:/solicitacao";
    }

    @DeleteMapping("/solicitacao/{id}")
    public String excluir(@PathVariable("id") long id) {
        solicitacaoRepository.deleteById(id);
        return "redirect:/solicitacao";
    }
}
