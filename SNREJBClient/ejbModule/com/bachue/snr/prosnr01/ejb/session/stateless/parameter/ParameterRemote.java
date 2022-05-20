package com.bachue.snr.prosnr01.ejb.session.stateless.parameter;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.registro.Archivo;
import com.bachue.snr.prosnr01.model.registro.TipoDocumentalActo;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaConvenio;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTramite;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraProceso;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;
import com.bachue.snr.prosnr01.model.sdb.acc.Desborde;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SalarioMinimo;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoPago;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarifaRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarjetaApoderado;
import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.OpcionEtapa;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.aut.RolOpcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.col.DominioRrr;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoNaturalGenero;
import com.bachue.snr.prosnr01.model.sdb.col.ParteInteresada;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.ActividadEconomica;
import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposCertificado;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.Catastro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalMayorValor;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalNegacionCopias;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalReimpresion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoActAdmin;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoCatastro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoFestivo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.CondicionTarifa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.DependenciaSNR;
import com.bachue.snr.prosnr01.model.sdb.pgn.DetalleProcesoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoActividad;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoNupre;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.FestivoNacional;
import com.bachue.snr.prosnr01.model.sdb.pgn.Gobernacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.InstanciaConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaComunicacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaNotificacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoAutomatico;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.RangoCuantia;
import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;
import com.bachue.snr.prosnr01.model.sdb.pgn.Reportes;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TarifaFija;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoCondicion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoEjecutoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoProhibicion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoCanalOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoCriterioBusquedaPGN;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDecisionRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEstadoLiquidacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoIntegracionGobernacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOperacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;
import com.bachue.snr.prosnr01.model.sdb.pgn.UsuarioLinea;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.CirculoOrigenDestino;
import com.bachue.snr.prosnr01.model.sdb.png.ComunidadesEtnicas;
import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;
import com.bachue.snr.prosnr01.model.sdb.png.DominioNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.EntidadesAlerta;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.ProcesoCanal;
import com.bachue.snr.prosnr01.model.sdb.png.TarifaAlerta;
import com.bachue.snr.prosnr01.model.sdb.png.TipoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.sdb.png.TipoNotificacion;
import com.bachue.snr.prosnr01.model.sdb.png.UsuarioEtapa;

import com.bachue.snr.prosnr02.model.pgn.ReglaNegocio;
import com.bachue.snr.prosnr02.model.pgn.TopologiaRegla;

import com.bachue.snr.prosnr04.model.pgn.CanalOrigenServicio;
import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;
import com.bachue.snr.prosnr04.model.pgn.MedioRecaudo;
import com.bachue.snr.prosnr04.model.pgn.MensajeRespuesta;
import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudo;
import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo;
import com.bachue.snr.prosnr04.model.pgn.SucursalCanalOrigenServicio;
import com.bachue.snr.prosnr04.model.pgn.TipoRecaudo;

import com.bachue.snr.prosnr16.model.sdb.pgn.Canal;
import com.bachue.snr.prosnr16.model.sdb.pgn.Estados;
import com.bachue.snr.prosnr16.model.sdb.pgn.Origen;
import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;
import com.bachue.snr.prosnr16.model.sdb.pgn.Reintentos;

