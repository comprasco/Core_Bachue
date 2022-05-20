package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaEstadoPredioRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ConsultaEstadoPredioRemote
{
	
	/**
	 * Buscar estado.
	 *
	 * @param asm_parametros de SolicitudMatricula
	 * @param as_usuario de as usuario
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException de b 2 B exception
	 */
	public byte[] findEstado(
	    SolicitudMatricula asm_parametros, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
