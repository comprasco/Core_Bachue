package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoEntradaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoSalidaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoEntradaDatosMatriculasMatriz;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoSalidaDatosMatriculasMatriz;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades MatriculasRelacionadasRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
@Remote
public interface MatriculasRelacionadasRemote
{
	/**
	 * Consultar matriculas matriz.
	 *
	 * @param atedmm_param de TipoEntradaDatosMatriculasMatriz
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida datos matriculas matriz
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosMatriculasMatriz consultarMatriculasMatriz(
	    TipoEntradaDatosMatriculasMatriz atedmm_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar matriculas derivadas.
	 *
	 * @param atedmd_param de TipoEntradaDatosMatriculasDerivadas
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida datos matriculas derivadas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosMatriculasDerivadas consultarMatriculasDerivadas(
	    TipoEntradaDatosMatriculasDerivadas atedmd_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
