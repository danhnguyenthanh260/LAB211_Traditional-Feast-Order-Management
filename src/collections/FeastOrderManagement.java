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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import models.FeastMenu;
import models.PLaceFeastOrder;
import tools.Inputter;
import menu.Menu;
import models.Customer;

/**
 *
 * @author DANH NGUYEN
 */
public class FeastOrderManagement implements Comparator<PLaceFeastOrder> {

    public static List<PLaceFeastOrder> feastOrderList = new ArrayList<>();

    public int compare(PLaceFeastOrder pfo1, PLaceFeastOrder pfo2) {
        return pfo1.getEventDate().compareTo(pfo2.getEventDate());
    }

    public static void addFeastOrder() {
        int orderCode = Inputter.getOrderCode();
        String customerCode = Inputter.getCustomerCode();
        String codeOfSetMenu = Inputter.getFeastMenuCode();
        int numOfTable = Inputter.inputNumOfTable();
        String orderDate = Inputter.inputDate();
        String totalCost = Inputter.totalCostOfSetMenu(numOfTable, codeOfSetMenu);
        String orderSetPrice = Inputter.OrderSetPrice(codeOfSetMenu);

        PLaceFeastOrder placeFeasrOrder = new PLaceFeastOrder(orderCode, customerCode, codeOfSetMenu, numOfTable, orderDate, totalCost, orderSetPrice);
        if (checkIfPlaceOrderDupplicate(placeFeasrOrder)) {
            feastOrderList.add(placeFeasrOrder);
            printOrder(placeFeasrOrder);
            continueToAddOrder();
        } else {
            System.out.println("Dupplicate data!");
        }
    }

    public static void printOrder(PLaceFeastOrder pfo) {
        FeastMenu fm = getFeastMenuListInfor(pfo.getCodeOfSetMenu());
        Customer cs = CustomerList.getCustomerById(pfo.getCustomerCode());
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Customer order information  [Order ID: " + pfo.getOrderCode() + "]");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Customer code   :" + pfo.getCustomerCode());
        System.out.println("Customer name   :" + cs.getCustomerName());

