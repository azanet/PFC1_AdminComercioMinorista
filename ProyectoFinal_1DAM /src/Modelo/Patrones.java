/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.regex.Pattern;

/**
 *
 * @author davidf
 */
public class Patrones {
    
     public Pattern patronDescripcion = Pattern.compile("[a-zA-Z0-9]{1,50}"); //Patron para verificar
     public Pattern patronCodigoArticulo = Pattern.compile("[0-9]{1,9}"); //Patron para verificar
     public Pattern patronOrdenPedido = Pattern.compile("[0-9]{1,100}"); //Patron para verificar
     public Pattern patronPrecio = Pattern.compile("[0-9]{1,}([.][0-9]{1,})?"); //Patron para verificar
     public Pattern patronDescuento = Pattern.compile("[0-9]{1,2}([.][0-9]{1,2})?"); //Patron para verificar
     public Pattern patronStock = Pattern.compile("[0-9]{1,}"); //Patron para verificar
     public Pattern patronNIF = Pattern.compile("[0-9]{8}[a-zA-Z]{1}"); //Patron para verificar
     public Pattern patronCIF = Pattern.compile("^[a-zA-Z]{1}\\d{7}[a-zA-Z0-9]{1}$"); //Patron para verificar
     public Pattern patronNombre = Pattern.compile("[A-Za-z]{2,20}(\\s[A-Za-z]{2,20})?"); //Patron para verificar
     public Pattern patronDireccion = Pattern.compile("(.){5,100}"); //Patron para verificar
     public Pattern patronTelefono = Pattern.compile("(?:\\d{3}|\\(\\d{3}\\))([-\\/\\.])\\d{3}\\1\\d{4}|[0-9]{9}"); //Patron para verificar
     public Pattern patronFecha = Pattern.compile("^\\d{4}\\-\\d{2}\\-\\d{2}$"); //Valida una fecha con formato dd/mm/aaaa(no tiene en cuenta si la fecha es valida en el tiempo o no)
     
}
