package br.com.sgv.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titulo;
    private String descricao;
    private String tipo_projeto;
    @ManyToOne
    private Cliente cliente;
}
