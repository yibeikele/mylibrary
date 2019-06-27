package com.southwind.repository.impl;

import com.southwind.entity.BarData;
import com.southwind.entity.Borrow;
import com.southwind.entity.PieData;
import com.southwind.repository.BorrowRepository;
import com.southwind.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<Borrow> findAll(int state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select br.id,r.id as readerId,b.id as bookId,b.name as bookName,r.name as readerName,br.borrowtime,br.returntime,br.state from borrow br,book b,reader r where br.bookid = b.id and br.readerid = r.id and state = ?";
        List<Borrow> list = new ArrayList<>();
        try {
            switch (state) {
                case 0:
                    list = queryRunner.query(connection, sql, new BeanListHandler<>(Borrow.class), 0);
                    break;
                case 1:
                    list = queryRunner.query(connection, sql, new BeanListHandler<>(Borrow.class), 1);
                    break;
            }
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
        String sql = "select count(id) from borrow where state = 0";
        int count = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
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
    public void updateState(int id, int bookAdminId, int state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update borrow set adminid = ?,state = ? where id = ?";
        try {
            queryRunner.update(connection,sql,bookAdminId,state,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public BarData getBarData() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select b.name,count(br.bookid) from borrow br,book b where b.id = br.bookid and state in (1,3) group by br.bookid";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> names = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        BarData barData = new BarData();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString(1);
                int count = resultSet.getInt(2);
                names.add(name);
                counts.add(count);
            }
            barData.setName(names);
            barData.setCount(counts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return barData;
    }

    @Override
    public List<PieData> getPieData() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select b.name,count(br.bookid) from borrow br,book b where b.id = br.bookid and state in (1,3) group by br.bookid";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<PieData> list = new ArrayList<>();
        PieData pieData = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString(1);
                int count = resultSet.getInt(2);
                pieData = new PieData(name,count);
                list.add(pieData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }
}
