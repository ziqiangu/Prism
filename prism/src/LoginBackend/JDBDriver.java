package driver;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class JDBCDriver {
	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	
	public static void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://prism-db.cixpikrggo1y.us-west-1.rds.amazonaws.com?user=prism_admin&password=thisclasssucks&useSSL=false");
			//setting database
			conn.prepareStatement("use fuckit").execute();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(){
		try{
			if (rs!=null){
				rs.close();
				rs = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
			if(ps != null ){
				ps = null;
			}
		}catch(SQLException sqle){
			System.out.println("connection close error");
			sqle.printStackTrace();
		}
	}
	
	
	public static boolean validate(String usr, String pwd){
		connect();
		try {
			ps = conn.prepareStatement("SELECT Passwd FROM PrismUser WHERE Username=?");
			ps.setString(1, usr);
			rs = ps.executeQuery();
			if(rs.next()){
				if(pwd.equals(rs.getString("Passwd")) ){
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException in function \"validate\"");
			e.printStackTrace();
		}finally{
			close();
		}
		return false;		
	}
	public static int newUser(String usr, String pwd){//returns -1 if user not found
		connect();
		try {
			ps = conn.prepareStatement("insert into PrismUser (Username, Passwd, isActive) VALUES (?, ?, ?)");
			ps.setString(1, usr);
			ps.setString(2, pwd);
			ps.setBoolean(3, true);
			ps.executeUpdate();//add the new user
			
			ps = conn.prepareStatement("SELECT UserID FROM PrismUser WHERE Username =?");
			ps.setString(1, usr);
			rs = ps.executeQuery();
			
			if(rs.next()){
				println("enters if next");
				return rs.getInt("UserID");
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException in function \"validate\"");
			e.printStackTrace();
			
		}finally{
			close();
			
		}
		return -1;
	}
	
	
	public static void increment(String usr, String page){
		connect();
		try{
			ps = conn.prepareStatement("SELECT userID FROM User WHERE Username =?");
			ps.setString(1, usr);
			rs = ps.executeQuery();
			int userID = -1;
			if(rs.next()){
				userID = rs.getInt("userID");
			}
			rs.close();
			ps = conn.prepareStatement("SELECT pageID FROM Page WHERE name= ?");
			ps.setString(1, page);
			rs = ps.executeQuery();
			int pageID = -1;
			
			if(rs.next()){
				pageID = rs.getInt("pageID");
			}
			rs.close();
			ps = conn.prepareStatement("SELECT count FROM PageVisited WHERE userID = '"+userID+"' AND pageID='"+pageID+"'");
			rs = ps.executeQuery();
			if(rs.equals(null)){
				System.out.println("nothing returned");
			}
			int count = -1;
			if (rs.next()){
				count = rs.getInt("count");
			}
			rs.close();
			if(count >0){
				ps = conn.prepareStatement("UPDATE pageVisited pv SET pv.count = "+(count+1)+" WHERE userID = "+userID+" AND pageID = "+pageID);
			}else{
				ps = conn.prepareStatement("INSERT INTO pageVisited (userID, pageID, count) VALUES ('"+userID+"', '"+pageID+"', 1) ");
			}
			ps.executeUpdate();
		}catch(SQLException sqle){
			System.out.println("SQLException in function \"increment\"");
			sqle.printStackTrace();
		}
		finally{
			close();
		}
	}
	public static ArrayList<ArrayList<String> >getData(){
		ArrayList<ArrayList<String>>  stat = new ArrayList<ArrayList<String>>();
		connect();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Lab10?user=root&password=root&useSSL=false");
			ps = conn.prepareStatement("SELECT u.Username, p.name, pv.count FROM User u, Page p, PageVisited pv "
					+ "WHERE u.userID = pv.userID AND p.pageID = pv.pageID ORDER BY u.userID, p.pageID");
			rs = ps.executeQuery();
			while(rs.next()){
				ArrayList<String> row = new ArrayList<String>();
				row.add(rs.getString("u.Username"));
				row.add(rs.getString("p.name"));
				row.add(rs.getString("pv.count"));
				stat.add(row);
			}
		}catch(SQLException sqle){
			System.out.println("SQLException in function \" getData\": ");
			sqle.printStackTrace();
		}finally{
			close();
		}
		return stat;
	}
	 public static void main(String[] args) throws Exception {
	        URL yahoo = new URL("http://www.yahoo.com/");
	        URLConnection yc = yahoo.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                yc.getInputStream()));
	        String inputLine;

	        while ((inputLine = in.readLine()) != null) 
	            println("from main:"+inputLine);
	        in.close();
	   	 connect();
	   	println("new user id return = 1 :"+newUser("u1","p1"));
//		println("================================");
	   	println("validation user 1 should be TRUE:"+validate("u1", "p1"));
		println("validation user 2 should be FALSE:"+validate("u2", "p2"));
	    }
	 public static void println(String s){
		 System.out.println(s); 
	 }
}

