/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import static collections.CustomerList.customers;
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
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import models.Customer;
import models.FeastMenu;
import models.PLaceFeastOrder;
import tools.Inputter;

/**
 *
 * @author DANH NGUYEN
 */
public class FeastOrderManagement implements Comparator<PLaceFeastOrder> {

    public static List<PLaceFeastOrder> feastOrderList = new ArrayList<>();
    public static boolean isSaved = true;

    public int compare(PLaceFeastOrder fm1, PLaceFeastOrder fm2) {
        int dateCompare = fm1.getEventDate().compareTo(fm2.getEventDate());
        return dateCompare;
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
        isSaved = false;
        feastOrderList.add(placeFeasrOrder);
        printOrder(placeFeasrOrder);
    }

    public static void printOrder(PLaceFeastOrder pfo) {
        FeastMenu fm = getFeastMenuListInfor(pfo.getCodeOfSetMenu());
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Customer order information  [Order ID: " + pfo.getCodeOfSetMenu() + "]");
        System.out.println("------------------------------------------------------------------------");
        System.out.println(pfo.getCustomerCode());
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

    public static void updateOrderInfor() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter order code to update: ");
            int orderCode = sc.nextInt();
            PLaceFeastOrder pfo = findOrderId(orderCode);
            boolean checkEventDay = checkEventDateOccur(pfo);

            if (pfo != null && checkEventDay) {
                while (true) {
                    System.out.println("Enter new code of set menu, If Not Change PRESS'ENTER'");
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
                    System.out.println("Enter new number of table, If Not Change PRESS'ENTER'");
                    String updateNumOfTable = sc.nextLine();
                    if (!updateNumOfTable.isEmpty()) {
                        System.out.println("Skipping");
                        break;
                    } else if (Integer.parseInt(updateNumOfTable) > 0) {
                        pfo.setNumberOfTable(orderCode);
                        break;
                    }
                }

                while (true) {
                    System.out.println("Enter date you want to update order(dd-MM-YYYY): ");
                    String dateTime = sc.nextLine();

                    if (dateTime.isEmpty()) {
                        System.out.println("Skipping");
                        break;
                    }
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
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
                isSaved = false;
            } else {
                System.out.println("Invalid input, please try again!");
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
        LocalDate inputDay = LocalDate.parse(pfo.getEventDate());
        if (inputDay.isBefore(LocalDate.now()) && inputDay.equals(LocalDate.now())) {
            return false;
        } else {
            return true;
        }
    }

    public static void readFromFile() {
        try (FileInputStream fis = new FileInputStream("src/data/feast_order_service.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            feastOrderList = (ArrayList<PLaceFeastOrder>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Can not find 'customer.dat'");
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
