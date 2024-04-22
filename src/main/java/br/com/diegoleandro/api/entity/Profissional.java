package br.com.diegoleandro.api.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "profissionais")
public class Profissional {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate nascimento;

    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    public Profissional() {
        this.createdDate = LocalDateTime.now();
    }

    public Profissional(String nome, String cargo, LocalDate nascimento) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.createdDate = LocalDateTime.now();
    }




}
