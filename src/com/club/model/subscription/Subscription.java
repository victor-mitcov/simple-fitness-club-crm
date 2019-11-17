package com.club.model.subscription;

public class Subscription {
    private Integer id;
    private String name;
    private Integer price;
    private String type;


    public Subscription(Integer id, String name, Integer price, String type) {
        this.id = id;
        setId(id);
        setName(name);
        setPrice(price);
        setType(type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object[] toArray() {
        return new Object[]{getId(), getName(), getPrice(), getType()};
    }
}
