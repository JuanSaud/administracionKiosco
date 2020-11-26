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
import Controlador.Informe;

public class ReporteMensualDiferenciado extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public ReporteMensualDiferenciado(String mes, String año) {
		super("Reporte Mensual Diferenciado por Venta");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
		Vector<Informe> lista = adminC.generarReporteMensual(mes, año);
		
		int dimensionLista = lista.capacity();
		
		Object [] []datosFormulario = new  Object[dimensionLista] [8];// matriz donde se tomaran los datos para armar la trabla
		int cont = 0;
		String apertura; 
		String cierre; 
		Vector<String> listaItems;
		
		for (Informe i: lista) {
			apertura = String.valueOf(i.getHorarioApertura());
			cierre = String.valueOf( i.getHorarioCierre());
			datosFormulario[cont][0] = i.getFecha();			
			datosFormulario[cont][1] = apertura.substring(0, 5);			
			datosFormulario[cont][2] = cierre.substring(0, 5);
			datosFormulario[cont][3] = i.getCapitalVentasEfectivo();
			datosFormulario[cont][4] = i.getCapitalVentasDebito();
			datosFormulario[cont][5] = i.getCapitalVentasCredito();
			
			datosFormulario[cont][6] = i.getSaldoGeneral();
			datosFormulario[cont][7] = i.getVentasRealizadas();
			cont++;
		}
	
		String [] columnaEncabezado = {"Fecha","Apertura","Cierre","Capital Efectivo", "Capital Debito","Capital credito", "Capital General","VentasRealizadas"};
		
		
		final JTable tabla =new JTable(datosFormulario,columnaEncabezado);
		
		
		tabla.setPreferredScrollableViewportSize(new Dimension(500,80));
		
		
 		JScrollPane escrolearVentana = new JScrollPane(tabla);
 		
 		getContentPane().add(escrolearVentana, BorderLayout.CENTER);
		
		
		
	}

}
