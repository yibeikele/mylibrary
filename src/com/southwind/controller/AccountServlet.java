package com.southwind.controller;

import com.southwind.entity.BookAdmin;
import com.southwind.entity.Reader;
import com.southwind.service.AccountService;
import com.southwind.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method != null){
            switch (method){
                case "login":
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    String type = req.getParameter("type");
                    Object object = accountService.login(username,password,type);
                    if(object == null){
                        resp.sendRedirect("/login.jsp");
                    }else{
                        HttpSession session = req.getSession();
                        switch (type){
                            case "reader":
                                Reader reader = (Reader) object;
                                session.setAttribute("reader",reader);
                                resp.sendRedirect("/index.jsp");
                                break;
                            case "bookadmin":
                                BookAdmin bookAdmin = (BookAdmin) object;
                                session.setAttribute("bookAdmin",bookAdmin);
                                resp.sendRedirect("/main.jsp");
                                break;
                        }
                    }
                    break;
                case "logout":
                    HttpSession session = req.getSession();
                    session.invalidate();
                    resp.sendRedirect("/login.jsp");
                    break;
            }
        }
    }
}
