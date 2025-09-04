package br.edu.utfpr.pb.tcc.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements IActivatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType type;

    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}