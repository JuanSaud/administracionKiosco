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
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ModificarProducto extends JFrame {

	private JPanel contentPane;

	private JTextField codBarra;
	private JLabel lblNewLabel_2;
	private JTextField precioProd;
	private JLabel lblNewLabel_3;
	private JTextField stockProd;
	private JLabel lblNewLabel_4;
	private JTextField stockMinimo;
	private JButton btnModificarProducto;
	private JTextField nombreProducto;
	private static Vector<String> vector; 

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ModificarProducto(Vector<String> datos) {
		//no descartar el uso de objetos bobos en vez de listas
		vector = datos;
		
		setTitle("Modificar Prodcuto");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de Producto");
		lblNewLabel.setBounds(10, 11, 134, 14);
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Codigo de Barra");
		lblNewLabel_1.setBounds(10, 51, 134, 14);
		contentPane.add(lblNewLabel_1);
		
		nombreProducto = new JTextField();
		nombreProducto.setBounds(177, 8, 295, 20);
		contentPane.add(nombreProducto);
		nombreProducto.setColumns(10);
		nombreProducto.setText(datos.elementAt(0));
		
		codBarra = new JTextField();
		codBarra.setBounds(177, 48, 295, 20);
		contentPane.add(codBarra);
		codBarra.setColumns(10);
		codBarra.setText(datos.elementAt(1));
		
		lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(10, 93, 119, 14);
		contentPane.add(lblNewLabel_2);
		
		precioProd = new JTextField();
		precioProd.setBounds(177, 90, 295, 20);
		contentPane.add(precioProd);
		precioProd.setColumns(10);
		precioProd.setText(datos.elementAt(2));
		
		lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setBounds(10, 138, 134, 14);
		contentPane.add(lblNewLabel_3);
		
		stockProd = new JTextField();
		stockProd.setBounds(177, 135, 295, 20);
		contentPane.add(stockProd);
		stockProd.setColumns(10);
		stockProd.setText(datos.elementAt(3));
		
		lblNewLabel_4 = new JLabel("Stock Minimo");
		lblNewLabel_4.setBounds(10, 180, 134, 14);
		contentPane.add(lblNewLabel_4);
		
		stockMinimo = new JTextField();
		stockMinimo.setBounds(177, 177, 295, 20);
		contentPane.add(stockMinimo);
		stockMinimo.setColumns(10);
		stockMinimo.setText(datos.elementAt(4));
		
		btnModificarProducto = new JButton("Modificar Producto");
		btnModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
				String nmbreProd = nombreProducto.getText();
				if (nmbreProd.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. Ingresar un nombre al producto", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int cod =  Integer.parseInt(codBarra.getText());
						int stock = Integer.parseInt(stockProd.getText());
						int stockM = Integer.parseInt(stockMinimo.getText());
						
						float precio = Float.parseFloat(precioProd.getText());
	
						
						boolean respuesta = adminP.modificarProducto(cod, nmbreProd, precio, stock, stockM);
						if (respuesta){
							JOptionPane.showMessageDialog(null, "El producto "+ nmbreProd + " se modifico con exito","",JOptionPane.INFORMATION_MESSAGE);
							//limpio lo campos de texto para continuar 
							nombreProducto.setText("");
							codBarra.setText("");
							stockProd.setText("");
							stockMinimo.setText("");
							precioProd.setText("");
							
						}else{
							JOptionPane.showMessageDialog(null, "Error. El producto con el codigo"+ cod + " no se encontro o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception NumerFormatException){
						JOptionPane.showMessageDialog(null, "Error. No ingrese caracteres en los campos no permitidos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnModificarProducto.setBounds(121, 265, 262, 36);
		contentPane.add(btnModificarProducto);
		
		
	}
}
