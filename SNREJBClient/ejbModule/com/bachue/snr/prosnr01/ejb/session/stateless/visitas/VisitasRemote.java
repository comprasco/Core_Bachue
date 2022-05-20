package com.bachue.snr.prosnr01.ejb.session.stateless.visitas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.business.DetalleEjecucionVisitas;
import com.bachue.snr.prosnr01.model.business.EjecucionVisitas;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaTipo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades VisitasRemote.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 3/08/2020
 */
@Remote
public interface VisitasRemote
{
	/**
	 * Accion enviar delegado registro.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idSubproceso de as id subproceso
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void accionEnviarDelegadoRegistro(
	    String as_idSolicitud, String as_idSubproceso, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Aprobacion ejecucion visitas.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void aprobacionEjecucionVisitas(String as_remoteIp)
	    throws B2BException;

	/**
	 * Buscar solicitud con persona.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id de la solicitud.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor del List
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> buscarSolicitudConPersona(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar datos panel solicitud visitas.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EjecucionVisitas cargarDatosEjecucionVisitas(String as_idSolicitud, String as_userId)
	    throws B2BException;

	/**
	 * Cargar datos panel tipo tramites A realizar.
	 *
	 * @param as_idTramiteVisita de as id tramite visita
	 * @param as_idRol de as id rol
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteVisitaTipo> cargarDatosPanelTipoTramitesARealizar(
	    String as_idTramiteVisita, String as_idRol
	)
	    throws B2BException;

	/**
	 * Cargar detalle ejecucion visitas.
	 *
	 * @param as_tipoTramiteSeleccionado de as tipo tramite seleccionado
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de detalle ejecucion visitas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DetalleEjecucionVisitas cargarDetalleEjecucionVisitas(
	    String as_tipoTramiteSeleccionado, String as_idSolicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar texto campos.
	 *
	 * @param ab_auto de ab auto
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_ipRemota de as ip remota
	 * @return el valor de string[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String[] cargarTextoCampos(
	    boolean ab_auto, String as_idSolicitudVisitas, String as_idUsuario, String as_localIp, String as_ipRemota
	)
	    throws B2BException;

	public Collection<Usuario> consultarUsuariosReasignacion(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar auto ordena visita general.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto de aot texto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoOrdenaVisitaGeneral(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar documentos.
	 *
	 * @param as_idConstante de as id constante
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_textoTags de aot texto tags
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de detalle ejecucion visitas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DetalleEjecucionVisitas generarDocumentos(
	    String as_idConstante, String as_idSolicitudVisitas, OficiosTexto aot_textoTags, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar resolucion ordena intervencion.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto de aot texto
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarResolucionOrdenaIntervencion(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar documento OWCC.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_ipRemota de as ip remota
	 * @param ab_auto de ab auto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDocumentoOWCC(
	    String as_idSolicitud, String as_idUsuario, String as_localIp, String as_ipRemota, boolean ab_auto
	)
	    throws B2BException;

	/**
	 * Salvar ejecucion visitas.
	 * @param lc_usuario
	 *
	 * @param as_idConstante de as id constante
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aba_documento de aba documento
	 * @param aot_solucionTags de aot solucion tags
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEjecucionVisitas(
	    boolean ab_procesoReasignacion, Collection<Usuario> acu_usuarios, String as_idConstante, String as_idSolicitudVisitas, byte[] aba_documento,
	    OficiosTexto aot_solucionTags, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar informacion visitas.
	 *
	 * @param as_solicitud de as solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud visitas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudVisitas salvarInformacionVisitas(
	    String as_idSolicitudComp, SolicitudVisitas as_solicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar usuarios visitadores.
	 *
	 * @param acu_usuarios de acu usuarios
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String salvarUsuariosVisitadores(
	    String as_nirComp, Collection<Usuario> acu_usuarios, String as_idSolicitud, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;
}
