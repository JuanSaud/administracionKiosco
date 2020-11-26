package Kiosko;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Vector;

import Controlador.AdministradorVentas;
import Controlador.VentaBobo;

public class CajaDiaria {
	private LocalDate fecha;
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private float efectivoInicial;
	private float saldoGeneral;
	private float saldoEfectivo;
	private int nroCaja;
	private static int proximoNumero;
	
	private float capitalEfectivo;
	private float capitalDebito;
	private float capitalCredito;
	
	
	private Vector<Venta> listaVentasGenerales;
	private Vector<VentaEfectivo> listaVentasEfectivo;
	private Vector<VentaDebito> listaVentasDebito;
	private Vector<VentaCredito> listaVentasCredito;
	
	//private static CajaDiaria instancia;
	
	//IMPORTANTE 
	//caja diaria es un objeto independiente ya que es la que retiene la informacion de las ventas
	// pero deberia comportarse como un singleton. Es decir que si no fue cerrada, debe devolverse la caja que esta abierta
	private static int getProximoNumero() {
		return ++proximoNumero;
	}
	
	
	public CajaDiaria(float efectivoInicial) {
		fecha = LocalDate.now();
		horarioApertura = LocalTime.now();
		this.efectivoInicial = efectivoInicial;
		saldoGeneral = efectivoInicial;
		saldoEfectivo = efectivoInicial;
		nroCaja = getProximoNumero();
		
		listaVentasGenerales = new Vector<Venta>();
		listaVentasEfectivo = new Vector<VentaEfectivo>();
		listaVentasDebito = new Vector<VentaDebito>();
		listaVentasCredito = new Vector<VentaCredito>();
	}
	
	public boolean sosLaCaja(int nroC) {
		return nroCaja == nroC;
	}
	
	
//======================Setters===================================

	public void setSaldoGeneral(float saldoGeneral) {
		this.saldoGeneral = saldoGeneral;
	}


	public void setSaldoEfectivo(float saldoEfectivo) {
		this.saldoEfectivo = saldoEfectivo;
	}

//=====================getters=================================
	public int getNroCaja() {
		return nroCaja;
	}
	
	public float getCapitalEfectivo() {
		return capitalEfectivo;
	}


	public void setCapitalEfectivo(float capitalEfectivo) {
		this.capitalEfectivo = capitalEfectivo;
	}


	public float getCapitalDebito() {
		return capitalDebito;
	}


	public void setCapitalDebito(float capitalDebito) {
		this.capitalDebito = capitalDebito;
	}


	public float getCapitalCredito() {
		return capitalCredito;
	}


	public void setCapitalCredito(float capitalCredito) {
		this.capitalCredito = capitalCredito;
	}


	public float getSaldoGeneral() {
		return saldoGeneral;
	}

	
	public float getSaldoEfectivo() {
		return saldoEfectivo;
	}

	public LocalDate getFecha() {
		return fecha;
	}


	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}


	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}


	public float getEfectivoInicial() {
		return efectivoInicial;
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
//=============================================================
	
	public boolean actualizarSaldo(int nroVenta) {
		AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
		VentaEfectivo vE;
		VentaDebito vD;
		VentaCredito vC;
		Venta v = adminV.buscarVenta(nroVenta);
		if (v!=null) {
			listaVentasGenerales.add(v);
			if (v.tipoVenta() == "Efectivo") {
				vE = adminV.buscarVentaEfectivo(v.getNumeroV());// o nroVenta
				listaVentasEfectivo.add(vE);
				saldoEfectivo = saldoEfectivo +(vE.getMontoPagado() - vE.getVuelto());
				saldoGeneral = saldoGeneral +(vE.getMontoPagado() - vE.getVuelto());
				capitalEfectivo = capitalEfectivo + vE.getTotal();
				return true;
				
			}
			if (v.tipoVenta() == "Debito") {
				vD = adminV.buscarVentaDebito(v.getNumeroV());// o nroVenta
				listaVentasDebito.add(vD);
				saldoGeneral = saldoGeneral + vD.getTotal();
				capitalDebito = capitalDebito + vD.getTotal();
				return true;
			}
			if (v.tipoVenta() == "Credito") {
				vC = adminV.buscarVentaCredito(v.getNumeroV());// o nroVenta
				listaVentasCredito.add(vC);
				saldoGeneral = saldoGeneral + vC.getTotal();
				capitalCredito = capitalCredito + vC.getTotal();
				return true;
			}
		}
		return false;
	}
	
	public void cerrarCaja() {
		horarioCierre = LocalTime.now();
		AdministradorVentas adminV = AdministradorVentas.getAdministradorVentas();
		adminV.cerrarElDia();
				
	}
	

	
}
