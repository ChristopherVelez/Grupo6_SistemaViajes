/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mini Wernaso
 */
public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarCliente(ClienteDTO cl) {
        String sql = "INSERT INTO clientes (dni,nombre,telefono,direccion) VALUES (?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarCliente() {
        List<ClienteDTO> ListaCl = new ArrayList();
        String sql = "SELECT * FROM clientes WHERE Estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteDTO cl = new ClienteDTO();
                cl.setcodigoCliente(rs.getInt("codigoCliente"));
                cl.setDni(rs.getString("DNI"));
                cl.setNombre(rs.getString("Nombre"));
                cl.setTelefono(rs.getString("Telefono"));
                cl.setDireccion(rs.getString("Direccion"));
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }

    public boolean EliminarCliente(int idCliente) {
        String sqlCliente = "UPDATE clientes SET Estado = 0 WHERE CodigoCliente = ?";
        String sqlReservas = "UPDATE reservas SET Estado = 0 WHERE CodigoCliente = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sqlCliente);
            ps.setInt(1, idCliente);
            ps.executeUpdate();

            ps = con.prepareStatement(sqlReservas);
            ps.setInt(1, idCliente);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

   public boolean ModificarCliente(ClienteDTO cl) {
    String sql = "UPDATE clientes SET dni=?, nombre=?, telefono=?, direccion=? WHERE CodigoCliente=?";
    try {
        con = cn.getConnection();   // abrir conexión aquí
        ps = con.prepareStatement(sql);
        ps.setString(1, cl.getDni());
        ps.setString(2, cl.getNombre());
        ps.setString(3, cl.getTelefono());
        ps.setString(4, cl.getDireccion());
        ps.setInt(5, cl.getcodigoCliente());
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    } finally {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}


    public List<ClienteDTO> buscarClientesPorCodigoDniNombre(String filtro) {
        List<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE Estado = 1 AND ("
        + "CAST(CodigoCliente AS CHAR) LIKE ? OR DNI LIKE ? OR Nombre LIKE ?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            String likeFiltro = "%" + filtro + "%";
            ps.setString(1, likeFiltro);
            ps.setString(2, likeFiltro);
            ps.setString(3, likeFiltro);
            rs = ps.executeQuery();

            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setcodigoCliente(rs.getInt("CodigoCliente"));
                c.setDni(rs.getString("DNI"));
                c.setNombre(rs.getString("Nombre"));
                c.setTelefono(rs.getString("Telefono"));
                c.setDireccion(rs.getString("Direccion"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    
    public ClienteDTO obtenerClientePorCodigo(int codigoCliente) {
    ClienteDTO cliente = null;
    String sql = "SELECT * FROM clientes WHERE CodigoCliente = ?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, codigoCliente);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            cliente = new ClienteDTO();
            cliente.setcodigoCliente(rs.getInt("CodigoCliente"));
            cliente.setDni(rs.getString("Dni"));
            cliente.setNombre(rs.getString("Nombre"));
            cliente.setTelefono(rs.getString("Telefono"));
            cliente.setDireccion(rs.getString("Direccion"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cliente;
}
}
