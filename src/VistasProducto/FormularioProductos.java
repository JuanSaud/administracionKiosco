package VistasProducto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProductos;
import Controlador.ProductoBobo;

public class FormularioProductos extends JFrame {

	private JPanel contentPane;
	private static FormularioProductos instancia;
	
	/**
	 * Create the frame.
	 */
	
	
	public FormularioProductos() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super("Listado de Productos");// titulo
		AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Vector<ProductoBobo> listaProductos = adminP.getListadoProductosBobos();
		
		int dimensionLista = listaProductos.capacity();
		
		Object [] []datosFormulario = new  Object[dimensionLista] [5];// matriz donde se tomaran los datos para armar la trabla
		int cont = 0;
		for (ProductoBobo prod: listaProductos) {
			datosFormulario[cont][0] = prod.getDescripcion();
			datosFormulario[cont][1] = prod.getCodigoBarra();
			datosFormulario[cont][2] = prod.getPrecio();
			datosFormulario[cont][3] = prod.getStock();
			datosFormulario[cont][4] = prod.getStockMinimo();
			
			cont++;
		}
		
		
		
		
		
		String [] columnaEncabezado = {"Descripcion","Cod. Barra","$Precio$","Stock", "Stock Minimo"};// array que representan el encabezado  y subdivision de categorias
		
		// creacion de la tabla; recibe como parametros el formulario a llenar y luego las columnas encabezado
		final JTable tabla =new JTable(datosFormulario,columnaEncabezado);
		
		//ATENDER CON URGENCIA aca se define el tamño del formulario,si surgen problemas, fijarse aca primero
		tabla.setPreferredScrollableViewportSize(new Dimension(500,80));
		
		//configuro para que la ventana sea escroleable y mete la tabla adentro del scroll
 		JScrollPane escrolearVentana = new JScrollPane(tabla);
 		
 		getContentPane().add(escrolearVentana, BorderLayout.CENTER);
		
		
	}

}
