package com.bachue.snr.prosnr01.ejb.session.stateless.cuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.cuentaCupos.AprobacionCuentaCupos;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;

import java.math.BigDecimal;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades CuentaCuposRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 09/03/2020
 */
@Remote
public interface CuentaCuposRemote
{
	/**
	 * Aprobar solicitud cuenta cupo.
	 *
	 * @param aacc_datosCuenta Objeto contenedor de los datos del trámite
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public void aprobarSolicitudCuentaCupo(
	    AprobacionCuentaCupos aacc_datosCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar por id cuenta cupo.
	 *
	 * @param as_idCuentaCupo de as id cuenta cupo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CuentaCupo buscarPorIdCuentaCupo(
	    String as_idCuentaCupo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Desencola los pagos de productos generados por medio de una cuenta cupo.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void desencolarPago(String as_remoteIp)
	    throws B2BException;

	/**
	 * Generar constancia decision aprobacion.
	 *
	 * @param as_idSolicitud Variable de tipo <code>String</code> que contiene id solicitud
	 * @param as_idCuentaCupo Variable de tipo <code>String</code> que contiene id cuenta cupo
	 * @param as_observaciones Variable de tipo <code>String</code> que contiene observaciones
	 * @param ab_aprobar Variable de tipo <code>boolean</code> que contiene aprobar
	 * @param lbd_valorMinimo Variable de tipo <code>BigDecimal</code> correspondiente al valor de valor minimo cuenta cupo
	 * @param lbd_valorMaximo Variable de tipo <code>BigDecimal</code> correspondiente al valor de valor maximo cuenta cupo
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarConstanciaDecisionAprobacion(
	    String as_idSolicitud, String as_idCuentaCupo, String as_observaciones, boolean ab_aprobar,
	    BigDecimal lbd_valorMinimo, BigDecimal lbd_valorMaximo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener bandeja detalle.
	 *
	 * @param atc_parametros Objeto contenedor de los parametros a utilizar en la busqueda
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public Collection<TramiteCantidad> obtenerBandejaDetalle(
	    TramiteCantidad atc_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener bandeja entrada.
	 *
	 * @param atc_parametros Objeto contenedor de los parametros a utilizar en la busqueda
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public Collection<TramiteCantidad> obtenerBandejaEntrada(
	    TramiteCantidad atc_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener datos turno.
	 *
	 * @param as_idTurno id del turno a utilizar como filtro en la busqueda
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Objeto contenedor de los resultados de la consulta
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public AprobacionCuentaCupos obtenerDatosTurno(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Inactivar cuenta cupo.
	 *
	 * @param aacc_datosCuenta de aacc datos cuenta
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void inactivarCuentaCupo(
	    AprobacionCuentaCupos aacc_datosCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
