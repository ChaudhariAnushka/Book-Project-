package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/purchasebook")
public class PurchaseBook extends HttpServlet
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
		
		//Fetch the value from HTML Page and parse it
		String isbn1=req.getParameter("isbn");
		int isbn = Integer.parseInt(isbn1);
		String qty1=req.getParameter("qty");
		int qty= Integer.parseInt(qty1);
		
		//Create Statement Platform(Declare resources)
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		PrintWriter pw = resp.getWriter();
		
		//Write the Query
		String query = "select price,stock from book_store where isbn=?";
		String query1= "update book_store set stock=? where isbn=?";
		
		try {
			pstmt=con.prepareStatement(query);
			//set the value
			pstmt.setInt(1, isbn);
			//execute the query
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				int dbPrice = rs.getInt(1);
				int dbStock = rs.getInt(2);
				
				if(qty<=dbStock) {
					double total = qty*dbPrice;
					//pw.print("===========================================================");
					pw.print("<h1 style=color:navy>BOOK BILL : </h1>");
					pw.print("<h2 style=color:maroon>TOTAL AMOUNT IS : "+total+"</h2>");
					pstmt=con.prepareStatement(query1);
					//set the value
					pstmt.setInt(1, dbStock-qty);
					pstmt.setInt(2, isbn);
					//EXECUTE THE QUERY
					pstmt.executeUpdate();
					pw.print("                                                       ");
					pw.print("===========================================================");
					pw.print("<h3 style=color:green>STOCK UPDATED SUCCESSFULLY!</h3>");
				}
				else {
					pw.print("<h1 style=color:red>BOOK OUT OF STOCK</h1>");
				}
				
			}
			else {
				pw.print("<h1 style=color:red>BOOK NOT FOUND</h1>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
