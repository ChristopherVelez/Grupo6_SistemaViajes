
package Controlador;

import ConexionDb.Conexion;
import Modelo.LoginDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public LoginDTO log(String correo, String pass){
        LoginDTO l = new LoginDTO();
        String sql = "SELECT * FROM usuario WHERE correo = ? AND pass = ?";
    try{
       con= cn.getConnection();
       ps = con.prepareStatement(sql);
       ps.setString(1, correo);
       ps.setString(2, pass);
       rs= ps.executeQuery();
       if(rs.next()){
           l.setId(rs.getInt("id"));
           l.setNombre(rs.getString("nombre"));
           l.setCorreo(rs.getString("correo"));
           l.setPass(rs.getString("pass"));
       }
    }catch(SQLException e){
        System.out.println(e.toString());
    }
    return l;
    }
    
}
