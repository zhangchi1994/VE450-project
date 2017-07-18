package ve450.ruix;

//import org.postgresql.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.sql.*;

public class sql_id_connection {
	Connection con;
	public class loginReturn{
		boolean find_or_not;
		String permission;
	};

	public sql_id_connection() {
		super();
	}

	public boolean SignUp(String username, String password, String permission){
		boolean can_create = true;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();
			//System.out.println("connection DONE");
			String sql = "select count(*) as trash from Users where user_id='" + username + "'";
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				if (rs.getInt("trash") > 0) {
					System.out.println("Username already exists!");
					can_create = false;
				} else {		
					sql = "INSERT INTO Users VALUES ('" + username + "','" + password + "','" + permission + "')";
					//System.out.println("sql yes");
					st.executeUpdate(sql);
					//System.out.println("sql DONE");
				}
			}
					
			rs.close();
			st.close();
			con.close(); 

		} catch (Exception ee) {
			System.out.print("error in SignUp");
		}
		return can_create;
	}
	
	public loginReturn logIN(String username, String password) {
		loginReturn lr = new loginReturn();
		boolean find_or_not = false;
		String permission = "a";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("yyyyyyyyyyyyyyy1");
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			System.out.println("yyyyyyyyyyyyyyy2");
			Statement st = con.createStatement();
			System.out.println("yyyyyyyyyyyyyyy3");
			//System.out.println("connection DONE");
			String sql = "select * from Users where user_id = '" + username + "'";
			//System.out.println("sql START");
			ResultSet rs = st.executeQuery(sql);
			System.out.println("yyyyyyyyyyyyyyy4");
			//System.out.println("sql DONE");
			if (!rs.next()) {
				System.out.println("no body");
				find_or_not = false;

			} else {
				System.out.println("yyyyyyyyyyyyyyy5");
				if (rs.getString("password").equals(password)){
					find_or_not = true;
					//System.out.println("password right");
				}
				else
					find_or_not = false;
			}
			if(find_or_not){
				lr.permission = rs.getString("permission_level");
				//System.out.println("get permission");
			}
			rs.close();
			st.close();
			con.close(); // TODO: remove this line after log out is
			// implemented
			lr.find_or_not = find_or_not;

		} catch (Exception ee) {
			System.out.print("error in LogIN");
		}
		return lr;
	}
	
	public void logOUT() {
		try {
			con.close();
		} catch (Exception ee) {
			System.out.print("error in LogOUT");
		}
	}
}
