package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoEntradaRegistrarIdentificadoresCatastrales;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoSalidaRegistrarIdentificadoresCatastrales;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedades;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoSalidaregistrarCambioSalvedades;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades SalvedadesRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 04/03/2020
 */
@Remote
public interface SalvedadesRemote
{
	/**
	 * Registra cambio salvedades.
	 *
	 * @param atercs_entrada de TipoEntradaregistrarCambioSalvedades
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salidaregistrar cambio salvedades
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaregistrarCambioSalvedades registraCambioSalvedades(
	    TipoEntradaregistrarCambioSalvedades atercs_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Registra identificadores catastrales.
	 *
	 * @param ateric_entrada de TipoEntradaRegistrarIdentificadoresCatastrales
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida registrar identificadores catastrales
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaRegistrarIdentificadoresCatastrales registraIdentificadoresCatastrales(
	    TipoEntradaRegistrarIdentificadoresCatastrales ateric_entrada, String as_userId, String as_localIp,
	    String                                         as_remoteIp
	)
	    throws B2BException;
}
