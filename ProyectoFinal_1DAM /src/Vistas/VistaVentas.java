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
public class VistaVentas extends JFrame {

    //DECLARANDO TODOS LOS ELEMENTOS QUE COMPONEN NUESTRA VENTANA
    //Declarando Campos y Area de Texto 
    public static JTextField nif;
    public static JTextField cantidad;
    public static JTextField numero_albaran;
    public static JTextField codigo_articulo;
    public static JTextField fecha_venta;

    public static JTextArea TextArea_existentes;
    public static JTextArea TextArea_agregados;

    //Declarando SCROLLPane para agregar el TExtArea a este mas abajo
    JScrollPane scrollListado;
    JScrollPane scrollSalida;
    //Declarando Etiquetas
    JLabel Titulo;
    JLabel NIF;
    JLabel Numero_albaran;
    JLabel Cantidad;
    JLabel Codigo_articulo;
    JLabel Fecha_venta;
    JLabel Label_existentes;
    JLabel Label_agregados;
    //Declarando Botones
    public JButton ButSalir;
    public JButton ButDelList;
    public JButton ButBorrarArticulo;
    public JButton ButFinalizarVenta;
    public JButton ButAgregarArticulo;

    /////////////////////////////////////
    //Constructor de La CLASE VISTAMENU
    public VistaVentas() {
        //Agregamos TITULO a nuestro PROGRAMA
        super("===> VENTAS <===");//super() SIEMPRE TIENE QUE IR EL PRIMERO
        //Agregamos ICONO a nuestro PROGRAMA
        ImageIcon icono = new ImageIcon("src/Vistas/favicon1.png");
        this.setIconImage(icono.getImage());

        Iniciar();
    }//Fin del constructor

    private void Iniciar() {
        setLayout(null);

        Titulo = new JLabel("-APARTADO VENTAS-");
        add(Titulo);
        Titulo.setBounds(570, 35, 300, 30);

        NIF = new JLabel("NIF:");
        add(NIF);
        NIF.setBounds(50, 75, 150, 30);

        nif = new JTextField("");
        nif.setEditable(false);
        add(nif);
        nif.setBounds(100, 75, 250, 30);

        Numero_albaran = new JLabel("NºAlbarán:");

        add(Numero_albaran);
        Numero_albaran.setBounds(450, 75, 150, 30);

        numero_albaran = new JTextField("");
        numero_albaran.setEditable(false);
        add(numero_albaran);
        numero_albaran.setBounds(550, 75, 250, 30);

        Fecha_venta = new JLabel("Fecha venta:");
        add(Fecha_venta);
        Fecha_venta.setBounds(900, 75, 150, 30);

        fecha_venta = new JTextField("");
        fecha_venta.setEditable(false);
        add(fecha_venta);
        fecha_venta.setBounds(1020, 75, 250, 30);

        Codigo_articulo = new JLabel("Cod.articulo:");
        add(Codigo_articulo);
        Codigo_articulo.setBounds(245, 150, 150, 30);

        codigo_articulo = new JTextField("");
        add(codigo_articulo);
        codigo_articulo.setBounds(340, 150, 250, 30);

        Cantidad = new JLabel("Cantidad:");
        add(Cantidad);
        Cantidad.setBounds(705, 150, 150, 30);

        cantidad = new JTextField("");
        add(cantidad);
        cantidad.setBounds(800, 150, 250, 30);

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
        ButSalir = new JButton("Volver al Menú");
        add(ButSalir);
        ButSalir.setBounds(50, 220, 150, 50);

        //    ButSalir.addActionListener(new OyenteAlta());
        ButDelList = new JButton("Eliminar Lista");
        //  ButDelList.addActionListener(new OyenteBaja());
        add(ButDelList);
        ButDelList.setBounds(310, 220, 150, 50);

        ButBorrarArticulo = new JButton("Borrar Artículo");
        //  ButBorrarArticulo.addActionListener(new OyenteConsulta());
        add(ButBorrarArticulo);
        ButBorrarArticulo.setBounds(570, 220, 150, 50);

        ButFinalizarVenta = new JButton("FINAL.Venta");
        // ButFinalizarVenta.addActionListener(new OyenteListado());
        add(ButFinalizarVenta);
        ButFinalizarVenta.setBounds(830, 220, 150, 50);

        ButAgregarArticulo = new JButton("Añade ART.");
        //  ButInicializar.addActionListener(new OyenteInicializar());
        add(ButAgregarArticulo);
        ButAgregarArticulo.setBounds(1090, 220, 150, 50);
        //Añadimos ELEMENTOS A LA "VENTANA" (luego los ordenamos en el siguiente paso)

        Label_agregados = new JLabel("Articulos Existentes:");
        add(Label_agregados);
        Label_agregados.setBounds(10, 285, 300, 30);

        Label_existentes = new JLabel("Articulos AGREGADOS:");
        add(Label_existentes);
        Label_existentes.setBounds(656, 285, 300, 30);
        //Añadiendo Campos y Area de Texto

        TextArea_existentes = new JTextArea("");//INicializando al TextArea 
        TextArea_existentes.setEditable(false);//Haciendo que TextArea NO SEA EDITABLE
        scrollListado = new JScrollPane(TextArea_existentes);//Agregando SCROLL a TextArea
        add(scrollListado); //AÑADIMOS scroll EN VEZ DE RESULTADO porque TextArea_existentes YA ESTA DENTRO DE SCROLL
        scrollListado.setBounds(5, 320, 644, 370);

        TextArea_agregados = new JTextArea("");//INicializando al TextArea 
        TextArea_agregados.setEditable(false);//Haciendo que TextArea NO SEA EDITABLE
        scrollSalida = new JScrollPane(TextArea_agregados);//Agregando SCROLL a TextArea
        add(scrollSalida);
        scrollSalida.setBounds(651, 320, 644, 370);
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
