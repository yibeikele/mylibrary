package com.southwind.service;

import com.southwind.entity.BarData;
import com.southwind.entity.Borrow;
import com.southwind.entity.PieData;

import java.util.List;

public interface BorrowService {
    public List<Borrow> findAll(int state);
    public int count();
    public void updateState(int id,int bookAdminId,int state);
    public BarData getBarData();
    public List<PieData> getPieData();
}
