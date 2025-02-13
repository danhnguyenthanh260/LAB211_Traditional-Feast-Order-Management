/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

/**
 *
 * @author DANH NGUYEN
 */
public class Menu {

    public static void printMainMenu() {
        System.out.println("\n<=================================================  MENU  ================================================>");
        System.out.println("1. Register customers.");
        System.out.println("2. Update customer information.");
        System.out.println("3. Search for customer information by name");
        System.out.println("4. Display feast menu.");
        System.out.println("5. Place a feast order.");
        System.out.println("6. Update order information.");
        System.out.println("7. Save data to file.");
        System.out.println("8. Display Customner or Order lists.");
        System.out.println("9. Quits");
        System.out.println("<==========================================================================================================>");
        System.out.print("Enter your choice: ");
    }

    public static void DisplayBarLine() {
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public static void displaySearchingListBar() {
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Code   | Customer Name                  | Phone      | Email                       ");
        System.out.println("---------------------------------------------------------------------------------------------------------");

    }

    public static void displayFeastMenuBar() {
        DisplayBarLine();
        System.out.println("List of Set Menus for ordering party:");
        DisplayBarLine();
    }

    public static void printPlaceFeastOrderMenu() {
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("ID   | Event date  | Customer ID| Set Menu| Price     | Tables | Cost                      ");
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }

}
