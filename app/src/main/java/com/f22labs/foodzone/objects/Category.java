package com.f22labs.foodzone.objects;

public class Category {
    private int id;
    private String name;
    private String catAlias;
    private String categoryIcon;


    public Category(int id, String name, String catAlias, String categoryIcon) {
        this.id = id;
        this.name = name;
        this.catAlias = catAlias;
        this.categoryIcon = categoryIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatAlias() {
        return catAlias;
    }

    public void setCatAlias(String catAlias) {
        this.catAlias = catAlias;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catAlias='" + catAlias + '\'' +
                ", categoryIcon='" + categoryIcon + '\'' +
                '}';
    }
}
