/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Testing;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author PERSONAL
 */
public class TestCajaBlanca {
 
    
    static Connection conn;

    @BeforeAll
    public static void conectarBD() throws Exception {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemareserva", "root", ""); // Ajusta tu contraseña
    }

    @AfterAll
    public static void cerrarBD() throws Exception {
        conn.close();
    }

    //Criterio de Comandos: Insertar cliente
    @Test
    public void testInsertarCliente() throws Exception {
        String sql = "INSERT INTO clientes (DNI, Nombre, Telefono, Direccion, Estado) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "0951317668");
        stmt.setString(2, "Jeremy");
        stmt.setString(3, "0954667890");
        stmt.setString(4, "Febres cordero 234");
        stmt.setInt(5, 1);

        int filas = stmt.executeUpdate();
        assertEquals(1, filas);
        System.out.println("Cliente agregado con éxito.");
    }

    //Criterio de Decisiones: Consultar clientes activos o inactivos
    @Test
    public void testBuscarClientePorEstado() throws Exception {
        String sql = "SELECT * FROM clientes WHERE Estado = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, 1); // Clientes activos

        ResultSet rs = stmt.executeQuery();
        boolean tieneResultados = rs.next();
        assertTrue(tieneResultados);
        System.out.println("Cliente activo encontrado.");
    }

    //Condiciones y Decisiones: Verificación antes de registrar
    @Test
    public void testValidarDatosReserva() {
        int cliente = 54;
        String destino = "Guayaquil";
        double precio = 5.00;

        boolean datosValidos = (cliente > 0) && (destino != null && !destino.isEmpty()) && (precio > 0);
        assertTrue(datosValidos); // Evalúa cada condición V/F y su efecto
        System.out.println("Validación de datos de reserva exitosa.");
    }

    //Condiciones múltiples: Buscar reservas por cliente, destino y estado
    @Test
    public void testBuscarReservaAvanzada() throws Exception {
        String sql = "SELECT * FROM reservas WHERE CodigoCliente = ? AND Destino = ? AND Estado = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, 54);
        stmt.setString(2, "Guayaquil");
        stmt.setInt(3, 0);

        ResultSet rs = stmt.executeQuery();
        boolean encontrada = rs.next();
        assertTrue(encontrada);
         System.out.println("Reserva encontrada con múltiples condiciones.");
    }

}
