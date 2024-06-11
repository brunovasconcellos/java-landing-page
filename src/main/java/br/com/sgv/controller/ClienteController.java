package br.com.sgv.controller;

import br.com.sgv.ClienteForm;
import br.com.sgv.model.Cliente;
import br.com.sgv.model.Usuario;
import br.com.sgv.repository.ClienteRepository;
import br.com.sgv.repository.UsuarioRepository;
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
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cliente")
    public String listar(Model model) {
        model.addAttribute("listaClientes", clienteRepository.findAll());
        return "gerenciar_clientes";
    }

    @GetMapping("/cliente/novo/{username}")
    public String novo(@PathVariable("username") String username, Model model) {
        Usuario usuario = usuarioRepository.findByLogin(username);
        ClienteForm clienteForm = new ClienteForm();
        clienteForm.setCliente(new Cliente());
        clienteForm.setUsuario(usuario);
        model.addAttribute("clienteForm",clienteForm);
        return "editar_cliente";
    }

    @GetMapping("/cliente/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        model.addAttribute("cliente", cliente.get());
        return "editar_cliente";
    }

    @PostMapping("/cliente")
    public String salvar(@Valid ClienteForm clienteForm, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_cliente";
        }

        Cliente cliente = clienteForm.getCliente();
        long usuarioId = clienteForm.getUsuario().getId();
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        
        if (usuario.isPresent()) {
            cliente.setUsuario(usuario.get());
        } else {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        clienteRepository.save(cliente);
        return "redirect:/cliente";
    }
//        Usuario usuario = usuarioRepository.findById(usuarioId)

    @DeleteMapping("/cliente/{id}")
    public String excluir(@PathVariable("id") long id) {
        clienteRepository.deleteById(id);
        return "redirect:/cliente";
    }
}
