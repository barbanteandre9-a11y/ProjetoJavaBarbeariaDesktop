package requests;

import enums.PaymentType;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class RequestScheduleServiceJson {

    @NotNull(message = "A data do agendamento deve ser informada")
    @FutureOrPresent(message = "A data do agendamento deve ser válida") 

    @NotNull(message = "É obrigatório informar os serviços desejados")
    @NotEmpty(message = "Pelo menos um serviço deve ser selecionado")
    private List<Long> serviceIds = new ArrayList<>();

    @Size(min = 1, max = 200, message = "O nome do cliente deve ser válido")
    private String clientName;

    @Size(min = 1, max = 15, message = "O telefone do cliente deve ser válido")
    private String clientPhone;
    
    private String appointmentDateTime;

    private Long clientId;

    @NotNull
    private Long employeeId;

    @NotNull(message = "Método de pagamento não informado")
    
    private PaymentType paymentType;

    // Getters e Setters

    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public List<Long> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<Long> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}