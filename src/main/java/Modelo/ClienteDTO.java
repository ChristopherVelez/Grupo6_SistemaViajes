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
