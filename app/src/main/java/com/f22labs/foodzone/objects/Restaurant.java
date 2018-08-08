package com.f22labs.foodzone.objects;

public class Restaurant {
    private int id;
    private String restroName;
    private String restroAlias;
    private String phoneNumber;
    private String address;
    private String locality;
    private String country;
    private String city;
    private String logo;
    private String restroBanner;
    private String categoryName;
    private String minOrder;
    private String rating;
    private String reviews;


    public Restaurant(int id, String restroName, String restroAlias, String phoneNumber, String address, String locality, String country, String city, String logo, String restroBanner, String categoryName, String minOrder, String rating, String reviews) {
        this.id = id;
        this.restroName = restroName;
        this.restroAlias = restroAlias;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.locality = locality;
        this.country = country;
        this.city = city;
        this.logo = logo;
        this.restroBanner = restroBanner;
        this.categoryName = categoryName;
        this.minOrder = minOrder;
        this.rating = rating;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestroName() {
        return restroName;
    }

    public void setRestroName(String restroName) {
        this.restroName = restroName;
    }

    public String getRestroAlias() {
        return restroAlias;
    }

    public void setRestroAlias(String restroAlias) {
        this.restroAlias = restroAlias;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRestroBanner() {
        return restroBanner;
    }

    public void setRestroBanner(String restroBanner) {
        this.restroBanner = restroBanner;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(String minOrder) {
        this.minOrder = minOrder;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
