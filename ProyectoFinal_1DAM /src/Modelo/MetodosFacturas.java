/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author davidf
 */
public class MetodosFacturas {
    private ConsultasSQL consultas=new ConsultasSQL();  
    
    
     public ArrayList LecturaAlbaranes (String nif, String tipo_pago) throws SQLException{
       consultas=new ConsultasSQL();  
     
        return consultas.LecturaAlbaranes(nif, tipo_pago);
   }
  
     
                  
           public boolean ExisteNIF(String nif){
                   
              consultas=new ConsultasSQL();
                   return consultas.ExisteNIF(nif);
               
           }

          
     
     

        
       public ArrayList LecturaAlbaranesDetalles_credito (String nif) throws SQLException{
       consultas=new ConsultasSQL();
     
        return  consultas.LecturaAlbaranesDetalles_credito(nif);
   }
  
    
     
       
       
       
             public ArrayList LecturaAlbaranesDetalles_contado (String numero_albaran) throws SQLException{
       consultas=new ConsultasSQL();
     
        return  consultas.LecturaAlbaranesDetalles_contado(numero_albaran);
   }
  
       
       
       
       
       
       
       
       
       
       
       
       
     
    
}
