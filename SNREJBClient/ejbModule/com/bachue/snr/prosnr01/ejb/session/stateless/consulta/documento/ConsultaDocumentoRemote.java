package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.documento;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.consulta.documento.ConsultaDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaDocumentoRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ConsultaDocumentoRemote
{
	
	/**
	 * Consulta documento.
	 *
	 * @param acd_parametros de ConsultaDocumento
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConsultaDocumento> findConsultaDocumento(ConsultaDocumento acd_parametros)
	    throws B2BException;

	/**
	 * Buscar trazabilidad.
	 *
	 * @param at_parametros de Trazabilidad
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Trazabilidad> findTrazabilidad(Trazabilidad at_parametros)
	    throws B2BException;
}
