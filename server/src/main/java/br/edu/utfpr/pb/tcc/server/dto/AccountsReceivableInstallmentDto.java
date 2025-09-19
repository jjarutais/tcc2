package br.edu.utfpr.pb.tcc.server.dto;

import br.edu.utfpr.pb.tcc.server.enumeration.InstallmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountsReceivableInstallmentDto {

    private Long id;

    @NotNull
    private Long accountsReceivableId;

    @NotNull
    private Integer installmentNumber;

    private Long chargeMethodId;

    private Long categoryId;

    private Long cashBoxId;

    @NotNull
    private BigDecimal amount;

    private BigDecimal amountPaid;

    @NotNull
    private LocalDate dueDate;

    private LocalDate paymentDate;

    private InstallmentStatus status;

    private String notes;

    private Long updatedByUserId;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    private Long createdByUserId;

    private LocalDateTime canceledAt;
}