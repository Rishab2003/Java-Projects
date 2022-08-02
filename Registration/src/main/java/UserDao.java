import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	protected boolean checkdb(String uname,String pass) throws Exception {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration	","root","system2003");
		
			String sql="select * from account where uname=? and upwd=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1,uname);
			statement.setString(2, pass);
			
			ResultSet rSet=statement.executeQuery();
			
			if(rSet.next()) {
				return true;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	protected void addrecord(User u) {
		
		try {
		
		//Class.forName("com.mysql.jdbc.Driver");	
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration	","root","system2003");
	
		String sql="insert into account(uname,upwd,uemail,umno) values(?,?,?,?)";
		PreparedStatement statement=con.prepareStatement(sql);
		statement.setString(1,u.name_new);
		statement.setString(2, u.password_new);
		statement.setString(3, u.email_new);
		statement.setString(4, u.mobileno_new);
		
		statement.executeUpdate();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
