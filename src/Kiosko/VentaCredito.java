package Kiosko;

import java.util.Vector;

import Controlador.VentaBobo;
import Controlador.itemVentaBobo;

public class VentaCredito extends Venta {
	private int codAutorizacion;
	private String banco;
	private int cuatroDigitos;
	

	public VentaCredito() {
			
	}
		
	public int getCodAutorizacion() {
		return codAutorizacion;
	}

	public String getBanco() {
		return banco;
	}


	public void setBanco(String banco) {
		this.banco = banco;
	}


	public int getCuatroDigitos() {
		return cuatroDigitos;
	}


	public void setCuatroDigitos(int cuatroDigitos) {
		this.cuatroDigitos = cuatroDigitos;
	}



	public void cerrarVentaCredito(String banco, int codAut, int cuatroDigitos) {
		this.banco = banco;
		this.codAutorizacion = codAut;
		this.cuatroDigitos = cuatroDigitos;
	}
	public String tipoVenta() {
		return "Credito";
	}
	public VentaBobo embobate(){
		Vector<itemVenta> lista = this.getListaItems(); 
		Vector<itemVentaBobo> listaI = new Vector<itemVentaBobo>();
		itemVentaBobo i;
		for (itemVenta iv: lista) {
			i = iv.embobate();
			listaI.add(i);
		}		
		VentaBobo bobo = new VentaBobo(this.getNumeroV(),this.getFecha(), this.total,listaI,this.tipoVenta());
		return bobo;
	}
}
