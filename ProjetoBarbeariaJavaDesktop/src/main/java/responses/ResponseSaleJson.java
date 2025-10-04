/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;

import entities.Sale;
import entities.SaleDetails;
import java.util.List;
/**
 *
 * @author andre
 */


public class ResponseSaleJson {

    private Sale sale;
    private List<SaleDetails> saleDetails;

    // Construtor padrão
    public ResponseSaleJson() {
    }

    // Construtor completo
    public ResponseSaleJson(Sale sale, List<SaleDetails> saleDetails) {
        this.sale = sale;
        this.saleDetails = saleDetails;
    }
    
    // Getters e Setters

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public List<SaleDetails> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetails> saleDetails) {
        this.saleDetails = saleDetails;
    }
}
