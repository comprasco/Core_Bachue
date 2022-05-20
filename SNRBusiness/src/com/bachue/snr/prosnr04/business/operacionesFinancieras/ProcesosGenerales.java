package com.bachue.snr.prosnr04.business.operacionesFinancieras;

import com.b2bsg.common.exception.B2BException;


/**
 * Clase que contiene todas las propiedades ProcesosGenerales.
 *
 * @author  Julian Vaca
 * Fecha de Creacion: 05/08/2019
 */
public class ProcesosGenerales
{
	/**
	 * Relacionar servicios generar liquidacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algún error controlado.
	 */
	public void relacionarServiciosGenerarLiquidacion()
	    throws B2BException
	{
		String[] las_relacion;

		try
		{
			las_relacion        = new String[255];
			las_relacion[0]     = "";
		}
		catch(Exception le_e)
		{
			throw new B2BException(le_e.getMessage());
		}
	}
}
