/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Albaran;
import Modelo.Albaran_Venta;
import Modelo.Imprimir;
import Modelo.MetodosFacturas;
import Modelo.Patrones;
import Modelo.Principal;
import Vistas.VistaFacturas;
import Vistas.VistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author davidf
 */
public class ControladorFacturas {

    //AGREGAMOSOBJETOS DEL MODELO, VISTA, Y OBJETOS, QUE VAYAMOS A UTILIZAR EN ESTE CONTROLADOR
    Imprimir impresora;
    public VistaFacturas vistaFacturas;
    private MetodosFacturas metodosFacturas;//DECLARAMOS OBJETO del MODELO que contiene los METODOS | Establecemoscomo PRIVADO
    Patrones p;
    Matcher m;
    String nif = "";
    ArrayList<Albaran> albaranes_detalles = new ArrayList<>();
    ArrayList<Albaran_Venta> albaranes_BBDD;
    String relleno = "                      ";

    public ControladorFacturas(VistaFacturas vistaFacturas, MetodosFacturas metodosFacturas) {
        this.vistaFacturas = vistaFacturas;
        this.metodosFacturas = metodosFacturas;
        this.p = new Patrones();

        //Inicializando BOTONES que se encuentran EN LA "VistaMenu"
        this.vistaFacturas.ButSalir.addActionListener(new OyenteSalir());
        this.vistaFacturas.ButFacturarContado.addActionListener(new OyenteFacturarContado());
        this.vistaFacturas.ButFacturarCredito.addActionListener(new OyenteFacturarCredito());
        this.vistaFacturas.ButListarAlbaranes.addActionListener(new OyenteListarAlbaranes());
        this.vistaFacturas.ButDelList.addActionListener(new OyenteBorrarLista());

    }

