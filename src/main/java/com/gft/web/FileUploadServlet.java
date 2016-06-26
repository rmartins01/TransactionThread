package com.gft.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/fileUpload")
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("request recebido");

        InputStream in = req.getInputStream();
        byte buffer[] = new byte[1024];
        int totalLength = 0;
        int n;
        while ((n = in.read(buffer)) > 0) {
            totalLength += n;
        }

        resp.getWriter().write("file received with " + totalLength + " bytes");
    }
}
