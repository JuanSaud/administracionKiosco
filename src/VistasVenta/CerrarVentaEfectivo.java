package VistasVenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

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
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class CerrarVentaEfectivo extends JFrame {

	private JPanel contentPane;
	private JTextField abono;
	private static int nv;
	private static Vector<ProductoBobo> listado;
	private static CerrarVentaEfectivo instancia;
	
	/**
	 * Create the frame.
	 */
	public static CerrarVentaEfectivo getCerrarEfectivo(int numeroVenta,Vector<ProductoBobo> lista) {
		if (instancia == null) {
			instancia = new CerrarVentaEfectivo( numeroVenta,lista);
		}
		return instancia;
	}
	
	private CerrarVentaEfectivo(int numeroVenta,Vector<ProductoBobo> lista) {
		listado = lista;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nv = numeroVenta;
		AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
		float total = adminV.calcularTotal(numeroVenta);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Total: ");
		lblNewLabel.setBounds(20, 61, 84, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTotal = new JLabel(String.valueOf(total));// preguntar si esta mal
		lblTotal.setBounds(99, 61, 84, 14);
		contentPane.add(lblTotal);
		
		JLabel lblNewLabel_1 = new JLabel("Abono: ");
		lblNewLabel_1.setBounds(20, 95, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nro Venta: ");
		lblNewLabel_2.setBounds(20, 25, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNroVenta = new JLabel(String.valueOf(numeroVenta));
		lblNroVenta.setBounds(99, 25, 63, 14);
		contentPane.add(lblNroVenta);
		
		abono = new JTextField();
		abono.setBounds(99, 92, 171, 20);
		contentPane.add(abono);
		abono.setColumns(10);
		
		JButton btnNewButton = new JButton("Finalizar Venta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String abonoAux = abono.getText();
				if (abonoAux.isBlank()) {
					JOptionPane.showMessageDialog(null, "Error. No dejar casillero en blanco", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						float efectivoRecibido = Float.parseFloat(abonoAux);
						float vuelto =adminV.cerrarVentaEfectivo(numeroVenta, efectivoRecibido);
						
						if(total <= efectivoRecibido) {
							AdministradorCajas adminC = AdministradorCajas.getAdministradorCajas();
							adminC.actualizarSaldo(numeroVenta);
							JOptionPane.showMessageDialog(null, "Entregar $"+ vuelto + " como vuelto","",JOptionPane.INFORMATION_MESSAGE);
							
							instancia.dispose();
							instancia = null;
						}else{
							JOptionPane.showMessageDialog(null, "Error. Te dieron menos plata de lo que deberia", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception NumberFormatException) {
						JOptionPane.showMessageDialog(null, "Error. No ingrese caracteres en los campos no permitidos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
		btnNewButton.setBounds(31, 186, 152, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar Venta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminV.cancelarVenta(numeroVenta);
				listado.clear();
				instancia.dispose();
				instancia =null;
			}
		});
		btnNewButton_1.setBounds(255, 186, 142, 41);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Listado de Productos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductosAgregadosAVenta ventana = new ProductosAgregadosAVenta(lista);
				ventana.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(189, 25, 200, 50);
		contentPane.add(btnNewButton_2);
		
		
	}
}
