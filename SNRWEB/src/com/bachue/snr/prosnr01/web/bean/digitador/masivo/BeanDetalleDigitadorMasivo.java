package com.bachue.snr.prosnr01.web.bean.digitador.masivo;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.LinderoRegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanAntSistema;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanDetalleRegistroCalificacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanGestionIntervinientes;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanRegistroCalificacion;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.component.dashboard.Dashboard;

import org.primefaces.component.panel.Panel;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.StreamedContent;

import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.application.Application;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanDetalleDigitadorMasivo.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanDetalleDigitadorMasivo")
public class BeanDetalleDigitadorMasivo extends BeanAntSistema implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleDigitadorMasivo.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4494378986369045192L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fdetalleDigitadorMasivo:globalMsg";

	/** Constante cs_ID_FORMULARIO. */
	private static final String cs_ID_FORMULARIO = "fdetalleDigitadorMasivo";

	/** Propiedad iaaui area UI. */
	private AccAreaUI iaaui_areaUI;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icap matriculas informacion. */
	private Collection<AreaPredio> icap_matriculasInformacion;

	/** Propiedad icdp direcciones. */
	private Collection<DireccionPredio> icdp_direcciones;

	/** Propiedad idb data model. */
	private Dashboard idb_dataModel;

	/** Propiedad iadap detalle area terreno. */
	private DetalleAreaPredio iadap_detalleAreaTerreno;

	/** Propiedad iddp data direccion predio. */
	private DireccionDelPredio iddp_dataDireccionPredio;

	/** Propiedad iddp direccion predio. */
	private DireccionDelPredio iddp_direccionPredio;

	/** Propiedad iddp direccion seleccionada. */
	private DireccionDelPredio iddp_direccionSeleccionada;

	/** Propiedad ilrc lindero registro calificacion. */
	private LinderoRegistroCalificacion ilrc_linderoRegistroCalificacion;

	/** Propiedad ils panels. */
	private List<String> ils_panels;

	/** Propiedad il matricula direccion predio. */
	private Long il_matriculaDireccionPredio;

	/** Propiedad imsb revision matriculas. */
	private Map<String, Boolean> imsb_revisionMatriculas;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irc detalle matricula. */
	private RegistroCalificacion irc_detalleMatricula;

	/** Propiedad irc matriculas. */
	private RegistroCalificacion irc_matriculas;

	/** Propiedad irc matriculas A heredar. */
	private RegistroCalificacion irc_matriculasAHeredar;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad is circulo. */
	private String is_circulo;

	/** Propiedad is circulo direccion predio. */
	private String is_circuloDireccionPredio;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is motivo. */
	private String is_motivo;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/**
	 * Propiedad is observaciones temporales.
	 */
	private String is_observacionesTemporales;

	/** Propiedad is turno. */
	private String is_turno;

	/** Propiedad it turno objeto. */
	private Turno it_turnoObjeto;

	/** Propiedad ib apertura matriculas. */
	private boolean ib_aperturaMatriculas;

	/** Propiedad ib deshabilitar boton confirmar direccion. */
	private boolean ib_deshabilitarBotonConfirmarDireccion;

	/** Propiedad ib division material. */
	private boolean ib_divisionMaterial;

	/** Propiedad ib editor tabla direcciones. */
	private boolean ib_editorTablaDirecciones;

	/** Propiedad ib englobe. */
	private boolean ib_englobe;

	/** Propiedad ib genera segregacion. */
	private boolean ib_generaSegregacion;

	/** Propiedad ib habilitar tabs. */
	private boolean ib_habilitarTabs;

	/** Propiedad ib inscripcion adicional. */
	private boolean ib_inscripcionAdicional;

	/** Propiedad ib modificar direccion predio. */
	private boolean ib_modificarDireccionPredio;

	/** Propiedad ib mostrar dialog. */
	private boolean ib_mostrarDialog;

	/** Propiedad ib mostrar regresar englobes. */
	private boolean ib_mostrarRegresarEnglobes;

	/** Propiedad ib mostrar siguiente englobes. */
	private boolean ib_mostrarSiguienteEnglobes;

	/** Propiedad ib pais inter residencia. */
	private boolean ib_paisInterResidencia;

	/** Propiedad ib pdf generado. */
	private boolean ib_pdfGenerado;

	/** Propiedad ib proceso loteo. */
	private boolean ib_procesoLoteo;

	/** Propiedad ib proceso reloteo. */
	private boolean ib_procesoReloteo;

	/** Propiedad ib salvar. */
	private boolean ib_salvar;

	/**
	 * Modifica el valor de apertura matriculas.
	 *
	 * @param ab_b asigna el valor a la propiedad apertura matriculas
	 */
	public void setAperturaMatriculas(boolean ab_b)
	{
		ib_aperturaMatriculas = ab_b;
	}

	/**
	 * Valida la propiedad apertura matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en apertura matriculas
	 */
	public boolean isAperturaMatriculas()
	{
		return ib_aperturaMatriculas;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/** {@inheritdoc} */
	public void setAreaUI(AccAreaUI aaaui_aaui)
	{
		iaaui_areaUI = aaaui_aaui;
	}

	/** {@inheritdoc} */
	public AccAreaUI getAreaUI()
	{
		if(iaaui_areaUI == null)
			iaaui_areaUI = new AccAreaUI();

		return iaaui_areaUI;
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
		return is_circulo;
	}

	/**
	 * Modifica el valor de circulo direccion predio.
	 *
	 * @param as_s asigna el valor a la propiedad circulo direccion predio
	 */
	public void setCirculoDireccionPredio(String as_s)
	{
		is_circuloDireccionPredio = as_s;
	}

	/**
	 * Retorna el valor de circulo direccion predio.
	 *
	 * @return el valor de circulo direccion predio
	 */
	public String getCirculoDireccionPredio()
	{
		return is_circuloDireccionPredio;
	}

	/**
	 * Modifica el valor de data direccion predio.
	 *
	 * @param addp_ddp asigna el valor a la propiedad data direccion predio
	 */
	public void setDataDireccionPredio(DireccionDelPredio addp_ddp)
	{
		iddp_dataDireccionPredio = addp_ddp;
	}

	/**
	 * Retorna el valor de data direccion predio.
	 *
	 * @return el valor de data direccion predio
	 */
	public DireccionDelPredio getDataDireccionPredio()
	{
		if(iddp_dataDireccionPredio == null)
			iddp_dataDireccionPredio = new DireccionDelPredio();

		return iddp_dataDireccionPredio;
	}

	/** {@inheritdoc} */
	public void setDataModel(Dashboard adb_adb)
	{
		idb_dataModel = adb_adb;
	}

	/** {@inheritdoc} */
	public Dashboard getDataModel()
	{
		return idb_dataModel;
	}

	/**
	 * Modifica el valor de deshabilitar boton confirmar direccion.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar boton confirmar direccion
	 */
	public void setDeshabilitarBotonConfirmarDireccion(boolean ab_b)
	{
		ib_deshabilitarBotonConfirmarDireccion = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar boton confirmar direccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar boton confirmar direccion
	 */
	public boolean isDeshabilitarBotonConfirmarDireccion()
	{
		return ib_deshabilitarBotonConfirmarDireccion;
	}

	/** {@inheritdoc} */
	public void setDetalleAreaTerreno(DetalleAreaPredio aadap_adap)
	{
		iadap_detalleAreaTerreno = aadap_adap;
	}

	/** {@inheritdoc} */
	public DetalleAreaPredio getDetalleAreaTerreno()
	{
		if(iadap_detalleAreaTerreno == null)
			iadap_detalleAreaTerreno = new DetalleAreaPredio();

		return iadap_detalleAreaTerreno;
	}

	/**
	 * Modifica el valor de detalle matricula.
	 *
	 * @param arc_rc asigna el valor a la propiedad detalle matricula
	 */
	public void setDetalleMatricula(RegistroCalificacion arc_rc)
	{
		irc_detalleMatricula = arc_rc;
	}

	/**
	 * Retorna el valor de detalle matricula.
	 *
	 * @return el valor de detalle matricula
	 */
	public RegistroCalificacion getDetalleMatricula()
	{
		if(irc_detalleMatricula == null)
			irc_detalleMatricula = new RegistroCalificacion();

		return irc_detalleMatricula;
	}

	/** {@inheritdoc} */
	public void setDireccionPredio(DireccionDelPredio addp_ddp)
	{
		iddp_direccionPredio = addp_ddp;
	}

	/** {@inheritdoc} */
	public DireccionDelPredio getDireccionPredio()
	{
		if(iddp_direccionPredio == null)
			iddp_direccionPredio = new DireccionDelPredio();

		return iddp_direccionPredio;
	}

	/**
	 * Modifica el valor de direccion seleccionada.
	 *
	 * @param addp_ddp asigna el valor a la propiedad direccion seleccionada
	 */
	public void setDireccionSeleccionada(DireccionDelPredio addp_ddp)
	{
		iddp_direccionSeleccionada = addp_ddp;
	}

	/**
	 * Retorna el valor de direccion seleccionada.
	 *
	 * @return el valor de direccion seleccionada
	 */
	public DireccionDelPredio getDireccionSeleccionada()
	{
		if(iddp_direccionSeleccionada == null)
			iddp_direccionSeleccionada = new DireccionDelPredio();

		return iddp_direccionSeleccionada;
	}

	/**
	 * Modifica el valor de direcciones.
	 *
	 * @param acdp_cdp asigna el valor a la propiedad direcciones
	 */
	public void setDirecciones(Collection<DireccionPredio> acdp_cdp)
	{
		icdp_direcciones = acdp_cdp;
	}

	/**
	 * Retorna el valor de direcciones.
	 *
	 * @return el valor de direcciones
	 */
	public Collection<DireccionPredio> getDirecciones()
	{
		return icdp_direcciones;
	}

	/**
	 * Modifica el valor de division material.
	 *
	 * @param ab_b asigna el valor a la propiedad division material
	 */
	public void setDivisionMaterial(boolean ab_b)
	{
		ib_divisionMaterial = ab_b;
	}

	/**
	 * Valida la propiedad division material.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en division material
	 */
	public boolean isDivisionMaterial()
	{
		return ib_divisionMaterial;
	}

	/**
	 * Modifica el valor de editor tabla direcciones.
	 *
	 * @param ab_b asigna el valor a la propiedad editor tabla direcciones
	 */
	public void setEditorTablaDirecciones(boolean ab_b)
	{
		ib_editorTablaDirecciones = ab_b;
	}

	/**
	 * Valida la propiedad editor tabla direcciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editor tabla direcciones
	 */
	public boolean isEditorTablaDirecciones()
	{
		return ib_editorTablaDirecciones;
	}

	/**
	 * Modifica el valor de englobe.
	 *
	 * @param ab_b asigna el valor a la propiedad englobe
	 */
	public void setEnglobe(boolean ab_b)
	{
		ib_englobe = ab_b;
	}

	/**
	 * Valida la propiedad englobe.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en englobe
	 */
	public boolean isEnglobe()
	{
		return ib_englobe;
	}

	/**
	 * Modifica el valor de genera segregacion.
	 *
	 * @param ab_b asigna el valor a la propiedad genera segregacion
	 */
	public void setGeneraSegregacion(boolean ab_b)
	{
		ib_generaSegregacion = ab_b;
	}

	/**
	 * Valida la propiedad genera segregacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en genera segregacion
	 */
	public boolean isGeneraSegregacion()
	{
		return ib_generaSegregacion;
	}

	/**
	 * Modifica el valor de habilitar tabs.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar tabs
	 */
	public void setHabilitarTabs(boolean ab_b)
	{
		ib_habilitarTabs = ab_b;
	}

	/**
	 * Valida la propiedad habilitar tabs.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar tabs
	 */
	public boolean isHabilitarTabs()
	{
		return ib_habilitarTabs;
	}

	/** {@inheritdoc} */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/** {@inheritdoc} */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/** {@inheritdoc} */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/** {@inheritdoc} */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * Modifica el valor de inscripcion adicional.
	 *
	 * @param ab_b asigna el valor a la propiedad inscripcion adicional
	 */
	public void setInscripcionAdicional(boolean ab_b)
	{
		ib_inscripcionAdicional = ab_b;
	}

	/**
	 * Valida la propiedad inscripcion adicional.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en inscripcion adicional
	 */
	public boolean isInscripcionAdicional()
	{
		return ib_inscripcionAdicional;
	}

	/** {@inheritdoc} */
	public void setLinderoRegistroCalificacion(LinderoRegistroCalificacion alrc_lrc)
	{
		ilrc_linderoRegistroCalificacion = alrc_lrc;
	}

	/** {@inheritdoc} */
	public LinderoRegistroCalificacion getLinderoRegistroCalificacion()
	{
		if(ilrc_linderoRegistroCalificacion == null)
			ilrc_linderoRegistroCalificacion = new LinderoRegistroCalificacion();

		return ilrc_linderoRegistroCalificacion;
	}

	/**
	 * Modifica el valor de matricula direccion predio.
	 *
	 * @param al_l asigna el valor a la propiedad matricula direccion predio
	 */
	public void setMatriculaDireccionPredio(Long al_l)
	{
		il_matriculaDireccionPredio = al_l;
	}

	/**
	 * Retorna el valor de matricula direccion predio.
	 *
	 * @return el valor de matricula direccion predio
	 */
	public Long getMatriculaDireccionPredio()
	{
		return il_matriculaDireccionPredio;
	}

	/** {@inheritdoc} */
	public void setMatriculas(RegistroCalificacion arc_rc)
	{
		irc_matriculas = arc_rc;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion getMatriculas()
	{
		if(irc_matriculas == null)
			irc_matriculas = new RegistroCalificacion();

		return irc_matriculas;
	}

	/**
	 * Modifica el valor de matriculas A heredar.
	 *
	 * @param arc_rc asigna el valor a la propiedad matriculas A heredar
	 */
	public void setMatriculasAHeredar(RegistroCalificacion arc_rc)
	{
		irc_matriculasAHeredar = arc_rc;
	}

	/**
	 * Retorna el valor de matriculas A heredar.
	 *
	 * @return el valor de matriculas A heredar
	 */
	public RegistroCalificacion getMatriculasAHeredar()
	{
		if(irc_matriculasAHeredar == null)
			irc_matriculasAHeredar = new RegistroCalificacion();

		return irc_matriculasAHeredar;
	}

	/**
	 * Modifica el valor de matriculas informacion.
	 *
	 * @param acap_ap asigna el valor a la propiedad matriculas informacion
	 */
	public void setMatriculasInformacion(Collection<AreaPredio> acap_ap)
	{
		icap_matriculasInformacion = acap_ap;
	}

	/**
	 * Retorna el valor de matriculas informacion.
	 *
	 * @return el valor de matriculas informacion
	 */
	public Collection<AreaPredio> getMatriculasInformacion()
	{
		return icap_matriculasInformacion;
	}

	/**
	 * Modifica el valor de modificar direccion predio.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar direccion predio
	 */
	public void setModificarDireccionPredio(boolean ab_b)
	{
		ib_modificarDireccionPredio = ab_b;
	}

	/**
	 * Valida la propiedad modificar direccion predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar direccion predio
	 */
	public boolean isModificarDireccionPredio()
	{
		return ib_modificarDireccionPredio;
	}

	/**
	 * Valida la propiedad mostrar dialog.
	 *
	 */
	public void setMostrarDialog(boolean ab_b)
	{
		ib_mostrarDialog = ab_b;
	}

	/**
	 * Valida la propiedad mostrar dialog.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar dialog
	 */
	public boolean isMostrarDialog()
	{
		return ib_mostrarDialog;
	}

	/**
	 * Modifica el valor de mostrar regresar englobes.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar regresar englobes
	 */
	public void setMostrarRegresarEnglobes(boolean ab_b)
	{
		ib_mostrarRegresarEnglobes = ab_b;
	}

	/**
	 * Valida la propiedad mostrar regresar englobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar regresar englobes
	 */
	public boolean isMostrarRegresarEnglobes()
	{
		return ib_mostrarRegresarEnglobes;
	}

	/**
	 * Modifica el valor de mostrar siguiente englobes.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar siguiente englobes
	 */
	public void setMostrarSiguienteEnglobes(boolean ab_b)
	{
		ib_mostrarSiguienteEnglobes = ab_b;
	}

	/**
	 * Valida la propiedad mostrar siguiente englobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar siguiente englobes
	 */
	public boolean isMostrarSiguienteEnglobes()
	{
		return ib_mostrarSiguienteEnglobes;
	}

	/**
	 * Modifica el valor de motivo.
	 *
	 * @param as_s asigna el valor a la propiedad motivo
	 */
	public void setMotivo(String as_s)
	{
		is_motivo = as_s;
	}

	/**
	 * Retorna el valor de motivo.
	 *
	 * @return el valor de motivo
	 */
	public String getMotivo()
	{
		return is_motivo;
	}

	/** {@inheritdoc} */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/** {@inheritdoc} */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de Observaciones temporales.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad ObservacionesTemporales
	 */
	public void setObservacionesTemporales(String as_s)
	{
		is_observacionesTemporales = as_s;
	}

	/**
	 * Get observaciones temporales.
	 *
	 * @return el valor de string
	 */
	public String getObservacionesTemporales()
	{
		return is_observacionesTemporales;
	}

	/**
	 * Modifica el valor de pais inter residencia.
	 *
	 * @param ab_b asigna el valor a la propiedad pais inter residencia
	 */
	public void setPaisInterResidencia(boolean ab_b)
	{
		ib_paisInterResidencia = ab_b;
	}

	/**
	 * Valida la propiedad pais inter residencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pais inter residencia
	 */
	public boolean isPaisInterResidencia()
	{
		return ib_paisInterResidencia;
	}

	/** {@inheritdoc} */
	public void setPanels(List<String> als_ls)
	{
		ils_panels = als_ls;
	}

	/** {@inheritdoc} */
	public List<String> getPanels()
	{
		return ils_panels;
	}

	/** {@inheritdoc} */
	public void setPdfGenerado(boolean ab_b)
	{
		ib_pdfGenerado = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isPdfGenerado()
	{
		return ib_pdfGenerado;
	}

	/**
	 * Modifica el valor de proceso loteo.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso loteo
	 */
	public void setProcesoLoteo(boolean ab_b)
	{
		ib_procesoLoteo = ab_b;
	}

	/**
	 * Valida la propiedad proceso loteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso loteo
	 */
	public boolean isProcesoLoteo()
	{
		return ib_procesoLoteo;
	}

	/**
	 * Modifica el valor de proceso reloteo.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso reloteo
	 */
	public void setProcesoReloteo(boolean ab_b)
	{
		ib_procesoReloteo = ab_b;
	}

	/**
	 * Valida la propiedad proceso reloteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso reloteo
	 */
	public boolean isProcesoReloteo()
	{
		return ib_procesoReloteo;
	}

	/**
	 * Sets the revision matriculas.
	 *
	 * @param ahmso_hmso correspondiente al valor del tipo de objeto Map<String,Boolean>
	 */
	public void setRevisionMatriculas(Map<String, Boolean> ahmso_hmso)
	{
		imsb_revisionMatriculas = ahmso_hmso;
	}

	/**
	 * Retorna el valor de revision matriculas.
	 *
	 * @return el valor de revision matriculas
	 */
	public Map<String, Boolean> getRevisionMatriculas()
	{
		if(imsb_revisionMatriculas == null)
			imsb_revisionMatriculas = new HashMap<String, Boolean>();

		return imsb_revisionMatriculas;
	}

	/**
	 * Modifica el valor de salvar.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar
	 */
	public void setSalvar(boolean ab_b)
	{
		ib_salvar = ab_b;
	}

	/**
	 * Valida la propiedad salvar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar
	 */
	public boolean isSalvar()
	{
		return ib_salvar;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param as_s asigna el valor a la propiedad turno
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * Modifica el valor de turno objeto.
	 *
	 * @param at_t asigna el valor a la propiedad turno objeto
	 */
	public void setTurnoObjeto(Turno at_t)
	{
		it_turnoObjeto = at_t;
	}

	/**
	 * Retorna el valor de turno objeto.
	 *
	 * @return el valor de turno objeto
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno getTurnoObjeto()
	    throws B2BException
	{
		String ls_idTurno;

		ls_idTurno = getMatriculas().getTurno();

		if(StringUtils.isValidString(ls_idTurno))
			it_turnoObjeto = irr_calificacionRemote.findTurno(ls_idTurno);

		return it_turnoObjeto;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionSalvar()
	{
		String ls_return;
		int    li_totalRevision;
		int    li_totalMatriculas;

		li_totalRevision       = 0;
		li_totalMatriculas     = 0;
		ls_return              = "";

		try
		{
			TurnoHistoria lth_turnoHistoriaTemp;
			boolean       lb_procesoTerminado;

			lb_procesoTerminado = false;

			String ls_observacionesTemp;

			lth_turnoHistoriaTemp     = new TurnoHistoria();
			ls_observacionesTemp      = null;
			lth_turnoHistoriaTemp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lth_turnoHistoriaTemp = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoriaTemp);

			if(lth_turnoHistoriaTemp != null)
			{
				String ls_etadoActividad;

				ls_etadoActividad = lth_turnoHistoriaTemp.getEstadoActividad();

				if(StringUtils.isValidString(ls_etadoActividad))
				{
					lb_procesoTerminado      = ls_etadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA);
					ls_observacionesTemp     = lth_turnoHistoriaTemp.getObservaciones();
				}
			}

			if(lb_procesoTerminado)
			{
				setObservacionesTemporales(ls_observacionesTemp);
				setMostrarDialog(true);

				PrimeFaces.current().executeScript("PF('dlgSuspension').show();");
				PrimeFaces.current().executeScript("fDialogSuspension");
			}
			else
			{
				Long                   ll_idTurnoHistoria;
				String                 ls_idTurno;
				Collection<AreaPredio> lcap_ap;
				AreaPredio             lap_tmp;
				RegistroCalificacion   lrc_tmp;
				lap_tmp     = new AreaPredio();

				ls_return              = NavegacionCommon.TURNOS_DIGITADOR_MASIVO;
				ll_idTurnoHistoria     = NumericUtils.getLongWrapper(getIdTurnoHistoria());
				ls_idTurno             = getTurno();
				li_totalRevision       = getRevisionMatriculas().size();
				lcap_ap                = getMatriculasInformacion();
				lrc_tmp                = getMatriculas();

				if(CollectionUtils.isValidCollection(lcap_ap))
				{
					li_totalMatriculas = lcap_ap.size();
					lap_tmp.setMatriculasInformacion(lcap_ap);
					lap_tmp.setRevisadoDigitador(false);
				}

				{
					if(lrc_tmp != null)
					{
						String ls_matriculaErrada;

						ls_matriculaErrada = irr_calificacionRemote.calcularArea(lrc_tmp, lcap_ap);

						if(StringUtils.isValidString(ls_matriculaErrada))
							throw new B2BException(ErrorKeys.ERROR_SUMA_AREAS);
					}
				}

				irr_calificacionRemote.salvarDigitador(
				    lap_tmp, ll_idTurnoHistoria, ls_idTurno, li_totalRevision, li_totalMatriculas, getUserId(),
				    getLocalIpAddress(), getRemoteIpAddress(), getObservaciones()
				);

				{
					BeanDetalleRegistroCalificacion lbdrc_drc;

					lbdrc_drc = (BeanDetalleRegistroCalificacion)FacesUtils.getFacesContext().getApplication()
							                                                   .evaluateExpressionGet(
							    FacesUtils.getFacesContext(), BeanNameCommon.BEAN_DETALLE_REGISTRO_CALIFICACION,
							    BeanDetalleRegistroCalificacion.class
							);

					lbdrc_drc.setRevisionMatriculas(null);
				}

				{
					BeanDigitadorMasivo lbdm_bean;
					FacesContext        lfc_context;
					Long                ll_idEtapa;

					lfc_context     = FacesUtils.getFacesContext();
					lbdm_bean       = (BeanDigitadorMasivo)lfc_context.getApplication()
							                                              .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_DIGITADOR_MASIVO, BeanDigitadorMasivo.class
							);

					ll_idEtapa = NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO);
					lbdm_bean.setIdEtapa(ll_idEtapa);
					lbdm_bean.clear();
					lbdm_bean.generarData();
				}

				clean();
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Método encargado de actualizar la dirección del predio.
	 *
	 * @param apd_direccion Objeto que contiene la información de la dirección.
	 * @return Objeto que contiene la información de la dirección.
	 * @throws B2BException
	 */
	public DireccionPredioAcc actualizarDireccion(DireccionPredioAcc apd_direccion)
	    throws B2BException
	{
		if(apd_direccion != null)
		{
			Map<String, Map<String, String>> lmsmss_data;

			lmsmss_data = getDatosParametricosDireccion();

			if(CollectionUtils.isValidCollection(lmsmss_data))
			{
				boolean             lb_coordenada;
				boolean             lb_eje;
				boolean             lb_letra;
				Map<String, String> lmss_coordenada;
				Map<String, String> lmss_eje;
				Map<String, String> lmss_letra;

				lmss_coordenada     = lmsmss_data.get(IdentificadoresCommon.COORDENADA);
				lmss_eje            = lmsmss_data.get(IdentificadoresCommon.EJE);
				lmss_letra          = lmsmss_data.get(IdentificadoresCommon.LETRA);
				lb_coordenada       = CollectionUtils.isValidCollection(lmss_coordenada);
				lb_eje              = CollectionUtils.isValidCollection(lmss_eje);
				lb_letra            = CollectionUtils.isValidCollection(lmss_letra);

				if(lb_coordenada || lb_eje || lb_letra)
				{
					StringBuilder lsb_direccionCompleta;
					String        ls_espacio;

					lsb_direccionCompleta     = new StringBuilder();
					ls_espacio                = IdentificadoresCommon.ESPACIO_VACIO;

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdTipoEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getDatoEjePrincipal();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdLetraEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdComplementoEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdCoordenadaEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdTipoEjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getDatoEjeSecundario();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdLetra1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdComplemento1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdCoordenada1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getDato2EjeSecundario();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdLetra2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdComplemento2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdCoordenada2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getIdComplementoDireccion();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = apd_direccion.getComplementoDireccion();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					apd_direccion.setDireccion(lsb_direccionCompleta.toString());
				}
			}
		}

		return apd_direccion;
	}

	/** {@inheritdoc} */
	public void actualizarDireccionPredio()
	{
		try
		{
			DireccionDelPredio lddp_direccion;
			lddp_direccion = getDireccionPredio();

			if((lddp_direccion != null))
			{
				StringBuilder lsb_direccionCompleta;
				lsb_direccionCompleta = new StringBuilder();

				{
					String ls_tipoEje;
					ls_tipoEje = lddp_direccion.getDireccionPredio().getIdTipoEjePrincipal();

					if(StringUtils.isValidString(ls_tipoEje))
					{
						TipoEje lte_tmp;
						lte_tmp = new TipoEje();
						lte_tmp.setIdTipoEje(ls_tipoEje);

						lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

						if(lte_tmp != null)
							lsb_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + " ");
					}
				}

				lsb_direccionCompleta.append(
				    StringUtils.getStringNotNull(lddp_direccion.getDireccionPredio().getDatoEjePrincipal()) + " "
				);

				{
					String ls_tipoEje1;
					ls_tipoEje1 = lddp_direccion.getDireccionPredio().getIdTipoEjeSecundario();

					if(StringUtils.isValidString(ls_tipoEje1))
					{
						TipoEje lte_tmp;
						lte_tmp = new TipoEje();
						lte_tmp.setIdTipoEje(ls_tipoEje1);

						lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

						if(lte_tmp != null)
							lsb_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + " ");
					}
				}

				lsb_direccionCompleta.append(
				    StringUtils.getStringNotNull(lddp_direccion.getDireccionPredio().getDatoEjeSecundario()) + " "
				);
				lsb_direccionCompleta.append(
				    StringUtils.getStringNotNull(lddp_direccion.getDireccionPredio().getComplementoDireccion()) + " "
				);

				{
					String ls_tmp;
					ls_tmp = lsb_direccionCompleta.toString();

					if(StringUtils.isValidString(ls_tmp))
						lddp_direccion.setDireccion(ls_tmp.trim());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de validar el formulario de direccion del predio a ser agregada.
	 */
	public void agregarDireccionDigitadorMasivo()
	{
		try
		{
			BeanDireccion lbd_beanDireccion;
			Direccion     ld_direccion;

			lbd_beanDireccion     = getBeanDireccion();
			ld_direccion          = lbd_beanDireccion.getDireccionPredio();

			if(ld_direccion != null)
			{
				Long                 ll_idMatricula;
				RegistroCalificacion lrc_data;
				String               ls_idCirculo;

				ll_idMatricula     = null;
				ls_idCirculo       = null;
				lrc_data           = getMatriculas();

				if(lrc_data != null)
				{
					ls_idCirculo       = lrc_data.getIdCirculo();
					ll_idMatricula     = lrc_data.getIdMatricula();
				}

				if(!NumericUtils.isValidLong(ll_idMatricula))
					ll_idMatricula = getIdMatricula();

				if(!StringUtils.isValidString(ls_idCirculo))
					ls_idCirculo = getIdCirculo();

				ld_direccion.setIdCirculo(ls_idCirculo);
				ld_direccion.setIdMatricula(ll_idMatricula);
				ld_direccion.setIpCreacion(getLocalIpAddress());
				ld_direccion.setIpModificacion(getLocalIpAddress());
			}

			lbd_beanDireccion.setDireccionPredio(ld_direccion);
			lbd_beanDireccion.agregarDireccionApertura();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarDireccionDigitadorMasivo", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(cs_ID_FORMULARIO);
		}
	}

	/**
	 * Cambiar pais predio.
	 */
	public void cambiarPaisPredio()
	{
		DireccionDelPredio lddp_direccion;

		lddp_direccion = getDireccionPredio();

		if(lddp_direccion != null)
		{
			String ls_pais;
			ls_pais = lddp_direccion.getDatosAntiguoSistema().getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
			{
				setPaisInterResidencia(true);
				lddp_direccion.getDatosAntiguoSistema().setIdDepartamento(null);
			}
			else
				setPaisInterResidencia(false);
		}

		findDepartamentosPredio();
		findMunicipioPredio();
	}

	/**
	 * Cambiar ubicacion englobe.
	 *
	 * @param lrc_item correspondiente al valor del tipo de objeto RegistroCalificacion
	 */
	public void cambiarUbicacionEnglobe(RegistroCalificacion lrc_item)
	{
		if(lrc_item != null)
		{
			String  ls_matricula;
			boolean lb_accion;

			ls_matricula     = lrc_item.getIdCirculo();
			lb_accion        = lrc_item.isMatriculaSeleccionada();

			if(StringUtils.isValidString(ls_matricula) && lb_accion)
			{
				RegistroCalificacion lrc_data;

				lrc_data = getMatriculas();

				if(lrc_data != null)
				{
					Collection<RegistroCalificacion> lcrc_matriculas;

					lcrc_matriculas = lrc_data.getAllMatriculas();

					if(CollectionUtils.isValidCollection(lcrc_matriculas))
					{
						Iterator<RegistroCalificacion> lirc_iterador;

						lirc_iterador = lcrc_matriculas.iterator();

						while(lirc_iterador.hasNext())
						{
							RegistroCalificacion lrc_dato;

							lrc_dato = lirc_iterador.next();

							if(lrc_dato != null)
							{
								String ls_matriculaIterador;

								ls_matriculaIterador = lrc_dato.getIdCirculo();

								if(StringUtils.isValidString(ls_matriculaIterador))
								{
									if(!ls_matriculaIterador.equalsIgnoreCase(ls_matricula))
										lrc_dato.setMatriculaSeleccionada(false);
								}
								else
									lrc_dato.setMatriculaSeleccionada(false);
							}
						}

						lrc_data.setZonaRegistral(lrc_item.getZonaRegistral());
					}
				}
			}
		}
	}

	/** {@inheritdoc} */
	public void cargarComplementacion()
	{
		try
		{
			ComplementacionCalificacion lcc_complementacionCalificacion;
			AccComplementacionPredio    lacp_complementacionPredio;

			lacp_complementacionPredio = new AccComplementacionPredio();

			lacp_complementacionPredio.setIdTurno(getTurno());
			lacp_complementacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));

			lcc_complementacionCalificacion     = getComplementacionCalificacion();
			lacp_complementacionPredio          = irr_calificacionRemote.cargarComplementacion(
				    lacp_complementacionPredio, getUserId()
				);

			if(lacp_complementacionPredio != null)
			{
				ComplementacionPredio lcp_cp;

				lcp_cp = lcc_complementacionCalificacion.getComplementacionPredio();

				lcp_cp.setIdComplementacion(
				    String.valueOf(NumericUtils.getLong(lacp_complementacionPredio.getIdComplementacionOriginal()))
				);
				lcp_cp.setComplementacion(lacp_complementacionPredio.getComplementacion());
				lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.EXISTENTE);
			}
			else
				lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.NUEVA);

			setComplementacionCalificacion(lcc_complementacionCalificacion);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Cargar direccion predio.
	 */
	public void cargarDireccionPredio()
	{
		Collection<RegistroCalificacion> lcrc_matricula;
		DireccionPredio                  ldp_direccionAConsultar;
		DireccionDelPredio               lddl_direccion;

		try
		{
			lddl_direccion              = new DireccionDelPredio();
			ldp_direccionAConsultar     = new DireccionPredio();
			lcrc_matricula              = getMatriculas().getAllMatriculas();

			if(CollectionUtils.isValidCollection(lcrc_matricula))
			{
				Matricula lm_matricula;
				Long      ll_matricula;
				String    ls_circulo;

				lm_matricula = null;

				for(RegistroCalificacion lrc_actual : lcrc_matricula)
				{
					if(lrc_actual != null)
						lm_matricula = Matricula.getMatricula(lrc_actual.getIdCirculo());
				}

				if(lm_matricula != null)
				{
					ll_matricula     = lm_matricula.getMatricula();
					ls_circulo       = lm_matricula.getCirculo();
				}
				else
				{
					ll_matricula     = null;
					ls_circulo       = null;
				}

				ldp_direccionAConsultar.setIdMatricula(ll_matricula);
				ldp_direccionAConsultar.setIdCirculo(ls_circulo);

				lddl_direccion = irr_calificacionRemote.obtenerDireccionPredioDigitadoreMasivo(
					    ldp_direccionAConsultar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setDireccionPredio(lddl_direccion);
				setMatriculaDireccionPredio(ll_matricula);
				setCirculoDireccionPredio(ls_circulo);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Cargar linderos.
	 */
	public void cargarLinderos()
	{
		try
		{
			Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;

			lcap_anotaciones = generarAnotacionesPredioAcc();

			if(CollectionUtils.isValidCollection(lcap_anotaciones))
			{
				Collection<AccLinderoPredio> lclp_linderos;
				LinderoRegistroCalificacion  llrc_linderoRegistroCalificacion;

				lclp_linderos     = new ArrayList<AccLinderoPredio>();

				llrc_linderoRegistroCalificacion = irr_calificacionRemote.cargarLinderosDigitadorMasivo(
					    lcap_anotaciones
					);

				if(llrc_linderoRegistroCalificacion != null)
				{
					for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio laap_actual : lcap_anotaciones)
					{
						if(laap_actual != null)
						{
							AccLinderoPredio lalp_linderoPredio;

							lalp_linderoPredio = new AccLinderoPredio();

							lalp_linderoPredio.setIdCirculo(laap_actual.getIdCirculo());
							lalp_linderoPredio.setIdMatricula(laap_actual.getIdMatricula());

							lclp_linderos.add(lalp_linderoPredio);
						}
					}

					llrc_linderoRegistroCalificacion.setLinderoPredios(lclp_linderos);
					setLinderoRegistroCalificacion(llrc_linderoRegistroCalificacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_idPanels correspondiente al valor del tipo de objeto List<String>
	 * @param as_IdMove correspondiente al valor del tipo de objeto String
	 * @param ai_position correspondiente al valor del tipo de objeto int
	 * @return devuelve el valor de String
	 */
	public String changePosition(List<String> as_idPanels, String as_IdMove, int ai_position)
	{
		String ls_changePosition;
		String ls_tmpValue;
		int    li_i;
		int    li_character;
		String ls_id;
		ls_changePosition     = null;
		li_i                  = 0;

		if(ai_position == -1)
		{
			for(String ls_tmp : as_idPanels)
			{
				if(ls_tmp.equalsIgnoreCase(as_IdMove))
				{
					ls_changePosition = changePosition(as_idPanels, as_IdMove, li_i);

					break;
				}

				li_i = li_i + 1;
			}
		}
		else if(ai_position >= 0)
		{
			ls_tmpValue = String.valueOf(ai_position);

			for(String ls_tmp : as_idPanels)
			{
				ls_id            = ls_tmp;
				li_character     = ls_tmp.indexOf("-");

				ls_tmp = ls_tmp.substring(li_character + 1);

				if(ls_tmp.equalsIgnoreCase(ls_tmpValue))
				{
					ls_changePosition = ls_id;

					break;
				}
			}
		}

		return ls_changePosition;
	}

	/** {@inheritdoc} */
	public void clean()
	{
		setComplementacionCalificacion(null);
		setLinderoRegistroCalificacion(null);
		setModificarDireccionPredio(false);
		setDeshabilitarBotonConfirmarDireccion(false);
		setImagen(null);
		setRevisionMatriculas(null);
		setDetalleMatricula(null);
		findMatriculas(false);
		setSalvar(false);
		setObservaciones(null);
		setHabilitarComplementacion(false);
		setMostrarDialog(false);
	}

	/**
	 * Confirmar direccion predio.
	 */
	public void confirmarDireccionPredio()
	{
		DireccionDelPredio lddp_d;

		lddp_d = getDireccionPredio();

		lddp_d.getDireccionPredio().setIdTurno(getTurno());
		lddp_d.getDireccionPredio().setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
		lddp_d.getDireccionPredio().setIdMatricula(getMatriculaDireccionPredio());
		lddp_d.getDireccionPredio().setIdCirculo(getCirculoDireccionPredio());

		if(isModificarDireccionPredio())
			lddp_d.getDireccionPredio().setIdDireccion("0");

		setDataDireccionPredio(lddp_d);

		addMessage(MessagesKeys.PROCESO_COMPLETADO);

		setModificarDireccionPredio(false);
		setDeshabilitarBotonConfirmarDireccion(true);

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String consultaDetalleMatricula()
	{
		Application                      la_a;
		String                           ls_idCirculo;
		String                           ls_Circulo;
		Long                             ll_matricula;
		int                              li_tmp;
		RegistroCalificacion             lrc_tmp;
		RegistroCalificacion             lrc_detail;
		FacesContext                     lfc_fc;
		DashboardModel                   ldbm_model;
		RegistroCalificacion             lrc_dataDoc;
		Collection<RegistroCalificacion> lcrc_tmp;
		int                              li_column;
		String                           ls_lineaSeparadora;
		String                           ls_idTurno;
		Date                             ls_fechaDoc;
		String                           ls_codDoc;
		String                           ls_notaria;
		String                           ls_municipioDoc;
		HtmlOutputText                   lhot_ot;
		int                              li_columnCount;
		int                              li_idHtso;
		DashboardColumn                  ldbc_dbc;
		int                              li_anotacion;
		HtmlOutputText                   lhot_space;

		try
		{
			lfc_fc             = FacesContext.getCurrentInstance();
			la_a               = lfc_fc.getApplication();
			lrc_detail         = new RegistroCalificacion();
			ls_idCirculo       = FacesUtils.getStringFacesParameter("idCirculo");
			ls_idTurno         = FacesUtils.getStringFacesParameter("idTurno");
			lrc_tmp            = new RegistroCalificacion();
			li_column          = 1;
			li_columnCount     = 1;
			li_anotacion       = 0;
			li_idHtso          = 1;
			ldbc_dbc           = new DefaultDashboardColumn();

			if(StringUtils.isValidString(ls_idCirculo) || StringUtils.isValidString(getCirculo()))
			{
				if(StringUtils.isValidString(ls_idCirculo))
					setCirculo(ls_idCirculo);
				else if(StringUtils.isValidString(getCirculo()))
					ls_idCirculo = getCirculo();

				if(StringUtils.isValidString(ls_idTurno))
					setTurno(ls_idTurno);
				else if(StringUtils.isValidString(getTurno()))
					ls_idTurno = getTurno();

				li_tmp           = ls_idCirculo.indexOf("-");
				ls_Circulo       = ls_idCirculo.substring(0, li_tmp);
				ll_matricula     = NumericUtils.getLongWrapper(ls_idCirculo.substring(li_tmp + 1));

				lrc_tmp.setIdCirculo(ls_Circulo);
				lrc_tmp.setIdMatricula(ll_matricula);
				lrc_tmp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lrc_tmp.setIdUsuario(getUserId());
				lrc_detail             = irr_calificacionRemote.findDetalleMatriculas(lrc_tmp);
				ls_lineaSeparadora     = "<br/>";

				/* Consulta Matriculas */
				if((lrc_detail != null) && CollectionUtils.isValidCollection(lrc_detail.getAllMatriculas()))
				{
					lcrc_tmp     = lrc_detail.getAllMatriculas();

					idb_dataModel = (Dashboard)la_a.createComponent(
						    lfc_fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer"
						);
					idb_dataModel.setId("dashboard");

					ldbm_model = new DefaultDashboardModel();

					ldbm_model.addColumn(ldbc_dbc);

					idb_dataModel.setModel(ldbm_model);

					for(RegistroCalificacion lorc_rc : lcrc_tmp)
					{
						/* Creacion panel para agregar al dashboard */
						Panel lp_panel = (Panel)la_a.createComponent(
							    lfc_fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer"
							);
						ldbc_dbc = ldbm_model.getColumn(li_column % li_columnCount);
						lp_panel.setId("ID_" + lorc_rc.getIdAnotacionPredio() + "-" + li_anotacion);
						li_anotacion = li_anotacion + 1;
						ldbc_dbc.addWidget(lp_panel.getId());
						lp_panel.setHeader(
						    "ANOTACION: Nro." + lorc_rc.getIdAnotacion() + "  " + " Fecha:"
						    + lorc_rc.getFechaRegistro() + "  " + "Radicación:" + ls_idTurno
						);
						lp_panel.setStyle("font-weight: bold;");
						lp_panel.setToggleable(true);
						lp_panel.setStyle("width:1100px;");

						lhot_space = new HtmlOutputText();
						lhot_space.setEscape(false);
						lhot_space.setValue(ls_lineaSeparadora);

						HtmlOutputText lhot_revision = new HtmlOutputText();
						lhot_revision.setEscape(false);
						lhot_revision.setStyle("font-weight: bold;font-size: 16px;");
						lhot_revision.setValue(" Revisión Completa" + ls_lineaSeparadora);

						HtmlSelectBooleanCheckbox locb_loc = new HtmlSelectBooleanCheckbox();
						locb_loc.setValue(new Boolean(lorc_rc.isRevision()));

						lrc_dataDoc         = lorc_rc.getDatosDocumento();
						ls_fechaDoc         = lrc_dataDoc.getFechaDocumento();
						ls_codDoc           = lrc_dataDoc.getCodDocumento();
						ls_notaria          = lrc_dataDoc.getNombreOficina();
						ls_municipioDoc     = lrc_dataDoc.getNombreMunicipio();

						/* Creacion descripcion del campo */
						lhot_ot             = new HtmlOutputText();
						lhot_ot.setEscape(false);
						lhot_ot.setStyle("padding-right:2em;display: inline-block;");
						lhot_ot.setValue(
						    "Doc:" + lorc_rc.getNombreDoc() + " " + ls_codDoc + " DEL " + " " + ls_fechaDoc + "  "
						    + ls_notaria + " " + ls_municipioDoc
						);

						HtmlOutputText lhot_descripcion = new HtmlOutputText();
						lhot_descripcion.setEscape(false);
						lhot_descripcion.setValue("VALOR ACTO $ : ");

						HtmlInputText lhot_valor = new HtmlInputText();
						lhot_valor.setId("val_" + lorc_rc.getIdAnotacionPredio());
						lhot_valor.setMaxlength(18);
						lhot_valor.setTitle("Valor");
						lhot_valor.setValue(lorc_rc.getValor());

						HtmlInputTextarea lhot_comentario = new HtmlInputTextarea();
						lhot_comentario.setId("comen_" + lorc_rc.getIdAnotacionPredio());
						lhot_comentario.setTitle("Comentario");
						lhot_comentario.setStyle("width: 600px;height: 40px;");
						lhot_comentario.setValue(lorc_rc.getComentario());

						/* Creacion especificacion del acto */
						HtmlOutputText lhot_esp = new HtmlOutputText();
						lhot_esp.setId("ot_esp" + li_column);
						lhot_esp.setEscape(false);
						lhot_esp.setStyle("padding-right: 5em;");
						lhot_esp.setValue(
						    ls_lineaSeparadora + "ESPECIFICACION:" + lorc_rc.getNombreGrupoActo() + " "
						    + lorc_rc.getCodActo() + " " + lorc_rc.getNombreActo()
						);

						HtmlOutputText lhot_d = new HtmlOutputText();
						lhot_d.setEscape(false);
						lhot_d.setValue(
						    ls_lineaSeparadora
						    + "PERSONAS QUE INTERVIENEN EN EL ACTO (X-Titular de derecho real de dominio,I-Titular de dominio incompleto)"
						    + ls_lineaSeparadora
						);
						lhot_d.setStyle("font-weight: bold;");

						HtmlOutputText lhot_titles = new HtmlOutputText();
						lhot_titles.setEscape(false);
						lhot_titles.setStyle("width:600px;padding-left:44em;font-weight: bold;");
						lhot_titles.setValue("Propietario - Porcentaje - Calidad Interviniente" + ls_lineaSeparadora);

						/* Adicion de htmlouputtext al panel */
						lp_panel.getChildren().add(lhot_ot);
						lp_panel.getChildren().add(lhot_descripcion);
						lp_panel.getChildren().add(lhot_valor);
						lp_panel.getChildren().add(lhot_esp);
						lp_panel.getChildren().add(lhot_comentario);
						lp_panel.getChildren().add(lhot_d);
						lp_panel.getChildren().add(lhot_titles);

						if(CollectionUtils.isValidCollection(lorc_rc.getAllMatriculas()))
						{
							/* Iteracion de los intervenientes por cada matricula */
							for(RegistroCalificacion lorc_tmp : lorc_rc.getAllMatriculas())
							{
								HtmlOutputText lhot_rol = new HtmlOutputText();
								lhot_rol.setEscape(false);
								lhot_rol.setValue(lorc_tmp.getRolPersona() + " " + " :");
								lp_panel.getChildren().add(lhot_rol);

								HtmlOutputText lhot_porcentaje = new HtmlOutputText();
								lhot_porcentaje.setId("por_" + lorc_tmp.getIdCiudadano());
								lhot_porcentaje.setStyle("padding-right: 7em;display: inline-block;");
								lhot_porcentaje.setTitle("Porcentaje");
								lhot_porcentaje.setValue(lorc_tmp.getPorcentajeStr() + "% " + " - ");

								HtmlOutputText lhot_spac = new HtmlOutputText();
								lhot_spac.setEscape(false);
								lhot_spac.setValue(ls_lineaSeparadora);

								HtmlOutputText lhot_tmp = new HtmlOutputText();
								lhot_tmp.setEscape(false);
								lhot_tmp.setStyle("width:600px;padding-right:8em;display: inline-block;");
								lhot_tmp.setValue(
								    StringUtils.getStringNotNull(lorc_tmp.getNombrePersona()).toString() + " "
								    + lorc_tmp.getTipoDoc() + " " + lorc_tmp.getDocumento()
								);

								HtmlOutputText lhot_propietario = new HtmlOutputText();
								lhot_propietario.setTitle("Propietario");
								lhot_propietario.setStyle("padding-right: 7em;display: inline-block;");
								lhot_propietario.setValue(lorc_tmp.getValuePropietario() + "  " + " -");

								HtmlOutputText lhot_rrr = new HtmlOutputText();
								lhot_rrr.setTitle("Interesada RRR");
								lhot_rrr.setStyle("padding-right: 3em;display: inline-block;");
								lhot_rrr.setValue(lorc_tmp.getInteresadaRrr());

								lp_panel.getChildren().add(lhot_tmp);
								lp_panel.getChildren().add(lhot_propietario);
								lp_panel.getChildren().add(lhot_porcentaje);
								lp_panel.getChildren().add(lhot_rrr);
								lp_panel.getChildren().add(lhot_spac);
								li_idHtso = li_idHtso + 1;
							}
						}

						/* Agregar boton al panel */
						HtmlCommandButton lohmb_button = new HtmlCommandButton();
						lohmb_button.setValue("Guardar Anotación");

						((UICommand)lohmb_button).addActionListener(
						    new ActionListener()
							{
								@Override
								public void processAction(ActionEvent aae_ae)
								{
									List<UIComponent> luic_ui;
									String            ls_idPanel;
									Boolean           lb_b;
									lb_b = new Boolean(false);

									HtmlCommandButton lhcb_cb;

									lhcb_cb     = (HtmlCommandButton)aae_ae.getSource();

									luic_ui        = lhcb_cb.getParent().getChildren();
									ls_idPanel = lhcb_cb.getParent().getId();

									try
									{
										for(UIComponent lui_childPanel : luic_ui)
										{
											if(lui_childPanel instanceof HtmlSelectBooleanCheckbox)
											{
												HtmlSelectBooleanCheckbox loiu_uio = (HtmlSelectBooleanCheckbox)lui_childPanel;
												lb_b = (Boolean)loiu_uio.getValue();

												break;
											}
										}

										if((lb_b != null) && lb_b.booleanValue())
											modificarAnotacion(luic_ui, ls_idPanel, "S");
										else
											throw new B2BException(
											    ErrorKeys.ERROR_ANOTACION_GUARDADA_UNA_VEZ_REVICION_COMPLETA
											);
									}
									catch(B2BException lbe_lbe)
									{
										addMessage(lbe_lbe);
										PrimeFaces.current().ajax().update(is_messageIdGrowl);
									}
								}
							}
						);

						HtmlCommandButton lhcb_updateInt = new HtmlCommandButton();
						lhcb_updateInt.setValue("Modificar Interviente");

						((UICommand)lhcb_updateInt).addActionListener(
						    new ActionListener()
							{
								@Override
								public void processAction(ActionEvent aae_ae)
								{
									String       ls_IdPanel;
									FacesContext lfc_context;
									lfc_context = FacesUtils.getFacesContext();

									BeanGestionIntervinientes lbgi_bean;
									lbgi_bean = (BeanGestionIntervinientes)lfc_context.getApplication()
											                                              .evaluateExpressionGet(
											    lfc_context, BeanNameCommon.BEAN_GESTION_INTERVINIENTES,
											    BeanGestionIntervinientes.class
											);

									HtmlCommandButton lhcb_cb;

									lhcb_cb     = (HtmlCommandButton)aae_ae.getSource();

									ls_IdPanel     = lhcb_cb.getParent().getId();

									ls_IdPanel = generateId(ls_IdPanel);

									lbgi_bean.setIdAnotacionPredio(ls_IdPanel);
									lbgi_bean.setDatatable(true);
									lbgi_bean.dataIntervinientes();

									ExternalContext lec_ec = FacesContext.getCurrentInstance().getExternalContext();

									try
									{
										lec_ec.redirect("gestionIntervinientes.jsf");
									}
									catch(IOException lioe_e)
									{
										clh_LOGGER.error("consultaDetalleMatricula", lioe_e);
									}
								}
							}
						);

						lp_panel.getChildren().add(locb_loc);
						lp_panel.getChildren().add(lhot_revision);
						lp_panel.getChildren().add(lohmb_button);
						lp_panel.getChildren().add(lhcb_updateInt);
						getDataModel().getChildren().add(lp_panel);
						li_column = li_column + 1;
					}

					setPanels(ldbc_dbc.getWidgets());
				}
			}
		}
		catch(B2BException lb2b_2b)
		{
			addMessage(lb2b_2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return NavegacionCommon.DETALLE_REGISTRO;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String detalleMatriculasInformacion(boolean ab_observaciones)
	{
		String ls_return;

		ls_return = null;

		try
		{
			String       ls_idPredioRegistro;
			FacesContext lfc_context;

			lfc_context             = FacesUtils.getFacesContext();
			ls_idPredioRegistro     = FacesUtils.getStringFacesParameter("idPredioRegistro");

			if(StringUtils.isValidString(ls_idPredioRegistro))
			{
				BeanDetalleRegistroCalificacion lbrc_bean;

				lbrc_bean = (BeanDetalleRegistroCalificacion)lfc_context.getApplication()
						                                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_REGISTRO_CALIFICACION,
						    BeanDetalleRegistroCalificacion.class
						);

				if(lbrc_bean != null)
				{
					if(ab_observaciones)
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = obtenerAnteriorTurnoHistoria(getTurno());

						if(lth_turnoHistoria != null)
							lbrc_bean.setObservaciones(lth_turnoHistoria.getObservacionesNoTramite());
					}

					lbrc_bean.setShowWizard(false);
					lbrc_bean.accionVolverView();
					lbrc_bean.setIdPredioRegistro(ls_idPredioRegistro);
					lbrc_bean.setIdTurno(getTurno());
					lbrc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
					lbrc_bean.setEnglobes(isEnglobe());
					lbrc_bean.setGeneraSegregacion(isGeneraSegregacion());
					lbrc_bean.setMatriculasInformacion(getMatriculasInformacion());
					lbrc_bean.setSiguiente(true);
					lbrc_bean.setTerminar(false);
					lbrc_bean.setRegresar(false);
					lbrc_bean.setComplementacionGuardada(false);
					lbrc_bean.setProcesoLoteo(isProcesoLoteo());
					lbrc_bean.setProcesoReloteo(isProcesoReloteo());
					lbrc_bean.setDivisionMaterial(isDivisionMaterial());
					lbrc_bean.setCopiarEditar(false);
					lbrc_bean.setMigaDigitadorMasivo(true);
					lbrc_bean.setNuevaLinderos(false);
					lbrc_bean.setHabilitarComplementacion(false);
					lbrc_bean.setMatriculasParaGenerarComplementacion(null);
					lbrc_bean.setMatriculas(null);
					lbrc_bean.setComplementacionCalificacion(null);
					lbrc_bean.setHabilitarMatriculasComplementacionAnotacion(false);
					lbrc_bean.setComplementacionAnotacion(null);

					{
						boolean lb_b;

						lb_b = false;

						if(isAperturaMatriculas() && isInscripcionAdicional() && isGeneraSegregacion())
							lb_b = true;

						lbrc_bean.setValidarPropiedadHorizontal(lb_b);
					}

					lbrc_bean.cargarMatriculaView(ab_observaciones);
					lbrc_bean.cargarDatosParametricosDireccion();
					lbrc_bean.setEjecucionCorrecciones(false);
					lbrc_bean.setCorreccionAnotacion(false);

					ls_return = NavegacionCommon.DETALLE_REGISTRO_CALIFICACION;
				}
			}
		}
		catch(Exception lb2b_2b)
		{
			addMessage(new B2BException(lb2b_2b.getMessage()));
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}

	/**
	 * Direccion seleccionada.
	 *
	 * @param adp_dp correspondiente al valor del tipo de objeto DireccionPredio
	 * @param arc_rc correspondiente al valor del tipo de objeto RegistroCalificacion
	 */
	public void direccionSeleccionada(DireccionPredio adp_dp, RegistroCalificacion arc_rc)
	{
		RegistroCalificacion lrc_data;

		lrc_data = getMatriculas();

		if((adp_dp != null) && (arc_rc != null) && (lrc_data != null))
		{
			String ls_matricula;
			String ls_idDireccion;

			ls_matricula       = arc_rc.getIdCirculo();
			ls_idDireccion     = adp_dp.getIdDireccion();

			if(StringUtils.isValidString(ls_matricula) && StringUtils.isValidString(ls_idDireccion))
			{
				HashMap<String, DireccionPredio> lhmsdp_direcciones;
				String                           ls_idData;

				lhmsdp_direcciones     = lrc_data.getDireccionesSeleccionadas();
				ls_idData              = ls_matricula + "-" + ls_idDireccion;

				if(!CollectionUtils.isValidCollection(lhmsdp_direcciones))
					lhmsdp_direcciones = new HashMap<String, DireccionPredio>();

				if(adp_dp.isSeleccionado())
				{
					if(!lhmsdp_direcciones.containsKey(ls_idData))
						lhmsdp_direcciones.put(ls_idData, adp_dp);
				}
				else
				{
					if(lhmsdp_direcciones.containsKey(ls_idData))
						lhmsdp_direcciones.remove(ls_idData);
				}

				lrc_data.setDireccionesSeleccionadas(lhmsdp_direcciones);
				setMatriculas(lrc_data);
			}
		}
	}

	/** {@inheritdoc} */
	public Collection<CirculoRegistral> findCirculoRegistral()
	{
		Collection<CirculoRegistral> lccr_circulos;
		lccr_circulos = null;

		try
		{
			RegistroCalificacion lrc_registroCalificacion;

			lrc_registroCalificacion = getMatriculas();

			if(lrc_registroCalificacion != null)
			{
				CirculoRegistral lcr_circulo;

				lcr_circulo = new CirculoRegistral();

				lcr_circulo.setIdCirculo(lrc_registroCalificacion.getIdCirculo());

				lcr_circulo = ipr_parameterRemote.findCirculoRegistralById(
					    lcr_circulo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lcr_circulo != null)
				{
					lccr_circulos = new ArrayList<CirculoRegistral>();
					lccr_circulos.add(lcr_circulo);

					{
						ComplementacionCalificacion lcc_complementacion;

						lcc_complementacion = getComplementacionCalificacion();

						if(lcc_complementacion != null)
						{
							Complementacion lc_complementacion;

							lc_complementacion = lcc_complementacion.getComplementacion();

							if(lc_complementacion != null)
								lc_complementacion.setIdCirculo(lcr_circulo.getIdCirculo());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lccr_circulos = null;
		}

		return lccr_circulos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosPredio()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			DireccionDelPredio direccion;
			direccion = getDireccionPredio();

			if(direccion != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(direccion.getDatosAntiguoSistema().getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
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
	 * Find matriculas.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 */
	public void findMatriculas(boolean ab_b)
	{
		RegistroCalificacion locrc_rc;

		locrc_rc = null;

		try
		{
			String ls_idTurnoHistoria;

			ls_idTurnoHistoria = getIdTurnoHistoria();

			if(StringUtils.isValidString(ls_idTurnoHistoria))
				locrc_rc = irr_calificacionRemote.findMatriculas(
					    null, ls_idTurnoHistoria, getTurno(), getUserId(), getRemoteIpAddress(), ab_b,
					    EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO
					);

			if(locrc_rc != null)
			{
				boolean                          lb_englobes;
				boolean                          lb_segregacion;
				Collection<RegistroCalificacion> lcrc_rc;

				lb_englobes        = locrc_rc.isEnglobe();
				lb_segregacion     = locrc_rc.isActoSegregacion();
				lcrc_rc            = locrc_rc.getAllMatriculas();

				setMatriculas(locrc_rc);
				setProcesoLoteo(locrc_rc.isLoteo());
				setDivisionMaterial(locrc_rc.isDivisionMaterial());
				setProcesoReloteo(locrc_rc.isReloteo());

				if(lb_englobes)
				{
					locrc_rc.setFechaApertura(new Date());

					cargarDatosBasicosEnglobes(getMatriculas());
					cargarDatosAreaEnglobes();
					cargarLinderosComplementacionEnglobes();
					setMostrarRegresarEnglobes(false);
					setMostrarSiguienteEnglobes(true);
				}

				if(CollectionUtils.isValidCollection(lcrc_rc))
				{
					setSalvar(false);
					matriculasAHeredar();
					setEnglobe(lb_englobes);
					setGeneraSegregacion(lb_segregacion);
					setInscripcionAdicional(locrc_rc.isInscripcionAdicional());
					setAperturaMatriculas(locrc_rc.isAperturaMatricula());

					if(!lb_englobes && !lb_segregacion)
					{
						cargarLinderos();
						cargarComplementacion();
						cargarDireccionPredio();
						actualizarDireccionPredio();
					}
					else
					{
						setRevisionMatriculas(null);
						setSalvar(true);
						setMatriculasAHeredar(irr_calificacionRemote.cargarAnotacionesEnglobes(getMatriculas()));
						guardarInfoDigitador();
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			locrc_rc = null;
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioPredio()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			DireccionDelPredio lddp_direccion;

			lddp_direccion = getDireccionPredio();

			if(lddp_direccion != null)
			{
				Municipio lm_parametros;

				lm_parametros = new Municipio();

				DatosAntSistema ldas_datosAntiguoSistema;

				ldas_datosAntiguoSistema = getDatosAntiguoSistema();

				if(ldas_datosAntiguoSistema != null)
				{
					lm_parametros.setIdPais(ldas_datosAntiguoSistema.getIdPais());
					lm_parametros.setIdDepartamento(ldas_datosAntiguoSistema.getIdDepartamento());

					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param afe_event correspondiente al valor del tipo de objeto FlowEvent
	 * @return devuelve el valor de String
	 */
	public String flowListener(FlowEvent afe_event)
	{
		String ls_new;
		String ls_old;

		ls_new     = afe_event.getNewStep();
		ls_old     = afe_event.getOldStep();

		try
		{
			if(StringUtils.isValidString(ls_old) && StringUtils.isValidString(ls_new))
			{
				final String ls_idComplementacion;
				final String ls_idDireccion;

				ls_idComplementacion     = "idTComplementacionEnglobes";
				ls_idDireccion           = "idTDireccionEnglobes";

				if(ls_new.equalsIgnoreCase(ls_idDireccion) && ls_old.equalsIgnoreCase(ls_idComplementacion))
				{
					if(isComplementacionSinConstruir())
						throw new B2BException(ErrorKeys.DEBE_CONSTRUIR_COMPLEMENTACION);

					salvarLinderosComplementacion();
					cargarDireccionesEnglobes();

					setMostrarSiguienteEnglobes(false);
					setMostrarRegresarEnglobes(true);

					{
						BeanDireccion        lbd_beanDireccion;
						RegistroCalificacion lrc_data;

						lbd_beanDireccion     = getBeanDireccion();
						lrc_data              = getMatriculas();

						if(lrc_data != null)
							lrc_data.setIdTurno(getTurno());

						lbd_beanDireccion.limpiarBeanDireccion();
						lbd_beanDireccion.setAgregarDireccionPredio(true);
						lbd_beanDireccion.setDeshabilitarDatosUbicacion(true);
						lbd_beanDireccion.setForm(cs_ID_FORMULARIO);
						lbd_beanDireccion.cargarDatosDireccionPredio(
						    irr_calificacionRemote.cargarDatosBasicosDigitadorMasivo(lrc_data)
						);
						lbd_beanDireccion.setDireccionesPredio2(getDireccionesPredio());
						lbd_beanDireccion.setDireccionesTemporales(getDireccionesPredio());
					}
				}
				else if(ls_new.equalsIgnoreCase(ls_idComplementacion) && ls_old.equalsIgnoreCase(ls_idDireccion))
				{
					setMostrarSiguienteEnglobes(true);
					setMostrarRegresarEnglobes(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			ls_new = ls_old;
		}

		return ls_new;
	}

	/**
	 * Genera excel.
	 */
	public void generaExcel()
	{
		{
			BeanRegistroCalificacion lbrc_bean;
			FacesContext             lfc_context;

			lfc_context     = FacesUtils.getFacesContext();
			lbrc_bean       = (BeanRegistroCalificacion)lfc_context.getApplication()
					                                                   .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION, BeanRegistroCalificacion.class
					);

			lbrc_bean.setMatriculasInformacion(getMatriculasInformacion());
			lbrc_bean.genera();
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> generarAnotacionesPredioAcc()
	{
		Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;
		lcap_anotaciones = null;

		RegistroCalificacion lrc_registroCalificacion;
		lrc_registroCalificacion = getMatriculas();

		if(lrc_registroCalificacion != null)
		{
			Collection<RegistroCalificacion> lcrc_registroCalificacion;
			lcrc_registroCalificacion = lrc_registroCalificacion.getAllMatriculas();

			if(CollectionUtils.isValidCollection(lcrc_registroCalificacion))
			{
				lcap_anotaciones = new ArrayList<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio>();

				for(RegistroCalificacion iterador : lcrc_registroCalificacion)
				{
					String ls_matriculaCompleta;
					String ls_idCirculo;
					Long   ll_idMatricula;

					ls_matriculaCompleta     = iterador.getIdCirculo();
					ls_idCirculo             = null;
					ll_idMatricula           = null;

					if(StringUtils.isValidString(ls_matriculaCompleta))
					{
						String[] lsa_matricula;
						lsa_matricula = ls_matriculaCompleta.split("-");

						if(CollectionUtils.isValidCollection(lsa_matricula) && (lsa_matricula.length > 0))
						{
							ls_idCirculo       = lsa_matricula[0];
							ll_idMatricula     = NumericUtils.getLongWrapper(lsa_matricula[1]);
						}

						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;

						lap_anotacionPredio = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
						lap_anotacionPredio.setIdMatricula(ll_idMatricula);
						lap_anotacionPredio.setIdCirculo(ls_idCirculo);

						lcap_anotaciones.add(lap_anotacionPredio);
					}
				}
			}
		}

		return lcap_anotaciones;
	}

	/**
	 * Guardar info digitador.
	 */
	public void guardarInfoDigitador()
	{
		RegistroCalificacion   lrc_dataFinal;
		RegistroCalificacion   lrc_return;
		Long                   ll_idTurnoHistoria;
		String                 ls_idTurno;
		Collection<AreaPredio> lcap_ap;
		String                 ls_tipoComplementacion;

		lrc_dataFinal              = new RegistroCalificacion();
		ls_tipoComplementacion     = null;

		try
		{
			lrc_dataFinal     = new RegistroCalificacion();

			ll_idTurnoHistoria = NumericUtils.getLongWrapper(getIdTurnoHistoria());

			if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				lrc_dataFinal.setIdTurnoHistoria(ll_idTurnoHistoria);

			ls_idTurno = getTurno();

			if(StringUtils.isValidString(ls_idTurno))
				lrc_dataFinal.setTurno(ls_idTurno);

			/* Validaciones tab Complementacion */
			if(!isEnglobe() && !isGeneraSegregacion())
			{
				{
					ComplementacionCalificacion lcc_complementacionCalificacion;
					lcc_complementacionCalificacion = getComplementacionCalificacion();

					if(lcc_complementacionCalificacion != null)
					{
						ComplementacionPredio lcp_complementacionPredio;
						lcp_complementacionPredio = lcc_complementacionCalificacion.getComplementacionPredio();

						if(lcp_complementacionPredio != null)
						{
							lcp_complementacionPredio.setIdUsuarioCreacion(getUserId());
							lcp_complementacionPredio.setIpCreacion(getLocalIpAddress());
							lcp_complementacionPredio.setIdUsuarioModificacion(getUserId());
							lcp_complementacionPredio.setIpModificacion(getLocalIpAddress());

							ls_tipoComplementacion = lcc_complementacionCalificacion.getTipoComplementacion();

							if(StringUtils.isValidString(ls_tipoComplementacion))
							{
								if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
								{
									String ls_complementacion;
									ls_complementacion = lcp_complementacionPredio.getComplementacion();

									if(StringUtils.isValidString(ls_complementacion))
									{
										if(ls_complementacion.trim().length() < 99)
											throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TAM);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);
								}

								lrc_dataFinal.setComplementacionCalificacion(lcc_complementacionCalificacion);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);
						}
					}

					LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;
					llrc_linderoRegistroCalificacion = getLinderoRegistroCalificacion();

					if(llrc_linderoRegistroCalificacion != null)
					{
						String ls_lindero;
						ls_lindero = llrc_linderoRegistroCalificacion.getLindero();

						if(StringUtils.isValidString(ls_lindero))
						{
							Collection<AccLinderoPredio> lcalp_accLinderoPredios;
							Collection<AccLinderoPredio> lcalp_return;

							lcalp_accLinderoPredios     = llrc_linderoRegistroCalificacion.getLinderoPredios();
							lcalp_return                = new ArrayList<AccLinderoPredio>();

							for(AccLinderoPredio alp_linderoPredio : lcalp_accLinderoPredios)
							{
								alp_linderoPredio.setIdTurno(ls_idTurno);
								alp_linderoPredio.setIdTurnoHistoria(ll_idTurnoHistoria);
								alp_linderoPredio.setLindero(ls_lindero);

								lcalp_return.add(alp_linderoPredio);
							}

							llrc_linderoRegistroCalificacion.setLinderoPredios(lcalp_return);
						}
					}

					lrc_dataFinal.setLinderoRegistroCalificacion(llrc_linderoRegistroCalificacion);
				}
				/* Validaciones tab direccion predio */
				{
					DireccionDelPredio lodp_tmp;
					DireccionDelPredio ldp_d;

					lodp_tmp     = getDataDireccionPredio();
					ldp_d        = getDireccionPredio();

					ldp_d.getDireccionPredio().setIdTurno(getTurno());
					ldp_d.getDireccionPredio().setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
					ldp_d.getDireccionPredio().setIdMatricula(getMatriculaDireccionPredio());
					ldp_d.getDireccionPredio().setIdCirculo(getCirculoDireccionPredio());

					if(isModificarDireccionPredio())
						ldp_d.getDireccionPredio().setIdDireccion("0");

					lodp_tmp = ldp_d;
					setDataDireccionPredio(lodp_tmp);
					setModificarDireccionPredio(false);
					setDeshabilitarBotonConfirmarDireccion(true);

					lrc_dataFinal.setDataDireccionPredio(lodp_tmp);
				}
			}

			if(StringUtils.isValidString(getObservaciones()))
				lrc_dataFinal.setObservaciones(getObservaciones());

			lrc_return = irr_calificacionRemote.guardarInfoDigitador(
				    lrc_dataFinal, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lrc_return != null)
			{
				lcap_ap = lrc_return.getMatriculasInformacion();

				Map<String, Boolean> lmsb_tmp;
				lmsb_tmp = getRevisionMatriculas();

				if(CollectionUtils.isValidCollection(lcap_ap))
				{
					for(AreaPredio lap_tmp : lcap_ap)
					{
						if(lap_tmp != null)
						{
							if(lap_tmp.isRevisadoDigitador())
								lmsb_tmp.put(lap_tmp.getIdPredioRegistro(), new Boolean(true));
						}
					}

					setMatriculasInformacion(lcap_ap);
					setSalvar(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		setDeshabilitarBotonConfirmarDireccion(true);
		setHabilitarTabs(false);
		setModificarDireccionPredio(false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param adre_dre correspondiente al valor del tipo de objeto DashboardReorderEvent
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public String handleReorder(DashboardReorderEvent adre_dre)
	    throws B2BException, IOException
	{
		String ls_idWidget;

		List<String> lls_idPanels;
		String ls_change;
		ls_idWidget      = adre_dre.getWidgetId();
		lls_idPanels     = getPanels();
		ls_change        = null;

		if(StringUtils.isValidString(ls_idWidget))
			ls_change = changePosition(lls_idPanels, ls_idWidget, -1);

		try
		{
			if(StringUtils.isValidString(ls_change))
			{
				ls_idWidget     = generateId(ls_idWidget);
				ls_change       = generateId(ls_change);

				irr_calificacionRemote.modifyidAnotacion(ls_idWidget, ls_change, getUserId());
				idb_dataModel = new Dashboard();
				consultaDetalleMatricula();

				ExternalContext lec_ec = FacesContext.getCurrentInstance().getExternalContext();
				lec_ec.redirect(((HttpServletRequest)lec_ec.getRequest()).getRequestURI());
			}
		}
		catch(B2BException lb2b_2b)
		{
			addMessage(lb2b_2b);
		}

		return NavegacionCommon.DETALLE_REGISTRO;
	}

	/**
	 * Info matriculas digitador.
	 *
	 * @param al_idTurno correspondiente al valor del tipo de objeto String
	 */
	public void infoMatriculasDigitador(String al_idTurno)
	{
		try
		{
			RegistroCalificacion lrc_return;
			lrc_return = irr_calificacionRemote.findMatriculasInfByTurno(al_idTurno, getUserId(), getLocalIpAddress());

			if(lrc_return != null)
			{
				Collection<AreaPredio> lcap_ap;

				lcap_ap = lrc_return.getMatriculasInformacion();

				if(CollectionUtils.isValidCollection(lcap_ap))
					setMatriculasInformacion(lcap_ap);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Limpiar complementacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void limpiarComplementacion()
	    throws B2BException
	{
		ComplementacionCalificacion lcc_complementacionCalificacion;

		lcc_complementacionCalificacion = getComplementacionCalificacion();

		if(lcc_complementacionCalificacion != null)
		{
			lcc_complementacionCalificacion.setComplementacionPredio(null);
			lcc_complementacionCalificacion.setComplementacion(null);
			lcc_complementacionCalificacion.setObservaciones(null);
			lcc_complementacionCalificacion.setCopiarEditar(false);

			String ls_tipoComplementacion;

			ls_tipoComplementacion = lcc_complementacionCalificacion.getTipoComplementacion();

			if(StringUtils.isValidString(ls_tipoComplementacion))
			{
				if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.CONSTRUIR))
					construirComplementacion();
				else if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
				{
					lcc_complementacionCalificacion.setCopiarEditar(true);
					addMessage(MessagesKeys.NO_COMPLEMENTACION_CERIFICAR_DIGITADOR_MASIVO);
				}
			}
		}
	}

	/**
	 * Matriculas A heredar.
	 */
	public void matriculasAHeredar()
	{
		RegistroCalificacion lrc_rc;

		lrc_rc = new RegistroCalificacion();

		try
		{
			if((getMatriculas() != null) && CollectionUtils.isValidCollection(getMatriculas().getAllMatriculas()))
			{
				getMatriculas().setDevolucion(true);
				lrc_rc = irr_calificacionRemote.matriculasAHeredar(getMatriculas());
			}

			if((lrc_rc != null) && CollectionUtils.isValidCollection(lrc_rc.getAllMatriculas()))
				setMatriculasAHeredar(lrc_rc);
			else
			{
				addMessage(MessagesKeys.NO_EXISTEN_ANOTACIONES_A_HEREDAR);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lrc_rc = null;
		}
	}

	/**
	 * Modificar anotacion.
	 *
	 * @param aui_childremPanel correspondiente al valor del tipo de objeto List<UIComponent>
	 * @param as_idPanel correspondiente al valor del tipo de objeto String
	 * @param as_revision correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarAnotacion(List<UIComponent> aui_childremPanel, String as_idPanel, String as_revision)
	    throws B2BException
	{
		boolean              lb_valor;
		boolean              lb_comentario;
		String               ls_valor;
		BigDecimal           lbd_valor;
		String               ls_comentario;
		RegistroCalificacion lrc_rc;
		lbd_valor         = null;
		as_idPanel        = generateId(as_idPanel);
		lrc_rc            = new RegistroCalificacion();
		ls_valor          = null;
		lb_valor          = false;
		lb_comentario     = false;
		ls_comentario     = null;

		try
		{
			lrc_rc.setIdAnotacionPredio(as_idPanel);
			lrc_rc.setUserId(getUserId());
			lrc_rc.setIdUsuario(getUserId());
			lrc_rc.setRevision(EstadoCommon.S);
			lrc_rc.setIdUsuarioModificacion(getUserId());

			for(UIComponent lui_childPanel : aui_childremPanel)
			{
				if(lui_childPanel instanceof UIOutput)
				{
					if(lui_childPanel.getId().contains("val_"))
					{
						UIInput loi_oi = (UIInput)lui_childPanel;
						ls_valor = loi_oi.getValue().toString();

						if(StringUtils.isValidString(ls_valor))
						{
							try
							{
								lbd_valor = NumericUtils.getBigDecimal(ls_valor);

								lrc_rc.setValor(lbd_valor);
							}
							catch(NumberFormatException nfe_nfe)
							{
								throw new B2BException(ErrorKeys.ERROR_VALOR_DEBE_SER_NUMERICO);
							}
						}
						else
							lb_valor = true;
					}
					else if(lui_childPanel.getId().contains("comen_"))
					{
						UIInput loi_oi = (UIInput)lui_childPanel;
						ls_comentario = loi_oi.getValue().toString();

						if(StringUtils.isValidString(ls_comentario))
							lrc_rc.setComentario(ls_comentario);
						else
							lb_comentario = true;
					}
				}

				if(StringUtils.isValidString(ls_comentario) || lb_comentario)
				{
					if(NumericUtils.isValidBigDecimal(lbd_valor) || lb_valor)
					{
						irr_calificacionRemote.saveDetailAnotacion(lrc_rc);

						lbd_valor         = null;
						ls_comentario     = null;
					}
				}
			}

			addMessage(MessagesKeys.REGISTRO_MODIFICADO);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * On row edit.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 */
	public void onRowEdit()
	{
		DireccionDelPredio lddp_direccionSeleccionada;

		lddp_direccionSeleccionada = getDireccionSeleccionada();

		if(lddp_direccionSeleccionada != null)
		{
			try
			{
				BeanDireccion lbd_beanDireccion;

				lbd_beanDireccion = getBeanDireccion();

				lddp_direccionSeleccionada.setDireccionPredio(
				    actualizarDireccion(lddp_direccionSeleccionada.getDireccionPredio())
				);

				lddp_direccionSeleccionada = iasr_antiguoSistemaRemote.actualizarDireccionPredio(
					    lddp_direccionSeleccionada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				Collection<DireccionDelPredio> lcddp_coleccionTemp;
				Collection<DireccionDelPredio> lcddp_coleccionActual;

				lcddp_coleccionTemp       = new LinkedList<DireccionDelPredio>();
				lcddp_coleccionActual     = lbd_beanDireccion.getDireccionesTemporales();

				if(!CollectionUtils.isValidCollection(lcddp_coleccionActual))
					throw new B2BException(ErrorKeys.DIRECCION_PREDIO_NO_ENCONTRADA);

				for(DireccionDelPredio lddp_iterador : lcddp_coleccionActual)
				{
					if(
					    lddp_direccionSeleccionada.getDireccionPredio().getIdDireccionPredio()
						                              .equalsIgnoreCase(
						        lddp_iterador.getDireccionPredio().getIdDireccionPredio()
						    )
					)
						lcddp_coleccionTemp.add(lddp_direccionSeleccionada);
					else
						lcddp_coleccionTemp.add(lddp_iterador);
				}

				lbd_beanDireccion.setDireccionesPredio2(lcddp_coleccionTemp);
				lbd_beanDireccion.setDireccionesTemporales(lbd_beanDireccion.getDireccionesPredio2());

				addMessage(MessagesKeys.DIRECCION_EDITADA);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(cs_ID_FORMULARIO);
			}
		}
	}

	/** {@inheritdoc} */
	public String processFile(FileUploadEvent event)
	{
		String ls_return;

		ls_return = null;

		try
		{
			FacesContext         lfc_context;
			String               ls_idTurnoHistoria;
			RegistroCalificacion locrc_rc;

			locrc_rc               = new RegistroCalificacion();
			ls_idTurnoHistoria     = getIdTurnoHistoria();

			if(StringUtils.isValidString(ls_idTurnoHistoria))
				locrc_rc = irr_calificacionRemote.findMatriculas(
					    null, ls_idTurnoHistoria, getTurno(), getUserId(), getRemoteIpAddress(), false,
					    EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO
					);

			if(locrc_rc != null)
			{
				BeanRegistroCalificacion lbrc_bean;

				lfc_context     = FacesUtils.getFacesContext();
				lbrc_bean       = (BeanRegistroCalificacion)lfc_context.getApplication()
						                                                   .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION, BeanRegistroCalificacion.class
						);

				lbrc_bean.setProcesoDesenglobes(locrc_rc.isDesenglobe());
				lbrc_bean.setDivisionMaterial(locrc_rc.isDivisionMaterial());
				lbrc_bean.setProcesoLoteo(locrc_rc.isLoteo());
				lbrc_bean.setProcesoReloteo(locrc_rc.isReloteo());
				lbrc_bean.setValidar(true);
				lbrc_bean.setMatriculas(locrc_rc);

				ls_return = lbrc_bean.processFile(event);
			}

			infoMatriculasDigitador(getTurno());
			PrimeFaces.current().ajax().update("fdetalleDigitadorMasivo:idDtMatriculasInfo");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String regresar()
	{
		clean();
		findMatriculas(false);

		FacesContext lfc_context;
		String       ls_return;

		ls_return       = NavegacionCommon.CALIFICACION;
		lfc_context     = FacesUtils.getFacesContext();

		BeanCalificacion lbde_bean;
		lbde_bean       = (BeanCalificacion)lfc_context.getApplication()
				                                           .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
				);

		if(lbde_bean != null)
			try
			{
				lbde_bean.clear();
				lbde_bean.generarDatosTramiteCantidad();
			}
			catch(B2BException lb2b_b2b)
			{
				ls_return = null;
				addMessage(lb2b_b2b);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String retornoBandeja()
	{
		setImagen(null);
		setDetalleMatricula(null);
		findMatriculas(false);

		return NavegacionCommon.REGISTRO_CALIFICACION;
	}

	/**
	 * Retorno a la bandeja principal.
	 *
	 * @return String con la pagina a redireccionar
	 */
	public String returnBandeja()
	{
		String ls_return;
		ls_return = NavegacionCommon.TURNOS_DIGITADOR_MASIVO;

		try
		{
			BeanDigitadorMasivo lbdm_bean;
			FacesContext        lfc_context;
			Long                ll_idEtapa;

			lfc_context     = FacesUtils.getFacesContext();
			lbdm_bean       = (BeanDigitadorMasivo)lfc_context.getApplication()
					                                              .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DIGITADOR_MASIVO, BeanDigitadorMasivo.class
					);

			ll_idEtapa = NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO);
			lbdm_bean.setIdEtapa(ll_idEtapa);
			lbdm_bean.clear();
			lbdm_bean.generarData();

			clean();
			setObservacionesTemporales(null);
		}
		catch(B2BException e)
		{
			clh_LOGGER.error("returnBandeja", e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvarEnglobes()
	{
		String ls_return;

		ls_return = NavegacionCommon.TURNOS_DIGITADOR_MASIVO;

		try
		{
			RegistroCalificacion lrc_data;

			lrc_data = getMatriculas();

			if(lrc_data != null)
			{
				BeanDireccion                    lb_beanDireccion;
				Collection<DireccionDelPredio>   lcddp_direcciones;
				Long                             ll_idMatricula;
				HashMap<String, DireccionPredio> lhmsdp_direcciones;

				lb_beanDireccion       = getBeanDireccion();
				lcddp_direcciones      = lb_beanDireccion.getDireccionesPredio2();
				ll_idMatricula         = lrc_data.getIdMatricula();
				lhmsdp_direcciones     = lrc_data.getDireccionesSeleccionadas();

				if(!CollectionUtils.isValidCollection(lhmsdp_direcciones))
					throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);

				if(CollectionUtils.isValidCollection(lcddp_direcciones))
				{
					int li_idDireccion;

					li_idDireccion = 1;

					if(lhmsdp_direcciones == null)
						lhmsdp_direcciones = new HashMap<String, DireccionPredio>();
					else
						li_idDireccion = lhmsdp_direcciones.size() + 1;

					for(DireccionDelPredio lddp_iterador : lcddp_direcciones)
					{
						if(lddp_iterador != null)
						{
							DireccionPredioAcc ldpa_iterador;

							ldpa_iterador = lddp_iterador.getDireccionPredio();

							if(ldpa_iterador != null)
							{
								DireccionPredio ldp_direccion;

								ldp_direccion = new DireccionPredio();

								ldp_direccion.setComplementoDireccion(ldpa_iterador.getComplementoDireccion());
								ldp_direccion.setIdTipoEjePrincipal(ldpa_iterador.getIdTipoEjePrincipal());
								ldp_direccion.setIdTipoEjeSecundario(ldpa_iterador.getIdTipoEjeSecundario());
								ldp_direccion.setDatoEjePrincipal(ldpa_iterador.getDatoEjePrincipal());
								ldp_direccion.setDatoEjeSecundario(ldpa_iterador.getDatoEjeSecundario());
								ldp_direccion.setIdDireccion(StringUtils.getString(li_idDireccion));

								lhmsdp_direcciones.put(
								    ll_idMatricula + IdentificadoresCommon.SIMBOLO_GUION + li_idDireccion, ldp_direccion
								);

								li_idDireccion++;
							}
						}
					}

					lrc_data.setDireccionesSeleccionadas(lhmsdp_direcciones);
				}

				lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lrc_data.setTurno(getTurno());
				lrc_data.setIdUsuario(getUserId());
				lrc_data.setIpAddress(getRemoteIpAddress());
				lrc_data.setObservaciones(getObservaciones());

				irr_calificacionRemote.salvarEnglobes(lrc_data);

				{
					BeanDigitadorMasivo lbdm_bean;
					FacesContext        lfc_context;

					lfc_context     = FacesUtils.getFacesContext();
					lbdm_bean       = (BeanDigitadorMasivo)lfc_context.getApplication()
							                                              .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_DIGITADOR_MASIVO, BeanDigitadorMasivo.class
							);

					lbdm_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO));
					lbdm_bean.clear();
					lbdm_bean.generarData();
				}

				clean();
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(cs_ID_FORMULARIO);
			ls_return = null;
		}

		return ls_return;
	}

	/** {@inheritdoc} */
	public void salvarLinderosComplementacion()
	    throws B2BException
	{
		try
		{
			ComplementacionCalificacion lcc_dataComplementacion;
			LinderoRegistroCalificacion llrc_dataLindero;
			RegistroCalificacion        lrc_data;

			lcc_dataComplementacion     = getComplementacionCalificacion();
			llrc_dataLindero            = getLinderoRegistroCalificacion();
			lrc_data                    = getMatriculas();

			if((lcc_dataComplementacion != null) && (llrc_dataLindero != null) && (lrc_data != null))
			{
				boolean      lb_focus;
				FacesContext lfc_context;
				String       ls_tipoComplementacion;
				String       ls_lindero;

				lb_focus                   = false;
				lfc_context                = FacesContext.getCurrentInstance();
				ls_tipoComplementacion     = lcc_dataComplementacion.getTipoComplementacion();
				ls_lindero                 = llrc_dataLindero.getLindero();
				lb_focus                   = validateStyles(
					    ":fdetalleDigitadorMasivo:idLinderoEnglobes", lfc_context, ls_lindero, lb_focus
					);

				if(!StringUtils.isValidString(ls_lindero))
					throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);

				if(ls_lindero.length() < 100)
					throw new B2BException(ErrorKeys.ERROR_LINDERO_TAM);

				if(StringUtils.isValidString(ls_tipoComplementacion))
				{
					ComplementacionPredio lcp_complementacion;

					lcp_complementacion = lcc_dataComplementacion.getComplementacionPredio();

					if(lcp_complementacion != null)
					{
						String ls_complementacion;

						ls_complementacion     = lcp_complementacion.getComplementacion();
						lb_focus               = validateStyles(
							    ":fdetalleDigitadorMasivo:idcomplementacionEnglobes", lfc_context, ls_complementacion,
							    lb_focus
							);

						if(StringUtils.isValidString(ls_complementacion))
						{
							if(ls_complementacion.length() < 100)
								throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TAM);
							else
							{
								lrc_data.setTurno(getTurno());
								lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

								irr_calificacionRemote.salvarLinderosComplementacionEnglobes(
								    lcc_dataComplementacion, llrc_dataLindero, lrc_data, getUserId(),
								    getLocalIpAddress(), getRemoteIpAddress()
								);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);
					}
					else
					{
						lb_focus = validateStyles(
							    ":fdetalleDigitadorMasivo:idcomplementacionEnglobes", lfc_context, "", lb_focus
							);

						throw new B2BException(ErrorKeys.ERROR_SIN_COMPLEMENTACION);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);
			}
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/** {@inheritdoc} */
	public void validarCopiarDeUnaMatricula()
	    throws B2BException
	{
		ComplementacionCalificacion lcc_complementacionCalificacion;

		lcc_complementacionCalificacion = getComplementacionCalificacion();

		try
		{
			if(lcc_complementacionCalificacion != null)
			{
				Complementacion lc_complementacion;

				lc_complementacion = lcc_complementacionCalificacion.getComplementacion();

				if(lc_complementacion != null)
				{
					ComplementacionPredio complementacionPredio;

					complementacionPredio = iasr_antiguoSistemaRemote.findComplementacion(lc_complementacion);

					if(complementacionPredio != null)
						lcc_complementacionCalificacion.setComplementacionPredio(complementacionPredio);
				}

				lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.COPIAR);
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			lcc_complementacionCalificacion.setComplementacion(null);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		clean();
		setMatriculas(null);

		FacesContext lfc_context;
		String       ls_return;

		ls_return       = NavegacionCommon.CALIFICACION;
		lfc_context     = FacesUtils.getFacesContext();

		BeanCalificacion lbde_bean;
		lbde_bean       = (BeanCalificacion)lfc_context.getApplication()
				                                           .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
				);

		if(lbde_bean != null)
			try
			{
				lbde_bean.clear();
				lbde_bean.generarDatosTramiteCantidad();
			}
			catch(B2BException lb2b_b2b)
			{
				ls_return = null;
				addMessage(lb2b_b2b);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}

		return ls_return;
	}

	/**
	 * Actualizar area terreno.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void actualizarAreaTerreno()
	    throws B2BException
	{
		AccAreaUI laaui_data;

		laaui_data = getAreaUI();

		if(laaui_data != null)
		{
			Collection<DetalleAreaPredio> lcadap_areas;

			lcadap_areas = laaui_data.getAreasTerreno();

			if(CollectionUtils.isValidCollection(lcadap_areas))
			{
				Map<String, String>         lhmss_medidasArea;
				Iterator<DetalleAreaPredio> liadap_iterator;
				StringBuilder               lsb_sb;

				lhmss_medidasArea     = irr_referenceRemote.findAllMedidaAreaNombres();
				liadap_iterator       = lcadap_areas.iterator();
				lsb_sb                = new StringBuilder();

				while(liadap_iterator.hasNext())
				{
					DetalleAreaPredio lacap_area;

					lacap_area = liadap_iterator.next();

					if(lacap_area != null)
					{
						String ls_separador;

						ls_separador = liadap_iterator.hasNext() ? ", " : IdentificadoresCommon.PUNTO;

						lsb_sb.append(
						    lacap_area.getArea() + " " + lhmss_medidasArea.get(lacap_area.getIdUnidadMedida())
						    + ls_separador
						);
					}
				}

				laaui_data.setAreaTerreno(lsb_sb.toString());
				setAreaUI(laaui_data);
			}
		}
	}

	/**
	 * Cargar datos area englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosAreaEnglobes()
	    throws B2BException
	{
		setAreaUI(irr_calificacionRemote.cargarDatosAreaEnglobes(getMatriculas()));
		actualizarAreaTerreno();
	}

	/**
	 * Cargar datos basicos englobes.
	 *
	 * @param arc_data correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosBasicosEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = irr_calificacionRemote.cargarDatosBasicosEnglobes(arc_data);

		if(lrc_data != null)
		{
			setMatriculas(lrc_data);

			Collection<RegistroCalificacion> lcrc_matriculas;

			lcrc_matriculas = lrc_data.getAllMatriculas();

			if(CollectionUtils.isValidCollection(lcrc_matriculas))
			{
				Iterator<RegistroCalificacion> lirc_iterator;

				lirc_iterator = lcrc_matriculas.iterator();

				while(lirc_iterator.hasNext())
				{
					RegistroCalificacion lrc_matriculaActual;

					lrc_matriculaActual = lirc_iterator.next();

					if(lrc_matriculaActual != null)
						cambiarUbicacionEnglobe(lrc_matriculaActual);
				}
			}
		}
	}

	/**
	 * Cargar direcciones englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDireccionesEnglobes()
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = irr_calificacionRemote.cargarDireccionesEnglobes(getMatriculas(), true);

		if(lrc_data != null)
		{
			setDirecciones(lrc_data.getDirecciones());
			setMatriculas(lrc_data);
		}
	}

	/**
	 * Cargar linderos complementacion englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarLinderosComplementacionEnglobes()
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = getMatriculas();

		if(lrc_data != null)
		{
			lrc_data.setTurno(getTurno());
			lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			setLinderoRegistroCalificacion(irr_calificacionRemote.cargarLinderosEnglobes(lrc_data));
			setComplementacionCalificacion(irr_calificacionRemote.cargarComplementacionEnglobes(lrc_data));
		}
	}
}
