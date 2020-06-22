/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author davidf
 */
public class VistaFacturas extends JFrame{
    
 
    
    //DECLARANDO TODOS LOS ELEMENTOS QUE COMPONEN NUESTRA VENTANA
    //Declarando Campos y Area de Texto 
    public static JTextField nif;
    public static JTextField nif_credito;
   // public static JTextField nif_contado;
    public static JTextField numero_albaran;
  
    public static JTextArea TextArea_existentes;
    public static JTextArea TextArea_agregados;
    
    //Declarando SCROLLPane para agregar el TExtArea a este mas abajo
    JScrollPane scrollListado;
    JScrollPane scrollSalida;
    //Declarando Etiquetas
    JLabel Titulo;
    
    JLabel NIF;
    JLabel Nif_credito;
 //   JLabel Nif_contado;
    JLabel Numero_albaran;
  
     JLabel Label_existentes;
        JLabel Label_agregados;
    //Declarando Botones
    public JButton ButListarAlbaranes;
    public JButton ButDelList;
    public JButton ButFacturarCredito;
    public JButton ButSalir;
    public JButton ButFacturarContado;

    

    
    /////////////////////////////////////
    //Constructor de La CLASE VISTAMENU
    public VistaFacturas(){
        
        //Agregamos TITULO a nuestro PROGRAMA
          
        //Agregamos TITULO a nuestro PROGRAMA
        super("===> FACTURAS <===");//super() SIEMPRE TIENE QUE IR EL PRIMERO
        
        //Agregamos ICONO a nuestro PROGRAMA
        ImageIcon icono = new ImageIcon("src/Vistas/favicon1.png");
       this.setIconImage(icono.getImage()); 
       //Ponemos los Layout a NULL poara poder asignar nosotros las coordenadas manualmente 
      
      Iniciar();  
    }//Fin del constructor

private void Iniciar(){
 setLayout(null);

        //Inicializando CAMPOS y AREA de Texto (aqui indicaremos lo que queremos que tenga escrito dentro el campo, EN MI CASO ESTARAN VACIOS)
           
                Titulo = new JLabel("-APARTADO FACTURAS-");
        add(Titulo);
          Titulo.setBounds(550, 10, 300, 30);
                
         JSeparator s4 = new JSeparator(); 
          // set layout as vertical 
       // s.setOrientation(SwingConstants.VERTICAL); 
        add(s4);
        s4.setBounds(0, 40, 1300, 1);
       
     
         
              
        
        NIF = new JLabel("NIF:");
         add(NIF);
        NIF.setBounds(25, 90, 150, 30);
                
          nif = new JTextField("");
       add(nif);
        nif.setBounds(70, 90, 250, 30);
       
        
        //Inicializando BOTONES y OYENTES (LES INDICAREMOS EL orden_pedidoe que llevarán)
        ButListarAlbaranes= new JButton("Listar Albaranes");
           add(ButListarAlbaranes);
         ButListarAlbaranes.setBounds(100, 140, 180, 50);
       
         
           
               JSeparator s5 = new JSeparator(); 
          // set layout as vertical 
        s5.setOrientation(SwingConstants.VERTICAL); 
        s5.setBounds(360, 40, 1, 160);
         add(s5);
         
              Nif_credito = new JLabel("NIF:");
         add(Nif_credito);
        Nif_credito.setBounds(470, 90, 150, 30);
             
        nif_credito = new JTextField("");
         add(nif_credito);
        nif_credito.setBounds(525, 90, 250, 30);

        
         
       
      ButFacturarCredito= new JButton("Facturar Crédito");
      //  ButFacturarCredito.addActionListener(new OyenteConsulta());
        add(ButFacturarCredito);
       ButFacturarCredito.setBounds(555, 140, 150, 50);
       
       
        
        
                   JSeparator s6 = new JSeparator(); 
          // set layout as vertical 
        s6.setOrientation(SwingConstants.VERTICAL); 
        s6.setBounds(835, 40, 1, 160);
         add(s6);
        
        
         Numero_albaran = new JLabel("Nº albarán:");
       add(Numero_albaran); 
        Numero_albaran.setBounds(900, 90,  150, 30);
        
         numero_albaran = new JTextField("");
        add(numero_albaran);
        numero_albaran.setBounds(1000, 90, 250, 30);
        
   /*     
          Nif_contado = new JLabel("NIF:");
       add(Nif_contado);
        Nif_contado.setBounds(915, 50, 150, 30);
        
        nif_contado = new JTextField("");
       add(nif_contado);
        nif_contado.setBounds(1020, 50, 250, 30);
       
*/
         
         
     ButFacturarContado= new JButton("Fact. Contado");
      //  ButInicializar.addActionListener(new OyenteInicializar());
            add(ButFacturarContado);
              ButFacturarContado.setBounds(1040, 140, 150, 50);
       //Añadimos ELEMENTOS A LA "VENTANA" (luego los ordenamos en el siguiente paso)

   
        
        
        
         JSeparator s = new JSeparator(); 
          // set layout as vertical 
       // s.setOrientation(SwingConstants.VERTICAL); 
        add(s);
        s.setBounds(0, 200, 1300, 1);
        
            JSeparator s2 = new JSeparator(); 
          // set layout as vertical 
        s2.setOrientation(SwingConstants.VERTICAL); 
        s2.setBounds(649, 285, 1, 445);
         add(s2);
        
        
        
            JSeparator s3 = new JSeparator(); 
          // set layout as vertical 
        //s3.setOrientation(SwingConstants.VERTICAL); 
        add(s3);
        s3.setBounds(0, 285, 1300, 50);
        
        
        

         
    //    ButListarAlbaranes.addActionListener(new OyenteAlta());
        ButDelList= new JButton("Limpiar Pantalla");
      //  ButDelList.addActionListener(new OyenteBaja());
          add(ButDelList);
       ButDelList.setBounds(310, 220, 150, 50);
       
      
       
      ButSalir= new JButton("Volver a Menú");
       // ButSalir.addActionListener(new OyenteListado());
        add(ButSalir);
         ButSalir.setBounds(830, 220, 150, 50);
      
        
         Label_agregados = new JLabel("Albaranes Existentes para Cliente:");
        add(Label_agregados);
         Label_agregados.setBounds(10, 285, 300, 30);
         
        
      Label_existentes=new JLabel ("Factura Generada:");
      add(Label_existentes);
       Label_existentes.setBounds(656, 285, 300, 30);
             //Añadiendo Campos y Area de Texto
     
        
      
      
       
        
              //Agregamos TEXTAREA al Scroll, como textArea está agregado a Scroll, será a este al que AGREGEMOS y ASIGNEMOS el tamaño mas abajo
         TextArea_existentes = new JTextArea("");//INicializando al TextArea 
        TextArea_existentes.setEditable(false);//Haciendo que TextArea NO SEA EDITABLE
        scrollListado = new JScrollPane(TextArea_existentes);//Agregando SCROLL a TextArea
        add(scrollListado); //AÑADIMOS scroll EN VEZ DE RESULTADO porque TextArea_existentes YA ESTA DENTRO DE SCROLL
       scrollListado.setBounds(5, 320, 644,370 );
      

        TextArea_agregados = new JTextArea("");//INicializando al TextArea 
        TextArea_agregados.setEditable(false);//Haciendo que TextArea NO SEA EDITABLE
         scrollSalida= new JScrollPane(TextArea_agregados);//Agregando SCROLL a TextArea
         add(scrollSalida); 
          scrollSalida.setBounds(651,320 , 644,370 );
    //    TextArea_existentes.setLineWrap(true); //Haciendo que se haga salto de linea al llegar al final
       // TextArea_existentes.setWrapStyleWord(true);
   
        
       
       //POSICIONANDO ELEMENTOS EN LA "VENTANA" 
      
  
       
    
  
           //Hacemos visible la ventana
       setSize(1300, 730);
        setLocation(30, 20);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //Seteamos el tamaño de la ventana de nuestro programa
    
}
    
    
    
}

    
    
    
    
    

