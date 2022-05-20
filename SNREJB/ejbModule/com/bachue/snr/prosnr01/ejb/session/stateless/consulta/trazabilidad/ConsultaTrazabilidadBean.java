package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.trazabilidad.ConsultaTrazabilidadBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.InformacionDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.ui.FoliosSirUI;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ConsultaTrazabilidadBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "ConsultaTrazabilidad", mappedName = "consultaTrazabilidadMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaTrazabilidadBean implements ConsultaTrazabilidadRemote
{
	/** Propiedad ict business. */
	private ConsultaTrazabilidadBusiness ict_business;

	/**
	 * Cargar bandeja historicos.
	 *
	 * @param at_turnoActualizado de at turno actualizado
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de FoliosSirUI
	 * @throws B2BException de b 2 B exception
	 */
	public FoliosSirUI cargarBandejaHistoricos(
	    Turno at_turnoActualizado, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		FoliosSirUI lfsui_return;

		lsw_watch     = Logger.getNewStopWatch();

		lfsui_return = getConsultaTrazabilidadBusiness().cargarBandejaHistoricos(at_turnoActualizado);

		Logger.log(
		    lsw_watch, "ConsultaTrazabilidadBean", "cargarBandejaHistoricos", as_idUsuario, as_localIp, as_remoteIp,
		    null
		);

		return lfsui_return;
	}

	/** {@inheritdoc} */
	public ConsultaTrazabilidad cargarInfoBandejaConsultaTrazabilidad(Trazabilidad at_parametros, boolean ab_onlyTurno)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		ConsultaTrazabilidad lct_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lct_datos     = getConsultaTrazabilidadBusiness()
				                .cargarInfoBandejaConsultaTrazabilidad(at_parametros, ab_onlyTurno);

		Logger.log(
		    lsw_watch, "ConsultaTrazabilidadBean", "cargarInfoBandejaConsultaTrazabilidad", null, null, null, null
		);

		return lct_datos;
	}

	/** {@inheritdoc} */
	public Trazabilidad detalleTurno(Turno at_turno)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		Trazabilidad lt_trazabilidad;

		lsw_watch           = Logger.getNewStopWatch();
		lt_trazabilidad     = getConsultaTrazabilidadBusiness().detalleTurno(at_turno);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "detalleTurno", null, null, null, null);

		return lt_trazabilidad;
	}

	/** {@inheritdoc} */
	public Collection<ConsultaTrazabilidad> findConsultaTraza(Turno at_parametros)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<ConsultaTrazabilidad> lcct_consultaTraza;

		lsw_watch              = Logger.getNewStopWatch();
		lcct_consultaTraza     = getConsultaTrazabilidadBusiness().findConsultaTraza(at_parametros);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findConsultaTraza", null, null, null, null);

		return lcct_consultaTraza;
	}

	/** {@inheritdoc} */
	public Collection<ConsultaTrazabilidad> findConsultaTrazaSolicitud(String as_parametros)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<ConsultaTrazabilidad> lcth_consultaTraza;

		lsw_watch              = Logger.getNewStopWatch();
		lcth_consultaTraza     = getConsultaTrazabilidadBusiness().findConsultaTrazaSolicitud(as_parametros);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findConsultaTrazaSolicitud", null, null, null, null);

		return lcth_consultaTraza;
	}

	/** {@inheritdoc} */
	public Collection<Trazabilidad> findDataAsociada(String as_idTurno)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<Trazabilidad> lct_return;

		lsw_watch      = Logger.getNewStopWatch();
		lct_return     = getConsultaTrazabilidadBusiness().findDataAsociada(as_idTurno);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findDataAsociada", null, null, null, null);

		return lct_return;
	}

	/** {@inheritdoc} */
	public ReasignarTurnos findDatosReasignacion(ReasignarTurnos art_rt)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		ReasignarTurnos lct_dato;

		lsw_watch     = Logger.getNewStopWatch();
		lct_dato      = getConsultaTrazabilidadBusiness().findDatosReasignacion(art_rt);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findDatosReasignacion", null, null, null, null);

		return lct_dato;
	}

	/** {@inheritdoc} */
	public InformacionDocumento findInfoDoc(Turno at_parametros)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		InformacionDocumento lid_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lid_datos     = getConsultaTrazabilidadBusiness().findInfoDoc(at_parametros);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findInfoDoc", null, null, null, null);

		return lid_datos;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> findMatriculas(String as_idTurno)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lct_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lct_datos     = getConsultaTrazabilidadBusiness().findMatriculas(as_idTurno);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findMatriculas", null, null, null, lct_datos);

		return lct_datos;
	}

	/** {@inheritdoc} */
	public Trazabilidad findProceso(Trazabilidad at_parametros)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		Trazabilidad lt_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lt_datos      = getConsultaTrazabilidadBusiness().findProceso(at_parametros);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findProceso", null, null, null, null);

		return lt_datos;
	}

	/** {@inheritdoc} */
	public Collection<Trazabilidad> findTrazabilidad(Trazabilidad at_parametros, boolean ab_onlyTurno)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<Trazabilidad> lct_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lct_datos     = getConsultaTrazabilidadBusiness().findTrazabilidad(at_parametros, ab_onlyTurno);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findTrazabilidad", null, null, null, lct_datos);

		return lct_datos;
	}

	/** {@inheritdoc} */
	public Collection<Trazabilidad> findTrazabilidadVinculados(Trazabilidad at_parametros)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<Trazabilidad> lct_consultaTraza;

		lsw_watch             = Logger.getNewStopWatch();
		lct_consultaTraza     = getConsultaTrazabilidadBusiness().findTrazabilidadVinculados(at_parametros);

		Logger.log(lsw_watch, "ConsultaTrazabilidadBean", "findTrazabilidadVinculados", null, null, null, null);

		return lct_consultaTraza;
	}

	/** {@inheritdoc} */
	public String findTurnosDerivadosConIndicadorVinculado(String as_s)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    lct_dato;

		lsw_watch     = Logger.getNewStopWatch();
		lct_dato      = getConsultaTrazabilidadBusiness().findTurnosDerivadosConIndicadorVinculado(as_s);

		Logger.log(
		    lsw_watch, "ConsultaTrazabilidadBean", "findTurnosDerivadosConIndicadorVinculado", null, null, null, null
		);

		return lct_dato;
	}

	/**
	 * Retorna el valor de consulta trazabilidad business.
	 *
	 * @return el valor de consulta trazabilidad business
	 */
	private ConsultaTrazabilidadBusiness getConsultaTrazabilidadBusiness()
	{
		if(ict_business == null)
			ict_business = new ConsultaTrazabilidadBusiness();

		return ict_business;
	}
}
