package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadas;
import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadas;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades PartesInteresadasRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/03/2020
 */
@Remote
public interface PartesInteresadasRemote
{
	/**
	 * Consultar partes interesadas.
	 *
	 * @param atecpi_entrada de TipoEntradaConsultarPartesInteresadas
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar partes interesadas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarPartesInteresadas consultarPartesInteresadas(
	    TipoEntradaConsultarPartesInteresadas atecpi_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
