/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Articulo;
import Modelo.Articulo_Venta;
import Modelo.ConsultasSQL;

import Modelo.MetodosVentas;
import Modelo.Patrones;
import Modelo.Principal;
import Vistas.VistaMenu;

import static java.lang.System.currentTimeMillis;
import Vistas.VistaVentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;

/**
 *
 * @author davidf
 */
public class ControladorVentas {
    //AGREGAMOSOBJETOS DEL MODELO, VISTA, Y OBJETOS, QUE VAYAMOS A UTILIZAR EN ESTE CONTROLADOR

  
    public VistaVentas vistaVentas;//Declaramos un oObjeto de la VISTA correspondiente (en este caSO "VistaMenu") | Establecemos como PUBLICO
    private MetodosVentas metodosVentas;//DECLARAMOS OBJETO del MODELO que contiene los METODOS | Establecemoscomo PRIVADO
    String input = "";
    Patrones p;
    Matcher m;
    ArrayList<Articulo_Venta> articulos_agregados = new ArrayList<>();//Arraylist que contiene los articulos agregados por el usuario
    ArrayList<Articulo> articulos_ExistentesV; //Array¡List que contiene un objeto de los ARTICULOS contenido en la DDBB y sus propiedades
    String nif;
    String tipo_pago = "";
    String cod_articulo = "";
    String cantidad = "";
    String num_albaran_VENTA = "";
    LocalDate fecha;
    float descuento_cliente = 0f;
    double precio_total = 0d;

    public ControladorVentas(VistaVentas vistaVentas, MetodosVentas metodosVentas) {
        this.vistaVentas = vistaVentas;
        this.metodosVentas = metodosVentas;
        this.p = new Patrones();

        //Inicializando BOTONES que se encuentran EN LA "VistaMenu"
        this.vistaVentas.ButAgregarArticulo.addActionListener(new OyenteAgregarArticulo());
        this.vistaVentas.ButBorrarArticulo.addActionListener(new OyenteBorrarArticulo());
        this.vistaVentas.ButDelList.addActionListener(new OyenteEliminarLista());
        this.vistaVentas.ButFinalizarVenta.addActionListener(new OyenteFinalizarVenta());
        this.vistaVentas.ButSalir.addActionListener(new OyenteSalir());
        Iniciar();

    }//Fin del constructor

    private void Iniciar() {
        boolean error = false;
        do {
            try {

                input = JOptionPane.showInputDialog(this.vistaVentas.ButBorrarArticulo, "Introduzca el NIF del CLIENTE:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);
                input =input.toLowerCase();
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronNIF.matcher(input);

                if (m.matches()) {
                    if (metodosVentas.ExisteNIF(input)) {
                        ///CONSULTA SQL DETERMINANDO SI EXISTE EL CIF/PROVEEDOR
                        this.nif = input;
                        error = false;
                        this.vistaVentas.nif.setText(this.nif);

                    } else {

                        JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "El CLIENTE no está dado de alta\nSe procederá a darle de alta.\n\nINTRODUZCA LOS DATOS QUE SE SOLICITEN.", "CLIENTE NO EXISTE", JOptionPane.WARNING_MESSAGE);
                        if (AltaCliente(input)) {
                            this.nif = input;
                            this.vistaVentas.nif.setText(this.nif);
                            error = false;
                        } else {
                            throw new Exception();
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "NIF incorrecto, no concuerda con el patron", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);

                    error = true;
                }//Fin del if-else                

            } catch (Exception e) {
                error = false;
                this.nif = "00000000A";
                JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "\tERROR\nInsertando DNI de Testeo\n00000000A", "CANCELAR", JOptionPane.WARNING_MESSAGE);
                this.vistaVentas.nif.setText(this.nif);
            }
        } while (error == true);

        do {
            try {
                input = JOptionPane.showInputDialog(this.vistaVentas.ButBorrarArticulo, "Introduzca el Nº de ALBARÁN para la VENTA\nSi PULSA CANCELAR\nse Generará un Nª Automaticamente:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);

                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronCodigoArticulo.matcher(input);

                if (m.matches()) {

                    ///CONSULTA SQL DETERMINANDO SI EXISTE EL CIF/PROVEEDOR
                    if (!this.metodosVentas.ExisteAlbaranVenta(input)) {
                        this.num_albaran_VENTA = input;
                        this.vistaVentas.numero_albaran.setText(num_albaran_VENTA);
                        error = false;
                    } else {
                        JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "El NºALBARAN introducido YA EXISTE en la BBDD\nIntroduca otro diferente MAX.9 digitos", "ENTRADA EXISTENTE", JOptionPane.ERROR_MESSAGE);
                        error = true;

                    }

                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Ingrese un Nº de ALBARÁN Válido", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }//Fin del if-else                

            } catch (Exception e) {
                error = false;

                String b = String.valueOf(currentTimeMillis());
                b = b.substring(4, 12);
                this.num_albaran_VENTA = b;
                this.vistaVentas.numero_albaran.setText(num_albaran_VENTA);
                JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Se Establecerá un NºAlbarán aleatorio", "CANCELAR entrada DATOS", JOptionPane.WARNING_MESSAGE);

            }
        } while (error == true);

        this.fecha = LocalDate.now();
        this.vistaVentas.fecha_venta.setText(this.fecha.toString());
        JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "\nSE ESTABLECERÁ LA FECHA DE HOY\n como FECHA DE VENTA", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);

        //CAPTURAMOS EL DESCUENTO DEL CLIENTE
        this.descuento_cliente = metodosVentas.ConsultaDescuentoCliente(this.nif);
        JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Se ha obtenido el descuento de cliente [" + descuento_cliente + "%]", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        Cabecera1();

    }

