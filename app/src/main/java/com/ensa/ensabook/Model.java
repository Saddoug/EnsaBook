package com.ensa.ensabook;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {
    private Integer id;
    private String title;
    private String author;
    private String category;
    private String description;
    private double price;
    private boolean favorite;
    private int bookPhoto;
    private boolean reserved;



    public Model() {
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Model(Integer id, String title, String author, String category, String description, double price, boolean favorite, int bookPhoto, boolean reserved) {
        this.id = null;
        this.title = title;
        this.author = author;
        this.category = category;
        this.description = description;
        this.price = price;
        this.favorite = favorite;
        this.bookPhoto=bookPhoto;
        this.reserved=reserved;
    }
    public Model(Integer id, String title, String author, String category, String description, double price, boolean favorite) {
        this.id = null;
        this.title = title;
        this.author = author;
        this.category = category;
        this.description = description;
        this.price = price;
        this.favorite = favorite;


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
    public Model( String title, String author, String category, double price) {

        this.title = title;
        this.author = author;
        this.category = category;
        this.description = description;
        this.price = price;
        this.favorite = favorite;
        this.bookPhoto=bookPhoto;
    }

    protected Model(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        author = in.readString();
        category = in.readString();
        description = in.readString();
        price = in.readDouble();
        favorite = in.readByte() != 0;
        bookPhoto = in.readInt();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };


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
        return this.favorite;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(category);
        parcel.writeString(description);
        parcel.writeDouble(price);
        parcel.writeByte((byte) (favorite ? 1 : 0));
        parcel.writeInt(bookPhoto);
    }
}