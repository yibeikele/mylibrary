package com.southwind.repository.impl;

import com.southwind.entity.BookAdmin;
import com.southwind.repository.BookAdminRepository;
import com.southwind.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class BookAdminRepositoryImpl implements BookAdminRepository {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public BookAdmin login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookadmin where username = ? and password = ?";
        BookAdmin bookAdmin = null;
        try {
            bookAdmin = queryRunner.query(connection,sql,new BeanHandler<>(BookAdmin.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return bookAdmin;
    }
}
