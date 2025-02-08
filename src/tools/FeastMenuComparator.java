/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Comparator;
import models.FeastMenu;

/**
 *
 * @author DANH NGUYEN
 */
public class FeastMenuComparator implements Comparator<FeastMenu> {

    public int compare(FeastMenu fm1, FeastMenu fm2) {
        // Example: Sort by price, then by name
        int priceComparison = Double.compare(fm1.getPrice(), fm2.getPrice());
        if (priceComparison != 0) {
            return priceComparison;
        } else {
            return fm1.getFeastName().compareTo(fm2.getFeastName());
        }
    }
}
