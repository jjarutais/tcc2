package br.edu.utfpr.pb.tcc.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts_payable")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsPayable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashBox_id")
    private CashBox cashBox;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id")
    private ChargeMethod chargeMethod;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_supplier_id", nullable = false)
    private ClientSupplier clientSupplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @Column(name = "total_amount", precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;

    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "initial_payment", precision = 12, scale = 2)
    private BigDecimal initialPayment;

    private String document;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id")
    private User createdByUser;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_id")
    private User updatedByUser;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}