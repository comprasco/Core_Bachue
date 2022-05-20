package com.bachue.snr.prosnr01.ejb.session.stateless.aprobacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades AprobacionRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface AprobacionRemote
{
	/**
	 * Termina la etapa actual y llama al proc crear etapa.
	 *
	 * @param ath_parametros de Objeto contenedor de la información de la etapa a terminar
	 * @param amt_motivoTramite Objeto contenedor del motivo a utilizar en la terminación de la etapa actual
	 * @param as_usuario Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarEtapaYCrearSiguiente(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * actualizar Turno Historia Relacion.
	 *
	 * @param aca_param Objeto contenedor de la aprobación a salvar
	 * @param as_usuario de as usuario
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Objeto contenedor del resultado de la ejecución del metodo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarTurnoHistoriaRelacion(
	    Aprobacion aca_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Verifica si el caso actual pertenece a una adjudicación de remate.
	 *
	 * @param al_idTurnoHistoria Id del turno historia al cual se aplicará la verificación
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIpAddress Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIpAddress Dirección IP del cliente que ejecuta la acción
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public boolean adjudicacionRemate(
	    Long al_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException, Exception;

	/**
	 * Aprobar traslado etapa 660.
	 *
	 * @param al_idTurnoHistoria correspondiente al valor de id turno historia
	 * @param as_ObservacionesTramite correspondiente al valor de observaciones tramite
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void aprobarTrasladoEtapa660(
	    long al_idTurnoHistoria, String as_ObservacionesTramite, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtiene la justificación del cierre de folio previamente ingresada.
	 *
	 * @param acep_arg Objeto contenedor de la información necesaria para obtener la justificación
	 * @return Objeto contenedor de la justificación de cierre de folio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CambioEstadoPredio cargarJustificacionCierreFolio(CambioEstadoPredio acep_arg)
	    throws B2BException;

	/**
	 * Consultar traslados matriculas.
	 *
	 * @param al_idTurnoHistoria correspondiente al valor de id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection TrasladoMatricula
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TrasladoMatricula> consultarTrasladosMatriculas(
	    Long al_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Regresa un caso a la etapa anterior.
	 *
	 * @param aa_aprobacion Objeto contenedor de la información del caso a devolver
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public void devolverAprobacion(Aprobacion aa_aprobacion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException, Exception;

	/**
	 * Ejecutar traslado procedimiento.
	 *
	 * @param al_idTurnoHistoria de Objeto contenedor de la información de la etapa actual.
	 * @param as_userId correspondiente al valor de user id
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void ejecutarTrasladoProcedimiento(
	    Long al_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Permite identificar si un caso hace parte del proceso de realizar registro.
	 *
	 * @param al_idTurnoHistoria Identificador del caso a validar
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean esRealizarRegistro(Long al_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Obtiene todos los casos y su información en la etapa de aprobación.
	 *
	 * @param aa_oa Objeto contenedor de lso filtros a utilizar en la busqueda de los casos
	 * @param ab_conUsuario indica si la aprobación tiene un usuario asignado
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Aprobacion> findAllData(Aprobacion aa_oa, boolean ab_conUsuario)
	    throws B2BException;

	/**
	 * Obtiene el detalle de un caso.
	 *
	 * @param aoa_oa de aoa oa
	 * @param ab_bandejaSubproceso true para indicar si es invocado desde la bandeja de subprocesos
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Aprobacion> findDetalleBandeja(
	    Aprobacion aoa_oa, boolean ab_bandejaSubproceso, boolean ab_conUsuario, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtiene la cantidad de turnos asignados a una etapa.
	 *
	 * @param as_estado Indicador del estado de los casos a buscar
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param al_etapa Indetificador de la etapa de los casos a buscar
	 * @param as_proceso Identificador del proceso de los casos a buscar
	 * @param ab_bandejaSubprocesos true para indicar si es invocado desde la bandeja de subprocesos
	 * @return El valor númerico de la cantidad de casos encontrados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Integer findTurnosCantidad(
	    String as_estado, String as_userId, long al_etapa, String as_proceso, boolean ab_bandejaSubprocesos
	)
	    throws B2BException;

	/**
	 * Generar archivo zip aprobacion relacion.
	 *
	 * @param aba_ba Mapa contenedor de los archivos y sus nombres para contruir el zip
	 * @return arreglo de bytes que representa el archivo zip generado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarArchivoZipAprobacionRelacion(Map<String, byte[]> aba_ba)
	    throws B2BException;

	/**
	 * Generar documento relacion aprobacion.
	 *
	 * @param aca_aprobaciones Objeto contenedor de los documentos que se deben generar
	 * @param ab_firmar Indica si los documentos son definitivos
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Resultado de generar los documentos en un mapa con el nombre y el arreglo de bytes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public Map<String, byte[]> generarDocumentoRelacionAprobacion(
	    Collection<Aprobacion> aca_aprobaciones, boolean ab_firmar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, Exception;

	/**
	 * Construye un comprimido con los documentos del turno actual.
	 *
	 * @param aoa_oa Objeto contenedor del turno actual
	 * @return el valor Objeto contenedor del comprimido generado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Aprobacion generateZip(Aprobacion aoa_oa)
	    throws B2BException;

	/**
	 * Inactivar matricula traslado por id.
	 *
	 * @param atm_matricula correspondiente al valor de TrasladoMatricula
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void inactivarMatriculaTrasladoById(
	    TrasladoMatricula atm_matricula, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insertar tipo decision recurso.
	 *
	 * @param aca_data Colección contenedor de las aprobaciones
	 * @param ls_idTipoDecisionRecurso String con el tipo decision recurso a insertar
	 * @param as_idUsuario correspondiente al valor de id usuario
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertarTipoDecisionRecurso(
	    Collection<Aprobacion> aca_data, String ls_idTipoDecisionRecurso, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Construye un mensaje adjudicacion remate para el turno.
	 *
	 * @param al_idTurnoHistoria Id del turno historia a construir el mensaje
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de Objeto contenedor de los mensajes generados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public Object[] mensajeAdjudicacionRemate(
	    Long al_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException, Exception;

	/**
	 * Obtener documentos por turno.
	 *
	 * @param al_idTurnoHistoria Id del turno historia a consultar los documentos
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> obtenerDocumentosPorTurno(
	    String al_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Salvar aprobacion.
	 *
	 * @param aca_param Objeto contenedor de las aprobaciones a salvar
	 * @param as_usuario de as usuario
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Objeto contenedor del resultado de la ejecución del metodo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Aprobacion salvarAprobacion(
	    Collection<Aprobacion> aca_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validacion etapa anterior aprobacion AS.
	 *
	 * @param aa_aprobacion Objeto contenedor de la información del caso a verificar
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return El valor de la etapa de antiguo sistema anterior
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public BigDecimal validacionEtapaAnteriorAprobacionAS(
	    Aprobacion aa_aprobacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, Exception;

	/**
	 * Validacion etapa anterior aprobacion recursos.
	 *
	 * @param aca_aprobaciones colección con la información a validar
	 * @param as_idUsuario correspondiente al valor de id usuario
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Aprobacion con el retorno del metodo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Aprobacion validacionEtapaAnteriorAprobacionRecursos(
	    Collection<Aprobacion> aca_aprobaciones, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
