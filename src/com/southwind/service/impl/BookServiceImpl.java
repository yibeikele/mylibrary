package com.southwind.service.impl;

import com.southwind.entity.Book;
import com.southwind.entity.BookCase;
import com.southwind.repository.BookCaseRepository;
import com.southwind.repository.BookRepository;
import com.southwind.repository.impl.BookCaseRepositoryImpl;
import com.southwind.repository.impl.BookRepositoryImpl;
import com.southwind.service.BookService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private BookCaseRepository bookCaseRepository = new BookCaseRepositoryImpl();

    @Override
    public List<Book> findAll(int page,int limit) {
        int start = (page-1)*limit;
        List<Book> list = bookRepository.findAll(start,limit);
        for (Book book:list){
            int bookcaseid = book.getBookcaseid();
            BookCase bookCase = bookCaseRepository.find(bookcaseid);
            book.setBookCaseName(bookCase.getName());
        }
        return list;
    }

    @Override
    public int count() {
        return bookRepository.count();
    }

    @Override
    public void borrow(int bookId, int readerId, String borrowTime, String returnTime, int state) {
        bookRepository.borrow(bookId,readerId,borrowTime,returnTime,state);
        bookRepository.updateState(bookId,0);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.delete(id);
    }

    @Override
    public Book find(int id) {
        return bookRepository.find(id);
    }

    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }

    @Override
    public void updateState(int id, int state) {
        bookRepository.updateState(id,state);
    }

    @Override
    public HSSFWorkbook getWorkbook() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("图书");
        List<Book> list = bookRepository.findAll();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("图书名称");
        cell = row.createCell(1);
        cell.setCellValue("作者");
        cell = row.createCell(2);
        cell.setCellValue("出版社");
        cell = row.createCell(3);
        cell.setCellValue("总页数");
        cell = row.createCell(4);
        cell.setCellValue("售价");
        cell = row.createCell(5);
        cell.setCellValue("图书分类");
        for(int i = 0; i < list.size(); i++){
            Book book = list.get(i);
            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellValue(book.getName());
            cell = row.createCell(1);
            cell.setCellValue(book.getAuthor());
            cell = row.createCell(2);
            cell.setCellValue(book.getPublish());
            cell = row.createCell(3);
            cell.setCellValue(book.getPages());
            cell = row.createCell(4);
            cell.setCellValue(book.getPrice());
            cell = row.createCell(5);
            cell.setCellValue(book.getBookCaseName());
        }
        return workbook;
    }
}
