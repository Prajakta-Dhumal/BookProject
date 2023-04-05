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
@WebServlet("/deletebook")
public class DeleteBook extends HttpServlet
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
		 String id=req.getParameter("id");
		 //parse the value
		 int id1=Integer.parseInt(id);
		 
		 //declare the resource
		 PreparedStatement pstmt=null;
		 PrintWriter pw=resp.getWriter();
		 
		 //write the query
		 String query="delete from book_data where book_id=?";
		 //create the platform
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setInt(1, id1);
			//execute update
			int count=pstmt.executeUpdate();
			pw.print("<h1 style='color:red'>"+count+"  RECORD DELETED SUCCESFULLY!!!</h1>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}

}
