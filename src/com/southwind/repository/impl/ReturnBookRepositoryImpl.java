package com.southwind.repository.impl;

import com.southwind.entity.ReturnBook;
import com.southwind.repository.ReturnBookRepository;
import com.southwind.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class ReturnBookRepositoryImpl implements ReturnBookRepository {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public void save(ReturnBook returnBook) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into returnbook(bookid,readerid,returntime,adminid) values(?,?,?,?)";
        try {
            queryRunner.update(connection,sql,returnBook.getBookId(),returnBook.getReaderId(),returnBook.getReturnTime(),returnBook.getAdminId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }
}
