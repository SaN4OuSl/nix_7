package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "sample-servlet", urlPatterns = "/servlet")
public class SampleServlet extends HttpServlet {
    
    private static final long serialVersionUID = -8948379822734246956L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleServlet.class);
    
    private static final Set<String> usersSet = ConcurrentHashMap.newKeySet();
    
    @Override
    public void init() {
        LOGGER.info(getServletName() + " initialized");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        String userIpAndHeader = req.getRemoteAddr() + " :: " + req.getHeader("User-Agent");
        usersSet.add(userIpAndHeader);
        
        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">List of users</h1>");
        responseBody.println("<ul>");
        for (String user : usersSet) {
            if (user.equals(userIpAndHeader)) {
                responseBody.println("<li><b> " + user + "</b></li>");
            } else {
                responseBody.println("<li>" + user + "</li>");
            }
        }
        responseBody.println("</ul>");
    }
    
    @Override
    public void destroy() {
        LOGGER.info(getServletName() + " destroyed");
    }
}