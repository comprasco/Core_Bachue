package com.bachue.snr.prosnr01.ejb.session.stateless.devolucionesDinero;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.devolucionesDinero.DevolucionDineroBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.ui.DevolucionDineroUI;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades DevolucionesDineroBean.
 *
 * @author ccalderon
 */
@javax.ejb.Stateless(name = "DevolucionDinero", mappedName = "devolucionDineroMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class DevolucionDineroBean implements DevolucionDineroRemote
{
	/** Propiedad idd business. */
	private DevolucionDineroBusiness idd_business;

	/**
	 * Modifica el valor de devoluciones dinero.
	 *
	 * @param add_dd asigna el valor a la propiedad business
	 */
	public void setDevolucionesDinero(DevolucionDineroBusiness add_dd)
	{
		idd_business = add_dd;
	}

	/**
	 * Retorna el valor de reasignar turnos.
	 *
	 * @return el valor de reasignar turnos
	 */
	public DevolucionDineroBusiness getDevolucionesDinero()
	{
		if(idd_business == null)
			idd_business = new DevolucionDineroBusiness();

		return idd_business;
	}

	/**
	 * Método para consultar las personas a mostrar en pantalla en el tab datos del interesado.
	 *
	 * @param as_idTurno String idTurno filtro de búsqueda
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de remote ip
	 * @return Colección de Persona con la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public Collection<Persona> bandejaDatosDelInteresado(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Persona> lcp_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcp_return = getDevolucionesDinero().bandejaDatosDelInteresado(as_idTurno);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "bandejaDatosDelInteresado", as_userId, as_localIp, as_localIp, null
		);

		return lcp_return;
	}

	/**
	 * Método para realizar consulta devolución.
	 *
	 * @param as_idTurno de as id turno
	 * @param ab_consultaNir de ab consulta nir
	 * @param add_devolDinero de add devol dinero
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param remoteIp de remote ip
	 * @return el valor de DevolucionesDineroUI
	 * @throws B2BException de b 2 B exception
	 */
	@Override
	public DevolucionDineroUI consultaBandejaDevolucion(
	    String as_idTurno, boolean ab_consultaNir, DevolucionDinero add_devolDinero, String as_userId, String as_localIp,
	    String remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DevolucionDineroUI lct_return;

		lsw_watch     = Logger.getNewStopWatch();

		lct_return = getDevolucionesDinero().consultaBandejaDevolucion(as_idTurno, ab_consultaNir, add_devolDinero);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "validacionesConsultaDevolucion", as_userId, as_localIp, as_localIp,
		    null
		);

		return lct_return;
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void devolucionDinero(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getDevolucionesDinero().devolucionDinero(as_remoteIp);

		Logger.log(lsw_watch, "DevolucionesDineroBean", "devolucionDinero", null, null, as_remoteIp, null);
	}

	/**
	 * Método para consultar devolución dinero por id solicitud.
	 *
	 * @param as_idSolicitud String con filtro de búsqueda
	 * @return DevolucionDinero objeto consultado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public DevolucionDinero findDevolucionDineroByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		DevolucionDinero ldd_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldd_return = getDevolucionesDinero().findDevolucionDineroByIdSolicitud(as_idSolicitud);

		Logger.log(lsw_watch, "DevolucionesDineroBean", "bandejaDatosDelInteresado", null, null, null, null);

		return ldd_return;
	}

	/** {@inheritdoc} */
	public void generarDocumentoCertificacionRecaudo(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getDevolucionesDinero().generarDocumentoCertificacionRecaudo(as_idSolicitud, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "generarDocumentoCertificacionRecaudo", as_userId, as_localIp,
		    as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public boolean buscarDocumentoCertificacionRecaudo(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getDevolucionesDinero().buscarDocumentoCertificacionRecaudo(as_idSolicitud);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "generarDocumentoCertificacionRecaudo", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return lb_return;
	}

	/**
	 * Método para salvar actos de devolución de dinero y terminar la etapa con su correspondiente id motivo
	 *
	 * @param addui_datosSalvarUI contenedor de la información a salvar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de DevolucionesDineroUI
	 * @throws B2BException de b 2 B exception
	 */
	@Override
	public DevolucionDineroUI salvarActosDevolucionDinero(
	    DevolucionDineroUI addui_datosSalvarUI, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DevolucionDineroUI lct_return;

		lsw_watch     = Logger.getNewStopWatch();

		lct_return = getDevolucionesDinero().salvarActosDevolucionDinero(addui_datosSalvarUI, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "salvarActosDevolucionDinero", as_userId, as_localIp, as_localIp, null
		);

		return lct_return;
	}

	/**
	 * Método encargado de salvar los datos de cuenta bancaria de devolución de dinero.
	 *
	 * @param add_parametros Argumento de tipo <code>DevolucionDinero</code> que contiene los criterios necesarios para salvar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void salvarDatosCuentaBancaria(
	    DevolucionDinero add_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getDevolucionesDinero().salvarDatosCuentaBancaria(add_parametros, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "salvarDatosCuentaBancaria", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Método encargado de salvar los datos del interesado de devolución de dinero.
	 *
	 * @param addu_parametros Argumento de tipo <code>DevolucionDineroUI</code> que contiene los criterios necesarios para salvar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void salvarDatosInteresadoDevDinero(
	    DevolucionDineroUI addu_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getDevolucionesDinero().salvarDatosInteresadoDevDinero(addu_parametros, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "salvarDatosInteresadoDevDinero", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/**
	 * Método para salvar el Tab de datos trámite.
	 *
	 * @param addui_datosSalvarUI contenedor de la información a salvar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de DevolucionesDineroUI
	 * @throws B2BException de b 2 B exception
	 */
	@Override
	public DevolucionDineroUI salvarTabDatosTramite(
	    DevolucionDineroUI addui_datosSalvarUI, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DevolucionDineroUI lct_return;

		lsw_watch     = Logger.getNewStopWatch();

		lct_return = getDevolucionesDinero().salvarTabDatosTramite(addui_datosSalvarUI, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "salvarTabDatosTramite", as_userId, as_localIp, as_localIp, null
		);

		return lct_return;
	}

	/**
	 * Método para guardar el titular de la cuenta precargado con la solicitud.
	 *
	 * @param add_devolucionDinero Objeto DevolucionDinero con información ingresada en pantalla
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void salvarTitularCuenta(
	    DevolucionDinero add_devolucionDinero, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getDevolucionesDinero().salvarTitularCuenta(add_devolucionDinero, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "salvarTitularCuenta", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Método para terminar el proceso de devoluciones.
	 *
	 * @param addu_devolucionDineroUi Argumento de tipo <code>DevolucionDineroUI</code> que contiene los criterios necesarios para terminar proceso.
	 * @param as_idTurnoAnterior String de turno anterior
	 * @param as_solicitud Objeto solicitud
	 * @param acacd_completiduDocumental de acacd completidu documental
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de remote ip
	 * @param as_userId de as user id
	 * @return Registro Contenedor de información
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public Registro terminarProcesoDevolucionDinero(
	    DevolucionDineroUI addu_devolucionDineroUi, String as_idTurnoAnterior, Solicitud as_solicitud,
	    Collection<AccCompletitudDocumental> acacd_completiduDocumental, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lr_return = getDevolucionesDinero()
				            .terminarProcesoDevolucionDinero(
				    addu_devolucionDineroUi, as_idTurnoAnterior, as_solicitud, acacd_completiduDocumental, as_localIp,
				    as_remoteIp, as_userId
				);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "salvarTitularCuenta", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_return;
	}

	/**
	 * Método encargado de validar los datos a salvar para los interesados de devolución de dinero.
	 *
	 * @param add_parametros Argumento de tipo <code>DevolucionDinero</code> que contiene los criterios necesarios para validar.
	 * @param ab_salvarSolicitud Argumento de tipo <code>boolean</code> que determina si se debe salvar los datos en la solicitud.
	 * @param ab_validarDatosPersona Argumento de tipo <code>boolean</code> que determina si se debe validar los datos de la persona.
	 * @param ab_validarTelefono Argumento de tipo <code>boolean</code> que determina si se debe validar el número telefónico.
	 * @param ab_soloValidar Argumento de tipo <code>boolean</code> que determina si solo se debe validar o no.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void validarDatosDelInteresado(
	    DevolucionDinero add_parametros, boolean ab_salvarSolicitud, boolean ab_validarDatosPersona,
	    boolean ab_validarTelefono, boolean ab_soloValidar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getDevolucionesDinero()
			    .validarDatosDelInteresado(
			    add_parametros, ab_salvarSolicitud, ab_validarDatosPersona, ab_validarTelefono, ab_soloValidar,
			    as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "salvarDatosInteresadoDevDinero", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_idActo de as id acto
	 * @param as_idTurno de as id turno
	 * @return el valor de acto devolucion dinero
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public ActoDevolucionDinero validarTurnoSeleccionado(String as_idActo, String as_idTurno)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		ActoDevolucionDinero ladd_return;

		lsw_watch     = Logger.getNewStopWatch();

		ladd_return = getDevolucionesDinero().validarTurnoSeleccionado(as_idActo, as_idTurno);

		Logger.log(lsw_watch, "DevolucionesDineroBean", "validarTurnoSeleccionado", null, null, null, null);

		return ladd_return;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion cargarDatosActoAdministrativo(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actoAdministrativo;

		lsw_watch                  = Logger.getNewStopWatch();
		laa_actoAdministrativo     = getDevolucionesDinero()
				                             .cargarDatosActoAdministrativo(aaa_parametros, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "cargarDatosActoAdministrativo", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return laa_actoAdministrativo;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actOAdministrativO;

		lsw_watch                  = Logger.getNewStopWatch();
		laa_actOAdministrativO     = getDevolucionesDinero()
				                             .visualizarCambiosResolucion(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo
				);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "visualizarCambiosResolucion", as_userId, as_localIp, as_remoteIp, null
		);

		return laa_actOAdministrativO;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion generarDocumentosActoAdministrativo(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actoAdministrativo;

		lsw_watch                  = Logger.getNewStopWatch();
		laa_actoAdministrativo     = getDevolucionesDinero()
				                             .generarDocumentosActoAdministrativo(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo
				);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "generarDocumentosActoAdministrativo", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return laa_actoAdministrativo;
	}

	/** {@inheritdoc} */
	public void enviarResponsableActoAdministrativo(
	    TagPlantillaResolucion aaa_parametros, long al_idMotivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getDevolucionesDinero().enviarResponsableActoAdministrativo(
		    aaa_parametros, as_userId, as_remoteIp, al_idMotivo
		);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "enviarResponsableActoAdministrativo", as_userId, as_localIp,
		    as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public Collection<String> findDecisionTramiteRegistral(String as_turnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		Collection<String> lcs_decisionesTramite;
		lcs_decisionesTramite = getDevolucionesDinero().findDecisionTramiteRegistral(as_turnoHistoria);

		Logger.log(
		    lsw_watch, "DevolucionesDineroBean", "findDecisionTramiteRegistral", null, null, null, lcs_decisionesTramite
		);

		return lcs_decisionesTramite;
	}
}
