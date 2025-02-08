
import collections.CustomerList;
import collections.FeastMenuList;
import java.util.Scanner;
import menu.Menu;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANH NGUYEN
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;
        FeastMenuList.loadFromFile();

        do {
            Menu.printMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CustomerList.addNewCustomer();
                    break;
                case 2:
                    CustomerList.updateCustomer();
                    break;
                case 3:
                    CustomerList.SearchingByCustomerName();
                    break;
                case 4:
                    CustomerList.showFeastMenu();
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                default:
                    System.out.println("This function is not available.");
                    break;
            }
        } while (choice != 9);

    }

}
