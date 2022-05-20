package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.reparto.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.reparto.calificacion.ConsultaRepartoCalificacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.reparto.calificacion.ConsultaRepartoCalificacionRemote;

import com.bachue.snr.prosnr01.model.consulta.reparto.calificacion.ConsultaRepartoCalificacion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ConsultaRepartoCalificacionBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "consultaRepartoCalificacion", mappedName = "consultaRepartoCalificacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaRepartoCalificacionBean implements ConsultaRepartoCalificacionRemote
{
	/** Propiedad icrcr business. */
	private ConsultaRepartoCalificacionBusiness icrcr_business;

	/** {@inheritdoc} */
	public Collection<Usuario> findByORIP(String as_usuario)
	    throws B2BException
	{
		Collection<Usuario> lcu_return;

		StopWatch           lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lcu_return = getConsultaRepartoCalificacionBusiness().findByORIP(as_usuario);

		Logger.log(lsw_watch, "ConsultaRepartoCalificacionBean", "findByORIP", null, null, null, lcu_return);

		return lcu_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<ConsultaRepartoCalificacion> findByUser(Usuario au_usuario)
	    throws B2BException
	{
		Collection<ConsultaRepartoCalificacion> lccrc_return;

		StopWatch                               lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lccrc_return = getConsultaRepartoCalificacionBusiness().findByUser(au_usuario);

		Logger.log(lsw_watch, "ConsultaRepartoCalificacionBean", "findByUser", null, null, null, lccrc_return);

		return lccrc_return;
	}

	/**
	 * Retorna el valor de consulta reparto calificacion business.
	 *
	 * @return el valor de consulta reparto calificacion business
	 */
	private ConsultaRepartoCalificacionBusiness getConsultaRepartoCalificacionBusiness()
	{
		if(icrcr_business == null)
			icrcr_business = new ConsultaRepartoCalificacionBusiness();

		return icrcr_business;
	}
}
