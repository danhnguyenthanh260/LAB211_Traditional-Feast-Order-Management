/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import models.Customer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import menu.Menu;
import tools.Acceptable;
import tools.Inputter;

/**
 *
 * @author DANH NGUYEN
 */
public class CustomerList implements Comparator<Customer> {

    public static ArrayList<Customer> customers = new ArrayList<>();

    public int compare(Customer cs1, Customer cs2) {
        int dateCompare = cs1.getCustomerName().compareTo(cs2.getCustomerName());
        return dateCompare;
    }

    public static void addNewCustomer() {
        String id = Inputter.getCustomerId();
        String name = Inputter.inputName();
        String phone = Inputter.inputPhone();
        String email = Inputter.inputEmail();

        Customer customer = new Customer(id, name, phone, email);
        customers.add(customer);
        FeastOrderManagement.isSaved = false;
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
                    st.setCustomerName(updatedName);
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
            FeastOrderManagement.isSaved = false;
            continueUpdateCustomer();
        } else {
            System.out.println("This customer has not registered yet.");
        }
    }

    public static Customer findCustomerByID(String id) {
        for (Customer customer : customers) {
            if (id.equalsIgnoreCase(customer.getCustomerCode())) {
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
            printCustomerList(matchCustomer);
        } else {
            System.out.println("No one matches the search criteria!");
        }

    }

    public static void printCustomerList(ArrayList<Customer> matchCustomer) {
        Collections.sort(customers, new CustomerList());
        Menu.displaySearchingListBar();
        for (Customer customer : matchCustomer) {
            System.out.println(customer);
        }
        Menu.DisplayBarLine();
    }

    public static ArrayList<Customer> existCustomerByName(String customerName) {
        ArrayList<Customer> customerMatchName = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.getCustomerName().toLowerCase().contains(customerName)) {
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

    public static void readFromFile() {
        try (FileInputStream fis = new FileInputStream("src/data/customer.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            customers = (ArrayList<Customer>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Can not find 'customer.dat'");
        } catch (IOException e) {
            System.out.println("Error to read from file " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }

    public static void writeToFile() {
        try (FileOutputStream fos = new FileOutputStream("src/data/customer.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(customers);
            System.out.println("Customer data has been successfully saved to `customer.dat`");
        } catch (FileNotFoundException e) {
            System.out.println("Can not find 'customer.dat'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayCustomerOrOrderList() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Which one do you want to display?"
                    + " 1-Customer "
                    + " 2-OrderList");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    printCustomerList(customers);
                    break;
                case 2:
                    FeastOrderManagement.printFeastOrderList();
                    break;
                default:
                    System.out.println("Please choose 1-Customer or 2-OrderList!");
                    break;
            }
        } while (choice != 1 && choice != 2);
    }
}
