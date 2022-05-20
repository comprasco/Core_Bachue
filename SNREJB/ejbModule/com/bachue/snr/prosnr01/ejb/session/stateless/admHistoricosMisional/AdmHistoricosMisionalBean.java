package com.bachue.snr.prosnr01.ejb.session.stateless.admHistoricosMisional;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.admHistoricosMisional.AdmHistoricosMisionalBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmHomologacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.ui.PagosUI;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades AdmHistoricosMisionalBean.
 *
 * @author hcastaneda
 */
@javax.ejb.Stateless(name = "AdmHistoricosMisional", mappedName = "admHistoricosMisionalMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class AdmHistoricosMisionalBean implements AdmHistoricosMisionalRemote
{
	/** Propiedad iahmb business. */
	private AdmHistoricosMisionalBusiness iahmb_business;

	/**
	 * Consultar adm homologacion.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AdmHomologacion> consultarAdmHomologacion(
	    String as_turnoActualizado, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<AdmHomologacion> lcdas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdas_return = getBusiness().consultarAdmHomologacion(as_turnoActualizado);

		Logger.log(
		    lsw_watch, "AdmHistoricosMisionalBean", "consultarAdmHomologacion", as_idUsuario, as_localIp, as_remoteIp,
		    lcdas_return
		);

		return lcdas_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<SolicitudMatricula> consultarMatriculasPorSolicitud(
	    String as_idSolicitud, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_return;
		StopWatch                      lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcsm_return = getBusiness().consultarMatriculasPorSolicitud(as_idSolicitud);

		Logger.log(
		    lsw_watch, "AdmHistoricosMisionalBean", "consultarMatriculasPorSolicitud", as_idUsuario, as_localIp,
		    as_remoteIp, null
		);

		return lcsm_return;
	}

	/** {@inheritdoc} */
	public Collection<PagosUI> consultarPagos(
	    String as_turnoActualizado, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<PagosUI> lcpui_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcpui_return = getBusiness().consultarPagos(as_turnoActualizado);

		Logger.log(
		    lsw_watch, "AdmHistoricosMisionalBean", "consultarPagos", as_idUsuario, as_localIp, as_remoteIp,
		    lcpui_return
		);

		return lcpui_return;
	}

	/** {@inheritdoc} */
	@Override
	public TramiteCantidad generarDatosTramiteCantidadTurnosHomologacion(
	    String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		TramiteCantidad ltc_return;
		StopWatch       lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		ltc_return = getBusiness().generarDatosTramiteCantidadTurnosHomologacion(as_idTurno);

		Logger.log(
		    lsw_watch, "AdmHistoricosMisionalBean", "generarDatosTramiteCantidadTurnosHomologacion", as_idUsuario,
		    as_localIp, as_remoteIp, null
		);

		return ltc_return;
	}

	/** {@inheritdoc} */
	@Override
	public void guardarMatriculasAsociadas(
	    Collection<SolicitudMatricula> acsm_solicitudMatricula, String as_idTurno, String as_userId,
	    String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().guardarMatriculasAsociadas(acsm_solicitudMatricula, as_idTurno, as_userId, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "AdmHistoricosMisionalBean", "guardarMatriculasAsociadas", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);
	}

	/**
	 * Insertar acto homologado.
	 *
	 * @param aah_homologacion de aah homologacion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de adm homologacion
	 * @throws B2BException de b 2 B exception
	 */
	public AdmHomologacion insertarActoHomologado(
	    AdmHomologacion aah_homologacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		aah_homologacion = getBusiness().insertarActoHomologado(aah_homologacion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AdmHistoricosMisionalBean", "consultarAdmHomologacion", as_userId, as_localIp, as_remoteIp, null
		);

		return aah_homologacion;
	}

	/**
	 * Terminar proceso homologacion.
	 *
	 * @param aah_homologacion de aah homologacion
	 * @param ab_administracionHomologacionActosLiquidacion de ab administracion homologacion actos liquidacion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void terminarProcesoHomologacion(
	    AdmHomologacion aah_homologacion, boolean ab_administracionHomologacionActosLiquidacion, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .terminarProcesoHomologacion(
			    aah_homologacion, ab_administracionHomologacionActosLiquidacion, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "AdmHistoricosMisionalBean", "terminarProcesoHomologacion", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private AdmHistoricosMisionalBusiness getBusiness()
	{
		if(iahmb_business == null)
			iahmb_business = new AdmHistoricosMisionalBusiness();

		return iahmb_business;
	}
}
