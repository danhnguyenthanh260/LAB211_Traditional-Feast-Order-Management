/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DANH NGUYEN
 */
public class FeastMenu {
    private String id;
    private String feastName;
    private double price;
    private String ingredients;

    public FeastMenu() {
    }

    public FeastMenu(String id, String feastName, double price, String ingredients) {
        this.id = id;
        this.feastName = feastName;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeastName() {
        return feastName;
    }

    public void setFeastName(String feastName) {
        this.feastName = feastName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "feastMenu{" + "id=" + id + ", feastName=" + feastName + ", price=" + price + ", ingredients=" + ingredients + '}';
    }
    
    
    
}
