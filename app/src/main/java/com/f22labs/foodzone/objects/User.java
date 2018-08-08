package com.f22labs.foodzone.objects;

public class User {
    private int userId;
    private String userName;
    private String phone_number;
    private String email;
    private String password;
    private String delivery_area;
    private String home_address;
    private String work_address;
    private String other_address;
    private String delivery_at;

    public User(int userId, String userName, String phone_number, String email, String password, String delivery_area, String home_address, String work_address, String other_address, String delivery_at) {
        this.userId = userId;
        this.userName = userName;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.delivery_area = delivery_area;
        this.home_address = home_address;
        this.work_address = work_address;
        this.other_address = other_address;
        this.delivery_at = delivery_at;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDelivery_area() {
        return delivery_area;
    }

    public void setDelivery_area(String delivery_area) {
        this.delivery_area = delivery_area;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public String getWork_address() {
        return work_address;
    }

    public void setWork_address(String work_address) {
        this.work_address = work_address;
    }

    public String getOther_address() {
        return other_address;
    }

    public void setOther_address(String other_address) {
        this.other_address = other_address;
    }

    public String getDelivery_at() {
        return delivery_at;
    }

    public void setDelivery_at(String delivery_at) {
        this.delivery_at = delivery_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", delivery_area='" + delivery_area + '\'' +
                ", home_address='" + home_address + '\'' +
                ", work_address='" + work_address + '\'' +
                ", other_address='" + other_address + '\'' +
                ", delivery_at='" + delivery_at + '\'' +
                '}';
    }
}
