/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagination;

import pagination.enums.OrderByExpenseColumn;
import enums.ExpenseStatus;

/**
 *
 * @author ferna
 */
public class RequestExpensesPaginationParamsJson extends RequestPaginationParamsJson {
    private OrderByExpenseColumn orderByColumn = OrderByExpenseColumn.DUE_DATE;
    private ExpenseStatus status;

    public OrderByExpenseColumn getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(OrderByExpenseColumn orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }
}