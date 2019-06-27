package com.southwind.repository.impl;

import com.southwind.entity.Borrow;
import com.southwind.entity.Reader;
import com.southwind.repository.ReaderRepository;
import com.southwind.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReaderRepositoryImpl implements ReaderRepository {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public Reader login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where username = ? and password = ?";
        Reader reader = null;
        try {
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return reader;
    }

    @Override
    public List<Borrow> findBorrowById(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select br.id as id,b.name as bookName,b.author ,b.price,r.cardid,r.name as readerName,r.tel,\n" +
                "br.borrowtime,br.returntime,br.state\n" +
                "from borrow br,book b,reader r where\n" +
                "br.bookid = b.id and br.readerid = r.id and br.readerid = ?";
        List<Borrow> list = null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    @Override
    public int borrowCountById(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(id) from borrow where state = 0 and readerid = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
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
    public void save(Reader reader) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into reader(username,password,name,tel,cardid,gender) values(?,?,?,?,?,?)";
        try {
            queryRunner.update(connection,sql,reader.getUsername(),reader.getPassword(),reader.getName(),reader.getTel(),reader.getCardId(),reader.getGender());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(id) from reader";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
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
    public List<Reader> findAll() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader";
        List<Reader> list = null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Reader.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    @Override
    public void delete(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "delete from reader where id = ?";
        try {
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public Reader find(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where id = ?";
        Reader reader = null;
        try {
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return reader;
    }

    @Override
    public void update(Reader reader) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update reader set username = ?,password = ?,name = ?,tel = ?,gender = ?,cardid = ? where id = ?";
        try {
            queryRunner.update(connection,sql,reader.getUsername(),reader.getPassword(),reader.getName(),reader.getTel(),reader.getGender(),reader.getCardId(),reader.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }
}
