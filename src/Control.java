
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Control")
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Proc proc;
	
	
 
	@Override
	public void init() throws ServletException {		
		super.init();
		proc=new Proc();
		System.out.println("proc is connected: "+proc.isConnected());
	}

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		RequestDispatcher dispatcher=request.getRequestDispatcher(proc.selectResponse(request, response));
		dispatcher.forward(request	, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(proc.selectResponse(request, response));
		dispatcher.forward(request	, response);

	} 
 
}
