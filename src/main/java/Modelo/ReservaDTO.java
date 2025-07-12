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
        
        if (codigoReserva <= 0) {
            throw new IllegalArgumentException("El código de reserva debe ser mayor a cero.");
        }
        
        if (codigoCliente <= 0) {
            throw new IllegalArgumentException("El código de cliente debe ser mayor a cero.");
        }
        
        if (origen == null || origen.trim().isEmpty()) {
            throw new IllegalArgumentException("El origen no puede estar vacío.");
        }
        
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }
        
        if (origen.equalsIgnoreCase(destino)) {
            throw new IllegalArgumentException("El origen y el destino no pueden ser iguales.");
        }
    
        if (fechaViaje == null || fechaViaje.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de viaje no puede estar vacía.");
        }
        
        if (!fechaViaje.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("La fecha de viaje debe tener el formato YYYY-MM-DD.");
        }
        
        if (horaSalida == null || horaSalida.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de salida no puede estar vacía.");
        }
        
        if (!horaSalida.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("La hora de salida debe tener el formato HH:mm.");
        }
        
        if (asientoAsignado == null || asientoAsignado.trim().isEmpty()) {
            throw new IllegalArgumentException("El asiento asignado no puede estar vacío.");
        }
        
        if (precioPasaje <= 0) {
            throw new IllegalArgumentException("El precio del pasaje debe ser mayor a cero.");
        }
        
        
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