    private void Cabecera1() {
       
        vistaVentas.TextArea_agregados.setText("");
        vistaVentas.TextArea_agregados.append("\nCliente: " + nif + " | Credito Cliente:" +metodosVentas.CreditoCliente(nif)+" | Descuento Cliente:" + descuento_cliente + "%\n_______________________________________________________\n\nARTICULOS AGREGADOS:\n");
        articulos_ExistentesV = metodosVentas.LecturaArticulos();
        vistaVentas.TextArea_existentes.setText("");
        vistaVentas.TextArea_existentes.append("Codigo | Descripcion | Stock | Precio |\n");
        precio_total = 0;
        for (Articulo obj : articulos_ExistentesV) {
            vistaVentas.TextArea_existentes.append(String.format("%-8s|    %-20s|    %-6d|    %-,2f\n", obj.getCodigo_articulo(), obj.getDescipcion(), obj.getCantidad(), obj.getPrecio()));

            for (Articulo_Venta obj_agre : articulos_agregados) {
                if (obj.getCodigo_articulo().equalsIgnoreCase(obj_agre.getCod_articulo())) {
                    precio_total += (obj.getPrecio() * obj_agre.getCantidad());
                }
            }
        }
        
        for (Articulo_Venta obj_agre : articulos_agregados) {
            vistaVentas.TextArea_agregados.append("Cod.Articulo: " + obj_agre.getCod_articulo() + " cantidad:" + obj_agre.getCantidad() + "\n");

        }

        /*  for (Articulo_Venta obj : articulos_agregados) {
            vistaVentas.TextArea_agregados.append("Cod.Articulo: " + obj.getCod_articulo() + " cantidad:" + obj.getCantidad() + "\n");
            
        }
         */
        vistaVentas.TextArea_agregados.append("\n_______________________________________________________\nPRECIO TOTAL VENTA:\t\t" + precio_total + "\n\nPRECIO TOTAL VENTA + DESCUENTO:\t" + (precio_total-((precio_total * descuento_cliente) / 100)) + "\n");
        vistaVentas.codigo_articulo.setText("");
        vistaVentas.cantidad.setText("");
    }//Fin cabecera

    class OyenteAgregarArticulo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean error = false;

