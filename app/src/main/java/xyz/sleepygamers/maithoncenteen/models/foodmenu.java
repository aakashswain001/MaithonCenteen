package xyz.sleepygamers.maithoncenteen.models;

public class foodmenu {
    private int id;
    private String name;
    private String price;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;


    public foodmenu(int id, String name, String category, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;

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

    public String getImg() {
        return img;
    }

}
