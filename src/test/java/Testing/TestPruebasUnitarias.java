package Testing;

import Modelo.ClienteDTO;
import Controlador.ClienteDAO;
import Controlador.FacturaDAO;
import Controlador.LoginDAO;
import Controlador.ReservaDAO;
import Modelo.FacturaDTO;
import Modelo.LoginDTO;
import Modelo.ReservaDTO;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;

public class TestPruebasUnitarias {

    private ClienteDAO clienteDAO;
    LoginDAO loginDAO = new LoginDAO();
    FacturaDAO facturaDAO = new FacturaDAO();
    ReservaDAO reservaDAO = new ReservaDAO();


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
    
    //Metodos constructorew
    
    //Modulo cliente
     // TC01: Constructor válido
    @Test
    public void TC01_constructorValido() {
        try {
            ClienteDTO cliente = new ClienteDTO(
                    1,
                    "0123456789",
                    "Juan Perez",
                    "0991234567",
                    "Calle 123"
            );
            
            // Si no lanza excepción
            System.out.println("TC01_constructorValido:  Prueba EXITOSA");
            assertNotNull(cliente);
            assertEquals(1, cliente.getcodigoCliente());
            assertEquals("0123456789", cliente.getDni());
            assertEquals("Juan Perez", cliente.getNombre());
            assertEquals("0991234567", cliente.getTelefono());
            assertEquals("Calle 123", cliente.getDireccion());
        } catch (Exception e) {
            System.out.println("TC01_constructorValido:  Prueba FALLIDA (" + e.getMessage() + ")");
            fail("No debía lanzar excepción");
        }
    }

