package com.medisupply.ingresobodega.entities;

public class RegistroBlockchain {

    private String tipoEvento;
    private String idProducto;
    private String datosEvento;
    private String actorEmisor;

    public RegistroBlockchain() {}

    public RegistroBlockchain(String tipoEvento, String idProducto, String datosEvento, String actorEmisor) {
        this.tipoEvento = tipoEvento;
        this.idProducto = idProducto;
        this.datosEvento = datosEvento;
        this.actorEmisor = actorEmisor;
    }

    // Getters y setters

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDatosEvento() {
        return datosEvento;
    }

    public void setDatosEvento(String datosEvento) {
        this.datosEvento = datosEvento;
    }

    public String getActorEmisor() {
        return actorEmisor;
    }

    public void setActorEmisor(String actorEmisor) {
        this.actorEmisor = actorEmisor;
    }
}
