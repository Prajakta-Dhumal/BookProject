package bookOperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displaydetails")
public class DisplayDetails extends HttpServlet
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	  {
		
		  //decleare the resources 
		  Statement stmt=null;
		  PrintWriter pw=resp.getWriter();
		  String query="select * from book_data";
		  ResultSet rs=null;
		  
		  //create the statement platform
		 try {
			stmt=con.createStatement();
			pw.print("<table border=2>");
			pw.print("<tr>");
			pw.print("<th>BOOK ID</th>");
			pw.print("<th>BOOK NAME</th>");
			pw.print("<th>AUTHOR NAME</th>");
			pw.print("<th>PRICE</th>");
			pw.print("<th>STOCK</th>");
			pw.print("</tr>");
			rs=stmt.executeQuery(query);
			while(rs.next()) 
			{
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
