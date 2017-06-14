package ve450.ruix;
import org.postgresql.*;
import java.sql. * ;

public class sql_connection{
	   /* (non-Java-doc)
		 * @see javax.servlet.http.HttpServlet#HttpServlet()
		 */
		public sql_connection() {
			super();
		}   	
		 public String[] gogogo(String Item_id)
	     {
			 String[] byebye = new String[6];
	         try 
	          {
	            Class.forName("org.postgresql.Driver").newInstance();
	            
	            //String url = "jdbc:postgresql://localhost:5432/postgres" ;
	            String url = "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
	            Connection con = DriverManager.getConnection(url, "postgres" , "123456");
	            
	            //Connection con = DriverManager.getConnection(url, "postgres" , "password");
	            Statement st = con.createStatement();
	            String sql = "select * from child where equipment_id="+Item_id;
	            ResultSet rs = st.executeQuery(sql);

	            if (rs.next()) {
	            	byebye[0] = rs.getString("equipment_id");
	            	byebye[1] = rs.getString("name");
	            	byebye[2] = rs.getString("date_of_birth");
	            	byebye[3] = rs.getString("last_maintenance_date");
	            	if(rs.getInt("dad_id") == 0){
	            		byebye[4] = "NULL";
	            	    byebye[5] = "NULL";
	            	}
	            	else {
	            		byebye[4] = rs.getString("dad_id");
	            		byebye[5] = "http://localhost:8080/VE450/Info_"+byebye[4]+".jsp";
	            	}
	            	System.out.println(byebye[4]);
	            }
	            rs.close();
	            st.close();
	            con.close();
	            return (byebye);

	        } 
	         catch (Exception ee)
	         {
	            System.out.print("errrrrro");
	        }
			return null;
	    } 
}