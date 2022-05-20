package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoEntradaConsultaCambioTercerOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrden;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades MutacionesTercerOrdenRemote.
 *
 * @author  Carlos Calderon
 * Fecha de Creacion: 04/03/2020
 */
@Remote
public interface MutacionesTercerOrdenRemote
{
	
	/**
	 * Consulta un cambio de tercer orden para un predio.
	 *
	 * @param ateccto_entrada Objeto contenedor de la información para utilizar en la consulta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return TipoSalidaConsultaCambioTercerOrden con el resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultaCambioTercerOrden consultaCambioTercerOrden(
	    TipoEntradaConsultaCambioTercerOrden ateccto_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
