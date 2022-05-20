package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoEntradaRegistrarIdentificadoresCatastrales;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoSalidaRegistrarIdentificadoresCatastrales;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedades;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoSalidaregistrarCambioSalvedades;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.SalvedadesBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades Salvedades.
 *
 * @author Carlos Calderon
 *
 */
@javax.ejb.Stateless(name = "salvedades", mappedName = "salvedadesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class SalvedadesBean implements SalvedadesRemote
{
	/** Propiedad isb business. */
	private SalvedadesBusiness isb_business;

	/**
	 * Gets de salvedades business.
	 *
	 * @return de salvedades business
	 */
	public SalvedadesBusiness getSalvedadesBusiness()
	{
		if(isb_business == null)
			isb_business = new SalvedadesBusiness();

		return isb_business;
	}

	/**
	 * Registrar identificadores catastrales.
	 *
	 * @param ateric_param the ateric param
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the tipo salida registrar identificadores catastrales
	 * @throws B2BException the b 2 B exception
	 */
	public TipoSalidaRegistrarIdentificadoresCatastrales registraIdentificadoresCatastrales(
	    TipoEntradaRegistrarIdentificadoresCatastrales ateric_param, String as_userId, String as_localIp,
	    String                                         as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                     lsw_watch;
		TipoSalidaRegistrarIdentificadoresCatastrales ltsric_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsric_return     = getSalvedadesBusiness()
				                    .registraIdentificadoresCatastrales(ateric_param, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "SalvedadesBean", "registraIdentificadoresCatastrales", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsric_return;
	}

	/**
	 * Registra cambio salvedades.
	 *
	 * @param atercs_entrada de atercs entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salidaregistrar cambio salvedades
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaregistrarCambioSalvedades registraCambioSalvedades(
	    TipoEntradaregistrarCambioSalvedades atercs_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		TipoSalidaregistrarCambioSalvedades ltsrcs_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsrcs_return     = getSalvedadesBusiness().registraCambioSalvedades(atercs_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "SalvedadesBean", "registraCambioSalvedades", as_userId, as_localIp, as_remoteIp, null);

		return ltsrcs_return;
	}
}
