package com.southwind.service.impl;

import com.southwind.entity.Borrow;
import com.southwind.entity.Reader;
import com.southwind.repository.ReaderRepository;
import com.southwind.repository.impl.ReaderRepositoryImpl;
import com.southwind.service.ReaderService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {

    private ReaderRepository readerRepository = new ReaderRepositoryImpl();

    @Override
    public List<Borrow> findBorrowById(int id) {
        List<Borrow> list = readerRepository.findBorrowById(id);
        for (Borrow borrow:list){
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
    public int borrowCountById(int id) {
        return readerRepository.borrowCountById(id);
    }

    @Override
    public void save(Reader reader) {
        readerRepository.save(reader);
    }

    @Override
    public int count() {
        return readerRepository.count();
    }

    @Override
    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    @Override
    public void delete(int id) {
        readerRepository.delete(id);
    }

    @Override
    public Reader find(int id) {
        return readerRepository.find(id);
    }

    @Override
    public void update(Reader reader) {
        readerRepository.update(reader);
    }

    @Override
    public HSSFWorkbook getWorkbook(int id) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        Sheet sheet = hssfWorkbook.createSheet("借书记录");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("图书名称");
        cell = row.createCell(1);
        cell.setCellValue("读者名称");
        cell = row.createCell(2);
        cell.setCellValue("借书时间");
        cell = row.createCell(3);
        cell.setCellValue("还书时间");
        cell = row.createCell(4);
        cell.setCellValue("状态");
        List<Borrow> list = readerRepository.findBorrowById(id);
        for(int i = 0; i < list.size(); i++){
            Borrow borrow = list.get(i);
            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellValue(borrow.getBookName());
            cell = row.createCell(1);
            cell.setCellValue(borrow.getReaderName());
            cell = row.createCell(2);
            cell.setCellValue(borrow.getBorrowTime());
            cell = row.createCell(3);
            cell.setCellValue(borrow.getReturnTime());
            cell = row.createCell(4);
            String state = borrow.getState();
            switch (state){
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
            cell.setCellValue(borrow.getState());
        }
        return hssfWorkbook;
    }
}
