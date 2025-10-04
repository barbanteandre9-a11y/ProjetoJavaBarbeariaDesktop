/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author andre
 */
public enum AppointmentStatus {
    PENDING(1, "Aguardando confirmação"),
    CONFIRMED(2, "Agendamento confirmado"),
    COMPLETED(3, "Atendimento concluído");

    private final int value;
    private final String description;

    AppointmentStatus(int value, String description) {
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
