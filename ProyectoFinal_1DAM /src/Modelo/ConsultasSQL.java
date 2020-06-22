/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.LocalDate.parse;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidf
 */
public class ConsultasSQL {

    //Declaramos OBJETO DE CLASE CONECTOR
    Connection con;
    ResultSet rs;
   //PreparedStatement s;

    //INICIALIZAMOS EL OBJETO EN EL CONSTRUCTOR (a travez de la llamada a una funcion privada,por seguridad)
    public ConsultasSQL() {
        Conexion1();
    }

    private void Conexion1() {
        try {
            //LE INDICAMOS CUAL ES EL DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Con este objeto de Connection y utilizando "getConnection" que lleva 3 parametros, le indicamos la bbdd a conectar, usuario y contraseña
            String url = "jdbc:mysql://localhost:3306/krazy_lab?verifyServerCertificate=false&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            this.con = DriverManager.getConnection(url, "root", "admin");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, ("Error " + e), "Failure", JOptionPane.ERROR_MESSAGE);
        }//Fin del try catch

    }//Fin del CONSTRUCTOR
    

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    
        public String AltaArticulo (String codigo, String descripcion, double precio, int stock, int stock_minimo, int stock_medio){
    
          
            return AltaArticuloH(codigo, descripcion, precio, stock, stock_minimo, stock_medio);
            
      }//Fin alta artículo
    
    
    private String AltaArticuloH(String codigo, String descripcion, double precio, int stock, int stock_minimo, int stock_medio) {
        // Preparamos la consulta
        String resultado = "";
        try {

            PreparedStatement s1 = this.con.prepareStatement("INSERT INTO `Articulos` (`codigo`, `descripcion`, `precio`, `stock`, `stock_minimo`, `stock_medio`) VALUES (?, ?, ?, ?, ?, ?)");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, codigo);
            s1.setString(2, descripcion);
            s1.setDouble(3, precio);
            s1.setInt(4, stock);
            s1.setInt(5, stock_minimo);
            s1.setInt(6, stock_medio);

            if (s1.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar el ALTA, compruebe que los datos están correctos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                resultado = "Articulo DADO DE ALTA con éxito:\n\nNombre: " + descripcion + "\nCódigo articulo: " + codigo + "\nPrecio: " + precio + "\nStock: " + stock + "\nStock_minimo: " + stock_minimo + "\nStock_medio: " + stock_medio;

            }

            s1.close();

            //CERRAMOS CONEXION
        } catch (Exception e) {

            resultado = "Error, " + e.getMessage();
        }//Fin del try catch

