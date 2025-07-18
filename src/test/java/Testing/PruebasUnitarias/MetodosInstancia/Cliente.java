/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Testing.PruebasUnitarias.MetodosInstancia;

import Controlador.ClienteDAO;
import Controlador.FacturaDAO;
import Controlador.LoginDAO;
import Controlador.ReservaDAO;
import Modelo.ClienteDTO;
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
public class Cliente {
    private ClienteDAO clienteDAO;


    @BeforeAll
    public static void beforeAll() {
        System.out.println("==> Iniciando pruebas");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("==> Pruebas finalizadas.");
    }

    @BeforeEach
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }
    
     //modulo cliente
    //Agregar cliente
    @Test
    public void TC01_registrarClienteValido() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setDni("1234567890");
        cliente.setNombre("Juan");
        cliente.setTelefono("0991234567");
        cliente.setDireccion("Calle A");
        boolean resultado = clienteDAO.RegistrarCliente(cliente);
        if (resultado) {
            System.out.println("TC01_registrarClienteValido: Prueba CORRECTA (cliente registrado)");
        } else {
            System.out.println("TC01_registrarClienteValido: Prueba FALLIDA (no registró cliente válido)");
        }
        assertTrue(resultado, "Debe registrar correctamente un cliente válido");
    }

    @Test
    public void TC02_registrarClienteDniVacio() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setDni(""); // DNI vacío
        cliente.setNombre("Juan");
        cliente.setTelefono("0991234567");
        cliente.setDireccion("Calle A");
        boolean resultado = clienteDAO.RegistrarCliente(cliente);
        if (!resultado) {
            System.out.println("TC02_registrarClienteDniVacio: Prueba CORRECTA (rechazó DNI vacío)");
        } else {
            System.out.println("TC02_registrarClienteDniVacio: Prueba FALLIDA (aceptó DNI vacío)");
        }
        assertFalse(resultado, "No debe registrar un cliente con DNI vacío");
    }

    @Test
    public void TC03_registrarClienteNombreNulo() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setDni("87654321");
        cliente.setNombre(null);
        cliente.setTelefono("0991234567");
        cliente.setDireccion("Calle A");
        boolean resultado = clienteDAO.RegistrarCliente(cliente);
        if (!resultado) {
            System.out.println("TC03_registrarClienteNombreNulo: Prueba CORRECTA (rechazó nombre nulo)");
        } else {
            System.out.println("TC03_registrarClienteNombreNulo: Prueba FALLIDA (aceptó nombre nulo)");
        }
        assertFalse(resultado, "No debe registrar un cliente con nombre nulo");
    }

    @Test
    public void TC04_registrarClienteDatosLargos() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setDni("123456789012345678901234567890");
        cliente.setNombre("Juan".repeat(50));
        cliente.setTelefono("0991234567");
        cliente.setDireccion("Calle".repeat(50));
        boolean resultado = clienteDAO.RegistrarCliente(cliente);
        if (!resultado) {
            System.out.println("TC04_registrarClienteDatosLargos: Prueba CORRECTA (rechazó datos demasiado largos)");
        } else {
            System.out.println("TC04_registrarClienteDatosLargos: Prueba FALLIDA (aceptó datos demasiado largos)");
        }

        assertFalse(resultado, "No debe registrar un cliente con datos demasiado largos");
    }

    @Test
    public void TC05_verificarClienteInsertado() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setDni("99999999");
        cliente.setNombre("Pedro");
        cliente.setTelefono("0987654321");
        cliente.setDireccion("Calle B");
        boolean resultado = clienteDAO.RegistrarCliente(cliente);
        if (resultado) {
            System.out.println("TC05_verificarClienteInsertado: Cliente registrado correctamente");
        } else {
            System.out.println("TC05_verificarClienteInsertado: Prueba FALLIDA al registrar");
        }

        assertTrue(resultado, "Debe registrar cliente correctamente");
        List<ClienteDTO> lista = clienteDAO.ListarCliente();
        boolean encontrado = lista.stream().anyMatch(c -> "99999999".equals(c.getDni()));
        if (encontrado) {
            System.out.println("TC05_verificarClienteInsertado: Cliente encontrado en la lista");
        } else {
            System.out.println("TC05_verificarClienteInsertado: Cliente NO encontrado en la lista");
        }
        assertTrue(encontrado, "El cliente recién insertado debe aparecer en la lista");
    }
    
   // Eliminar cliente
    
@Test
public void TC05_eliminarClienteExistente() {
    // Prepara un cliente válido para asegurarte de que exista
    ClienteDTO cliente = new ClienteDTO();
    cliente.setDni("1234567890");
    cliente.setNombre("ClienteEliminar");
    cliente.setTelefono("0991111111");
    cliente.setDireccion("Direccion TC05");
    clienteDAO.RegistrarCliente(cliente);

    // Buscar el último cliente insertado
    List<ClienteDTO> lista = clienteDAO.ListarCliente();
int idAEliminar = lista.get(lista.size() - 1).getcodigoCliente();

    // Ejecutar eliminación
    boolean resultado = clienteDAO.EliminarCliente(idAEliminar);
    if (resultado) {
        System.out.println("TC05_eliminarClienteExistente:  Prueba CORRECTA (cliente eliminado)");
    } else {
        System.out.println("TC05_eliminarClienteExistente:  Prueba FALLIDA (no se eliminó)");
    }
    assertTrue(resultado, "Debe eliminar correctamente un cliente existente");
}

