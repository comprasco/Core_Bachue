package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.documento.publico;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import javax.ejb.Remote;

/**
 * Interface que contiene todos las propiedades ConsultaDocumentoPublicoRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 02/07/2019
 */
@Remote
public interface ConsultaDocumentoPublicoRemote
{
	
	/**
	 * Buscar por id solicitud.
	 *
	 * @param acdd_datosDocumento de ConsultaDatosDocumento
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Documento findByIdSolicitud(
	    ConsultaDatosDocumento acdd_datosDocumento, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
