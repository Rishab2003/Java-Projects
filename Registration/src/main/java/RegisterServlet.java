

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		String repeatpass=request.getParameter("re_pass");
		String contact=request.getParameter("contact");
		
		User user=new User(name,pass,email,contact);
		
		UserDao uDao=new UserDao();

		if(name.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("status", "invalidname");
			rd.forward(request, response);
			return;
		}
		if(email.equals("") || email==null) {
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("status", "invalidemail");
			rd.forward(request, response);
			return;
		}
		if(pass.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("status", "invalidpass");
			rd.forward(request, response);
			return;
		}
		if(contact.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("status", "invalidcontact");
			rd.forward(request, response);
			return;
		}
		if(!pass.equals(repeatpass)) {
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("status", "passnotmatch");
			rd.forward(request, response);
			return;	
		}
		
		
		boolean present=uDao.checkdb(user.name_new, user.password_new);
		
		RequestDispatcher rd=request.getRequestDispatcher("registration.jsp");
		
		
		
		if(!present) {
			uDao.addrecord(user);
			request.setAttribute("status", "success");
			
			
		}
		else {
			request.setAttribute("status", "failure");
		}
		
		rd.forward(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
