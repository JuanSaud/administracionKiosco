package VistasVenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

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
import java.awt.event.ActionEvent;

public class CerrarVentaCredito extends JFrame {

	private JPanel contentPane;
	private JTextField ult4Digitos;
	private JTextField codAutorizacion;
	
	private JComboBox<String> cboBancosDisponibles;
	private static int nv;
	private static Vector<ProductoBobo> listado;
	
	private static CerrarVentaCredito instancia;
	
	

	

	/**
	 * Create the frame.
	 */
	public static CerrarVentaCredito getCerrarCredito(int numeroVenta,	Vector<ProductoBobo> lista) {
		if (instancia == null) {
			instancia = new CerrarVentaCredito(numeroVenta,lista);
		}
		return instancia;
	}
	
	
	private CerrarVentaCredito(int numeroVenta,	Vector<ProductoBobo> lista) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
		nv = numeroVenta;
		listado = lista;
		setBounds(100, 100, 479, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nro Venta");
		lblNewLabel.setBounds(10, 11, 85, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNroVenta = new JLabel(String.valueOf(numeroVenta));
		lblNroVenta.setBounds(105, 11, 78, 14);
		contentPane.add(lblNroVenta);
		
		JLabel lblNewLabel_1 = new JLabel("Total:");
		lblNewLabel_1.setBounds(10, 49, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTotal = new JLabel(String.valueOf(adminV.calcularTotal(numeroVenta)));
		lblTotal.setBounds(105, 49, 84, 14);
		contentPane.add(lblTotal);
		
		JLabel lblNewLabel_2 = new JLabel("Banco");
		lblNewLabel_2.setBounds(10, 92, 78, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ult 4 Digitos");
		lblNewLabel_3.setBounds(10, 134, 85, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Cod Autorizacion");
		lblNewLabel_4.setBounds(10, 174, 85, 14);
		contentPane.add(lblNewLabel_4);
		
		ult4Digitos = new JTextField();
		ult4Digitos.setBounds(105, 131, 195, 20);
		contentPane.add(ult4Digitos);
		ult4Digitos.setColumns(10);
		
		codAutorizacion = new JTextField();
		codAutorizacion.setBounds(105, 171, 195, 20);
		contentPane.add(codAutorizacion);
		codAutorizacion.setColumns(10);
		
		JButton btnNewButton = new JButton("Finalizar Venta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean respuesta;
				String bancoAux;
				try {
					bancoAux = cboBancosDisponibles.getSelectedItem().toString();
					
				}catch(Exception NullPointerException) {
					bancoAux = "";
				}
				
				String codAut = codAutorizacion.getText();
				String ult4 = ult4Digitos.getText();
				
				if (bancoAux.isBlank() ) {
					JOptionPane.showMessageDialog(null, "Error. Elejir un banco", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(codAut.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. Ingresar codigo de autorizacion", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (ult4.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. Ingrese los ultimos 4 digitos", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (ult4.length()<4) {//ult4.length()!=4
					JOptionPane.showMessageDialog(null, "Error. Se ingresaron menos de 4 digitos", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (ult4.length()>4) {
					JOptionPane.showMessageDialog(null, "Error. Se ingresaron mas de 4 digitos", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int codA = Integer.parseInt(codAut);
						int ultimos4 = Integer.parseInt(ult4);
						respuesta = adminV.cerrarVentaCredito(numeroVenta, bancoAux,codA,ultimos4) ;
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
		btnNewButton.setBounds(6, 235, 177, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar Venta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminV.cancelarVenta(numeroVenta);
				listado.clear();
				instancia.dispose();
				instancia = null;
				
			}
		});
		btnNewButton_1.setBounds(240, 235, 182, 23);
		contentPane.add(btnNewButton_1);
		
		cboBancosDisponibles = new JComboBox<String>();
		cboBancosDisponibles.setBounds(105, 84, 195, 30);
		Vector<String> BancosDisponibles = adminV.getListadoDeBancos();		
		for(String banco : BancosDisponibles) {
			cboBancosDisponibles.addItem(banco);
		}
		contentPane.add(cboBancosDisponibles);
		
		JButton btnProductosAgregados = new JButton("Prodcutos Agregados");
		btnProductosAgregados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductosAgregadosAVenta ventana = new ProductosAgregadosAVenta(lista);
				ventana.setVisible(true);
			}
		});
		btnProductosAgregados.setBounds(270, 7, 152, 57);
		contentPane.add(btnProductosAgregados);
		
	
		
	}
}
