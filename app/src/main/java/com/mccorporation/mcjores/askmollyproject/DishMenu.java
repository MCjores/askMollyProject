package com.mccorporation.mcjores.askmollyproject;

import java.util.PrimitiveIterator;

public class DishMenu {

    private String Name;
    private String Description;
    private String[] Composition;

    private double Fat;
    private double Proteins;
    private double Carbohydrates;
    private double Kcal;
    private double Weight;

    private double Price;

    private String[] ProductDish;
    private int DishPhoto;

    public DishMenu(String name, String description,int dishPhoto, String[] composition, double fat, double proteins, double carbohydrates, double kcal, double weight, double price) {
        Name = name;
        Description = description;
        Composition = composition;
        Fat = fat;
        Proteins = proteins;
        Carbohydrates = carbohydrates;
        Kcal = kcal;
        Weight = weight;
        Price = price;
        DishPhoto = dishPhoto;
    }



    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String[] getComposition() {
        return Composition;
    }

    public double getFat() {
        return Fat;
    }

    public double getProteins() {
        return Proteins;
    }

    public double getCarbohydrates() {
        return Carbohydrates;
    }

    public double getKcal() {
        return Kcal;
    }

    public double getWeight() {
        return  Weight;
    }

    public int getDishPhoto() {
        return DishPhoto;
    }


    public double getPrice() {
        return Price;
    }

    public void setDishPhoto(int dishPhoto) {
        DishPhoto = dishPhoto;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setComposition(String[] composition) {
        Composition = composition;
    }

    public void setFat(double fat) {
        Fat = fat;
    }

    public void setProteins(double proteins) {
        Proteins = proteins;
    }

    public void setCarbohydrates(double carbohydrates) {
        Carbohydrates = carbohydrates;
    }

    public void setKcal(double kcal) {
        Kcal = kcal;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public void setPrice(double price) {
        Price = price;
    }
}