            try {
                boolean existe = false;
                int stock_bbdd = 0;
                int stock_sumados = 0;
                int stocK_deseado_cli = 0;

                cantidad = (vistaVentas.cantidad.getText().trim());

//VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronStock.matcher(cantidad);

                if (m.matches()) {
                    stocK_deseado_cli = Integer.parseInt(cantidad);
                    existe = true;
                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Entrada NO válida\nCANTIDAD\n", "ENTRADA NO VÁLIDA", JOptionPane.ERROR_MESSAGE);
                    vistaVentas.cantidad.setText("");
                    
                }//Fin del if-else                

                if (existe == true) {

                    cod_articulo = (vistaVentas.codigo_articulo.getText()).trim();
                    m = p.patronCodigoArticulo.matcher(cod_articulo);

                    if (m.matches()) {

                        for (Articulo obj_exis : articulos_ExistentesV) {
                            if (obj_exis.getCodigo_articulo().equalsIgnoreCase(cod_articulo)) {
                                stock_bbdd = obj_exis.getCantidad();
                                existe = true;
                            }//Fin del if que determina el si los codigos son iguales

                        }//Fin el Fore1

                        if (existe == true) {
                            for (Articulo_Venta obj : articulos_agregados) {
                                if (obj.getCod_articulo().equalsIgnoreCase(cod_articulo)) {
                                    stock_sumados += obj.getCantidad();
                                }//Fin del if que determina el si los codigos son iguales

                            }//Fin el Fore1
                        }
                        stock_sumados += stocK_deseado_cli;

                        if (stock_bbdd >= stock_sumados) {
                            //Se guarda el objeto en ARICULO_VENTA y se ALMACENA EN EL ARRRAY LIST

                            Articulo_Venta articulo_new = new Articulo_Venta(cod_articulo, stocK_deseado_cli);

                            articulos_agregados.add(articulo_new);

                            Cabecera1();

                        } else {
                            JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "NO HAY STOCK SUFICIENTE\nPARA LA CANTIDAD \nSOLICITADA\n\nNO SE AGREGARÄ A LA LISTA", "STOCK INSUFICIENTE", JOptionPane.ERROR_MESSAGE);

                        }

                    } else {
                        JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Entrada NO válida\nCÓDIGO ARTÍCULO\n", "ENTRADA NO VÁLIDA", JOptionPane.ERROR_MESSAGE);
                        vistaVentas.codigo_articulo.setText("");
                        error = false;
                    }//Fin del if-else        

                }

            } catch (Exception e) {
                error = false;
                JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "No se agregará el ARTICULO a la LISTA", "CANCELANDO OPERACIÓN", JOptionPane.WARNING_MESSAGE);

            }

        }//Fin ActionPerformed
    }//FIn OyenteAlta

    //_________________________________________________________________________
    class OyenteBorrarArticulo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!

            try {
                boolean remove = false;
                input = JOptionPane.showInputDialog(vistaVentas.ButBorrarArticulo, "Introduce el CODIGO ARTICULO de artículo a ELIMINAR de la lista", "Introduzca Datos Solicitados", JOptionPane.WARNING_MESSAGE);
                m = p.patronCodigoArticulo.matcher(input);

                if (m.matches()) {

                    //ITERADOR TRADICIONAL para la Coleccion "LIST"
                    Iterator<Articulo_Venta> it = articulos_agregados.iterator(); //Creamos el iterador del Objeto a Iterar
//Iteraremos la lista mientras existan datos.
                    while (it.hasNext()) {

                        Articulo_Venta obj = it.next(); //Cogemos el "siguiente" elemento

                        //Ejemplo de condicion X, en la que si se cumple SE ELIMINA UN OBJETO DE LA LISTA
                        if (obj.getCod_articulo().equalsIgnoreCase(input)) {

                            it.remove(); //Eliminando el OBJETO de la Lista_ArrayList
                            remove = true;
                        }//Fin del if

                    }//Fin del While

                    if (remove == true) {
                        JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "El ARTÍCULO fue removido con EXITO de la LISTA", "ARTICULO ELIMINADO", JOptionPane.INFORMATION_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "El CODIGO ARTICULO introducido NO EXISTE en la Lista", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Introduzca un CODIGO ARTICULO válido", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception e) {

            }

            Cabecera1();

        }//Fin ActionPerformed
    }//FIn OyenteBaja

//______________________________________________________________________________    
    //_________________________________________________________________________
    class OyenteEliminarLista implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!

            articulos_agregados = new ArrayList<>();
            Cabecera1();

        }//Fin ActionPerformed
    }//FIn OyenteBaja

