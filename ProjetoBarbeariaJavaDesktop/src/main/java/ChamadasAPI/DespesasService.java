package ChamadasAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import entities.Appointment;
import entities.Expense;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import pagination.RequestExpensesPaginationParamsJson;
import responses.ResponseAppointmentsJson;
import responses.ResponseExpensesJson;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ferna
 */
public class DespesasService {
    private static final String API_URL = "http://localhost:5156/api/expenses";
     
     private final HttpClient httpClient;
     private final ObjectMapper objectMapper;
    
    public DespesasService() {
        
        // Inicialização padrão do HttpClient
        this.httpClient = HttpClient.newHttpClient(); 
        
        // Inicialização do ObjectMapper
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
        
    /**
     * Busca as despesas da barbearia com base em filtros
     * @param pagination
     * @return
     */
    public List<Expense> buscarDespesas(RequestExpensesPaginationParamsJson pagination) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                
                ResponseExpensesJson jsonResponse = objectMapper.readValue(
                    response.body(), 
                    ResponseExpensesJson.class
                );
                
                return jsonResponse.expenses != null ? jsonResponse.expenses : Collections.emptyList();
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
