/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.FeastMenu;
import tools.DataComparator;
import menu.Menu;

/**
 *
 * @author DANH NGUYEN
 */
public class FeastMenuList implements Comparator<FeastMenu>{

    public static List<FeastMenu> feastMenuList = new ArrayList<>();
    public static Set<String> feastMenuCode = new HashSet<>();
    
       public int compare(FeastMenu fm1, FeastMenu fm2) {
        // Example: Sort by price, then by name
        int priceComparison = Double.compare(fm1.getPrice(), fm2.getPrice());
        if (priceComparison != 0) {
            return priceComparison;
        } else {
            return fm1.getFeastName().compareTo(fm2.getFeastName());
        }
    }

    private static FeastMenu dataToObject(String text) {
        String[] parts = text.split(",", -1);
        if (parts.length == 4) {
            return new FeastMenu(
                    parts[0].trim(),
                    parts[1].trim(),
                    Double.parseDouble(parts[2].trim()),
                    costumeIngredientsData(parts[3].trim())
            );
        } else {
            System.out.println("Invalid data format: " + text);
        }
        return null;

    }

    public static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/FeastMenu.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                FeastMenu fm = dataToObject(line);
                if (fm != null) {
                    feastMenuList.add(fm);
                    feastMenuCode.add(fm.getId());
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        Collections.sort(feastMenuList, new DataComparator());
    }

    public static void showListFeastMenu() {
        Menu.displayFeastMenuBar();
        for (FeastMenu feastMenu : feastMenuList) {
            System.out.println("Code       :" + feastMenu.getId() + "\n"
                    + "Name       :" + feastMenu.getFeastName() + "\n"
                    + "Price      :" + CustomerList.addCommaToPrice(feastMenu.getPrice()) + "\n"
                    + "Ingredients: " + "\n"
                    + feastMenu.getIngredients());
            Menu.DisplayBarLine();
        }
    }

    public static FeastMenu findFeastCode(String feastMenuCode) {
        for (FeastMenu fsCode : feastMenuList) {
            if (feastMenuCode.contains(fsCode.getId())) {
                return fsCode;
            }
        }
        return null;
    }

    public static String totalCostOfTable(int table, FeastMenu fs) {
        double totalPrice = (double) table * (fs.getPrice());
        return String.format("%,.0f", totalPrice);
    }

    public static String costumeIngredientsData(String text) {
        text = text.replaceAll("\"", "");
        text = text.replaceAll("\\#", "\n");
        String costumeText = text;
        return costumeText;
    }
}
