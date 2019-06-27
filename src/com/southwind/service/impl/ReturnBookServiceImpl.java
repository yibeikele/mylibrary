package com.southwind.service.impl;

import com.southwind.entity.ReturnBook;
import com.southwind.repository.ReturnBookRepository;
import com.southwind.repository.impl.ReturnBookRepositoryImpl;
import com.southwind.service.ReturnBookService;

public class ReturnBookServiceImpl implements ReturnBookService {

    private ReturnBookRepository returnBookRepository = new ReturnBookRepositoryImpl();

    @Override
    public void save(ReturnBook returnBook) {
        returnBookRepository.save(returnBook);
    }
}
