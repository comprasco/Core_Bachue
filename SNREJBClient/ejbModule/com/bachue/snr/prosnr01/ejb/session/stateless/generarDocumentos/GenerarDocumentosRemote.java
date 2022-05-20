package com.bachue.snr.prosnr01.ejb.session.stateless.generarDocumentos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades GenerarDocumentosRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 23/07/2019
 */
@Remote
public interface GenerarDocumentosRemote
{
	
	/**
	 * Generar documentos.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarDocumentos(String as_remoteIp)
	    throws B2BException;

	/**
	 * Generar documentos respuesta.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @param as_solicitud de Solicitud
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarDocumentosRespuesta(
	    TurnoHistoria ath_turnoHistoria, Solicitud as_solicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
	
	/**
	 * Enviar A entrega.
	 *
	 * @param ath_parametros de TurnoHistoria
	 * @param amt_motivoTramite de MotivoTramite
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAEntrega(
		    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_localIp,
		    String as_remoteIp
		)
		    throws B2BException;
}
