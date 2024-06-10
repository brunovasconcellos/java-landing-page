package br.com.sgv.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Cliente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String empresa;
    private String cnpj;
    private String cep;
    private String cidade;
    private String estado;
    private String bairro;
    private String complemento;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Usuario usuario;
    @OneToMany
    private Collection<Solicitacao> solicitacoes;
}
