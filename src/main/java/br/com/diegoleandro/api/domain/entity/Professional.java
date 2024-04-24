package br.com.diegoleandro.api.domain.entity;

import br.com.diegoleandro.api.domain.entity.enums.CargoEnum;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "profissionais")
public class Professional {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "nome",nullable = false, length = 200)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", length = 20)
    private CargoEnum cargo;

    @NotNull
    @Column(nullable = false, name = "nascimento")
    private LocalDate nascimento;

    @NotNull
    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate = LocalDateTime.now().withNano(0);


    public Professional(String diego, CargoEnum cargoEnum, LocalDateTime parse) {
    }
}
