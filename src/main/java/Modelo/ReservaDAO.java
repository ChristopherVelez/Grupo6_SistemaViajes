/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mini Wernaso
 */
public class ReservaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarReserva(ReservaDTO re) {
        String sql = "INSERT INTO reservas (codigocliente,origen,destino,fechaviaje,horasalida,asientoasignado,preciopasaje) VALUES (?,?,?,?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, re.getCodigoCliente());
            ps.setString(2, re.getOrigen());
            ps.setString(3, re.getDestino());
            ps.setString(4, re.getFechaViaje());
            ps.setString(5, re.getHoraSalida());
            ps.setString(6, re.getAsientoAsignado());
            ps.setDouble(7, re.getPrecioPasaje());

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

    public boolean asientoYaReservado(String origen, String destino, String fecha, String hora, String asiento) {
        String sql = "SELECT COUNT(*) FROM reservas WHERE Origen = ? AND Destino = ? AND FechaViaje = ? AND HoraSalida = ? AND AsientoAsignado = ? AND Estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, origen);
            ps.setString(2, destino);
            ps.setString(3, fecha);
            ps.setString(4, hora);
            ps.setString(5, asiento);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

   
    // Para EDITAR, excluye la misma reserva (por su ID)
public boolean asientoYaReservado(String origen, String destino, String fecha, String hora, String asiento, int idReservaActual) {
    String sql = "SELECT COUNT(*) FROM reservas WHERE Origen = ? AND Destino = ? AND FechaViaje = ? AND HoraSalida = ? AND AsientoAsignado = ? AND Estado = 1 AND CodigoReserva <> ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, origen);
        ps.setString(2, destino);
        ps.setString(3, fecha);
        ps.setString(4, hora);
        ps.setString(5, asiento);
        ps.setInt(6, idReservaActual); 
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;
}

    
    
    
    
    public List ListarReservas() {
        List<ReservaDTO> ListaRs = new ArrayList<>();
        String sql = "SELECT r.*"
                + "FROM reservas r "
                + "JOIN clientes c ON r.CodigoCliente = c.CodigoCliente "
                + "WHERE r.Estado = 1 AND c.Estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReservaDTO rsdto = new ReservaDTO();
                rsdto.setCodigoReserva(rs.getInt("CodigoReserva"));
                rsdto.setCodigoCliente(rs.getInt("CodigoCliente"));
                rsdto.setOrigen(rs.getString("Origen"));
                rsdto.setDestino(rs.getString("Destino"));
                rsdto.setFechaViaje(rs.getString("FechaViaje"));
                rsdto.setHoraSalida(rs.getString("HoraSalida"));
                rsdto.setAsientoAsignado(rs.getString("AsientoAsignado"));
                rsdto.setPrecioPasaje(rs.getDouble("PrecioPasaje"));
                ListaRs.add(rsdto);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaRs;
    }

    public boolean eliminarReserva(int codigoReserva) {
        String sql = "UPDATE reservas SET Estado = 0 WHERE CodigoReserva = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoReserva);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean editarReserva(ReservaDTO re) {
        String sql = "UPDATE reservas SET CodigoCliente = ?, Origen = ?, Destino = ?, FechaViaje = ?, HoraSalida = ?, AsientoAsignado = ?, PrecioPasaje = ? WHERE CodigoReserva = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, re.getCodigoCliente());
            ps.setString(2, re.getOrigen());
            ps.setString(3, re.getDestino());
            ps.setString(4, re.getFechaViaje());
            ps.setString(5, re.getHoraSalida());
            ps.setString(6, re.getAsientoAsignado());
            ps.setDouble(7, re.getPrecioPasaje());
            ps.setInt(8, re.getCodigoReserva());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    
     public List<ReservaDTO> buscarReservasPorClienteOCodigo(String filtro) {
    List<ReservaDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM reservas WHERE Estado = 1 AND ("
            + "CAST(CodigoReserva AS CHAR) LIKE ? OR CAST(CodigoCliente AS CHAR) LIKE ?)";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        String likeFiltro = "%" + filtro + "%";
        ps.setString(1, likeFiltro);
        ps.setString(2, likeFiltro);
        rs = ps.executeQuery();

        while (rs.next()) {
            ReservaDTO r = new ReservaDTO();
            r.setCodigoReserva(rs.getInt("CodigoReserva"));
            r.setCodigoCliente(rs.getInt("CodigoCliente"));
            r.setOrigen(rs.getString("Origen"));
            r.setDestino(rs.getString("Destino"));
            r.setFechaViaje(rs.getString("FechaViaje"));
            r.setHoraSalida(rs.getString("HoraSalida"));
            r.setAsientoAsignado(rs.getString("AsientoAsignado"));
            r.setPrecioPasaje(rs.getDouble("PrecioPasaje"));
            lista.add(r);
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return lista;
}


     
     public List<ReservaDTO> obtenerReservasPorCodigosReservas(List<Integer> codigosReservas) {
    List<ReservaDTO> lista = new ArrayList<>();
    if (codigosReservas == null || codigosReservas.isEmpty()) {
        return lista; // Devuelve lista vacía si no hay códigos
    }

    // Construir parte dinámica del IN (?, ?, ?)
    StringBuilder placeholders = new StringBuilder();
    for (int i = 0; i < codigosReservas.size(); i++) {
        placeholders.append("?");
        if (i < codigosReservas.size() - 1) {
            placeholders.append(",");
        }
    }

    String sql = "SELECT * FROM reservas WHERE CodigoReserva IN (" + placeholders.toString()+ ")";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        // Establecer los valores del IN (?, ?, ?)
        for (int i = 0; i < codigosReservas.size(); i++) {
            ps.setInt(i + 1, codigosReservas.get(i));
        }

        rs = ps.executeQuery();
        while (rs.next()) {
            ReservaDTO r = new ReservaDTO();
            r.setCodigoReserva(rs.getInt("CodigoReserva"));
            r.setOrigen(rs.getString("Origen"));
            r.setDestino(rs.getString("Destino"));
            r.setFechaViaje(rs.getString("FechaViaje"));
            r.setHoraSalida(rs.getString("HoraSalida"));
            r.setAsientoAsignado(rs.getString("AsientoAsignado"));
            r.setPrecioPasaje(rs.getDouble("PrecioPasaje"));
            // Agrega más campos si los necesitas
            lista.add(r);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener reservas por facturas: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return lista;
}
}
