package com.bachue.snr.prosnr01.ejb.session.stateless.grabacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.grabacion.GrabacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades GrabacionBean.
 *
 * @author Gabriel Arias
 */
@javax.ejb.Stateless(name = "grabacion", mappedName = "grabacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GrabacionBean implements GrabacionRemote
{
	/** Propiedad igb business. */
	private GrabacionBusiness igb_business;

	/** {@inheritdoc} */
	public OficiosTexto cargarOficioTexto(String as_idTurno, String as_motivoTramite)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		OficiosTexto lot_return;

		lsw_watch      = Logger.getNewStopWatch();
		lot_return     = getBusiness().cargarOficioTexto(as_idTurno, as_motivoTramite);

		Logger.log(lsw_watch, "GrabacionBusiness", "cargarOficioTexto", null, null, null, null);

		return lot_return;
	}

	/** {@inheritdoc} */
	public DatosBasicos consultarDatosBasicos(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		DatosBasicos ldb_return;

		lsw_watch      = Logger.getNewStopWatch();
		ldb_return     = getBusiness().consultarDatosBasicos(ath_turnoHistoria);

		Logger.log(lsw_watch, "GrabacionBusiness", "consultarDatosBasicos", null, null, null, null);

		return ldb_return;
	}

	/** {@inheritdoc} */
	public void consultarExistenciaPredio(String as_idTurno)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().consultarExistenciaPredio(as_idTurno);

		Logger.log(lsw_watch, "GrabacionBusiness", "consultarExistenciaPredio", null, null, null, null);
	}

	/** {@inheritdoc} */
	public DatosDelInteresado consultarPersonaInteresado(String as_idSolicitud, boolean ab_fullData)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DatosDelInteresado lddi_return;

		lsw_watch       = Logger.getNewStopWatch();
		lddi_return     = getBusiness().consultarPersonaInteresado(as_idSolicitud, ab_fullData);

		Logger.log(lsw_watch, "GrabacionBusiness", "consultarPersonaInteresado", null, null, null, null);

		return lddi_return;
	}

	/** {@inheritdoc} */
	public DatosAntSistema consultarPredioGrabar(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		DatosAntSistema ldas_return;

		lsw_watch       = Logger.getNewStopWatch();
		ldas_return     = getBusiness().consultarPredioGrabar(ath_turnoHistoria);

		Logger.log(lsw_watch, "GrabacionBusiness", "consultarPredioGrabar", as_userId, as_localIp, as_remoteIp, null);

		return ldas_return;
	}

	/** {@inheritdoc} */
	public boolean enviarAprobador104(
	    String as_idTurnoHistoria, String as_userId, String as_ipLocal, String as_ipRemote
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().enviarAprobador104(as_idTurnoHistoria, as_userId, as_ipRemote);

		Logger.log(lsw_watch, "GrabacionBusiness", "findInboxByUserId", as_userId, as_ipLocal, as_ipRemote, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> findInboxByUserId(String as_userId, String as_idTurno, String as_nir)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lctc_return;

		lsw_watch       = Logger.getNewStopWatch();
		lctc_return     = getBusiness().findInboxByUserId(as_userId, as_idTurno, as_nir);

		Logger.log(lsw_watch, "GrabacionBusiness", "findInboxByUserId", as_userId, null, null, null);

		return lctc_return;
	}

	/** {@inheritdoc} */
	public byte[] generarDocumentoNegacion(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress,
	    boolean ab_salvar
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_return;

		lsw_watch      = Logger.getNewStopWatch();
		lba_return     = getBusiness()
				                 .generarDocumentoNegacion(
				    as_parametros, as_userId, as_remoteIpAddress, ab_salvar, false
				);

		Logger.log(
		    lsw_watch, "GrabacionBusiness", "generarDocumento", as_userId, as_localIpAddress, as_remoteIpAddress, null
		);

		return lba_return;
	}

	/** {@inheritdoc} */
	public byte[] generarDocumentoResolucion(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress,
	    boolean ab_salvar
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_return;

		lsw_watch      = Logger.getNewStopWatch();
		lba_return     = getBusiness()
				                 .generarDocumentoResolucion(
				    as_parametros, as_userId, as_remoteIpAddress, ab_salvar, false
				);

		Logger.log(
		    lsw_watch, "GrabacionBusiness", "generarDocumentoResolucion", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);

		return lba_return;
	}

	/** {@inheritdoc} */
	public DatosBasicos generarPredio(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		DatosBasicos ldb_return;

		lsw_watch      = Logger.getNewStopWatch();
		ldb_return     = getBusiness().generarPredio(adb_datosBasicos, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "GrabacionBusiness", "generarPredio", as_userId, as_localIp, as_remoteIp, null);

		return ldb_return;
	}

	/** {@inheritdoc} */
	public void generarResolucion(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().generarResolucion(as_parametros, as_userId, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "GrabacionBusiness", "generarResolucion", as_userId, as_localIpAddress, as_remoteIpAddress, null
		);
	}

	/** {@inheritdoc} */
	public void negarSolicitud(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().negarSolicitud(as_parametros, as_userId, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "GrabacionBusiness", "negarSolicitud", as_userId, as_localIpAddress, as_remoteIpAddress, null
		);
	}

	/** {@inheritdoc} */
	public boolean salvarGrabacionMatriculas(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().salvarGrabacionMatriculas(as_parametros, as_userId, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "GrabacionBusiness", "salvarGrabacionMatriculas", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean validarExistenciaMatricula(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().validarExistenciaMatricula(as_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "GrabacionBusiness", "validarExistenciaMatricula", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void validarPredioGrabar(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().validarPredioGrabar(ath_turnoHistoria);

		Logger.log(lsw_watch, "GrabacionBusiness", "validarPredioGrabar", null, null, null, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private GrabacionBusiness getBusiness()
	{
		if(igb_business == null)
			igb_business = new GrabacionBusiness();

		return igb_business;
	}
}
