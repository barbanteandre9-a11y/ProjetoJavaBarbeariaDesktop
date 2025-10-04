/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package requests;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author andre
 */

public class RequestRegisterSaleJson {

    @NotNull(message = "É obrigatório informar a data da venda.")
    private LocalDateTime saleDate;

    @NotEmpty(message = "É necessário pelo menos 1 item na venda.")
    private List<RequestDetailsProductSaleJson> detailsProductSale;

    // Getters e Setters

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public List<RequestDetailsProductSaleJson> getDetailsProductSale() {
        return detailsProductSale;
    }

    public void setDetailsProductSale(List<RequestDetailsProductSaleJson> detailsProductSale) {
        this.detailsProductSale = detailsProductSale;
    }
}
