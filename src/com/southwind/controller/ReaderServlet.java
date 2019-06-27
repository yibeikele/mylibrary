package com.southwind.controller;

import com.southwind.entity.Borrow;
import com.southwind.entity.BorrowVO;
import com.southwind.entity.Reader;
import com.southwind.entity.ReaderVO;
import com.southwind.service.ReaderService;
import com.southwind.service.impl.ReaderServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ReaderServlet extends HttpServlet {

    private ReaderService readerService = new ReaderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method!=null){
            String idStr = null;
            String username = null;
            String password = null;
            String name = null;
            String tel = null;
            String cardId = null;
            String gender = null;
            int id = 0;
            switch (method){
                case "getBorrowed":
                    resp.setContentType("text/json;charset=utf-8");
                    HttpSession session = req.getSession();
                    Reader reader = (Reader) session.getAttribute("reader");
                    int readerId = reader.getId();
                    List<Borrow> list = readerService.findBorrowById(readerId);
                    BorrowVO borrowVO = new BorrowVO(0,"",readerService.borrowCountById(readerId),list);
                    JSONObject jsonObject = JSONObject.fromObject(borrowVO);
                    resp.getWriter().write(jsonObject.toString());
                    break;
                case "addReader":
                    username = req.getParameter("username");
                    password = req.getParameter("password");
                    name = req.getParameter("name");
                    tel = req.getParameter("tel");
                    cardId = req.getParameter("cardid");
                    gender = req.getParameter("gender");
                    Reader reader1 = new Reader(username,password,name,tel,cardId,gender);
                    readerService.save(reader1);
                    resp.sendRedirect("/reader_manage.jsp");
                    break;
                case "findAll":
                    resp.setContentType("text/json;charset=UTF-8");
                    ReaderVO readerVO = new ReaderVO();
                    readerVO.setCode(0);
                    readerVO.setMsg("");
                    readerVO.setCount(readerService.count());
                    readerVO.setData(readerService.findAll());
                    JSONObject jsonObject1 = JSONObject.fromObject(readerVO);
                    resp.getWriter().write(jsonObject1.toString());
                    break;
                case "delete":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    readerService.delete(id);
                    resp.sendRedirect("/reader_manage.jsp");
                    break;
                case "find":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    Reader reader2 = readerService.find(id);
                    req.setAttribute("reader",reader2);
                    req.getRequestDispatcher("reader_edit.jsp").forward(req,resp);
                    break;
                case "update":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    username = req.getParameter("username");
                    password = req.getParameter("password");
                    name = req.getParameter("name");
                    tel = req.getParameter("tel");
                    cardId = req.getParameter("cardId");
                    gender = req.getParameter("gender");
                    Reader reader3 = new Reader(id,username,password,name,tel,cardId,gender);
                    readerService.update(reader3);
                    resp.sendRedirect("/reader_manage.jsp");
                    break;
            }
        }
    }
}
