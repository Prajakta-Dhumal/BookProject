package bookOperation;

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
@WebServlet("/updatebook")
public class UpdateBook extends HttpServlet
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
				String id=req.getParameter("id");
			    String bname=req.getParameter("bname");
				String author=req.getParameter("author");
				String price=req.getParameter("price");
				String stock= req.getParameter("stock");
				
				//parse the value
				int id1=Integer.parseInt(id);
				double price1=Double.parseDouble(price);
				int stock1=Integer.parseInt(stock);
				
				//declare the resources
				PreparedStatement pstmt=null;
				PrintWriter pw=resp.getWriter();
				
				//write the sql query
				String query="update book_data set book_name=?,author=?,price=?,stock=? where book_id=?";
				
				//create the statement platform
			   try {
				pstmt=con.prepareStatement(query);
				//set the value at placeholder
				pstmt.setString(1,bname);
				pstmt.setString(2,author);
				pstmt.setDouble(3,price1);
				pstmt.setInt(4, stock1);
				pstmt.setInt(5,id1);
				
				//execute the query
			   int count=pstmt.executeUpdate();
			   pw.print("<h1 style='color:green'>"+count+" RECORD UPADATED SUCESFULLY</h1>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

}
