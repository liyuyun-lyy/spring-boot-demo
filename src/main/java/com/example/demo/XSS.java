package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liyuyun.lyy
 * @date 2024/10/17 14:54
 */
public class XSS extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // BAD: a request parameter is written directly to the Servlet response stream
        response.getWriter().print(
            "The page \"" + request.getParameter("page") + "\" was not found.");

    }
}
