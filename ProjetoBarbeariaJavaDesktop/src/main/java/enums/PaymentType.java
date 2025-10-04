/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author andre
 */
public enum PaymentType {
    CreditCard(1, "Cartão de Crédito"),
    DebitCard(2, "Cartão de Débito"),
    Cash(3, "Dinheiro"),
    Pix(4, "Pix"),
    Other(5, "Outro");

    private final int value;
    private final String description;

    PaymentType(int value, String description) {
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