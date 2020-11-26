package Controlador;

import java.util.Vector;

import Kiosko.Producto;


public class AdministradorProductos {
	private Vector<Producto> listaProductos;
	private Vector<Producto> listaProductosEliminados;
	
	private static AdministradorProductos instancia;
	
	public AdministradorProductos() {
		listaProductos = new Vector<Producto>();
		listaProductosEliminados =  new Vector<Producto>();
		
	}

	
	public static AdministradorProductos getAdministradorProducto() {
		if (instancia == null) {
			instancia = new AdministradorProductos();
		}
		return instancia;
	}
	
	public boolean crearProducto(int codigo, String descripcion, float precio, int stock, int stockMinimo) {
		Producto prod = buscarProducto(codigo);
		if (prod == null) {
			prod = new Producto(codigo,  descripcion,  precio,  stock,  stockMinimo);
			listaProductos.add(prod);

			return true;
		}
		return false;
		
	}
	

//============================================================================================================	

	public Producto buscarProducto(int codigo) {
		for (Producto prod : listaProductos) {
			if(prod.sosElProducto(codigo) && prod.getEstado() == "Activo") {//baja o alta logica de un objeto
				return prod;
			}
		}
		return null;
	}

	public boolean modificarProducto(int codigo, String descripcion, float precio, int stock,int stockMinimo) {
		Producto prod = buscarProducto(codigo);
		if (prod != null) {
			
			//prod.setCodigoBarra(codigo);
			prod.setDescripcion(descripcion);
			prod.setPrecio(precio);
			prod.setStock(stock);
			prod.setStockMinimo(stockMinimo);
			return true;
		}
		return false;
		
	}
	
	
	public boolean eliminarProducto(int codigo) {
	
		Producto prod = buscarProducto(codigo);
		
		if (prod != null) {
			//prod.setEstado("Baja");
			listaProductosEliminados.add(prod);
			listaProductos.remove(prod);
			
			return true;
		}else {
			return false;
		}
	}

//============================================================================================================	
// metodos para ventanas 
	public boolean existeProducto(int cod) {
		boolean respuesta = false;
		if (buscarProducto(cod)!= null) {
			respuesta = true;
		}
		
		return respuesta;
	}
	
	public float consultarPrecio(int cod) {
		float precio = 0;
		Producto p = buscarProducto(cod);
		if (p!=null) {
			precio = p.getPrecio();
		}
		
		return precio;
	}
	public String consultarNombre(int cod) {
		String d=""; 
		Producto p = buscarProducto(cod);
		if (p != null) {
			d = p.getDescripcion();
		}
		return d;
	}
	public Vector<String> consultarDatosProducto(int cod) {
		//no descartar el uso de objetos bobos
		Vector<String> datos = new Vector<String>(); 
		Producto p = buscarProducto(cod);
		if (p != null) {
			datos.add(p.getDescripcion());
			datos.add(String.valueOf(p.getCodigoBarra()));
			datos.add(String.valueOf(p.getPrecio()));
			datos.add(String.valueOf(p.getStock()));
			datos.add(String.valueOf(p.getStockMinimo()));
			
		}else {
			datos.add("");
			datos.add("");
			datos.add("");
			datos.add("");
			datos.add("");
		}
		return datos;
	}
	
	public Vector<ProductoBobo> getListadoProductosBobos(){
		// hace una copia boboa de todos los productos
		
		Vector<ProductoBobo> listaBobo = new Vector<ProductoBobo>();
		String descripcion;
		int codigo;
		float precio;
		int Stock;
		int StockMinimo;
		
		for (Producto prod: listaProductos) {
			descripcion = prod.getDescripcion();
			codigo = prod.getCodigoBarra();
			precio = prod.getPrecio();
			Stock = prod.getStock();
			StockMinimo = prod.getStockMinimo();
			ProductoBobo bobo = new ProductoBobo(codigo,descripcion,precio,Stock,StockMinimo);
			listaBobo.add(bobo);
		}
		
		return listaBobo;
	}
	
	public ProductoBobo getProductoBobo(int cod) {
		Producto p = buscarProducto(cod);
		String descripcion;
		int codigo;
		float precio;
		int Stock;
		int StockMinimo;
		ProductoBobo bobo;
		if (p!=null) {
			codigo = p.getCodigoBarra();
			descripcion = p.getDescripcion();
			precio= p.getPrecio();
			Stock = p.getStock();
			StockMinimo = p.getStockMinimo();
			bobo = new ProductoBobo(codigo,descripcion,precio,Stock,StockMinimo); 
			return bobo;
		}
		return null;
		
	}
	
	public Vector<ProductoBobo> getListadoProductosStockMinimo() {
		//por ahora va a ser void pero bien podria deovlver un array de tipo String
		Vector<ProductoBobo> listaComprar= new Vector<ProductoBobo>();
		ProductoBobo bobo;
		int codigo;
		String descripcion;
		float precio;
		int Stock;
		int StockMinimo;
		
		for (Producto prod : listaProductos) {
			if(prod.getStock() <= prod.getStockMinimo()) {
				codigo = prod.getCodigoBarra();
				descripcion = prod.getDescripcion();
				precio = prod.getPrecio();
				Stock = prod.getStock();
				StockMinimo = prod.getStockMinimo();
				bobo = new ProductoBobo(codigo, descripcion, precio, Stock, StockMinimo);
				listaComprar.add(bobo);
			}
		}
		return listaComprar;
		
	}
}
