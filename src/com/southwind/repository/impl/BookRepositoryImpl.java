package com.southwind.repository.impl;

import com.southwind.entity.Book;
import com.southwind.repository.BookRepository;
import com.southwind.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<Book> findAll(int start,int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book where abled = 1 limit ?,?";
        List<Book> list = null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Book.class),start,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select count(id) from book";
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public void borrow(int bookId, int readerId, String borrowTime, String returnTime, int state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into borrow(bookid,readerid,borrowtime,returntime,state) values(?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,bookId);
            preparedStatement.setInt(2,readerId);
            preparedStatement.setString(3,borrowTime);
            preparedStatement.setString(4,returnTime);
            preparedStatement.setInt(5,state);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public void updateState(int id, int state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update book set abled = ? where id = ?";
        try {
            queryRunner.update(connection,sql,state,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public void save(Book book) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into book(name,author,publish,pages,price,bookcaseid,abled) values(?,?,?,?,?,?,1)";
        try {
            queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPublish(),book.getPages(),book.getPrice(),book.getBookcaseid());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "delete from book where id = ?";
        try {
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public Book find(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book where id = ?";
        Book book = null;
        try {
            book = queryRunner.query(connection,sql,new BeanHandler<>(Book.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return book;
    }

    @Override
    public void update(Book book) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update book set name = ?,author = ?,publish = ?,pages = ?,price = ?,bookcaseid = ? where id = ?";
        try {
            queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPublish(),book.getPages(),book.getPrice(),book.getBookcaseid(),book.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public List<Book> findAll() {
        Connection connection =  JDBCTools.getConnection();
        String sql = "select b.name,b.author,b.publish,b.pages,b.price,bc.name from book b,bookcase bc where b.bookcaseid = bc.id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();
        Book book = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString(1);
                String author = resultSet.getString(2);
                String publish = resultSet.getString(3);
                int pages = resultSet.getInt(4);
                double price = resultSet.getDouble(5);
                String bookCaseName = resultSet.getString(6);
                book = new Book(name,author,publish,pages,price,bookCaseName);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }
}
