package com.bachue.snr.prosnr17.ejb.session.stateless.solicitud.correccion;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoSalidaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoObtenerCausalesCorreccion;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccion;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades SolicitudCorreccionRemote.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 31/03/2020
 */
@Remote
public interface SolicitudCorreccionRemote
{
	
	/**
	 * Ingreso solicitud.
	 *
	 * @param ateis_teis de TipoEntradaIngresoSolicitud
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida ingreso solicitud
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public TipoSalidaIngresoSolicitud ingresoSolicitud(
	    TipoEntradaIngresoSolicitud ateis_teis, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws Exception;

	/**
	 * Obtener causales correccion.
	 *
	 * @param atocc_tocc de TipoObtenerCausalesCorreccion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida obtener causales correccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaObtenerCausalesCorreccion obtenerCausalesCorreccion(
	    TipoObtenerCausalesCorreccion atocc_tocc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