    // TC02: Código inválido
    @Test
    public void TC02_codigoInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(0, "0123456789", "Juan Perez", "0991234567", "Calle 123");
        });
        if (ex.getMessage().equals("El código debe ser mayor que cero.")) {
            System.out.println("TC02_codigoInvalido:  Prueba EXITOSA");
        } else {
            System.out.println("TC02_codigoInvalido:  Prueba FALLIDA (mensaje inesperado)");
        }
    }

    // TC03: Nombre vacío
    @Test
    public void TC03_nombreVacio() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(1, "0123456789", "", "0991234567", "Calle 123");
        });
        if (ex.getMessage().equals("El nombre no puede estar vacío.")) {
            System.out.println("TC03_nombreVacio:  Prueba EXITOSA");
        } else {
            System.out.println("TC03_nombreVacio:  Prueba FALLIDA");
        }
    }

    // TC04: Nombre con números
    @Test
    public void TC04_nombreConNumeros() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(1, "0123456789", "Juan123", "0991234567", "Calle 123");
        });
        if (ex.getMessage().equals("El nombre no debe contener números.")) {
            System.out.println("TC04_nombreConNumeros:  Prueba EXITOSA");
        } else {
            System.out.println("TC04_nombreConNumeros:  Prueba FALLIDA");
        }
    }

    // TC05: DNI vacío
    @Test
    public void TC05_dniVacio() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(1, "", "Juan Perez", "0991234567", "Calle 123");
        });
        if (ex.getMessage().equals("El DNI no puede estar vacío.")) {
            System.out.println("TC05_dniVacio: Prueba EXITOSA");
        } else {
            System.out.println("TC05_dniVacio: Prueba FALLIDA");
        }
    }

    // TC06: DNI inválido
    @Test
    public void TC06_dniInvalidoMenosDe10() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(1, "12345", "Juan Perez", "0991234567", "Calle 123");
        });
        if (ex.getMessage().equals("El DNI debe tener exactamente 10 dígitos.")) {
            System.out.println("TC06_dniInvalidoMenosDe10:  Prueba EXITOSA");
        } else {
            System.out.println("TC06_dniInvalidoMenosDe10:  Prueba FALLIDA");
        }
    }

    // TC07: Teléfono vacío
    @Test
    public void TC07_telefonoVacio() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(1, "0123456789", "Juan Perez", "", "Calle 123");
        });
        if (ex.getMessage().equals("El teléfono no puede estar vacío.")) {
            System.out.println("TC07_telefonoVacio: Prueba EXITOSA");
        } else {
            System.out.println("TC07_telefonoVacio: Prueba FALLIDA");
        }
    }

    // TC08: Teléfono inválido
    @Test
    public void TC08_telefonoInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(1, "0123456789", "Juan Perez", "09912", "Calle 123");
        });
        if (ex.getMessage().equals("El teléfono debe contener solo 10 dígitos.")) {
            System.out.println("TC08_telefonoInvalido:  Prueba EXITOSA");
        } else {
            System.out.println("TC08_telefonoInvalido:  Prueba FALLIDA");
        }
    }

    // TC09: Dirección vacía
    @Test
    public void TC09_direccionVacia() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(1, "0123456789", "Juan Perez", "0991234567", "");
        });
        if (ex.getMessage().equals("La dirección no puede estar vacía.")) {
            System.out.println("TC09_direccionVacia: Prueba EXITOSA");
        } else {
            System.out.println("TC09_direccionVacia: Prueba FALLIDA");
        }
    }
    
    //Modulo factura
    //Metodo constructor
    @Test
    public void TC01_constructorFacturaValido() {
        try {
            FacturaDTO factura = new FacturaDTO(
                1, 1, new Date(System.currentTimeMillis()), 100.0,
                "Tarjeta", "Activa", Arrays.asList(1, 2),
                "0123456789", "Juan Perez"
            );
            System.out.println("TC01_constructorValido: PRUEBA CORRECTA");
        } catch (Exception e) {
            System.out.println("TC01_constructorValido: PRUEBA FALLIDA - " + e.getMessage());
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void TC02_codigoFacturaInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FacturaDTO(
                0, 1, new Date(System.currentTimeMillis()), 100.0,
                "Tarjeta", "Activa", Arrays.asList(1, 2),
                "0123456789", "Juan Perez"
            );
        });
        if ("El código de factura debe ser mayor que cero.".equals(exception.getMessage())) {
            System.out.println("TC02_codigoFacturaInvalido: PRUEBA CORRECTA");
        } else {
            System.out.println("TC02_codigoFacturaInvalido: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC03_codigoClienteInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FacturaDTO(
                1, 0, new Date(System.currentTimeMillis()), 100.0,
                "Tarjeta", "Activa", Arrays.asList(1, 2),
                "0123456789", "Juan Perez"
            );
        });
        if ("El código de cliente debe ser mayor que cero.".equals(exception.getMessage())) {
            System.out.println("TC03_codigoClienteInvalido: PRUEBA CORRECTA");
        } else {
            System.out.println("TC03_codigoClienteInvalido: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC04_fechaEmisionNula() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FacturaDTO(
                1, 1, null, 100.0,
                "Tarjeta", "Activa", Arrays.asList(1, 2),
                "0123456789", "Juan Perez"
            );
        });
        if ("La fecha de emisión no puede ser nula.".equals(exception.getMessage())) {
            System.out.println("TC04_fechaEmisionNula: PRUEBA CORRECTA");
        } else {
            System.out.println("TC04_fechaEmisionNula: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC05_montoTotalNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FacturaDTO(
                1, 1, new Date(System.currentTimeMillis()), -1.0,
                "Tarjeta", "Activa", Arrays.asList(1, 2),
                "0123456789", "Juan Perez"
            );
        });
        if ("El monto total no puede ser negativo.".equals(exception.getMessage())) {
            System.out.println("TC05_montoTotalNegativo: PRUEBA CORRECTA");
        } else {
            System.out.println("TC05_montoTotalNegativo: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC06_metodoPagoVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FacturaDTO(
                1, 1, new Date(System.currentTimeMillis()), 100.0,
                "", "Activa", Arrays.asList(1, 2),
                "0123456789", "Juan Perez"
            );
        });
        if ("El método de pago no puede estar vacío.".equals(exception.getMessage())) {
            System.out.println("TC06_metodoPagoVacio: PRUEBA CORRECTA");
        } else {
            System.out.println("TC06_metodoPagoVacio: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC07_estadoFacturaVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FacturaDTO(
                1, 1, new Date(System.currentTimeMillis()), 100.0,
                "Tarjeta", "", Arrays.asList(1, 2),
                "0123456789", "Juan Perez"
            );
        });
        if ("El estado de la factura no puede estar vacío.".equals(exception.getMessage())) {
            System.out.println("TC07_estadoFacturaVacio: PRUEBA CORRECTA");
        } else {
            System.out.println("TC07_estadoFacturaVacio: PRUEBA FALLIDA");
        }
    }

    //Modelo Login
    //Metodo constructor
    
    
     @Test
    public void TC01_constructorLoginValido() {
        try {
            LoginDTO login = new LoginDTO(1, "Juan", "juan@mail.com", "123456");
            System.out.println("TC01_constructorValido: PRUEBA CORRECTA");
        } catch (Exception e) {
            System.out.println("TC01_constructorValido: PRUEBA FALLIDA - " + e.getMessage());
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void TC02_idInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new LoginDTO(0, "Juan", "juan@mail.com", "123456");
        });
        if ("El ID no puede estar vacío ni ser menor o igual a cero.".equals(ex.getMessage())) {
            System.out.println("TC02_idInvalido: PRUEBA CORRECTA");
        } else {
            System.out.println("TC02_idInvalido: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC03_nombreLoginVacio() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new LoginDTO(1, "", "juan@mail.com", "123456");
        });
        if ("El nombre no puede estar vacío.".equals(ex.getMessage())) {
            System.out.println("TC03_nombreVacio: PRUEBA CORRECTA");
        } else {
            System.out.println("TC03_nombreVacio: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC04_correoVacio() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new LoginDTO(1, "Juan", "", "123456");
        });
        if ("El correo no puede estar vacío.".equals(ex.getMessage())) {
            System.out.println("TC04_correoVacio: PRUEBA CORRECTA");
        } else {
            System.out.println("TC04_correoVacio: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC05_correoInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new LoginDTO(1, "Juan", "abc", "123456");
        });
        if ("El formato del correo no es válido.".equals(ex.getMessage())) {
            System.out.println("TC05_correoInvalido: PRUEBA CORRECTA");
        } else {
            System.out.println("TC05_correoInvalido: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC06_passVacia() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new LoginDTO(1, "Juan", "juan@mail.com", "");
        });
        if ("La contraseña no puede estar vacía.".equals(ex.getMessage())) {
            System.out.println("TC06_passVacia: PRUEBA CORRECTA");
        } else {
            System.out.println("TC06_passVacia: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC07_passCorta() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new LoginDTO(1, "Juan", "juan@mail.com", "123");
        });
        if ("La contraseña debe tener al menos 6 caracteres.".equals(ex.getMessage())) {
            System.out.println("TC07_passCorta: PRUEBA CORRECTA");
        } else {
            System.out.println("TC07_passCorta: PRUEBA FALLIDA");
        }
    }
    //Modelo Reservas
    //Metodo constructor
     @Test
    public void TC01_constructorReservaValido() {
        try {
            ReservaDTO r = new ReservaDTO(1, 10, "Quito", "Guayaquil", "2025-07-20", "08:30", "A1", 50.0);
            System.out.println("TC01_constructorValido: PRUEBA CORRECTA");
        } catch (Exception e) {
            System.out.println("TC01_constructorValido: PRUEBA FALLIDA - " + e.getMessage());
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void TC02_codigoReservaInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ReservaDTO(0, 10, "Quito", "Guayaquil", "2025-07-20", "08:30", "A1", 50.0);
        });
        if ("El código de reserva debe ser mayor a cero.".equals(ex.getMessage())) {
            System.out.println("TC02_codigoReservaInvalido: PRUEBA CORRECTA");
        } else {
            System.out.println("TC02_codigoReservaInvalido: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC03_origenVacio() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ReservaDTO(1, 10, "", "Guayaquil", "2025-07-20", "08:30", "A1", 50.0);
        });
        if ("El origen no puede estar vacío.".equals(ex.getMessage())) {
            System.out.println("TC03_origenVacio: PRUEBA CORRECTA");
        } else {
            System.out.println("TC03_origenVacio: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC04_origenDestinoIguales() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ReservaDTO(1, 10, "Quito", "Quito", "2025-07-20", "08:30", "A1", 50.0);
        });
        if ("El origen y el destino no pueden ser iguales.".equals(ex.getMessage())) {
            System.out.println("TC04_origenDestinoIguales: PRUEBA CORRECTA");
        } else {
            System.out.println("TC04_origenDestinoIguales: PRUEBA FALLIDA");
        }
    }

    @Test
    public void TC05_fechaFormatoIncorrecto() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new ReservaDTO(1, 10, "Quito", "Guayaquil", "20-07-2025", "08:30", "A1", 50.0);
        });
        if ("La fecha de viaje debe tener el formato YYYY-MM-DD.".equals(ex.getMessage())) {
            System.out.println("TC05_fechaFormatoIncorrecto: PRUEBA CORRECTA");
        } else {
            System.out.println("TC05_fechaFormatoIncorrecto: PRUEBA FALLIDA");
        }
    }
    //Metodos de instancia
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
    
    //Login
    
 @Test
    public void TL01_loginCorrecto() {
        String correoValido = "vendedor@gmail.com";
        String passValida = "12345";

        LoginDTO resultado = loginDAO.log(correoValido, passValida);

        if (resultado != null && resultado.getId() > 0) {
            System.out.println("TC01_loginCorrecto: Prueba CORRECTA (usuario autenticado)");
        } else {
            System.out.println("TC01_loginCorrecto: Prueba FALLIDA (no autenticó usuario válido)");
        }

        assertNotNull(resultado, "Debe retornar un objeto LoginDTO");
        assertTrue(resultado.getId() > 0, "El ID debe ser mayor a 0 para un login válido");
        assertNotNull(resultado.getNombre(), "El nombre no debe ser nulo");
        assertEquals(correoValido, resultado.getCorreo(), "El correo debe coincidir");
    }

    // Caso TC02: Login incorrecto (usuario o contraseña no existen)
    @Test
    public void TL02_loginIncorrecto() {
        String correoInvalido = "noexiste@correo.com";
        String passInvalida = "contraseñaInvalida";

        LoginDTO resultado = loginDAO.log(correoInvalido, passInvalida);

        if (resultado == null || resultado.getId() == 0) {
            System.out.println("TC02_loginIncorrecto: Prueba CORRECTA (no autenticó usuario inexistente)");
        } else {
            System.out.println("TC02_loginIncorrecto: Prueba FALLIDA (retornó datos de un usuario inexistente)");
        }

        // Validar que no devolvió datos de usuario
        assertNotNull(resultado, "Debe retornar un objeto LoginDTO aunque sea vacío");
        assertEquals(0, resultado.getId(), "El ID debe ser 0 si no autenticó");
        assertNull(resultado.getNombre(), "El nombre debe ser null si no autenticó");
    }

    // Caso TC03: Login con datos nulos o vacíos
    @Test
    public void TL03_loginDatosNulosOVacios() {
        LoginDTO resultado = loginDAO.log(null, null);

        if (resultado == null || resultado.getId() == 0) {
            System.out.println("TC03_loginDatosNulosOVacios: Prueba CORRECTA (manejó datos nulos sin autenticación)");
        } else {
            System.out.println("TC03_loginDatosNulosOVacios: Prueba FALLIDA (retornó datos con parámetros nulos)");
        }

        assertNotNull(resultado, "Debe retornar un objeto LoginDTO aunque sea vacío");
        assertEquals(0, resultado.getId(), "El ID debe ser 0 si no autenticó");
    }
    
    // Caso TC01: Registrar factura con datos válidos
    @Test
    public void TF01_registrarFacturaValida() {
        FacturaDTO factura = new FacturaDTO();
        factura.setFechaEmision(new java.sql.Date(System.currentTimeMillis()));

        factura.setMontoTotal(200.0);
        factura.setMetodoPago("Tarjeta");
        factura.setEstadoFactura("Pendiente");
        factura.setCodigoCliente(54);  // Cliente válido en BD
        factura.setReserva(Arrays.asList(57));  // Reservas válidas y activas

        boolean resultado = facturaDAO.RegistrarFactura(factura);

        if (resultado) {
            System.out.println("TC01_registrarFacturaValida: Prueba CORRECTA (factura registrada)");
        } else {
            System.out.println("TC01_registrarFacturaValida: Prueba FALLIDA (no se registró la factura)");
        }

        assertTrue(resultado, "Debe registrar correctamente una factura válida");
    }

  

    // Caso TC02: Registrar factura con lista de reservas vacía o nula
    @Test
    public void TF02_registrarFacturaReservasVacias() {
        FacturaDTO factura = new FacturaDTO();
        factura.setFechaEmision(new java.sql.Date(System.currentTimeMillis()));

        factura.setMontoTotal(100.0);
        factura.setMetodoPago("Transferencia");
        factura.setEstadoFactura("Activa");
        factura.setCodigoCliente(55);  // Cliente válido
        factura.setReserva(null);    // Lista de reservas nula

        boolean resultado = facturaDAO.RegistrarFactura(factura);

        if (resultado) {
            System.out.println("TC02_registrarFacturaReservasVacias: Prueba CORRECTA (manejó lista de reservas nula)");
        } else {
            System.out.println("TC02_registrarFacturaReservasVacias: Prueba FALLIDA (no manejó lista nula correctamente)");
        }

        assertTrue(resultado, "Debe manejar lista de reservas vacía o nula sin excepción");
    }
     // Caso TC01: Eliminar factura existente
    @Test
    public void Tc01_eliminarFacturaExistente() {
        int codigoFacturaValido = 25;

        boolean resultado = facturaDAO.eliminarFactura(codigoFacturaValido);

        if (resultado) {
            System.out.println("TC01_eliminarFacturaExistente: Prueba CORRECTA (factura desactivada)");
        } else {
            System.out.println("TC01_eliminarFacturaExistente: Prueba FALLIDA (no se desactivó factura)");
        }

        assertTrue(resultado, "Debe eliminar (desactivar) correctamente una factura existente");
    }

    // Caso TC02: Eliminar factura inexistente
    @Test
    public void TC02_eliminarFacturaInexistente() {
        int codigoFacturaInexistente = 99999; // No existe en BD

        boolean resultado = facturaDAO.eliminarFactura(codigoFacturaInexistente);

        if (resultado) {
            System.out.println("TC02_eliminarFacturaInexistente: Prueba CORRECTA (ejecutó query sin afectar filas)");
        } else {
            System.out.println("TC02_eliminarFacturaInexistente: Prueba FALLIDA (no esperaba fallo)");
        }

        assertTrue(resultado, "Eliminar factura inexistente debe retornar true (query ejecutada)");
    }

    // Caso TC03: Eliminar factura con código negativo o cero
    @Test
    public void TC03_eliminarFacturaCodigoInvalido() {
        int codigoFacturaInvalido = -5;

        boolean resultado = facturaDAO.eliminarFactura(codigoFacturaInvalido);

        if (resultado) {
            System.out.println("TC03_eliminarFacturaCodigoInvalido: Prueba CORRECTA (query ejecutada sin error con código inválido)");
        } else {
            System.out.println("TC03_eliminarFacturaCodigoInvalido: Prueba FALLIDA (se esperaba true)");
        }

        assertTrue(resultado, "Eliminar factura con código inválido debe retornar true (sin excepción)");
    }

    
    // Caso TC01: Calcular monto con reservas válidas
    @Test
    public void TC01_calcularMontoReservasValidas() {
        double total = facturaDAO.calcularMontoTotal(Arrays.asList(69, 60)); // IDs válidos

        if (total > 0) {
            System.out.println("TC01_calcularMontoReservasValidas: Prueba CORRECTA (total = " + total + ")");
        } else {
            System.out.println("TC01_calcularMontoReservasValidas: Prueba FALLIDA (total esperado > 0)");
        }

        assertTrue(total > 0, "El monto total debe ser mayor que 0 para reservas válidas");
    }

    // Caso TC02: Calcular monto con lista vacía
    @Test
    public void TC02_calcularMontoListaVacia() {
        double total = facturaDAO.calcularMontoTotal(Collections.emptyList());

        if (total == 0.0) {
            System.out.println("TC02_calcularMontoListaVacia: Prueba CORRECTA (total = 0)");
        } else {
            System.out.println("TC02_calcularMontoListaVacia: Prueba FALLIDA (total esperado 0)");
        }

        assertEquals(0.0, total, 0.001, "El monto total debe ser 0 para una lista vacía");
    }

    // Caso TC03: Calcular monto con reservas inexistentes
    @Test
    public void TC03_calcularMontoReservasInexistentes() {
        double total = facturaDAO.calcularMontoTotal(Arrays.asList(9999, 8888)); // IDs inexistentes

        if (total == 0.0) {
            System.out.println("TC03_calcularMontoReservasInexistentes: Prueba CORRECTA (total = 0)");
        } else {
            System.out.println("TC03_calcularMontoReservasInexistentes: Prueba FALLIDA (total esperado 0)");
        }

        assertEquals(0.0, total, 0.001, "El monto total debe ser 0 si no hay reservas válidas");
    }

    // Caso TC04: Calcular monto con lista nula
    @Test
    public void TC04_calcularMontoListaNula() {
        double total = facturaDAO.calcularMontoTotal(null);

        if (total == 0.0) {
            System.out.println("TC04_calcularMontoListaNula: Prueba CORRECTA (total = 0)");
        } else {
            System.out.println("TC04_calcularMontoListaNula: Prueba FALLIDA (total esperado 0)");
        }

        assertEquals(0.0, total, 0.001, "El monto total debe ser 0 si la lista es nula");
    }

    
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