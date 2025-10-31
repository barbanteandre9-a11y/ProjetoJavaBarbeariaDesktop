package ChamadasAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import responses.ResponseAppointmentsJson;
import entities.Appointment;
import java.io.IOException; 
import com.fasterxml.jackson.databind.JsonNode;
import entities.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import requests.RequestMarkServiceCompletedJson;
import requests.RequestScheduleServiceJson;

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
    
            public List<Service> buscarServicos() throws Exception {
 

            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .GET()
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    return objectMapper.readValue(
                        response.body(), 
                        new com.fasterxml.jackson.core.type.TypeReference<List<Service>>() {}
                    );
                } else {
                    System.err.println("Erro ao buscar serviços. Status: " + response.statusCode());
                    throw new Exception("Falha ao buscar serviços na API.");
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                throw new Exception("Falha de comunicação ao buscar serviços.");
            }
        }
    
    public Appointment criarAgendamento(RequestScheduleServiceJson novoAgendamento) {
        try {
            // Converte o objeto RequestScheduleServiceJson para uma string JSON
            String jsonRequestBody = objectMapper.writeValueAsString(novoAgendamento);

            // Constrói a requisição POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                    .header("Content-Type", "application/json")
                    .build();

            // Envia a requisição
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Trata a resposta
            if (response.statusCode() == 201 || response.statusCode() == 200) {
                try {
                    Appointment appointmentCriado = objectMapper.readValue(response.body(), Appointment.class);
                    System.out.println("Agendamento criado com sucesso: ID " + (appointmentCriado != null ? appointmentCriado.getId() : "N/A"));
                    return appointmentCriado;
                } catch (Exception e) {
                    System.err.println("Sucesso no POST, mas falha ao ler a resposta JSON: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
            } else {
                System.err.println("Erro HTTP ao criar agendamento: Status " + response.statusCode() + " | Corpo: " + response.body());
                return null;
            }

            } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.err.println("Erro ao converter dados do agendamento para JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException | InterruptedException e) {
            System.err.println("Falha de comunicação ou interrupção ao criar agendamento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    
        public boolean confirmarAgendamentoPatch(long appointmentId) {
            
            final String URL_PATCH = API_URL + "/" + appointmentId + "/confirm"; 

            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_PATCH))
                    .method("PATCH", HttpRequest.BodyPublishers.noBody()) 
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 || response.statusCode() == 204) {
                    System.out.println("Agendamento " + appointmentId + " CONFIRMADO com sucesso (PATCH).");
                    return true;
                } else {
                    System.err.println("Falha ao confirmar. Status: " + response.statusCode() + " | Body: " + response.body());
                    return false;
                }

            } catch (IOException | InterruptedException e) { 
                System.err.println("Falha de comunicação (PATCH/Confirmar): " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }


            public boolean concluirAtendimentoPut(long appointmentId) throws Exception {

        final String URL_PUT = API_URL + "/" + appointmentId + "/completed";

        try {
            // cria e serializa o objeto de requisição
            RequestMarkServiceCompletedJson requestBody = new RequestMarkServiceCompletedJson();
            requestBody.observation = "Concluído via sistema Swing.";
            String jsonBody = objectMapper.writeValueAsString(requestBody);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_PUT))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 204) {
                System.out.println("Agendamento " + appointmentId + " CONCLUÍDO com sucesso (PUT).");
                return true;
            } 

            else if (response.statusCode() == 400) {

                String body = response.body();
                String errorMessage = "Falha de validação ou regra de negócio (400).";

                try {
                    // Tenta extrair a mensagem de erro do JSON de resposta da API C#
                    JsonNode root = objectMapper.readTree(body);

                    if (root.has("errorMessages") && root.get("errorMessages").isArray() && root.get("errorMessages").size() > 0) {
                        errorMessage = root.get("errorMessages").get(0).asText();
                    } else if (root.has("title")) {
                        errorMessage = root.get("title").asText();
                    } else {
                        errorMessage = "Erro de API. Não foi possível extrair a mensagem do corpo: " + body;
                    }

                } catch (Exception jsonEx) {
                    System.err.println("Falha ao desserializar JSON de erro: " + jsonEx.getMessage());
                    // exceção com a mensagem de erro HTTP
                    throw new Exception("Erro interno da API: Status " + response.statusCode());
                }

                // exceção com mensagem de erro
                throw new Exception(errorMessage);

            }

            // Tratamento de outros erros HTTP (500, 404, etc.)
            else {
                System.err.println("Falha ao concluir. Status: " + response.statusCode() + " | Body: " + response.body());
                throw new Exception("Falha na API. Status: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Falha de comunicação (PUT/Concluir): " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}