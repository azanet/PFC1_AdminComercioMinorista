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
public class Albaran_Venta {
    
    
    private final String nif;
      private final String numero_albaran;
       private final String fecha_venta;
    private final String tipo_pago;

    public Albaran_Venta(String nif, String numero_albaran, String fecha_venta, String tipo_pago) {
        this.nif = nif;
        this.numero_albaran = numero_albaran;
        this.fecha_venta = fecha_venta;
        this.tipo_pago = tipo_pago;
    }

    public String getNif() {
        return nif;
    }

    public String getNumero_albaran() {
        return numero_albaran;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }
    
    
    
}
