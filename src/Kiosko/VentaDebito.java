package Kiosko;

import java.util.Vector;

import Controlador.VentaBobo;
import Controlador.itemVentaBobo;

public class VentaDebito extends Venta {
	private int codAutorizacion;
	private String banco;
	
	public VentaDebito() {
		
	}
	
	
	public String getBanco() {
		return banco;
	}


	public void setBanco(String banco) {
		this.banco = banco;
	}


	public int getCodAutorizacion() {
		return codAutorizacion;
	}


	public void cerrarVentaDebito(String banco, int codAut) {
		this.banco = banco;
		this.codAutorizacion = codAut;
	}

	@Override
	public String tipoVenta() {
		return "Debito";
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
