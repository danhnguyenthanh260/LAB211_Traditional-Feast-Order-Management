/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import collections.CustomerList;
import collections.FeastMenuList;
import static collections.FeastOrderManagement.feastOrderList;
import static collections.FeastOrderManagement.getFeastMenuListInfor;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import menu.Menu;
import models.Customer;
import models.FeastMenu;
import models.PLaceFeastOrder;

/**
 *
 * @author DANH NGUYEN
 */
public class Inputter {
    
    boolean check = false;
    
    public static String getCustomerFirstCharacter() {
        Scanner scanner = new Scanner(System.in);
        String customerFirstCharacter = "";
        
        while (true) {
            Menu.DisplayBarLine();
            System.out.print("Choose customer first character (1.C--2.G--3.K): ");
            
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        customerFirstCharacter = "C";
                        break;
                    case 2:
                        customerFirstCharacter = "G";
                        break;
                    case 3:
                        customerFirstCharacter = "K";
                        break;
                    default:
                        System.out.println("Invalid!!");
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid campus! Choose a number");
                scanner.next();
            }
            
        }
        return customerFirstCharacter;
    }
    
    public static String getCustomerId() {
        String customerFirstCharacter = getCustomerFirstCharacter();
        
        String id = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        String uniqueId = id.substring(0, 4);
        
        System.out.print("Customer ID: ");
        System.out.println(customerFirstCharacter + uniqueId);
        return customerFirstCharacter + uniqueId;
    }
    
    public static String inputName() {
        boolean check = false;
        String name = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter customer name: ");
            name = sc.nextLine();
            if (Acceptable.isValid(name, Acceptable.NAME_VALID)) {
                check = true;
            } else {
                System.out.println("Name must be from(2-25) characters!");
            }
        } while (!check);
        return name;
    }
    
    public static String inputPhone() {
        boolean check = false;
        String phone = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter customer phone number: ");
            phone = sc.nextLine();
            if (Acceptable.isValid(phone, Acceptable.PHONE_VALID) && CustomerList.dupplicatePhoneNumber(phone)) {
                check = true;
            } else {
                System.out.println("INVALID PHONE NUMBER!");
            }
        } while (!check);
        return phone;
    }
    
    public static String inputEmail() {
        boolean check = false;
        String email = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter customer email: ");
            email = sc.nextLine();
            if (Acceptable.isValid(email, Acceptable.EMAIL_VALID) && CustomerList.dupplicateEmail(email)) {
                check = true;
            } else {
                System.out.println("INVALID EMAIL!!");
            }
        } while (!check);
        return email;
    }
    
    public static int getOrderCode() {
        List<Integer> existingCodes = new ArrayList<>();
        for (PLaceFeastOrder pLaceFeastOrder : feastOrderList) {
            existingCodes.add(pLaceFeastOrder.getOrderCode());
        }
        return generateNextCode(existingCodes);
    }
    
    public static int generateNextCode(List<Integer> existingCode) {
        int nextCode = 1;
        while (existingCode.contains(nextCode)) {
            nextCode++;
        }
        return nextCode;
    }
    
    public static String getCustomerCode() {
        Customer customer;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter customer code: ");
            String customerCode = sc.nextLine();
            customer = CustomerList.findCustomerByID(customerCode);
            if (customer != null) {
                break;
            } else {
                System.out.println("This customer had not registered yet!");
            }
        }
        
        return customer.getCustomerCode();
    }
    
    public static String getFeastMenuCode() {
        FeastMenu feastMenu;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter feast menu code: ");
            String feastMenuCode = sc.nextLine();
            feastMenu = FeastMenuList.findFeastCode(feastMenuCode);
            if (feastMenu != null) {
                break;
            } else {
                System.out.println("There none of feast code existed under that code!");
            }
        }
        return feastMenu.getId();
    }
    
    public static int inputNumOfTable() {
        int numOfTable;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter number of table: ");
            numOfTable = sc.nextInt();
            
            if (numOfTable > 0) {
                break;
            } else {
                System.out.println("Invalid number of table!");
            }
        }
        return numOfTable;
    }
    
    public static String inputDate() {
        String dateTime = "";
        LocalDate inputDay;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter date you want to order(dd/MM/YYYY): ");
                dateTime = sc.nextLine();
                inputDay = LocalDate.parse(dateTime, formatter);
                
                if (inputDay.isAfter(LocalDate.now())) {
                    break;
                } else if (inputDay.isBefore(LocalDate.now())) {
                    System.out.println("You must be enter a day after today!");
                }
                
            } catch (DateTimeException e) {
                System.out.println("Invalid date format, please try again!");
            }
        }
        return dateTime;
    }
    
    public static String totalCostOfSetMenu(int tables, String codeOfSetMenu) {
        FeastMenu fm = getFeastMenuListInfor(codeOfSetMenu);
        return String.format("%,.0f", (double) tables * fm.getPrice());
    }
    
    public static String OrderSetPrice(String codeOfSetMenu) {
        FeastMenu fm = getFeastMenuListInfor(codeOfSetMenu);
        return String.format("%,.0f", fm.getPrice());
    }
}
