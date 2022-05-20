package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoEntradaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoEntradaConsultaSegregacionSinCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaConsultaSegregacionSinCambioPropietario;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades CambioLinderosPredioRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/03/2020
 */
@Remote
public interface CambioLinderosPredioRemote
{
	/**
	 * Consulta segregacion con cambio propietario.
	 *
	 * @param atecsccp_entrada de TipoEntradaConsultaSegregacionConCambioPropietario
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consulta segregacion con cambio propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultaSegregacionConCambioPropietario consultaSegregacionConCambioPropietario(
	    TipoEntradaConsultaSegregacionConCambioPropietario atecsccp_entrada, String as_userId, String as_localIp,
	    String                                             as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta segregacion sin cambio propietario.
	 *
	 * @param atecsscp_entrada de TipoEntradaConsultaSegregacionSinCambioPropietario
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consulta segregacion sin cambio propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultaSegregacionSinCambioPropietario consultaSegregacionSinCambioPropietario(
	    TipoEntradaConsultaSegregacionSinCambioPropietario atecsscp_entrada, String as_userId, String as_localIp,
	    String                                             as_remoteIp
	)
	    throws B2BException;
}
