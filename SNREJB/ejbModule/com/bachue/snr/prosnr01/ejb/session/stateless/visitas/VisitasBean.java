package com.bachue.snr.prosnr01.ejb.session.stateless.visitas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.visitas.VisitasBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.business.DetalleEjecucionVisitas;
import com.bachue.snr.prosnr01.model.business.EjecucionVisitas;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaTipo;

import org.perf4j.StopWatch;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades VisitasBean.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 3/08/2020
 */
@javax.ejb.Stateless(name = "Visitas", mappedName = "visitasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class VisitasBean implements VisitasRemote
{
	/** Propiedad ivb business. */
	private VisitasBusiness ivb_business;

	/** {@inheritdoc} */
	public void accionEnviarDelegadoRegistro(
	    String as_idSolicitud, String as_idSubproceso, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getVisitasBusiness().accionEnviarDelegadoRegistro(as_idSolicitud, as_idSubproceso, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "VisitasBean", "salvarUsuariosVisitadores", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void aprobacionEjecucionVisitas(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getVisitasBusiness().aprobacionEjecucionVisitas(as_remoteIp);

		Logger.log(lsw_watch, "VisitasBean", "aprobacionEjecucionVisitas", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public List<Map<String, Object>> buscarSolicitudConPersona(
	    String as_idSolicitud, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		List<Map<String, Object>> laee_datos;

		lsw_watch     = Logger.getNewStopWatch();

		laee_datos = getVisitasBusiness().buscarSolicitudConPersona(as_idSolicitud);

		Logger.log(
		    lsw_watch, "VisitasBean", "buscarSolicitudConPersona", as_idUsuario, as_localIp, as_remoteIp, laee_datos
		);

		return laee_datos;
	}

	/** {@inheritdoc} */
	public EjecucionVisitas cargarDatosEjecucionVisitas(String as_idSolicitud, String as_userId)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		EjecucionVisitas lev_infoSolicitud;

		lsw_watch             = Logger.getNewStopWatch();
		lev_infoSolicitud     = getVisitasBusiness().cargarDatosEjecucionVisitas(as_idSolicitud, as_userId);

		Logger.log(lsw_watch, "VisitasBean", "cargarDatosEjecucionVisitas", null, null, null, null);

		return lev_infoSolicitud;
	}

	/** {@inheritdoc} */
	public Collection<TramiteVisitaTipo> cargarDatosPanelTipoTramitesARealizar(
	    String as_idTramiteVisita, String as_idRol
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<TramiteVisitaTipo> lctvt_tipoTramitesARealizar;

		lsw_watch                       = Logger.getNewStopWatch();
		lctvt_tipoTramitesARealizar     = getVisitasBusiness()
				                                  .cargarDatosPanelTipoTramitesARealizar(as_idTramiteVisita, as_idRol);

		Logger.log(
		    lsw_watch, "VisitasBean", "cargarDatosPanelTipoTramitesARealizar", null, null, null,
		    lctvt_tipoTramitesARealizar
		);

		return lctvt_tipoTramitesARealizar;
	}

	/** {@inheritdoc} */
	public DetalleEjecucionVisitas cargarDetalleEjecucionVisitas(
	    String as_tipoTramiteSeleccionado, String as_idSolicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		DetalleEjecucionVisitas lev_infoSolicitud;

		lsw_watch             = Logger.getNewStopWatch();
		lev_infoSolicitud     = getVisitasBusiness()
				                        .cargarDetalleEjecucionVisitas(
				    as_tipoTramiteSeleccionado, as_idSolicitud, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "VisitasBean", "cargarDatosEjecucionVisitas", as_userId, as_localIp, as_remoteIp, null);

		return lev_infoSolicitud;
	}

	/** {@inheritdoc} */
	public String[] cargarTextoCampos(
	    boolean ab_auto, String as_idSolicitudVisitas, String as_idUsuario, String as_localIp, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String[]  las_return;

		lsw_watch      = Logger.getNewStopWatch();
		las_return     = getVisitasBusiness().cargarTextoCampos(ab_auto, as_idSolicitudVisitas);

		Logger.log(lsw_watch, "VisitasBean", "cargarTextoCampos", as_idUsuario, as_localIp, as_ipRemota, null);

		return las_return;
	}

	/** {@inheritdoc} */
	public Collection<Usuario> consultarUsuariosReasignacion(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Usuario> lcu_usuarios;

		lsw_watch        = Logger.getNewStopWatch();
		lcu_usuarios     = getVisitasBusiness()
				                   .consultarUsuariosReasignacion(as_idSolicitud, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "VisitasBean", "consultarUsuariosReasignacion", as_userId, as_localIp, as_remoteIp, null);

		return lcu_usuarios;
	}

	/** {@inheritdoc} */
	public byte[] generarAutoOrdenaVisitaGeneral(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_return;

		lsw_watch      = Logger.getNewStopWatch();
		lba_return     = getVisitasBusiness()
				                 .generarAutoOrdenaVisitaGeneral(
				    as_idSolicitudVisitas, aot_texto, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "VisitasBean", "generarAutoOrdenaVisitaGeneral", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_return;
	}

	/** {@inheritdoc} */
	public DetalleEjecucionVisitas generarDocumentos(
	    String as_idConstante, String as_idSolicitudVisitas, OficiosTexto aot_textoTags, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		DetalleEjecucionVisitas lev_infoSolicitud;

		lsw_watch             = Logger.getNewStopWatch();
		lev_infoSolicitud     = getVisitasBusiness()
				                        .generarDocumentos(
				    as_idConstante, as_idSolicitudVisitas, aot_textoTags, as_userId, as_remoteIp
				);
		Logger.log(lsw_watch, "VisitasBean", "cargarDatosEjecucionVisitas", as_userId, as_localIp, as_remoteIp, null);

		return lev_infoSolicitud;
	}

	/** {@inheritdoc} */
	public byte[] generarResolucionOrdenaIntervencion(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_return;

		lsw_watch      = Logger.getNewStopWatch();
		lba_return     = getVisitasBusiness()
				                 .generarResolucionOrdenaIntervencion(
				    as_idSolicitudVisitas, aot_texto, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "VisitasBean", "generarResolucionOrdenaIntervencion", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_return;
	}

	/** {@inheritdoc} */
	public void guardarDocumentoOWCC(
	    String as_idSolicitud, String as_idUsuario, String as_localIp, String as_ipRemota, boolean ab_auto
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getVisitasBusiness().guardarDocumentoOWCC(as_idSolicitud, as_idUsuario, as_ipRemota, ab_auto);

		Logger.log(lsw_watch, "VisitasBean", "salvarUsuariosVisitadores", as_idUsuario, as_localIp, as_ipRemota, null);
	}

	/** {@inheritdoc} */
	public void salvarEjecucionVisitas(
	    boolean ab_procesoReasignacion, Collection<Usuario> acu_usuarios,
	    String as_idConstante, String as_idSolicitudVisitas, byte[] aba_documento, OficiosTexto aot_solucionTags,
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getVisitasBusiness()
			    .salvarEjecucionVisitas(
			    ab_procesoReasignacion, acu_usuarios, as_idConstante, as_idSolicitudVisitas,
			    aba_documento, aot_solucionTags, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "VisitasBean", "cargarDatosPanelTipoTramitesARealizar", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public SolicitudVisitas salvarInformacionVisitas(
	    String as_idSolicitudComp, SolicitudVisitas asv_solicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		SolicitudVisitas lsv_solicitud;
		StopWatch        lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lsv_solicitud = getVisitasBusiness()
				                .salvarInformacionVisitas(as_idSolicitudComp, asv_solicitud, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "VisitasBean", "salvarInformacionVisitas", as_userId, as_localIp, as_remoteIp, null);

		return lsv_solicitud;
	}

	/** {@inheritdoc} */
	public String salvarUsuariosVisitadores(
	    String as_nirComp, Collection<Usuario> acu_usuarios, String as_idSolicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_nir;

		lsw_watch     = Logger.getNewStopWatch();
		ls_nir        = getVisitasBusiness()
				                .salvarUsuariosVisitadores(
				    as_nirComp, acu_usuarios, as_idSolicitud, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "VisitasBean", "salvarUsuariosVisitadores", as_userId, as_localIp, as_remoteIp, null);

		return ls_nir;
	}

	/**
	 * Retorna Objeto o variable de valor visitas business.
	 *
	 * @return el valor de visitas business
	 */
	private VisitasBusiness getVisitasBusiness()
	{
		if(ivb_business == null)
			ivb_business = new VisitasBusiness();

		return ivb_business;
	}
}
