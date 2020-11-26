package Kiosko;

public class itemVenta {
	private Producto producto;
	private int cantidad;
	
	public itemVenta(Producto prod, int cant) {
		this.producto = prod;
		this.cantidad = cant;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public float calcularSubTotal() {
		return producto.precio * cantidad;
	}
	
	public void actualizarStock() {
		producto.descontarStock(cantidad);
	}

}
