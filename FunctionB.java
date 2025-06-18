package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;

public class FunctionB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("Hello from Function B! Authentication successful.");
    }
}
