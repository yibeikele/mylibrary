package com.southwind.service;

import com.southwind.entity.Borrow;
import com.southwind.entity.Reader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface ReaderService {
    public List<Borrow> findBorrowById(int id);
    public int borrowCountById(int id);
    public void save(Reader reader);
    public int count();
    public List<Reader> findAll();
    public void delete(int id);
    public Reader find(int id);
    public void update(Reader reader);
    public HSSFWorkbook getWorkbook(int id);
}
