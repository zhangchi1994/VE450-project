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
		 public String[] gogogo()
	     {
			 String[] byebye = new String[5];
	         try 
	          {
	            Class.forName("org.postgresql.Driver").newInstance();
	            String url = "jdbc:postgresql://localhost:5432/example_db" ;
	            Connection con = DriverManager.getConnection(url, "postgres" , "123456");
	            Statement st = con.createStatement();
	            String sql = "select * from equipment";
	            ResultSet rs = st.executeQuery(sql);
	            if (rs.next()) {
	            	byebye[0] = rs.getString("id");
	            	byebye[1] = rs.getString("name");
	            	byebye[2] = rs.getString("date_of_birth");
	            	byebye[3] = rs.getString("last_maintenance_date");
	            	byebye[4] = rs.getString("dad_id");
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