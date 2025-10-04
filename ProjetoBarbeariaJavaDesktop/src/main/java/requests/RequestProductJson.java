/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package requests;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author andre
 */

public class RequestProductJson {

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 3, max = 255, message = "O nome do produto deve ter no mínimo 3 caracteres e no máximo 255")
    private String name;

    @NotNull(message = "O preço do produto é obrigatório")
    private Long salePrice;

    @NotNull(message = "O custo do produto é obrigatório")
    private Long unitCost;

    @NotNull(message = "A quantidade do produto é obrigatória")
    private Long quantity;

    @NotNull(message = "O estoque mínimo do produto é obrigatório")
    private Integer minimumStock;

    // Getters e Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Long unitCost) {
        this.unitCost = unitCost;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }
}
