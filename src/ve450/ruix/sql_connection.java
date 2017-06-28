package ve450.ruix;

import org.postgresql.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.sql.*;
import java.util.ArrayList;

/*
 * status 0: in warehouse
 * status 1: out of warehouse
 * status 2: installed
 * status 3: running
 */

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

	public void InsertRecord(String equipment_id, String recorded_date, String personnel_id, String explaination) {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			// String url =
			// "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();

			String sql;
			sql = "INSERT INTO MaintenanceRecord VALUES ('" + equipment_id + "','" + recorded_date + "','"
					+ personnel_id + "','" + explaination + ")";
			// sql = "INSERT INTO Equipment (name, manufacturer, date_of_birth,
			// last_maintenance_date, status, ssize, dad_id)"
			// + " VALUES ('pn', 'manu1', '1989-08-18', '1989-08-18','0',
			// '17kg',0)";
			// }
			st.executeUpdate(sql);
			// System.out.println("Someone Upload");
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("error in InsertRecord");
		}
	}

	// Used by machine operator
	public ArrayList<String> ViewStatus(String equipment_id, String start_time, String end_time) {
		ArrayList<String> stock = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();
			String sqlSelectStatus = "select * from Status where recorded_time BETWEEN '" + start_time + "' and '"
					+ end_time + "' and equipment_id in (select equipment_id from Equipment where dad_id = "
					+ equipment_id + ") group by equipment_id order by recorded_time";
			ResultSet rs = st.executeQuery(sqlSelectStatus);
			while (rs.next()) {
				String rubbish = "ID: " + rs.getString("equipment_id") + " Manufacturer: "
						+ rs.getString("manufacturer") + " Date of Birth: " + rs.getString("date_of_birth")
						+ " Last Maintenance Date: " + rs.getString("last_maintenance_date");
				stock.add(rubbish);
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in ViewStatus");
		}
		return stock;
	}

	// Used by maintenance engineer. Uninstall a piece of equipment.
	public void ChangeDown(String equipment_id, String dad_id) {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			// String url =
			// "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();
			// if it does not exist, fail?
			String sql;
			sql = "UPDATE Equipment SET status = '1', dad_id = '0', WHERE equipment_id='" + equipment_id + "'";
			System.out.println("Something is changed down.");
			st.executeUpdate(sql);
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in change down");
		}
	}

	// Used by maintenance engineer. Install a piece of equipment.
	public void ChangeUp(String equipment_id, String dad_id) {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			// String url =
			// "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();
			// if it does not exist, fail?
			String sql;
			sql = "UPDATE Equipment SET status = '2', dad_id = '" + dad_id + "', WHERE equipment_id='" + equipment_id
					+ "'";
			System.out.println("Something is changed up.");
			st.executeUpdate(sql);
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in changeUp");
		}
	}

	// Used by warehouse manager. View a list of equipment.
	public String ViewStock(String equipment_name) {
		ArrayList<String> stock = new ArrayList<String>();
		String jsonStock = "";
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();

			String sql = "select * from Equipment where equipment_name = '" + equipment_name + "' and status = '0'";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("read sql ready");
			while (rs.next()) {
				String rubbish = "ID: " + rs.getString("equipment_id") + " Manufacturer: "
						+ rs.getString("manufacturer") + " Date of Birth: " + rs.getString("date_of_birth")
						+ " Last Maintenance Date: " + rs.getString("last_maintenance_date");
				stock.add(rubbish);
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in read");
		}
		return jsonStock;
	}

	// Used by warehouse manager. Take things out of warehouse.
	public boolean TakeOutFromWarehouse(String equipment_id) {
		boolean success = true;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			// String url =
			// "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();
			// if it does not exist, fail?
			String sql;
			sql = "UPDATE Equipment SET status = '1', dad_id = '0', WHERE equipment_id='" + equipment_id + "'";
			System.out.println("Something is taken out of the warehouse");
			st.executeUpdate(sql);
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in insert");
		}
		return success;
	}

	// Used by warehouse manager. Put used things back.
	public boolean PutUsedThingBackToWarehouse(String equipment_id) {
		boolean success = true;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://localhost:5432/example_db";
			// String url =
			// "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
			Connection con = DriverManager.getConnection(url, "postgres", "123456");
			Statement st = con.createStatement();
			// if it does not exist, fail?
			String sql;
			sql = "UPDATE Equipment SET status = '0', dad_id = '0', WHERE equipment_id='" + equipment_id + "'";
			System.out.println("Something is put back to the warehouse");
			st.executeUpdate(sql);
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("error in PutUsedThingBackToWarehouse");
		}
		return success;
	}

	// Used by warehouse manager. Insert newly purchased equipment.
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
				// System.out.println("The id is " + returnId);
			}
			// System.out.println("Someone Upload");
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
				// System.out.println("dad is " + byebye[7]);

			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in read");
		}
		return byebye;
	}
}