package com.southwind.service;

import com.southwind.entity.Book;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page,int limit);
    public int count();
    public void borrow(int bookId,int readerId,String borrowTime,String returnTime,int state);
    public void save(Book book);
    public void delete(int id);
    public Book find(int id);
    public void update(Book book);
    public void updateState(int id,int state);
    public HSSFWorkbook getWorkbook();
}
