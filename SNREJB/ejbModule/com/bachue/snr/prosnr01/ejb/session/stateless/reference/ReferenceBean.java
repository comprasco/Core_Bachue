package com.bachue.snr.prosnr01.ejb.session.stateless.reference;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.reference.ReferenceBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

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

import org.perf4j.StopWatch;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades ReferenceBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Reference", mappedName = "referenceMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ReferenceBean implements ReferenceRemote
{
	/** Propiedad irb business. */
	private ReferenceBusiness irb_business;

	/** {@inheritdoc} */
	public Collection<CamposConsulta> buscarCamposPorCriterio(
	    CamposConsulta am_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CamposConsulta> lcm_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcm_datos     = getReferenceBusiness().buscarCamposPorCriterio(am_parametros);

		Logger.log(
		    lsw_watch, "ReferenceBean", "buscarCamposPorCriterio", as_userId, as_localIp, as_remoteIp, lcm_datos
		);

		return lcm_datos;
	}

	/** {@inheritdoc} */
	public Collection<Constantes> buscarMotivosConsulta(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Constantes> lcm_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcm_datos     = getReferenceBusiness().buscarMotivosConsulta();

		Logger.log(lsw_watch, "ReferenceBean", "buscarMotivosConsulta", as_userId, as_localIp, as_remoteIp, lcm_datos);

		return lcm_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoRegistral> buscarTodosCirculosRegistralesOrigenDestinoActivos(
	    boolean ab_b, String as_idCirculoOrigen, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lccr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccr_datos     = getReferenceBusiness()
				                 .buscarTodosCirculosRegistralesOrigenDestinoActivos(ab_b, as_idCirculoOrigen);

		Logger.log(
		    lsw_watch, "ReferenceBean", "buscarTodosCirculosRegistralesOrigenDestinoActivos", as_userId, as_localIp,
		    as_remoteIp, lccr_datos
		);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> buscarTodosMunicipiosPorPais(
	    Municipio am_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Municipio> lcm_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcm_datos     = getReferenceBusiness().buscarTodosMunicipiosPorPais(am_parametros);

		Logger.log(
		    lsw_watch, "ReferenceBean", "buscarTodosMunicipiosPorPais", as_userId, as_localIp, as_remoteIp, lcm_datos
		);

		return lcm_datos;
	}

	/** {@inheritdoc} */
	public Date calcularFechaVencimiento(FechaVenceTerminosUI afvtui_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Date      ld_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ld_datos = getReferenceBusiness().calcularFechaVencimiento(afvtui_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "calcularFechaVencimiento", null, null, null, null);

		return ld_datos;
	}

	/** {@inheritdoc} */
	public com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado cargarAnotacionesMatriculaAgregar(
	    com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado aps_predioAgregar, boolean ab_conBaseEn
	)
	    throws B2BException
	{
		StopWatch                                             lsw_watch;
		com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado lps_return;

		lsw_watch      = Logger.getNewStopWatch();
		lps_return     = getReferenceBusiness().cargarAnotacionesMatriculaAgregar(aps_predioAgregar, ab_conBaseEn);

		Logger.log(lsw_watch, "ReferenceBean", "cargarAnotacionesMatriculaAgregar", null, null, null, null);

		return lps_return;
	}

	/** {@inheritdoc} */
	public List<Componente> cargarComponentesMenu(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		List<Componente> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_datos     = getReferenceBusiness().cargarComponentesMenu(as_userId);

		Logger.log(lsw_watch, "ReferenceBean", "cargarComponentesMenu", as_userId, as_localIp, as_remoteIp, lcc_datos);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Map<String, Map<String, String>> cargarDataDireccion()
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Map<String, Map<String, String>> lmsmss_data;

		lsw_watch       = Logger.getNewStopWatch();
		lmsmss_data     = getReferenceBusiness().cargarDataDireccion();

		Logger.log(lsw_watch, "ReferenceBean", "cargarDataDireccion", null, null, null, null);

		return lmsmss_data;
	}

	/** {@inheritdoc} */
	public Collection<Opcion> cargarOpcionesMenu(
	    String as_userId, String as_idComponente, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<Opcion> lco_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lco_datos     = getReferenceBusiness().cargarOpcionesMenu(as_userId, as_idComponente);

		Logger.log(lsw_watch, "ReferenceBean", "cargarOpcionesMenu", as_userId, as_localIp, as_remoteIp, lco_datos);

		return lco_datos;
	}

	/** {@inheritdoc} */
	public Collection<Consultas> cargarTipoConsulta(
	    String as_userId, boolean ab_orderById, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Consultas> lccr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccr_datos     = getReferenceBusiness().cargarTipoConsulta(ab_orderById);

		Logger.log(lsw_watch, "ReferenceBean", "cargarTipoConsulta", as_userId, as_localIp, as_remoteIp, lccr_datos);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Collection<String> findActivoByCirculoMatricula(PredioRegistro apr_pr)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> lcs_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcs_datos     = getReferenceBusiness().findActivoByCirculoMatricula(apr_pr);

		Logger.log(lsw_watch, "ReferenceBean", "findActivoByCirculoMatricula", null, null, null, lcs_datos);

		return lcs_datos;
	}

	/**
	 * Find all activo by usuario.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> findAllActivoByUsuario(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lccr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccr_datos     = getReferenceBusiness().findAllActivoByUsuario(as_userId);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllActivoByUsuario", as_userId, as_localIp, as_remoteIp, lccr_datos
		);

		return lccr_datos;
	}

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
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<CausalNegacionCopias> lccr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccr_datos = getReferenceBusiness().findAllCausalNegacionCopias();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllCausalNegacionCopias", as_userId, as_localIp, as_remoteIp, lccr_datos
		);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoRegistral> findAllCirculosRegistralesActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lccr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccr_datos     = getReferenceBusiness().findAllCirculosRegistralesActivos(ab_b);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllCirculosRegistralesActivos", as_userId, as_localIp, as_remoteIp,
		    lccr_datos
		);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoRegistral> findAllCirculosRegistralesActivosByIdSolicitud(
	    String as_userId, String as_localIp, String as_remoteIp, String as_idSolicitud
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lccr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccr_datos     = getReferenceBusiness().findAllCirculosRegistralesActivosByIdSolicitud(as_idSolicitud);

		Logger.log(lsw_watch, "ReferenceBean", "findVeredas", as_userId, as_localIp, as_remoteIp, lccr_datos);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoRegistral> findAllCirculosRegistralesByRegional(
	    CirculoRegistral acr_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lccr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccr_datos     = getReferenceBusiness().findAllCirculosRegistralesByRegional(acr_param);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllCirculosRegistralesByRegional", as_userId, as_localIp, as_remoteIp,
		    lccr_datos
		);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoEje> findAllComplementoDireccionByTipoPredio(String as_idTipoPredio)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<TipoEje> lcte_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcte_datos     = getReferenceBusiness().findAllComplementoDireccionByTipoPredio(as_idTipoPredio);

		Logger.log(lsw_watch, "ReferenceBean", "findAllComplementoDireccionByTipoPredio", null, null, null, lcte_datos);

		return lcte_datos;
	}

	/** {@inheritdoc} */
	public Collection<Constantes> findAllConstantesActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Constantes> lcc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_datos     = getReferenceBusiness().findAllConstantesActivos(ab_b);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllConstantesActivos", as_userId, as_localIp, as_remoteIp, lcc_datos
		);

		return lcc_datos;
	}

	/** {@inheritdoc} */
	public Collection<DependenciaSNR> findAllDependenciaByIndVisitas()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<DependenciaSNR> lcd_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcd_datos     = getReferenceBusiness().findAllDependenciaByIndVisitas();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoDocumento", null, null, null, lcd_datos);

		return lcd_datos;
	}

	/** {@inheritdoc} */
	public Collection<DominioNaturalezaJuridica> findAllDominioNaturalezaJuridicaActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                             lsw_watch;
		Collection<DominioNaturalezaJuridica> lcdnj_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcdnj_datos     = getReferenceBusiness().findAllDominioNaturalezaJuridicaActivos(ab_b);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllDominioNaturalezaJuridicaActivos", as_userId, as_localIp, as_remoteIp,
		    lcdnj_datos
		);

		return lcdnj_datos;
	}

	/** {@inheritdoc} */
	public Collection<Etapa> findAllEtapasActivo(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Etapa> lce_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lce_datos     = getReferenceBusiness().findAllEtapasActivo(false);

		Logger.log(lsw_watch, "ReferenceBean", "findAllEtapasActivo", as_userId, as_localIp, as_remoteIp, lce_datos);

		return lce_datos;
	}

	/** {@inheritdoc} */
	public Collection<Etapa> findAllEtapasActivoById(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Etapa> lce_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lce_datos     = getReferenceBusiness().findAllEtapasActivo(true);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllEtapasActivoById", as_userId, as_localIp, as_remoteIp, lce_datos
		);

		return lce_datos;
	}

	/** {@inheritdoc} */
	public Collection<Fases> findAllFasesActivas(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Fases> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findAllFasesActivas();

		Logger.log(lsw_watch, "ReferenceBean", "findAllFasesActivas", as_userId, as_localIp, as_remoteIp, lte_datos);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<GrupoNaturalezaJuridica> findAllGrupoNaturalezaJuridicaActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<GrupoNaturalezaJuridica> lcgnj_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcgnj_datos     = getReferenceBusiness().findAllGrupoNaturalezaJuridicaActivos(ab_b);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllGrupoNaturalezaJuridicaActivos", as_userId, as_localIp, as_remoteIp,
		    lcgnj_datos
		);

		return lcgnj_datos;
	}

	/** {@inheritdoc} */
	public Collection<CalidadSolicitante> findAllInCalidadSolicitante(
	    CalidadSolicitante acs_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<CalidadSolicitante> lccs_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccs_datos = getReferenceBusiness().findAllInCalidadSolicitante(acs_parametros);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllInCalidadSolicitante", as_userId, as_localIp, as_remoteIp, lccs_datos
		);

		return lccs_datos;
	}

	/** {@inheritdoc} */
	public Collection<NaturalezaJuridica> findAllMaxVersionNaturalezaJuridica(boolean ab_b)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<NaturalezaJuridica> lcnj_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcnj_datos     = getReferenceBusiness().findAllMaxVersionNaturalezaJuridica(ab_b);

		Logger.log(lsw_watch, "ReferenceBean", "findAllMaxVersionNaturalezaJuridica", null, null, null, lcnj_datos);

		return lcnj_datos;
	}

	/** {@inheritdoc} */
	public int findAllMaxVersionTipoActoEjecutoria(String as_tipoActo)
	    throws B2BException
	{
		StopWatch lsw_watch;
		int       li_version;

		lsw_watch      = Logger.getNewStopWatch();
		li_version     = getReferenceBusiness().findAllMaxVersionTipoActoEjecutoria(as_tipoActo);

		Logger.log(lsw_watch, "ReferenceBean", "findAllMaxVersionTipoActoEjecutoria", null, null, null, null);

		return li_version;
	}

	/** {@inheritdoc} */
	public Collection<MedidaArea> findAllMedidaAreaActivo()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<MedidaArea> lcma_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcma_datos     = getReferenceBusiness().findAllMedidaAreaActivo();

		Logger.log(lsw_watch, "ReferenceBean", "findAllMedidaAreaActivo", null, null, null, lcma_datos);

		return lcma_datos;
	}

	/** {@inheritdoc} */
	public Map<String, String> findAllMedidaAreaNombres()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Map<String, String> lhmss_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lhmss_datos     = getReferenceBusiness().findAllMedidaAreaNombres();

		Logger.log(lsw_watch, "ReferenceBean", "findAllMedidaAreaNombres", null, null, null, null);

		return lhmss_datos;
	}

	/** {@inheritdoc} */
	public Collection<NaturalezaJuridica> findAllNaturalezaJuridica(boolean ab_b)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<NaturalezaJuridica> lcnj_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcnj_datos     = getReferenceBusiness().findAllNaturalezaJuridica(ab_b);

		Logger.log(lsw_watch, "ReferenceBean", "findAllNaturalezaJuridica", null, null, null, lcnj_datos);

		return lcnj_datos;
	}

	/**
	 * Método para consultar todos los nombres de las columnas corresponddientes a la tabla.
	 *
	 * @param as_nombreTabla de as nombre tabla
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public Collection<UserObjects> findAllNombresColumnas(
	    String as_nombreTabla, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<UserObjects> lcuo_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcuo_datos     = getReferenceBusiness().findAllNombresColumnas(as_nombreTabla);

		Logger.log(
		    lsw_watch, "ReferenceBean", "obtenerNombresColumnas", as_userId, as_localIp, as_remoteIp, lcuo_datos
		);

		return lcuo_datos;
	}

	/**
	 * Método para consultar todos los nombres de las tablas de la base de datos.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public Collection<UserObjects> findAllNombresTablas(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<UserObjects> lcuo_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcuo_datos     = getReferenceBusiness().findAllNombresTablas();

		Logger.log(lsw_watch, "ReferenceBean", "obtenerNombresTablas", as_userId, as_localIp, as_remoteIp, lcuo_datos);

		return lcuo_datos;
	}

	/** {@inheritdoc} */
	public Collection<OficinaOrigen> findAllOficinasOrigen(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<OficinaOrigen> lcoo_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcoo_datos     = getReferenceBusiness().findAllOficinasOrigen();

		Logger.log(lsw_watch, "ReferenceBean", "findAllOficinasOrigen", as_userId, as_localIp, as_remoteIp, lcoo_datos);

		return lcoo_datos;
	}

	/** {@inheritdoc} */
	public Collection<Plantilla> findAllPlantillasActivo(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Plantilla> lce_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lce_datos     = getReferenceBusiness().findAllPlantillasActivo();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllPlantillasActivo", as_userId, as_localIp, as_remoteIp, lce_datos
		);

		return lce_datos;
	}

	/** {@inheritdoc} */
	public Collection<ProcesoConsulta> findAllProcesoConsultaActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<ProcesoConsulta> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findAllProcesoConsultaActivos();

		Logger.log(lsw_watch, "ReferenceBean", "findAllProcesosActivos", as_userId, as_localIp, as_remoteIp, lte_datos);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<Proceso> findAllProcesosActivos(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Proceso> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findAllProcesosActivos(false);

		Logger.log(lsw_watch, "ReferenceBean", "findAllProcesosActivos", as_userId, as_localIp, as_remoteIp, lte_datos);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<Proceso> findAllProcesosActivosById(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Proceso> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findAllProcesosActivos(true);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllProcesosActivosById", as_userId, as_localIp, as_remoteIp, lte_datos
		);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<Regional> findAllRegionalesActivos(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<Regional> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcr_datos     = getReferenceBusiness().findAllRegionalesActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllRegionalesActivos", as_userId, as_localIp, as_remoteIp, lcr_datos
		);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Regional> findAllRegionalesActivosConCirculo(
	    String as_iCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<Regional> lcr_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcr_datos     = getReferenceBusiness().findAllRegionalesActivosConCirculo(as_iCirculo);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllRegionalesActivos", as_userId, as_localIp, as_remoteIp, lcr_datos
		);

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Subproceso> findAllSubProcesosActivos(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Subproceso> lsp_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lsp_datos     = getReferenceBusiness().findAllSubProcesosActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllSubProcesosActivos", as_userId, as_localIp, as_remoteIp, lsp_datos
		);

		return lsp_datos;
	}

	/** {@inheritdoc} */
	public Collection<SubprocesoVersion> findAllSubprocesosVersion(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<SubprocesoVersion> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findAllSubprocesosVersion();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllSubprocesosVersion", as_userId, as_localIp, as_remoteIp, lte_datos
		);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<AccCompletitudDocumental> findAllTipoCompletitudProceso(
	    String as_userId, String as_localIp, String as_remoteIp, String as_idSolicitud, String as_idProceso
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<AccCompletitudDocumental> lctd_data;

		lsw_watch     = Logger.getNewStopWatch();
		lctd_data     = getReferenceBusiness().findAllTipoCompletitudProceso(as_idSolicitud, as_idProceso);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTipoCompletitudProceso", as_userId, as_localIp, as_remoteIp, lctd_data
		);

		return lctd_data;
	}

	/**
	 * Método para consultar todos los tipos criterio de busqueda.
	 *
	 * @return colección de criteriosDeBusqueda conn la información solicitada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CriteriosDeBusqueda> findAllTipoCriterioBusqueda()
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<CriteriosDeBusqueda> lccb_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccb_datos     = getReferenceBusiness().findAllTipoCriterioBusqueda();

		Logger.log(lsw_watch, "ReferenceBean", "findAllTipoCriterioBusqueda", null, null, null, null);

		return lccb_datos;
	}

	/** {@inheritdoc} */
	public Collection<CriteriosDeBusqueda> findAllTipoCriterioBusquedaActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<CriteriosDeBusqueda> ltcb_datos;

		lsw_watch      = Logger.getNewStopWatch();
		ltcb_datos     = getReferenceBusiness().findAllTipoCriterioBusquedaActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTipoCriterioBusquedaActivos", as_userId, as_localIp, as_remoteIp,
		    ltcb_datos
		);

		return ltcb_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoOficina> findAllTipoOficinaActivo(String as_s, boolean ab_orderById)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoOficina> lcma_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcma_datos     = getReferenceBusiness().findAllTipoOficinaActivo(as_s, ab_orderById);

		Logger.log(lsw_watch, "ReferenceBean", "findAllTipoOficinaActivo", null, null, null, lcma_datos);

		return lcma_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRrr> findAllTipoRrrActivos(
	    boolean ab_b, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<TipoRrr> lctr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lctr_datos     = getReferenceBusiness().findAllTipoRrrActivos(ab_b);

		Logger.log(lsw_watch, "ReferenceBean", "findAllTipoRrrActivos", as_userId, as_localIp, as_remoteIp, lctr_datos);

		return lctr_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoTarifaRegistral> findAllTipoTarifaRegistralActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<TipoTarifaRegistral> lccr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccr_datos     = getReferenceBusiness().findAllTipoTarifaRegistralActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTipoTarifaRegistralActivos", as_userId, as_localIp, as_remoteIp,
		    lccr_datos
		);

		return lccr_datos;
	}

	/**
	 * Método para consultar todos los tipos Acto .
	 *
	 * @return colección de TipoActo con la información solicitada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActo> findAllTiposActo()
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lccb_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccb_datos     = getReferenceBusiness().findAllTiposActo();

		Logger.log(lsw_watch, "ReferenceBean", "findAllTiposActo", null, null, null, null);

		return lccb_datos;
	}

	/** {@inheritdoc} */
	public Map<String, TipoActo> findAllTiposActoActivoData(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Map<String, TipoActo> lhm_return;

		lsw_watch      = Logger.getNewStopWatch();
		lhm_return     = getReferenceBusiness().findAllTiposActoActivoData();

		Logger.log(lsw_watch, "ReferenceBean", "findAllTiposActoActivoData", as_userId, as_localIp, as_remoteIp, null);

		return lhm_return;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findAllTiposActoActivos(
	    String as_userId, String as_localIp, String as_remoteIp, boolean ab_orderBy, boolean ab_nuevaSolicitud,
	    boolean ab_registro
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lcta_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcta_datos     = getReferenceBusiness().findAllTiposActoActivos(ab_orderBy, ab_nuevaSolicitud, ab_registro);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposActoActivos", as_userId, as_localIp, as_remoteIp, lcta_datos
		);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findAllTiposActoRemanenteActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lcta_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcta_datos     = getReferenceBusiness().findAllTiposActoRemanenteActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposActoRemanenteActivos", as_userId, as_localIp, as_remoteIp,
		    lcta_datos
		);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public Map<String, TipoActo> findAllTiposActoRemanenteActivosMap(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Map<String, TipoActo> lmst_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lmst_datos     = getReferenceBusiness().findAllTiposActoRemanenteActivosMap();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposActoRemanenteActivosMap", as_userId, as_localIp, as_remoteIp, null
		);

		return lmst_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findAllTiposActoVisActivos(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lcta_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcta_datos     = getReferenceBusiness().findAllTiposActoVisActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposActoVisActivos", as_userId, as_localIp, as_remoteIp, lcta_datos
		);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoAnotacion> findAllTiposAnotacionActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TipoAnotacion> lcta_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcta_datos     = getReferenceBusiness().findAllTiposAnotacionActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposAnotacionActivos", as_userId, as_localIp, as_remoteIp, lcta_datos
		);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoCausal> findAllTiposCausalesActivos(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp, boolean ab_bandera,
	    boolean ab_notaDevolutiva
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<TipoCausal> ltc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		ltc_datos     = getReferenceBusiness()
				                .findAllTiposCausalesActivos(as_idTurnoHistoria, ab_bandera, ab_notaDevolutiva);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposCausalesActivos", as_userId, as_localIp, as_remoteIp, ltc_datos
		);

		return ltc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoCausal> findAllTiposCausalesActivosPorProcSubproc(
	    String as_idProceso, String as_idSubproceso, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<TipoCausal> lctc_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lctc_datos     = getReferenceBusiness().findAllTiposCausalesActivosPorProcSubproc(
			    as_idProceso, as_idSubproceso
			);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposCausalesActivosPorProcSubproc", as_userId, as_localIp, as_remoteIp,
		    lctc_datos
		);

		return lctc_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumental> findAllTiposDocumentales(
	    String as_userId, String as_localIp, String as_remoteIp, String ls_constante
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TipoDocumental> lctd_data;

		lsw_watch     = Logger.getNewStopWatch();
		lctd_data     = getReferenceBusiness().findAllTiposDocumentales(ls_constante);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposDocumentales", as_userId, as_localIp, as_remoteIp, lctd_data
		);

		return lctd_data;
	}

	/** {@inheritdoc} */
	public Collection<TipoEstadoSolicitud> findAllTiposEstadoSolicitudActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<TipoEstadoSolicitud> lctes_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lctes_datos     = getReferenceBusiness().findAllTiposEstadoSolicitudActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllTiposEstadoSolicitudActivos", as_userId, as_localIp, as_remoteIp,
		    lctes_datos
		);

		return lctes_datos;
	}

	/** {@inheritdoc} */
	public String findAllTurnoNirCorrecciones(String as_s, String as_idProceso)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_datos;

		lsw_watch     = Logger.getNewStopWatch();
		ls_datos      = getReferenceBusiness().findTurnoNirCorrecciones(as_s, as_idProceso);

		Logger.log(lsw_watch, "ReferenceBean", "findAllTurnoNirCorrecciones", null, null, null, null);

		return ls_datos;
	}

	/** {@inheritdoc} */
	public Collection<UnidadTiempoVencimiento> findAllUnidadesDeTiempo(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<UnidadTiempoVencimiento> lcutv_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcutv_datos     = getReferenceBusiness().findAllUnidadesDeTiempo();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllUnidadesDeTiempo", as_userId, as_localIp, as_remoteIp, lcutv_datos
		);

		return lcutv_datos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Usuario> findAllUsers(String as_idUsuario)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Usuario> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findAllUsers(as_idUsuario);

		Logger.log(lsw_watch, "ReferenceBean", "findAllUsers", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<Usuario> findAllUsersActivos(
	    String as_idUsuario, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Usuario> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findAllUsersActivos(as_idUsuario);

		Logger.log(lsw_watch, "ReferenceBean", "findAllUsersActivos", as_userId, as_localIp, as_remoteIp, lte_datos);

		return lte_datos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Usuario> findAllUsersByEtapa(long as_etapa, boolean ab_b)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Usuario> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findAllUsersByEtapa(as_etapa, ab_b);

		Logger.log(lsw_watch, "ReferenceBean", "findAllUsersByEtapa", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<NaturalezaJuridica> findAllVersionById(String as_idAlertaN)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<NaturalezaJuridica> lcnj_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcnj_datos     = getReferenceBusiness().findAllVersionById(as_idAlertaN);

		Logger.log(lsw_watch, "ReferenceBean", "findAllVersionById", null, null, null, lcnj_datos);

		return lcnj_datos;
	}

	/** {@inheritdoc} */
	public Collection<InteresadoDocumentoTipo> findAprobaciones(Long al_idEtapa)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findAprobaciones(al_idEtapa);

		Logger.log(lsw_watch, "ReferenceBean", "findAprobaciones", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<BitacoraBloqueo> findBitacoraBloqueoByCirculoMatricula(
	    BitacoraBloqueo abb_bb, boolean ab_onlyBloqueantes
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<BitacoraBloqueo> lcbb_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcbb_datos     = getReferenceBusiness().findBitacoraBloqueoByCirculoMatricula(abb_bb, ab_onlyBloqueantes);

		Logger.log(lsw_watch, "ReferenceBean", "findBitacoraBloqueoByCirculoMatricula", null, null, null, lcbb_datos);

		return lcbb_datos;
	}

	/** {@inheritDoc} */
	public Collection<CausalAprobacionDevolucion> findByEtapaDevolucion111(
	    CausalAprobacionDevolucion acad_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		Collection<CausalAprobacionDevolucion> lccda_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lccda_datos     = getReferenceBusiness().findByEtapaDevolucion111(acad_param);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findByEtapaDevolucion111", as_userId, as_localIp, as_remoteIp, lccda_datos
		);

		return lccda_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRecepcion> findByHabilitado(
	    TipoRecepcion atr_parametros, boolean ab_not, boolean ab_personaJuridica, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TipoRecepcion> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findByHabilitado(atr_parametros, ab_not, ab_personaJuridica);

		Logger.log(lsw_watch, "ReferenceBean", "findByHabilitado", as_userId, as_localIp, as_remoteIp, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<CirculoRegistral> findByIdRegionalOnly(
	    CirculoRegistral acr_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lccr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lccr_datos     = getReferenceBusiness().findByIdRegionalOnly(acr_param);

		Logger.log(lsw_watch, "ReferenceBean", "findByIdRegionalOnly", as_userId, as_localIp, as_remoteIp, lccr_datos);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public TipoDocumental findByIdTipoDocumental(
	    TipoDocumental atd_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		TipoDocumental ltd_datos;

		lsw_watch     = Logger.getNewStopWatch();
		ltd_datos     = getReferenceBusiness().findByIdTipoDocumental(atd_param);

		Logger.log(lsw_watch, "ReferenceBean", "findByIdTipoDocumental", as_userId, as_localIp, as_remoteIp, null);

		return ltd_datos;
	}

	/** {@inheritdoc} */
	public Collection<AccEntidadExterna> findByIdTipoOficinaActivoEntidadExenta(
	    String as_idTipoOficina, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<AccEntidadExterna> lcaee_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcaee_datos     = getReferenceBusiness().findByIdTipoOficinaActivoEntidadExenta(as_idTipoOficina);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findByIdTipoOficinaActivoEntidadExenta", as_userId, as_localIp, as_remoteIp,
		    lcaee_datos
		);

		return lcaee_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRecepcion> findByProcedencia(TipoRecepcion atr_parametros)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TipoRecepcion> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findByProcedencia(atr_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findByProcendencia", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<CalidadSolicitante> findCalidadSolicitante(boolean ab_bool)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<CalidadSolicitante> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findCalidadSolicitante(ab_bool);

		Logger.log(lsw_watch, "ReferenceBean", "findCalidadSolicitante", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<CalidadSolicitante> findCalidadSolicitanteEntrega(
	    String as_userId, String as_localIp, String as_remoteIp, boolean ab_b
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<CalidadSolicitante> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findCalidadSolicitanteEntrega(ab_b);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findCalidadSolicitanteEntrega", as_userId, as_localIp, as_remoteIp, lcidt_datos
		);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<CalidadSolicitante> findCalidadSolicitanteTraslado()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<CalidadSolicitante> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findCalidadSolicitanteTraslado();

		Logger.log(lsw_watch, "ReferenceBean", "findCalidadSolicitanteTraslado", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<CamposConsulta> findCamposConsulta(
	    boolean ab_activo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<CamposConsulta> lcrc_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcrc_datos     = getReferenceBusiness().findCamposConsulta(ab_activo);

		Logger.log(lsw_watch, "ReferenceBean", "findCamposConsulta", as_userId, as_localIp, as_remoteIp, lcrc_datos);

		return lcrc_datos;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote#findCirculoRegistralByTurnoEtapa(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Collection<CirculoRegistral> findCirculoRegistralByTurnoEtapa(
	    String as_idTurno, String as_idEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lcr_return;

		lsw_watch      = Logger.getNewStopWatch();
		lcr_return     = getReferenceBusiness().findCirculoRegistralByTurnoEtapa(as_idTurno, as_idEtapa);

		Logger.log(
		    lsw_watch, "ReferenceBean", "verificarProcesoTrasladoPorIdTurno", as_userId, as_localIp, as_remoteIp,
		    lcr_return
		);

		return lcr_return;
	}

	/** {@inheritdoc} */
	public Constantes findConstantesById(String as_parametro)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getReferenceBusiness().findConstantesById(as_parametro);

		Logger.log(lsw_watch, "ReferenceBean", "findConstantesById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public Collection<Coordenada> findCoordenada()
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Coordenada> lcc_c;

		lsw_watch     = Logger.getNewStopWatch();
		lcc_c         = getReferenceBusiness().findCoordenada();

		Logger.log(lsw_watch, "ReferenceBean", "findCoordenada", null, null, null, lcc_c);

		return lcc_c;
	}

	/** {@inheritdoc} */
	public String findDecisionCalificacion(Trazabilidad as_s)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ls_datos = getReferenceBusiness().findDecisionCalificacion(as_s);

		Logger.log(lsw_watch, "ReferenceBean", "findDecisionCalificacion", null, null, null, null);

		return ls_datos;
	}

	/** {@inheritdoc} */
	public Departamento findDepartamentById(Departamento ad_parametros)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		Departamento ld_datos;

		lsw_watch     = Logger.getNewStopWatch();
		ld_datos      = getReferenceBusiness().findDepartamentById(ad_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findDepartamentById", null, null, null, null);

		return ld_datos;
	}

	/** {@inheritdoc} */
	public Collection<Departamento> findDepartaments(Departamento ad_parametros)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<Departamento> lcd_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcd_datos     = getReferenceBusiness().findDepartaments(ad_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findDepartaments", null, null, null, lcd_datos);

		return lcd_datos;
	}

	/** {@inheritdoc} */
	public Map<String, Object> findDetalleActo(String as_idActo)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Map<String, Object> lmso_return;

		lsw_watch       = Logger.getNewStopWatch();
		lmso_return     = getReferenceBusiness().findDetalleActo(as_idActo);

		Logger.log(lsw_watch, "ReferenceBean", "findDetalleActo", null, null, null, null);

		return lmso_return;
	}

	/** {@inheritdoc} */
	public Collection<MotivoTramite> findDistinctByIdProcesoAndIdEtapa(
	    String as_idProceso, long al_idEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<MotivoTramite> lce_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lce_datos     = getReferenceBusiness().findDistinctByIdProcesoAndIdEtapa(as_idProceso, al_idEtapa);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findDistinctByIdProcesoAndIdEtapa", as_userId, as_localIp, as_remoteIp,
		    lce_datos
		);

		return lce_datos;
	}

	/** {@inheritdoc} */
	public Collection<AccEntidadExterna> findEntidadExternaByNombreLike(String as_nombreEntidad)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<AccEntidadExterna> lcaee_return;

		lsw_watch        = Logger.getNewStopWatch();
		lcaee_return     = getReferenceBusiness().findEntidadExternaByNombreLike(as_nombreEntidad);

		Logger.log(lsw_watch, "ReferenceBean", "findEntidadExternaByNombreLike", null, null, null, lcaee_return);

		return lcaee_return;
	}

	/** {@inheritdoc} */
	public Collection<AccEntidadExterna> findEntidadesExternasActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<AccEntidadExterna> lee_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lee_datos     = getReferenceBusiness().findAllEntidadesExternasActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findAllEntidadesExternasActivos", as_userId, as_localIp, as_remoteIp, lee_datos
		);

		return lee_datos;
	}

	/** {@inheritdoc} */
	public EstadoActividad findEstadoActividad(String as_estado)
	    throws B2BException
	{
		EstadoActividad lea_return;
		StopWatch       lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lea_return     = getReferenceBusiness().findEstadoActividad(as_estado);

		Logger.log(lsw_watch, "ReferenceBean", "findEstadoActividad", null, null, null, null);

		return lea_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<EstadoActividad> findEstadoActividadActivo(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<EstadoActividad> lce_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lce_datos     = getReferenceBusiness().findEstadoActividadActivo();

		Logger.log(lsw_watch, "ReferenceBean", "findAllEtapasActivo", as_userId, as_localIp, as_remoteIp, lce_datos);

		return lce_datos;
	}

	/** {@inheritdoc} */
	public Collection<EstadoAnotacion> findEstadoAnotacion()
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<EstadoAnotacion> lcea_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcea_datos     = getReferenceBusiness().findEstadoAnotacion();

		Logger.log(lsw_watch, "ReferenceBean", "findEstadoAnotacion", null, null, null, lcea_datos);

		return lcea_datos;
	}

	/** {@inheritdoc} */
	public Collection<EstadoPredio> findEstadoPredios()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<EstadoPredio> lcep_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcep_datos     = getReferenceBusiness().findEstadoPredios();

		Logger.log(lsw_watch, "ReferenceBean", "findEstadoPredios", null, null, null, lcep_datos);

		return lcep_datos;
	}

	/** {@inheritdoc} */
	public Collection<InteresadoNaturalGenero> findGeneros()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<InteresadoNaturalGenero> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findGeneros();

		Logger.log(lsw_watch, "ReferenceBean", "findGeneros", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public String findIfMotivoIsReasignacion(Trazabilidad as_s)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ls_datos = getReferenceBusiness().findIfMotivoIsReasignacion(as_s);

		Logger.log(lsw_watch, "ReferenceBean", "findIfMotivoIsReasignacion", null, null, null, null);

		return ls_datos;
	}

	/** {@inheritdoc} */
	public Map<String, Object> findInfoAnotacionAcc(AnotacionPredio lap_anotacion)
	    throws B2BException
	{
		Map<String, Object> lmso_return;
		StopWatch           lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lmso_return     = getReferenceBusiness().findInfoAnotacionAcc(lap_anotacion);

		Logger.log(lsw_watch, "ReferenceBean", "findInfoAnotacionAcc", null, null, null, null);

		return lmso_return;
	}

	/** {@inheritdoc} */
	public Map<String, Object> findInfoAnotacionBng(AnotacionPredio lap_anotacion)
	    throws B2BException
	{
		Map<String, Object> lmso_return;
		StopWatch           lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lmso_return     = getReferenceBusiness().findInfoAnotacionBng(lap_anotacion);

		Logger.log(lsw_watch, "ReferenceBean", "findInfoAnotacionBng", null, null, null, null);

		return lmso_return;
	}

	/** {@inheritdoc} */
	public InteresadoDocumentoTipo findInteresadoDocumentoTipoById(InteresadoDocumentoTipo ate_parametros)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		InteresadoDocumentoTipo lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findInteresadoDocumentoTipoById(ate_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findInteresadoDocumentoTipoById", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<LetraEje> findLetraEje()
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<LetraEje> lcle_le;

		lsw_watch     = Logger.getNewStopWatch();
		lcle_le       = getReferenceBusiness().findLetraEje();

		Logger.log(lsw_watch, "ReferenceBean", "findLetraEje", null, null, null, lcle_le);

		return lcle_le;
	}

	/** {@inheritdoc} */
	public Collection<LibroAntiguoSistema> findLibroAntiguoSistema()
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<LibroAntiguoSistema> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findLibroAntiguoSistema();

		Logger.log(lsw_watch, "ReferenceBean", "findLibroAntiguoSistema", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<LineaProduccion> findLineasProduccionByEtapa(
	    long al_etapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<LineaProduccion> lclp_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lclp_datos     = getReferenceBusiness().findLineasProduccionByEtapa(al_etapa);

		Logger.log(lsw_watch, "ReferenceBean", "findAllLineasProduccion", null, null, null, lclp_datos);

		return lclp_datos;
	}

	/** {@inheritdoc} */
	public LineaProduccion findLineasProduccionByNomenclatura(
	    String as_nomenclatura, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		LineaProduccion llp_datos;

		lsw_watch     = Logger.getNewStopWatch();
		llp_datos     = getReferenceBusiness().findLineasProduccionByNomenclatura(as_nomenclatura);

		Logger.log(lsw_watch, "ReferenceBean", "findLineasProduccionByNomenclatura", null, null, null, null);

		return llp_datos;
	}

	/** {@inheritdoc} */
	public PredioSegregado findMatriculaDefinitiva(PredioSegregado aps_ps)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		PredioSegregado lps_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lps_datos     = getReferenceBusiness().findMatriculaDefinitiva(aps_ps);

		Logger.log(lsw_watch, "ReferenceBean", "findMatriculaDefinitiva", null, null, null, null);

		return lps_datos;
	}

	/** {@inheritdoc} */
	public Collection<MedidaArea> findMedidaAreaById(String as_arg)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<MedidaArea> lcma_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcma_datos     = getReferenceBusiness().findMedidaAreaById(as_arg);

		Logger.log(lsw_watch, "ReferenceBean", "findMedidaAreaById", null, null, null, lcma_datos);

		return lcma_datos;
	}

	/** {@inheritdoc} */
	public Collection<MotivoTramite> findMotivoByIdProcesoIdSubProcesoAndIdEtapa(
	    String as_idProceso, String as_idSubProceso, long al_idEtapa, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<MotivoTramite> lce_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lce_datos     = getReferenceBusiness()
				                .findMotivoByIdProcesoIdSubProcesoAndIdEtapa(as_idProceso, as_idSubProceso, al_idEtapa);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findMotivoByIdProcesoIdSubProcesoAndIdEtapa", as_userId, as_localIp,
		    as_remoteIp, lce_datos
		);

		return lce_datos;
	}

	/** {@inheritdoc} */
	public Collection<MotivoTramite> findMotivosByEtapa(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<MotivoTramite> lccr_ccr;

		lsw_watch     = Logger.getNewStopWatch();
		lccr_ccr      = getReferenceBusiness()
				                .findMotivosByEtapa(
				    as_etapa, as_idTurnoHistoria, ab_isRepConstancia, ab_isCalificacion
				);

		Logger.log(lsw_watch, "ReferenceBean", "findMotivosByEtapa", null, null, null, lccr_ccr);

		return lccr_ccr;
	}

	/** {@inheritdoc} */
	public Collection<MotivoTramite> findMotivosByEtapa(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion,
	    List<Map<String, Object>> lmso_actos
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<MotivoTramite> lccr_ccr;

		lsw_watch     = Logger.getNewStopWatch();
		lccr_ccr      = getReferenceBusiness()
				                .findMotivosByEtapa(
				    as_etapa, as_idTurnoHistoria, ab_isRepConstancia, ab_isCalificacion, lmso_actos
				);

		Logger.log(lsw_watch, "ReferenceBean", "findMotivosByEtapa", null, null, null, lccr_ccr);

		return lccr_ccr;
	}

	/** {@inheritdoc} */
	public Collection<MotivoTramite> findMotivosByEtapa105(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<MotivoTramite> lccr_ccr;

		lsw_watch     = Logger.getNewStopWatch();
		lccr_ccr      = getReferenceBusiness()
				                .findMotivosByEtapa105(
				    as_etapa, as_idTurnoHistoria, ab_isRepConstancia, ab_isCalificacion
				);

		Logger.log(lsw_watch, "ReferenceBean", "findMotivosByEtapa", null, null, null, lccr_ccr);

		return lccr_ccr;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipios(Municipio am_parametros)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Municipio> lcm_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcm_datos     = getReferenceBusiness().findMunicipios(am_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findMunicipios", null, null, null, lcm_datos);

		return lcm_datos;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipiosByCirculo(String as_parametros)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Municipio> lcm_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcm_datos     = getReferenceBusiness().findMunicipiosByCirculo(as_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findMunicipiosByCirculo", null, null, null, lcm_datos);

		return lcm_datos;
	}

	/**
	 * Método para encontrar la maxíma version
	 * de la tabla SDB_PNG_NATURALEZA_JURIDICA unicamente coleccion de actos 0841 y 0842.
	 *
	 * @return el valor de collection
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<NaturalezaJuridica> findNaturalezaJuridica0841and0842()
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<NaturalezaJuridica> lcnj_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcnj_datos     = getReferenceBusiness().findNaturalezaJuridica0841and0842();

		Logger.log(lsw_watch, "ReferenceBean", "findNaturalezaJuridica0841and0842", null, null, null, lcnj_datos);

		return lcnj_datos;
	}

	/** {@inheritdoc} */
	public NaturalezaJuridica findNaturalezaJuridicaById(String as_idNaturalezJuridica)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		NaturalezaJuridica lnj_return;

		lsw_watch      = Logger.getNewStopWatch();
		lnj_return     = getReferenceBusiness().findNaturalezaJuridicaById(as_idNaturalezJuridica);

		Logger.log(lsw_watch, "ReferenceBean", "findNaturalezaJuridicaById", null, null, null, null);

		return lnj_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<OficinaOrigen> findOficinaOrigen(OficinaOrigen oficina)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<OficinaOrigen> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findOficinaOrigen(oficina);

		Logger.log(lsw_watch, "ReferenceBean", "findOficinaOrigen", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public OficinaOrigen findOficinaOrigenById(String as_parametros)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		OficinaOrigen lcm_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcm_datos     = getReferenceBusiness().findOficinaOrigenById(as_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findOficinaOrigenById", null, null, null, null);

		return lcm_datos;
	}

	/** {@inheritdoc} */
	public Collection<Pais> findPaises(boolean ab_activo, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		Collection<Pais> lcp_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcp_datos     = getReferenceBusiness().findPaises(ab_activo);

		Logger.log(lsw_watch, "ReferenceBean", "findPaises", as_userId, as_localIp, as_remoteIp, lcp_datos);

		return lcp_datos;
	}

	/** {@inheritdoc} */
	public Collection<PredioRegistro> findPredioRegistro()
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<PredioRegistro> lccr_ccr;

		lsw_watch     = Logger.getNewStopWatch();
		lccr_ccr      = getReferenceBusiness().findPredioRegistroAll();

		Logger.log(lsw_watch, "ReferenceBean", "findPredioRegistro", null, null, null, lccr_ccr);

		return lccr_ccr;
	}

	/** {@inheritdoc} */
	public PredioRegistro findPredioRegistroByMatriculaCirculo(
	    String as_idCirculo, Long al_idMatricula, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		PredioRegistro lpr_pr;

		lsw_watch     = Logger.getNewStopWatch();
		lpr_pr        = getReferenceBusiness().findPredioRegistroByMatriculaCirculo(as_idCirculo, al_idMatricula);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findPredioRegistroByMatriculaCirculo", as_userId, as_localIp, as_remoteIp, null
		);

		return lpr_pr;
	}

	/** {@inheritdoc} */
	public Collection<Proceso> findProcesos(boolean ab_b)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Proceso> lcpr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcpr_datos     = getReferenceBusiness().findProcesos(ab_b);

		Logger.log(lsw_watch, "ReferenceBean", "findProcesos", null, null, null, null);

		return lcpr_datos;
	}

	/** {@inheritdoc} */
	public Collection<ProcesoAutomatico> findProcesosAutomaticos(
	    boolean ab_activo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<ProcesoAutomatico> lcpa_cpa;

		lsw_watch     = Logger.getNewStopWatch();
		lcpa_cpa      = getReferenceBusiness().findProcesosAutomaticos(ab_activo);

		Logger.log(lsw_watch, "ReferenceBean", "findProcesosAutomaticos", as_userId, as_localIp, as_remoteIp, lcpa_cpa);

		return lcpa_cpa;
	}

	/** {@inheritdoc} */
	public Regional findRegionalById(Regional ar_parametros, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Regional  lr_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lr_datos      = getReferenceBusiness().findRegionalById(ar_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findRegionalById", as_userId, as_localIp, as_remoteIp, null);

		return lr_datos;
	}

	/** {@inheritdoc} */
	public Collection<ResultadoConsulta> findResultadoConsulta(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<ResultadoConsulta> lcrc_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcrc_datos     = getReferenceBusiness().findResultadoConsulta();

		Logger.log(lsw_watch, "ReferenceBean", "findResultadoConsulta", as_userId, as_localIp, as_remoteIp, lcrc_datos);

		return lcrc_datos;
	}

	/** {@inheritdoc} */
	public Collection<InteresadoDocumentoTipo> findRrr(String as_idActo)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findRrr(as_idActo);

		Logger.log(lsw_watch, "ReferenceBean", "findRrr", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public InteresadoDocumentoTipo findRrrById(String as_idDoc)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		InteresadoDocumentoTipo lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findRrrById(as_idDoc);

		Logger.log(lsw_watch, "ReferenceBean", "findRrr", null, null, null, null);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Solicitud findSolicitudById(String as_idSolicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud ls_return;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getReferenceBusiness().findSolicitudById(as_idSolicitud);

		Logger.log(lsw_watch, "ReferenceBean", "findSolicitudById", null, null, null, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public Collection<Subproceso> findSubprocesos(boolean ab_activo)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Subproceso> lcpr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcpr_datos     = getReferenceBusiness().findSubprocesos(ab_activo);

		Logger.log(lsw_watch, "ReferenceBean", "findSubprocesos", null, null, null, null);

		return lcpr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Subproceso> findSubprocesosActivosByProceso(
	    Subproceso as_subproceso, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Subproceso> lcpr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcpr_datos     = getReferenceBusiness().findSubprocesosActivosByProceso(as_subproceso);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findSubprocesosActivosByProceso", as_userId, as_localIp, as_remoteIp,
		    lcpr_datos
		);

		return lcpr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Subproceso> findSubprocesosByProceso(
	    Subproceso as_subproceso, boolean ab_orden, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Subproceso> lcpr_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcpr_datos     = getReferenceBusiness().findSubprocesosByProceso(as_subproceso, ab_orden);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findSubprocesosByProcesos", as_userId, as_localIp, as_remoteIp, lcpr_datos
		);

		return lcpr_datos;
	}

	/** {@inheritdoc} */
	public Collection<Subproceso> findSubprocesosByProcesoSolicitudCert(
	    Subproceso as_subproceso, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Subproceso> lcs_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcs_datos     = getReferenceBusiness().findSubprocesosByProcesoSolicitudCert(as_subproceso);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findSubprocesosByProcesoSolicitudCert", as_userId, as_localIp, as_remoteIp,
		    lcs_datos
		);

		return lcs_datos;
	}

	/** {@inheritdoc} */
	public String findTipificacionTurno(String as_s)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ls_datos = getReferenceBusiness().findTipificacionTurno(as_s);

		Logger.log(lsw_watch, "ReferenceBean", "findTipificacionTurno", null, null, null, null);

		return ls_datos;
	}

	/**
	 * Método para consultar un registro de tipo acto basado en su identificador.
	 *
	 * @param as_arg de as arg
	 * @return Colección de TipoActo con la información solicitada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActo> findTipoActoById(String as_arg)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lcta_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcta_datos     = getReferenceBusiness().findTipoActoById(as_arg);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoActoById", null, null, null, lcta_datos);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<TipoAdquisicion> findTipoAdquisicion(String as_s)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TipoAdquisicion> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findTipoAdquisicion(as_s);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoDocumentoPublico", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/**
	 * Método para consultar un registro de tipo Adquisicion basado en su identificador.
	 *
	 * @param as_arg de as arg
	 * @return Colección de Tipo Adquisicion con la información solicitada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoAdquisicion findTipoAdquisicionById(String as_arg)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		TipoAdquisicion lcta_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcta_datos     = getReferenceBusiness().findTipoAdquisicionById(as_arg);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoAdquisicionById", null, null, null, null);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public Collection<InteresadoDocumentoTipo> findTipoDocumento()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findTipoDocumento();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoDocumento", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<InteresadoDocumentoTipo> findTipoDocumentoActivo()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findTipoDocumentoActivo();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoDocumentoActivo", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublico()
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<TipoDocumentoPublico> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findTipoDocumentoPublico();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoDocumentoPublico", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublico(String as_s)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<TipoDocumentoPublico> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findTipoDocumentoPublico(as_s);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoDocumentoPublico", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoEje> findTipoEje()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<TipoEje> lcte_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcte_datos     = getReferenceBusiness().findTipoEje();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoEje", null, null, null, null);

		return lcte_datos;
	}

	/** {@inheritdoc} */
	public TipoEje findTipoEjeById(TipoEje ate_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		TipoEje   lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findTipoEjeById(ate_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoEjeById", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoEje> findTipoEjeByTipoPredio(String as_tipoPredio)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<TipoEje> lcte_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcte_datos     = getReferenceBusiness().findTipoEjeByTipoPredio(as_tipoPredio);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoEjeByTipoPredio", null, null, null, null);

		return lcte_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoEntidad> findTipoEntidad(Apertura aod_od)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoEntidad> lcte_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcte_datos     = getReferenceBusiness().findTipoEntidad(aod_od);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoEntidad", null, null, null, lcte_datos);

		return lcte_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoEntidad> findTipoEntidad(boolean ab_activo)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoEntidad> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findTipoEntidad(ab_activo);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoEntidad", null, null, null, lcidt_datos);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public TipoEntidad findTipoEntidadById(TipoEntidad ate_te)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		TipoEntidad lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findTipoEntidadById(ate_te);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoEntidadById", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<AccEntidadExterna> findTipoEntidadExterna(
	    String as_idTipoEntidad, boolean ab_orden, boolean ab_activo
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<AccEntidadExterna> lcaee_return;

		lsw_watch        = Logger.getNewStopWatch();
		lcaee_return     = getReferenceBusiness().findTipoEntidadExterna(as_idTipoEntidad, ab_orden, ab_activo);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoEntidadExterna", null, null, null, lcaee_return);

		return lcaee_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<TipoOficina> findTipoOficina()
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoOficina> lcto_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcto_datos     = getReferenceBusiness().findTipoOficina();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoOficina", null, null, null, lcto_datos);

		return lcto_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoOficina> findTipoOficina(Documento aod_od)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoOficina> lcto_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcto_datos     = getReferenceBusiness().findTipoOficina(aod_od);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoOficina", null, null, null, lcto_datos);

		return lcto_datos;
	}

	/** {@inheritdoc} */
	public TipoOficina findTipoOficinaById(TipoOficina ato_to)
	    throws B2BException
	{
		StopWatch   lsw_watch;
		TipoOficina lto_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lto_datos     = getReferenceBusiness().findTipoOficinaById(ato_to);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoOficinaById", null, null, null, null);

		return lto_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoPersona> findTipoPersona()
	    throws B2BException
	{
		StopWatch               lsw_watch;
		Collection<TipoPersona> lcidt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcidt_datos     = getReferenceBusiness().findTipoPersona();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoPersona", null, null, null, null);

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public Collection<PredioTipo> findTipoPredio(
	    boolean ab_activo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<PredioTipo> lccr_ccr;

		lsw_watch     = Logger.getNewStopWatch();
		lccr_ccr      = getReferenceBusiness().findTipoPredio(ab_activo);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoPredio", as_userId, as_localIp, as_remoteIp, lccr_ccr);

		return lccr_ccr;
	}

	/** {@inheritdoc} */
	public Collection<TipoRrr> findTipoRRR()
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<TipoRrr> lctr_tr;

		lsw_watch     = Logger.getNewStopWatch();
		lctr_tr       = getReferenceBusiness().findTipoRRR();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoRRR", null, null, null, lctr_tr);

		return lctr_tr;
	}

	/** {@inheritdoc} */
	public String findTipoRepartoByEtapa(long as_etapa)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_dato;

		lsw_watch     = Logger.getNewStopWatch();
		ls_dato       = getReferenceBusiness().findTipoRepartoByEtapa(as_etapa);

		Logger.log(lsw_watch, "ReferenceBean", "findTipoRepartoByEtapa", null, null, null, null);

		return ls_dato;
	}

	/** {@inheritdoc} */
	public Collection<TipoRequiereMatricula> findTipoRequiereMatriculaActivos(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		Collection<TipoRequiereMatricula> lctrm_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lctrm_datos     = getReferenceBusiness().findTipoRequiereMatriculaActivos();

		Logger.log(
		    lsw_watch, "ReferenceBean", "findTipoRequiereMatriculaActivos", as_userId, as_localIp, as_remoteIp,
		    lctrm_datos
		);

		return lctrm_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoRequiereMatricula> findTipoRequiereMatriculaRegistro(
	    boolean ab_requiereMatricula, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		Collection<TipoRequiereMatricula> lctrm_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lctrm_datos     = getReferenceBusiness().findTipoRequiereMatriculaRegistro(ab_requiereMatricula);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findTipoRequiereMatriculaRegistro", as_userId, as_localIp, as_remoteIp,
		    lctrm_datos
		);

		return lctrm_datos;
	}

	/** {@inheritdoc} */
	public Collection<TipoUsoSuelo> findTipoUsoSuelo()
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<TipoUsoSuelo> lccr_ccr;

		lsw_watch     = Logger.getNewStopWatch();
		lccr_ccr      = getReferenceBusiness().findTipoUsoSuelo();

		Logger.log(lsw_watch, "ReferenceBean", "findTipoUsoSuelo", null, null, null, lccr_ccr);

		return lccr_ccr;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findTiposActosCodigo4Y5Digitos(
	    String as_userId, String as_localIp, String as_remoteIp, boolean ab_orderBy
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lcta_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcta_datos     = getReferenceBusiness().findTiposActosCodigo4Y5Digitos(ab_orderBy);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findTiposActosCodigo4Y5Digitos", as_userId, as_localIp, as_remoteIp, lcta_datos
		);

		return lcta_datos;
	}

	/** {@inheritdoc} */
	public TurnoHistoria findTurnoHistoriaByIdTurno(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_return;

		lsw_watch      = Logger.getNewStopWatch();
		lth_return     = getReferenceBusiness().findTurnoHistoriaByIdTurno(ath_turnoHistoria);

		Logger.log(lsw_watch, "ReferenceBean", "findTurnoHistoriaByIdTurno", null, null, null, null);

		return lth_return;
	}

	/** {@inheritdoc} */
	public TurnoHistoria findTurnoHistoriaByid(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_return;

		lsw_watch      = Logger.getNewStopWatch();
		lth_return     = getReferenceBusiness().findTurnoHistoriaByid(ath_turnoHistoria);

		Logger.log(lsw_watch, "ReferenceBean", "findTurnoHistoriaByid", null, null, null, null);

		return lth_return;
	}

	/** {@inheritdoc} */
	public TurnoHistoria findTurnoHistoriaByid(Long al_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_return;

		lsw_watch      = Logger.getNewStopWatch();
		lth_return     = getReferenceBusiness().findTurnoHistoriaByid(al_idTurnoHistoria);

		Logger.log(lsw_watch, "ReferenceBean", "findTurnoHistoriaByid", null, null, null, null);

		return lth_return;
	}

	/** {@inheritdoc} */
	public Turno findTurnoNombreEtapaById(Turno at_turno, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Turno     lt_turno;

		lsw_watch     = Logger.getNewStopWatch();
		lt_turno      = getReferenceBusiness().findTurnoNombreEtapaById(at_turno);

		Logger.log(lsw_watch, "ReferenceBean", "findTurnoNombreEtapaById", as_userId, null, null, null);

		return lt_turno;
	}

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
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    lccr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccr_datos = getReferenceBusiness().findUrlCatedra(as_urlBachue);

		Logger.log(lsw_watch, "ReferenceBean", "findUrlCatedra", as_userId, localIpAddress, remoteIpAddress, null);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Usuario findUserActiveById(Usuario ate_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Usuario   lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findUserActiveById(ate_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findUserActiveById", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Usuario findUserById(Usuario ate_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Usuario   lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findUserById(ate_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "findUserById", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<Usuario> findUsersByRolOripActivos(
	    String as_idCirculo, String as_idRol, boolean ab_apoyoNacional, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Usuario> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness().findUsersByRolOripActivos(as_idCirculo, as_idRol, ab_apoyoNacional);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findUsersByRolOripActivos", as_userId, as_localIp, as_remoteIp, lte_datos
		);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<Usuario> findUsersLineaProduccionByRolOripActivos(
	    String as_idCirculo, String as_idRol, String ls_idLineaProduccion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Usuario> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReferenceBusiness()
				                .findUsersLineaProduccionByRolOripActivos(as_idCirculo, as_idRol, ls_idLineaProduccion);

		Logger.log(
		    lsw_watch, "ReferenceBean", "findUsersLineaProduccionByRolOripActivos", as_userId, as_localIp, as_remoteIp,
		    lte_datos
		);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<Vereda> findVeredas(Vereda am_parametros, boolean ab_param)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<Vereda> lcm_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lcm_datos     = getReferenceBusiness().findVeredas(am_parametros, ab_param);

		Logger.log(lsw_watch, "ReferenceBean", "findVeredas", null, null, null, lcm_datos);

		return lcm_datos;
	}

	/** {@inheritdoc} */
	public long funcionCalcularDiasFechas(FechaVenceTerminosUI afvtui_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		long      ll_dato;

		lsw_watch     = Logger.getNewStopWatch();

		ll_dato = getReferenceBusiness().funcionCalcularDiasFechas(afvtui_parametros);

		Logger.log(lsw_watch, "ReferenceBean", "funcionCalcularDiasFechas", null, null, null, null);

		return ll_dato;
	}

	/** {@inheritdoc} */
	public String generarExpediente(String as_userId, String as_remoteIp, String as_localIp, Turno at_turno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getReferenceBusiness().generarExpediente(as_userId, as_remoteIp, at_turno);

		Logger.log(lsw_watch, "ReferenceBean", "generarExpediente", as_userId, as_localIp, as_remoteIp, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public void insertUser(Usuario au_usuario, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReferenceBusiness().insertUser(au_usuario, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ReferenceBean", "insertUser", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<CriteriosDeBusqueda> listarTiposCriteriosBusqueda(
	    String as_userId, String as_localIp, String as_remoteIp, String as_idTipoCriterioBusqueda, String as_idProceso,
	    String as_procesoBusqueda
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<CriteriosDeBusqueda> lctd_data;

		lsw_watch     = Logger.getNewStopWatch();
		lctd_data     = getReferenceBusiness()
				                .listarTiposCriteriosBusqueda(
				    as_idTipoCriterioBusqueda, as_idProceso, as_procesoBusqueda
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "listarTiposCriteriosBusqueda", as_userId, as_localIp, as_remoteIp, lctd_data
		);

		return lctd_data;
	}

	/** {@inheritdoc} */
	public TurnoHistoria obtenerAnteriorTurnoHistoria(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_return;

		lsw_watch      = Logger.getNewStopWatch();
		lth_return     = getReferenceBusiness().obtenerAnteriorTurnoHistoria(ath_turnoHistoria);

		Logger.log(lsw_watch, "ReferenceBean", "findTurnoHistoriaByid", null, null, null, null);

		return lth_return;
	}

	/** {@inheritDoc} */
	public String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_constante;

		lsw_watch        = Logger.getNewStopWatch();
		ls_constante     = getReferenceBusiness().obtenerCaracterConstante(as_idConstante);

		Logger.log(lsw_watch, "ReferenceBean", "obtenerCaracterConstante", null, null, null, null);

		return ls_constante;
	}

	/** {@inheritdoc} */
	public Collection<CausalAprobacionDevolucion> obtenerCausalesAprobacionDevolucionActivos(
	    CausalAprobacionDevolucion acad_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		Collection<CausalAprobacionDevolucion> lccda_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lccda_datos     = getReferenceBusiness().obtenerCausalesAprobacionDevolucionActivos(acad_param);

		Logger.log(
		    lsw_watch, "ReferenceBean", "obtenerCausalesAprobacionDevolucionActivos", as_userId, as_localIp, as_remoteIp,
		    lccda_datos
		);

		return lccda_datos;
	}

	/** {@inheritDoc} */
	public Collection<String> obtenerIdConstanesPorCaracterIdLikeCaracter(String as_IdLike, String as_caracter)
	    throws B2BException
	{
		Collection<String> lcs_idConstantes;
		StopWatch          lsw_watch;

		lsw_watch            = Logger.getNewStopWatch();
		lcs_idConstantes     = getReferenceBusiness().obtenerIdConstanesPorCaracterIdLikeCaracter(
			    as_IdLike, as_caracter
			);

		Logger.log(lsw_watch, "ReferenceBean", "obtenerIdConstanesPorCaracterIdLikeCaracter", null, null, null, null);

		return lcs_idConstantes;
	}

	/** {@inheritdoc} */
	public void updateCaracter(String as_id, String as_caracter, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReferenceBusiness().updateCaracter(as_id, as_caracter, as_userId);

		Logger.log(lsw_watch, "ReferenceBean", "updateCaracter", null, null, null, null);
	}

	/** {@inheritdoc} */
	@Override
	public void updateUser(Usuario au_usuario, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReferenceBusiness().updateUser(au_usuario, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ReferenceBean", "updateUser", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void validarTipoDocumental(
	    String as_idProceso, Collection<AccCompletitudDocumental> acacd_salvar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReferenceBusiness().validarTipoDocumental(as_idProceso, acacd_salvar, as_userId, as_localIp);

		Logger.log(lsw_watch, "ReferenceBean", "validarTipoDocumental", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public boolean verificarAutenticacionSegundoFactor(String as_parametro)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getReferenceBusiness().verificarAutenticacionSegundoFactor(as_parametro);

		Logger.log(lsw_watch, "ReferenceBean", "verificarAutenticacionSegundoFactor", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public boolean verificarProcesoTrasladoPorIdTurno(String as_idTurno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_retorno;

		lsw_watch      = Logger.getNewStopWatch();
		lb_retorno     = getReferenceBusiness().verificarProcesoTrasladoPorIdTurno(as_idTurno);

		Logger.log(lsw_watch, "ReferenceBean", "verificarProcesoTrasladoPorIdTurno", null, null, null, null);

		return lb_retorno;
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
}
