/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



/**
 *
 * @author davidf
 */
public class VistaMenu extends JFrame{
    
  
    //DECLARANDO TODOS LOS ELEMENTOS QUE COMPONEN NUESTRA VENTANA
    //Declarando Campos y Area de Texto 

    //Declarando SCROLLPane para agregar el TExtArea a este mas abajo
   
    //Declarando Etiquetas
    JLabel Titulo;
    JLabel Resultado;
  
    //Declarando Botones
    public JButton ButCompras;
    public JButton ButVentas;
    public JButton ButFacturas;
    public JButton ButSalir;
  //  public JButton ButBorrar;
   

    
    /////////////////////////////////////
    //Constructor de La CLASE VISTAMENU
    public VistaMenu(){
        
        //Agregamos TITULO a nuestro PROGRAMA
        super("Gestor de Ventas Comercio");//super() SIEMPRE TIENE QUE IR EL PRIMERO
        
        //Agregamos ICONO a nuestro PROGRAMA
        ImageIcon icono = new ImageIcon("src/Vistas/favicon1.png");
       this.setIconImage(icono.getImage()); 
       Iniciar();
    }//Fin del constructor


    private void Iniciar(){
    
       
       setLayout(null);
        //Inicializando CAMPOS y AREA de Texto (aqui indicaremos lo que queremos que tenga escrito dentro el campo, EN MI CASO ESTARAN VACIOS)
      
   

//Inicializando ETIQUETAS (LES INDICAREMOS EL nombre que llevarán)
        Titulo = new JLabel("-KRAZY_LAB SL-");
       add(Titulo);
       Titulo.setBounds(590, 10, 300, 30);
         
        Resultado = new JLabel("SELECCIONE OPCIÓN:");
       add(Resultado);
       Resultado.setBounds(570, 100, 300, 30);
    
        //Inicializando BOTONES y OYENTES (LES INDICAREMOS EL nombree que llevarán)
        
        ButCompras= new JButton("Compras");
        add(ButCompras);
         ButCompras.setBounds(475, 150, 150, 80);
    //    ButCompras.addActionListener(new OyenteAlta());
    
        ButVentas= new JButton("Ventas");
       add(ButVentas);
       ButVentas.setBounds(675, 150, 150, 80);
       //  ButVentas.addActionListener(new OyenteBaja()); 
        
        ButFacturas= new JButton("Facturas");
       add(ButFacturas);
       ButFacturas.setBounds(475, 260, 150, 80);
        //  ButFacturas.addActionListener(new OyenteConsulta()); 
       
        ButSalir= new JButton("Salir");
       add(ButSalir);
       ButSalir.setBounds(675, 260, 150,80);
       // ButSalir.addActionListener(new OyenteListado()); 

 
  
           //Hacemos visible la ventana
          
       
        //Seteamos el tamaño de la ventana de nuestro programa
      
       // setExtendedState(Frame.MAXIMIZED_BOTH);
          setSize(1300, 730);
        setLocation(30, 20);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
        
    }
    
    
    
    
}
