package Kiosko;

import java.time.LocalDate;
import java.util.Vector;





public abstract class Venta {
	
	private int numeroV;
	private LocalDate fecha;
	protected float total;
	private Vector<itemVenta> listaItems;
	private static int proximoNumero;
	
	public static int getProximoNumero() {
		return ++proximoNumero;
	}
	public static void ventaCancelada() {
		proximoNumero = proximoNumero -1;
	}
	public static void cerrarElDia() {
		proximoNumero = 0;
	}
	
	public Venta() {
		fecha = LocalDate.now();
		listaItems = new Vector<itemVenta>();
		numeroV = getProximoNumero();
	}

	public int getNumeroV() {
		return numeroV;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total  = total;
	}

	public Vector<itemVenta> getListaItems() {
		return listaItems;
	}
	
	public void actualizarStock() {
		for (itemVenta iv: listaItems) {
			iv.actualizarStock();
		}
	}
	public float calcularTotal() {
		float total=  0;
		for (itemVenta iv: listaItems) {
			total= total + iv.calcularSubTotal();
		}
		return total;
	}
	
	public boolean sosLaVenta(int nroV) {
		return numeroV == nroV;
		//return getNumeroV() == nroV
	}
	
	public void agregarItem(Producto prod, int cant){
		itemVenta iv = new itemVenta(prod, cant);
		listaItems.add(iv);
		
	}
	
	public abstract String tipoVenta();
}
