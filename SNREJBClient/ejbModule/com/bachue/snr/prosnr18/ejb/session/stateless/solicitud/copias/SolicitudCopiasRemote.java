package com.bachue.snr.prosnr18.ejb.session.stateless.solicitud.copias;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoEntradaConsultarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitud;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades SolicitudCopiasRemote.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 04/04/2020
 */
@Remote
public interface SolicitudCopiasRemote
{
	
	/**
	 * Método que consulta la solicitud de copias.
	 *
	 * @param atecs_entrada Objeto que contiene los datos de entrada.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto que conntiene los datos de salida.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarSolicitud consultarSolicitud(
	    TipoEntradaConsultarSolicitud atecs_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método que crear la solicitud de copias.
	 *
	 * @param ateis_teis Objeto que contiene los datos de entrada.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto que conntiene los datos de salida.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaIngresoSolicitud ingresoSolicitud(
	    TipoEntradaIngresoSolicitud ateis_teis, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
