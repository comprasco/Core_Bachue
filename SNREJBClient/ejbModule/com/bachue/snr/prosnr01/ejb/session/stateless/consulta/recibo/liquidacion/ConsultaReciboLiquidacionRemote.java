package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.recibo.liquidacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaReciboLiquidacionRemote.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 04/03/2020
 */
@Remote
public interface ConsultaReciboLiquidacionRemote
{
	
	/**
	 * M�todo encargado de eliminar la liquidaci�n.
	 *
	 * @param al_liquidacion Objeto que contiene la informaci�n de la liquidaci�n que se va a eliminar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	void confirmarEliminarLiquidacion(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar la informaci�n de la bandeja.
	 *
	 * @param as_numeroReferencia Variable que contiene el numero de referencia.
	 * @return Colecci�n que contiene la informaci�n consultada para la bandeja.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	Collection<Liquidacion> consultarBandeja(String as_numeroReferencia)
	    throws B2BException;

	/**
	 * Metodo encargado de terminar el proceso.
	 *
	 * @param al_liquidacion Objeto que contiene la informaci�n del proceso.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	void terminarProceso(Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * M�todo encargado de validar si es posible eliminar la liquidaci�n.
	 *
	 * @param al_liquidacion Objeto que contiene la informaci�n de la liquidaci�n que se va a eliminar.
	 * @return boolean que indica si es posible eliminar la liquidaci�n.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	boolean validarEliminarLiquidacion(Liquidacion al_liquidacion)
	    throws B2BException;
}
