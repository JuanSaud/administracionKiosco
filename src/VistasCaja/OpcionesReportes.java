package VistasCaja;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.VentaBobo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpcionesReportes extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 */
	public OpcionesReportes(int nroCaja) {
		super("Reportes - Caja Cerrada");
		
		setBounds(100, 100, 447, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVentasGenerales = new JButton("Reporte Ventas General");
		btnVentasGenerales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioVentasGenerales ventanaVG = new FormularioVentasGenerales(nroCaja);
				ventanaVG.setVisible(true);
		
			}
		});
		btnVentasGenerales.setBounds(75, 11, 279, 48);
		contentPane.add(btnVentasGenerales);
		
		JButton btnVentasEfectivo = new JButton("Reporte Ventas Efectivo");
		btnVentasEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioVentasEfectivo ventanaVE = new FormularioVentasEfectivo(nroCaja);
				ventanaVE.setVisible(true);
		
			}
		});
		btnVentasEfectivo.setBounds(75, 82, 279, 48);
		contentPane.add(btnVentasEfectivo);
		
		JButton btnVentasDebito = new JButton("Reporte Ventas Debito");
		btnVentasDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioVentasDebito ventanaVD = new FormularioVentasDebito(nroCaja);
				ventanaVD.setVisible(true);
		
			}
		});
		btnVentasDebito.setBounds(75, 154, 279, 48);
		contentPane.add(btnVentasDebito);
		
		JButton btnVentasCredito = new JButton("Reporte Ventas Credito");
		btnVentasCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioVentasCredito ventanaVC = new FormularioVentasCredito(nroCaja);
				ventanaVC.setVisible(true);
		
			}
		});
		btnVentasCredito.setBounds(75, 220, 279, 48);
		contentPane.add(btnVentasCredito);
		
		JButton btnInformeGeneral = new JButton("Informe General del Dia");
		btnInformeGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeGeneral ventanaInforme = new InformeGeneral(nroCaja);
				ventanaInforme.setVisible(true);
				
			}
		});
		btnInformeGeneral.setBounds(75, 283, 279, 48);
		contentPane.add(btnInformeGeneral);
	}
}
