package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.MatriculasRRRBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades RRRMatriculasBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/03/2020
 */
@javax.ejb.Stateless(name = "RRRMatriculasOrden", mappedName = "RRRMatriculasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class RRRMatriculasBean implements RRRMatriculasRemote
{
	/** Propiedad imrrrb business. */
	private MatriculasRRRBusiness imrrrb_business;

	/**
	 * Retorna Objeto o variable de valor matricuas RRR bussines.
	 *
	 * @return el valor de matricuas RRR bussines
	 */
	public MatriculasRRRBusiness getMatricuasRRRBussines()
	{
		if(imrrrb_business == null)
			imrrrb_business = new MatriculasRRRBusiness();

		return imrrrb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarRRRMatriculas consultaRRRMatriculas(
	    TipoEntradaConsultarRRRMatriculas atecrrrm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		TipoSalidaConsultarRRRMatriculas ltscrrrm_return;

		lsw_watch           = Logger.getNewStopWatch();
		ltscrrrm_return     = getMatricuasRRRBussines().consultaRRRMatriculas(atecrrrm_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RRRMatriculasBean", "consultaRRRMatriculas", as_userId, as_localIp, as_remoteIp, null);

		return ltscrrrm_return;
	}
}
