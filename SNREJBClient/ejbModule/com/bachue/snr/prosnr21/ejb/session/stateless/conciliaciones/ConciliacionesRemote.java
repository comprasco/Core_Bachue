package com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import com.bachue.snr.prosnr21.model.pgn.ArchivoDRXC;
import com.bachue.snr.prosnr21.model.pgn.ConPartidaA;
import com.bachue.snr.prosnr21.model.pgn.DRXCTotalCTA;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.ExtractoBancoMensual;
import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;

import java.io.IOException;

import java.text.ParseException;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.Remote;

import javax.mail.MessagingException;

import javax.mail.internet.AddressException;


/**
 * Interface que contiene todos las propiedades ConciliacionesRemote.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 8/09/2021
 */
@Remote
public interface ConciliacionesRemote
{
	/**
	 * Cargar en BD los archivos que se encuentran en el FTP para un banco / cuenta / día.
	 *
	 * @param as_idProcesoConciliacion Id del proceso de conciliación que se ejecutará
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_localIp IP del servidor que atiende la peticion
	 * @param as_remoteIp IP desde donde se lanza la ejecucion del método
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public void cargarArchivos(
	    String as_idProcesoConciliacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, Exception;

	/**
	 * Cargar archivos.
	 *
	 * @param ad_fechaConciliacion the ad fecha conciliacion
	 * @param as_idCuenta the as id cuenta
	 * @param adTipoArchivo the ad tipo archivo
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the int
	 * @throws B2BException the b 2 B exception
	 */
	public int cargarArchivos(
	    Date ad_fechaConciliacion, String as_idCuenta, String adTipoArchivo, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, AddressException, MessagingException;

	/**
	 * Cargar en BD los archivos CRPS que se encuentran en el FTP.
	 *
	 * @param ad_fecha Fecha de transacción bancaria sobre la cual se extraeran los registros en el archivo CRPS
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_localIp IP del servidor que atiende la peticion
	 * @param as_remoteIp IP desde donde se lanza la ejecucion del método
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarCRPS(Date ad_fecha, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Busca las opciones disponibles del menú para el usuario que inicia sesión.
	 *
	 * @param as_userId Id del usuario a utilizar como filtro en la busqueda
	 * @param as_idComponente Id del componente a utilizar como filtro en la busqueda
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Colección resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos o no se encuentra información
	 */
	public Collection<Opcion> cargarOpcionesMenu(
	    String as_userId, String as_idComponente, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Ejecutar procedimiento reportes.
	 *
	 * @param as_fecha the as fecha
	 * @param as_procedimiento the as procedimiento
	 * @param as_userId the as user id
	 * @param as_localIpAddress the as local ip address
	 * @param as_remoteIpAddress the as remote ip address
	 * @throws B2BException the b 2 B exception
	 */
	public void ejecutarProcedimientoReportes(
	    String as_fecha, String as_procedimiento, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Fill numero fecha SIIF.
	 *
	 * @param adtc_objParametros de adtc obj parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void fillNumeroFechaSIIF(
	    DRXCTotalCTA adtc_objParametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método que retorna todas las entidades recaudadoras de conciliación de un usuario.
	 *
	 * @param as_idUser de as id user
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadoraConciliacion> findEntidadRecaudadoraConciliacionByUser(String as_idUser)
	    throws B2BException;

	/**
	 * Retorna el valor del objeto de Collection de EntidadRecaudadoraCuenta.
	 *
	 * @param is_idEntidadRecaudadora de is id entidad recaudadora
	 * @param as_idUser de as id user
	 * @return devuelve el valor de Collection de EntidadRecaudadoraCuenta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraCuenta
	 */
	public Collection<EntidadRecaudadoraCuenta> findEntidadRecaudadoraCuentaByUser(
	    String is_idEntidadRecaudadora, String as_idUser
	)
	    throws B2BException;

	/**
	 * Find entidad recaudadora cuenta data.
	 *
	 * @param as_idEntidadRecaudadora the as id entidad recaudadora
	 * @param as_userId the as user id
	 * @return the map
	 * @throws B2BException the b 2 B exception
	 */
	public Map<String, Object> findEntidadRecaudadoraCuentaData(String as_idEntidadRecaudadora, String as_userId)
	    throws B2BException;

	/**
	 * Find multicash detalle by cuenta and fecha.
	 *
	 * @param as_idCuenta de as id cuenta
	 * @param ad_fecha de ad fecha
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConPartidaA> findMulticashDetalleByCuentaAndFecha(
	    String as_idCuenta, Date ad_fecha, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	public Usuario findUserById(Usuario ate_parametros)
	    throws B2BException;

	/**
	 * Generar reportes.
	 *
	 * @param as_userId the as user id
	 * @param as_localIpAddress the as local ip address
	 * @param as_remoteIpAddress the as remote ip address
	 * @param accpa_consulta the accpa consulta
	 * @param ad_fechaConciliacion the ad fecha conciliacion
	 * @param as_idCuenta the as id cuenta
	 * @param as_idBanco the as id banco
	 * @return the byte[]
	 * @throws B2BException the b 2 B exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] generarReportes(
	    String as_userId, String as_localIpAddress, String as_remoteIpAddress, Collection<ConPartidaA> accpa_consulta,
	    Date ad_fechaConciliacion, String as_idCuenta, String as_idBanco
	)
	    throws B2BException, IOException;

	/**
	 * Generar reportes conciliaciones.
	 *
	 * @param arptp_reporte the arptp reporte
	 * @param ac_fecha the ac fecha
	 * @param as_userId the as user id
	 * @param as_localIpAddress the as local ip address
	 * @param as_remoteIpAddress the as remote ip address
	 * @throws B2BException the b 2 B exception
	 * @throws ParseException the parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void generarReportesConciliaciones(
	    RPTPrograma arptp_reporte, Calendar ac_fecha, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException, ParseException, IOException;

	/**
	 * Procesos conciliaciones.
	 *
	 * @param as_procesoConciliacion the as proceso conciliacion
	 * @param ad_fechaDesde the ad fecha desde
	 * @param ad_fechaHasta the ad fecha hasta
	 * @param as_idCuenta the as id cuenta
	 * @param as_userId the as user id
	 * @param as_remoteIpAddress the as remote ip address
	 */
	public void procesosConciliaciones(
	    String as_procesoConciliacion, Date ad_fechaDesde, Date ad_fechaHasta, String as_idCuenta, String as_userId,
	    String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Realizar busqueda fecha conciliar DRXC.
	 *
	 * @param aerc_param the aerc param
	 * @param as_userId the as user id
	 * @param as_localIpAddress the as local ip address
	 * @param as_remoteIpAddress the as remote ip address
	 * @return the map
	 * @throws B2BException the b 2 B exception
	 */
	public Map<String, Object> realizarBusquedaFechaConciliarDRXC(
	    EntidadRecaudadoraCuenta aerc_param, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Realizar nueva solicitud.
	 *
	 * @param ad_fechaConciliacion the ad fecha conciliacion
	 * @param as_idCuenta the as id cuenta
	 * @param as_correoElectronico the as correo electronico
	 * @param as_idTipoArchivo the as id tipo archivo
	 * @param as_userId the as user id
	 * @param as_localIpAddress the as local ip address
	 * @param as_remoteIpAddress the as remote ip address
	 * @throws B2BException the b 2 B exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public void realizarNuevaSolicitud(
	    Date ad_fechaConciliacion, String as_idCuenta, String as_correoElectronico, String as_idTipoArchivo,
	    String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException, AddressException, MessagingException;

	/**
	 * Salvar partidas A.
	 *
	 * @param as_userId the as user id
	 * @param as_localIpAddress the as local ip address
	 * @param as_remoteIpAddress the as remote ip address
	 * @param accpa_consulta the accpa consulta
	 * @param acad_dataComprobante the acad data comprobante
	 * @throws B2BException the b 2 B exception
	 */
	public void salvarPartidasA(
	    String as_userId, String as_localIpAddress, String as_remoteIpAddress, Collection<ConPartidaA> accpa_consulta,
	    Collection<ArchivoDRXC> acad_dataComprobante
	)
	    throws B2BException;

	/**
	 * Upload file extracto banco mensual.
	 *
	 * @param aebm_extractoBancoMensual the aebm extracto banco mensual
	 * @param as_userId the as user id
	 * @param as_localIpAddress the as local ip address
	 * @param as_remoteIpAddress the as remote ip address
	 * @return the int
	 * @throws B2BException the b 2 B exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public int uploadFileExtractoBancoMensual(
	    ExtractoBancoMensual aebm_extractoBancoMensual, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException, IOException, ParseException;

	/**
	 * Verificar autenticacion segundo factor.
	 *
	 * @param as_parametro de as parametro
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean verificarAutenticacionSegundoFactor(String as_parametro)
	    throws B2BException;
}
