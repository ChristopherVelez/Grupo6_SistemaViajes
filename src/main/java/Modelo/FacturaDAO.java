/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mini Wernaso
 */
public class FacturaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarFactura(FacturaDTO factura) {
        String sqlFactura = "INSERT INTO facturas (FechaEmision, MontoTotal, MetodoPago, EstadoFactura, CodigoCliente) VALUES (?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_factura (CodigoFactura, CodigoReserva) VALUES (?, ?)";
        String sqlReservas = "UPDATE reservas SET Estado = 0 WHERE CodigoCliente = ?";

        try {
            con = cn.getConnection();
            con.setAutoCommit(false); // Iniciar transacción

            // Insertar en la tabla facturas
            ps = con.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(factura.getFechaEmision().getTime()));
            ps.setDouble(2, factura.getMontoTotal());
            ps.setString(3, factura.getMetodoPago());
            ps.setString(4, factura.getEstadoFactura());
            ps.setInt(5, factura.getCodigoCliente());
            ps.executeUpdate();

            // Obtener el CódigoFactura generado
            rs = ps.getGeneratedKeys();
            int codigoFacturaGenerado = 0;
            if (rs.next()) {
                codigoFacturaGenerado = rs.getInt(1);
            }
            ps = con.prepareStatement(sqlReservas);
            ps.setInt(1, factura.getCodigoCliente());
            ps.executeUpdate();

            // Insertar en detalle_factura
            ps = con.prepareStatement(sqlDetalle);
            for (Integer codigoReserva : factura.getReserva()) {
                ps.setInt(1, codigoFacturaGenerado);
                ps.setInt(2, codigoReserva);
                ps.addBatch();
            }
            ps.executeBatch();

