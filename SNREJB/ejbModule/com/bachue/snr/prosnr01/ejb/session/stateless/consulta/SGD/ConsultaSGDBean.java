package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.SGD;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.SGD.ConsultaSGDBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ConsultaSGDBean.
 *
 * @author Jorge Patino
 */
@javax.ejb.Stateless(name = "ConsultaSGD", mappedName = "consultaSGDMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaSGDBean implements ConsultaSGDRemote
{
	/** Propiedad icsgdb business. */
	private ConsultaSGDBusiness icsgdb_business;

	/** {@inheritdoc} */
	public Collection<Trazabilidad> consultaPorMatriculaYCirculo(
	    ConsultaPredio acp_consultaPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<Trazabilidad> lcdo_return;
		StopWatch                lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lcdo_return     = getConsultaSGDBusiness().consultaPorMatriculaYCirculo(acp_consultaPredio);

		Logger.log(
		    lsw_watch, "ConsultaSGDBean", "consultaPorMatriculaYCirculo", as_userId, as_localIp, as_remoteIp,
		    lcdo_return
		);

		return lcdo_return;
	}

	/** {@inheritdoc} */
	public Collection<DocumentoOWCC> consultaSGD(
	    DocumentoOWCC ado_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<DocumentoOWCC> lcdo_return;
		StopWatch                 lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcdo_return = getConsultaSGDBusiness().consultaSGD(ado_parametro);

		Logger.log(lsw_watch, "ConsultaSGDBean", "consultaSGD", as_userId, as_localIp, as_remoteIp, lcdo_return);

		return lcdo_return;
	}

	/** {@inheritdoc} */
	public DocumentoOWCC generarDataPais(
	    OficinaOrigen aoo_oficina, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DocumentoOWCC lcdo_return;
		StopWatch     lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcdo_return = getConsultaSGDBusiness().generarDataPais(aoo_oficina);

		Logger.log(lsw_watch, "ConsultaSGDBean", "generarDataPais", as_userId, as_localIp, as_remoteIp, null);

		return lcdo_return;
	}

	/** {@inheritdoc} */

	/**
	 * Método encargado de generar una <code>Collection</code> cargada con la
	 * respuesta del servicio de búsqueda el OWCC.
	 *
	 * @param ado_parametro
	 *            <code>DocumentoOWCC</code> que contiene los parametros que serán
	 *            enviados al servicio.
	 * @param as_userId
	 *            <code>String</code> que contiene el usuario que esta ejecutando el
	 *            método.
	 * @param as_localIp
	 *            <code>String</code> que contiene la dirección IP local del usuario
	 *            que esta ejecutando el método.
	 * @param as_remoteIp
	 *            <code>String</code> que contiene la dirección IP remota del
	 *            usuario que esta ejecutando el método.
	 * @return <code>Collection</code> cargada con la respuesta del servicio de
	 *         búsqueda el OWCC
	 * @throws B2BException
	 */
	public Collection<DocumentoOWCC> generarDocumento(
	    DocumentoOWCC ado_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<DocumentoOWCC> lcdo_return;
		StopWatch                 lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcdo_return = getConsultaSGDBusiness().generarDocumento(ado_parametro);

		Logger.log(lsw_watch, "ConsultaSGDBean", "generarDocumento", as_userId, as_localIp, as_remoteIp, null);

		return lcdo_return;
	}

	/**
	 * Retorna el valor de consulta SGD business.
	 *
	 * @return el valor de consulta SGD business
	 */
	private ConsultaSGDBusiness getConsultaSGDBusiness()
	{
		if(icsgdb_business == null)
			icsgdb_business = new ConsultaSGDBusiness();

		return icsgdb_business;
	}
}
