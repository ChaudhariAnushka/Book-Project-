package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addbook")
public class AddBook extends HttpServlet
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	//Fetch the values from HTML Page & Parse the value
      
      String bname=req.getParameter("bname");
      String author=req.getParameter("author");
      String price=req.getParameter("price");
      Double bprice = Double.parseDouble(price);
      String stock=req.getParameter("stock");
      int bstock=Integer.parseInt(stock);
      //Declare the Resources(Create Statement Platform
      PreparedStatement pstmt= null;//Because Dynamic Values
      //Write Query
      String query="insert into book_store(book_name,author,price,stock)values(?,?,?,?)";
    try {
		pstmt=con.prepareStatement(query);
		//Set the values of Placeholder
		
		pstmt.setString(1, bname);
		pstmt.setString(2, author);
		pstmt.setDouble(3, bprice);
		pstmt.setInt(4, bstock);
		//Execute the query
		int count=pstmt.executeUpdate();
		//Print the Result
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+" RECORD INSERTED SUCCESSFULLY!</h1>");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
    }
	

}
