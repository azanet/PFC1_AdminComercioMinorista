/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author davidf
 */
public class Articulo_Compra {
     private final String numero_albaran;
     private final String Orden_Pedidos;
     private final String orden_pedido;
     private final String cif;
     private final String codigo_articulo;
     private final int cantidad;
     private final double precio;
     private final float descuento;
     private final LocalDate fecha;

    public Articulo_Compra(String numero_albaran, String Orden_Pedidos, String orden_pedido, String cif, String codigo_articulo, int cantidad, double precio, float descuento, LocalDate fecha) {
        this.numero_albaran = numero_albaran;
        this.Orden_Pedidos = Orden_Pedidos;
        this.orden_pedido = orden_pedido;
        this.cif = cif;
        this.codigo_articulo = codigo_articulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.fecha = fecha;
    }

    public String getNumero_albaran() {
        return numero_albaran;
    }

    public String getOrden_Pedidos() {
        return Orden_Pedidos;
    }

    public String getOrden_pedido() {
        return orden_pedido;
    }

    public String getCif() {
        return cif;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
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

    public LocalDate getFecha() {
        return fecha;
    }

    
    
    
    
}
