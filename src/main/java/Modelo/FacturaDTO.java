/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mini Wernaso
 */
public class FacturaDTO {

   private int codigoFactura;
   private int codigoCliente;
   private Date fechaEmision;
   private double montoTotal;
   private String metodoPago;
   private String estadoFactura;
   private List<Integer> reserva;
   private String dniCliente;
   private String nombreCliente;

    public FacturaDTO() {
    }

    public FacturaDTO(int codigoFactura, int codigoCliente, Date fechaEmision, double montoTotal, String metodoPago, String estadoFactura, List<Integer> reserva, String dniCliente, String nombreCliente) {
        
        // Validar código factura
        if (codigoFactura <= 0) {
            throw new IllegalArgumentException("El código de factura debe ser mayor que cero.");
        }

        // Validar código cliente
        if (codigoCliente <= 0) {
            throw new IllegalArgumentException("El código de cliente debe ser mayor que cero.");
        }

        // Validar fecha de emisión
        if (fechaEmision == null) {
            throw new IllegalArgumentException("La fecha de emisión no puede ser nula.");
        }

        // Validar monto total
        if (montoTotal < 0) {
            throw new IllegalArgumentException("El monto total no puede ser negativo.");
        }

        // Validar método de pago
        if (metodoPago == null || metodoPago.trim().isEmpty()) {
            throw new IllegalArgumentException("El método de pago no puede estar vacío.");
        }

        // Validar estado de la factura
        if (estadoFactura == null || estadoFactura.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de la factura no puede estar vacío.");
        }

        // Validar lista de reservas
        if (reserva == null || reserva.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos una reserva asociada.");
        }

        // Validar DNI del cliente
        if (dniCliente == null || !dniCliente.matches("\\d{10}")) {
            throw new IllegalArgumentException("El DNI del cliente debe tener exactamente 10 dígitos.");
        }

        // Validar nombre del cliente
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (nombreCliente.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre del cliente no debe contener números.");
        }
        
       
        this.codigoFactura = codigoFactura;
        this.codigoCliente = codigoCliente;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.estadoFactura = estadoFactura;
        this.reserva = reserva;
        this.dniCliente = dniCliente;
        this.nombreCliente = nombreCliente;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public List<Integer> getReserva() {
        return reserva;
    }

    public void setReserva(List<Integer> reserva) {
        this.reserva = reserva;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    

}