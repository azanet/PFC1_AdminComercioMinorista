/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MetodosCompras;
import Modelo.MetodosFacturas;
import Modelo.MetodosVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import Modelo.Principal;
import Vistas.VistaCompras;
import Vistas.VistaFacturas;

import Vistas.VistaMenu;
import Vistas.VistaVentas;
import javax.swing.JScrollPane;

/**
 *
 * @author davidf
 */
//LA Clase CONTROLADOR siempre IMPLEMENTARA de "ActionListener" y sobreescribiremos su metodo ActionPerformed
public class ControladorMenu {

    //AGREGAMOSOBJETOS DEL MODELO, VISTA, Y OBJETOS, QUE VAYAMOS A UTILIZAR EN ESTE CONTROLADOR
    public VistaMenu vistaMenu;//Declaramos un oObjeto de la VISTA correspondiente (en este caSO "VistaMenu") | Establecemos como PUBLICO
    
 
  //  private Principal metodos;//DECLARAMOS OBJETO del MODELO que contiene los METODOS | Establecemoscomo PRIVADO
    
    ControladorCompras controladorCompras;
    ControladorVentas controladorVentas;
    ControladorFacturas controladorFacturas;
    
    JScrollPane scrollPanelesSup;
    JScrollPane scrollTextArea2;
    JScrollPane scrollTeaxArea1;
    

   
    public ControladorMenu(VistaMenu vistaMenu, Principal metodos) {
        //Inicializamos los objetos de Vista y Modelo pasandole directamene el parametro, que a su vez el parametro trae los constructores inicializados del metodo MAIN(en este caso)
        this.vistaMenu=vistaMenu;
     //   this.metodos = metodos;
        //Agregamops el PANEL MENU

       
      //Inicializando BOTONES que se encuentran EN LA "VistaMenu"
       this.vistaMenu.ButCompras.addActionListener(new OyenteCompras());
       this. vistaMenu.ButVentas.addActionListener(new OyenteVentas());
       this. vistaMenu.ButFacturas.addActionListener(new OyenteFacturas());
       this. vistaMenu.ButSalir.addActionListener(new OyenteSalir());
         // this.vistaMenu.ButBorrar.addActionListener(new OyenteBorrar());
    
       vistaMenu.setVisible(true);
    }
    
    
    
    

    class OyenteCompras implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
           //INTRODUCOR AQUI COPDIGOOOOOO
        
        //Declaramos OBJETOS de MODELO,VISTA y CONTROLADOR. El controlador, lo inicializaremos con su constructor parametrizado, pasandole la vista y el modelo
       VistaCompras comp=new VistaCompras();
        MetodosCompras modeloComp= new MetodosCompras();
        ControladorCompras controladorComp;
        //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
        vistaMenu.setVisible(false);
        controladorComp= new ControladorCompras(comp, modeloComp);
      
        
       
        
        }//Fin ActionPerformed
    }//FIn OyenteCompras
    //_________________________________________________________________________
    
    
    
    
    
   //_________________________________________________________________________
    class OyenteVentas implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
  
             //Declaramos OBJETOS de MODELO,VISTA y CONTROLADOR. El controlador, lo inicializaremos con su constructor parametrizado, pasandole la vista y el modelo
        VistaVentas ventas=new VistaVentas();
        MetodosVentas modeloVentas= new MetodosVentas();
        ControladorVentas controladorVentas;
        //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
        vistaMenu.setVisible(false);
        controladorVentas= new ControladorVentas(ventas, modeloVentas);
      
        }//Fin ActionPerformed
    }//FIn OyenteVentas
//______________________________________________________________________________    
    
    
    
    
    class OyenteFacturas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
         
             VistaFacturas facturas=new VistaFacturas();
        MetodosFacturas modeloFacturas= new MetodosFacturas();
        ControladorFacturas controladorFacturas;
        //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
        vistaMenu.setVisible(false);
        controladorFacturas= new ControladorFacturas(facturas, modeloFacturas);
            
        }//Fin ActionPerformed
    }//FIn OyenteFacturas

//______________________________________________________________________-
    class OyenteSalir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //INSERTAR CODIGO aqui!!
            System.exit(0);

        }//Fin ActionPerformed
    }//FIn OyenteSalir

}//Fin del Controlador Menu
