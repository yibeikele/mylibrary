package com.southwind.controller;

import com.southwind.entity.*;
import com.southwind.service.*;
import com.southwind.service.impl.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookAdminServlet extends HttpServlet {

    private BookCaseService bookCaseService = new BookCaseServiceImpl();
    private BookService bookService = new BookServiceImpl();
    private BorrowService borrowService = new BorrowServiceImpl();
    private ReturnBookService returnBookService = new ReturnBookServiceImpl();
    private ReaderService readerService = new ReaderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=UTF-8");
        String method = req.getParameter("method");
        if(method!=null){
            String idStr = null;
            int id = 0;
            String name = null;
            String author = null;
            String publish = null;
            String pagesStr = null;
            int pages = 0;
            String priceStr = null;
            double price = 0.0;
            String bookCaseIdStr = null;
            int bookCaseId = 0;
            Book book = null;
            switch (method){
                case "findBookCases":
                    List<BookCase> list = bookCaseService.findAll();
                    req.setAttribute("list",list);
                    req.getRequestDispatcher("/book_add.jsp").forward(req,resp);
                    break;
                case "addBook":
                    name = req.getParameter("name");
                    author = req.getParameter("author");
                    publish = req.getParameter("publish");
                    pagesStr = req.getParameter("pages");
                    pages = Integer.parseInt(pagesStr);
                    priceStr = req.getParameter("price");
                    price = Double.parseDouble(priceStr);
                    bookCaseIdStr = req.getParameter("bookCaseId");
                    bookCaseId = Integer.parseInt(bookCaseIdStr);
                    book = new Book(name,author,publish,pages,price,bookCaseId);
                    bookService.save(book);
                    resp.sendRedirect("/book_manage.jsp");
                    break;
                case "delete":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    bookService.delete(id);
                    resp.sendRedirect("/book_manage.jsp");
                    break;
                case "find":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    Book book1 = bookService.find(id);
                    req.setAttribute("book",book1);
                    List<BookCase> list1 = bookCaseService.findAll();
                    req.setAttribute("list",list1);
                    req.getRequestDispatcher("/book_edit.jsp").forward(req,resp);
                    break;
                case "edit":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    name = req.getParameter("name");
                    author = req.getParameter("author");
                    publish = req.getParameter("publish");
                    pagesStr = req.getParameter("pages");
                    pages = Integer.parseInt(pagesStr);
                    priceStr = req.getParameter("price");
                    price = Double.parseDouble(priceStr);
                    bookCaseIdStr = req.getParameter("bookCaseId");
                    bookCaseId = Integer.parseInt(bookCaseIdStr);
                    book = new Book(id,name,author,publish,pages,price,bookCaseId);
                    bookService.update(book);
                    resp.sendRedirect("/book_manage.jsp");
                    break;
                case "getBorrows":
                    BorrowVO borrowVO = new BorrowVO();
                    borrowVO.setCode(0);
                    borrowVO.setMsg("");
                    borrowVO.setCount(borrowService.count());
                    borrowVO.setData(borrowService.findAll(0));
                    JSONObject jsonObject = JSONObject.fromObject(borrowVO);
                    resp.getWriter().write(jsonObject.toString());
                    break;
                case "agreeBorrow":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    HttpSession session = req.getSession();
                    BookAdmin bookAdmin = (BookAdmin) session.getAttribute("bookAdmin");
                    int bookAdminId = bookAdmin.getId();
                    borrowService.updateState(id,bookAdminId,1);
                    resp.sendRedirect("/borrow_manage.jsp");
                    break;
                case "disAgreeBorrow":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    HttpSession session2 = req.getSession();
                    BookAdmin bookAdmin2 = (BookAdmin) session2.getAttribute("bookAdmin");
                    int bookAdminId2 = bookAdmin2.getId();
                    String bookIdStr = req.getParameter("bookId");
                    int bookId = Integer.parseInt(bookIdStr);
                    borrowService.updateState(id,bookAdminId2,2);
                    bookService.updateState(bookId,1);
                    resp.sendRedirect("/borrow_manage.jsp");
                    break;
                case "getReturn":
                    BorrowVO borrowVO2 = new BorrowVO();
                    borrowVO2.setCode(0);
                    borrowVO2.setMsg("");
                    borrowVO2.setCount(borrowService.count());
                    borrowVO2.setData(borrowService.findAll(1));
                    JSONObject jsonObject2 = JSONObject.fromObject(borrowVO2);
                    resp.getWriter().write(jsonObject2.toString());
                    break;
                case "agreeReturn":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    HttpSession session3 = req.getSession();
                    BookAdmin bookAdmin3 = (BookAdmin) session3.getAttribute("bookAdmin");
                    int bookAdminId3 = bookAdmin3.getId();
                    String bookIdStr3 = req.getParameter("bookId");
                    int bookId3 = Integer.parseInt(bookIdStr3);
                    String readerIdStr = req.getParameter("readerId");
                    int readerId = Integer.parseInt(readerIdStr);
                    bookService.updateState(bookId3,1);
                    borrowService.updateState(id,bookAdminId3,3);

                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    ReturnBook returnBook = new ReturnBook(bookId3,readerId,bookAdminId3,simpleDateFormat.format(date).toString());
                    returnBookService.save(returnBook);
                    resp.sendRedirect("/borrow_back.jsp");
                    break;
                case "getBarData":
                    BarData barData = borrowService.getBarData();
                    JSONObject jsonObject1 = JSONObject.fromObject(barData);
                    resp.getWriter().write(jsonObject1.toString());
                    break;
                case "getPieData":
                    List<PieData> list2 = borrowService.getPieData();
                    JSONArray jsonArray = JSONArray.fromObject(list2);
                    resp.getWriter().write(jsonArray.toString());
                    break;
                case "exportBook":
                    HSSFWorkbook hssfWorkbook = bookService.getWorkbook();
                    resp.setContentType("application/x-msdownload");
                    String fileName = URLEncoder.encode("图书信息.xls","UTF-8");
                    resp.setHeader("Content-Disposition","attachment;filename="+fileName);
                    OutputStream outputStream = resp.getOutputStream();
                    hssfWorkbook.write(outputStream);
                    outputStream.close();
                    break;
                case "exportBorrow":
                    HttpSession session1 = req.getSession();
                    Reader reader = (Reader) session1.getAttribute("reader");
                    readerId = reader.getId();
                    HSSFWorkbook workbook = readerService.getWorkbook(readerId);
                    resp.setContentType("application/x-msdownload");
                    String fileName1 = URLEncoder.encode(reader.getName()+"的借书记录.xls","UTF-8");
                    resp.setHeader("Content-Disposition","attachment;filename="+fileName1);
                    OutputStream outputStream1 = resp.getOutputStream();
                    workbook.write(outputStream1);
                    outputStream1.close();
                    break;
            }
        }
    }
}
