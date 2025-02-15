
import collections.CustomerList;
import collections.FeastMenuList;
import java.util.Scanner;
import menu.Menu;
import collections.FeastOrderManagement;

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
        CustomerList.readFromFile();
        FeastOrderManagement.readFromFile();

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
                    FeastOrderManagement.addFeastOrder();
                    break;
                case 6:
                    FeastOrderManagement.updateOrderInfor();
                    break;
                case 7:
                    CustomerList.writeToFile();
                    FeastOrderManagement.writeToFile();
                    break;
                case 8:
                    CustomerList.displayCustomerOrOrderList();
                    break;
                default:
                    System.out.println("Exít program successfully!.");
                    break;
            }
        } while (choice > 0 && choice < 9);

    }

}
