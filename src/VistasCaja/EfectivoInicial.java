package VistasCaja;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCajas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EfectivoInicial extends JFrame {

	private JPanel contentPane;
	private JTextField textEfectivo;
	private static EfectivoInicial instancia;
	
	

	/**
	 * Create the frame.
	 */
	public static EfectivoInicial getVentanaEI() {
		if (instancia == null) {
			instancia = new EfectivoInicial();
		}
		return instancia;
	}
	
	
	private EfectivoInicial() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super("Ingresar Efectivo Comienzo del dia");
		setBounds(100, 100, 350, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingresar efectivo en que comienza la caja:");
		lblNewLabel.setBounds(10, 11, 234, 26);
		contentPane.add(lblNewLabel);
		
		textEfectivo = new JTextField();
		textEfectivo.setBounds(52, 48, 219, 20);
		contentPane.add(textEfectivo);
		textEfectivo.setColumns(10);
		
		JButton btnIniciarCaja = new JButton("Iniciar Caja");
		btnIniciarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String efectivoAux = textEfectivo.getText();
				if (efectivoAux.isBlank()) {
					JOptionPane.showMessageDialog(null, "No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
						// y ahora que?
						float efectivo = Float.parseFloat(efectivoAux);
						adminC.iniciarCajaDiaria(efectivo);
						CajaIniciada ventanaCajaIniciada = CajaIniciada.getVentanaCajaInidiada();
						ventanaCajaIniciada.setVisible(true);
						textEfectivo.setText("");
						instancia.dispose();
						
						
					}catch(Exception NumberFormatException) {
						JOptionPane.showMessageDialog(null, "No ingresar caracteres en el cuadro de texto", "Error", JOptionPane.ERROR_MESSAGE);
					}
				
				}
				
			}
		});
		btnIniciarCaja.setBounds(22, 105, 104, 49);
		contentPane.add(btnIniciarCaja);
		
		JLabel lblNewLabel_1 = new JLabel("$");
		lblNewLabel_1.setBounds(20, 51, 22, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instancia.dispose();
				
			}
		});
		btnCancelar.setBounds(184, 105, 104, 49);
		contentPane.add(btnCancelar);
	}
}
