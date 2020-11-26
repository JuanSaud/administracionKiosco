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

import Controlador.ProductoBobo;

public class FormularioStockMinimo extends JFrame {

	private JPanel contentPane;
	private static Vector<ProductoBobo> listado;
	
	

	/**
	 * Create the frame.
	 */
	

	
	public FormularioStockMinimo(Vector<ProductoBobo> lista) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super("Productos para reponer");
		listado = lista;
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		int dimensionLista = lista.capacity();
		
		Object[][] datosFormulario = new Object [dimensionLista][4];
		int cont = 0;
		for(ProductoBobo prod: lista) {
			datosFormulario[cont][0] = prod.getDescripcion();
			datosFormulario[cont][1] = prod.getStock();
			datosFormulario[cont][2] = prod.getCodigoBarra();
			datosFormulario[cont][3] = prod.getStockMinimo();
			cont++;
		}
		
		
		String [] columnaEncabezado = {"Producto","Stock","Codigo","Minimo"};// array que representan el encabezado  y subdivision de categorias
		
		// creacion de la tabla; recibe como parametros el formulario a llenar y luego las columnas encabezado
		final JTable tabla =new JTable(datosFormulario,columnaEncabezado);
		
		//ATENDER CON URGENCIA aca se define el tamño del formulario,si surgen problemas, fijarse aca primero
		tabla.setPreferredScrollableViewportSize(new Dimension(500,80));
		
		//configuro para que la ventana sea escroleable y mete la tabla adentro del scroll
 		JScrollPane escrolearVentana = new JScrollPane(tabla);
 		
 		getContentPane().add(escrolearVentana, BorderLayout.CENTER);
		
	}

}
