package xyz.sleepygamers.maithoncenteen.models;

public class Order {
    private String order_details, order_date,order_type,status,delivery_type;
    private int id, user_id,price;


    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOrder_details() {
        return order_details;
    }

    public void setOrder_details(String order_details) {
        this.order_details = order_details;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice() {
        setPrice();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.status = delivery_type;
    }

    public Order(int id, String order_details, String order_date, int price, String order_type, String delivery_type,String status) {
        this.id = id;
        //this.user_id=user_id;
        this.order_details=order_details;
        this.order_date=order_date;
        this.price=price;
        this.order_type=order_type;
        this.delivery_type=delivery_type;
        this.status=status;
    }
}