//______________________________________________________________________________    
    class OyenteFinalizarVenta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            double precio_fin=0;
            int error = 0;

            if (!articulos_agregados.isEmpty()) {
                try {
                    String[] options = {"credito", "contado"};
                    int seleccion1 = JOptionPane.showOptionDialog(vistaVentas.ButBorrarArticulo, "Seleccione Método de PAGO", "Facturación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, vistaVentas.ButBorrarArticulo);

                    switch (seleccion1) {

                        case 0:
                            JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Opcion CREDITO elegida", "COMPRA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);
                            tipo_pago = "credito";

                            //Sumando precio total, para comprobar si el cliente tiene fondo en la cuentareturn false;
                            for (Articulo_Venta obj : articulos_agregados) {

                                for (Articulo obj_exis : articulos_ExistentesV) {

                                    if (obj_exis.getCodigo_articulo().equalsIgnoreCase(obj.getCod_articulo())) {

                                        precio_fin += (obj_exis.getPrecio() * obj.getCantidad());
                                    }//Fin del IF
                                }
                            }//Fin del for EXterno
                            precio_fin=(precio_fin-((precio_fin*descuento_cliente)/100));
                            if ( precio_fin<= metodosVentas.CreditoCliente(nif)) {

                            } else {
                                JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "El CLIENTE NO Dispone de saldo suficiente", "Error", JOptionPane.ERROR_MESSAGE);
                                error++;
                            }
                            break;

                        case 1:
                            JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Opcion CONTADO elegida", "FINALIZANDO VENTA", JOptionPane.INFORMATION_MESSAGE);
                            tipo_pago = "contado";
                            break;

                    }//Fin del Switch

                    if (error == 0) {

                        ////////////////////////////////CREANDO EL ALBARAN VENTAAS!!!, ESTE SOLO SE CREARA UNA VEZ
                        metodosVentas.CrearAlbaran_ventas(nif, num_albaran_VENTA, fecha, tipo_pago);
             //           JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "FACTURA VENTArealizada con ÉXITO", "COMPRA FINALIZADA", JOptionPane.ERROR_MESSAGE);
                        ///////////      

                        for (Articulo_Venta obj : articulos_agregados) {
                            

                            for (Articulo obj_exis : articulos_ExistentesV) {

                                if (obj_exis.getCodigo_articulo().equalsIgnoreCase(obj.getCod_articulo())) {

/////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////CREAMOS UNA FACTURA_DETALLE COMPRA POR CADA VEZ AQUI
                                        
                                      
                                    metodosVentas.CrearAlbaran_ventas_Detalle(num_albaran_VENTA, obj_exis.getCodigo_articulo(), fecha, obj.getCantidad(), obj_exis.getPrecio(), descuento_cliente);
           //                         JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "FACTURA DETALLErealizada con ÉXITO", "COMPRA FINALIZADA", JOptionPane.ERROR_MESSAGE);
                                int nuevo_stock =0;
                                    //RESTAMOS EL STOCK PARA  CORREGIRLO EN LA BBDD
                                    nuevo_stock = (obj_exis.getCantidad()-obj.getCantidad());

                                    ////CONSULTA DESCONTANDO EL STOCK
                                    metodosVentas.ArticuloRestaStock(obj.getCod_articulo(), nuevo_stock);

                                }//Fin del if que determina el si los codigos son iguales

                            }//Find el fore anidado

                        }//Fin el Fore1

                        if (tipo_pago.equalsIgnoreCase("credito")) {

                            //CONSULTA DESCONTANDO SALDO AL CLIENTE SI ES DE CREDITO
                            /// DESCONTANDO EL TOTAL DEL SALDO PAGADO
                            metodosVentas.DescontarSaldoCliente(nif, precio_fin);
    
                        }//Fin de cobrando segun tipo de pago

                        
                        
                        
                        
                        
                        
                        JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "FACTURACIÓN realizada con ÉXITO", "VENTA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);
                        int resp = JOptionPane.showConfirmDialog(vistaVentas.ButBorrarArticulo, "¿Desea SALIR de COMPRAS?\n\nSi pulsa NO volverán\na solicitarse NUEVOS DATOS\n\n¿Desea SALIR ahora??", "Alerta!", JOptionPane.YES_NO_OPTION);
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

                        articulos_agregados = new ArrayList<>();
                    }//FIN DEL IF==ERROR=0

                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
                    articulos_agregados = new ArrayList<>();
                }
            } else {
                JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "No existen ARTICULOS en la Lista", "Error", JOptionPane.ERROR_MESSAGE);
                Cabecera1();
            }

        }//Fin ActionPerformed
    }//FIn OyenteConsulta

    private boolean AltaCliente(String nifA) {

        boolean error2 = false;

        String nombre2 = "";
        String direccion2 = "";
        String telefono2 = "";
        double credito_disponible2 = 0d;
        float descuento_cli2 = 0f;
        String input2 = "";
        try {

            do {
                input2 = JOptionPane.showInputDialog(vistaVentas.ButBorrarArticulo, "Introduzca el NOMBRE para cliente:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronNombre.matcher(input2);

                if (m.matches()) {

                    nombre2 = input2;
                    error2 = false;
                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Ingrese un NOMBRE válido", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error2 = true;
                }//Fin del if-else                

            } while (error2 == true);

            do {
                input2 = JOptionPane.showInputDialog(vistaVentas.ButBorrarArticulo, "Introduzca la DIRECCIÓN para cliente:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronDireccion.matcher(input2);

                if (m.matches()) {

                    direccion2 = input2.toLowerCase();
                    error2 = false;
                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Ingrese una DIRECCIÓN válida", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error2 = true;
                }//Fin del if-else                

            } while (error2 == true);

            do {
                input2 = JOptionPane.showInputDialog(vistaVentas.ButBorrarArticulo, "Introduzca el TELEFONO para cliente:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronTelefono.matcher(input2);

                if (m.matches()) {

                    telefono2 = input2.toLowerCase();
                    error2 = false;
                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Ingrese un TELEFONO válido", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error2 = true;
                }//Fin del if-else                

            } while (error2 == true);

            do {
                input2 = JOptionPane.showInputDialog(vistaVentas.ButBorrarArticulo, "Introduzca CREDITO DISPONIBLE para cliente:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronPrecio.matcher(input2);
                if (m.matches()) {

                    credito_disponible2 = Double.parseDouble(input2);
                    error2 = false;
                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Ingrese un CRÉDITO válido", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error2 = true;
                }//Fin del if-else                

            } while (error2 == true);

            do {
                input2 = JOptionPane.showInputDialog(vistaVentas.ButBorrarArticulo, "Introduzca DESCUENTO para cliente:", "Introduzca Datos Solicitados", JOptionPane.QUESTION_MESSAGE);
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronDescuento.matcher(input2);

                if (m.matches()) {

                    descuento_cli2 = Float.parseFloat(input2);
                    error2 = false;
                } else {
                    JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Ingrese un DESCUENTO válido (solo números MAX 99.99)", "Entrada de DATOS INCORRECTA", JOptionPane.ERROR_MESSAGE);
                    error2 = true;
                }//Fin del if-else                

            } while (error2 == true);

            metodosVentas.DarAltaCliente(nifA.toLowerCase(), nombre2, direccion2, telefono2, credito_disponible2, descuento_cli2);
            JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "El cliente se dio de alta correctamente. ", "ALTA CORRECTA", JOptionPane.INFORMATION_MESSAGE);

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaVentas.ButBorrarArticulo, "Proceso de ALTA Cliente, CANCELADO", "ALTA CANCELADA", JOptionPane.WARNING_MESSAGE);
            return false;

        }

    }//Fin de alta cliente

    //_____________________________________________________________________________-
    class OyenteSalir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
            VistaMenu menu = new VistaMenu();
            Principal metodos = new Principal();
            ControladorMenu controlMenu;
            //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
            vistaVentas.setVisible(false);
            controlMenu = new ControladorMenu(menu, metodos);

        }//Fin ActionPerformed
    }//FIn OyenteSalir

    private void Salir() {
        VistaMenu menu = new VistaMenu();
        Principal metodos = new Principal();
        ControladorMenu controlMenu;
        //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
        vistaVentas.setVisible(false);
        controlMenu = new ControladorMenu(menu, metodos);
    }

}
