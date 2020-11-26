package Kiosko;

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
	
	
}
