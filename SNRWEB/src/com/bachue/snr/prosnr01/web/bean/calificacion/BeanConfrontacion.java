package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.excel.ExcelUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.suspension.SuspensionRemote;

import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.calificacion.Calificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConfrontacionCorrectiva;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;

import com.bachue.snr.prosnr01.web.bean.testamentos.BeanTestamentos;
import com.bachue.snr.prosnr01.web.util.ColumnModel;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConfrontacion.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanConfrontacion")
public class BeanConfrontacion extends BeanNotaDevolutiva implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2096507104369954298L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fCalifCorrecBasic:idGrowl";

	/** Constante is_idForm. */
	private static final String is_idForm = "fCalifCorrecBasic";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConfrontacion.class);

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad iap apertura. */
	private Apertura iap_apertura;

	/** Propiedad ioc calificaciones. */
	private Calificacion ioc_calificaciones;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icmso documentos vinculado. */
	private Collection<Map<String, Object>> icmso_documentosVinculado;

	/** Propiedad icpaui cpaui. */
	private Collection<PredioActoIU> icpaui_cpaui;

	/** Propiedad icpaui cpui. */
	private Collection<PredioActoIU> icpaui_cpui;

	/** Propiedad illlhmso documentos. */
	private Collection<Map<String, Object>> illlhmso_documentos;

	/** Propiedad illlhmso matriculas. */
	private Collection<Map<String, Object>> illlhmso_matriculas;

	/** Propiedad ilpaui selected matriculas eliminar. */
	private List<PredioActoIU> ilpaui_selectedMatriculasEliminar;

	/** Propiedad ipr predio registro. */
	private PredioRegistro ipr_predioRegistro;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc file. */
	private StreamedContent isc_file;

	/** Propiedad is circulo. */
	private String is_circulo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad mensaje predio inconsistente. */
	private String is_mensajePredioInconsistente;

	/** Propiedad is motivo tramite. */
	private String is_motivoTramite;

	/** Propiedad is observaciones proceso anterior. */
	private String is_observacionesProcesoAnterior;

	/** Propiedad is observaciones verificar folio. */
	private String is_observacionesVerificarFolio;

	/** Propiedad is rango fin. */
	private String is_rangoFin;

	/** Propiedad is rango inicio. */
	private String is_rangoInicio;

	/** Propiedad is seccion tipo matricula. */
	private String is_seccionTipoMatricula;

	/** Propiedad is turno vinculado. */
	private String is_turnoVinculado;

	/** Propiedad isr suspension remote. */
	@EJB
	private SuspensionRemote isr_suspensionRemote;

	/** Propiedad ib datos basicos. */
	private boolean ib_agregoUnamatriculaCertificados;

	/** Propiedad ib datos basicos. */
	private boolean ib_datosBasicos;

	/** Propiedad ib decision elimina matricula. */
	private boolean ib_decisionEliminaMatricula;

	/** Propiedad ib decisionverifica folio cerrado. */
	private boolean ib_decisionverificaFolioCerrado;

	/** Propiedad ib elimina matricula. */
	private boolean ib_eliminaMatricula;

	/** Propiedad ib es acto con ejecutoria. */
	private boolean ib_esActoConEjecutoria;

	/** Propiedad ib es certificados especiales. */
	private boolean ib_esCertificadosEspeciales;

	/** Propiedad ib inserta matricula. */
	private boolean ib_insertaMatricula;

	/** Propiedad ib turno seleccionado. */
	private boolean ib_turnoSeleccionado;

	/** Propiedad ib verifica folio cerrado. */
	private boolean ib_verificaFolioCerrado;

	/**
	 * Propiedad il id etapa
	 */
	private long il_idEtapa;

	/**
	 * Modifica el valor de actos asociados general.
	 *
	 * @param acpaui_cpaiu asigna el valor a la propiedad actos asociados general
	 */
	public void setActosAsociadosGeneral(Collection<PredioActoIU> acpaui_cpaiu)
	{
		icpaui_cpui = acpaui_cpaiu;
	}

	/**
	 * Retorna el valor de actos asociados general.
	 *
	 * @return el valor de actos asociados general
	 */
	public Collection<PredioActoIU> getActosAsociadosGeneral()
	{
		return icpaui_cpui;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad agregoUnamatriculaCertificados por ab_b
	 */
	public void setAgregoUnamatriculaCertificados(boolean ab_b)
	{
		ib_agregoUnamatriculaCertificados = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad agregoUnamatriculaCertificados
	 */
	public boolean isAgregoUnamatriculaCertificados()
	{
		return ib_agregoUnamatriculaCertificados;
	}

	/**
	 * Modifica el valor de apertura.
	 *
	 * @param aap_ap asigna el valor a la propiedad apertura
	 */
	public void setApertura(Apertura aap_ap)
	{
		iap_apertura = aap_ap;
	}

	/**
	 * Retorna el valor de apertura.
	 *
	 * @return el valor de apertura
	 */
	public Apertura getApertura()
	{
		if(iap_apertura == null)
			iap_apertura = new Apertura();

		return iap_apertura;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de calificaciones.
	 *
	 * @param ac_c asigna el valor a la propiedad calificaciones
	 */
	public void setCalificaciones(Calificacion ac_c)
	{
		ioc_calificaciones = ac_c;
	}

	/**
	 * Retorna el valor de calificaciones.
	 *
	 * @return el valor de calificaciones
	 */
	public Calificacion getCalificaciones()
	{
		if(ioc_calificaciones == null)
			ioc_calificaciones = new Calificacion();

		return ioc_calificaciones;
	}

	/**
	 * Modifica el valor de circulo.
	 *
	 * @param as_s asigna el valor a la propiedad circulo
	 */
	public void setCirculo(String as_s)
	{
		is_circulo = as_s;
	}

	/**
	 * Retorna el valor de circulo.
	 *
	 * @return el valor de circulo
	 */
	public String getCirculo()
	{
		if(!StringUtils.isValidString(is_circulo))
		{
			try
			{
				is_circulo = irr_calificacionRemote.findByIdTurno(getIdTurnoHistoria());
			}
			catch(B2BException e)
			{
				clh_LOGGER.error("getCirculo", e);
			}
		}

		return is_circulo;
	}

	/**
	 * Sobrecarga de método encargado de consultar lista de actos asociados a la solicitud que se esta trabajando.
	 *
	 * @return el valor de columns
	 */
	public List<ColumnModel> getColumns()
	{
		return getColumns(isIndVinculado());
	}

	/**
	 * Método encargado de consultar lista de actos asociados a la solicitud que se esta trabajando.
	 *
	 * @param ab_vinculado Variable de tipo <code>boolean</code> que indica si el caso actual es un caso con turnos vinculados
	 * @return el valor de columns
	 */
	public List<ColumnModel> getColumns(boolean ab_vinculado)
	{
		List<ColumnModel> llcm_columns;

		llcm_columns = new ArrayList<ColumnModel>();

		try
		{
			Collection<String> lcs_solicitudes;

			lcs_solicitudes = new ArrayList<String>();

			if(ab_vinculado)
			{
				Collection<RegistroCalificacion> lcrc_infoTurnos;

				lcrc_infoTurnos = getInfoTurnos();

				if(CollectionUtils.isValidCollection(lcrc_infoTurnos))
				{
					for(RegistroCalificacion lrc_item : lcrc_infoTurnos)
					{
						if(lrc_item != null)
						{
							TurnoHistoria lth_tmp;

							lth_tmp     = new TurnoHistoria(lrc_item.getIdTurnoHistoria());

							lth_tmp = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmp);

							if(lth_tmp != null)
								lcs_solicitudes.add(lth_tmp.getIdSolicitud());
						}
					}
				}
			}
			else
				lcs_solicitudes.add(findSolicitudByIdTurnoHistoria(getIdTurnoHistoria()));

			if(CollectionUtils.isValidCollection(lcs_solicitudes))
			{
				for(String ls_solicitud : lcs_solicitudes)
				{
					if(StringUtils.isValidString(ls_solicitud))
					{
						Collection<TipoActo> lca_actosSolicitud;
						Acto                 la_param;

						la_param = new Acto();

						la_param.setIdSolicitud(ls_solicitud);
						la_param.setIdCirculo(getCirculo());

						lca_actosSolicitud = findActosByIdSolicitudCirculo(la_param, getUserId());

						if(CollectionUtils.isValidCollection(lca_actosSolicitud))
						{
							Iterator<TipoActo> lia_actos;

							lia_actos = lca_actosSolicitud.iterator();

							while(lia_actos.hasNext())
							{
								TipoActo la_actos;

								la_actos = lia_actos.next();

								if(la_actos != null)
									llcm_columns.add(
									    new ColumnModel(
									        la_actos.getIdTipoActo(), Boolean.FALSE, la_actos.getNombre(),
									        conversionCientifica(
									            NumericUtils.getDoubleWrapper(la_actos.getIdTipoCalculo())
									        ), la_actos.getIdUsuarioCreacion(), ls_solicitud
									    )
									);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("getColumns", lb2be_e);
			llcm_columns = null;
		}

		return llcm_columns;
	}

	/**
	 * Modifica el valor de datos basicos.
	 *
	 * @param ab_b asigna el valor a la propiedad datos basicos
	 */
	public void setDatosBasicos(boolean ab_b)
	{
		ib_datosBasicos = ab_b;
	}

	/**
	 * Valida la propiedad datos basicos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos basicos
	 */
	public boolean isDatosBasicos()
	{
		if(!(ib_datosBasicos))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("datosBasicos");

			if(StringUtils.isValidString(tmp))
				ib_datosBasicos = tmp.equals("true");
		}

		return ib_datosBasicos;
	}

	/**
	 * Modifica el valor de decision elimina matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad decision elimina matricula
	 */
	public void setDecisionEliminaMatricula(boolean ab_b)
	{
		ib_decisionEliminaMatricula = ab_b;
	}

	/**
	 * Valida la propiedad decision elimina matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en decision elimina matricula
	 */
	public boolean isDecisionEliminaMatricula()
	{
		return ib_decisionEliminaMatricula;
	}

	/**
	 * Modifica el valor de decisionverifica folio cerrado.
	 *
	 * @param ab_b asigna el valor a la propiedad decisionverifica folio cerrado
	 */
	public void setDecisionverificaFolioCerrado(boolean ab_b)
	{
		ib_decisionverificaFolioCerrado = ab_b;
	}

	/**
	 * Valida la propiedad decisionverifica folio cerrado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en decisionverifica folio cerrado
	 */
	public boolean isDecisionverificaFolioCerrado()
	{
		return ib_decisionverificaFolioCerrado;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param alllhmso_lllhmso correspondiente al valor del tipo de objeto Collection<Map<String,Object>>
	 */
	public void setDocumentos(Collection<Map<String, Object>> alllhmso_lllhmso)
	{
		illlhmso_documentos = alllhmso_lllhmso;
	}

	/**
	 * Retorna el valor de documentos.
	 *
	 * @return el valor de documentos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Map<String, Object>> getDocumentos()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(illlhmso_documentos))
			illlhmso_documentos = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.DOCUMENTO
				);

		return illlhmso_documentos;
	}

	/**
	 * Sets the documentos vinculado.
	 *
	 * @param acmso_cmso correspondiente al valor del tipo de objeto Collection<Map<String,Object>>
	 */
	public void setDocumentosVinculado(Collection<Map<String, Object>> acmso_cmso)
	{
		icmso_documentosVinculado = acmso_cmso;
	}

	/**
	 * Retorna el valor de documentos vinculado.
	 *
	 * @return el valor de documentos vinculado
	 */
	public Collection<Map<String, Object>> getDocumentosVinculado()
	{
		return icmso_documentosVinculado;
	}

	/**
	 * Modifica el valor de elimina matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad elimina matricula
	 */
	public void setEliminaMatricula(boolean ab_b)
	{
		ib_eliminaMatricula = ab_b;
	}

	/**
	 * Valida la propiedad elimina matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en elimina matricula
	 */
	public boolean isEliminaMatricula()
	{
		if(!(ib_eliminaMatricula))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("eliminarMatricula");

			if(StringUtils.isValidString(tmp))
				ib_eliminaMatricula = tmp.equals("true");
		}

		return ib_eliminaMatricula;
	}

	/**
	 * Modifica el valor de es acto con ejecutoria.
	 *
	 * @param ab_b asigna el valor a la propiedad es acto con ejecutoria
	 */
	public void setEsActoConEjecutoria(boolean ab_b)
	{
		ib_esActoConEjecutoria = ab_b;
	}

	/**
	 * Valida la propiedad es acto con ejecutoria.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es acto con ejecutoria
	 */
	public boolean isEsActoConEjecutoria()
	{
		return ib_esActoConEjecutoria;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad esCertificadosEspeciales por ab_b
	 */
	public void setEsCertificadosEspeciales(boolean ab_b)
	{
		ib_esCertificadosEspeciales = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esCertificadosEspeciales
	 */
	public boolean isEsCertificadosEspeciales()
	{
		return ib_esCertificadosEspeciales;
	}

	/**
	 * Modifica el valor de file.
	 *
	 * @param asc_sc asigna el valor a la propiedad file
	 */
	public void setFile(StreamedContent asc_sc)
	{
		isc_file = asc_sc;
	}

	/**
	 * Retorna el valor de file.
	 *
	 * @return el valor de file
	 */
	public StreamedContent getFile()
	{
		return isc_file;
	}

	/**
	 * Metodo que modifica el valor de la propiedad
	 *
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		if(!StringUtils.isValidString(is_idSolicitud))
			is_idSolicitud = findSolicitudByIdTurnoHistoria(getIdTurnoHistoria());

		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de inserta matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad inserta matricula
	 */
	public void setInsertaMatricula(boolean ab_b)
	{
		ib_insertaMatricula = ab_b;
	}

	/**
	 * Valida la propiedad inserta matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en inserta matricula
	 */
	public boolean isInsertaMatricula()
	{
		if(!(ib_insertaMatricula))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("insertarMatricula");

			if(StringUtils.isValidString(tmp))
				ib_insertaMatricula = tmp.equals("true");
		}

		return ib_insertaMatricula;
	}

	/**
	 * Modifica el valor de justificacion.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna el valor de justificacion.
	 *
	 * @return el valor de justificacion
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * Sets the matriculas.
	 *
	 * @param alllhsmo_lllhsmo correspondiente al valor del tipo de objeto Collection<Map<String,Object>>
	 */
	public void setMatriculas(Collection<Map<String, Object>> alllhsmo_lllhsmo)
	{
		illlhmso_matriculas = alllhsmo_lllhsmo;
	}

	/**
	 * Retorna el valor de matriculas.
	 *
	 * @return el valor de matriculas
	 */
	public Collection<Map<String, Object>> getMatriculas()
	{
		return illlhmso_matriculas;
	}

	/**
	 * Modifica el valor de matriculas actos.
	 *
	 * @param acpaiu_cpaiu asigna el valor a la propiedad matriculas actos
	 */
	public void setMatriculasActos(Collection<PredioActoIU> acpaiu_cpaiu)
	{
		icpaui_cpaui = acpaiu_cpaiu;
	}

	/**
	 * Retorna el valor de matriculas actos.
	 *
	 * @return el valor de matriculas actos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<PredioActoIU> getMatriculasActos()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(icpaui_cpaui))
		{
			Collection<PredioActoIU> lcpaiu_paui;

			lcpaiu_paui = cargarSolicitudMatricula();

			if(CollectionUtils.isValidCollection(lcpaiu_paui))
				icpaui_cpaui = lcpaiu_paui;
			else
				icpaui_cpaui = null;
		}

		return icpaui_cpaui;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad mensajePredioInconsistente por as_s
	 */
	public void setMensajePredioInconsistente(String as_s)
	{
		is_mensajePredioInconsistente = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad mensajePredioInconsistente
	 */
	public String getMensajePredioInconsistente()
	{
		return is_mensajePredioInconsistente;
	}

	/**
	 * Modifica el valor de motivo tramite.
	 *
	 * @param as_s asigna el valor a la propiedad motivo tramite
	 */
	public void setMotivoTramite(String as_s)
	{
		is_motivoTramite = as_s;
	}

	/**
	 * Retorna el valor de motivo tramite.
	 *
	 * @return el valor de motivo tramite
	 */
	public String getMotivoTramite()
	{
		if(!StringUtils.isValidString(is_motivoTramite))
		{
			String ls_motivoTramite = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
					                                                       .getRequest()).getParameter("idMotivo");

			is_motivoTramite = ls_motivoTramite;
		}

		return is_motivoTramite;
	}

	/**
	 * Modifica el valor de observaciones proceso anterior.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones proceso anterior
	 */
	public void setObservacionesProcesoAnterior(String as_s)
	{
		is_observacionesProcesoAnterior = as_s;
	}

	/**
	 * Retorna el valor de observaciones proceso anterior.
	 *
	 * @return el valor de observaciones proceso anterior
	 */
	public String getObservacionesProcesoAnterior()
	{
		return is_observacionesProcesoAnterior;
	}

	/**
	 * Modifica el valor de observaciones verificar folio.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones verificar folio
	 */
	public void setObservacionesVerificarFolio(String as_s)
	{
		is_observacionesVerificarFolio = as_s;
	}

	/**
	 * Retorna el valor de observaciones verificar folio.
	 *
	 * @return el valor de observaciones verificar folio
	 */
	public String getObservacionesVerificarFolio()
	{
		return is_observacionesVerificarFolio;
	}

	/**
	 * Modifica el valor de predio registro.
	 *
	 * @param apr_pr asigna el valor a la propiedad predio registro
	 */
	public void setPredioRegistro(PredioRegistro apr_pr)
	{
		ipr_predioRegistro = apr_pr;
	}

	/**
	 * Retorna el valor de predio registro.
	 *
	 * @return el valor de predio registro
	 */
	public PredioRegistro getPredioRegistro()
	{
		return ipr_predioRegistro;
	}

	/**
	 * Modifica el valor de rango fin.
	 *
	 * @param as_s asigna el valor a la propiedad rango fin
	 */
	public void setRangoFin(String as_s)
	{
		is_rangoFin = as_s;
	}

	/**
	 * Retorna el valor de rango fin.
	 *
	 * @return el valor de rango fin
	 */
	public String getRangoFin()
	{
		return is_rangoFin;
	}

	/**
	 * Modifica el valor de rango inicio.
	 *
	 * @param as_s asigna el valor a la propiedad rango inicio
	 */
	public void setRangoInicio(String as_s)
	{
		is_rangoInicio = as_s;
	}

	/**
	 * Retorna el valor de rango inicio.
	 *
	 * @return el valor de rango inicio
	 */
	public String getRangoInicio()
	{
		return is_rangoInicio;
	}

	/**
	 * Modifica el valor de seccion tipo matricula.
	 *
	 * @param as_s asigna el valor a la propiedad seccion tipo matricula
	 */
	public void setSeccionTipoMatricula(String as_s)
	{
		is_seccionTipoMatricula = as_s;
	}

	/**
	 * Retorna el valor de seccion tipo matricula.
	 *
	 * @return el valor de seccion tipo matricula
	 */
	public String getSeccionTipoMatricula()
	{
		return is_seccionTipoMatricula;
	}

	/**
	 * Modifica el valor de selected matriculas.
	 *
	 * @param alpaiu_lpaiu asigna el valor a la propiedad selected matriculas
	 */
	public void setSelectedMatriculas(List<PredioActoIU> alpaiu_lpaiu)
	{
		ilpaui_selectedMatriculasEliminar = alpaiu_lpaiu;
	}

	/**
	 * Retorna el valor de selected matriculas.
	 *
	 * @return el valor de selected matriculas
	 */
	public List<PredioActoIU> getSelectedMatriculas()
	{
		return ilpaui_selectedMatriculasEliminar;
	}

	/**
	 * Modifica el valor de turno seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad turno seleccionado
	 */
	public void setTurnoSeleccionado(boolean ab_b)
	{
		ib_turnoSeleccionado = ab_b;
	}

	/**
	 * Valida la propiedad turno seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en turno seleccionado
	 */
	public boolean isTurnoSeleccionado()
	{
		return ib_turnoSeleccionado;
	}

	/**
	 * Modifica el valor de turno vinculado.
	 *
	 * @param as_s asigna el valor a la propiedad turno vinculado
	 */
	public void setTurnoVinculado(String as_s)
	{
		is_turnoVinculado = as_s;
	}

	/**
	 * Retorna el valor de turno vinculado.
	 *
	 * @return el valor de turno vinculado
	 */
	public String getTurnoVinculado()
	{
		return is_turnoVinculado;
	}

	/**
	 * Modifica el valor de verifica folio cerrado.
	 *
	 * @param ab_b asigna el valor a la propiedad verifica folio cerrado
	 */
	public void setVerificaFolioCerrado(boolean ab_b)
	{
		ib_verificaFolioCerrado = ab_b;
	}

	/**
	 * Valida la propiedad verifica folio cerrado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en verifica folio cerrado
	 */
	public boolean isVerificaFolioCerrado()
	{
		if(!(ib_verificaFolioCerrado))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("varificarFolioCerrado");

			if(StringUtils.isValidString(tmp))
				ib_verificaFolioCerrado = tmp.equals("true");
		}

		return ib_verificaFolioCerrado;
	}

	/**
	 * Retorna el valor del objeto de byte[].
	 *
	 * @param aba_excel correspondiente al valor del tipo de objeto byte[]
	 * @param as_nameFile correspondiente al valor del tipo de objeto String
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public byte[] FileLoad(byte[] aba_excel, String as_nameFile, String as_userId)
	    throws B2BException, IOException
	{
		try
		{
			if(aba_excel != null)
			{
				byte[] aba_archivo;

				aba_archivo = aba_excel;

				if(aba_archivo != null)
				{
					Sheet    lsh_hoja;
					Workbook lw_libro;
					String   ls_nombreArchivoOriginal;
					ls_nombreArchivoOriginal = as_nameFile;

					if(StringUtils.isValidString(ls_nombreArchivoOriginal))
					{
						InputStream lis_archivoExcel;

						lis_archivoExcel = new ByteArrayInputStream(aba_archivo);

						if(ls_nombreArchivoOriginal.toUpperCase().endsWith(ExtensionCommon.XLS_PUNTO))
							lw_libro = new HSSFWorkbook(lis_archivoExcel);
						else
							lw_libro = new XSSFWorkbook(lis_archivoExcel);

						lsh_hoja = lw_libro.getSheetAt(0);
					}
					else
						throw new B2BException(ErrorKeys.ARCHIVO_NOMBRE);

					if(lsh_hoja != null)
					{
						int li_primeraFila;
						int li_ultimaFila;

						li_primeraFila     = 1;
						li_ultimaFila      = lsh_hoja.getLastRowNum();

						if(!(li_ultimaFila > 1002))
						{
							int li_numcol;

							li_numcol = 3;

							for(int ii_fila = 0; ii_fila <= li_ultimaFila; ii_fila++)
							{
								if(li_primeraFila != 1)
								{
									Row lr_filaActual;

									lr_filaActual = lsh_hoja.getRow(ii_fila);

									if(ExcelUtils.isValidRow(lr_filaActual, li_numcol))
									{
										StringBuilder lsb_mensaje;
										String        ls_nupre;
										Long          ll_numeroMatricula;
										String        ls_circuloRegistral;

										lsb_mensaje             = new StringBuilder();
										ll_numeroMatricula      = null;
										ls_circuloRegistral     = null;
										ls_nupre                = null;

										for(int li_celda = 0; li_celda < li_numcol; li_celda++)
										{
											try
											{
												if(li_celda == 0)
													ls_circuloRegistral = ExcelUtils.validarStringCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.CIRCULO_REGISTRAL, true
														);
												else if(li_celda == 1)
													ll_numeroMatricula = ExcelUtils.validarLongCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.NUMERO_MATRICULA, true
														);
											}
											catch(B2BException ab2be_e)
											{
												lsb_mensaje.append(ab2be_e.getMessage());
											}
										}

										if(!StringUtils.isValidString(lsb_mensaje.toString()))
											lsb_mensaje.append(
											    processCell(
											        ll_numeroMatricula, ls_nupre, ls_circuloRegistral, as_userId
											    )
											);

										{
											CellStyle lcs_estiloCelda;

											lcs_estiloCelda = lw_libro.createCellStyle();

											lcs_estiloCelda.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);

											ExcelUtils.creaCelda(
											    lr_filaActual, li_numcol, lsb_mensaje.toString(), lcs_estiloCelda
											);

											lsh_hoja.autoSizeColumn(li_numcol);
										}

										{
											String ls_matricula;

											ls_matricula = StringUtils.getString(ll_numeroMatricula);

											if(
											    isr_suspensionRemote.validarTramiteSuspension(
												        ls_circuloRegistral, ls_matricula, getUserId(),
												        getLocalIpAddress(), getRemoteIpAddress()
												    )
											)
											{
												CellStyle lcs_estiloCelda;
												Object[]  loa_args;

												lcs_estiloCelda     = lw_libro.createCellStyle();
												loa_args            = new String[1];

												lcs_estiloCelda.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
												loa_args[0] = ls_matricula;

												ExcelUtils.creaCelda(
												    lr_filaActual, li_numcol + 1,
												    getMessages()
													        .getString(
													        MessagesKeys.MATRICULA_TIENE_SUSPENSION, loa_args
													    ), lcs_estiloCelda
												);

												lsh_hoja.autoSizeColumn(li_numcol + 1);
											}
										}
									}
								}

								li_primeraFila++;
							}

							{
								ByteArrayOutputStream lbaos_output;

								lbaos_output = new ByteArrayOutputStream();

								ExcelUtils.write(lw_libro, lbaos_output);

								aba_excel = lbaos_output.toByteArray();
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_LIMITE_REGISTROS);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("FileLoad", lb2be_e);
			throw lb2be_e;
		}

		return aba_excel;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String accionRegresar()
	    throws B2BException
	{
		FacesContext lfc_context;
		String       ls_return;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = NavegacionCommon.DETALLE_ACTO;

		{
			BeanPredioDocumentoActo lbpdab_bean;
			lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
					                                              .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
					);

			if(lbpdab_bean != null)
			{
				lbpdab_bean.setOcultarPaneles(false);
				lbpdab_bean.generarDatosAntSistema();
				lbpdab_bean.generarDatosDocumento();
				lbpdab_bean.obtenerInformacionASEtapa101();
				lbpdab_bean.validarFechaVencimientoActo();
				lbpdab_bean.setMotivoTramite(null);

				lbpdab_bean.getMatriculasRangos();
				lbpdab_bean.getMatriculasIndividuales();
				lbpdab_bean.getMatriculasTmpRangos();
				lbpdab_bean.getMatriculasTmpIndividuales();
			}
		}

		return ls_return;
	}

	/**
	 * Agregar actos individual.
	 *
	 * @param aae_ae correspondiente al valor del tipo de objeto ActionEvent
	 */
	public void agregarActosIndividual(ActionEvent aae_ae)
	{
		try
		{
			Collection<PredioActoIU> lcpaui_tmp;

			lcpaui_tmp = getActosAsociadosGeneral();

			if(!CollectionUtils.isValidCollection(lcpaui_tmp))
				lcpaui_tmp = new ArrayList<PredioActoIU>();
			else
			{
				for(PredioActoIU lpaui_actual : lcpaui_tmp)
				{
					if(lpaui_actual != null)
					{
						String ls_matriculaActual;

						ls_matriculaActual = lpaui_actual.getMatricula();

						if(!StringUtils.isValidString(ls_matriculaActual))
							throw new B2BException(ErrorKeys.ERROR_TABLA_MATRICULA_INGRESADA);
					}
				}
			}

			{
				PredioActoIU lpaui_nuevo;

				lpaui_nuevo = new PredioActoIU(getColumns(isIndVinculado()));

				lcpaui_tmp.add(lpaui_nuevo);
			}

			setActosAsociadosGeneral(lcpaui_tmp);

			if(isEsCertificadosEspeciales())
				setAgregoUnamatriculaCertificados(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarActosIndividual", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Agregar actos masivos.
	 *
	 * @param aae_ae correspondiente al valor del tipo de objeto ActionEvent
	 */
	public void agregarActosMasivos(ActionEvent aae_ae)
	{
		StringBuilder lsb_Errors;

		lsb_Errors = new StringBuilder();

		try
		{
			Collection<PredioActoIU> lcpaui_tmp;
			String                   ls_rangoInicio;
			String                   ls_rangoFin;

			lcpaui_tmp         = getActosAsociadosGeneral();
			ls_rangoInicio     = getRangoInicio();
			ls_rangoFin        = getRangoFin();

			if(StringUtils.isValidString(ls_rangoInicio) && StringUtils.isValidString(ls_rangoFin))
			{
				if(!CollectionUtils.isValidCollection(lcpaui_tmp))
					lcpaui_tmp = new ArrayList<PredioActoIU>();
				else
				{
					for(PredioActoIU lpaui_actual : lcpaui_tmp)
					{
						if(lpaui_actual != null)
						{
							String ls_matriculaActual;

							ls_matriculaActual = lpaui_actual.getMatricula();

							if(!StringUtils.isValidString(ls_matriculaActual))
								throw new B2BException(ErrorKeys.ERROR_TABLA_MATRICULA_INGRESADA);

							if(ls_rangoInicio.equalsIgnoreCase(ls_matriculaActual))
								throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGO_INICIAL);

							if(ls_rangoFin.equalsIgnoreCase(ls_matriculaActual))
								throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGO_FINAL);
						}
					}
				}

				{
					long ll_desde;
					long ll_hasta;

					ll_desde     = NumericUtils.getLong(ls_rangoInicio);
					ll_hasta     = NumericUtils.getLong(ls_rangoFin);

					if(ll_desde > ll_hasta)
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGOS);

					if(ll_desde < 1)
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGO_INICIAL_FORMATO);

					if(ll_hasta < 1)
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGO_FINAL_FORMATO);

					{
						long ll_rangoMaximo;

						ll_rangoMaximo = ll_hasta - ll_desde;

						if(ll_rangoMaximo > 1000)
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGOS_LIMITE);
					}

					{
						CirculoRegistral lcr_circulo;

						lcr_circulo = new CirculoRegistral();

						lcr_circulo.setIdCirculo(getCirculo());

						lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

						if(lcr_circulo != null)
						{
							long ll_contador;

							ll_contador = 0L;

							for(long ll_numeroMatricula = ll_desde; ll_numeroMatricula <= ll_hasta;
								    ll_numeroMatricula++
							)
							{
								String       ls_matriculaConcatenada;
								PredioActoIU lpaui_nuevo;

								lpaui_nuevo = new PredioActoIU(getColumns(isIndVinculado()));

								{
									PredioRegistro lpr_predio;

									lpr_predio = new PredioRegistro();

									String ls_idCirculo;
									ls_idCirculo = lcr_circulo.getIdCirculo();

									lpr_predio.setIdCirculo(ls_idCirculo);
									lpr_predio.setIdMatricula(ll_numeroMatricula);

									lpr_predio = irr_registroRemote.findPredioRegistroById(lpr_predio, getUserId());

									if(lpr_predio != null)
									{
										int li_contarMatriculasIguales;

										boolean lb_matriculaRepetidaTurno;
										lb_matriculaRepetidaTurno = false;

										{
											li_contarMatriculasIguales     = 0;

											lpaui_nuevo     = new PredioActoIU(getColumns(isIndVinculado()));

											ls_matriculaConcatenada = lpr_predio.getIdCirculo() + "-"
												+ lpr_predio.getIdMatricula();
											lpaui_nuevo.setMatricula(ls_matriculaConcatenada);

											{
												DireccionPredio ld_direccion;

												ld_direccion = new DireccionPredio();

												ld_direccion.setIdCirculo(lpr_predio.getIdCirculo());
												ld_direccion.setIdMatricula(
												    NumericUtils.getLongWrapper(lpr_predio.getIdMatricula())
												);

												ld_direccion = irr_registroRemote.findDireccionPredioById(
													    ld_direccion, getUserId()
													);

												if(ld_direccion != null)
													lpaui_nuevo.setDireccion(ld_direccion.getDireccion());
												else
													lsb_Errors.append(
													    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
													    + ls_matriculaConcatenada
													    + getMessages().getString(MessagesKeys.NO_RELACIONA_DIRECCION)
													);
											}

											Collection<Map<String, Object>> llllhm_matriculas;
											llllhm_matriculas = getMatriculas();

											if(llllhm_matriculas != null)
											{
												String ls_numeroMatricula;
												ls_numeroMatricula = StringUtils.getString(ll_numeroMatricula);

												for(Map<String, Object> llhm_data : getMatriculas())
												{
													if(llhm_data != null)
													{
														String ls_circuloMatricula;
														ls_circuloMatricula = llhm_data.get(
															    IdentificadoresCommon.MATRICULA
															).toString();

														String[] las_cirMat;
														las_cirMat = ls_circuloMatricula.split("-");

														String ls_circulo;
														ls_circulo = las_cirMat[0];

														String ls_matricula;
														ls_matricula = las_cirMat[1];

														if(
														    ls_circulo.equals(ls_idCirculo)
															    && ls_numeroMatricula.equals(ls_matricula)
														)
														{
															lb_matriculaRepetidaTurno = true;

															break;
														}
													}
												}
											}

											if(CollectionUtils.isValidCollection(lcpaui_tmp))
											{
												for(PredioActoIU lpaui_actual : lcpaui_tmp)
												{
													if(lpaui_actual != null)
													{
														String ls_matriculaCargada;

														ls_matriculaCargada = lpaui_actual.getMatricula();

														if(
														    ls_matriculaConcatenada.equalsIgnoreCase(
															        ls_matriculaCargada
															    )
														)
															li_contarMatriculasIguales++;
													}
												}
											}

											if(li_contarMatriculasIguales >= 1)
												lsb_Errors.append(
												    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
												    + ls_matriculaConcatenada
												    + getMessages().getString(MessagesKeys.YA_SE_ENCUENTRA_INSERTADA)
												);
											else if(!lb_matriculaRepetidaTurno)
												lcpaui_tmp.add(lpaui_nuevo);
										}

										{
											String ls_estadoPredio;

											ls_estadoPredio = lpr_predio.getIdEstadoPredio();

											if(StringUtils.isValidString(ls_estadoPredio))
											{
												EstadoPredio lep_estado;

												lep_estado = new EstadoPredio();

												lep_estado.setIdEstadoPredio(ls_estadoPredio);

												lep_estado = irr_registroRemote.findEstadoPredioById(
													    lep_estado, getUserId()
													);

												if(lep_estado != null)
												{
													String ls_estado;

													ls_estado = lep_estado.getNombre();

													if(
													    StringUtils.isValidString(ls_estado)
														    && ls_estado.toUpperCase()
														                    .equalsIgnoreCase(
														        EstadoCommon.ESTADO_CERRADO
														    )
													)
														lsb_Errors.append(
														    getMessages()
															        .getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
														    + getCirculo() + "-" + ll_numeroMatricula
														    + getMessages()
															          .getString(
															        MessagesKeys.A_INCLUIR_ESTA_ESTADO_CERRADO
															    )
														);
												}
											}
										}

										{
											String ls_turnoBloqueo;

											ls_turnoBloqueo = lpr_predio.getTurnoBloqueo();

											if(StringUtils.isValidString(ls_turnoBloqueo))
												lsb_Errors.append(
												    getMessages().getString(MessagesKeys.EL_FOLIO_DE_MATRICULA)
												    + getCirculo() + "-" + ll_numeroMatricula
												    + getMessages()
													          .getString(
													        MessagesKeys.SE_ENCUENTRA_BLOQUEADO_CON_EL_TUNRO
													    ) + ls_turnoBloqueo + ". "
												);
										}

										if(lb_matriculaRepetidaTurno)
											lsb_Errors.append(
											    getMessages().getString(MessagesKeys.NO_SE_PUEDE_AGREGAR_LA_MATRICULA)
											    + ll_numeroMatricula
											    + getMessages()
												          .getString(MessagesKeys.PORQUE_YA_SE_ENCUENTRA_RELACIONADA)
											);

										if(
										    StringUtils.getStringNotNull(lpr_predio.getPredioInconsistente())
											               .equalsIgnoreCase(EstadoCommon.S)
										)
										{
											Object[] aoa_messageArgs;
											aoa_messageArgs        = new String[1];
											aoa_messageArgs[0]     = StringUtils.getString(ll_numeroMatricula);

											lsb_Errors.append(
											    getMessages()
												        .getString(MessagesKeys.PREDIO_INCONSISTENTE, aoa_messageArgs)
											);

											lpaui_nuevo.setEsPredioInconsistente(true);
										}
									}
									else
									{
										lsb_Errors.append(
										    getMessages().getString(MessagesKeys.PORQUE_YA_SE_ENCUENTRA_RELACIONADA)
										    + getCirculo() + "-" + ll_numeroMatricula
										    + getMessages().getString(MessagesKeys.NO_ENCONTRADO_NO_EXISTE)
										);
										ll_contador++;
									}
								}
							}

							{
								long ll_rango;

								ll_rango = 0L;

								for(long cont = ll_desde; cont <= ll_hasta; cont++)
									ll_rango++;

								if((ll_rango == ll_contador))
								{
									lsb_Errors = new StringBuilder();

									lsb_Errors.append(
									    getMessages().getString(MessagesKeys.PARA_RANGO_INGRESADO_NO_EXISTEN_PREDIOS)
									);
								}
							}
						}
						else
							throw new B2BException(
							    getMessages().getString(MessagesKeys.EL_CIRCULO_REGISTRAL) + getCirculo()
							    + getMessages().getString(MessagesKeys.NO_ENCONTRADO_NO_EXISTE)
							);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGOS_2);

			setActosAsociadosGeneral(lcpaui_tmp);

			if(getIdEtapa() != EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
				cargarMensajePredioInconsistente();

			if(StringUtils.isValidString(lsb_Errors.toString()))
			{
				addMessage("I", lsb_Errors.toString());
				PrimeFaces.current().ajax().update("fCalifCorrecBasic:globalMsg");
				PrimeFaces.current().ajax().update("confrontacion5:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarActosMasivos", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param apaiu_predioActo correspondiente al valor del tipo de objeto PredioActoIU
	 * @param as_growl actualizar los mensajes
	 * @throws B2BException
	 */
	public void cargarDireccionPredio(PredioActoIU apaiu_predioActo, String as_growl)
	{
		try
		{
			Collection<String> lcs_errores;
			String             ls_matriculaInmobiliaria;
			String             ls_nupre;
			Object             lo_matricula;
			Object             lo_nupre;

			if(apaiu_predioActo == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			lo_matricula                 = apaiu_predioActo.getMatricula();
			lo_nupre                     = apaiu_predioActo.getNupre();
			ls_matriculaInmobiliaria     = (lo_matricula != null) ? (lo_matricula.toString()) : null;
			ls_nupre                     = (lo_nupre != null) ? lo_nupre.toString() : null;

			if(StringUtils.isValidString(ls_matriculaInmobiliaria))
				ls_matriculaInmobiliaria = getCirculo() + "-" + ls_matriculaInmobiliaria;

			lcs_errores = cargarDireccionPredio(
				    apaiu_predioActo, ls_matriculaInmobiliaria, ls_nupre, is_messageIdGrowl
				);

			if(CollectionUtils.isValidCollection(lcs_errores))
			{
				for(String ls_error : lcs_errores)
				{
					if(StringUtils.isValidString(ls_error))
					{
						if(ls_error.contains("ya está registrada para esta solicitud"))
							addMessage(EstadoCommon.W, ls_error);
						else
							addMessage(EstadoCommon.I, ls_error);

						PrimeFaces.current().ajax().update(as_growl);
					}
				}
			}

			{
				Matricula lm_matricula;

				lm_matricula = Matricula.getMatricula(ls_matriculaInmobiliaria);

				Collection<String> lcs_errores2;

				lcs_errores2 = new ArrayList<String>();

				if(lm_matricula != null)
				{
					lcs_errores2 = isr_suspensionRemote.validarTramiteSuspension2(
					    lm_matricula.getCirculo(), StringUtils.getString(lm_matricula.getMatricula()), true, getUserId(),
					    getLocalIpAddress(), getRemoteIpAddress(), lcs_errores2
					);
					PrimeFaces.current().ajax().update(as_growl);
				}

				if(CollectionUtils.isValidCollection(lcs_errores2))
				{
					for(String ls_error : lcs_errores2)
					{
						if(StringUtils.isValidString(ls_error))
						{
							    ls_error = ls_error + " por lo tanto no es posible agregar más matrículas" ;
							    setActosAsociadosGeneral(null);
								throw new B2BException(ls_error);
						}
					}
				}
			}

			if(getIdEtapa() != EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
				cargarMensajePredioInconsistente();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDireccionPredio", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(as_growl);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param apaiu_predioActo correspondiente al valor del tipo de objeto PredioActoIU
	 * @throws B2BException
	 */
	public void cargarDireccionPredio(PredioActoIU apaiu_predioActo)
	    throws B2BException
	{
		cargarDireccionPredio(apaiu_predioActo, is_messageIdGrowl);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param apaiu_predioActo correspondiente al valor del tipo de objeto PredioActoIU
	 * @param as_matricula correspondiente al valor del tipo de objeto String
	 * @param as_nupre correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> cargarDireccionPredio(
	    PredioActoIU apaiu_predioActo, String as_matricula, String as_nupre
	)
	    throws B2BException
	{
		return cargarDireccionPredio(apaiu_predioActo, as_matricula, as_nupre, is_messageIdGrowl);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param apaiu_predioActo correspondiente al valor del tipo de objeto PredioActoIU
	 * @param as_matricula correspondiente al valor del tipo de objeto String
	 * @param as_nupre correspondiente al valor del tipo de objeto String
	 * @param as_idGrowl Correspondiente al valor del id del growl a repintar.
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> cargarDireccionPredio(
	    PredioActoIU apaiu_predioActo, String as_matricula, String as_nupre, String as_idGrowl
	)
	    throws B2BException
	{
		Collection<String> lcs_return;

		lcs_return = new ArrayList<String>();

		try
		{
			String         ls_matriculaInmoviliaria;
			String         ls_nupre;
			String         ls_message;
			String         ls_circulo;
			boolean        lb_matricula;
			boolean        lb_nupre;
			PredioRegistro lpr_predio;

			lpr_predio                   = new PredioRegistro();
			lb_nupre                     = false;
			lb_matricula                 = false;
			ls_matriculaInmoviliaria     = as_matricula;
			ls_nupre                     = as_nupre;
			ls_message                   = "";
			ls_circulo                   = getCirculo();

			lb_matricula     = StringUtils.isValidString(ls_matriculaInmoviliaria);
			lb_nupre         = StringUtils.isValidString(ls_nupre);

			if(lb_matricula || lb_nupre)
			{
				if(lb_matricula)
				{
					Collection<Map<String, Object>> lcmso_matriculas;
					lcmso_matriculas = getMatriculas();

					if(CollectionUtils.isValidCollection(lcmso_matriculas))
					{
						for(Map<String, Object> llhm_data : lcmso_matriculas)
						{
							String ls_circuloMatricula;
							ls_circuloMatricula = llhm_data.get(IdentificadoresCommon.MATRICULA).toString();

							if(ls_circuloMatricula.equals(as_matricula))
							{
								getActosAsociadosGeneral().remove(apaiu_predioActo);
								throw new B2BException(ErrorKeys.ERROR_MATRICULA_REPETIDA);
							}
						}
					}

					if(ls_matriculaInmoviliaria.length() > 3)
					{
						int                      li_contarMatriculasIguales;
						Collection<PredioActoIU> lcpaui_tmp;

						lcpaui_tmp                     = getActosAsociadosGeneral();
						li_contarMatriculasIguales     = 0;

						if(CollectionUtils.isValidCollection(lcpaui_tmp))
						{
							for(PredioActoIU lpaui_actual : lcpaui_tmp)
							{
								if(lpaui_actual != null)
								{
									String ls_matriculaCargada;

									ls_matriculaCargada = lpaui_actual.getMatricula();

									if(
									    ls_matriculaInmoviliaria.equalsIgnoreCase(
										        ls_circulo + "-" + ls_matriculaCargada
										    )
									)
										li_contarMatriculasIguales++;
								}
							}
						}

						if(!ls_matriculaInmoviliaria.contains("-"))
						{
							Object[] aoa_messageArgs = new String[1];
							aoa_messageArgs[0] = ls_matriculaInmoviliaria;
							throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
						}

						if(li_contarMatriculasIguales <= 1)
						{
							int    li_inicio;
							String ls_circuloRegistral;
							String ls_matricula;

							ls_matriculaInmoviliaria     = ls_matriculaInmoviliaria.toUpperCase();

							li_inicio               = ls_matriculaInmoviliaria.lastIndexOf("-");
							ls_circuloRegistral     = ls_matriculaInmoviliaria.substring(0, li_inicio);
							ls_matricula            = ls_matriculaInmoviliaria.substring(
								    li_inicio + 1, ls_matriculaInmoviliaria.length()
								);

							if(
							    StringUtils.isValidString(ls_matricula)
								    && StringUtils.isValidString(ls_circuloRegistral)
							)
							{
								long             ll_matricula;
								CirculoRegistral lcr_circulo;

								lcr_circulo = new CirculoRegistral();
								lcr_circulo.setIdCirculo(ls_circuloRegistral);

								lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

								if(lcr_circulo != null)
								{
									ll_matricula = NumericUtils.getLong(ls_matricula);

									if(NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_matricula), 1))
									{
										lpr_predio.setIdCirculo(lcr_circulo.getIdCirculo());
										lpr_predio.setIdMatricula(ll_matricula);
									}

									else
									{
										Object[] aoa_messageArgs = new String[1];
										aoa_messageArgs[0] = ls_matriculaInmoviliaria;
										throw new B2BException(
										    ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs
										);
									}
								}
								else
									throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_INVALIDO);
							}
							else
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = ls_matriculaInmoviliaria;
								throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
							}
						}
						else
							throw new B2BException(ErrorKeys.MATRICULA_INGRESADA);
					}
					else
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = ls_matriculaInmoviliaria;
						throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
					}
				}

				if(lb_nupre)
					lpr_predio.setNupre(as_nupre);

				if(lpr_predio != null)
				{
					PredioRegistro lpr_predioR;

					lpr_predioR = null;

					lpr_predio.setValidNupre(lb_nupre);
					lpr_predio.setValidMatricula(lb_matricula);

					lpr_predioR = irr_registroRemote.findPredioRegistroById(lpr_predio, getUserId());

					if(lpr_predioR != null)
					{
						DireccionPredio ldp_direccionPredio;
						String          ls_tmpCirculo;
						long            ll_tmpMatricula;
						ldp_direccionPredio     = new DireccionPredio();

						ls_tmpCirculo       = lpr_predioR.getIdCirculo();
						ll_tmpMatricula     = lpr_predioR.getIdMatricula();

						ldp_direccionPredio.setIdCirculo(ls_tmpCirculo);
						ldp_direccionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_tmpMatricula));

						ls_matriculaInmoviliaria     = ls_tmpCirculo + "-" + ll_tmpMatricula;

						ldp_direccionPredio = irr_registroRemote.findDireccionPredioById(
							    ldp_direccionPredio, getUserId()
							);

						if(ldp_direccionPredio != null)
						{
							String ls_address;

							ls_address = ldp_direccionPredio.getDireccion();

							if(StringUtils.isValidString(ls_address))
							{
								Collection<PredioActoIU> lcpaui_cargado;

								lcpaui_cargado = getActosAsociadosGeneral();

								if(CollectionUtils.isValidCollection(lcpaui_cargado))
								{
									for(PredioActoIU lpaui_actual : lcpaui_cargado)
									{
										if(lpaui_actual != null)
										{
											String ls_matriculaCargada;
											String ls_nupreCargado;

											if(lb_nupre)
											{
												ls_nupreCargado = lpaui_actual.getNupre();

												if(ls_nupre.equalsIgnoreCase(ls_nupreCargado))
													lpaui_actual.setDireccion(ls_address);
											}

											if(lb_matricula)
											{
												ls_matriculaCargada = lpaui_actual.getMatricula();

												if(
												    ls_matriculaInmoviliaria.equalsIgnoreCase(
													        ls_circulo + "-" + ls_matriculaCargada
													    )
												)
													lpaui_actual.setDireccion(ls_address);
											}
										}
									}
								}

								setActosAsociadosGeneral(lcpaui_cargado);
							}
						}

						{
							String ls_estadoPredio;

							ls_estadoPredio = lpr_predioR.getIdEstadoPredio();

							if(StringUtils.isValidString(ls_estadoPredio))
							{
								EstadoPredio lep_estado;

								lep_estado = new EstadoPredio();

								lep_estado.setIdEstadoPredio(ls_estadoPredio);

								lep_estado = irr_registroRemote.findEstadoPredioById(lep_estado, getUserId());

								if(lep_estado != null)
								{
									String ls_estado;

									ls_estado = lep_estado.getNombre();

									if(
									    StringUtils.isValidString(ls_estado)
										    && ls_estado.toUpperCase().equalsIgnoreCase(EstadoCommon.ESTADO_CERRADO)
									)
									{
										String ls_error;
										ls_error = (getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
											+ ls_matriculaInmoviliaria
											+ getMessages().getString(MessagesKeys.A_INCLUIR_ESTA_ESTADO_CERRADO));

										if(StringUtils.isValidString(ls_error))
											lcs_return.add(ls_error);
									}
								}
							}
						}

						{
							String ls_turnoBloqueo;

							ls_turnoBloqueo = lpr_predioR.getTurnoBloqueo();

							if(StringUtils.isValidString(ls_turnoBloqueo))
							{
								Collection<String> lcs_turnosBloqueo;

								lcs_turnosBloqueo = irr_registroRemote.findTurnosBloqueoPredio(lpr_predioR);

								if(CollectionUtils.isValidCollection(lcs_turnosBloqueo))
								{
									String ls_turnosBloqueo;
									String ls_error;

									ls_turnosBloqueo     = "";
									ls_error             = null;

									for(String ls_turnoTemp : lcs_turnosBloqueo)
										ls_turnosBloqueo = ls_turnosBloqueo + ",\n" + ls_turnoTemp;

									ls_error = ("El folio de matrícula " + as_matricula
										+ " se encuentra bloqueado con los turnos: " + ls_turnosBloqueo);

									if(StringUtils.isValidString(ls_error))
										lcs_return.add(ls_error);
								}
							}
						}

						if(lpr_predioR.getPredioInconsistente().equalsIgnoreCase(EstadoCommon.S))
						{
							lcs_return.add(
							    "El predio con matrícula inmobiliaria " + as_matricula + " es un predio inconsistente."
							);

							if(apaiu_predioActo != null)
							{
								apaiu_predioActo.setEsPredioInconsistente(true);

								apaiu_predioActo.setIdCirculo(lpr_predioR.getIdCirculo());
							}
						}

						{
							long ll_idEtapa;

							ll_idEtapa = (apaiu_predioActo != null) ? apaiu_predioActo.getIdEtapa()
								                                    : ConstanteCommon.DEFAULT_ID;

							if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    || (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
							)
							{
								String ls_predioInconsistente;

								ls_predioInconsistente = lpr_predioR.getPredioInconsistente();

								if(
								    StringUtils.isValidString(ls_predioInconsistente)
									    && ls_predioInconsistente.equalsIgnoreCase(EstadoCommon.S)
								)
								{
									Object[] loa_object;

									loa_object     = new String[1];

									loa_object[0] = lpr_predioR.getIdCirculo() + " - " + lpr_predioR.getIdMatricula();

									addMessage(MessagesKeys.PREDIO_INCONSISTENTE, loa_object);
									actualizarComponente(as_idGrowl);
								}
							}
						}
					}
					else
					{
						Collection<PredioActoIU> lcpaui_cargado;

						lcpaui_cargado = getActosAsociadosGeneral();

						if(CollectionUtils.isValidCollection(lcpaui_cargado))
						{
							for(PredioActoIU lpaui_actual : lcpaui_cargado)
							{
								if(lpaui_actual != null)
								{
									String ls_matriculaCargada;
									String ls_nupreCargada;

									ls_matriculaCargada     = ls_circulo + "-" + lpaui_actual.getMatricula();
									ls_nupreCargada         = lpaui_actual.getNupre();

									if(
									    (lb_matricula
										    && StringUtils.getStringNotNull(ls_matriculaInmoviliaria)
										                      .equalsIgnoreCase(ls_matriculaCargada))
										    || (lb_nupre
										    && StringUtils.getStringNotNull(ls_nupre).equalsIgnoreCase(ls_nupreCargada))
									)
									{
										lpaui_actual.setDireccion(null);
										lpaui_actual.setCantidad(NumericUtils.getLongWrapper(0L));
										lpaui_actual.setCertificadoAsociado(false);

										{
											List<ColumnModel> lcm_lcm;

											lcm_lcm = getColumns(isIndVinculado());

											if(CollectionUtils.isValidCollection(lcm_lcm))
											{
												Map<String, Boolean> lmsb_actos;

												lmsb_actos = new HashMap<String, Boolean>(lpaui_actual.getActos());

												for(ColumnModel lcm_tmp : lcm_lcm)
												{
													if(lcm_tmp != null)
													{
														boolean lb_actoAsociado;
														String  ls_idActo;

														lb_actoAsociado     = false;
														ls_idActo           = lcm_tmp.getIdActo();

														if(StringUtils.isValidString(ls_idActo))
															lb_actoAsociado = BooleanUtils.getBooleanValue(
																    lmsb_actos.get(ls_idActo)
																);

														if(lb_actoAsociado)
															lmsb_actos.replace(ls_idActo, Boolean.FALSE);
													}
												}

												lpaui_actual.setActos(lmsb_actos);
											}
										}
									}
								}
							}
						}

						setActosAsociadosGeneral(lcpaui_cargado);

						if(lb_nupre && lb_matricula)
							ls_message = "Matrícula :" + as_matricula + " " + "Con Nupre :" + as_nupre;
						else if(lb_nupre)
							ls_message = "Nupre :" + as_nupre;
						else if(lb_matricula)
							ls_message = "Matrícula :" + as_matricula;

						Object[] aoa_messageArgs = new String[1];

						aoa_messageArgs[0] = ls_message;

						throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_PREDIO, aoa_messageArgs);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			lcs_return.add(lb2be_e.getMessage());
		}

		return lcs_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<PredioActoIU> cargarSolicitudMatricula()
	{
		Collection<PredioActoIU> lcpaiu_paui;

		lcpaiu_paui = new ArrayList<PredioActoIU>();

		try
		{
			SolicitudMatricula     lsm_sm;
			SolicitudMatriculaActo lsma_sma;

			Collection<SolicitudMatricula>     lcsm_sm;
			Collection<SolicitudMatriculaActo> lcsma_sma;

			lsm_sm = new SolicitudMatricula();

			lsm_sm.setIdSolicitud(getIdSolicitud());

			lcsm_sm = irr_calificacionRemote.findSolicitudMatricula(lsm_sm);

			if(CollectionUtils.isValidCollection(lcsm_sm))
			{
				for(SolicitudMatricula lsm_actual : lcsm_sm)
				{
					if(lsm_actual != null)
					{
						if(lsm_actual.getIdCirculo().equalsIgnoreCase(getCirculo()))
						{
							PredioActoIU lpaiu_paiu;
							String       ls_matriculaConcatenada;

							lpaiu_paiu     = new PredioActoIU(getColumns(isIndVinculado()));
							lsma_sma       = new SolicitudMatriculaActo();

							lsma_sma.setIdSolicitud(lsm_actual.getIdSolicitud());
							lsma_sma.setIdCirculo(lsm_actual.getIdCirculo());
							lsma_sma.setIdMatricula(lsm_actual.getIdMatricula());

							{
								ls_matriculaConcatenada = lsm_actual.getIdCirculo() + "-" + lsm_actual.getIdMatricula();

								lpaiu_paiu.setMatricula(ls_matriculaConcatenada);

								{
									DireccionPredio ld_direccion;

									ld_direccion = new DireccionPredio();

									ld_direccion.setIdCirculo(lsm_actual.getIdCirculo());
									ld_direccion.setIdMatricula(
									    NumericUtils.getLongWrapper(lsm_actual.getIdMatricula())
									);

									ld_direccion = irr_registroRemote.findDireccionPredioById(
										    ld_direccion, getUserId()
										);

									if(ld_direccion != null)
										lpaiu_paiu.setDireccion(ld_direccion.getDireccion());
								}
							}

							lcsma_sma = irr_calificacionRemote.findSolicitudMatriculaActo(lsma_sma);

							if(CollectionUtils.isValidCollection(lcsma_sma))
							{
								for(SolicitudMatriculaActo lmpa_actual : lcsma_sma)
								{
									if(lmpa_actual != null)
										lpaiu_paiu.changeActoIU(lmpa_actual);
								}
							}

							lcpaiu_paui.add(lpaiu_paiu);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarSolicitudMatricula", lb2be_e);
		}

		return lcpaiu_paui;
	}

	/** {@inheritdoc} */
	public void clear()
	{
		setAgregoUnamatriculaCertificados(false);
		setActosAsociadosGeneral(null);
		setCalificaciones(null);
		setCirculo(null);
		setDatosBasicos(false);
		setEliminaMatricula(false);
		setFile(null);
		setIdSolicitud(null);
		setIdTurnoHistoria(null);
		setInsertaMatricula(false);
		setJustificacion(null);
		setMatriculasActos(null);
		setMotivoTramite(null);
		setPredioRegistro(null);
		setObservacionesVerificarFolio(null);
		setRangoFin(null);
		setRangoInicio(null);
		setSeccionTipoMatricula(null);
		setSelectedMatriculas(null);
		setVerificaFolioCerrado(false);
		setObservacionesProcesoAnterior(null);
		setDocumentos(null);
		setIndVinculado(false);
		setTurnoSeleccionado(false);
		setTurnoVinculado(null);
		setDecisionverificaFolioCerrado(false);
		setDecisionEliminaMatricula(false);
		setApertura(null);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String contar()
	{
		String observaciones;
		char[] arrayChar;
		int    contador;
		String result;

		observaciones     = getJustificacion();
		contador          = 0;

		if(observaciones != null)
		{
			arrayChar     = observaciones.toCharArray();
			contador      = arrayChar.length;
		}

		result = Integer.toString(contador) + "/400";

		return result;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ad_valor correspondiente al valor del tipo de objeto Double
	 * @return devuelve el valor de String
	 */
	public String conversionCientifica(Double ad_valor)
	{
		return conversionCientifica(ad_valor, new DecimalFormat("#,###.00"));
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ad_valor correspondiente al valor del tipo de objeto Double
	 * @param adf_format correspondiente al valor del tipo de objeto DecimalFormat
	 * @return devuelve el valor de String
	 */
	public String conversionCientifica(Double ad_valor, DecimalFormat adf_format)
	{
		String ls_conversion;

		ls_conversion = null;

		if((ad_valor != null) && (adf_format != null))
			ls_conversion = adf_format.format(ad_valor);

		return ls_conversion;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param apaui_paui correspondiente al valor del tipo de objeto PredioActoIU
	 * @return devuelve el valor de String
	 */
	public String deleteActoSelected(PredioActoIU apaui_paui)
	{
		getActosAsociadosGeneral().remove(apaui_paui);

		if(getIdEtapa() != EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
			cargarMensajePredioInconsistente();

		if(isEsCertificadosEspeciales())
			setAgregoUnamatriculaCertificados(false);

		return null;
	}

	/**
	 * Detalle turno seleccionado.
	 *
	 * @param arc_item correspondiente al valor del tipo de objeto RegistroCalificacion
	 */
	public void detalleTurnoSeleccionado(RegistroCalificacion arc_item)
	{
		try
		{
			if(arc_item != null)
			{
				String ls_idTurno;

				ls_idTurno = arc_item.getTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					Long ll_idTurnoHistoria;

					ll_idTurnoHistoria = arc_item.getIdTurnoHistoria();

					if(NumericUtils.isValidLong(ll_idTurnoHistoria))
					{
						setTurnoVinculado(ls_idTurno);
						setTurnoSeleccionado(true);
						setDocumentosVinculado(
						    irr_calificacionRemote.findPredioDocumentoByTurno(
						        getUserId(), ll_idTurnoHistoria.toString(), IdentificadoresCommon.DOCUMENTO
						    )
						);

						setCalificaciones(arc_item.getDataConfrontacion());
					}
				}
				else
					throw new B2BException(ErrorKeys.TURNO_SELECCIONADO_INVALIDO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("detalleTurnoSeleccionado", lb2be_e);
			addMessage(lb2be_e);
			setTurnoSeleccionado(false);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String enviarCalifTemp()
	    throws B2BException
	{
		PrimeFaces lpf_pf;
		String     ls_return;

		lpf_pf        = PrimeFaces.current();
		ls_return     = null;

		try
		{
			FacesContext lfc_context;
			String       ls_idDialogdesasociar;
			String       ls_idDialogVerificarFolio;
			boolean      lb_revision;
			boolean      lb_vinculado;

			lfc_context                   = FacesContext.getCurrentInstance();
			ls_idDialogdesasociar         = "dlgDesasociarMatriculaConfrontacion";
			ls_idDialogVerificarFolio     = "dlgVerificarFolioCerradoConfrontacion";
			lb_revision                   = false;
			lb_vinculado                  = isIndVinculado();

			Collection<ConfrontacionCorrectiva> lccc_ccc;
			lccc_ccc                      = new ArrayList<ConfrontacionCorrectiva>();

			if(lb_vinculado)
			{
				Collection<RegistroCalificacion> lcrc_crc;
				lcrc_crc = getInfoTurnos();

				if(CollectionUtils.isValidCollection(lcrc_crc))
				{
					for(RegistroCalificacion lrc_item : lcrc_crc)
					{
						if(lrc_item != null)
							lccc_ccc.add(
							    validarConfrontacion(
							        lrc_item.getDataConfrontacion(), lrc_item.getIdTurnoHistoria(), lfc_context
							    )
							);
					}
				}
			}
			else
				lccc_ccc.add(
				    validarConfrontacion(
				        getCalificaciones(), NumericUtils.getLongWrapper(getIdTurnoHistoria()), lfc_context
				    )
				);

			if(CollectionUtils.isValidCollection(lccc_ccc))
			{
				boolean lb_editoDatosBasicos;

				lb_editoDatosBasicos = false;

				if(ib_datosBasicos)
				{
					for(ConfrontacionCorrectiva lcc_item : lccc_ccc)
					{
						if(lcc_item != null)
						{
							Calificacion lc_c;

							lc_c = lcc_item.getDataCalificacion();

							if(lc_c != null)
							{
								String ls_estado;

								ls_estado = lc_c.getEstadoModificacion();

								if(
								    StringUtils.isValidString(ls_estado)
									    && ls_estado.equalsIgnoreCase(EstadoCommon.MODIFICADO)
								)
									lb_editoDatosBasicos = true;
							}
						}
					}
				}

				if(ib_datosBasicos && !lb_editoDatosBasicos)
					throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_DATOS_CONTINUAR_PROCESO);

				if(lb_vinculado)
				{
					if(ib_eliminaMatricula && !isDecisionEliminaMatricula())
					{
						lpf_pf.executeScript("PF('" + ls_idDialogdesasociar + "').show();");
						lpf_pf.ajax().update(ls_idDialogdesasociar);
					}
					else if(ib_verificaFolioCerrado && !isDecisionverificaFolioCerrado())
					{
						lpf_pf.executeScript("PF('" + ls_idDialogVerificarFolio + "').show();");
						lpf_pf.ajax().update(ls_idDialogVerificarFolio);
					}

					if(
					    (ib_eliminaMatricula && isDecisionEliminaMatricula())
						    || (ib_verificaFolioCerrado && isDecisionverificaFolioCerrado())
					)
					{
						if(
						    !validateStyles(
							        ":fCalifCorrecBasic:idITAJustificacion", lfc_context, getJustificacion(), true
							    )
						)
						{
							lpf_pf.ajax().update("fCalifCorrecBasic");
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
						}

						lb_revision = true;
					}
					else if(!ib_eliminaMatricula && !ib_verificaFolioCerrado)
						lb_revision = true;
				}
				else
					lb_revision = true;

				if(lb_revision)
				{
					irr_calificacionRemote.saveCheks(lccc_ccc, getUserId(), getRemoteIpAddress());

					BeanCalificacion lbde_bean;

					lbde_bean = (BeanCalificacion)lfc_context.getApplication()
							                                     .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
							);

					if(lbde_bean != null)
					{
						lbde_bean.clear();
						lbde_bean.generarDatosTramiteCantidad();
					}

					clear();

					ls_return = NavegacionCommon.CALIFICACION;
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarCalifTemp", lb2be_e);
			ls_return = null;
			setDecisionEliminaMatricula(false);
			setDecisionverificaFolioCerrado(false);
			addMessage(lb2be_e);
		}

		lpf_pf.ajax().update("fCalifCorrecBasic");

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto Acto
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoActo> findActosByIdSolicitudCirculo(Acto ap_parametros, String as_userId)
	{
		Collection<TipoActo> lcta_actos = null;

		try
		{
			lcta_actos = irr_registroRemote.findActosById(ap_parametros, as_userId, true, false);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findActosByIdSolicitudCirculo", lb2be_e);
			lcta_actos = null;
		}

		return lcta_actos;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_turnoHistoria correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String findObservacionesByIdTurnoHistoria(String as_turnoHistoria)
	{
		String ls_observaciones;

		try
		{
			ls_observaciones = irr_calificacionRemote.findObservacionesByIdTurnoHistoria(as_turnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findObservacionesByIdTurnoHistoria", lb2be_e);
			ls_observaciones = null;
		}

		if(StringUtils.isValidString(ls_observaciones))
			setObservacionesProcesoAnterior(ls_observaciones);

		return ls_observaciones;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_turnoHistoria correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public void validarActo0463()
	    throws B2BException
	{
		if(isInsertaMatricula() || isEliminaMatricula())
			irr_calificacionRemote.validarActo0463(getMatriculas());
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_turnoHistoria correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String findSolicitudByIdTurnoHistoria(String as_turnoHistoria)
	{
		String ls_solicitud;

		try
		{
			ls_solicitud = irr_calificacionRemote.findIdSolicitudByIdTurnoHistoria(as_turnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findSolicitudByIdTurnoHistoria", lb2be_e);
			ls_solicitud = null;
		}

		return ls_solicitud;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param afue_event correspondiente al valor del tipo de objeto FileUploadEvent
	 * @return devuelve el valor de String
	 */
	public String processFile(FileUploadEvent afue_event)
	{
		try
		{
			String       ls_mensaje = null;
			UploadedFile luf_file;

			luf_file                = afue_event.getFile();

			if(luf_file != null)
			{
				if(luf_file.getSize() <= 500000)
				{
					byte[] lba_excelFile;

					lba_excelFile = luf_file.getContents();

					if((lba_excelFile != null) && (luf_file.getFileName() != null))
						lba_excelFile = FileLoad(lba_excelFile, luf_file.getFileName(), getUserId());

					if(lba_excelFile != null)
					{
						String ls_nombreArchivo;

						ls_nombreArchivo = luf_file.getFileName();

						if(StringUtils.isValidString(ls_nombreArchivo))
						{
							InputStream stream = new ByteArrayInputStream(lba_excelFile);
							isc_file = new DefaultStreamedContent(
								    stream, luf_file.getContentType(), "Resultado_del_Cargue.xlsx"
								);
						}
					}
					else
						isc_file = null;
				}
				else
					throw new B2BException(ErrorKeys.ERROR_ARCHIVO_PESO);
			}
			else
				ls_mensaje = MessagesKeys.NO_ENCONTRAR_ARCHIVO_CARGUE_MASIVO;

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = MessagesKeys.PROCESAMINETO_DE_CARGUE_MASIVO_TERMINADO;

			addMessage(ls_mensaje);

			if(getIdEtapa() != EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
				cargarMensajePredioInconsistente();

			PrimeFaces.current().ajax().update("fCalifCorrecBasic:idDtActoAsociadoGeneral");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("processFile", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("processFile", le_e);
			addMessage("E", le_e.getMessage());
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return null;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String regresar()
	    throws B2BException
	{
		String                  ls_return;
		BeanPredioDocumentoActo lbpda_bean;
		FacesContext            lfc_context;

		ls_return       = null;
		lfc_context     = FacesUtils.getFacesContext();
		lbpda_bean      = (BeanPredioDocumentoActo)lfc_context.getApplication()
				                                                  .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
				);

		if(lbpda_bean != null)
		{
			lbpda_bean.setLectura(true);
			lbpda_bean.setConfrontacion(true);
			lbpda_bean.setMotivoTramite(getMotivoTramite());
			lbpda_bean.setDatosBasicos(isDatosBasicos());
			lbpda_bean.setInsertaMatricula(isInsertaMatricula());
			lbpda_bean.setEliminaMatricula(isEliminaMatricula());
			lbpda_bean.setVerificaFolioCerrado(isVerificaFolioCerrado());

			Map<String, Object> lp_predio;

			lp_predio = lbpda_bean.getPredio();

			if(CollectionUtils.isValidCollection(lp_predio))
			{
				String ls_turno;

				ls_turno = StringUtils.getString(lp_predio.get(IdentificadoresCommon.ID_TURNO));

				if(StringUtils.isValidString(ls_turno))
				{
					Turno lt_turno;

					lt_turno = irr_calificacionRemote.findTurno(ls_turno);

					if(lt_turno != null)
					{
						String ls_etapa;

						ls_etapa = StringUtils.getString(lt_turno.getIdEtapaActual());

						if(StringUtils.isValidString(ls_etapa) && ls_etapa.equals("70"))
						{
							ls_return = NavegacionCommon.TESTAMENTOS_EJECUTOR;

							BeanTestamentos lbt_bean;

							lbt_bean = (BeanTestamentos)lfc_context.getApplication()
									                                   .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_TESTAMENTOS, BeanTestamentos.class
									);

							if(lbt_bean != null)
								lbt_bean.generarData(StringUtils.getString(lt_turno.getNir()));
						}
					}
				}
			}
		}

		if(!StringUtils.isValidString(ls_return))
			ls_return = NavegacionCommon.DETALLE_ACTO;

		return ls_return;
	}

	/**
	 * Salvar turno seleccionado.
	 */
	public void salvarTurnoSeleccionado()
	{
		salvarTurnoSeleccionado(null);
	}

	/**
	 * Validar acto ejecutoria.
	 */
	public void validarActoEjecutoria()
	{
		try
		{
			String                          ls_idTipoDoc;
			Collection<Map<String, Object>> lllhmso_datos;

			lllhmso_datos = getDocumentos();

			if(CollectionUtils.isValidCollection(lllhmso_datos))
			{
				Iterator<Map<String, Object>> lim_im;
				boolean                       lb_tipoDocumento;

				lim_im               = lllhmso_datos.iterator();
				lb_tipoDocumento     = false;

				while(lim_im.hasNext() && lb_tipoDocumento)
				{
					Map<String, Object> lhmso_tmp;

					lhmso_tmp = lim_im.next();

					if(CollectionUtils.isValidCollection(lllhmso_datos))
					{
						ls_idTipoDoc = (String)lhmso_tmp.get("ID_TIPO_DOCUMENTO");

						if(StringUtils.isValidString(ls_idTipoDoc))
						{
							lb_tipoDocumento = true;
							setEsActoConEjecutoria(validarActoEjecutoria(is_idForm, ls_idTipoDoc));
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarActoEjecutoria", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Validar checks.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @param ls_check correspondiente al valor del tipo de objeto String
	 */
	public void validarChecks(boolean ab_accion, String ls_check)
	{
		if(StringUtils.isValidString(ls_check))
		{
			Calificacion lc_calificacion;

			lc_calificacion = getCalificaciones();

			if(lc_calificacion != null)
			{
				boolean lb_tipoOficina;
				boolean lb_pais;
				boolean lb_departamento;
				boolean lb_municipio;
				boolean lb_oficinaOrigen;
				boolean lb_fechaEjecutoria;
				String  ls_campoActual;
				String  ls_campos;
				String  ls_mensaje;
				String  ls_mensaje2;

				lb_tipoOficina         = lc_calificacion.isTipoOficina();
				lb_pais                = lc_calificacion.isPais();
				lb_departamento        = lc_calificacion.isDepartamentoOficina();
				lb_municipio           = lc_calificacion.isMunicipioOficina();
				lb_oficinaOrigen       = lc_calificacion.isOficinaOrigen();
				lb_fechaEjecutoria     = lc_calificacion.isFechaEjecutoria();
				ls_campoActual         = "";
				ls_campos              = "";
				ls_mensaje             = "";
				ls_mensaje2            = ", debido a la relación que tiene con los campos: ";

				if(ls_check.equalsIgnoreCase(IdentificadoresCommon.TIPOOFICINA))
				{
					lc_calificacion.setPais(ab_accion);
					lc_calificacion.setDepartamentoOficina(ab_accion);
					lc_calificacion.setMunicipioOficina(ab_accion);
					lc_calificacion.setOficinaOrigen(ab_accion);
				}
				else if(ls_check.equalsIgnoreCase(IdentificadoresCommon.PAIS))
				{
					if(!lb_tipoOficina)
					{
						lc_calificacion.setDepartamentoOficina(ab_accion);
						lc_calificacion.setMunicipioOficina(ab_accion);
						lc_calificacion.setOficinaOrigen(ab_accion);
					}
					else
					{
						ls_mensaje         = "No puede desmarcar el campo ";
						ls_campoActual     = "país";
						ls_campos          = "tipo oficina.";

						ls_mensaje += (ls_campoActual + ls_mensaje2 + ls_campos);
						lc_calificacion.setPais(true);
					}
				}
				else if(ls_check.equalsIgnoreCase(IdentificadoresCommon.DEPARTAMENTO))
				{
					if(!lb_tipoOficina && !lb_pais)
					{
						lc_calificacion.setMunicipioOficina(ab_accion);
						lc_calificacion.setOficinaOrigen(ab_accion);
					}
					else
					{
						ls_mensaje         = "No puede desmarcar el campo ";
						ls_campoActual     = "departamento";

						if(lb_tipoOficina)
							ls_campos += "tipo oficina, ";

						if(lb_pais)
							ls_campos += "país.";

						ls_mensaje += (ls_campoActual + ls_mensaje2 + ls_campos);
						lc_calificacion.setDepartamentoOficina(true);
					}
				}
				else if(ls_check.equalsIgnoreCase(IdentificadoresCommon.MUNICIPIO))
				{
					if(!lb_tipoOficina && !lb_pais && !lb_departamento)
						lc_calificacion.setOficinaOrigen(ab_accion);
					else
					{
						ls_mensaje         = "No puede desmarcar el campo ";
						ls_campoActual     = IdentificadoresCommon.MUNICIPIO;

						if(lb_tipoOficina)
							ls_campos += "tipo oficina, ";

						if(lb_pais)
							ls_campos += "país, ";

						if(lb_departamento)
							ls_campos += "departamento.";

						ls_mensaje += (ls_campoActual + ls_mensaje2 + ls_campos);
						lc_calificacion.setMunicipioOficina(true);
					}
				}
				else if(ls_check.equalsIgnoreCase(IdentificadoresCommon.OFICINAORIGEN))
				{
					if(lb_tipoOficina || lb_pais || lb_departamento || lb_municipio)
					{
						ls_mensaje         = "No puede desmarcar el campo ";
						ls_campoActual     = "oficina origen";

						if(lb_tipoOficina)
							ls_campos += "tipo oficina, ";

						if(lb_pais)
							ls_campos += "país, ";

						if(lb_departamento)
							ls_campos += "departamento, ";

						if(lb_municipio)
							ls_campos += "municipio.";

						ls_mensaje += (ls_campoActual + ls_mensaje2 + ls_campos);
						lc_calificacion.setOficinaOrigen(true);
					}
				}
				else if(ls_check.equalsIgnoreCase(IdentificadoresCommon.TIPODOCUMENTO))
				{
					if(lb_tipoOficina && lb_pais && lb_departamento && lb_municipio && lb_oficinaOrigen)
					{
						ls_mensaje         = "No puede desmarcar el campo ";
						ls_campoActual     = "tipo de documento";

						if(lb_tipoOficina)
							ls_campos += "tipo oficina, ";

						if(lb_pais)
							ls_campos += "país, ";

						if(lb_departamento)
							ls_campos += "departamento, ";

						if(lb_municipio)
							ls_campos += "municipio, ";

						if(isEsActoConEjecutoria())
						{
							if(lb_oficinaOrigen)
								ls_campos += "oficina origen,";

							if(lb_fechaEjecutoria)
								ls_campos += "fecha ejecutoria.";
						}
						else if(lb_oficinaOrigen)
							ls_campos += "oficina origen.";

						ls_mensaje += (ls_campoActual + ls_mensaje2 + ls_campos);
						lc_calificacion.setTipoDocumento(true);
					}
					else
					{
						lc_calificacion.setTipoOficina(ab_accion);
						lc_calificacion.setPais(ab_accion);
						lc_calificacion.setDepartamentoOficina(ab_accion);
						lc_calificacion.setMunicipioOficina(ab_accion);
						lc_calificacion.setOficinaOrigen(ab_accion);

						if(isEsActoConEjecutoria())
							lc_calificacion.setFechaEjecutoria(ab_accion);
					}
				}
				else if(ls_check.equalsIgnoreCase(IdentificadoresCommon.FECHAEJECUTORIA))
					lc_calificacion.setFechaEjecutoria(ab_accion);

				addMessage("I", ls_mensaje);
				PrimeFaces.current().ajax().update("fCalifCorrecBasic:globalMsg");
			}
		}
	}

	/**
	 * Salvar turno seleccionado.
	 *
	 * @param aa_apertura correspondiente al valor del tipo de objeto Apertura
	 */
	protected void salvarTurnoSeleccionado(Apertura aa_apertura)
	{
		try
		{
			Collection<RegistroCalificacion> acrc_infoTurnos;
			String                           ls_turnoVinculado;

			acrc_infoTurnos       = getInfoTurnos();
			ls_turnoVinculado     = getTurnoVinculado();

			if(CollectionUtils.isValidCollection(acrc_infoTurnos))
			{
				for(RegistroCalificacion lrc_item : acrc_infoTurnos)
				{
					if(lrc_item != null)
					{
						String ls_idTurno;

						ls_idTurno = lrc_item.getTurno();

						if(
						    StringUtils.isValidString(ls_turnoVinculado) && StringUtils.isValidString(ls_idTurno)
							    && ls_turnoVinculado.equalsIgnoreCase(ls_idTurno)
						)
						{
							Calificacion lc_c;

							lc_c = getCalificaciones();

							if(lc_c != null)
							{
								if(
								    lc_c.isTipoDocumento() || lc_c.isNumeroDoc() || lc_c.isFechaDoc()
									    || lc_c.isTipoOficina() || lc_c.isPais() || lc_c.isDepartamentoOficina()
									    || lc_c.isMunicipioOficina() || lc_c.isOficinaOrigen()
									    || lc_c.isFechaEjecutoria() || lc_c.isTipoEntidad()
								)
									lrc_item.setEspecificacion(EstadoCommon.MODIFICADO);
								else
									lrc_item.setEspecificacion(EstadoCommon.SIN_MODIFICAR);

								lrc_item.setDataConfrontacion(lc_c);

								if(aa_apertura != null)
								{
									{
										Apertura la_apertura;

										la_apertura = aa_apertura;

										if(la_apertura != null)
										{
											Documento ld_documentoApertura;

											ld_documentoApertura = la_apertura.getDocumento();

											if(ld_documentoApertura != null)
											{
												Date   ld_fechaDocumento;
												Date   ld_fechaEjecutoria;
												String ls_idPais;
												String ls_idDepartamento;
												String ls_idMunicipio;
												String ls_idOficinaOrigen;
												String ls_idTipoDocumento;
												String ls_idNumeroDocumento;

												ld_fechaDocumento        = ld_documentoApertura.getFechaDocumento();
												ld_fechaEjecutoria       = ld_documentoApertura.getFechaEjecutoria();
												ls_idPais                = la_apertura.getIdPais();
												ls_idDepartamento        = la_apertura.getIdDepartamento();
												ls_idMunicipio           = la_apertura.getIdMunicipio();
												ls_idOficinaOrigen       = la_apertura.getIdOficinaOrigen();
												ls_idTipoDocumento       = ld_documentoApertura.getIdTipoDocumento();
												ls_idNumeroDocumento     = ld_documentoApertura.getNumero();

												if(!StringUtils.isValidString(ls_idPais))
													throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

												if(!StringUtils.isValidString(ls_idTipoDocumento))
													throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO);

												if(!StringUtils.isValidString(ls_idNumeroDocumento))
													throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

												if(ld_fechaDocumento == null)
												{
													Object[] aoa_messageArgs = new String[1];
													aoa_messageArgs[0] = " Documento ";
													throw new B2BException(
													    ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs
													);
												}

												if(ld_fechaDocumento.after(new Date()))
												{
													Object[] aoa_messageArgs = new String[1];
													aoa_messageArgs[0] = " del documento ";
													throw new B2BException(
													    ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs
													);
												}

												if(!StringUtils.isValidString(ls_idDepartamento))
												{
													Object[] aoa_messageArgs = new String[1];
													aoa_messageArgs[0] = " un Departamento ";
													throw new B2BException(
													    ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs
													);
												}

												if(!StringUtils.isValidString(ls_idMunicipio))
												{
													Object[] aoa_messageArgs = new String[1];
													aoa_messageArgs[0] = " un municipio ";
													throw new B2BException(
													    ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs
													);
												}

												if(!StringUtils.isValidString(ls_idOficinaOrigen))
												{
													Object[] aoa_messageArgs = new String[1];
													aoa_messageArgs[0] = " la oficina origen ";
													throw new B2BException(
													    ErrorKeys.ERROR_SIN_SELECCION_COMBOS, aoa_messageArgs
													);
												}

												if((ld_fechaEjecutoria == null) && aa_apertura.isEsActoConEjecutoria())
												{
													Object[] aoa_messageArgs = new String[1];
													aoa_messageArgs[0] = " ejecutoria ";
													throw new B2BException(
													    ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs
													);
												}
											}
										}
									}

									lrc_item.setApertura(aa_apertura);
								}

								setCalificaciones(null);
								setTurnoSeleccionado(false);
								setTurnoVinculado(null);
								setApertura(null);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_TURNO_VINCULADO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarTurnoSeleccionado", lb2be_e);
			addMessage(lb2be_e);
			setTurnoSeleccionado(true);
		}
	}

	/**
	 * Validar acto ejecutoria.
	 *
	 * @param as_idform correspondiente al valor del tipo de objeto String
	 * @param ls_idTipoDoc correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	protected boolean validarActoEjecutoria(String as_idform, String ls_idTipoDoc)
	{
		boolean lb_isActoEjecutoria;

		lb_isActoEjecutoria = false;

		try
		{
			if(StringUtils.isValidString(ls_idTipoDoc))
				lb_isActoEjecutoria = irr_registroRemote.validarActoEjecutoria(ls_idTipoDoc);

			if(lb_isActoEjecutoria)
				getCalificaciones().setFechaEjecutoria(lb_isActoEjecutoria);

			PrimeFaces.current().ajax().update(as_idform);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lb_isActoEjecutoria;
	}

	/**
	 * Método para cargar mensaje si el predio es inconsistente
	 */
	private void cargarMensajePredioInconsistente()
	{
		String ls_matriculas;
		ls_matriculas = null;

		Collection<PredioActoIU> lcpaiu_prediosActos;
		lcpaiu_prediosActos     = getActosAsociadosGeneral();

		ls_matriculas = cargarMensajePredioInconsistentePredio(lcpaiu_prediosActos, ls_matriculas, true);

		if(StringUtils.isValidString(ls_matriculas))
		{
			Object[] aoa_messageArgs;

			aoa_messageArgs     = new String[1];

			aoa_messageArgs[0] = ls_matriculas;

			setMensajePredioInconsistente(
			    getMessages().getString(
			        MessagesKeys.SOLICITUD_TIENE_MATRICULAS_EN_ESTADO_INCONSISTENTE, aoa_messageArgs
			    )
			);
		}
		else
			setMensajePredioInconsistente(null);

		actualizarComponente(is_idForm);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param al_numeroMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_nupre correspondiente al valor del tipo de objeto String
	 * @param as_circuloRegistral correspondiente al valor del tipo de objeto String
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String processCell(Long al_numeroMatricula, String as_nupre, String as_circuloRegistral, String as_userId)
	    throws B2BException
	{
		boolean       lb_validNupre;
		StringBuilder lsb_msj;

		lb_validNupre     = StringUtils.isValidString(as_nupre);
		lsb_msj           = new StringBuilder();

		try
		{
			{
				Long                            ll_tmp;
				Collection<Map<String, Object>> llllhm_matriculas;

				ll_tmp = al_numeroMatricula;

				if(!NumericUtils.isValidLong(ll_tmp))
					lsb_msj.append(getMessages().getString(MessagesKeys.NUMERO_METRICULA_INVALIDO));

				llllhm_matriculas = getMatriculas();

				if(llllhm_matriculas != null)
				{
					String ls_numeroMatricula;
					ls_numeroMatricula = StringUtils.getString(ll_tmp);

					for(Map<String, Object> llhm_data : getMatriculas())
					{
						if(llhm_data != null)
						{
							String ls_circuloMatricula;
							ls_circuloMatricula = llhm_data.get(IdentificadoresCommon.MATRICULA).toString();

							String[] las_cirMat;
							las_cirMat = ls_circuloMatricula.split("-");

							String ls_circulo;
							ls_circulo = las_cirMat[0];

							String ls_matricula;
							ls_matricula = las_cirMat[1];

							if(ls_circulo.equals(as_circuloRegistral) && ls_numeroMatricula.equals(ls_matricula))
							{
								lsb_msj.append(
								    getMessages().getString(MessagesKeys.NO_SE_PUEDE_AGREGAR_MATRICULA_RELACIONADA)
								);

								break;
							}
						}
					}
				}
			}

			if(lb_validNupre && ExpresionRegular.getExpresionRegular().contieneCaracteresEspeciales(as_nupre))
				lsb_msj.append(getMessages().getString(MessagesKeys.NUPRE_CONTIENE_CARACTERES_ESPECIALES));

			{
				String ls_tmp;

				ls_tmp = as_circuloRegistral;

				if(!StringUtils.isValidString(ls_tmp))
					lsb_msj.append(getMessages().getString(MessagesKeys.EL_CIRCULO_REGISTRAL_ES_INVALIDO));
				else
				{
					if(!ls_tmp.contentEquals(getCirculo()))
						lsb_msj.append(
						    getMessages().getString(MessagesKeys.EL_CIRCULO_REGISTRAL) + ls_tmp
						    + getMessages().getString(MessagesKeys.NO_PERTENECE_AL_CIRCULO_DEL_TURNO)
						);
				}
			}

			if(!StringUtils.isValidString(lsb_msj.toString()))
			{
				CirculoRegistral lcr_circulo;

				lcr_circulo = new CirculoRegistral();

				lcr_circulo.setIdCirculo(as_circuloRegistral);

				lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

				if(lcr_circulo != null)
				{
					PredioRegistro lpr_predio;

					lpr_predio = new PredioRegistro();

					lpr_predio.setIdCirculo(lcr_circulo.getIdCirculo());
					lpr_predio.setIdMatricula(NumericUtils.getLong(al_numeroMatricula));
					lpr_predio.setNupre(as_nupre);
					lpr_predio.setValidNupre(lb_validNupre);
					lpr_predio.setValidMatricula(true);

					lpr_predio = irr_registroRemote.findPredioRegistroById(lpr_predio, getUserId());

					if(lpr_predio != null)
					{
						int                      li_contarMatriculasIguales;
						String                   ls_matriculaConcatenada;
						PredioActoIU             lpaui_nuevo;
						Collection<PredioActoIU> lcpaui_tmp;

						lcpaui_tmp = getActosAsociadosGeneral();

						if(!CollectionUtils.isValidCollection(lcpaui_tmp))
							lcpaui_tmp = new ArrayList<PredioActoIU>();

						{
							li_contarMatriculasIguales     = 0;

							lpaui_nuevo     = new PredioActoIU(getColumns(isIndVinculado()));

							ls_matriculaConcatenada = as_circuloRegistral + "-" + al_numeroMatricula;
							lpaui_nuevo.setMatricula(ls_matriculaConcatenada);

							{
								DireccionPredio ld_direccion;

								ld_direccion = new DireccionPredio();

								ld_direccion.setIdCirculo(lpr_predio.getIdCirculo());
								ld_direccion.setIdMatricula(NumericUtils.getLongWrapper(lpr_predio.getIdMatricula()));

								ld_direccion = irr_registroRemote.findDireccionPredioById(ld_direccion, getUserId());

								if(ld_direccion != null)
									lpaui_nuevo.setDireccion(ld_direccion.getDireccion());
								else
									lsb_msj.append(
									    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
									    + ls_matriculaConcatenada
									    + getMessages().getString(MessagesKeys.NO_RELACIONA_DIRECCION)
									);
							}

							if(CollectionUtils.isValidCollection(lcpaui_tmp))
							{
								for(PredioActoIU lpaui_actual : lcpaui_tmp)
								{
									if(lpaui_actual != null)
									{
										String ls_matriculaCargada;

										ls_matriculaCargada = lpaui_actual.getMatricula();

										if(ls_matriculaConcatenada.equalsIgnoreCase(ls_matriculaCargada))
											li_contarMatriculasIguales++;
									}
								}
							}

							if((li_contarMatriculasIguales < 1))
							{
								lcpaui_tmp.add(lpaui_nuevo);
								lsb_msj.append(
								    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
								    + ls_matriculaConcatenada
								    + getMessages().getString(MessagesKeys.INSERTADA_CORRECTAMENTE)
								);
								setActosAsociadosGeneral(lcpaui_tmp);

								{
									String ls_estadoPredio;

									ls_estadoPredio = lpr_predio.getIdEstadoPredio();

									if(StringUtils.isValidString(ls_estadoPredio))
									{
										EstadoPredio lep_estado;

										lep_estado = new EstadoPredio();

										lep_estado.setIdEstadoPredio(ls_estadoPredio);

										lep_estado = irr_registroRemote.findEstadoPredioById(lep_estado, getUserId());

										if(lep_estado != null)
										{
											String ls_estado;

											ls_estado = lep_estado.getNombre();

											if(
											    StringUtils.isValidString(ls_estado)
												    && ls_estado.toUpperCase()
												                    .equalsIgnoreCase(EstadoCommon.ESTADO_CERRADO)
											)
												lsb_msj.append(
												    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
												    + ls_matriculaConcatenada
												    + getMessages().getString(
												        MessagesKeys.A_INCLUIR_ESTA_ESTADO_CERRADO
												    )
												);
										}
									}
								}

								{
									String ls_turnoBloqueo;

									ls_turnoBloqueo = lpr_predio.getTurnoBloqueo();

									if(StringUtils.isValidString(ls_turnoBloqueo))
										lsb_msj.append(
										    getMessages().getString(MessagesKeys.EL_FOLIO_DE_MATRICULA)
										    + ls_matriculaConcatenada
										    + getMessages().getString(MessagesKeys.SE_ENCUENTRA_BLOQUEADO_CON_EL_TUNRO)
										    + ls_turnoBloqueo + ". "
										);
								}

								if(
								    StringUtils.getStringNotNull(lpr_predio.getPredioInconsistente())
									               .equalsIgnoreCase(EstadoCommon.S)
								)
								{
									Object[] aoa_messageArgs;
									aoa_messageArgs        = new String[1];
									aoa_messageArgs[0]     = ls_matriculaConcatenada;

									lsb_msj.append(
									    getMessages().getString(MessagesKeys.PREDIO_INCONSISTENTE, aoa_messageArgs)
									);

									lpaui_nuevo.setEsPredioInconsistente(true);
								}
							}
							else
								lsb_msj.append(
								    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
								    + ls_matriculaConcatenada
								    + getMessages().getString(MessagesKeys.YA_SE_ENCUENTRA_INSERTADA)
								);
						}
					}
					else
						lsb_msj.append(
						    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA) + as_circuloRegistral + "-"
						    + al_numeroMatricula + getMessages().getString(MessagesKeys.NO_ENCONTRADO_NO_EXISTE)
						);
				}
				else
					lsb_msj.append(
					    getMessages().getString(MessagesKeys.EL_CIRCULO_REGISTRAL) + as_circuloRegistral
					    + getMessages().getString(MessagesKeys.NO_ENCONTRADO_NO_EXISTE)
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("processCell", lb2be_e);
			throw lb2be_e;
		}

		return lsb_msj.toString();
	}

	/**
	 * Retorna el valor del objeto de ConfrontacionCorrectiva.
	 *
	 * @param ac_data correspondiente al valor del tipo de objeto Calificacion
	 * @param al_idTurnoHistoria correspondiente al valor del tipo de objeto Long
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @return devuelve el valor de ConfrontacionCorrectiva
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private ConfrontacionCorrectiva validarConfrontacion(
	    Calificacion ac_data, Long al_idTurnoHistoria, FacesContext afc_context
	)
	    throws B2BException
	{
		ConfrontacionCorrectiva lcc_cc;

		lcc_cc = null;

		try
		{
			Calificacion                          lc_data;
			Collection<TmpSolicitudMatricula>     lctsm_datosBandejaPrediosInsertar;
			Collection<TmpSolicitudMatricula>     lctsm_datosBandejaPrediosEliminar;
			Collection<TmpSolicitudMatriculaActo> lctsma_datosBandejaActosInsertar;
			Collection<TmpSolicitudMatriculaActo> lctsma_datosBandejaActosEliminar;
			Map<String, Object>                   lmso_tabs;
			String                                ls_justificacion;
			String                                ls_verificarFolio;

			lc_data                               = ac_data;
			lctsm_datosBandejaPrediosInsertar     = new ArrayList<TmpSolicitudMatricula>();
			lctsm_datosBandejaPrediosEliminar     = new ArrayList<TmpSolicitudMatricula>();
			lctsma_datosBandejaActosInsertar      = new ArrayList<TmpSolicitudMatriculaActo>();
			lctsma_datosBandejaActosEliminar      = new ArrayList<TmpSolicitudMatriculaActo>();
			lmso_tabs                             = new HashMap<String, Object>();
			ls_justificacion                      = getJustificacion();
			ls_verificarFolio                     = getObservacionesVerificarFolio();

			if(!validateStyles(":fCalifCorrecBasic:idITAJustificacion", afc_context, ls_justificacion, true))
			{
				PrimeFaces.current().ajax().update("fCalifCorrecBasic");
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
			}
			else
				lc_data.setJustificacion(ls_justificacion);

			if(ib_datosBasicos)
			{
				if(StringUtils.isValidString(getMotivoTramite()))
				{
					lc_data.setIdTurnoHistoria(al_idTurnoHistoria);

					if(lc_data != null)
					{
						boolean lb_edito;

						lb_edito = false;

						if(lc_data.isTipoDocumento())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.TIPO_DOCUMENTO, Boolean.TRUE);
						}

						if(lc_data.isNumeroDoc())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.NUMERO_DOCUMENTO, Boolean.TRUE);
						}

						if(lc_data.isFechaDoc())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.FECHA_DOCUMENTO, Boolean.TRUE);
						}

						if(lc_data.isTipoOficina())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.TIPO_OFICINA, Boolean.TRUE);
						}

						if(lc_data.isPais())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.PAIS_OFICINA, Boolean.TRUE);
						}

						if(lc_data.isDepartamentoOficina())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.DEPARTAMENTO_OFICINA, Boolean.TRUE);
						}

						if(lc_data.isMunicipioOficina())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.MUNICIPIO_OFICINA, Boolean.TRUE);
						}

						if(lc_data.isOficinaOrigen())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.OFICINA_ORIGEN, Boolean.TRUE);
						}

						if(lc_data.isFechaEjecutoria())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.FECHA_EJECUTORIA, Boolean.TRUE);
						}

						if(lc_data.isTipoEntidad())
						{
							lb_edito = true;
							lmso_tabs.put(IdentificadoresCommon.TIPO_ENTIDAD, Boolean.TRUE);
						}

						if(lb_edito)
							lc_data.setEstadoModificacion(EstadoCommon.MODIFICADO);

						lmso_tabs.put(IdentificadoresCommon.CORREGIR_DATOS_BASICOS, Boolean.TRUE);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_CAMPOS_ENVIAR_CONFRONTADOR);
			}

			if(ib_insertaMatricula)
			{
				if(!CollectionUtils.isValidCollection(getActosAsociadosGeneral()))
					throw new B2BException(ErrorKeys.ERROR_SIN_RELACION_MATRICULAS_ACTOS_ASOCIADOS);
				else
					lmso_tabs.put(IdentificadoresCommon.INSERTAR_MATRICULA, Boolean.TRUE);

				{
					Collection<PredioActoIU> lcpaui_bandeja;
					String                   ls_accion;
					TurnoHistoria            lth_turno;

					ls_accion          = EstadoCommon.INACTIVO;
					lcpaui_bandeja     = getActosAsociadosGeneral();

					lth_turno = irr_calificacionRemote.findTurnoHistoria(
						    al_idTurnoHistoria, NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_CALIFICACION)
						);

					if(lth_turno != null)
					{
						String ls_solicitud;

						ls_solicitud = lth_turno.getIdSolicitud();

						if(!StringUtils.isValidString(ls_solicitud))
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = lth_turno.getIdTurno();
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD_TURNO, loa_messageArgs);
						}

						if(CollectionUtils.isValidCollection(lcpaui_bandeja))
						{
							long ll_cantidadCertificadosSolicitud;

							ll_cantidadCertificadosSolicitud = 0L;

							for(PredioActoIU lpaui_actual : lcpaui_bandeja)
							{
								if(lpaui_actual != null)
								{
									TmpSolicitudMatricula lsm_matricula;
									String                ls_matricula;

									lsm_matricula = new TmpSolicitudMatricula();

									String ls_temp;
									ls_temp = lpaui_actual.getMatricula();

									if(ls_temp.contains("-"))
										ls_matricula = ls_temp;
									else
										ls_matricula = getCirculo() + "-" + ls_temp;

									if(StringUtils.isValidString(ls_matricula))
									{
										String ls_circuloRegistral;
										long   ll_matricula;

										if(!ls_matricula.contains("-"))
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = ls_matricula;
											throw new B2BException(
											    ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs
											);
										}

										int li_inicio = 0;

										li_inicio     = ls_matricula.lastIndexOf("-");

										ll_matricula     = NumericUtils.getLong(
											    ls_matricula.substring(li_inicio + 1, ls_matricula.length())
											);

										ls_circuloRegistral = ls_matricula.substring(0, li_inicio);

										{
											boolean lb_certificadoAsociado;

											lb_certificadoAsociado = false;

											CirculoRegistral lcr_circulo;

											lcr_circulo = new CirculoRegistral();

											lcr_circulo.setIdCirculo(ls_circuloRegistral);

											lcr_circulo = irr_registroRemote.findCirculoRegistralById(
												    lcr_circulo, getUserId()
												);

											if(lcr_circulo != null)
											{
												PredioRegistro lpr_predio;

												lpr_predio = new PredioRegistro();

												lpr_predio.setIdCirculo(lcr_circulo.getIdCirculo());
												lpr_predio.setIdMatricula(NumericUtils.getLong(ll_matricula));

												lpr_predio = irr_registroRemote.findPredioRegistroById(
													    lpr_predio, getUserId()
													);

												if(lpr_predio != null)
												{
													lsm_matricula.setIdMatricula(ll_matricula);
													lsm_matricula.setIdCirculo(ls_circuloRegistral);
													lsm_matricula.setIdSolicitud(ls_solicitud);
													lb_certificadoAsociado = lpaui_actual.isCertificadoAsociado();

													lsm_matricula.setExpedirCertificado(
													    lb_certificadoAsociado ? EstadoCommon.S : EstadoCommon.N
													);

													if(lb_certificadoAsociado)
													{
														long ll_cantidadCertificados;

														ll_cantidadCertificados     = NumericUtils.getLong(
															    lpaui_actual.getCantidad()
															);

														ll_cantidadCertificadosSolicitud = ll_cantidadCertificadosSolicitud
															+ ll_cantidadCertificados;

														if(
														    NumericUtils.isValidLong(
															        NumericUtils.getLongWrapper(
															            ll_cantidadCertificados
															        ), 1
															    )
														)
															lsm_matricula.setCantidadCertificados(
															    NumericUtils.getBigDecimal(ll_cantidadCertificados)
															);
														else
														{
															Object[] aoa_messageArgs = new String[1];

															aoa_messageArgs[0] = ls_matricula;

															throw new B2BException(
															    ErrorKeys.ERROR_PARA_MATRICULA_CANTIDAD_ACTOS,
															    aoa_messageArgs
															);
														}
													}
													else
														lsm_matricula.setCantidadCertificados(new BigDecimal(0));

													lsm_matricula.setAccion(ls_accion);
													lsm_matricula.setIdUsuario(getUserId());
													lsm_matricula.setIdUsuarioCreacion(getUserId());
													lsm_matricula.setIdUsuarioModificacion(getUserId());
													lsm_matricula.setIpCreacion(getRemoteIpAddress());
													lsm_matricula.setIpModificacion(getRemoteIpAddress());
												}
												else
												{
													Object[] aoa_messageArgs = new String[1];

													aoa_messageArgs[0] = ls_circuloRegistral + "-" + ll_matricula;

													throw new B2BException(
													    ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA, aoa_messageArgs
													);
												}
											}
											else
											{
												Object[] aoa_messageArgs = new String[1];

												aoa_messageArgs[0] = ls_circuloRegistral;

												throw new B2BException(
												    ErrorKeys.CIRCULO_REGISTRAL_NO_ENCONTRADO, aoa_messageArgs
												);
											}
										}

										{
											List<ColumnModel> lcm_lcm;

											lcm_lcm = getColumns(isIndVinculado());

											if(CollectionUtils.isValidCollection(lcm_lcm))
											{
												Map<String, Boolean> lmsb_actos;
												boolean              lb_ActoAsocido;

												lb_ActoAsocido     = false;

												lmsb_actos = lpaui_actual.getActos();

												for(ColumnModel lcm_tmp : lcm_lcm)
												{
													if(lcm_tmp != null)
													{
														boolean lb_actoAsociado;
														String  ls_idActo;

														lb_actoAsociado     = false;
														ls_idActo           = lcm_tmp.getIdActo();

														if(StringUtils.isValidString(ls_idActo))
															lb_actoAsociado = BooleanUtils.getBooleanValue(
																    lmsb_actos.get(ls_idActo)
																);

														if(lb_actoAsociado)
														{
															TmpSolicitudMatriculaActo lsma_matriculaActo;

															lsma_matriculaActo = new TmpSolicitudMatriculaActo();

															lsma_matriculaActo.setIdSolicitud(ls_solicitud);
															lsma_matriculaActo.setIdCirculo(ls_circuloRegistral);
															lsma_matriculaActo.setIdMatricula(ll_matricula);
															lsma_matriculaActo.setIdActo(lcm_tmp.getIdActo());
															lsma_matriculaActo.setIdTurno(lth_turno.getIdTurno());
															lsma_matriculaActo.setAccion(ls_accion);
															lsma_matriculaActo.setUsuario(getUserId());
															lsma_matriculaActo.setIdUsuarioCreacion(getUserId());
															lsma_matriculaActo.setIdUsuarioModificacion(getUserId());
															lsma_matriculaActo.setIpCreacion(getRemoteIpAddress());
															lsma_matriculaActo.setIpModificacion(getRemoteIpAddress());

															lctsma_datosBandejaActosInsertar.add(lsma_matriculaActo);

															lb_ActoAsocido = true;
														}
													}
												}

												if(!lb_ActoAsocido)
												{
													Object[] aoa_messageArgs = new String[1];

													aoa_messageArgs[0] = ls_circuloRegistral + "-" + ll_matricula;

													throw new B2BException(
													    ErrorKeys.DEBE_DIGITAR_ACTOS_ASOCIADOS_MATRICULA,
													    aoa_messageArgs
													);
												}
											}
										}
									}
									else
										throw new B2BException(ErrorKeys.MATRICULAS_FORMATO);

									if(
									    (lsm_matricula != null)
										    && CollectionUtils.isValidCollection(lctsma_datosBandejaActosInsertar)
									)
										lctsm_datosBandejaPrediosInsertar.add(lsm_matricula);
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTOS_ASOCIADOS);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_TURNO_INSERTAR_VALIDO);
				}
			}

			if(ib_eliminaMatricula)
			{
				if(!CollectionUtils.isValidCollection(getSelectedMatriculas()))
					throw new B2BException(ErrorKeys.ERROR_SIN_MATRICULA_A_DESASOCIAR);
				else
					lmso_tabs.put(IdentificadoresCommon.ELIMINAR_MATRICULA, Boolean.TRUE);

				{
					String             ls_accion;
					List<PredioActoIU> llsma_bandeja;

					llsma_bandeja     = getSelectedMatriculas();
					ls_accion         = EstadoCommon.E;

					if(getMatriculasActos().size() == ilpaui_selectedMatriculasEliminar.size())
						throw new B2BException(ErrorKeys.ERROR_NO_ELIMINAR_TODAS_LAS_MATRICULAS);
					else
					{
						if(CollectionUtils.isValidCollection(llsma_bandeja))
						{
							TurnoHistoria lth_turno;

							lth_turno = irr_calificacionRemote.findTurnoHistoria(
								    al_idTurnoHistoria, NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_CALIFICACION)
								);

							if(lth_turno != null)
							{
								for(PredioActoIU lpaui_actual : llsma_bandeja)
								{
									if(lpaui_actual != null)
									{
										TmpSolicitudMatricula lsm_matricula;
										String                ls_matricula;

										lsm_matricula     = new TmpSolicitudMatricula();
										ls_matricula      = lpaui_actual.getMatricula();

										if(StringUtils.isValidString(ls_matricula))
										{
											String ls_circuloRegistral;
											long   ll_matricula;

											if(!ls_matricula.contains("-"))
												throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA);

											int li_inicio = 0;

											li_inicio     = ls_matricula.lastIndexOf("-");

											ll_matricula     = NumericUtils.getLong(
												    ls_matricula.substring(li_inicio + 1, ls_matricula.length())
												);

											ls_circuloRegistral = ls_matricula.substring(0, li_inicio);

											{
												boolean lb_certificadoAsociado;

												lb_certificadoAsociado = false;

												lsm_matricula.setIdMatricula(ll_matricula);
												lsm_matricula.setIdCirculo(ls_circuloRegistral);
												lsm_matricula.setIdSolicitud(lth_turno.getIdSolicitud());

												{
													lb_certificadoAsociado = lpaui_actual.isCertificadoAsociado();

													lsm_matricula.setExpedirCertificado(
													    lb_certificadoAsociado ? EstadoCommon.SI : EstadoCommon.NO
													);
												}

												lsm_matricula.setCantidadCertificados(new BigDecimal(0));
												lsm_matricula.setAccion(ls_accion);
												lsm_matricula.setIdUsuario(getUserId());
												lsm_matricula.setIdUsuarioCreacion(getUserId());
												lsm_matricula.setIpCreacion(getRemoteIpAddress());
												lsm_matricula.setIdUsuarioModificacion(getUserId());
												lsm_matricula.setIpModificacion(getRemoteIpAddress());
											}

											{
												List<ColumnModel> lcm_lcm;

												lcm_lcm = getColumns();

												if(CollectionUtils.isValidCollection(lcm_lcm))
												{
													Map<String, Boolean> lmsb_actos;

													lmsb_actos = lpaui_actual.getActos();

													for(ColumnModel lcm_tmp : lcm_lcm)
													{
														if(lcm_tmp != null)
														{
															boolean lb_actoAsociado;
															String  ls_idActo;

															lb_actoAsociado     = false;
															ls_idActo           = lcm_tmp.getIdActo();

															if(StringUtils.isValidString(ls_idActo))
																lb_actoAsociado = BooleanUtils.getBooleanValue(
																	    lmsb_actos.get(ls_idActo)
																	);

															if(lb_actoAsociado)
															{
																TmpSolicitudMatriculaActo lsma_matriculaActo;

																lsma_matriculaActo = new TmpSolicitudMatriculaActo();

																lsma_matriculaActo.setIdSolicitud(
																    lth_turno.getIdSolicitud()
																);
																lsma_matriculaActo.setIdCirculo(ls_circuloRegistral);
																lsma_matriculaActo.setIdMatricula(ll_matricula);
																lsma_matriculaActo.setIdActo(lcm_tmp.getIdActo());
																lsma_matriculaActo.setIdTurno(lth_turno.getIdTurno());
																lsma_matriculaActo.setAccion(ls_accion);
																lsma_matriculaActo.setUsuario(getUserId());
																lsma_matriculaActo.setIdUsuarioCreacion(getUserId());
																lsma_matriculaActo.setIdUsuarioModificacion(
																    getUserId()
																);

																lctsma_datosBandejaActosEliminar.add(
																    lsma_matriculaActo
																);
															}
														}
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.MATRICULAS_FORMATO);

										if(
										    (lsm_matricula != null)
											    && CollectionUtils.isValidCollection(lctsma_datosBandejaActosEliminar)
										)
											lctsm_datosBandejaPrediosEliminar.add(lsm_matricula);
									}
								}

								if(!CollectionUtils.isValidCollection(lctsma_datosBandejaActosEliminar))
									throw new B2BException(ErrorKeys.ERROR_SIN_SELECCIONAR_PREDIO_A_ELIMINAR);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_TURNO_INSERTAR_VALIDO);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_SELECCIONAR_PREDIO_A_ELIMINAR);
					}
				}
			}

			if(ib_verificaFolioCerrado)
			{
				validateStyles(
				    ":fCalifCorrecBasic:idTab:idObservacionesReabrirFolio", afc_context, ls_verificarFolio, true
				);

				if(!StringUtils.isValidString(ls_verificarFolio))
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_REABRIR_FOLIO);

				lmso_tabs.put(IdentificadoresCommon.VERIFICAR_FOLIO, Boolean.TRUE);
			}

			lc_data.setTabs(lmso_tabs);

			lcc_cc = new ConfrontacionCorrectiva(
				    lctsm_datosBandejaPrediosInsertar, lctsma_datosBandejaActosInsertar,
				    lctsm_datosBandejaPrediosEliminar, lctsma_datosBandejaActosEliminar, lc_data, ls_verificarFolio,
				    al_idTurnoHistoria
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarConfrontacion", lb2be_e);
			throw lb2be_e;
		}

		return lcc_cc;
	}
}
