package VistasVenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCajas;
import Controlador.AdministradorVentas;
import Controlador.ProductoBobo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class CerrarVentaDebito extends JFrame {

	private JPanel contentPane;
	private JTextField codigoAutorizacion;
	
	private JComboBox<String> cboBancosDisponibles;
	private static int nv;
	private static Vector<ProductoBobo> listado;
	private static CerrarVentaDebito instancia;
	
	/** 
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public static CerrarVentaDebito getCerrarDebito(int numeroVenta,Vector<ProductoBobo> lista) {
		if (instancia == null) {
			instancia = new CerrarVentaDebito( numeroVenta, lista);
		}
		return instancia;
		
	}
	
	
	private CerrarVentaDebito(int numeroVenta,Vector<ProductoBobo> lista) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
		nv = numeroVenta;
		listado = lista;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nro Venta:");
		lblNewLabel.setBounds(10, 21, 85, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNroVenta = new JLabel(String.valueOf(numeroVenta));
		lblNroVenta.setBounds(133, 21, 63, 14);
		contentPane.add(lblNroVenta);
		
		JLabel lblNewLabel_1 = new JLabel("Total:");
		lblNewLabel_1.setBounds(10, 58, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTotal = new JLabel(String.valueOf(adminV.calcularTotal(numeroVenta)));
		lblTotal.setBounds(133, 58, 46, 14);
		contentPane.add(lblTotal);
		
		JLabel lblNewLabel_2 = new JLabel("Banco");
		lblNewLabel_2.setBounds(10, 99, 85, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cod Autorizacion");
		lblNewLabel_3.setBounds(10, 143, 100, 14);
		contentPane.add(lblNewLabel_3);
		
		codigoAutorizacion = new JTextField();
		codigoAutorizacion.setBounds(133, 140, 176, 20);
		contentPane.add(codigoAutorizacion);
		codigoAutorizacion.setColumns(10);
		
		JButton btnFinalizarVenta = new JButton("Finalizar Venta");
		btnFinalizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean respuesta;
				String bancoAux;
				try {
					bancoAux = cboBancosDisponibles.getSelectedItem().toString();// el problema salta aca
					
				}catch(Exception NullPointerException) {
					bancoAux = "";
				}
				
				String codAutorizacion = codigoAutorizacion.getText();			
				if (bancoAux.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error. Elejir un banco", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (codAutorizacion.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. Ingrese codigo de autorizacion", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else {
					try {
						int codA = Integer.parseInt(codAutorizacion);
						respuesta = adminV.cerrarVentaDebito(numeroVenta, bancoAux, codA);
						if (respuesta){
							AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
							adminC.actualizarSaldo(numeroVenta);
							JOptionPane.showMessageDialog(null, "La venta se filaizo con exito","",JOptionPane.INFORMATION_MESSAGE);
							instancia.dispose();
							instancia = null;
						}
						
					}catch(Exception NumberFormatException) {
						JOptionPane.showMessageDialog(null, "Error. No ingrese caracteres en los campos no permitidos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnFinalizarVenta.setBounds(10, 212, 169, 23);
		contentPane.add(btnFinalizarVenta);
		
		JButton btnCancelarVenta = new JButton("Cancelar Venta");
		btnCancelarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminV.cancelarVenta(numeroVenta);
				listado.clear();
				instancia.dispose();
				instancia = null;
			}
		});
		btnCancelarVenta.setBounds(249, 212, 162, 23);
		contentPane.add(btnCancelarVenta);
		
		cboBancosDisponibles = new JComboBox<String>();
		cboBancosDisponibles.setBounds(133, 91, 195, 30);
		Vector<String> BancosDisponibles = adminV.getListadoDeBancos();
		for(String banco : BancosDisponibles) {// por alguna razon a veces funciona a veces no
			cboBancosDisponibles.addItem(banco);
		}	
		contentPane.add(cboBancosDisponibles);
		
		JButton btnProductos = new JButton("Productos Agregados");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// por alguna razon a veces funciona a veces no
				ProductosAgregadosAVenta ventana = new ProductosAgregadosAVenta(lista);
				ventana.setVisible(true);
			}
		});
		btnProductos.setBounds(239, 17, 148, 55);
		contentPane.add(btnProductos);
		
	}
}
