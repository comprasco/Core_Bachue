package com.bachue.snr.prosnr21.business.test;

import com.bachue.snr.prosnr21.business.conciliaciones.ConciliacionesBusiness;

import java.util.Date;


public class CargarArchivos
{
	public static void main(String[] args)
	    throws Exception
	{
		Date ld_fin;
		Date ld_inicio;

		ld_inicio     = new Date();
		ld_fin        = new Date();

		new ConciliacionesBusiness().cargarArchivos("10001", "garias_conciliaciones", "192.168.100.102");

		System.err.println(
		    "Inicio -> " + ld_inicio + " :: Finializo -> " + ld_fin + " :: Duración ->"
		    + ((ld_fin.getTime() - ld_inicio.getTime()) / 1000d)
		);
	}
}
