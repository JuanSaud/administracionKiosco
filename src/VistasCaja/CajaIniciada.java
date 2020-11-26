package VistasCaja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCajas;
import Controlador.AdministradorProductos;
import Controlador.ProductoBobo;
import VistasProducto.BuscarProductoParaModificar;
import VistasProducto.CrearProducto;
import VistasProducto.EliminarProducto;
import VistasProducto.FormularioProductos;
import VistasProducto.FormularioStockMinimo;
import VistasProducto.OpcionesProducto;
import VistasVenta.BancosAB;
import VistasVenta.IniciarVenta;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

public class CajaIniciada extends JFrame {

	private JPanel contentPane;
	private static CajaIniciada instancia;
	
	public static CajaIniciada getVentanaCajaInidiada() {
		if(instancia == null) {
			instancia = new CajaIniciada();
		}
		return instancia;
	}
	
	
	private CajaIniciada() {
		setTitle("Caja");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 307);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 11, 174, 22);
		contentPane.add(menuBar);
		
		JMenu mnMenuVentas = new JMenu("Ventas");
		menuBar.add(mnMenuVentas);
		
		JMenuItem mntmIniciarVenta = new JMenuItem("Iniciar Venta");
		mntmIniciarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarVenta ventanaIniciarVenta = new IniciarVenta();
				ventanaIniciarVenta.setVisible(true);	
				
				
			}
		});
		mnMenuVentas.add(mntmIniciarVenta);
		
		JMenu mnMenuProductos = new JMenu("Productos");
		menuBar.add(mnMenuProductos);
		
		JMenuItem mntmCrearProducto = new JMenuItem("Crear Producto");
		mntmCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo que quiero que se ejecute cuando el usuario hace click
				// instancio el objeto de la ventana "CrearProducto"
				CrearProducto ventanaProducto = CrearProducto.getCrearProducto();
				ventanaProducto.setVisible(true);
			}
		});
		mnMenuProductos.add(mntmCrearProducto);
		
		JMenuItem mntmModificarProducto = new JMenuItem("Modificar Producto");
		mntmModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarProductoParaModificar ventanaModificarN = BuscarProductoParaModificar.getBuscarProducto();
				ventanaModificarN.setVisible(true);
			}
		});
		mnMenuProductos.add(mntmModificarProducto);
		
		JMenuItem mntmEliminarProducto = new JMenuItem("Eliminar Producto");
		mntmEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarProducto ventanaEliminar = EliminarProducto.getEliminarProducto();
				ventanaEliminar.setVisible(true);
			}
		});
		mnMenuProductos.add(mntmEliminarProducto);
		
		JMenuItem mntmListadoDeProdcutos = new JMenuItem("Listado de Productos");
		mntmListadoDeProdcutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioProductos ventanaFormularioProductos = new FormularioProductos();
				ventanaFormularioProductos.setVisible(true);
			}
		});
		mnMenuProductos.add(mntmListadoDeProdcutos);
		
		JMenuItem mntmNewStockMinimo = new JMenuItem("Stock Minimo");
		mntmNewStockMinimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
				Vector<ProductoBobo> lista=adminP.getListadoProductosStockMinimo();
				if (lista.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No tiene Productos con Stock por debajo del Stock minimo","",JOptionPane.INFORMATION_MESSAGE);
				}else {
					FormularioStockMinimo formulario = new FormularioStockMinimo(lista);
					formulario.setVisible(true);
				}
				
			}
		});
		mnMenuProductos.add(mntmNewStockMinimo);
		
		JMenu mnMenuCaja = new JMenu("Caja");
		menuBar.add(mnMenuCaja);
		
		JMenuItem mntmEfectivoCaja = new JMenuItem("Efectivo en Caja");
		mntmEfectivoCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
				JOptionPane.showMessageDialog(null, "Tiene $"+adminC.getEfectivoEnCaja()+" en la caja","",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnMenuCaja.add(mntmEfectivoCaja);
		
		JMenuItem mntmCerrarCaja = new JMenuItem("Cerrar Caja");
		mntmCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarCerrarCaja ventanaConfirmar = ConfirmarCerrarCaja.getVentanaConfirmar();
				ventanaConfirmar.setVisible(true);
			}
		});
		mnMenuCaja.add(mntmCerrarCaja);
		
		JButton btnBanco = new JButton("Bancos");
		btnBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				BancosAB ventanaBancos = BancosAB.getBancosAB();
				ventanaBancos.setVisible(true);
			}
		});
		btnBanco.setBounds(146, 209, 160, 48);
		contentPane.add(btnBanco);
		
		
		JButton btnNuevaVenta = new JButton("Nueva Venta");
		btnNuevaVenta.setBackground(Color.green);
		btnNuevaVenta.setFont(new Font(Font.SERIF,Font.BOLD,15));
		
		btnNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarVenta ventanaIniciarVenta = new IniciarVenta();
				ventanaIniciarVenta.setVisible(true);
			}
		});
		btnNuevaVenta.setBounds(146, 57, 160, 48);
		contentPane.add(btnNuevaVenta);
		
		JButton btnProductos = new JButton("Producto");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcionesProducto ventanaOP =OpcionesProducto.getOpciones();
				ventanaOP.setVisible(true);
			}
		});
		btnProductos.setBounds(146, 131, 160, 48);
		contentPane.add(btnProductos);
		
		JButton btnCerrarCaja = new JButton("<html> Cerrar <br /> Caja</html>\"");
		btnCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarCerrarCaja ventanaConfirmar = ConfirmarCerrarCaja.getVentanaConfirmar();
				ventanaConfirmar.setVisible(true);
			}
		});
		btnCerrarCaja.setBackground(Color.red);
		btnCerrarCaja.setBounds(369, 209, 89, 48);
		contentPane.add(btnCerrarCaja);
		
		
		try {
			Image imagenVenta = ImageIO.read(getClass().getResource("/resources/venta.png"));
			Image redimensionVenta= imagenVenta.getScaledInstance(44, 44, Image.SCALE_SMOOTH);	
			ImageIcon iconoVenta = new ImageIcon(redimensionVenta);
			JLabel VentaLabel = new JLabel(iconoVenta);
			VentaLabel.setLocation(71, 57);
			VentaLabel.setSize(44, 44);
			contentPane.add(VentaLabel);
			

		}catch(Exception IOException) {
			JOptionPane.showMessageDialog(null, "No se encontro imagen","",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		try {
			Image imagenProducto = ImageIO.read(getClass().getResource("/resources/producto.png"));
			Image redimensionProducto= imagenProducto.getScaledInstance(44, 44, Image.SCALE_SMOOTH);	
			ImageIcon iconoProducto = new ImageIcon(redimensionProducto);
			JLabel ProdutcoLabel = new JLabel(iconoProducto);
			ProdutcoLabel.setLocation(71, 131);
			ProdutcoLabel.setSize(44, 44);
			contentPane.add(ProdutcoLabel);
			

		}catch(Exception IOException) {
			JOptionPane.showMessageDialog(null, "No se encontro imagen","",JOptionPane.INFORMATION_MESSAGE);
		}
		try {
			Image imagenBanco = ImageIO.read(getClass().getResource("/resources/banco.png"));
			Image redimensionBanco= imagenBanco.getScaledInstance(44, 44, Image.SCALE_SMOOTH);	
			ImageIcon iconoBanco = new ImageIcon(redimensionBanco);
			JLabel BancoLabel = new JLabel(iconoBanco);
			BancoLabel.setLocation(71, 209);
			BancoLabel.setSize(44, 44);
			contentPane.add(BancoLabel);
			
		
		}catch(Exception IOException) {
			JOptionPane.showMessageDialog(null, "No se encontro imagen","",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
