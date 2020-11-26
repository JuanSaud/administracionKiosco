package VistasCaja;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCajas;
import Controlador.Informe;


public class ReporteMensualSinDiferenciar extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public ReporteMensualSinDiferenciar(String mes, String año) {
		super("Reporte mensual");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
		Vector<Informe> lista = adminC.generarReporteMensual(mes, año);
		
		int dimensionLista = lista.capacity();
		
		Object [] []datosFormulario = new  Object[dimensionLista] [5];// matriz donde se tomaran los datos para armar la trabla
		int cont = 0;
		String apertura;
		String cierre ;
		Vector<String> listaItems;
		float total = 0;
		for (Informe i: lista) {
			apertura = String.valueOf(i.getHorarioApertura());
			cierre = String.valueOf( i.getHorarioCierre());
			datosFormulario[cont][0] = i.getFecha();
			datosFormulario[cont][1] = apertura.substring(0, 5);
			datosFormulario[cont][2] = cierre.substring(0, 5);			
			datosFormulario[cont][3] = i.getSaldoGeneral();
			datosFormulario[cont][4] = i.getVentasRealizadas(); 
			total = total + i.getSaldoGeneral();
			cont++;
		}
	
		String [] columnaEncabezado = {"Fecha","Apertura","Cierre","Capital Generado", "Total de Ventas"};
		
		
		final JTable tabla =new JTable(datosFormulario,columnaEncabezado);
		
		
		tabla.setPreferredScrollableViewportSize(new Dimension(500,80));
		
		
 		JScrollPane escrolearVentana = new JScrollPane(tabla);
 		
 		getContentPane().add(escrolearVentana, BorderLayout.CENTER);
		
 		JOptionPane.showMessageDialog(null, "El total vendidio en el mes "+mes+" fue de $"+total,"",JOptionPane.INFORMATION_MESSAGE);
		
	}

}
