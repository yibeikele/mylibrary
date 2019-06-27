package com.southwind.repository;

import com.southwind.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int start,int limit);
    public int count();
    public void borrow(int bookId,int readerId,String borrowTime,String returnTime,int state);
    public void updateState(int id,int state);
    public void save(Book book);
    public void delete(int id);
    public Book find(int id);
    public void update(Book book);
    public List<Book> findAll();
}
