package Controlador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.Vector;


import Kiosko.CajaDiaria;
import Kiosko.Producto;
import Kiosko.Venta;
import Kiosko.VentaCredito;
import Kiosko.VentaDebito;
import Kiosko.VentaEfectivo;



public class AdministradorVentas {
	
	private static AdministradorVentas instancia;
	private Vector<Venta> listaVentasGenerales;
	private Vector<VentaEfectivo> listaVentasEfectivo;
	private Vector<VentaDebito> listaVentasDebito;
	private Vector<VentaCredito> listaVentasCredito;
	
	
	private Vector<String>listadoBancos;
	

	
	private AdministradorVentas() {
		listaVentasGenerales = new Vector<Venta>();
		listaVentasEfectivo = new Vector<VentaEfectivo>();
		listaVentasDebito = new Vector<VentaDebito>();
		listaVentasCredito = new Vector<VentaCredito>();
		listadoBancos = new Vector<String>();
		
	}
	
	public static AdministradorVentas getAdministradorVentas() {
		if (instancia==null)
		{
			instancia= new AdministradorVentas();
		}
		
		return instancia;
		
	}
//=====================getters=================================

	public Vector<Venta> getListaVentasGenerales() {
		return listaVentasGenerales;
	}


	public Vector<VentaEfectivo> getListaVentasEfectivo() {
		return listaVentasEfectivo;
	}


	public Vector<VentaDebito> getListaVentasDebito() {
		return listaVentasDebito;
	}


	public Vector<VentaCredito> getListaVentasCredito() {
		return listaVentasCredito;
	}
	
//====================================================================
	public int iniciarVentaEfectivo(){
		VentaEfectivo vE = new VentaEfectivo();
		listaVentasEfectivo.add(vE);
		listaVentasGenerales.add(vE);
		return vE.getNumeroV();
		
	}
	
	public int iniciarVentaDebito(){
		VentaDebito vD = new VentaDebito();
		listaVentasDebito.add(vD);
		listaVentasGenerales.add(vD);
		return vD.getNumeroV();
		
	}
	public int iniciarVentaCredito(){
		VentaCredito vC = new VentaCredito();
		listaVentasCredito.add(vC);
		listaVentasGenerales.add(vC);
		return vC.getNumeroV();	
	}
//====================================================================	
	public Venta buscarVenta(int nroVenta) {
		for (Venta v: listaVentasGenerales) {
			if (v.sosLaVenta(nroVenta)) {
				return v;
			}
		}
		return null;
	} 
	public VentaEfectivo buscarVentaEfectivo(int nroVenta) {
		for (VentaEfectivo v: listaVentasEfectivo) {
			if (v.sosLaVenta(nroVenta)) {
				return v;
			}
		}
		return null;
	} 
	public VentaDebito buscarVentaDebito(int nroVenta) {
		for (VentaDebito v: listaVentasDebito) {
			if (v.sosLaVenta(nroVenta)) {
				return v;
			}
		}
		return null;
	} 
	public VentaCredito buscarVentaCredito(int nroVenta) {
		for (VentaCredito v: listaVentasCredito) {
			if (v.sosLaVenta(nroVenta)) {
				return v;
			}
		}
		return null;
	} 
	
//====================================================================	
	
	public boolean agregarProductoAVenta(int nroVenta, int codBarra, int cant) {
		Venta v = buscarVenta(nroVenta);
		Producto p;
		AdministradorProductos ap = AdministradorProductos.getAdministradorProducto();
		if (v!=null) {
			p = ap.buscarProducto(codBarra);
			if (p!= null) {	
				v.agregarItem(p, cant);
				return true;
			}
		} 
		return false;
	}
//====================================================================	
	public boolean cancelarVenta(int nroVenta) {
		Venta v = buscarVenta(nroVenta);
		VentaEfectivo vE = buscarVentaEfectivo(nroVenta);
		VentaDebito vD = buscarVentaDebito(nroVenta);
		VentaCredito vC = buscarVentaCredito(nroVenta);
		
		if (vE != null) {
			listaVentasEfectivo.removeElement(vE);
		}
		if (vD != null) {
			listaVentasDebito.removeElement(vD);
		}
		if (vC != null) {
			listaVentasCredito.removeElement(vC);
		}
		
		if (v!=null) {
			v.ventaCancelada();
			listaVentasGenerales.remove(v);
			return true;
		}
		return false;
	}
//====================================================================	
	public float calcularTotal(int nroVenta) {
		Venta v = buscarVenta(nroVenta);
		float resultado = 0;
		if (v!=null) {
			resultado = v.calcularTotal();
		}
		return resultado;
	}
//====================================================================	

	public float cerrarVentaEfectivo(int nroVenta, float montoPagado) {
		Venta v = buscarVenta(nroVenta);
		VentaEfectivo vE;
		float vuelto = 0;
		if (v!=null) {
			v.actualizarStock();
			v.setTotal(v.calcularTotal());
			vE = buscarVentaEfectivo(nroVenta);
			vuelto = vE.cerrarVentaEfectivo(montoPagado);
		}
		return vuelto;
	}
	
	public boolean cerrarVentaDebito(int nroVenta, String banco, int CodigoAutorizacion) {
		Venta v = buscarVenta(nroVenta);
		VentaDebito vD;
		if (v!=null) {
			v.actualizarStock();
			v.setTotal(v.calcularTotal());
			vD = buscarVentaDebito(nroVenta);
			vD.cerrarVentaDebito(banco, CodigoAutorizacion);
			return true;
		}
		return false;
	}
	public boolean cerrarVentaCredito(int nroVenta, String banco, int CodigoAutorizacion, int ultimos4) {
		Venta v = buscarVenta(nroVenta);
		VentaCredito vC;
		if (v!=null) {
			v.actualizarStock();
			v.setTotal(v.calcularTotal());
			vC = buscarVentaCredito(nroVenta);
			vC.cerrarVentaCredito(banco, CodigoAutorizacion, ultimos4);
			return true;
		}
		return false;
	}
	
	
	
	public void cerrarElDia() {
		listaVentasGenerales = null;
		listaVentasEfectivo = null;
		listaVentasDebito = null;
		listaVentasCredito = null;
		Venta.cerrarElDia();
	}
//====================================================================	
	// ABM de bancos utilizados por las ventanas
	 public boolean deleteBancoAlListado(String banco){ 
         
    	 if (buscarBanco(banco)) {
    		listadoBancos.remove(banco);
    		System.out.println("se elimino el banco"+ banco);
    		return true;
    	 }	
          return false;
     }
	
	 public boolean addBancoAlListado(String banco){ 
		if (!buscarBanco(banco)) {
			listadoBancos.add(banco);		 	
			System.out.println("se agrego el banco"+ banco);
			return true;
		}
		return false;
	    }
	
	 public Vector<String> getListadoDeBancos(){ 
	        return listadoBancos;
	    }
	 
	 public boolean buscarBanco(String banco) {
		for (String b: listadoBancos) {			
			if (b.equals(banco)) {				
				return true;
			}
		}
		return false;
	 }
	
}
