import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Proc {
	private static DBManager dbm;
	private boolean isConnected;
	
	
	public boolean isConnected() {
		return isConnected;
	}

	public Proc(){
		dbm=new DBManager();
		isConnected=dbm.makeConnection();
	}
	
	public String selectResponse(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		String sessionState=(String)session.getAttribute("state");
		String sessionUser=(String)session.getAttribute("sessionUser");
		
		System.out.println("state: "+sessionState);
		
		if(sessionState==null)
			sessionState="login";
	
		if(sessionState.equals("login")){
			String requestUser=request.getParameter("User");
			String requestPassword=request.getParameter("Password");
			
			if(requestUser!="" && requestUser!=null && requestPassword!="" && requestPassword!=null){
				
				if(dbm.containsUser(requestUser)){
					if(dbm.validateUserAndPassword(requestUser, requestPassword)){				
						session.setAttribute("sessionUser", requestUser);
						session.setAttribute("state", "catalog");
						request.setAttribute("catalog", dbm.getCatalog());
						return "Catalog.jsp?sessionUser="+requestUser;
					}
					else{
						System.out.println("error");
						return "Login.jsp?error=User and password dont match";
					}
				}
				else{
					dbm.saveUser(requestUser, requestPassword);
					session.setAttribute("sessionUser", requestUser);
					session.setAttribute("state", "catalog");
					request.setAttribute("catalog", dbm.getCatalog());
					return "Catalog.jsp?sessionUser="+requestUser;
				}
				
			}else{
				System.out.println("error");
				return "Login.jsp?error=fill de blanks";
			}
		}
		else if(sessionState.equals("catalog")){
			String action=request.getParameter("action");
			if(action==null)action="";
			
			if(action.equals("login")){
				session.setAttribute("sessionUser", null);
				session.setAttribute("state", "login");
				return "Login.jsp";
			}
			else if(action.equals("MyGames")){
				session.setAttribute("state","MyGames");
				request.setAttribute("MyGames", dbm.getMyGames((String) session.getAttribute("sessionUser")));
				return "MyGames.jsp";
			}
			else if(action.equals("buy")){
				System.out.println("user: "+session.getAttribute("sessionUser")+" game: "+request.getParameter("game"));
				dbm.buyGame((String)session.getAttribute("sessionUser"),(String) request.getParameter("game"));
				session.setAttribute("state", "catalog");
				request.setAttribute("catalog", dbm.getCatalog());
				return "Catalog.jsp?sessionUser="+session.getAttribute("sessionUser");
			}
			
		}
		else if(sessionState.equals("MyGames")){
			String action=request.getParameter("action");
			if(action==null)action="";
			
			if(action.equals("login")){
				session.setAttribute("sessionUser", null);
				session.setAttribute("state", "login");
				return "Login.jsp";
			}
			else if(action.equals("catalog")){
				session.setAttribute("state", "catalog");
				request.setAttribute("catalog", dbm.getCatalog());
				return "Catalog.jsp?sessionUser="+session.getAttribute("sessionUser");
			}
			else if(action.equals("delete")){
				dbm.deleteMyGame(request.getParameter("id"));
				session.setAttribute("state","MyGames");
				request.setAttribute("MyGames", dbm.getMyGames((String) session.getAttribute("sessionUser")));
				return "MyGames.jsp";
			}
		}
		
		System.out.println("no if");
		session.setAttribute("state", "login");
		return "Login.jsp";
		
	}
	
	
	public void printValues(){
		
	
	}

}
