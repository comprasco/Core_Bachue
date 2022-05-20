package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.NuevasMatriculasBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades NuevasMatriculas.
 *
 * @author Carlos Calderon
 *
 */
@javax.ejb.Stateless(name = "NuevasMatriculas", mappedName = "nuevasMatriculasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class NuevasMatriculasBean implements NuevasMatriculasRemote
{
	/** Propiedad inmb business. */
	private NuevasMatriculasBusiness inmb_business;

	/**
	 * Retorna Objeto o variable de valor nuevas matriculas business.
	 *
	 * @return el valor de nuevas matriculas business
	 */
	public NuevasMatriculasBusiness getNuevasMatriculasBusiness()
	{
		if(inmb_business == null)
			inmb_business = new NuevasMatriculasBusiness();

		return inmb_business;
	}

	/**
	 * Permite consultar los datos registrales asociados a la creación de nuevas matrículas por
	 * círculo registral en un determinado periodo de tiempo.
	 *
	 * @param atecnm_entrada de ateccnm entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar nuevas matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarNuevasMatriculas consultaNuevasMatriculas(
	    TipoEntradaConsultarNuevasMatriculas atecnm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		TipoSalidaConsultarNuevasMatriculas ltscnm_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltscnm_return     = getNuevasMatriculasBusiness()
				                    .consultaNuevasMatriculas(atecnm_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "NuevasMatriculasBean", "consultaNuevasMatriculas", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscnm_return;
	}
}
