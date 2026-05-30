package org.banco.entidad;

public class Cajero {

    private Integer idCajero;
    private Sucursal sucursalCajero;
    private String nombreCajero;
    private String turnoCajero;

    public Cajero() {
    }

    public Integer getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(Integer idCajero) {
        this.idCajero = idCajero;
    }

    public Sucursal getSucursalCajero() {
        return sucursalCajero;
    }

    public void setSucursalCajero(Sucursal sucursalCajero) {
        this.sucursalCajero = sucursalCajero;
    }

    public String getNombreCajero() {
        return nombreCajero;
    }

    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }

    public String getTurnoCajero() {
        return turnoCajero;
    }

    public void setTurnoCajero(String turnoCajero) {
        this.turnoCajero = turnoCajero;
    }

}
