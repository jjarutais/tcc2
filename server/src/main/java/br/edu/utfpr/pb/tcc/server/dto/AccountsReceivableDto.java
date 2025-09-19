package br.edu.utfpr.pb.tcc.server.dto;

import br.edu.utfpr.pb.tcc.server.model.CashBox;
import br.edu.utfpr.pb.tcc.server.model.ChargeMethod;
import br.edu.utfpr.pb.tcc.server.model.ClientSupplier;
import br.edu.utfpr.pb.tcc.server.model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsReceivableDto {

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
    private Integer dueDay;

    private LocalDateTime createdAt;

    private Long createdByUserId;
}