package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/display")
public class DisplayBook extends HttpServlet
{
	Connection con;
	 public void init() throws ServletException{
		 
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?user=root&password=sql@123");
				
			} catch (ClassNotFoundException e) 
			{
				
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 Statement stmt = null;
		 ResultSet rs = null;
		 //Create Query
		 String query="select * from book_store";
		 //Create Statement Platform
		 try {
			stmt=con.createStatement();
			//Execute the Query
			rs=stmt.executeQuery(query);
			//Print the Result
			PrintWriter pw = resp.getWriter();
			pw.print("                                                       ");
			pw.print("                                                       ");
			pw.print("<h1 style=color:purple> DISPLAY BOOK DETAILS</h1>");
			pw.print("                                                       ");
			pw.print("===========================================================");
			pw.print("                                                       ");
			pw.print("<table border='2'>");
			
			pw.print("<tr>");
			pw.print("<th>ISBN </th>");
			pw.print("<th>BOOK NAME </th>");
			pw.print("<th>AUTHOR </th>");
			pw.print("<th>PRICE </th>");
			pw.print("<th>STOCK </th>");
			pw.print("</tr>");
			
			while(rs.next()) {
				pw.print("<tr>");
				pw.print("<td>"+rs.getInt(1)+"</td>");
				pw.print("<td>"+rs.getString(2)+"</td>");
				pw.print("<td>"+rs.getString(3)+"</td>");
				pw.print("<td>"+rs.getDouble(4)+"</td>");
				pw.print("<td>"+rs.getInt(5)+"</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
