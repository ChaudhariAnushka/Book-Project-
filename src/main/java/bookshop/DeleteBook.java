package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deletebook")
public class DeleteBook extends HttpServlet
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Fetch the value from HTML and Parse it
		 String isbn1 =req.getParameter("isbn");
		 int isbn = Integer.parseInt(isbn1);
		 //Create Statemet Platform(Declare the resource)
		 PreparedStatement pstmt = null;
		 //Write Query
		 String query="delete from book_store where isbn=?";
		 try {
			
			pstmt=con.prepareStatement(query);
			//set the value
			pstmt.setInt(1, isbn);
			//execute the query
			int count=pstmt.executeUpdate();
			//Print the result
			PrintWriter pw = resp.getWriter();
			pw.print("<h1>"+count+" RECORD DELETED SUCCESSFULLY!</h1>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
