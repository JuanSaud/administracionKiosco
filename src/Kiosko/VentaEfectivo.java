package Kiosko;

public class VentaEfectivo extends Venta {
	private float montoPagado;
	private float vuelto;
	
	public VentaEfectivo() {
		//this.montoPagado = 0;
		//this.vuelto = 0;
	}

	public float getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(int montoPagado) {
		this.montoPagado = montoPagado;
	}

	public float getVuelto() {
		return vuelto;
	}

	public void setVuelto(int vuelto) {
		this.vuelto = vuelto;
	}

	public float cerrarVentaEfectivo(float montoPagado) { 
		this.montoPagado = montoPagado;
	
		this.vuelto = montoPagado- this.total;
		return vuelto;
	}
	
	public String tipoVenta() {
		return "Efectivo";
	}

}
