package br.com.sgv.controller;

import br.com.sgv.SolicitacaoForm;
import br.com.sgv.model.Cliente;
import br.com.sgv.model.Solicitacao;
import br.com.sgv.repository.ClienteRepository;
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

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/solicitacao")
    public String listar(Model model) {
        model.addAttribute("listaSolicitacoes", solicitacaoRepository.findAll());
        return "gerenciar_solicitacoes";
    }

    @GetMapping("/solicitacao/novo/{username}")
    public String novo(@PathVariable("username") String username, Model model) {
        Cliente cliente = clienteRepository.findClienteByUsername(username);
        Solicitacao solicitacao = new Solicitacao();
        SolicitacaoForm solicitacaoForm = new SolicitacaoForm();
        solicitacaoForm.setClienteId(cliente.getId());
        solicitacaoForm.setSolicitacao(solicitacao);
        model.addAttribute("solicitacaoForm", solicitacaoForm);
        return "editar_solicitacao";
    }

    @GetMapping("/solicitacao/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);

        SolicitacaoForm solicitacaoForm = new SolicitacaoForm();
        solicitacaoForm.setSolicitacao(solicitacao.get());
        solicitacaoForm.setClienteId(solicitacao.get().getCliente().getId());

        model.addAttribute("solicitacaoForm", solicitacaoForm);
        return "editar_solicitacao";
    }

    @PostMapping("/solicitacao")
    public String salvar(@Valid SolicitacaoForm solicitacaoForm, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_solicitacao";
        }

        Optional<Cliente> cliente = clienteRepository.findById(solicitacaoForm.getClienteId());

        if (cliente.isPresent()) {
            Solicitacao solicitacao = solicitacaoForm.getSolicitacao();
            solicitacao.setCliente(cliente.get());
            solicitacaoRepository.save(solicitacao);
        }else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        return "redirect:/solicitacao";
    }

    @DeleteMapping("/solicitacao/{id}")
    public String excluir(@PathVariable("id") long id) {
        solicitacaoRepository.deleteById(id);
        return "redirect:/solicitacao";
    }
}
