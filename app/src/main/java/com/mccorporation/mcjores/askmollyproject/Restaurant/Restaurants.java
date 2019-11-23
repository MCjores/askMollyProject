package com.mccorporation.mcjores.askmollyproject;

import java.util.ArrayList;

public class Restaurants {

    private String NameCafe;
    private String RestaurantDescription;
    private int PhotoRest;
    private Long TimeOpenCafe;
    private Long TimeCloseCafe;
    private String Address;
    private Double Stars;
    private int AverageCheck;
    private ArrayList<DishMenu> menus;
//    private ArrayList<MenuRestaurant> menuRestaurant;


    public Restaurants(String nameCafe, int photoRest, Long timeOpenCafe, Long timeCloseCafe, String address, Double stars) {
        NameCafe = nameCafe;
        PhotoRest = photoRest;
        TimeOpenCafe = timeOpenCafe;
        TimeCloseCafe = timeCloseCafe;
        Address = address;
        Stars = stars;
    }

    public Restaurants(String nameCafe, String address, Double stars) {
        NameCafe = nameCafe;
        Address = address;
        Stars = stars;
    }
    public Restaurants(String nameCafe, String address, Double stars, int resoursePhoto,
                       String restaurantDescription, int averageCheck, ArrayList<DishMenu> dishMenus){
        NameCafe = nameCafe;
        Address = address;
        Stars = stars;
        PhotoRest = resoursePhoto;
        RestaurantDescription = restaurantDescription;
        AverageCheck = averageCheck;
        menus = dishMenus;

    }

    public String getRestaurantDescription() {
        return RestaurantDescription;
    }

    public int getAverageCheck() {
        return AverageCheck;
    }

    public ArrayList<DishMenu> getMenus() {
        return menus;
    }

    public Restaurants(String nameCafe, String address, Double stars, int resourse ) {
        NameCafe = nameCafe;
        Address = address;
        Stars = stars;
        PhotoRest = resourse;
    }

    public String getNameCafe() {
        return NameCafe;
    }

    public int getPhotoRest() {
        return PhotoRest;
    }

    public Long getTimeOpenCafe() {
        return TimeOpenCafe;
    }

    public Long getTimeCloseCafe() {
        return TimeCloseCafe;
    }
    public String getTimeHours(){
        //маска для времени работы
        return "9:00 - 23:00";
    }

    public String getAddress() {
        return Address;
    }

    public Double getStars() {
        return Stars;
    }
}
