/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author andre
 */
public class ResponseErrorJson {
   private List<String> errorMessages;

    /**
     * Construtor para criar uma resposta de erro a partir de uma única mensagem.
     * @param errorMessage A mensagem de erro.
     */
    public ResponseErrorJson(String errorMessage) {
        // A sintaxe C# "[errorMessage]" cria uma nova lista com um único elemento.
        // Esta é a forma equivalente e segura de fazer o mesmo em Java.
        this.errorMessages = new ArrayList<>(Collections.singletonList(errorMessage));
    }

    /**
     * Construtor para criar uma resposta de erro a partir de uma lista de mensagens já existente.
     * @param errorMessages A lista de mensagens de erro.
     */
    public ResponseErrorJson(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    // Getter
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    // Setter
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
