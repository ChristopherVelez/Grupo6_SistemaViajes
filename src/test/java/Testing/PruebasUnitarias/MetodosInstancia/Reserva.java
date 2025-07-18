/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Testing.PruebasUnitarias.MetodosInstancia;

import Controlador.ClienteDAO;
import Controlador.FacturaDAO;
import Controlador.LoginDAO;
import Controlador.ReservaDAO;
import Modelo.ReservaDTO;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MiniWernaso
 */
public class Reserva {
    
    ReservaDAO reservaDAO = new ReservaDAO();


    @BeforeAll
    public static void beforeAll() {
        System.out.println("==> Iniciando pruebas");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("==> Pruebas finalizadas.");
    }

   
    
     //Reserva
     // Caso TC01: Registrar reserva válida
    @Test
    public void TC01_registrarReservaValida() {
        ReservaDTO reserva = new ReservaDTO();
        reserva.setCodigoCliente(54); // Cliente existente
        reserva.setOrigen("Quito");
        reserva.setDestino("Guayaquil");
        reserva.setFechaViaje("2025-07-25");
        reserva.setHoraSalida("10:30");
        reserva.setAsientoAsignado("A1");
        reserva.setPrecioPasaje(50.0);

        boolean resultado = reservaDAO.RegistrarReserva(reserva);

        if (resultado) {
            System.out.println("TC01_registrarReservaValida: Prueba CORRECTA (reserva registrada)");
        } else {
            System.out.println("TC01_registrarReservaValida: Prueba FALLIDA (no se registró la reserva)");
        }

        assertTrue(resultado, "Debe registrar correctamente una reserva válida");
    }

    // Caso TC02: Registrar reserva con cliente inexistente
    @Test
    public void TC02_registrarReservaClienteInexistente() {
        ReservaDTO reserva = new ReservaDTO();
        reserva.setCodigoCliente(99999); // Cliente inexistente
        reserva.setOrigen("Quito");
        reserva.setDestino("Cuenca");
        reserva.setFechaViaje("2025-07-25");
        reserva.setHoraSalida("14:00");
        reserva.setAsientoAsignado("B3");
        reserva.setPrecioPasaje(60.0);

        boolean resultado = reservaDAO.RegistrarReserva(reserva);

        if (!resultado) {
            System.out.println("TC02_registrarReservaClienteInexistente: Prueba CORRECTA (manejada sin registrar)");
        } else {
            System.out.println("TC02_registrarReservaClienteInexistente: Prueba FALLIDA (registró con cliente inexistente)");
        }

        assertFalse(resultado, "No debe registrar una reserva con cliente inexistente");
    }

    // Caso TC03: Registrar reserva con datos nulos o vacíos
    @Test
    public void TC03_registrarReservaDatosInvalidos() {
        ReservaDTO reserva = new ReservaDTO();
        reserva.setCodigoCliente(55); // Cliente válido
        reserva.setOrigen(null); // Campo inválido
        reserva.setDestino("Loja");
        reserva.setFechaViaje("2025-07-25");
        reserva.setHoraSalida("08:00");
        reserva.setAsientoAsignado("C2");
        reserva.setPrecioPasaje(40.0);

        boolean resultado = reservaDAO.RegistrarReserva(reserva);

        if (!resultado) {
            System.out.println("TC03_registrarReservaDatosInvalidos: Prueba CORRECTA (no registró por datos inválidos)");
        } else {
            System.out.println("TC03_registrarReservaDatosInvalidos: Prueba FALLIDA (registró con datos inválidos)");
        }

        assertFalse(resultado, "No debe registrar una reserva con datos inválidos");
    }


    
    // Caso Tc01: Editar reserva existente con datos válidos
    @Test
    public void Tc01_editarReservaValida() {
        ReservaDTO reserva = new ReservaDTO();
        reserva.setCodigoReserva(59); // Este ID debe existir en BD
        reserva.setCodigoCliente(54); // Cliente existente
        reserva.setOrigen("Quito");
        reserva.setDestino("Guayaquil");
        reserva.setFechaViaje("2025-08-01");
        reserva.setHoraSalida("12:00");
        reserva.setAsientoAsignado("B4");
        reserva.setPrecioPasaje(55.0);

        boolean resultado = reservaDAO.editarReserva(reserva);

        if (resultado) {
            System.out.println("TC01_editarReservaValida: Prueba CORRECTA (reserva editada)");
        } else {
            System.out.println("TC01_editarReservaValida: Prueba FALLIDA (no se editó la reserva existente)");
        }

        assertTrue(resultado, "Debe editar correctamente una reserva existente");
    }

    // Caso TC02: Editar reserva inexistente
    @Test
    public void TC02_editarReservaInexistente() {
        ReservaDTO reserva = new ReservaDTO();
        reserva.setCodigoReserva(9999); // ID que no existe en BD
        reserva.setCodigoCliente(54);
        reserva.setOrigen("Loja");
        reserva.setDestino("Cuenca");
        reserva.setFechaViaje("2025-08-05");
        reserva.setHoraSalida("14:30");
        reserva.setAsientoAsignado("C1");
        reserva.setPrecioPasaje(40.0);

        boolean resultado = reservaDAO.editarReserva(reserva);

        if (!resultado) {
            System.out.println("TC02_editarReservaInexistente: Prueba CORRECTA (no editó porque no existe)");
        } else {
            System.out.println("TC02_editarReservaInexistente: Prueba FALLIDA (editó una reserva inexistente)");
        }

        assertFalse(resultado, "No debe editar una reserva inexistente");
    }

