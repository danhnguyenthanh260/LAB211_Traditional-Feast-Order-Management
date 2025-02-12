/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DANH NGUYEN
 */
public class PLaceFeastOrder {

    private int orderCode;
    private String customerCode;
    private String codeOfSetMenu;
    private int numberOfTable;
    private String eventDate;

    public PLaceFeastOrder() {
    }

    public PLaceFeastOrder(int orderNum, String customerCode, String codeOfSetMenu, int numberOfTable, String eventDate) {
        this.orderCode = orderNum;
        this.customerCode = customerCode;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numberOfTable = numberOfTable;
        this.eventDate = eventDate;
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

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "PLaceFeastOrder{" + "customerCode=" + customerCode + ", codeOfSetMenu=" + codeOfSetMenu + ", numberOfTable=" + numberOfTable + ", eventDate=" + eventDate + '}';
    }

}
