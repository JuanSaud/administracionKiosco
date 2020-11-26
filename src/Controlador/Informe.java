package Controlador;

import java.time.LocalDate;
import java.time.LocalTime;

public class Informe {
	LocalDate fecha;
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
	
	
	
	public Informe(LocalDate fecha,LocalTime apertura, LocalTime cierre, int totalVentas, int ventasE, int ventasD,int ventasC, float efectivoFinal, float capitalTotal) {
		this.fecha = fecha;
		HorarioApertura = apertura;
		HorarioCierre = cierre;
		VentasRealizadas= totalVentas;
		VentasEfectivoRealizadas = ventasE;
		VentasCreditoRealizadas = ventasC;
		VentasDebitoRealizadas = ventasD;
		
		SaldoEfectivoFinal = efectivoFinal;
		SaldoGeneral = capitalTotal;
		
		capitalVentasEfectivo = 0;
		capitalVentasDebito = 0;
		capitalVentasCredito = 0;
	}

	public float getCapitalVentasEfectivo() {
		return capitalVentasEfectivo;
	}

	public void setCapitalVentasEfectivo(float capitalVentasEfectivo) {
		this.capitalVentasEfectivo = capitalVentasEfectivo;
	}

	public float getCapitalVentasDebito() {
		return capitalVentasDebito;
	}

	public void setCapitalVentasDebito(float capitalVentasDebito) {
		this.capitalVentasDebito = capitalVentasDebito;
	}

	public float getCapitalVentasCredito() {
		return capitalVentasCredito;
	}

	public void setCapitalVentasCredito(float capitalVentasCredito) {
		this.capitalVentasCredito = capitalVentasCredito;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public LocalTime getHorarioApertura() {
		return HorarioApertura;
	}


	public LocalTime getHorarioCierre() {
		return HorarioCierre;
	}


	public int getVentasRealizadas() {
		return VentasRealizadas;
	}


	public int getVentasEfectivoRealizadas() {
		return VentasEfectivoRealizadas;
	}


	public int getVentasCreditoRealizadas() {
		return VentasCreditoRealizadas;
	}


	public int getVentasDebitoRealizadas() {
		return VentasDebitoRealizadas;
	}


	public float getSaldoEfectivoFinal() {
		return SaldoEfectivoFinal;
	}


	public float getSaldoGeneral() {
		return SaldoGeneral;
	}
	
	

}
