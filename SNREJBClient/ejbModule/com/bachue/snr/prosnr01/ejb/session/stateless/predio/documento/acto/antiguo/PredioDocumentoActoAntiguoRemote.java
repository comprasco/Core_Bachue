package com.bachue.snr.prosnr01.ejb.session.stateless.predio.documento.acto.antiguo;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.antiguoSistema.CalificacionAntiguoSistema;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades PredioDocumentoActoAntiguoRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface PredioDocumentoActoAntiguoRemote
{
	
	/**
	 * Retorna el valor de observaciones calificacion.
	 *
	 * @param aoa_oa de id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de observaciones calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String getObservacionesCalificacion(String aoa_oa, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Datos etapa 101.
	 *
	 * @param aoa_oa de CalificacionAntiguoSistema
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de calificacion antiguo sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CalificacionAntiguoSistema datosEtapa101(
	    CalificacionAntiguoSistema aoa_oa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
