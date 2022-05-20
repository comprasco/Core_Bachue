package com.bachue.snr.prosnr01.web.bean.reference;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.objectDataBase.UserObjects;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarifaRegistral;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
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
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoActividad;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoAutomatico;
import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDecisionRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoIntegracionGobernacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


/**
 * Clase que contiene todos las propiedades BeanReference.
 *
 * @author  garias
 * Fecha de Creacion: 30/07/2020
 */
@ApplicationScoped
@ManagedBean(name = "beanReference")
public class BeanReference extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5773402671525751676L;

	/** Propiedad icaa adquisicion activo */
	private Collection<TipoAdquisicion> icaa_adquisicionActivo;

	/** Propiedad icaee entidad externa. */
	private Collection<AccEntidadExterna> icaee_entidadExterna;

	/** Propiedad icc coordenada. */
	private Collection<Coordenada> icc_Coordenada;

	/** Propiedad icc cargar tipo consulta. */
	private Collection<Consultas> icc_cargarTipoConsulta;

	/** Propiedad icc coordenada activo. */
	private Collection<Coordenada> icc_coordenadaActivo;

	/** Propiedad icc etapa activo. */
	private Collection<Etapa> icc_etapaActivo;

	/** Propiedad icc motivos consulta. */
	private Collection<Constantes> icc_motivosConsulta;

	/** Propiedad iccc campos consulta. */
	private Collection<CamposConsulta> iccc_CamposConsulta;

	/** Propiedad iccnc causal negacion copias. */
	private Collection<CausalNegacionCopias> iccnc_causalNegacionCopias;

	/** Propiedad iccr circulo registral activo usuario. */
	private Collection<CirculoRegistral> iccr_circuloRegistralActivoUsuario;

	/** Propiedad ics calidad solicitante entrega. */
	private Collection<CalidadSolicitante> iccs_calidadSolicitanteEntrega;

	/** Propiedad iccs calidad solicitante indices. */
	private Collection<CalidadSolicitante> iccs_calidadSolicitanteIndices;

	/** Propiedad iccs in calidad solicitante indices. */
	private Collection<CalidadSolicitante> iccs_inCalidadSolicitanteIndices;

	/** Propiedad icdp documento publico */
	private Collection<TipoDocumentoPublico> icdp_documentoPublico;

	/** Propiedad icdpa documento publico activo */
	private Collection<TipoDocumentoPublico> icdpa_documentoPublicoActivo;

	/** Propiedad icdte datos tipo entidad */
	private Collection<TipoEntidad> icdte_datosTipoEntidad;

	/** Propiedad icdto datos tipo oficina */
	private Collection<TipoOficina> icdto_datosTipoOficina;

	/** Propiedad ice etapa activo by id. */
	private Collection<Etapa> ice_etapaActivoById;

	/** Propiedad icea estado actividad activo. */
	private Collection<EstadoActividad> icea_EstadoActividadActivo;

	/** Propiedad icea estado anotacion. */
	private Collection<EstadoAnotacion> icea_estadoAnotacion;

	/** Propiedad icep estado predio. */
	private Collection<EstadoPredio> icep_estadoPredio;

	/** Propiedad icfdp find documento publico */
	private Collection<InteresadoDocumentoTipo> icfdp_findDocumentoPublico;

	/** Propiedad icidt cargar documentos tipo. */
	private Collection<InteresadoDocumentoTipo> icidt_cargarDocumentosTipo;

	/** Propiedad icidt interesado documento tipo */
	private Collection<InteresadoDocumentoTipo> icidt_interesadoDocumentoTipo;

	/** Propiedad icing generos. */
	private Collection<InteresadoNaturalGenero> icing_generos;

	/** Propiedad iclas libro antiguo sistema. */
	private Collection<LibroAntiguoSistema> iclas_libroAntiguoSistema;

	/** Propiedad  icle letra eje. */
	private Collection<LetraEje> icle_letraEje;

	/** Propiedad icle letra eje activo. */
	private Collection<LetraEje> icle_letraEjeActivo;

	/** Propiedad icm municipios. */
	private Collection<Municipio> icm_municipiosPorPais;

	/** Propiedad icma medida area activo. */
	private Collection<MedidaArea> icma_medidaAreaActivo;

	/** Propiedad icnj naturaleza juridica 0841 and 0842. */
	private Collection<NaturalezaJuridica> icnj_naturalezaJuridica0841and0842;

	/** Propiedad ico opcion. */
	private Collection<Opcion> ico_opcion;

	/** Propiedad icp paises */
	private Collection<Pais> icp_paises;

	/** Propiedad icp plantillas activo. */
	private Collection<Plantilla> icp_plantillasActivo;

	/** Propiedad icp procesos */
	private Collection<Proceso> icp_procesos;

	/** Propiedad icpa procesos activos */
	private Collection<Proceso> icpa_procesosActivos;

	/** Propiedad icpa procesos automaticos */
	private Collection<ProcesoAutomatico> icpa_procesosAutomaticos;

	/** Propiedad icpabi procesos activos by id */
	private Collection<Proceso> icpabi_procesosActivosById;

	/** Propiedad icr regionales activos. */
	private Collection<Regional> icr_regionalesActivos;

	/** Propiedad icr  rol. */
	private Collection<Rol> icr_rol;

	/** Propiedad icrc resultado consulta */
	private Collection<ResultadoConsulta> icrc_resultadoConsulta;

	/** Propiedad ics recepcion documento. */
	private Collection<String> ics_recepcionDocumento;

	/** Propiedad ics subprocesos */
	private Collection<Subproceso> ics_subprocesos;

	/** Propiedad ics tipos consignacion. */
	private Collection<String> ics_tiposConsignacion;

	/** Propiedad ics tipos publicacion. */
	private Collection<String> ics_tiposPublicacion;

	/** Propiedad icv codigo 4 y 5 digitos */
	private Collection<TipoActo> icta_codigo4Y5Digitos;

	/** Propiedad icta tipo acto. */
	private Collection<TipoActo> icta_tiposActo;

	/** Propiedad icta tipos actos codigo */
	private Collection<TipoActo> icta_tiposActosCodigo;

	/** Propiedad ictcb tipo criterio busqueda */
	private Collection<CriteriosDeBusqueda> ictcb_tipoCriterioBusqueda;

	/** Propiedad ictd tipo documental. */
	private Collection<TipoDocumental> ictd_tipoDocumental;

	/** Propiedad ictd tipos documentales. */
	private Collection<TipoDocumental> ictd_tiposDocumentales;

	/** Propiedad ictdr tipo decision recurso. */
	private Collection<TipoDecisionRecurso> ictdr_tipoDecisionRecurso;

	/** Propiedad icte tipo eje */
	private Collection<TipoEje> icte_tipoEje;

	/** Propiedad ictea tipo entidad activo */
	private Collection<TipoEntidad> ictea_tipoEntidadActivo;

	/** Propiedad ics tipo oficina activo. */
	private Collection<TipoOficina> icto_tipoOficinaActivo;

	/** Propiedad icto tipo oficina activo by id. */
	private Collection<TipoOficina> icto_tipoOficinaActivoById;

	/** Propiedad ictp tipo persona */
	private Collection<TipoPersona> ictp_tipoPersona;

	/** Propiedad ictp tipo predio */
	private Collection<PredioTipo> ictp_tipoPredio;

	/** Propiedad ictr habilitado not. */
	private Collection<TipoRecepcion> ictr_habilitadoNot;

	/** Propiedad ictr habilitado not negat. */
	private Collection<TipoRecepcion> ictr_habilitadoNotNegat;

	/** Propiedad ictr habilitado rec. */
	private Collection<TipoRecepcion> ictr_habilitadoRec;

	/** Propiedad ictr procedencia. */
	private Collection<TipoRecepcion> ictr_procedencia;

	/** Propiedad ics tipo recurso activo. */
	private Collection<TipoRecurso> ictr_tipoRecursoByActivo;

	/** Propiedad ictr tipo rrr */
	private Collection<TipoRrr> ictr_tipoRrr;

	/** Propiedad ictrm requiere matriculas activos */
	private Collection<TipoRequiereMatricula> ictrm_requiereMatriculaActivos;

	/** Propiedad icttr tarifa registral */
	private Collection<TipoTarifaRegistral> icttr_tipoTarifaRegistral;

	/** Propiedad ictus tipo suelo */
	private Collection<TipoUsoSuelo> ictus_tipoUsoSuelo;

	/** Propiedad icuo nombres tablas. */
	private Collection<UserObjects> icuo_nombresTablas;

	/** Propiedad imbcc tipos consulta. */
	private Map<Boolean, Collection<Constantes>> imbcc_tiposConsulta;

	/** Propiedad imbccr circulo registral */
	private Map<Boolean, Collection<CirculoRegistral>> imbccr_circuloRegistral;

	/** Propiedad imbccr circulos registrales origen destino activos */
	private Map<Boolean, Collection<CirculoRegistral>> imbccr_circulosRegistralesOrigenDestinoActivos;

	/** Propiedad imbccs calidad solicitante */
	private Map<Boolean, Collection<CalidadSolicitante>> imbccs_calidadSolicitante;

	/** Propiedad imbcnj max version naturaleza juridica */
	private Map<Boolean, Collection<NaturalezaJuridica>> imbcnj_maxVersionNaturalezaJuridica;

	/** Propiedad imbcnj naturaleza jurídica */
	private Map<Boolean, Collection<NaturalezaJuridica>> imbcnj_naturalezaJuridica;

	/** Propiedad imbctig tipo integración gobernación */
	private Map<Boolean, Collection<TipoIntegracionGobernacion>> imbctig_tipoIntegracionGobernacion;

	/** Propiedad imcaee tipo entidad externa. */
	private Map<String, Collection<AccEntidadExterna>> imcaee_tipoEntidadExterna;

	/** Propiedad imscaee tipo entidad externa activo. */
	private Map<String, Collection<AccEntidadExterna>> imscaee_TipoEntidadExternaActivo;

	/** Propiedad imsccr todos circulos registrales */
	private Map<String, Collection<CirculoRegistral>> imsccr_todosCirculosRegistrales;

	/** Propiedad imscd departamento */
	private Map<String, Collection<Departamento>> imscd_departamentos;

	/** Propiedad imscm municipios. */
	private Map<String, Collection<Municipio>> imscm_municipios;

	/** Propiedad imscma medida area by id */
	private Map<String, Collection<MedidaArea>> imscma_medidaAreaById;

	/** Propiedad imscoo oficina origen. */
	private Map<String, Collection<OficinaOrigen>> imscoo_oficinaOrigen;

	/** Propiedad imscoo oficina origen by collection */
	private Map<String, Collection<OficinaOrigen>> imscoo_oficinaOrigenByIdCollection;

	/** Propiedad imscs subprocesos by proceso. */
	private Map<String, Collection<Subproceso>> imscs_subprocesosByProceso;

	/** Propiedad imscs subprocesos by proceso id. */
	private Map<String, Collection<Subproceso>> imscs_subprocesosByProcesoId;

	/** Propiedad complemento dirección by tipo predio */
	private Map<String, Collection<TipoEje>> imscte_complementoDireccionByTipoPredio;

	/** Propiedad imscte tipo eje by tipo predio. */
	private Map<String, Collection<TipoEje>> imscte_tipoEjeByTipoPredio;

	/** Propiedad imscto id tipo oficina. */
	private Map<String, Collection<TipoOficina>> imscto_idTipoOficina;

	/** Propiedad imscuo nombres columnas */
	private Map<String, Collection<UserObjects>> imscuo_nombresColumnas;

	/** Propiedad imscv veredas. */
	private Map<String, Collection<Vereda>> imscv_veredas;

	/** Propiedad imsa estado actividad */
	private Map<String, EstadoActividad> imsea_estadoActividad;

	/** Propiedad imsoo oficina origen by id */
	private Map<String, OficinaOrigen> imsoo_oficinaOrigenById;

	/** Propiedad imsta tipo acto. */
	private Map<String, TipoActo> imsta_tipoActo;

	/** Propiedad imste tipo entidad by id. */
	private Map<String, TipoEntidad> imste_tipoEntidadById;

	/** Propiedad imste tipo oficina by id. */
	private Map<String, TipoOficina> imsto_tipoOficinaById;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/**
	* Modifica el valor de adquisicion activo
	*
	* @param acaa_aa asigna el valor a la propiedad adquisicion activo
	*/
	public void setAdquisicionActivo(Collection<TipoAdquisicion> acaa_aa)
	{
		icaa_adquisicionActivo = acaa_aa;
	}

	/**
	 * Retorna el valor de adquisicion activo
	 *
	 * @return el valor de adquisicion activo
	 */
	public Collection<TipoAdquisicion> getAdquisicionActivo()
	{
		return icaa_adquisicionActivo;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	* Modifica el valor de calidad solicitante.
	*
	* @param ambccs_mbccs asigna el valor a la propiedad calidad solicitante
	*/
	public void setCalidadSolicitante(Map<Boolean, Collection<CalidadSolicitante>> ambccs_mbccs)
	{
		imbccs_calidadSolicitante = ambccs_mbccs;
	}

	/**
	 * Retorna el valor de calidad solicitante.
	 *
	 * @return el valor de calidad solicitante
	 */
	public Map<Boolean, Collection<CalidadSolicitante>> getCalidadSolicitante()
	{
		return imbccs_calidadSolicitante;
	}

	/**
	* Modifica el valor de calidad solicitante entrega.
	*
	* @param accs_ccs asigna el valor a la propiedad calidad solicitante entrega
	*/
	public void setCalidadSolicitanteEntrega(Collection<CalidadSolicitante> accs_ccs)
	{
		iccs_calidadSolicitanteEntrega = accs_ccs;
	}

	/**
	* Retorna el valor de calidad solicitante entrega.
	*
	* @return el valor de calidad solicitante entrega
	*/
	public Collection<CalidadSolicitante> getCalidadSolicitanteEntrega()
	{
		return iccs_calidadSolicitanteEntrega;
	}

	/**
	 * Modifica el valor de calidad solicitante indices.
	 *
	 * @param accs_ccs asigna el valor a la propiedad calidad solicitante indices.
	 */
	public void setCalidadSolicitanteIndices(Collection<CalidadSolicitante> accs_ccs)
	{
		iccs_calidadSolicitanteIndices = accs_ccs;
	}

	/**
	 * Retorna el valor de calidad solicitante indices.
	 *
	 * @return el valor de calidad solicitante indices.
	 */
	public Collection<CalidadSolicitante> getCalidadSolicitanteIndices()
	{
		return iccs_calidadSolicitanteIndices;
	}

	/**
	* Modifica el valor de campos consulta.
	*
	* @param accc_ccc asigna el valor a la propiedad campos consulta
	*/
	public void setCamposConsulta(Collection<CamposConsulta> accc_ccc)
	{
		iccc_CamposConsulta = accc_ccc;
	}

	/**
	* Retorna el valor de campos consulta.
	*
	* @return el valor de campos consulta
	*/
	public Collection<CamposConsulta> getCamposConsulta()
	{
		return iccc_CamposConsulta;
	}

	/**
	 * Modifica el valor de cargar documentos tipo.
	 *
	 * @param acidt_cidt asigna el valor a la propiedad cargar documentos tipo
	 */
	public void setCargarDocumentosTipo(Collection<InteresadoDocumentoTipo> acidt_cidt)
	{
		icidt_cargarDocumentosTipo = acidt_cidt;
	}

	/**
	 * Retorna el valor de cargar documentos tipo.
	 *
	 * @return el valor de cargar documentos tipo.
	 */
	public Collection<InteresadoDocumentoTipo> getCargarDocumentosTipo()
	{
		return icidt_cargarDocumentosTipo;
	}

	/**
	* Modifica el valor de cargar tipo consulta.
	*
	* @param acc_cc asigna el valor a la propiedad cargar tipo consulta
	*/
	public void setCargarTipoConsulta(Collection<Consultas> acc_cc)
	{
		icc_cargarTipoConsulta = acc_cc;
	}

	/**
	* Retorna el valor de cargar tipo consulta.
	*
	* @return el valor de cargar tipo consulta
	*/
	public Collection<Consultas> getCargarTipoConsulta()
	{
		return icc_cargarTipoConsulta;
	}

	/**
	* Modifica el valor de causal negacion copias.
	*
	* @param accnc_ccnc asigna el valor a la propiedad causal negacion copias
	*/
	public void setCausalNegacionCopias(Collection<CausalNegacionCopias> accnc_ccnc)
	{
		iccnc_causalNegacionCopias = accnc_ccnc;
	}

	/**
	 * Retorna el valor de causal negación copias.
	 *
	 * @return el valor de causal negación copias
	 */
	public Collection<CausalNegacionCopias> getCausalNegacionCopias()
	{
		return iccnc_causalNegacionCopias;
	}

	/**
	* Modifica el valor de circulo registral.
	*
	* @param ambccr_mbccr asigna el valor a la propiedad circulo registral
	*/
	public void setCirculoRegistral(Map<Boolean, Collection<CirculoRegistral>> ambccr_mbccr)
	{
		imbccr_circuloRegistral = ambccr_mbccr;
	}

	/**
	 * Retorna el valor de circulo registral.
	 *
	 * @return el valor de circulo registral
	 */
	public Map<Boolean, Collection<CirculoRegistral>> getCirculoRegistral()
	{
		return imbccr_circuloRegistral;
	}

	/**
	* Modifica el valor de circulo registral activo usuario.
	*
	* @param accr_ccr asigna el valor a la propiedad circulo registral activo usuario
	*/
	public void setCirculoRegistralActivoUsuario(Collection<CirculoRegistral> accr_ccr)
	{
		iccr_circuloRegistralActivoUsuario = accr_ccr;
	}

	/**
	* Retorna el valor de circulo registral activo usuario.
	*
	* @return el valor de circulo registral activo usuario
	*/
	public Collection<CirculoRegistral> getCirculoRegistralActivoUsuario()
	{
		return iccr_circuloRegistralActivoUsuario;
	}

	/**
	* Modifica el valor de circulos registrales origen destino activos.
	*
	* @param ambccs_mbcss asigna el valor a la propiedad circulos registrales origen destino activos
	*/
	public void setCirculosRegistralesOrigenDestinoActivos(Map<Boolean, Collection<CirculoRegistral>> ambccs_mbcss)
	{
		imbccr_circulosRegistralesOrigenDestinoActivos = ambccs_mbcss;
	}

	/**
	 * Retorna el valor de circulos registrales origen destino activos.
	 *
	 * @return el valor de circulos registrales origen destino activos
	 */
	public Map<Boolean, Collection<CirculoRegistral>> getCirculosRegistralesOrigenDestinoActivos()
	{
		return imbccr_circulosRegistralesOrigenDestinoActivos;
	}

	/**
	* Modifica el valor de codigo 4 y 5 digitos
	*
	* @param acta_cta asigna el valor a la propiedad codigo 4 y 5 digitos
	*/
	public void setCodigo4Y5Digitos(Collection<TipoActo> acta_cta)
	{
		icta_codigo4Y5Digitos = acta_cta;
	}

	/**
	 * Retorna el valor de codigo 4 y 5 digitos
	 *
	 * @return el valor de codigo 4 y 5 digitos
	 */
	public Collection<TipoActo> getCodigo4Y5Digitos()
	{
		return icta_codigo4Y5Digitos;
	}

	/**
	* Modifica el valor de complemento dirección by tipo predio
	*
	* @param al_l asigna el valor a la propiedad complemento dirección by tipo predio
	*/
	public void setComplementoDireccionByTipoPredio(Map<String, Collection<TipoEje>> amscte_mscte)
	{
		imscte_complementoDireccionByTipoPredio = amscte_mscte;
	}

	/**
	 * Retorna el valor de complemento dirección by tipo predio
	 *
	 * @return el valor de complemento dirección by tipo predio
	 */
	public Map<String, Collection<TipoEje>> getComplementoDireccionByTipoPredio()
	{
		return imscte_complementoDireccionByTipoPredio;
	}

	/**
	* Modifica el valor de coordenada.
	*
	* @param acc_cc asigna el valor a la propiedad coordenada
	*/
	public void setCoordenada(Collection<Coordenada> acc_cc)
	{
		icc_Coordenada = acc_cc;
	}

	/**
	* Retorna el valor de coordenada.
	*
	* @return el valor de coordenada
	*/
	public Collection<Coordenada> getCoordenada()
	{
		return icc_Coordenada;
	}

	/**
	* Modifica el valor de coordenada activo.
	*
	* @param acc_cc asigna el valor a la propiedad coordenada activo
	*/
	public void setCoordenadaActivo(Collection<Coordenada> acc_cc)
	{
		icc_coordenadaActivo = acc_cc;
	}

	/**
	* Retorna el valor de coordenada activo.
	*
	* @return el valor de coordenada activo
	*/
	public Collection<Coordenada> getCoordenadaActivo()
	{
		return icc_coordenadaActivo;
	}

	/**
	* Modifica el valor de datos tipo entidad
	*
	* @param acdte asigna el valor a la propiedad datos tipo entidad
	*/
	public void setDatosTipoEntidad(Collection<TipoEntidad> acdte_dte)
	{
		icdte_datosTipoEntidad = acdte_dte;
	}

	/**
	 * Retorna el valor de datos tipo entidad
	 *
	 * @return el valor de datos tipo entidad
	 */
	public Collection<TipoEntidad> getDatosTipoEntidad()
	{
		return icdte_datosTipoEntidad;
	}

	/**
	* Modifica el valor de tipo oficina
	*
	* @param acdto_dto asigna el valor a la propiedad tipo oficina
	*/
	public void setDatosTipoOficina(Collection<TipoOficina> acdto_dto)
	{
		icdto_datosTipoOficina = acdto_dto;
	}

	/**
	 * Retorna el valor de tipo oficina
	 *
	 * @return el valor de tipo oficina
	 */
	public Collection<TipoOficina> getDatosTipoOficina()
	{
		return icdto_datosTipoOficina;
	}

	/**
	* Modifica el valor de departamento.
	*
	* @param amscd_mscd asigna el valor a la propiedad departamento
	*/
	public void setDepartamentos(Map<String, Collection<Departamento>> amscd_mscd)
	{
		imscd_departamentos = amscd_mscd;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public Map<String, Collection<Departamento>> getDepartamentos()
	{
		return imscd_departamentos;
	}

	/**
	* Modifica el valor de documento publico .
	*
	* @param icdp_dp asigna el valor a la propiedad documento publico
	*/
	public void setDocumentoPublico(Collection<TipoDocumentoPublico> icdp_dp)
	{
		icdp_documentoPublico = icdp_dp;
	}

	/**
	 * Retorna el valor de documento publico .
	 *
	 * @return el valor de documento publico
	 */
	public Collection<TipoDocumentoPublico> getDocumentoPublico()
	{
		return icdp_documentoPublico;
	}

	/**
	* Modifica el valor de documento publico activo.
	*
	* @param icdpa_dpa asigna el valor a la propiedad documento publico activo
	*/
	public void setDocumentoPublicoActivo(Collection<TipoDocumentoPublico> icdpa_dpa)
	{
		icdpa_documentoPublicoActivo = icdpa_dpa;
	}

	/**
	 * Retorna el valor de documento publico activo.
	 *
	 * @return el valor de documento publico activo
	 */
	public Collection<TipoDocumentoPublico> getDocumentoPublicoActivo()
	{
		return icdpa_documentoPublicoActivo;
	}

	/**
	* Modifica el valor de entidad externa.
	*
	* @param acaee_caee asigna el valor a la propiedad entidad externa
	*/
	public void setEntidadExterna(Collection<AccEntidadExterna> acaee_caee)
	{
		icaee_entidadExterna = acaee_caee;
	}

	/**
	* Retorna el valor de entidad externa.
	*
	* @return el valor de entidad externa
	*/
	public Collection<AccEntidadExterna> getEntidadExterna()
	{
		return icaee_entidadExterna;
	}

	/**
	* Modifica el valor de estado actividad.
	*
	* @param amsea_msea asigna el valor a la propiedad estado actividad
	*/
	public void setEstadoActividad(Map<String, EstadoActividad> amsea_msea)
	{
		imsea_estadoActividad = amsea_msea;
	}

	/**
	 * Retorna el valor de estado actividad.
	 *
	 * @return el valor de estado actividad
	 */
	public Map<String, EstadoActividad> getEstadoActividad()
	{
		return imsea_estadoActividad;
	}

	/**
	* Modifica el valor de estado actividad activo.
	*
	* @param acea_cea asigna el valor a la propiedad estado actividad activo
	*/
	public void setEstadoActividadActivo(Collection<EstadoActividad> acea_cea)
	{
		icea_EstadoActividadActivo = acea_cea;
	}

	/**
	* Retorna el valor de estado actividad activo.
	*
	* @return el valor de estado actividad activo
	*/
	public Collection<EstadoActividad> getEstadoActividadActivo()
	{
		return icea_EstadoActividadActivo;
	}

	/**
	* Modifica el valor de estado anotacion.
	*
	* @param acea_cea asigna el valor a la propiedad estado anotacion
	*/
	public void setEstadoAnotacion(Collection<EstadoAnotacion> acea_cea)
	{
		icea_estadoAnotacion = acea_cea;
	}

	/**
	* Retorna el valor de estado anotacion.
	*
	* @return el valor de estado anotacion
	*/
	public Collection<EstadoAnotacion> getEstadoAnotacion()
	{
		return icea_estadoAnotacion;
	}

	/**
	* Modifica el valor de estado predio.
	*
	* @param acep_cep asigna el valor a la propiedad estado predio
	*/
	public void setEstadoPredio(Collection<EstadoPredio> acep_cep)
	{
		icep_estadoPredio = acep_cep;
	}

	/**
	* Retorna el valor de estado predio.
	*
	* @return el valor de estado predio
	*/
	public Collection<EstadoPredio> getEstadoPredio()
	{
		return icep_estadoPredio;
	}

	/**
	* Modifica el valor de etapa activo.
	*
	* @param ace_ce asigna el valor a la propiedad etapa activo
	*/
	public void setEtapaActivo(Collection<Etapa> ace_ce)
	{
		icc_etapaActivo = ace_ce;
	}

	/**
	* Retorna el valor de etapa activo.
	*
	* @return el valor de etapa activo
	*/
	public Collection<Etapa> getEtapaActivo()
	{
		return icc_etapaActivo;
	}

	/**
	* Modifica el valor de etapa activo by id.
	*
	* @param ace_ce asigna el valor a la propiedad etapa activo by id
	*/
	public void setEtapaActivoById(Collection<Etapa> ace_ce)
	{
		ice_etapaActivoById = ace_ce;
	}

	/**
	* Retorna el valor de etapa activo by id.
	*
	* @return el valor de etapa activo by id
	*/
	public Collection<Etapa> getEtapaActivoById()
	{
		return ice_etapaActivoById;
	}

	/**
	* Modifica el valor de find documento publico
	*
	* @param acfdp_fdp asigna el valor a la propiedad find documento publico
	*/
	public void setFindDocumentoPublico(Collection<InteresadoDocumentoTipo> acfdp_fdp)
	{
		icfdp_findDocumentoPublico = acfdp_fdp;
	}

	/**
	 * Retorna el valor de find documento publico
	 *
	 * @return el valor de find documento publico
	 */
	public Collection<InteresadoDocumentoTipo> getFindDocumentoPublico()
	{
		return icfdp_findDocumentoPublico;
	}

	/**
	* Modifica el valor de generos.
	*
	* @param acing_cing asigna el valor a la propiedad generos
	*/
	public void setGeneros(Collection<InteresadoNaturalGenero> acing_cing)
	{
		icing_generos = acing_cing;
	}

	/**
	* Retorna el valor de generos.
	*
	* @return el valor de generos
	*/
	public Collection<InteresadoNaturalGenero> getGeneros()
	{
		return icing_generos;
	}

	/**
	* Modifica el valor de habilitado not.
	*
	* @param actr_ctr asigna el valor a la propiedad habilitado not
	*/
	public void setHabilitadoNot(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_habilitadoNot = actr_ctr;
	}

	/**
	* Retorna el valor de habilitado not.
	*
	* @return el valor de habilitado not
	*/
	public Collection<TipoRecepcion> getHabilitadoNot()
	{
		return ictr_habilitadoNot;
	}

	/**
	* Modifica el valor de habilitado not negat.
	*
	* @param actr_ctr asigna el valor a la propiedad habilitado not negat
	*/
	public void setHabilitadoNotNegat(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_habilitadoNotNegat = actr_ctr;
	}

	/**
	* Retorna el valor de habilitado not negat.
	*
	* @return el valor de habilitado not negat
	*/
	public Collection<TipoRecepcion> getHabilitadoNotNegat()
	{
		return ictr_habilitadoNotNegat;
	}

	/**
	* Modifica el valor de habilitado rec.
	*
	* @param actr_ctr asigna el valor a la propiedad habilitado rec
	*/
	public void setHabilitadoRec(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_habilitadoRec = actr_ctr;
	}

	/**
	* Retorna el valor de habilitado rec.
	*
	* @return el valor de habilitado rec
	*/
	public Collection<TipoRecepcion> getHabilitadoRec()
	{
		return ictr_habilitadoRec;
	}

	/**
	* Modifica el valor de id tipo oficina.
	*
	* @param amscto_mscto asigna el valor a la propiedad id tipo oficina
	*/
	public void setIdTipoOficina(Map<String, Collection<TipoOficina>> amscto_mscto)
	{
		imscto_idTipoOficina = amscto_mscto;
	}

	/**
	* Retorna el valor de id tipo oficina.
	*
	* @return el valor de id tipo oficina
	*/
	public Map<String, Collection<TipoOficina>> getIdTipoOficina()
	{
		return imscto_idTipoOficina;
	}

	/**
	* Modifica el valor de in calidad solicitante indices.
	*
	* @param accs_ccs asigna el valor a la propiedad in calidad solicitante indices
	*/
	public void setInCalidadSolicitanteIndices(Collection<CalidadSolicitante> accs_ccs)
	{
		iccs_inCalidadSolicitanteIndices = accs_ccs;
	}

	/**
	* Retorna el valor de in calidad solicitante indices.
	*
	* @return el valor de in calidad solicitante indices
	*/
	public Collection<CalidadSolicitante> getInCalidadSolicitanteIndices()
	{
		return iccs_inCalidadSolicitanteIndices;
	}

	/**
	* Modifica el valor de interesado documento tipo
	*
	* @param acidt_idt asigna el valor a la propiedad interesado documento tipo
	*/
	public void setInteresadoDocumentoTipo(Collection<InteresadoDocumentoTipo> acidt_idt)
	{
		icidt_interesadoDocumentoTipo = acidt_idt;
	}

	/**
	 * Retorna el valor de interesado documento tipo
	 *
	 * @return el valor de interesado documento tipo
	 */
	public Collection<InteresadoDocumentoTipo> getInteresadoDocumentoTipo()
	{
		return icidt_interesadoDocumentoTipo;
	}

	/**
	* Modifica el valor de letra eje.
	*
	* @param acle_cle asigna el valor a la propiedad letra eje
	*/
	public void setLetraEje(Collection<LetraEje> acle_cle)
	{
		icle_letraEje = acle_cle;
	}

	/**
	* Retorna el valor de letra eje.
	*
	* @return el valor de letra eje
	*/
	public Collection<LetraEje> getLetraEje()
	{
		return icle_letraEje;
	}

	/**
	* Modifica el valor de letra eje activo.
	*
	* @param acle_cle asigna el valor a la propiedad letra eje activo
	*/
	public void setLetraEjeActivo(Collection<LetraEje> acle_cle)
	{
		icle_letraEjeActivo = acle_cle;
	}

	/**
	* Retorna el valor de letra eje activo.
	*
	* @return el valor de letra eje activo
	*/
	public Collection<LetraEje> getLetraEjeActivo()
	{
		return icle_letraEjeActivo;
	}

	/**
	* Modifica el valor de libro antiguo sistema.
	*
	* @param aclas_clas asigna el valor a la propiedad libro antiguo sistema
	*/
	public void setLibroAntiguoSistema(Collection<LibroAntiguoSistema> aclas_clas)
	{
		iclas_libroAntiguoSistema = aclas_clas;
	}

	/**
	* Retorna el valor de libro antiguo sistema.
	*
	* @return el valor de libro antiguo sistema
	*/
	public Collection<LibroAntiguoSistema> getLibroAntiguoSistema()
	{
		return iclas_libroAntiguoSistema;
	}

	/**
	* Modifica el valor de max version naturaleza juridica.
	*
	* @param ambcnj_mbcnj asigna el valor a la propiedad max version naturaleza juridica
	*/
	public void setMaxVersionNaturalezaJuridica(Map<Boolean, Collection<NaturalezaJuridica>> ambcnj_mbcnj)
	{
		imbcnj_maxVersionNaturalezaJuridica = ambcnj_mbcnj;
	}

	/**
	 * Retorna el valor de max version naturaleza juridica.
	 *
	 * @return el valor de max version naturaleza juridica
	 */
	public Map<Boolean, Collection<NaturalezaJuridica>> getMaxVersionNaturalezaJuridica()
	{
		return imbcnj_maxVersionNaturalezaJuridica;
	}

	/**
	* Modifica el valor de medida area activo.
	*
	* @param acma_cma asigna el valor a la propiedad medida area activo
	*/
	public void setMedidaAreaActivo(Collection<MedidaArea> acma_cma)
	{
		icma_medidaAreaActivo = acma_cma;
	}

	/**
	* Retorna el valor de medida area activo.
	*
	* @return el valor de medida area activo
	*/
	public Collection<MedidaArea> getMedidaAreaActivo()
	{
		return icma_medidaAreaActivo;
	}

	/**
	 * Modifica el valor de medida area by id.
	 *
	 * @param amscma_mscma asigna el valor a la propiedad medida area by id
	 */
	public void setMedidaAreaById(Map<String, Collection<MedidaArea>> amscma_mscma)
	{
		imscma_medidaAreaById = amscma_mscma;
	}

	/**
	 * Retorna el valor de medida area by id.
	 *
	 * @return el valor de medida area by id
	 */
	public Map<String, Collection<MedidaArea>> getMedidaAreaById()
	{
		return imscma_medidaAreaById;
	}

	/**
	* Modifica el valor de codigo 4 y 5 digitos
	*
	* @param acc_cc asigna el valor a la propiedad codigo 4 y 5 digitos
	*/
	public void setMotivosConsulta(Collection<Constantes> acc_cc)
	{
		icc_motivosConsulta = acc_cc;
	}

	/**
	//     * Retorna el valor de motivos consulta
	 *
	 * @return el valor de motivos consulta
	 */
	public Collection<Constantes> getMotivosConsulta()
	{
		return icc_motivosConsulta;
	}

	/**
	* Modifica el valor de municipios.
	*
	* @param amscm_mscm asigna el valor a la propiedad municipios
	*/
	public void setMunicipios(Map<String, Collection<Municipio>> amscm_mscm)
	{
		imscm_municipios = amscm_mscm;
	}

	/**
	* Retorna el valor de municipios.
	*
	* @return el valor de municipios
	*/
	public Map<String, Collection<Municipio>> getMunicipios()
	{
		return imscm_municipios;
	}

	/**
	 * Modifica el valor de municipios por pais.
	 *
	 * @param acm_m asigna el valor a la propiedad municipios por pais
	 */
	public void setMunicipiosPorPais(Collection<Municipio> acm_m)
	{
		icm_municipiosPorPais = acm_m;
	}

	/**
	 * Retorna el valor de municipios.
	 *
	 * @return el valor de municipios
	 */
	public Collection<Municipio> getMunicipiosPorPais()
	{
		return icm_municipiosPorPais;
	}

	/**
	* Modifica el valor de naturaleza jurídica.
	*
	* @param ambcnj_mbnj asigna el valor a la propiedad naturaleza jurídica
	*/
	public void setNaturalezaJuridica(Map<Boolean, Collection<NaturalezaJuridica>> ambcnj_mbnj)
	{
		imbcnj_naturalezaJuridica = ambcnj_mbnj;
	}

	/**
	 * Retorna el valor de naturaleza jurídica.
	 *
	 * @return el valor de naturaleza jurídica
	 */
	public Map<Boolean, Collection<NaturalezaJuridica>> getNaturalezaJuridica()
	{
		return imbcnj_naturalezaJuridica;
	}

	/**
	* Modifica el valor de naturaleza juridica 0841 and 0842.
	*
	* @param acnj_cnj asigna el valor a la propiedad naturaleza juridica 0841 and 0842
	*/
	public void setNaturalezaJuridica0841and0842(Collection<NaturalezaJuridica> acnj_cnj)
	{
		icnj_naturalezaJuridica0841and0842 = acnj_cnj;
	}

	/**
	* Retorna el valor de naturaleza juridica 0841 and 0842.
	*
	* @return el valor de naturaleza juridica 0841 and 0842
	*/
	public Collection<NaturalezaJuridica> getNaturalezaJuridica0841and0842()
	{
		return icnj_naturalezaJuridica0841and0842;
	}

	/**
	* Modifica el valor de nombres columnas.
	*
	* @param amscuo_mscuo asigna el valor a la propiedad nombres columnas
	*/
	public void setNombresColumnas(Map<String, Collection<UserObjects>> amscuo_mscuo)
	{
		imscuo_nombresColumnas = amscuo_mscuo;
	}

	/**
	 * Retorna el valor de nombres columnas.
	 *
	 * @return el valor de nombres columnas
	 */
	public Map<String, Collection<UserObjects>> getNombresColumnas()
	{
		return imscuo_nombresColumnas;
	}

	/**
	* Modifica el valor de  nombres tablas.
	*
	* @param acuo_cuo asigna el valor a la propiedad  nombres tablas
	*/
	public void setNombresTablas(Collection<UserObjects> acuo_cuo)
	{
		icuo_nombresTablas = acuo_cuo;
	}

	/**
	* Retorna el valor de nombres tablas.
	*
	* @return el valor de nombres tablas
	*/
	public Collection<UserObjects> getNombresTablas()
	{
		return icuo_nombresTablas;
	}

	/**
	* Modifica el valor de oficina origen.
	*
	* @param amscoo_mscoo asigna el valor a la propiedad oficina origen
	*/
	public void setOficinaOrigen(Map<String, Collection<OficinaOrigen>> amscoo_mscoo)
	{
		imscoo_oficinaOrigen = amscoo_mscoo;
	}

	/**
	* Retorna el valor de oficina origen.
	*
	* @return el valor de oficina origen
	*/
	public Map<String, Collection<OficinaOrigen>> getOficinaOrigen()
	{
		return imscoo_oficinaOrigen;
	}

	/**
	* Modifica el valor de oficina origen by id.
	*
	* @param amsoo_msoo asigna el valor a la propiedad oficina origen by id
	*/
	public void setOficinaOrigenById(Map<String, OficinaOrigen> amsoo_msoo)
	{
		imsoo_oficinaOrigenById = amsoo_msoo;
	}

	/**
	 * Retorna el valor de oficina origen by id.
	 *
	 * @return el valor de oficina origen by id
	 */
	public Map<String, OficinaOrigen> getOficinaOrigenById()
	{
		return imsoo_oficinaOrigenById;
	}

	/**
	* Modifica el valor de oficina origen by collection.
	*
	* @param amscoo_mscoo asigna el valor a la propiedad oficina origen by collection
	*/
	public void setOficinaOrigenByIdCollection(Map<String, Collection<OficinaOrigen>> amscoo_mscoo)
	{
		imscoo_oficinaOrigenByIdCollection = amscoo_mscoo;
	}

	/**
	 * Retorna el valor de oficina origen by collection.
	 *
	 * @return el valor de oficina origen by collection
	 */
	public Map<String, Collection<OficinaOrigen>> getOficinaOrigenByIdCollection()
	{
		return imscoo_oficinaOrigenByIdCollection;
	}

	/**
	* Modifica el valor de opcion.
	*
	* @param aco_co asigna el valor a la propiedad opcion
	*/
	public void setOpcion(Collection<Opcion> aco_co)
	{
		ico_opcion = aco_co;
	}

	/**
	* Retorna el valor de opcion.
	*
	* @return el valor de opcion
	*/
	public Collection<Opcion> getOpcion()
	{
		return ico_opcion;
	}

	/**
	* Modifica el valor de paises.
	*
	* @param acp_p asigna el valor a la propiedad paises
	*/
	public void setPaises(Collection<Pais> acp_p)
	{
		icp_paises = acp_p;
	}

	/**
	 * Retorna el valor de paises.
	 *
	 * @return el valor de paises
	 */
	public Collection<Pais> getPaises()
	{
		return icp_paises;
	}

	/**
	* Modifica el valor de plantillas activo.
	*
	* @param acp_cp asigna el valor a la propiedad plantillas activo
	*/
	public void setPlantillasActivo(Collection<Plantilla> acp_cp)
	{
		icp_plantillasActivo = acp_cp;
	}

	/**
	* Retorna el valor de plantillas activo.
	*
	* @return el valor de plantillas activo
	*/
	public Collection<Plantilla> getPlantillasActivo()
	{
		return icp_plantillasActivo;
	}

	/**
	* Modifica el valor de procedencia.
	*
	* @param actr_ctr asigna el valor a la propiedad procedencia
	*/
	public void setProcedencia(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_procedencia = actr_ctr;
	}

	/**
	* Retorna el valor de procedencia.
	*
	* @return el valor de procedencia
	*/
	public Collection<TipoRecepcion> getProcedencia()
	{
		return ictr_procedencia;
	}

	/**
	* Modifica el valor de procesos.
	*
	* @param acp_p asigna el valor a la propiedad procesos
	*/
	public void setProcesos(Collection<Proceso> acp_p)
	{
		icp_procesos = acp_p;
	}

	/**
	 * Retorna el valor de procesos.
	 *
	 * @return el valor de procesos
	 */
	public Collection<Proceso> getProcesos()
	{
		return icp_procesos;
	}

	/**
	* Modifica el valor de procesos activos.
	*
	* @param icpa_pa asigna el valor a la propiedad procesos activos
	*/
	public void setProcesosActivos(Collection<Proceso> icpa_pa)
	{
		icpa_procesosActivos = icpa_pa;
	}

	/**
	 * Retorna el valor de procesos activos
	 *
	 * @return el valor de procesos activos
	 */
	public Collection<Proceso> getProcesosActivos()
	{
		return icpa_procesosActivos;
	}

	/**
	* Modifica el valor de procesos activos by id
	*
	* @param acpabi_pabi asigna el valor a la propiedad procesos activos by id
	*/
	public void setProcesosActivosById(Collection<Proceso> acpabi_pabi)
	{
		icpabi_procesosActivosById = acpabi_pabi;
	}

	/**
	 * Retorna el valor de procesos activos by id
	 *
	 * @return el valor de procesos activos by id
	 */
	public Collection<Proceso> getProcesosActivosById()
	{
		return icpabi_procesosActivosById;
	}

	/**
	* Modifica el valor de procesos automaticos
	*
	* @param icpa_pa asigna el valor a la propiedad procesos automaticos
	*/
	public void setProcesosAutomaticos(Collection<ProcesoAutomatico> icpa_pa)
	{
		icpa_procesosAutomaticos = icpa_pa;
	}

	/**
	 * Retorna el valor de procesos automaticos
	 *
	 * @return el valor de procesos automaticos
	 */
	public Collection<ProcesoAutomatico> getProcesosAutomaticos()
	{
		return icpa_procesosAutomaticos;
	}

	/**
	* Modifica el valor de recepcion documento.
	*
	* @param acs_cs asigna el valor a la propiedad matricula
	*/
	public void setRecepcionDocumento(Collection<String> acs_cs)
	{
		ics_recepcionDocumento = acs_cs;
	}

	/**
	* Retorna el valor de recepcion documento.
	*
	* @return el valor de recepcion documento
	*/
	public Collection<String> getRecepcionDocumento()
	{
		return ics_recepcionDocumento;
	}

	/**
	* Modifica el valor de regionales activos.
	*
	* @param acr_cr asigna el valor a la propiedad regionales activos
	*/
	public void setRegionalesActivos(Collection<Regional> acr_cr)
	{
		icr_regionalesActivos = acr_cr;
	}

	/**
	* Retorna el valor de regionales activos.
	*
	* @return el valor de regionales activos
	*/
	public Collection<Regional> getRegionalesActivos()
	{
		return icr_regionalesActivos;
	}

	/**
	* Modifica el valor de ictrm requiere matriculas activos
	*
	* @param actrm_ctrm asigna el valor a la propiedad ictrm requiere matriculas activos
	*/
	public void setRequiereMatriculaActivos(Collection<TipoRequiereMatricula> actrm_ctrm)
	{
		ictrm_requiereMatriculaActivos = actrm_ctrm;
	}

	/**
	 * Retorna el valor de ictrm requiere matriculas activos
	 *
	 * @return el valor de ictrm requiere matriculas activos
	 */
	public Collection<TipoRequiereMatricula> getRequiereMatriculaActivos()
	{
		return ictrm_requiereMatriculaActivos;
	}

	/**
	* Modifica el valor de resultado consulta
	*
	* @param acrs_cr asigna el valor a la propiedad resultado consulta
	*/
	public void setResultadoConsulta(Collection<ResultadoConsulta> acrs_cr)
	{
		icrc_resultadoConsulta = acrs_cr;
	}

	/**
	 * Retorna el valor de resultado consulta
	 *
	 * @return el valor de resultado consulta
	 */
	public Collection<ResultadoConsulta> getResultadoConsulta()
	{
		return icrc_resultadoConsulta;
	}

	/**
	* Modifica el valor de rol.
	*
	* @param acr_cr asigna el valor a la propiedad rol
	*/
	public void setRol(Collection<Rol> acr_cr)
	{
		icr_rol = acr_cr;
	}

	/**
	* Retorna el valor de rol.
	*
	* @return el valor de rol
	*/
	public Collection<Rol> getRol()
	{
		return icr_rol;
	}

	/**
	* Modifica el valor de subprocesos
	*
	* @param acs_s asigna el valor a la propiedad subprocesos
	*/
	public void setSubprocesos(Collection<Subproceso> acs_s)
	{
		ics_subprocesos = acs_s;
	}

	/**
	 * Retorna el valor de subprocesos
	 *
	 * @return el valor de subprocesos
	 */
	public Collection<Subproceso> getSubprocesos()
	{
		return ics_subprocesos;
	}

	/**
	* Modifica el valor de subprocesos by proceso.
	*
	* @param amscs_mscs asigna el valor a la propiedad subprocesos by proceso
	*/
	public void setSubprocesosByProceso(Map<String, Collection<Subproceso>> amscs_mscs)
	{
		imscs_subprocesosByProceso = amscs_mscs;
	}

	/**
	* Retorna el valor de subprocesos by proceso.
	*
	* @return el valor de subprocesos by proceso
	*/
	public Map<String, Collection<Subproceso>> getSubprocesosByProceso()
	{
		return imscs_subprocesosByProceso;
	}

	/**
	* Modifica el valor de subprocesos by proceso id.
	*
	* @param amscs_mscs asigna el valor a la propiedad subprocesos by proceso id
	*/
	public void setSubprocesosByProcesoId(Map<String, Collection<Subproceso>> amscs_mscs)
	{
		imscs_subprocesosByProcesoId = amscs_mscs;
	}

	/**
	* Retorna el valor de subprocesos by proceso id.
	*
	* @return el valor de subprocesos by proceso id
	*/
	public Map<String, Collection<Subproceso>> getSubprocesosByProcesoId()
	{
		return imscs_subprocesosByProcesoId;
	}

	/**
	* Modifica el valor de tipo acto.
	*
	* @param amsta_msta asigna el valor a la propiedad tipo acto
	*/
	public void setTipoActo(Map<String, TipoActo> amsta_msta)
	{
		imsta_tipoActo = amsta_msta;
	}

	/**
	* Retorna el valor de tipo acto.
	*
	* @return el valor de tipo acto
	*/
	public Map<String, TipoActo> getTipoActo()
	{
		return imsta_tipoActo;
	}

	/**
	* Modifica el valor de tipo criterio busqueda
	*
	* @param actcb_tcb asigna el valor a la propiedad tipo criterio busqueda
	*/
	public void setTipoCriterioBusqueda(Collection<CriteriosDeBusqueda> actcb_tcb)
	{
		ictcb_tipoCriterioBusqueda = actcb_tcb;
	}

	/**
	 * Retorna el valor de tipo criterio busqueda
	 *
	 * @return el valor de tipo criterio busqueda
	 */
	public Collection<CriteriosDeBusqueda> getTipoCriterioBusqueda()
	{
		return ictcb_tipoCriterioBusqueda;
	}

	/**
	* Modifica el valor de tipo decision recurso.
	*
	* @param actdr_ctdr asigna el valor a la propiedad tipo decision recurso
	*/
	public void setTipoDecisionRecurso(Collection<TipoDecisionRecurso> actdr_ctdr)
	{
		ictdr_tipoDecisionRecurso = actdr_ctdr;
	}

	/**
	* Retorna el valor de tipo decision recurso.
	*
	* @return el valor de tipo decision recurso
	*/
	public Collection<TipoDecisionRecurso> getTipoDecisionRecurso()
	{
		return ictdr_tipoDecisionRecurso;
	}

	/**
	* Modifica el valor de tipo documental.
	*
	* @param actd_ctd asigna el valor a la propiedad tipo documental
	*/
	public void setTipoDocumental(Collection<TipoDocumental> actd_ctd)
	{
		ictd_tipoDocumental = actd_ctd;
	}

	/**
	* Retorna el valor de tipo documental.
	*
	* @return el valor de tipo documental
	*/
	public Collection<TipoDocumental> getTipoDocumental()
	{
		return ictd_tipoDocumental;
	}

	/**
	* Modifica el valor de tipo eje.
	*
	* @param acte_te asigna el valor a la propiedad tipo eje
	*/
	public void setTipoEje(Collection<TipoEje> acte_te)
	{
		icte_tipoEje = acte_te;
	}

	/**
	 * Retorna el valor de tipo eje.
	 *
	 * @return el valor de tipo eje
	 */
	public Collection<TipoEje> getTipoEje()
	{
		return icte_tipoEje;
	}

	/**
	* Modifica el valor de tipo eje by tipo predio.
	*
	* @param imscte_mscte asigna el valor a la propiedad tipo eje by tipo predio
	*/
	public void setTipoEjeByTipoPredio(Map<String, Collection<TipoEje>> imscte_mscte)
	{
		imscte_tipoEjeByTipoPredio = imscte_mscte;
	}

	/**
	* Retorna el valor de tipo eje by tipo predio.
	*
	* @return el valor de tipo eje by tipo predio
	*/
	public Map<String, Collection<TipoEje>> getTipoEjeByTipoPredio()
	{
		return imscte_tipoEjeByTipoPredio;
	}

	/**
	* Modifica el valor de tipo entidad activo
	*
	* @param actea_tea asigna el valor a la propiedad tipo entidad activo
	*/
	public void setTipoEntidadActivo(Collection<TipoEntidad> actea_tea)
	{
		ictea_tipoEntidadActivo = actea_tea;
	}

	/**
	 * Retorna el valor de tipo entidad activo
	 *
	 * @return el valor de tipo entidad activo
	 */
	public Collection<TipoEntidad> getTipoEntidadActivo()
	{
		return ictea_tipoEntidadActivo;
	}

	/**
	* Modifica el valor de tipo entidad by id.
	*
	* @param amste_mste asigna el valor a la propiedad tipo entidad by id
	*/
	public void setTipoEntidadById(Map<String, TipoEntidad> amste_mste)
	{
		imste_tipoEntidadById = amste_mste;
	}

	/**
	* Retorna el valor de tipo entidad by id
	*
	* @return el valor de tipo entidad by id
	*/
	public Map<String, TipoEntidad> getTipoEntidadById()
	{
		return imste_tipoEntidadById;
	}

	/**
	* Modifica el valor de tipo entidad externa.
	*
	* @param amcaee_mcaee asigna el valor a la propiedad tipo entidad externa
	*/
	public void setTipoEntidadExterna(Map<String, Collection<AccEntidadExterna>> amcaee_mcaee)
	{
		imcaee_tipoEntidadExterna = amcaee_mcaee;
	}

	/**
	* Retorna el valor de tipo entidad externa.
	*
	* @return el valor de tipo entidad externa
	*/
	public Map<String, Collection<AccEntidadExterna>> getTipoEntidadExterna()
	{
		return imcaee_tipoEntidadExterna;
	}

	/**
	* Modifica el valor de  tipo entidad externa activo..
	*
	* @param amscaee_mscaee asigna el valor a la propiedad  tipo entidad externa activo.
	*/
	public void setTipoEntidadExternaActivo(Map<String, Collection<AccEntidadExterna>> amscaee_mscaee)
	{
		imscaee_TipoEntidadExternaActivo = amscaee_mscaee;
	}

	/**
	* Retorna el valor de  tipo entidad externa activo..
	*
	* @return el valor de  tipo entidad externa activo.
	*/
	public Map<String, Collection<AccEntidadExterna>> getTipoEntidadExternaActivo()
	{
		return imscaee_TipoEntidadExternaActivo;
	}

	/**
	* Modifica el valor de tipo integración gobernación.
	*
	* @param ambctig_mbctig asigna el valor a la propiedad tipo integración gobernación
	*/
	public void setTipoIntegracionGobernacion(Map<Boolean, Collection<TipoIntegracionGobernacion>> ambctig_mbctig)
	{
		imbctig_tipoIntegracionGobernacion = ambctig_mbctig;
	}

	/**
	 * Retorna el valor de tipo integración gobernación.
	 *
	 * @return el valor de tipo integración gobernación
	 */
	public Map<Boolean, Collection<TipoIntegracionGobernacion>> getTipoIntegracionGobernacion()
	{
		return imbctig_tipoIntegracionGobernacion;
	}

	/**
	* Modifica el valor de  tipo oficina activo.
	*
	* @param acto_cto asigna el valor a la propiedad  tipo oficina activo
	*/
	public void setTipoOficinaActivo(Collection<TipoOficina> acto_cto)
	{
		icto_tipoOficinaActivo = acto_cto;
	}

	/**
	* Retorna el valor de  tipo oficina activo.
	*
	* @return el valor de  tipo oficina activo
	*/
	public Collection<TipoOficina> getTipoOficinaActivo()
	{
		return icto_tipoOficinaActivo;
	}

	/**
	* Modifica el valor de tipo oficina activo by id.
	*
	* @param acto_cto asigna el valor a la propiedad tipo oficina activo by id
	*/
	public void setTipoOficinaActivoById(Collection<TipoOficina> acto_cto)
	{
		icto_tipoOficinaActivoById = acto_cto;
	}

	/**
	* Retorna el valor de tipo oficina activo by id.
	*
	* @return el valor de tipo oficina activo by id
	*/
	public Collection<TipoOficina> getTipoOficinaActivoById()
	{
		return icto_tipoOficinaActivoById;
	}

	/**
	* Modifica el valor de tipo oficina by id.
	*
	* @param amsto_msto asigna el valor a la propiedad tipo oficina by id
	*/
	public void setTipoOficinaById(Map<String, TipoOficina> amsto_msto)
	{
		imsto_tipoOficinaById = amsto_msto;
	}

	/**
	 * Retorna el valor de tipo oficina by id.
	 *
	 * @return el valor de tipo oficina by id
	 */
	public Map<String, TipoOficina> getTipoOficinaById()
	{
		return imsto_tipoOficinaById;
	}

	/**
	* Modifica el valor de persona
	*
	* @param actp_tp asigna el valor a la propiedad persona
	*/
	public void setTipoPersona(Collection<TipoPersona> actp_tp)
	{
		ictp_tipoPersona = actp_tp;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @return el valor de circulo
	 */
	public Collection<TipoPersona> getTipoPersona()
	{
		return ictp_tipoPersona;
	}

	/**
	* Modifica el valor de tipo predio
	*
	* @param actp_tp asigna el valor a la propiedad tipo predio
	*/
	public void setTipoPredio(Collection<PredioTipo> actp_tp)
	{
		ictp_tipoPredio = actp_tp;
	}

	/**
	 * Retorna el valor de tipo predio
	 *
	 * @return el valor de tipo predio
	 */
	public Collection<PredioTipo> getTipoPredio()
	{
		return ictp_tipoPredio;
	}

	/**
	* Modifica el valor de tipo recurso activo.
	*
	* @param actr_ctr asigna el valor a la propiedad tipo recurso activo
	*/
	public void setTipoRecursoByActivo(Collection<TipoRecurso> actr_ctr)
	{
		ictr_tipoRecursoByActivo = actr_ctr;
	}

	/**
	* Retorna el valor de tipo recurso activo.
	*
	* @return el valor de tipo recurso activo
	*/
	public Collection<TipoRecurso> getTipoRecursoByActivo()
	{
		return ictr_tipoRecursoByActivo;
	}

	/**
	* Modifica el valor de tipo rrr
	*
	* @param actr_tr asigna el valor a la propiedad tipo rrr
	*/
	public void setTipoRrr(Collection<TipoRrr> actr_tr)
	{
		ictr_tipoRrr = actr_tr;
	}

	/**
	 * Retorna el valor de tipo rrr
	 *
	 * @return el valor de tipo rrr
	 */
	public Collection<TipoRrr> getTipoRrr()
	{
		return ictr_tipoRrr;
	}

	/**
	* Modifica el valor de icttr tarifa registral
	*
	* @param acttr_cttr asigna el valor a la propiedad icttr tarifa registral
	*/
	public void setTipoTarifaRegistral(Collection<TipoTarifaRegistral> acttr_cttr)
	{
		icttr_tipoTarifaRegistral = acttr_cttr;
	}

	/**
	 * Retorna el valor de icttr tarifa registral
	 *
	 * @return el valor de icttr tarifa registral
	 */
	public Collection<TipoTarifaRegistral> getTipoTarifaRegistral()
	{
		return icttr_tipoTarifaRegistral;
	}

	/**
	* Modifica el valor de ictus tipo suelo
	*
	* @param actu_ctu asigna el valor a la propiedad ictus tipo suelo
	*/
	public void setTipoUsoSuelo(Collection<TipoUsoSuelo> actu_ctu)
	{
		ictus_tipoUsoSuelo = actu_ctu;
	}

	/**
	 * Retorna el valor de ictus tipo suelo
	 *
	 * @return el valor de ictus tipo suelo
	 */
	public Collection<TipoUsoSuelo> getTipoUsoSuelo()
	{
		return ictus_tipoUsoSuelo;
	}

	/**
	* Modifica el valor de tipo acto.
	*
	* @param acta_cta asigna el valor a la propiedad tipo acto
	*/
	public void setTiposActo(Collection<TipoActo> acta_cta)
	{
		icta_tiposActo = acta_cta;
	}

	/**
	* Retorna el valor de tipo acto.
	*
	* @return el valor de tipo acto
	*/
	public Collection<TipoActo> getTiposActo()
	{
		return icta_tiposActo;
	}

	/**
	    * Modifica el valor de tipos actos codigo
	    *
	    * @param acta_cta asigna el valor a la propiedad tipos actos codigo
	    */
	public void setTiposActosCodigo(Collection<TipoActo> acta_cta)
	{
		icta_tiposActosCodigo = acta_cta;
	}

	/**
	 * Retorna el valor de tipos actos codigo
	 *
	 * @return el valor de tipos actos codigo
	 */
	public Collection<TipoActo> getTiposActosCodigo()
	{
		return icta_tiposActosCodigo;
	}

	/**
	* Modifica el valor de tipos consignación.
	*
	* @param al_l asigna el valor a la propiedad tipos consignación
	*/
	public void setTiposConsignacion(Collection<String> acs_s)
	{
		ics_tiposConsignacion = acs_s;
	}

	/**
	 * Retorna el valor de tipos consignación.
	 *
	 * @return el valor de tipos consignación
	 */
	public Collection<String> getTiposConsignacion()
	{
		return ics_tiposConsignacion;
	}

	/**
	* Modifica el valor de tipos consulta.
	*
	* @param ambcc_mbcc asigna el valor a la propiedad tipos consulta
	*/
	public void setTiposConsulta(Map<Boolean, Collection<Constantes>> ambcc_mbcc)
	{
		imbcc_tiposConsulta = ambcc_mbcc;
	}

	/**
	* Retorna el valor de tipos consulta.
	*
	* @return el valor de tipos consulta
	*/
	public Map<Boolean, Collection<Constantes>> getTiposConsulta()
	{
		return imbcc_tiposConsulta;
	}

	/**
	* Modifica el valor de tipos documentales.
	*
	* @param actd_ctd asigna el valor a la propiedad tipos documentales
	*/
	public void setTiposDocumentales(Collection<TipoDocumental> actd_ctd)
	{
		ictd_tiposDocumentales = actd_ctd;
	}

	/**
	* Retorna el valor de tipos documentales.
	*
	* @return el valor de tipos documentales
	*/
	public Collection<TipoDocumental> getTiposDocumentales()
	{
		return ictd_tiposDocumentales;
	}

	/**
	* Modifica el valor de tipos publicación.
	*
	* @param al_l asigna el valor a la propiedad tipos publicación
	*/
	public void setTiposPublicacion(Collection<String> tiposPublicacion)
	{
		ics_tiposPublicacion = tiposPublicacion;
	}

	/**
	 * Retorna el valor de tipos publicación.
	 *
	 * @return el valor de tipos publicación
	 */
	public Collection<String> getTiposPublicacion()
	{
		return ics_tiposPublicacion;
	}

	/**
	* Modifica el valor de todos circulos registrales.
	*
	* @param amsccr_msccr asigna el valor a la propiedad todos circulos registrales
	*/
	public void setTodosCirculosRegistrales(Map<String, Collection<CirculoRegistral>> amsccr_msccr)
	{
		imsccr_todosCirculosRegistrales = amsccr_msccr;
	}

	/**
	 * Retorna el valor de todos circulos registrales.
	 *
	 * @return el valor de todos circulos registrales
	 */
	public Map<String, Collection<CirculoRegistral>> getTodosCirculosRegistrales()
	{
		return imsccr_todosCirculosRegistrales;
	}

	/**
	* Modifica el valor de veredas.
	*
	* @param amscv_mscv asigna el valor a la propiedad veredas
	*/
	public void setVeredas(Map<String, Collection<Vereda>> amscv_mscv)
	{
		imscv_veredas = amscv_mscv;
	}

	/**
	* Retorna el valor de veredas.
	*
	* @return el valor de veredas
	*/
	public Map<String, Collection<Vereda>> getVeredas()
	{
		return imscv_veredas;
	}

	/**
	 * Metodo encargado de consultar todos los motivos de consulta.
	 *
	 * @return devuelve el valor de Collection de constantes
	 */
	public Collection<Constantes> buscarMotivosConsulta()
	{
		if(icc_motivosConsulta == null)
		{
			try
			{
				icc_motivosConsulta = irr_referenceRemote.buscarMotivosConsulta(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icc_motivosConsulta = null;
			}
		}

		return icc_motivosConsulta;
	}

	/**
	 * Método encargado de consultar los valores de tipos de consignación.
	 *
	 * @return retorna el listado de valores que tiene la constante.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> buscarTiposConsignacion()
	    throws B2BException
	{
		if(ics_tiposConsignacion == null)
		{
			try
			{
				Constantes lcc_constante;

				lcc_constante = irr_referenceRemote.findConstantesById(ConstanteCommon.TIPO_CONSIGNACION_DEV_DINERO);

				if(lcc_constante != null)
					ics_tiposConsignacion = ListadoCodigosConstantes.generarCodigosCollection(
						    lcc_constante.getCaracter()
						);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ics_tiposConsignacion = null;
			}
		}

		return ics_tiposConsignacion;
	}

	/**
	 * Método encargado de consultar los valores de tipos de publicación.
	 *
	 * @return retorna el listado de valores que tiene la constante.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> buscarTiposPublicacion()
	    throws B2BException
	{
		if(ics_tiposPublicacion == null)
		{
			try
			{
				Constantes lcc_constante;

				lcc_constante = irr_referenceRemote.findConstantesById(ConstanteCommon.MEDIOS_PUBLICACION_AVISOS_WEB);

				if(lcc_constante != null)
					ics_tiposPublicacion = ListadoCodigosConstantes.generarCodigosCollection(
						    lcc_constante.getCaracter()
						);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ics_tiposPublicacion = null;
			}
		}

		return ics_tiposPublicacion;
	}

	/**
	 * Buscar todos circulos registrales origen destino activos.
	 *
	 * @param ab_b correspondiente al valor de ab b
	 * @return el valor de collection
	 */
	public Collection<CirculoRegistral> buscarTodosCirculosRegistralesOrigenDestinoActivos(boolean ab_b)
	{
		Map<Boolean, Collection<CirculoRegistral>> lmbccr_circulosRegistral;
		Collection<CirculoRegistral>               lcidt_datos;
		boolean                                    lb_circulosRegistral;
		Boolean                                    lb_argumento;

		lmbccr_circulosRegistral     = getCirculosRegistralesOrigenDestinoActivos();
		lcidt_datos                  = null;
		lb_circulosRegistral         = CollectionUtils.isValidCollection(lmbccr_circulosRegistral);
		lb_argumento                 = new Boolean(ab_b);

		try
		{
			if(lb_circulosRegistral)
				lcidt_datos = lmbccr_circulosRegistral.get(lb_argumento);

			if(!CollectionUtils.isValidCollection(lcidt_datos))
			{
				lcidt_datos = buscarTodosCirculosRegistralesOrigenDestinoActivos(ab_b, null);

				if(CollectionUtils.isValidCollection(lcidt_datos))
				{
					if(!lb_circulosRegistral)
						lmbccr_circulosRegistral = new HashMap<Boolean, Collection<CirculoRegistral>>();

					lmbccr_circulosRegistral.put(lb_argumento, lcidt_datos);

					setCirculosRegistralesOrigenDestinoActivos(lmbccr_circulosRegistral);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Buscar todos circulos registrales origen destino activos.
	 *
	 * @param ab_b correspondiente al valor de ab b
	 * @param as_idCirculoOrigen correspondiente al valor de as id circulo origen
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> buscarTodosCirculosRegistralesOrigenDestinoActivos(
	    boolean ab_b, String as_idCirculoOrigen
	)
	    throws B2BException
	{
		Collection<CirculoRegistral> lcidt_datos;

		lcidt_datos = null;

		try
		{
			String ls_cadenaCompleta;

			ls_cadenaCompleta = null;

			{
				StringBuilder lsb_cadena;

				lsb_cadena = new StringBuilder();

				lsb_cadena.append(ab_b);
				lsb_cadena.append("-");
				lsb_cadena.append(as_idCirculoOrigen);

				ls_cadenaCompleta = lsb_cadena.toString();
			}

			{
				Map<String, Collection<CirculoRegistral>> lmsccr_todosCirculosRegistrales;
				boolean                                   lb_esValido;

				lmsccr_todosCirculosRegistrales     = getTodosCirculosRegistrales();
				lb_esValido                         = CollectionUtils.isValidCollection(
					    lmsccr_todosCirculosRegistrales
					);

				if(lb_esValido)
					lcidt_datos = lmsccr_todosCirculosRegistrales.get(ls_cadenaCompleta);

				if(!CollectionUtils.isValidCollection(lcidt_datos))
				{
					lcidt_datos = irr_referenceRemote.buscarTodosCirculosRegistralesOrigenDestinoActivos(
						    ab_b, as_idCirculoOrigen, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(CollectionUtils.isValidCollection(lcidt_datos))
						lmsccr_todosCirculosRegistrales = new HashMap<String, Collection<CirculoRegistral>>();

					lmsccr_todosCirculosRegistrales.put(ls_cadenaCompleta, lcidt_datos);

					setTodosCirculosRegistrales(lmsccr_todosCirculosRegistrales);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Metodo encargado de consultar todos los municipios de un pais (Colombia).
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Municipio> buscarTodosMunicipiosPorPais()
	{
		if(icm_municipiosPorPais == null)
		{
			try
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
				lm_parametros.setActivo(EstadoCommon.S);

				icm_municipiosPorPais = irr_referenceRemote.buscarTodosMunicipiosPorPais(
					    lm_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icm_municipiosPorPais = null;
			}
		}

		return icm_municipiosPorPais;
	}

	/**
	 * Retorna el valor del objeto de Collection de CalidadSolicitante.
	 *
	 * @return devuelve el valor de Collection de CalidadSolicitante
	 */
	public Collection<CalidadSolicitante> calidadSolicitanteIndices()
	{
		if(iccs_calidadSolicitanteIndices == null)
		{
			try
			{
				iccs_calidadSolicitanteIndices = irr_referenceRemote.findCalidadSolicitante(true);

				if(CollectionUtils.isValidCollection(iccs_calidadSolicitanteIndices))
				{
					Iterator<CalidadSolicitante>   lics_ics;
					int                            li_i;
					Collection<CalidadSolicitante> lcidt_tmp;

					lics_ics      = iccs_calidadSolicitanteIndices.iterator();
					li_i          = 0;
					lcidt_tmp     = new ArrayList<CalidadSolicitante>();

					while(lics_ics.hasNext() && (li_i != 2))
					{
						CalidadSolicitante lcs_cs;
						lcs_cs = lics_ics.next();

						if(lcs_cs != null)
						{
							String ls_calidadSolicitante;
							ls_calidadSolicitante = StringUtils.getStringNotNull(lcs_cs.getIdCalidadSolicitante());

							if(ls_calidadSolicitante.equalsIgnoreCase("3"))
								lcidt_tmp.add(lcs_cs);

							if(ls_calidadSolicitante.equalsIgnoreCase("4"))
								lcidt_tmp.add(lcs_cs);
						}

						li_i = lcidt_tmp.size();
					}

					iccs_calidadSolicitanteIndices = lcidt_tmp;
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				iccs_calidadSolicitanteIndices = null;
			}
		}

		return iccs_calidadSolicitanteIndices;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<InteresadoDocumentoTipo> cargarDocumentosTipo()
	{
		if(icidt_cargarDocumentosTipo == null)
		{
			try
			{
				icidt_cargarDocumentosTipo = ipr_parameterRemote.findAllInteresadoDocumentoTipos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icidt_cargarDocumentosTipo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Consultas> cargarTipoConsulta()
	{
		if(icc_cargarTipoConsulta == null)
		{
			try
			{
				icc_cargarTipoConsulta = irr_referenceRemote.cargarTipoConsulta(
					    getUserId(), false, getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icc_cargarTipoConsulta;
	}

	/**
	 * Metodo encargado de consultar todos los motivos de consulta.
	 *
	 * @return devuelve el valor de Collection de causal negacion copias
	 */
	public Collection<CausalNegacionCopias> findAllCausalNegacionCopias()
	{
		if(iccnc_causalNegacionCopias == null)
		{
			try
			{
				iccnc_causalNegacionCopias = irr_referenceRemote.findAllCausalNegacionCopias(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				iccnc_causalNegacionCopias = null;
			}
		}

		return iccnc_causalNegacionCopias;
	}

	/**
	 * Método encargado de consultar los complementos para dirección.
	 *
	 * @param as_idTipoPredio Variable de tipo String que contiene el id tipo predio.
	 * @return Colección que contiene los datos de la busqueda.
	 */
	public Collection<TipoEje> findAllComplementoDireccionByTipoPredio(String as_idTipoPredio)
	{
		Map<String, Collection<TipoEje>> lmscte_tipoEje;
		Collection<TipoEje>              lcte_datos;
		boolean                          lb_tipoEje;

		lmscte_tipoEje     = getComplementoDireccionByTipoPredio();
		lcte_datos         = null;
		lb_tipoEje         = CollectionUtils.isValidCollection(lmscte_tipoEje);

		try
		{
			if(lb_tipoEje)
				lcte_datos = lmscte_tipoEje.get(as_idTipoPredio);

			if(!CollectionUtils.isValidCollection(lcte_datos))
			{
				lcte_datos = irr_referenceRemote.findAllComplementoDireccionByTipoPredio(as_idTipoPredio);

				if(CollectionUtils.isValidCollection(lcte_datos))
				{
					if(!lb_tipoEje)
						lmscte_tipoEje = new HashMap<String, Collection<TipoEje>>();

					lmscte_tipoEje.put(as_idTipoPredio, lcte_datos);

					setComplementoDireccionByTipoPredio(lmscte_tipoEje);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcte_datos = null;
		}

		return lcte_datos;
	}

	/**
	 * Find all coordenada activo.
	 *
	 * @return el valor de collection
	 */
	public Collection<Coordenada> findAllCoordenadaActivo()
	{
		if(icc_coordenadaActivo == null)
		{
			try
			{
				icc_coordenadaActivo = ipr_parameterRemote.findAllCoordenadaActivo();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icc_coordenadaActivo = null;
			}
		}

		return icc_coordenadaActivo;
	}

	/**
	 * Método para encontrar todos los EntidadExterna.
	 *
	 * @return Colección de EntidadExterna con los resultados
	 */
	public Collection<AccEntidadExterna> findAllEntidadExterna()
	{
		if(icaee_entidadExterna == null)
		{
			try
			{
				icaee_entidadExterna = irr_referenceRemote.findEntidadesExternasActivos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icaee_entidadExterna;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CalidadSolicitante> findAllInCalidadSolicitanteIndices()
	{
		if(iccs_inCalidadSolicitanteIndices == null)
		{
			try
			{
				CalidadSolicitante lcs_calidadSolicitante;

				lcs_calidadSolicitante = new CalidadSolicitante();

				lcs_calidadSolicitante.setSoloIncluir(IdentificadoresCommon.INDICES);

				iccs_inCalidadSolicitanteIndices = irr_referenceRemote.findAllInCalidadSolicitante(
					    lcs_calidadSolicitante, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				iccs_inCalidadSolicitanteIndices = null;
			}
		}

		return iccs_inCalidadSolicitanteIndices;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<LetraEje> findAllLetraEjeActivo()
	{
		if(icle_letraEjeActivo == null)
		{
			try
			{
				icle_letraEjeActivo = ipr_parameterRemote.findAllLetraEjeActivo();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icle_letraEjeActivo = null;
			}
		}

		return icle_letraEjeActivo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<NaturalezaJuridica> findAllMaxVersionNaturalezaJuridica(boolean ab_b)
	{
		Map<Boolean, Collection<NaturalezaJuridica>> lmbcnj_naturalezaJuridica;
		Collection<NaturalezaJuridica>               lcnj_datos;
		boolean                                      lb_naturalezaJuridica;
		Boolean                                      lb_argumento;

		lmbcnj_naturalezaJuridica     = getMaxVersionNaturalezaJuridica();
		lcnj_datos                    = null;
		lb_naturalezaJuridica         = CollectionUtils.isValidCollection(lmbcnj_naturalezaJuridica);
		lb_argumento                  = new Boolean(ab_b);

		try
		{
			if(lb_naturalezaJuridica)
				lcnj_datos = lmbcnj_naturalezaJuridica.get(lb_argumento);

			if(!CollectionUtils.isValidCollection(lcnj_datos))
			{
				lcnj_datos = irr_referenceRemote.findAllMaxVersionNaturalezaJuridica(ab_b);

				if(CollectionUtils.isValidCollection(lcnj_datos))
				{
					if(!lb_naturalezaJuridica)
						lmbcnj_naturalezaJuridica = new HashMap<Boolean, Collection<NaturalezaJuridica>>();

					lmbcnj_naturalezaJuridica.put(lb_argumento, lcnj_datos);

					setMaxVersionNaturalezaJuridica(lmbcnj_naturalezaJuridica);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcnj_datos = null;
		}

		return lcnj_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<MedidaArea> findAllMedidaAreaActivo()
	{
		if(icma_medidaAreaActivo == null)
		{
			try
			{
				icma_medidaAreaActivo = irr_referenceRemote.findAllMedidaAreaActivo();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icma_medidaAreaActivo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<NaturalezaJuridica> findAllNaturalezaJuridica(boolean ab_b)
	{
		Map<Boolean, Collection<NaturalezaJuridica>> lmbcnj_naturalezaJuridica;
		Collection<NaturalezaJuridica>               lcnj_datos;
		boolean                                      lb_naturalezaJuridica;
		Boolean                                      lb_argumento;

		lmbcnj_naturalezaJuridica     = getNaturalezaJuridica();
		lcnj_datos                    = null;
		lb_naturalezaJuridica         = CollectionUtils.isValidCollection(lmbcnj_naturalezaJuridica);
		lb_argumento                  = new Boolean(ab_b);

		try
		{
			if(lb_naturalezaJuridica)
				lcnj_datos = lmbcnj_naturalezaJuridica.get(lb_argumento);

			if(!CollectionUtils.isValidCollection(lcnj_datos))
			{
				lcnj_datos = irr_referenceRemote.findAllNaturalezaJuridica(ab_b);

				if(CollectionUtils.isValidCollection(lcnj_datos))
				{
					if(!lb_naturalezaJuridica)
						lmbcnj_naturalezaJuridica = new HashMap<Boolean, Collection<NaturalezaJuridica>>();

					lmbcnj_naturalezaJuridica.put(lb_argumento, lcnj_datos);

					setNaturalezaJuridica(lmbcnj_naturalezaJuridica);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcnj_datos = null;
		}

		return lcnj_datos;
	}

	/**
	 * Metodo pra encontrar todos los nombres de las columnas asociados a la tabla.
	 *
	 * @param as_nombreTabla nombre de la tabla
	 * @return collecciín de UserObjects con ell nombre de las columnas
	 */
	public Collection<UserObjects> findAllNombresColumnas(String as_nombreTabla)
	{
		Map<String, Collection<UserObjects>> lmscuo_userObjets;
		Collection<UserObjects>              lcuo_datos;
		boolean                              lb_userObjets;

		lmscuo_userObjets     = getNombresColumnas();
		lcuo_datos            = null;
		lb_userObjets         = CollectionUtils.isValidCollection(lmscuo_userObjets);

		try
		{
			if(lb_userObjets)
				lcuo_datos = lmscuo_userObjets.get(as_nombreTabla);

			if(!CollectionUtils.isValidCollection(lcuo_datos))
			{
				lcuo_datos = irr_referenceRemote.findAllNombresColumnas(
					    as_nombreTabla, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lcuo_datos))
				{
					if(!lb_userObjets)
						lmscuo_userObjets = new HashMap<String, Collection<UserObjects>>();

					lmscuo_userObjets.put(as_nombreTabla, lcuo_datos);

					setNombresColumnas(lmscuo_userObjets);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcuo_datos = null;
		}

		return lcuo_datos;
	}

	/**
	 * Mpetodo encargado de buscar todos los nombres tablas.
	 *
	 * @return el valor de collection
	 */
	public Collection<UserObjects> findAllNombresTablas()
	{
		if(icuo_nombresTablas == null)
		{
			try
			{
				icuo_nombresTablas = irr_referenceRemote.findAllNombresTablas(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icuo_nombresTablas = null;
			}
		}

		return icuo_nombresTablas;
	}

	/**
	 * Método encargado de buscar todas las opciones.
	 *
	 * @return el valor de collection
	 */
	public Collection<Opcion> findAllOpcion()
	{
		if(ico_opcion == null)
		{
			try
			{
				ico_opcion = ipr_parameterRemote.findAllOpcion(false);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return ico_opcion;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Plantilla> findAllPlantillasActivo()
	{
		if(icp_plantillasActivo == null)
		{
			try
			{
				icp_plantillasActivo = irr_referenceRemote.findAllPlantillasActivo(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icp_plantillasActivo = null;
			}
		}

		return icp_plantillasActivo;
	}

	/**
	 * Metodo para encontrar la constante de recepción de documentos.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> findAllRecepcionDocumento()
	    throws B2BException
	{
		if(ics_recepcionDocumento == null)
		{
			try
			{
				Constantes lcc_constante;

				lcc_constante = irr_referenceRemote.findConstantesById(ConstanteCommon.MEDIO_RECEPCION_DOCUMENTOS);

				if(lcc_constante != null)
					ics_recepcionDocumento = ListadoCodigosConstantes.generarCodigosCollection(
						    lcc_constante.getCaracter()
						);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ics_recepcionDocumento = null;
			}
		}

		return ics_recepcionDocumento;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Regional> findAllRegionalesActivos()
	{
		if(icr_regionalesActivos == null)
		{
			try
			{
				icr_regionalesActivos = irr_referenceRemote.findAllRegionalesActivos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icr_regionalesActivos;
	}

	/**
	 * Método para encontrar todos los roles.
	 *
	 * @return Colección de Rol con los resultados
	 */
	public Collection<Rol> findAllRol()
	{
		if(icr_rol == null)
		{
			try
			{
				icr_rol = ipr_parameterRemote.findAllRols(false);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icr_rol;
	}

	/**
	 * Retorna el valor del objeto de Map.
	 *
	 * @return devuelve el valor de Map
	 * @see Map
	 */
	public Map<String, TipoActo> findAllTipoActo()
	{
		if(imsta_tipoActo == null)
		{
			try
			{
				imsta_tipoActo = irr_referenceRemote.findAllTiposActoActivoData(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return imsta_tipoActo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoDecisionRecurso> findAllTipoDecisionRecurso()
	{
		if(ictdr_tipoDecisionRecurso == null)
		{
			try
			{
				ictdr_tipoDecisionRecurso = ipr_parameterRemote.findAllTipoDecisionRecurso(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictdr_tipoDecisionRecurso = null;
			}
		}

		return ictdr_tipoDecisionRecurso;
	}

	/**
	 * Método para encontrar todos los TipoDocumental.
	 *
	 * @return Colección de TipoDocumental con los resultados
	 */
	public Collection<TipoDocumental> findAllTipoDocumental()
	{
		if(ictd_tipoDocumental == null)
		{
			try
			{
				ictd_tipoDocumental = ipr_parameterRemote.findAllTipoDocumental();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return ictd_tipoDocumental;
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_PGN_TIPO_INTEGRACION_GOBERNACION.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoIntegracionGobernacion> findAllTipoIntegracionGobernacion(boolean ab_b)
	{
		Map<Boolean, Collection<TipoIntegracionGobernacion>> lmbctig_integracionGobernacion;
		Collection<TipoIntegracionGobernacion>               lcd_constantes;
		boolean                                              lb_integracionGobernacion;
		Boolean                                              lb_argumento;

		lmbctig_integracionGobernacion     = getTipoIntegracionGobernacion();
		lcd_constantes                     = null;
		lb_integracionGobernacion          = CollectionUtils.isValidCollection(lmbctig_integracionGobernacion);
		lb_argumento                       = null;

		try
		{
			if(lb_integracionGobernacion)
				lcd_constantes = lmbctig_integracionGobernacion.get(lb_argumento);

			if(!CollectionUtils.isValidCollection(lcd_constantes))
			{
				lcd_constantes = ipr_parameterRemote.findAllTipoIntegracionGobernacion(ab_b);

				if(!lb_integracionGobernacion)
					lmbctig_integracionGobernacion = new HashMap<Boolean, Collection<TipoIntegracionGobernacion>>();

				lmbctig_integracionGobernacion.put(lb_argumento, lcd_constantes);

				setTipoIntegracionGobernacion(lmbctig_integracionGobernacion);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcd_constantes;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoOficina> findAllTipoOficinaActivo()
	{
		if(icto_tipoOficinaActivo == null)
		{
			try
			{
				icto_tipoOficinaActivo = irr_referenceRemote.findAllTipoOficinaActivo(EstadoCommon.S, false);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icto_tipoOficinaActivo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoOficina> findAllTipoOficinaActivoById()
	{
		if(icto_tipoOficinaActivoById == null)
		{
			try
			{
				icto_tipoOficinaActivoById = irr_referenceRemote.findAllTipoOficinaActivo(EstadoCommon.S, true);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icto_tipoOficinaActivoById;
	}

	/**
	 * Metodo encargado de consultar todos los tipo recurso con activo igual a "S".
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoRecurso> findAllTipoRecursoByActivo()
	{
		if(ictr_tipoRecursoByActivo == null)
		{
			try
			{
				ictr_tipoRecursoByActivo = ipr_parameterRemote.findAllTipoRecursoByActivo(
					    EstadoCommon.S, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictr_tipoRecursoByActivo = null;
			}
		}

		return ictr_tipoRecursoByActivo;
	}

	/**
	 * Método para encontrar todos los TipoActo.
	 *
	 * @return Colección de TipoActo con los resultados
	 */
	public Collection<TipoActo> findAllTiposActo()
	{
		if(icta_tiposActo == null)
		{
			try
			{
				icta_tiposActo = irr_referenceRemote.findAllTiposActo();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icta_tiposActo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoDocumental> findAllTiposDocumentales()
	{
		if(ictd_tiposDocumentales == null)
		{
			try
			{
				ictd_tiposDocumentales = irr_referenceRemote.findAllTiposDocumentales(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), null
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return ictd_tiposDocumentales;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoRecepcion> findByHabilitadoNot()
	{
		if(ictr_habilitadoNot == null)
		{
			try
			{
				TipoRecepcion ltr_parametros;
				ltr_parametros = new TipoRecepcion();

				ltr_parametros.setHabilitadoNotificacion(EstadoCommon.S);

				ictr_habilitadoNot = irr_referenceRemote.findByHabilitado(
					    ltr_parametros, true, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictr_habilitadoNot = null;
			}
		}

		return ictr_habilitadoNot;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoRecepcion> findByHabilitadoNotNegat()
	{
		if(ictr_habilitadoNotNegat == null)
		{
			try
			{
				TipoRecepcion ltr_parametros;
				ltr_parametros = new TipoRecepcion();

				ltr_parametros.setHabilitadoNotificacion(EstadoCommon.N);

				ictr_habilitadoNotNegat = irr_referenceRemote.findByHabilitado(
					    ltr_parametros, true, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictr_habilitadoNotNegat = null;
			}
		}

		return ictr_habilitadoNotNegat;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoRecepcion> findByHabilitadoRec()
	{
		if(ictr_habilitadoRec == null)
		{
			try
			{
				TipoRecepcion ltr_parametros;
				ltr_parametros = new TipoRecepcion();

				ltr_parametros.setHabilitadoComunicacion(EstadoCommon.S);

				ictr_habilitadoRec = irr_referenceRemote.findByHabilitado(
					    ltr_parametros, false, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictr_habilitadoRec = null;
			}
		}

		return ictr_habilitadoRec;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoRecepcion> findByProcedencia()
	{
		if(ictr_procedencia == null)
		{
			try
			{
				TipoRecepcion ltr_parametros;
				ltr_parametros = new TipoRecepcion();

				ltr_parametros.setProcedencia(EstadoCommon.S);

				ictr_procedencia = irr_referenceRemote.findByProcedencia(ltr_parametros);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictr_procedencia = null;
			}
		}

		return ictr_procedencia;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_traslados variable que valida si es el proceso de traslados.
	 * @return devuelve el valor de Collection.
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitante(boolean ab_traslados)
	{
		Map<Boolean, Collection<CalidadSolicitante>> lmbccs_calidadSolicitante;
		Collection<CalidadSolicitante>               lcidt_datos;
		boolean                                      lb_calidadSolicitante;
		Boolean                                      lb_argumento;

		lmbccs_calidadSolicitante     = getCalidadSolicitante();
		lcidt_datos                   = null;
		lb_calidadSolicitante         = CollectionUtils.isValidCollection(lmbccs_calidadSolicitante);
		lb_argumento                  = null;

		try
		{
			if(lb_calidadSolicitante)
				lcidt_datos = lmbccs_calidadSolicitante.get(lb_argumento);

			if(!CollectionUtils.isValidCollection(lcidt_datos))
			{
				if(ab_traslados)
					lcidt_datos = irr_referenceRemote.findCalidadSolicitanteTraslado();
				else
					lcidt_datos = irr_referenceRemote.findCalidadSolicitante(true);

				if(CollectionUtils.isValidCollection(lcidt_datos))
				{
					if(!lb_calidadSolicitante)
						lmbccs_calidadSolicitante = new HashMap<Boolean, Collection<CalidadSolicitante>>();

					lmbccs_calidadSolicitante.put(lb_argumento, lcidt_datos);

					setCalidadSolicitante(lmbccs_calidadSolicitante);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitante()
	{
		return findCalidadSolicitante(false);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitanteEntrega()
	{
		if(iccs_calidadSolicitanteEntrega == null)
		{
			try
			{
				iccs_calidadSolicitanteEntrega = irr_referenceRemote.findCalidadSolicitanteEntrega(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), true
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				iccs_calidadSolicitanteEntrega = null;
			}
		}

		return iccs_calidadSolicitanteEntrega;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CamposConsulta> findCamposConsulta()
	{
		if(iccc_CamposConsulta == null)
		{
			try
			{
				iccc_CamposConsulta = irr_referenceRemote.findCamposConsulta(
					    false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				iccc_CamposConsulta = null;
			}
		}

		return iccc_CamposConsulta;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CirculoRegistral> findCirculoRegistral(boolean ab_b)
	{
		Map<Boolean, Collection<CirculoRegistral>> lmbccr_circuloRegistral;
		Collection<CirculoRegistral>               lccr_datos;
		boolean                                    lb_circuloRegistral;
		Boolean                                    lb_argumento;

		lmbccr_circuloRegistral     = getCirculoRegistral();
		lccr_datos                  = null;
		lb_circuloRegistral         = CollectionUtils.isValidCollection(lmbccr_circuloRegistral);
		lb_argumento                = new Boolean(ab_b);

		try
		{
			if(lb_circuloRegistral)
				lccr_datos = lmbccr_circuloRegistral.get(lb_argumento);

			if(!CollectionUtils.isValidCollection(lccr_datos))
			{
				lccr_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
					    ab_b, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lccr_datos))
				{
					if(!lb_circuloRegistral)
						lmbccr_circuloRegistral = new HashMap<Boolean, Collection<CirculoRegistral>>();

					lmbccr_circuloRegistral.put(lb_argumento, lccr_datos);

					setCirculoRegistral(lmbccr_circuloRegistral);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lccr_datos = null;
		}

		return lccr_datos;
	}

	/**
	 * Método de encontrar todos los circulos activos del usuario
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CirculoRegistral> findCirculoRegistralActivoUsuario()
	{
		if(iccr_circuloRegistralActivoUsuario == null)
		{
			try
			{
				iccr_circuloRegistralActivoUsuario = irr_referenceRemote.findAllActivoByUsuario(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return iccr_circuloRegistralActivoUsuario;
	}

	/**
	 * Retorna el valor del objeto de Collection de Coordenada.
	 *
	 * @return devuelve el valor de Collection de Coordenada
	 *
	 */
	public Collection<Coordenada> findCoordenada()
	{
		if(icc_Coordenada == null)
		{
			try
			{
				icc_Coordenada = irr_referenceRemote.findCoordenada();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icc_Coordenada = null;
			}
		}

		return icc_Coordenada;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Departamento> findDepartamentos()
	{
		return findDepartamentos(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idPais correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Departamento> findDepartamentos(String as_idPais)
	{
		Map<String, Collection<Departamento>> lmscd_departamentos;
		Collection<Departamento>              lcd_departamentos;
		boolean                               lb_departamentos;

		lmscd_departamentos     = getDepartamentos();
		lcd_departamentos       = null;
		lb_departamentos        = CollectionUtils.isValidCollection(lmscd_departamentos);

		try
		{
			if(lb_departamentos)
				lcd_departamentos = lmscd_departamentos.get(as_idPais);

			if(!CollectionUtils.isValidCollection(lcd_departamentos))
			{
				if(StringUtils.isValidString(as_idPais))
				{
					Departamento ld_parametros;

					ld_parametros = new Departamento();

					ld_parametros.setIdPais(as_idPais);

					lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
				}

				if(CollectionUtils.isValidCollection(lcd_departamentos))
				{
					if(CollectionUtils.isValidCollection(lcd_departamentos))
						lmscd_departamentos = new HashMap<String, Collection<Departamento>>();

					lmscd_departamentos.put(as_idPais, lcd_departamentos);

					setDepartamentos(lmscd_departamentos);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection de Letra Eje.
	 *
	 * @return devuelve el valor de Collection de Letra Eje
	 *
	 */
	public Collection<LetraEje> findEje()
	{
		if(icle_letraEje == null)
		{
			try
			{
				icle_letraEje = irr_referenceRemote.findLetraEje();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icle_letraEje = null;
			}
		}

		return icle_letraEje;
	}

	/**
	 * Retorna el valor del objeto de EstadoActividad.
	 *
	 * @param as_estado correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de EstadoActividad
	 * @see EstadoActividad
	 */
	public EstadoActividad findEstadoActividad(String as_estado)
	{
		Map<String, EstadoActividad> lmsea_estadoActividad;
		EstadoActividad              lea_estadoActividad;
		boolean                      lb_estadoActividad;

		lmsea_estadoActividad     = getEstadoActividad();
		lea_estadoActividad       = null;
		lb_estadoActividad        = CollectionUtils.isValidCollection(lmsea_estadoActividad);

		try
		{
			if(lb_estadoActividad)
				lea_estadoActividad = lmsea_estadoActividad.get(as_estado);

			if(lea_estadoActividad == null)
			{
				lea_estadoActividad = irr_referenceRemote.findEstadoActividad(as_estado);

				if(lea_estadoActividad != null)
				{
					if(!lb_estadoActividad)
						lmsea_estadoActividad = new HashMap<String, EstadoActividad>();

					lmsea_estadoActividad.put(as_estado, lea_estadoActividad);

					setEstadoActividad(lmsea_estadoActividad);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lea_estadoActividad = null;
		}

		return lea_estadoActividad;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<EstadoActividad> findEstadoActividadActivo()
	{
		if(icea_EstadoActividadActivo == null)
		{
			try
			{
				icea_EstadoActividadActivo = irr_referenceRemote.findEstadoActividadActivo(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icea_EstadoActividadActivo = null;
			}
		}

		return icea_EstadoActividadActivo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<EstadoAnotacion> findEstadoAnotacion()
	{
		if(icea_estadoAnotacion == null)
		{
			try
			{
				icea_estadoAnotacion = irr_referenceRemote.findEstadoAnotacion();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icea_estadoAnotacion = null;
			}
		}

		return icea_estadoAnotacion;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<EstadoPredio> findEstadoPredio()
	{
		if(icep_estadoPredio == null)
		{
			try
			{
				icep_estadoPredio = irr_referenceRemote.findEstadoPredios();

				if(!CollectionUtils.isValidCollection(icep_estadoPredio))
					icep_estadoPredio = null;
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icep_estadoPredio = null;
			}
		}

		return icep_estadoPredio;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Etapa> findEtapaActivo()
	{
		if(icc_etapaActivo == null)
		{
			try
			{
				icc_etapaActivo = irr_referenceRemote.findAllEtapasActivo(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icc_etapaActivo = null;
			}
		}

		return icc_etapaActivo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Etapa> findEtapaActivoById()
	{
		if(ice_etapaActivoById == null)
		{
			try
			{
				ice_etapaActivoById = irr_referenceRemote.findAllEtapasActivoById(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ice_etapaActivoById = null;
			}
		}

		return ice_etapaActivoById;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<InteresadoNaturalGenero> findGeneros()
	{
		if(icing_generos == null)
		{
			try
			{
				icing_generos = irr_referenceRemote.findGeneros();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icing_generos = null;
			}
		}

		return icing_generos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aod_od correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoOficina> findIdTipoOficina(Documento aod_od)
	{
		Collection<TipoOficina> lcidt_datos;

		lcidt_datos = null;

		try
		{
			if(aod_od != null)
			{
				String ls_cadenaCompleta;

				ls_cadenaCompleta = aod_od.getIdTipoDocumento();

				{
					Map<String, Collection<TipoOficina>> lmscto_tipoOficina;
					boolean                              lb_esValida;

					lmscto_tipoOficina     = getIdTipoOficina();
					lb_esValida            = CollectionUtils.isValidCollection(lmscto_tipoOficina);

					if(lb_esValida)
						lcidt_datos = lmscto_tipoOficina.get(ls_cadenaCompleta);

					if(!CollectionUtils.isValidCollection(lcidt_datos))
					{
						lcidt_datos = irr_referenceRemote.findTipoOficina(aod_od);

						if(CollectionUtils.isValidCollection(lcidt_datos))
						{
							if(!lb_esValida)
								lmscto_tipoOficina = new HashMap<String, Collection<TipoOficina>>();

							lmscto_tipoOficina.put(ls_cadenaCompleta, lcidt_datos);

							setIdTipoOficina(lmscto_tipoOficina);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<LibroAntiguoSistema> findLibroAntiguoSistema()
	{
		if(iclas_libroAntiguoSistema == null)
		{
			try
			{
				iclas_libroAntiguoSistema = irr_referenceRemote.findLibroAntiguoSistema();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				iclas_libroAntiguoSistema = null;
			}
		}

		return iclas_libroAntiguoSistema;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_arg correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<MedidaArea> findMedidaAreaById(String as_arg)
	{
		Map<String, Collection<MedidaArea>> lmscma_medidaArea;
		Collection<MedidaArea>              lcma_medidasArea;
		boolean                             lb_medidaArea;

		lmscma_medidaArea     = getMedidaAreaById();
		lcma_medidasArea      = null;
		lb_medidaArea         = CollectionUtils.isValidCollection(lmscma_medidaArea);

		try
		{
			if(lb_medidaArea)
				lcma_medidasArea = lmscma_medidaArea.get(as_arg);

			if(!CollectionUtils.isValidCollection(lcma_medidasArea))
			{
				lcma_medidasArea = irr_referenceRemote.findMedidaAreaById(as_arg);

				if(CollectionUtils.isValidCollection(lcma_medidasArea))
				{
					if(!lb_medidaArea)
						lmscma_medidaArea = new HashMap<String, Collection<MedidaArea>>();

					lmscma_medidaArea.put(as_arg, lcma_medidasArea);

					setMedidaAreaById(lmscma_medidaArea);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcma_medidasArea;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idPais correspondiente al valor del tipo de objeto String
	 * @param as_idDepartamento correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Municipio> findMunicipios(String as_idPais, String as_idDepartamento)
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		if(StringUtils.isValidString(as_idPais) && StringUtils.isValidString(as_idDepartamento))
		{
			try
			{
				String ls_cadenaCompleta;

				ls_cadenaCompleta = null;

				{
					StringBuilder lsb_cadena;

					lsb_cadena = new StringBuilder();

					lsb_cadena.append(as_idPais);
					lsb_cadena.append("-");
					lsb_cadena.append(as_idDepartamento);

					ls_cadenaCompleta = lsb_cadena.toString();
				}

				{
					Map<String, Collection<Municipio>> lmscm_municipios;
					boolean                            lb_esValida;

					lmscm_municipios     = getMunicipios();
					lb_esValida          = CollectionUtils.isValidCollection(lmscm_municipios);

					if(lb_esValida)
						lcm_municipios = lmscm_municipios.get(ls_cadenaCompleta);

					if(!CollectionUtils.isValidCollection(lcm_municipios))
					{
						Municipio lm_parametros;

						lm_parametros = new Municipio();

						lm_parametros.setIdPais(as_idPais);
						lm_parametros.setIdDepartamento(as_idDepartamento);

						lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);

						if(CollectionUtils.isValidCollection(lcm_municipios))
						{
							if(!lb_esValida)
								lmscm_municipios = new HashMap<String, Collection<Municipio>>();

							lmscm_municipios.put(ls_cadenaCompleta, lcm_municipios);

							setMunicipios(lmscm_municipios);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				lcm_municipios = null;
			}
		}

		return lcm_municipios;
	}

	/**
	 * Método para encontrar la maxíma version
	 * de la tabla SDB_PNG_NATURALEZA_JURIDICA unicamente coleccion de actos 0841 y 0842.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<NaturalezaJuridica> findNaturalezaJuridica0841and0842()
	{
		if(icnj_naturalezaJuridica0841and0842 == null)
		{
			try
			{
				icnj_naturalezaJuridica0841and0842 = irr_referenceRemote.findNaturalezaJuridica0841and0842();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icnj_naturalezaJuridica0841and0842 = null;
			}
		}

		return icnj_naturalezaJuridica0841and0842;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aoo_oficina correspondiente al valor del tipo de objeto OficinaOrigen
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<OficinaOrigen> findOficinaOrigen(OficinaOrigen aoo_oficina)
	{
		Collection<OficinaOrigen> lcoo_datos;

		lcoo_datos = null;

		try
		{
			if(aoo_oficina != null)
			{
				String ls_cadenaCompleta;

				ls_cadenaCompleta = null;

				{
					StringBuilder lsb_cadena;

					lsb_cadena = new StringBuilder();

					lsb_cadena.append(aoo_oficina.getIdTipoOficina());
					lsb_cadena.append("-");
					lsb_cadena.append(aoo_oficina.getIdPais());
					lsb_cadena.append("-");
					lsb_cadena.append(aoo_oficina.getIdDepartamento());
					lsb_cadena.append("-");
					lsb_cadena.append(aoo_oficina.getIdMunicipio());

					ls_cadenaCompleta = lsb_cadena.toString();
				}

				{
					Map<String, Collection<OficinaOrigen>> lmscoo_oficinaOrigen;
					boolean                                lb_esValida;

					lmscoo_oficinaOrigen     = getOficinaOrigen();
					lb_esValida              = CollectionUtils.isValidCollection(lmscoo_oficinaOrigen);

					if(lb_esValida)
						lcoo_datos = lmscoo_oficinaOrigen.get(ls_cadenaCompleta);

					if(!CollectionUtils.isValidCollection(lcoo_datos))
					{
						lcoo_datos = irr_referenceRemote.findOficinaOrigen(aoo_oficina);

						if(CollectionUtils.isValidCollection(lcoo_datos))
						{
							if(!lb_esValida)
								lmscoo_oficinaOrigen = new HashMap<String, Collection<OficinaOrigen>>();

							lmscoo_oficinaOrigen.put(ls_cadenaCompleta, lcoo_datos);

							setOficinaOrigen(lmscoo_oficinaOrigen);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcoo_datos = null;
		}

		return lcoo_datos;
	}

	/**
	 * Retorna el valor del objeto de OficinaOrigen.
	 *
	 * @param as_idOficinaOrigen correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de OficinaOrigen
	 * @see OficinaOrigen
	 */
	public OficinaOrigen findOficinaOrigenById(String as_idOficinaOrigen)
	{
		Map<String, OficinaOrigen> lmsoo_oficinaOrigen;
		OficinaOrigen              loo_oficinaOrigen;
		boolean                    lb_oficinaOrigen;

		lmsoo_oficinaOrigen     = getOficinaOrigenById();
		loo_oficinaOrigen       = null;
		lb_oficinaOrigen        = CollectionUtils.isValidCollection(lmsoo_oficinaOrigen);

		try
		{
			if(lb_oficinaOrigen)
				loo_oficinaOrigen = lmsoo_oficinaOrigen.get(as_idOficinaOrigen);

			if(loo_oficinaOrigen == null)
			{
				loo_oficinaOrigen = irr_referenceRemote.findOficinaOrigenById(as_idOficinaOrigen);

				if(loo_oficinaOrigen != null)
				{
					if(!lb_oficinaOrigen)
						lmsoo_oficinaOrigen = new HashMap<String, OficinaOrigen>();

					lmsoo_oficinaOrigen.put(as_idOficinaOrigen, loo_oficinaOrigen);

					setOficinaOrigenById(lmsoo_oficinaOrigen);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			loo_oficinaOrigen = null;
		}

		return loo_oficinaOrigen;
	}

	/**
	 * Retorna el valor del objeto de OficinaOrigen.
	 *
	 * @param as_idOficinaOrigen correspondiente al valor del tipo de objeto String
	 * @return devuelve una colección con el valor de OficinaOrigen
	 * @see OficinaOrigen
	 */
	public Collection<OficinaOrigen> findOficinaOrigenByIdCollection(String as_idOficinaOrigen)
	{
		Map<String, Collection<OficinaOrigen>> lmscoo_oficinaOrigen;
		Collection<OficinaOrigen>              lcoo_return;
		boolean                                lb_oficinaOrigen;

		lmscoo_oficinaOrigen     = getOficinaOrigenByIdCollection();
		lcoo_return              = null;
		lb_oficinaOrigen         = CollectionUtils.isValidCollection(lmscoo_oficinaOrigen);

		try
		{
			if(lb_oficinaOrigen)
				lcoo_return = lmscoo_oficinaOrigen.get(as_idOficinaOrigen);

			if(!CollectionUtils.isValidCollection(lcoo_return))
			{
				OficinaOrigen loo_oficinaOrigen;

				loo_oficinaOrigen = irr_referenceRemote.findOficinaOrigenById(as_idOficinaOrigen);

				if(loo_oficinaOrigen != null)
					lcoo_return.add(loo_oficinaOrigen);

				if(CollectionUtils.isValidCollection(lcoo_return))
				{
					if(!lb_oficinaOrigen)
						lmscoo_oficinaOrigen = new HashMap<String, Collection<OficinaOrigen>>();

					lmscoo_oficinaOrigen.put(as_idOficinaOrigen, lcoo_return);

					setOficinaOrigenByIdCollection(lmscoo_oficinaOrigen);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcoo_return = null;
		}

		return lcoo_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Pais> findPaises()
	{
		if(icp_paises == null)
		{
			try
			{
				icp_paises = irr_referenceRemote.findPaises(
					    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icp_paises = null;
			}
		}

		return icp_paises;
	}

	/**
	 * Retorna el valor del objeto de Collection de Proceso.
	 *
	 * @return devuelve el valor de Collection de Proceso
	 *
	 */
	public Collection<Proceso> findProcesos()
	{
		if(icp_procesos == null)
		{
			try
			{
				icp_procesos = irr_referenceRemote.findProcesos(false);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icp_procesos = null;
			}
		}

		return icp_procesos;
	}

	/**
	 * Retorna el valor del objeto de Collection de Proceso con los procesos activos.
	 *
	 * @return devuelve el valor de Collection de Proceso
	 *
	 */
	public Collection<Proceso> findProcesosActivos()
	{
		if(icpa_procesosActivos == null)
		{
			try
			{
				icpa_procesosActivos = irr_referenceRemote.findAllProcesosActivos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icpa_procesosActivos = null;
			}
		}

		return icpa_procesosActivos;
	}

	/**
	 * Retorna el valor del objeto de Collection de Proceso con los procesos activos.
	 *
	 * @return devuelve el valor de Collection de Proceso
	 *
	 */
	public Collection<Proceso> findProcesosActivosById()
	{
		if(icpabi_procesosActivosById == null)
		{
			try
			{
				icpabi_procesosActivosById = irr_referenceRemote.findAllProcesosActivosById(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icpabi_procesosActivosById = null;
			}
		}

		return icpabi_procesosActivosById;
	}

	/**
	 * Retorna el valor del objeto de Collection de ProcesoAutomatico.
	 *
	 * @return devuelve el valor de Collection de ProcesoAutomatico
	 *
	 */
	public Collection<ProcesoAutomatico> findProcesosAutomaticos()
	{
		if(icpa_procesosAutomaticos == null)
		{
			try
			{
				icpa_procesosAutomaticos = irr_referenceRemote.findProcesosAutomaticos(
					    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icpa_procesosAutomaticos = null;
			}
		}

		return icpa_procesosAutomaticos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<ResultadoConsulta> findResultadoConsulta()
	{
		if(icrc_resultadoConsulta == null)    /* ES USADO POR VARIOS EJB, SE EVIDENCIA EN EL BUSSINESS */
		{
			try
			{
				icrc_resultadoConsulta = irr_referenceRemote.findResultadoConsulta(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icrc_resultadoConsulta = null;
			}
		}

		return icrc_resultadoConsulta;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Subproceso> findSubprocesos()
	{
		if(ics_subprocesos == null)
		{
			try
			{
				ics_subprocesos = irr_referenceRemote.findSubprocesos(true);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ics_subprocesos = null;
			}
		}

		return ics_subprocesos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idProceso correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Subproceso> findSubprocesosByProceso(String as_idProceso)
	{
		Map<String, Collection<Subproceso>> lmscs_subprocesosByProceso;
		Collection<Subproceso>              lcs_subprocesos;
		boolean                             lb_subprocesos;

		lmscs_subprocesosByProceso     = getSubprocesosByProceso();
		lcs_subprocesos                = null;
		lb_subprocesos                 = CollectionUtils.isValidCollection(lmscs_subprocesosByProceso);

		try
		{
			if(lb_subprocesos)
				lcs_subprocesos = lmscs_subprocesosByProceso.get(as_idProceso);

			if(!CollectionUtils.isValidCollection(lcs_subprocesos))
			{
				Subproceso lsp_subproceso;

				lsp_subproceso = new Subproceso();

				lsp_subproceso.setIdProceso(as_idProceso);

				lcs_subprocesos = irr_referenceRemote.findSubprocesosByProceso(
					    lsp_subproceso, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lcs_subprocesos))
				{
					if(!lb_subprocesos)
						lmscs_subprocesosByProceso = new HashMap<String, Collection<Subproceso>>();

					lmscs_subprocesosByProceso.put(as_idProceso, lcs_subprocesos);

					setSubprocesosByProceso(lmscs_subprocesosByProceso);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcs_subprocesos = null;
		}

		return lcs_subprocesos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idProceso correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Subproceso> findSubprocesosByProcesoId(String as_idProceso)
	{
		Map<String, Collection<Subproceso>> lmscs_subprocesos;
		Collection<Subproceso>              lcs_subprocesos;
		boolean                             lb_subprocesos;

		lmscs_subprocesos     = getSubprocesosByProcesoId();
		lcs_subprocesos       = null;
		lb_subprocesos        = CollectionUtils.isValidCollection(lmscs_subprocesos);

		try
		{
			if(lb_subprocesos)
				lcs_subprocesos = lmscs_subprocesos.get(as_idProceso);

			if(!CollectionUtils.isValidCollection(lcs_subprocesos))
			{
				Subproceso lsp_subproceso;

				lsp_subproceso = new Subproceso();

				lsp_subproceso.setIdProceso(as_idProceso);

				lcs_subprocesos = irr_referenceRemote.findSubprocesosByProceso(
					    lsp_subproceso, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lcs_subprocesos))
				{
					if(!lb_subprocesos)
						lmscs_subprocesos = new HashMap<String, Collection<Subproceso>>();

					lmscs_subprocesos.put(as_idProceso, lcs_subprocesos);

					setSubprocesosByProcesoId(lmscs_subprocesos);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcs_subprocesos = null;
		}

		return lcs_subprocesos;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoAdquisicion.
	 *
	 * @return devuelve el valor de Collection de TipoAdquisicion
	 *
	 */
	public Collection<TipoAdquisicion> findTipoAdquisicionActivo()
	{
		if(icaa_adquisicionActivo == null)
		{
			try
			{
				icaa_adquisicionActivo = irr_referenceRemote.findTipoAdquisicion(EstadoCommon.S);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icaa_adquisicionActivo = null;
			}
		}

		return icaa_adquisicionActivo;
	}

	/**
	 * Método para encontrar todos los TipoCriterioBusqueda.
	 *
	 * @return Colección de CriteriosDeBusqueda con los resultados
	 */
	public Collection<CriteriosDeBusqueda> findTipoCriterioBusqueda()
	{
		if(ictcb_tipoCriterioBusqueda == null)
		{
			try
			{
				ictcb_tipoCriterioBusqueda = irr_referenceRemote.findAllTipoCriterioBusqueda();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictcb_tipoCriterioBusqueda = null;
			}
		}

		return ictcb_tipoCriterioBusqueda;
	}

	/**
	 * Retorna el valor del objeto de Collection de InteresadoDocumentoTipo.
	 *
	 * @return devuelve el valor de Collection de InteresadoDocumentoTipo
	 *
	 */
	public Collection<InteresadoDocumentoTipo> findTipoDocumento()
	{
		if(icidt_interesadoDocumentoTipo == null)
		{
			try
			{
				icidt_interesadoDocumentoTipo = irr_referenceRemote.findTipoDocumento();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icidt_interesadoDocumentoTipo = null;
			}
		}

		return icidt_interesadoDocumentoTipo;
	}

	/**
	 * Retorna el valor del objeto de Collection de InteresadoDocumentoTipo.
	 *
	 * @return devuelve el valor de Collection de InteresadoDocumentoTipo
	 *
	 */
	public Collection<InteresadoDocumentoTipo> findTipoDocumentoActivo()
	{
		if(icfdp_findDocumentoPublico == null)
		{
			try
			{
				icfdp_findDocumentoPublico = irr_referenceRemote.findTipoDocumentoActivo();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icfdp_findDocumentoPublico = null;
			}
		}

		return icfdp_findDocumentoPublico;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoDocumentoPublico.
	 *
	 * @return devuelve el valor de Collection de TipoDocumentoPublico
	 *
	 */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublico()
	{
		if(icdp_documentoPublico == null)
		{
			try
			{
				icdp_documentoPublico = irr_referenceRemote.findTipoDocumentoPublico();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icdp_documentoPublico = null;
			}
		}

		return icdp_documentoPublico;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoDocumentoPublico.
	 *
	 * @return devuelve el valor de Collection de TipoDocumentoPublico
	 *
	 */
	public Collection<TipoDocumentoPublico> findTipoDocumentoPublicoActivo()
	{
		if(icdpa_documentoPublicoActivo == null)
		{
			try
			{
				icdpa_documentoPublicoActivo = irr_referenceRemote.findTipoDocumentoPublico(EstadoCommon.S);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icdpa_documentoPublicoActivo = null;
			}
		}

		return icdpa_documentoPublicoActivo;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoEje.
	 *
	 * @return devuelve el valor de Collection de TipoEje
	 *
	 */
	public Collection<TipoEje> findTipoEje()
	{
		if(icte_tipoEje == null)
		{
			try
			{
				icte_tipoEje = irr_referenceRemote.findTipoEje();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icte_tipoEje = null;
			}
		}

		return icte_tipoEje;
	}

	/**
	 * Método encargado de consultar tipo eje con base a un id tipo predio.
	 *
	 * @param as_tipoPredio Variable de tipo String que contiene el id tipo predio.
	 * @return Colección de tipoEje.
	 */
	public Collection<TipoEje> findTipoEjeByTipoPredio(String as_tipoPredio)
	{
		Map<String, Collection<TipoEje>> lmscte_tipoEjeByTipoPredio;
		Collection<TipoEje>              lcte_tipoEjeByTipoPredio;
		boolean                          lb_tipoEjeByTipoPredio;

		lmscte_tipoEjeByTipoPredio     = getTipoEjeByTipoPredio();
		lcte_tipoEjeByTipoPredio       = null;
		lb_tipoEjeByTipoPredio         = CollectionUtils.isValidCollection(lmscte_tipoEjeByTipoPredio);

		try
		{
			if(lb_tipoEjeByTipoPredio)
				lcte_tipoEjeByTipoPredio = lmscte_tipoEjeByTipoPredio.get(as_tipoPredio);

			if(!CollectionUtils.isValidCollection(lcte_tipoEjeByTipoPredio))
			{
				lcte_tipoEjeByTipoPredio = irr_referenceRemote.findTipoEjeByTipoPredio(as_tipoPredio);

				if(CollectionUtils.isValidCollection(lcte_tipoEjeByTipoPredio))
					lmscte_tipoEjeByTipoPredio = new HashMap<String, Collection<TipoEje>>();

				lmscte_tipoEjeByTipoPredio.put(as_tipoPredio, lcte_tipoEjeByTipoPredio);

				setTipoEjeByTipoPredio(lmscte_tipoEjeByTipoPredio);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcte_tipoEjeByTipoPredio = null;
		}

		return lcte_tipoEjeByTipoPredio;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoEntidad.
	 *
	 * @return devuelve el valor de Collection de TipoEntidad
	 *
	 */
	public Collection<TipoEntidad> findTipoEntidad()
	{
		if(icdte_datosTipoEntidad == null)
		{
			try
			{
				icdte_datosTipoEntidad = irr_referenceRemote.findTipoEntidad(false);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icdte_datosTipoEntidad = null;
			}
		}

		return icdte_datosTipoEntidad;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoEntidad activos.
	 *
	 * @return devuelve el valor de Collection de TipoEntidad
	 *
	 */
	public Collection<TipoEntidad> findTipoEntidadActivo()
	{
		if(ictea_tipoEntidadActivo == null)
		{
			try
			{
				ictea_tipoEntidadActivo = irr_referenceRemote.findTipoEntidad(true);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictea_tipoEntidadActivo = null;
			}
		}

		return ictea_tipoEntidadActivo;
	}

	/**
	 * Retorna el valor del objeto de TipoEntidad.
	 *
	 * @param ate_te correspondiente al valor del tipo de objeto TipoEntidad
	 * @return devuelve el valor de TipoEntidad
	 * @see TipoEntidad
	 */
	public TipoEntidad findTipoEntidadById(TipoEntidad ate_te)
	{
		TipoEntidad lte_datos;

		lte_datos = null;

		try
		{
			if(ate_te != null)
			{
				String ls_cadenaCompleta;

				ls_cadenaCompleta = ate_te.getIdTipoEntidad();

				{
					Map<String, TipoEntidad> lmste_tipoEntidad;
					boolean                  lb_esValida;

					lmste_tipoEntidad     = getTipoEntidadById();
					lb_esValida           = CollectionUtils.isValidCollection(lmste_tipoEntidad);

					if(lb_esValida)
						lte_datos = lmste_tipoEntidad.get(ls_cadenaCompleta);

					if(lte_datos == null)
					{
						lte_datos = irr_referenceRemote.findTipoEntidadById(ate_te);

						if(lte_datos != null)
						{
							if(!lb_esValida)
								lmste_tipoEntidad = new HashMap<String, TipoEntidad>();

							lmste_tipoEntidad.put(ls_cadenaCompleta, lte_datos);

							setTipoEntidadById(lmste_tipoEntidad);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lte_datos = null;
		}

		return lte_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection de AccEntidadExterna.
	 *
	 * @param as_idTipoEntidad de as id tipo entidad
	 * @return devuelve el valor de Collection de AccEntidadExterna
	 */
	public Collection<AccEntidadExterna> findTipoEntidadExterna(String as_idTipoEntidad)
	{
		Map<String, Collection<AccEntidadExterna>> lmcaee_tipoEntidadExterna;
		Collection<AccEntidadExterna>              lcaee_tipoEntidadExterna;
		boolean                                    lb_tipoEntidadExterna;

		lmcaee_tipoEntidadExterna     = getTipoEntidadExterna();
		lcaee_tipoEntidadExterna      = null;
		lb_tipoEntidadExterna         = CollectionUtils.isValidCollection(lmcaee_tipoEntidadExterna);

		try
		{
			if(lb_tipoEntidadExterna)
				lcaee_tipoEntidadExterna = lmcaee_tipoEntidadExterna.get(as_idTipoEntidad);

			if(!CollectionUtils.isValidCollection(lcaee_tipoEntidadExterna))
			{
				lcaee_tipoEntidadExterna = irr_referenceRemote.findTipoEntidadExterna(as_idTipoEntidad, false, false);

				if(CollectionUtils.isValidCollection(lcaee_tipoEntidadExterna))
					lmcaee_tipoEntidadExterna = new HashMap<String, Collection<AccEntidadExterna>>();

				lmcaee_tipoEntidadExterna.put(as_idTipoEntidad, lcaee_tipoEntidadExterna);

				setTipoEntidadExterna(lmcaee_tipoEntidadExterna);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcaee_tipoEntidadExterna = null;
		}

		return lcaee_tipoEntidadExterna;
	}

	/**
	 * Retorna el valor del objeto de Collection de AccEntidadExterna.
	 *
	 * @param as_idTipoEntidad de as id tipo entidad
	 * @return devuelve el valor de Collection de AccEntidadExterna
	 */
	public Collection<AccEntidadExterna> findTipoEntidadExternaActivo(String as_idTipoEntidad)
	{
		Map<String, Collection<AccEntidadExterna>> lmscaee_tipoEntidadExternaActivo;
		Collection<AccEntidadExterna>              lcaee_tipoEntidadExternaActivo;
		boolean                                    lb_tipoEntidadExternaActivo;

		lmscaee_tipoEntidadExternaActivo     = getTipoEntidadExternaActivo();
		lcaee_tipoEntidadExternaActivo       = null;
		lb_tipoEntidadExternaActivo          = CollectionUtils.isValidCollection(lmscaee_tipoEntidadExternaActivo);

		try
		{
			if(lb_tipoEntidadExternaActivo)
				lcaee_tipoEntidadExternaActivo = lmscaee_tipoEntidadExternaActivo.get(as_idTipoEntidad);

			if(!CollectionUtils.isValidCollection(lcaee_tipoEntidadExternaActivo))
			{
				lcaee_tipoEntidadExternaActivo = irr_referenceRemote.findTipoEntidadExterna(
					    as_idTipoEntidad, true, true
					);

				if(CollectionUtils.isValidCollection(lcaee_tipoEntidadExternaActivo))
				{
					if(!lb_tipoEntidadExternaActivo)
						lmscaee_tipoEntidadExternaActivo = new HashMap<String, Collection<AccEntidadExterna>>();

					lmscaee_tipoEntidadExternaActivo.put(as_idTipoEntidad, lcaee_tipoEntidadExternaActivo);

					setTipoEntidadExternaActivo(lmscaee_tipoEntidadExternaActivo);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcaee_tipoEntidadExternaActivo = null;
		}

		return lcaee_tipoEntidadExternaActivo;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoOficina.
	 *
	 * @return devuelve el valor de Collection de TipoOficina
	 *
	 */
	public Collection<TipoOficina> findTipoOficina()
	{
		if(icdto_datosTipoOficina == null)
		{
			try
			{
				icdto_datosTipoOficina = irr_referenceRemote.findTipoOficina();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icdto_datosTipoOficina = null;
			}
		}

		return icdto_datosTipoOficina;
	}

	/**
	 * Retorna el valor del objeto de TipoOficina.
	 *
	 * @param ato_to correspondiente al valor del tipo de objeto TipoOficina
	 * @return devuelve el valor de TipoOficina
	 * @see TipoOficina
	 */
	public TipoOficina findTipoOficinaById(TipoOficina ato_to)
	{
		TipoOficina lto_datos;

		lto_datos = null;

		try
		{
			if(ato_to != null)
			{
				String ls_cadenaCompleta;

				ls_cadenaCompleta = ato_to.getIdTipoOficina();

				{
					Map<String, TipoOficina> lmsto_tipoOficina;
					boolean                  lb_esValido;

					lmsto_tipoOficina     = getTipoOficinaById();
					lb_esValido           = CollectionUtils.isValidCollection(lmsto_tipoOficina);

					if(lb_esValido)
						lto_datos = lmsto_tipoOficina.get(ls_cadenaCompleta);

					if(lto_datos == null)
					{
						lto_datos = irr_referenceRemote.findTipoOficinaById(ato_to);

						if(lto_datos != null)
							lmsto_tipoOficina = new HashMap<String, TipoOficina>();

						lmsto_tipoOficina.put(ls_cadenaCompleta, lto_datos);

						setTipoOficinaById(lmsto_tipoOficina);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lto_datos = null;
		}

		return lto_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoPersona.
	 *
	 * @return devuelve el valor de Collection de TipoPersona
	 *
	 */
	public Collection<TipoPersona> findTipoPersona()
	{
		if(ictp_tipoPersona == null)
		{
			try
			{
				ictp_tipoPersona = irr_referenceRemote.findTipoPersona();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictp_tipoPersona = null;
			}
		}

		return ictp_tipoPersona;
	}

	/**
	 * Retorna el valor del objeto de Collection de PredioTipo.
	 *
	 * @return devuelve el valor de Collection de PredioTipo
	 *
	 */
	public Collection<PredioTipo> findTipoPredio()
	{
		if(ictp_tipoPredio == null)
		{
			try
			{
				ictp_tipoPredio = irr_referenceRemote.findTipoPredio(
					    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictp_tipoPredio = null;
			}
		}

		return ictp_tipoPredio;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoRrr> findTipoRRR()
	{
		if(ictr_tipoRrr == null)
		{
			try
			{
				ictr_tipoRrr = irr_referenceRemote.findTipoRRR();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictr_tipoRrr = null;
			}
		}

		return ictr_tipoRrr;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoRequiereMatricula.
	 *
	 * @return devuelve el valor de Collection de TipoRequiereMatricula
	 *
	 */
	public Collection<TipoRequiereMatricula> findTipoRequiereMatriculaActivos()
	{
		if(ictrm_requiereMatriculaActivos == null)
		{
			try
			{
				ictrm_requiereMatriculaActivos = irr_referenceRemote.findTipoRequiereMatriculaActivos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return ictrm_requiereMatriculaActivos;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoTarifaRegistral.
	 *
	 * @return devuelve el valor de Collection de TipoTarifaRegistral
	 *
	 */
	public Collection<TipoTarifaRegistral> findTipoTarifaRegistral()
	{
		if(icttr_tipoTarifaRegistral == null)
			try
			{
				icttr_tipoTarifaRegistral = irr_referenceRemote.findAllTipoTarifaRegistralActivos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				icttr_tipoTarifaRegistral = null;
			}

		return icttr_tipoTarifaRegistral;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoUsoSuelo.
	 *
	 * @return devuelve el valor de Collection de TipoUsoSuelo
	 *
	 */
	public Collection<TipoUsoSuelo> findTipoUsoSuelo()
	{
		if(ictus_tipoUsoSuelo == null)
		{
			try
			{
				ictus_tipoUsoSuelo = irr_referenceRemote.findTipoUsoSuelo();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				ictus_tipoUsoSuelo = null;
			}
		}

		return ictus_tipoUsoSuelo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoActo> findTiposActosCodigo()
	{
		if(icta_tiposActosCodigo == null)
		{
			try
			{
				icta_tiposActosCodigo = irr_referenceRemote.findAllTiposActoActivos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), true, false, false
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icta_tiposActosCodigo;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoActo.
	 *
	 * @return devuelve el valor de Collection de TipoActo
	 *
	 */
	public Collection<TipoActo> findTiposActosCodigo4Y5Digitos()
	{
		if(icta_codigo4Y5Digitos == null)
		{
			try
			{
				icta_codigo4Y5Digitos = irr_referenceRemote.findTiposActosCodigo4Y5Digitos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), true
					);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icta_codigo4Y5Digitos;
	}

	/**
	 * Retorna el valor del objeto de Collection de Vereda.
	 *
	 * @param as_idPais correspondiente al valor del tipo de objeto String
	 * @param as_idDepartamento correspondiente al valor del tipo de objeto String
	 * @param as_idMunicipio correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de Vereda
	 *
	 */
	public Collection<Vereda> findVeredas(String as_idPais, String as_idDepartamento, String as_idMunicipio)
	{
		Collection<Vereda> lcv_veredas;

		lcv_veredas = null;

		try
		{
			String ls_cadenaCompleta;

			ls_cadenaCompleta = null;

			{
				StringBuilder lsb_cadena;

				lsb_cadena = new StringBuilder();

				lsb_cadena.append(as_idPais);
				lsb_cadena.append("-");
				lsb_cadena.append(as_idDepartamento);
				lsb_cadena.append("-");
				lsb_cadena.append(as_idMunicipio);

				ls_cadenaCompleta = lsb_cadena.toString();
			}

			{
				Map<String, Collection<Vereda>> lmscv_veredas;
				boolean                         lb_esValida;

				lmscv_veredas     = getVeredas();
				lb_esValida       = CollectionUtils.isValidCollection(lmscv_veredas);

				if(lb_esValida)
					lcv_veredas = lmscv_veredas.get(ls_cadenaCompleta);

				if(!CollectionUtils.isValidCollection(lcv_veredas))
				{
					Vereda lm_parametros;

					lm_parametros = new Vereda();

					lm_parametros.setIdPais(as_idPais);
					lm_parametros.setIdDepartamento(as_idDepartamento);
					lm_parametros.setIdMunicipio(as_idMunicipio);

					lcv_veredas = irr_referenceRemote.findVeredas(lm_parametros, false);

					if(CollectionUtils.isValidCollection(lcv_veredas))
					{
						if(!lb_esValida)
							lmscv_veredas = new HashMap<String, Collection<Vereda>>();

						lmscv_veredas.put(ls_cadenaCompleta, lcv_veredas);

						setVeredas(lmscv_veredas);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcv_veredas = null;
		}

		return lcv_veredas;
	}

	/**
	 * Retorna el valor del objeto de Collection de Constantes.
	 *
	 * @return devuelve el valor de Collection de Constantes
	 *
	 */
	public Collection<Constantes> tiposConsulta()
	{
		return tiposConsulta(false);
	}

	/**
	 * Retorna el valor del objeto de Collection de Constantes.
	 *
	 * @param ab_noExenta Argumento de tipo <code>boolean</code> que determina si es una entidad exenta.
	 *
	 * @return devuelve el valor de Collection de Constantes
	 *
	 */
	public Collection<Constantes> tiposConsulta(boolean ab_noExenta)
	{
		Map<Boolean, Collection<Constantes>> lmbcc_tiposConsulta;
		Collection<Constantes>               lc_tiposConsulta;
		boolean                              lb_tiposConsulta;
		Boolean                              lb_argumentoTiposConsulta;

		lmbcc_tiposConsulta           = getTiposConsulta();
		lc_tiposConsulta              = null;
		lb_tiposConsulta              = CollectionUtils.isValidCollection(lmbcc_tiposConsulta);
		lb_argumentoTiposConsulta     = new Boolean(ab_noExenta);

		try
		{
			if(lb_tiposConsulta)
				lc_tiposConsulta = lmbcc_tiposConsulta.get(lb_argumentoTiposConsulta);

			if(!CollectionUtils.isValidCollection(lc_tiposConsulta))
			{
				Constantes lc_c;
				lc_c = irr_referenceRemote.findConstantesById(ConstanteCommon.TIPOS_CONSULTA);

				if(lc_c != null)
				{
					Map<String, String> lmss_caracter;

					lmss_caracter = ListadoCodigosConstantes.generarCodigos(lc_c.getCaracter());

					if(lmss_caracter != null)
					{
						lc_tiposConsulta = new ArrayList<Constantes>();

						for(Map.Entry<String, String> lmss_mss : lmss_caracter.entrySet())
						{
							if(lmss_mss != null)
							{
								Constantes lc_tmp;
								String     ls_value;

								lc_tmp       = new Constantes();
								ls_value     = lmss_mss.getValue();

								if(
								    (StringUtils.isValidString(ls_value) && !ab_noExenta)
									    || (ab_noExenta && !ls_value.equalsIgnoreCase(EstadoCommon.MASIVO))
								)
								{
									lc_tmp.setCaracter(ls_value.toUpperCase());

									lc_tiposConsulta.add(lc_tmp);
								}
							}
						}
					}
				}

				if(CollectionUtils.isValidCollection(lc_tiposConsulta))
				{
					if(!lb_tiposConsulta)
						lmbcc_tiposConsulta = new HashMap<Boolean, Collection<Constantes>>();

					lmbcc_tiposConsulta.put(lb_argumentoTiposConsulta, lc_tiposConsulta);

					setTiposConsulta(lmbcc_tiposConsulta);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lc_tiposConsulta = null;
		}

		return lc_tiposConsulta;
	}
}
