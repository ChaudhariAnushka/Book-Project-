package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/updatebook")
public class UpdateBook extends HttpServlet
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
		//Fetch the values from HTML Page
		 String isbn1=req.getParameter("isbn");
		 int isbn = Integer.parseInt(isbn1);
		String bname= req.getParameter("bname");
		String author=req.getParameter("author");
		String bprice=req.getParameter("price");
		double price=Double.parseDouble(bprice);
		String bstock=req.getParameter("stock");
		int stock=Integer.parseInt(bstock);
		
		//Create Statement Platform
		PreparedStatement pstmt=null;
		//Write Query
		String query="update book_store set book_name=?,author=?,price=?,stock=? where isbn=?";
		try {
			pstmt=con.prepareStatement(query);
			//set the values
			pstmt.setString(1,bname);
			pstmt.setString(2, author);
			pstmt.setDouble(3, price);
			pstmt.setInt(4, stock);
			pstmt.setInt(5, isbn);
			//execute the query
			int count=pstmt.executeUpdate();
			//Print the result
			PrintWriter pw = resp.getWriter();
			pw.print("<h1>"+count+" RECORD UPDATED SUCCESSFULLY!</h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
