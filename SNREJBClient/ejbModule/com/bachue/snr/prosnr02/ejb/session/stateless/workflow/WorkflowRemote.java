package com.bachue.snr.prosnr02.ejb.session.stateless.workflow;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;

import com.bachue.snr.prosnr02.model.acc.EtapaTrabajo;
import com.bachue.snr.prosnr02.model.acc.ProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoVersionTrabajo;
import com.bachue.snr.prosnr02.model.pgn.ReglaNegocio;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades WorkflowRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 25/11/2019
 */
@Remote
public interface WorkflowRemote
{
	/**
	 * Procesa un documento generado por medio de workflow.
	 *
	 * @param as_definicion <code>String</code> del archivo a procesar
	 * @param ai_version de version
	 * @param ai_modo de modo
	 * @param ab_nuevaVersion de nueva version
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Colección de procesos generada a partir del archivo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoTrabajo> analizarContenidoDefiniciones(
	    String as_definicion, int ai_version, int ai_modo, boolean ab_nuevaVersion, String as_idUsuarioCreacion,
	    String as_localIp, String as_ipCreacion
	)
	    throws B2BException;

	/**
	 * Aprobar.
	 *
	 * @param as_idProceso de id proceso
	 * @param as_idSubProceso de id sub proceso
	 * @param ai_version de version
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void aprobar(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar info bandeja consulta.
	 *
	 * @param at_parametros de Trazabilidad
	 * @param ab_onlyTurno de only turno
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Trazabilidad> cargarInfoBandejaConsulta(
	    Trazabilidad at_parametros, boolean ab_onlyTurno, String as_idUsuarioCreacion, String as_localIp,
	    String as_ipCreacion
	)
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
	 * Enviar al aprobador.
	 *
	 * @param as_idProceso de id proceso
	 * @param as_idSubProceso de id sub proceso
	 * @param ai_version de version
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAlAprobador(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar todas las etapas.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EtapaTrabajo> findAllEtapas(String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion)
	    throws B2BException;

	/**
	 * Buscar todas las fases.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Fases> findAllFases()
	    throws B2BException;

	/**
	 * Find all reglas.
	 *
	 * @param as_idUsuarioCreacion de as id usuario creacion
	 * @param as_localIp de as local ip
	 * @param as_ipCreacion de as ip creacion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ReglaNegocio> findAllReglas(String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion)
	    throws B2BException;

	/**
	 * Buscar todos los sub procesos.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SubProcesoVersionTrabajo> findAllSubProcesos(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion
	)
	    throws B2BException;

	/**
	 * Buscar todos los usuario tiempos vencimiento.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UnidadTiempoVencimiento> findAllUnidadTiempoVencimiento()
	    throws B2BException;

	/**
	 * Buscar constantes por llave.
	 *
	 * @param as_parametros de llave
	 * @return el valor Constantees
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes findConstantesById(String as_parametros)
	    throws B2BException;

	/**
	 * Método de transacciones con la base de datos para encontrar la constante que
	 * contiene un diagrama BPMN vacío.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findNewDiagram(String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion)
	    throws B2BException;

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_SUBPROCESO
	 * que su campo DEFINICION sea diferente de NULL.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoTrabajo> findProcesos(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion
	)
	    throws B2BException;

	/**
	 * Buscar procesos aprobacion.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoTrabajo> findProcesosAprobacion(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion
	)
	    throws B2BException;

	/**
	 * Buscar sub procesos aprobacion.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_idProceso de id proceso
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SubProcesoTrabajo> findSubProcesosAprobacion(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion, String as_idProceso
	)
	    throws B2BException;

	/**
	 * Buscar subprocesos.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_idProceso de id proceso
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SubProcesoTrabajo> findSubprocesos(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion, String as_idProceso
	)
	    throws B2BException;

	/**
	 * Buscar usuario por id usuario.
	 *
	 * @param ate_parametros de Usuario
	 * @return el valor de usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Usuario findUserById(Usuario ate_parametros)
	    throws B2BException;

	/**
	 * buscar versiones.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_idProceso de id proceso
	 * @param as_idSubproceso de id subproceso
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SubProcesoVersionTrabajo> findVersiones(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion, String as_idProceso,
	    String as_idSubproceso
	)
	    throws B2BException;

	/**
	 * Buscar versiones aprobacion.
	 *
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_idProceso de id proceso
	 * @param as_idSubproceso de id subproceso
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SubProcesoVersionTrabajo> findVersionesAprobacion(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion, String as_idProceso,
	    String as_idSubproceso
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
	 * Obtener monitoreo.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @param as_idTurno de id turno
	 * @param as_idUsuarioCreacion Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipCreacion Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String obtenerMonitoreo(
	    String as_idSolicitud, String as_idTurno, String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion
	)
	    throws B2BException;

	/**
	 * Retornar a estado devuelto.
	 *
	 * @param as_idProceso de idProceso
	 * @param as_idSubProceso de idSubProceso
	 * @param ai_version de version
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void retornarAEstadoDevuelto(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

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
