package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.cuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.cuentaCupos.ConsultaCuentaCupos;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaCuentaCuposRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 13/03/2020
 */
@Remote
public interface ConsultaCuentaCuposRemote
{
	
	/**
	 * busca los movimientos de una cuenta cupo en un lapso determinado.
	 *
	 * @param accc_datosConsulta Objeto contenedor de la información a utilizar como filtro en la busqueda
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Objeto contenedor de los resultados de la consulta
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public ConsultaCuentaCupos obtenerMovimientosCuentaCupo(
	    ConsultaCuentaCupos accc_datosConsulta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Busca los datos de la ultima recarga de una cuenta cupo.
	 *
	 * @param as_idCuentaCupo id de la cuenta cupo a consultar
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Objeto contenedor de los resultados de la consulta
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public ConsultaCuentaCupos obtenerDatosUltimaRecarga(
	    String as_idCuentaCupo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Genera un documento con los movimientos de una cuenta cupo.
	 *
	 * @param accc_datosConsulta Objeto contenedor de la información a utilizar como filtro en la busqueda
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Objeto contenedor del documento generado
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public byte[] generarDocumentoMovimientos(
	    ConsultaCuentaCupos accc_datosConsulta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
