package com.serve;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.serve.utill.ConnectionUtill;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name=req.getParameter("name");
		String uname=req.getParameter("uname");
		String pass=req.getParameter("pass");
		String mail=req.getParameter("mail");
		Long phone=Long.parseLong(req.getParameter("phone"));
		
		
		try {
		
		
			ConnectionUtill conUtill=new ConnectionUtill();
			Connection con=conUtill.getDBConnect();
			String query="insert into users( name, username, role,password, email, phoneNumber) values(?,?,?,?,?,?)";
			
			PreparedStatement stmt=con.prepareStatement(query);
		
			stmt.setString(1, name);
			stmt.setString(2, uname);
			stmt.setString(3, "User");
			stmt.setString(4, pass);
			stmt.setString(5, mail);
			stmt.setLong(6, phone);
			System.out.println(stmt.executeUpdate()+" row inserted");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   
		System.out.println(name+"\n"+uname);
		PrintWriter output=res.getWriter();
		output.println("Name:\t"+name);
		output.println("Username:\t"+uname);
		output.println("Password:\t"+pass);
		output.println("Email:\t"+mail);
		output.println("Phone Number:\t"+phone);
	}
	

}
