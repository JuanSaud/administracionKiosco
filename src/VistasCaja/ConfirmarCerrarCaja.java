package VistasCaja;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCajas;
import Controlador.VentaBobo;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ConfirmarCerrarCaja extends JFrame {

	private JPanel contentPane;
	private static ConfirmarCerrarCaja instancia;
	
	/**
	 * Create the frame.
	 */
	public static ConfirmarCerrarCaja getVentanaConfirmar() {
		if(instancia == null) {
			instancia = new ConfirmarCerrarCaja();
		}
		return instancia;
	}
	
	
	private ConfirmarCerrarCaja() {
		super("Cerrar Caja");
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u00BFEsta seguro que desea cerrar la Caja?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(84, 11, 326, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
				int nroCaja = adminC.cerrarCajaDiaria();
				OpcionesReportes ventanaOR = new OpcionesReportes(nroCaja);
				ventanaOR.setVisible(true);
				
				CajaIniciada ventanaCajaI = CajaIniciada.getVentanaCajaInidiada();
				System.out.println("Se cerro la caja");
				ventanaCajaI.dispose();
				instancia.dispose();
				instancia = null;
				CajaIniciada ventanaCaja = CajaIniciada.getVentanaCajaInidiada();
				ventanaCaja.dispose();
		
			}
		});
		btnAceptar.setBounds(21, 71, 164, 47);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instancia.dispose();
				instancia = null;
			}
		});
		btnCancelar.setBounds(246, 71, 164, 47);
		contentPane.add(btnCancelar);
	}

}
