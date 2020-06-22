package com.hfad.sneakershop.model;

public class Sneaker {
    private String name;
    private int size;
    private int price;
    private int imageResourceId;

    public Sneaker(String name, int size, int price, int imageResourceId) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public Sneaker(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
