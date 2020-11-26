package VistasVenta;

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

public class ProductosAgregadosAVenta extends JFrame {

	private JPanel contentPane;
	private static Vector<ProductoBobo> listado;
//	private static ProductosAgregadosAVenta instancia;



	/**
	 * Create the frame.
	 */
	/*
	public static ProductosAgregadosAVenta getProductosAgregados(Vector<ProductoBobo>listadoProducto) {
		if (instancia == null) {
			instancia = new ProductosAgregadosAVenta(listadoProducto);
		}
		return instancia;
	}
	*/
	public ProductosAgregadosAVenta(Vector<ProductoBobo> lista) {
		super("Productos Agregados a la Venta");
		listado = lista;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		int dimensionLista = lista.capacity();
		
		Object [] []datosFormulario = new  Object[dimensionLista] [4];// matriz donde se tomaran los datos para armar la trabla
		int cont = 0;
		for (ProductoBobo prod: lista) {
			datosFormulario[cont][0] = prod.getCantidad();
			datosFormulario[cont][1] = prod.getDescripcion();
			datosFormulario[cont][2] = prod.getCodigoBarra();
			datosFormulario[cont][3] = prod.getPrecio();
	
			cont++;
		}
	
		String [] columnaEncabezado = {"Cantidad","Descripcion","Cod. Barra","Precio"};// array que representan el encabezado  y subdivision de categorias
		
	
		final JTable tabla =new JTable(datosFormulario,columnaEncabezado);
		
		
		tabla.setPreferredScrollableViewportSize(new Dimension(500,80));
		
		
 		JScrollPane escrolearVentana = new JScrollPane(tabla);
 		
 		getContentPane().add(escrolearVentana, BorderLayout.CENTER);
		
	}

}
