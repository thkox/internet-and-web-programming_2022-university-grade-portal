package com.controller;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LogoutServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//terminate the session for the session management
        HttpSession session=request.getSession(false);
        session.invalidate(); 
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");	//redirect the user to the index.jsp page
        requestDispatcher.forward(request,response);
        System.out.println("Logout Succesfull");
    }
}
