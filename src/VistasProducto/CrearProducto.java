package VistasProducto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProductos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearProducto extends JFrame {

	private JPanel contentPane;
	private JTextField nombreProducto;
	private JTextField CodBarra;
	private JTextField precioProducto;
	private JTextField stockProducto;
	private JTextField stockMinimoProducto;
	
	private static CrearProducto instancia;
	

	/**
	 * Create the frame.
	 */
	public static CrearProducto getCrearProducto () {
		if (instancia == null) {
			instancia = new CrearProducto();
		}
		return instancia;
	}
	
	
	private CrearProducto() {
		setTitle("Crear Producto Nacional");
		// saco el defailCloseOpertion para que cuando cierrre esta ventana no me cierre el menu principal
		// o la ventana desde la que es llamada
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Producto");
		lblNewLabel.setBounds(10, 5, 128, 14);
		contentPane.add(lblNewLabel);
		
		nombreProducto = new JTextField();
		nombreProducto.setBounds(174, 2, 231, 20);
		contentPane.add(nombreProducto);
		nombreProducto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo de Barra");
		lblNewLabel_1.setBounds(10, 35, 128, 14);
		contentPane.add(lblNewLabel_1);
		
		CodBarra = new JTextField();
		CodBarra.setBounds(174, 32, 231, 20);
		contentPane.add(CodBarra);
		CodBarra.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(10, 66, 71, 14);
		contentPane.add(lblNewLabel_2);
		
		precioProducto = new JTextField();
		precioProducto.setBounds(174, 63, 231, 20);
		contentPane.add(precioProducto);
		precioProducto.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setBounds(10, 109, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		stockProducto = new JTextField();
		stockProducto.setBounds(174, 106, 231, 20);
		contentPane.add(stockProducto);
		stockProducto.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Stock Minimo");
		lblNewLabel_4.setBounds(10, 155, 113, 14);
		contentPane.add(lblNewLabel_4);
		
		stockMinimoProducto = new JTextField();
		stockMinimoProducto.setText("");
		stockMinimoProducto.setBounds(175, 152, 230, 20);
		contentPane.add(stockMinimoProducto);
		stockMinimoProducto.setColumns(10);
		
		JButton btnNewButton_nacional = new JButton("Crear Producto");
		btnNewButton_nacional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
				String nmbreProd = nombreProducto.getText();
				if (nmbreProd.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. Ingresar un nombre al producto", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int cod =  Integer.parseInt(CodBarra.getText());
						int stock = Integer.parseInt(stockProducto.getText());
						int stockM = Integer.parseInt(stockMinimoProducto.getText());
						
						float precio = Float.parseFloat(precioProducto.getText());
				
	
						
						boolean respuesta = adminP.crearProducto(cod, nmbreProd, precio, stock, stockM);
						if (respuesta){
							JOptionPane.showMessageDialog(null, "El producto "+ nmbreProd + " se creo con exitosamente","",JOptionPane.INFORMATION_MESSAGE);
							//limpio lo campos de texto para continuar 
							nombreProducto.setText("");
							CodBarra.setText("");
							stockProducto.setText("");
							stockMinimoProducto.setText("");
							precioProducto.setText("");
							
							
						}else{
							JOptionPane.showMessageDialog(null, "Error. Un producto con codigo"+ cod + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception NumberFormatException){
						JOptionPane.showMessageDialog(null, "Error. No ingrese caracteres en los campos no permitidos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton_nacional.setBounds(21, 217, 174, 50);
		contentPane.add(btnNewButton_nacional);
		
		JButton btnNewButton_Importado = new JButton("Cancelar");
		btnNewButton_Importado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instancia.dispose();
			}
		});
		btnNewButton_Importado.setBounds(261, 217, 198, 50);
		contentPane.add(btnNewButton_Importado);
	}
}
