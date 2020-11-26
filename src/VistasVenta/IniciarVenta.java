package VistasVenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProductos;
import Controlador.AdministradorVentas;
import Controlador.ProductoBobo;

import VistasProducto.FormularioProductos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class IniciarVenta extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodProd;
	private JTextField tfCantProd;
	private Vector<ProductoBobo> listadoProducto;
	private static IniciarVenta instancia;

	private JTextField consultarPrecioProducto;
	
	
	
	
	public IniciarVenta() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
		AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
		
		listadoProducto = new Vector<ProductoBobo>();
		
		
		setBounds(100, 100, 506, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consultar Precio");
		lblNewLabel.setBounds(10, 11, 114, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo de Producto");
		lblNewLabel_1.setBounds(10, 122, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(10, 175, 114, 14);
		contentPane.add(lblNewLabel_2);
		
		tfCodProd = new JTextField();
		tfCodProd.setBounds(134, 119, 193, 20);
		contentPane.add(tfCodProd);
		tfCodProd.setColumns(10);
		
		tfCantProd = new JTextField();
		tfCantProd.setBounds(134, 172, 193, 20);
		contentPane.add(tfCantProd);
		tfCantProd.setColumns(10);
		
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ya_estaba_en_lista = false;
				
				String codAux = tfCodProd.getText();
				String cantAux =  tfCantProd.getText();
				if (codAux.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(cantAux.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					
			
					try {
						int codProd = Integer.parseInt(codAux);
						int cantProd = Integer.parseInt(cantAux);
						ProductoBobo prodB = adminP.getProductoBobo(codProd);
						if (prodB!=null) {
							if (cantProd> prodB.getStock()) {
								//verifico que la cantidad no se apase del stock que tengo
								JOptionPane.showMessageDialog(null, "Error. La cantidad del producto: "+ prodB.getDescripcion() +" esta excediendo el stock que tiene: "+ prodB.getStock(), "Error", JOptionPane.ERROR_MESSAGE);
								//tfCodProd.setText("");
								tfCantProd.setText("");
							}else {
								//verifico que el producto no estuviera en la lista y de estarlo, le sumo
								// la cantidad a agregar
								for (ProductoBobo prod: listadoProducto) {
									if (prod.getCodigoBarra() == prodB.getCodigoBarra()) {
										prod.setCantidad(prod.getCantidad() + cantProd);
										ya_estaba_en_lista = true;
									}
								}
								if(!ya_estaba_en_lista) {
									prodB.setCantidad(cantProd);
									listadoProducto.add(prodB);
								}
								
								tfCodProd.setText("");
								tfCantProd.setText("");
								
								System.out.println("se agrego "+cantProd + " unidad/es del producto "+prodB.getDescripcion());
							
							}						
						}else {
							JOptionPane.showMessageDialog(null, "Error. No existe ningun producto con codigo: "+ codProd, "Error", JOptionPane.ERROR_MESSAGE);
						}
					
						
						
					}catch(Exception NumberFormatException){
						JOptionPane.showMessageDialog(null, "Error. No ingrese caracteres o numeros con coma en los campos", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			
			}
		});
		btnAgregarProducto.setBounds(27, 216, 166, 23);
		contentPane.add(btnAgregarProducto);
		
		JButton btnSacarProducto = new JButton("Sacar Producto");
		btnSacarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codAux = tfCodProd.getText();
				String cantAux =  tfCantProd.getText();
				if (codAux.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(cantAux.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					
					try {
						int codProd = Integer.parseInt(codAux);
						int cantProd = Integer.parseInt(cantAux);
						ProductoBobo prodB = adminP.getProductoBobo(codProd);
						if (prodB!=null) {
							for (ProductoBobo prod: listadoProducto) {
								if (prod.getCodigoBarra() == prodB.getCodigoBarra()) {
									
									if (prod.getCantidad() - cantProd <= 0) {// por algnuna razon esto hace saltar el catch
										listadoProducto.remove(prod);
									}else {
										prod.setCantidad(prod.getCantidad() - cantProd);
									}
									
									
								}
							}
	
							tfCodProd.setText("");
							tfCantProd.setText("");
							
							System.out.println("se quito "+cantProd + " unidad/es del producto "+prodB.getDescripcion());
							
							
						
						}else {
							JOptionPane.showMessageDialog(null, "Error. No existe ningun producto con codigo: "+ codProd, "Error", JOptionPane.ERROR_MESSAGE);
						}
					
					}catch(Exception NumberFormatException){
						JOptionPane.showMessageDialog(null, "Error. No ingrese caracteres o numeros con coma en los campos", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
					
				
				
			
			}
		});
		btnSacarProducto.setBounds(241, 216, 193, 23);
		contentPane.add(btnSacarProducto);
		
		JButton btnEfectivo = new JButton("Efectivo");
		btnEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listadoProducto.isEmpty()) {// evito iniciar una venta sin productos
					int numeroVenta=adminV.iniciarVentaEfectivo();
					boolean respuesta;
					for (ProductoBobo prod: listadoProducto) {
						respuesta = adminV.agregarProductoAVenta(numeroVenta, prod.getCodigoBarra(), prod.getCantidad());
						if (!respuesta) {
							//no deberia saltar
							JOptionPane.showMessageDialog(null, "Error. No se reconoce el codigo: "+prod.getCodigoBarra(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				
					CerrarVentaEfectivo ventanaCerarEfectivo = CerrarVentaEfectivo.getCerrarEfectivo(numeroVenta,listadoProducto);
					ventanaCerarEfectivo.setVisible(true);
					listadoProducto = new Vector<ProductoBobo>();
				}else {
					JOptionPane.showMessageDialog(null, "Error. No estas vendiendo ningun producto", "Error", JOptionPane.ERROR_MESSAGE);
				}
			
			
			}
		});
		btnEfectivo.setBounds(10, 283, 131, 39);
		contentPane.add(btnEfectivo);
		
		JButton btnDebito = new JButton("Debito");
		btnDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listadoProducto.isEmpty()) {// evito iniciar una venta sin productos
					int numeroVenta=adminV.iniciarVentaDebito();
					boolean respuesta;
					for (ProductoBobo prod: listadoProducto) {
						respuesta = adminV.agregarProductoAVenta(numeroVenta, prod.getCodigoBarra(), prod.getCantidad());
						if (!respuesta) {
							//no deberia saltar
							JOptionPane.showMessageDialog(null, "Error. No se reconoce el codigo: "+ prod.getCodigoBarra(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					CerrarVentaDebito ventanaCerarDebito =  CerrarVentaDebito.getCerrarDebito(numeroVenta,listadoProducto);
					ventanaCerarDebito.setVisible(true);
					listadoProducto = new Vector<ProductoBobo>();
				}else {
					JOptionPane.showMessageDialog(null, "Error. No estas vendiendo ningun producto", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			
			}
		});
		btnDebito.setBounds(168, 283, 131, 39);
		contentPane.add(btnDebito);
		
		JButton btnCredito = new JButton("Credito");
		btnCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listadoProducto.isEmpty()) {// evito iniciar una venta sin productos
					int numeroVenta=adminV.iniciarVentaCredito();
					boolean respuesta;
					for (ProductoBobo prod: listadoProducto) {
						respuesta = adminV.agregarProductoAVenta(numeroVenta, prod.getCodigoBarra(), prod.getCantidad());
						if (!respuesta) {
							//no deberia saltar
							JOptionPane.showMessageDialog(null, "Error. No se reconoce el codigo: "+ prod.getCodigoBarra(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					
					CerrarVentaCredito ventanaCerarCredito =CerrarVentaCredito.getCerrarCredito(numeroVenta,listadoProducto);
					ventanaCerarCredito.setVisible(true);
					listadoProducto = new Vector<ProductoBobo>();
				}else {
					JOptionPane.showMessageDialog(null, "Error. No estas vendiendo ningun producto", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnCredito.setBounds(320, 283, 127, 39);
		contentPane.add(btnCredito);
		
		consultarPrecioProducto = new JTextField();
		consultarPrecioProducto.setBounds(134, 8, 193, 20);
		contentPane.add(consultarPrecioProducto);
		consultarPrecioProducto.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorProductos adminP = AdministradorProductos.getAdministradorProducto();
				String consulta = consultarPrecioProducto.getText();
				if (consulta.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casilleros en blanco", "Error", JOptionPane.ERROR_MESSAGE);	
				}else {
					try {
						int codigo = Integer.parseInt(consulta);
						if(adminP.existeProducto(codigo)) {
							float precio = adminP.consultarPrecio(codigo);
							String descripcion = adminP.consultarNombre(codigo);
							JOptionPane.showMessageDialog(null, "El producto "+ descripcion + " tiene un precio de $"+precio,"",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Error. No se reconoce ningun producto con el codigo "+codigo, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception NumberFormatException){
						JOptionPane.showMessageDialog(null, "Error. No Ingresar caracteres en el casillero", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnConsultar.setBounds(166, 50, 120, 29);
		contentPane.add(btnConsultar);
		
		JButton btnProductosAgregados = new JButton();
		btnProductosAgregados.setText("<html> Productos<br />Agregados </html>");
		btnProductosAgregados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductosAgregadosAVenta ventana =  new ProductosAgregadosAVenta(listadoProducto);
				ventana.setVisible(true);
			}
		});
		btnProductosAgregados.setBounds(360, 122, 120, 69);
		contentPane.add(btnProductosAgregados);
		
		JButton btnFormularioProductos = new JButton("Productos");
		btnFormularioProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioProductos ventanaFormulario =  new FormularioProductos();
				ventanaFormulario.setVisible(true);
			}
		});
		btnFormularioProductos.setBounds(360, 11, 120, 68);
		contentPane.add(btnFormularioProductos);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