            con.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback(); // Revertir si hay error
                }
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback: " + ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al registrar factura: " + e.toString());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.toString());
            }
        }
    }

    public boolean editarFactura(FacturaDTO factura) {
        String sqlUpdate = "UPDATE facturas SET FechaEmision=?, MontoTotal=?, MetodoPago=?, EstadoFactura=?, CodigoCliente=? WHERE CodigoFactura=?";
        String sqlDeleteDetalle = "DELETE FROM detalle_factura WHERE CodigoFactura=?";
        String sqlInsertDetalle = "INSERT INTO detalle_factura (CodigoFactura, CodigoReserva) VALUES (?, ?)";

        try {
            con = cn.getConnection();
            con.setAutoCommit(false);

            // Actualizar factura
            ps = con.prepareStatement(sqlUpdate);
            ps.setDate(1, new java.sql.Date(factura.getFechaEmision().getTime()));
            ps.setDouble(2, factura.getMontoTotal());
            ps.setString(3, factura.getMetodoPago());
            ps.setString(4, factura.getEstadoFactura());
            ps.setInt(5, factura.getCodigoCliente());
            ps.setInt(6, factura.getCodigoFactura());
            ps.executeUpdate();

            // Eliminar detalle anterior
            ps = con.prepareStatement(sqlDeleteDetalle);
            ps.setInt(1, factura.getCodigoFactura());
            ps.executeUpdate();

            // Insertar nuevo detalle
            ps = con.prepareStatement(sqlInsertDetalle);
            for (Integer codReserva : factura.getReserva()) {
                ps.setInt(1, factura.getCodigoFactura());
                ps.setInt(2, codReserva);
                ps.addBatch();
            }
            ps.executeBatch();

            con.commit();
            return true;
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
            }
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean eliminarFactura(int codigoFactura) {
        String sql = "UPDATE facturas SET Estado = 0 WHERE CodigoFactura = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoFactura);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List<FacturaDTO> buscarFacturas(String filtro) {
        List<FacturaDTO> lista = new ArrayList<>();

        String sql = "SELECT * FROM facturas WHERE Estado = 1 AND ("
                + "CAST(CodigoFactura AS CHAR) LIKE ? OR "
                + "MetodoPago LIKE ? OR "
                + "EstadoFactura LIKE ?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            String likeFiltro = "%" + filtro + "%";
            ps.setString(1, likeFiltro);
            ps.setString(2, likeFiltro);
            ps.setString(3, likeFiltro);
            rs = ps.executeQuery();

            while (rs.next()) {
                FacturaDTO factura = new FacturaDTO();
                factura.setCodigoFactura(rs.getInt("CodigoFactura"));
                factura.setFechaEmision(rs.getDate("FechaEmision"));
                factura.setMontoTotal(rs.getDouble("MontoTotal"));
                factura.setMetodoPago(rs.getString("MetodoPago"));
                factura.setEstadoFactura(rs.getString("EstadoFactura"));
                factura.setCodigoCliente(rs.getInt("CodigoCliente"));
                // Obtener reservas relacionadas
                List<Integer> reservas = obtenerReservasPorFactura(factura.getCodigoFactura());
                factura.setReserva(reservas);

                lista.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar facturas: " + e.getMessage());
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

    private List<Integer> obtenerReservasPorFactura(int codigoFactura) {
        List<Integer> reservas = new ArrayList<>();
        String sql = "SELECT CodigoReserva FROM detalle_factura WHERE CodigoFactura = ?";

        // Usa variables locales
        PreparedStatement localPs = null;
        ResultSet localRs = null;

        try {
            localPs = con.prepareStatement(sql);
            localPs.setInt(1, codigoFactura);
            localRs = localPs.executeQuery();

            while (localRs.next()) {
                reservas.add(localRs.getInt("CodigoReserva"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reservas: " + e.getMessage());
        } finally {
            try {
                if (localRs != null) {
                    localRs.close();
                }
                if (localPs != null) {
                    localPs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reservas;
    }

    // Listar facturas activas
    public List<FacturaDTO> ListarFacturas() {
        List<FacturaDTO> listaFacturas = new ArrayList<>();
        String sqlFactura = "SELECT * FROM facturas WHERE Estado = 1"; // corregido de EstadoFactura = 1 a Estado = 1

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sqlFactura);
            rs = ps.executeQuery();

            while (rs.next()) {
                FacturaDTO factura = new FacturaDTO();
                int codigoFactura = rs.getInt("CodigoFactura");

                factura.setCodigoFactura(codigoFactura);
                factura.setFechaEmision(rs.getDate("FechaEmision"));
                factura.setMontoTotal(rs.getDouble("MontoTotal"));
                factura.setMetodoPago(rs.getString("MetodoPago"));
                factura.setEstadoFactura(rs.getString("EstadoFactura"));
                factura.setCodigoCliente(rs.getInt("CodigoCliente"));

                List<Integer> reservas = obtenerReservasPorFactura(codigoFactura);
                factura.setReserva(reservas);

                listaFacturas.add(factura);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar facturas: " + e);
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
                System.out.println("Error cerrando recursos: " + e);
            }
        }

        return listaFacturas;
    }

    public double calcularMontoTotal(List<Integer> codigosReserva) {
        double total = 0.0;
        String sql = "SELECT PrecioPasaje FROM reservas WHERE CodigoReserva = ?";

        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            for (Integer codigo : codigosReserva) {
                ps.setInt(1, codigo);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        total += rs.getDouble("PrecioPasaje");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al calcular monto total: " + e.getMessage());
        }

        return total;
    }

    public List<Integer> obtenerReservasNoFacturadasPorCliente(int codigoCliente) {
        List<Integer> lista = new ArrayList<>();
        String sql = "SELECT CodigoReserva FROM reservas WHERE CodigoCliente = ? AND Estado = 1";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCliente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("CodigoReserva"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener reservas no facturadas: " + e.getMessage());
        }

        return lista;
    }
    
    
    public String obtenerCadenaReservasPorFactura(int codigoFactura) {
    StringBuilder sb = new StringBuilder();
    String sql = "SELECT CodigoReserva FROM detalle_factura WHERE CodigoFactura = ?";

    try (Connection con = cn.getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, codigoFactura);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(rs.getInt("CodigoReserva"));
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener cadena de reservas: " + e.getMessage());
    }

    return sb.toString(); // Retorna por ejemplo "28, 29, 30"
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
