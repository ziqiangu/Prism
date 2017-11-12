

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class GetEntryServlet
 */
@WebServlet("/GetEntryServlet")
public class GetEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String story_id = request.getParameter("story_id");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PrintWriter pw = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://prism-db.cixpikrggo1y.us-west-1.rds.amazonaws.com?user=prism_admin&password=thisclasssucks&useSSL=false");
			conn.prepareStatement("use fuckit").execute();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * from Entry where storyId="+story_id);
			pw = new PrintWriter(response.getOutputStream());
			response.setContentType("application/json");
			JsonArray arr = new JsonArray();
			while (rs.next()) {
				String sentence = rs.getString("sentence");
				int user_id = rs.getInt("UserId");
				int numWords = rs.getInt("numWords");
				JsonObject obj = new JsonObject();
				obj.addProperty("sentence", sentence);
				obj.addProperty("UserId", user_id);
				obj.addProperty("numWords", numWords);
				arr.add(obj);
			}
			pw.write(arr.toString());
			pw.flush();
			pw.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
