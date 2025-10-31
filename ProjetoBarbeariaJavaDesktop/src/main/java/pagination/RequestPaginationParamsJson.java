/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagination;

import pagination.enums.OrderByDirection;

/**
 *
 * @author ferna
 */
public class RequestPaginationParamsJson {

    private OrderByDirection orderByDirection = OrderByDirection.DESCENDING;

    public OrderByDirection getOrderByDirection() {
        return orderByDirection;
    }

    public void setOrderByDirection(OrderByDirection orderByDirection) {
        this.orderByDirection = orderByDirection;
    }
}