package com.medisupply.ingresobodega.entities;

public class Producto {
    private String productoID;
    private String descripcion;
    private String presentacion;
    private String categoriaID;
    private String loteID;
    private String centroDistribucionID;

    public Producto(String productoID, String descripcion, String presentacion, String categoriaID, String loteID, String centroDistribucionID) {
        this.productoID = productoID;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.categoriaID = categoriaID;
        this.loteID = loteID;
        this.centroDistribucionID = centroDistribucionID;
    }

    public String getProductoID() {
        return productoID;
    }

    public void setProductoID(String productoID) {
        this.productoID = productoID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(String categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getLoteID() {
        return loteID;
    }

    public void setLoteID(String loteID) {
        this.loteID = loteID;
    }

    public String getCentroDistribucionID() {
        return centroDistribucionID;
    }

    public void setCentroDistribucionID(String centroDistribucionID) {
        this.centroDistribucionID = centroDistribucionID;
    }
}
