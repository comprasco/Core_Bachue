package com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.actuaciones.administrativas.ActuacionesAdministrativasBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ActuacionesAdministrativasBean.
 *
 * @author Gabriel Arias
 */
@javax.ejb.Stateless(name = "actuacionesAdministrativas", mappedName = "actuacionesAdministrativasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ActuacionesAdministrativasBean implements ActuacionesAdministrativasRemote
{
	/**  Atributo iaab_actuacionesAdministrativasBusiness. */
	private ActuacionesAdministrativasBusiness iaab_actuacionesAdministrativasBusiness;

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public ActuacionesAdministrativasBusiness getActuacionesAdministrativasBusiness()
	{
		if(iaab_actuacionesAdministrativasBusiness == null)
			iaab_actuacionesAdministrativasBusiness = new ActuacionesAdministrativasBusiness();

		return iaab_actuacionesAdministrativasBusiness;
	}

	/** {@inheritdoc} */
	public Registro buscarDatosPorPersona(
	    Registro ar_registro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getActuacionesAdministrativasBusiness().buscarDatosPorPersona(ar_registro);

		Logger.log(lsw_watch, "ReferenceBean", "buscarDatosPorPersona", as_userId, as_localIp, as_remoteIp, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Registro buscarPersonasAsociadasRecursos(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getActuacionesAdministrativasBusiness().buscarPersonasAsociadasRecursos(as_idSolicitud);

		Logger.log(
		    lsw_watch, "ReferenceBean", "buscarPersonasAsociadasRecursos", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion cargarDatosActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .cargarDatosActuacionesAdministrativas(
				    aaa_parametros, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "cargarDatosActuacionesAdministrativas", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion cargarDatosActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, boolean ab_etapa651, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .cargarDatosActuacionesAdministrativas(
				    aaa_parametros, ab_etapa651, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "cargarDatosActuacionesAdministrativas", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion cargarDatosRechazaRecursos(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .cargarDatosRechazaRecursos(
				    aaa_parametros, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "ReferenceBean", "cargarDatosRechazaRecursos", as_userId, as_localIp, as_remoteIp, null);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public boolean documentosEnviadosOWCC(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_enviados;

		lsw_watch       = Logger.getNewStopWatch();
		lb_enviados     = getActuacionesAdministrativasBusiness()
				                  .documentosEnviadosOWCC(aaa_parametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ReferenceBean", "documentosEnviadosOWCC", as_userId, as_localIp, as_remoteIp, null);

		return lb_enviados;
	}

	/** {@inheritdoc} */
	public boolean documentosEnviadosOWCCBySolicitud(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_enviados;

		lsw_watch       = Logger.getNewStopWatch();
		lb_enviados     = getActuacionesAdministrativasBusiness()
				                  .documentosEnviadosOWCCBySolicitud(as_idSolicitud, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ReferenceBean", "documentosEnviadosOWCCBySolicitud", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_enviados;
	}

	/** {@inheritdoc} */
	public void enviarAlAprobadorRechazoRecurso(
	    TagPlantillaResolucion aaa_parametros, boolean ab_etapa460, long al_idMotivo, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getActuacionesAdministrativasBusiness()
			    .enviarAlAprobadorRechazoRecurso(aaa_parametros, ab_etapa460, as_userId, as_remoteIp, al_idMotivo);

		Logger.log(
		    lsw_watch, "ReferenceBean", "enviarAlAprobadorRechazoRecurso", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public boolean enviarAprobarProrrogaDocumentacion(
	    TurnoHistoria ath_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getActuacionesAdministrativasBusiness()
				                .enviarAprobarProrrogaDocumentacion(ath_param, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ReferenceBean", "enviarAprobarProrrogaDocumentacion", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void enviarResponsableActuacionesAdmin(
	    TagPlantillaResolucion aaa_parametros, long al_idMotivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getActuacionesAdministrativasBusiness()
			    .enviarResponsableActuacionesAdmin(aaa_parametros, as_userId, as_remoteIp, al_idMotivo);

		Logger.log(
		    lsw_watch, "ReferenceBean", "enviarResponsableActuacionesAdmin", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion generarDocumentosActuacionesAdmin(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .generarDocumentosActuacionesAdmin(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "generarDocumentosActuacionesAdmin", as_userId, as_localIp, as_remoteIp, null
		);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion generarDocumentosActuacionesAdmin(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp,
	    boolean ab_651
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .generarDocumentosActuacionesAdmin(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo, ab_651
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "generarDocumentosActuacionesAdmin", as_userId, as_localIp, as_remoteIp, null
		);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion generarDocumentosRechazaRecurso(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .generarDocumentosRechazaRecursos(
				    aaa_parametros, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "generarDocumentosRechazaRecurso", as_userId, as_localIp, as_remoteIp, null
		);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion generarDocumentosTraslados(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .generarDocumentosTraslados(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo
				);

		Logger.log(lsw_watch, "ReferenceBean", "generarDocumentosTraslados", as_userId, as_localIp, as_remoteIp, null);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public boolean prorrogaDocumentacion(String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getActuacionesAdministrativasBusiness().prorrogaDocumentacion(as_idTurnoHistoria);

		Logger.log(lsw_watch, "ReferenceBean", "prorrogaDocumentacion", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion validarCondicionesDecision(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .validarCondicionesDecision(
				    aaa_parametros, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "ReferenceBean", "validarCondicionesDecision", as_userId, as_localIp, as_remoteIp, null);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .visualizarCambiosResolucion(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo
				);

		Logger.log(lsw_watch, "ReferenceBean", "visualizarCambiosResolucion", as_userId, as_localIp, as_remoteIp, null);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, boolean ab_etapa651, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .visualizarCambiosResolucion(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo, ab_etapa651
				);

		Logger.log(lsw_watch, "ReferenceBean", "visualizarCambiosResolucion", as_userId, as_localIp, as_remoteIp, null);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion visualizarCambiosResolucionRechazaRecurso(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getActuacionesAdministrativasBusiness()
				                                     .visualizarCambiosResolucionRechazaRecurso(
				    aaa_parametros, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "visualizarCambiosResolucionRechazaRecurso", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return laa_actuacionesAdministrativas;
	}
}
