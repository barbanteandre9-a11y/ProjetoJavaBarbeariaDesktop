// Local: enums/AppointmentStatus.java

package enums;

// Adicione este import
import com.fasterxml.jackson.annotation.JsonValue; 

public enum AppointmentStatus {
    NAOSEI(0, "Teste"),
    PENDENTE(1, "Aguardando confirmação"),
    CONFIRMADO(2, "Agendamento confirmado"),
    CONCLUÍDO(3, "Atendimento concluído"); 

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