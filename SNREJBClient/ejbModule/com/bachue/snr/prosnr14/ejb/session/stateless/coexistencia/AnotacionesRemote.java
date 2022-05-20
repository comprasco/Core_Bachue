package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacion;
import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacion;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades AnotacionesRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
@Remote
public interface AnotacionesRemote
{
	/**
	 * Consultar anotaciones.
	 *
	 * @param ateda_entrada de TipoEntradaDatosAnotacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo anotacion[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoAnotacion[] consultarAnotaciones(
	    TipoEntradaDatosAnotacion ateda_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
