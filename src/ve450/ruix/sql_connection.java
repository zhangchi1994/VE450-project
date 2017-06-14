package ve450.ruix;

import org.postgresql.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.*;

public class sql_connection {
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	Connection con;

	public sql_connection() {
		super();
	}

	// TODO: this currently does not work because con has been closed!
	public void Insert(String id, String product_name, String manufacturer, String time, String size) {
		try {
			Statement st = con.createStatement();
			
			String sql;
			//if(id.equals("")){
				sql = "INSERT INTO Equipment"
						+ "(name, manufacturer, date_of_birth, last_maintenance_date, status, ssize, dad_id) VALUES("
						+ product_name + "," + manufacturer + "," + time + "," + "'0'" + "," + size + "," + "0";
			//}
			st.executeQuery(sql);
			st.close();
		} catch (Exception ee) {
			System.out.print("error in updateInfo");
		}
	}

	public boolean logIN(String username, String password) {
		boolean find_or_not = false;
		try {
			Class.forName("org.postgresql.Driver").newInstance();

			// String url = "jdbc:postgresql://localhost:5432/example_db" ;
			String url = "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db";
			con = DriverManager.getConnection(url, "postgres", "123456");

			Statement st = con.createStatement();
			String sql = "select * from users where user_id=" + username;
			// String sql = "select * from child where equipment_id="+Item_id;
			ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) {
				System.out.println("hi_1");
				find_or_not = false;

				/*
				 * if (rs.next()) { byebye[0] = rs.getString("equipment_id");
				 * byebye[1] = rs.getString("name"); byebye[2] =
				 * rs.getString("date_of_birth"); byebye[3] =
				 * rs.getString("last_maintenance_date"); if(rs.getInt("dad_id")
				 * == 0){ byebye[4] = "NULL"; byebye[5] = "NULL"; } else {
				 * byebye[4] = rs.getString("dad_id"); byebye[5] =
				 * "http://localhost:8080/VE450/Info_"+byebye[4]+".jsp"; }
				 * System.out.println(byebye[4]);
				 */
			} else {
				if (rs.getString("password").equals(password))
					find_or_not = true;
				else
					find_or_not = false;
			}
			rs.close();
			st.close();
			// con.close(); // TODO: remove this line after log out is
			// implemented
			return (find_or_not);

		} catch (Exception ee) {
			System.out.print("error in LogIN");
		}
		return find_or_not;
	}

	public void logOUT() {
		try {
			con.close();
		} catch (Exception ee) {
			System.out.print("error in LogOUT");
		}
	}
}