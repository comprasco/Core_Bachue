package com.bachue.snr.prosnr01.ejb.session.stateless.segunda.instancia;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades SegundaInstanciaRemote.
 *
 * @author  Duvan Beltrán
 * Fecha de Creacion: 11/08/2020
 */
@Remote
public interface SegundaInstanciaRemote
{
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
	public TagPlantillaResolucion cargarDatosSegundaInstancia(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de consultar las personas vinculadas a una solicitud con proceso de segunda instancia.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id solicitud a consultar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación
	 * @return Retorna objeto de tipo <code>Registro</code> que contiene el listado de personas que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Registro buscarPersonasAsociadasSegundaInstancia(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de generar documentos de segunda instancia.
	 *
	 * @param aaa_parametros Objeto que contiene la información para generar los pdf.
	 * @param al_motivo Variable de tipo <code>long</code> que contiene la decision seleccionada.
	 * @param as_userId Variable de tipo <code>String</code> que contiene el id del usuario que está realizando el trámite.
	 * @param as_localIp Variable de tipo <code>String</code> que contiene la ip desde donde se está realizando el trámite.
	 * @param as_remoteIp Variable de tipo <code>String</code> que contiene la ip del usuario que está realizando el trámite.
	 * @return Variable de tipo byte <code>Array</code> que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public TagPlantillaResolucion generarDocumentosSegundaInstancia(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp, long al_motivo
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
	 * Método encargado de enviar al responsable de actuaciones administrativas.
	 *
	 * @param aaa_parametros Objeto que contiene la información del proceso a terminar.
	 * @param al_idMotivo Variable String que contiene el id motivo a terminar.
	 * @param as_userId Variable String que contiene el usuario que realiza la operación
	 * @param as_localIp Variable String que contiene la ip local desde donde se realiza la operación
	 * @param as_remoteIp Variable String que contiene la ip remota desde donde se realiza la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAprobador(
	    TagPlantillaResolucion aaa_parametros, long al_idMotivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
