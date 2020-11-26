package VistasCaja;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCajas;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpcionesCaja extends JFrame {

	private JPanel contentPane;
	private static OpcionesCaja instancia;
	

	/**
	 * Create the frame.
	 */
	public static OpcionesCaja getOpcionesCaja () {
		if (instancia == null) {
			instancia = new OpcionesCaja();
		}
		return instancia;
	}
	
	
	private OpcionesCaja() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super("Opciones Caja");
		AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDevolverCaja = new JButton("Devolver Caja");
		btnDevolverCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!adminC.CajaNoEstaIniciada()) {
					//Habra que ver como se comporta con respecto al numero de caja
					
					CajaIniciada ventanaCajaIniciada = CajaIniciada.getVentanaCajaInidiada();
					ventanaCajaIniciada.setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "Error. No hay ninguna caja iniciada. Por favor seleccione la opcion de iniciar caja", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnDevolverCaja.setBounds(97, 62, 215, 40);
		contentPane.add(btnDevolverCaja);
		
		JButton btnSaldoDia = new JButton("Saldo del Dia");
		btnSaldoDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "El saldo hasta ahora es de: "+adminC.getSaldoDelDia(),"",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnSaldoDia.setBounds(97, 109, 215, 40);
		contentPane.add(btnSaldoDia);
		
		JButton btnVentasRealizadas = new JButton("Ventas Realizadas");
		btnVentasRealizadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// informa todas las ventas realizadas desde el dia 0
				JOptionPane.showMessageDialog(null, "El numero de ventas realizados hashta ahora son: "+adminC.getVentasTotales(),"",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnVentasRealizadas.setBounds(97, 160, 215, 40);
		contentPane.add(btnVentasRealizadas);
		
		JButton btnIniciarCaja = new JButton("Iniciar Caja");
		btnIniciarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(adminC.CajaNoEstaIniciada()) {
					
					EfectivoInicial ventanaEI = EfectivoInicial.getVentanaEI();
					ventanaEI.setVisible(true);
					instancia.dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "Ya hay una caja iniciada","",JOptionPane.INFORMATION_MESSAGE);
					CajaIniciada ventanaCajaIniciada = CajaIniciada.getVentanaCajaInidiada();
					ventanaCajaIniciada.setVisible(true);
				}

				
			}
		});
		btnIniciarCaja.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIniciarCaja.setBackground(Color.GREEN);
		btnIniciarCaja.setBounds(97, 11, 215, 40);
		contentPane.add(btnIniciarCaja);
		
		JButton btnCerrarCaja = new JButton("Cerrar Caja");
		btnCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adminC.CajaNoEstaIniciada()) {
					JOptionPane.showMessageDialog(null, "Error. No hay ninguna caja iniciada para cerrar.", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else {
					adminC.cerrarCajaDiaria();
					CajaIniciada ventanaCaja = CajaIniciada.getVentanaCajaInidiada();
					ventanaCaja.dispose();
					JOptionPane.showMessageDialog(null, "La caja se está cerrando","",JOptionPane.INFORMATION_MESSAGE);
					//activarreporte
				}
				
			}
			
			
			
		});
		btnCerrarCaja.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarCaja.setBackground(Color.RED);
		btnCerrarCaja.setBounds(97, 211, 215, 39);
		contentPane.add(btnCerrarCaja);
	}
}
