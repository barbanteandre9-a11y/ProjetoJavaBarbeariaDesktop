/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagination.enums;

/**
 *
 * @author ferna
 */
public enum OrderByExpenseColumn {

    DUE_DATE("Ordernar pela data de vencimento da despesa", 1),
    PAYMENT_DATE("Ordernar pela data de pagamento da despesa", 2),
    STATUS("Ordernar pelo status da despesa", 3),
    AMOUNT("Ordernar pelo valor da despesa", 4);

    private final String description;
    private final int value;

    // Construtor
    OrderByExpenseColumn(String description, int value) {
        this.description = description;
        this.value = value;
    }

    // Getter para a descrição
    public String getDescription() {
        return description;
    }

    // Getter para o valor inteiro
    public int getValue() {
        return value;
    }
}
