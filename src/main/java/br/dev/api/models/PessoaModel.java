package br.dev.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EnderecoModel> enderecos = new HashSet<>();

    public int getIdade() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}