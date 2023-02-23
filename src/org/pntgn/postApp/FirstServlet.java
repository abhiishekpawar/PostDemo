package org.pntgn.postApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fs")
public class FirstServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		String id = req.getParameter("i");
		int sid = Integer.parseInt(id);
		String name = req.getParameter("nm");
		String dept = req.getParameter("dp");
		String perc = req.getParameter("pr");
		double sperc = Double.parseDouble(perc);
		
		PrintWriter out = resp.getWriter();
		//presentation logic
		out.println("<html><body bgcolor='24E0B5'><h1> The Student details are </h1>"
				+"<br></br><h2> The student "+name+" succesfully registerd to "+dept+ " dept</h1></body></html>");
		
		out.flush();
		out.checkError();
		out.close();
		
		//jdbc code for persistence class
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into pentagon.student values(?,?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt = con.prepareStatement(sql);
			//set data for placeholder before exection
			pstmt.setInt(1, sid);
			pstmt.setString(2, name);
			pstmt.setString(3,dept);
			pstmt.setDouble(4, sperc);
			
			//execute now
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
