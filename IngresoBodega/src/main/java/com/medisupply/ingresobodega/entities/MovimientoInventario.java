package com.medisupply.ingresobodega.entities;

public class MovimientoInventario {
    private String movimientoInventarioID;
    private String tipoMovimiento;
    private int cantidad;
    private String fechaMovimiento;
    private String motivo;
    private String usuarioResponsable;
    private String referenciaExterna;
    private String productoID;
    private String ordenVentaID;

    public MovimientoInventario(String movimientoInventarioID, String tipoMovimiento, int cantidad, String fechaMovimiento, String motivo, String usuarioResponsable, String referenciaExterna, String productoID, String ordenVentaID) {
        this.movimientoInventarioID = movimientoInventarioID;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.motivo = motivo;
        this.usuarioResponsable = usuarioResponsable;
        this.referenciaExterna = referenciaExterna;
        this.productoID = productoID;
        this.ordenVentaID = ordenVentaID;
    }

    public String getMovimientoInventarioID() {
        return movimientoInventarioID;
    }

    public void setMovimientoInventarioID(String movimientoInventarioID) {
        this.movimientoInventarioID = movimientoInventarioID;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(String usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public String getReferenciaExterna() {
        return referenciaExterna;
    }

    public void setReferenciaExterna(String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }

    public String getProductoID() {
        return productoID;
    }

    public void setProductoID(String productoID) {
        this.productoID = productoID;
    }

    public String getOrdenVentaID() {
        return ordenVentaID;
    }

    public void setOrdenVentaID(String ordenVentaID) {
        this.ordenVentaID = ordenVentaID;
    }
}
