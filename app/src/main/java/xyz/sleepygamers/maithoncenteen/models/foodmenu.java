package xyz.sleepygamers.maithoncenteen.models;

public class foodmenu {
    private int id;
    private String name;
    private String price;
    private String img;

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




    public foodmenu(int id, String name,  String price,String img) {
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
