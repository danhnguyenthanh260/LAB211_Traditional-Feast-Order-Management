/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import models.Customer;
import models.FeastMenu;
import java.util.ArrayList;
import java.util.Scanner;
import menu.Menu;
import tools.Acceptable;
import tools.Inputter;

/**
 *
 * @author DANH NGUYEN
 */
public class CustomerList {

    public static ArrayList<Customer> customers = new ArrayList<>();

    public static void addNewCustomer() {
        String id = Inputter.getCustomerId();
        String name = Inputter.inputName();
        String phone = Inputter.inputPhone();
        String email = Inputter.inputEmail();

        Customer customer = new Customer(id, name, phone, email);
        customers.add(customer);
        System.out.println("Add new customer successfull!");
        continueAddCustomer();
    }

    public static void continueAddCustomer() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Do you want to continue add customer? [Y/N]: ");
            String choose = sc.nextLine();
            if (choose.equalsIgnoreCase("y")) {
                addNewCustomer();
                break;
            } else if (choose.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Please enter [Y/N]!");
            }
        } while (true);
    }

    public static void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID to update: ");
        String findId = scanner.nextLine();
        Customer st = findCustomerByID(findId);

        if (st != null) {
            while (true) {
                System.out.print("Do you want to change customer name?(if NO press enter)");
                String updatedName = scanner.nextLine();
                if (!updatedName.equals("") && Acceptable.isValid(updatedName, Acceptable.NAME_VALID)) {
                    st.setName(updatedName);
                    break;
                } else if (updatedName.equals("")) {
                    System.out.println("Skipping");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            while (true) {
                System.out.print("Do you want to change phone number?(if NO press enter)");

                String updatedPhone = scanner.nextLine();
                if (!updatedPhone.equals("") && Acceptable.isValid(updatedPhone, Acceptable.PHONE_VALID)) {
                    st.setPhoneNumber(updatedPhone);
                    break;
                } else if (updatedPhone.equals("")) {
                    System.out.println("Skipping");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            while (true) {
                System.out.print("Do you want to change email?(if NO press enter)");
                String updatedEmail = scanner.nextLine();
                if (!updatedEmail.equals("") && Acceptable.isValid(updatedEmail, Acceptable.EMAIL_VALID)) {
                    st.setEmail(updatedEmail);
                } else if (updatedEmail.equals("")) {
                    System.out.println("Skipping");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }

            }
            continueUpdateCustomer();
        } else {
            System.out.println("This customer has not registered yet.");
        }
    }

    public static Customer findCustomerByID(String id) {
        for (Customer customer : customers) {
            if (id.equalsIgnoreCase(customer.getCode())) {
                System.out.println("Your Customer ID had been found!");
                return customer;
            }
        }
        return null;
    }

    public static void continueUpdateCustomer() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Do you want to continue update customer? [Y/N]: ");
            String choose = sc.nextLine();
            if (choose.equalsIgnoreCase("y")) {
                updateCustomer();
                break;
            } else if (choose.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Please enter [Y/N]!");
            }
        } while (true);
    }

    public static void SearchingByCustomerName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer name you want to find: ");
        String customerName = sc.nextLine();
        ArrayList<Customer> matchCustomer = existCustomerByName(customerName.toLowerCase());

        if (!matchCustomer.isEmpty()) {
            Menu.displaySearchingListBar();
            for (Customer customer : matchCustomer) {
                System.out.format("%-11s | %-22s| %-11s| %-28s\n", customer.getCode(), customCustomerName(customer.getName()), customer.getEmail(), customer.getPhoneNumber());
            }
            Menu.DisplayBarLine();
        } else {
            System.out.println("No one matches the search criteria!");
        }

    }

    public static ArrayList<Customer> existCustomerByName(String customerName) {
        ArrayList<Customer> customerMatchName = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(customerName)) {
                customerMatchName.add(customer);
            }
        }
        return customerMatchName;
    }

    public static String customCustomerName(String name) {
        int lastIndexOfSpace = name.lastIndexOf(" ");
        return (name.substring(lastIndexOfSpace + 1) + "," + name.substring(0, lastIndexOfSpace));
    }

    public static void showFeastMenu() {
        FeastMenuList.showListFeastMenu();
    }

    public static String addCommaToPrice(double price) {
        return String.format("%,.0f", price);
    }

    public static void PlaceAFeastOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID to place order: ");
        String customerId = scanner.nextLine();
        Customer cs = findCustomerByID(customerId);

        System.out.println("Enter code of set to place order: ");
        String setCode = scanner.nextLine();
        FeastMenu fm = FeastMenuList.findFeastCode(setCode);
        
        int table = scanner.nextInt();
    }
}
