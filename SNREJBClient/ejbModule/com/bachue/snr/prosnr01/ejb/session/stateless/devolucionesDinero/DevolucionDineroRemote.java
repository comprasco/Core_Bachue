package com.bachue.snr.prosnr01.ejb.session.stateless.devolucionesDinero;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.ui.DevolucionDineroUI;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades DevolucionDineroRemote.
 *
 * @author  Carlos Calderon
 * Fecha de Creacion: 13/02/2020
 */
@Remote
public interface DevolucionDineroRemote
{
	/**
	 * Método para consultar las personas a mostrar en pantalla en el tab datos del interesado.
	 *
	 * @param as_idTurno String idTurno filtro de búsqueda
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Colección de Persona con la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> bandejaDatosDelInteresado(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de buscar si existe en base de datos el documento certificación recaudo
	 *
	 * @param as_idSolicitud Id de la solicitud desde la cual se está solicitando la construcción del documento
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return Si el documento ya fue generado
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public boolean buscarDocumentoCertificacionRecaudo(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para relizar validaciones iniciales de consulta devolución.
	 *
	 * @param as_idTurno del turno
	 * @param ab_consultaNir para saber si debe consultarse el nir
	 * @param add_devolDinero de add devol dinero
	 * @return el valor de DevolucionesDineroUI
	 */
	public DevolucionDineroUI consultaBandejaDevolucion(
	    String as_idTurno, boolean ab_consultaNir, DevolucionDinero add_devolDinero, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Devolucion dinero.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void devolucionDinero(String as_remoteIp)
	    throws B2BException;

	/**
	 * Método para consultar devolución dinero por id solicitud.
	 *
	 * @param as_idSolicitud String con filtro de búsqueda
	 * @return DevolucionDinero objeto consultado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDinero findDevolucionDineroByIdSolicitud(String as_idSolicitud)
	    throws B2BException;

	/**
	 * Método encargado de generar el documento certificación recaudo
	 *
	 * @param as_idSolicitud Id de la solicitud desde la cual se está solicitando la construcción del documento
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public void generarDocumentoCertificacionRecaudo(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para salvar actos de devolución de dinero y terminar la etapa con su correspondiente id motivo
	 *
	 * @param addui_datosSalvarUI addui_datosSalvarUI Interfaz con toda la información
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return DevolucionesDineroUI Objeto con información salvada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDineroUI salvarActosDevolucionDinero(
	    DevolucionDineroUI addui_datosSalvarUI, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de salvar los datos de cuenta bancaria de devolución de dinero.
	 *
	 * @param add_parametros Argumento de tipo <code>DevolucionDinero</code> que contiene los criterios necesarios para salvar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDatosCuentaBancaria(
	    DevolucionDinero add_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de salvar los datos del interesado de devolución de dinero.
	 *
	 * @param addu_parametros Argumento de tipo <code>DevolucionDineroUI</code> que contiene los criterios necesarios para salvar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDatosInteresadoDevDinero(
	    DevolucionDineroUI addu_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para salvar el tab de datos trámite.
	 *
	 * @param addui_datosSalvarUI addui_datosSalvarUI Interfaz con toda la información
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return DevolucionesDineroUI Objeto con información salvada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDineroUI salvarTabDatosTramite(
	    DevolucionDineroUI addui_datosSalvarUI, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar el titular de la cuenta precargado con la solicitud.
	 *
	 * @param add_devolucionDinero Objeto DevolucionDinero con información ingresada en pantalla
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTitularCuenta(
	    DevolucionDinero add_devolucionDinero, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para terminar el proceso de devoluciones.
	 *
	 * @param addu_devolucionDineroUi Argumento de tipo <code>DevolucionDineroUI</code> que contiene los criterios necesarios para terminar proceso.
	 * @param as_idTurnoAnterior String de turno anterior
	 * @param as_solicitud Objeto solicitud
	 * @param aacc_completitudDocumental de aacc completitud documental
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @return Registro Contenedor de información
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Registro terminarProcesoDevolucionDinero(
	    DevolucionDineroUI addu_devolucionDineroUi, String as_idTurnoAnterior, Solicitud as_solicitud,
	    Collection<AccCompletitudDocumental> aacc_completitudDocumental, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException;

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
	public void validarDatosDelInteresado(
	    DevolucionDinero add_parametros, boolean ab_salvarSolicitud, boolean ab_validarDatosPersona,
	    boolean ab_validarTelefono, boolean ab_soloValidar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar turno seleccionado.
	 *
	 * @param as_idActo de id acto
	 * @param as_idTurno de id turno
	 * @return de acto devolucion dinero
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ActoDevolucionDinero validarTurnoSeleccionado(String as_idActo, String as_idTurno)
	    throws B2BException;

	/**
	 * Metodo encargado de cargar la información de acto administrativo.
	 *
	 * @param aaa_parametros Argumento de tipo <code>ActoAdministrativo</code> que contiene los argumentos necesarios para realizar la búsqueda.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los datos encotrados para actuaciones administrativas.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion cargarDatosActoAdministrativo(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de enviar al responsable de acto administrativo.
	 *
	 * @param aaa_parametros Objeto que contiene la información para enviar al responsable de acto administrativo.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de actuaciones administrativas
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de generar documentos de actuaciones administrativas.
	 *
	 * @param aaa_parametros Objeto que contiene la información para generar los pdf.
	 * @param al_motivo Variable de tipo <code>long</code> que contiene la decision seleccionada.
	 * @param as_userId Variable de tipo <code>String</code> que contiene el id del usuario que está realizando el trámite.
	 * @param as_localIp Variable de tipo <code>String</code> que contiene la ip desde donde se está realizando el trámite.
	 * @param as_remoteIp Variable de tipo <code>String</code> que contiene la ip del usuario que está realizando el trámite.
	 * @return Variable de tipo byte <code>Array</code> que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion generarDocumentosActoAdministrativo(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de enviar al responsable de acto administrativo.
	 *
	 * @param aaa_parametros Objeto que contiene la información del proceso a terminar.
	 * @param al_idMotivo Variable String que contiene el id motivo a terminar.
	 * @param as_userId Variable String que contiene el usuario que realiza la operación
	 * @param as_localIp Variable String que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Variable String que contiene la ip remota desde donde se realiza la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarResponsableActoAdministrativo(
	    TagPlantillaResolucion aaa_parametros, long al_idMotivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método de consulta de la constante de DecisionTramiteRegistral
	 * @param ls_turnoHistoria con el turno historia de la solicitud
	 * @throws B2BException
	 */
	public Collection<String> findDecisionTramiteRegistral(String ls_turnoHistoria)
	    throws B2BException;
}