        return resultado;

    }//Fin metodo

    
    
    
       public boolean ExisteAlbaranCompra(String numero_albaran){
       
   
        return ExisteAlbaranCompraH(numero_albaran);
   }
        
    
    
    private boolean ExisteAlbaranCompraH(String numero_albaran) {

        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM Albaran_compras WHERE numero_albaran=?");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, numero_albaran);

            rs = s1.executeQuery();

            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                String albaran_bbdd = rs.getString("numero_albaran");
                //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false

                //       JOptionPane.showMessageDialog(null, "albaran leido:"+albaran_bbdd, "Error", JOptionPane.ERROR_MESSAGE);
                if (albaran_bbdd.equalsIgnoreCase(numero_albaran)) {
                    s1.close();
                    return true;
                }
            }
            s1.close();
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return true;

    }

    
        public boolean ExisteCIF(String cif){
           
        return ExisteCIFH(cif);
    }
    
    
    public boolean ExisteCIFH(String cif) {

        try {
            try (PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM Proveedores WHERE cif=?") //Agregando parametros a la posicion correspondiente de la consulta
            ) {
                s1.setString(1, cif);
                
                rs = s1.executeQuery();
                
                //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
                while (rs.next()) {
                    String albaran_bbdd = rs.getString("cif");
                    //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false
                    
//           JOptionPane.showMessageDialog(null, "albaran leido:"+albaran_bbdd, "Error", JOptionPane.ERROR_MESSAGE);
if (albaran_bbdd.equalsIgnoreCase(cif)) {
    s1.close();
    return true;
}
                }
            }
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return true;

    }

    
    
    
    
        
        public boolean ExisteOrdenPedidos(String orden_pedidos){
       

        return ExisteOrdenPedidosH(orden_pedidos);

   }
    
    
  private boolean ExisteOrdenPedidosH(String orden_pedidos) {

        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM Albaran_compras WHERE orden_pedidos=?");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, orden_pedidos);

            rs = s1.executeQuery();

            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                String albaran_bbdd = rs.getString("orden_pedidos");
                //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false

                //       JOptionPane.showMessageDialog(null, "albaran leido:"+albaran_bbdd, "Error", JOptionPane.ERROR_MESSAGE);
                if (albaran_bbdd.equalsIgnoreCase(orden_pedidos)) {
                    s1.close();
                    return true;
                }
            }
            s1.close();
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return true;

    }

  
  
  
    public ArrayList LecturaArticulos (){
       
     
        return LecturaArticulosH();
   }
  
  
  private ArrayList LecturaArticulosH() {

        ArrayList<Articulo> lista = new ArrayList<>();
        Articulo art;

        int i = 0;

        try {
            try (PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM Articulos") //Agregando parametros a la posicion correspondiente de la consulta
            ) {
                rs = s1.executeQuery();
                
                //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
                while (rs.next()) {
                    
                    art = new Articulo(rs.getString("codigo"), rs.getString("descripcion"), rs.getDouble("precio"), rs.getInt("stock"));
                    lista.add(art);
                }
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return lista;

    }
  
  

     public int StockArticulo(String codigo_articulo) {
          
            return StockArticuloH(codigo_articulo);
       
     }
    int StockArticuloH(String codigo_articulo) {

        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM `Articulos` WHERE `codigo` = ?");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, codigo_articulo);

            rs = s1.executeQuery();

            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                String codigo_bbdd = rs.getString("stock");
                //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false

                //       JOptionPane.showMessageDialog(null, "albaran leido:"+albaran_bbdd, "Error", JOptionPane.ERROR_MESSAGE);
               
                    s1.close();
                    return Integer.parseInt(codigo_bbdd);
           
            }
            s1.close();
            return 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return 0;

    }

  
  
  
  
  
  
  
  
  
  
  
  
  
     public boolean ExisteCodigoArticulo(String codigo_articulo) {
          
            return ExisteCodigoArticuloH(codigo_articulo);
       
     }
    private boolean ExisteCodigoArticuloH(String codigo_articulo) {

        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM Articulos WHERE codigo=?");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, codigo_articulo);

            rs = s1.executeQuery();

            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                String codigo_bbdd = rs.getString("codigo");
                //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false

                //       JOptionPane.showMessageDialog(null, "albaran leido:"+albaran_bbdd, "Error", JOptionPane.ERROR_MESSAGE);
                if (codigo_bbdd.equalsIgnoreCase(codigo_articulo)) {
                    s1.close();
                    return true;
                }
            }
            s1.close();
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return true;

    }

    
    
    public void ArticuloSumaStock(String codigo_articulo,int cantidad) throws SQLException {
           ArticuloSumaStockH(codigo_articulo,cantidad) ;
     }
     
    
    private void ArticuloSumaStockH(String codigo_articulo, int cantidad) throws SQLException {
        // Preparamos la consulta
        //Agregando parametros a la posicion correspondiente de la consulta
        //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
        //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta
        PreparedStatement s = this.con.prepareStatement("UPDATE `Articulos` SET `stock` = ? WHERE `codigo` = ?");               //Agregando parametros a la posicion correspondiente de la consulta
        s.setInt(1, cantidad);
        s.setString(2, codigo_articulo);
        //EJECUTANDO CONSULTA
        if (s.executeUpdate() == 0) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //CERRAMOS CONEXION
        s.close();
    }

    
    
         public void ArticuloSumaStockPrecio(String codigo_articulo,int cantidad,double precio) throws SQLException {
           ArticuloSumaStockPrecioH(codigo_articulo,cantidad,precio) ;
     }
    
    private void ArticuloSumaStockPrecioH(String codigo_articulo, int cantidad, double precio) throws SQLException {
        // Preparamos la consulta

        //Agregando parametros a la posicion correspondiente de la consulta
        //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
        //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta
        PreparedStatement s = this.con.prepareStatement("UPDATE `Articulos` SET `precio` = ?, `stock` = ? WHERE `codigo` = ?");               //Agregando parametros a la posicion correspondiente de la consulta
        s.setInt(2, cantidad);
        s.setString(3, codigo_articulo);
        s.setDouble(1, precio);
        //EJECUTANDO CONSULTA
        if (s.executeUpdate() == 0) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //CERRAMOS CONEXION
        s.close();
    }

    
     public void CrearAlbaran_compras(String cif_proveedor,String numero_albaran, LocalDate fecha_compra, String orden_pedidos) throws SQLException {
         CrearAlbaran_comprasH(cif_proveedor, numero_albaran, fecha_compra,  orden_pedidos);
     
     }
    
     private void CrearAlbaran_comprasH(String cif_proveedor, String numero_albaran, LocalDate fecha_compra, String orden_pedidos) throws SQLException {
        try ( // Preparamos la consulta
                //Agregando parametros a la posicion correspondiente de la consulta
                //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
                //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta

                PreparedStatement s = this.con.prepareStatement("INSERT INTO `Albaran_compras` (`cif_proveedor`, `numero_albaran`, `fecha_compra`, `orden_pedidos`) VALUES (?,?,?,?)"); //Agregando parametros a la posicion correspondiente de la consulta
                ) {
            String parche_fecha = String.valueOf(fecha_compra);
            s.setString(2, numero_albaran);
            s.setString(3, parche_fecha);
            s.setString(1, cif_proveedor);
            s.setString(4, orden_pedidos);

            //EJECUTANDO CONSULTA
            if (s.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
                s.close();
            }
            s.close();
            //CERRAMOS CONEXION
        }
    }
     
     
         
     public void CrearAlbaran_compras_Detalle(String numero_albaran,String codigo_articulo, String orden_pedido,int cantidad,double precio,float descuento ) throws SQLException {
         CrearAlbaran_compras_DetalleH(numero_albaran, codigo_articulo,  orden_pedido,cantidad,precio,descuento);
 
         
     }
     
    
    private void CrearAlbaran_compras_DetalleH(String numero_albaran, String codigo_articulo, String orden_pedido, int cantidad, double precio, float descuento) throws SQLException {
        try ( // Preparamos la consulta
                //Agregando parametros a la posicion correspondiente de la consulta
                //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
                //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta
                PreparedStatement s = this.con.prepareStatement("INSERT INTO `Detalle_albaran_compras` (`numero_albaran`, `codigo_articulo`, `orden_pedido`, `cantidad`, `precio`, `descuento`) VALUES (?,?,?,?,?,?)"); //Agregando parametros a la posicion correspondiente de la consulta
                ) {
            s.setString(1, numero_albaran);
            s.setString(2, codigo_articulo);
            s.setString(3, orden_pedido);
            s.setInt(4, cantidad);
            s.setDouble(5, precio);
            s.setFloat(6, descuento);
            //EJECUTANDO CONSULTA
            if (s.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
                s.close();
              
            }
            //CERRAMOS CONEXION
            s.close();
        }
    }

    
    public boolean AltaProveedor(String cif, String nombre_empresa, String direccion, String telefonos) throws SQLException {
        
        return AltaProveedorH( cif,  nombre_empresa,  direccion,  telefonos);
        
    }
        
      private boolean AltaProveedorH(String cif, String nombre_empresa, String direccion, String telefonos) throws SQLException {
        try ( // Preparamos la consulta
                //Agregando parametros a la posicion correspondiente de la consulta
                //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
                //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta
                PreparedStatement s3 = this.con.prepareStatement("INSERT INTO `Proveedores` (`cif`, `nombre_empresa`, `direccion`, `telefonos`) VALUES (?, ?, ?, ?)"); //Agregando parametros a la posicion correspondiente de la consulta
                ) {
            s3.setString(1, cif);
            s3.setString(2, nombre_empresa);
            s3.setString(3, direccion);
            s3.setString(4, telefonos);
            
            //EJECUTANDO CONSULTA
            if (s3.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
                s3.close();
                return false;
              
            }
            
              s3.close();
            return true;
            }catch (Exception e) {
                           
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
        }//Fin del try catch
            //CERRAMOS CONEXION
          
        
    }
    
    
    
    
    
  ///////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////
  //////////////////////////CONSULTAS DE VENTA///////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////
   
          public double CreditoCliente (String nif) {
              return CreditoClienteH( nif) ;
      
      }
    
    private double CreditoClienteH(String nif) {
        double credito_bbdd = 0d;
        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM Clientes WHERE nif=?");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, nif);

            rs = s1.executeQuery();

            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                credito_bbdd = rs.getDouble("credito_disponible");

            }
            s1.close();
            return credito_bbdd;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return credito_bbdd;

    }
    
    
    
         public void ArticuloRestaStock(String codigo_articulo,int nuevo_stock) throws SQLException {
              ArticuloRestaStockH(codigo_articulo,nuevo_stock);
         }
    

    private void ArticuloRestaStockH(String codigo_articulo, int nuevo_stock) throws SQLException {
        // Preparamos la consulta
try{
        //Agregando parametros a la posicion correspondiente de la consulta
        //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
        //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta
        PreparedStatement s = this.con.prepareStatement("UPDATE `Articulos` SET `stock` = ? WHERE `codigo` = ?");               //Agregando parametros a la posicion correspondiente de la consulta
        s.setString(2, codigo_articulo);
        s.setDouble(1, nuevo_stock);
        //EJECUTANDO CONSULTA
        if (s.executeUpdate() == 0) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //CERRAMOS CONEXION
        s.close();
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

    }
    
      
        public void DescontarSaldoCliente(String nif, double precio_total) {
            DescontarSaldoClienteH( nif, precio_total);
        }
        
    private void DescontarSaldoClienteH(String nif, double precio_total) {
        double credito_final = 0;
        double disponible = 0;
        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT `credito_disponible` FROM `Clientes` WHERE `nif` = ?");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, nif);

            rs = s1.executeQuery();
            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                disponible = rs.getDouble("credito_disponible");
                //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false

            }
            s1.close();

            credito_final = (disponible - precio_total);

            PreparedStatement s = this.con.prepareStatement("UPDATE `Clientes` SET `credito_disponible` = ? WHERE `nif` = ?");               //Agregando parametros a la posicion correspondiente de la consulta
            s.setString(2, nif);
            s.setDouble(1, credito_final);
            //EJECUTANDO CONSULTA
            if (s.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
                s.close();
            }
            //CERRAMOS CONEXION
            s.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

    }

    
    
            
          public float ConsultaDescuentoCliente(String nif) {
           return ConsultaDescuentoClienteH(nif) ;
       }
          
       
          private float ConsultaDescuentoClienteH(String nif) {

        Float descuento = 0f;
        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM Clientes WHERE nif=?");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, nif);

            rs = s1.executeQuery();

            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                descuento = rs.getFloat("descuento_cliente");
                //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false

            }
            s1.close();
            return descuento;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return descuento;

    }

          
          
          
          
       public boolean ExisteNIF(String nif){
               return ExisteNIFH(nif);
               
           }
          
    private boolean ExisteNIFH(String nif) {

        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM `Clientes` WHERE `nif` LIKE ? "); //Agregando parametros a la posicion correspondiente de la consulta
  s1.setString(1, nif);
          

            rs = s1.executeQuery();

            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                String nif_bbdd = rs.getString("nif");
                //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false

//           JOptionPane.showMessageDialog(null, "albaran leido:"+albaran_bbdd, "Error", JOptionPane.ERROR_MESSAGE);
                if (nif_bbdd.equalsIgnoreCase(nif)) {
                    s1.close();
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return false;

    }

    
    
    
    
    
    
             public boolean ExisteAlbaranVenta(String numero_albaran) {
            return ExisteAlbaranVentaH(numero_albaran);
            }
                        
    
    private boolean ExisteAlbaranVentaH(String numero_albaran) {

        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT * FROM Albaran_ventas WHERE numero_albaran=?");               //Agregando parametros a la posicion correspondiente de la consulta
            s1.setString(1, numero_albaran);

            rs = s1.executeQuery();

            //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
            while (rs.next()) {
                String albaran_bbdd = rs.getString("numero_albaran");
                //Si encuentra el nº albaran cevolvera true, en caso contrario devolvera false

                if (albaran_bbdd.equalsIgnoreCase(numero_albaran)) {
                    s1.close();
                    return true;
                }
            }
            s1.close();
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return true;

    }

             
       public void CrearAlbaran_ventas(String nif,String numero_albaran, LocalDate fecha_venta, String contado_credito) throws SQLException {
            CrearAlbaran_ventasH(nif,numero_albaran,fecha_venta,  contado_credito);
            }  
       
    private void CrearAlbaran_ventasH(String nif, String numero_albaran, LocalDate fecha_venta, String contado_credito) throws SQLException {
        try ( // Preparamos la consulta
                //Agregando parametros a la posicion correspondiente de la consulta
                //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
                //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta

                //        JOIN Esc_Cli ON cod_Cliente = Esc_Cli.codCli JOIN Escrituras ON Esc_Cli.codEsc = Escrituras.Codigo WHERE Escrituras.Tipo='CPVE';");//

                PreparedStatement s = this.con.prepareStatement("INSERT INTO `Albaran_ventas` (`nif_cliente`, `numero_albaran`, `fecha_venta`, `contado_credito`) VALUES (?,?,?,?)"); //Agregando parametros a la posicion correspondiente de la consulta
                ) {
            String parche_fecha = String.valueOf(fecha_venta);
            s.setString(2, numero_albaran);
            s.setString(3, parche_fecha);
            s.setString(1, nif);
            s.setString(4, contado_credito);

            //EJECUTANDO CONSULTA
            if (s.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
                s.close();
            }
            s.close();
            //CERRAMOS CONEXION
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch
    }
    
    
    
    
 public void CrearAlbaran_ventas_Detalle(String numero_albaran,String codigo_articulo, LocalDate fecha_venta,int cantidad,double precio,float descuento ) throws SQLException {
     
             CrearAlbaran_ventas_DetalleH( numero_albaran, codigo_articulo, fecha_venta, cantidad, precio, descuento );
     
         }
 
    private void CrearAlbaran_ventas_DetalleH(String numero_albaran, String codigo_articulo, LocalDate fecha_venta, int cantidad, double precio, float descuento) throws SQLException {
        try ( // Preparamos la consulta
                //Agregando parametros a la posicion correspondiente de la consulta
                //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
                //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta
                PreparedStatement sa = this.con.prepareStatement("INSERT INTO `Detalle_albaran_ventas` (`numero_albaran`, `codigo_articulo`, `fecha_venta`, `cantidad`, `precio`, `descuento`, `id`) VALUES (?,?,?,?,?,?, NULL)"); //Agregando parametros a la posicion correspondiente de la consulta
                ) {

              
            String parche_fecha = String.valueOf(fecha_venta);
            sa.setString(1, numero_albaran.toLowerCase());
            sa.setString(2, codigo_articulo);
            sa.setString(3, parche_fecha);
            sa.setInt(4, cantidad);
            sa.setDouble(5, precio);
            sa.setFloat(6, descuento);
            ///EJECUTANDO CONSULTA
            if (sa.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo CREAR EL ALBARAN VENTAS DETALLE", "Error", JOptionPane.ERROR_MESSAGE);
                sa.close();
            }
            sa.close();
            //CERRAMOS CONEXION
           } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch
    }

  
    
    
    
    
    
    
    public void DarAltaClientes(String nif, String nombre, String direccion, String telefono, double credito_disponible, float descuento_cliente) throws SQLException {
     
        DarAltaClientesH(nif, nombre, direccion, telefono, credito_disponible, descuento_cliente);
          
    }
        
    
    private void DarAltaClientesH(String nif, String nombre, String direccion, String telefono, double credito_disponible, float descuento_cliente) throws SQLException {
        // Preparamos la consulta

        //Agregando parametros a la posicion correspondiente de la consulta
        //CREAMOS Y EJECUTAMOS LA "ACTUALIZACION DEL CAMPO" (USAREMOS EL METODO "executeUpdate")
        //Montando la consulta con PREPAREDSTATEMENT y poder pasarle los parametros como parte de la consulta
        try {
            PreparedStatement s = this.con.prepareStatement("INSERT INTO `Clientes` ( `nif`, `nombre`, `direccion`, `telefono`, `credito_disponible`, `descuento_cliente`) VALUES ( ? , ? , ? , ? , ? , ? )"); //Agregando parametros a la posicion correspondiente de la consulta

            s.setString(1, nif);
            s.setString(2, nombre);
            s.setString(3, direccion);
            s.setString(4, telefono);
            s.setDouble(5, credito_disponible);
            s.setFloat(6, descuento_cliente);
            //EJECUTANDO CONSULTA
            if (s.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la Actualización, compruebe que el CODIGO existe en la BBDD", "Error", JOptionPane.ERROR_MESSAGE);
               
                //   return false;
            }
             s.close();
            //  return true;
            //CERRAMOS CONEXION
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Se produjo un error en la consulta", "Error", JOptionPane.ERROR_MESSAGE);
      
        }
     
    }  //Fin de daraltacliente


    
    
    
    ///////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    //////////////   ALBARANES      //////////////////////////////////
    ///////////////////////////////////////////////////////////////////
       
    public ArrayList<Albaran_Venta> LecturaAlbaranes (String nif, String tipo_pago)throws SQLException {
       
     
        return LecturaAlbaranesH(nif, tipo_pago);
   }
  
    
  
  private ArrayList<Albaran_Venta> LecturaAlbaranesH(String nif, String tipo_pago) throws SQLException {
            
      Albaran_Venta art;
        ArrayList<Albaran_Venta> lista = new ArrayList<>();
        

        try {
            PreparedStatement s1 = this.con.prepareStatement("(SELECT * FROM `Albaran_ventas` WHERE `nif_cliente` = ? AND `contado_credito` LIKE ? )"); //Agregando parametros a la posicion correspondiente de la consulta
                            s1.setString(1, nif);
            s1.setString(2, tipo_pago);
            
                rs = s1.executeQuery();

                //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
                while (rs.next()) {
                    
                    art = new Albaran_Venta(rs.getString("nif_cliente"), rs.getString("numero_albaran"), rs.getString("fecha_venta"), rs.getString("contado_credito"));
                            //Articulo(rs.getString("codigo"), rs.getString("descripcion"), rs.getDouble("precio"), rs.getInt("stock"));
                    lista.add(art);
                }
            
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return lista;

    }
    
  
  
  
    
       public ArrayList<Albaran> LecturaAlbaranesDetalles_credito (String nif)throws SQLException {
       
     
        return  LecturaAlbaranesDetalles_creditoH(nif);
   }
  
    
  
  private ArrayList<Albaran> LecturaAlbaranesDetalles_creditoH(String nif)throws SQLException  {

        ArrayList<Albaran> lista = new ArrayList<>();
        Albaran art;

        try {
           PreparedStatement s1 = this.con.prepareStatement("SELECT Detalle_albaran_ventas.* FROM Albaran_ventas INNER JOIN Detalle_albaran_ventas ON Albaran_ventas.numero_albaran = Detalle_albaran_ventas.numero_albaran WHERE Albaran_ventas.nif_cliente=?"); //Agregando parametros a la posicion correspondiente de la consulta
             s1.setString(1, nif);
     
                rs = s1.executeQuery();
               
                //MIENTRAS EXISTAN REGISTROS, DEVOLVERÄ LOS RESULTADOS
                  LocalDate fechaAlbaran;
            Date fechaAntigua = new Date();
         //RECOGIENDO ALBARANES SOLO DEL MES ANTERIOR AL VIGENTE
                //rECOGEMOS fECHA aCTUAL
                LocalDate fechaNueva = fechaAntigua.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               //le restamos un mes, y ahora despues haremos lo mismo pero sin restar con la fecha del albaran y solo los
               //listaremos si pertenecen al mismo mes =D
                fechaNueva=fechaNueva.minusMonths(1);
                 
         
                while (rs.next()) {
                    //Creamos objeto Albarán DETALLE
                    art = new Albaran(rs.getString("numero_albaran"), rs.getString("codigo_articulo"), rs.getString("fecha_venta"), rs.getInt("cantidad"), rs.getDouble("precio"), rs.getFloat("descuento"));
                           
                         fechaAlbaran = parse(art.getFecha_venta());
                         //SI LOS DOS MESES (QUE SERÁ EL ANTERIOR AL VIGENTE)Y AÑOS COINCINDEN, ALMACENAREMOS EL ALBARAN EN EL ARRAYLIST, PARA PASARLO A LA FACTURA 
                          if(fechaNueva.getMonth()==fechaAlbaran.getMonth()&&fechaAlbaran.getYear()==fechaNueva.getYear()){
                              //AGREGANDO OBJETO AL ARRAYLIST
                              lista.add(art);
                              
                          }else{
                           //nO HARÁ NADA
                          }   
                            
                    
                }
            
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);

        }//Fin del try catch

        return lista;

    }
    
    
    
    
    
     
       public ArrayList<Albaran> LecturaAlbaranesDetalles_contado (String numero_albaran)throws SQLException {
       
     
        return  LecturaAlbaranesDetalles_contadoH(numero_albaran);
   }
  
    
  
  private ArrayList<Albaran> LecturaAlbaranesDetalles_contadoH(String numero_albaran) throws SQLException {

        ArrayList<Albaran> lista = new ArrayList<>();
        Albaran art;

        try {
            PreparedStatement s1 = this.con.prepareStatement("SELECT Detalle_albaran_ventas.* FROM Albaran_ventas INNER JOIN Detalle_albaran_ventas ON Albaran_ventas.numero_albaran = Detalle_albaran_ventas.numero_albaran WHERE Albaran_ventas.numero_albaran=?"); //Agregando parametros a la posicion correspondiente de la consulta
               s1.setString(1,numero_albaran);
                rs = s1.executeQuery();
               
     
         
                while (rs.next()) {
                    //Creamos objeto Albarán DETALLE
                    art = new Albaran(rs.getString("numero_albaran"), rs.getString("codigo_articulo"), rs.getString("fecha_venta"), rs.getInt("cantidad"), rs.getDouble("precio"), rs.getFloat("descuento"));
                       lista.add(art);     
                     
                }
            
                return lista;
                
            
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);
            return lista;
        }//Fin del try catch

 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}//Fin de la clase
