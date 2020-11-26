package VistasProducto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProductos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EliminarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField codProd;
	private static EliminarProducto instancia;
	

	/**
	 * Create the frame.
	 */
	public static EliminarProducto getEliminarProducto () {
		if (instancia == null) {
			instancia = new EliminarProducto();
		}
		return instancia;
	}
	
	private EliminarProducto() {
		setTitle("Eliminar Producto");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo de Producto");
		lblNewLabel.setBounds(10, 90, 116, 14);
		contentPane.add(lblNewLabel);
		
		codProd = new JTextField();
		codProd.setBounds(154, 87, 222, 20);
		contentPane.add(codProd);
		codProd.setColumns(10);
		
		JButton btnEliminarProdcuto = new JButton("Eliminar Producto");
		btnEliminarProdcuto.setBackground(Color.RED);
		btnEliminarProdcuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
				try {
					int cod = Integer.parseInt(codProd.getText());
					boolean respuesta = adminP.eliminarProducto(cod);
					
					if (respuesta) {
						JOptionPane.showMessageDialog(null, "El producto se elimino  exitosamente","",JOptionPane.INFORMATION_MESSAGE);
						codProd.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Error. No se encontro ningun producto con ese codigo", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception NumerFormatException){
					JOptionPane.showMessageDialog(null, "Error. No ingrese caracteres en los campos no permitidos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminarProdcuto.setBounds(92, 144, 228, 36);
		contentPane.add(btnEliminarProdcuto);
		
		JButton btnListadoProductos = new JButton("Listado de Productos");
		btnListadoProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioProductos ventanaListado = new FormularioProductos();
				ventanaListado.setVisible(true);
			}
		});
		btnListadoProductos.setBounds(113, 11, 177, 50);
		contentPane.add(btnListadoProductos);
	}
}
