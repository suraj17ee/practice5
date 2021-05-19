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

public class LogIn extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String user=req.getParameter("user");
	String pass=req.getParameter("pass");
	
	Connection cn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	PrintWriter out=resp.getWriter();
	ServletContext context=getServletContext();
	
	try {
		Class.forName(context.getInitParameter("driver"));
		cn=DriverManager.getConnection(context.getInitParameter("url"),context.getInitParameter("username"),context.getInitParameter("password"));
		pst=cn.prepareStatement("select * from simpleproject.registration where username=?");
		
		pst.setString(1, user);
		rs=pst.executeQuery();
		if(rs.next()) {
			if(rs.getString("password").equals(pass)) {
//			out.println("<html><body><h1>welcome "+user+" to jspiders</h1></body></html>");
			ServletContext con=getServletContext();
			
			con.setAttribute("username", user);
			con.setAttribute("cn", cn);
//			con.setAttribute("name", rs.getString("name"));
//			con.setAttribute("mob", rs.getInt("mobile"));
			
			resp.sendRedirect("prof");
		}
			else {
				out.println("<html><body><h1>invalid password</h1></body></html>");
				RequestDispatcher rd=req.getRequestDispatcher("home.html");
				rd.include(req, resp);
			}
		}
		else {
			out.println("<html><body><h1>first register/signup to login</h1></body></html>");
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		}
	}
}
