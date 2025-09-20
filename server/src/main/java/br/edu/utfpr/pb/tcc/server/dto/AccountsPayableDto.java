package br.edu.utfpr.pb.tcc.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsPayableDto {

    private Long id;

    private Long cashBoxId;

    @NotNull
    private Long chargeMethodId;

    @NotNull
    private Long clientSupplierId;

    private Long categoryId;

    @NotNull
    private BigDecimal totalAmount;

    @NotNull
    private Integer numberOfInstallments;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;

    private BigDecimal initialPayment;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    private Long createdByUserId;
}