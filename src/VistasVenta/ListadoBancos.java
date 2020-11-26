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

import Controlador.AdministradorVentas;
import Controlador.ProductoBobo;

public class ListadoBancos extends JFrame {

	private JPanel contentPane;
	//private static ListadoBancos instancia;


	/**
	 * Create the frame.
	 */

	
	public ListadoBancos() {
		super("Listado de Bancos");
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
		
		
		AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
		Vector<String> lista = adminV.getListadoDeBancos();
		int dimensionLista = lista.capacity();
		
		Object[][] datosFormulario = new String [dimensionLista][1];
		int cont = 0;
		for(String banco: lista) {
			datosFormulario[cont][0] = banco;
			cont++;
		}
		
		
		String [] columnaEncabezado = {"Banco"};// array que representan el encabezado  y subdivision de categorias
		
		// creacion de la tabla; recibe como parametros el formulario a llenar y luego las columnas encabezado
		final JTable tabla =new JTable(datosFormulario,columnaEncabezado);
		
		//ATENDER CON URGENCIA aca se define el tamño del formulario,si surgen problemas, fijarse aca primero
		tabla.setPreferredScrollableViewportSize(new Dimension(500,80));
		
		//configuro para que la ventana sea escroleable y mete la tabla adentro del scroll
 		JScrollPane escrolearVentana = new JScrollPane(tabla);
 		
 		getContentPane().add(escrolearVentana, BorderLayout.CENTER);
		
	}

}
