package com.contextExample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/prof")
public class Profile extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	ServletContext con=getServletContext();
	Connection cn=(Connection)con.getAttribute("cn");
	String user=(String)con.getAttribute("username");
	PreparedStatement pst;
	try {
		String qu="select * from simpleproject.registration where username=?";
		pst=cn.prepareStatement(qu); 
		pst.setString(1, user);		
		ResultSet rs=pst.executeQuery();
	
		if(rs.next()) {
			PrintWriter out=resp.getWriter();
			out.println("<html><body>welcome "+rs.getString("name")+" your number is "+rs.getInt("mobile")+"</body></html>");
		
			resp.sendRedirect("prof");
		
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
}
}
