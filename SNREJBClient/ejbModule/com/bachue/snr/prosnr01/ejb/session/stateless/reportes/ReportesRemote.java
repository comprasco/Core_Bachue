package com.bachue.snr.prosnr01.ejb.session.stateless.reportes;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ReportesRemote.
 *
 * @author  Juli?n David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ReportesRemote
{
	
	/**
	 * Retorna el valor de reportes nocturnos.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operaci?n.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void getEveningReport(String as_remoteIp)
	    throws B2BException;
}
