package com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroMayorValor;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades LiquidacionRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface LiquidacionRemote
{
	/**
	 * Actualiza condiciones.
	 *
	 * @param ac_liquidacion de Collection<Liquidacion>
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemote Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizaCondiciones(Collection<Liquidacion> ac_liquidacion, String as_userId, String as_ipRemote)
	    throws B2BException;

	/**
	 * Metodo encargado de consultar todos los registros de LIQUIDACION_ITEM para un ID_LIQUIDACION específico.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param ab_tipoRecibo de tipo recibo
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza que realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza que realiza la consulta.
	 * @return Colección de Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Liquidacion> buscarDetalleLiquidacion(
	    Liquidacion al_liquidacion, boolean ab_tipoRecibo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo encargado de consultar todos los registros de LIQUIDACION_ITEM para un ID_LIQUIDACION específico.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza que realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza que realiza la consulta.
	 * @return Colección de Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Liquidacion> buscarLiquidacionItem(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo encargado de consultar la imagen de un documento salida.
	 *
	 * @param ads_parametros Argumento de tipo <code>DocumentosSalida</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto de tipo <code>Imagenes</code> que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Imagenes consultarImagenDocumento(
	    DocumentosSalida ads_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de consultar los valores de registro mayor valor para un id turno.
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id_turno a consultar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RegistroMayorValor> consultarRegistroMayorValor(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Detalle liquidacion.
	 *
	 * @param al_liquidacion de al liquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Liquidacion> detalleLiquidacion(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo encargado de eliminar un acto de mayor valor por id acto.
	 *
	 * @param as_idActo Argumento de tipo <code>String</code> que contiene los criterios necesarios para realizar la eliminación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza que realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza que realiza la consulta.
	 * @throws B2BException Se produce cuando se genera una excepción.
	 */
	public void eliminarActoMayorValor(String as_idActo, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find acto liquidacion item.
	 *
	 * @param al_liquidacion de Liquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Liquidacion> findActoLiquidacionItem(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find condiciones liquidacion.
	 *
	 * @param at_solicitud de Solicitud
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Liquidacion> findCondicionesLiquidacion(Solicitud at_solicitud)
	    throws B2BException;

	/**
	 * Generar PDF cobro mayor valor.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_idPersonaCorreoPantalla
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPDFCobroMayorValor(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar PDF nota informativa por pago en exceso.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPDFNotaInformativaPorPagoEnExceso(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Liquidar.
	 *
	 * @param al_liquidacion de Liquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Liquidacion liquidar(Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Proc liquidacion.
	 *
	 * @param al_liquidacion de Liquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Registro procLiquidacion(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Proc re liquidacion.
	 *
	 * @param al_liquidacion de Liquidacion
	 * @param al_data de Liquidacion data
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void procReLiquidacion(
	    Liquidacion al_liquidacion, Liquidacion al_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar el turno ingresado para correcciones mayor valor.
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id turno a validar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza que realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza que realiza la consulta.
	 * @return Colección de Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException
	 */
	public boolean validacionesTurnoRegistroCorrecciones(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
