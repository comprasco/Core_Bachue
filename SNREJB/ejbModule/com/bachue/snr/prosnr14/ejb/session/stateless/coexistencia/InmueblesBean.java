package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.InmueblesBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades InmueblesBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
@javax.ejb.Stateless(name = "inmuebles", mappedName = "inmueblesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class InmueblesBean implements InmueblesRemote
{
	/** Propiedad iib business. */
	private InmueblesBusiness iib_business;

	/**
	 * Retorna Objeto o variable de valor inmuebles business.
	 *
	 * @return el valor de inmuebles business
	 */
	public InmueblesBusiness getInmueblesBusiness()
	{
		if(iib_business == null)
			iib_business = new InmueblesBusiness();

		return iib_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaDatosInmueble consultarDatosInmueble(
	    TipoEntradaDatosInmueble atedi_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		TipoSalidaDatosInmueble ltsdi_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsdi_return     = getInmueblesBusiness().consultarDatosInmueble(atedi_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "InmueblesBean", "consultarDatosInmueble", as_userId, as_localIp, as_remoteIp, null);

		return ltsdi_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaDatosPropietario consultarPropietarios(
	    TipoEntradaDatosPropietario atedp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		TipoSalidaDatosPropietario ltsdp_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsdp_return     = getInmueblesBusiness().consultarPropietarios(atedp_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "InmueblesBean", "consultarPropietarios", as_userId, as_localIp, as_remoteIp, null);

		return ltsdp_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaDireccionesAnteriores consultarDireccionesAnteriores(
	    TipoEntradaDireccionesAnteriores ateda_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		TipoSalidaDireccionesAnteriores ltsda_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsda_return     = getInmueblesBusiness().consultarDireccionesAnteriores(ateda_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "InmueblesBean", "consultarDireccionesAnteriores", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsda_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultaMatriculas consultarMatriculas(
	    TipoEntradaConsultaMatriculas atecm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		TipoSalidaConsultaMatriculas ltscm_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltscm_return     = getInmueblesBusiness().consultarMatriculas(atecm_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "InmueblesBean", "consultarMatriculas", as_userId, as_localIp, as_remoteIp, null);

		return ltscm_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaDatosBasicosMatriculas consultarDatosBasicos(
	    TipoEntradaDatosBasicosMatriculas atedbm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		TipoSalidaDatosBasicosMatriculas ltsdbm_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsdbm_return     = getInmueblesBusiness().consultarDatosBasicos(atedbm_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "InmueblesBean", "consultarDatosBasicos", as_userId, as_localIp, as_remoteIp, null);

		return ltsdbm_return;
	}
}
