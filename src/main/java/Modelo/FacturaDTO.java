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