package ConexionDb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Grupo 6
 */
public class Conexion {
    public Connection getConnection(){
        Connection con;
    try{
    String myBD = "jdbc:mysql://localhost:3306/sistemareserva?serverTimezone=UTC";
    con=DriverManager.getConnection(myBD,"root","");
    return con;
    }catch (SQLException e){
        System.out.println(e.toString());
    }
    return null;
    } 
}
