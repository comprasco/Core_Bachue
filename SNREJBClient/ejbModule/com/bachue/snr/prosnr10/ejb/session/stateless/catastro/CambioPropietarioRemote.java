package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoEntradaConsultarCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoSalidaConsultarCambioPropietario;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades CambioPropietarioRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/04/2020
 */
@Remote
public interface CambioPropietarioRemote
{
	
	/**
	 * Consultar cambio propietario.
	 *
	 * @param ateccp_entrada de TipoEntradaConsultarCambioPropietario
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar cambio propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarCambioPropietario consultarCambioPropietario(
	    TipoEntradaConsultarCambioPropietario ateccp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