@Test
public void TC06_eliminarClienteInexistente() {
    boolean resultado = clienteDAO.EliminarCliente(99999); // ID que no existe
    if (resultado) {
        System.out.println("TC06_eliminarClienteInexistente:  Prueba CORRECTA (query ejecutada aunque no afectó filas)");
    } else {
        System.out.println("TC06_eliminarClienteInexistente:  Prueba FALLIDA (se esperaba true)");
    }
    assertTrue(resultado, "Eliminar un cliente inexistente debe devolver true (query ejecutada)");
}

@Test
public void TC07_eliminarClienteIdNegativo() {
    boolean resultado = clienteDAO.EliminarCliente(-5); // ID inválido
    if (resultado) {
        System.out.println("TC07_eliminarClienteIdNegativo:  Prueba CORRECTA (query ejecutada sin afectar filas)");
    } else {
        System.out.println("TC07_eliminarClienteIdNegativo:  Prueba FALLIDA (se esperaba true)");
    }
    assertTrue(resultado, "Eliminar un cliente con ID negativo debe devolver true (query ejecutada)");
}
    
    
    
    //Modifiar clientes
    
   @Test
public void TC1_modificarClienteValido() {
    // Prepara un cliente válido para asegurarte de que exista
    ClienteDTO cliente = new ClienteDTO();
    cliente.setDni("1234567890");
    cliente.setNombre("Cliente Modificar");
    cliente.setTelefono("0999999999");
    cliente.setDireccion("Dirección inicial");
    clienteDAO.RegistrarCliente(cliente);
    // Buscar el último cliente insertado
    List<ClienteDTO> lista = clienteDAO.ListarCliente();
    int idCliente = lista.get(lista.size() - 1).getcodigoCliente();
    // Crear objeto con datos modificados
    ClienteDTO clienteMod = new ClienteDTO();
    clienteMod.setcodigoCliente(idCliente);
    clienteMod.setDni("0987654321");
    clienteMod.setNombre("Cliente Modificado");
    clienteMod.setTelefono("0988888888");
    clienteMod.setDireccion("Nueva dirección");
    // Ejecutar modificación
    boolean resultado = clienteDAO.ModificarCliente(clienteMod);
    if (resultado) {
        System.out.println("TC1_modificarClienteValido:  Prueba CORRECTA (cliente modificado)");
    } else {
        System.out.println("TC1_modificarClienteValido:  Prueba FALLIDA (no se modificó)");
    }
    assertTrue(resultado, "Debe modificar correctamente un cliente existente");
}
@Test
public void TC2_modificarClienteInexistente() {
    ClienteDTO cliente = new ClienteDTO();
    cliente.setcodigoCliente(99999); // ID que no existe
    cliente.setDni("1234567890");
    cliente.setNombre("Cliente Inexistente");
    cliente.setTelefono("0999999999");
    cliente.setDireccion("Dirección");

    boolean resultado = clienteDAO.ModificarCliente(cliente);
    if (resultado) {
        System.out.println("TC2_modificarClienteInexistente:  Prueba CORRECTA (query ejecutada aunque no afectó filas)");
    } else {
        System.out.println("TC2_modificarClienteInexistente:  Prueba FALLIDA (se esperaba true)");
    }
    assertTrue(resultado, "Modificar un cliente inexistente debe devolver true (query ejecutada)");
}

@Test
public void TC3_modificarClienteDatosInvalidos() {
    // Intentar crear cliente con datos inválidos (DTO lanza excepción)
    try {
        new ClienteDTO(1, null, "", "abc", "");
        System.out.println("TC3_modificarClienteDatosInvalidos:  Prueba FALLIDA (se esperaba excepción por datos inválidos)");
        fail("Se esperaba IllegalArgumentException por datos inválidos");
    } catch (IllegalArgumentException e) {
        System.out.println("TC3_modificarClienteDatosInvalidos:  Prueba CORRECTA (excepción por datos inválidos)");
    }

    // También probar modificar con datos inválidos en el DAO (para ver si retorna false)
    ClienteDTO clienteInvalido = new ClienteDTO();
    clienteInvalido.setcodigoCliente(1);
    clienteInvalido.setDni("123");       // DNI inválido
    clienteInvalido.setNombre(null);     // Nombre inválido
    clienteInvalido.setTelefono("abc");  // Teléfono inválido
    clienteInvalido.setDireccion(null);  // Dirección inválida

    boolean resultado = clienteDAO.ModificarCliente(clienteInvalido);
    if (!resultado) {
        System.out.println("TC3_modificarClienteDatosInvalidos:  Prueba CORRECTA (DAO devolvió false por datos inválidos)");
    } else {
        System.out.println("TC3_modificarClienteDatosInvalidos:  Prueba FALLIDA (se esperaba false por datos inválidos)");
    }
    assertFalse(resultado, "Modificar cliente con datos inválidos debe devolver false");
}
}
