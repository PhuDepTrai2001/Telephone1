package com.example.listview;

public class ContactModel {
    private String name;
    private String phone;
    private int Image;

    public ContactModel(String name, String phone, int image) {
        this.name = name;
        this.phone = phone;
        Image = image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getImage() {
        return Image;
    }
}
