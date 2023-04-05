package bookOperation;

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
			//fetch the value from html page
			String bname=  req.getParameter("bname");
			String author=req.getParameter("author");
			String price=  req.getParameter("price");
			String stock=  req.getParameter("stock");
			
			//parse the value
			int stock1=Integer.parseInt(stock);
			double price1=Double.parseDouble(price);
			
			//decleare the resources
			PreparedStatement pstmt=null;
			PrintWriter pw=resp.getWriter();
			
			//write the query
			String query="insert into book_data(book_name,author,price,stock) values(?,?,?,?)";
			//create the statement platform
			try {
				pstmt= con.prepareStatement(query);
				//set the values
				pstmt.setString(1,bname);
				pstmt.setString(2,author);
				pstmt.setDouble(3, price1);
				pstmt.setInt(4, stock1);
				
				//execute query
				int count=pstmt.executeUpdate();
				pw.print("<h1 style='color:green'>"+count+" RECORD INSERTED SUCESSFULLY!!!<h1>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	

}
