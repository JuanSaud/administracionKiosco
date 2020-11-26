package VistasCaja;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCajas;
import Controlador.Informe;

import javax.swing.JLabel;

public class InformeGeneral extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public InformeGeneral(int nroCaja) {
		super("Balanceo del dia");
		setBounds(100, 100, 415, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
		Informe i = adminC.generarInforme(nroCaja);
		
		JLabel lblNewLabel = new JLabel("Fecha: ");
		lblNewLabel.setBounds(15, 7, 150, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Horario de Apertura: ");
		lblNewLabel_1.setBounds(15, 43, 150, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Horario de Cierre: ");
		lblNewLabel_2.setBounds(15, 79, 157, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total de Ventas Realizadas: ");
		lblNewLabel_3.setBounds(15, 115, 183, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ventas en Efectivo:");
		lblNewLabel_4.setBounds(15, 155, 150, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ventas con Tarjeta de Debito:");
		lblNewLabel_5.setBounds(15, 191, 183, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Ventas con Tarjeta de Credito:");
		lblNewLabel_6.setBounds(15, 228, 183, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Efectivo en Caja al Cerrar:  ");
		lblNewLabel_7.setBounds(15, 266, 183, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Capital Total del dia:");
		lblNewLabel_8.setBounds(15, 309, 150, 14);
		contentPane.add(lblNewLabel_8);
		
		String fecha = String.valueOf(i.getFecha()) ;
		JLabel lblFecha = new JLabel(fecha);
		lblFecha.setBounds(220, 12, 130, 14);
		contentPane.add(lblFecha);
		
		
		String apertura = String.valueOf(i.getHorarioApertura());
		JLabel lblApertura = new JLabel(apertura.substring(0, 5));
		lblApertura.setBounds(220, 43, 112, 14);
		contentPane.add(lblApertura);
		
		String cierre = String.valueOf(i.getHorarioCierre());
		JLabel lblCierre = new JLabel(cierre.substring(0, 5));
		lblCierre.setBounds(220, 79, 101, 14);
		contentPane.add(lblCierre);
		
		String vt= String.valueOf(i.getVentasRealizadas()); 
		JLabel lblVentasTotal = new JLabel(vt);
		lblVentasTotal.setBounds(220, 115, 89, 14);
		contentPane.add(lblVentasTotal);
		
		String ve = String.valueOf(i.getVentasEfectivoRealizadas());
		JLabel lblVentasE = new JLabel(ve);
		lblVentasE.setBounds(220, 155, 89, 14);
		contentPane.add(lblVentasE);
		
		String vd = String.valueOf(i.getVentasDebitoRealizadas());
		JLabel lblVentasD = new JLabel(vd);
		lblVentasD.setBounds(220, 191, 130, 14);
		contentPane.add(lblVentasD);
		
		String vc = String.valueOf(i.getVentasCreditoRealizadas());
		JLabel lblVentasC = new JLabel(vc);
		lblVentasC.setBounds(220, 228, 89, 14);
		contentPane.add(lblVentasC);
		
		String efectivo =String.valueOf(i.getSaldoEfectivoFinal());
		JLabel lblEfectivo = new JLabel(efectivo);
		lblEfectivo.setBounds(220, 266, 89, 14);
		contentPane.add(lblEfectivo);
		
		String capital = String.valueOf(i.getSaldoGeneral());
		JLabel lblCapital = new JLabel(capital);
		lblCapital.setBounds(220, 309, 112, 14);
		contentPane.add(lblCapital);
	}
}
