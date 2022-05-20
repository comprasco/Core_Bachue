package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.MotivonNoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.ejb.session.stateless.aprobacion.AprobacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.aprobador.antiguo.sistema.AprobadorAntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.aprobacion.FirmaAprobacion;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.correcciones.BeanVisualizacionCorrecciones;
import com.bachue.snr.prosnr01.web.bean.publicacionAvisosWeb.BeanPublicacionAvisosWeb;
import com.bachue.snr.prosnr01.web.bean.visitas.BeanDetalleEjecucionVisitas;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import org.primefaces.model.chart.PieChartModel;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import java.util.Map.Entry;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import javax.servlet.http.HttpServletRequest;


/**
 * Contiene los métodos para el manejo de casos en etapa 190, de aprobación, por
 * ejemplo el proceso de firma o devolución.
 *
 * @author Alejandro Santos
 *
 */
@SessionScoped
@ManagedBean(name = "beanAprobacion")
public class BeanAprobacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3049638354872673934L;

	/** Constante is_idMessageGrowl. */
	private static final String is_idMessageGrowl = "fBandejaAprobacion:idGrowl";

	/** Constante is_idMessageGrowl2. */
	private static final String is_idMessageGrowl2 = "fAprobacion:globalMsg";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAprobacion.class);

	/** Constante cs_PREGUNTA_APROBAR_RELACIONES_DIALOG. */
	private static final String cs_PREGUNTA_APROBAR_RELACIONES_DIALOG = "preguntaAprobarRelaciones";

	/** Propiedad ioa detalle bandeja. */
	private Aprobacion ioa_detalleBandeja;

	/** Propiedad irr aprobacion remote. */
	@EJB
	private AprobacionRemote irr_aprobacionRemote;

	/** Propiedad iaas aprobador antiguo sistema remote. */
	@EJB
	private AprobadorAntiguoSistemaRemote iaas_aprobadorAntiguoSistemaRemote;

	/** Propiedad ica data bandeja. */
	private Collection<Aprobacion> ica_dataBandeja;

	/** Propiedad ica tramites aprobacion. */
	private Collection<Aprobacion> ica_tramitesAprobacion;

	/** Propiedad ica tramites detalle motivo. */
	private Collection<Aprobacion> ica_tramitesDetalleMotivo;

	/** Propiedad icfa firmas masivo. */
	private Collection<FirmaAprobacion> icfa_firmasMasivo;

	/** Propiedad ila data aprobacion. */
	private Collection<Aprobacion> ila_dataAprobacion;

	/** Propiedad ila filtro aprobacion. */
	private Collection<Aprobacion> ila_filtroAprobacion;

	/** Propiedad il id etapa. */
	private Long il_idEtapa;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad ilhm documentos generados. */
	private Map<String, byte[]> ilhm_documentosGenerados;

	/** Propiedad ipcm pie chart model. */
	private PieChartModel ipcm_pieChartModel;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad isc zip documentos relacion. */
	private StreamedContent isc_zipDocumentosRelacion;

	/** Propiedad is filtro firma. */
	private String is_filtroFirma;

	/** Propiedad is id proceso seleccionado. */
	private String is_idProcesoSeleccionado;

	/** Propiedad is id tipo decision recurso. */
	private String is_idTipoDecisionRecurso;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is label boton. */
	private String is_labelBoton;

	/** Propiedad is motivo tramite seleccionado. */
	private String is_motivoTramiteSeleccionado;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre tipo decision recurso. */
	private String is_nombreTipoDecisionRecurso;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is observaciones temporales. */
	private String is_observacionesTemporales;

	/** Propiedad is observaciones turno historia. */
	private String is_observacionesTurnoHistoria;

	/** Propiedad is relaciones creadas string. */
	private String is_relacionesCreadasString;

	/** Propiedad is subproceso seleccionado. */
	private String is_subprocesoSeleccionado;

	/** Propiedad ib zip file. */
	private byte[] ib_zipFile;

	/** Propiedad ab proceso certificados. */
	private boolean ab_procesoCertificados;

	/** Propiedad ib mostrar firmar. */
	private boolean ib_esDesistimiento;

	/** Propiedad ib recursos. */
	private boolean ib_esRecursos;

	/** Propiedad ib mostrar bandeja subprocesos. */
	private boolean ib_mostrarBandejaSubprocesos;

	/** Propiedad ib mostrar devolver. */
	private boolean ib_mostrarDevolver;

	/** Propiedad ib mostrar firmar. */
	private boolean ib_mostrarFirmar;

	/** Propiedad ib mostrarlapiz. */
	private boolean ib_mostrarlapiz;

	/** Propiedad ib no es obligatorio recursos. */
	private boolean ib_noEsObligatorioRecursos;

	/** Propiedad ib regresar bandeja ofi destino. */
	private boolean ib_regresarBandejaOfiDestino;

	/** Propiedad ib_vieneAprobacionApoyoNacional. */
	private boolean ib_vieneAprobacionApoyoNacional;

	/** Propiedad ib viene de antiguo sistema. */
	private boolean ib_vieneDeAntiguoSistema;

	/** Propiedad ib viene de aprobación. */
	private boolean ib_vieneDeAprobacion;

	/** Propiedad ib viene de aprobación asesoría jurídica. */
	private boolean ib_vieneDeAprobacionAsesoriaJuridica;

	/** Propiedad ib viene de aprobación dirección técnica de resgitro. */
	private boolean ib_vieneDeAprobacionDireccionTecnicaRegistroOficinaDestino;

	/** Propiedad ib viene de aprobacion ejecucion visitas. */
	private boolean ib_vieneDeAprobacionEjecucionVisitas;

	/** Propiedad ib viene de aprobación y firma despacho. */
	private boolean ib_vieneDeAprobacionFirmaDespacho;

	/** Propiedad ib viene de aprobación subdirección apoyo jurídico. */
	private boolean ib_vieneDeAprobacionSubDireccionApoyoJuridico;

	/** Propiedad ib viene de aprobación superintendente. */
	private boolean ib_vieneDeAprobacionSuperintendente;

	/** Propiedad ib viene de aprobacion traslados oficina destino. */
	private boolean ib_vieneDeAprobacionTrasladosOficinaDestino;

	/** Propiedad ib viene de aprobación visitas. */
	private boolean ib_vieneDeAprobacionVisitas;

	/** Propiedad ib viene de aprobación visitas. */
	private boolean ib_vieneDeAprobacionVisitasSuperintendente;

	/** Propiedad ib viene de autorizacion firma. */
	private boolean ib_vieneDeAutorizacionFirma;

	/** Propiedad ib viene de autorizacion responsable de devoluciones. */
	private boolean ib_vieneDeAutorizacionResponsableDevoluciones;

	/** Propiedad ib viene de bandeja ejecucion visitas. */
	private boolean ib_vieneDeBandejaEjecucionVisitas;

	/** Propiedad ib viene bandeja fijación avisos oficina destino. */
	private boolean ib_vieneDeBandejaFijacionAvisosOficinaDestino;

	/** Propiedad ib viene bandeja fijación avisos oficina origen. */
	private boolean ib_vieneDeBandejaFijacionAvisosOficinaOrigen;

	/** Propiedad ib viene de comp otro circulo. */
	private boolean ib_vieneDeCompOtroCirculo;

	/** Propiedad ib viene de coordinador juridico. */
	private boolean ib_vieneDeCoordinadorJuridico;

	/** Propiedad ib viene de correcciones. */
	private boolean ib_vieneDeCorrecciones;

	/** Propiedad ib viene de despacho superintendente. */
	private boolean ib_vieneDeDespachoSuperintendente;

	/** Propiedad ib viene direccion técnica registro. */
	private boolean ib_vieneDeDireccionTecnicaRegistro;

	/** Propiedad ib viene de correcciones. */
	private boolean ib_vieneDeEjecucionTraslado;

	/** Propiedad ib viene de ejecucion visitas. */
	private boolean ib_vieneDeEjecucionVisitas;

	/** Propiedad ib viene de responsable tesoreria. */
	private boolean ib_vieneDeResponsableTesoreria;

	/** Propiedad ib viene de revision recursos coordinador jurídico. */
	private boolean ib_vieneDeRevisionRecursosCoordinadorJuridico;

	/**  Propiedad id motivo. */
	private long il_idMotivo380;

	/** Propiedad il total bandeja. */
	private long il_totalBandeja;

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
	 * Modifica el valor de data aprobacion.
	 *
	 * @param ala_la asigna el valor a la propiedad data aprobacion
	 */
	public void setDataAprobacion(Collection<Aprobacion> ala_la)
	{
		ila_dataAprobacion = ala_la;
	}

	/**
	 * Retorna el valor de data aprobacion.
	 *
	 * @return el valor de data aprobacion
	 */
	public Collection<Aprobacion> getDataAprobacion()
	{
		return ila_dataAprobacion;
	}

	/**
	 * Modifica el valor de data bandeja.
	 *
	 * @param aca_ca asigna el valor a la propiedad data bandeja
	 */
	public void setDataBandeja(Collection<Aprobacion> aca_ca)
	{
		ica_dataBandeja = aca_ca;
	}

	/**
	 * Retorna el valor de data bandeja.
	 *
	 * @return el valor de data bandeja
	 */
	public Collection<Aprobacion> getDataBandeja()
	{
		return ica_dataBandeja;
	}

	/**
	 * Modifica el valor de detalle bandeja.
	 *
	 * @param aa_a asigna el valor a la propiedad detalle bandeja
	 */
	public void setDetalleBandeja(Aprobacion aa_a)
	{
		ioa_detalleBandeja = aa_a;
	}

	/**
	 * Retorna el valor de detalle bandeja.
	 *
	 * @return el valor de detalle bandeja
	 */
	public Aprobacion getDetalleBandeja()
	{
		if(ioa_detalleBandeja == null)
			ioa_detalleBandeja = new Aprobacion();

		return ioa_detalleBandeja;
	}

	/**
	 * Sets the documentos generados.
	 *
	 * @param alhm_lhm correspondiente al valor del tipo de objeto Map<String,byte[]>
	 */
	public void setDocumentosGenerados(Map<String, byte[]> alhm_lhm)
	{
		ilhm_documentosGenerados = alhm_lhm;
	}

	/**
	 * Retorna el valor de documentos generados.
	 *
	 * @return el valor de documentos generados
	 */
	public Map<String, byte[]> getDocumentosGenerados()
	{
		return ilhm_documentosGenerados;
	}

	/**
	 * Modifica el valor de EsDesistimiento.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsDesistimiento(boolean ab_b)
	{
		ib_esDesistimiento = ab_b;
	}

	/**
	 * Valida la propiedad es desistimiento.
	 *
	 * @return Retorna el valor de la propiedad esDesistimiento
	 */
	public boolean isEsDesistimiento()
	{
		return ib_esDesistimiento;
	}

	/**
	 * Modifica el valor de EsRecursos.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsRecursos(boolean ab_b)
	{
		ib_esRecursos = ab_b;
	}

	/**
	 * Valida la propiedad es recursos.
	 *
	 * @return Retorna el valor de la propiedad esRecursos
	 */
	public boolean isEsRecursos()
	{
		return ib_esRecursos;
	}

	/**
	 * Modifica el valor de filtro aprobacion.
	 *
	 * @param ala_la asigna el valor a la propiedad filtro aprobacion
	 */
	public void setFiltroAprobacion(Collection<Aprobacion> ala_la)
	{
		ila_filtroAprobacion = ala_la;
	}

	/**
	 * Retorna el valor de filtro aprobacion.
	 *
	 * @return el valor de filtro aprobacion
	 */
	public Collection<Aprobacion> getFiltroAprobacion()
	{
		return ila_filtroAprobacion;
	}

	/**
	 * Modifica el valor de filtro firma.
	 *
	 * @param as_s asigna el valor a la propiedad filtro firma
	 */
	public void setFiltroFirma(String as_s)
	{
		is_filtroFirma = as_s;
	}

	/**
	 * Retorna el valor de filtro firma.
	 *
	 * @return el valor de filtro firma
	 */
	public String getFiltroFirma()
	{
		return is_filtroFirma;
	}

	/**
	 * Modifica el valor de firmas masivo.
	 *
	 * @param acfa_cfa asigna el valor a la propiedad firmas masivo
	 */
	public void setFirmasMasivo(Collection<FirmaAprobacion> acfa_cfa)
	{
		icfa_firmasMasivo = acfa_cfa;
	}

	/**
	 * Retorna el valor de firmas masivo.
	 *
	 * @return el valor de firmas masivo
	 */
	public Collection<FirmaAprobacion> getFirmasMasivo()
	{
		return icfa_firmasMasivo;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(Long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public Long getIdEtapa()
	{
		if((il_idEtapa == null) || (NumericUtils.getLong(il_idEtapa) == 0))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idEtapa");

			if(StringUtils.isValidString(tmp))
				il_idEtapa = NumericUtils.getLongWrapper(tmp);
		}

		return il_idEtapa;
	}

	/**
	 * Modifica el valor de IdMotivo380.
	 *
	 * @param il_idMotivo380 de il id motivo 380
	 */
	public void setIdMotivo380(long il_idMotivo380)
	{
		this.il_idMotivo380 = il_idMotivo380;
	}

	/**
	 * Retorna Objeto o variable de valor id motivo 380.
	 *
	 * @return Retorna el valor de la propiedad il_idMotivo380
	 */
	public long getIdMotivo380()
	{
		return il_idMotivo380;
	}

	/**
	 * Modifica el valor de id proceso seleccionado.
	 *
	 * @param as_s asigna el valor a la propiedad id proceso seleccionado
	 */
	public void setIdProcesoSeleccionado(String as_s)
	{
		is_idProcesoSeleccionado = as_s;
	}

	/**
	 * Retorna el valor de id proceso seleccionado.
	 *
	 * @return el valor de id proceso seleccionado
	 */
	public String getIdProcesoSeleccionado()
	{
		return is_idProcesoSeleccionado;
	}

	/**
	 * Modifica el valor de IdTipoDecisionRecurso.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoDecisionRecurso(String as_s)
	{
		is_idTipoDecisionRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo decision recurso.
	 *
	 * @return Retorna el valor de la propiedad idTipoDecisionRecurso
	 */
	public String getIdTipoDecisionRecurso()
	{
		return is_idTipoDecisionRecurso;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de imagen.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen
	 */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/**
	 * Retorna el valor de imagen.
	 *
	 * @return el valor de imagen
	 */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * Modifica el valor de label boton.
	 *
	 * @param as_s asigna el valor a la propiedad label boton
	 */
	public void setLabelBoton(String as_s)
	{
		is_labelBoton = as_s;
	}

	/**
	 * Retorna el valor de label boton.
	 *
	 * @return el valor de label boton
	 */
	public String getLabelBoton()
	{
		if(!StringUtils.isValidString(is_labelBoton))
			is_labelBoton = getMessages().getString(MessagesKeys.LABEL_ENVIAR_ENTREGA);

		return is_labelBoton;
	}

	/**
	 * Modifica el valor de mostrar bandeja subprocesos.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar bandeja subprocesos
	 */
	public void setMostrarBandejaSubprocesos(boolean ab_b)
	{
		ib_mostrarBandejaSubprocesos = ab_b;
	}

	/**
	 * Valida la propiedad mostrar bandeja subprocesos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar bandeja subprocesos
	 */
	public boolean isMostrarBandejaSubprocesos()
	{
		return ib_mostrarBandejaSubprocesos;
	}

	/**
	 * Modifica el valor de mostrar devolver.
	 *
	 * @param ab_m asigna el valor a la propiedad mostrar devolver
	 */
	public void setMostrarDevolver(boolean ab_m)
	{
		ib_mostrarDevolver = ab_m;
	}

	/**
	 * Valida la propiedad mostrar devolver.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar devolver
	 */
	public boolean isMostrarDevolver()
	{
		return ib_mostrarDevolver;
	}

	/**
	 * Modifica el valor de mostrar firmar.
	 *
	 * @param ab_m asigna el valor a la propiedad mostrar firmar
	 */
	public void setMostrarFirmar(boolean ab_m)
	{
		ib_mostrarFirmar = ab_m;
	}

	/**
	 * Valida la propiedad mostrar firmar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar firmar
	 */
	public boolean isMostrarFirmar()
	{
		return ib_mostrarFirmar;
	}

	/**
	 * Modifica el valor de mostrarlapiz.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrarlapiz
	 */
	public void setMostrarlapiz(boolean ab_b)
	{
		ib_mostrarlapiz = ab_b;
	}

	/**
	 * Valida la propiedad mostrarlapiz.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrarlapiz
	 */
	public boolean isMostrarlapiz()
	{
		return ib_mostrarlapiz;
	}

	/**
	 * Modifica el valor de motivo tramite seleccionado.
	 *
	 * @param as_motivoTramiteSeleccionado asigna el valor a la propiedad motivo tramite seleccionado
	 */
	public void setMotivoTramiteSeleccionado(String as_motivoTramiteSeleccionado)
	{
		is_motivoTramiteSeleccionado = as_motivoTramiteSeleccionado;
	}

	/**
	 * Retorna el valor de motivo tramite seleccionado.
	 *
	 * @return el valor de motivo tramite seleccionado
	 */
	public String getMotivoTramiteSeleccionado()
	{
		return is_motivoTramiteSeleccionado;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NoEsObligatorioRecursos.
	 *
	 * @param ab_b de ab b
	 */
	public void setNoEsObligatorioRecursos(boolean ab_b)
	{
		ib_noEsObligatorioRecursos = ab_b;
	}

	/**
	 * Valida la propiedad no es obligatorio recursos.
	 *
	 * @return Retorna el valor de la propiedad noEsObligatorioRecursos
	 */
	public boolean isNoEsObligatorioRecursos()
	{
		return ib_noEsObligatorioRecursos;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setNombreTipoDecisionRecurso(String as_s)
	{
		is_nombreTipoDecisionRecurso = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getNombreTipoDecisionRecurso()
	{
		return is_nombreTipoDecisionRecurso;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de observaciones temporales.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones temporales
	 */
	public void setObservacionesTemporales(String as_s)
	{
		is_observacionesTemporales = as_s;
	}

	/**
	 * Retorna el valor de observaciones temporales.
	 *
	 * @return el valor de observaciones temporales
	 */
	public String getObservacionesTemporales()
	{
		return is_observacionesTemporales;
	}

	/**
	 * Modifica el valor de observaciones turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones turno historia
	 */
	public void setObservacionesTurnoHistoria(String as_s)
	{
		is_observacionesTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de observaciones turno historia.
	 *
	 * @return el valor de observaciones turno historia
	 */
	public String getObservacionesTurnoHistoria()
	{
		return is_observacionesTurnoHistoria;
	}

	/**
	 * Retorna el valor de pie chart model.
	 *
	 * @return el valor de pie chart model
	 */
	public PieChartModel getPieChartModel()
	{
		return ipcm_pieChartModel;
	}

	/**
	 * Modifica el valor de proceso certificados.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso certificados
	 */
	public void setProcesoCertificados(boolean ab_b)
	{
		ab_procesoCertificados = ab_b;
	}

	/**
	 * Valida la propiedad proceso certificados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso certificados
	 */
	public boolean isProcesoCertificados()
	{
		return ab_procesoCertificados;
	}

	/**
	 * Modifica el valor de RegresarBandejaOfiDestino.
	 *
	 * @param ab_r de ab r
	 */
	public void setRegresarBandejaOfiDestino(boolean ab_r)
	{
		ib_regresarBandejaOfiDestino = ab_r;
	}

	/**
	 * Valida la propiedad regresar bandeja ofi destino.
	 *
	 * @return Retorna el valor de la propiedad ib_regresarBandejaOfiDestino
	 */
	public boolean isRegresarBandejaOfiDestino()
	{
		return ib_regresarBandejaOfiDestino;
	}

	/**
	 * Modifica el valor de relaciones creadas string.
	 *
	 * @param as_s asigna el valor a la propiedad relaciones creadas string
	 */
	public void setRelacionesCreadasString(String as_s)
	{
		is_relacionesCreadasString = as_s;
	}

	/**
	 * Retorna el valor de relaciones creadas string.
	 *
	 * @return el valor de relaciones creadas string
	 */
	public String getRelacionesCreadasString()
	{
		return is_relacionesCreadasString;
	}

	/**
	 * Modifica el valor de subproceso seleccionado.
	 *
	 * @param as_s asigna el valor a la propiedad subproceso seleccionado
	 */
	public void setSubprocesoSeleccionado(String as_s)
	{
		is_subprocesoSeleccionado = as_s;
	}

	/**
	 * Retorna el valor de subproceso seleccionado.
	 *
	 * @return el valor de subproceso seleccionado
	 */
	public String getSubprocesoSeleccionado()
	{
		return is_subprocesoSeleccionado;
	}

	/**
	 * Modifica el valor de total bandeja.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja
	 */
	public void setTotalBandeja(long ai_i)
	{
		il_totalBandeja = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public long getTotalBandeja()
	{
		return il_totalBandeja;
	}

	/**
	 * Modifica el valor de tramites aprobacion.
	 *
	 * @param aca_ca asigna el valor a la propiedad tramites aprobacion
	 */
	public void setTramitesAprobacion(Collection<Aprobacion> aca_ca)
	{
		ica_tramitesAprobacion = aca_ca;
	}

	/**
	 * Retorna el valor de tramites aprobacion.
	 *
	 * @return el valor de tramites aprobacion
	 */
	public Collection<Aprobacion> getTramitesAprobacion()
	{
		return ica_tramitesAprobacion;
	}

	/**
	 * Modifica el valor de tramites detalle motivo.
	 *
	 * @param aca_ca asigna el valor a la propiedad tramites detalle motivo
	 */
	public void setTramitesDetalleMotivo(Collection<Aprobacion> aca_ca)
	{
		ica_tramitesDetalleMotivo = aca_ca;
	}

	/**
	 * Retorna el valor de tramites detalle motivo.
	 *
	 * @return el valor de tramites detalle motivo
	 */
	public Collection<Aprobacion> getTramitesDetalleMotivo()
	{
		return ica_tramitesDetalleMotivo;
	}

	/**
	 * Modifica el valor de VieneAprobacionApoyoNacional.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneAprobacionApoyoNacional(boolean ab_b)
	{
		ib_vieneAprobacionApoyoNacional = ab_b;
	}

	/**
	 * Valida la propiedad viene aprobacion apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad vieneAprobacionApoyoNacional
	 */
	public boolean isVieneAprobacionApoyoNacional()
	{
		return ib_vieneAprobacionApoyoNacional;
	}

	/**
	 * Modifica el valor de viene de antiguo sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de antiguo sistema
	 */
	public void setVieneDeAntiguoSistema(boolean ab_b)
	{
		ib_vieneDeAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad viene de antiguo sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de antiguo sistema
	 */
	public boolean isVieneDeAntiguoSistema()
	{
		return ib_vieneDeAntiguoSistema;
	}

	/**
	 * Modifica el valor de VieneDeAprobacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeAprobacion(boolean ab_b)
	{
		ib_vieneDeAprobacion = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion.
	 *
	 * @return Retorna el valor de la propiedad ib_vieneDeAprobacion
	 */
	public boolean isVieneDeAprobacion()
	{
		return ib_vieneDeAprobacion;
	}

	/**
	 * Modifica el valor de VieneDeAprobacionAsesoriaJuridica.
	 *
	 * @param ab_b Modifica el valor de la propiedad vieneDeAprobacionAsesoriaJuridica por vieneDeAprobacionAsesoriaJuridica
	 */
	public void setVieneDeAprobacionAsesoriaJuridica(boolean ab_b)
	{
		ib_vieneDeAprobacionAsesoriaJuridica = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion asesoria juridica.
	 *
	 * @return Retorna el valor de la propiedad vieneDeAprobacionAsesoriaJuridica
	 */
	public boolean isVieneDeAprobacionAsesoriaJuridica()
	{
		return ib_vieneDeAprobacionAsesoriaJuridica;
	}

	/**
	 * Modifica el valor de VieneDeAprobacionDireccionTecnicaRegistro.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeAprobacionDireccionTecnicaRegistro(boolean ab_b)
	{
		ib_vieneDeAprobacionDireccionTecnicaRegistroOficinaDestino = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion direccion tecnica registro.
	 *
	 * @return Retorna el valor de la propiedad ib_vieneDeAprobacionDireccionTecnicaRegistro
	 */
	public boolean isVieneDeAprobacionDireccionTecnicaRegistro()
	{
		return ib_vieneDeAprobacionDireccionTecnicaRegistroOficinaDestino;
	}

	/**
	 * Modifica el valor de VieneDeAprobacionEjecucionVisitas.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeAprobacionEjecucionVisitas(boolean ab_b)
	{
		ib_vieneDeAprobacionEjecucionVisitas = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion ejecucion visitas.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en viene de aprobacion ejecucion visitas
	 */
	public boolean isVieneDeAprobacionEjecucionVisitas()
	{
		return ib_vieneDeAprobacionEjecucionVisitas;
	}

	/**
	 * Modifica el valor de VieneDeAprobacionFirmaDespacho.
	 *
	 * @param ab_b Modifica el valor de la propiedad vieneDeAprobacionFirmaDespacho por vieneDeAprobacionFirmaDespacho
	 */
	public void setVieneDeAprobacionFirmaDespacho(boolean ab_b)
	{
		ib_vieneDeAprobacionFirmaDespacho = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion firma despacho.
	 *
	 * @return Retorna el valor de la propiedad vieneDeAprobacionFirmaDespacho
	 */
	public boolean isVieneDeAprobacionFirmaDespacho()
	{
		return ib_vieneDeAprobacionFirmaDespacho;
	}

	/**
	 * Modifica el valor de viene de aprobación subdirección apoyo jurídico.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de aprobación subdirección apoyo jurídico.
	 */
	public void setVieneDeAprobacionSubDireccionApoyoJuridico(boolean ab_b)
	{
		ib_vieneDeAprobacionSubDireccionApoyoJuridico = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobación subdirección apoyo jurídico.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de aprobación subdirección apoyo jurídico.
	 */
	public boolean isVieneDeAprobacionSubDireccionApoyoJuridico()
	{
		return ib_vieneDeAprobacionSubDireccionApoyoJuridico;
	}

	/**
	 * Modifica el valor de VieneDeAprobacionSuperintendente.
	 *
	 * @param ab_b Modifica el valor de la propiedad vieneDeAprobacionSuperintendente por vieneDeAprobacionSuperintendente
	 */
	public void setVieneDeAprobacionSuperintendente(boolean ab_b)
	{
		ib_vieneDeAprobacionSuperintendente = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion superintendente.
	 *
	 * @return Retorna el valor de la propiedad vieneDeAprobacionSuperintendente
	 */
	public boolean isVieneDeAprobacionSuperintendente()
	{
		return ib_vieneDeAprobacionSuperintendente;
	}

	/**
	 * Modifica el valor de VieneDeAprobacionTrasladosOficinaDestino.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeAprobacionTrasladosOficinaDestino(boolean ab_b)
	{
		ib_vieneDeAprobacionTrasladosOficinaDestino = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion traslados oficina destino.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en viene de aprobacion traslados oficina destino
	 */
	public boolean isVieneDeAprobacionTrasladosOficinaDestino()
	{
		return ib_vieneDeAprobacionTrasladosOficinaDestino;
	}

	/**
	 * Modifica el valor de VieneDeAprobacionVisitas.
	 *
	 * @param ab_b Modifica el valor de la propiedad vieneDeAprobacionVisitas por vieneDeAprobacionVisitas
	 */
	public void setVieneDeAprobacionVisitas(boolean ab_b)
	{
		ib_vieneDeAprobacionVisitas = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion visitas.
	 *
	 * @return Retorna el valor de la propiedad vieneDeAprobacionVisitas
	 */
	public boolean isVieneDeAprobacionVisitas()
	{
		return ib_vieneDeAprobacionVisitas;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_vieneDeAprobacionVisitasSuperintendente por ib_vieneDeAprobacionVisitasSuperintendente
	 */
	public void setVieneDeAprobacionVisitasSuperintendente(boolean ab_b)
	{
		ib_vieneDeAprobacionVisitasSuperintendente = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_vieneDeAprobacionVisitasSuperintendente
	 */
	public boolean isVieneDeAprobacionVisitasSuperintendente()
	{
		return ib_vieneDeAprobacionVisitasSuperintendente;
	}

	/**
	 * Modifica el valor de VieneDeAutorizacionFirma.
	 *
	 * @param ab_b Modifica el valor de la propiedad vieneDeAutorizacionFirma por vieneDeAutorizacionFirma
	 */
	public void setVieneDeAutorizacionFirma(boolean ab_b)
	{
		ib_vieneDeAutorizacionFirma = ab_b;
	}

	/**
	 * Valida la propiedad viene de autorizacion firma.
	 *
	 * @return Retorna el valor de la propiedad vieneDeAutorizacionFirma
	 */
	public boolean isVieneDeAutorizacionFirma()
	{
		return ib_vieneDeAutorizacionFirma;
	}

	/**
	 * Modifica el valor de VieneDeAutorizacionResponsableDevoluciones.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeAutorizacionResponsableDevoluciones(boolean ab_b)
	{
		ib_vieneDeAutorizacionResponsableDevoluciones = ab_b;
	}

	/**
	 * Valida la propiedad viene de autorizacion responsable devoluciones.
	 *
	 * @return Retorna el valor de la propiedad ib_vieneDeAutorizacionResponsableDevoluciones
	 */
	public boolean isVieneDeAutorizacionResponsableDevoluciones()
	{
		return ib_vieneDeAutorizacionResponsableDevoluciones;
	}

	/**
	 * Modifica el valor de VieneDeBandejaEjecucionVisitas.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeBandejaEjecucionVisitas(boolean ab_b)
	{
		ib_vieneDeBandejaEjecucionVisitas = ab_b;
	}

	/**
	 * Valida la propiedad viene de bandeja ejecucion visitas.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en viene de bandeja ejecucion visitas
	 */
	public boolean isVieneDeBandejaEjecucionVisitas()
	{
		return ib_vieneDeBandejaEjecucionVisitas;
	}

	/**
	 * Modifica el valor de VieneDeBandejaFijacionAvisosOficinaDestino.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeBandejaFijacionAvisosOficinaDestino(boolean ab_b)
	{
		ib_vieneDeBandejaFijacionAvisosOficinaDestino = ab_b;
	}

	/**
	 * Valida la propiedad viene de bandeja fijacion avisos oficina destino.
	 *
	 * @return Retorna el valor de la propiedad vieneDeBandejaFijacionAvisosOficinaDestino
	 */
	public boolean isVieneDeBandejaFijacionAvisosOficinaDestino()
	{
		return ib_vieneDeBandejaFijacionAvisosOficinaDestino;
	}

	/**
	 * Modifica el valor de VieneDeBandejaFijacionAvisosOficinaOrigen.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeBandejaFijacionAvisosOficinaOrigen(boolean ab_b)
	{
		ib_vieneDeBandejaFijacionAvisosOficinaOrigen = ab_b;
	}

	/**
	 * Valida la propiedad viene de bandeja fijacion avisos oficina origen.
	 *
	 * @return Retorna el valor de la propiedad vieneDeBandejaFijacionAvisosOficinaOrigen
	 */
	public boolean isVieneDeBandejaFijacionAvisosOficinaOrigen()
	{
		return ib_vieneDeBandejaFijacionAvisosOficinaOrigen;
	}

	/**
	 * Modifica el valor de VieneDeCompOtroCirculo.
	 *
	 * @param ab_vieneDeCompOtroCirculo de ab viene de comp otro circulo
	 */
	public void setVieneDeCompOtroCirculo(boolean ab_vieneDeCompOtroCirculo)
	{
		ib_vieneDeCompOtroCirculo = ab_vieneDeCompOtroCirculo;
	}

	/**
	 * Valida la propiedad viene de comp otro circulo.
	 *
	 * @return Retorna el valor de la propiedad ib_vieneDeCompOtroCirculo
	 */
	public boolean isVieneDeCompOtroCirculo()
	{
		return ib_vieneDeCompOtroCirculo;
	}

	/**
	 * Modifica el valor de VieneDeCoordinadorJuridico.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeCoordinadorJuridico(boolean ab_b)
	{
		ib_vieneDeCoordinadorJuridico = ab_b;
	}

	/**
	 * Valida la propiedad viene de coordinador juridico.
	 *
	 * @return Retorna el valor de la propiedad vieneDeCoordinadorJuridico
	 */
	public boolean isVieneDeCoordinadorJuridico()
	{
		return ib_vieneDeCoordinadorJuridico;
	}

	/**
	 * Modifica el valor de viene de correcciones.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de correcciones
	 */
	public void setVieneDeCorrecciones(boolean ab_b)
	{
		ib_vieneDeCorrecciones = ab_b;
	}

	/**
	 * Valida la propiedad viene de correcciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de correcciones
	 */
	public boolean isVieneDeCorrecciones()
	{
		return ib_vieneDeCorrecciones;
	}

	/**
	 * Modifica el valor de VieneDeDespachoSuperintendente.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeDespachoSuperintendente(boolean ab_b)
	{
		ib_vieneDeDespachoSuperintendente = ab_b;
	}

	/**
	 * Valida la propiedad viene de despacho superintendente.
	 *
	 * @return Retorna el valor de la propiedad vieneDeDespachoSuperintendente
	 */
	public boolean isVieneDeDespachoSuperintendente()
	{
		return ib_vieneDeDespachoSuperintendente;
	}

	/**
	 * Modifica el valor de VieneDeDireccionTecnicaRegistro.
	 *
	 * @param ab_vieneDeDireccionTecnicaRegistro de ab viene de direccion tecnica registro
	 */
	public void setVieneDeDireccionTecnicaRegistro(boolean ab_vieneDeDireccionTecnicaRegistro)
	{
		ib_vieneDeDireccionTecnicaRegistro = ab_vieneDeDireccionTecnicaRegistro;
	}

	/**
	 * Valida la propiedad viene de direccion tecnica registro.
	 *
	 * @return Retorna el valor de la propiedad ib_vieneDeDireccionTecnicaRegistro
	 */
	public boolean isVieneDeDireccionTecnicaRegistro()
	{
		return ib_vieneDeDireccionTecnicaRegistro;
	}

	/**
	 * Modifica el valor de VieneDeEjecucionTraslado.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeEjecucionTraslado(boolean ab_b)
	{
		ib_vieneDeEjecucionTraslado = ab_b;
	}

	/**
	 * Valida la propiedad viene de ejecucion traslado.
	 *
	 * @return Retorna el valor de la propiedad vieneDeEjecucionTraslado
	 */
	public boolean isVieneDeEjecucionTraslado()
	{
		return ib_vieneDeEjecucionTraslado;
	}

	/**
	 * Modifica el valor de VieneDeEjecucionVisitas.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeEjecucionVisitas(boolean ab_b)
	{
		ib_vieneDeEjecucionVisitas = ab_b;
	}

	/**
	 * Valida la propiedad viene de ejecucion visitas.
	 *
	 * @return Retorna el valor de la propiedad vieneDeEjecucionVisitas
	 */
	public boolean isVieneDeEjecucionVisitas()
	{
		return ib_vieneDeEjecucionVisitas;
	}

	/**
	 * Modifica el valor de viene de responsable tesoreria.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeResponsableTesoreria(boolean ab_b)
	{
		ib_vieneDeResponsableTesoreria = ab_b;
	}

	/**
	 * Valida la propiedad viene de responsable tesoreria.
	 *
	 * @return Retorna el valor de la propiedad viene de responsable tesoreria
	 */
	public boolean isVieneDeResponsableTesoreria()
	{
		return ib_vieneDeResponsableTesoreria;
	}

	/**
	 * Modifica el valor de viene de revisión recursos coordinador jurídico.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de revisión recursos coordinador jurídico.
	 */
	public void setVieneDeRevisionRecursosCoordinadorJuridico(boolean ab_b)
	{
		ib_vieneDeRevisionRecursosCoordinadorJuridico = ab_b;
	}

	/**
	 * Valida la propiedad viene de revisión recursos coordinador júridico.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de revisión recursos coordinador júridico.
	 */
	public boolean isVieneDeRevisionRecursosCoordinadorJuridico()
	{
		return ib_vieneDeRevisionRecursosCoordinadorJuridico;
	}

	/**
	 * Modifica el valor de zip documentos relacion.
	 *
	 * @param asc_sc asigna el valor a la propiedad zip documentos relacion
	 */
	public void setZipDocumentosRelacion(StreamedContent asc_sc)
	{
		isc_zipDocumentosRelacion = asc_sc;
	}

	/**
	 * Retorna el valor de zip documentos relacion.
	 *
	 * @return el valor de zip documentos relacion
	 */
	public StreamedContent getZipDocumentosRelacion()
	{
		return isc_zipDocumentosRelacion;
	}

	/**
	 * Modifica el valor de zip file.
	 *
	 * @param ab_b asigna el valor a la propiedad zip file
	 */
	public void setZipFile(byte[] ab_b)
	{
		ib_zipFile = ab_b;
	}

	/**
	 * Retorna el valor de zip file.
	 *
	 * @return el valor de zip file
	 */
	public byte[] getZipFile()
	{
		return ib_zipFile;
	}

	/**
	 * Carga la información del tramite y prepara la pantalla de detalle de turno para su visualización.
	 *
	 * @param aa_aprobacion objeto contenedor de la información del tramite
	 * @return enlace a la pantalla de detalle del turno
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public String abrirDetalleTurno(Aprobacion aa_aprobacion)
	    throws B2BException, Exception
	{
		String                  ls_return;
		BeanPredioDocumentoActo lbpda_bean;
		FacesContext            lfc_context;

		ls_return       = NavegacionCommon.DETALLE_ACTO;
		lfc_context     = FacesUtils.getFacesContext();
		lbpda_bean      = (BeanPredioDocumentoActo)lfc_context.getApplication()
				                                                  .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
				);

		if(aa_aprobacion != null)
		{
			String ls_idTurno;
			Long   ll_idTurnoHistoria;
			String ls_idTurnoHistoria;

			ls_idTurno             = aa_aprobacion.getIdTurno();
			ll_idTurnoHistoria     = aa_aprobacion.getIdTurnoHistoria();
			ls_idTurnoHistoria     = StringUtils.getString(ll_idTurnoHistoria);

			if(isVieneDeEjecucionVisitas())
			{
				ls_return = NavegacionCommon.DETALLE_EJECUCION_VISITAS;

				{
					BeanDetalleEjecucionVisitas lbdev_bean;

					lbdev_bean = (BeanDetalleEjecucionVisitas)obtenerInstanciaBean(
						    BeanDetalleEjecucionVisitas.class, BeanNameCommon.BEAN_DETALLE_EJECUCION_VISITAS
						);

					if(lbdev_bean != null)
					{
						lbdev_bean.clean();
						lbdev_bean.setIdTurno(ls_idTurno);
						lbdev_bean.setIdTurnoHistoria(ls_idTurnoHistoria);

						{
							TurnoHistoria lth_historia;

							lth_historia = aa_aprobacion.getTurnoHistoria();

							if(lth_historia != null)
								lbdev_bean.setIdSolicitud(lth_historia.getIdSolicitud());
						}

						lbdev_bean.cargarDatosEjecucionVisitas();
					}
				}
			}
			else if((lbpda_bean != null))
			{
				boolean      lb_vinculado;
				boolean      lb_autorizacionFirma;
				long         ll_idEtapaActual;
				String       ls_decisionCalificacion;
				Trazabilidad lt_trazaTemp;

				lb_autorizacionFirma        = false;
				ll_idEtapaActual            = NumericUtils.DEFAULT_LONG_VALUE;
				lb_vinculado                = irr_aprobacionRemote.adjudicacionRemate(
					    ll_idTurnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				ls_decisionCalificacion     = null;
				lt_trazaTemp                = new Trazabilidad();

				lbpda_bean.clear();
				lbpda_bean.limpiarDatos();
				lbpda_bean.setAprobacion(aa_aprobacion);
				lbpda_bean.setIdTurno(ls_idTurno);

				if(aa_aprobacion.getMotivoNoTramite().equalsIgnoreCase(IdentificadoresCommon.APROBAR_SECUENCIAS))
					lbpda_bean.setEsAprobadorSecuencia(true);

				lbpda_bean.setIdTurnoHistoria(ls_idTurnoHistoria);

				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = irr_referenceRemote.findTurnoHistoriaByid(ll_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					TurnoHistoria lth_turnoHistoriaAnt;
					String        ls_idProceso;

					ls_idProceso = StringUtils.getStringNotNull(lth_turnoHistoria.getIdProceso());

					lbpda_bean.setIdProceso(ls_idProceso);

					if(StringUtils.getStringNotNull(ls_idProceso).equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4))
					{
						lbpda_bean.consultarPersona();
						lbpda_bean.consultarTitularCuentaDevolucion();
					}

					lth_turnoHistoriaAnt     = irr_referenceRemote.findTurnoHistoriaByid(
						    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
						);
					ll_idEtapaActual         = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());

					if(ll_idEtapaActual > NumericUtils.DEFAULT_LONG_VALUE)
					{
						if(ll_idEtapaActual == EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS)
						{
							aa_aprobacion.setVieneDeEjecutorTraslados(true);
							lbpda_bean.setOcultarPaneles(true);
						}
						else if(
						    isVieneDeAprobacionVisitas() || isVieneDeAprobacionSuperintendente()
							    || isVieneDeAprobacionEjecucionVisitas() || isVieneDeAprobacionVisitasSuperintendente()
						)
						{
							lbpda_bean.setOcultarPaneles(true);
							lbpda_bean.setOcultarPanelesTraslados(true);
							lbpda_bean.setVieneDeVisitas(true);
						}
					}

					if(lth_turnoHistoriaAnt != null)
					{
						long ll_idEtapaAnt;
						long ll_idMotivoAnt;

						ll_idEtapaAnt            = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdEtapa());
						ll_idMotivoAnt           = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdMotivo());
						lb_autorizacionFirma     = (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S)
								|| ((ll_idEtapaAnt == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
								&& ((ll_idMotivoAnt == MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA)
								|| (ll_idMotivoAnt == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA)
								|| (ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA)))
								|| ((ll_idEtapaAnt == EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA)
								&& ((ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA)
								|| (ll_idMotivoAnt == MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA)
								|| (ll_idMotivoAnt == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA_FIRMA)));
					}

					lbpda_bean.setIdEtapa(NumericUtils.getLongWrapper(ll_idEtapaActual));
				}

				lbpda_bean.setIndVinculado(lb_vinculado);
				lbpda_bean.generarDatosDocumento();
				lbpda_bean.generarDatosTramitesVinculados();
				lbpda_bean.validacionEtapaAnteriorAprobacion();

				if(isVieneDeAntiguoSistema())
				{
					String ls_observacionesProcAnt;

					ls_observacionesProcAnt = observacionesProcesoAnterior(ls_idTurnoHistoria);

					aa_aprobacion.setVieneDeAntiguoSistema(true);

					if(lb_autorizacionFirma)
						setVieneDeAutorizacionFirma(true);

					aa_aprobacion.setVieneDeAutorizacionFirma(lb_autorizacionFirma);

					if(
					    StringUtils.isValidString(ls_observacionesProcAnt)
						    && ls_observacionesProcAnt.contains(
						        "estado inconsistente. Debe tramitarse la solución de la inconsistencia a través de la herramienta de calidad"
						    )
					)
						lbpda_bean.setMensajePredioInconsistente(ls_observacionesProcAnt);
				}
				else if(ll_idEtapaActual != EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS)
					aa_aprobacion.setVieneDeAprobacion(true);

				if(isProcesoCertificados())
					aa_aprobacion.setProcesoCertificados(true);
				else if(isVieneDeCorrecciones())
					aa_aprobacion.setProcesoCorrecciones(true);
				else if(isVieneDeCoordinadorJuridico())
				{
					aa_aprobacion.setProcesoCoordinadorJuridico(true);
					lbpda_bean.validarProrrogaEntregaDePruebas();
				}
				else if(isVieneAprobacionApoyoNacional())
				{
					if(lth_turnoHistoria != null)
					{
						Solicitud ls_solicitud;

						ls_solicitud = irr_referenceRemote.findSolicitudById(lth_turnoHistoria.getIdSolicitud());

						if(ls_solicitud != null)
							lbpda_bean.setIdCirculoOripSolicitante(ls_solicitud.getIdCirculoAnt());
					}

					aa_aprobacion.setVieneAprobacionApoyoNacional(true);
				}

				lbpda_bean.setObservacionesAprobador(aa_aprobacion.getObservacionesAprobador());
				lbpda_bean.setDecisionAprobacion(
				    aa_aprobacion.isGenerarRelacion() ? IdentificadoresCommon.FIRMAR_CARACTER : null
				);
				lbpda_bean.getMatriculasRangos();
				lbpda_bean.getMatriculasIndividuales();
				lbpda_bean.getMatriculasTmpRangos();
				lbpda_bean.getMatriculasTmpIndividuales();
				lbpda_bean.cargarJustificacionCierreFolio();
				lbpda_bean.validarFechaVencimientoActo();

				lth_turnoHistoria.setFechaEtapaValida(true);

				if(lth_turnoHistoria != null)
				{
					lbpda_bean.setObservacionesAprobador(lth_turnoHistoria.getObservaciones());

					String ls_nombreUsuarioCreacion;

					ls_nombreUsuarioCreacion = StringUtils.getStringNotNull(lth_turnoHistoria.getIdUsuarioCreacion());

					if(ls_nombreUsuarioCreacion.equals(ConstanteCommon.CORE_BACHUE))
					{
						BeanRegistroCalificacion lbrc_regCal;

						lbrc_regCal = (BeanRegistroCalificacion)lfc_context.getApplication()
								                                               .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION,
								    BeanRegistroCalificacion.class
								);

						if(lbrc_regCal != null)
						{
							boolean lb_insertarDoc;
							boolean lb_cancelarAnotacionAutomatico;
							String  ls_idProceso;
							String  ls_idSubproceso;

							ls_idProceso                       = lth_turnoHistoria.getIdProceso();
							ls_idSubproceso                    = lth_turnoHistoria.getIdSubproceso();
							lb_cancelarAnotacionAutomatico     = StringUtils.isValidString(ls_idProceso)
									&& StringUtils.isValidString(ls_idSubproceso)
									&& ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
									&& ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_8);

							lb_insertarDoc = lb_cancelarAnotacionAutomatico;

							if(lb_cancelarAnotacionAutomatico)
							{
								Collection<DocumentosSalida> lcds_documentos;

								lcds_documentos = irr_aprobacionRemote.obtenerDocumentosPorTurno(
									    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

								if(CollectionUtils.isValidCollection(lcds_documentos))
								{
									Iterator<DocumentosSalida> lids_iterador;

									lids_iterador = lcds_documentos.iterator();

									while(lids_iterador.hasNext() && lb_insertarDoc)
									{
										DocumentosSalida lds_temp;

										lds_temp = lids_iterador.next();

										if(lds_temp != null)
										{
											String ls_tipo;

											ls_tipo = StringUtils.getStringNotNull(lds_temp.getTipo());

											if(ls_tipo.equalsIgnoreCase(ConstanteCommon.CONSTANCIA_INSCRIPCION))
												lb_insertarDoc = false;
										}
									}
								}
							}

							lbrc_regCal.setVieneDeAprobacion(true);
							lbrc_regCal.setIdTurno(ls_idTurno);
							lbrc_regCal.setTurno(ls_idTurno);
							lbrc_regCal.setIdTurnoHistoria(StringUtils.getString(lth_turnoHistoria.getIdAnterior()));
							lbrc_regCal.findMatriculas(false, false);

							if(lb_insertarDoc)
								lbrc_regCal.generateFile(true);

							lbrc_regCal.clean();
						}
					}

					//Bandejas de Fijacion Aviso
					if(isVieneDeBandejaFijacionAvisosOficinaOrigen() || isVieneDeBandejaFijacionAvisosOficinaDestino())
					{
						BeanPublicacionAvisosWeb lbpaw_publiWeb;

						lbpaw_publiWeb = (BeanPublicacionAvisosWeb)lfc_context.getApplication()
								                                                  .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_PUBLICACION_AVISOS_WEB,
								    BeanPublicacionAvisosWeb.class
								);

						if(lbpaw_publiWeb != null)
						{
							Collection<DocumentosSalida> lcds_documentos;
							boolean                      lb_insertarDoc;

							lcds_documentos     = irr_aprobacionRemote.obtenerDocumentosPorTurno(
								    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);
							lb_insertarDoc      = true;

							if(CollectionUtils.isValidCollection(lcds_documentos))
							{
								Iterator<DocumentosSalida> lids_iterador;

								lids_iterador = lcds_documentos.iterator();

								while(lids_iterador.hasNext() && lb_insertarDoc)
								{
									DocumentosSalida lds_temp;

									lds_temp = lids_iterador.next();

									if(lds_temp != null)
									{
										String ls_tipo;

										ls_tipo = StringUtils.getStringNotNull(lds_temp.getTipo());

										if(ls_tipo.equalsIgnoreCase(ConstanteCommon.CONSTANCIA_INSCRIPCION))
											lb_insertarDoc = false;
									}
								}
							}

							if(isVieneDeBandejaFijacionAvisosOficinaOrigen())
								lbpaw_publiWeb.setIdEtapa(EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN);
							else
								lbpaw_publiWeb.setIdEtapa(EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS);

							lbpaw_publiWeb.cargarCasos();

							ls_return = NavegacionCommon.FIJACION_AVISOS;
						}
					}

					TurnoHistoria lth_turnoHistoriaAnterior;

					lth_turnoHistoriaAnterior = new TurnoHistoria();

					lth_turnoHistoriaAnterior.setIdTurnoHistoria(
					    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
					);

					lth_turnoHistoriaAnterior = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoriaAnterior);

					if(lth_turnoHistoriaAnterior != null)
					{
						long                          ll_idEtapa;
						long                          ll_idMotivo;
						BeanVisualizacionCorrecciones lbvc_bean;

						ll_idEtapa      = NumericUtils.getLong(lth_turnoHistoriaAnterior.getIdEtapa());
						ll_idMotivo     = NumericUtils.getLong(lth_turnoHistoriaAnterior.getIdMotivo());
						lbvc_bean       = (BeanVisualizacionCorrecciones)lfc_context.getApplication()
								                                                        .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_VISUALIZACION_CORRECCIONES,
								    BeanVisualizacionCorrecciones.class
								);

						if(lbvc_bean != null)
							lbvc_bean.clean();

						if(
						    (ll_idEtapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
							    || (ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS)
						)
						{
							lbpda_bean.setEsGrabacionMatriculas(true);

							if(ll_idMotivo == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
							{
								lbpda_bean.setMensajeMotivoTramite("APROBAR NEGACIÓN DE LA SOLICITUD");
								lbpda_bean.setNegarSolicitudGrabacion(true);
							}
							else if(ll_idMotivo == MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
								lbpda_bean.setMensajeMotivoTramite("APROBAR RESOLUCION DE GRABACION");
							else if(ll_idMotivo == MotivoTramiteCommon.EJECUCION_GRABACION_MATRICULA)
								lbpda_bean.setMensajeMotivoTramite("APROBAR GRABACIÓN DE LA MATRÍCULA");
						}
						else if(
						    (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES)
							    && (ll_idMotivo == MotivoTramiteCommon.APROBAR_SECUENCIAS_132)
						)
							lbpda_bean.setEsAprobadorSecuencia(true);
						else if(
						    (isVieneDeCorrecciones()
							    && (ll_idMotivo == MotivoTramiteCommon.ENTREGA_ORIP_SOLICITUD_DOCUMENTACION))
							    && (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES)
						)
						{
							if(lbvc_bean != null)
							{
								lbvc_bean.setRenderizar(true);
								lbvc_bean.setIdTurno(ls_idTurno);
								lbvc_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
							}
						}
					}
					else
						lbpda_bean.setEsGrabacionMatriculas(false);
				}

				if(StringUtils.isValidString(lbpda_bean.getIdTurno()))
				{
					Turno lt_turnoTemp;

					lt_turnoTemp = new Turno();

					lt_turnoTemp.setIdTurno(lbpda_bean.getIdTurno());

					lt_trazaTemp.setTurno(lt_turnoTemp);

					ls_decisionCalificacion = irr_referenceRemote.findDecisionCalificacion(lt_trazaTemp);

					lbpda_bean.setDecisionCalificacion(ls_decisionCalificacion);
				}

				if(lb_vinculado)
				{
					if(irr_aprobacionRemote.esRealizarRegistro(ll_idTurnoHistoria))
					{
						Object[] aoa_messageArgs;

						aoa_messageArgs = irr_aprobacionRemote.mensajeAdjudicacionRemate(
							    ll_idTurnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(CollectionUtils.isValidCollection(aoa_messageArgs))
						{
							ExternalContext lec_externalContext;

							lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

							addMessage(MessagesKeys.MATRICULAS_VINCULACION, aoa_messageArgs);

							if(lec_externalContext != null)
							{
								Flash lf_flash;

								lf_flash = lec_externalContext.getFlash();

								if(lf_flash != null)
									lf_flash.setKeepMessages(true);
							}
						}
					}
				}

				if(lbpda_bean.isVieneDeVisitas())
					lbpda_bean.buscarSolicitudConPersona();
			}
		}

		return ls_return;
	}

	/**
	 * Accion segundo factor verificado.
	 *
	 * @return el valor de string
	 */
	public String accionSegundoFactorVerificado()
	{
		String ls_return;

		ls_return = null;

		try
		{
			Collection<Aprobacion> lca_data;
			boolean                lb_procesoCertificados;
			Ajax                   la_ajax;

			lca_data                   = getDataAprobacion();
			lb_procesoCertificados     = isProcesoCertificados();
			la_ajax                    = PrimeFaces.current().ajax();

			if(
			    isVieneDeAntiguoSistema() && CollectionUtils.isValidCollection(lca_data)
				    && (isVieneDeAutorizacionFirma() || !lb_procesoCertificados)
			)
				ls_return = aprobarAntiguoSistema();
			else if(
			    (lb_procesoCertificados || isVieneDeCorrecciones() || isVieneDeCoordinadorJuridico()
				    || isEsDesistimiento()) && CollectionUtils.isValidCollection(lca_data)
			)
			{
				ls_return = aprobarCertificados();

				{
					la_ajax.update(
					    ListadoCodigosConstantes.generarCodigosCollection(
					        "fAprobacion:idDtTurnos,fAprobacion:idCBBotonSalvarCertificado"
					    )
					);
				}
			}
			else
			{
				generarDocumentoRelacionAprobacion(true);
				la_ajax.update(
				    ListadoCodigosConstantes.generarCodigosCollection(
				        "fAprobacion:idDtTurnos,fAprobacion:idCBBotonSalvarCertificado,fDialogConfirmacionRelaciones"
				    )
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("accionSegundoFactorVerificado", lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Método para poder agregar el comentario del aprobador en la aprobación
	 * especifica.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarcomentario()
	    throws B2BException
	{
		try
		{
			Long ll_identificadorTurnoHistoria;
			ll_identificadorTurnoHistoria = getIdTurnoHistoria();

			if(NumericUtils.isValidLong(ll_identificadorTurnoHistoria))
			{
				String ls_observaciones;

				ls_observaciones = getObservacionesTurnoHistoria();

				if(StringUtils.isValidString(ls_observaciones))
				{
					if(ls_observaciones.length() < 20)
						throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_20);

					Collection<Aprobacion> lca_dataTmp;
					lca_dataTmp = getDataAprobacion();

					if(CollectionUtils.isValidCollection(lca_dataTmp))
					{
						for(Aprobacion la_interator : lca_dataTmp)
						{
							Long ll_idTurnoHistoriaAprobacion;
							ll_idTurnoHistoriaAprobacion = la_interator.getIdTurnoHistoria();

							if(ll_idTurnoHistoriaAprobacion.compareTo(ll_identificadorTurnoHistoria) == 0)
							{
								la_interator.setObservacionesAprobador(ls_observaciones);
								la_interator.setObservaciones(ls_observaciones);
							}
						}

						PrimeFaces.current().executeScript("PF('dlg4').hide();");
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_idMessageGrowl2);
		}
	}

	/**
	 * Firma casos de antiguo sistema que hayan sido seleccionados.
	 *
	 * @return devuelve el valor de String
	 */
	public String aprobarAntiguoSistema()
	{
		String ls_return;

		ls_return = null;

		try
		{
			Collection<Aprobacion> lca_aprobaciones;
			Collection<Aprobacion> lca_paraFirmar;

			lca_aprobaciones     = getDataAprobacion();
			lca_paraFirmar       = new LinkedList<Aprobacion>();

			if(!CollectionUtils.isValidCollection(lca_aprobaciones))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			for(Aprobacion la_data : lca_aprobaciones)
			{
				if(la_data != null)
				{
					if(la_data.isGenerarRelacion())
					{
						la_data.setFirmar(IdentificadoresCommon.FIRMAR);

						lca_paraFirmar.add(la_data);
					}
				}
			}

			iaas_aprobadorAntiguoSistemaRemote.salvarAprobacion(
			    lca_paraFirmar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			ls_return = regresar();

			if(!CollectionUtils.isValidCollection(getTramitesDetalleMotivo()))
				setMotivoTramiteSeleccionado(null);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_idMessageGrowl2);

		return ls_return;
	}

	/**
	 * Guarda en la base de datos los casos de certificados que han sido aprobados.
	 *
	 * @return Enlace a la pagina inicial de la bandeja
	 */
	public String aprobarCertificados()
	{
		String ls_return;

		ls_return = null;

		try
		{
			Collection<Aprobacion> lca_aprobacion;

			lca_aprobacion = getDataAprobacion();

			if(!CollectionUtils.isValidCollection(lca_aprobacion))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			{
				boolean              lb_errorSinSeleccion;
				Iterator<Aprobacion> lia_iterador;

				lb_errorSinSeleccion     = true;
				lia_iterador             = lca_aprobacion.iterator();

				while(lia_iterador.hasNext() && lb_errorSinSeleccion)
				{
					Aprobacion la_data;

					la_data = lia_iterador.next();

					if((la_data != null) && la_data.isGenerarRelacion())
						lb_errorSinSeleccion = false;
				}

				if(lb_errorSinSeleccion)
					throw new B2BException(ErrorKeys.ERROR_SIN_APROBACIONES_SELECCIONADAS_CERTIFICADOS);
			}

			irr_aprobacionRemote.salvarAprobacion(
			    lca_aprobacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			if(!CollectionUtils.isValidCollection(getTramitesDetalleMotivo()))
				setMotivoTramiteSeleccionado(null);

			clean();
			
			setVieneDeAprobacion(true);
			findDetalleAprobacion(false);
			setMotivoTramiteSeleccionado(null);
			setIdProcesoSeleccionado(null);

			ls_return = regresar();
			limpiarBanderaProcesos();
			
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("aprobarCertificados", lb2be_e);

			PrimeFaces.current().ajax().update(is_idMessageGrowl2);
		}

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla.
	 */
	public void clean()
	{
		setNir(null);
		setIdTurno(null);
		setDocumentosGenerados(null);
		setZipDocumentosRelacion(null);
		setRelacionesCreadasString(null);
		setTramitesAprobacion(null);
		setVieneDeEjecucionVisitas(false);
	}

	/**
	 * Maneja la descarga del archivo ZIP con los documentos de relacion.
	 */
	public void descargarArchivoZip()
	{
		try
		{
			byte[]      lba_zipFinal;
			InputStream lis_stream;

			lba_zipFinal = irr_aprobacionRemote.generarArchivoZipAprobacionRelacion(getDocumentosGenerados());

			if(lba_zipFinal != null)
			{
				lis_stream = new ByteArrayInputStream(lba_zipFinal);

				setZipDocumentosRelacion(
				    new DefaultStreamedContent(lis_stream, TipoContenidoCommon.ZIP, "APROBACION_RELACION.zip")
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para retornar success.
	 *
	 * @return devuelve el valor de String
	 */
	public String detalleActo()
	{
		return "success";
	}

	/**
	 * Método para poder descargar el zip.
	 *
	 * @param aa_aprobacion correspondiente al valor del tipo de objeto Aprobacion
	 * @return devuelve el valor de StreamedContent
	 */
	public StreamedContent donwloadZip(Aprobacion aa_aprobacion)
	{
		StreamedContent lsc_zipFinal;
		lsc_zipFinal = null;

		try
		{
			if(aa_aprobacion != null)
			{
				Aprobacion la_dataFile;
				la_dataFile = irr_aprobacionRemote.generateZip(aa_aprobacion);

				if(la_dataFile != null)
				{
					byte[] lba_byteArray;
					lba_byteArray = la_dataFile.getZipFinal();

					if(lba_byteArray != null)
					{
						InputStream lis_stream;
						lis_stream     = new ByteArrayInputStream(lba_byteArray);

						lsc_zipFinal = new DefaultStreamedContent(
							    lis_stream, TipoContenidoCommon.ZIP, "Aprobaciones.zip"
							);
					}
					else
						throw new B2BException(ErrorKeys.NO_GENERO_ZIP);
				}
				else
					throw new B2BException("Error generando ZIP.");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_idMessageGrowl2);
		}

		return lsc_zipFinal;
	}

	/**
	 * Método para encontrar todas las aprobaciones por etapa.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<InteresadoDocumentoTipo> findAprobaciones()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		try
		{
			lcidt_datos = irr_referenceRemote.findAprobaciones(getIdEtapa());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Método para encontrar los registros de aprobación filtrando por etapa y
	 * usuario de la etapa.
	 *
	 * @param aa_arg correspondiente al valor del tipo de objeto Aprobacion
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findDataAprobacion(Aprobacion aa_arg)
	    throws B2BException
	{
		Aprobacion             la_parameters;
		String                 ls_etapa;
		String                 ls_return;
		Collection<Aprobacion> lca_datosAprobacion;
		String                 ls_idMotivo;

		la_parameters           = new Aprobacion();
		ls_etapa                = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
				                                                       .getRequest()).getParameter("idMotivoTramite");
		lca_datosAprobacion     = new ArrayList<Aprobacion>();
		ls_return               = NavegacionCommon.APROBACION;
		ls_idMotivo             = aa_arg.getMotivo();
		setEsDesistimiento(false);

		generarFirmarMasivo(ls_etapa);

		if(aa_arg != null)
		{
			long ll_idEtapaAnterior;

			ll_idEtapaAnterior = NumericUtils.getLong(aa_arg.getIdEtapaAnterior());

			if((ll_idEtapaAnterior > 0) && StringUtils.isValidString(ls_idMotivo))
			{
				String ls_labelEnviar;

				ls_labelEnviar = getMessages().getString(MessagesKeys.LABEL_ENVIAR_ENTREGA);

				if(ll_idEtapaAnterior == EtapaCommon.ID_ETAPA_CALIFICACION)
				{
					if(
					    ls_idMotivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.APROBAR_SECUENCIAS))
						    || ls_idMotivo.equalsIgnoreCase(
						        StringUtils.getString(MotivoTramiteCommon.COBRO_POR_MAYOR_VALOR)
						    )
					)
						ls_labelEnviar = getMessages().getString(MessagesKeys.LABEL_ENVIAR);
				}
				else if(ll_idEtapaAnterior == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
				{
					if(
					    ls_idMotivo.equalsIgnoreCase(
						        StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
						    )
					)
						ls_labelEnviar = getMessages().getString(MessagesKeys.LABEL_ENVIAR);
				}
				else if(ll_idEtapaAnterior == EtapaCommon.ID_ETAPA_MAYOR_VALOR)
					ls_labelEnviar = getMessages().getString(MessagesKeys.LABEL_ENVIAR);
				else if(ll_idEtapaAnterior == EtapaCommon.ID_ETAPA_APROBACION)
					ls_labelEnviar = getMessages().getString(MessagesKeys.LABEL_ENVIAR);
				else if(
				    isVieneDeAprobacionAsesoriaJuridica() || isVieneDeDespachoSuperintendente()
					    || isVieneDeAprobacionFirmaDespacho()
				)
					ls_labelEnviar = getMessages().getString(MessagesKeys.LABEL_ENVIAR_APROBACION);
				else if(isVieneDeRevisionRecursosCoordinadorJuridico())
					ls_labelEnviar = getMessages().getString(MessagesKeys.LABEL_ENVIAR_SAJR);
				else if(ll_idEtapaAnterior == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
					setEsDesistimiento(true);

				setLabelBoton(ls_labelEnviar);
			}
		}

		try
		{
			la_parameters.setIdUsuarioCreacion(getUserId());
			la_parameters.setNombreSubproceso(getSubprocesoSeleccionado());
			la_parameters.setProceso(StringUtils.getStringNotNull(ls_etapa));
			la_parameters.setNir(getNir());
			la_parameters.setIdTurno(getIdTurno());

			if(isVieneDeAntiguoSistema())
			{
				la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_MAYOR_VALOR));

				lca_datosAprobacion = iaas_aprobadorAntiguoSistemaRemote.findAllData(
					    la_parameters, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
			else
			{
				if(isVieneDeCorrecciones())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES));
				else if(isVieneDeCoordinadorJuridico())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS));
				else if(isVieneDeAprobacionTrasladosOficinaDestino())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_DE_TRASLADOS_OFICINA_DESTINO)
					);
				else if(isVieneDeAprobacionDireccionTecnicaRegistro())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(
					        EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_DE_REGISTRO_OFICINA_DESTINO
					    )
					);
				else if(isVieneDeDireccionTecnicaRegistro())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_REGISTRO)
					);
				else if(isVieneDeAutorizacionResponsableDevoluciones())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.AUTORIZACION_RESPONSABLE_DEVOLUCIONES)
					);
				else if(isVieneDeEjecucionTraslado())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS));
				else if(isVieneDeAprobacionFirmaDespacho())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_FIRMA_DESPACHO)
					);
				else if(isVieneDeAprobacionSuperintendente())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE)
					);
				else if(isVieneDeAprobacionVisitas())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_DELEGADO_REGISTRO));
				else if(isVieneDeAprobacionEjecucionVisitas())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_EJECUCION));
				else if(isVieneDeAprobacionAsesoriaJuridica())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_REVISION_JURIDICA_FASE_TRASLADOS)
					);
				else if(isVieneDeBandejaFijacionAvisosOficinaOrigen())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN)
					);
				else if(isVieneDeBandejaFijacionAvisosOficinaDestino())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS));
				else if(isVieneDeDespachoSuperintendente())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(
					        EtapaCommon.ID_ETAPA_APROBACION_RESOLUCION_CREACION_SUPRESION_MODIFICACION
					    )
					);
				else if(isVieneDeCompOtroCirculo())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.SOLICITAR_COMPLEMENTACION_OTRO_CICRCULO)
					);
				else if(isVieneDeAprobacionSubDireccionApoyoJuridico())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_470));
				else if(isVieneDeRevisionRecursosCoordinadorJuridico())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_465));
				else if(isVieneDeEjecucionVisitas())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_830));
				else if(isVieneDeResponsableTesoreria())
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_380));
				else if(isVieneAprobacionApoyoNacional())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBADOR_APOYO_NACIONAL)
					);
				else if(isVieneDeAprobacionVisitasSuperintendente())
					la_parameters.setIdEtapa(
					    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE_845)
					);
				else
					la_parameters.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION));

				{
					boolean lb_oficinaOrigen;
					boolean lb_oficinaDestino;
					boolean lb_oficina;

					lb_oficinaOrigen      = isVieneDeBandejaFijacionAvisosOficinaOrigen();
					lb_oficinaDestino     = isVieneDeBandejaFijacionAvisosOficinaDestino();
					lb_oficina            = lb_oficinaOrigen || lb_oficinaDestino || isVieneDeEjecucionVisitas()
							|| isVieneDeAprobacionEjecucionVisitas();

					lca_datosAprobacion = irr_aprobacionRemote.findAllData(la_parameters, !lb_oficina);

					{
						String ls_nir;

						ls_nir = getNir();

						if(StringUtils.isValidString(ls_nir))
						{
							ls_nir = ls_nir.toUpperCase().trim();

							setNir(ls_nir);
						}

						aa_arg.setNir(ls_nir);
					}

					{
						String ls_idTurno;

						ls_idTurno = getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							ls_idTurno = ls_idTurno.toUpperCase().trim();

							setIdTurno(ls_idTurno);
						}

						aa_arg.setIdTurno(ls_idTurno);
					}

					aa_arg.setConsultaDetalle(true);

					//Probando
					if(isVieneDeBandejaFijacionAvisosOficinaOrigen() || isVieneDeBandejaFijacionAvisosOficinaDestino())
					{
						FacesContext lfc_context;
						String       ls_idTurno;
						lfc_context = FacesUtils.getFacesContext();

						BeanPublicacionAvisosWeb lbpaw_publiWeb;
						ls_idTurno     = aa_arg.getIdTurno();

						lbpaw_publiWeb = (BeanPublicacionAvisosWeb)lfc_context.getApplication()
								                                                  .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_PUBLICACION_AVISOS_WEB,
								    BeanPublicacionAvisosWeb.class
								);

						if(lbpaw_publiWeb != null)
						{
							Collection<DocumentosSalida> lcds_documentos;
							boolean                      lb_insertarDoc;

							lcds_documentos     = irr_aprobacionRemote.obtenerDocumentosPorTurno(
								    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);
							lb_insertarDoc      = true;

							if(CollectionUtils.isValidCollection(lcds_documentos))
							{
								Iterator<DocumentosSalida> lids_iterador;

								lids_iterador = lcds_documentos.iterator();

								while(lids_iterador.hasNext() && lb_insertarDoc)
								{
									DocumentosSalida lds_temp;

									lds_temp = lids_iterador.next();

									if(lds_temp != null)
									{
										String ls_tipo;

										ls_tipo = StringUtils.getStringNotNull(lds_temp.getTipo());

										if(ls_tipo.equalsIgnoreCase(ConstanteCommon.CONSTANCIA_INSCRIPCION))
											lb_insertarDoc = false;
									}
								}
							}

							if(isVieneDeBandejaFijacionAvisosOficinaOrigen())
								lbpaw_publiWeb.setIdEtapa(EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN);
							else
								lbpaw_publiWeb.setIdEtapa(EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS);

							{
								String ls_subproceso;
								String ls_proceso;

								ls_proceso        = getIdProcesoSeleccionado();
								ls_subproceso     = getSubprocesoSeleccionado();

								if(StringUtils.isValidString(ls_proceso) && StringUtils.isValidString(ls_subproceso))
									lbpaw_publiWeb.cargarCasosFijacionAvisos(ls_proceso, ls_subproceso, aa_arg);
								else
									lbpaw_publiWeb.cargarCasos();
							}

							ls_return = NavegacionCommon.FIJACION_AVISOS;
						}
					}
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			clh_LOGGER.error("findDataAprobacion", lb2b_e);
			addMessage(lb2b_e);
			ls_return = null;
			PrimeFaces.current().ajax().update(is_idMessageGrowl2);
		}

		setDataAprobacion(lca_datosAprobacion);
		setRegresarBandejaOfiDestino(true);
		
		return ls_return;
	}

	/**
	 * Método para encontrar el detalle de aprobación filtrando por el nir, turno,
	 * etapa y usuario.
	 *
	 * @return devuelve el valor de String
	 */
	public String findDetalleAprobacion()
	{
		setMotivoTramiteSeleccionado(null);

		return findDetalleAprobacion(true);
	}

	/**
	 * Método para encontrar el detalle de aprobación filtrando por el nir, turno,
	 * etapa y usuario.
	 *
	 * @param ab_bandejaSubproceso correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String findDetalleAprobacion(boolean ab_bandejaSubproceso)
	{
		String ls_return;
		Ajax   la_ajax;

		ls_return     = NavegacionCommon.APROBACION;
		la_ajax       = PrimeFaces.current().ajax();

		try
		{
			String                 ls_proceso;
			Aprobacion             la_tmp;
			Collection<Aprobacion> lca_dataBandeja;
			boolean                lb_vieneDeFijacion;

			ls_proceso = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idMotivoTramite");

			setMostrarGrafica(true);

			if(!StringUtils.isValidString(ls_proceso))
			{
				ls_proceso = getMotivoTramiteSeleccionado();

				if(!StringUtils.isValidString(ls_proceso))
					ls_proceso = "6";
			}
			else
				setMotivoTramiteSeleccionado(ls_proceso);

			lb_vieneDeFijacion     = isVieneDeBandejaFijacionAvisosOficinaOrigen();

			la_tmp = new Aprobacion();

			la_tmp.setIdUsuarioCreacion(getUserId());

			{
				String ls_nir;

				ls_nir = getNir();

				if(StringUtils.isValidString(ls_nir))
				{
					ls_nir = ls_nir.toUpperCase().trim();

					setNir(ls_nir);
				}

				la_tmp.setNir(ls_nir);
			}

			{
				String ls_idTurno;

				ls_idTurno = getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					ls_idTurno = ls_idTurno.toUpperCase().trim();

					setIdTurno(ls_idTurno);
				}

				la_tmp.setIdTurno(ls_idTurno);
			}

			if(isVieneDeAntiguoSistema())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_MAYOR_VALOR));
			else if(isVieneDeCorrecciones())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES));
			else if(isVieneDeCoordinadorJuridico())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS));
			else if(isVieneDeAprobacionDireccionTecnicaRegistro())
				la_tmp.setIdEtapa(
				    NumericUtils.getLongWrapper(
				        EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_DE_REGISTRO_OFICINA_DESTINO
				    )
				);
			else if(isVieneDeDireccionTecnicaRegistro())
				la_tmp.setIdEtapa(
				    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_REGISTRO)
				);
			else if(isVieneDeAutorizacionResponsableDevoluciones())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.AUTORIZACION_RESPONSABLE_DEVOLUCIONES));
			else if(isVieneDeAprobacionTrasladosOficinaDestino())
				la_tmp.setIdEtapa(
				    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_DE_TRASLADOS_OFICINA_DESTINO)
				);
			else if(isVieneDeAprobacionFirmaDespacho())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_FIRMA_DESPACHO));
			else if(isVieneDeAprobacionVisitas())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_DELEGADO_REGISTRO));
			else if(isVieneDeAprobacionEjecucionVisitas())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_EJECUCION));
			else if(isVieneDeAprobacionSuperintendente())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE));
			else if(isVieneDeAprobacionAsesoriaJuridica())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_REVISION_JURIDICA_FASE_TRASLADOS));
			else if(isVieneDeEjecucionTraslado())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS));
			else if(lb_vieneDeFijacion)
			{
				la_tmp.setConMotivoNoTramite(lb_vieneDeFijacion);
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN));
			}
			else if(isVieneDeBandejaFijacionAvisosOficinaDestino())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS));
			else if(isVieneDeDespachoSuperintendente())
				la_tmp.setIdEtapa(
				    NumericUtils.getLongWrapper(
				        EtapaCommon.ID_ETAPA_APROBACION_RESOLUCION_CREACION_SUPRESION_MODIFICACION
				    )
				);
			else if(isVieneDeCompOtroCirculo())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.SOLICITAR_COMPLEMENTACION_OTRO_CICRCULO));
			else if(isVieneDeAprobacionSubDireccionApoyoJuridico())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_470));
			else if(isVieneDeRevisionRecursosCoordinadorJuridico())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_465));
			else if(isVieneDeEjecucionVisitas())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_830));
			else if(isVieneDeResponsableTesoreria())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_380));
			else if(isVieneAprobacionApoyoNacional())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBADOR_APOYO_NACIONAL));
			else if(isVieneDeAprobacionVisitasSuperintendente())
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE_845));
			else
				la_tmp.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_APROBACION));

			la_tmp.setConsultaDetalle(true);

			if(!ab_bandejaSubproceso)
			{
				String ls_idProceso;

				ls_idProceso = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getParameter("idProceso");

				if(!StringUtils.isValidString(ls_idProceso))
				{
					ls_idProceso = getIdProcesoSeleccionado();

					if(!StringUtils.isValidString(ls_idProceso))
						ls_idProceso = "6";
				}
				else
				{
					if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_1))
						setProcesoCertificados(true);

					setIdProcesoSeleccionado(ls_idProceso);
				}

				la_tmp.setIdProceso(ls_idProceso);
				la_tmp.setNombreProceso(ls_proceso);
			}
			else
			{
				if(!CollectionUtils.isValidCollection(getTramitesDetalleMotivo()))
					setMotivoTramiteSeleccionado(null);

				setProcesoCertificados(false);
			}

			{
				boolean lb_oficinaOrigen;
				boolean lb_oficinaDestino;
				boolean lb_ejecucionVisitas;
				boolean lb_aprobacionEjecucionVisitas;
				boolean lb_oficina;

				lb_oficinaOrigen                  = isVieneDeBandejaFijacionAvisosOficinaOrigen();
				lb_oficinaDestino                 = isVieneDeBandejaFijacionAvisosOficinaDestino();
				lb_ejecucionVisitas               = isVieneDeEjecucionVisitas();
				lb_aprobacionEjecucionVisitas     = isVieneDeAprobacionEjecucionVisitas();
				lb_oficina                        = lb_oficinaOrigen || lb_oficinaDestino || lb_ejecucionVisitas
						|| lb_aprobacionEjecucionVisitas;

				lca_dataBandeja = irr_aprobacionRemote.findDetalleBandeja(
					    la_tmp, ab_bandejaSubproceso, !lb_oficina, getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);
			}

			if(CollectionUtils.isValidCollection(lca_dataBandeja))
			{
				if(ab_bandejaSubproceso)
				{
					for(Aprobacion la_actual : lca_dataBandeja)
					{
						if(la_actual != null)
						{
							Collection<Aprobacion> lca_tmp;
							long                   li_cantidadPorEtapas;

							lca_tmp                  = la_actual.getDatosBandeja();
							li_cantidadPorEtapas     = 0;

							for(Aprobacion la_data : lca_tmp)
							{
								if(la_data != null)
									li_cantidadPorEtapas = li_cantidadPorEtapas
										+ NumericUtils.getLong(la_data.getCantidad());
							}

							la_actual.setTotalBandeja(li_cantidadPorEtapas);
						}
					}

					setTramitesAprobacion(lca_dataBandeja);

					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				}
				else
				{
					long li_cantidadPorEtapas;

					li_cantidadPorEtapas = 0;

					for(Aprobacion la_actual : lca_dataBandeja)
					{
						if(la_actual != null)
						{
							li_cantidadPorEtapas = li_cantidadPorEtapas + NumericUtils.getLong(la_actual.getCantidad());

							setTotalBandeja(li_cantidadPorEtapas);
						}
					}

					setMostrarGrafica(false);
					setTramitesDetalleMotivo(lca_dataBandeja);
				}

				la_ajax.update(is_idMessageGrowl);

				setMostrarBandejaSubprocesos(ab_bandejaSubproceso);
			}
			else
				addMessageInfo(EstadoCommon.W, MessagesKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			setMostrarBandejaSubprocesos(!ab_bandejaSubproceso);

			ls_return = null;
			clean();
			clh_LOGGER.error("findDetalleAprobacion", lb2be_e);

			addMessage(lb2be_e);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			actualizarComponente(is_idMessageGrowl2);
		}

		return ls_return;
	}

	/**
	 * Información a mostrar en el campo para firma del aprobador.
	 *
	 * @param aa_aprobacion correspondiente al valor del tipo de objeto Aprobacion
	 * @return devuelve el valor de Collection
	 */
	public Collection<FirmaAprobacion> firmas(Aprobacion aa_aprobacion)
	{
		Collection<FirmaAprobacion> lcfa_firmas;

		lcfa_firmas = new ArrayList<FirmaAprobacion>();

		lcfa_firmas.add(
		    new FirmaAprobacion(getMessages().getString(MessagesKeys.FIRMAR), IdentificadoresCommon.FIRMAR)
		);

		if(aa_aprobacion != null)
		{
			if(aa_aprobacion.isEtapaAnterior100O110())
				lcfa_firmas.add(
				    new FirmaAprobacion(
				        getMessages().getString(MessagesKeys.DEVOLVER_APROBADOR_ANTIGUO_SISTEMA),
				        IdentificadoresCommon.ANTIGUO_SISTEMA
				    )
				);
			else
			{
				if(!aa_aprobacion.isEtapaAnterior180())
					lcfa_firmas.add(
					    new FirmaAprobacion(
					        getMessages().getString(MessagesKeys.DEVOLVER_A_CALIFICACION),
					        IdentificadoresCommon.DEVOLVER
					    )
					);
			}
		}

		return lcfa_firmas;
	}

	/**
	 * Método que genera zip que contiene relaciones en formato pdf.
	 *
	 * @param ab_firmar bandera para indicar si el proceso es de firma
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarDocumentoRelacionAprobacion(boolean ab_firmar)
	    throws B2BException
	{
		PrimeFaces lpf_faces;

		lpf_faces = PrimeFaces.current();

		try
		{
			boolean lb_procesoTerminado;
			boolean lb_validar;
			Long    ll_idTurnoHistoria;
			String  ls_observacionesTemp;

			lb_procesoTerminado      = false;
			lb_validar               = false;
			ll_idTurnoHistoria       = getIdTurnoHistoria();
			ls_observacionesTemp     = null;

			setObservacionesTemporales(null);

			if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				lb_validar = true;
			else
			{
				Collection<Aprobacion> ll_aprobacion;

				ll_aprobacion = getDataAprobacion();

				if(CollectionUtils.isValidCollection(ll_aprobacion))
				{
					Iterator<Aprobacion> lia_iterator;

					lia_iterator = ll_aprobacion.iterator();

					while(lia_iterator.hasNext() && !lb_validar)
					{
						Aprobacion la_aprobacion;

						la_aprobacion = lia_iterator.next();

						if(la_aprobacion != null)
						{
							Long ll_idTurnoHistoriaTemp;

							ll_idTurnoHistoriaTemp = la_aprobacion.getIdTurnoHistoria();

							if(NumericUtils.isValidLong(ll_idTurnoHistoriaTemp))
							{
								ll_idTurnoHistoria     = ll_idTurnoHistoriaTemp;
								lb_validar             = true;
							}
						}
					}
				}
			}

			if(lb_validar)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

				lth_turnoHistoria = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_etadoActividad;

					ls_etadoActividad = lth_turnoHistoria.getEstadoActividad();

					if(StringUtils.isValidString(ls_etadoActividad))
					{
						lb_procesoTerminado      = ls_etadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA);
						ls_observacionesTemp     = lth_turnoHistoria.getObservaciones();
					}
				}
			}

			if(lb_procesoTerminado)
			{
				setObservacionesTemporales(ls_observacionesTemp);

				lpf_faces.executeScript("PF('dlgSuspension').show();");
				lpf_faces.ajax().update("fDialogSuspension");
			}
			else
			{
				Map<String, byte[]> llhm_docs;

				llhm_docs = irr_aprobacionRemote.generarDocumentoRelacionAprobacion(
					    getDataAprobacion(), ab_firmar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(llhm_docs))
				{
					byte[]                          lba_zipFinal;
					InputStream                     lis_stream;
					Iterator<Entry<String, byte[]>> liesb_iterador;
					StringBuilder                   lsb_relacionesString;

					liesb_iterador           = llhm_docs.entrySet().iterator();
					lsb_relacionesString     = new StringBuilder();

					lsb_relacionesString.append(" ");

					while(liesb_iterador.hasNext())
					{
						Entry<String, byte[]> le_objeto;

						le_objeto = liesb_iterador.next();

						if(le_objeto != null)
						{
							String ls_key;

							ls_key = le_objeto.getKey();

							if(StringUtils.isValidString(ls_key))
							{
								lsb_relacionesString.append(ls_key);

								if(liesb_iterador.hasNext())
									lsb_relacionesString.append(", ");
							}
						}
					}

					lba_zipFinal     = irr_aprobacionRemote.generarArchivoZipAprobacionRelacion(llhm_docs);
					lis_stream       = new ByteArrayInputStream(lba_zipFinal);

					setZipDocumentosRelacion(
					    new DefaultStreamedContent(lis_stream, TipoContenidoCommon.ZIP, "APROBACION_RELACION.zip")
					);

					setRelacionesCreadasString(lsb_relacionesString.toString());
				}

				setDocumentosGenerados(llhm_docs);

				if(ab_firmar)
				{
					if(
					    isVieneDeAprobacionAsesoriaJuridica() || isVieneDeDespachoSuperintendente()
						    || isVieneDeAprobacionFirmaDespacho()
					)
						setRelacionesCreadasString(getMessages().getString(MessagesKeys.PROCESO_COMPLETADO));

					if(!CollectionUtils.isValidCollection(getTramitesDetalleMotivo()))
						setMotivoTramiteSeleccionado(null);

					if(NumericUtils.getLong(getIdEtapa()) == EtapaCommon.ID_ETAPA_380)
					{
						MotivoTramite lmt_motivo;
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = new TurnoHistoria();

						lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

						lth_turnoHistoria     = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoria);
						lmt_motivo            = new MotivoTramite(EtapaCommon.ID_ETAPA_380, getIdMotivo380());

						if(lth_turnoHistoria != null)
							irr_aprobacionRemote.actualizarEtapaYCrearSiguiente(
							    lth_turnoHistoria, lmt_motivo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
					}

					lpf_faces.executeScript("PF('confirmacionRelaciones').show();");
				}
			}

			if(!ab_firmar)
			{
				Collection<Aprobacion> lca_aprobacion;
				Aprobacion             la_tmp;

				lca_aprobacion     = getDataAprobacion();
				la_tmp             = irr_aprobacionRemote.validacionEtapaAnteriorAprobacionRecursos(
					    lca_aprobacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(la_tmp != null)
				{
					setEsRecursos(la_tmp.isEsRecursos());

					PrimeFaces.current().ajax().update("fDialogPreguntaAprobarRelaciones");

					setNoEsObligatorioRecursos(la_tmp.isNoEsObligatorioRecursos());

					if(la_tmp.isPreseleccionRechazoRecursos())
						setIdTipoDecisionRecurso("5");

					{
						String ls_idTipoDecisionRecurso;

						ls_idTipoDecisionRecurso = la_tmp.getIdTipoDecisionRecurso();

						if(StringUtils.isValidString(ls_idTipoDecisionRecurso))
							setIdTipoDecisionRecurso(ls_idTipoDecisionRecurso);
					}

					{
						String ls_nombreTipoDecisionRecurso;

						ls_nombreTipoDecisionRecurso = la_tmp.getNombreTipoDecisionRecurso();

						if(StringUtils.isValidString(ls_nombreTipoDecisionRecurso))
							setNombreTipoDecisionRecurso(ls_nombreTipoDecisionRecurso);
					}

					setIdEtapa(la_tmp.getIdEtapa());
				}

				lpf_faces.executeScript("PF('" + cs_PREGUNTA_APROBAR_RELACIONES_DIALOG + "').show();");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentoRelacionAprobacion", lb2be_e);
			lpf_faces.executeScript("PF('" + cs_PREGUNTA_APROBAR_RELACIONES_DIALOG + "').hide();");
			addMessage(lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarDocumentoRelacionAprobacion", le_e);
			throw new B2BException("Exception", le_e);
		}
	}

	/**
	 * Generar torta.
	 *
	 * @return el valor de pie chart model
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PieChartModel generarTorta()
	    throws B2BException
	{
		return getModeloTorta();
	}

	/**
	 * Método para poder guardar el idTurnoHistoria y las observaciones del
	 * aprobador.
	 *
	 * @param aa_a            Aprobación que se quiere guardar el id y obtenr las observaciones
	 *            del aprobador
	 */
	public void guardarIdTurnoHistoria(Aprobacion aa_a)
	{
		Long ll_idturnohistoria;
		ll_idturnohistoria = aa_a.getIdTurnoHistoria();

		if(NumericUtils.isValidLong(ll_idturnohistoria))
		{
			setIdTurnoHistoria(ll_idturnohistoria);
			setObservacionesTurnoHistoria(aa_a.getObservacionesAprobador());
		}

		PrimeFaces.current().executeScript("PF('dlg4').show();");
		PrimeFaces.current().ajax().update("fDialog:id_comentarioaprobacion");
	}

	/**
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}

	/**
	 * Hace un reset a las variables que funcionan como indicador de un proceso específico.
	 */
	public void limpiarBanderaProcesos()
	{
		setVieneDeAprobacion(false);
		setVieneDeAntiguoSistema(false);
		setVieneDeAutorizacionFirma(false);
		setVieneDeCorrecciones(false);
		setVieneDeCoordinadorJuridico(false);
		setVieneDeDireccionTecnicaRegistro(false);
		setVieneAprobacionApoyoNacional(false);
		setVieneDeAprobacionSubDireccionApoyoJuridico(false);

		setVieneDeCompOtroCirculo(false);
		setVieneDeEjecucionTraslado(false);
		setVieneDeDespachoSuperintendente(false);
		setVieneDeAprobacionAsesoriaJuridica(false);

		//Sección booleanas miga de pan
		setVieneDeAprobacion(false);
		setVieneDeCorrecciones(false);
		setVieneDeCoordinadorJuridico(false);
		setVieneDeAprobacionTrasladosOficinaDestino(false);
		setVieneDeAprobacionDireccionTecnicaRegistro(false);
		setVieneDeBandejaFijacionAvisosOficinaOrigen(false);
		setVieneDeBandejaFijacionAvisosOficinaDestino(false);
		setVieneDeAprobacionFirmaDespacho(false);
		setVieneDeAprobacionVisitas(false);
		setVieneDeAprobacionEjecucionVisitas(false);
		setVieneDeAprobacionSuperintendente(false);
		setVieneDeEjecucionVisitas(false);
		setVieneDeResponsableTesoreria(false);
		setVieneDeAutorizacionResponsableDevoluciones(false);
	}

	/**
	 * Método para mostrar o no mostrar las observaciones de la aprobacion.
	 */
	public void mostrarObservaciones()
	{
		Collection<Aprobacion> lda_dataaprobacion;
		lda_dataaprobacion = getDataAprobacion();

		if(lda_dataaprobacion != null)
		{
			for(Aprobacion la_aprobacion : ila_dataAprobacion)
			{
				String ls_firmar;
				ls_firmar = la_aprobacion.getFirmar();

				if(StringUtils.isValidString(ls_firmar))
				{
					if(ls_firmar.equalsIgnoreCase("DEVOLVER"))
						la_aprobacion.setMostrarObservaciones(true);
					else if(ls_firmar.equalsIgnoreCase("ANTIGUO_SISTEMA"))
						la_aprobacion.setMostrarObservaciones(true);
					else if(ls_firmar.equalsIgnoreCase(IdentificadoresCommon.FIRMAR))
						la_aprobacion.setMostrarObservaciones(false);
					else if(ls_firmar.equalsIgnoreCase("SELECCIONE"))
						la_aprobacion.setMostrarObservaciones(false);
				}
			}
		}
	}

	/**
	 * Método para poder guardar las observaciones del
	 * proceso anterior.
	 *
	 * @param aa_a objeto contenedor de observaciones a validar
	 */
	public void mostrarObservacionesProcesoAnterior(Aprobacion aa_a)
	{
		if(aa_a != null)
		{
			String ls_observaciones;

			ls_observaciones = null;

			if(isVieneDeAntiguoSistema())
			{
				ls_observaciones = aa_a.getObservacionesProcesoAnterior();

				PrimeFaces.current().ajax().update("fAprobacion:globalMsg");
			}
			else
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = aa_a.getTurnoHistoria();

				if(lth_turnoHistoria != null)
				{
					ls_observaciones = lth_turnoHistoria.getObservaciones();

					PrimeFaces.current().ajax().update("fAprobacion:globalMsg");
				}
			}

			if(StringUtils.isValidString(ls_observaciones))
			{
				setObservaciones(ls_observaciones);

				PrimeFaces.current().executeScript("PF('obsProcesoAnterior').show();");
				PrimeFaces.current().ajax().update("fDialogo:id_observacionesProcesoAnterior");
			}
			else
				addMessage(MessagesKeys.SIN_OBSERVACIONES_PROCESO_ANTERIOR);
		}
	}

	/**
	 * Mostrar torta.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean mostrarTorta()
	    throws B2BException
	{
		return isMostrarGrafica();
	}

	/**
	 * Retorna a la bandeja de turnos de aprobación.
	 *
	 * @return enlace a la pagina de turnos
	 */
	public String regresar()
	{
		String ls_return;

		ls_return = NavegacionCommon.BANDEJA_ENTRADA;

		if(!isVieneDeEjecucionVisitas())
		{
			clean();
			findDetalleAprobacion(false);

			if(ib_regresarBandejaOfiDestino)
			{
				setRegresarBandejaOfiDestino(false);

				ls_return = NavegacionCommon.BANDEJA_OFI_DESTINO;
			}
		}

		return ls_return;
	}

	/**
	 * Muestra un mensaje de confirmación dependiendo de la desición tomada con
	 * respecto a un turno especifico.
	 */
	public void renderedMensaje()
	{
		setMostrarFirmar(false);
		setMostrarDevolver(false);

		boolean lb_firmar;
		lb_firmar = false;

		if(getDataAprobacion() != null)
		{
			for(Aprobacion la_tmp : getDataAprobacion())
			{
				if(la_tmp.getFirmar() != null)
				{
					if(la_tmp.getFirmar().equalsIgnoreCase(IdentificadoresCommon.FIRMAR))
					{
						setMostrarFirmar(true);
						lb_firmar = true;
					}
					else if(la_tmp.getFirmar().equalsIgnoreCase("DEVOLVER"))
					{
						setMostrarDevolver(true);
						lb_firmar = true;
					}
					else if(la_tmp.getFirmar().equalsIgnoreCase("ANTIGUO_SISTEMA"))
					{
						setMostrarDevolver(true);
						lb_firmar = true;
					}
				}
			}
		}

		if(lb_firmar == false)
		{
			addMessage("W", getMessages().getString(MessagesKeys.SIN_FIRMA));
			PrimeFaces.current().ajax().update(is_idMessageGrowl2);
		}
		else
			PrimeFaces.current().executeScript("PF('dlg').show();");

		PrimeFaces.current().ajax().update("fDialogSalvar");
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnBandeja()
	{
		findDetalleAprobacion();

		return NavegacionCommon.BANDEJA_ENTRADA;
	}

	/**
	 * Envía los casos que hayan sido seleccionados en pantalla para ser procesados,
	 * ya sea para ser firmados o regresarlos a una etapa anterior.
	 *
	 * @return enlace para redirigir a la bandeja principal de la etapa 190
	 */
	public String salvar()
	{
		String ls_return;

		ls_return = null;

		try
		{
			Aprobacion             la_parametros;
			Collection<Aprobacion> lca_todasAprobaciones;

			la_parametros             = new Aprobacion();
			lca_todasAprobaciones     = getDataAprobacion();

			if(!CollectionUtils.isValidCollection(lca_todasAprobaciones))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			la_parametros.setDataZip(lca_todasAprobaciones);

			clean();
			findDetalleAprobacion();

			ls_return = NavegacionCommon.BANDEJA_ENTRADA;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvar", lb2be_e);

			addMessage(lb2be_e);

			actualizarComponente(is_idMessageGrowl2);
		}

		return ls_return;
	}

	/**
	 * Validar tipo recurso.
	 */
	public void validarTipoRecurso()
	{
		try
		{
			if(isEsRecursos())
			{
				Collection<Aprobacion> lca_aprobacion;
				String                 ls_idTipoDecisionRecurso;

				lca_aprobacion               = getDataAprobacion();
				ls_idTipoDecisionRecurso     = getIdTipoDecisionRecurso();

				if(!StringUtils.isValidString(ls_idTipoDecisionRecurso) && !isNoEsObligatorioRecursos())
					throw new B2BException(ErrorKeys.SELECCIONE_TIPO_DECISION_RECURSO);

				irr_aprobacionRemote.insertarTipoDecisionRecurso(
				    lca_aprobacion, ls_idTipoDecisionRecurso, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarTipoRecurso", lb2be_e);

			addMessage(lb2be_e);

			actualizarComponente(is_idMessageGrowl2);
		}
	}

	/**
	 * Método encargado de validar si la etapa es apta para firmar.
	 *
	 * @param as_etapa correspondiente al valor del tipo de objeto String
	 */
	private void generarFirmarMasivo(String as_etapa)
	{
		if(StringUtils.isValidString(as_etapa))
		{
			Collection<FirmaAprobacion> lcfa_firmas;

			lcfa_firmas = new ArrayList<FirmaAprobacion>();

			lcfa_firmas.add(
			    new FirmaAprobacion(getMessages().getString(MessagesKeys.FIRMAR), IdentificadoresCommon.FIRMAR)
			);

			if(!as_etapa.contains(MotivonNoTramiteCommon.REPRODUCCION_DE_CONSTANCIA))
			{
				if(as_etapa.equalsIgnoreCase(MotivonNoTramiteCommon.APROBADO_ANTIGUO_SISTEMA))
					lcfa_firmas.add(
					    new FirmaAprobacion(
					        getMessages().getString(MessagesKeys.DEVOLVER_APROBADOR_ANTIGUO_SISTEMA),
					        IdentificadoresCommon.ANTIGUO_SISTEMA
					    )
					);
				else
					lcfa_firmas.add(
					    new FirmaAprobacion(
					        getMessages().getString(MessagesKeys.DEVOLVER_A_CALIFICACION),
					        IdentificadoresCommon.DEVOLVER
					    )
					);
			}

			setFirmasMasivo(lcfa_firmas);
		}
	}
}
