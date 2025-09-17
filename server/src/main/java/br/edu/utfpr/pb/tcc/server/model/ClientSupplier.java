package br.edu.utfpr.pb.tcc.server.model;

import br.edu.utfpr.pb.tcc.server.enumeration.ClientSupplierType;
import br.edu.utfpr.pb.tcc.server.enumeration.EntityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "client_supplier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.client_supplier.name.NotNull}")
    @Size(min = 2, max = 255, message = "{br.edu.pb.utfpr.tcc.server.client_supplier.name.Size}")
    private String name;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.client_supplier.email.NotNull}")
    @Email(message = "{br.edu.pb.utfpr.tcc.server.client_supplier.email.Valid}")
    @Column(unique = true)
    private String email;

    private String phone;

    private String address;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.client_supplier.type.NotNull}")
    @Enumerated(EnumType.STRING)
    private ClientSupplierType type;

    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    private String cpf;

    private String cnpj;

    private String companyName;
}