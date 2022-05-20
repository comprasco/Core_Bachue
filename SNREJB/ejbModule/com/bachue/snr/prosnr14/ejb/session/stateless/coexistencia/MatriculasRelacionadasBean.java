package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoEntradaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoSalidaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoEntradaDatosMatriculasMatriz;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoSalidaDatosMatriculasMatriz;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.MatriculasRelacionadasBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MatriculasRelacionadasBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/03/2020
 */
@javax.ejb.Stateless(name = "matriculasRelacionadas", mappedName = "matriculasRelacionadasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class MatriculasRelacionadasBean implements MatriculasRelacionadasRemote
{
	/** Propiedad imrb business. */
	private MatriculasRelacionadasBusiness imrb_business;

	/**
	 * Retorna Objeto o variable de valor matriculas relacionadas business.
	 *
	 * @return el valor de matriculas relacionadas business
	 */
	public MatriculasRelacionadasBusiness getMatriculasRelacionadasBusiness()
	{
		if(imrb_business == null)
			imrb_business = new MatriculasRelacionadasBusiness();

		return imrb_business;
	}

	/**
	 * Consultar matriculas matriz.
	 *
	 * @param atedmm_param de atedmm param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida datos matriculas matriz
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosMatriculasMatriz consultarMatriculasMatriz(
	    TipoEntradaDatosMatriculasMatriz atedmm_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		TipoSalidaDatosMatriculasMatriz ltsdmm_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsdmm_return     = getMatriculasRelacionadasBusiness()
				                    .consultarMatriculasMatriz(atedmm_param, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "MatriculasRelacionadasBean", "consultarMatriculasMatriz", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsdmm_return;
	}

	/**
	 * Consultar matriculas derivadas.
	 *
	 * @param atedmd_param de atedmm param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida datos matriculas matriz
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosMatriculasDerivadas consultarMatriculasDerivadas(
	    TipoEntradaDatosMatriculasDerivadas atedmd_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaDatosMatriculasDerivadas ltsdmd_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsdmd_return     = getMatriculasRelacionadasBusiness()
				                    .consultarMatriculasDerivadas(atedmd_param, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "MatriculasRelacionadasBean", "consultarMatriculasDerivadas", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsdmd_return;
	}
}
