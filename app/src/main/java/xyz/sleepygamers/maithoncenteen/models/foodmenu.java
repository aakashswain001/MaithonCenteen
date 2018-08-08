package xyz.sleepygamers.maithoncenteen.models;


import java.io.Serializable;

public class foodmenu implements Serializable {
    private int id;
    private String name, price, type;
    private int count;
    public foodmenu() {
    }

    public foodmenu(int id, String name, String price, String type) {
        this.count =0;
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
