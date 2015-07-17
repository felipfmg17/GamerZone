import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private final static String drv="com.mysql.jdbc.Driver";
    private final static String db="jdbc:mysql://localhost:3306/gamezone";
    private final static String userAndPass="root";
    private  Connection connection;
    private  Statement statement;
    public static final String bracket="\"";
    
    
    
    public boolean makeConnection(){
    	 try{
             Class.forName(drv);           
             connection=DriverManager.getConnection(db,userAndPass,userAndPass);
             statement=connection.createStatement();        
             return true;
         }
         catch(SQLException e){             
             e.printStackTrace();
             return false;
         }
         catch(Exception e){
        	 e.printStackTrace();
             System.out.println("Error con drv");
             return false;
         }
    }
    
    public boolean saveUser(String user,String password){
    	try{
    		statement.executeUpdate("insert into usuario values (\""+user+"\",\""+password+"\");");
    		return true;
    	}catch(SQLException e){e.printStackTrace(); return false;}
    	
    }
    
    public boolean containsUser(String user){
    	StringBuilder query=new StringBuilder();   	
    	query.append("select count(*) from usuario where idusuario=");
    	query.append(bracket);
    	query.append(user);
    	query.append(bracket);
    	query.append(";");
    	
    	try{
    		ResultSet res=statement.executeQuery(query.toString());
    		if(res.next()){
	    		String num=res.getString(1);
	    		if(Integer.parseInt(num)!=0)return true;
	    		else return false;
    		}
    		return false;
    	}catch(SQLException e){e.printStackTrace();return false;}
    	
    }
    
    public boolean validateUserAndPassword(String user,String password){
    	ResultSet res;
    	StringBuilder q=new StringBuilder();
    	q.append("select count(*) from usuario where ");
    	q.append("idusuario=");
    	q.append(bracket);
    	q.append(user);
    	q.append(bracket);
    	q.append(" and  ");
    	q.append(" password=");
    	q.append(bracket);
    	q.append(password);
    	q.append(bracket);
    	q.append(";");
    	
    	try{
    		res=statement.executeQuery(q.toString());
    		if(res.next()){
	    		int num=res.getInt(1);
	    		return num!=0?true:false;
    		}
    		return false;
    	}catch(SQLException e){e.printStackTrace(); return false;}
    }
    
    public ResultSet getCatalog(){
    	StringBuilder q=new StringBuilder();
    	ResultSet res;
    	q.append("select * from juego");
    	try{
    		res=statement.executeQuery(q.toString());
    		return res;
    	}catch(SQLException e){e.printStackTrace();}
    	return null;
    }

    public void buyGame(String user,String game){
    	StringBuilder q=new StringBuilder();
    	q.append("insert into pedido (idusuario,idjuego) values(");
    	q.append(bracket);
    	q.append(user);
    	q.append(bracket);
    	q.append(",");
    	q.append(bracket);
    	q.append(game);
    	q.append(bracket);
    	q.append(");");
    	
    	try{
    		statement.executeUpdate(q.toString());
    	}catch(SQLException e){e.printStackTrace();}
    }
    
    public ResultSet getMyGames(String user){
    	StringBuilder q=new StringBuilder();
    	q.append("select pedido.idpedido,juego.idjuego,juego.imagen, juego.precio from pedido,juego,usuario where ");
    	q.append("usuario.idusuario=");
    	q.append(bracket);
    	q.append(user);
    	q.append(bracket);
    	q.append(" and usuario.idusuario=pedido.idusuario ");
    	q.append(" and juego.idjuego=pedido.idjuego ");
    	q.append(";");
    	ResultSet res;
    	
    	try{
    		res=statement.executeQuery(q.toString());
    		return res;
    	}catch(SQLException e){e.printStackTrace();return null;}
    
    }

    public void deleteMyGame(String id){
    	StringBuilder q=new StringBuilder();
    	q.append("delete from pedido where idpedido=");
    	q.append(bracket);
    	q.append(id);
    	q.append(bracket);
    	q.append(";");
    	
    	try{
    		statement.executeUpdate(q.toString());
    	}catch(SQLException e){e.printStackTrace();}
    
    }
}
