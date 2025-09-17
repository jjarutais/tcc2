package br.edu.utfpr.pb.tcc.server.dto;

import br.edu.utfpr.pb.tcc.server.annotation.UniqueCpf;
import br.edu.utfpr.pb.tcc.server.annotation.UniqueCnpj;
import br.edu.utfpr.pb.tcc.server.annotation.ValidCnpj;
import br.edu.utfpr.pb.tcc.server.annotation.ValidCpf;
import br.edu.utfpr.pb.tcc.server.enumeration.ClientSupplierType;
import br.edu.utfpr.pb.tcc.server.enumeration.EntityType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientSupplierDto {

    private Long id;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.client_supplier.name.NotNull}")
    @Size(min = 2, max = 255, message = "{br.edu.pb.utfpr.tcc.server.client_supplier.name.Size}")
    private String name;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.client_supplier.email.NotNull}")
    @Email(message = "{br.edu.pb.utfpr.tcc.server.client_supplier.email.Valid}")
    private String email;

    private String phone;

    private String address;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.client_supplier.type.NotNull}")
    private ClientSupplierType type;

    private EntityType entityType;

    @UniqueCpf
    @ValidCpf
    private String cpf;

    @UniqueCnpj
    @ValidCnpj
    private String cnpj;

    private String companyName;
}