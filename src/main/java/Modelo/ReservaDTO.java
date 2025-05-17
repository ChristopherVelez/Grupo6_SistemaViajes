/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class ReservaDTO {

    private int codigoReserva;
    private int codigoCliente;
    private String origen;
    private String destino;
    private String fechaViaje;
    private String horaSalida;
    private String asientoAsignado;
    private double precioPasaje;

   


    public ReservaDTO() {
    }

    public ReservaDTO(int codigoReserva, int codigoCliente, String origen, String destino, String fechaViaje, String horaSalida, String asientoAsignado, double precioPasaje) {
        this.codigoReserva = codigoReserva;
        this.codigoCliente = codigoCliente;
        this.origen = origen;
        this.destino = destino;
        this.fechaViaje = fechaViaje;
        this.horaSalida = horaSalida;
        this.asientoAsignado = asientoAsignado;
        this.precioPasaje = precioPasaje;
    }

    public int getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(int codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getAsientoAsignado() {
        return asientoAsignado;
    }

    public void setAsientoAsignado(String asientoAsignado) {
        this.asientoAsignado = asientoAsignado;
    }

    public double getPrecioPasaje() {
        return precioPasaje;
    }

    public void setPrecioPasaje(double precioPasaje) {
        this.precioPasaje = precioPasaje;
    }
    
    
}
