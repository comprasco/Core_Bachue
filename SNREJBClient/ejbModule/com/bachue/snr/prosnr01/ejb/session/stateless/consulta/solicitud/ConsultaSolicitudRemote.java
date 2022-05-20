package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.solicitud;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.consulta.solicitud.Predio;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Segregacion;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Solicitud;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Tramite;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaSolicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaSolicitudRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ConsultaSolicitudRemote
{
	
	/**
	 * Buscar por id solicitud.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException;

	/**
	 * Buscar datos del tramite por id solicitud.
	 *
	 * @param as_parametros de id solicitud
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Tramite> findDatosDelTramiteByidSolicitud(String as_parametros)
	    throws B2BException;

	/**
	 * Buscar documento por id solicitud.
	 *
	 * @param as_parametros de id solicitud
	 * @return el valor de documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Documento findDocumentoByIdSolicitud(String as_parametros)
	    throws B2BException;

	/**
	 * Buscar interesados.
	 *
	 * @param as_parametros de parametros
	 * @param ab_b de boolean
	 * @return el valor de solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Solicitud findInteresados(String as_parametros, boolean ab_b)
	    throws B2BException;

	/**
	 * Buscar matricula por solicitud.
	 *
	 * @param as_parametros de parametros
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Predio> findMatriculaBySolicitud(String as_parametros)
	    throws B2BException;

	/**
	 * Buscar personas por id solicitud.
	 *
	 * @param as_parametros de parametros
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PersonaSolicitud> findPersonasByIdSolicitud(String as_parametros)
	    throws B2BException;

	/**
	 * Buscar segregacion.
	 *
	 * @param as_parametros de parametros
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	Collection<Segregacion> findSegregacion(String as_parametros)
	    throws B2BException;
}
