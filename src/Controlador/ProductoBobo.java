package Controlador;

public class ProductoBobo {
	private int codigoBarra;
	private String descripcion;
	protected float precio;
	private int stock;
	private int cantidad;

	private int stockMinimo;

	public ProductoBobo(int codigoBarra, String descripcion, float precio, int stock, int stockMinimo) {
		super();
		this.codigoBarra = codigoBarra;
		this.descripcion = descripcion;
		this.precio = precio;	
		this.stockMinimo = stockMinimo;
		this.stock = stock;
		this.cantidad = 0;
	}

	public int getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
