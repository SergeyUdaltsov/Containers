package com.testSpringBoot.testServer.model;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 8/30/2020
 */
public class Product {

    private int id;
    private String name;
    private List<String> subProducts;

    public Product(int id, String name, List<String> subProducts) {
        this.id = id;
        this.name = name;
        this.subProducts = subProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<String> subProducts) {
        this.subProducts = subProducts;
    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", subProducts=" + subProducts +
//                '}';
//    }
}
