package VistasCaja;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ElejirFecha extends JFrame {

	private JPanel contentPane;
	private JTextField txtMes;
	private JTextField txtAño;

	
	/**
	 * Create the frame.
	 */
	public ElejirFecha() {
		
		setBounds(100, 100, 415, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Por favor, Ingrese fecha como: MM / AAAA");
		lblNewLabel.setBounds(24, 11, 318, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Mes");
		lblNewLabel_2.setBounds(24, 75, 94, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("A\u00F1o");
		lblNewLabel_3.setBounds(24, 146, 94, 14);
		contentPane.add(lblNewLabel_3);
		
		txtMes = new JTextField();
		txtMes.setBounds(167, 72, 197, 20);
		contentPane.add(txtMes);
		txtMes.setColumns(10);
		
		txtAño = new JTextField();
		txtAño.setBounds(167, 143, 197, 20);
		contentPane.add(txtAño);
		txtAño.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mes = txtMes.getText();
				String año = txtAño.getText();
				
				if (mes.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (año.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int mesAux = Integer.valueOf(mes);
						int añoAux = Integer.valueOf(año);
						if (mesAux > 12) {
							JOptionPane.showMessageDialog(null, "Error. Ingresar un mes valido", "Error", JOptionPane.ERROR_MESSAGE);
						}else if (mesAux < 1) {
							JOptionPane.showMessageDialog(null, "Error. Ingresar un mes valido", "Error", JOptionPane.ERROR_MESSAGE);
						}else{
							ReporteMensualDiferenciado ventana1 = new ReporteMensualDiferenciado(mes,año);
							ventana1.setVisible(true);
							
							ReporteMensualSinDiferenciar ventana2 = new ReporteMensualSinDiferenciar(mes,año);
							ventana2.setLocationRelativeTo(null);
							ventana2.setVisible(true);
						}
						
					}catch(Exception NumberFormatException){
						JOptionPane.showMessageDialog(null, "Error. No ingrese caracteres o numeros con coma en los campos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnBuscar.setBounds(111, 193, 154, 36);
		contentPane.add(btnBuscar);
	}
}
