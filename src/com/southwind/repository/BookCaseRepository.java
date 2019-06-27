package com.southwind.repository;

import com.southwind.entity.BookCase;

import java.util.List;

public interface BookCaseRepository {
    public BookCase find(int id);
    public List<BookCase> findAll();
}
