package servlets;
import driver.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//is the login password
		
		// TODO Auto-generated method stub
		//gotta split by /
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("n");
		String password = request.getParameter("p");
		
		if( JDBCDriver.validate(username, password)){
			println(" yes log in");
			request.getSession().setAttribute("msg", "yes log in");
			
//			response.getOutputStream().print("true/"+username);
		}else{
//			response.getOutputStream().print("false/");
			request.getSession().setAttribute("msg", "no log in");
			println("no log in in");
		}
		response.sendRedirect("form.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//is the create account password. Returns userID
		String username = request.getParameter("n");
		String password = request.getParameter("p");
		String userID = Integer.toString(JDBCDriver.newUser(username,password));
				
		println("user id is"+ userID);
		
		request.getSession().setAttribute("msg", "User id:"+userID);
		response.getOutputStream().print(userID);//returning the user id
		response.sendRedirect("form.jsp");
		
	}
	public static void println(String s){
		 System.out.println(s); 
	 }

}
