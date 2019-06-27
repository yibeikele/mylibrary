package com.southwind.repository.impl;

import com.southwind.entity.BookCase;
import com.southwind.repository.BookCaseRepository;
import com.southwind.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookCaseRepositoryImpl implements BookCaseRepository {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public BookCase find(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookcase where id = ?";
        BookCase bookCase = null;
        try {
            bookCase = queryRunner.query(connection,sql,new BeanHandler<>(BookCase.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return bookCase;
    }

    @Override
    public List<BookCase> findAll() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookcase";
        List<BookCase> list = null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(BookCase.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }
}
