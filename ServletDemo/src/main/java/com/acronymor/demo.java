package com.acronymor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class demo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello world");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Hello Init");
    }

    @Override
    public void destroy() {
        System.out.printf("Hello destroy");
    }
}
