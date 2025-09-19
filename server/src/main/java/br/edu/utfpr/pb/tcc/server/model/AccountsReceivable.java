package br.edu.utfpr.pb.tcc.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts_receivable")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsReceivable {

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

    private String document;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "due_day")
    private Integer dueDay;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id")
    private User createdByUser;
}