package com.example.fitpeak;
public class Book {
    private String bookName;
    private String bookAuthor;
    private int bookPrice;
    //private String bookRating;

    public Book(String bookName, String bookAuthor, int bookPrice) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    //public String getBookRating() {
    //    return bookRating;
    //}

    //public void setBookRating(String bookRating) {
    //    this.bookRating = bookRating;
    //}
}

