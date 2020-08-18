package com.fabric.warehouse.service;

import com.fabric.warehouse.data.Product;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashMap;

@Service
public class ProductsService {
    private static HashMap<String, Product> products = new HashMap<>();

    @PostConstruct
    public static void init() {
        Product milk = new Product("Milk", 10, new int[] {5,6});
        Product bread = new Product("Bread", 10, new int[] {1,5});
        Product salt = new Product("Salt", 10, new int[] {6,5});
        Product soap = new Product("Soap", 10, new int[] {8,96});
        Product pasta = new Product("Pasta", 10, new int[] {9,2});

        products.put("mile",milk);
        products.put("bread",bread);
        products.put("salt",salt);
        products.put("soap",soap);
        products.put("pasta",pasta);
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public void add(@NotBlank String productName) {
        Product product = products.get(productName.toLowerCase());
        if (product != null) {
            product.setAmount(product.getAmount() + 1);
        } else {
            throw new IllegalArgumentException("Invalid Product name " + productName);
        }
    }

    public void get(@NotBlank String productName) {
        Product product = products.get(productName.toLowerCase());
        if (product != null) {
            if (product.getAmount() > 0) {
                product.setAmount(product.getAmount() - 1);
            } else {
                throw new IllegalArgumentException("Product amount is 0 for : " + productName);
            }
        } else {
            throw new IllegalArgumentException("Invalid Product name " + productName);
        }
    }

    public int[] getLocation(String productName) {
        Product product = products.get(productName.toLowerCase());
        if (product != null) {
            return product.getLocation();
        } else {
            throw new IllegalArgumentException("Invalid Product name " + productName);
        }
    }
}
