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
@WebServlet("/searchbook")
public class SearchBook extends HttpServlet
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	 {
		  //fetch the value from html page
		String bname=  req.getParameter("bname");
		
		//declare the resources
		PreparedStatement pstmt=null;
		PrintWriter pw=resp.getWriter();
		ResultSet rs=null;
		
		//write the sql query
		String query="select book_name,author,price,stock from book_data where book_name=?";
		//create the statement
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1,bname);
			rs=pstmt.executeQuery();
			pw.print("<table border='2'>");
			
			pw.print("<tr style='color:red'>");
			pw.print("<th>BOOK NAME</th>");
			pw.print("<th>AUTHOR</th>");
		    pw.print("<th>BOOK PRICE</th>");
			pw.print("<th>STOCK</th>");
			pw.print("</tr>");
			if(rs.next()) 
			{
				pw.print("<tr style='color:green'>");
				pw.print("<td>"+rs.getString(1)+"</td>");
    			pw.print("<td>"+rs.getString(2)+"</td>");
				pw.print("<td>"+rs.getDouble(3)+"</td>");
				pw.print("<td>"+rs.getInt(4)+"</td>");
				pw.print("</tr>");
	     		pw.print("</table>");
				
				
				
			}
			else 
			{
				pw.print("<h1 style='color:red'>PRODUCT NOT FOUND!!!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
