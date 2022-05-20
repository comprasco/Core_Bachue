package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import com.b2bsg.common.exception.B2BException;


/**
 * Interface que contiene todos las propiedades ReferenceRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 31/03/2020
 */
@javax.ejb.Remote
public interface ReferenceRemote extends com.bachue.snr.ejb.session.stateless.reference.SchedulerRemote
{
	/**
	 * Buscar constantes por llave.
	 *
	 * @param as_parametros de llave
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public com.bachue.snr.prosnr01.model.sdb.pgn.Constantes findConstantesById(String as_parametros)
	    throws B2BException;

	/**
	 * Obtener la propiedad caracter de una constante
	 * @param as_idConstante Codigo de la constante a buscar
	 * @return Propiedad caracter de la constante identificada con as_idConstante
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException;

	/**
	 * Obtener todos los ID_CONSTANTE que coincidan con un patrón y su el valor de la columna CARACTER concida sea
	 * igual a un valor
	 *
	 * @param as_IdLike Patrón sobre el cual se realizará búsqueda tipo like en en campo ID_CONSTANTE
	 * @param as_caracter Valor de coincidencia del campo CARACTER
	 * @return Listado de ID_CONSTANTE
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public java.util.Collection<String> obtenerIdConstanesPorCaracterIdLikeCaracter(
	    String as_IdLike, String as_caracter
	)
	    throws B2BException;

	/**
	 * Actualizar caracter.
	 *
	 * @param as_id de llave
	 * @param as_caracter de caracter
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateCaracter(String as_id, String as_caracter, String as_userId)
	    throws B2BException;
}
