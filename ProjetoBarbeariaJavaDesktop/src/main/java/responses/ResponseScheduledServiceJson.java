/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;

/**
 *
 * @author andre
 */
import java.time.LocalDateTime;

/**
 * Representa a resposta da API com os detalhes de um serviço agendado.
 */
public class ResponseScheduledServiceJson {

    private LocalDateTime appointmentDateTime;
    private long servicePrice;
    private Long employeeId; // Usamos a classe wrapper 'Long' para permitir valores nulos (null)

    // Getters e Setters

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public long getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(long servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
