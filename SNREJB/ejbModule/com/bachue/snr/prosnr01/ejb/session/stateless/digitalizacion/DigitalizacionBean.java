package com.bachue.snr.prosnr01.ejb.session.stateless.digitalizacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.digitalizacion.DigitalizacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades DigitalizacionBean.
 *
 * @author Carlos Calderon
 */
@javax.ejb.Stateless(name = "Digitalizacion", mappedName = "digitalizacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class DigitalizacionBean implements DigitalizacionRemote
{
	/** Propiedad idb business. */
	private DigitalizacionBusiness idb_business;

	/**
	 * Metodo encargado de actualizar el estado de digitalización.
	 * @param ath_parametros Argumento de tipo <code>TurnoHistoria</code> que contiene
	 * los criterios necesarios para realizar la actualización.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene
	 * el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene
	 * la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene
	 * la ip remota desde donde se realiza la operación.
	 * @throws B2BException
	 */
	public void actualizarEstadoDigitalizacion(
	    TurnoHistoria ath_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().actualizarEstadoDigitalizacion(ath_parametros, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "DigitalizacionBean", "actualizarEstadoDigitalizacion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Método encargado de consultar los turnos para un nir y estado actividad de etapas menores a las ingresadas.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene el nir para realizar la consulta.
	 * @param as_userid Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la consulta.
	 * @return Collection<Turno> Colección de datos de tipo turnos con todos los registros encontrados.
	 * @throws B2BException
	 */
	public Collection<Turno> consultarTurnosNirEtapaEstado(
	    Turno at_parametros, String as_userid, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Turno> lct_turnos;

		lsw_watch      = Logger.getNewStopWatch();
		lct_turnos     = getBusiness().consultarTurnosNirEtapaEstado(at_parametros);

		Logger.log(
		    lsw_watch, "DigitalizacionBean", "consultarTurnosNirEtapaEstado", as_userid, as_localIp, as_remoteIp, null
		);

		return lct_turnos;
	}

	/** {@inheritdoc} */
	public TramiteCantidad findTurnos(Turno at_t, String as_userid, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		TramiteCantidad la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusiness().findTurnos(at_t);

		Logger.log(lsw_watch, "DigitalizacionBean", "findTurnos", as_userid, as_localIp, as_remoteIp, null);

		return la_datos;
	}

	/** {@inheritdoc} */
	public String construirUrlCapture(
	    String as_nir, String as_idTurno, String as_userid, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusiness().construirUrlCapture(as_nir, as_idTurno);

		Logger.log(lsw_watch, "DigitalizacionBean", "construirUrlCapture", as_userid, as_localIp, as_remoteIp, null);

		return la_datos;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private DigitalizacionBusiness getBusiness()
	{
		if(idb_business == null)
			idb_business = new DigitalizacionBusiness();

		return idb_business;
	}
}
