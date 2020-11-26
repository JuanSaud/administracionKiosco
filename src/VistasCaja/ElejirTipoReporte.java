package VistasCaja;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ElejirTipoReporte extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ElejirTipoReporte() {
	
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnElejirMes = new JButton("Elejir Mes");
		btnElejirMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElejirFecha ventanaElejir = new ElejirFecha();
				ventanaElejir.setVisible(true);
			}
		});
		btnElejirMes.setBounds(66, 49, 285, 55);
		contentPane.add(btnElejirMes);
		
		JButton btnMesActual = new JButton("Mes Actual");
		btnMesActual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate hoy = LocalDate.now();
				String fecha = String.valueOf(hoy);
				String año = fecha.substring(0, 4);
				String mes = fecha.substring(5, 7);
				ReporteMensualDiferenciado ventana1 = new ReporteMensualDiferenciado(mes,año);
				ventana1.setVisible(true);
				
				ReporteMensualSinDiferenciar ventana2 = new ReporteMensualSinDiferenciar(mes,año);
				ventana2.setLocationRelativeTo(null);
				ventana2.setVisible(true);
				
			}
		});
		btnMesActual.setBounds(66, 180, 285, 55);
		contentPane.add(btnMesActual);
	}

}
