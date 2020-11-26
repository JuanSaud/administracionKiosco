package VistasCaja;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

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

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		super("Inicio");
		AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(20, 22, 118, 22);
		contentPane.add(menuBar);
		
		JMenu mnCaja = new JMenu("Caja");
		menuBar.add(mnCaja);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Iniciar Caja");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adminC.CajaNoEstaIniciada()) {
				
				EfectivoInicial ventanaEI = EfectivoInicial.getVentanaEI();
				ventanaEI.setVisible(true);
				
				}else {
					//Habra que ver como se comporta con respecto al numero de caja
					CajaIniciada ventanaCajaIniciada = CajaIniciada.getVentanaCajaInidiada();
					ventanaCajaIniciada.setVisible(true);
				}
			}
		});
		mnCaja.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Cerrar Caja");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adminC.CajaNoEstaIniciada()) {
					JOptionPane.showMessageDialog(null, "Error. No hay ninguna caja iniciada para cerrar.", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else {
					ConfirmarCerrarCaja ventanaConfirmar = ConfirmarCerrarCaja.getVentanaConfirmar();
					ventanaConfirmar.setVisible(true);
				}
				
			}
		});
		mnCaja.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Generar Reporte Mensual");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElejirTipoReporte ventanaRM = new ElejirTipoReporte();
				ventanaRM.setVisible(true);
			}
		});
		mnCaja.add(mntmNewMenuItem_5);
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenuItem mntmCrearProducto = new JMenuItem("Crear Producto");
		mntmCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProducto ventanaCrearProducto = CrearProducto.getCrearProducto();
				ventanaCrearProducto.setVisible(true);
			}
		});
		mnProductos.add(mntmCrearProducto);
		
		JMenuItem mntmModificarProducto = new JMenuItem("Modificar Producto");
		mntmModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarProductoParaModificar ventanaBuscarProducto = BuscarProductoParaModificar.getBuscarProducto();
				ventanaBuscarProducto.setVisible(true);
			}
		});
		mnProductos.add(mntmModificarProducto);
		
		JMenuItem mntmEliminarProducto = new JMenuItem("Eliminar Producto");
		mntmEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarProducto ventanaEliminarProd = EliminarProducto.getEliminarProducto();
				ventanaEliminarProd.setVisible(true);
			}
		});
		mnProductos.add(mntmEliminarProducto);
		
		JMenuItem mntmListadoProductos = new JMenuItem("Listado de Productos");
		mntmListadoProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioProductos ventanaListadoProd = new FormularioProductos();
				ventanaListadoProd.setVisible(true);
			}
		});
		mnProductos.add(mntmListadoProductos);
		
		JMenuItem mntmStockMinimo = new JMenuItem("Listado en Stock Minimo");
		mntmStockMinimo.addActionListener(new ActionListener() {
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
		mnProductos.add(mntmStockMinimo);
		
		JButton btnBancos = new JButton("Bancos");
		btnBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chequear si el bancos de esta ventana  y la otra ventana tienen los mismos datos
				//deberian
				BancosAB ventanaBancos = BancosAB.getBancosAB();
				ventanaBancos.setVisible(true);
			}
		});
		btnBancos.setBounds(132, 206, 160, 44);
		contentPane.add(btnBancos);
		
		JButton btnNewButton = new JButton("Productos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcionesProducto ventanaOP = OpcionesProducto.getOpciones();
				ventanaOP.setVisible(true);
			}
		});
		btnNewButton.setBounds(132, 147, 160, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Caja");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					OpcionesCaja ventanaOC = OpcionesCaja.getOpcionesCaja();
					ventanaOC.setVisible(true);
			
			
			}
		});
		btnNewButton_1.setBounds(132, 83, 160, 44);
		contentPane.add(btnNewButton_1);
		try {
			Image imagenCaja = ImageIO.read(getClass().getResource("/resources/caja.png"));
			Image redimensionCaja = imagenCaja.getScaledInstance(44, 44, Image.SCALE_SMOOTH);	
			ImageIcon iconoCaja = new ImageIcon(redimensionCaja);
			JLabel cajaLabel = new JLabel(iconoCaja);
			cajaLabel.setLocation(60, 80);
			cajaLabel.setSize(44, 44);
			contentPane.add(cajaLabel);
			
			
			ImageIcon iconoCaja2 = new ImageIcon(redimensionCaja);
			JLabel cajaLabel2 = new JLabel(iconoCaja2);
			cajaLabel2.setLocation(320, 80);
			cajaLabel2.setSize(44, 44);
			contentPane.add(cajaLabel2);
		
		}catch(Exception IOException) {
			JOptionPane.showMessageDialog(null, "No se encontro imagen","",JOptionPane.INFORMATION_MESSAGE);
		}
		try {
			Image imagenProducto = ImageIO.read(getClass().getResource("/resources/producto.png"));
			Image redimensionProducto= imagenProducto.getScaledInstance(44, 44, Image.SCALE_SMOOTH);	
			ImageIcon iconoProducto = new ImageIcon(redimensionProducto);
			JLabel ProdutcoLabel = new JLabel(iconoProducto);
			ProdutcoLabel.setLocation(60, 148);
			ProdutcoLabel.setSize(44, 44);
			contentPane.add(ProdutcoLabel);
			
				
			ImageIcon iconoProducto2 = new ImageIcon(redimensionProducto);
			JLabel ProdutcoLabel2 = new JLabel(iconoProducto2);
			ProdutcoLabel2.setLocation(320, 148);
			ProdutcoLabel2.setSize(44, 44);
			contentPane.add(ProdutcoLabel2);
		
		}catch(Exception IOException) {
			JOptionPane.showMessageDialog(null, "No se encontro imagen","",JOptionPane.INFORMATION_MESSAGE);
		}
		try {
			Image imagenBanco = ImageIO.read(getClass().getResource("/resources/banco.png"));
			Image redimensionBanco= imagenBanco.getScaledInstance(44, 44, Image.SCALE_SMOOTH);	
			ImageIcon iconoBanco = new ImageIcon(redimensionBanco);
			JLabel BancoLabel = new JLabel(iconoBanco);
			BancoLabel.setLocation(60, 210);
			BancoLabel.setSize(44, 44);
			contentPane.add(BancoLabel);
			
			ImageIcon iconoBanco2 = new ImageIcon(redimensionBanco);
			JLabel BancoLabel2 = new JLabel(iconoBanco2);
			BancoLabel2.setLocation(320, 210);
			BancoLabel2.setSize(44, 44);
			contentPane.add(BancoLabel2);
			
				
			
		
		}catch(Exception IOException) {
			JOptionPane.showMessageDialog(null, "No se encontro imagen","",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
}
