package ve450.ruix;

//import org.postgresql.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.sql.*;
import java.text.SimpleDateFormat;
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

	public String CalculateAge(String equipment_id) {
		String age = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();

			String sql = "select * from UsageInformation where equipment_id = '" + equipment_id + "'";
			ResultSet rs = st.executeQuery(sql);
			SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				Date startTime = new Date(rs.getTimestamp("start_time").getTime());
				String strStartTime = formattime.format(startTime);
				Date endTime = new Date(rs.getTimestamp("end_time").getTime());
				String strEndTime = formattime.format(startTime);
				String sqlTime = "select TIMESTAMPDIFF(HOUR,'" + strStartTime + "','" + strEndTime + "')";

			}

			rs.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in CalculateAge");
		}
		return age;
	}

	// Used by Maintenance Engineer, see a page of detailed problem
	public String ViewDetailedProblem(String problem_id) {
		String json = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();

			String sql = "select * from Problem where status <> 'rubbish' and problem_id = '" + problem_id + "'";
			ResultSet rs = st.executeQuery(sql);
			json += "[";
			while (rs.next()) {
				json += "\n{ \"problem_id\": \"" + rs.getString("problem_id") + "\", \"explaination\": \""
						+ rs.getString("explaination") + "\", \"equipment_id\": \"" + rs.getString("equipment_id")
						+ "\", \"status\": \"" + rs.getString("status") + "\", \"personnel\": \""
						+ rs.getString("personnel") + "\", \"picture_name\": \"" + rs.getString("picture_name")
						+ "\" },";
			}
			json = json.substring(0, json.length() - 1);
			json += "\n]";
			rs.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in ViewDetailedProblem");
		}
		return json;
	}

	// Used by Maintenance Engineer, see a list of all unresolved problems
	public String ViewProblemList() {
		String json = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();

			String sql = "select * from Problem where status <> 'rubbish'";
			ResultSet rs = st.executeQuery(sql);
			json += "[";
			while (rs.next()) {
				json += "\n{ \"problem_id\": \"" + rs.getString("problem_id") + "\", \"explaination\": \""
						+ rs.getString("explaination").substring(0, 30) + "\" },";
				System.out.println("next IN2"); 
			}
			json = json.substring(0, json.length() - 1);
			json += "\n]";
			rs.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in ViewProblemList");
		}
		return json;
	}

	public void Report(String equipment_id, String explaination, String personnel, String picture_name) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();

			String sql;
			sql = "INSERT INTO Problem (equipment_id, explaination, status, personnel, picture_name) VALUES ('"
					+ equipment_id + "','" + explaination + "','waiting to be repaired', '" + personnel + "','"
					+ picture_name + "')";
			st.executeUpdate(sql);
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("error in Report");
		}
	}

	public void MarkRepair(String problem_id, String personnel) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();

			String sql;
			sql = "UPDATE Problem SET status = 'Staff: " + personnel + " is repairing' WHERE problem_id='" + problem_id
					+ "'";
			st.executeUpdate(sql);
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("MarkRepair");
		}
	}

	public void MarkResolve(String problem_id) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();

			String sql;
			sql = "UPDATE Problem SET status = 'rubbish' WHERE problem_id='" + problem_id + "'";
			st.executeUpdate(sql);
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("MarkResolve");
		}
	}

	public void StartUse(String equipment_id) {

		try {
			// connect to db
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();
			Statement stt = con.createStatement();

			String sqlSetStatus;
			String sqlSelect;
			String sqlRecordStart;

			// select itself and all children, set status to '3' (running)
			sqlSetStatus = "UPDATE Equipment SET status = '3' WHERE (equipment_id='" + equipment_id + "' OR dad_id = '"
					+ equipment_id + "') AND status <> '3'";

			// select itself and all children
			sqlSelect = "Select * FROM Equipment WHERE (equipment_id='" + equipment_id + "' or dad_id = '"
					+ equipment_id + "') AND status <> '3'";

			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

			ResultSet rs = st.executeQuery(sqlSelect);
			stt.executeUpdate(sqlSetStatus);
			while (rs.next()) {
				// insert usage information

				String eid = rs.getString("equipment_id");
				String rrr = "haha you fail";
				sqlRecordStart = "INSERT INTO UsageInformation (equipment_id, start_time) VALUES (" + eid + ", '"
						+ timeStamp + "')";
				Statement stloop = con.createStatement();
				stloop.executeUpdate(sqlRecordStart, Statement.RETURN_GENERATED_KEYS);
				ResultSet generatedKeys = stloop.getGeneratedKeys();
				if (generatedKeys.next()) {
					rrr = generatedKeys.getString(1);
				}
				stloop.close();
				String sqlInsertMap = "";
				// check if usage map already exists
				String sqlCheck = "SELECT COUNT(*) as trash from CurrentUsageMap where equipment_id=" + eid;
				Statement stloopp = con.createStatement();
				ResultSet rsCheck = stloopp.executeQuery(sqlCheck);
				// System.out.println("sql_1 DONE");
				if (rsCheck.next()) {
					if (rsCheck.getInt("trash") > 0) {
						System.out.println("current usage map already exists! (this is not an error)");
						sqlInsertMap = "UPDATE CurrentUsageMap SET usage_id = '" + rrr + "' WHERE equipment_id = '"
								+ equipment_id + "'";
					} else {
						System.out.println("current usage map does not exist! (this is not an error)");
						sqlInsertMap = "INSERT INTO CurrentUsageMap VALUES (" + eid + "," + rrr + ")";
					}
				}
				stloopp.close();
				stt.executeUpdate(sqlInsertMap);
				generatedKeys.close();
				rsCheck.close();
			}
			rs.close();
			st.close();
			stt.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("error in StartUse");
		}
	}

	public void EndUse(String equipment_id) {
		try {
			// connect to db
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();
			Statement stt = con.createStatement();
			String sqlSelect;
			String sqlRecordStart;

			// select itself and all children
			sqlSelect = "Select * FROM Equipment WHERE (equipment_id='" + equipment_id + "' or dad_id = '"
					+ equipment_id + "') AND status = '3'";
			String sqlSetStatus = "UPDATE Equipment SET status = '0' WHERE (equipment_id='" + equipment_id
					+ "' OR dad_id = '" + equipment_id + "') AND status <> '0'";
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

			ResultSet rs = st.executeQuery(sqlSelect);
			stt.executeUpdate(sqlSetStatus);
			while (rs.next()) {
				String eid = rs.getString("equipment_id");
				String uid = "haha wrong uid";
				Statement stloop = con.createStatement();
				String sqlFindUid = "SELECT * FROM CurrentUsageMap WHERE equipment_id ='" + eid + "'";
				ResultSet rsUid = stloop.executeQuery(sqlFindUid);
				while (rsUid.next()) {
					uid = rsUid.getString("usage_id");
				}
				sqlRecordStart = "UPDATE UsageInformation SET end_time = '" + timeStamp + "' WHERE usage_id = '" + uid
						+ "'";
				stloop.executeUpdate(sqlRecordStart);
				stloop.close();
			}
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("error in EndUse");
		}
	}

	public void InsertRecord(String equipment_id, String recorded_date, String personnel_id, String explaination) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();

			String sql;
			sql = "INSERT INTO MaintenanceRecord VALUES ('" + equipment_id + "','" + recorded_date + "','"
					+ personnel_id + "','" + explaination + ")";
			st.executeUpdate(sql);
			// System.out.println("Someone Upload");
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print("error in InsertRecord");
		}
	}

	// Used by machine operator
	public String ViewStatus(String equipment_id) {
		ArrayList<String> stock = new ArrayList<String>();
		String json = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();
			String sqlSelectStatus = "select * from Status where equipment_id in (select equipment_id from Equipment where dad_id = '"
					+ equipment_id + "' or equipment_id = '" + equipment_id
					+ "') order by equipment_id";
			ResultSet rs = st.executeQuery(sqlSelectStatus);
			json += "[{ \"Status\":[";
			Boolean isEmpty = true;
			while (rs.next()) {
				// add status
				String rubbish = "\n{ \"equipment_id\": \"" + rs.getString("equipment_id") + "\", \"recorded_time\": \""
						+ rs.getString("recorded_time") + "\", \"temperature\": \"" + rs.getString("temperature")
						+ "\", \"speed\": \"" + rs.getString("speed") + "\" },";
				stock.add(rubbish);
			}
			for (int i = 0; i < 10 && i < stock.size(); i++) {
				json += stock.get(stock.size() - i - 1);

				isEmpty = false;
			}
			if (!isEmpty) {
				json = json.substring(0, json.length() - 1);
			}
			json += "\n],\n\"MaintenanceRecord\":[";
			rs.close();
			System.out.println("mark1");
			String sqlSelectMaintenanceRecord = "select * from MaintenanceRecord where equipment_id in (select equipment_id from Equipment where dad_id = '"
					+ equipment_id + "' or equipment_id = '" + equipment_id
					+ "') order by equipment_id";
			ResultSet rss = st.executeQuery(sqlSelectMaintenanceRecord);
			isEmpty = true;
			System.out.println("mark2");
			while (rss.next()) {
				// add status
				json += "\n{ \"equipment_id\": \"" + rss.getString("equipment_id") + "\", \"recorded_date\": \""
						+ rss.getString("recorded_date") + "\", \"personnel_id\": \"" + rss.getString("personnel_id")
						+ "\", \"explaination\": \"" + rss.getString("explaination") + "\" },";
				isEmpty = false;
			}
			System.out.println("mark3");
			if (!isEmpty) {
				json = json.substring(0, json.length() - 1);
			}

			json += "\n] }]";
			rss.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in ViewStatus");
		}
		return json;
	}

	// Used by maintenance engineer. Uninstall a piece of equipment.
	public void ChangeDown(String equipment_id, String dad_id) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			// String url =
			// "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
			Statement st = con.createStatement();
			// if it does not exist, fail?
			String sql;
			sql = "UPDATE Equipment SET status = '1', dad_id = '0' WHERE equipment_id='" + equipment_id + "'";
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
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();
			// if it does not exist, fail?
			String sql;
			sql = "UPDATE Equipment SET status = '2', dad_id = '" + dad_id + "' WHERE equipment_id='" + equipment_id
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
		String json = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();
			//System.out.println("conn OK");

			String sql = "select * from Equipment where name = '" + equipment_name + "' and status = '0'";
			ResultSet rs = st.executeQuery(sql);
			json += "[";
			while (rs.next()) {
				String rubbish = "ID: " + rs.getString("equipment_id") + " Manufacturer: "
						+ rs.getString("manufacturer") + " Date of Birth: " + rs.getString("date_of_birth")
						+ " Last Maintenance Date: " + rs.getString("last_maintenance_date");
				//System.out.println(rubbish);
				stock.add(rubbish);
				//System.out.println("add OK");
				json += "\n{ \"equipment_id\": \"" + rs.getString("equipment_id") + "\", \"manufacturer\": \""
						+ rs.getString("manufacturer") + "\", \"date_of_birth\": \"" + rs.getString("date_of_birth")
						+ "\", \"last_maintenance_date\": \"" + rs.getString("last_maintenance_date")
						+ "\", \"size\": \"" + rs.getString("size") + "\" },";
			}
			// System.out.println("Loop OK");
			json = json.substring(0, json.length() - 1);
			json += "\n]";
			rs.close();
			st.close();
			con.close();
		} catch (Exception ee) {
			System.out.print("error in read");
		}

		return json;
	}

	// Used by warehouse manager. Take things out of warehouse.
	public boolean TakeOutFromWarehouse(String equipment_id) {
		boolean success = true;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();
			// if it does not exist, fail?
			String sql;
			sql = "UPDATE Equipment SET status = '1', dad_id = '0' WHERE equipment_id='" + equipment_id + "'";
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
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();
			// if it does not exist, fail?
			String sql;
			sql = "UPDATE Equipment SET status = '0', dad_id = '0' WHERE equipment_id='" + equipment_id + "'";
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
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
			Statement st = con.createStatement();

			String sql;
			sql = "INSERT INTO Equipment (name, manufacturer, date_of_birth, last_maintenance_date, status, size, dad_id) VALUES ('"
					+ product_name + "','" + manufacturer + "','" + time + "','" + time + "','" + "0" + "','" + size
					+ "'," + "0)";
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

	public String Read(String id) {
		String[] byebye = new String[8];
		String json = "";
		System.out.println("enter read ready");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://59547c58081cb.sh.cdb.myqcloud.com:3857/VE450";
			Connection con = DriverManager.getConnection(url, "cdb_outerroot", "seimens450");
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
				byebye[6] = rs.getString("size");
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
		json += "[\n{ \"equipment_id\": \"" + byebye[0] + "\", \"name\": \"" + byebye[1] + "\", \"manufacturer\": \""
				+ byebye[2] + "\", \"date_of_birth\": \"" + byebye[3] + "\", \"last_maintenance_date\": \"" + byebye[4]
				+ "\", \"status\": \"" + byebye[5] + "\", \"size\": \"" + byebye[6] + "\", \"parent_id\": \""
				+ byebye[7] + "\" }\n]";
		return json;
	}
}