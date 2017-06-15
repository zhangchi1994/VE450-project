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
	public String Insert(String product_name, String manufacturer, String time, String size) {
		String returnId = "Haha you fail";
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			// String url =
			// "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();

			String sql;
			sql = "INSERT INTO Equipment (name, manufacturer, date_of_birth, last_maintenance_date, status, ssize, dad_id) VALUES ('"
					+ product_name + "','" + manufacturer + "','" + time + "','" + time + "','" + "0" + "','" + size
					+ "'," + "0)";
			// sql = "INSERT INTO Equipment (name, manufacturer, date_of_birth,
			// last_maintenance_date, status, ssize, dad_id)"
			// + " VALUES ('pn', 'manu1', '1989-08-18', '1989-08-18','0',
			// '17kg',0)";
			// }
			st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = st.getGeneratedKeys();
			if (generatedKeys.next()) {
				returnId = generatedKeys.getString(1);
				System.out.println("The id is " + returnId);
			}
			System.out.println("Someone Upload");
			generatedKeys.close();
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("error in insert");
		}
		return returnId;
	}

	public String[] Read(String id) {
		String[] byebye = new String[8];
		System.out.println("enter read ready");
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();

			String sql = "select * from Equipment where equipment_id = " + id;
			ResultSet rs = st.executeQuery(sql);
			System.out.println("read sql ready");
			if (rs.next()) {
				byebye[0] = rs.getString("equipment_id");
				byebye[1] = rs.getString("name");
				byebye[2] = rs.getString("manufacturer");
				byebye[3] = rs.getString("date_of_birth");
				byebye[4] = rs.getString("last_maintenance_date");
				byebye[5] = rs.getString("status");
				byebye[6] = rs.getString("ssize");
				if (rs.getInt("dad_id") == 0) {
					byebye[7] = "NULL";
				} else {
					byebye[7] = rs.getString("dad_id");
				}
				//System.out.println("dad is " + byebye[7]);

			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in read");
		}
		return byebye;
	}

	public boolean logIN(String username, String password) {
		boolean find_or_not = false;
		try {
			Class.forName("org.postgresql.Driver").newInstance();

			String url = "jdbc:postgresql://localhost:5432/example_db";
			// String url =
			// "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			// Connection con = DriverManager.getConnection(url, "postgres" ,
			// "password");
			Statement st = con.createStatement();
			String sql = "select * from users where user_id=" + username;

			ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) {
				System.out.println("hi_1");
				find_or_not = false;

			} else {
				if (rs.getString("password").equals(password))
					find_or_not = true;
				else
					find_or_not = false;
			}
			rs.close();
			st.close();
			con.close(); // TODO: remove this line after log out is
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