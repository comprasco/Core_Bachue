package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculas;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades NuevasMatriculasRemote.
 *
 * @author  Carlos Calderon
 * Fecha de Creacion: 04/03/2020
 */
@Remote
public interface NuevasMatriculasRemote
{
	
	/**
	 * Consultar nuevas matriculas.
	 *
	 * @param atecnm_entrada de atecnm entrada
	 * @param userId de user id
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar nuevas matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarNuevasMatriculas consultaNuevasMatriculas(
	    TipoEntradaConsultarNuevasMatriculas atecnm_entrada, String userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
