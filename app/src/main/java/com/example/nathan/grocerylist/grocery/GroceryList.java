package com.example.nathan.grocerylist.grocery;
import com.example.nathan.grocerylist.grocery.*;
import java.util.ArrayList;

public class GroceryList {
    private ArrayList<GroceryItem> items;
    public GroceryList() {
        this.items = new ArrayList<GroceryItem>();
    }
    public void addItem(String name, String section) {
        GroceryItem item = new GroceryItem(name, section);
        this.items.add(item);
    }

    public ArrayList<GroceryItem> getItems() {
        return this.items;
    }
}