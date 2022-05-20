package com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.business.liquidacion.LiquidacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion.LiquidacionRemote;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroMayorValor;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Class que contiene todos las propiedades LiquidacionBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Liquidacion", mappedName = "liquidacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class LiquidacionBean implements LiquidacionRemote
{
	/** Propiedad ilb business. */
	private LiquidacionBusiness ilb_business;

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	public LiquidacionBusiness getBusiness()
	{
		if(ilb_business == null)
			ilb_business = new LiquidacionBusiness();

		return ilb_business;
	}

	/** {@inheritdoc} */
	@Override
	public void actualizaCondiciones(Collection<Liquidacion> ac_liquidacion, String as_userId, String as_ipRemote)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		if(CollectionUtils.isValidCollection(ac_liquidacion))
			getBusiness().actualizaCondiciones(ac_liquidacion, as_userId, as_ipRemote);

		Logger.log(lsw_watch, "LiquidacionBean", "actualizaCondiciones", null, null, null, null);
	}

	/**
	 * Metodo encargado de consultar todos los registros de LIQUIDACION_ITEM para un ID_LIQUIDACION específico.
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza que realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza que realiza la consulta.
	 * @return Colección de Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException
	 */
	@Override
	public Collection<Liquidacion> buscarDetalleLiquidacion(
	    Liquidacion al_liquidacion, boolean ab_tipoRecibo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_liquidacion;
		StopWatch               lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lcl_liquidacion = getBusiness().buscarDetalleLiquidacion(al_liquidacion, ab_tipoRecibo);

		Logger.log(
		    lsw_watch, "LiquidacionBean", "buscarDetalleLiquidacion", as_userId, as_localIp, as_remoteIp,
		    lcl_liquidacion
		);

		return lcl_liquidacion;
	}

	/**
	 * Metodo encargado de consultar todos los registros de LIQUIDACION_ITEM para un ID_LIQUIDACION específico.
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza que realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza que realiza la consulta.
	 * @return Colección de Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException
	 */
	@Override
	public Collection<Liquidacion> buscarLiquidacionItem(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_liquidacion;
		StopWatch               lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lcl_liquidacion = getBusiness().buscarLiquidacionItem(al_liquidacion);

		Logger.log(
		    lsw_watch, "LiquidacionBean", "buscarLiquidacionItem", as_userId, as_localIp, as_remoteIp, lcl_liquidacion
		);

		return lcl_liquidacion;
	}

	/**
	 * Metodo encargado de consultar la imagen de un documento salida.
	 *
	 * @param ads_parametros Argumento de tipo <code>DocumentosSalida</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Objeto de tipo <code>Imagenes</code> que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	@Override
	public Imagenes consultarImagenDocumento(
	    DocumentosSalida ads_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Imagenes  li_imagenes;
		StopWatch lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		li_imagenes = getBusiness().consultarImagenDocumento(ads_parametros);

		Logger.log(lsw_watch, "LiquidacionBean", "consultarImagenDocumento", as_userId, as_localIp, as_remoteIp, null);

		return li_imagenes;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<RegistroMayorValor> consultarRegistroMayorValor(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<RegistroMayorValor> lcrmv_registroMayorValor;
		StopWatch                      lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcrmv_registroMayorValor = getBusiness().consultarRegistroMayorValor(as_idTurno);

		Logger.log(
		    lsw_watch, "LiquidacionBean", "consultarRegistroMayorValor", as_userId, as_localIp, as_remoteIp, null
		);

		return lcrmv_registroMayorValor;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Liquidacion> detalleLiquidacion(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<Liquidacion> lc_liquidacion;
		StopWatch               lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lc_liquidacion = getBusiness().detalleLiquidacion(al_liquidacion);

		Logger.log(
		    lsw_watch, "LiquidacionBean", "detalleLiquidacion", as_userId, as_localIp, as_remoteIp, lc_liquidacion
		);

		return lc_liquidacion;
	}

	/**
	 * Metodo encargado de eliminar un acto de mayor valor por id acto.
	 *
	 * @param as_idActo Argumento de tipo <code>String</code> que contiene los criterios necesarios para realizar la eliminación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza que realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza que realiza la consulta.
	 * @throws B2BException Se produce cuando se genera una excepción.
	 */
	@Override
	public void eliminarActoMayorValor(String as_idActo, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().eliminarActoMayorValor(as_idActo);

		Logger.log(lsw_watch, "LiquidacionBean", "eliminarActoMayorValor", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Liquidacion> findActoLiquidacionItem(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<Liquidacion> lc_liquidacion;
		StopWatch               lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lc_liquidacion = getBusiness().findActoLiquidacionItem(al_liquidacion);

		Logger.log(
		    lsw_watch, "LiquidacionBean", "findActoLiquidacionItem", as_userId, as_localIp, as_remoteIp, lc_liquidacion
		);

		return lc_liquidacion;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Liquidacion> findCondicionesLiquidacion(Solicitud at_solicitud)
	    throws B2BException
	{
		Collection<Liquidacion> lc_liquidacion;
		StopWatch               lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lc_liquidacion = null;

		if(at_solicitud != null)
			lc_liquidacion = getBusiness().findCondicionesLiquidacion(at_solicitud);

		Logger.log(lsw_watch, "LiquidacionBean", "findCondicionesLiquidacion", null, null, null, null);

		return lc_liquidacion;
	}

	/** {@inheritdoc} */
	@Override
	public byte[] generarPDFCobroMayorValor(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		byte[]    lba_archivo;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lba_archivo = getBusiness()
				              .generarPDFCobroMayorValor(
				    ath_turnoHistoria, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "LiquidacionBean", "generarPDFCobroMayorValor", as_userId, as_localIp, as_remoteIp, null);

		return lba_archivo;
	}

	/** {@inheritdoc} */
	@Override
	public byte[] generarPDFNotaInformativaPorPagoEnExceso(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]    lba_archivo;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lba_archivo = getBusiness().generarPDFNotaInformativaPorPagoEnExceso(ath_turnoHistoria, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "LiquidacionBean", "generarPDFNotaInformativaPorPagoEnExceso", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lba_archivo;
	}

	/** {@inheritdoc} */
	@Override
	public Liquidacion liquidar(Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		Liquidacion ll_liquidacion;
		StopWatch   lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		ll_liquidacion = getBusiness().liquidar(al_liquidacion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "LiquidacionBean", "liquidacion", as_userId, as_localIp, as_remoteIp, null);

		return ll_liquidacion;
	}

	/** {@inheritdoc} */
	public Registro procLiquidacion(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Registro  lr_liquidacion;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lr_liquidacion = getBusiness().procLiquidacion(al_liquidacion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "LiquidacionBean", "procLiquidacion", as_userId, as_localIp, as_remoteIp, null);

		return lr_liquidacion;
	}

	/** {@inheritdoc} */
	public void procReLiquidacion(
	    Liquidacion al_liquidacion, Liquidacion al_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().procReLiquidacion(al_liquidacion, al_data, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "LiquidacionBean", "procReLiquidacion", as_userId, as_localIp, as_remoteIp, null);
	}

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
	@Override
	public boolean validacionesTurnoRegistroCorrecciones(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		boolean   lb_migrado;
		StopWatch lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lb_migrado = getBusiness().validacionesTurnoRegistroCorrecciones(as_idTurno);

		Logger.log(
		    lsw_watch, "LiquidacionBean", "validacionesTurnoRegistroCorrecciones", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lb_migrado;
	}
}
