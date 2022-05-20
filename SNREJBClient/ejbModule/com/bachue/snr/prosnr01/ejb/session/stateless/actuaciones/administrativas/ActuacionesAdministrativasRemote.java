package com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ActuacionesAdministrativasRemote.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 11/03/2020
 */
@Remote
public interface ActuacionesAdministrativasRemote
{
	/**
	 * Método encargado de consultar los datos de personas vinculadas a una solicitud con proceso de recursos.
	 *
	 * @param ar_registro Argumento de tipo <code>Registro</code> que contiene los criterios para realizar la consulta.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación
	 * @return Retorna objeto de tipo <code>Registro</code> que contiene el listado de personas que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Registro buscarDatosPorPersona(
	    Registro ar_registro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de consultar las personas vinculadas a una solicitud con proceso de recursos.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id solicitud a consultar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación
	 * @return Retorna objeto de tipo <code>Registro</code> que contiene el listado de personas que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Registro buscarPersonasAsociadasRecursos(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 *
	 * @param aaa_parametros Argumento de tipo <code>ActuacionesAdministrativas</code> que contiene los argumentos necesarios para realizar la búsqueda.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los datos encotrados para actuaciones administrativas.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion cargarDatosActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar datos actuaciones administrativas.
	 *
	 * @param aaa_parametros de aaa parametros
	 * @param ab_etapa651 de ab etapa 651
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tag plantilla resolucion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TagPlantillaResolucion cargarDatosActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, boolean ab_etapa651, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo encargado de cargar la información de rechaza recurso.
	 *
	 * @param aaa_parametros Argumento de tipo <code>ActuacionesAdministrativas</code> que contiene los argumentos necesarios para realizar la búsqueda.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los datos encotrados para actuaciones administrativas.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion cargarDatosRechazaRecursos(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar si se enviaron documentos al OWCC por id turno historia
	 *
	 * @param aaa_parametros Objeto que contiene la información para realizar la consulta.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_localIp Variable de tipo String que contiene la ip local del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip remota del usuario que está realizando el trámite.
	 * @return Variable de tipo <code>boolean</code> que determina si se enviaron los documentos al OWCC o no.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public boolean documentosEnviadosOWCC(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar si se enviaron documentos al OWCC por id solicitud
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean documentosEnviadosOWCCBySolicitud(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de enviar al aprobador de rechazo recurso.
	 *
	 * @param aaa_parametros Objeto que contiene la información del proceso a terminar.
	 * @param ab_etapa460 Variable tipo boolean que indica si proviene de la etapa 460.
	 * @param al_idMotivo Variable String que contiene el id motivo a terminar.
	 * @param as_userId Variable String que contiene el usuario que realiza la operación
	 * @param as_localIp Variable String que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Variable String que contiene la ip remota desde donde se realiza la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAlAprobadorRechazoRecurso(
	    TagPlantillaResolucion aaa_parametros, boolean ab_etapa460, long al_idMotivo, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de aprobar la prorroga de documentación.
	 *
	 * @param ath_param Objeto que contiene la información del proceso.
	 * @param as_userId Variable String que contiene el usuario que realiza la operación
	 * @param as_localIp Variable String que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Variable String que contiene la ip remota desde donde se realiza la operación
	 * @return boolean que válida si el proceso terminó exitosamente.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean enviarAprobarProrrogaDocumentacion(
	    TurnoHistoria ath_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de enviar al responsable de actuaciones administrativas.
	 *
	 * @param aaa_parametros Objeto que contiene la información del proceso a terminar.
	 * @param al_idMotivo Variable String que contiene el id motivo a terminar.
	 * @param as_userId Variable String que contiene el usuario que realiza la operación
	 * @param as_localIp Variable String que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Variable String que contiene la ip remota desde donde se realiza la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarResponsableActuacionesAdmin(
	    TagPlantillaResolucion aaa_parametros, long al_idMotivo, String as_userId, String as_localIp, String as_remoteIp
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
	public TagPlantillaResolucion generarDocumentosActuacionesAdmin(
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
	 * @param ab_651 Variable de tipo <code>Boolean </code> que contiene la decision de 651 seleccionada
	 * @return Variable de tipo byte <code>Array</code> que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion generarDocumentosActuacionesAdmin(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp,
	    boolean ab_651
	)
	    throws B2BException;

	/**
	 * Método encargado de generar documentos de actuaciones administrativas.
	 *
	 * @param aaa_parametros Objeto que contiene la información para generar los pdf.
	 * @param as_userId Variable de tipo <code>String</code> que contiene el id del usuario que está realizando el trámite.
	 * @param as_localIp Variable de tipo <code>String</code> que contiene la ip local del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo <code>String</code> que contiene la ip remota del usuario que está realizando el trámite.
	 * @return Variable de tipo byte <code>Array</code> que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion generarDocumentosRechazaRecurso(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de generar documentos de actuaciones traslados.
	 *
	 * @param aaa_parametros Objeto que contiene la información para generar los pdf.
	 * @param al_motivo Variable de tipo <code>long</code> que contiene la decision seleccionada.
	 * @param as_userId Variable de tipo <code>String</code> que contiene el id del usuario que está realizando el trámite.
	 * @param as_localIp Variable de tipo <code>String</code> que contiene la ip desde donde se está realizando el trámite.
	 * @param as_remoteIp Variable de tipo <code>String</code> que contiene la ip del usuario que está realizando el trámite.
	 * @return Variable de tipo byte <code>Array</code> que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion generarDocumentosTraslados(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Válida que el tramite haga parte de una prorroga de documentación.
	 *
	 * @param as_idTurnoHistoria Variable que contiene el id del turno historia en trámite.
	 * @return boolean que válida que el tramite haga parte de una prorroga de documentación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean prorrogaDocumentacion(String as_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 *
	 * @param aaa_parametros Argumento de tipo <code>ActuacionesAdministrativas</code> que contiene los argumentos necesarios para realizar la búsqueda.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los datos encotrados para actuaciones administrativas.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion validarCondicionesDecision(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de enviar al responsable de actuaciones administrativas.
	 *
	 * @param aaa_parametros Objeto que contiene la información para enviar al responsable de actuaciones administrativas.
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
	 * Visualizar cambios resolucion.
	 *
	 * @param aaa_parametros de aaa parametros
	 * @param al_motivo de al motivo
	 * @param ab_etapa651 correspondiente al valor de ab etapa 651
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tag plantilla resolucion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, boolean ab_etapa651, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de enviar al responsable de rechaza recursos.
	 *
	 * @param aaa_parametros Objeto que contiene la información para enviar al responsable de actuaciones administrativas.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_localIp Variable de tipo String que contiene la ip local del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip remota del usuario que está realizando el trámite.
	 * @return el valor de actuaciones administrativas
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion visualizarCambiosResolucionRechazaRecurso(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
