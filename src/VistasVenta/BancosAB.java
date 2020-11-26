package VistasVenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorVentas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BancosAB extends JFrame {

	private JPanel contentPane;
	private JTextField txbanco;
	private static BancosAB instancia;


	/**
	 * Create the frame.
	 */
	public static BancosAB getBancosAB() {
		if (instancia == null) {
			instancia = new BancosAB();
		}
		return instancia;
	}

	public BancosAB() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Listado de Bancos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoBancos ventanaLB =  new ListadoBancos();
				ventanaLB.setVisible(true);
			}
		});
		btnNewButton.setBounds(119, 11, 193, 50);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Banco");
		lblNewLabel.setBounds(10, 114, 79, 18);
		contentPane.add(lblNewLabel);
		
		txbanco = new JTextField();
		txbanco.setBounds(113, 113, 199, 20);
		contentPane.add(txbanco);
		txbanco.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String banco = txbanco.getText();
				
				if (banco.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
					boolean respuesta =adminV.addBancoAlListado(banco);
					if (respuesta) {
						JOptionPane.showMessageDialog(null, "El banco "+banco + " se agrego con exito","",JOptionPane.INFORMATION_MESSAGE);
						txbanco.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Error.El banco "+banco + " ya esta en el listado de bancos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnAgregar.setBounds(52, 176, 133, 50);
		contentPane.add(btnAgregar);
		
		JButton btnSacar = new JButton("Sacar");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String banco = txbanco.getText();
				
				if (banco.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
					boolean respuesta =adminV.deleteBancoAlListado(banco);
					if (respuesta) {
						JOptionPane.showMessageDialog(null, "El banco "+banco + " se elimino con exito","",JOptionPane.INFORMATION_MESSAGE);
						txbanco.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Error.El banco "+banco + " no se encontro en el listado de bancos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSacar.setBounds(248, 176, 156, 50);
		contentPane.add(btnSacar);
	}
}