    //_________________________________________________________________________
    class OyenteFacturarContado implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
            double suma_total = 0d;
            float descuento_cli = 0f;
            boolean imprimir = false;
            boolean error = false;
            String input = "";
            try {
                vistaFacturas.TextArea_agregados.setText("");

                input = vistaFacturas.numero_albaran.getText().trim();
                //  vistaFacturas.TextArea_existentes.append(input);
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronCodigoArticulo.matcher(input);

                if (m.matches()) {
                    //           JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "Dentro del IF", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
                    if (metodosFacturas.LecturaAlbaranesDetalles_contado(input).size() > 0) {
                        ///CONSULTA SQL DETERMINANDO SI EXISTE EL CIF/PROVEEDOR
                        albaranes_detalles = metodosFacturas.LecturaAlbaranesDetalles_contado(input);
                        error = false;
                        //     JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "EXISTE DNI", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "ALBARÁN no encontrado en la BBDD", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);

                        error = true;

                    }

                } else {
                    JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "Nº de Albarán incorrecto\nno concuerda con el patron", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }//Fin del if-else                

                if (error == false) {
                    try {

                        String[] options = {"Imprimir", "Guardar y Listar", "Listar"};
                        int seleccion1 = JOptionPane.showOptionDialog(vistaFacturas.ButFacturarCredito, "¿Qué desea hacer?", "Consulta ALBARANES Clientes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                        //    vistaFacturas.TextArea_existentes.append(String.valueOf(seleccion1));

                        switch (seleccion1) {

                            //IMPRIMIR
                            case 0:
                                imprimir = true;

                            //GUARDAR Y LISTAR   
                            case 1:

                                //Crea un objeto File asociado al fichero fichSec.txt
                                //CREANDO NOMBRE DE ARCHIVO
                                String nombre_fichero = "";
                                nombre_fichero = input + "_" + albaranes_detalles.get(0).getNumero_albaran() + "_" + LocalDate.now();
                                descuento_cli = albaranes_detalles.get(0).getDescuento();

                                File fichero = new File(nombre_fichero + ".doc");
                                if (!fichero.exists()) {
                                    fichero.createNewFile();
                                } else {
                                    fichero.delete();
                                    fichero.createNewFile();

                                }
                                //Crea un flujo de caracteres para grabar
                                FileWriter flujo = new FileWriter(fichero);//Podriamos dejarle true para que seguiera escribiendo debajo de este si existiera, (aunqeu lo estoy eliminando mas arriba pòrque no es el caso)
                                // Escribe los campos cada uno en una linea

                                //Enlaza el flujo de salida con la clase PrintWrite que tiene el metodo println
                                PrintWriter escritor = new PrintWriter(flujo);

                                escritor.println("\n\t\t\t-KRAZY LAB S.L-\n\tTlfn: 6XXXXXXXX     Dir: C/ El Torpedo nº7\n\n\n\t\tALBARÁN VENTA -- PAGO CONTADO \n");
                                escritor.println("Cliente:" + input + "    Descuento Cliente:" + descuento_cli + "%" + "    Fecha Hoy:" + LocalDate.now() + "\n\n");

                                vistaFacturas.TextArea_agregados.append("\n\t\t\t-KRAZY LAB S.L-\n\tTlfn: 6XXXXXXXX     Dir: C/ El Torpedo nº7\n\n\n\t\t\tALBARÁN VENTA -- PAGO CONTADO\n");
                                vistaFacturas.TextArea_agregados.append("Nº Albarán:" + input + "    Descuento Cliente:" + descuento_cli + "%" + "    Fecha Hoy:" + LocalDate.now() + "\n\n");

                                vistaFacturas.TextArea_agregados.append(("Nº ALBARÁN" + relleno).substring(0, 14) + ("Cod.ARTÍCULO" + relleno).substring(0, 14) + ("FECHA VENTA" + relleno).substring(0, 14) + ("CANTIDAD" + relleno).substring(0, 10) + ("PRECIO" + relleno).substring(0, 10) + ("DESCUENTO" + "%" + relleno).substring(0, 9) + "\n");
                                escritor.print(("Nº ALBARÁN" + relleno).substring(0, 14) + ("Cod.ARTÍCULO" + relleno).substring(0, 14) + ("FECHA VENTA" + relleno).substring(0, 14) + ("CANTIDAD" + relleno).substring(0, 10) + ("PRECIO" + relleno).substring(0, 10) + ("DESCUENTO" + "%" + relleno).substring(0, 9) + "\n");

                                for (Albaran obj : albaranes_detalles) {

                                    ///AQUI IMPRIMIMOS EL ARRAY LIST QUE RECOJAMOS de ALBARANES NORMALES, PARA MOSTRARLOS EN EL PANEL
                                    vistaFacturas.TextArea_agregados.append((obj.getNumero_albaran() + relleno).substring(0, 14) + (obj.getCodigo_articulo() + relleno).substring(0, 14) + (obj.getFecha_venta() + relleno).substring(0, 14) + (obj.getCantidad() + relleno).substring(0, 10) + (String.format("%.2f€", obj.getPrecio()) + relleno).substring(0, 10) + (String.format("%.2f", obj.getDescuento()) + "%" + relleno).substring(0, 7) + "\n");
                                    escritor.print((obj.getNumero_albaran() + relleno).substring(0, 14) + (obj.getCodigo_articulo() + relleno).substring(0, 14) + (obj.getFecha_venta() + relleno).substring(0, 14) + (obj.getCantidad() + relleno).substring(0, 10) + (String.format("%.2f€", obj.getPrecio()) + relleno).substring(0, 10) + (String.format("%.2f", obj.getDescuento()) + "%" + relleno).substring(0, 7) + "\n");

                                    //Sumando el total de los productos y cantidades y capturando el descuento del cliente,para hacer luego el total de la factura
                                    suma_total += (obj.getCantidad() * obj.getPrecio());

                                }//Fin del if-Error==false

                                escritor.println("_______________________________________________________________________\n" + ("Importe Bruto:" + relleno + relleno).substring(0, 37) + suma_total + "€\nImporte FINAL con Descuento Cliente: " + (suma_total-((suma_total * descuento_cli) / 100)) + "€\n\n");
                                vistaFacturas.TextArea_agregados.append("_______________________________________________________________________\n" + ("Importe Bruto:" + relleno + relleno).substring(0, 37) + suma_total + "€\nImporte FINAL con Descuento Cliente: " + (suma_total-((suma_total * descuento_cli) / 100)) + "€\n\n");

                                escritor.close();
                                flujo.close();

                                if (imprimir == true) {
                                    impresora = new Imprimir("./" + nombre_fichero + ".doc");
                                    String[] options1 = {"Guardar", "Eliminar"};
                                    seleccion1 = JOptionPane.showOptionDialog(vistaFacturas.ButFacturarCredito, "¿Qué desea hacer con el ALBARÁN?", "Consulta ALBARANES Clientes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, null);

                                    switch (seleccion1) {
                                        case 0:
                                            JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "El ALBARÁN quedó almacenado con nombre:\n\"" + nombre_fichero + ".doc\"", "TAREA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);

                                            break;

                                        case 1:

                                            fichero = new File(nombre_fichero + ".doc");
                                            fichero.delete();
                                            JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "El ALBARÁN FUE ELIMINADO", "TAREA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);

                                            break;

                                    }
                                    
                                    imprimir = false;
                                }

                                break;

                            //LISTAR  
                            case 2:
                                descuento_cli = albaranes_detalles.get(0).getDescuento();
                                vistaFacturas.TextArea_agregados.append("\n\t\t\t-KRAZY LAB S.L-\n\tTlfn: 6XXXXXXXX     Dir: C/ El Torpedo nº7\n\n\n\t\t\tALBARÁN VENTA -- PAGO CONTADO\n");
                                vistaFacturas.TextArea_agregados.append("Cliente:" + input + "    Descuento Cliente:" + descuento_cli + "%" + "    Fecha Hoy:" + LocalDate.now() + "\n\n");

                                vistaFacturas.TextArea_agregados.append(("Nº ALBARÁN" + relleno).substring(0, 14) + ("Cod.ARTÍCULO" + relleno).substring(0, 14) + ("FECHA VENTA" + relleno).substring(0, 14) + ("CANTIDAD" + relleno).substring(0, 10) + ("PRECIO" + relleno).substring(0, 10) + ("DESCUENTO" + "%" + relleno).substring(0, 9) + "\n");

                                for (Albaran obj : albaranes_detalles) {

                                    ///AQUI IMPRIMIMOS EL ARRAY LIST QUE RECOJAMOS de ALBARANES NORMALES, PARA MOSTRARLOS EN EL PANEL
                                    vistaFacturas.TextArea_agregados.append((obj.getNumero_albaran() + relleno).substring(0, 14) + (obj.getCodigo_articulo() + relleno).substring(0, 14) + (obj.getFecha_venta() + relleno).substring(0, 14) + (obj.getCantidad() + relleno).substring(0, 10) + (String.format("%.2f€", obj.getPrecio()) + relleno).substring(0, 10) + (String.format("%.2f", obj.getDescuento()) + "%" + relleno).substring(0, 7) + "\n");

                                    //Sumando el total de los productos y cantidades y capturando el descuento del cliente,para hacer luego el total de la factura
                                    suma_total += (obj.getCantidad() * obj.getPrecio());
                                }//Fin del for
                                vistaFacturas.TextArea_agregados.append("_______________________________________________________________________\n" + ("Importe Bruto:" + relleno + relleno).substring(0, 37) + suma_total + "€\nImporte FINAL con Descuento Cliente: " + (suma_total-((suma_total * descuento_cli) / 100)) + "€\n\n");
                            //    vistaFacturas.TextArea_agregados.print(); //Con este comando podemos mandar a la impresora directamente lo que esté en el text area
                      
                                
                                break;

                        }

                    } catch (IOException ioe) {
                        System.out.println("Error al crear el fichero\n\n");
                    } catch (Exception e) {
                        System.out.println("Error de fichero\n\n");
                    }

                } else {
                    //JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "No existe el NIF en la BBDD\n", "ERROR", JOptionPane.WARNING_MESSAGE);

                }

            } catch (Exception e) {
                error = false;
                JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
                vistaFacturas.nif.setText("");

            }

                         vistaFacturas.nif_credito.setText("");
             vistaFacturas.nif.setText("");
             vistaFacturas.numero_albaran.setText("");
        }//Fin ActionPerformed
    }//FIn OyenteBaja

//______________________________________________________________________________    
    //_________________________________________________________________________
    class OyenteFacturarCredito implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
            double suma_total = 0d;
            float descuento_cli = 0f;
            boolean imprimir = false;
            boolean error = false;
            String input = "";
            try {
                vistaFacturas.TextArea_agregados.setText("");

                input = vistaFacturas.nif_credito.getText().trim();
                //  vistaFacturas.TextArea_existentes.append(input);
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronNIF.matcher(input);

                if (m.matches()) {
                    //           JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "Dentro del IF", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
                    if (metodosFacturas.ExisteNIF(input)) {
                        ///CONSULTA SQL DETERMINANDO SI EXISTE EL CIF/PROVEEDOR
                        albaranes_detalles = metodosFacturas.LecturaAlbaranesDetalles_credito(input);
                        error = false;
                        //     JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "EXISTE DNI", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "NIF no encontrado en la BBDD", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);

                        error = true;

                    }

                } else {
                    JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "NIF incorrecto, no concuerda con el patron", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }//Fin del if-else                

                if (error == false) {
                    try {

                        String[] options = {"Imprimir", "Guardar y Listar", "Listar"};
                        int seleccion1 = JOptionPane.showOptionDialog(vistaFacturas.ButFacturarCredito, "¿Qué desea hacer?", "Consulta ALBARANES Clientes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                        //    vistaFacturas.TextArea_existentes.append(String.valueOf(seleccion1));

                        switch (seleccion1) {

                            //IMPRIMIR
                            case 0:
                                imprimir = true;

                            //GUARDAR Y LISTAR   
                            case 1:

                                //Crea un objeto File asociado al fichero fichSec.txt
                                //CREANDO NOMBRE DE ARCHIVO
                                String nombre_fichero = "";
                                nombre_fichero = input + "_" + albaranes_detalles.get(0).getNumero_albaran() + "_" + LocalDate.now();
                                descuento_cli = albaranes_detalles.get(0).getDescuento();

                                File fichero = new File(nombre_fichero + ".doc");
                                if (!fichero.exists()) {
                                    fichero.createNewFile();
                                } else {
                                    fichero.delete();
                                    fichero.createNewFile();

                                }
                                //Crea un flujo de caracteres para grabar
                                FileWriter flujo = new FileWriter(fichero);//Podriamos dejarle true para que seguiera escribiendo debajo de este si existiera, (aunqeu lo estoy eliminando mas arriba pòrque no es el caso)
                                // Escribe los campos cada uno en una linea

                                //Enlaza el flujo de salida con la clase PrintWrite que tiene el metodo println
                                PrintWriter escritor = new PrintWriter(flujo);

                                escritor.println("\n\t\t\t-KRAZY LAB S.L-\n\tTlfn: 6XXXXXXXX     Dir: C/ El Torpedo nº7\n\n\n\t\tALBARÁN VENTA -- PAGO CRÉDITO \n");
                                escritor.println("Cliente:" + input + "    Descuento Cliente:" + descuento_cli + "%" + "    Fecha Hoy:" + LocalDate.now() + "\n\n");

                                vistaFacturas.TextArea_agregados.append("\n\t\t\t-KRAZY LAB S.L-\n\tTlfn: 6XXXXXXXX     Dir: C/ El Torpedo nº7\n\n\n\t\t\tALBARÁN VENTA -- PAGO CRÉDITO\n");
                                vistaFacturas.TextArea_agregados.append("Cliente:" + input + "    Descuento Cliente:" + descuento_cli + "%" + "    Fecha Hoy:" + LocalDate.now() + "\n\n");

                                vistaFacturas.TextArea_agregados.append(("Nº ALBARÁN" + relleno).substring(0, 14) + ("Cod.ARTÍCULO" + relleno).substring(0, 14) + ("FECHA VENTA" + relleno).substring(0, 14) + ("CANTIDAD" + relleno).substring(0, 10) + ("PRECIO" + relleno).substring(0, 10) + ("DESCUENTO" + "%" + relleno).substring(0, 9) + "\n");
                                escritor.print(("Nº ALBARÁN" + relleno).substring(0, 14) + ("Cod.ARTÍCULO" + relleno).substring(0, 14) + ("FECHA VENTA" + relleno).substring(0, 14) + ("CANTIDAD" + relleno).substring(0, 10) + ("PRECIO" + relleno).substring(0, 10) + ("DESCUENTO" + "%" + relleno).substring(0, 9) + "\n");

                                for (Albaran obj : albaranes_detalles) {

                                    ///AQUI IMPRIMIMOS EL ARRAY LIST QUE RECOJAMOS de ALBARANES NORMALES, PARA MOSTRARLOS EN EL PANEL
                                    vistaFacturas.TextArea_agregados.append((obj.getNumero_albaran() + relleno).substring(0, 14) + (obj.getCodigo_articulo() + relleno).substring(0, 14) + (obj.getFecha_venta() + relleno).substring(0, 14) + (obj.getCantidad() + relleno).substring(0, 10) + (String.format("%.2f€", obj.getPrecio()) + relleno).substring(0, 10) + (String.format("%.2f", obj.getDescuento()) + "%" + relleno).substring(0, 7) + "\n");
                                    escritor.print((obj.getNumero_albaran() + relleno).substring(0, 14) + (obj.getCodigo_articulo() + relleno).substring(0, 14) + (obj.getFecha_venta() + relleno).substring(0, 14) + (obj.getCantidad() + relleno).substring(0, 10) + (String.format("%.2f€", obj.getPrecio()) + relleno).substring(0, 10) + (String.format("%.2f", obj.getDescuento()) + "%" + relleno).substring(0, 7) + "\n");

                                    //Sumando el total de los productos y cantidades y capturando el descuento del cliente,para hacer luego el total de la factura
                                    suma_total += (obj.getCantidad() * obj.getPrecio());

                                }//Fin del if-Error==false

                                escritor.println("_______________________________________________________________________\n" + ("Importe Bruto:" + relleno + relleno).substring(0, 37) + suma_total + "€\nImporte FINAL con Descuento Cliente: " + (suma_total-((suma_total * descuento_cli) / 100)) + "€\n\n");
                                vistaFacturas.TextArea_agregados.append("_______________________________________________________________________\n" + ("Importe Bruto:" + relleno + relleno).substring(0, 37) + suma_total + "€\nImporte FINAL con Descuento Cliente: " + (suma_total-((suma_total * descuento_cli) / 100)) + "€\n\n");

                                escritor.close();
                                flujo.close();

                                if (imprimir == true) {
                                    impresora = new Imprimir("./" + nombre_fichero + ".doc");
                                    String[] options1 = {"Guardar", "Eliminar"};
                                    seleccion1 = JOptionPane.showOptionDialog(vistaFacturas.ButFacturarCredito, "¿Qué desea hacer con el ALBARÁN?", "Consulta ALBARANES Clientes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, null);

                                    switch (seleccion1) {
                                        case 0:
                                            JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "El ALBARÁN quedó almacenado con nombre:\n\"" + nombre_fichero + ".doc\"", "TAREA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);

                                            break;

                                        case 1:

                                            fichero = new File(nombre_fichero + ".doc");
                                            fichero.delete();
                                            JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "El ALBARÁN FUE ELIMINADO", "TAREA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);

                                            break;

                                    }

                                    imprimir = false;
                                }

                                break;

                            //LISTAR  
                            case 2:

                                descuento_cli = albaranes_detalles.get(0).getDescuento();
                                vistaFacturas.TextArea_agregados.append("\n\t\t\t-KRAZY LAB S.L-\n\tTlfn: 6XXXXXXXX     Dir: C/ El Torpedo nº7\n\n\n\t\t\tALBARÁN VENTA  -- PAGO CRÉDITO\n");
                                vistaFacturas.TextArea_agregados.append("Cliente:" + input + "    Descuento Cliente:" + descuento_cli + "%" + "    Fecha Hoy:" + LocalDate.now() + "\n\n");

                                vistaFacturas.TextArea_agregados.append(("Nº ALBARÁN" + relleno).substring(0, 14) + ("Cod.ARTÍCULO" + relleno).substring(0, 14) + ("FECHA VENTA" + relleno).substring(0, 14) + ("CANTIDAD" + relleno).substring(0, 10) + ("PRECIO" + relleno).substring(0, 10) + ("DESCUENTO" + "%" + relleno).substring(0, 9) + "\n");

                                for (Albaran obj : albaranes_detalles) {

                                    ///AQUI IMPRIMIMOS EL ARRAY LIST QUE RECOJAMOS de ALBARANES NORMALES, PARA MOSTRARLOS EN EL PANEL
                                    vistaFacturas.TextArea_agregados.append((obj.getNumero_albaran() + relleno).substring(0, 14) + (obj.getCodigo_articulo() + relleno).substring(0, 14) + (obj.getFecha_venta() + relleno).substring(0, 14) + (obj.getCantidad() + relleno).substring(0, 10) + (String.format("%.2f€", obj.getPrecio()) + relleno).substring(0, 10) + (String.format("%.2f", obj.getDescuento()) + "%" + relleno).substring(0, 7) + "\n");

                                    //Sumando el total de los productos y cantidades y capturando el descuento del cliente,para hacer luego el total de la factura
                                    suma_total += (obj.getCantidad() * obj.getPrecio());
                                }//Fin del for
                                vistaFacturas.TextArea_agregados.append("_______________________________________________________________________\n" + ("Importe Bruto:" + relleno + relleno).substring(0, 37) + suma_total + "€\nImporte FINAL con Descuento Cliente: " + (suma_total-((suma_total * descuento_cli) / 100)) + "€\n\n");

                                break;

                        }

                    } catch (IOException ioe) {
                        System.out.println("Error al crear el fichero\n\n");
                    } catch (Exception e) {
                        System.out.println("Error de fichero\n\n");
                    }

                } else {
                    //JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "No existe el NIF en la BBDD\n", "ERROR", JOptionPane.WARNING_MESSAGE);

                }

            } catch (Exception e) {
                error = false;
                JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
                vistaFacturas.nif.setText("");

            }

                         vistaFacturas.nif_credito.setText("");
             vistaFacturas.nif.setText("");
             vistaFacturas.numero_albaran.setText("");
        }//Fin ActionPerformed
    }//FIn OyenteBaja

//______________________________________________________________________________    
    class OyenteListarAlbaranes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean error = false;
            String input = "";
            relleno = "                                                            ";
            try {
                vistaFacturas.TextArea_existentes.setText("");

                input = vistaFacturas.nif.getText().trim();
                //       vistaFacturas.TextArea_existentes.append(input);
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = p.patronNIF.matcher(input);

                if (m.matches()) {
                    //           JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "Dentro del IF", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
                    if (metodosFacturas.ExisteNIF(input)) {
                        ///CONSULTA SQL DETERMINANDO SI EXISTE EL CIF/PROVEEDOR
                        error = false;
                        //     JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "EXISTE DNI", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "NIF no encontrado en la BBDD", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);

                        error = true;

                    }

                } else {
                    JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "NIF incorrecto, no concuerda con el patron", "DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }//Fin del if-else                

                if (error == false) {

                    String[] options = {"todas", "credito", "contado"};
                    int seleccion1 = JOptionPane.showOptionDialog(vistaFacturas.ButFacturarCredito, "Seleccione Tipo de PAGO para clientes a mostrar:", "Consulta ALBARANES Clientes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                    //vistaFacturas.TextArea_existentes.append(String.valueOf(seleccion1));

                    switch (seleccion1) {

                        //TODAS
                        case 0:
                            albaranes_BBDD = metodosFacturas.LecturaAlbaranes(input, "%");
                            break;

                        //CREDITO    
                        case 1:
                            albaranes_BBDD = metodosFacturas.LecturaAlbaranes(input, "credito");
                            break;

                        //CONTADO    
                        case 2:
                            albaranes_BBDD = metodosFacturas.LecturaAlbaranes(input, "contado");
                            break;

                    }

                    vistaFacturas.TextArea_existentes.append(("DNI CLIENTE" + relleno).substring(0, 14) + "|" + ("Nº ALBARÁN" + relleno).substring(0, 14) + "|" + ("FECHA VENTA" + relleno).substring(0, 14) + "|" + ("TIPO PAGO" + relleno).substring(0, 14) + "\n");

                    for (Albaran_Venta obj : albaranes_BBDD) {
                        vistaFacturas.TextArea_existentes.append(String.format("%-14s   |  %-14s  |  %-14s  |  %-10s  |\n", obj.getNif(), obj.getNumero_albaran(), obj.getFecha_venta(), obj.getTipo_pago()));

                        ///AQUI IMPRIMIMOS EL ARRAY LIST QUE RECOJAMOS de ALBARANES NORMALES, PARA MOSTRARLOS EN EL PANEL
                        //*****LOS DE DETALLES LOS IMPRIMIREMOS EN SU CORRESPONDIENTE BOTON A LA HORA DE FACTURAR A LA VEZ QUE CREAMOS EL FICHERO PARA ALMACENAR.
                    }//Fin del if-Error==false

                }

            } catch (Exception e) {
                error = false;
                JOptionPane.showMessageDialog(vistaFacturas.ButFacturarCredito, "Se ha producido un error\n", "ERROR", JOptionPane.WARNING_MESSAGE);
                vistaFacturas.nif.setText("");

            }
                         vistaFacturas.nif_credito.setText("");
             vistaFacturas.nif.setText("");
             vistaFacturas.numero_albaran.setText("");

        }//Fin ActionPerformed
    }//FIn OyenteConsulta

    //_____________________________________________________________________   
    class OyenteBorrarLista implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
             vistaFacturas.nif_credito.setText("");
             vistaFacturas.nif.setText("");
             vistaFacturas.numero_albaran.setText("");
             
            vistaFacturas.TextArea_agregados.setText("");
            vistaFacturas.TextArea_existentes.setText("");
           
        }//Fin ActionPerformed
    }//FIn OyenteListado

//_____________________________________________________________________________-
    class OyenteSalir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
            VistaMenu menu = new VistaMenu();
            Principal metodos = new Principal();
            ControladorMenu controlMenu;
            //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
            vistaFacturas.setVisible(false);
            controlMenu = new ControladorMenu(menu, metodos);
        }//Fin ActionPerformed
    }//FIn OyenteSalir

    private void Salir() {
        VistaMenu menu = new VistaMenu();
        Principal metodos = new Principal();
        ControladorMenu controlMenu;
        //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
        vistaFacturas.setVisible(false);
        controlMenu = new ControladorMenu(menu, metodos);

    }

}
