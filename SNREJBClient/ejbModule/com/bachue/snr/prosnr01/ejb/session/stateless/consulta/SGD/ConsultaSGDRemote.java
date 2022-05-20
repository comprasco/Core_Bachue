package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.SGD;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaSGDRemote.
 *
 * @author  Jorge Patiño
 * Fecha de Creacion: 16/09/2019
 */
@Remote
public interface ConsultaSGDRemote
{
	
	/**
	 * Consulta por matricula Y circulo.
	 *
	 * @param acp_consultaPredio de acp consulta predio
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Trazabilidad> consultaPorMatriculaYCirculo(
	    ConsultaPredio acp_consultaPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta SGD.
	 *
	 * @param ado_parametro de DocumentoOWCC
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentoOWCC> consultaSGD(
	    DocumentoOWCC ado_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar data pais.
	 *
	 * @param aoo_oficina de OficinaOrigen
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de documento OWCC
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentoOWCC generarDataPais(
	    OficinaOrigen aoo_oficina, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de generar una <code>Collection</code> cargada con la
	 * respuesta del servicio de búsqueda el OWCC.
	 *
	 * @param ado_parametro <code>DocumentoOWCC</code> que contiene los parametros que serán enviados al servicio.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return <code>Collection</code> cargada con la respuesta del servicio de búsqueda el OWCC
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentoOWCC> generarDocumento(
	    DocumentoOWCC ado_parametro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
