/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import menu.Menu;

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
            if (Acceptable.isValid(phone, Acceptable.PHONE_VALID)) {
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
            if (Acceptable.isValid(email, Acceptable.EMAIL_VALID)) {
                check = true;
            } else {
                System.out.println("INVALID EMAIL!!");
            }
        } while (!check);
        return email;
    }
   
}
