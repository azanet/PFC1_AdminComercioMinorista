/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Articulo;
import Modelo.Articulo_Compra;
import Modelo.MetodosCompras;
import Modelo.Patrones;
import Modelo.Principal;

import Vistas.VistaCompras;
import Vistas.VistaMenu;
import com.mysql.cj.result.LocalDateTimeValueFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.currentTimeMillis;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author davidf
 */
public class ControladorCompras {

    //AGREGAMOSOBJETOS DEL MODELO, VISTA, Y OBJETOS, QUE VAYAMOS A UTILIZAR EN ESTE CONTROLADOR
    public VistaCompras vistaCompras;
    private MetodosCompras metodosCompras;//DECLARAMOS OBJETO del MODELO que contiene los METODOS | Establecemoscomo PRIVADO
   
    Patrones p;
    Matcher m;
    ArrayList<Articulo_Compra> articulos_agregados = new ArrayList<>();//Arraylist que contiene los articulos agregados por el usuario
    ArrayList<Articulo> articulos_Existentes; //Array¡List que contiene un objeto de los ARTICULOS contenido en la DDBB y sus propiedades
    String cif = "";   //CIF
    String Orden_Pedidos = "";//ORDEN PEDIDOS
    String numero_albaran_COMPRA;//Nº ALBARAN
    LocalDate fecha;//FECHA COMPRA   

    public ControladorCompras(VistaCompras vistaCompras, MetodosCompras metodos) {
        //Inicializamos los objetos de Vista y Modelo pasandole directamene el parametro, que a su vez el parametro trae los constructores inicializados del metodo MAIN(en este caso)
        this.vistaCompras = vistaCompras;
        this.metodosCompras = metodos;
        this.p = new Patrones();

        //  Inicializando BOTONES que se encuentran EN LA "VistaMenu"
        this.vistaCompras.ButAgregarArticulo.addActionListener(new OyenteAgregarArticulo());
        this.vistaCompras.ButBorrarArticulo.addActionListener(new OyenteBorrarArticulo());
        this.vistaCompras.ButDelList.addActionListener(new OyenteEliminarLista());
        this.vistaCompras.ButFinalizarVenta.addActionListener(new OyenteFinalizarVenta());
        this.vistaCompras.ButSalir.addActionListener(new OyenteSalir());

        Iniciar();
        

    }

    
    
