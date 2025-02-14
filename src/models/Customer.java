/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import collections.CustomerList;

/**
 *
 * @author DANH NGUYEN
 */
public class Customer implements Serializable {

    private String customerCode;
    private String customerName;
    private String phoneNumber;
    private String email;

    public Customer() {
    }

    public Customer(String code, String name, String phoneNumber, String email) {
        this.customerCode = code;
        this.customerName = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-31s| %-11s| %-28s", customerCode, CustomerList.customCustomerName(customerName) , phoneNumber, email);
    }

}

