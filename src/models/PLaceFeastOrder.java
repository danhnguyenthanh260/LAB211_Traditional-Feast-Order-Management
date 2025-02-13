/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author DANH NGUYEN
 */
public class PLaceFeastOrder implements Serializable {

    private int orderCode;
    private String customerCode;
    private String codeOfSetMenu;
    private int numberOfTable;
    private String eventDate;
    private String totalCost;
    private String orderSetPrice;

    public PLaceFeastOrder() {
    }

    public PLaceFeastOrder(int orderCode, String customerCode, String codeOfSetMenu, int numberOfTable, String eventDate, String totalCost, String OrderCost) {
        this.orderCode = orderCode;
        this.customerCode = customerCode;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numberOfTable = numberOfTable;
        this.eventDate = eventDate;
        this.totalCost = totalCost;
        this.orderSetPrice = OrderCost;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCodeOfSetMenu() {
        return codeOfSetMenu;
    }

    public void setCodeOfSetMenu(String codeOfSetMenu) {
        this.codeOfSetMenu = codeOfSetMenu;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderSetPrice() {
        return orderSetPrice;
    }

    public void setOrderSetPrice(String orderSetPrice) {
        this.orderSetPrice = orderSetPrice;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-22s| %-6s| %-6s| %-10s| %-6s| %-10s", customerCode, eventDate, customerCode, codeOfSetMenu, orderSetPrice, numberOfTable, totalCost);
    }

}
