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
public class Articulo_Venta {

   
    private String cod_articulo;
    private int cantidad;
   
  


    public Articulo_Venta(String cod_articulo, int cantidad) {
       
        this.cod_articulo = cod_articulo;
        this.cantidad = cantidad;
         
    }

     
    //Getters
  


    public String getCod_articulo() {
        return cod_articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

  


    
            
        





    
            
}
