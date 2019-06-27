package com.southwind.service.impl;

import com.southwind.entity.BookCase;
import com.southwind.repository.BookCaseRepository;
import com.southwind.repository.impl.BookCaseRepositoryImpl;
import com.southwind.service.BookCaseService;

import java.util.List;

public class BookCaseServiceImpl implements BookCaseService {

    private BookCaseRepository bookCaseRepository = new BookCaseRepositoryImpl();

    @Override
    public List<BookCase> findAll() {
        return bookCaseRepository.findAll();
    }
}
