package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoEntradaSalvedades;
import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalidaSalvedades;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades SalvedadesRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
@Remote
public interface SalvedadesRemote
{
	/**
	 * Consultar salvedades.
	 *
	 * @param ates_entrada de TipoEntradaSalvedades
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida salvedades
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaSalvedades consultarSalvedades(
	    TipoEntradaSalvedades ates_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
