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
public class Articulo {
     private final String codigo_articulo;
     private final String descipcion;
     private final int cantidad;
     private final double precio;

    public Articulo(String codigo_articulo, String descipcion, double precio,int cantidad ) {
        this.codigo_articulo = codigo_articulo;
        this.descipcion = descipcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
     
    
    
    
    
    
    
}
