package com.southwind.service.impl;

import com.southwind.repository.BookAdminRepository;
import com.southwind.repository.ReaderRepository;
import com.southwind.repository.impl.BookAdminRepositoryImpl;
import com.southwind.repository.impl.ReaderRepositoryImpl;
import com.southwind.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private BookAdminRepository bookAdminRepository = new BookAdminRepositoryImpl();

    @Override
    public Object login(String username, String password, String type) {
        Object object = null;
        switch (type){
            case "reader":
                object = readerRepository.login(username,password);
                break;
            case "bookadmin":
                object = bookAdminRepository.login(username,password);
                break;
        }
        return object;
    }
}
