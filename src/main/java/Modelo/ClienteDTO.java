/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Mini Wernaso
 */
public class ClienteDTO {

    private int codigoCliente;
    private String dni;
    private String nombre;
    private String telefono;
    private String direccion;

    public ClienteDTO() {
    }

    public ClienteDTO(int codigoCliente, String dni, String nombre, String telefono, String direccion) {
        
        // Validar código
        if (codigoCliente <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor que cero.");
        }

        // Validar nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nombre.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre no debe contener números.");
        }

        // Validar cédula (dni)
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }
        if (!dni.matches("\\d{10}")) {
            throw new IllegalArgumentException("El DNI debe tener exactamente 10 dígitos.");
        }

        // Validar teléfono
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        if (!telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono debe contener solo 10 dígitos.");
        }

        // Validar dirección
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        
        
        this.codigoCliente = codigoCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getcodigoCliente() {
        return codigoCliente;
    }

    public void setcodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

     /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.getNombre();
    }

}
