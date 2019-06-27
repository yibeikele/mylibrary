package com.southwind.service.impl;

import com.southwind.entity.BarData;
import com.southwind.entity.Book;
import com.southwind.entity.Borrow;
import com.southwind.entity.PieData;
import com.southwind.repository.BorrowRepository;
import com.southwind.repository.impl.BorrowRepositoryImpl;
import com.southwind.service.BorrowService;

import java.util.List;

public class BorrowServiceImpl implements BorrowService {

    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();

    @Override
    public List<Borrow> findAll(int state) {
        List<Borrow> list = borrowRepository.findAll(state);
        for (Borrow borrow : list){
            switch (borrow.getState()){
                case "0":
                    borrow.setState("未审核");
                    break;
                case "1":
                    borrow.setState("审核通过");
                    break;
                case "2":
                    borrow.setState("审核未通过");
                    break;
                case "3":
                    borrow.setState("已归还");
                    break;
            }
        }
        return list;
    }

    @Override
    public int count() {
        return borrowRepository.count();
    }

    @Override
    public void updateState(int id, int bookAdminId, int state) {
        borrowRepository.updateState(id,bookAdminId,state);
    }

    @Override
    public BarData getBarData() {
        return borrowRepository.getBarData();
    }

    @Override
    public List<PieData> getPieData() {
        return borrowRepository.getPieData();
    }
}
