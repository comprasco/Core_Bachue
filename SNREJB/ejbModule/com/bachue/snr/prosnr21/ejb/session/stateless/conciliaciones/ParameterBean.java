package com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr21.business.conciliaciones.ParameterBusiness;

import com.bachue.snr.prosnr21.model.pgn.AfectacionPrestacionServicio;
import com.bachue.snr.prosnr21.model.pgn.ConArchivo;
import com.bachue.snr.prosnr21.model.pgn.ConInconsistencia;
import com.bachue.snr.prosnr21.model.pgn.CuentaAnalista;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.Expediente;
import com.bachue.snr.prosnr21.model.pgn.ExtractoDiario;
import com.bachue.snr.prosnr21.model.pgn.ExtractoMensual;
import com.bachue.snr.prosnr21.model.pgn.HoraEjecucionProceso;
import com.bachue.snr.prosnr21.model.pgn.Periodicidad;
import com.bachue.snr.prosnr21.model.pgn.PeriodoCorte;
import com.bachue.snr.prosnr21.model.pgn.ProcesoConciliacion;
import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;
import com.bachue.snr.prosnr21.model.pgn.Rubro;
import com.bachue.snr.prosnr21.model.pgn.RubroHomologacion;
import com.bachue.snr.prosnr21.model.pgn.SIIFCatalogo;
import com.bachue.snr.prosnr21.model.pgn.TipoDocumental;

import org.perf4j.StopWatch;

import java.text.ParseException;

import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades ParameterBean.
 *
 * @author  Duvan Beltran
 * Fecha de Creacion: 19/06/2020
 */
