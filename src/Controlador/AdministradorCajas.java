package Controlador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

import Kiosko.CajaDiaria;
import Kiosko.Venta;
import Kiosko.itemVenta;

public class AdministradorCajas {
	
	private Vector<CajaDiaria> listaCajas;
	private static AdministradorCajas instancia;
	private CajaDiaria CajaIniciada;
	private float CapitalTotal;
	private int VentasTotales;
	

	private AdministradorCajas() {
		listaCajas  = new Vector<CajaDiaria>();
		VentasTotales = 0;
		CapitalTotal = 0;
		
	}
	
	public static AdministradorCajas getAdministradorCajas() {
		if (instancia == null){
			instancia = new AdministradorCajas();
		}
		return instancia;
	}
	
	public void iniciarCajaDiaria(float efectivoInicial) {			
		CajaIniciada = new CajaDiaria(efectivoInicial);
	}
	
	public int getNroCajaIniciada() {
		int nroCaja = 0;
		if (CajaIniciada!=null){
			nroCaja = CajaIniciada.getNroCaja();
		}
		return nroCaja;
	}
	
	public CajaDiaria buscarCaja(int nroCaja) {
		for (CajaDiaria caja: listaCajas) {
			if (caja.sosLaCaja(nroCaja)) {
				return caja;
			}
		}
		return null;
	}
	
	
	public int cerrarCajaDiaria() {// habra problemas aca?
		int nroCaja = CajaIniciada.getNroCaja();
		CajaIniciada.cerrarCaja();
		listaCajas.add(CajaIniciada);
		CajaIniciada = null;
		return nroCaja;
	}
	public boolean actualizarSaldo(int nroVenta) {	
		if(CajaIniciada!=null) {
			boolean respuesta = CajaIniciada.actualizarSaldo(nroVenta);
			VentasTotales = VentasTotales +1;
			
			return respuesta;
		}
		return false;
	}
	
	public float getEfectivoEnCaja() {
		float efectivo = 0;
		if (CajaIniciada != null) {
			efectivo = CajaIniciada.getSaldoEfectivo();
		}
		return efectivo;
	}
	
	public int getVentasTotales() {
		return VentasTotales;
	}
	public float getSaldoDelDia() {
		float saldo = 0;
		if (CajaIniciada != null) {
			saldo = CajaIniciada.getSaldoGeneral();
		}
		return saldo;
	}
	

//=====================Reportes=========================
	
	public Informe generarInforme(int nroCaja) {
		CajaDiaria caja = buscarCaja(nroCaja);
		Informe i;
		LocalDate fecha;
		LocalTime HorarioApertura;
		LocalTime HorarioCierre;
		int VentasRealizadas;
		int VentasEfectivoRealizadas;
		int VentasCreditoRealizadas;
		int VentasDebitoRealizadas;
		
		float SaldoEfectivoFinal;
		float SaldoGeneral;
		if(caja!=null) {
			fecha = caja.getFecha(); 
			HorarioApertura = caja.getHorarioApertura();
			HorarioCierre= caja.getHorarioCierre();
			VentasRealizadas = caja.getListaVentasGenerales().size();
			VentasEfectivoRealizadas = caja.getListaVentasEfectivo().size();
			VentasDebitoRealizadas = caja.getListaVentasDebito().size();
			VentasCreditoRealizadas = caja.getListaVentasCredito().size();
			
			SaldoGeneral = caja.getSaldoGeneral();
			SaldoEfectivoFinal = caja.getSaldoEfectivo();
			i = new Informe(fecha,HorarioApertura,HorarioCierre,VentasRealizadas,VentasEfectivoRealizadas,VentasDebitoRealizadas,VentasCreditoRealizadas,SaldoEfectivoFinal,SaldoGeneral);
			return i;
		}
		return null;
		
	}
	
	public Vector<VentaBobo> generarReporte(int nroCaja) 
		{
		CajaDiaria caja = buscarCaja(nroCaja);
		Vector<Venta> listaVentas = caja.getListaVentasGenerales();
		VentaBobo vb;
		Vector<VentaBobo> listadoVentas = new Vector<VentaBobo>();
		
		for (Venta v:listaVentas) {
			vb = v.embobate();
			listadoVentas.add(vb);
		}
		return listadoVentas;
	}
	
	
	public Vector<Informe> generarReporteMensual(String mes,String año) {
		CajaDiaria caja;
		String fecha;
		String añoC;
		String mesC;
		String diaC;
		
		LocalDate fecha2;
		LocalTime HorarioApertura;
		LocalTime HorarioCierre;
		int VentasRealizadas;
		int VentasEfectivoRealizadas;
		int VentasCreditoRealizadas;
		int VentasDebitoRealizadas;
		
		float SaldoEfectivoFinal;
		float SaldoGeneral;
		
		float capitalVentasEfectivo;
		float capitalVentasDebito;
		float capitalVentasCredito;
		
		Vector<Informe> listaInformes = new Vector<Informe>();
		Informe i;
		for (CajaDiaria c: listaCajas) {
			fecha = String.valueOf(c.getFecha());
			añoC = fecha.substring(0, 4);
			mesC = fecha.substring(5, 7);
			//diaC = fecha.substring(8, 10);
			if (mesC.equals(mes) && añoC.equals(año) ) {
				fecha2 = c.getFecha();
				HorarioApertura = c.getHorarioApertura();
				HorarioCierre = c.getHorarioCierre();
				VentasRealizadas = c.getListaVentasGenerales().size();
				VentasEfectivoRealizadas = c.getListaVentasEfectivo().size();
				VentasCreditoRealizadas =  c.getListaVentasCredito().size();
				VentasDebitoRealizadas = c.getListaVentasDebito().size();
				
				SaldoEfectivoFinal = c.getSaldoEfectivo();
				SaldoGeneral = c.getSaldoGeneral();
				
				capitalVentasEfectivo = c.getCapitalEfectivo();
				capitalVentasDebito = c.getCapitalDebito(); 
				capitalVentasCredito  = c.getCapitalCredito();
				i = new Informe(fecha2,HorarioApertura, HorarioCierre, VentasRealizadas, VentasEfectivoRealizadas, VentasDebitoRealizadas,VentasCreditoRealizadas, SaldoEfectivoFinal, SaldoGeneral);
				i.setCapitalVentasEfectivo(capitalVentasEfectivo);
				i.setCapitalVentasDebito(capitalVentasDebito);
				i.setCapitalVentasCredito(capitalVentasCredito);
				listaInformes.add(i);
			}
		
		}
		return listaInformes;
	}
	

//==================================================================
	//metodos vista-controlador
	
	
	public boolean CajaNoEstaIniciada() {
		return CajaIniciada == null;
	}
}
