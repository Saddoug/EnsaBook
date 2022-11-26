package com.ensa.ensabook;

public class Model {
    private Integer id;
    private String title;
    private String author;
    private String category;
    private String description;
    private double price;
    private boolean favorite;
    private int bookPhoto;


    public Model() {
    }

    public Model(Integer id, String title, String author, String category, String description, double price, boolean favorite,int bookPhoto) {
        this.id = null;
        this.title = title;
        this.author = author;
        this.category = category;
        this.description = description;
        this.price = price;
        this.favorite = favorite;
        this.bookPhoto=bookPhoto;
    }
    public Model( String title, String author, String category, String description, double price, boolean favorite,int bookPhoto) {

        this.title = title;
        this.author = author;
        this.category = category;
        this.description = description;
        this.price = price;
        this.favorite = favorite;
        this.bookPhoto=bookPhoto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookPhoto() {
        return bookPhoto;
    }

    public void setBookPhoto(int bookPhoto) {
        this.bookPhoto = bookPhoto;
    }
}