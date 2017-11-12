import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AddEntryServlet
 * When a user wants to contribute to a story, the front end will make a call to AddEntryServlet
 * to add his entry to the SQL database
 */
@WebServlet("/AddEntryServlet")
public class AddEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String entry = request.getParameter("entry");
		String numWords = request.getParameter("numWords");
		String user_id = request.getParameter("user_id");
		String story_id = request.getParameter("story_id");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String insertTableSQL = "INSERT into Entry"
					+ "(sentence, UserId, StoryId, numWords) VALUES"
					+ "(?,?,?,?)";
			conn = DriverManager.getConnection("jdbc:mysql://prism-db.cixpikrggo1y.us-west-1.rds.amazonaws.com?user=prism_admin&password=thisclasssucks&useSSL=false");
			conn.prepareStatement("use fuckit").execute();
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, entry);
			preparedStatement.setInt(2, Integer.parseInt(user_id));
			preparedStatement.setInt(3, Integer.parseInt(story_id));
			preparedStatement.setInt(4, Integer.parseInt(numWords));
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
