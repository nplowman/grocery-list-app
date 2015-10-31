package com.example.nathan.grocerylist.grocery;
import com.example.nathan.grocerylist.grocery.GrocerySection;

public class GroceryItem {
    private  String name;
    private  GrocerySection section;

    public GroceryItem(String name, String section) {
        this.name = name;
        this.section = new GrocerySection(section);
    }
    public String getName() {
        return this.name;
    }

    public String getSection() {
        return this.section.getName();
    }
}
