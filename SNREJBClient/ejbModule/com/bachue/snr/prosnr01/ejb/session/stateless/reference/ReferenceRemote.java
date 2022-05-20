package com.bachue.snr.prosnr01.ejb.session.stateless.reference;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.objectDataBase.UserObjects;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraBloqueo;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarifaRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoNaturalGenero;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalNegacionCopias;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.DependenciaSNR;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoActividad;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoAutomatico;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;
import com.bachue.snr.prosnr01.model.sdb.png.DominioNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


// TODO: Auto-generated Javadoc
/**
 * Interface que contiene todos las propiedades ReferenceRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@javax.ejb.Remote
public interface ReferenceRemote extends com.bachue.snr.ejb.session.stateless.reference.SchedulerRemote
{
	/**
	 * Buscar campos por criterio.
	 *
	 * @param am_parametros de am parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CamposConsulta> buscarCamposPorCriterio(
	    CamposConsulta am_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar motivos consulta.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Constantes> buscarMotivosConsulta(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Buscar todos circulos registrales origen destino activos.
	 *
	 * @param ab_b correspondiente al valor de ab b
	 * @param as_idCirculoOrigen correspondiente al valor de id circulo origen
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> buscarTodosCirculosRegistralesOrigenDestinoActivos(
	    boolean ab_b, String as_idCirculoOrigen, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar todos municipios por pais.
	 *
	 * @param am_parametros de am parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Municipio> buscarTodosMunicipiosPorPais(
	    Municipio am_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Calcular fecha vencimiento.
	 *
	 * @param afvtui_parametros de afvtui parametros
	 * @return el valor de date
	 * @throws B2BException de b 2 B exception
	 */
	public Date calcularFechaVencimiento(FechaVenceTerminosUI afvtui_parametros)
	    throws B2BException;

	/**
	 * Método encargado de cargar las anotaciones para la matricula a agregar.
	 *
	 * @param aps_predioAgregar Objeto que contiene la información del predio.
	 * @param ab_conBaseEn Variable que indica si es un caso de con base en o matriculas segregación.
	 * @return Objeto que contiene la información del predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado cargarAnotacionesMatriculaAgregar(
	    com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado aps_predioAgregar, boolean ab_conBaseEn
	)
	    throws B2BException;

	/**
	 * Busca los componentes disponibles del menú para el usuario que inicia sesión.
	 *
	 * @param as_userId Id del usuario a utilizar como filtro en la busqueda
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Colección resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos o no se encuentra información
	 */
	public List<Componente> cargarComponentesMenu(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Cargar data direccion.
	 *
	 * @return retorna un mapa con los datos parametricos de direccion
	 * @throws B2BException de b 2 B exception
	 */
	public Map<String, Map<String, String>> cargarDataDireccion()
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
	 * Cargar tipo consulta.
	 *
	 * @param as_userId de as user id
	 * @param ab_OrderById de ab order by id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Consultas> cargarTipoConsulta(
	    String as_userId, boolean ab_OrderById, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find activo by circulo matricula.
	 *
	 * @param apr_pr de apr pr
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<String> findActivoByCirculoMatricula(PredioRegistro apr_pr)
	    throws B2BException;

	/**
	 * Find all activo by usuario.
	 *
	 * @param userId de user id
	 * @param localIpAddress de local ip address
	 * @param remoteIpAddress de remote ip address
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> findAllActivoByUsuario(
	    String userId, String localIpAddress, String remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Método para consultar un registro de Causal Negacion Copias.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return CausalNegacionCopias con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalNegacionCopias> findAllCausalNegacionCopias(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all circulos registrales activos.
	 *
	 * @param ab_b de ab b
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CirculoRegistral> findAllCirculosRegistralesActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all circulos registrales activos by id solicitud.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param as_idSolicitud de as id solicitud
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CirculoRegistral> findAllCirculosRegistralesActivosByIdSolicitud(
	    String as_userId, String as_localIp, String as_remoteIp, String as_idSolicitud
	)
	    throws B2BException;

	/**
	 * Find all circulos registrales by regional.
	 *
	 * @param acr_param de acr param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CirculoRegistral> findAllCirculosRegistralesByRegional(
	    CirculoRegistral acr_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find complemento direccion by tipo predio.
	 *
	 * @param as_idTipoPredio Variable de tipo String que contiene el id tipo predio
	 * @return el valor de la coleccion
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoEje> findAllComplementoDireccionByTipoPredio(String as_idTipoPredio)
	    throws B2BException;

	/**
	 * Find all constantes activos.
	 * @param ab_b de ab b
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Constantes> findAllConstantesActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all dependencia by ind visitas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DependenciaSNR> findAllDependenciaByIndVisitas()
	    throws B2BException;

	/**
	 * Find all Dominio naturaleza juridica activos.
	 * @param ab_b de ab b
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<DominioNaturalezaJuridica> findAllDominioNaturalezaJuridicaActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all etapas activo.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Etapa> findAllEtapasActivo(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all etapas activo ordenadas por id.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Etapa> findAllEtapasActivoById(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all fases activas.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Fases> findAllFasesActivas(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all grupo naturaleza juridica activos.
	 * @param ab_b de ab b
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<GrupoNaturalezaJuridica> findAllGrupoNaturalezaJuridicaActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta en base de datos todos los registros de la tabla que contengan los valores enviados como argumento.
	 *
	 * @param acs_parametros Argumento de tipo <code>CalidadSolicitante</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return Colección de tipo <code>CalidadSolicitante</code> con los resultados de la consulta.
	 * @throws B2BException Señala que se prodújo una excepción.
	 */
	public Collection<CalidadSolicitante> findAllInCalidadSolicitante(
	    CalidadSolicitante acs_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all max version naturaleza juridica.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<NaturalezaJuridica> findAllMaxVersionNaturalezaJuridica(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all max version tipo acto ejecutoria.
	 *
	 * @param as_tipoActo de as tipo acto
	 * @return el valor de int
	 * @throws B2BException de b 2 B exception
	 */
	public int findAllMaxVersionTipoActoEjecutoria(String as_tipoActo)
	    throws B2BException;

	/**
	 * Find all medida area activo.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<MedidaArea> findAllMedidaAreaActivo()
	    throws B2BException;

	/**
	 * Find all medida area nombres.
	 *
	 * @return el valor de map
	 * @throws B2BException de b 2 B exception
	 */
	public Map<String, String> findAllMedidaAreaNombres()
	    throws B2BException;

	/**
	 * Find all naturaleza juridica.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<NaturalezaJuridica> findAllNaturalezaJuridica(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all nombres comlumnas.
	 *
	 * @param as_nombreTabla identificador de la tabla a la que pertenecen las columnas
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<UserObjects> findAllNombresColumnas(
	    String as_nombreTabla, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all nombres tablas.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<UserObjects> findAllNombresTablas(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all oficinas origen.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<OficinaOrigen> findAllOficinasOrigen(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Retorna el valor del objeto de Collection del resultado de la consulta de las plantillas activas.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la IP local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la IP remota del usuario que realiza la operación.
	 * @return Colección de tipo <code>Plantilla</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Plantilla> findAllPlantillasActivo(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all proceso consulta activos.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<ProcesoConsulta> findAllProcesoConsultaActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all procesos activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Proceso> findAllProcesosActivos(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all procesos activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Proceso> findAllProcesosActivosById(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all regionales activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Regional> findAllRegionalesActivos(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all regionales activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Regional> findAllRegionalesActivosConCirculo(
	    String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all subprocesos activos.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Subproceso> findAllSubProcesosActivos(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all subprocesos version.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la dirección ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la dirección ip remota del usuario que realiza la aoperación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SubprocesoVersion> findAllSubprocesosVersion(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipo completitud proceso.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param as_idSolicitud de ls as idSolicitud
	 * @param as_idProceso de ls as idProceso
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AccCompletitudDocumental> findAllTipoCompletitudProceso(
	    String as_userId, String as_localIp, String as_remoteIp, String as_idSolicitud, String as_idProceso
	)
	    throws B2BException;

	/**
	 * Find all tipo criterio busqueda.
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CriteriosDeBusqueda> findAllTipoCriterioBusqueda()
	    throws B2BException;

	/**
	 * Find all tipo criterio busqueda activos.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CriteriosDeBusqueda> findAllTipoCriterioBusquedaActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipo oficina activo.
	 *
	 * @param as_s de as s
	 * @param ab_orderById de ab order by id
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoOficina> findAllTipoOficinaActivo(String as_s, boolean ab_orderById)
	    throws B2BException;

	/**
	 * Find all Tipo Rrr activos.
	 *
	 * @param ab_b de ab b
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoRrr> findAllTipoRrrActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipo tarifa registral activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoTarifaRegistral> findAllTipoTarifaRegistralActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipos acto.
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoActo> findAllTiposActo()
	    throws B2BException;

	/**
	 * Find all tipos acto activo data.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de map
	 * @throws B2BException de b 2 B exception
	 */
	public Map<String, TipoActo> findAllTiposActoActivoData(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all tipos acto activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_orderBy de ab order by
	 * @param ab_nuevaSolicitud de ab nueva solicitud
	 * @param ab_registro de ab registro
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoActo> findAllTiposActoActivos(
	    String as_userId, String as_localIp, String as_remoteIp, boolean ab_orderBy, boolean ab_nuevaSolicitud,
	    boolean ab_registro
	)
	    throws B2BException;

	/**
	 * Find all tipos acto remanente activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoActo> findAllTiposActoRemanenteActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipos acto remanente activos map.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de map
	 * @throws B2BException de b 2 B exception
	 */
	public Map<String, TipoActo> findAllTiposActoRemanenteActivosMap(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipos acto vis activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoActo> findAllTiposActoVisActivos(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all tipos anotacion activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoAnotacion> findAllTiposAnotacionActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipos causales activos.
	 *
	 * @param as_idturnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_bandera de ab bandera
	 * @param ab_notaDevolutiva de ab nota devolutiva
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoCausal> findAllTiposCausalesActivos(
	    String as_idturnoHistoria, String as_userId, String as_localIp, String as_remoteIp, boolean ab_bandera,
	    boolean ab_notaDevolutiva
	)
	    throws B2BException;

	/**
	 * Find all tipos causales activos por proc subproc.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_idSubproceso de as id subproceso
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoCausal> findAllTiposCausalesActivosPorProcSubproc(
	    String as_idProceso, String as_idSubproceso, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipos documentales.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ls_constante de ls constante
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoDocumental> findAllTiposDocumentales(
	    String as_userId, String as_localIp, String as_remoteIp, String ls_constante
	)
	    throws B2BException;

	/**
	 * Find all tipos estado solicitud activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoEstadoSolicitud> findAllTiposEstadoSolicitudActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all turno nir correcciones.
	 *
	 * @param as_s de as s
	 * @param as_idProceso corresponde al id del proceso
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String findAllTurnoNirCorrecciones(String as_s, String as_idProceso)
	    throws B2BException;

	/**
	 * Find all unidades de tiempo.
	 *
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UnidadTiempoVencimiento> findAllUnidadesDeTiempo(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all users.
	 *
	 * @param as_idUsuario de as id usuario
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Usuario> findAllUsers(String as_idUsuario)
	    throws B2BException;

	/**
	 * Find all users activos.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Usuario> findAllUsersActivos(
	    String as_idUsuario, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all users by etapa.
	 *
	 * @param as_etapa de as etapa
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Usuario> findAllUsersByEtapa(long as_etapa, boolean ab_b)
	    throws B2BException;

	/**
	 * Find all version by id.
	 *
	 * @param as_idAlertaN de as id alerta N
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<NaturalezaJuridica> findAllVersionById(String as_idAlertaN)
	    throws B2BException;

	/**
	 * Find aprobaciones.
	 *
	 * @param al_idEtapa de al id etapa
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<InteresadoDocumentoTipo> findAprobaciones(Long al_idEtapa)
	    throws B2BException;

	/**
	 * Find bitacora bloqueo by circulo matricula.
	 *
	 * @param abb_bb de abb bb
	 * @param ab_onlyBloqueantes de ab only bloqueantes
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<BitacoraBloqueo> findBitacoraBloqueoByCirculoMatricula(
	    BitacoraBloqueo abb_bb, boolean ab_onlyBloqueantes
	)
	    throws B2BException;

	/**
	 * Find by etapa devolucion 111.
	 *
	 * @param acad_param correspondiente al valor de acad param
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalAprobacionDevolucion> findByEtapaDevolucion111(
	    CausalAprobacionDevolucion acad_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find by habilitado.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_not de ab not
	 * @param ab_personaJuridica de ab persona juridica
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoRecepcion> findByHabilitado(
	    TipoRecepcion atr_parametros, boolean ab_not, boolean ab_personaJuridica, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find by id regional only.
	 *
	 * @param acr_param de acr param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CirculoRegistral> findByIdRegionalOnly(
	    CirculoRegistral acr_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find by id tipo documental.
	 *
	 * @param atd_param de atd param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo documental
	 * @throws B2BException de b 2 B exception
	 */
	public TipoDocumental findByIdTipoDocumental(
	    TipoDocumental atd_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find by id tipo oficina activo entidad exenta.
	 *
	 * @param as_idTipoOficina de as id tipo oficina
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExterna> findByIdTipoOficinaActivoEntidadExenta(
	    String as_idTipoOficina, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find by procedencia.
	 *
	 * @param atr_parametros de atr parametros
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoRecepcion> findByProcedencia(TipoRecepcion atr_parametros)
	    throws B2BException;

	/**
	 * Find calidad solicitante.
	 *
	 * @param ab_bool de ab bool
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitante(boolean ab_bool)
	    throws B2BException;

	/**
	 * Find calidad solicitante entrega.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitanteEntrega(
	    String as_userId, String as_localIp, String as_remoteIp, boolean ab_b
	)
	    throws B2BException;

	/**
	 * Método encargado de consultar las calidades del solicitante para el proceso de traslado.
	 *
	 * @return Colección de calidad solicitante con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitanteTraslado()
	    throws B2BException;

	/**
	 * Find campos consulta.
	 *
	 * @param ab_activo de ab activo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CamposConsulta> findCamposConsulta(
	    boolean ab_activo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find circulo registral by turno etapa.
	 *
	 * @param as_idTurno correspondiente al valor de as id turno
	 * @param as_idEtapa correspondiente al valor de as id etapa
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> findCirculoRegistralByTurnoEtapa(
	    String as_idTurno, String as_idEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find constantes by id.
	 *
	 * @param as_parametros de as parametros
	 * @return el valor de constantes
	 * @throws B2BException de b 2 B exception
	 */
	public Constantes findConstantesById(String as_parametros)
	    throws B2BException;

	/**
	 * Find coordenada.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Coordenada> findCoordenada()
	    throws B2BException;

	/**
	 * Find decision calificacion.
	 *
	 * @param at_t de at t
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String findDecisionCalificacion(Trazabilidad at_t)
	    throws B2BException;

	/**
	 * Find departament by id.
	 *
	 * @param ad_parametros de ad parametros
	 * @return el valor de departamento
	 * @throws B2BException de b 2 B exception
	 */
	public Departamento findDepartamentById(Departamento ad_parametros)
	    throws B2BException;

	/**
	 * Find departaments.
	 *
	 * @param ad_parametros de ad parametros
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Departamento> findDepartaments(Departamento ad_parametros)
	    throws B2BException;

	/**
	 * Método encargado de consultar la información del acto.
	 *
	 * @param as_idActo de as id acto
	 * @return Mapa que contiene la información del acto consultado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Object> findDetalleActo(String as_idActo)
	    throws B2BException;

	/**
	 * Retorna el valor del objeto de Collection del resultado de la consulta de los motivos diferentes por id proceso y id etapa.
	 *
	 * @param as_idProceso Objeto de tipo <code>String</code> que indica el id del proceso.
	 * @param al_idEtapa Parametro de tipo <code>long</code> que indica el id de la etapa.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la IP local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la IP remota del usuario que realiza la operación.
	 * @return Colección de tipo <code>MotivoTramite</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MotivoTramite> findDistinctByIdProcesoAndIdEtapa(
	    String as_idProceso, long al_idEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find entidad externa by nombre like.
	 *
	 * @param as_idTipoEntidad id del tipo entidad
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AccEntidadExterna> findEntidadExternaByNombreLike(String as_idTipoEntidad)
	    throws B2BException;

	/**
	 * Find all Entidades externas activos.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AccEntidadExterna> findEntidadesExternasActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find estado actividad.
	 *
	 * @param as_estado de as estado
	 * @return el valor de estado actividad
	 * @throws B2BException de b 2 B exception
	 */
	public EstadoActividad findEstadoActividad(String as_estado)
	    throws B2BException;

	/**
	 * Find estado actividad activo.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<EstadoActividad> findEstadoActividadActivo(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find estado anotacion.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<EstadoAnotacion> findEstadoAnotacion()
	    throws B2BException;

	/**
	 * Find estado predios.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<EstadoPredio> findEstadoPredios()
	    throws B2BException;

	/**
	 * Find generos.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<InteresadoNaturalGenero> findGeneros()
	    throws B2BException;

	/**
	 * Find if motivo is reasignacion.
	 *
	 * @param lt_temp de lt temp
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String findIfMotivoIsReasignacion(Trazabilidad lt_temp)
	    throws B2BException;

	/**
	 * Método encargado de consultar la información de la anotación definitiva.
	 *
	 * @param aap_anotacion Objeto que contiene la información de la anotación.
	 * @return Mapa que contiene la información de la anotación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Object> findInfoAnotacionAcc(AnotacionPredio aap_anotacion)
	    throws B2BException;

	/**
	 * Método encargado de consultar la información de la anotación definitiva.
	 *
	 * @param aap_anotacion Objeto que contiene la información de la anotación.
	 * @return Mapa que contiene la información de la anotación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Object> findInfoAnotacionBng(AnotacionPredio aap_anotacion)
	    throws B2BException;

	/**
	 * Find interesado documento tipo by id.
	 *
	 * @param ate_parametros de ate parametros
	 * @return el valor de interesado documento tipo
	 * @throws B2BException de b 2 B exception
	 */
	public InteresadoDocumentoTipo findInteresadoDocumentoTipoById(InteresadoDocumentoTipo ate_parametros)
	    throws B2BException;

	/**
	 * Find letra eje.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<LetraEje> findLetraEje()
	    throws B2BException;

	/**
	 * Find libro antiguo sistema.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<LibroAntiguoSistema> findLibroAntiguoSistema()
	    throws B2BException;

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de LINEA DE PRODUCCION para una etapa.
	 *
	 * @param al_etapa Argumento de tipo <code>long</code> que contiene la etapa a consultar.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<LineaProduccion> findLineasProduccionByEtapa(
	    long al_etapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de LINEA DE PRODUCCION para una nomenclatura.
	 *
	 * @param as_nomenclatura Argumento de tipo <code>String</code> que contiene la nomenclatura a consultar.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return devuelve el valor de LineaProduccion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public LineaProduccion findLineasProduccionByNomenclatura(
	    String as_nomenclatura, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find matricula definitiva.
	 *
	 * @param aps_ps de aps ps
	 * @return el valor de predio segregado
	 * @throws B2BException de b 2 B exception
	 */
	public PredioSegregado findMatriculaDefinitiva(PredioSegregado aps_ps)
	    throws B2BException;

	/**
	 * Find medida area by id.
	 *
	 * @param as_arg de as arg
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<MedidaArea> findMedidaAreaById(String as_arg)
	    throws B2BException;

	/**
	 * Retorna el valor del objeto de Collection de motivos tramite por su id proceso, id subproceso y id etapa.
	 *
	 * @param as_idProceso Objeto de tipo <code>String</code> que indica el id del subproceso..
	 * @param as_idSubProceso de as id sub proceso
	 * @param al_idEtapa Parametro de tipo <code>long</code> que indica el id de la etapa.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la IP local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la IP remota del usuario que realiza la operación.
	 * @return Colección de tipo <code>MotivoTramite</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MotivoTramite> findMotivoByIdProcesoIdSubProcesoAndIdEtapa(
	    String as_idProceso, String as_idSubProceso, long al_idEtapa, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find motivos by etapa.
	 *
	 * @param as_etapa de as etapa
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param ab_isRepConstancia de ab is rep constancia
	 * @param ab_isCalificacion de ab is calificacion
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<MotivoTramite> findMotivosByEtapa(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion
	)
	    throws B2BException;

	/**
	 * Find motivos by etapa.
	 *
	 * @param as_etapa de as etapa
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param ab_isRepConstancia de ab is rep constancia
	 * @param ab_isCalificacion de ab is calificacion
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<MotivoTramite> findMotivosByEtapa(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion,
	    List<Map<String, Object>> lmso_actos
	)
	    throws B2BException;

	/**
	 * Find motivos by etapa.
	 *
	 * @param as_etapa de as etapa
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param ab_isRepConstancia de ab is rep constancia
	 * @param ab_isCalificacion de ab is calificacion
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<MotivoTramite> findMotivosByEtapa105(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion
	)
	    throws B2BException;

	/**
	 * Find municipios.
	 *
	 * @param am_parametros de am parametros
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Municipio> findMunicipios(Municipio am_parametros)
	    throws B2BException;

	/**
	 * Find municipios por circulo registral.
	 *
	 * @param as_parametros de as parametros
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Municipio> findMunicipiosByCirculo(String as_parametros)
	    throws B2BException;

	/**
	 * Find naturaleza juridica 0841 and 0842.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<NaturalezaJuridica> findNaturalezaJuridica0841and0842()
	    throws B2BException;

	/**
	 * Find naturaleza juridica by id.
	 *
	 * @param as_idNaturalezJuridica de as id naturalez juridica
	 * @return el valor de naturaleza juridica
	 * @throws B2BException de b 2 B exception
	 */
	public NaturalezaJuridica findNaturalezaJuridicaById(String as_idNaturalezJuridica)
	    throws B2BException;

	/**
	 * Find oficina origen.
	 *
	 * @param oficina de oficina
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<OficinaOrigen> findOficinaOrigen(OficinaOrigen oficina)
	    throws B2BException;

	/**
	 * Find oficina origen by id.
	 *
	 * @param as_idOficinaOrigen de as id oficina origen
	 * @return el valor de oficina origen
	 * @throws B2BException de b 2 B exception
	 */
	public OficinaOrigen findOficinaOrigenById(String as_idOficinaOrigen)
	    throws B2BException;

	/**
	 * Find paises.
	 *
	 * @param ab_activo de ab activo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Pais> findPaises(boolean ab_activo, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find predio registro.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<PredioRegistro> findPredioRegistro()
	    throws B2BException;

	/**
	 * Find predio registro by matricula circulo.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public PredioRegistro findPredioRegistroByMatriculaCirculo(
	    String as_idCirculo, Long al_idMatricula, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find procesos.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Proceso> findProcesos(boolean ab_b)
	    throws B2BException;

	/**
	 * Find procesos automaticos.
	 *
	 * @param ab_activo de ab activo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<ProcesoAutomatico> findProcesosAutomaticos(
	    boolean ab_activo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find regional by id.
	 *
	 * @param ar_parametros de ar parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de regional
	 * @throws B2BException de b 2 B exception
	 */
	public Regional findRegionalById(Regional ar_parametros, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find resultado consulta.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<ResultadoConsulta> findResultadoConsulta(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find rrr.
	 *
	 * @param as_idActo de as id acto
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<InteresadoDocumentoTipo> findRrr(String as_idActo)
	    throws B2BException;

	/**
	 * Find rrr by id.
	 *
	 * @param as_idDoc de as id doc
	 * @return el valor de interesado documento tipo
	 * @throws B2BException de b 2 B exception
	 */
	public InteresadoDocumentoTipo findRrrById(String as_idDoc)
	    throws B2BException;

	/**
	 * Find solicitud by id.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return el valor de solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Solicitud findSolicitudById(String as_idSolicitud)
	    throws B2BException;

	/**
	 * Find subprocesos.
	 *
	 * @param ab_activo de ab activo
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Subproceso> findSubprocesos(boolean ab_activo)
	    throws B2BException;

	/**
	 * Find subprocesos activos by proceso.
	 *
	 * @param as_subproceso de as subproceso
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Subproceso> findSubprocesosActivosByProceso(
	    Subproceso as_subproceso, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find subprocesos by proceso.
	 *
	 * @param as_subproceso de as subproceso
	 * @param ab_orden de ab orden
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Subproceso> findSubprocesosByProceso(
	    Subproceso as_subproceso, boolean ab_orden, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find subprocesos by proceso solicitud cert.
	 *
	 * @param as_subproceso de as subproceso
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Subproceso> findSubprocesosByProcesoSolicitudCert(
	    Subproceso as_subproceso, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipificacion turno.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de string
	 * @throws B2BException
	 */
	public String findTipificacionTurno(String as_idTurno)
	    throws B2BException;

	/**
	 * Find all acto by id.
	 *
	 * @param as_idTipoActo de as idTipoActo
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoActo> findTipoActoById(String as_idTipoActo)
	    throws B2BException;

	/**
	 * Find tipo adquisicion.
	 *
	 * @param as_s de as s
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoAdquisicion> findTipoAdquisicion(String as_s)
	    throws B2BException;

	/**
	 * Find tipo adquisicion By Id.
	 *
	 * @param as_s de as s
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public TipoAdquisicion findTipoAdquisicionById(String as_s)
	    throws B2BException;

	/**
	 * Find tipo documento.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<InteresadoDocumentoTipo> findTipoDocumento()
	    throws B2BException;

	/**
	 * Find tipo documento activo.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<InteresadoDocumentoTipo> findTipoDocumentoActivo()
	    throws B2BException;

	/**
	 * Find tipo documento publico.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublico()
	    throws B2BException;

	/**
	 * Find tipo documento publico.
	 *
	 * @param as_s de as s
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublico(String as_s)
	    throws B2BException;

	/**
	 * Find tipo eje.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoEje> findTipoEje()
	    throws B2BException;

	/**
	 * Find tipo eje by id.
	 *
	 * @param ate_parametros de ate parametros
	 * @return el valor de tipo eje
	 * @throws B2BException de b 2 B exception
	 */
	public TipoEje findTipoEjeById(TipoEje ate_parametros)
	    throws B2BException;

	/**
	 * Find tipo eje by tipo predio.
	 *
	 * @param as_tipoPredio de as tipo predio
	 * @return el valor de tipo eje
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoEje> findTipoEjeByTipoPredio(String as_tipoPredio)
	    throws B2BException;

	/**
	 * Find Tipo Entidad.
	 *
	 * @param aod_od de aod od
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoEntidad> findTipoEntidad(Apertura aod_od)
	    throws B2BException;

	/**
	 * Find tipo entidad.
	 *
	 * @param ab_activo de ab_activo
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoEntidad> findTipoEntidad(boolean ab_activo)
	    throws B2BException;

	/**
	 * Find tipo entidad by id.
	 *
	 * @param ate_te de ate te
	 * @return el valor de tipo entidad
	 * @throws B2BException de b 2 B exception
	 */
	public TipoEntidad findTipoEntidadById(TipoEntidad ate_te)
	    throws B2BException;

	/**
	 * Find tipo entidad externa.
	 *
	 * @param as_idTipoEntidad id del tipo entidad
	 * @param ab_orden de ab orden
	 * @param ab_activo de ab activo
	 * @return el valor de tipo entidad
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<AccEntidadExterna> findTipoEntidadExterna(
	    String as_idTipoEntidad, boolean ab_orden, boolean ab_activo
	)
	    throws B2BException;

	/**
	 * Find tipo oficina.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoOficina> findTipoOficina()
	    throws B2BException;

	/**
	 * Find tipo oficina.
	 *
	 * @param aod_od de aod od
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoOficina> findTipoOficina(Documento aod_od)
	    throws B2BException;

	/**
	 * Find tipo oficina by id.
	 *
	 * @param ato_to de ato to
	 * @return el valor de tipo oficina
	 * @throws B2BException de b 2 B exception
	 */
	public TipoOficina findTipoOficinaById(TipoOficina ato_to)
	    throws B2BException;

	/**
	 * Find tipo persona.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoPersona> findTipoPersona()
	    throws B2BException;

	/**
	 * Find tipo predio.
	 *
	 * @param ab_activo de ab activo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<PredioTipo> findTipoPredio(
	    boolean ab_activo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo RRR.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoRrr> findTipoRRR()
	    throws B2BException;

	/**
	 * Find tipo reparto by etapa.
	 *
	 * @param al_etapa de la etapa
	 * @return el valor de string
	 * @throws B2BException de b 2 B exception
	 */
	public String findTipoRepartoByEtapa(long al_etapa)
	    throws B2BException;

	/**
	 * Find tipo requiere matricula activos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoRequiereMatricula> findTipoRequiereMatriculaActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo requiere matricula registro.
	 *
	 * @param ab_requiereMatricula de ab requiere matricula
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoRequiereMatricula> findTipoRequiereMatriculaRegistro(
	    boolean ab_requiereMatricula, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo uso suelo.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoUsoSuelo> findTipoUsoSuelo()
	    throws B2BException;

	/**
	 * Find tipos actos codigo 4 Y 5 digitos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_orderBy de ab order by
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<TipoActo> findTiposActosCodigo4Y5Digitos(
	    String as_userId, String as_localIp, String as_remoteIp, boolean ab_orderBy
	)
	    throws B2BException;

	/**
	 * Find turno historia by id turno.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @return el valor de turno historia
	 * @throws B2BException de b 2 B exception
	 */
	public TurnoHistoria findTurnoHistoriaByIdTurno(TurnoHistoria ath_turnoHistoria)
	    throws B2BException;

	/**
	 * Find turno historia byid.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @return el valor de turno historia
	 * @throws B2BException de b 2 B exception
	 */
	public TurnoHistoria findTurnoHistoriaByid(TurnoHistoria ath_turnoHistoria)
	    throws B2BException;

	/**
	 * Find turno historia byid.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @return el valor de turno historia
	 * @throws B2BException de b 2 B exception
	 */
	public TurnoHistoria findTurnoHistoriaByid(Long al_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Find turno nombre etapa by id.
	 *
	 * @param at_turno de at turno
	 * @param as_userId de as user id
	 * @return el valor de turno
	 * @throws B2BException de b 2 B exception
	 */
	public Turno findTurnoNombreEtapaById(Turno at_turno, String as_userId)
	    throws B2BException;

	/**
	 * Método de consulta de la url de catedra de la última opción visitada
	 * @param as_urlBachue con la url de la opcion a buscar
	 * @param as_userId con el usuario de la transacción
	 * @param localIpAddress con la ip local de la transacción
	 * @param remoteIpAddress con la ip remota de la transacción
	 * @return de  tipo String con la respuesta de la consulta
	 * @throws B2BException en caso de error
	 */
	public String findUrlCatedra(String as_urlBachue, String as_userId, String localIpAddress, String remoteIpAddress)
	    throws B2BException;

	/**
	 * Find user active by id.
	 *
	 * @param ate_parametros de ate parametros
	 * @return el valor de usuario
	 * @throws B2BException de b 2 B exception
	 */
	public Usuario findUserActiveById(Usuario ate_parametros)
	    throws B2BException;

	/**
	 * Find user by id.
	 *
	 * @param ate_parametros de ate parametros
	 * @return el valor de usuario
	 * @throws B2BException de b 2 B exception
	 */
	public Usuario findUserById(Usuario ate_parametros)
	    throws B2BException;

	/**
	 * Busca usuarios por un circulo y rol especificos que se encuentren activos.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param as_idRol de as id rol
	 * @param ab_apoyoNacional de ab apoyo nacional
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Usuario> findUsersByRolOripActivos(
	    String as_idCirculo, String as_idRol, boolean ab_apoyoNacional, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find users linea produccion by rol orip activos.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param as_idRol de as id rol
	 * @param ls_idLineaProduccion de ls id linea produccion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Usuario> findUsersLineaProduccionByRolOripActivos(
	    String as_idCirculo, String as_idRol, String ls_idLineaProduccion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find veredas.
	 *
	 * @param am_parametros de am parametros
	 * @param ab_param de ab param
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<Vereda> findVeredas(Vereda am_parametros, boolean ab_param)
	    throws B2BException;

	/**
	 * Calcular fecha dias vencimiento restantes.
	 *
	 * @param afvtui_parametros de afvtui parametros
	 * @return el valor de date
	 * @throws B2BException de b 2 B exception
	 */
	public long funcionCalcularDiasFechas(FechaVenceTerminosUI afvtui_parametros)
	    throws B2BException;

	/**
	 * Generar expediente.
	 *
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param at_turno Objeto que contiene los datos del turno.
	 * @return String que contiene el código de expediente generado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String generarExpediente(String as_userId, String as_remoteIp, String as_localIp, Turno at_turno)
	    throws B2BException;

	/**
	 * Insert user.
	 *
	 * @param au_usuario de au usuario
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void insertUser(Usuario au_usuario, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Listar tipos criterios busqueda.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param as_idTipoCriterioBusqueda de as id tipo criterio busqueda
	 * @param as_idProceso de as id proceso
	 * @param as_procesoBusqueda de as proceso busqueda
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CriteriosDeBusqueda> listarTiposCriteriosBusqueda(
	    String as_userId, String as_localIp, String as_remoteIp, String as_idTipoCriterioBusqueda, String as_idProceso,
	    String as_procesoBusqueda
	)
	    throws B2BException;

	/**
	 * Find turno historia id turno.
	 *
	 * @param ath_turnoHistoria de ath turno
	 * @return el valor de turno historia
	 * @throws B2BException de b 2 B exception
	 */
	public TurnoHistoria obtenerAnteriorTurnoHistoria(TurnoHistoria ath_turnoHistoria)
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
	 * Obtener causales aprobacion devolucion activos.
	 *
	 * @param acad_param de acad param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<CausalAprobacionDevolucion> obtenerCausalesAprobacionDevolucionActivos(
	    CausalAprobacionDevolucion acad_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener todos los ID_CONSTANTE que coincidan con un patrón y su el valor de la columna CARACTER concida sea
	 * igual a un valor.
	 *
	 * @param as_IdLike Patrón sobre el cual se realizará búsqueda tipo like en en campo ID_CONSTANTE
	 * @param as_caracter Valor de coincidencia del campo CARACTER
	 * @return Listado de ID_CONSTANTE
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> obtenerIdConstanesPorCaracterIdLikeCaracter(String as_IdLike, String as_caracter)
	    throws B2BException;

	/**
	 * Update caracter.
	 *
	 * @param as_id de as id
	 * @param as_caracter de as caracter
	 * @param as_userId de as user id
	 * @throws B2BException de b 2 B exception
	 */
	public void updateCaracter(String as_id, String as_caracter, String as_userId)
	    throws B2BException;

	/**
	 * Update user.
	 *
	 * @param au_usuario de au usuario
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void updateUser(Usuario au_usuario, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Validar Tipo Documental.
	 *
	 * @param as_idProceso de as idProceso
	 * @param acacd_salvar de acacd salvar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException de b 2 B exception
	 */
	public void validarTipoDocumental(
	    String as_idProceso, Collection<AccCompletitudDocumental> acacd_salvar, String as_userId, String as_localIp,
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

	/**
	 * Verificar proceso traslado por id turno.
	 *
	 * @param as_idTurno correspondiente al valor de  id turno
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean verificarProcesoTrasladoPorIdTurno(String as_idTurno)
	    throws B2BException;
}