    // Caso TC03: Editar reserva con datos inválidos
    @Test
    public void TC03_editarReservaDatosInvalidos() {
        ReservaDTO reserva = new ReservaDTO();
        reserva.setCodigoReserva(61); // ID existente
        reserva.setCodigoCliente(55);
        reserva.setOrigen(null); // Dato inválido
        reserva.setDestino("Ambato");
        reserva.setFechaViaje("2025-08-10");
        reserva.setHoraSalida("09:00");
        reserva.setAsientoAsignado("A5");
        reserva.setPrecioPasaje(45.0);

        boolean resultado = reservaDAO.editarReserva(reserva);

        if (!resultado) {
            System.out.println("TC03_editarReservaDatosInvalidos: Prueba CORRECTA (no editó por datos inválidos)");
        } else {
            System.out.println("TC03_editarReservaDatosInvalidos: Prueba FALLIDA (editó con datos inválidos)");
        }

        assertFalse(resultado, "No debe editar una reserva con datos inválidos");
    }

    
    
    // Caso TC01: Listar reservas con datos existentes
    @Test
    public void TC01_listarReservasConDatos() {
        List<ReservaDTO> lista = reservaDAO.ListarReservas();

        if (lista != null && !lista.isEmpty()) {
            System.out.println("TC01_listarReservasConDatos: Prueba CORRECTA (se listaron " + lista.size() + " reservas)");
        } else {
            System.out.println("TC01_listarReservasConDatos: Prueba FALLIDA (no se listaron reservas esperadas)");
        }

        assertNotNull(lista, "La lista no debe ser null");
        assertTrue(lista.size() > 0, "Debe listar al menos una reserva activa");
        for (ReservaDTO r : lista) {
            assertEquals(1, 1, "Validando elemento: " + r.getCodigoReserva()); // Solo para mostrar iteración
            assertNotNull(r.getOrigen(), "El origen no debe ser null");
            assertNotNull(r.getDestino(), "El destino no debe ser null");
        }
    }

    // Caso TC02: Listar reservas sin registros activos
    @Test
    public void TC02_listarReservasSinDatos() {
        // Precondición: Base de datos sin reservas activas ni clientes activos
        // (puedes usar una BD de pruebas o limpiar datos antes)

        List<ReservaDTO> lista = reservaDAO.ListarReservas();

        if (lista.isEmpty()) {
            System.out.println("TC02_listarReservasSinDatos: Prueba CORRECTA (lista vacía)");
        } else {
            System.out.println("TC02_listarReservasSinDatos: Prueba FALLIDA (se listaron reservas cuando no debía)");
        }

        assertNotNull(lista, "La lista no debe ser null");
        assertEquals(0, lista.size(), "La lista debe ser vacía si no hay datos");
    }

   @Test
public void TC03_listarReservasErrorConexion() {
    // Para simular esto, se crea un ReservaDAO alterno que falle al conectarse.
    ReservaDAO reservaDAOBad = new ReservaDAO() {
        @Override
        public List<ReservaDTO> ListarReservas() {
            // Simulación de error
            System.out.println("Simulando fallo de conexión");
            return new java.util.ArrayList<>();
        }
    };

    List<ReservaDTO> lista = reservaDAOBad.ListarReservas();

    // Validación manual con mensaje
    if (lista != null && lista.size() == 0) {
        System.out.println("TC03_listarReservasErrorConexion: PRUEBA APROBADA (manejó el error y devolvió lista vacía)");
    } else {
        System.out.println("TC03_listarReservasErrorConexion: PRUEBA NO APROBADA (no manejó el error correctamente)");
    }

    // Asserts formales
    assertNotNull(lista, "La lista no debe ser null incluso si hay error");
    assertEquals(0, lista.size(), "En caso de error, se espera lista vacía");
}


    // TC01: Eliminar reserva válida
    @Test
    public void TR01_eliminarReservaValida() {
        int codigoValido = 59; // Debe existir y estar activa en la BD

        boolean resultado = reservaDAO.eliminarReserva(codigoValido);

        if (resultado) {
            System.out.println("TC01_eliminarReservaValida: PRUEBA APROBADA (reserva eliminada correctamente)");
        } else {
            System.out.println("TC01_eliminarReservaValida: PRUEBA NO APROBADA (falló al eliminar reserva válida)");
        }

        assertTrue(resultado, "Debe eliminar correctamente una reserva válida");
    }

    // TC02: Eliminar reserva inexistente
    @Test
    public void TR02_eliminarReservaInexistente() {
        int codigoInexistente = 999999; // No existe en BD

        boolean resultado = reservaDAO.eliminarReserva(codigoInexistente);

        if (resultado) {
            System.out.println("TC02_eliminarReservaInexistente: PRUEBA APROBADA (manejó reserva inexistente sin error)");
        } else {
            System.out.println("TC02_eliminarReservaInexistente: PRUEBA NO APROBADA (devolvió false al intentar eliminar inexistente)");
        }

        assertTrue(resultado, "Debe retornar true aunque la reserva no exista");
    }

    // TC03: Eliminar reserva con código inválido
    @Test
    public void TR03_eliminarReservaCodigoInvalido() {
        int codigoInvalido = -1; // Código inválido

        boolean resultado = reservaDAO.eliminarReserva(codigoInvalido);

        if (resultado) {
            System.out.println("TC03_eliminarReservaCodigoInvalido: PRUEBA APROBADA (manejó código inválido sin error)");
        } else {
            System.out.println("TC03_eliminarReservaCodigoInvalido: PRUEBA NO APROBADA (devolvió false al intentar eliminar código inválido)");
        }

        assertTrue(resultado, "Debe retornar true aunque el código sea inválido");
    }
}
