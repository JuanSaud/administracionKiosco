package Kiosko;

import Controlador.ProductoBobo;

public class Producto {
//============================================================================================================	

	//atributos
	private int codigoBarra;
	private String descripcion;
	private float precio;//protected para que lo pueda
	private int stock;
	private String estado;
	protected int stockMinimo;
//============================================================================================================	
	

	public Producto(int codigoBarra, String descripcion, float precio, int stock, int stockMinimo) {
		super();
		this.codigoBarra = codigoBarra;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = "Activo";
		this.stockMinimo = stockMinimo;
		this.stock = stock;
	}
//============================================================================================================	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	//Getters y Setters
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
//============================================================================================================	
	
	//metodos necesarios para el funcionamiento del sistema
	
	public boolean sosElProducto(int codigo) {
		return codigoBarra== codigo;
	}
	
	public void descontarStock(int cant) {
		if (stock -cant >0) {
			stock = stock -cant;
		}else {
			stock = 0;
		}
		
	}
	
	public boolean stockOK() {
		return stock > stockMinimo;
	}
	
	public ProductoBobo embobate() {
		ProductoBobo bobo = new ProductoBobo(codigoBarra,descripcion,precio, stock, stockMinimo);
		return bobo;
	}
}
