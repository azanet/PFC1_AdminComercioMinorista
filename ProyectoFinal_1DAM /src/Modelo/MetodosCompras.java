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
public class MetodosCompras {
    
    
    
private ConsultasSQL consultas=new ConsultasSQL();  
    
    
    
    
    public String AltaArticulo (String codigo, String descripcion, double precio, int stock, int stock_minimo, int stock_medio){
    
            consultas=new ConsultasSQL();
            return (consultas.AltaArticulo(codigo, descripcion, precio, stock, stock_minimo, stock_medio));
            
      }//Fin alta artículo
    
    
    
    
        
   public boolean ExisteAlbaranCompra(String numero_albaran){
       
        consultas=new ConsultasSQL();
        return consultas.ExisteAlbaranCompra(numero_albaran);
   }
        
    
   
     public boolean ExisteCIF(String cif){
       
        consultas=new ConsultasSQL();
        return consultas.ExisteCIF(cif);
    }
        
   
   
   
    
        public boolean ExisteOrdenPedidos(String orden_pedidos){
       
        consultas=new ConsultasSQL();
        return consultas.ExisteOrdenPedidos(orden_pedidos);

   }
        
    
    
        public ArrayList LecturaArticulos (){
       
        consultas=new ConsultasSQL();
        return consultas.LecturaArticulos();
   }
    
    
     public boolean ExisteCodigoArticulo(String codigo_articulo) {
            consultas=new ConsultasSQL();
            return  consultas.ExisteCodigoArticulo(codigo_articulo);
       
     }
    
    
     public void ArticuloSumaStock(String codigo_articulo,int cantidad) throws SQLException {
     consultas=new ConsultasSQL();
         consultas.ArticuloSumaStock(codigo_articulo,cantidad) ;
     }
     
     
     public void ArticuloSumaStockPrecio(String codigo_articulo,int cantidad,double precio) throws SQLException {
     consultas=new ConsultasSQL();
         consultas.ArticuloSumaStockPrecio(codigo_articulo,cantidad,precio) ;
     }

    
     
     
     public void CrearAlbaran_compras(String cif_proveedor,String numero_albaran, LocalDate fecha_compra, String orden_pedidos) throws SQLException {
     consultas=new ConsultasSQL();
            consultas.CrearAlbaran_compras(cif_proveedor, numero_albaran, fecha_compra,  orden_pedidos);
     
     }
     
     
     public void CrearAlbaran_compras_Detalle(String numero_albaran,String codigo_articulo, String orden_pedido,int cantidad,double precio,float descuento ) throws SQLException {
        consultas=new ConsultasSQL();
         consultas.CrearAlbaran_compras_Detalle(numero_albaran, codigo_articulo,  orden_pedido,cantidad,precio,descuento);
 
         
     }
     
     
     
        public boolean AltaProveedor(String cif, String nombre_empresa, String direccion, String telefonos) throws SQLException {
          consultas=new ConsultasSQL();
        return consultas.AltaProveedor(cif, nombre_empresa, direccion, telefonos);
        
    }

     
     
        public int StockArticulo(String codigo_articulo) {
          consultas=new ConsultasSQL();
            return consultas.StockArticulo(codigo_articulo);
       
     }
           
     
     
     
}
