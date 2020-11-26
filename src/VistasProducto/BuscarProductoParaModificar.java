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

public class BuscarProductoParaModificar extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodP;
	private static BuscarProductoParaModificar instancia;
	
	/**
	 * Create the frame.
	 */
	public static BuscarProductoParaModificar getBuscarProducto () {
		if (instancia == null) {
			instancia = new BuscarProductoParaModificar();
		}
		return instancia;
	}
	
	
	private BuscarProductoParaModificar() {
		super("Buscar Producto");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese Codigo de Producto:");
		lblNewLabel.setBounds(10, 110, 154, 14);
		contentPane.add(lblNewLabel);
		
		tfCodP = new JTextField();
		tfCodP.setText("");
		tfCodP.setBounds(188, 107, 220, 20);
		contentPane.add(tfCodP);
		tfCodP.setColumns(10);
		
		JButton btnListadoProducto = new JButton("Listado de Productos");
		btnListadoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioProductos ventanaFormularioProducto = new FormularioProductos();
				ventanaFormularioProducto.setVisible(true);
			}
		});
		btnListadoProducto.setBounds(111, 25, 200, 50);
		contentPane.add(btnListadoProducto);
		
		JButton btnBuscarProducto = new JButton("Buscar Producto");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
				
				String codAux = tfCodP.getText();
				if(codAux.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int cod = Integer.parseInt(codAux);
						if(adminP.existeProducto(cod)) {
							ModificarProducto ventanaModificarP = new ModificarProducto(adminP.consultarDatosProducto(cod));
							ventanaModificarP.setVisible(true);
							
							
							
						}else {
							JOptionPane.showMessageDialog(null, "Error. No se encontro ningun producto con el codigo: "+cod, "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}catch(Exception NumberFormatException) {
						JOptionPane.showMessageDialog(null, "Error. No ingresar caracteres en casillero", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnBuscarProducto.setBounds(10, 175, 200, 50);
		contentPane.add(btnBuscarProducto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instancia.dispose();
			}
		});
		btnCancelar.setBounds(224, 175, 200, 50);
		contentPane.add(btnCancelar);
	}

}
