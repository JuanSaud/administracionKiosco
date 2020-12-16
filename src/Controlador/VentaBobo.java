package Controlador;

import java.time.LocalDate;
import java.util.Vector;
import Controlador.itemVentaBobo;


public class VentaBobo {
	private int numeroV;
	private LocalDate fecha;
	private float total;
	private Vector<itemVentaBobo> listaItems;
	private String tipoVenta;
	
	//si es efectivo
	private float montoPagado;
	private float vuelto;
	
	//si es debito o credito
//	private int codAutorizacion; no debria de necesitarlo
	private String banco;
	
	// add si es credito
	//private int cuatroDigitos; // no deberia de necesitarlo
	
	
	public VentaBobo(int numero, LocalDate fecha, float total,Vector<itemVentaBobo> listaItems, String tipoVenta) {
		this.numeroV = numero;
		this.fecha = fecha;
		this.total = total;
		this.listaItems = listaItems;
		this.tipoVenta = tipoVenta;
		
		
	}
	public LocalDate getFecha() {
		return fecha;
	}

	public int getNumeroV() {
		return numeroV;
	}


	public void setNumeroV(int numeroV) {
		this.numeroV = numeroV;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	public Vector<itemVentaBobo> getListaItems() {
		return listaItems;
	}


	public void setListaItems(Vector<itemVentaBobo> listaItems) {
		this.listaItems = listaItems;
	}


	public String getTipoVenta() {
		return tipoVenta;
	}


	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}


	public float getMontoPagado() {
		return montoPagado;
	}


	public void setMontoPagado(float montoPagado) {
		this.montoPagado = montoPagado;
	}


	public float getVuelto() {
		return vuelto;
	}


	public void setVuelto(float vuelto) {
		this.vuelto = vuelto;
	}


//	public int getCodAutorizacion() {
//		return codAutorizacion;
//	}


//	public void setCodAutorizacion(int codAutorizacion) {
//		this.codAutorizacion = codAutorizacion;
//	}


	public String getBanco() {
		return banco;
	}


	public void setBanco(String banco) {
		this.banco = banco;
	}


//	public int getCuatroDigitos() {
//		return cuatroDigitos;
//	}


//	public void setCuatroDigitos(int cuatroDigitos) {
//		this.cuatroDigitos = cuatroDigitos;
//	}
	
	
}
