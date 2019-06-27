package com.southwind.repository;

import com.southwind.entity.Borrow;
import com.southwind.entity.Reader;

import java.util.List;

public interface ReaderRepository {
    public Reader login(String username,String password);
    public List<Borrow> findBorrowById(int id);
    public int borrowCountById(int id);
    public void save(Reader reader);
    public int count();
    public List<Reader> findAll();
    public void delete(int id);
    public Reader find(int id);
    public void update(Reader reader);
}