import java.io.IOException;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ParameterRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ParameterRemote
{
	/**
	 * Retorna el valor de caracter constante.
	 *
	 * @param as_constante de as constante
	 * @return el valor de caracter constante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, String> getCaracterConstante(String as_constante)
	    throws B2BException;

	/**
	 * Método encargado de consultar los circulos destino para un circulo origen especifico.
	 *
	 * @param as_idCirculoDestino Corresponde al id del circulo origen.
	 * @return Mapa que contiene los resultados de la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, String> getCirculosDestinoPorOrigen(String as_idCirculoDestino)
	    throws B2BException;

	/**
	 * Accion click archivo.
	 *
	 * @param aa_archivo de aa archivo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Archivo> accionClickArchivo(Archivo aa_archivo)
	    throws B2BException;

	/**
	 * Accion regresar log.
	 *
	 * @param aa_archivo de aa archivo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Archivo> accionRegresarLog(Archivo aa_archivo)
	    throws B2BException;

	/**
	 * Méotodo que retorna una colección con todos los documentos aportados de una solicitud.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id de la solicitud.
	 * @param as_userId de as user id
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccCompletitudDocumental> buscarDocumentosAportados(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar lista archivos.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Archivo> cargarListaArchivos()
	    throws B2BException;

	/**
	 * Descargar log.
	 *
	 * @param aa_archivo de aa archivo
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] descargarLog(Archivo aa_archivo)
	    throws B2BException;

	/**
	 * Ejecutar proc nocturno.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void ejecutarProcNocturno()
	    throws B2BException;

	/**
	 * Filtro bitacora proceso.
	 *
	 * @param as_descripcion de as descripcion
	 * @param ad_fechaDesde de ad fecha desde
	 * @param ld_fechaHasta de ld fecha hasta
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<BitacoraProceso> filtroBitacoraProceso(
	    String as_descripcion, Date ad_fechaDesde, Date ld_fechaHasta, boolean ab_b
	)
	    throws B2BException;

	/**
	 * Find acc entidad externa by id.
	 *
	 * @param aaee_aee de aaee aee
	 * @return el valor de canal
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccEntidadExterna findAccEntidadExternaById(AccEntidadExterna aaee_aee)
	    throws B2BException;

	/**
	 * Find acc entidad externa convenio by id.
	 *
	 * @param aaeec_aeec de aaeec aeec
	 * @return el valor de AccEntidadExternaConvenio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccEntidadExternaConvenio findAccEntidadExternaConvenioById(AccEntidadExternaConvenio aaeec_aeec)
	    throws B2BException;

	/**
	 * Find actividad economica by id.
	 *
	 * @param aae_param de aae param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de ActividadEconomica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ActividadEconomica findActividadEconomicaById(
	    ActividadEconomica aae_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de UnidadTiempoVencimiento.
	 *
	 * @param as_actuaciones de as actuaciones
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de actuaciones administrativas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TagPlantillaResolucion findActuacionesAdministrativasById(
	    String as_actuaciones, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find alerta naturaleza juridica by id.
	 *
	 * @param ama_ma de ama ma
	 * @return el valor de alerta naturaleza juridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaNaturalezaJuridica findAlertaNaturalezaJuridicaById(AlertaNaturalezaJuridica ama_ma)
	    throws B2BException;

	/**
	 * Find alerta tramite by id.
	 *
	 * @param acad_cad de acad cad
	 * @return el valor de alerta tramite
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTramite findAlertaTramiteById(AlertaTramite acad_cad)
	    throws B2BException;

	/**
	 * Find all acc entidad externa convenio.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExternaConvenio> findAllAccEntidadExternaConvenio(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all acc entidad externas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExterna> findAllAccEntidadExternas()
	    throws B2BException;

	/**
	 * Find all actividad economica.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ActividadEconomica> findAllActividadEconomica()
	    throws B2BException;

	/**
	 * Find all actividad economica activo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ActividadEconomica> findAllActividadEconomicaActivo()
	    throws B2BException;

	/**
	 * Find all actuaciones administrativas.
	 *
	 * @param as_idUsuario de as idUsuario
	 * @param as_localIp de as localIp
	 * @param as_remoteIp de as remoteIp
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TagPlantillaResolucion> findAllActuacionesAdministrativas(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Realiza la busqueda en la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION mediante
	 * plantilla y tag.
	 *
	 * @param as_plantilla            parametro de busqueda
	 * @param as_tag            parametro de busqueda
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return retorna el objeto a consultar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TagPlantillaResolucion findAllActuacionesAdministrativasByIdPlantillaTag(
	    String as_plantilla, String as_tag, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * findAllActualizarVersionById.
	 *
	 * @param as_idTipoActo de as id tipo acto
	 * @return el valor Version
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActo findAllActualizarVersionById(String as_idTipoActo)
	    throws B2BException;

	/**
	 * Find all administracion componentes.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Componente> findAllAdministracionComponentes()
	    throws B2BException;

	/**
	 * Find administracion componentes by id.
	 *
	 * @param aac_ac de aac ac
	 * @return el valor de calidad solicitante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Componente findAllAdministracionComponentesById(Componente aac_ac)
	    throws B2BException;

	/**
	 * Find all administracion componentes permisos.
	 *
	 * @param as_s de as s
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Componente> findAllAdministracionComponentesPermisos(String as_s)
	    throws B2BException;

	/**
	 * Find all alerta naturaleza juridica.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AlertaNaturalezaJuridica> findAllAlertaNaturalezaJuridica()
	    throws B2BException;

	/**
	 * Find all alerta tramite.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AlertaTramite> findAllAlertaTramite()
	    throws B2BException;

	/**
	 * Find all apoderados.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoTarjetaApoderado> findAllApoderados()
	    throws B2BException;

	/**
	 * Find all campos certificado.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposCertificado> findAllCamposCertificado()
	    throws B2BException;

	/**
	 * Find all campos consulta.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposConsulta> findAllCamposConsulta()
	    throws B2BException;

	/**
	 * Find all campos correccion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposCorreccion> findAllCamposCorreccion()
	    throws B2BException;

	/**
	 * Find all campos criterio Búsqueda.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposConsulta> findAllCamposCriteriosBusqueda(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all campos criterio búsqueda.
	 *
	 * @param as_tipoCriterioBusquedaActual de as tipo criterio busqueda actual
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposConsulta> findAllCamposCriteriosBusquedaByTipoCriterioBusqueda(
	    String as_tipoCriterioBusquedaActual
	)
	    throws B2BException;

	/**
	 * Find all canal.
	 *
	 * @param ab_accion de ab accion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Canal> findAllCanal(boolean ab_accion)
	    throws B2BException;

	/**
	 * Find all canal origen servicio.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CanalOrigenServicio> findAllCanalOrigenServicio()
	    throws B2BException;

	/**
	 * Find all catastro.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Catastro> findAllCatastro()
	    throws B2BException;

	/**
	 * Find all causal aprobacion devolucion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalAprobacionDevolucion> findAllCausalAprobacionDevolucion()
	    throws B2BException;

	/**
	 * Find all causal correccion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalCorreccion> findAllCausalCorreccion()
	    throws B2BException;

	/**
	 * Find all causal mayor valor.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalMayorValor> findAllCausalMayorValor()
	    throws B2BException;

	/**
	 * Find all causal mayor valor.
	 *
	 * @param al_idCausalMayorValor de al id causal mayor valor
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalMayorValor findAllCausalMayorValorById(long al_idCausalMayorValor)
	    throws B2BException;

	/**
	 * Método para consultar un registro de Causal Negacion Copias.
	 *
	 * @return CausalNegacionCopias con los datos solicitados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalNegacionCopias> findAllCausalNegacionCopias()
	    throws B2BException;

	/**
	 * Find all causal negacion copias.
	 *
	 * @param as_idTipoDocumental de as id tipo documental
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalNegacionCopias findAllCausalNegacionCopiasById(String as_idTipoDocumental)
	    throws B2BException;

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla SDB_ACC_ENTIDAD_EXTERNA.
	 *
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Collection<CausalRechazoRecurso> findAllCausalRechazoRecurso(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all causal reimpresion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalReimpresion> findAllCausalReimpresion()
	    throws B2BException;

	/**
	 * Find all circulo act admin.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoActAdmin> findAllCirculoActAdmin()
	    throws B2BException;

	/**
	 * Find all circulo catastro.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoCatastro> findAllCirculoCatastro()
	    throws B2BException;

	/**
	 * Find all circulo festivo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoFestivo> findAllCirculoFestivo()
	    throws B2BException;

	/**
	 * Find all circulo origen destino.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoOrigenDestino> findAllCirculoOrigenDestino()
	    throws B2BException;

	/**
	 * Find all circulos.
	 *
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> findAllCirculos(
	    boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all componente activo.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Componente> findAllComponenteActivo(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all comunidades etnicas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ComunidadesEtnicas> findAllComunidadesEtnicas()
	    throws B2BException;

	/**
	 * Find all condicion tarifa.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CondicionTarifa> findAllCondicionTarifa()
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
	 * Find all constants.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Constantes> findAllConstantsCYN()
	    throws B2BException;

	/**
	 * Find all consultas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Consultas> findAllConsultas()
	    throws B2BException;

	/**
	 * Find all coordenada.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Coordenada> findAllCoordenada()
	    throws B2BException;

	/**
	 * Find all coordenadas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Coordenada> findAllCoordenadaActivo()
	    throws B2BException;

	/**
	 * Find all departamentos.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Departamento> findAllDepartamentos()
	    throws B2BException;

	/**
	 * Find all dependencia snr.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DependenciaSNR> findAllDependenciaSNR()
	    throws B2BException;

	/**
	 * Find all desbordes.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Desborde> findAllDesbordes(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all detalle proceso consulta.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleProcesoConsulta> findAllDetalleProcesoConsulta()
	    throws B2BException;

	/**
	 * Find all dominio RRR.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DominioRrr> findAllDominioRRR()
	    throws B2BException;

	/**
	 * Find all dominios naturaleza juridica.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DominioNaturalezaJuridica> findAllDominiosNaturalezaJuridica(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all entidad recaudadora.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadRecaudadora> findAllEntidadRecaudadora()
	    throws B2BException;

	/**
	 * Find all entidades alerta.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadesAlerta> findAllEntidadesAlerta()
	    throws B2BException;

	/**
	 * Find all estado actividad.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EstadoActividad> findAllEstadoActividad(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all estado anotacion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EstadoAnotacion> findAllEstadoAnotacion()
	    throws B2BException;

	/**
	 * Find all estado nupre.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EstadoNupre> findAllEstadoNupre()
	    throws B2BException;

	/**
	 * Find all estado predio.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EstadoPredio> findAllEstadoPredio()
	    throws B2BException;

	/**
	 * Find all estado registro.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EstadoRegistro> findAllEstadoRegistro()
	    throws B2BException;

	/**
	 * Find all estados.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Estados> findAllEstados()
	    throws B2BException;

	/**
	 * Find all etapa opciones.
	 *
	 * @param ao_opcion de ao opcion
	 * @return el valor de opcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Opcion findAllEtapaOpciones(Opcion ao_opcion)
	    throws B2BException;

	/**
	 * Find all etapas.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Etapa> findAllEtapas(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all fases.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Fases> findAllFases(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all festivo nacional.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<FestivoNacional> findAllFestivoNacional(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all gobernacion.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Gobernacion> findAllGobernacion(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all grupo naturaleza juridica.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<GrupoNaturalezaJuridica> findAllGrupoNaturalezaJuridica()
	    throws B2BException;

	/**
	 * Find all instancia consulta.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<InstanciaConsulta> findAllInstanciaConsulta(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all interesado documento tipos.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<InteresadoDocumentoTipo> findAllInteresadoDocumentoTipos(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all interesado natural genero.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<InteresadoNaturalGenero> findAllInteresadoNaturalGenero()
	    throws B2BException;

	/**
	 * Find all letra eje.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LetraEje> findAllLetraEje()
	    throws B2BException;

	/**
	 * Find all letra eje.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LetraEje> findAllLetraEjeActivo()
	    throws B2BException;

	/**
	 * Find all libro antiguo sistema.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LibroAntiguoSistema> findAllLibroAntiguoSistema(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all libro testamento.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LibroTestamento> findAllLibroTestamento()
	    throws B2BException;

	/**
	 * Find all lineas produccion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LineaProduccion> findAllLineasProduccion()
	    throws B2BException;

	/**
	 * Find all medida area.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MedidaArea> findAllMedidaArea()
	    throws B2BException;

	/**
	 * Find all medio recaudo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MedioRecaudo> findAllMedioRecaudo()
	    throws B2BException;

	/**
	 * Find all mensaje respuesta.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MensajeRespuesta> findAllMensajeRespuesta()
	    throws B2BException;

	/**
	 * Find all motivo tramite.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MotivoTramite> findAllMotivoTramite()
	    throws B2BException;

	/**
	 * Find all municipios.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Municipio> findAllMunicipios()
	    throws B2BException;

	/**
	 * Find all naturaleza juridica.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<NaturalezaJuridica> findAllNaturalezaJuridica()
	    throws B2BException;

	/**
	 * Find all numeracion oficios circulo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<NumeracionOficiosCirculo> findAllNumeracionOficiosCirculo()
	    throws B2BException;

	/**
	 * Find all oficina origen.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<OficinaOrigen> findAllOficinaOrigen()
	    throws B2BException;

	/**
	 * Find all opcion.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Opcion> findAllOpcion(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all opcion etapa.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<OpcionEtapa> findAllOpcionEtapa()
	    throws B2BException;

	/**
	 * Find all origen.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Origen> findAllOrigen()
	    throws B2BException;

	/**
	 * Find all paises.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Pais> findAllPaises()
	    throws B2BException;

	/**
	 * Find all parte interesadas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ParteInteresada> findAllParteInteresadas()
	    throws B2BException;

	/**
	 * Find all plantilla.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Plantilla> findAllPlantilla()
	    throws B2BException;

	/**
	 * Método encargado de consultar todos los datos de Plantillas Comunicación.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la IP local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la IP remota del usuario que realiza la operación.
	 * @return Colección de tipo <code>PlantillaComunicacion</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PlantillaComunicacion> findAllPlantillaComunicacion(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de consultar todos los datos de Plantillas Notificación.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la IP local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la IP remota del usuario que realiza la operación.
	 * @return Colección de tipo <code>PlantillaNotificacion</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PlantillaNotificacion> findAllPlantillaNotificacion(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all proceso canal.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoCanal> findAllProcesoCanal()
	    throws B2BException;

	/**
	 * Find all proceso canal by id.
	 *
	 * @param ls_idProceso de ls id proceso
	 * @param ls_idSubProceso de ls id sub proceso
	 * @param ls_idTipoCanalOrigen de ls id tipo canal origen
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcesoCanal findAllProcesoCanalById(
	    String ls_idProceso, String ls_idSubProceso, String ls_idTipoCanalOrigen
	)
	    throws B2BException;

	/**
	 * Find all proceso consulta.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoConsulta> findAllProcesoConsulta()
	    throws B2BException;

	/**
	 * Find all procesos automaticos.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoAutomatico> findAllProcesosAutomaticos(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all punto recaudo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PuntoRecaudo> findAllPuntoRecaudo()
	    throws B2BException;

	/**
	 * Find all punto recaudo tipo recaudo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PuntoRecaudoTipoRecaudo> findAllPuntoRecaudoTipoRecaudo()
	    throws B2BException;

	/**
	 * Find all rango cuantia.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RangoCuantia> findAllRangoCuantia(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all regionales.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Regional> findAllRegionales()
	    throws B2BException;

	/**
	 * Método encargado de consultar todos los datos de regla negocio.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la IP local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la IP remota del usuario que realiza la operación.
	 * @return Colección de tipo <code>ReglaNegocio</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ReglaNegocio> findAllReglaNegocio(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all reintentos.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Reintentos> findAllReintentos()
	    throws B2BException;

	/**
	 * Find all reportes.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Reportes> findAllReportes(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all RolOpcion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RolOpcion> findAllRolOpcion()
	    throws B2BException;

	/**
	 * Find all rols.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Rol> findAllRols(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all rtf.
	 *
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] findAllRtf()
	    throws B2BException;

	/**
	 * Find all sucursal canal origen servicio.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SucursalCanalOrigenServicio> findAllSucursalCanalOrigenServicio()
	    throws B2BException;

	/**
	 * Find all tarifa alerta activo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TarifaAlerta> findAllTarifaAlertaActivo()
	    throws B2BException;

	/**
	 * Find all tarifa fija.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TarifaFija> findAllTarifaFija(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all tipo acto.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActo> findAllTipoActo(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all tipo acto condicion.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActoCondicion> findAllTipoActoCondicion(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipo acto ejecutoria.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActoEjecutoria> findAllTipoActoEjecutoria(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipo acto prohibicion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActoProhibicion> findAllTipoActoProhibicion()
	    throws B2BException;

	/**
	 * Find all tipo acto prohibicion.
	 *
	 * @param as_idTipoActoProhibicion de as id tipo acto prohibicion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActoProhibicion findAllTipoActoProhibicionById(String as_idTipoActoProhibicion)
	    throws B2BException;

	/**
	 * Find all tipo adquisicion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoAdquisicion> findAllTipoAdquisicion()
	    throws B2BException;

	/**
	 * Find all tipo adquisicion.
	 *
	 * @param as_s de as s
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoAdquisicion> findAllTipoAdquisicion(String as_s)
	    throws B2BException;

	/**
	 * Find all tipo area.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoArea> findAllTipoArea()
	    throws B2BException;

	/**
	 * Find all tipo canal origen.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoCanalOrigen> findAllTipoCanalOrigen()
	    throws B2BException;

	/**
	 * Find all tipo criterio busqueda pgn.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoCriterioBusquedaPGN> findAllTipoCriterioBusquedaPGN()
	    throws B2BException;

	/**
	 * Find all tipo decision recurso.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDecisionRecurso> findAllTipoDecisionRecurso(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all TipoDocumental.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumental> findAllTipoDocumental()
	    throws B2BException;

	/**
	 * Find all TipoDocumentalActo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumentalActo> findAllTipoDocumentalActo()
	    throws B2BException;

	/**
	 * Find all TipoDocumentalActo.
	 *
	 * @param al_idTipoDocumentalActo de al id tipo documental acto
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoDocumentalActo findAllTipoDocumentalActoById(long al_idTipoDocumentalActo)
	    throws B2BException;

	/**
	 * Find all TipoDocumental.
	 *
	 * @param as_idTipoDocumental de as id tipo documental
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoDocumental findAllTipoDocumentalById(String as_idTipoDocumental)
	    throws B2BException;

	/**
	 * Find all tipo documento publico.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumentoPublico> findAllTipoDocumentoPublico()
	    throws B2BException;

	/**
	 * Find all tipo documento publico.
	 *
	 * @param as_s de as s
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumentoPublico> findAllTipoDocumentoPublico(String as_s)
	    throws B2BException;

	/**
	 * Find all tipo eje.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEje> findAllTipoEje()
	    throws B2BException;

	/**
	 * Find all tipo estado liquidacion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEstadoLiquidacion> findAllTipoEstadoLiquidacion()
	    throws B2BException;

	/**
	 * Find all tipo integracion gobernacion.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoIntegracionGobernacion> findAllTipoIntegracionGobernacion(boolean ab_b)
	    throws B2BException;

	/**
	 * Find all tipo operacion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoOperacion> findAllTipoOperacion()
	    throws B2BException;

	/**
	 * Find all tipo persona.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoPersona> findAllTipoPersona()
	    throws B2BException;

	/**
	 * Find all tipo predio.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioTipo> findAllTipoPredio()
	    throws B2BException;

	/**
	 * Find all tipo RRR.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoRrr> findAllTipoRRR()
	    throws B2BException;

	/**
	 * Find all tipo recaudo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoRecaudo> findAllTipoRecaudo()
	    throws B2BException;

	/**
	 * Find all tipo recepcion.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoRecepcion> findAllTipoRecepcion()
	    throws B2BException;

	/**
	 * Find all tipo recepcion ordenado por nombre.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoRecepcion> findAllTipoRecepcionOrdenado()
	    throws B2BException;

	/**
	 * Find all tipo recurso.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoRecurso> findAllTipoRecurso(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Método para traer de la base de datos todos lo registros de la taba tipo recurso con ACTIVO igual al argumento enviado.
	 *
	 * @param as_activo Argumento de tipo <code>String</code> que contiene el criterio necesario para realizar la consulta.
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la petición.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la petición.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la petición.
	 * @return devuelve el valor del objeto collection de TipoRecurso.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoRecurso
	 */
	public Collection<TipoRecurso> findAllTipoRecursoByActivo(
	    String as_activo, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipo requiere matricula.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoRequiereMatricula> findAllTipoRequiereMatricula()
	    throws B2BException;

	/**
	 * Find all tipo uso suelo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoUsoSuelo> findAllTipoUsoSuelo()
	    throws B2BException;

	/**
	 * Find all tipos acto aplica desborde.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActo> findAllTiposActoAplicaDesborde(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipos anotacion.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoAnotacion> findAllTiposAnotacion(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all tipos causal.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoCausal> findAllTiposCausal(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all tipos eje.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEje> findAllTiposEje(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all tipos estado solicitud.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoEstadoSolicitud> findAllTiposEstadoSolicitud(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all tipos testamento.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoTestamento> findAllTiposTestamento(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de consultar todos los datos de topología regla.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la IP local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la IP remota del usuario que realiza la operación.
	 * @return Colección de tipo <code>ReglaRegla</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TopologiaRegla> findAllTopologiaRegla(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all usuario tiempo vencimiento.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UnidadTiempoVencimiento> findAllUnidadTiempoVencimiento()
	    throws B2BException;

	/**
	 * Find all usuario circulos.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioCirculo> findAllUsuarioCirculos(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find all usuario etapas.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioEtapa> findAllUsuarioEtapas(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all usuario lineas.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioLinea> findAllUsuarioLineas(String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find all veredas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Vereda> findAllVeredas()
	    throws B2BException;

	/**
	 * Find all zona registrales.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ZonaRegistral> findAllZonaRegistrales()
	    throws B2BException;

	/**
	 * Find all zona registrales activos.
	 *
	 * @param as_idCirculo de as idCirculo
	 * @param ab_zona de ab zona
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ZonaRegistral> findAllZonaRegistralesActivos(String as_idCirculo, boolean ab_zona)
	    throws B2BException;

	/**
	 * Find all zona registrales asociadas.
	 *
	 * @param aczr_czr de aczr czr
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ZonaRegistral> findAllZonaRegistralesAsociadas(Collection<ZonaRegistral> aczr_czr)
	    throws B2BException;

	/**
	 * Find by id documento version.
	 *
	 * @param as_idDocumento de as id documento
	 * @param al_versionDoc de al version doc
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Documento findByIdDocumentoVersion(
	    String as_idDocumento, Long al_versionDoc, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find calidad solicitante.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitante()
	    throws B2BException;

	/**
	 * Find calidad solicitante by id.
	 *
	 * @param accs_ccs de accs ccs
	 * @return el valor de calidad solicitante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CalidadSolicitante findCalidadSolicitanteById(CalidadSolicitante accs_ccs)
	    throws B2BException;

	/**
	 * Find campos criterio busqueda by id.
	 *
	 * @param as_tipoCriterioBusqueda de as tipo criterio busqueda
	 * @param as_campoCriterioBusqueda de as campo criterio busqueda
	 * @return el valor de campos consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposConsulta findCampoCriterioBusquedaById(
	    String as_tipoCriterioBusqueda, String as_campoCriterioBusqueda
	)
	    throws B2BException;

	/**
	 * Find campos certificado by id.
	 *
	 * @param acc_param de acc param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de CamposCertificado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposCertificado findCamposCertificadoById(
	    CamposCertificado acc_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find campos consulta by id.
	 *
	 * @param ama_ma de ama ma
	 * @return el valor de campos consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposConsulta findCamposConsultaById(CamposConsulta ama_ma)
	    throws B2BException;

	/**
	 * Find campos correccion by id.
	 *
	 * @param acc_param de acc param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de CamposCertificado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposCorreccion findCamposCorreccionById(
	    CamposCorreccion acc_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find canal by id.
	 *
	 * @param ac_c de ac c
	 * @return el valor de canal
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Canal findCanalById(Canal ac_c)
	    throws B2BException;

	/**
	 * Find canal origen servicio by id.
	 *
	 * @param lmr_dato de lmr dato
	 * @return el valor de canal origen servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CanalOrigenServicio findCanalOrigenServicioById(String lmr_dato)
	    throws B2BException;

	/**
	 * Find catastro by id.
	 *
	 * @param ac_param de ac param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de ActividadEconomica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Catastro findCatastroById(Catastro ac_param, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find causal aprobacion devolucion by id.
	 *
	 * @param acad_cad de acad cad
	 * @return el valor de causal aprobacion devolucion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalAprobacionDevolucion findCausalAprobacionDevolucionById(CausalAprobacionDevolucion acad_cad)
	    throws B2BException;

	/**
	 * Find causal correccion by id.
	 *
	 * @param acad_cad de acad cad
	 * @return el valor de causal correccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalCorreccion findCausalCorreccionById(CausalCorreccion acad_cad)
	    throws B2BException;

	/**
	 * Find causal rechazo recurso by id.
	 *
	 * @param acrr_param de acrr param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de CausalRechazoRecurso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalRechazoRecurso findCausalRechazoRecursoById(
	    CausalRechazoRecurso acrr_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find causal reimpresion by id.
	 *
	 * @param accr_ccr de accr ccr
	 * @return el valor de causal reimpresion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CausalReimpresion findCausalReimpresionById(CausalReimpresion accr_ccr)
	    throws B2BException;

	/**
	 * Find causales mayor valor por estado.
	 *
	 * @param as_estado de as estado
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalMayorValor> findCausalesMayorValorPorEstado(
	    String as_estado, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find circulo act admin by id.
	 *
	 * @param acaa_param de acaa param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de ActividadEconomica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoActAdmin findCirculoActAdminById(
	    CirculoActAdmin acaa_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find circulo catastro by id.
	 *
	 * @param acc_param de acc param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de ActividadEconomica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoCatastro findCirculoCatastroById(
	    CirculoCatastro acc_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find circulo festivo by id.
	 *
	 * @param as_s de as s
	 * @return el valor de circulo festivo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoFestivo findCirculoFestivoById(String as_s)
	    throws B2BException;

	/**
	 * Find circulo origen destino by id.
	 *
	 * @param acod_cod de acod cod
	 * @return el valor de circulo origen destino
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoOrigenDestino findCirculoOrigenDestinoById(CirculoOrigenDestino acod_cod)
	    throws B2BException;

	/**
	 * Find circulo registral by id.
	 *
	 * @param acr_circulo de acr circulo
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de circulo registral
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoRegistral findCirculoRegistralById(
	    CirculoRegistral acr_circulo, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find circulos por usuario.
	 *
	 * @param auc_usuarioCirculo de auc usuario circulo
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioCirculo> findCirculosPorUsuario(
	    UsuarioCirculo auc_usuarioCirculo, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find circulos por usuario activo.
	 *
	 * @param auc_usuarioCirculo de auc usuario circulo
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioCirculo> findCirculosPorUsuarioActivo(
	    UsuarioCirculo auc_usuarioCirculo, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find comunidades étnicas by id.
	 *
	 * @param ai_i de ai i
	 * @return el valor de comunidades etnicas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ComunidadesEtnicas findComunidadesEtnicasById(int ai_i)
	    throws B2BException;

	/**
	 * Find condicion tarifa by id.
	 *
	 * @param as_s de as s
	 * @return el valor de condicion tarifa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CondicionTarifa findCondicionTarifaById(String as_s)
	    throws B2BException;

	/**
	 * Find constant by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes findConstantById(Constantes ac_parametros)
	    throws B2BException;

	/**
	 * Find constant by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes findConstantByIdCYN(Constantes ac_parametros)
	    throws B2BException;

	/**
	 * Find consultas by id.
	 *
	 * @param as_s de as s
	 * @return el valor de consultas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Consultas findConsultasById(Consultas as_s)
	    throws B2BException;

	/**
	 * Find convenios.
	 *
	 * @param as_numeroConvenio de as numeroConvenio
	 * @param as_nombreConvenio de as nombreConvenio
	 * @return el valor de Collection de AccEntidadExternaConvenio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExternaConvenio> findConvenios(String as_numeroConvenio, String as_nombreConvenio)
	    throws B2BException;

	/**
	 * Find coordenada by id.
	 *
	 * @param as_s de as s
	 * @return el valor de coordenada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Coordenada findCoordenadaById(String as_s)
	    throws B2BException;

	/**
	 * Find departamento by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de departamento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Departamento findDepartamentoById(Departamento ac_parametros)
	    throws B2BException;

	/**
	 * Find dependencia snr by id.
	 *
	 * @param adsnr_param de adsnr param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de ActividadEconomica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DependenciaSNR findDependenciaSNRById(
	    DependenciaSNR adsnr_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Encontrar DetalleAntSistema por DetalleAntSistema y DatosAntSistema.
	 *
	 * @param as_idDetalleAntSistema de as id detalle ant sistema
	 * @param as_idDatosAntSistema de as id datos ant sistema
	 * @return el valor de DetalleAntSistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DetalleAntSistema findDetalleAntSistemaByDetalleYDatosAntSis(
	    String as_idDetalleAntSistema, String as_idDatosAntSistema
	)
	    throws B2BException;

	/**
	 * Find detalle proceso consulta by id.
	 *
	 * @param acdpc_cdpc de acdpc cdpc
	 * @return el valor de detalle proceso consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DetalleProcesoConsulta findDetalleProcesoConsultaById(DetalleProcesoConsulta acdpc_cdpc)
	    throws B2BException;

	/**
	 * Find dominio RRR by id.
	 *
	 * @param ldr_parametros de ldr parametros
	 * @return el valor de dominio rrr
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DominioRrr findDominioRRRById(DominioRrr ldr_parametros)
	    throws B2BException;

	/**
	 * Find entidad recaudadora by id.
	 *
	 * @param aer_er de aer er
	 * @return el valor de entidad recaudadora
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EntidadRecaudadora findEntidadRecaudadoraById(EntidadRecaudadora aer_er)
	    throws B2BException;

	/**
	 * Find entidades alerta by id.
	 *
	 * @param aea_param de aea param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de EntidadesAlerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EntidadesAlerta findEntidadesAlertaById(
	    EntidadesAlerta aea_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find estado actividad by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de estado actividad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EstadoActividad findEstadoActividadById(EstadoActividad ac_parametros)
	    throws B2BException;

	/**
	 * Find estado anotacion by id.
	 *
	 * @param aea_ea de aea ea
	 * @return el valor de estado anotacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EstadoAnotacion findEstadoAnotacionById(EstadoAnotacion aea_ea)
	    throws B2BException;

	/**
	 * Find estado nupre by id.
	 *
	 * @param aen_en de aen en
	 * @return el valor de estado nupre
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EstadoNupre findEstadoNupreById(EstadoNupre aen_en)
	    throws B2BException;

	/**
	 * Find estado predio by id.
	 *
	 * @param aen_en de aen en
	 * @return el valor de estado predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EstadoPredio findEstadoPredioById(EstadoPredio aen_en)
	    throws B2BException;

	/**
	 * Find estado registro by id.
	 *
	 * @param aer_er de aer er
	 * @return el valor de estado registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EstadoRegistro findEstadoRegistroById(EstadoRegistro aer_er)
	    throws B2BException;

	/**
	 * Find estados by id.
	 *
	 * @param ae_e de ae e
	 * @return el valor de estados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Estados findEstadosById(Estados ae_e)
	    throws B2BException;

	/**
	 * Find etapa by id.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MotivoTramite> findEtapaById()
	    throws B2BException;

	/**
	 * Find etapa by id.
	 *
	 * @param ae_etapa de ae etapa
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de etapa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Etapa findEtapaById(Etapa ae_etapa, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find etapas por usuario.
	 *
	 * @param aue_usuarioEtapa de aue usuario etapa
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioEtapa> findEtapasPorUsuario(
	    UsuarioEtapa aue_usuarioEtapa, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find etapas por usuario activo.
	 *
	 * @param aue_usuarioEtapa de aue usuario etapa
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioEtapa> findEtapasPorUsuarioActivo(
	    UsuarioEtapa aue_usuarioEtapa, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find festivo nacional by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de festivo nacional
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public FestivoNacional findFestivoNacionalById(FestivoNacional ac_parametros)
	    throws B2BException;

	/**
	 * Find gobernacion by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de gobernacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Gobernacion findGobernacionById(Gobernacion ac_parametros)
	    throws B2BException;

	/**
	 * Find grupo naturaleza juridica by id.
	 *
	 * @param agnj_gnj de agnj gnj
	 * @return el valor de grupo naturaleza juridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public GrupoNaturalezaJuridica findGrupoNaturalezaJuridicaById(GrupoNaturalezaJuridica agnj_gnj)
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
	 * Find instancia consulta by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de instancia consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public InstanciaConsulta findInstanciaConsultaById(InstanciaConsulta ac_parametros)
	    throws B2BException;

	/**
	 * Find interesado documento tipo by id.
	 *
	 * @param aidt_parametros de aidt parametros
	 * @return el valor de interesado documento tipo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public InteresadoDocumentoTipo findInteresadoDocumentoTipoById(InteresadoDocumentoTipo aidt_parametros)
	    throws B2BException;

	/**
	 * Find interesado natural genero by id.
	 *
	 * @param aing_parametros de aing parametros
	 * @return el valor de interesado natural genero
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public InteresadoNaturalGenero findInteresadoNaturalGeneroById(InteresadoNaturalGenero aing_parametros)
	    throws B2BException;

	/**
	 * Find letra by id.
	 *
	 * @param as_s de as s
	 * @return el valor de coordenada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LetraEje findLetraEjeById(String as_s)
	    throws B2BException;

	/**
	 * Find libro antiguo sistema by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de libro antiguo sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LibroAntiguoSistema findLibroAntiguoSistemaById(LibroAntiguoSistema ac_parametros)
	    throws B2BException;

	/**
	 * Find libro testamento by id.
	 *
	 * @param alt_param de alt param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de ActividadEconomica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LibroTestamento findLibroTestamentoById(
	    LibroTestamento alt_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find linea produccion by id.
	 *
	 * @param alp_parametros de alp parametros
	 * @return el valor de linea produccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LineaProduccion findLineaProduccionById(LineaProduccion alp_parametros)
	    throws B2BException;

	/**
	 * Find lineas por usuario.
	 *
	 * @param aul_usuarioLinea de aul usuario linea
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioLinea> findLineasPorUsuario(
	    UsuarioLinea aul_usuarioLinea, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find lineas por usuario activo.
	 *
	 * @param aul_usuarioLinea de aul usuario linea
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioLinea> findLineasPorUsuarioActivo(
	    UsuarioLinea aul_usuarioLinea, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find medida area by id.
	 *
	 * @param as_s de as s
	 * @return el valor de medida area
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MedidaArea findMedidaAreaById(String as_s)
	    throws B2BException;

	/**
	 * Find medio recaudo by id.
	 *
	 * @param as_dato de as dato
	 * @return el valor de medio recaudo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MedioRecaudo findMedioRecaudoById(String as_dato)
	    throws B2BException;

	/**
	 * Find mensaje respuesta by id.
	 *
	 * @param ls_codigoMensaje de ls codigo mensaje
	 * @return el valor de mensaje respuesta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MensajeRespuesta findMensajeRespuestaById(String ls_codigoMensaje)
	    throws B2BException;

	/**
	 * Find motivo tramite by id.
	 *
	 * @param amt_mt de amt mt
	 * @return el valor de motivo tramite
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MotivoTramite findMotivoTramiteById(MotivoTramite amt_mt)
	    throws B2BException;

	/**
	 * Find motivo tramite by id unique.
	 *
	 * @param amt_mt Argumento de tipo <code>String</code> que contiene el id delñ motivo trámite.
	 * @return Variable de tipo <code>MotivoTramite</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MotivoTramite findMotivoTramiteById(String as_idMotivoTramite)
	    throws B2BException;

	/**
	 * Find municipio by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de municipio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Municipio findMunicipioById(Municipio ac_parametros)
	    throws B2BException;

	/**
	 * Find naturaleza juridica by id.
	 *
	 * @param acnj_cnj de acnj cnj
	 * @return el valor de naturaleza juridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NaturalezaJuridica findNaturalezaJuridicaById(NaturalezaJuridica acnj_cnj)
	    throws B2BException;

	/**
	 * Find nota devolutiva by id.
	 *
	 * @param acad_cad de acad cad
	 * @return el valor de nota devolutiva
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NotaDevolutiva findNotaDevolutivaById(NotaDevolutiva acad_cad)
	    throws B2BException;

	/**
	 * Find nota devolutiva by turno.
	 *
	 * @param and_notaDevolutiva de and nota devolutiva
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<NotaDevolutiva> findNotaDevolutivaByTurno(NotaDevolutiva and_notaDevolutiva)
	    throws B2BException;

	/**
	 * Find numeracion oficios circulo by id.
	 *
	 * @param anoc_param de anoc param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de ActividadEconomica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NumeracionOficiosCirculo findNumeracionOficiosCirculoById(
	    NumeracionOficiosCirculo anoc_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find oficina origen by id.
	 *
	 * @param amt_mt de amt mt
	 * @return el valor de oficina origen
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficinaOrigen findOficinaOrigenById(OficinaOrigen amt_mt)
	    throws B2BException;

	/**
	 * Find opcion by id.
	 *
	 * @param as_idOpcion de as idOpcion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Opcion findOpcionById(String as_idOpcion)
	    throws B2BException;

	/**
	 * Find opcion etapa by id.
	 *
	 * @param as_idEtapa de as idEtapa
	 * @param as_idOpcion de as idOpcion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OpcionEtapa findOpcionEtapaById(long as_idEtapa, String as_idOpcion)
	    throws B2BException;

	/**
	 * Find origen by id.
	 *
	 * @param ao_o de ao o
	 * @return el valor de origen
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Origen findOrigenById(Origen ao_o)
	    throws B2BException;

	/**
	 * Find pais by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de pais
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Pais findPaisById(Pais ac_parametros)
	    throws B2BException;

	/**
	 * Find parte interesada by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de parte interesada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ParteInteresada findParteInteresadaById(ParteInteresada ac_parametros)
	    throws B2BException;

	/**
	 * Find persona by entidad.
	 *
	 * @param aaee_entidadExterna de aaee entidadExterna
	 * @return el valor de Collection de Persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> findPersonaByEntidad(AccEntidadExterna aaee_entidadExterna)
	    throws B2BException;

	/**
	 * Find persona correo by id.
	 *
	 * @param as_idPersona de as idPersona
	 * @return el valor de PersonaCorreoElectronico
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PersonaCorreoElectronico findPersonaCorreoById(String as_idPersona)
	    throws B2BException;

	/**
	 * Find plantilla by id.
	 *
	 * @param ap_p de ap p
	 * @return el valor de plantilla
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Plantilla findPlantillaById(Plantilla ap_p)
	    throws B2BException;

	/**
	 * Método encargado de buscar una plantilla comunicación por id.
	 *
	 * @param apn_pn Argumento de tipo <code>PlantillaComunicacion</code> a consultar.
	 * @return Objeto de tipo <code>PlantillaComunicacion</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public PlantillaComunicacion findPlantillaComunicacionById(PlantillaComunicacion apn_pn)
	    throws B2BException;

	/**
	 * Método encargado de buscar una plantillanotificacion por id.
	 *
	 * @param apn_pn Argumento de tipo <code>PlantillaNotificacion</code> a consultar.
	 * @return Objeto de tipo <code>PlantillaNotificacion</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public PlantillaNotificacion findPlantillaNotificacionById(PlantillaNotificacion apn_pn)
	    throws B2BException;

	/**
	 * Find proceso automatico by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de proceso automatico
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcesoAutomatico findProcesoAutomaticoById(ProcesoAutomatico ac_parametros)
	    throws B2BException;

	/**
	 * Find proceso by id.
	 *
	 * @param ap_p de ap p
	 * @return el valor de proceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Proceso findProcesoById(Proceso ap_p)
	    throws B2BException;

	/**
	 * Find proceso consulta by id.
	 *
	 * @param acpc_cpc de acpc cpc
	 * @return el valor de proceso consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcesoConsulta findProcesoConsultaById(ProcesoConsulta acpc_cpc)
	    throws B2BException;

	/**
	 * Find punto recaudo by id.
	 *
	 * @param lpr_dato de lpr dato
	 * @return el valor de punto recaudo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PuntoRecaudo findPuntoRecaudoById(String lpr_dato)
	    throws B2BException;

	/**
	 * Find punto recaudo tipo recaudo by id.
	 *
	 * @param aprtr_prtr de aprtr prtr
	 * @return el valor de punto recaudo tipo recaudo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PuntoRecaudoTipoRecaudo findPuntoRecaudoTipoRecaudoById(PuntoRecaudoTipoRecaudo aprtr_prtr)
	    throws B2BException;

	/**
	 * Find rango cuantia by id.
	 *
	 * @param adr_parametros de adr parametros
	 * @return el valor de rango cuantia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RangoCuantia findRangoCuantiaById(RangoCuantia adr_parametros)
	    throws B2BException;

	/**
	 * Find regional by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de regional
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Regional findRegionalById(Regional ac_parametros)
	    throws B2BException;

	/**
	 * Método encargado de buscar una regla negocio por id.
	 *
	 * @param arn_rn Argumento de tipo <code>ReglaNegocio</code> a consultar.
	 * @return Objeto de tipo <code>ReglaNegocio</code> que contiene los resultados de la busqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public ReglaNegocio findReglaNegocioById(ReglaNegocio arn_rn)
	    throws B2BException;

	/**
	 * Find reintentos by id.
	 *
	 * @param ar_r de ar r
	 * @return el valor de reintentos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Reintentos findReintentosById(Reintentos ar_r)
	    throws B2BException;

	/**
	 * Find reportes by id.
	 *
	 * @param adr_parametros de adr parametros
	 * @return el valor de reportes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Reportes findReportesById(Reportes adr_parametros)
	    throws B2BException;

	/**
	 * Find resultado consulta by id.
	 *
	 * @param arc_rc de arc rc
	 * @return el valor de ResultadoConsulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ResultadoConsulta findResultadoConsultaById(ResultadoConsulta arc_rc)
	    throws B2BException;

	/**
	 * Find rol by id.
	 *
	 * @param ar_parametros de ar parametros
	 * @return el valor de rol
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Rol findRolById(Rol ar_parametros)
	    throws B2BException;

	/**
	 * Find rolOpcion by id.
	 *
	 * @param as_idRol de as id rol
	 * @param as_idOpcion de as id opcion
	 * @return el valor de rolOpcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RolOpcion findRolOpcionById(String as_idRol, String as_idOpcion)
	    throws B2BException;

	/**
	 * Find salario minimo.
	 *
	 * @param aottf_data de aottf data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de salario minimo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SalarioMinimo findSalarioMinimo(
	    SalarioMinimo aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find subproceso by id.
	 *
	 * @param acsp_csp de acsp csp
	 * @return el valor de subproceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Subproceso findSubProcesoById(Subproceso acsp_csp)
	    throws B2BException;

	/**
	 * Find subproceso version by id.
	 *
	 * @param acspv_cspv de acspv cspv
	 * @return el valor de SubprocesoVersion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SubprocesoVersion findSubProcesoVersionById(SubprocesoVersion acspv_cspv)
	    throws B2BException;

	/**
	 * Find subprocesos.
	 *
	 * @param acsp_csp de acsp csp
	 * @return el valor de subproceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Subproceso> findSubprocesos(boolean acsp_csp)
	    throws B2BException;

	/**
	 * Find sucursal canal origen servicio by id.
	 *
	 * @param ascos_scos de ascos scos
	 * @return el valor de sucursal canal origen servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SucursalCanalOrigenServicio findSucursalCanalOrigenServicioById(SucursalCanalOrigenServicio ascos_scos)
	    throws B2BException;

	/**
	 * Find tarifa fija by id.
	 *
	 * @param adr_parametros de adr parametros
	 * @return el valor de tarifa fija
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TarifaFija findTarifaFijaById(TarifaFija adr_parametros)
	    throws B2BException;

	/**
	 * Find tipo acto by linea produccion.
	 *
	 * @param as_lineaProduccion de as linea produccion
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActo> findTipoActoByLineaProduccion(
	    String as_lineaProduccion, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo acto condicion by id.
	 *
	 * @param adr_parametros de adr parametros
	 * @return el valor de tipo acto condicion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActoCondicion findTipoActoCondicionById(TipoActoCondicion adr_parametros)
	    throws B2BException;

	/**
	 * Find tipo acto ejecutoria by id.
	 *
	 * @param adr_parametros de adr parametros
	 * @return el valor de tipo acto ejecutoria
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActoEjecutoria findTipoActoEjecutoriaById(TipoActoEjecutoria adr_parametros)
	    throws B2BException;

	/**
	 * Find tipo area by id.
	 *
	 * @param ata_ta de ata ta
	 * @return el valor de tipo area
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoArea findTipoAreaById(TipoArea ata_ta)
	    throws B2BException;

	/**
	 * Find tipo canal origen by id.
	 *
	 * @param atco_tipoCanalOrigen de atco tipo canal origen
	 * @return el valor de tipo canal origen
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoCanalOrigen findTipoCanalOrigenById(TipoCanalOrigen atco_tipoCanalOrigen)
	    throws B2BException;

	/**
	 * Find tipo criterio busqueda pgn by id.
	 *
	 * @param atcb_param de atcb param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de ActividadEconomica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoCriterioBusquedaPGN findTipoCriterioBusquedaPGNById(
	    TipoCriterioBusquedaPGN atcb_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo decision recurso by id.
	 *
	 * @param adr_parametros de adr parametros
	 * @return el valor de tipo decision recurso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoDecisionRecurso findTipoDecisionRecursoById(TipoDecisionRecurso adr_parametros)
	    throws B2BException;

	/**
	 * Find tipo eje by id.
	 *
	 * @param as_s de as s
	 * @return el valor de tipo eje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoEje findTipoEjeById(String as_s)
	    throws B2BException;

	/**
	 * Find tipo entidad.
	 *
	 * @param aottf_data de aottf data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo entidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoEntidad findTipoEntidad(
	    TipoEntidad aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo estado liquidacion by id.
	 *
	 * @param ata_ta de ata ta
	 * @return el valor de tipo estado liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoEstadoLiquidacion findTipoEstadoLiquidacionById(TipoEstadoLiquidacion ata_ta)
	    throws B2BException;

	/**
	 * Find tipo estado solicitud by id.
	 *
	 * @param ates_param de ates param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de TipoEstadoSolicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoEstadoSolicitud findTipoEstadoSolicitudById(
	    TipoEstadoSolicitud ates_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo integracion gobernacion by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de tipo integracion gobernacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoIntegracionGobernacion findTipoIntegracionGobernacionById(TipoIntegracionGobernacion ac_parametros)
	    throws B2BException;

	/**
	 * Find calidad solicitante.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoNotificacion> findTipoNotificacion()
	    throws B2BException;

	/**
	 * Find tipo notificacion by id.
	 *
	 * @param actn_ctn de actn ctn
	 * @return el valor de tipo notificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoNotificacion findTipoNotificacionById(TipoNotificacion actn_ctn)
	    throws B2BException;

	/**
	 * Find tipo oficina.
	 *
	 * @param aottf_data de aottf data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo oficina
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoOficina findTipoOficina(
	    TipoOficina aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo operacion by id.
	 *
	 * @param ato_param de ato param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de TipoOperacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoOperacion findTipoOperacionById(
	    TipoOperacion ato_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo persona by id.
	 *
	 * @param atp_parametros de atp parametros
	 * @return el valor de tipo persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoPersona findTipoPersonaById(TipoPersona atp_parametros)
	    throws B2BException;

	/**
	 * Find tipo predio by id.
	 *
	 * @param apt_parametros de apt parametros
	 * @return el valor de predio tipo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PredioTipo findTipoPredioById(PredioTipo apt_parametros)
	    throws B2BException;

	/**
	 * Find tipo RRR by id.
	 *
	 * @param lpt_parametros de lpt parametros
	 * @return el valor de tipo rrr
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoRrr findTipoRRRById(TipoRrr lpt_parametros)
	    throws B2BException;

	/**
	 * Find tipo recaudo by id.
	 *
	 * @param ata_ta de ata ta
	 * @return el valor de tipo recaudo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoRecaudo findTipoRecaudoById(TipoRecaudo ata_ta)
	    throws B2BException;

	/**
	 * Find tipo recepcion by id.
	 *
	 * @param atr_parametros de atr parametros
	 * @return el valor de tipo recepcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoRecepcion findTipoRecepcionById(TipoRecepcion atr_parametros)
	    throws B2BException;

	/**
	 * Find tipo recurso by id.
	 *
	 * @param adr_parametros de adr parametros
	 * @return el valor de tipo recurso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoRecurso findTipoRecursoById(TipoRecurso adr_parametros)
	    throws B2BException;

	/**
	 * Find tipo requiere matricula by id.
	 *
	 * @param aen_en de aen en
	 * @return el valor de tipo requiere matricula
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoRequiereMatricula findTipoRequiereMatriculaById(TipoRequiereMatricula aen_en)
	    throws B2BException;

	/**
	 * Find tipo tarjeta apoderado by id.
	 *
	 * @param ar_parametros de ar parametros
	 * @return el valor de tipo tarjeta apoderado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoTarjetaApoderado findTipoTarjetaApoderadoById(TipoTarjetaApoderado ar_parametros)
	    throws B2BException;

	/**
	 * Find tipo uso predio by id.
	 *
	 * @param atus_parametros de atus parametros
	 * @return el valor de tipo uso suelo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoUsoSuelo findTipoUsoPredioById(TipoUsoSuelo atus_parametros)
	    throws B2BException;

	/**
	 * Find tipos adquisicion.
	 *
	 * @param aottf_data de aottf data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo adquisicion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoAdquisicion findTiposAdquisicion(
	    TipoAdquisicion aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipos pagos.
	 *
	 * @param aottf_data de aottf data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo pago
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoPago findTiposPagos(
	    TipoPago aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipos tarifas.
	 *
	 * @param aottf_data de aottf data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo tarifa registral
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoTarifaRegistral findTiposTarifas(
	    TipoTarifaRegistral aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find unidad tiempo vencimiento by id.
	 *
	 * @param acutv_cutv de acutv cutv
	 * @return el valor de unidad tiempo vencimiento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public UnidadTiempoVencimiento findUnidadTiempoVencimientoById(UnidadTiempoVencimiento acutv_cutv)
	    throws B2BException;

	/**
	 * Find usuario by tipo doc.
	 *
	 * @param au_parametro de au parametro
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Usuario findUsuarioByTipoDoc(Usuario au_parametro, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find usuario persona.
	 *
	 * @param ap_persona de ap persona
	 * @param acp_cp de acp cp
	 * @param as_idEntidad de as idEntidad
	 * @return el valor de String
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findUsuarioPersona(Persona ap_persona, Collection<Persona> acp_cp, String as_idEntidad)
	    throws B2BException;

	/**
	 * Consulta todos los registros asociados a un id rol, id linea produccion y
	 * activo especificos.
	 *
	 * @param al_idEtapa Argumento de tipo <code>long</code> que contiene el id etapa.
	 * @param as_lineaProduccion de as linea produccion
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene el id circulo.
	 * @param as_idRol Argumento de tipo <code>String</code> que contiene el id rol.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id usuario.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota.
	 * @return colección resultante de la búsqueda
	 * @throws B2BException si ocurre un error en base de datos
	 */
	public Collection<UsuarioRol> findUsuarioRolByRolLineaProduccion(
	    long al_idEtapa, String as_lineaProduccion, String as_idCirculo, String as_idRol, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find vereda by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de vereda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Vereda findVeredaById(Vereda ac_parametros)
	    throws B2BException;

	/**
	 * Find zona registral by id.
	 *
	 * @param ac_parametros de ac parametros
	 * @return el valor de zona registral
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ZonaRegistral findZonaRegistralById(ZonaRegistral ac_parametros)
	    throws B2BException;

	/**
	 * Generar codigos.
	 *
	 * @param as_idConstante de as id constante
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, String> generarCodigos(String as_idConstante)
	    throws B2BException;

	/**
	 * Gestion salario minimo.
	 *
	 * @param aottf_data de aottf data
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void gestionSalarioMinimo(
	    SalarioMinimo aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Gestion tipo adquisicion.
	 *
	 * @param aottf_data de aottf data
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void gestionTipoAdquisicion(
	    TipoAdquisicion aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Gestion tipo entidad.
	 *
	 * @param aottf_data de aottf data
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void gestionTipoEntidad(TipoEntidad aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Gestion tipo oficina.
	 *
	 * @param aottf_data de aottf data
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void gestionTipoOficina(TipoOficina aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Gestion tipo pago.
	 *
	 * @param aottf_data de aottf data
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void gestionTipoPago(TipoPago aottf_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Gestion tipos tarifas.
	 *
	 * @param aottf_data de aottf data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void gestionTiposTarifas(
	    TipoTarifaRegistral aottf_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update circulo.
	 *
	 * @param acr_circulo de acr circulo
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateCirculo(
	    CirculoRegistral acr_circulo, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update desbordes.
	 *
	 * @param ad_desbores de ad desbores
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateDesbordes(
	    Collection<Desborde> ad_desbores, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update dominio naturaleza juridica.
	 *
	 * @param adnj_dominioNatJur de adnj dominio nat jur
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateDominioNaturalezaJuridica(
	    DominioNaturalezaJuridica adnj_dominioNatJur, boolean ab_insert, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update etapa.
	 *
	 * @param ae_etapa de ae etapa
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateEtapa(
	    Etapa ae_etapa, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update fase.
	 *
	 * @param af_fase de af fase
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateFase(
	    Fases af_fase, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update interesado documento tipo.
	 *
	 * @param aidt_intDocTipo de aidt int doc tipo
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateInteresadoDocumentoTipo(
	    InteresadoDocumentoTipo aidt_intDocTipo, boolean ab_insert, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update rango cuantia.
	 *
	 * @param arc_data de arc data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateRangoCuantia(
	    RangoCuantia arc_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update reportes.
	 *
	 * @param arc_data de arc data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateReportes(
	    Reportes arc_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tarifa fija.
	 *
	 * @param arc_data de arc data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTarifaFija(
	    TarifaFija arc_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipo acto.
	 *
	 * @param ata_tipoActo de ata tipo acto
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTipoActo(
	    TipoActo ata_tipoActo, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipo acto condicion.
	 *
	 * @param arc_data de arc data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTipoActoCondicion(
	    TipoActoCondicion arc_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipo acto ejecutoria.
	 *
	 * @param arc_data de arc data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTipoActoEjecutoria(
	    TipoActoEjecutoria arc_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipo decision recurso.
	 *
	 * @param arc_data de arc data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTipoDecisionRecurso(
	    TipoDecisionRecurso arc_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipo recurso.
	 *
	 * @param arc_data de arc data
	 * @param ab_b de ab b
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTipoRecurso(
	    TipoRecurso arc_data, boolean ab_b, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipos anotacion.
	 *
	 * @param ata_tipoAnotacion de ata tipo anotacion
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTiposAnotacion(
	    TipoAnotacion ata_tipoAnotacion, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipos causal.
	 *
	 * @param atc_tipoCausal de atc tipo causal
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTiposCausal(
	    TipoCausal atc_tipoCausal, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipos eje.
	 *
	 * @param ate_tipoEje de ate tipo eje
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTiposEje(
	    TipoEje ate_tipoEje, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipos estado solicitud.
	 *
	 * @param ates_tipoEstadoSolicitud de ates tipo estado solicitud
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTiposEstadoSolicitud(
	    TipoEstadoSolicitud ates_tipoEstadoSolicitud, boolean ab_insert, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update tipos testamento.
	 *
	 * @param att_tipoTestamento de att tipo testamento
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateTiposTestamento(
	    TipoTestamento att_tipoTestamento, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update usuario circulo.
	 *
	 * @param auc_usuarioCirculo de auc usuario circulo
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateUsuarioCirculo(
	    UsuarioCirculo auc_usuarioCirculo, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update usuario etapa.
	 *
	 * @param aue_usuarioEtapa de aue usuario etapa
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateUsuarioEtapa(
	    UsuarioEtapa aue_usuarioEtapa, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insert update usuario linea.
	 *
	 * @param aul_usuarioLinea de aul usuario linea
	 * @param ab_insert de ab insert
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String insertUpdateUsuarioLinea(
	    UsuarioLinea aul_usuarioLinea, boolean ab_insert, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar acc entidad externa.
	 *
	 * @param aaee_parametros de aaee parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAccEntidadExterna(
	    AccEntidadExterna aaee_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar acc entidad externa convenio.
	 *
	 * @param aaeec_parametros de aaeec parametros
	 * @param ab_accion de ab accion
	 * @param lczr_zonaRegistral de lczr zonaRegistral
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAccEntidadExternaConvenio(
	    AccEntidadExternaConvenio aaeec_parametros, boolean ab_accion, Collection<ZonaRegistral> lczr_zonaRegistral,
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de una actividad economica.
	 *
	 * @param aae_parametros de aae parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarActividadEconomica(
	    ActividadEconomica aae_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de ActuacionesAdministrativas.
	 *
	 * @param aaa_parametros de aaa parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar sucursal administracion componentes.
	 *
	 * @param aac_parametros de aro prametros
	 * @param as_insertar de as insertar
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAdministracionComponentes(
	    Componente aac_parametros, boolean as_insertar, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Salvar alerta naturaleza juridica.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAlertaNaturalezaJuridica(
	    AlertaNaturalezaJuridica atr_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar alerta tramite.
	 *
	 * @param aat_parametros de aat parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAlertaTramite(
	    AlertaTramite aat_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar CalidadSolicitante.
	 *
	 * @param acs_parametros de  parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCalidadSolicitante(
	    CalidadSolicitante acs_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar CampoCriterioBusqueda.
	 *
	 * @param acc_cc de  parametros
	 * @param as_insertar de ab accion
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCampoCriterioBusqueda(
	    CamposConsulta acc_cc, boolean as_insertar, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un campos certificados.
	 *
	 * @param acc_parametros de acc parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCamposCertificado(
	    CamposCertificado acc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar campos consulta.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCamposConsulta(
	    CamposConsulta atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un campos correccion.
	 *
	 * @param acc_parametros de acc parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCamposCorreccion(
	    CamposCorreccion acc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar canal.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCanal(
	    Canal ac_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar canal origen servicio.
	 *
	 * @param acos_parametros de acos parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCanalOrigenServicio(
	    CanalOrigenServicio acos_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un catastro.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCatastro(
	    Catastro ac_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar causal aprobacion devolucion.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCausalAprobacionDevolucion(
	    CausalAprobacionDevolucion atr_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar causal correccion.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCausalCorreccion(
	    CausalCorreccion atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar causal mayor valor.
	 *
	 * @param cmv_parametros de parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCausalMayorValor(
	    CausalMayorValor cmv_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar causal negacion copias.
	 *
	 * @param acnc_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCausalNegacionCopias(
	    CausalNegacionCopias acnc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un causal rechazo registro.
	 *
	 * @param acrr_parametros de acrr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCausalRechazoRecurso(
	    CausalRechazoRecurso acrr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar CausalReimpresion.
	 *
	 * @param acr_parametros de  parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCausalReimpresion(
	    CausalReimpresion acr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de una circulo act admin.
	 *
	 * @param acaa_parametros de acaa parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCirculoActAdmin(
	    CirculoActAdmin acaa_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un circulo catastro.
	 *
	 * @param acc_parametros de acc parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCirculoCatastro(
	    CirculoCatastro acc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar circulo festivo.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCirculoFestivo(
	    CirculoFestivo atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar circulo origen destino.
	 *
	 * @param lpc_parametros de lpc parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCirculoOrigenDestino(
	    CirculoOrigenDestino lpc_parametros, boolean ab_insertar, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Salvar comunidades etnicas.
	 *
	 * @param acce_parametros de acce parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarComunidadesEtnicas(
	    Collection<ComunidadesEtnicas> acce_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar condicion tarifa.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCondicionTarifa(
	    CondicionTarifa atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
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
	 * Salvar constante.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarConstanteCYN(Constantes ac_parametros, boolean ab_accion)
	    throws B2BException;

	/**
	 * Salvar consultas.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarConsultas(
	    Consultas atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar coordenada.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCoordenada(
	    Coordenada ac_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar departamento.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_remota de as remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDepartamento(
	    Departamento ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_remota
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de una dependencia snr.
	 *
	 * @param adsnr_parametros de adsnr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDependenciaSNR(
	    DependenciaSNR adsnr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar DetalleProcesoConsulta.
	 *
	 * @param adpc_parametros de  parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDetalleProcesoConsulta(
	    DetalleProcesoConsulta adpc_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar dominio RRR.
	 *
	 * @param ldr_parametros de ldr parametros
	 * @param insertar de insertar
	 * @param userId de user id
	 * @param localIpAddress de local ip address
	 * @param ipAddress de ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDominioRRR(
	    DominioRrr ldr_parametros, boolean insertar, String userId, String localIpAddress, String ipAddress
	)
	    throws B2BException;

	/**
	 * Salvar entidad recaudadora.
	 *
	 * @param aer_parametros de aer parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEntidadRecaudadora(
	    EntidadRecaudadora aer_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar entidades.
	 *
	 * @param aaee_parametros de aaee parametros
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEntidades(
	    AccEntidadExterna aaee_parametros, Collection<Persona> ap_parametros, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de una entidades alerta.
	 *
	 * @param aea_parametros de aea parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEntidadesAlerta(
	    EntidadesAlerta aea_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar entidades modificadas.
	 *
	 * @param aaee_parametros de aaee parametros
	 * @param ap_parametros de ap parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEntidadesModificadas(
	    AccEntidadExterna aaee_parametros, Collection<Persona> ap_parametros, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar estado actividad.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipRemota de as ip remota
	 * @param as_ipLocal de as ip local
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEstadoActividad(
	    EstadoActividad ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException;

	/**
	 * Salvar estado anotacion.
	 *
	 * @param aea_parametros de aea parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEstadoAnotacion(
	    EstadoAnotacion aea_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar estado nupre.
	 *
	 * @param aen_parametros de aen parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEstadoNupre(
	    EstadoNupre aen_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar estado predio.
	 *
	 * @param aep_parametros de aep parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEstadoPredio(
	    EstadoPredio aep_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar estado registro.
	 *
	 * @param aer_parametros de aer parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEstadoRegistro(
	    EstadoRegistro aer_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar estados.
	 *
	 * @param ae_parametros de ae parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEstados(
	    Estados ae_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar festivo nacional.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipRemota de as ip remota
	 * @param as_ipLocal de as ip local
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarFestivoNacional(
	    FestivoNacional ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException;

	/**
	 * Salvar gobernacion.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipRemota de as ip remota
	 * @param as_ipLocal de as ip local
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarGobernacion(
	    Gobernacion ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException;

	/**
	 * Salvar grupo naturaleza juridica.
	 *
	 * @param agnj_parametros de agnj parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarGrupoNaturalezaJuridica(
	    GrupoNaturalezaJuridica agnj_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar instancia consulta.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipRemota de as ip remota
	 * @param as_ipLocal de as ip local
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarInstanciaConsulta(
	    InstanciaConsulta ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException;

	/**
	 * Salvar interesado natural genero.
	 *
	 * @param aing_parametros de aing parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarInteresadoNaturalGenero(
	    InteresadoNaturalGenero aing_parametros, boolean ab_accion, String as_usuario, String as_ipLocal,
	    String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar letra eje.
	 *
	 * @param ale_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipRemota de as ip remota
	 * @param as_ipLocal de as ip local
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarLetraEje(
	    LetraEje ale_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException;

	/**
	 * Salvar libro antiguo sistema.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipRemota de as ip remota
	 * @param as_ipLocal de as ip local
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarLibroAntiguoSistema(
	    LibroAntiguoSistema ac_parametros, boolean ab_accion, String as_usuario, String as_ipRemota, String as_ipLocal
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un libro testamento.
	 *
	 * @param alt_parametros de alt parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarLibroTestamento(
	    LibroTestamento alt_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar linea produccion.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_remota de as remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarLineaProduccion(
	    LineaProduccion ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_remota
	)
	    throws B2BException;

	/**
	 * Salvar medida area.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarMedidaArea(
	    MedidaArea atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar medio recaudo.
	 *
	 * @param amr_parametros de amr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarMedioRecaudo(
	    MedioRecaudo amr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar mensaje respuesta.
	 *
	 * @param amr_parametros de amr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarMensajeRespuesta(
	    MensajeRespuesta amr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar motivo tramite.
	 *
	 * @param amt_parametros de amt parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarMotivoTramite(
	    MotivoTramite amt_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar municipio.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarMunicipio(
	    Municipio ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar NaturalezaJuridica.
	 *
	 * @param anj_parametros de  parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarNaturalezaJuridica(
	    NaturalezaJuridica anj_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un numeracion oficios circulo.
	 *
	 * @param anoc_parametros de anoc parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarNumeracionOficiosCirculo(
	    NumeracionOficiosCirculo anoc_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar oficina origen.
	 *
	 * @param amt_parametros de amt parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarOficinaOrigen(
	    OficinaOrigen amt_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar Opcion Etapa.
	 *
	 * @param aoe_parametros de atd parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarOpcionEtapa(OpcionEtapa aoe_parametros, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Salvar Opcion Insertar.
	 *
	 * @param ao_parametros de ao parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarOpcionInsertar(Opcion ao_parametros, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Salvar Opcion Modificar.
	 *
	 * @param ao_parametros de ao parametros
	 * @param ace_opcionEtapa de ace opcionEtapa
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarOpcionModificar(
	    Opcion ao_parametros, Collection<Etapa> ace_opcionEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar origen.
	 *
	 * @param ao_parametros de ao parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarOrigen(
	    Origen ao_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar pais.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarPais(
	    Pais ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar parte interesada.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarParteInteresada(
	    ParteInteresada ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar plantilla.
	 *
	 * @param ap_parametros de ap parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarPlantilla(
	    Plantilla ap_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para salvar la insercion o modificacion para una plantilla comunicación.
	 *
	 * @param ap_parametros objeto de tipo <code>PlantillaComunicacion</code> que se desea salvar
	 * @param ab_insertar variable de tipo <code>boolean</code> que indica si la operacion es una inserción
	 * @param as_userId objeto de tipo <code>String</code> usuario en sesión que realiza alguna accion(modificar o insertar)
	 * @param as_localIp objeto de tipo <code>String</code> ip local del usuario en sesión
	 * @param as_remoteIp objeto de tipo <code>String</code> ip remota del usuario en sesión
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarPlantillaComunicacion(
	    PlantillaComunicacion ap_parametros, boolean ab_insertar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para salvar la insercion o modificacion para una plantilla notificación.
	 *
	 * @param ap_parametros objeto de tipo <code>PlantillaNotificacion</code> que se desea salvar
	 * @param ab_insertar variable de tipo <code>boolean</code> que indica si la operacion es una inserción
	 * @param as_userId objeto de tipo <code>String</code> usuario en sesión que realiza alguna accion(modificar o insertar)
	 * @param as_localIp objeto de tipo <code>String</code> ip local del usuario en sesión
	 * @param as_remoteIp objeto de tipo <code>String</code> ip remota del usuario en sesión
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarPlantillaNotificacion(
	    PlantillaNotificacion ap_parametros, boolean ab_insertar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar proceso .
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarProceso(
	    Proceso atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar proceso automatico.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarProcesoAutomatico(
	    ProcesoAutomatico ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar proceso canal.
	 *
	 * @param apc_parametros de apc parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarProcesoCanal(
	    ProcesoCanal apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar ProcesoConsulta.
	 *
	 * @param apc_parametros de  parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarProcesoConsulta(
	    ProcesoConsulta apc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar punto recaudo.
	 *
	 * @param apr_parametros de apr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarPuntoRecaudo(
	    PuntoRecaudo apr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar punto recaudo tipo recaudo.
	 *
	 * @param aprtr_parametros de aprtr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarPuntoRecaudoTipoRecaudo(
	    PuntoRecaudoTipoRecaudo aprtr_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar regional.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarRegional(
	    Regional ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Método para salvar la insercion o modificacion para una regla negocio.
	 *
	 * @param arn_parametros objeto de tipo <code>ReglaNegocio</code> que se desea salvar
	 * @param ab_insertar variable de tipo <code>boolean</code> que indica si la operacion es una inserción
	 * @param as_userId objeto de tipo <code>String</code> usuario en sesión que realiza alguna accion(modificar o insertar)
	 * @param as_localIp objeto de tipo <code>String</code> ip local del usuario en sesión
	 * @param as_remoteIp objeto de tipo <code>String</code> ip remota del usuario en sesión
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarReglaNegocio(
	    ReglaNegocio rn_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar reintentos.
	 *
	 * @param ar_parametros de ar parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarReintentos(
	    Reintentos ar_parametros, boolean ab_insertar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un resultado consulta.
	 *
	 * @param arc_parametros de arc parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarResultadoConsulta(
	    ResultadoConsulta arc_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, IOException;

	/**
	 * Salvar rol.
	 *
	 * @param ar_parametros de ar parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de rol
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Rol salvarRol(Rol ar_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Salvar sucursal Rol Opción.
	 *
	 * @param aro_prametros de aro prametros
	 * @param as_insertar de as insertar
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarRolOpcion(
	    RolOpcion aro_prametros, boolean as_insertar, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Salvar rol opción componente.
	 *
	 * @param as_rol de as rol
	 * @param acc_parametros de acc parametros
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarRolOpcionComponente(
	    String as_rol, Collection<Componente> acc_parametros, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Salvar SubProceso.
	 *
	 * @param asp_parametros de  parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarSubproceso(
	    Subproceso asp_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar subproceso version.
	 *
	 * @param asp_parametros Argumento de tipo <code>SubprocesoVersion</code> que contiene la información de la operación.
	 * @param ab_accion Argumento de tipo <code>boolean</code> que indica el tipo de operación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la dirección ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la dirección ip remota del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarSubprocesoVersion(
	    SubprocesoVersion asp_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar sucursal canal origen servicio.
	 *
	 * @param ascos_parametros de ascos parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarSucursalCanalOrigenServicio(
	    SucursalCanalOrigenServicio ascos_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tarifa alerta.
	 *
	 * @param actpa_parametros de actpa parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTarifaAlerta(
	    Collection<TarifaAlerta> actpa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo acto prohibicion.
	 *
	 * @param atap_parametros de atap parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoActoProhibicion(
	    TipoActoProhibicion atap_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo adquisicion.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoAdquisicion(
	    TipoAdquisicion atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo area.
	 *
	 * @param ata_parametros de ata parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoArea(
	    TipoArea ata_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo canal origen.
	 *
	 * @param atco_parametros de atco parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoCanalOrigen(
	    TipoCanalOrigen atco_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de una tipo criterio busqueda pgn.
	 *
	 * @param atcb_parametros de atcb parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoCriterioBusquedaPGN(
	    TipoCriterioBusquedaPGN atcb_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo documental.
	 *
	 * @param atd_parametros de atd parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoDocumental(
	    TipoDocumental atd_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo documental acto.
	 *
	 * @param atda_parametros de atda parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoDocumentalActo(
	    TipoDocumentalActo atda_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo documento publico.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoDocumentoPublico(
	    TipoDocumentoPublico atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo estado liquidacion.
	 *
	 * @param atel_parametros de atel parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoEstadoLiquidacion(
	    TipoEstadoLiquidacion atel_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo integracion gobernacion.
	 *
	 * @param at_parametros de at parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoIntegracionGobernacion(
	    TipoIntegracionGobernacion at_parametros, boolean ab_accion, String as_usuario, String as_ipLocal,
	    String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar TipoNotificacion.
	 *
	 * @param atn_parametros de  parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoNotificacion(
	    TipoNotificacion atn_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para guardar los cambios o la inserción de un tipo operacion.
	 *
	 * @param ato_parametros de ato parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoOperacion(
	    TipoOperacion ato_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo persona.
	 *
	 * @param atp_parametros de atp parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoPersona(
	    TipoPersona atp_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar tipo predio.
	 *
	 * @param apt_parametros de apt parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoPredio(
	    PredioTipo apt_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar tipo RRR.
	 *
	 * @param lpt_parametros de lpt parametros
	 * @param ib_query de ib query
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemote de as ip remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoRRR(
	    TipoRrr lpt_parametros, boolean ib_query, String as_userId, String as_ipLocal, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * Salvar tipo recaudo.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoRecaudo(
	    TipoRecaudo atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo recepcion.
	 *
	 * @param atr_parametros de atr parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoRecepcion(
	    TipoRecepcion atr_parametros, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo requiere matricula.
	 *
	 * @param atrm_parametros de atrm parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoRequiereMatricula(
	    TipoRequiereMatricula atrm_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar tipo tarjeta apoderado.
	 *
	 * @param ar_parametros de ar parametros
	 * @param ab_accion de ab accion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoTarjetaApoderado(TipoTarjetaApoderado ar_parametros, boolean ab_accion)
	    throws B2BException;

	/**
	 * Salvar tipo uso suelo.
	 *
	 * @param atus_parametros de atus parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTipoUsoSuelo(
	    TipoUsoSuelo atus_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar UnidadTiempoVencimiento.
	 *
	 * @param autv_parametros de  parametros
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarUnidadTiempoVencimiento(
	    UnidadTiempoVencimiento autv_parametros, boolean ab_accion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar vereda.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarVereda(
	    Vereda ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar zona registral.
	 *
	 * @param ac_parametros de ac parametros
	 * @param ab_accion de ab accion
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarZonaRegistral(
	    ZonaRegistral ac_parametros, boolean ab_accion, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Validar excel persona entidad.
	 *
	 * @param aba_archivo de aba archivo
	 * @param as_nombreFile de as nombre file
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public Collection<Persona> validarExcelPersonaEntidad(
	    byte[] aba_archivo, String as_nombreFile, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, IOException;

	/**
	 * Validar tipo documento.
	 *
	 * @param as_tipoDocumento de as tipo documento
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarTipoDocumento(String as_tipoDocumento)
	    throws B2BException;
}
