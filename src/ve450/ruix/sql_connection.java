package ve450.ruix;
import org.postgresql.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql. * ;

public class sql_connection{
	   /* (non-Java-doc)
		 * @see javax.servlet.http.HttpServlet#HttpServlet()
		 */
		public sql_connection() {
			super();
		}
		
		 public boolean logIN(String username, String password)
	     {		
			 boolean find_or_not = false;
	         try 
	          {
	            Class.forName("org.postgresql.Driver").newInstance();
	            String url = "jdbc:postgresql://localhost:5432/example_db" ;
	            //String url = "jdbc:postgresql://ve450postgresql.nat123.cc:44966/example_db" ;
	            Connection con = DriverManager.getConnection(url, "postgres" , "123456");
	            Statement st = con.createStatement();
	            String sql = "select * from users where user_id="+username;
	            ResultSet rs = st.executeQuery(sql);
	            if(!rs.next()){
	            	System.out.println("hi_1");
	            	find_or_not = false;
	            }
	            else {
	            	System.out.println("yes");
	            	
	            	/*if(rs.getString("password") == password)  {
	            		System.out.println("OK");
	            		find_or_not = true;
	            	else find_or_not = false;*/
	            }
	            rs.close();
	            st.close();
	            con.close();
	            return (find_or_not);

	        } 
	         catch (Exception ee)
	         {
	            System.out.print("errrrrro");
	        }
			return find_or_not;
	    } 
}