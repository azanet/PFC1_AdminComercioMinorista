/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author davidf
 */
public class MetodosVentas {
 
        
private ConsultasSQL consultas=new ConsultasSQL();   

     public ArrayList LecturaArticulos() {
          consultas=new ConsultasSQL();
        return  consultas.LecturaArticulos();
    
    }
     
     
     
      public double CreditoCliente (String nif) {
       consultas=new ConsultasSQL();
           return consultas.CreditoCliente( nif) ;
      
      }
     
    
      
        public void ArticuloRestaStock(String codigo_articulo,int nuevo_stock) throws SQLException {
  consultas=new ConsultasSQL();
            consultas.ArticuloRestaStock(codigo_articulo,nuevo_stock);
 
       
        }
      
      
       
        public void DescontarSaldoCliente(String nif, double precio_total) {
         consultas=new ConsultasSQL();
                   consultas.DescontarSaldoCliente( nif, precio_total);
        }
       
      
        
          public float ConsultaDescuentoCliente(String nif) {
               consultas=new ConsultasSQL();
              return consultas.ConsultaDescuentoCliente(nif) ;
       }

        
        
                    
           public boolean ExisteNIF(String nif){
                   
              consultas=new ConsultasSQL();
                   return consultas.ExisteNIF(nif);
               
           }

          
               
           public boolean ExisteAlbaranVenta(String numero_albaran) {
           
                consultas=new ConsultasSQL();
            return consultas.ExisteAlbaranVenta(numero_albaran);
              }
                        
           
           
           
           
       public void CrearAlbaran_ventas(String nif,String numero_albaran, LocalDate fecha_venta, String contado_credito) throws SQLException {
                
          consultas=new ConsultasSQL();
           consultas.CrearAlbaran_ventas(nif,numero_albaran,fecha_venta,  contado_credito);
                 
      
       }
               
           
        
           
         public void CrearAlbaran_ventas_Detalle(String numero_albaran,String codigo_articulo,LocalDate fecha_venta,int cantidad,double precio,float descuento ) throws SQLException {
              consultas=new ConsultasSQL();
           consultas.CrearAlbaran_ventas_Detalle(numero_albaran, codigo_articulo, fecha_venta, cantidad, precio, descuento);
     
         }
       
       
      
          
          
         
      
          
          
          
        
          public boolean DarAltaCliente(String nif,String nombre, String direccion, String telefono,Double credito_disponible,float descuento_cliente ) throws SQLException  {

          
     consultas=new ConsultasSQL();
              consultas.DarAltaClientes(nif, nombre, direccion, telefono, credito_disponible, descuento_cliente);
            
            return false;
    
               
   
          }

 }
           
           

