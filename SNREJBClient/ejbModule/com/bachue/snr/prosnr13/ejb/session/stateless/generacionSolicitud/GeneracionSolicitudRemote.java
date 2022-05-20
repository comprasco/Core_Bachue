package com.bachue.snr.prosnr13.ejb.session.stateless.generacionSolicitud;

import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoEntradaConsultarEstadoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSalidaConsultarEstadoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoEntradaGenerarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSalidaGenerarSolicitud;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades GeneracionSolicitudRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 04/03/2020
 */
@Remote
public interface GeneracionSolicitudRemote
{
	
	/**
	 * genera una solicitud de una cuenta cupo.
	 *
	 * @param ateiu_request de ateiu request
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaGenerarSolicitud generarSolicitud(
	    TipoEntradaGenerarSolicitud ateiu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * genera una solicitud de una cuenta cupo.
	 *
	 * @param ateces_request de ateces request
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarEstadoSolicitud consultarEstadoSolicitud(
	    TipoEntradaConsultarEstadoSolicitud ateces_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