    private void Iniciar(){
    
        boolean error = false;
         String input = "";
        do {
            input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduzca el CIF del PROVEEDOR:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);

            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronCIF.matcher(input);

                if (m.matches()) {

                    ///CONSULTA SQL DETERMINANDO SI EXISTE EL CIF/PROVEEDOR
                    if (metodosCompras.ExisteCIF(input)) {
                        this.cif = input.toLowerCase();
                        error = false;
                        this.vistaCompras.cif.setText(this.cif);
                    } else {
                        
                         int resp = JOptionPane.showConfirmDialog(vistaCompras.ButBorrarArticulo, "El PROVEEDOR no esxiste en la BBDD\n¿Desea darle de alta?", "Alerta!", JOptionPane.YES_NO_OPTION);
                            //     JOptionPane.showConfirmDialog(vistaCompras.ButBorrarArticulo, resp, "Alerta!", JOptionPane.YES_NO_OPTION);

                            switch (resp) {
                                case 1:
                                   
                                    error=true;
                                    break;

                                case 0:

                                    if (AltaProveedor(input)) {
                                        this.cif = input;
                                        vistaCompras.cif.setText(this.cif);
                                        error=false;
                                    } else {
                                        error=true;
                                    }

                                    break;
                                //FIN DEL ALTA ARTICULO

                            }//Fin del Switch


//JOptionPane.showMessageDialog(this.vistaCompras.ButBorrarArticulo, "El CIF introducido NO EXISTE en la BBDD", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                       
                    }

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese un CIF Válido EJEMPLO:\n CIF: a1234567b", "Entrada de DATOS INCORRECTA", JOptionPane.WARNING_MESSAGE);

                    error = true;
                }//Fin del if-else                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Pulsó CANCELAR\nSe establecerá un CIF de Testeo\n", "ATENCIÖN", JOptionPane.WARNING_MESSAGE);
                this.cif = "a1234567b";
                error = false;
                vistaCompras.cif.setText(this.cif);
            }

        } while (error == true);

        do {
            input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduzca la ORDEN DE PEDIDOS:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);

            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronOrdenPedido.matcher(input);

                if (m.matches()) {

                    ///CONSULTA SQL DETERMINANDO SI EXISTE EL ORDEN PEDIDOS
                    if (!metodosCompras.ExisteOrdenPedidos(input)) {
                        this.Orden_Pedidos = input;
                        error = false;
                        vistaCompras.orden_pedidos.setText(this.Orden_Pedidos);
                    } else {
                        JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "El ORDEN DE PEDIDOS YA ESXISTE en la BBDD", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese un ORDEN de pedidos Válido MAX. 100 digitos", "Entrada de DATOS INCORRECTA", JOptionPane.WARNING_MESSAGE);
                    error = true;
                }//Fin del if-else                

            } catch (Exception e) {
                error = false;
                this.Orden_Pedidos = String.valueOf(currentTimeMillis());
                vistaCompras.orden_pedidos.setText(this.Orden_Pedidos);
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Pulsó CANCELAR la Operacion\nSe establecerá un OrdenPedidos de testeo", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);

            }
        } while (error == true);

        do {
            input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduzca el Nº de ALBARÁN para la COMPRA:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);

            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronCodigoArticulo.matcher(input);

                if (m.matches()) {

                    ///CONSULTA SQL DETERMINANDO SI EXISTE EL CIF/PROVEEDOR
                    if (!metodosCompras.ExisteAlbaranCompra(input)) {
                        this.numero_albaran_COMPRA = input;
                        vistaCompras.numero_albaran.setText(this.numero_albaran_COMPRA);
                        error = false;
                    } else {
                        JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "El NºALBARAN introducido YA EXISTE en la BBDD\nIntroduca otro diferente MAX.9 digitos", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                        error = true;

                    }

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese un Nº de ALBARÁN Válido", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }//Fin del if-else                

            } catch (Exception e) {
                error = false;
                String b = String.valueOf(currentTimeMillis());
                b = b.substring(4, 12);
                this.numero_albaran_COMPRA = b;
                vistaCompras.numero_albaran.setText(this.numero_albaran_COMPRA);
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Seleccionó CANCELAR\nSe Establecerá un NºAlbarán de Testeo", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);

            }
        } while (error == true);

        do {
            input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduzca FECHA\nSe espera formato AAAA-MM-DD\nSe establecerá la fecha de hoy  en caso de CANCELAR la operación", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);

            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronFecha.matcher(input);

                if (m.matches()) {
                    try {
                        this.fecha = LocalDate.parse(input);
                        this.vistaCompras.fecha_compra.setText(fecha.toString());
                        error = false;

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "La FECHA es INCORRECTA.\nSe espera formato AAAA-MM-DD\nSe establecerá la fecha de hoy como ejemplo", "Error", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "La FECHA es INCORRECTA.\nSe espera formato AAAA-MM-DD\nSe establecerá la fecha de hoy como ejemplo", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);

                    error = true;
                }//Fin del if-else                

            } catch (Exception e) {
                error = false;
                this.fecha = LocalDate.now();
                this.vistaCompras.fecha_compra.setText(this.fecha.toString());
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Introducción de FECHA CANCELADA.\nSE ESTABLECERÁ LA FECHA DE HOY", "Error", JOptionPane.WARNING_MESSAGE);

            }
        } while (error == true);

    
    
    Cabecera();
    }
    
    
    
    
    
    
    private void Cabecera() {
        vistaCompras.TextArea_agregados.setText("");
        vistaCompras.TextArea_agregados.append("\nARTICULOS AGREGADOS:\n");
        articulos_Existentes = metodosCompras.LecturaArticulos();
        vistaCompras.TextArea_existentes.setText("");
        vistaCompras.TextArea_existentes.append("Codigo | Descripcion | Stock | Precio |\n");
        
        for (Articulo obj : articulos_Existentes) {
            vistaCompras.TextArea_existentes.append(String.format("%-8s|    %-20s|    %-6d|    %-,2f\n", obj.getCodigo_articulo(), obj.getDescipcion(), obj.getCantidad(), obj.getPrecio()));
        }

        for (Articulo_Compra obj : articulos_agregados) {
            vistaCompras.TextArea_agregados.append("Cod.Articulo: " + obj.getCodigo_articulo() + " cantidad:" + obj.getCantidad() + "\n");
        }

                 vistaCompras.codigo_articulo.setText("");
                vistaCompras.orden_pedido.setText("");
                vistaCompras.cantidad.setText("");
                vistaCompras.precio.setText("");
                vistaCompras.descuento.setText("");
    }//Fin cabecera

    class OyenteAgregarArticulo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            int error = 0;
            String input;
            String codigo_articulo = "";
            String orden_pedido = "";//ORDEN PEDIDO

            int cantidad = 0;
            double precio = 0;
            float descuento = 0;//Descuento

            input = vistaCompras.cantidad.getText();
            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronStock.matcher(input);

                if (m.matches()) {
                    cantidad = Integer.parseInt(input);

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "CANTIDAD Incorrecta introduce un Número ENTERO", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error++;
                    vistaCompras.cantidad.setText("");
                }//Fin del if-else                

            } catch (Exception e) {
                error++;
                vistaCompras.cantidad.setText("");
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error, ingrese un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            }

            input = vistaCompras.precio.getText();
            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronPrecio.matcher(input);

                if (m.matches()) {
                    precio = Double.parseDouble(input);

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "PRECIO incorrecto, Ingrese un Número DECIMAL", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error++;
                    vistaCompras.precio.setText("");
                }//Fin del if-else                

            } catch (Exception e) {
                error++;
                vistaCompras.precio.setText("");
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }

            input = vistaCompras.orden_pedido.getText();
            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronOrdenPedido.matcher(input);

                if (m.matches()) {
                    orden_pedido = input;

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "ORDEN PEDIDO Incorrecto, Introduzca un ENTERO max 100 digitos", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    vistaCompras.orden_pedido.setText("");
                    error++;
                }//Fin del if-else                

            } catch (Exception e) {
                error++;
                vistaCompras.orden_pedido.setText("");
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }

            input = vistaCompras.descuento.getText();
            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronDescuento.matcher(input);

                if (m.matches()) {
                    descuento = Float.parseFloat(input);

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "DESCUENTO Incorrector, Ingrese un Número DECIMAL", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    vistaCompras.descuento.setText("");
                    error++;
                }//Fin del if-else                

            } catch (Exception e) {
                vistaCompras.descuento.setText("");
                error++;
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }

            input = vistaCompras.codigo_articulo.getText();
            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronCodigoArticulo.matcher(input);

                if (m.matches()) {
                    if (metodosCompras.ExisteCodigoArticulo(input)) {
                        codigo_articulo = input;

                    } else {
                        if (error == 0) {
                            int resp = JOptionPane.showConfirmDialog(vistaCompras.ButBorrarArticulo, "El Artículo no esxiste en la BBDD\n¿Desea darle de alta?", "Alerta!", JOptionPane.YES_NO_OPTION);
                            //     JOptionPane.showConfirmDialog(vistaCompras.ButBorrarArticulo, resp, "Alerta!", JOptionPane.YES_NO_OPTION);

                            switch (resp) {
                                case 1:
                                    vistaCompras.codigo_articulo.setText("");
                                    error++;
                                    break;

                                case 0:

                                    if (AltaArticulo(input, precio)) {
                                        codigo_articulo = input;
                                    } else {
                                        vistaCompras.codigo_articulo.setText("");
                                        error++;
                                    }

                                    break;
                                //FIN DEL ALTA ARTICULO

                            }//Fin del Switch
                        } else {

                            error++;
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "CODIGO ARTÍCULO Incorrecto, Introduzca un ENTERO max 9 digitos", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    vistaCompras.codigo_articulo.setText("");
                    error++;
                }//Fin del if-else                

            } catch (Exception e) {
                error++;
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }

            ////////////////////////
            ///     AGREGANDO ARTICULO a la LISTA "articulos_agregados";
            if (error == 0) {
                Articulo_Compra add_art = new Articulo_Compra(numero_albaran_COMPRA, Orden_Pedidos, orden_pedido, cif, codigo_articulo, cantidad, precio, descuento, fecha);
                articulos_agregados.add(add_art);
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Articulo añadido a la LISTA cd COMPRA", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                vistaCompras.codigo_articulo.setText("");
                vistaCompras.orden_pedido.setText("");

                vistaCompras.cantidad.setText("");
                vistaCompras.precio.setText("");
                vistaCompras.descuento.setText("");
            } else {
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "El ARTICULO NO FUE AÑADIDO a la LISTA", "ERROR", JOptionPane.WARNING_MESSAGE);
            }

            Cabecera();
        }//Fin ActionPerformed
    }//FIn OyenteAlta

    //_________________________________________________________________________
    class OyenteBorrarArticulo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
             String input = "";
            try {
                boolean remove = false;
                input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduce el CODIGO ARTICULO de artículo a ELIMINAR de la lista", "Introduzca Datos Solicitados", JOptionPane.WARNING_MESSAGE);
                m = p.patronCodigoArticulo.matcher(input);

                if (m.matches()) {

                    //ITERADOR TRADICIONAL para la Coleccion "LIST"
                    Iterator<Articulo_Compra> it = articulos_agregados.iterator(); //Creamos el iterador del Objeto a Iterar
//Iteraremos la lista mientras existan datos.
                    while (it.hasNext()) {

                        Articulo_Compra obj = it.next(); //Cogemos el "siguiente" elemento

                        //Ejemplo de condicion X, en la que si se cumple SE ELIMINA UN OBJETO DE LA LISTA
                        if (obj.getCodigo_articulo().equalsIgnoreCase(input)) {

                            it.remove(); //Eliminando el OBJETO de la Lista_ArrayList
                            remove = true;
                        }//Fin del if

                    }//Fin del While

                    if (remove == true) {
                        JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "El ARTÍCULO fue removido con EXITO de la LISTA", "ARTICULO ELIMINADO", JOptionPane.INFORMATION_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "El CODIGO ARTICULO introducido NO EXISTE en la Lista", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Introduzca un CODIGO ARTICULO válido", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception e) {

            }
            vistaCompras.codigo_articulo.setText("");
            Cabecera();
        }//Fin ActionPerformed
    }//FIn OyenteBaja

