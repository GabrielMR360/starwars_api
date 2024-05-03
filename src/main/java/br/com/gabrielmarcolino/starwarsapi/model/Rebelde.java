package br.com.gabrielmarcolino.starwarsapi.model;

import br.com.gabrielmarcolino.starwarsapi.model.enums.GeneroEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rebelde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @ManyToOne(cascade = CascadeType.ALL)
    private Localizacao localizacao;

    @OneToOne(cascade = CascadeType.ALL)
    private Inventario inventario;

    @Column(name = "quantidade_reports")
    private int quantidadeReports;

    @Column(name = "traidor")
    private boolean isTraidor;
}
