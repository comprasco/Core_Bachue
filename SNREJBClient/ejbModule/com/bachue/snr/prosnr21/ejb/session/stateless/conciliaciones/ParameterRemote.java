package com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

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

import java.text.ParseException;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ParameterRemote.
 *
 * @author  Duvan Beltran
 * Fecha de Creacion: 19/06/2020
 */
@Remote
public interface ParameterRemote extends com.bachue.snr.ejb.session.stateless.reference.SchedulerRemote
{
	/**
	 * Buscar alertas confrontacion ingresos.
	 *
	 * @param as_alerta de as alerta
	 * @param ad_fecha de ad fecha
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConInconsistencia> buscarAlertasConfrontacionIngresos(
	    String as_alerta, Date ad_fecha, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar avisos conciliacion ingresos.
	 *
	 * @param as_idCuenta de as id cuenta
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConInconsistencia> buscarAvisosConciliacionIngresos(
	    String as_idCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método que retorna todas las entidades recaudadoras de conciliación.
	 *
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacion(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar extracto diario by cuenta banco fecha.
	 *
	 * @param aed_parametros the aed parametros
	 * @param as_idUsuario the as id usuario
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the extracto diario
	 * @throws B2BException the b 2 B exception
	 */
	public ExtractoDiario buscarExtractoDiarioByCuentaBancoFecha(
	    ExtractoDiario aed_parametros, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar extracto mensual.
	 *
	 * @param aem_objExtractoMensual de aem obj extracto mensual
	 * @return el valor de extracto mensual
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ExtractoMensual buscarExtractoMensual(ExtractoMensual aem_objExtractoMensual)
	    throws B2BException;

	/**
	 * Buscar periodicidad.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Periodicidad> buscarPeriodicidad(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Método que retorna una colección con todos los procesos de conciliación.
	 *
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public Collection<ProcesoConciliacion> buscarProcesosConciliacion(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método que retorna una colección con todos los procesos de conciliación.
	 *
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public Collection<ProcesoConciliacion> buscarProcesosConciliacionActivos(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar procesos rpt programas.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RPTPrograma> buscarProcesosRptProgramas(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método que retorna una colección con todos los RPT programas de conciliación.
	 *
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public Collection<RPTPrograma> buscarProcesosRptProgramasActivos(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de Usuario.
	 *
	 * @return colleccion de Subproceso con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Usuario> buscarUsuariosRolCatalogo()
	    throws B2BException;

	/**
	 * Método que calcula las posibles horas de ejecución de un proceso conciliacion.
	 *
	 * @param al_hora Argumento de tipo <code>Long</code> que contiene la hora inicial del proceso.
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<HoraEjecucionProceso> calcularHorasProcesoConciliacion(
	    Long al_hora, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar afectacion prestacion servicio.
	 *
	 * @param ad_fechaConsulta the ad fecha consulta
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the map
	 * @throws B2BException the b 2 B exception
	 */
	public Map<String, Collection<AfectacionPrestacionServicio>> consultarAfectacionPrestacionServicio(
	    Date   ad_fechaConsulta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de   Archivo.
	 *
	 * @return colleccion de Archivo con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConArchivo> findAllArchivo()
	    throws B2BException;

	/**
	 * Find all constants.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Constantes> findAllConstants()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de Cuenta Analista.
	 *
	 * @return colleccion de Cuenta Analista con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CuentaAnalista> findAllCuentaAnalista()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de Entidad Recaudadora Conciliacion.
	 *
	 * @return colleccion de Entidad Recaudadora Conciliacion con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de Entidad Recaudadora Conciliacion.
	 *
	 * @param ab_activo de ab activo
	 * @return colleccion de Entidad Recaudadora Conciliacion con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion(boolean ab_activo)
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de EntidadRecaudadoraConciliacion.
	 *
	 * @return colleccion de EntidadRecaudadoraConciliacion con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacionCatalogo()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de Entidad Recaudadora Cuenta.
	 *
	 * @return colleccion de EntidadRecaudadoraCuenta con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadoraCuenta> findAllEntidadRecaudadoraCuenta()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de Entidad Recaudadora Cuenta.
	 *
	 * @param as_idEntidad con el id de la entidad a consultar
	 * @return colleccion de EntidadRecaudadoraCuenta con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadoraCuenta> findAllEntidadRecaudadoraCuentaByEntidadRecaudadora(
	    String as_idEntidad
	)
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de.
	 *
	 * @return colleccion de PeriodoCorte con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PeriodoCorte> findAllPeriodoCorte()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de Proceso.
	 *
	 * @return colleccion de Proceso con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Proceso> findAllProcesosConciliaciones()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de   Rubro.
	 *
	 * @return colleccion de Rubro con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Rubro> findAllRubro()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de   Rubro.
	 *
	 * @return colleccion de Rubro con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Rubro> findAllRubroActivo()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de   RubroHomologacion.
	 *
	 * @return colleccion de RubroHomologacion con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RubroHomologacion> findAllRubroHomologacion()
	    throws B2BException;

	/**
	 * Find all SIIF catalogo.
	 *
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<SIIFCatalogo> findAllSIIFCatalogo()
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de Subproceso.
	 *
	 * @param as_parametro correspondiente al valor de as parametro
	 * @return colleccion de Subproceso con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Subproceso> findAllSubprocesosConciliaciones(String as_parametro)
	    throws B2BException;

	/**
	 * Método para conssultar un registro de Archivo basad en su identificador.
	 *
	 * @param ar_r correspondiente al valor de ar r
	 * @return Archivo con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConArchivo findArchivoById(ConArchivo ar_r)
	    throws B2BException;

	/**
	 * Find archivo ingresos.
	 *
	 * @param ad_paramBusqueda de ad param busqueda
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Collection<Object>> findArchivoIngresos(Date ad_paramBusqueda)
	    throws B2BException;

	/**
	 * Find caracteres constante by id.
	 *
	 * @param as_idContante the as id contante
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<String> findCaracteresConstanteById(
	    String as_idContante, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find constant by id.
	 *
	 * @param as_id Código de la constante a encontrar correspondiente al valor del tipo de objeto Constantes
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes findConstantById(String as_id)
	    throws B2BException;

	/**
	 * Find correos analistas cuenta.
	 *
	 * @param as_idCuenta de as id cuenta
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> findCorreosAnalistasCuenta(
	    String as_idCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para conssultar un registro de CuentaAnalista basad en su identificador.
	 *
	 * @param aca_ca correspondiente al valor de aca ca
	 * @return CausalMayorValor con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CuentaAnalista findCuentaAnalistaById(CuentaAnalista aca_ca)
	    throws B2BException;

	/**
	 * Método para conssultar un registro de Entidad Recaudadora Conciliacion basad en su identificador.
	 *
	 * @param aerc_erc correspondiente al valor de aerc erc
	 * @return EntidadRecaudadoraConciliacion con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EntidadRecaudadoraConciliacion findEntidadRecaudadoraConciliacionById(
	    EntidadRecaudadoraConciliacion aerc_erc
	)
	    throws B2BException;

	/**
	 * Método para conssultar un registro de Entidad Recaudadora Cuenta basad en su identificador.
	 *
	 * @param aerc_erc correspondiente al valor de aerc erc
	 * @return EntidadRecaudadoraCuenta con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(EntidadRecaudadoraCuenta aerc_erc)
	    throws B2BException;

	/**
	 * Método para conssultar un registro de Entidad Recaudadora Cuenta basad en su identificador.
	 *
	 * @param aerc_erc correspondiente al valor de aerc erc
	 * @param ab_validarCuenta para validar si se debe validar la cuenta consultada
	 * @return EntidadRecaudadoraCuenta con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(
	    EntidadRecaudadoraCuenta aerc_erc, boolean ab_validarCuenta
	)
	    throws B2BException;

	/**
	 * Find entidad recaudadora cuenta by id.
	 *
	 * @param as_idCuenta the as id cuenta
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the entidad recaudadora cuenta
	 * @throws B2BException the b 2 B exception
	 */
	public EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(
	    String as_idCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar expedientes.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Expediente> findExpedientes()
	    throws B2BException;

	/**
	 * Find image by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes findImageById(Constantes ac_parametros)
	    throws B2BException;

	/**
	 * Método para conssultar un registro de Periodo Corte basad en su identificador.
	 *
	 * @param apc_pc correspondiente al valor de apc pc
	 * @return PeriodoCorte con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PeriodoCorte findPeriodoCorteById(PeriodoCorte apc_pc)
	    throws B2BException;

	/**
	 * Find periodos fecha.
	 *
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<PeriodoCorte> findPeriodosFecha(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find procedimientos reportes by periodicidad.
	 *
	 * @param as_periodicidad the as periodicidad
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<String> findProcedimientosReportesByPeriodicidad(
	    String as_periodicidad, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find rpt programa by id.
	 *
	 * @param as_idReporte the as id reporte
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the RPT programa
	 * @throws B2BException the b 2 B exception
	 */
	public RPTPrograma findRptProgramaById(
	    String as_idReporte, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para conssultar un registro de Rubro basad en su identificador.
	 *
	 * @param ar_r correspondiente al valor de ar r
	 * @return Rubro con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Rubro findRubroById(Rubro ar_r)
	    throws B2BException;

	/**
	 * Método para conssultar un registro de RubroHomologacion basad en su identificador.
	 *
	 * @param ar_r correspondiente al valor de ar r
	 * @return RubroHomologacion con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RubroHomologacion findRubroHomologacionById(RubroHomologacion ar_r)
	    throws B2BException;

	/**
	 * Find SIIF catalogo by id.
	 *
	 * @param asc_data the lsc data
	 * @return the SIIF catalogo
	 * @throws B2BException the b 2 B exception
	 */
	public SIIFCatalogo findSIIFCatalogoById(SIIFCatalogo asc_data)
	    throws B2BException;

	/**
	 * Find tipo documental by expediente.
	 *
	 * @param as_param de as param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumental> findTipoDocumentalByExpediente(String as_param)
	    throws B2BException;

	/**
	 * Método de generación de datos de periodo corte.
	 *
	 * @param apc_periodoCorte correspondiente al valor de apc periodo corte
	 * @return Periodo corte con los datos generados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws ParseException cuando se produce algun error en el proceso
	 */
	public PeriodoCorte generarDatosPeriodoCorte(PeriodoCorte apc_periodoCorte)
	    throws B2BException, java.text.ParseException;

	/**
	 * Generar reportes afectacion prestacion servicio.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param acaps_consulta de acaps consulta
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarReportesAfectacionPrestacionServicio(
	    String as_userId, String as_localIp, String as_remoteIp, Collection<AfectacionPrestacionServicio> acaps_consulta
	)
	    throws B2BException;

	/**
	 * Obtener la propiedad caracter de una constante.
	 *
	 * @param as_idConstante Codigo de la constante a buscar
	 * @return Propiedad caracter de la constante identificada con as_idConstante
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de Archivo.
	 *
	 * @param apc_parametros correspondiente al valor de apc parametros
	 * @param ab_accion correspondiente al valor de ab accion
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarArchivo(
	    ConArchivo apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar constante.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarConstante(Constantes ac_parametros, boolean ab_accion)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de CuentaAnalista.
	 *
	 * @param aca_parametros correspondiente al valor de aca parametros
	 * @param ab_accion correspondiente al valor de ab accion
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCuentaAnalista(
	    CuentaAnalista aca_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de EntidadRecaudadoraConciliacion.
	 *
	 * @param aerc_parametros correspondiente al valor de aerc parametros
	 * @param ab_accion correspondiente al valor de ab accion
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEntidadRecaudadoraConciliacion(
	    EntidadRecaudadoraConciliacion aerc_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de EntidadRecaudadoraCuenta.
	 *
	 * @param aerc_parametros correspondiente al valor de aerc parametros
	 * @param ab_accion correspondiente al valor de ab accion
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEntidadRecaudadoraCuenta(
	    EntidadRecaudadoraCuenta aerc_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar extracto mensual.
	 *
	 * @param aem_extractoMensual de aem extracto mensual
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarExtractoMensual(
	    ExtractoMensual aem_extractoMensual, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de PeriodoCorte.
	 *
	 * @param apc_parametros correspondiente al valor de apc parametros
	 * @param ab_accion correspondiente al valor de ab accion
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws ParseException cuando se produce algun error en el proceso
	 */
	public void salvarPeriodoCorte(
	    PeriodoCorte apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, ParseException;

	/**
	 * Método encargado de salvar proceso conciliacion.
	 *
	 * @param apc_parametros Argumento de tipo <code>ProcesoConciliacion</code> que contiene el proceso conciliación.
	 * @param ab_insertar Argumento de tipo <code>boolean</code> que indica la acción a ejecutar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de proceso conciliacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcesoConciliacion salvarProcesoConciliacion(
	    ProcesoConciliacion apc_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar reportes conciliacion.
	 *
	 * @param arptp_parametros de arptp parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de RPT programa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RPTPrograma salvarReportesConciliacion(
	    RPTPrograma arptp_parametros, boolean ab_insertar, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de Rubro.
	 *
	 * @param apc_parametros correspondiente al valor de apc parametros
	 * @param ab_accion correspondiente al valor de ab accion
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarRubro(
	    Rubro apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de RubroHomologacion.
	 *
	 * @param apc_parametros correspondiente al valor de apc parametros
	 * @param ab_accion correspondiente al valor de ab accion
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarRubroHomologacion(
	    RubroHomologacion apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar SIIF catalogo.
	 *
	 * @param asc_parametros the asc parametros
	 * @param ab_accion the ab accion
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @throws B2BException the b 2 B exception
	 */
	public void salvarSIIFCatalogo(
	    SIIFCatalogo asc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