//______________________________________________________________________________    
    //_________________________________________________________________________
    class OyenteEliminarLista implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
            articulos_agregados = new ArrayList<>();
            Cabecera();
        }//Fin ActionPerformed
    }//FIn OyenteBaja

//______________________________________________________________________________    
    class OyenteFinalizarVenta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (!articulos_agregados.isEmpty()) {

                try {
                    //INSERTAMOS UUUNAAAA SOLLAAA FACTURA_COMPRA
                    metodosCompras.CrearAlbaran_compras(cif, numero_albaran_COMPRA, fecha, Orden_Pedidos);

             //       JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "ALBARAN VENTA realizado con ÉXITO", "COMPRA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);
     for (Articulo_Compra obj : articulos_agregados) {
                    
                for (Articulo obj_exis : articulos_Existentes) {
                   
                            if (obj_exis.getCodigo_articulo().equalsIgnoreCase(obj.getCodigo_articulo())) {

                                metodosCompras.CrearAlbaran_compras_Detalle(numero_albaran_COMPRA, obj.getCodigo_articulo(), obj.getOrden_pedido(), obj.getCantidad(), obj.getPrecio(), obj.getDescuento());
            //                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "ALB. VENTA DETALLES realizado con ÉXITO", "COMPRA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);

                                int cantidad_final = metodosCompras.StockArticulo(obj.getCodigo_articulo()) + obj.getCantidad();

                                if (obj_exis.getPrecio() < obj.getPrecio()) {

                                    //REALIZAMOS LA CONSULTA CAMBIANDO EL PRECIO y INSERTANDO LA CANTIDAD FINAL
                                    metodosCompras.ArticuloSumaStockPrecio(obj.getCodigo_articulo(), cantidad_final, obj.getPrecio());

                                } else {

                                    //Realizamos la consulta SIN CAMBIAR EL PRECIO,INSERTANDO LA CANTIDAD FINAL
                                    metodosCompras.ArticuloSumaStock(obj.getCodigo_articulo(), cantidad_final);

                                }//Fin del if-else

                            }//Fin del if que determina el si los codigos son iguales

                        }//Find el fore anidado

                    }//Fin del fore
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "COMPRA realizada con ÉXITO", "COMPRA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);

                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se Reiniciará y volverá a SOLICITAR NUEVOS DATOS\nSe procederá a solicitar otro NIF para continuar", "Error", JOptionPane.INFORMATION_MESSAGE);
                     int resp = JOptionPane.showConfirmDialog(vistaCompras.ButBorrarArticulo, "¿Desea SALIR de COMPRAS?\n\nSi pulsa NO volverán\na solicitarse NUEVOS DATOS\n\n¿Desea SALIR ahora??", "Alerta!", JOptionPane.YES_NO_OPTION);
                            //     JOptionPane.showConfirmDialog(vistaCompras.ButBorrarArticulo, resp, "Alerta!", JOptionPane.YES_NO_OPTION);

                            switch (resp) {
                                case 0:
                                    Salir();
                                    break;

                                case 1:
                                    articulos_agregados = new ArrayList<>();
                                    Iniciar();
                                break;
                                //FIN DEL ALTA ARTICULO

                            }//Fin del Switch
                    
                    

                } catch (SQLException ex) {
                    
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                    Logger.getLogger(ControladorCompras.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "No existen ARTICULOS en la Lista", "Error", JOptionPane.ERROR_MESSAGE);
            Cabecera();
            }

           

        }//Fin ActionPerformed
    }//FIn OyenteConsulta

