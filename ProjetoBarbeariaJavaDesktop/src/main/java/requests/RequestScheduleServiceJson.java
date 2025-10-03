package requests;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// O enum PaymentType precisaria ser definido em Java também.
// Exemplo:
// public enum PaymentType {
//     CREDIT_CARD,
//     DEBIT_CARD,
//     CASH,
//     PIX
// }

public class RequestScheduleServiceJson {

    @NotNull(message = "A data do agendamento deve ser informada")
    @FutureOrPresent(message = "A data do agendamento deve ser válida") // Garante que a data não está no passado
    private LocalDateTime appointmentDateTime;

    @NotNull(message = "É obrigatório informar os serviços desejados")
    @NotEmpty(message = "Pelo menos um serviço deve ser selecionado")
    private List<Long> serviceIds = new ArrayList<>();

    @Size(min = 1, max = 200, message = "O nome do cliente deve ser válido")
    private String clientName;

    @Size(min = 1, max = 15, message = "O telefone do cliente deve ser válido")
    private String clientPhone;

    private Long clientId;

    @NotNull // Assumindo que o EmployeeId é obrigatório, assim como no C# (long não pode ser nulo)
    private Long employeeId;

    @NotNull(message = "Método de pagamento não informado")
    // A validação de Enum em Java geralmente é feita com @NotNull.
    // A anotação @Range não é aplicável diretamente a enums da mesma forma.
    // Se você precisar garantir que um valor numérico específico seja enviado,
    // o tipo no DTO deveria ser Integer e então o @Range poderia ser usado.
    private PaymentType paymentType;

    // Getters e Setters

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
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