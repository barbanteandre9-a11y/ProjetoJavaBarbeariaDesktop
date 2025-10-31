package entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ferna
 */
import enums.ExpenseStatus;
import enums.Recurrence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Expense {
    private long id;

    /**
     * Data de vencimento da despesa.
     * DateTime pois o Dapper não consegue mapear o tipo DateOnly
     */
    private LocalDateTime dueDate;

    /**
     * Data de pagamento da despesa.
     */
    private LocalDateTime paymentDate = null;

    /**
     * Valor da despesa
     */
    private BigDecimal amount;

    /**
     * Valor total pago da despesa até o momento
     */
    private BigDecimal paidAmount;

    /**
     * Valor da parcela da despesa. Caso seja uma conta parcelada.
     * Pode ser salvo como nulo caso não seja uma conta parcelada.
     */
    private BigDecimal amountOfInstallment = null;

    /**
     * Se o gasto está pago, pendente ou agendado para futuro pagamento.
     */
    private ExpenseStatus status;

    /**
     * Nome do fornecedor, empresa ou pessoa que recebeu o pagamento.
     */
    private String supplier = "";

    /**
     * Observações ou detalhes adicionais relevantes sobre a despesa.
     * Pode ser salvo como nulo caso não haja informações adicionais.
     */
    private String notes = null;

    /**
     * Indica se é um gasto recorrente, eventual, anual, parcelado, etc.
     */
    private Recurrence recurrence;

    /**
     * Número total de parcelas e quantas já foram pagas, caso a despesa seja parcelada.
     * Pode ser salvo como nulo caso não seja uma despesa parcelada.
     */
    private Long totalInstallments = null;

    /**
     * Número total de parcelas pagas até o momento.
     * Pode ser salvo como nulo caso não seja uma despesa parcelada.
     */
    private Long paidInstallments = null;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getAmountOfInstallment() {
        return amountOfInstallment;
    }

    public void setAmountOfInstallment(BigDecimal amountOfInstallment) {
        this.amountOfInstallment = amountOfInstallment;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public Long getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(Long totalInstallments) {
        this.totalInstallments = totalInstallments;
    }

    public Long getPaidInstallments() {
        return paidInstallments;
    }

    public void setPaidInstallments(Long paidInstallments) {
        this.paidInstallments = paidInstallments;
    }
}