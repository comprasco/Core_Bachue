package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoSalidaRegistrarCambioQuintoOrden;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades MutacionesQuintoOrdenRemote.
 *
 * @author  Carlos Calderon
 * Fecha de Creacion: 04/03/2020
 */
@Remote
public interface MutacionesQuintoOrdenRemote
{
	
	/**
	 * Registrar cambio quinto orden.
	 *
	 * @param atercqo_entrada de TipoEntradaRegistrarCambioQuintoOrden
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de tipo salida registrar cambio quinto orden
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaRegistrarCambioQuintoOrden registraCambioQuintoOrden(
	    TipoEntradaRegistrarCambioQuintoOrden atercqo_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
