package br.edu.utfpr.pb.tcc.server.model;

import br.edu.utfpr.pb.tcc.server.enumeration.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

//Categoria = {id: Long, name: String}

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType type;
}