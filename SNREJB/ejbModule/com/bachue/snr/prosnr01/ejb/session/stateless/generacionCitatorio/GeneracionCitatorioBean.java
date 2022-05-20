package com.bachue.snr.prosnr01.ejb.session.stateless.generacionCitatorio;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.generacionCitatorio.GeneracionCitatorioBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase para la implementación de los metodos de la interfaz remota del proceso de aprobación de generación citatorio.
 *
 * @author Manuel Blanco
 *
 */
@javax.ejb.Stateless(name = "GeneracionCitatorio", mappedName = "generacionCitatorioMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GeneracionCitatorioBean implements GeneracionCitatorioRemote
{
	/** Propiedad igcb generacion citatorio business. */
	private GeneracionCitatorioBusiness igcb_generacionCitatorioBusiness;

	/** {@inheritdoc} */
	public void procesarCasos(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().procesarCasos(as_remoteIp);

		Logger.log(lsw_watch, "GeneracionCitatorioBean", "procesarCasos", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void enviarDocumentos(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarDocumentos(as_remoteIp);

		Logger.log(lsw_watch, "GeneracionCitatorioBean", "enviarDocumentos", null, null, as_remoteIp, null);
	}

	/**
	 * Obtiene el valor de business.
	 *
	 * @return el valor de business
	 */
	private GeneracionCitatorioBusiness getBusiness()
	{
		if(igcb_generacionCitatorioBusiness == null)
			igcb_generacionCitatorioBusiness = new GeneracionCitatorioBusiness();

		return igcb_generacionCitatorioBusiness;
	}
}
