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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author davidf
 */
public class VistaCompras extends JFrame{

    
    
    //DECLARANDO TODOS LOS ELEMENTOS QUE COMPONEN NUESTRA VENTANA
    //Declarando Campos y Area de Texto 
    public static JTextField cif;
    public static JTextField orden_pedido;
    public static JTextField orden_pedidos;
    public static JTextField codigo_articulo;
    public static JTextField fecha_compra;
    public static JTextField numero_albaran;
    public static JTextField descuento;
    public static JTextField cantidad;
    public static JTextField precio;
    
    public static JTextArea TextArea_existentes;
    public static JTextArea TextArea_agregados;
    
    //Declarando SCROLLPane para agregar el TExtArea a este mas abajo
    JScrollPane scrollListado;
    JScrollPane scrollSalida;
    //Declarando Etiquetas
    JLabel Titulo;
    JLabel Label_agregados;
    JLabel CIF;
    JLabel Orden_Pedidos;
    JLabel Orden_pedido;
    JLabel Codigo_articulo;
    JLabel Fecha_compra;
    JLabel Numero_albaran;
     JLabel Descuento;
    JLabel Cantidad;
    JLabel Precio;
     JLabel Label_existentes;

    //Declarando Botones
    public JButton ButSalir;
    public JButton ButDelList;
    public JButton ButBorrarArticulo;
    public JButton ButFinalizarVenta;
    public JButton ButAgregarArticulo;

    

    
    /////////////////////////////////////
    //Constructor de La CLASE VISTAMENU
    public VistaCompras(){
        
        //Agregamos TITULO a nuestro PROGRAMA
          
        //Agregamos TITULO a nuestro PROGRAMA
        super("===> COMPRAS <===");//super() SIEMPRE TIENE QUE IR EL PRIMERO
        
        //Agregamos ICONO a nuestro PROGRAMA
        ImageIcon icono = new ImageIcon("src/Vistas/favicon1.png");
       this.setIconImage(icono.getImage()); 
        Iniciar();
    }//Fin del constructor


    private void Iniciar(){
    
       setLayout(null);

        //Inicializando CAMPOS y AREA de Texto (aqui indicaremos lo que queremos que tenga escrito dentro el campo, EN MI CASO ESTARAN VACIOS)
           
                Titulo = new JLabel("-APARTADO COMPRAS-");
        add(Titulo);
          Titulo.setBounds(570, 10, 300, 30);
                
          
        CIF = new JLabel("CIF:");
         add(CIF);
        CIF.setBounds(10, 50, 150, 30);
                
          cif = new JTextField("");
        cif.setEditable(false);
          add(cif);
        cif.setBounds(100, 50, 250, 30);
       
        
              Orden_Pedidos = new JLabel("NºOrd.PedidoS:");
         add(Orden_Pedidos);
        Orden_Pedidos.setBounds(385, 50, 150, 30);
             
        orden_pedidos = new JTextField("");
        orden_pedidos.setEditable(false);
       add(orden_pedidos);
        orden_pedidos.setBounds(500, 50, 250, 30);

        
    
            Fecha_compra = new JLabel("Fecha compra:");
            add(Fecha_compra);
        Fecha_compra.setBounds(905, 50, 150, 30);
        
        fecha_compra = new JTextField("");
        fecha_compra.setEditable(false);
       add(fecha_compra);
        fecha_compra.setBounds(1020, 50, 250, 30);
        
                        
        
            Codigo_articulo = new JLabel("Cod.articulo:");
       add(Codigo_articulo);
        Codigo_articulo.setBounds(5, 100, 150, 30);
        
        codigo_articulo = new JTextField("");
        add(codigo_articulo);
        codigo_articulo.setBounds(100, 100, 250, 30);
        
      
        
        
        
         Orden_pedido = new JLabel("Orden pedido:");
       add(Orden_pedido);
         Orden_pedido.setBounds(395, 100,  150, 30);
         
        orden_pedido = new JTextField("");
       add(orden_pedido);
       orden_pedido.setBounds(500, 100, 250, 30);
               
            Numero_albaran = new JLabel("Nº albarán:");
       add(Numero_albaran); 
        Numero_albaran.setBounds(940, 100,  150, 30);
        
         numero_albaran = new JTextField("");
         numero_albaran.setEditable(false);
       add(numero_albaran);
        numero_albaran.setBounds(1020, 100, 250, 30);
        
        
      
       

             Cantidad=new JLabel ("Cantidad:");
     add(Cantidad);
      Cantidad.setBounds(10, 150, 150, 30);
      
         cantidad = new JTextField("");
         add(cantidad);
        cantidad.setBounds(100, 150, 250, 30);
          
        
         Precio=new JLabel ("Precio:");
      add(Precio); 
      Precio.setBounds(450, 150, 150, 30);
        precio = new JTextField("");
         add(precio);
        precio.setBounds(500, 150, 250, 30);
        
        
     Descuento=new JLabel ("Descuento(%):");
     add(Descuento);
        Descuento.setBounds(900, 150, 150, 30);
       
         descuento = new JTextField("");
         add(descuento);
        descuento.setBounds(1020, 150, 250, 30);
        
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
        
        
        

        //Inicializando BOTONES y OYENTES (LES INDICAREMOS EL orden_pedidoe que llevarán)
        ButSalir= new JButton("Volver al Menú");
           add(ButSalir);
         ButSalir.setBounds(50, 220, 150, 50);
       
         
         
    //    ButSalir.addActionListener(new OyenteAlta());
        ButDelList= new JButton("Eliminar Lista");
      //  ButDelList.addActionListener(new OyenteBaja());
          add(ButDelList);
       ButDelList.setBounds(310, 220, 150, 50);
       
       
       
      ButBorrarArticulo= new JButton("Borrar Artículo");
      //  ButBorrarArticulo.addActionListener(new OyenteConsulta());
        add(ButBorrarArticulo);
       ButBorrarArticulo.setBounds(570, 220, 150, 50);
       
       
       
      ButFinalizarVenta= new JButton("FINAL.Compra");
       // ButFinalizarVenta.addActionListener(new OyenteListado());
        add(ButFinalizarVenta);
         ButFinalizarVenta.setBounds(830, 220, 150, 50);
      
         
         
     ButAgregarArticulo= new JButton("Añade ART.");
      //  ButInicializar.addActionListener(new OyenteInicializar());
            add(ButAgregarArticulo);
              ButAgregarArticulo.setBounds(1090, 220, 150, 50);
       //Añadimos ELEMENTOS A LA "VENTANA" (luego los ordenamos en el siguiente paso)

   
        
         Label_agregados = new JLabel("Articulos Existentes:");
        add(Label_agregados);
         Label_agregados.setBounds(10, 285, 300, 30);
         
        
      Label_existentes=new JLabel ("Articulos AGREGADOS:");
      add(Label_existentes);
       Label_existentes.setBounds(656, 285, 300, 30);
             //Añadiendo Campos y Area de Texto
     
        
      
      
       
        
         
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
        //Agregamos TEXTAREA al Scroll, como textArea está agregado a Scroll, será a este al que AGREGEMOS y ASIGNEMOS el tamaño mas abajo

           //Hacemos visible la ventana
       setSize(1300, 730);
        setLocation(30, 20);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //Seteamos el tamaño de la ventana de nuestro programa
    
           
    }
    
    
}

    
    
    
    
    
    
    
    

