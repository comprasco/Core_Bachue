package com.bachue.snr.prosnr21.business.test;

import com.bachue.snr.prosnr21.business.conciliaciones.ReportesConciliacionesBusiness;


/**
 * The Class ReportesConciliaciones.
 */
public class ReportesConciliaciones
{
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args)
	    throws Exception
	{
		new ReportesConciliacionesBusiness().generarReportes("1", "garias_conciliaciones", "192.168.100.102");
	}
}
