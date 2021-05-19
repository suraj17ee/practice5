package com.contextExample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String mob=req.getParameter("mob");
	String user=req.getParameter("user");
	String pass=req.getParameter("pass");
	
	Connection cn=null;
	PreparedStatement pst=null;
	PrintWriter out=resp.getWriter();
	ServletContext context=getServletContext();
	
	try {
		Class.forName(context.getInitParameter("driver"));
		cn=DriverManager.getConnection(context.getInitParameter("url"),context.getInitParameter("username"),context.getInitParameter("password"));
		pst=cn.prepareStatement("insert into simpleproject.registration (name,mobile,username,password) values(?,?,?,?)");
		pst.setString(1, name);
		pst.setInt(2,Integer.parseInt(mob));
		pst.setString(3,user);
		pst.setString(4, pass);
		
		pst.executeUpdate();
		
		out.println("<html><body><h3 style='color:green;'>signed up successfully!!</h3></body></html>");
		RequestDispatcher rd=req.getRequestDispatcher("home.html");
		rd.include(req, resp);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
