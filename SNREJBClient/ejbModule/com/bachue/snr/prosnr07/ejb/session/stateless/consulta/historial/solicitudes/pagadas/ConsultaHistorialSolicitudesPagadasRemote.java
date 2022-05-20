package com.bachue.snr.prosnr07.ejb.session.stateless.consulta.historial.solicitudes.pagadas;

import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoEntradaBuscarSolicitudes;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoSalidaBuscarSolicitudes;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoEntradaConsultarDetalleSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoSalidaConsultarDetalleSolicitud;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaHistorialSolicitudesPagadasRemote.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 07/10/2019
 */
@Remote
public interface ConsultaHistorialSolicitudesPagadasRemote
{
	
	/**
	 * Buscar solicitudes.
	 *
	 * @param atebs_param de TipoEntradaBuscarSolicitudes
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida buscar solicitudes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaBuscarSolicitudes buscarSolicitudes(
	    TipoEntradaBuscarSolicitudes atebs_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar detalle solicitud.
	 *
	 * @param atecds_entrada de TipoEntradaConsultarDetalleSolicitud
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar detalle solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarDetalleSolicitud consultarDetalleSolicitud(
	    TipoEntradaConsultarDetalleSolicitud atecds_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
