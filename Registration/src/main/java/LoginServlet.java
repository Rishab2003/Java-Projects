

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String name=request.getParameter("username");
		String pasString=request.getParameter("password");
		
		if(name.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("status", "invalidusername");
			rd.forward(request, response);
			return;
		}
		if(pasString.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("status", "invalidpassword");
			rd.forward(request, response);
			return;
		}
		
		UserDao udDao=new UserDao();
		
		boolean present=udDao.checkdb(name, pasString);
		if(!present) {
			
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("status", "failure");
			rd.forward(request, response);
			
		
		}
		else {
			HttpSession session=request.getSession();
			session.setAttribute("USERNAME", name);
			session.setAttribute("PASSWORD", pasString);
			response.sendRedirect("index.jsp");
			
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	}
