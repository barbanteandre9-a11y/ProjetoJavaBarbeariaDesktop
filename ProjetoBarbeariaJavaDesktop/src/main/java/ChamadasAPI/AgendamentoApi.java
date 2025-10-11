package ChamadasAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import responses.ResponseAppointmentsJson;
import entities.Appointment;
import java.io.IOException; // Adicionar este import é uma boa prática
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class AgendamentoApi {
    
     private static final String API_URL = "http://localhost:5156/api/appointments";
     
     private final HttpClient httpClient;
     private final ObjectMapper objectMapper;
    
    public AgendamentoApi() {
        
        // Inicialização padrão do HttpClient
        this.httpClient = HttpClient.newHttpClient(); 
        
        // Inicialização do ObjectMapper
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
        
    public List<Appointment> buscarTodosAgendamentos() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

            // Sincronamente envia e recebe a resposta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                
                ResponseAppointmentsJson jsonResponse = objectMapper.readValue(
                    response.body(), 
                    ResponseAppointmentsJson.class
                );
                
                return jsonResponse.appointments != null ? jsonResponse.appointments : Collections.emptyList();
            } else {
                System.err.println("Erro HTTP na API C#: Status " + response.statusCode() + " | Body: " + response.body());
                return Collections.emptyList();
            }

        } catch (IOException | InterruptedException e) { 
            System.err.println("Falha de comunicação ou processamento: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}