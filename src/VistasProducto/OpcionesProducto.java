package VistasProducto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProductos;
import Controlador.ProductoBobo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class OpcionesProducto extends JFrame {

	private JPanel contentPane;
	private static OpcionesProducto instancia;


	/**
	 * Create the frame.
	 */
	public static OpcionesProducto getOpciones() {
		if (instancia == null) {
			instancia = new OpcionesProducto();
		}
		return instancia;
	}
	
	private OpcionesProducto() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super("Opciones de Producto");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCrearProducto = new JButton("Crear Producto");
		btnCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProducto ventanaCrear = CrearProducto.getCrearProducto();
				ventanaCrear.setVisible(true);
			}
		});
		btnCrearProducto.setBounds(104, 11, 183, 43);
		contentPane.add(btnCrearProducto);

		JButton btnModificarProducto = new JButton("<html> Modificar <br /> Producto</html>");
		btnModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarProductoParaModificar ventanaBuscar = BuscarProductoParaModificar.getBuscarProducto();
				ventanaBuscar.setVisible(true);
			}
		});
		btnModificarProducto.setBounds(104, 59, 183, 43);
		contentPane.add(btnModificarProducto);
		
		
		JButton btnListadoProductos = new JButton("<html> Listado <br />de <br /> Productos</html>");
		btnListadoProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioProductos ventanaFormulario = new FormularioProductos();
				ventanaFormulario.setVisible(true);
			}
		});
		btnListadoProductos.setBounds(104, 161, 183, 43);
		contentPane.add(btnListadoProductos);
		
		JButton btnEliminarProducto = new JButton("<html> Eliminar <br /> Productos</html>");
		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarProducto ventanaEliminar = EliminarProducto.getEliminarProducto();
				ventanaEliminar.setVisible(true);
			}
		});
		btnEliminarProducto.setBounds(104, 218, 183, 43);
		contentPane.add(btnEliminarProducto);
		
		JButton btnStockMinimo = new JButton("Stock Minimo");
		btnStockMinimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
				Vector<ProductoBobo> lista=adminP.getListadoProductosStockMinimo();
				if (lista.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No tiene Productos con Stock por debajo del Stock minimo","",JOptionPane.INFORMATION_MESSAGE);
				}else {
					FormularioStockMinimo formulario =  new FormularioStockMinimo(lista);
					formulario.setVisible(true);
				}
				
			}
		});
		btnStockMinimo.setBounds(104, 107, 183, 43);
		contentPane.add(btnStockMinimo);
	}

}
