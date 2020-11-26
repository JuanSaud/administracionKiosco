package Kiosko;

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
}
