package com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.reference.ReferenceBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import com.bachue.snr.prosnr21.business.conciliaciones.ConciliacionesBusiness;
import com.bachue.snr.prosnr21.business.conciliaciones.ReportesConciliacionesBusiness;

import com.bachue.snr.prosnr21.model.pgn.ArchivoDRXC;
import com.bachue.snr.prosnr21.model.pgn.ConPartidaA;
import com.bachue.snr.prosnr21.model.pgn.DRXCTotalCTA;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.ExtractoBancoMensual;
import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;

import org.perf4j.StopWatch;

import java.io.IOException;

import java.text.ParseException;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;

import javax.mail.internet.AddressException;


/**
 * Clase que contiene todos las acciones Conciliaciones.
 *
 * @author Edgar Prieto
 */
@javax.ejb.Stateless(name = "Conciliaciones", mappedName = "conciliacionesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConciliacionesBean implements ConciliacionesRemote
{
	/**  Objeto de negocio de conciliaciones. */
	private ConciliacionesBusiness icb_business;

	/** Propiedad irb business. */
	private ReferenceBusiness irb_business;

	/**  Objeto de negocio de reportes conciliaciones. */
	private ReportesConciliacionesBusiness ircb_business;

	/** {@inheritDoc}
	 * @throws Exception */
	public void cargarArchivos(
	    String as_idProcesoConciliacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws Exception
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getConciliacionesBusiness().cargarArchivos(as_idProcesoConciliacion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ConciliacionesBean", "cargueArchivos", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritDoc}
	 * @throws MessagingException
	 * @throws AddressException */
	public int cargarArchivos(
	    Date ad_fechaConciliacion, String as_idCuenta, String adTipoArchivo, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, AddressException, MessagingException
	{
		int       li_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		li_return     = getConciliacionesBusiness()
				                .cargarArchivos(
				    ad_fechaConciliacion, as_idCuenta, adTipoArchivo, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "ConciliacionesBean", "cargarArchivos", as_userId, as_localIp, as_remoteIp, null);

		return li_return;
	}

	/** {@inheritDoc} */
	public void cargarCRPS(Date ad_fecha, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getConciliacionesBusiness().cargarCRPS(ad_fecha, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ConciliacionesBean", "cargarCRPS", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritDoc} */
	public Collection<Opcion> cargarOpcionesMenu(
	    String as_userId, String as_idComponente, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<Opcion> lco_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lco_datos     = getConciliacionesBusiness().cargarOpcionesMenu(as_userId, as_idComponente);

		Logger.log(
		    lsw_watch, "ConciliacionesBean", "cargarOpcionesMenu", as_userId, as_localIp, as_remoteIp, lco_datos
		);

		return lco_datos;
	}

	/** {@inheritDoc} */
	public void ejecutarProcedimientoReportes(
	    String as_fecha, String as_procedimiento, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getConciliacionesBusiness()
			    .ejecutarProcedimientoReportes(as_fecha, as_procedimiento, as_userId, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "ConciliacionesBean", "ejecutarProcedimientoReportes", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote#fillNumeroFechaSIIF(com.bachue.snr.prosnr21.model.pgn.DRXCTotalCTA, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void fillNumeroFechaSIIF(
	    DRXCTotalCTA adtc_objParametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getConciliacionesBusiness().fillNumeroFechaSIIF(adtc_objParametros, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ConciliacionesBean", "fillNumeroFechaSIIF", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Método para consultar un registro de Entidad Recaudadora Conciliacion basados en su usuario correspondiente.
	 *
	 * @param as_idUser de as id user
	 * @return Collection de EntidadRecaudadoraConciliacion con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadoraConciliacion> findEntidadRecaudadoraConciliacionByUser(String as_idUser)
	    throws B2BException
	{
		Collection<EntidadRecaudadoraConciliacion> lerc_return;
		StopWatch                                  lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lerc_return = getConciliacionesBusiness().findEntidadRecaudadoraConciliacionByUserId(as_idUser);

		Logger.log(
		    lsw_watch, "ConciliacionesBean", "findEntidadRecaudadoraConciliacionById", null, null, null, lerc_return
		);

		return lerc_return;
	}

	/**
	 * Retorna el valor del objeto de Collection de EntidadRecaudadoraCuenta asignados a un usuario y una entidad recaudadora.
	 *
	 * @param as_idEntidadRecaudadora correspondiente al valor del tipo de objeto String
	 * @param as_idUser de as id user
	 * @return devuelve el valor de Collection de EntidadRecaudadoraCuenta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraCuenta
	 */
	public Collection<EntidadRecaudadoraCuenta> findEntidadRecaudadoraCuentaByUser(
	    String as_idEntidadRecaudadora, String as_idUser
	)
	    throws B2BException
	{
		Collection<EntidadRecaudadoraCuenta> lerc_return;
		StopWatch                            lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lerc_return     = getConciliacionesBusiness()
				                  .findEntidadRecaudadoraCuentaByUser(as_idEntidadRecaudadora, as_idUser);

		Logger.log(
		    lsw_watch, "ConciliacionesBean", "findEntidadRecaudadoraConciliacionById", null, null, null, lerc_return
		);

		return lerc_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote#findEntidadRecaudadoraCuentaData(java.lang.String, java.lang.String)
	 */
	public Map<String, Object> findEntidadRecaudadoraCuentaData(String as_idEntidadRecaudadora, String as_userId)
	    throws B2BException
	{
		Map<String, Object> lmso_return;
		StopWatch           lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lmso_return     = getConciliacionesBusiness()
				                  .findEntidadRecaudadoraCuentaData(as_idEntidadRecaudadora, as_userId);

		Logger.log(lsw_watch, "ConciliacionesBean", "findEntidadRecaudadoraCuentaData", null, null, null, null);

		return lmso_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote#findMulticashDetalleByCuentaAndPeriodo(java.lang.String, java.lang.String)
	 */
	public Collection<ConPartidaA> findMulticashDetalleByCuentaAndFecha(
	    String as_idCuenta, Date ad_fecha, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<ConPartidaA> lccpa_return;
		StopWatch               lsw_watch;

		lsw_watch        = Logger.getNewStopWatch();
		lccpa_return     = getConciliacionesBusiness()
				                   .findMulticashDetalleByCuentaAndFecha(as_idCuenta, ad_fecha, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ConciliacionesBean", "findMulticashDetalleByCuentaAndFecha", as_userId, as_localIp, as_remoteIp,
		    lccpa_return
		);

		return lccpa_return;
	}

	/** {@inheritDoc} */
	public Usuario findUserById(Usuario ate_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Usuario   lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findUserById(ate_parametros);

		Logger.log(lsw_watch, "ConciliacionesBean", "findUserById", null, null, null, null);

		return lte_datos;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote#generarReportes(java.lang.String, java.lang.String, java.lang.String, java.util.Collection)
	 */
	public byte[] generarReportes(
	    String as_userId, String as_localIp, String as_remoteIp, Collection<ConPartidaA> accpa_consulta,
	    Date ad_fechaConciliacion, String as_idCuenta, String as_idBanco
	)
	    throws B2BException, IOException
	{
		byte[]    lba_return;
		StopWatch lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lba_return     = getConciliacionesBusiness()
				                 .generarReportes(
				    as_userId, as_remoteIp, accpa_consulta, ad_fechaConciliacion, as_idCuenta, as_idBanco
				);

		Logger.log(lsw_watch, "ConciliacionesBean", "generarReportes", as_userId, as_localIp, as_remoteIp, null);

		return lba_return;
	}

	/** {@inheritDoc} */
	public void generarReportesConciliaciones(
	    RPTPrograma arptp_reporte, Calendar ac_fecha, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException, ParseException, IOException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReportesConciliacionesBusiness().generarReportes(arptp_reporte, as_userId, as_remoteIpAddress, ac_fecha);

		Logger.log(
		    lsw_watch, "ConciliacionesBean", "generarReportesConciliaciones", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);
	}

	/** {@inheritDoc} */
	public void procesosConciliaciones(
	    String as_procesoConciliacion, Date ad_fechaDesde, Date ad_fechaHasta, String as_idCuenta, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getConciliacionesBusiness()
			    .procesosConciliaciones(
			    as_procesoConciliacion, ad_fechaDesde, ad_fechaHasta, as_idCuenta, as_userId, as_remoteIp
			);

		Logger.log(lsw_watch, "ConciliacionesBean", "procesosConciliaciones", as_userId, null, as_remoteIp, null);
	}

	/** {@inheritDoc} */
	public Map<String, Object> realizarBusquedaFechaConciliarDRXC(
	    EntidadRecaudadoraCuenta aerc_param, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		Map<String, Object> lmso_return;
		StopWatch           lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lmso_return     = getConciliacionesBusiness()
				                  .realizarBusquedaFechaConciliarDRXC(aerc_param, as_userId, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "ConciliacionesBean", "realizarBusquedaFechaConciliarDRXC", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);

		return lmso_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote#realizarNuevaSolicitud(java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void realizarNuevaSolicitud(
	    Date ad_fechaConciliacion, String as_idCuenta, String as_correoElectronico, String as_idTipoArchivo,
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, AddressException, MessagingException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getConciliacionesBusiness()
			    .realizarNuevaSolicitud(
			    as_userId, as_remoteIp, ad_fechaConciliacion, as_idCuenta, as_correoElectronico, as_idTipoArchivo
			);

		Logger.log(lsw_watch, "ConciliacionesBean", "realizarNuevaSolicitud", as_userId, as_localIp, as_remoteIp, null);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote#salvarPartidasA(java.lang.String, java.lang.String, java.lang.String, java.util.Collection, java.util.Collection)
	 */
	public void salvarPartidasA(
	    String as_userId, String as_localIp, String as_remoteIp, Collection<ConPartidaA> accpa_consulta,
	    Collection<ArchivoDRXC> acad_dataComprobante
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getConciliacionesBusiness().salvarPartidasA(as_userId, as_remoteIp, accpa_consulta, acad_dataComprobante);

		Logger.log(lsw_watch, "ConciliacionesBean", "salvarPartidasA", as_userId, as_localIp, as_remoteIp, null);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote#uploadFileExtractoBancoMensual(com.bachue.snr.prosnr21.model.pgn.ExtractoBancoMensual, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int uploadFileExtractoBancoMensual(
	    ExtractoBancoMensual aebm_extractoBancoMensual, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException, IOException, ParseException
	{
		int       li_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		li_return     = getConciliacionesBusiness()
				                .uploadFileExtractoBancoMensual(
				    aebm_extractoBancoMensual, as_userId, as_remoteIpAddress
				);

		Logger.log(
		    lsw_watch, "ConciliacionesBean", "uploadFileExtractoBancoMensual", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);

		return li_return;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_parametro de as parametro
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean verificarAutenticacionSegundoFactor(String as_parametro)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getReferenceBusiness().verificarAutenticacionSegundoFactor(as_parametro, true);

		Logger.log(lsw_watch, "ConciliacionesBean", "verificarAutenticacionSegundoFactor", null, null, null, null);

		return lc_datos;
	}

	/**
	 * Retorna el objeto de negocio de conciliaciones.
	 *
	 * @return objeto de negocio de conciliaciones
	 */
	private ConciliacionesBusiness getConciliacionesBusiness()
	{
		if(icb_business == null)
			icb_business = new ConciliacionesBusiness();

		return icb_business;
	}

	/**
	 * Retorna el valor de reference business.
	 *
	 * @return el valor de reference business
	 */
	private ReferenceBusiness getReferenceBusiness()
	{
		if(irb_business == null)
			irb_business = new ReferenceBusiness();

		return irb_business;
	}

	/**
	 * Gets the reportes conciliaciones business.
	 *
	 * @return the reportes conciliaciones business
	 */
	private ReportesConciliacionesBusiness getReportesConciliacionesBusiness()
	{
		if(ircb_business == null)
			ircb_business = new ReportesConciliacionesBusiness();

		return ircb_business;
	}
}