        System.out.println("Phone number    :" + cs.getPhoneNumber());
        System.out.println("Email           :" + cs.getEmail());
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Code of Set Menu:" + pfo.getCodeOfSetMenu());
        System.out.println("Set menu name   :" + fm.getFeastName());
        System.out.println("Event date      :" + pfo.getEventDate());
        System.out.println("Number of tables:" + pfo.getNumberOfTable());
        System.out.println("Price           :" + pfo.getOrderSetPrice());
        System.out.println("Ingredients     :\n" + fm.getIngredients());
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Total cost      :" + pfo.getTotalCost());
        System.out.println("------------------------------------------------------------------------");
    }

    public static FeastMenu getFeastMenuListInfor(String codeOdSetMenu) {
        for (FeastMenu feastMenu : FeastMenuList.feastMenuList) {
            if (feastMenu.getId().contains(codeOdSetMenu)) {
                return feastMenu;
            }
        }
        return null;
    }

    public static void printFeastOrderList() {
        Collections.sort(feastOrderList, new FeastOrderManagement());
        Menu.printPlaceFeastOrderMenu();
        for (PLaceFeastOrder pLaceFeastOrder : feastOrderList) {
            System.out.println(pLaceFeastOrder);
        }
    }

    public static void continueToAddOrder() {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        while (true) {
            if (choice.equalsIgnoreCase("y")) {
                addFeastOrder();
                break;
            } else if (choice.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid input, please try again! [Y/N]");
            }
        }
    }

    public static boolean checkIfPlaceOrderDupplicate(PLaceFeastOrder pfo1) {
        for (PLaceFeastOrder pLaceFeastOrder : feastOrderList) {
            if (pLaceFeastOrder.getCustomerCode().contains(pfo1.getCustomerCode())
                    && pLaceFeastOrder.getCodeOfSetMenu().contains(pfo1.getCodeOfSetMenu())
                    && pLaceFeastOrder.getEventDate().contains(pfo1.getEventDate())) {
                return false;
            }
        }
        return true;
    }

    public static void updateOrderInfor() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter ORDER CODE to update: ");
            int orderCode = sc.nextInt();
            PLaceFeastOrder pfo = findOrderId(orderCode);
            if (pfo == null) {
                System.out.println("There're no order code registed with: " + orderCode);
                break;
            }
            boolean checkEventDay = checkEventDateOccur(pfo);
            sc.nextLine();
            if (checkEventDay) {
                while (true) {
                    System.out.print("Enter new CODE OR SET MENU, If Not Change PRESS 'ENTER': ");
                    String codeOfSetMenu = sc.nextLine();
                    if (!codeOfSetMenu.isEmpty()) {
                        pfo.setCodeOfSetMenu(codeOfSetMenu);
                        break;
                    } else if (codeOfSetMenu.isEmpty()) {
                        System.out.println("Skipping");
                        break;
                    }
                }

                while (true) {
                    System.out.print("Enter new NUMBER OF TABLE, If Not Change PRESS 'ENTER': ");
                    String updateNumOfTable = sc.nextLine();
                    if (updateNumOfTable.isEmpty()) {
                        System.out.println("Skipping");
                        break;
                    } else {
                        try {
                            int numOfTables = Integer.parseInt(updateNumOfTable);
                            if (numOfTables > 0) {
                                pfo.setNumberOfTable(numOfTables);
                            } else {
                                System.out.println("Invalid input, please try again!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! PLease enter a number");
                        }
                    }
                }

                while (true) {
                    System.out.print("Enter EVENT DATE you want to update(dd/MM/YYYY), If Not Change PRESS 'ENTER': ");
                    String dateTime = sc.nextLine();

                    if (dateTime.isEmpty()) {
                        System.out.println("Skipping");
                        break;
                    }
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate inputDay = LocalDate.parse(dateTime, formatter);

                        if (inputDay.isAfter(LocalDate.now())) {
                            pfo.setEventDate(dateTime);
                            break;
                        } else if (inputDay.isBefore(LocalDate.now())) {
                            System.out.println("You must be enter a day after today!");
                        }

                    } catch (DateTimeException e) {
                        System.out.println("Invalid date format, please try again!");
                    }
                }
                break;
            } else {
                System.out.println("This ORDER has been expired!");
            }

        }
    }

    public static PLaceFeastOrder findOrderId(int orderId) {
        for (PLaceFeastOrder pLaceFeastOrder : feastOrderList) {
            if (pLaceFeastOrder.getOrderCode() == orderId) {
                return pLaceFeastOrder;
            }
        }
        return null;
    }

    public static boolean checkEventDateOccur(PLaceFeastOrder pfo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate inputDay = LocalDate.parse(pfo.getEventDate(), formatter);
            if (inputDay.isBefore(LocalDate.now()) && inputDay.equals(LocalDate.now())) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void deletePlaceOrder() {
        Scanner sc = new Scanner(System.in);
        PLaceFeastOrder deleteFeastOrder = null;
        System.out.println("Enter order code you want to delete: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input! Order code must be a number.");
            return;
        }
        int orderCode = sc.nextInt();

        for (PLaceFeastOrder pLaceFeastOrder : feastOrderList) {
            if (pLaceFeastOrder.getOrderCode() == orderCode) {
                deleteFeastOrder = pLaceFeastOrder;
                break;
            }
        }
        if (deleteFeastOrder != null) {
            feastOrderList.remove(deleteFeastOrder);
            System.out.println("Successfully REMOVE feast order!");
        } else {
            System.out.println("There was no feast order under this order code!");
        }
    }

    public static void readFromFile() {
        try (FileInputStream fis = new FileInputStream("src/data/feast_order_service.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            feastOrderList = (ArrayList<PLaceFeastOrder>) ois.readObject();
            System.out.println("Had been upload data from `feast_order_service.dat`");
        } catch (FileNotFoundException e) {
            System.out.println("Can not find `feast_order_service.dat`");
        } catch (IOException e) {
            System.out.println("Error to read from file " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }

    public static void writeToFile() {
        try (FileOutputStream fos = new FileOutputStream("src/data/feast_order_service.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(feastOrderList);
            System.out.println("Order data has been successfully saved to `feast_order_service.dat`");
        } catch (FileNotFoundException e) {
            System.out.println("Can not find 'customer.dat'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
