package com.southwind.filter;

import com.southwind.entity.BookAdmin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BookAdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        BookAdmin bookAdmin = (BookAdmin) session.getAttribute("bookAdmin");
        if(bookAdmin == null){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/login.jsp");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
