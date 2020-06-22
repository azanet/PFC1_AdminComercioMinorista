/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author davidf
 */
public class Albaran {
    private final String numero_albaran;
    private final String codigo_articulo;
    private final String fecha_venta;
    private final int cantidad;
    private final double precio;
    private final float descuento;

    public Albaran(String numero_albaran, String codigo_articulo, String fecha_venta, int cantidad, double precio, float descuento) {
        this.numero_albaran = numero_albaran;
        this.codigo_articulo = codigo_articulo;
        this.fecha_venta = fecha_venta;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    public String getNumero_albaran() {
        return numero_albaran;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public float getDescuento() {
        return descuento;
    }

   
    
    
    
    
    
}
