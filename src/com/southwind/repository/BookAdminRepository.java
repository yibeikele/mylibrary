package com.southwind.repository;

import com.southwind.entity.BookAdmin;

public interface BookAdminRepository {
    public BookAdmin login(String username,String password);
}
