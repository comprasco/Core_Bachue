package com.bachue.snr.prosnr01.ejb.session.stateless.grabacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades GrabacionRemote.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 16/07/2019
 */
@Remote
public interface GrabacionRemote
{
	
	/**
	 * Cargar oficio texto.
	 *
	 * @param as_idTurno de id turno
	 * @param as_motivoTramite de motivo tramite
	 * @return el valor de oficios texto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficiosTexto cargarOficioTexto(String as_idTurno, String as_motivoTramite)
	    throws B2BException;

	/**
	 * Consultar datos basicos.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @return el valor de datos basicos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosBasicos consultarDatosBasicos(TurnoHistoria ath_turnoHistoria)
	    throws B2BException;

	/**
	 * Consultar existencia predio.
	 *
	 * @param as_idTurno de id turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultarExistenciaPredio(String as_idTurno)
	    throws B2BException;

	/**
	 * Consultar persona interesado.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @param ab_fullData de ab full data
	 * @return el valor de datos del interesado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosDelInteresado consultarPersonaInteresado(String as_idSolicitud, boolean ab_fullData)
	    throws B2BException;

	/**
	 * Consultar predio grabar.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de datos ant sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosAntSistema consultarPredioGrabar(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enviar aprobador 104.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemote de as ip remote
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean enviarAprobador104(
	    String as_idTurnoHistoria, String as_userId, String as_ipLocal, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * Find inbox by user id.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_idTurno de id turno
	 * @param as_nir de nir
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> findInboxByUserId(String as_userId, String as_idTurno, String as_nir)
	    throws B2BException;

	/**
	 * Generar documento negacion.
	 *
	 * @param as_parametros de Suspension
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @param ab_salvar de ab salvar
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarDocumentoNegacion(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress,
	    boolean ab_salvar
	)
	    throws B2BException;

	/**
	 * Generar documento resolucion.
	 *
	 * @param as_parametros de Suspension
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @param ab_salvar de ab salvar
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarDocumentoResolucion(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress,
	    boolean ab_salvar
	)
	    throws B2BException;

	/**
	 * Generar predio.
	 *
	 * @param adb_datosBasicos de DatosBasicos
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de datos basicos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosBasicos generarPredio(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar resolucion.
	 *
	 * @param as_parametros de Suspension
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarResolucion(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Negar solicitud.
	 *
	 * @param as_parametros de Suspension
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void negarSolicitud(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Salvar grabacion matriculas.
	 *
	 * @param as_parametros de Suspension
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarGrabacionMatriculas(
	    Suspension as_parametros, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Validar existencia matricula.
	 *
	 * @param as_idTurnoHistoria de id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarExistenciaMatricula(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar predio grabar.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarPredioGrabar(TurnoHistoria ath_turnoHistoria)
	    throws B2BException;
}
