package com.group43.cse360_project;

import java.io.IOException;

public class Book {
    private String key;
    private String title;
    private String author;
    private BookGenre genre;
    private BookCondition condition;
    private String seller;
    private float price;
    private int quantity;
    private ListingStatus status;

    public Book(String key, String title, String author, BookGenre genre, BookCondition condition,
                String seller, float price, int quantity, ListingStatus status) {
        this.key = key;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.condition = condition;
        this.seller = seller;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public String getSeller() {
        return seller;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public ListingStatus getListingStatus() {
        return status;
    }

    // Changing the quantity after a sale or new listing
    public void setQuantity(int quantity) throws IOException {
        int old = quantity;
        this.quantity = quantity;
        BookDB.updateQuantity(this, old);
    }

    // String representation (Same as Database entry)
    @Override
    public String toString() {
        return key + ":" + title + ":" + author + ":" + BookGenre.getBookGenreDBFlag(genre) + ":" +
                BookCondition.getBookConditionDBFlag(condition) + ":" + seller + ":" +
                Float.toString(price) + ":" + Integer.toString(quantity) + ":" +
                ListingStatus.getListingStatusDBFlag(status);
    }
}
