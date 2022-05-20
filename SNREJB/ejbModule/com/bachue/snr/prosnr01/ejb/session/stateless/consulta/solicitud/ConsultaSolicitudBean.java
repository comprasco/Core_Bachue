package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.solicitud;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.solicitud.ConsultaSolicitudBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.solicitud.ConsultaSolicitudRemote;

import com.bachue.snr.prosnr01.model.consulta.solicitud.Predio;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Segregacion;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Solicitud;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Tramite;
import com.bachue.snr.prosnr01.model.registro.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaSolicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ConsultaSolicitudBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "ConsultaSolicitud", mappedName = "consultaSolicitudMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaSolicitudBean implements ConsultaSolicitudRemote
{
	/** Propiedad ict business. */
	private ConsultaSolicitudBusiness ict_business;

	/** {@inheritdoc} */
	@Override
	public Collection<Acto> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		Collection<Acto> lca_actos;
		lsw_watch     = Logger.getNewStopWatch();
		lca_actos     = getConsultaSolicituddBusiness().findByIdSolicitud(as_idSolicitud);
		Logger.log(lsw_watch, "ConsultaSolicitudBusiness", "findByIdSolicitud", as_idSolicitud, null, null, null);

		return lca_actos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Tramite> findDatosDelTramiteByidSolicitud(String as_parametros)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Tramite> lct_tramites;
		lsw_watch     = Logger.getNewStopWatch();

		lct_tramites = getConsultaSolicituddBusiness().findDatosDelTramiteByidSolicitud(as_parametros);

		Logger.log(
		    lsw_watch, "ConsultaSolicitudBusiness", "findDatosDelTramiteByidSolicitud", as_parametros, null, null, null
		);

		return lct_tramites;
	}

	/** {@inheritdoc} */
	public Documento findDocumentoByIdSolicitud(String as_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Documento lct_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lct_datos = getConsultaSolicituddBusiness().findDocumentoByIdSolicitud(as_parametros);

		Logger.log(
		    lsw_watch, "ConsultaSolicitudBusiness", "findDocumentoByIdSolicitud", as_parametros, null, null, null
		);

		return lct_datos;
	}

	/** {@inheritdoc} */
	public Solicitud findInteresados(String as_parametros, boolean ab_b)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud lct_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lct_datos = getConsultaSolicituddBusiness().findInteresados(as_parametros, ab_b);

		Logger.log(lsw_watch, "ConsultaSolicitudBusiness", "findInteresados", as_parametros, null, null, null);

		return lct_datos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Predio> findMatriculaBySolicitud(String as_parametros)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<Predio> lcp_predios;
		lsw_watch       = Logger.getNewStopWatch();
		lcp_predios     = getConsultaSolicituddBusiness().findMatriculaBySolicitud(as_parametros);

		Logger.log(lsw_watch, "ConsultaSolicitudBusiness", "findMatriculaBySolicitud", as_parametros, null, null, null);

		return lcp_predios;
	}

	/** {@inheritdoc} */
	public Collection<PersonaSolicitud> findPersonasByIdSolicitud(String as_parametros)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<PersonaSolicitud> lct_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lct_datos = getConsultaSolicituddBusiness().findPersonasByIdSolicitud(as_parametros);

		Logger.log(
		    lsw_watch, "ConsultaSolicitudBusiness", "findPersonasByIdSolicitud", as_parametros, null, null, null
		);

		return lct_datos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Segregacion> findSegregacion(String as_parametros)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<Segregacion> lcs_segregacion;
		lsw_watch           = Logger.getNewStopWatch();
		lcs_segregacion     = getConsultaSolicituddBusiness().findSegregacion(as_parametros);

		Logger.log(lsw_watch, "ConsultaSolicitudBusiness", "findSegregacion", as_parametros, null, null, null);

		return lcs_segregacion;
	}

	/**
	 * Retorna el valor de consulta solicitudd business.
	 *
	 * @return el valor de consulta solicitudd business
	 */
	private ConsultaSolicitudBusiness getConsultaSolicituddBusiness()
	{
		if(ict_business == null)
			ict_business = new ConsultaSolicitudBusiness();

		return ict_business;
	}
}