@javax.ejb.Stateless(name = "Parameter", mappedName = "parameterMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ParameterBean implements ParameterRemote
{
	/** Propiedad ip business. */
	private ParameterBusiness ip_business;

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#buscarAlertasConfrontacionIngresos(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<ConInconsistencia> buscarAlertasConfrontacionIngresos(
	    String as_alerta, Date ad_fecha, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<ConInconsistencia> lcci_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcci_datos     = getParameterBusiness().findAlertasConfrontacionIngresos(as_alerta, ad_fecha);

		Logger.log(
		    lsw_watch, "ParameterBean", "buscarAlertasConfrontacionIngresos", as_userId, as_localIp, as_remoteIp,
		    lcci_datos
		);

		return lcci_datos;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#buscarAvisosConciliacionIngresos(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<ConInconsistencia> buscarAvisosConciliacionIngresos(
	    String as_idCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<ConInconsistencia> lcci_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcci_datos     = getParameterBusiness().findAvisosConciliacionIngresos(as_idCuenta);

		Logger.log(
		    lsw_watch, "ParameterBean", "buscarAvisosConciliacionIngresos", as_userId, as_localIp, as_remoteIp,
		    lcci_datos
		);

		return lcci_datos;
	}

	/** {@inheritDoc} */
	public Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacion(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                  lsw_watch;
		Collection<EntidadRecaudadoraConciliacion> laee_datos;

		lsw_watch      = Logger.getNewStopWatch();
		laee_datos     = getParameterBusiness().buscarEntidadRecaudadoraConciliacion();

		Logger.log(
		    lsw_watch, "ParameterBean", "buscarEntidadRecaudadoraConciliacion", as_idUsuario, as_localIp, as_remoteIp,
		    laee_datos
		);

		return laee_datos;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#buscarExtractoDiarioByCuentaBancoFecha(com.bachue.snr.prosnr21.model.pgn.ExtractoDiario)
	 */
	@Override
	public ExtractoDiario buscarExtractoDiarioByCuentaBancoFecha(
	    ExtractoDiario aed_parametros, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		ExtractoDiario led_return;
		StopWatch      lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		led_return     = getParameterBusiness()
				                 .buscarExtractoDiarioByCuentaBancoFecha(aed_parametros, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "buscarExtractoDiarioByCuentaBancoFecha", as_idUsuario, as_localIp, as_remoteIp,
		    null
		);

		return led_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#buscarExtractoMensual(com.bachue.snr.prosnr21.model.pgn.ExtractoMensual)
	 */
	@Override
	public ExtractoMensual buscarExtractoMensual(ExtractoMensual aem_objExtractoMensual)
	    throws B2BException
	{
		ExtractoMensual lem_return;
		StopWatch       lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lem_return     = getParameterBusiness().buscarExtractoMensual(aem_objExtractoMensual);

		Logger.log(lsw_watch, "ParameterBean", "buscarExtractoMensual", null, null, null, null);

		return lem_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#buscarPeriodicidad(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<Periodicidad> buscarPeriodicidad(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<Periodicidad> lcp_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcp_datos     = getParameterBusiness().buscarPeriodicidad();

		Logger.log(lsw_watch, "ParameterBean", "buscarPeriodicidad", as_userId, as_localIp, as_remoteIp, lcp_datos);

		return lcp_datos;
	}

	/** {@inheritDoc} */
	public Collection<ProcesoConciliacion> buscarProcesosConciliacion(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<ProcesoConciliacion> laee_datos;

		lsw_watch      = Logger.getNewStopWatch();
		laee_datos     = getParameterBusiness().buscarProcesosConciliacion();

		Logger.log(
		    lsw_watch, "ParameterBean", "buscarProcesosConciliacion", as_idUsuario, as_localIp, as_remoteIp, laee_datos
		);

		return laee_datos;
	}

	/** {@inheritDoc} */
	public Collection<ProcesoConciliacion> buscarProcesosConciliacionActivos(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<ProcesoConciliacion> laee_datos;

		lsw_watch      = Logger.getNewStopWatch();
		laee_datos     = getParameterBusiness().buscarProcesosConciliacionActivos();

		Logger.log(
		    lsw_watch, "ParameterBean", "buscarProcesosConciliacionActivos", as_idUsuario, as_localIp, as_remoteIp,
		    laee_datos
		);

		return laee_datos;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#buscarProcesosRptProgramas(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<RPTPrograma> buscarProcesosRptProgramas(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<RPTPrograma> lcrpt_procesos;

		lsw_watch          = Logger.getNewStopWatch();
		lcrpt_procesos     = getParameterBusiness().buscarProcesosRptProgramas();

		Logger.log(
		    lsw_watch, "ParameterBean", "buscarProcesosRptProgramas", as_idUsuario, as_localIp, as_remoteIp,
		    lcrpt_procesos
		);

		return lcrpt_procesos;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#buscarProcesosRptProgramasActivos(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<RPTPrograma> buscarProcesosRptProgramasActivos(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<RPTPrograma> lcrpt_procesos;

		lsw_watch          = Logger.getNewStopWatch();
		lcrpt_procesos     = getParameterBusiness().buscarProcesosRptProgramasActivos();

		Logger.log(
		    lsw_watch, "ParameterBean", "buscarProcesosRptProgramasActivos", as_idUsuario, as_localIp, as_remoteIp,
		    lcrpt_procesos
		);

		return lcrpt_procesos;
	}

	/** {@inheritDoc} */
	public Collection<Usuario> buscarUsuariosRolCatalogo()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Usuario> lcca_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcca_datos     = getParameterBusiness().buscarUsuariosRolCatalogo();

		Logger.log(lsw_watch, "ParameterBean", "buscarUsuariosRolCatalogo", null, null, null, lcca_datos);

		return lcca_datos;
	}

	/** {@inheritDoc} */
	public Collection<HoraEjecucionProceso> calcularHorasProcesoConciliacion(
	    Long al_hora, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<HoraEjecucionProceso> laee_datos;

		lsw_watch      = Logger.getNewStopWatch();
		laee_datos     = getParameterBusiness().calcularHorasProcesoConciliacion(al_hora);

		Logger.log(
		    lsw_watch, "ParameterBean", "calcularHorasProcesoConciliacion", as_idUsuario, as_localIp, as_remoteIp,
		    laee_datos
		);

		return laee_datos;
	}

	/** {@inheritDoc} */
	public Map<String, Collection<AfectacionPrestacionServicio>> consultarAfectacionPrestacionServicio(
	    Date   ad_fechaConsulta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                             lsw_watch;
		Map<String, Collection<AfectacionPrestacionServicio>> lmscaps_return;

		lsw_watch          = Logger.getNewStopWatch();
		lmscaps_return     = getParameterBusiness()
				                     .consultarAfectacionPrestacionServicio(ad_fechaConsulta, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "consultarAfectacionPrestacionServicio", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lmscaps_return;
	}

	/** {@inheritDoc} */
	public Collection<ConArchivo> findAllArchivo()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<ConArchivo> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcr_datos     = getParameterBusiness().findAllArchivo();

		Logger.log(lsw_watch, "ParameterBean", "findAllArchivo", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritDoc} */
	public Collection<Constantes> findAllConstants()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Constantes> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_datos     = getParameterBusiness().findAllConstants();

		Logger.log(lsw_watch, "ParameterBean", "findAllConstants", null, null, null, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritDoc} */
	public Collection<CuentaAnalista> findAllCuentaAnalista()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CuentaAnalista> lcca_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcca_datos     = getParameterBusiness().findAllCuentaAnalista();

		Logger.log(lsw_watch, "ParameterBean", "findAllCuentaAnalista", null, null, null, lcca_datos);

		return lcca_datos;
	}

	/** {@inheritDoc} */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion()
	    throws B2BException
	{
		StopWatch                                  lsw_watch;
		Collection<EntidadRecaudadoraConciliacion> lcerc_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcerc_datos     = getParameterBusiness().findAllEntidadRecaudadoraConciliacion();

		Logger.log(lsw_watch, "ParameterBean", "findAllEntidadRecaudadoraConciliacion", null, null, null, lcerc_datos);

		return lcerc_datos;
	}

	/** {@inheritDoc} */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion(boolean ab_estado)
	    throws B2BException
	{
		StopWatch                                  lsw_watch;
		Collection<EntidadRecaudadoraConciliacion> lcerc_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcerc_datos     = getParameterBusiness().findAllEntidadRecaudadoraConciliacion(ab_estado);

		Logger.log(lsw_watch, "ParameterBean", "findAllEntidadRecaudadoraConciliacion", null, null, null, lcerc_datos);

		return lcerc_datos;
	}

	/** {@inheritDoc} */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacionCatalogo()
	    throws B2BException
	{
		StopWatch                                  lsw_watch;
		Collection<EntidadRecaudadoraConciliacion> lcca_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcca_datos     = getParameterBusiness().buscarEntidadRecaudadoraConciliaciones();

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllEntidadRecaudadoraConciliacionCatalogo", null, null, null, lcca_datos
		);

		return lcca_datos;
	}

	/** {@inheritDoc} */
	public Collection<EntidadRecaudadoraCuenta> findAllEntidadRecaudadoraCuenta()
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<EntidadRecaudadoraCuenta> lcerc_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcerc_datos     = getParameterBusiness().findAllEntidadRecaudadoraCuenta();

		Logger.log(lsw_watch, "ParameterBean", "findAllEntidadRecaudadoraCuenta", null, null, null, lcerc_datos);

		return lcerc_datos;
	}

	/** {@inheritDoc} */
	public Collection<EntidadRecaudadoraCuenta> findAllEntidadRecaudadoraCuentaByEntidadRecaudadora(
	    String as_idEntidad
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<EntidadRecaudadoraCuenta> lcerc_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcerc_datos     = getParameterBusiness().findAllEntidadRecaudadoraCuentaByEntidadRecaudadora(as_idEntidad);

		Logger.log(
		    lsw_watch, "ParameterBean", "findAllEntidadRecaudadoraCuentaByEntidadRecaudadora", null, null, null,
		    lcerc_datos
		);

		return lcerc_datos;
	}

	/** {@inheritDoc} */
	public Collection<PeriodoCorte> findAllPeriodoCorte()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<PeriodoCorte> lcerc_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcerc_datos     = getParameterBusiness().findAllPeriodoCorte();

		Logger.log(lsw_watch, "ParameterBean", "findAllPeriodoCorte", null, null, null, lcerc_datos);

		return lcerc_datos;
	}

	/** {@inheritDoc} */
	public Collection<Proceso> findAllProcesosConciliaciones()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Proceso> lcca_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcca_datos     = getParameterBusiness().buscarProcesosConciliaciones();

		Logger.log(lsw_watch, "ParameterBean", "findAllProcesosConciliaciones", null, null, null, lcca_datos);

		return lcca_datos;
	}

	/** {@inheritDoc} */
	public Collection<Rubro> findAllRubro()
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Rubro> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcr_datos     = getParameterBusiness().findAllRubro();

		Logger.log(lsw_watch, "ParameterBean", "findAllRubro", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritDoc} */
	public Collection<Rubro> findAllRubroActivo()
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Rubro> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcr_datos     = getParameterBusiness().findAllRubroActivo();

		Logger.log(lsw_watch, "ParameterBean", "findAllRubroActivo", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritDoc} */
	public Collection<RubroHomologacion> findAllRubroHomologacion()
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<RubroHomologacion> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcr_datos     = getParameterBusiness().findAllRubroHomologacion();

		Logger.log(lsw_watch, "ParameterBean", "findAllRubroHomologacion", null, null, null, lcr_datos);

		return lcr_datos;
	}

	/** {@inheritDoc} */
	public Collection<SIIFCatalogo> findAllSIIFCatalogo()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<SIIFCatalogo> lcsc_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcsc_return     = getParameterBusiness().findAllSIIFCatalogo();

		Logger.log(lsw_watch, "ParameterBean", "findAllSIIFCatalogo", null, null, null, lcsc_return);

		return lcsc_return;
	}

	/** {@inheritDoc} */
	public Collection<Subproceso> findAllSubprocesosConciliaciones(String as_parametro)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Subproceso> lcca_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcca_datos     = getParameterBusiness().buscarSubprocesosConciliaciones(as_parametro);

		Logger.log(lsw_watch, "ParameterBean", "findAllSubprocesosConciliaciones", null, null, null, lcca_datos);

		return lcca_datos;
	}

	/** {@inheritDoc} */
	public ConArchivo findArchivoById(ConArchivo ar_r)
	    throws B2BException
	{
		ConArchivo lr_return;
		StopWatch  lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lr_return     = getParameterBusiness().findArchivoById(ar_r);

		Logger.log(lsw_watch, "ParameterBean", "findArchivoById", null, null, null, null);

		return lr_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#findArchivoIngresos(java.util.Date)
	 */
	@Override
	public Map<String, Collection<Object>> findArchivoIngresos(Date ad_paramBusqueda)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Map<String, Collection<Object>> lmap_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lmap_datos     = getParameterBusiness().findArchivoIngresos(ad_paramBusqueda);

		Logger.log(lsw_watch, "ParameterBean", "findArchivoIngresos", null, null, null, null);

		return lmap_datos;
	}

	/** {@inheritDoc} */
	public Collection<String> findCaracteresConstanteById(
	    String as_idContante, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> lcs_return;

		lsw_watch      = Logger.getNewStopWatch();
		lcs_return     = getParameterBusiness().findCaracteresConstanteById(as_idContante);

		Logger.log(lsw_watch, "ParameterBean", "findCaracteresConstanteById", null, null, null, lcs_return);

		return lcs_return;
	}

	/** {@inheritDoc} */
	public Constantes findConstantById(String as_id)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getParameterBusiness().findConstantById(as_id);

		Logger.log(lsw_watch, "ParameterBean", "findConstantById", null, null, null, null);

		return lc_datos;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#findCorreosAnalistasCuenta(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<String> findCorreosAnalistasCuenta(
	    String as_idCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> lcs_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcs_datos     = getParameterBusiness().findCorreosAnalistasCuenta(as_idCuenta);

		Logger.log(lsw_watch, "ParameterBean", "findCorreosAnalistasCuenta", as_userId, as_localIp, as_remoteIp, null);

		return lcs_datos;
	}

	/** {@inheritDoc} */
	public CuentaAnalista findCuentaAnalistaById(CuentaAnalista aca_ca)
	    throws B2BException
	{
		CuentaAnalista lc_car;
		StopWatch      lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lc_car        = getParameterBusiness().findCuentaAnalistaById(aca_ca);

		Logger.log(lsw_watch, "ParameterBean", "findCuentaAnalistaById", null, null, null, null);

		return lc_car;
	}

	/** {@inheritDoc} */
	public EntidadRecaudadoraConciliacion findEntidadRecaudadoraConciliacionById(
	    EntidadRecaudadoraConciliacion aerc_erc
	)
	    throws B2BException
	{
		EntidadRecaudadoraConciliacion lerc_return;
		StopWatch                      lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lerc_return     = getParameterBusiness().findEntidadRecaudadoraConciliacionById(aerc_erc);

		Logger.log(lsw_watch, "ParameterBean", "findEntidadRecaudadoraConciliacionById", null, null, null, null);

		return lerc_return;
	}

	/** {@inheritDoc} */
	public EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(EntidadRecaudadoraCuenta aerc_erc)
	    throws B2BException
	{
		EntidadRecaudadoraCuenta lerc_return;
		StopWatch                lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lerc_return     = getParameterBusiness().findEntidadRecaudadoraCuentaById(aerc_erc);

		Logger.log(lsw_watch, "ParameterBean", "findEntidadRecaudadoraCuentaById", null, null, null, null);

		return lerc_return;
	}

	/** {@inheritDoc} */
	public EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(
	    EntidadRecaudadoraCuenta aerc_erc, boolean ab_validarCuenta
	)
	    throws B2BException
	{
		EntidadRecaudadoraCuenta lerc_return;
		StopWatch                lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lerc_return     = getParameterBusiness().findEntidadRecaudadoraCuentaById(aerc_erc, ab_validarCuenta);

		Logger.log(lsw_watch, "ParameterBean", "findEntidadRecaudadoraCuentaById", null, null, null, null);

		return lerc_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#findEntidadRecaudadoraCuentaById(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(
	    String as_idCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		EntidadRecaudadoraCuenta lerc_return;

		lsw_watch       = Logger.getNewStopWatch();
		lerc_return     = getParameterBusiness().findEntidadRecaudadoraCuentaById(as_idCuenta);

		Logger.log(lsw_watch, "ParameterBean", "findEntidadRecaudadoraCuentaById", null, null, null, null);

		return lerc_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#findExpedientes()
	 */
	@Override
	public Collection<Expediente> findExpedientes()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Expediente> lce_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lce_datos     = getParameterBusiness().findExpedientes();

		Logger.log(lsw_watch, "ParameterBean", "findExpedientes", null, null, null, lce_datos);

		return lce_datos;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes findImageById(Constantes ac_parametros)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getParameterBusiness().findImageById(ac_parametros);

		Logger.log(lsw_watch, "ParameterBean", "findImageById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritDoc} */
	public PeriodoCorte findPeriodoCorteById(PeriodoCorte apc_pc)
	    throws B2BException
	{
		PeriodoCorte lerc_return;
		StopWatch    lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lerc_return     = getParameterBusiness().findPeriodoCorteById(apc_pc);

		Logger.log(lsw_watch, "ParameterBean", "findPeriodoCorteById", null, null, null, null);

		return lerc_return;
	}

	/** {@inheritDoc} */
	public Collection<PeriodoCorte> findPeriodosFecha(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		Collection<PeriodoCorte> lcpc_return;
		StopWatch                lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lcpc_return     = getParameterBusiness().findPeriodosFecha();

		Logger.log(lsw_watch, "ParameterBean", "findPeriodosFecha", as_userId, as_localIp, as_remoteIp, null);

		return lcpc_return;
	}

	/** {@inheritDoc} */
	public Collection<String> findProcedimientosReportesByPeriodicidad(
	    String as_periodicidad, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<String> lcs_return;
		StopWatch          lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lcs_return     = getParameterBusiness()
				                 .findProcedimientosReportesByPeriodicidad(as_periodicidad, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "findProcedimientosReportesByPeriodicidad", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lcs_return;
	}

	public RPTPrograma findRptProgramaById(
	    String as_idReporte, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		RPTPrograma lrptp_return;
		StopWatch   lsw_watch;

		lsw_watch        = Logger.getNewStopWatch();
		lrptp_return     = getParameterBusiness().findRptProgramaById(as_idReporte);

		Logger.log(lsw_watch, "ParameterBean", "findRptProgramaById", as_userId, as_localIp, as_remoteIp, null);

		return lrptp_return;
	}

	/** {@inheritDoc} */
	public Rubro findRubroById(Rubro ar_r)
	    throws B2BException
	{
		Rubro     lr_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lr_return     = getParameterBusiness().findRubroById(ar_r);

		Logger.log(lsw_watch, "ParameterBean", "findRubroById", null, null, null, null);

		return lr_return;
	}

	/** {@inheritDoc} */
	public RubroHomologacion findRubroHomologacionById(RubroHomologacion ar_r)
	    throws B2BException
	{
		RubroHomologacion lr_return;
		StopWatch         lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lr_return     = getParameterBusiness().findRubroHomologacionById(ar_r);

		Logger.log(lsw_watch, "ParameterBean", "findRubroHomologacionById", null, null, null, null);

		return lr_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#findSIIFCatalogoById(com.bachue.snr.prosnr21.model.pgn.SIIFCatalogo)
	 */
	@Override
	public SIIFCatalogo findSIIFCatalogoById(SIIFCatalogo asc_data)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		SIIFCatalogo lsc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lsc_return     = getParameterBusiness().findSIIFCatalogoById(asc_data);

		Logger.log(lsw_watch, "ParameterBean", "findSIIFCatalogoById", null, null, null, null);

		return lsc_return;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#findTipoDocumentalByExpediente(java.lang.String)
	 */
	@Override
	public Collection<TipoDocumental> findTipoDocumentalByExpediente(String as_param)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TipoDocumental> lctd_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lctd_datos     = getParameterBusiness().findTipoDocumentalByExpediente(as_param);

		Logger.log(lsw_watch, "ParameterBean", "findTipoDocumentalByExpediente", null, null, null, null);

		return lctd_datos;
	}

	/** {@inheritDoc} */
	public PeriodoCorte generarDatosPeriodoCorte(PeriodoCorte apc_periodoCorte)
	    throws B2BException, ParseException
	{
		StopWatch    lsw_watch;
		PeriodoCorte lpc_pc;

		lsw_watch     = Logger.getNewStopWatch();
		lpc_pc        = getParameterBusiness().generarDatosPeriodoCorte(apc_periodoCorte);

		Logger.log(lsw_watch, "ParameterBean", "generarDatosPeriodoCorte", null, null, null, null);

		return lpc_pc;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#generarReportesAfectacionPrestacionServicio(java.lang.String, java.lang.String, java.lang.String, java.util.Collection)
	 */
	@Override
	public byte[] generarReportesAfectacionPrestacionServicio(
	    String as_userId, String as_localIp, String as_remoteIp, Collection<AfectacionPrestacionServicio> acaps_consulta
	)
	    throws B2BException
	{
		byte[]    lba_return;
		StopWatch lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lba_return     = getParameterBusiness()
				                 .generarReportesAfectacionPrestacionServicio(as_userId, as_remoteIp, acaps_consulta);

		Logger.log(
		    lsw_watch, "ParameterBean", "generarReportesAfectacionPrestacionServicio", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return lba_return;
	}

	/** {@inheritDoc} */
	public String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_constante;

		lsw_watch        = Logger.getNewStopWatch();
		ls_constante     = getParameterBusiness().obtenerCaracterConstante(as_idConstante);

		Logger.log(lsw_watch, "ParameterBean", "obtenerCaracterConstante", null, null, null, null);

		return ls_constante;
	}

	/** {@inheritDoc} */
	public Collection<String> obtenerIdConstanesPorCaracterIdLikeCaracter(String as_IdLike, String as_caracter)
	    throws B2BException
	{
		Collection<String> lcs_idConstantes;
		StopWatch          lsw_watch;

		lsw_watch            = Logger.getNewStopWatch();
		lcs_idConstantes     = getParameterBusiness().obtenerIdConstanesPorCaracterIdLikeCaracter(
			    as_IdLike, as_caracter
			);

		Logger.log(lsw_watch, "ParameterBean", "obtenerIdConstanesPorCaracterIdLikeCaracter", null, null, null, null);

		return lcs_idConstantes;
	}

	/** {@inheritDoc} */
	public void salvarArchivo(
	    ConArchivo apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarArchivo(apc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarArchivo", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritDoc} */
	public void salvarConstante(Constantes ac_parametros, boolean ab_accion)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarConstantes(ac_parametros, ab_accion);

		Logger.log(lsw_watch, "ParameterBean", "salvarConstante", null, null, null, null);
	}

	/** {@inheritDoc} */
	public void salvarCuentaAnalista(
	    CuentaAnalista aca_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarCuentaAnalista(aca_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarCuentaAnalista", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritDoc} */
	public void salvarEntidadRecaudadoraConciliacion(
	    EntidadRecaudadoraConciliacion aerc_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarEntidadRecaudadoraConciliacion(aerc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarEntidadRecaudadoraConciliacion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritDoc} */
	public void salvarEntidadRecaudadoraCuenta(
	    EntidadRecaudadoraCuenta aerc_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarEntidadRecaudadoraCuenta(aerc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ParameterBean", "salvarEntidadRecaudadoraCuenta", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#salvarExtractoMensual(com.bachue.snr.prosnr21.model.pgn.ExtractoMensual, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void salvarExtractoMensual(
	    ExtractoMensual aem_extractoMensual, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarExtractoMensual(aem_extractoMensual, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarExtractoMensual", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritDoc} */
	public void salvarPeriodoCorte(
	    PeriodoCorte apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, ParseException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarPeriodoCorte(apc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarPeriodoCorte", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritDoc} */
	public ProcesoConciliacion salvarProcesoConciliacion(
	    ProcesoConciliacion apc_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		ProcesoConciliacion lpc_respuesta;
		StopWatch           lsw_watch;

		lsw_watch         = Logger.getNewStopWatch();
		lpc_respuesta     = getParameterBusiness()
				                    .salvarProcesoConciliacion(apc_parametros, ab_insertar, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarProcesoConciliacion", as_userId, as_localIp, as_remoteIp, null);

		return lpc_respuesta;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#salvarReportesConciliacion(com.bachue.snr.prosnr21.model.pgn.RPTPrograma, boolean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public RPTPrograma salvarReportesConciliacion(
	    RPTPrograma arptp_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		RPTPrograma lrptp_respuesta;
		StopWatch   lsw_watch;

		lsw_watch           = Logger.getNewStopWatch();
		lrptp_respuesta     = getParameterBusiness()
				                      .salvarReportesConciliacion(
				    arptp_parametros, ab_insertar, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "ParameterBean", "salvarReportesConciliacion", as_userId, as_localIp, as_remoteIp, null);

		return lrptp_respuesta;
	}

	/** {@inheritDoc} */
	public void salvarRubro(
	    Rubro apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarRubro(apc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarRubro", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritDoc} */
	public void salvarRubroHomologacion(
	    RubroHomologacion apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarRubroHomologacion(apc_parametros, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarRubroHomologacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote#salvarSIIFCatalogo(com.bachue.snr.prosnr21.model.pgn.SIIFCatalogo, boolean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void salvarSIIFCatalogo(
	    SIIFCatalogo asc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().salvarSIIFCatalogo(asc_parametros, ab_accion, as_userId, as_localIp);

		Logger.log(lsw_watch, "ParameterBean", "salvarSIIFCatalogo", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritDoc} */
	public void updateCaracter(String as_id, String as_caracter, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getParameterBusiness().updateCaracter(as_id, as_caracter, as_userId);

		Logger.log(lsw_watch, "ParameterBean", "updateCaracter", as_userId, null, null, null);
	}

	/**
	 * Retorna el valor de parameter business.
	 *
	 * @return el valor de parameter business
	 */
	private ParameterBusiness getParameterBusiness()
	{
		if(ip_business == null)
			ip_business = new ParameterBusiness();

		return ip_business;
	}
}
