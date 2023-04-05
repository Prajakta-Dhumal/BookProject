package bookOperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/purchasebook")
public class PurchaseBook extends HttpServlet
{
	//declare the resources
	Connection con;
	  @Override
	public void init() throws ServletException 
	{ 
		  //Load the driver and establish the Connnection
		  try 
		  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1ejm?user=root&password=sql@123");
		}
		  catch (ClassNotFoundException e) 
		  {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	  
	  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//fetch the data from html page
		String bname=  req.getParameter("bname");
		String stock=  req.getParameter("qty");
		
		//parse the value
		int stock1=Integer.parseInt(stock);
		//declare the resources
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		
		
		//write the query
		String query="select price,stock from book_data where book_name=?";
		String query1="update book_data set stock=? where book_name=?";
		
		//create the statement platform
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,bname);
			//execute the query
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
			{
				double price=rs.getDouble(1);
				int dbstock=rs.getInt(2);
				if(stock1>=dbstock) 
				{
					pw.print("<h1 style='color:red'>BOOK OUT OF STOCK</h1>");
					
				}
				else 
				{
					//create the statement platform
					pstmt=con.prepareStatement(query1);
					pstmt.setInt(1, dbstock-stock1);
					pstmt.setString(2,bname);
			       int count=pstmt.executeUpdate();
			       double bill=price*stock1;
			       pw.print("<h1 style='color:green'> FINAL BILL OF BOOK  "+bill+"</h1>");
					
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
