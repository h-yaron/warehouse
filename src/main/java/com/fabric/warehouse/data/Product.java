package com.fabric.warehouse.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
  name: <str>,
  amount: <int>
 */
public class Product {
    String name;
    int amount;
    @JsonIgnore
    int[] location;

    public Product(String name, int amount, int[] location) {
        this.name = name;
        this.amount = amount;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }
}
