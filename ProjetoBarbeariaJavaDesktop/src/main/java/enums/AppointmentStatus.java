// Local: enums/AppointmentStatus.java

package enums;

// Adicione este import
import com.fasterxml.jackson.annotation.JsonValue; 

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

    @JsonValue 
    public int getValue() {
        return value; 
    }

    public String getDescription() {
        return description;
    }
}