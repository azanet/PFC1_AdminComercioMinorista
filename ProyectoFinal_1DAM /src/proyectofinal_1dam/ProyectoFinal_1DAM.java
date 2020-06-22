/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal_1dam;

import Controlador.ControladorMenu;
import Modelo.Principal;
import Vistas.VistaMenu;
import javax.swing.JFrame;

/**
 *
 * @author davidf
 */
public class ProyectoFinal_1DAM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
               // TODO code application logic here
        //Declaramos OBJETOS de MODELO,VISTA y CONTROLADOR. El controlador, lo inicializaremos con su constructor parametrizado, pasandole la vista y el modelo
        VistaMenu vista=new VistaMenu();
        Principal modelo= new Principal();
        ControladorMenu controlador;
        //Inicializando CONTROLADOR PASANDOLE MODELO Y VISTA
        controlador= new ControladorMenu(vista, modelo);
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Hacemos visile la vista a mostra PRIMERO
       vista.setVisible(true);
        
    }//Fin del metodo main
    
}//Fin de la clase principal
