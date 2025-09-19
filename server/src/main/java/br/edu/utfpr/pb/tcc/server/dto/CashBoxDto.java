package br.edu.utfpr.pb.tcc.server.dto;

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
public class CashBoxDto {

    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String description;

    @NotNull
    private Boolean active;
}