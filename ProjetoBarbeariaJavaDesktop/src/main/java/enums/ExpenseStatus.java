/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author ferna
 */
public enum ExpenseStatus {
    PAID(1, "Paga"),
    PENDING(2, "Pendente de pagamento"),
    SCHEDULED(3, "Pagamento agendado"),
    OVERDUE(4, "Pagamento atrasado"),
    INSTALLMENTS_REMAINING(5, "Faltam parcelas");

    private final int value;
    private final String description;

    ExpenseStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}