package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades RRRMatriculasRemote.
 *
 * @author  Carlos Calderon
 * Fecha de Creacion: 04/03/2020
 */
@Remote
public interface RRRMatriculasRemote
{
	/**
	 * Consultar RRR matriculas.
	 *
	 * @param atecrrrm_entrada de TipoEntradaConsultarRRRMatriculas
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar RRR matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarRRRMatriculas consultaRRRMatriculas(
	    TipoEntradaConsultarRRRMatriculas atecrrrm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
