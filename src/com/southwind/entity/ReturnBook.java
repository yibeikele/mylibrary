package com.southwind.entity;

public class ReturnBook {
    private int id;
    private int bookId;
    private int readerId;
    private int adminId;
    private String returnTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public ReturnBook() {
    }

    public ReturnBook(int bookId, int readerId, int adminId, String returnTime) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.adminId = adminId;
        this.returnTime = returnTime;
    }
}
