package VistasCaja;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCajas;
import Controlador.VentaBobo;

public class FormularioVentasEfectivo extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public FormularioVentasEfectivo(int nroCaja) {
		super("Formulario Ventas Efectivo");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
		Vector<VentaBobo>lista = adminC.generarReporte(nroCaja);
		int dimensionLista = lista.capacity();
		
		Object [] []datosFormulario = new  Object[dimensionLista] [5];// matriz donde se tomaran los datos para armar la trabla
		int cont = 0;
		String items;
		Vector<String> listaItems;
		for (VentaBobo v: lista) {
			if (v.getTipoVenta() == "Efectivo") {
				items = "";
				datosFormulario[cont][0] = v.getNumeroV();
				datosFormulario[cont][1] = v.getTotal();
				datosFormulario[cont][2] = v.getTipoVenta();			
				datosFormulario[cont][3] = v.getFecha();
				listaItems = v.getListaItems();
				for (String i: listaItems) {
					items = items + i +", ";			
				}
				datosFormulario[cont][4] = items; 
				cont++;
			}
		}
	
		String [] columnaEncabezado = {"Numero de Venta","Total $","Tipo de Venta","Fecha", "items Vendidos"};
		
		
		final JTable tabla =new JTable(datosFormulario,columnaEncabezado);
		
		
		tabla.setPreferredScrollableViewportSize(new Dimension(500,80));
		
		
 		JScrollPane escrolearVentana = new JScrollPane(tabla);
 		
 		getContentPane().add(escrolearVentana, BorderLayout.CENTER);
		
		
	}

}
