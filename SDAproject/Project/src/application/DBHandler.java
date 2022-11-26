package application;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class DBHandler implements PersistenceHandler {
 private static DBHandler DbObject;
 private DBHandler() {};
 public static  DBHandler getInstance() {
	   if( DbObject==null) {
	   DbObject = new DBHandler();
	   }
	   return DbObject;
 }
	Connection con=null;
	@Override

	public void connectDB() {
			try {
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/foodorderdb?user=root");
			if(con == null) {
			System.out.println("DB connection failed");}
			else
			{
			
			System.out.println("DB connection successful");
			}
			}
			catch(Exception e) {
			System.out.println("exception: "+e);
			}
			}
			
	
	
	@Override
	public boolean matchRecord(String s) {
		Statement stmt;
		try {
		stmt =  (Statement) con.createStatement();
		String sql= s;
		
		
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
		  if(rs.first()==false)
		  {
			  return true;
		  }
	
		}

		catch (SQLException e) {

		System.out.println("exception: "+e);
		}
		return false;
		}
	@Override
	public void saveRecord(String s) {
		try {
		String sql =s;
				PreparedStatement statement;
				
					statement = (PreparedStatement) con.prepareStatement(sql);
					
					
					int rowsInserted = statement.executeUpdate(sql);
					if (rowsInserted > 0) {
					System.out.println("A new user was inserted successfully!");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
	}
	@Override
	public void updateRecord(String s) {
	
		try {
		String sql = s;
				PreparedStatement statement;
				
					statement = (PreparedStatement) con.prepareStatement(sql);
					
					int rowsUpdated = statement.executeUpdate(sql);
					if (rowsUpdated > 0) {
					System.out.println("An existing user was updated successfully!");
					}
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}		
	@Override
	public void deleteRecord(String s) {
		try {
		String sql =s;
		PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
	
		int rowsDeleted = statement.executeUpdate(sql);
		if (rowsDeleted > 0) {
		System.out.println("A user was deleted successfully!");
		}

	
}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public ResultSet readRecord(String s) {
		Statement stmt;
		ResultSet rs = null;
		try {
		stmt =  (Statement) con.createStatement();
		String sql= s;
		
		
		 rs = (ResultSet) stmt.executeQuery(sql);
	
		}

		catch (SQLException e) {

		System.out.println("exception: "+e);
		}
		return rs;
		
		
	}
	
}

