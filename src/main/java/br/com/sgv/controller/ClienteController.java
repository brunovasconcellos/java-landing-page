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
        ClienteForm clienteForm = new ClienteForm();
        Usuario usuario = usuarioRepository.findByLogin(username);
        clienteForm.setUsuario(usuario);
        clienteForm.setCliente(new Cliente());
        model.addAttribute("clienteForm",clienteForm);
        return "editar_cliente";
    }

    @GetMapping("/cliente-usuario")
    public String novoClienteUsuario(Model model) {
        ClienteForm clienteForm = new ClienteForm();
        clienteForm.setUsuario(new Usuario());
        clienteForm.setCliente(new Cliente());
        model.addAttribute("clienteForm",clienteForm);
        return "cadastro_cliente";
    }

    @GetMapping("/cliente/{id}/{username}")
    public String editar(@PathVariable("id") long id, @PathVariable("username") String username, Model model) {
        ClienteForm clienteForm = new ClienteForm();
        Usuario usuario = usuarioRepository.findByLogin(username);
        clienteForm.setUsuario(usuario);
        Optional<Cliente> cliente = clienteRepository.findById(id);
        clienteForm.setCliente(cliente.get());

        model.addAttribute("clienteForm", clienteForm);
        return "editar_cliente";
    }

    @PostMapping("/cliente")
    public String salvar(@Valid ClienteForm clienteForm, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_cliente";
        }

        Cliente cliente = clienteForm.getCliente();
        long usuarioId = clienteForm.getUsuario().getId();
        Optional<Usuario> loadedUsuario = usuarioRepository.findById(usuarioId);
        
        if (loadedUsuario.isPresent()) {
            cliente.setLogin(loadedUsuario.get().getLogin());
            cliente.setSenha(loadedUsuario.get().getSenha());
            cliente.setUsuario(loadedUsuario.get());
            clienteRepository.save(cliente);
            return "redirect:/cliente";
        } else {
            cliente.setLogin(clienteForm.getUsuario().getLogin());
            cliente.setSenha(clienteForm.getUsuario().getSenha());
            cliente.setUsuario(clienteForm.getUsuario());
            clienteRepository.save(cliente);
            return "redirect:/solicitacao";
        }
    }

    @DeleteMapping("/cliente/{id}")
    public String excluir(@PathVariable("id") long id) {
        clienteRepository.deleteById(id);
        return "redirect:/cliente";
    }
}
