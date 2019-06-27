package com.southwind.entity;

public class Book {
    private int id;
    private String name;
    private String author;
    private String publish;
    private int pages;
    private double price;
    private int bookcaseid;
    private String bookCaseName;

    public String getBookCaseName() {
        return bookCaseName;
    }

    public void setBookCaseName(String bookCaseName) {
        this.bookCaseName = bookCaseName;
    }

    public int getBookcaseid() {
        return bookcaseid;
    }

    public void setBookcaseid(int bookcaseid) {
        this.bookcaseid = bookcaseid;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", bookcaseid=" + bookcaseid +
                ", bookCaseName='" + bookCaseName + '\'' +
                '}';
    }

    public Book() {
    }

    public Book(String name, String author, String publish, int pages, double price, int bookcaseid) {
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookcaseid = bookcaseid;
    }

    public Book(int id, String name, String author, String publish, int pages, double price, int bookcaseid) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookcaseid = bookcaseid;
    }

    public Book(String name, String author, String publish, int pages, double price, String bookCaseName) {
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookCaseName = bookCaseName;
    }
}