//_____________________________________________________________________________-
    class OyenteSalir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
            VistaMenu menu = new VistaMenu();
            Principal metodos = new Principal();
            ControladorMenu controlMenu;
            //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
            vistaCompras.setVisible(false);
            controlMenu = new ControladorMenu(menu, metodos);

        }//Fin ActionPerformed
    }//FIn OyenteSalir

    private boolean AltaArticulo(String codigo_ar, double precio_alta) {

        ////METODOS PARA DAR DE ALTA AL ARTICULO!!!!
        String descripcion = "";
        String respuesta = "";
        int stock_medio = 0;
        int stock_minimo = 0;
        boolean error_obj = false;
        boolean excepcion = false;
         String input = "";
        do {
            input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduce el NOMBRE/descripción del NUEVO Articulo:", "Introduzca Datos Solicitados", JOptionPane.WARNING_MESSAGE);
            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronDescripcion.matcher(input);

                if (m.matches()) {
                    descripcion = input;
                    error_obj = false;

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese un NOMBRE/descripción para el artículo", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error_obj = true;
                }//Fin del if-else                

            } catch (Exception e) {
                error_obj = false;
                excepcion = true;
                //  JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (error_obj == true);

        if (excepcion == false) {
            JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha asignado como STOCK, la CANTIDAD [0]", "Introduzca Datos Solicitados", JOptionPane.WARNING_MESSAGE);

            do {
                input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduce el \"stock Medio\"del NUEVO Articulo:", "Introduzca Datos Solicitados", JOptionPane.WARNING_MESSAGE);
                try {
                    //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                    m = p.patronStock.matcher(input);

                    if (m.matches()) {
                        stock_medio = Integer.parseInt(input);
                        error_obj = false;

                    } else {
                        JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese un Número ENTERO", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                        error_obj = true;
                    }//Fin del if-else                

                } catch (Exception e) {
                    error_obj = false;
                    excepcion = true;
                    //      JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error, ingrese un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (error_obj == true);

            if (excepcion == false) {

                do {
                    input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduce el \"stock Mínimo\" del NUEVO Articulo:", "Introduzca Datos Solicitados", JOptionPane.WARNING_MESSAGE);
                    try {
                        //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                        m = p.patronStock.matcher(input);

                        if (m.matches()) {
                            stock_minimo = Integer.parseInt(input);
                            error_obj = false;

                        } else {
                            JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese un Número ENTERO", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                            error_obj = true;
                        }//Fin del if-else                

                    } catch (Exception e) {
                        error_obj = false;
                        excepcion = true;
                        //      JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error, ingrese un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (error_obj == true);

                //Damos de alta al ARTICULO si todo está bien
            }
        }
        if (excepcion == false) {
            respuesta = metodosCompras.AltaArticulo(codigo_ar, descripcion, precio_alta, 0, stock_medio, stock_minimo);
            JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, respuesta, "ALTA CORRECTA", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } else {
            vistaCompras.codigo_articulo.setText("");
            JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "El ARTICULO NO FUE DADO DE ALTA", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
    
    
    

    
      private boolean AltaProveedor(String cif_alta) throws SQLException {

        ////METODOS PARA DAR DE ALTA AL ARTICULO!!!!
        String nombre_empresa = "";
        String direccion = "";
        String telefonos = "";
        boolean error_obj = false;
        boolean excepcion = false;
         String input = "";
        do {
            input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduce el NOMBRE para la empresa:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);
            try {
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronDireccion.matcher(input);

                if (m.matches()) {
                    nombre_empresa = input;
                    error_obj = false;

                } else {
                    JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese un NOMBRE valido para la empresa", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error_obj = true;
                }//Fin del if-else                

            } catch (Exception e) {
                error_obj = false;
                excepcion = true;
                //  JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (error_obj == true);

        if (excepcion == false) {
        
            do {
                input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduzca DIRECCIÓN de la empresa:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);
                try {
                    //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                    m = p.patronDireccion.matcher(input);

                    if (m.matches()) {
                        direccion = input;
                        error_obj = false;

                    } else {
                        JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese una dirección válida", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                        error_obj = true;
                    }//Fin del if-else                

                } catch (Exception e) {
                    error_obj = false;
                    excepcion = true;
                    //      JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error, ingrese un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (error_obj == true);

            if (excepcion == false) {

                do {
                    input = JOptionPane.showInputDialog(vistaCompras.ButBorrarArticulo, "Introduce el TELÉFONO de la empresa", "Introduzca Datos Solicitados", JOptionPane.WARNING_MESSAGE);
                    try {
                        //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                        m = p.patronTelefono.matcher(input);

                        if (m.matches()) {
                            telefonos = input;
                            error_obj = false;

                        } else {
                            JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Ingrese un telefono válido", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                            error_obj = true;
                        }//Fin del if-else                

                    } catch (Exception e) {
                        error_obj = false;
                        excepcion = true;
                        //      JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "Se ha producido un error, ingrese un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (error_obj == true);

                //Damos de alta al ARTICULO si todo está bien
            }
        }
        if (excepcion == false) {
            
            if(metodosCompras.AltaProveedor(cif_alta, nombre_empresa, direccion, telefonos)){
                JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "El PROVEEDOR fue dado de alta \n\tSatisfactoriamente.", "ALTA CORRECTA", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }else{
                return false;
            }
                        
          
        } else {
            vistaCompras.codigo_articulo.setText("");
            JOptionPane.showMessageDialog(vistaCompras.ButBorrarArticulo, "SE PRODUJO UN ERROR DE ALTA", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
    
    
    
    
    private void Salir(){
     VistaMenu menu = new VistaMenu();
            Principal metodos = new Principal();
            ControladorMenu controlMenu;
            //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
            vistaCompras.setVisible(false);
            controlMenu = new ControladorMenu(menu, metodos);
    }
    
    

}//Fin del Controlador Menu

