package com.bachue.snr.prosnr01.web.bean.correcciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.excel.ExcelUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanCorreccion;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CamposCorreccionCommon;
import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.LibroAntSistemaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.UnidadMedidaAreaCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.CabidaLinderos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.LinderoRegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAntSistemaSolicitud;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelApertura;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAreaPredio;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelCatastral;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelComplementacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDatosAnotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDatosDocumento;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaAnotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaSolicitud;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleIntervinientes;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDireccionPredio;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelEspecificacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelIntervinientes;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelLinderos;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasAbiertas;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasSegregacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelUbicacion;
import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadAnotacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;
import com.bachue.snr.prosnr01.model.ui.PermisosCorreccionesUI;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanDetalleRegistroCalificacion;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.MatriculaSegregacionUi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanEjecucionCorrecciones.
 *
 * @author  Heiner Castañeda
 * Fecha de Creacion: 09/08/2019
 */
@SessionScoped
@ManagedBean(name = "beanEjecucionCorrecciones")
public class BeanEjecucionCorrecciones extends BeanPermisosCorrecciones implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6449218560574127560L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanEjecucionCorrecciones.class);

	/** Constante cs_ID_FORMULARIO. */
	private static final String cs_ID_FORMULARIO = "fEjecucionCorreccion";

	/** Constante cs_ID_FORMULARIO_DETALLE_ANT. */
	private static final String cs_ID_FORMULARIO_DETALLE_ANT = "fDialogDetallePredio:";

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = cs_ID_FORMULARIO + ":globalMsg";

	/** Constante cs_ID_FORMULARIO_TV. */
	private static final String cs_ID_FORMULARIO_TV = cs_ID_FORMULARIO + ":TvdetalleRegistroCalif";

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icms data info predial. */
	private Collection<MatriculaSegregacionUi> icms_dataInfoPredial;

	/** Propiedad idsc documento formulario correccion. */
	private DefaultStreamedContent idsc_documentoFormularioCorreccion;

	/** Propiedad idas detalle ant sis para eliminar. */
	private DetalleAntSistema idas_detalleAntSisParaEliminar;

	/** Propiedad idasr digitador antiguo sistema remote. */
	@EJB
	private DigitadorAntiguoSistemaRemote idasr_digitadorAntiguoSistemaRemote;

	/** Propiedad iddp direccion seleccionada. */
	private DireccionDelPredio iddp_direccionSeleccionada;

	/** Propiedad ier entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad imso info anotacion. */
	private Map<String, Object> imso_infoAnotacion;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ips predio validar anotaciones. */
	private PredioSegregado ips_predioValidarAnotaciones;

	/**  Propiedadd irr_referenceRemote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc file predial. */
	private StreamedContent isc_filePredial;

	/** Propiedad is copiar complementacion. */
	private String is_copiarComplementacion;

	/** Propiedad is copiar complementacion seleccionadas. */
	private String is_copiarComplementacionSeleccionadas;

	/** Propiedad is copiar documento. */
	private String is_copiarDocumento;

	/** Propiedad is copiar documento seleccionadas. */
	private String is_copiarDocumentoSeleccionadas;

	/** Propiedad is copiar interviniente. */
	private String is_copiarInterviniente;

	/** Propiedad is copiar interviniente seleccionadas. */
	private String is_copiarIntervinienteSeleccionadas;

	/** Propiedad is id causal. */
	private String is_idCausal;

	/** Propiedad is mensaje confirmacion salvar. */
	private String is_mensajeConfirmacionSalvar;

	/** Propiedad ib anotacion matriz. */
	private boolean ib_anotacionMatriz;

	/** Propiedad ib editor tabla direcciones. */
	private boolean ib_editorTablaDirecciones;

	/** Propiedad ib guardar detalle ant. */
	private boolean ib_guardarDetalleAnt;

	/** Propiedad ii tab actual. */
	private int ii_tabActual;

	/**
	 * @param ab_b Modifica el valor de la propiedad anotacionMatriz por anotacionMatriz
	 */
	public void setAnotacionMatriz(boolean ab_b)
	{
		ib_anotacionMatriz = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad anotacionMatriz
	 */
	public boolean isAnotacionMatriz()
	{
		return ib_anotacionMatriz;
	}

	/**
	 * Modifica el valor de copiar complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad copiar complementacion
	 */
	public void setCopiarComplementacion(String as_s)
	{
		is_copiarComplementacion = as_s;
	}

	/**
	 * Retorna el valor de copiar complementacion.
	 *
	 * @return el valor de copiar complementacion
	 */
	public String getCopiarComplementacion()
	{
		return is_copiarComplementacion;
	}

	/**
	 * Modifica el valor de copiar complementacion seleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad copiar complementacion seleccionadas
	 */
	public void setCopiarComplementacionSeleccionadas(String as_s)
	{
		is_copiarComplementacionSeleccionadas = as_s;
	}

	/**
	 * Retorna el valor de copiar complementacion seleccionadas.
	 *
	 * @return el valor de copiar complementacion seleccionadas
	 */
	public String getCopiarComplementacionSeleccionadas()
	{
		return is_copiarComplementacionSeleccionadas;
	}

	/**
	 * Modifica el valor de copiar documento.
	 *
	 * @param as_s asigna el valor a la propiedad copiar documento
	 */
	public void setCopiarDocumento(String as_s)
	{
		is_copiarDocumento = as_s;
	}

	/**
	 * Retorna el valor de copiar documento.
	 *
	 * @return el valor de copiar documento
	 */
	public String getCopiarDocumento()
	{
		return is_copiarDocumento;
	}

	/**
	 * Modifica el valor de copiar documento seleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad copiar documento seleccionadas
	 */
	public void setCopiarDocumentoSeleccionadas(String as_s)
	{
		is_copiarDocumentoSeleccionadas = as_s;
	}

	/**
	 * Retorna el valor de copiar documento seleccionadas.
	 *
	 * @return el valor de copiar documento seleccionadas
	 */
	public String getCopiarDocumentoSeleccionadas()
	{
		return is_copiarDocumentoSeleccionadas;
	}

	/**
	 * Modifica el valor de copiar interviniente.
	 *
	 * @param as_s asigna el valor a la propiedad copiar interviniente
	 */
	public void setCopiarInterviniente(String as_s)
	{
		is_copiarInterviniente = as_s;
	}

	/**
	 * Retorna el valor de copiar interviniente.
	 *
	 * @return el valor de copiar interviniente
	 */
	public String getCopiarInterviniente()
	{
		return is_copiarInterviniente;
	}

	/**
	 * Modifica el valor de copiar interviniente seleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad copiar interviniente seleccionadas
	 */
	public void setCopiarIntervinienteSeleccionadas(String as_s)
	{
		is_copiarIntervinienteSeleccionadas = as_s;
	}

	/**
	 * Retorna el valor de copiar interviniente seleccionadas.
	 *
	 * @return el valor de copiar interviniente seleccionadas
	 */
	public String getCopiarIntervinienteSeleccionadas()
	{
		return is_copiarIntervinienteSeleccionadas;
	}

	/**
	 * Modifica el valor de data info predial.
	 *
	 * @param acms_cms asigna el valor a la propiedad data info predial
	 */
	public void setDataInfoPredial(Collection<MatriculaSegregacionUi> acms_cms)
	{
		icms_dataInfoPredial = acms_cms;
	}

	/**
	 * Retorna el valor de data info predial.
	 *
	 * @return el valor de data info predial
	 */
	public Collection<MatriculaSegregacionUi> getDataInfoPredial()
	{
		if(!CollectionUtils.isValidCollection(icms_dataInfoPredial))
			icms_dataInfoPredial = new ArrayList<MatriculaSegregacionUi>();

		return icms_dataInfoPredial;
	}

	/**
	 * Modifica el valor de detalle ant sis para eliminar.
	 *
	 * @param adas_das asigna el valor a la propiedad detalle ant sis para eliminar
	 */
	public void setDetalleAntSisParaEliminar(DetalleAntSistema adas_das)
	{
		idas_detalleAntSisParaEliminar = adas_das;
	}

	/**
	 * Retorna el valor de detalle ant sis para eliminar.
	 *
	 * @return el valor de detalle ant sis para eliminar
	 */
	public DetalleAntSistema getDetalleAntSisParaEliminar()
	{
		return idas_detalleAntSisParaEliminar;
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
	 * Modifica el valor de documento formulario correccion.
	 *
	 * @param adsc_sc asigna el valor a la propiedad documento formulario correccion
	 */
	public void setDocumentoFormularioCorreccion(DefaultStreamedContent adsc_sc)
	{
		idsc_documentoFormularioCorreccion = adsc_sc;
	}

	/**
	 * Retorna el valor de documento formulario correccion.
	 *
	 * @return el valor de documento formulario correccion
	 */
	public DefaultStreamedContent getDocumentoFormularioCorreccion()
	{
		return idsc_documentoFormularioCorreccion;
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
	 * Modifica el valor de file predial.
	 *
	 * @param asc_sc asigna el valor a la propiedad file predial
	 */
	public void setFilePredial(StreamedContent asc_sc)
	{
		isc_filePredial = asc_sc;
	}

	/**
	 * Retorna el valor de file predial.
	 *
	 * @return el valor de file predial
	 */
	public StreamedContent getFilePredial()
	{
		return isc_filePredial;
	}

	/**
	 * Modifica el valor de guardar detalle ant.
	 *
	 * @param ab_b asigna el valor a la propiedad guardar detalle ant
	 */
	public void setGuardarDetalleAnt(boolean ab_b)
	{
		ib_guardarDetalleAnt = ab_b;
	}

	/**
	 * Valida la propiedad guardar detalle ant.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en guardar detalle ant
	 */
	public boolean isGuardarDetalleAnt()
	{
		return ib_guardarDetalleAnt;
	}

	/**
	 * Modifica el valor de id causal.
	 *
	 * @param as_s asigna el valor a la propiedad id causal
	 */
	public void setIdCausal(String as_s)
	{
		is_idCausal = as_s;
	}

	/**
	 * Retorna el valor de id causal.
	 *
	 * @return el valor de id causal
	 */
	public String getIdCausal()
	{
		return is_idCausal;
	}

	/**
	 * @param amso_mso Modifica el valor de la propiedad
	 */
	public void setInfoAnotacion(Map<String, Object> amso_mso)
	{
		imso_infoAnotacion = amso_mso;
	}

	/**
	 * @return Retorna el valor de la propiedad
	 */
	public Map<String, Object> getInfoAnotacion()
	{
		return imso_infoAnotacion;
	}

	/**
	 * Método encargado de generar el mensaje para el panel de anotación.
	 * @return Variable String que contiene el mensaje.
	 */
	public String getMensajeAnotacion()
	{
		return getNumeroAnotacion() + IdentificadoresCommon.ESPACIO_VACIO + IdentificadoresCommon.SIMBOLO_GUION
		+ IdentificadoresCommon.ESPACIO_VACIO + getMessages().getString(MessagesKeys.LABEL_ORDEN)
		+ IdentificadoresCommon.ESPACIO_VACIO + getNumeroOrden();
	}

	/**
	 * Modifica el valor de mensaje confirmacion salvar.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje confirmacion salvar
	 */
	public void setMensajeConfirmacionSalvar(String as_s)
	{
		is_mensajeConfirmacionSalvar = as_s;
	}

	/**
	 * Retorna el valor de mensaje confirmacion salvar.
	 *
	 * @return el valor de mensaje confirmacion salvar
	 */
	public String getMensajeConfirmacionSalvar()
	{
		return is_mensajeConfirmacionSalvar;
	}

	/**
	 * Indica si se está agregando una anotación.
	 * @return Variable boolean que indica si se está creando una anotación.
	 */
	public boolean isNuevaAnotacion()
	{
		return getIdAnotacionGeneral() < 0;
	}

	/**
	 * @param aps_ps Modifica el valor de la propiedad
	 */
	public void setPredioValidarAnotaciones(PredioSegregado aps_ps)
	{
		ips_predioValidarAnotaciones = aps_ps;
	}

	/**
	 * @return Retorna el valor de la propiedad
	 */
	public PredioSegregado getPredioValidarAnotaciones()
	{
		return ips_predioValidarAnotaciones;
	}

	/**
	 * @param Modifica el valor de la propiedad tabActual por tabActual
	 */
	public void setTabActual(int ai_i)
	{
		ii_tabActual = ai_i;
	}

	/**
	 * @return Retorna el valor de la propiedad tabActual
	 */
	public int getTabActual()
	{
		return ii_tabActual;
	}

	/**
	 * Retorna el valor del objeto de byte[] correspondiente al predial.
	 *
	 * @param aba_excel correspondiente al valor del tipo de objeto byte[]
	 * @param as_nameFile correspondiente al valor del tipo de objeto String
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_solicitud correspondiente al valor del tipo de objeto String
	 * @param ab_correccionAnotacion Variable boolean que valida si el cargue es para la anotación o para el predio matriz.
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 * @see byte[]
	 */
	public byte[] FileLoadPredial(
	    byte[] aba_excel, String as_nameFile, String as_userId, String as_solicitud, boolean ab_correccionAnotacion
	)
	    throws B2BException, IOException
	{
		try
		{
			if(aba_excel != null)
			{
				byte[]                 aba_archivo;
				MatriculaSegregacionUi loms_dataFile;

				aba_archivo       = aba_excel;
				loms_dataFile     = new MatriculaSegregacionUi(getColumns());

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
							PermisosCorreccionesUI lpcui_data;

							lpcui_data = getPermisosCorrecciones();

							if((lpcui_data != null) || ab_correccionAnotacion)
							{
								Anotacion                  la_anotacion;
								PanelMatriculasSegregacion lpms_panel;

								la_anotacion     = getAnotacion();
								lpms_panel       = lpcui_data.getMatriculasSegregacion();

								if((lpms_panel != null) || (la_anotacion != null))
								{
									boolean lb_titulosValidos;
									int     li_numcol;
									int     li_cantidadPredios;
									int     li_cantidadPrediosExcel;

									li_numcol                   = 7;
									li_cantidadPredios          = ab_correccionAnotacion
										? la_anotacion.getCantidadAperturar()
										: NumericUtils.getInt(lpms_panel.getCantidad());
									li_cantidadPrediosExcel     = 0;
									lb_titulosValidos           = true;

									for(int ii_fila = 0; ii_fila <= li_ultimaFila; ii_fila++)
									{
										Row           lr_filaActual;
										StringBuilder lsb_mensaje;

										lr_filaActual     = lsh_hoja.getRow(ii_fila);
										lsb_mensaje       = new StringBuilder();

										if(lr_filaActual != null)
										{
											int li_lastCell;

											li_lastCell = NumericUtils.getInt(lr_filaActual.getLastCellNum());

											if((li_primeraFila != 1) && lb_titulosValidos)
											{
												if(ExcelUtils.isValidRow(lr_filaActual, li_numcol))
												{
													boolean lb_filaValida;
													Long    ll_unidad;
													Long    ll_cantidadDeCertificados;
													String  ls_nombre;
													Double  ld_areaTerreno;
													Double  ld_areaPrivada;
													Double  ld_areaConstruida;
													Double  ld_coeficientePropiedad;

													lb_filaValida                 = false;
													ll_unidad                     = null;
													ll_cantidadDeCertificados     = null;
													ls_nombre                     = null;
													ld_areaTerreno                = null;
													ld_areaPrivada                = null;
													ld_areaConstruida             = null;
													ld_coeficientePropiedad       = null;

													for(int li_celda = 0; li_celda < li_numcol; li_celda++)
													{
														try
														{
															if(li_celda == 0)
															{
																ll_unidad = ExcelUtils.validarLongCeldaExcel(
																	    lr_filaActual, ii_fila + 1, li_celda,
																	    IdentificadoresCommon.UNIDAD, true
																	);
																loms_dataFile.setUnidad(ll_unidad);

																lb_filaValida = true;
															}
															else if(li_celda == 1)
															{
																ls_nombre = ExcelUtils.validarStringCeldaExcel(
																	    lr_filaActual, ii_fila + 1, li_celda,
																	    IdentificadoresCommon.NOMBRE, true
																	);
																loms_dataFile.setNombrePredio(ls_nombre);

																lb_filaValida = true;
															}
															else if(li_celda == 2)
															{
																ld_areaTerreno = ExcelUtils.validarDoubleCeldaExcel(
																	    lr_filaActual, ii_fila + 1, li_celda,
																	    IdentificadoresCommon.AREA_TERRENO, false
																	);
																loms_dataFile.setAreaTerreno(ld_areaTerreno);

																lb_filaValida = true;
															}
															else if(li_celda == 3)
															{
																ld_areaPrivada = ExcelUtils.validarDoubleCeldaExcel(
																	    lr_filaActual, ii_fila + 1, li_celda,
																	    IdentificadoresCommon.AREA_PRIVADA, false
																	);
																loms_dataFile.setAreaPrivada(ld_areaPrivada);

																lb_filaValida = true;
															}
															else if(li_celda == 4)
															{
																ld_areaConstruida = ExcelUtils.validarDoubleCeldaExcel(
																	    lr_filaActual, ii_fila + 1, li_celda,
																	    IdentificadoresCommon.AREA_CONSTRUIDA, false
																	);
																loms_dataFile.setAreaConstruida(ld_areaConstruida);

																lb_filaValida = true;
															}
															else if(li_celda == 5)
															{
																ld_coeficientePropiedad = ExcelUtils.validarPorcentaje(
																	    lr_filaActual, ii_fila + 1, li_celda,
																	    IdentificadoresCommon.COEFICIENTE_DE_PROPIEDAD,
																	    false
																	);
																loms_dataFile.setCoeficiente(ld_coeficientePropiedad);

																lb_filaValida = true;
															}
															else if(li_celda == 6)
															{
																ll_cantidadDeCertificados = ExcelUtils
																		.validarLongCeldaExcel(
																		    lr_filaActual, ii_fila + 1, li_celda, 0L,
																		    IdentificadoresCommon.CANTIDAD_DE_CERTIFICADOS,
																		    false
																		);
																loms_dataFile.setCantidadCertificados(
																    (!NumericUtils.isValidLong(
																        ll_cantidadDeCertificados
																    )) ? NumericUtils.getLongWrapper(0L)
																       : ll_cantidadDeCertificados
																);

																lb_filaValida = true;
															}
														}
														catch(B2BException ab2be_e)
														{
															lsb_mensaje.append(ab2be_e.getMessage());
														}
													}

													if(lb_filaValida)
														li_cantidadPrediosExcel++;

													if(!StringUtils.isValidString(lsb_mensaje.toString()))
													{
														lsb_mensaje.append(
														    processCellPredial(
														        ll_unidad, loms_dataFile, as_userId, as_solicitud
														    )
														);
														loms_dataFile = new MatriculaSegregacionUi(getColumns());
													}
												}
											}
											else
											{
												if(lb_titulosValidos)
												{
													if(li_lastCell == 0)
														li_lastCell = li_numcol;

													for(int li_celda = 0; li_celda < li_lastCell; li_celda++)
													{
														try
														{
															Cell   lc_tmp;
															String ls_tmp;

															lc_tmp     = lr_filaActual.getCell(li_celda);
															ls_tmp     = null;

															if(lc_tmp != null)
															{
																if(li_celda == 0)
																{
																	if(lc_tmp.getCellType() == Cell.CELL_TYPE_STRING)
																		ls_tmp = lc_tmp.getStringCellValue();

																	if(
																	    !StringUtils.isValidString(ls_tmp)
																		    || !ls_tmp.equalsIgnoreCase(
																		        IdentificadoresCommon.UNIDAD
																		    )
																	)
																		throw new B2BException(
																		    MessagesKeys.LA_COLUMNA + (li_celda + 1)
																		    + MessagesKeys.DEBE_LLAMARSE
																		    + IdentificadoresCommon.UNIDAD
																		    + IdentificadoresCommon.PUNTO
																		);
																}

																else if(li_celda == 1)
																{
																	if(lc_tmp.getCellType() == Cell.CELL_TYPE_STRING)
																		ls_tmp = lc_tmp.getStringCellValue();

																	if(
																	    !StringUtils.isValidString(ls_tmp)
																		    || !ls_tmp.equalsIgnoreCase(
																		        IdentificadoresCommon.NOMBRE
																		    )
																	)
																		throw new B2BException(
																		    MessagesKeys.LA_COLUMNA + (li_celda + 1)
																		    + MessagesKeys.DEBE_LLAMARSE
																		    + IdentificadoresCommon.NOMBRE
																		    + IdentificadoresCommon.PUNTO
																		);
																}

																else if(li_celda == 2)
																{
																	if(lc_tmp.getCellType() == Cell.CELL_TYPE_STRING)
																		ls_tmp = lc_tmp.getStringCellValue();

																	if(
																	    !StringUtils.isValidString(ls_tmp)
																		    || !ls_tmp.equalsIgnoreCase(
																		        IdentificadoresCommon.AREA_TERRENO
																		    )
																	)
																		throw new B2BException(
																		    MessagesKeys.LA_COLUMNA + (li_celda + 1)
																		    + MessagesKeys.DEBE_LLAMARSE
																		    + IdentificadoresCommon.AREA_TERRENO
																		    + IdentificadoresCommon.PUNTO
																		);
																}

																else if(li_celda == 3)
																{
																	if(lc_tmp.getCellType() == Cell.CELL_TYPE_STRING)
																		ls_tmp = lc_tmp.getStringCellValue();

																	if(
																	    !StringUtils.isValidString(ls_tmp)
																		    || !ls_tmp.equalsIgnoreCase(
																		        IdentificadoresCommon.AREA_PRIVADA
																		    )
																	)
																		throw new B2BException(
																		    MessagesKeys.LA_COLUMNA + (li_celda + 1)
																		    + MessagesKeys.DEBE_LLAMARSE
																		    + IdentificadoresCommon.AREA_PRIVADA
																		    + IdentificadoresCommon.PUNTO
																		);
																}

																else if(li_celda == 4)
																{
																	if(lc_tmp.getCellType() == Cell.CELL_TYPE_STRING)
																		ls_tmp = lc_tmp.getStringCellValue();

																	if(
																	    !StringUtils.isValidString(ls_tmp)
																		    || !ls_tmp.equalsIgnoreCase(
																		        IdentificadoresCommon.AREA_CONSTRUIDA
																		    )
																	)
																		throw new B2BException(
																		    MessagesKeys.LA_COLUMNA + (li_celda + 1)
																		    + MessagesKeys.DEBE_LLAMARSE
																		    + IdentificadoresCommon.AREA_CONSTRUIDA
																		    + IdentificadoresCommon.PUNTO
																		);
																}

																else if(li_celda == 5)
																{
																	if(lc_tmp.getCellType() == Cell.CELL_TYPE_STRING)
																		ls_tmp = lc_tmp.getStringCellValue();

																	if(
																	    !StringUtils.isValidString(ls_tmp)
																		    && ls_tmp.equalsIgnoreCase(
																		        IdentificadoresCommon.COEFICIENTE_DE_PROPIEDAD
																		    )
																	)
																		throw new B2BException(
																		    MessagesKeys.LA_COLUMNA + (li_celda + 1)
																		    + MessagesKeys.DEBE_LLAMARSE
																		    + IdentificadoresCommon.COEFICIENTE_DE_PROPIEDAD
																		    + IdentificadoresCommon.PUNTO
																		);
																}
																else if(li_celda == 6)
																{
																	if(lc_tmp.getCellType() == Cell.CELL_TYPE_STRING)
																		ls_tmp = lc_tmp.getStringCellValue();

																	if(
																	    !StringUtils.isValidString(ls_tmp)
																		    && ls_tmp.equalsIgnoreCase(
																		        IdentificadoresCommon.CANTIDAD_DE_CERTIFICADOS
																		    )
																	)
																		throw new B2BException(
																		    MessagesKeys.LA_COLUMNA + (li_celda + 1)
																		    + MessagesKeys.DEBE_LLAMARSE
																		    + IdentificadoresCommon.CANTIDAD_DE_CERTIFICADOS
																		    + IdentificadoresCommon.PUNTO
																		);
																}
																else
																	lb_titulosValidos = false;
															}
														}
														catch(B2BException ab2be_e)
														{
															lsb_mensaje.append(ab2be_e.getMessage());
														}
													}
												}
											}

											{
												CellStyle lcs_estiloCelda;

												lcs_estiloCelda = lw_libro.createCellStyle();

												lcs_estiloCelda.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);

												ExcelUtils.creaCelda(
												    lr_filaActual, lb_titulosValidos ? li_numcol : li_lastCell,
												    lsb_mensaje.toString(), lcs_estiloCelda
												);

												lsh_hoja.autoSizeColumn(li_numcol);
											}
										}

										li_primeraFila++;
									}

									if(lb_titulosValidos)
									{
										if(li_cantidadPredios != li_cantidadPrediosExcel)
										{
											Object[] aoa_messageArgs = new String[2];
											aoa_messageArgs[0]     = StringUtils.getString(li_cantidadPrediosExcel);
											aoa_messageArgs[1]     = StringUtils.getString(li_cantidadPredios);

											throw new B2BException(
											    ErrorKeys.ERROR_CANTIDAD_REGISTROS_EXCEL, aoa_messageArgs
											);
										}
									}
									else
										throw new B2BException(ErrorKeys.ERROR_ESTRUCTURA_EXCEL_INCORRECTA);

									{
										ByteArrayOutputStream lbaos_output;

										lbaos_output = new ByteArrayOutputStream();

										ExcelUtils.write(lw_libro, lbaos_output);

										aba_excel = lbaos_output.toByteArray();
									}
								}
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
			clh_LOGGER.error(lb2be_e);

			setDataInfoPredial(null);

			throw lb2be_e;
		}

		return aba_excel;
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

	/**
	 * Método encargado de actualziar el formulario
	 */
	public void actualizarFormulario()
	{
		actualizarComponente(cs_ID_FORMULARIO);
	}

	/**
	 * Metodo encargado de limpiar los datos del detalle de la anotación corrección
	 * para agregar una nueva.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarAnotacionCorreccion()
	    throws B2BException
	{
		try
		{
			limpiarDetalleAnotaciones();

			getDocumento();

			cargarTiposOficinaAnotaciones(EstadoCommon.A);
			cargarListasDesplegablesDocumento();

			setBloquearModificarIntervenientes(false);
			setMostrarBandejaAnotaciones(false);
			setMostrarDetalleAnotaciones(true);
			setMostrarBotonSalvarAnotacion(true);
			setIdAnotacionGeneral(-1L);
			setAnotacionApertura(false);
			setAnotacionEnglobe(false);

			{
				AnotacionPredio lap_anotacionPredio;

				lap_anotacionPredio = new AnotacionPredio();

				lap_anotacionPredio.setIdCirculo(getIdCirculo());
				lap_anotacionPredio.setIdMatricula(getIdMatricula());
				lap_anotacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));

				{
					int li_max;

					li_max = iasr_antiguoSistemaRemote.consultarMaxIdAnotacion(
						    lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					setNumeroAnotacion(NumericUtils.getLongWrapper(li_max + 1));
				}

				{
					int li_max;

					li_max = iasr_antiguoSistemaRemote.consultarMaxOrden(
						    lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(li_max > 0)
						setNumeroOrden(NumericUtils.getLongWrapper(li_max + 1));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarAnotacionCorreccion", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}
	}

	/**
	 * Método encargado de agregar area del terreno.
	 */
	public void agregarAreaTerreno()
	{
		try
		{
			AccAreaUI         laaui_dataArea;
			DetalleAreaPredio ladap_detalleArea;

			laaui_dataArea        = getAreaUI();
			ladap_detalleArea     = getDetalleAreaTerreno();

			if((laaui_dataArea != null) && (ladap_detalleArea != null))
			{
				boolean      lb_focus;
				BigInteger   lbi_count;
				FacesContext lfc_context;
				String       ls_area;
				String       ls_pantalla;

				lb_focus        = false;
				lbi_count       = laaui_dataArea.getIdDetalleAreaPredio();
				lfc_context     = FacesContext.getCurrentInstance();
				ls_area         = ladap_detalleArea.getAreaLectura();
				ls_pantalla     = ":fEjecucionCorreccion:TvdetalleRegistroCalif:";
				lb_focus        = validateStyles(ls_pantalla + "idITareaTerrenoTabs", lfc_context, ls_area, lb_focus);

				if(StringUtils.isValidString(ls_area))
				{
					Double ld_area;
					String ls_medidaArea;

					ld_area           = NumericUtils.getDoubleWrapper(ls_area);
					ls_medidaArea     = ladap_detalleArea.getIdUnidadMedida();

					if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
					{
						String ls_areaTerreno;
						ls_areaTerreno     = StringUtils.getString(ld_area);

						lb_focus = validateStyles(
							    ls_pantalla + "idITareaTerrenoTabs", lfc_context, ls_areaTerreno, lb_focus
							);
						ladap_detalleArea.setArea(ld_area);
					}
					else
					{
						lb_focus = validateStyles(ls_pantalla + "idITareaTerrenoTabs", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
					}

					lb_focus = validateStyles(
						    ls_pantalla + "idSOMunidadMedidaTerrenoTabs", lfc_context, ls_medidaArea, lb_focus
						);

					if(StringUtils.isValidString(ls_medidaArea))
					{
						Collection<DetalleAreaPredio> lcadap_areas;

						lcadap_areas = laaui_dataArea.getAreasTerreno();

						if(CollectionUtils.isValidCollection(lcadap_areas))
						{
							Iterator<DetalleAreaPredio> liadap_iterator;

							liadap_iterator = lcadap_areas.iterator();

							while(liadap_iterator.hasNext())
							{
								DetalleAreaPredio ladap_areaTerreno;

								ladap_areaTerreno = liadap_iterator.next();

								if(ladap_areaTerreno != null)
								{
									String ls_idDetalleAreaPredio;

									ls_idDetalleAreaPredio = ladap_areaTerreno.getIdDetalleAreaPredio();

									if(StringUtils.isValidString(ls_idDetalleAreaPredio))
									{
										BigInteger lbi_idDetalleAreaPredio;
										String     ls_medidaAreaIterador;

										ls_medidaAreaIterador       = ladap_areaTerreno.getIdUnidadMedida();
										lbi_idDetalleAreaPredio     = NumericUtils.getBigInteger(
											    ls_idDetalleAreaPredio
											);

										lbi_count = (lbi_idDetalleAreaPredio.compareTo(lbi_count) > 0)
											? lbi_idDetalleAreaPredio : lbi_count;

										if(StringUtils.isValidString(ls_medidaAreaIterador))
										{
											if(ls_medidaAreaIterador.equalsIgnoreCase(ls_medidaArea))
											{
												MedidaArea lma_medidaArea;
												Object[]   aoa_messageArgs;
												String     ls_medidaAreaNombre;

												lma_medidaArea = ipr_parameterRemote.findMedidaAreaById(
													    ls_medidaAreaIterador
													);

												if(lma_medidaArea == null)
													throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);

												ls_medidaAreaNombre     = lma_medidaArea.getDescripcion();
												aoa_messageArgs         = new String[1];
												aoa_messageArgs[0]      = ls_medidaAreaNombre;
												lb_focus                = validateStyles(
													    ls_pantalla + "idSOMunidadMedidaTerrenoTabs", lfc_context, "",
													    lb_focus
													);

												throw new B2BException(
												    ErrorKeys.ERROR_UNIDAD_MEDIDA_DUPLICADO, aoa_messageArgs
												);
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);
									}
								}
							}
						}

						lbi_count = lbi_count.add(BigInteger.ONE);

						ladap_detalleArea.setIdTipoArea(TipoAreaCommon.TERRENO);
						ladap_detalleArea.setIdDetalleAreaPredio(StringUtils.getString(lbi_count));
						lcadap_areas.add(ladap_detalleArea);
						laaui_dataArea.setIdDetalleAreaPredio(lbi_count);
						laaui_dataArea.setAreasTerreno(lcadap_areas);

						setDetalleAreaTerreno(null);
						setAreaUI(laaui_dataArea);
						actualizarAreaTerreno();
					}
					else
						throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}
	}

	/**
	 * Prepara la pantalla para agregar un detalle ant sistema.
	 */
	public void agregarDetalleAntSistema()
	{
		setGuardarDetalleAnt(true);

		{
			DatosAntSistema ldas_datos;

			ldas_datos = getDatoAntSistemaCargado();

			if(ldas_datos != null)
			{
				DetalleAntSistema ldas_detalle;

				ldas_detalle = new DetalleAntSistema();

				ldas_detalle.setIdDatosAntSistema(ldas_datos.getIdDatosAntSistema());

				setDetalleAntSistemaCargado(ldas_detalle);
			}
			else
				setDetalleAntSistemaCargado(null);
		}

		setDocumentoDetalleCargado(new Documento());

		abrirDialogo("detallePredio", cs_ID_FORMULARIO);
	}

	/**
	 * Método encargado de validar el formulario de direccion del predio a ser agregada.
	 */
	public void agregarDireccionAnotacion()
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
			clh_LOGGER.error("agregarDireccionAnotacion", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de agregar intervinientes nuevos.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarIntervinientesAnotacion()
	    throws B2BException
	{
		agregarIntervinientesData(false, true);
		actualizarComponente(cs_ID_GROWL);
	}

	/**
	 * Agregar segregacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void agregarSegregacion()
	    throws B2BException
	{
		try
		{
			PredioSegregado lps_predioAgregar;

			lps_predioAgregar = getPredioSegregadoAgregar();

			if(lps_predioAgregar != null)
			{
				boolean                     lb_focus;
				Collection<PredioSegregado> lcps_predios;
				FacesContext                lfc_context;
				Long                        ll_idMatricula;
				PredioRegistro              lpr_predio;
				String                      ls_idCirculo;

				lb_focus           = true;
				lcps_predios       = getPrediosSegregados();
				lfc_context        = FacesContext.getCurrentInstance();
				ll_idMatricula     = lps_predioAgregar.getIdMatricula1();
				lpr_predio         = new PredioRegistro();
				ls_idCirculo       = lps_predioAgregar.getIdCirculo1();

				lb_focus = validateStyles(
					    "idSOMAgregarMatriculaSegregacionIdCirculo", lfc_context, ls_idCirculo, lb_focus
					);

				if(!StringUtils.isValidString(ls_idCirculo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				else
					lpr_predio.setIdCirculo(ls_idCirculo);

				lb_focus = validateStyles("idSOMAnotacionesSegregacion", lfc_context, ll_idMatricula, lb_focus);

				if(!NumericUtils.isValidLong(ll_idMatricula))
					throw new B2BException(ErrorKeys.INGRESE_MATRICULA_VALIDA);
				else
					lpr_predio.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

				lpr_predio = irr_registroRemote.findPredioRegistroByCirculoMatricula(lpr_predio);

				if(lpr_predio == null)
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ll_idMatricula;

					throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA, loa_arg);
				}

				if(StringUtils.getStringNotNull(lpr_predio.getPredioInconsistente()).equalsIgnoreCase(EstadoCommon.S))
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ll_idMatricula;

					throw new B2BException(
					    ErrorKeys.PREDIO_SE_ENCUENTRA_ESTADO_INCONSISTENTE_NO_SE_PUEDE_REALIZAR_CORRECCION, loa_arg
					);
				}

				{
					Long ll_dato;

					ll_dato      = lps_predioAgregar.getIdAnotacion();
					lb_focus     = validateStyles("idSOMAnotaciones2Segregacion", lfc_context, ll_dato, lb_focus);

					if(!NumericUtils.isValidLong(ll_dato))
						throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);
				}

				{
					String ls_dato;

					ls_dato = lps_predioAgregar.getLote();

					if(StringUtils.isValidString(ls_dato))
					{
						ls_dato = ls_dato.toUpperCase();

						lps_predioAgregar.setLote(ls_dato);
					}
				}

				{
					String ls_dato;

					ls_dato = lps_predioAgregar.getDescripcion();

					if(StringUtils.isValidString(ls_dato))
					{
						ls_dato = ls_dato.toUpperCase();

						lps_predioAgregar.setDescripcion(ls_dato);
					}
				}

				lps_predioAgregar.setSeleccionado(true);
				lps_predioAgregar.setEditado(true);
				lps_predioAgregar.setAgregado(true);

				if(!CollectionUtils.isValidCollection(lcps_predios))
					lcps_predios = new ArrayList<PredioSegregado>();

				lcps_predios.add(lps_predioAgregar);

				lps_predioAgregar = new PredioSegregado();

				lps_predioAgregar.setIdCirculo(getIdCirculo());
				lps_predioAgregar.setIdCirculo1(getIdCirculo());
				lps_predioAgregar.setIdMatricula(getIdMatricula());
				lps_predioAgregar.setIdMatricula1(null);
				lps_predioAgregar.setAnotaciones(null);
				lps_predioAgregar.setAnotacionesSegregadas(null);
				lps_predioAgregar.setIdAnotacion(null);
				lps_predioAgregar.setIdAnotacion1(null);
				lps_predioAgregar.setLote(null);
				lps_predioAgregar.setDescripcion(null);

				setPrediosSegregados(lcps_predios);
				setPredioSegregadoAgregar(lps_predioAgregar);

				setCierreFolio(irr_calificacionRemote.cierreFolio(lcps_predios));
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Agregar segregacion con base.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void agregarSegregacionConBase()
	    throws B2BException
	{
		try
		{
			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				PredioSegregado lps_predioAgregar;

				lps_predioAgregar = ldb_datosBasicos.getMatriculaBase();

				if(lps_predioAgregar != null)
				{
					boolean                     lb_focus;
					Collection<PredioSegregado> lcps_predios;
					FacesContext                lfc_context;
					Long                        ll_idMatricula;
					PredioRegistro              lpr_predio;
					String                      ls_idCirculo;

					lb_focus           = true;
					lcps_predios       = ldb_datosBasicos.getPredioSegregado();
					lfc_context        = FacesContext.getCurrentInstance();
					ll_idMatricula     = lps_predioAgregar.getIdMatricula();
					lpr_predio         = new PredioRegistro();
					ls_idCirculo       = lps_predioAgregar.getIdCirculo();

					lb_focus = validateStyles(
						    "idSOMAgregarMatriculaBaseIdCirculo", lfc_context, ls_idCirculo, lb_focus
						);

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
					else
						lpr_predio.setIdCirculo(ls_idCirculo);

					lb_focus = validateStyles("idSOMAnotacionesSegregacionBase", lfc_context, ll_idMatricula, lb_focus);

					if(!NumericUtils.isValidLong(ll_idMatricula))
						throw new B2BException(ErrorKeys.INGRESE_MATRICULA_VALIDA);
					else
						lpr_predio.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

					lpr_predio = irr_registroRemote.findPredioRegistroByCirculoMatricula(lpr_predio);

					if(lpr_predio == null)
					{
						Object[] loa_arg;

						loa_arg        = new String[1];
						loa_arg[0]     = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ll_idMatricula;

						throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA, loa_arg);
					}

					if(
					    StringUtils.getStringNotNull(lpr_predio.getPredioInconsistente())
						               .equalsIgnoreCase(EstadoCommon.S)
					)
					{
						Object[] loa_arg;

						loa_arg        = new String[1];
						loa_arg[0]     = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ll_idMatricula;

						throw new B2BException(
						    ErrorKeys.PREDIO_SE_ENCUENTRA_ESTADO_INCONSISTENTE_NO_SE_PUEDE_REALIZAR_CORRECCION, loa_arg
						);
					}

					{
						Long ll_dato;

						ll_dato      = lps_predioAgregar.getIdAnotacion();
						lb_focus     = validateStyles(
							    "idSOMAnotaciones2SegregacionBase", lfc_context, ll_dato, lb_focus
							);

						if(!NumericUtils.isValidLong(ll_dato))
							throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);
					}

					{
						String ls_dato;

						ls_dato = lps_predioAgregar.getLote();

						if(StringUtils.isValidString(ls_dato))
						{
							ls_dato = ls_dato.toUpperCase();

							lps_predioAgregar.setLote(ls_dato);
						}
					}

					{
						String ls_dato;

						ls_dato = lps_predioAgregar.getDescripcion();

						if(StringUtils.isValidString(ls_dato))
						{
							ls_dato = ls_dato.toUpperCase();

							lps_predioAgregar.setDescripcion(ls_dato);
						}
					}

					lps_predioAgregar.setSeleccionado(true);
					lps_predioAgregar.setEditado(true);
					lps_predioAgregar.setAgregado(true);

					if(!CollectionUtils.isValidCollection(lcps_predios))
						lcps_predios = new ArrayList<PredioSegregado>();

					lcps_predios.add(lps_predioAgregar);

					lps_predioAgregar = new PredioSegregado();

					lps_predioAgregar.setIdCirculo1(getIdCirculo());
					lps_predioAgregar.setIdCirculo(getIdCirculo());
					lps_predioAgregar.setIdMatricula1(getIdMatricula());
					lps_predioAgregar.setIdMatricula(null);
					lps_predioAgregar.setAnotaciones(null);
					lps_predioAgregar.setAnotacionesSegregadas(null);
					lps_predioAgregar.setIdAnotacion(null);
					lps_predioAgregar.setIdAnotacion1(null);
					lps_predioAgregar.setLote(null);
					lps_predioAgregar.setDescripcion(null);

					ldb_datosBasicos.setPredioSegregado(lcps_predios);
					ldb_datosBasicos.setMatriculaBase(new MatriculaBase(lps_predioAgregar));

					setDatosBasicos(ldb_datosBasicos);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Aperturar matriculas segregacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void aperturarMatriculasSegregacion()
	    throws B2BException
	{
		try
		{
			PermisosCorreccionesUI lpcui_data;

			lpcui_data = getPermisosCorrecciones();

			if(lpcui_data != null)
			{
				PanelMatriculasSegregacion lp_panel;

				lp_panel = lpcui_data.getMatriculasSegregacion();

				if(lp_panel != null)
				{
					boolean                          lb_masivo;
					Collection<MatriculaSegregacion> lcms_data;

					lb_masivo = lp_panel.isSegregacionMasiva();

					if(lb_masivo)
					{
						Collection<MatriculaSegregacionUi> lcmsui_dataInfoPredial;

						lcms_data                  = new ArrayList<MatriculaSegregacion>();
						lcmsui_dataInfoPredial     = getDataInfoPredial();

						if(CollectionUtils.isValidCollection(lcmsui_dataInfoPredial))
						{
							for(MatriculaSegregacionUi lmsui_iterador : lcmsui_dataInfoPredial)
							{
								if(lmsui_iterador != null)
								{
									MatriculaSegregacion lms_dato;

									lms_dato = new MatriculaSegregacion();

									lms_dato.setNombrePredio(lmsui_iterador.getNombrePredio());
									lms_dato.setAreaConstruida(lmsui_iterador.getAreaConstruida());
									lms_dato.setAreaPrivada(lmsui_iterador.getAreaPrivada());
									lms_dato.setAreaTerreno(lmsui_iterador.getAreaTerreno());
									lms_dato.setCoeficiente(lmsui_iterador.getCoeficiente());
									lms_dato.setCantidadCertificados(lmsui_iterador.getCantidadCertificados());

									lcms_data.add(lms_dato);
								}
							}
						}
					}
					else
						lcms_data = lp_panel.getMatriculasSegregacion();

					if(CollectionUtils.isValidCollection(lcms_data))
					{
						Iterator<MatriculaSegregacion> lims_iterator;
						RegistroCalificacion           lrc_data;

						lims_iterator     = lcms_data.iterator();
						lrc_data          = new RegistroCalificacion();

						while(lims_iterator.hasNext())
						{
							MatriculaSegregacion lms_matriculaSegregacion;

							lms_matriculaSegregacion = lims_iterator.next();

							if(lms_matriculaSegregacion != null)
							{
								String ls_nombrePredio;
								String ls_areaTerreno;

								ls_nombrePredio     = lms_matriculaSegregacion.getNombrePredio();
								ls_areaTerreno      = lb_masivo
									? StringUtils.getString(lms_matriculaSegregacion.getAreaTerreno())
									: lms_matriculaSegregacion.getAreaTerrenoLectura();

								if(!StringUtils.isValidString(ls_nombrePredio))
									throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_PREDIO);

								if(!StringUtils.isValidString(ls_areaTerreno))
									throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
							}
						}

						lrc_data.setTurno(getIdTurno());
						lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						lrc_data.setInfoMatriculasSegregacion(lcms_data);
						lrc_data.setIdMatricula(getIdMatricula());
						lrc_data.setIdCirculo(getIdCirculo());

						lrc_data = irr_calificacionRemote.aperturarMatriculasSegregacion(
							    lrc_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(lrc_data != null)
						{
							Collection<AreaPredio> lcap_matriculasInfo;

							lcap_matriculasInfo = lrc_data.getMatriculasInformacion();

							if(CollectionUtils.isValidCollection(lcap_matriculasInfo))
							{
								lp_panel.setMatriculasAperturadas(true);
								lp_panel.setMatriculasInformacion(lrc_data.getMatriculasInformacion());
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Aperturar matriculas segregacion anotación.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void aperturarMatriculasSegregacionAnotacion()
	    throws B2BException
	{
		try
		{
			Anotacion la_anotacion;

			la_anotacion = getAnotacion();

			if(la_anotacion != null)
			{
				boolean                          lb_masivo;
				Collection<MatriculaSegregacion> lcms_data;

				lb_masivo = la_anotacion.isSegregacionMasiva();

				if(lb_masivo)
				{
					Collection<MatriculaSegregacionUi> lcmsui_dataInfoPredial;

					lcms_data                  = new ArrayList<MatriculaSegregacion>();
					lcmsui_dataInfoPredial     = getDataInfoPredial();

					if(CollectionUtils.isValidCollection(lcmsui_dataInfoPredial))
					{
						for(MatriculaSegregacionUi lmsui_iterador : lcmsui_dataInfoPredial)
						{
							if(lmsui_iterador != null)
							{
								MatriculaSegregacion lms_dato;

								lms_dato = new MatriculaSegregacion();

								lms_dato.setNombrePredio(lmsui_iterador.getNombrePredio());
								lms_dato.setAreaConstruida(lmsui_iterador.getAreaConstruida());
								lms_dato.setAreaPrivada(lmsui_iterador.getAreaPrivada());
								lms_dato.setAreaTerreno(lmsui_iterador.getAreaTerreno());
								lms_dato.setCoeficiente(lmsui_iterador.getCoeficiente());
								lms_dato.setCantidadCertificados(lmsui_iterador.getCantidadCertificados());
								lms_dato.setIdAnotacionApertura(StringUtils.getString(getNumeroAnotacion()));

								lcms_data.add(lms_dato);
							}
						}
					}
				}
				else
					lcms_data = la_anotacion.getMatriculasSegregacion();

				if(CollectionUtils.isValidCollection(lcms_data))
				{
					Iterator<MatriculaSegregacion> lims_iterator;
					RegistroCalificacion           lrc_data;
					String                         ls_idTurno;

					lims_iterator     = lcms_data.iterator();
					lrc_data          = new RegistroCalificacion();
					ls_idTurno        = getIdTurno();

					while(lims_iterator.hasNext())
					{
						MatriculaSegregacion lms_matriculaSegregacion;

						lms_matriculaSegregacion = lims_iterator.next();

						if(lms_matriculaSegregacion != null)
						{
							String ls_nombrePredio;
							String ls_areaTerreno;

							ls_nombrePredio     = lms_matriculaSegregacion.getNombrePredio();
							ls_areaTerreno      = lb_masivo
								? StringUtils.getString(lms_matriculaSegregacion.getAreaTerreno())
								: lms_matriculaSegregacion.getAreaTerrenoLectura();

							if(!StringUtils.isValidString(ls_nombrePredio))
								throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_PREDIO);

							if(!StringUtils.isValidString(ls_areaTerreno))
								throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
						}
					}

					if(!StringUtils.isValidString(ls_idTurno))
					{
						Map<String, Object> lmso_data;

						lmso_data = getPredio();

						if((lmso_data != null) && lmso_data.containsKey("ID_TURNO"))
							ls_idTurno = (String)lmso_data.get("ID_TURNO");
					}

					lrc_data.setTurno(ls_idTurno);
					lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
					lrc_data.setInfoMatriculasSegregacion(lcms_data);
					lrc_data.setIdMatricula(getIdMatricula());
					lrc_data.setIdCirculo(getIdCirculo());
					lrc_data.setIdAnotacionApertura(StringUtils.getString(getNumeroAnotacion()));

					lrc_data = irr_calificacionRemote.aperturarMatriculasSegregacion(
						    lrc_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lrc_data != null)
					{
						Collection<AreaPredio> lcap_matriculasInfo;

						lcap_matriculasInfo = lrc_data.getMatriculasInformacion();

						if(CollectionUtils.isValidCollection(lcap_matriculasInfo))
						{
							la_anotacion.setMatriculasAperturadas(true);
							la_anotacion.setMatriculasInformacion(lrc_data.getMatriculasInformacion());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método encargado de cancelar la selección del código del acto
	 */
	public void cancelarActoSeleccionado()
	{
		setCodigoNaturalezaJuridicaSeleccionada(null);
		setNaturalezaJuridicaSeleccionada(null);
	}

	/**
	 * Cancelar anotacion apertura segregacion.
	 */
	public void cancelarAnotacionAperturaSegregacion()
	{
		PredioSegregado lps_predio;

		lps_predio = getPredioSegregadoAgregar();

		if(lps_predio != null)
		{
			if(isAnotacionMatriz())
				lps_predio.setIdAnotacion(null);
			else
				lps_predio.setIdAnotacion1(null);
		}
	}

	/**
	 * Cargar anotaciones matricula agregar.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarAnotacionesMatriculaAgregar()
	    throws B2BException
	{
		cargarAnotacionesMatriculaAgregar(false);
	}

	/**
	 * Cargar anotaciones matricula agregar.
	 *
	 * @param ab_conBase correspondiente al valor de ab con base
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarAnotacionesMatriculaAgregar(boolean ab_conBase)
	    throws B2BException
	{
		try
		{
			PredioSegregado lps_predioAgregar;

			lps_predioAgregar = null;

			if(ab_conBase)
			{
				DatosBasicos ldb_data;

				ldb_data = getDatosBasicos();

				if(ldb_data != null)
					lps_predioAgregar = ldb_data.getMatriculaBase();
			}
			else
				lps_predioAgregar = getPredioSegregadoAgregar();

			if(lps_predioAgregar != null)
			{
				lps_predioAgregar = irr_referenceRemote.cargarAnotacionesMatriculaAgregar(
					    lps_predioAgregar, ab_conBase
					);

				if(lps_predioAgregar != null)
				{
					if(ab_conBase)
					{
						DatosBasicos ldb_data;

						ldb_data = getDatosBasicos();

						if(ldb_data != null)
							ldb_data.setMatriculaBase(new MatriculaBase(lps_predioAgregar));
					}
					else
						setPredioSegregadoAgregar(lps_predioAgregar);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de modificar el campo anotación a cancelar.
	 */
	public void cargarCampoAnotacionACancelar()
	{
		Collection<RegistroCalificacion> lcrc_anotacionesACancelar;

		lcrc_anotacionesACancelar = getAnotacionACancelar();

		if(CollectionUtils.isValidCollection(lcrc_anotacionesACancelar))
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			for(RegistroCalificacion lrc_iterador : lcrc_anotacionesACancelar)
			{
				if((lrc_iterador != null) && lrc_iterador.isRevision())
				{
					if(lsb_sb.length() > 0)
						lsb_sb.append(",");

					lsb_sb.append(lrc_iterador.getIdAnotacion());
				}
			}

			{
				AnotacionCancelacion lac_anotacionCancelacion;

				lac_anotacionCancelacion = getAnotacionCancelacion();

				if(lac_anotacionCancelacion != null)
					lac_anotacionCancelacion.setAnotacion1String(lsb_sb.toString());
			}
		}
	}

	/**
	 * Método encargado de consultar la información del englobe para anotación.
	 *
	 * @param aa_anotacion Objeto que contiene la información de la anotación.
	 * @throws B2BException
	 */
	public void cargarInfoEnglobeAnotacion(Anotacion aa_anotacion)
	    throws B2BException
	{
		try
		{
			if(aa_anotacion != null)
			{
				RegistroCalificacion lrc_data;

				lrc_data = irr_calificacionRemote.cargarInfoEnglobeAnotacion(
					    aa_anotacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lrc_data != null)
				{
					aa_anotacion.setMatriculasEnglobeSeleccionadas(true);
					lrc_data.setFechaApertura(new Date());
					setMostrarSiguienteEnglobes(true);

					setMatriculas(lrc_data);

					cargarDatosBasicosEnglobes();

					cerrarPanelesAnotacion();
				}

				setAnotacion(aa_anotacion);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de cargar los datos de la oficina origen para documentos
	 * anotaciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarOficinaOrigenAnotaciones()
	    throws B2BException
	{
		cargarOficinaOrigen();
		actualizarComponente(cs_ID_GROWL);
	}

	/**
	 * Cierra paneles de anotación.
	 */
	public void cerrarPanelesAnotacion()
	{
		abrirCerrarPanel(false, "wPanelDatosAnotacion");
		abrirCerrarPanel(false, "wPanelDatosDocumento");
		abrirCerrarPanel(false, "wPanelDetalleAntSistemaAnotacion");
		abrirCerrarPanel(false, "wPanelEspecificacion");
		abrirCerrarPanel(false, "wPanelIntervinientes");
	}

	/**
	 * Limpia las variables de la clase.
	 */
	public void clean()
	{
		setGuardarDetalleAnt(false);
		setHabilitarAgregarDetalleAnt(false);
		setDetalleAntSisParaEliminar(null);
		setSecuenciaAgregada(false);
	}

	/**
	 * Método encargado de confirmar la cantidad de predios a aperturar por una anotación.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void confirmarCantidadAperturarAnotacion()
	    throws B2BException
	{
		try
		{
			Anotacion la_anotacion;

			la_anotacion = getAnotacion();

			if(la_anotacion != null)
			{
				irr_calificacionRemote.confirmarCantidadAperturarAnotacion(
				    la_anotacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				boolean                          lb_segregacionMasivo;
				Collection<MatriculaSegregacion> lcms_matriculasSegregacion;
				int                              li_cantidadAperturar;

				li_cantidadAperturar           = la_anotacion.getCantidadAperturar();
				lb_segregacionMasivo           = li_cantidadAperturar > 10;
				lcms_matriculasSegregacion     = new ArrayList<MatriculaSegregacion>();

				la_anotacion.setSegregacionMasiva(lb_segregacionMasivo);

				for(int li_count = 0; li_count < li_cantidadAperturar; li_count++)
					lcms_matriculasSegregacion.add(new MatriculaSegregacion());

				la_anotacion.setMatriculasSegregacion(lcms_matriculasSegregacion);

				setAnotacion(la_anotacion);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarFormulario();
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}
	}

	/**
	 * Método encargado de confirmar las matrículas seleccionadas para el englobe.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void confirmarMatriculasEnglobe()
	    throws B2BException
	{
		try
		{
			Anotacion la_anotacion;

			la_anotacion = getAnotacion();

			if(la_anotacion != null)
			{
				if(la_anotacion.isCierreFolio())
				{
					String ls_motivo;

					ls_motivo = la_anotacion.getMotivoCambioEstado();

					if(!StringUtils.isValidString(ls_motivo))
						throw new B2BException(ErrorKeys.ERROR_MOTIVO_CAMBIO_ESTADO);
					else
					{
						if(
						    !ls_motivo.equalsIgnoreCase(EstadoCommon.N)
							    && !StringUtils.isValidString(la_anotacion.getJustificacionCambio())
						)
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
					}
				}

				cargarInfoEnglobeAnotacion(
				    irr_calificacionRemote.confirmarMatriculasEnglobe(
				        getIdCirculo(), getIdMatricula(), getIdTurno(), la_anotacion, getUserId(), getLocalIpAddress(),
				        getRemoteIpAddress()
				    )
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarFormulario();
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}
	}

	/**
	 * Construir salvedad.
	 *
	 * @param la_anotacion correspondiente al valor del tipo de objeto Anotacion
	 */
	public void construirSalvedad(Anotacion la_anotacion)
	{
		try
		{
			if(la_anotacion != null)
			{
				PermisosCorreccionesUI lpc_correcciones;

				lpc_correcciones = getPermisosCorrecciones();

				if(lpc_correcciones != null)
				{
					String ls_complementoSalvedad;

					ls_complementoSalvedad = lpc_correcciones.getComplementoSalvedad();

					if(StringUtils.isValidString(ls_complementoSalvedad))
					{
						StringBuilder lsb_base;
						String        ls_espacio;
						String        ls_justificacion;

						lsb_base             = new StringBuilder(ConstanteCommon.SE_MODIFICAN_LOS_DATOS_DE);
						ls_espacio           = IdentificadoresCommon.ESPACIO_VACIO;
						ls_justificacion     = la_anotacion.getJustificacion();

						lsb_base.append(camposAnotacion(la_anotacion));

						if(StringUtils.isValidString(ls_justificacion))
						{
							ls_justificacion = ls_justificacion.toUpperCase();

							lsb_base.append((ls_espacio + ls_justificacion));
						}

						lsb_base.append((ls_espacio + ls_complementoSalvedad));

						la_anotacion.setJustificacion(ls_justificacion);
						la_anotacion.setSalvedad(lsb_base.toString().toUpperCase());
					}
					else
						throw new B2BException(ErrorKeys.COMPLEMENTO_SALVEDAD);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}
	}

	/**
	 * Construir salvedad.
	 *
	 * @param as_causal correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void construirSalvedad(String as_causal)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_causal))
			{
				PermisosCorreccionesUI lpc_correcciones;

				lpc_correcciones = getPermisosCorrecciones();

				if(lpc_correcciones != null)
				{
					String ls_complementoSalvedad;

					ls_complementoSalvedad = lpc_correcciones.getComplementoSalvedad();

					if(StringUtils.isValidString(ls_complementoSalvedad))
					{
						StringBuilder lsb_base;
						String        ls_espacio;

						lsb_base       = new StringBuilder(ConstanteCommon.SE_MODIFICAN_LOS_DATOS_DE);
						ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;

						switch(as_causal)
						{
							case CausalCorreccionCommon.DATOS_BASICOS:
							{
								PanelUbicacion lp_panel;

								lp_panel = lpc_correcciones.getUbicacion();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposDatosBasicosUbicacion(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setJustificacion(ls_justificacion);
									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.INFORMACION_DE_APERTURA:
							{
								PanelApertura lp_panel;

								lp_panel = lpc_correcciones.getApertura();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposDatosBasicosApertura(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setJustificacion(ls_justificacion);
									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS:
							{
								PanelMatriculasAbiertas lp_panel;

								lp_panel = lpc_correcciones.getMatriculasAbiertas();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposMatriculasAbiertas(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.INFORMACION_CATASTRAL:
							{
								PanelCatastral lp_panel;

								lp_panel = lpc_correcciones.getCatastral();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposDatosBasicosCatastral(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.MATRICULAS_SEGREGADAS:
							{
								PanelMatriculasSegregacion lp_panel;

								lp_panel = lpc_correcciones.getMatriculasSegregacion();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposMatriculasSegregacion(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.DATOS_ANT_SISTEMA:
							{
								PanelAntSistemaSolicitud lp_panel;

								lp_panel = lpc_correcciones.getAntSistema();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposDatosAntSistema(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.DETALLE_ANT_SISTEMA:
							{
								PanelDetalleAntSistemaSolicitud lp_panel;

								lp_panel = lpc_correcciones.getDetalleAntSistema();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposDetalleAntSistema(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.LINDEROS:
							{
								PanelLinderos lp_panel;

								lp_panel = lpc_correcciones.getLinderos();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposLinderos(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.COMPLEMENTACION:
							{
								PanelComplementacion lp_panel;

								lp_panel = lpc_correcciones.getComplementacion();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposComplementacion(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.AREA_DEL_PREDIO:
							{
								PanelAreaPredio lp_panel;

								lp_panel = lpc_correcciones.getAreaPredio();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposAreaPredio(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							case CausalCorreccionCommon.DIRECCION_DEL_PREDIO:
							{
								PanelDireccionPredio lp_panel;

								lp_panel = lpc_correcciones.getDireccionPredio();

								if(lp_panel != null)
								{
									String ls_justificacion;

									ls_justificacion = lp_panel.getJustificacion();

									lsb_base.append(camposDireccionPredio(lp_panel));

									if(StringUtils.isValidString(ls_justificacion))
									{
										ls_justificacion = ls_justificacion.toUpperCase();

										lsb_base.append((ls_espacio + ls_justificacion));
									}

									lp_panel.setJustificacion(ls_justificacion);
									lsb_base.append((ls_espacio + ls_complementoSalvedad));

									lp_panel.setSalvedad(lsb_base.toString().toUpperCase());
								}

								break;
							}

							default:
								break;
						}
					}
					else
						throw new B2BException(ErrorKeys.COMPLEMENTO_SALVEDAD);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}
	}

	/**
	 * Método encargado de cargar los paneles y sus respectivas selecciones de correcciones para la anotacion seleccionada.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarAnotacion(Anotacion aa_anotacion)
	    throws B2BException
	{
		if(aa_anotacion != null)
		{
			AnotacionPredio lap_anotacionPredio;

			lap_anotacionPredio = aa_anotacion.getAnotacionPredio();

			if(lap_anotacionPredio != null)
			{
				String ls_idNaturalezaJuridida;

				ls_idNaturalezaJuridida = lap_anotacionPredio.getIdNaturalezaJuridica();

				if(
				    StringUtils.isValidString(ls_idNaturalezaJuridida)
					    && !ls_idNaturalezaJuridida.contains(IdentificadoresCommon.SIMBOLO_GUION)
				)
				{
					String ls_version;

					ls_version = StringUtils.getString(lap_anotacionPredio.getVersion());

					if(StringUtils.isValidString(ls_version))
						lap_anotacionPredio.setIdNaturalezaJuridica(
						    ls_idNaturalezaJuridida + IdentificadoresCommon.SIMBOLO_GUION + ls_version
						);
				}
			}

			consultarAnotacion(aa_anotacion, true, false);

			if(isAnotacionEnglobe())
				cargarInfoEnglobeAnotacion(getAnotacion());
		}
	}

	/**
	 * Método para contar carácteres.
	 *
	 * @param as_campo String para contar caracteres
	 * @return devuelve el valor de String
	 */
	public String contar(String as_campo)
	{
		char[] lc_arrayChar;
		int    li_contador;
		String ls_result;

		li_contador = 0;

		if(as_campo != null)
		{
			lc_arrayChar     = as_campo.toCharArray();
			li_contador      = lc_arrayChar.length;
		}

		ls_result = Integer.toString(li_contador);

		return ls_result;
	}

	/**
	 * Continuar validar anotacion.
	 *
	 * @param ab_documento correspondiente al valor de ab documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void continuarValidarAnotacion(boolean ab_documento)
	    throws B2BException
	{
		PredioSegregado lps_data;

		lps_data = getPredioValidarAnotaciones();

		if(lps_data != null)
		{
			if(ab_documento)
				lps_data.setNoValidarDocumento(true);
			else
				lps_data.setNoValidarSegregacion(true);
		}

		editarSegregaciones(lps_data, true);
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param adp_direccionBase Argumento de tipo <code>DireccionPredio</code> correspondiente al valor de direccion base
	 * @return el valor de string
	 */
	public String deleteMatriculaBase(DireccionPredio adp_direccionBase)
	{
		DatosBasicos ldb_datosBasicos;

		ldb_datosBasicos = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			MatriculaBase lmb_matriculaBase;

			lmb_matriculaBase = ldb_datosBasicos.getMatriculaBase();

			if(lmb_matriculaBase != null)
			{
				Collection<DireccionPredio> lcdp_data;

				lcdp_data = lmb_matriculaBase.getDireccionPredio();

				if(CollectionUtils.isValidCollection(lcdp_data))
					lcdp_data.remove(adp_direccionBase);
			}
		}

		return null;
	}

	/**
	 * Método para descargar plantilla cargue excel en formato xlsx de la constante.
	 */
	public void descargarPlantillaCarguePredios()
	{
		try
		{
			setImagen(descargaPlantillaXLSX(ConstanteCommon.CARGUE_EXCEL_MATRICULAS_APERTURAR));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Carga la información del detalle de las matrículas.
	 *
	 * @return enlace de la pagina de detalle de matrículas
	 */
	public String detalleMatriculasInformacion(boolean ab_correccionAnotacion)
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
					Anotacion la_anotacion;

					if(ab_correccionAnotacion)
						la_anotacion = getAnotacion();
					else
						la_anotacion = null;

					lbrc_bean.setShowWizard(true);
					lbrc_bean.accionVolver(false);
					lbrc_bean.setIdPredioRegistro(ls_idPredioRegistro);
					lbrc_bean.setIdTurno(getIdTurno());
					lbrc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
					lbrc_bean.cargarMatricula(false);
					lbrc_bean.cargarDatosParametricosDireccion();
					lbrc_bean.setEjecucionCorrecciones(true);
					lbrc_bean.setCorreccionAnotacion(ab_correccionAnotacion);

					if(ab_correccionAnotacion)
						lbrc_bean.setAnotacion(la_anotacion);

					ls_return = NavegacionCommon.DETALLE_REGISTRO_CALIFICACION;
				}
			}
		}
		catch(Exception lb2b_2b)
		{
			addMessage(new B2BException(lb2b_2b.getMessage()));
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}

		return ls_return;
	}

	/**
	 * Dialog salvedades.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 */
	public void dialogSalvedades(Anotacion aa_anotacion)
	{
		if(aa_anotacion != null)
		{
			setAnotacion(aa_anotacion);

			abrirDialogo("dlgConfirmacionSalvedadAnotacion", "fDialogConfirmacionSalvedadAnotacion");
		}
	}

	/**
	 * Dialog salvedades.
	 *
	 * @param as_idCausal correspondiente al valor del tipo de objeto String
	 */
	public void dialogSalvedades(String as_idCausal)
	{
		if(StringUtils.isValidString(as_idCausal))
		{
			setIdCausal(as_idCausal);

			abrirDialogo("dlgConfirmacionSalvedad", "fDialogConfirmacionSalvedad");
		}
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

	/**
	 * Editar segregaciones.
	 *
	 * @param aps_predioSegregado Argumento de tipo <code>PredioSegregado</code> correspondiente al valor de aps predio segregado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void editarSegregaciones(PredioSegregado aps_predioSegregado)
	    throws B2BException
	{
		editarSegregaciones(aps_predioSegregado, true);
	}

	/**
	 * Editar segregaciones.
	 *
	 * @param aps_predioSegregado Argumento de tipo <code>PredioSegregado</code> correspondiente al valor de predio segregado
	 * @param ab_editar Argumento de tipo <code>boolean</code> correspondiente al valor de editar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void editarSegregaciones(PredioSegregado aps_predioSegregado, boolean ab_editar)
	    throws B2BException
	{
		if(aps_predioSegregado != null)
		{
			try
			{
				String ls_return;

				ls_return = null;

				if(ab_editar)
				{
					{
						String ls_descripcion;

						ls_descripcion     = aps_predioSegregado.getDescripcion();
						ls_descripcion     = ls_descripcion.toUpperCase();

						aps_predioSegregado.setDescripcion(ls_descripcion);
					}

					{
						String ls_lote;

						ls_lote     = aps_predioSegregado.getLote();
						ls_lote     = ls_lote.toUpperCase();

						aps_predioSegregado.setLote(ls_lote);
					}

					{
						Long ll_idAnotacion;

						ll_idAnotacion = aps_predioSegregado.getIdAnotacion();

						if(!NumericUtils.isValidLong(ll_idAnotacion, 1L))
							throw new B2BException(ErrorKeys.ANOTACION_VALIDA_SELECCIONAR);
					}

					{
						Long ll_idAnotacion1;

						ll_idAnotacion1 = aps_predioSegregado.getIdAnotacion1();

						if(!NumericUtils.isValidLong(ll_idAnotacion1, 1L))
							ll_idAnotacion1 = null;
					}

					ls_return = irr_calificacionRemote.validarAnotacionesPredioSegregado(aps_predioSegregado);

					aps_predioSegregado.setEditado(true);

					if(ls_return != null)
					{
						switch(ls_return)
						{
							case EstadoCommon.S:
								setPredioValidarAnotaciones(aps_predioSegregado);
								abrirDialogo("idDialogValidarSegregacion", "idFormValidarSegregacion");

								break;

							case EstadoCommon.D:
								setPredioValidarAnotaciones(aps_predioSegregado);
								abrirDialogo("idDialogValidarDocumento", "idFormValidarDocumento");

								break;

							default:
								break;
						}
					}
					else
						setCierreFolio(irr_calificacionRemote.cierreFolio(getPrediosSegregados()));
				}
				else
				{
					aps_predioSegregado.setNoValidarDocumento(false);
					aps_predioSegregado.setNoValidarSegregacion(false);
				}

				if(ls_return == null)
					aps_predioSegregado.setEditar(!ab_editar);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("editarSegregaciones", lb2be_e);
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Elimina un detalle previamente seleccionado.
	 */
	public void eliminarDetalleAntSistema()
	{
		DetalleAntSistema ldas_detalle;

		ldas_detalle = getDetalleAntSisParaEliminar();

		if(ldas_detalle != null)
		{
			try
			{
				irr_registroRemote.eliminarDetalleAntSistema(
				    ldas_detalle, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				Collection<DetalleAntSistema> lcdas_detalles;

				lcdas_detalles = getDetallesAntSistema();

				if(CollectionUtils.isValidCollection(lcdas_detalles))
					lcdas_detalles.remove(ldas_detalle);

				addMessage(MessagesKeys.DETALLE_ELIMINADO_SATISFACTORIAMENTE);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("eliminarDetalleAntSistema", lb2be_e);
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Eliminar segregacion.
	 *
	 * @param aps_predioSegregado Argumento de tipo <code>PredioSegregado</code> correspondiente al valor de predio segregado
	 * @param ab_accion correspondiente al valor de accion
	 */
	public void eliminarSegregacion(PredioSegregado aps_predioSegregado, boolean ab_accion)
	{
		if(aps_predioSegregado != null)
			aps_predioSegregado.setEliminar(ab_accion);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String enviarAprobador()
	{
		FacesContext lfc_context;
		String       ls_return;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = null;

		try
		{
			BeanCorreccion lbc_bean;

			lbc_bean = (BeanCorreccion)lfc_context.getApplication()
					                                  .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CORRECCION, BeanCorreccion.class
					);

			irr_calificacionRemote.enviarAprobadorRealizarCorrecciones(
			    getObservaciones(), getIdTurnoHistoria(), isSecuenciaAgregada(), getUserId(), getLocalIpAddress(),
			    getRemoteIpAddress()
			);

			lbc_bean.setIdTurnoConsulta(null);
			lbc_bean.setNirConsulta(null);
			lbc_bean.generarDatosTramiteCantidad();

			ls_return = NavegacionCommon.ANALISIS_CORRECCION;

			clean();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitante()
	{
		Collection<CalidadSolicitante> lcidt_datos;

		try
		{
			lcidt_datos = irr_referenceRemote.findCalidadSolicitante(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
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
			RegistroCalificacion lrc_data;

			lrc_data = getMatriculas();

			if(lrc_data != null)
			{
				if(StringUtils.isValidString(ls_old) && StringUtils.isValidString(ls_new))
				{
					final String ls_idDatosBasicos;
					final String ls_idAreaPredio;
					final String ls_idAnotaciones;
					final String ls_idComplementacion;
					final String ls_idDireccion;

					ls_idDatosBasicos        = "datosBasicos_idTabs";
					ls_idAreaPredio          = "areaPredio_id";
					ls_idAnotaciones         = "anotaciones_id";
					ls_idComplementacion     = "complementacion_id";
					ls_idDireccion           = "direccion_id";

					cerrarPanelesAnotacion();

					if(ls_new.equalsIgnoreCase(ls_idDatosBasicos) && ls_old.equalsIgnoreCase(ls_idAreaPredio))
					{
						setMostrarRegresarEnglobes(false);
						setMostrarSiguienteEnglobes(true);
					}
					else if(ls_new.equalsIgnoreCase(ls_idAreaPredio))
					{
						if(ls_old.equalsIgnoreCase(ls_idDatosBasicos))
						{
							salvarDatosBasicosEnglobes();
							cargarDatosAreaEnglobes();
							limpiarAreasEnglobes();
						}

						setMostrarRegresarEnglobes(true);
						setMostrarSiguienteEnglobes(true);
					}
					else if(ls_new.equalsIgnoreCase(ls_idAnotaciones))
					{
						if(ls_old.equalsIgnoreCase(ls_idAreaPredio))
							salvarAreaPredioEnglobes();

						if(ls_old.equalsIgnoreCase(ls_idAreaPredio))
							salvarAreaPredioEnglobes();

						if(!ls_old.equalsIgnoreCase(ls_idComplementacion))
							cargarAnotacionesEnglobes();

						cargarAnotacionProceso();

						setMostrarRegresarEnglobes(true);
						setMostrarSiguienteEnglobes(true);
					}
					else if(ls_new.equalsIgnoreCase(ls_idComplementacion))
					{
						if(ls_old.equalsIgnoreCase(ls_idAreaPredio))
							salvarAreaPredioEnglobes();

						cargarLinderosComplementacionEnglobes(true);

						setMostrarRegresarEnglobes(true);
						setMostrarSiguienteEnglobes(true);
					}
					else if(ls_new.equalsIgnoreCase(ls_idDireccion))
					{
						if(ls_old.equalsIgnoreCase(ls_idComplementacion))
							if(isComplementacionSinConstruir())
								throw new B2BException(ErrorKeys.DEBE_CONSTRUIR_COMPLEMENTACION);

						salvarLinderosComplementacionEnglobes();
						cargarDireccionesEnglobes();

						setMostrarRegresarEnglobes(true);
						setMostrarSiguienteEnglobes(false);

						{
							BeanDireccion                  lbd_beanDireccion;
							Collection<DireccionDelPredio> lcddp_direccionesPredio;
							DatosBasicos                   ldb_datosBasicos;
							RegistroCalificacion           lrc_dataDirecciones;

							lbd_beanDireccion           = getBeanDireccion();
							lcddp_direccionesPredio     = getDireccionesPredio();
							ldb_datosBasicos            = getDatosBasicos();
							lrc_dataDirecciones         = irr_calificacionRemote.cargarDireccionesPredioTemporal(
								    lrc_data
								);

							if(lrc_dataDirecciones != null)
							{
								ldb_datosBasicos            = lrc_dataDirecciones.getDatosBasicos();
								lcddp_direccionesPredio     = lrc_dataDirecciones.getDireccionesDelPredio();
							}

							setEditorTablaDirecciones(false);

							lbd_beanDireccion.limpiarBeanDireccion();
							lbd_beanDireccion.setAnotacion(true);
							lbd_beanDireccion.setAgregarDireccionPredio(true);
							lbd_beanDireccion.setDeshabilitarDatosUbicacion(true);
							lbd_beanDireccion.setForm(cs_ID_FORMULARIO);
							lbd_beanDireccion.cargarDatosDireccionPredio(ldb_datosBasicos);
							lbd_beanDireccion.setDireccionesPredio2(lcddp_direccionesPredio);
							lbd_beanDireccion.setDireccionesTemporales(lcddp_direccionesPredio);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_new = ls_old;
		}

		PrimeFaces.current().ajax().update(cs_ID_GROWL);

		return ls_new;
	}

	/**
	 * genera el documento que relaciona los cambios realizados al tramite de
	 * correcciones.
	 */
	public void generarFormularioCorrecciones()
	{
		try
		{
			byte[] lba_documento;

			lba_documento = irr_calificacionRemote.generarFormularioCorrecciones(
				    getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lba_documento != null)
				setDocumentoFormularioCorreccion(
				    new DefaultStreamedContent(
				        new ByteArrayInputStream(lba_documento), TipoContenidoCommon.ZIP,
				        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.ZIP_PUNTO
				    )
				);
			else
				throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarFormularioCorrecciones", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de guardar los datos del panel de Datos anotación para
	 * ejecución correcciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosAnotacion()
	    throws B2BException
	{
		try
		{
			AnotacionPredio lap_anotacionPredio;

			lap_anotacionPredio = getAnotacionPredio();

			if(lap_anotacionPredio != null)
			{
				validarPanelDatosAnotacion(
				    lap_anotacionPredio, false, true, cs_ID_FORMULARIO_TV, FacesContext.getCurrentInstance()
				);

				iasr_antiguoSistemaRemote.guardarDatosAnotacion(
				    lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
				actualizarComponente(cs_ID_GROWL);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDatosAnotacion", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de guardar los datos del panel de Datos ant sistema para ejecución correcciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosAntSistema()
	    throws B2BException
	{
		try
		{
			DatosAntSistema ldas_datosAnt;

			ldas_datosAnt = getAntiguoSistemaData();

			if(ldas_datosAnt != null)
			{
				irr_registroRemote.guardarDatosAntSistema(
				    ldas_datosAnt, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), false
				);

				addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
				actualizarComponente(cs_ID_GROWL);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDatosAntSistema", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de guardar los datos del panel de Datos ant sistema
	 * anotación para ejecución correcciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosAntSistemaAnotacion()
	    throws B2BException
	{
		try
		{
			DetalleAntSistema ldas_detalleAntSistema;

			ldas_detalleAntSistema = getDetalleAntSistemaAnotacion();

			if(ldas_detalleAntSistema != null)
			{
				validarPanelDetalleAnotacion(ldas_detalleAntSistema, true);

				iasr_antiguoSistemaRemote.guardarDatosAntSistema(
				    getAnotacionPredio(), ldas_detalleAntSistema, getAnotacion(), null, getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);

				addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
				actualizarComponente(cs_ID_GROWL);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDatosAntSistema", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Guardar datos apertura.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosApertura()
	    throws B2BException
	{
		DatosBasicos ldb_datosBasicos;

		ldb_datosBasicos = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			validarPanelDatosBasicosApertura();

			{
				TurnoHistoria lth_turnoHistoria;
				Long          ll_idTurnoHistoria;

				lth_turnoHistoria      = new TurnoHistoria();
				ll_idTurnoHistoria     = NumericUtils.getLongWrapper(getIdTurnoHistoria());

				lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

				lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
					ldb_datosBasicos.setTurnoHistoria(lth_turnoHistoria);
			}

			{
				Turno  lt_turno;
				String ls_idTurno;

				lt_turno       = new Turno();
				ls_idTurno     = getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					lt_turno.setIdTurno(ls_idTurno);

					lt_turno = ier_entregaRemote.findTurnoById(lt_turno, getUserId());

					if(lt_turno != null)
						ldb_datosBasicos.setTurno(lt_turno);
				}
			}

			iasr_antiguoSistemaRemote.guardarDatosApertura(
			    ldb_datosBasicos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Guardar datos area.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosArea()
	    throws B2BException
	{
		if(!isBloquearAgregarAreaTerreno())
		{
			AccAreaUI laaui_data;

			laaui_data = validarPanelAreaPredi();

			if((laaui_data != null))
			{
				laaui_data.setIdCirculo(getIdCirculo());
				laaui_data.setIdMatricula(getIdMatricula());
				laaui_data.setIdTurno(getIdTurno());
				laaui_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

				irr_calificacionRemote.guardarDatosArea(
				    laaui_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
				actualizarComponente(cs_ID_GROWL);
			}
		}
		else
			throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_MODIFICAR);
	}

	/**
	 * Guardar datos catastral.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosCatastral()
	    throws B2BException
	{
		DatosBasicos ldb_datosBasicos;

		ldb_datosBasicos = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			iasr_antiguoSistemaRemote.guardarDatosCatastral(
			    ldb_datosBasicos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Guardar datos complementacion.
	 *
	 * @param ab_validar correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosComplementacion(boolean ab_validar)
	    throws B2BException
	{
		boolean                        lb_salvar;
		boolean                        lb_copiar;
		boolean                        lb_seleccionar;
		Collection<SolicitudMatricula> lcsm_matriculas;
		String                         ls_copiar;
		String                         ls_seleccionar;

		lb_salvar           = false;
		lb_copiar           = false;
		lb_seleccionar      = false;
		lcsm_matriculas     = getMatriculasMasivasComplementacion();
		ls_copiar           = getCopiarComplementacion();
		ls_seleccionar      = getCopiarComplementacionSeleccionadas();

		validarPanelComplementacion();

		if(isCopiarMasivos())
		{
			if(StringUtils.isValidString(ls_copiar))
			{
				lb_copiar = BooleanUtils.getBooleanValue(ls_copiar);

				if(lb_copiar)
				{
					if(StringUtils.isValidString(ls_seleccionar))
					{
						lb_seleccionar = BooleanUtils.getBooleanValue(ls_seleccionar);

						if(lb_seleccionar)
						{
							if(CollectionUtils.isValidCollection(lcsm_matriculas))
							{
								if(ab_validar)
									abrirDialogo(
									    "dlgConfirmacionCopiaComplementacion", "fDialogConfirmacionCopiaComplementacion"
									);
								else
								{
									boolean                      lb_seleccionado;
									Iterator<SolicitudMatricula> lism_iterator;

									lb_seleccionado     = false;
									lism_iterator       = lcsm_matriculas.iterator();

									while(lism_iterator.hasNext() && !lb_seleccionado)
									{
										SolicitudMatricula lsm_iterador;

										lsm_iterador = lism_iterator.next();

										if(lsm_iterador != null)
											lb_seleccionado = lsm_iterador.isSeleccionado();
									}

									if(!lb_seleccionado)
										throw new B2BException(ErrorKeys.ERROR_SIN_SELECCIONAR_MATRICULA);
									else
										lb_salvar = true;
								}
							}
						}
						else
							lb_salvar = true;
					}
					else
						throw new B2BException(ErrorKeys.ERROR_COPIAR_COMPLEMENTACION);
				}
				else
					lb_salvar = true;
			}
			else
				throw new B2BException(ErrorKeys.ERROR_COPIAR_COMPLEMENTACION);
		}
		else
		{
			lb_salvar     = true;
			ls_copiar     = EstadoCommon.NO;
		}

		if(lb_salvar)
		{
			RegistroCalificacion lrc_data;

			lrc_data = new RegistroCalificacion();

			lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lrc_data.setTurno(getIdTurno());
			lrc_data.setIdCirculoMatriz(getIdCirculo() + IdentificadoresCommon.SIMBOLO_GUION + getIdMatricula());
			lrc_data.setComplementacionCalificacion(getComplementacionCalificacion());
			lrc_data.setMatriculasCopiar(lcsm_matriculas);
			lrc_data.setCopiar(lb_copiar);
			lrc_data.setSeleccionar(lb_seleccionar);

			irr_calificacionRemote.guardarDatosComplementacion(
			    lrc_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			cerrarDialogo("dlgConfirmacionCopiaComplementacion", "fDialogConfirmacionCopiaComplementacion");

			addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Guarda la modificación realizada a un detalle de antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosDetallesAntSistema()
	    throws B2BException
	{
		try
		{
			DetalleAntSistema ldas_detalle;

			ldas_detalle = getDetalleAntSistemaCargado();

			if(ldas_detalle != null)
			{
				FacesContext lfc_faces;
				boolean      lb_focus;
				Long         ll_idLibro;

				lfc_faces      = FacesContext.getCurrentInstance();
				lb_focus       = true;
				ll_idLibro     = ldas_detalle.getIdLibroAntSistema();

				ldas_detalle.setDocumento(getDocumentoDetalleCargado());

				lb_focus     = validateStyles(
					    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMLibro", lfc_faces, ll_idLibro, lb_focus
					);
				lb_focus     = validateStyles(
					    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMTomo", lfc_faces, ldas_detalle.getTomo(), lb_focus
					);
				lb_focus     = validateStyles(
					    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMFolio", lfc_faces, ldas_detalle.getFolio(), lb_focus
					);
				lb_focus     = validateStyles(
					    cs_ID_FORMULARIO_DETALLE_ANT + "idItNumeroPartida", lfc_faces, ldas_detalle.getNumeroPartida(),
					    lb_focus
					);

				if(NumericUtils.isValidLong(ll_idLibro))
				{
					String ls_idLibroSeleccionado;

					ls_idLibroSeleccionado = StringUtils.getString(ll_idLibro);

					if(ls_idLibroSeleccionado.equals(LibroAntSistemaCommon.LIBRO_DE_MATRICULAS))
						lb_focus = validateStyles(
							    cs_ID_FORMULARIO_DETALLE_ANT + "datosPredioIdMatricula", lfc_faces,
							    ldas_detalle.getIdMatricula(), lb_focus
							);
					else
					{
						lb_focus     = validateStyles(
							    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMPartida", lfc_faces, ldas_detalle.getPartida(),
							    lb_focus
							);
						lb_focus     = validateStyles(
							    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMAno", lfc_faces, ldas_detalle.getAnio(), lb_focus
							);
					}

					if(ls_idLibroSeleccionado.equals(LibroAntSistemaCommon.LIBRO_SEGUNDO))
					{
						Documento ld_documento;

						ld_documento = ldas_detalle.getDocumento();

						if(ld_documento != null)
						{
							lb_focus     = validateStyles(
								    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMEscrituraAntSis", lfc_faces,
								    ld_documento.getIdTipoDocumento(), lb_focus
								);
							lb_focus     = validateStyles(
								    cs_ID_FORMULARIO_DETALLE_ANT + "idItDocuActoAntSis", lfc_faces,
								    ld_documento.getNumero(), lb_focus
								);
							lb_focus     = validateStyles(
								    cs_ID_FORMULARIO_DETALLE_ANT + "idCalFechaDocAntSis", lfc_faces,
								    ld_documento.getFechaDocumento(), lb_focus
								);
							lb_focus     = validateStyles(
								    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMTipoOficinaAntSis", lfc_faces,
								    ld_documento.getIdTipoOficina(), lb_focus
								);
							lb_focus     = validateStyles(
								    cs_ID_FORMULARIO_DETALLE_ANT + "idPaisDocumentoAntSis", lfc_faces,
								    ld_documento.getIdPais(), lb_focus
								);
							lb_focus     = validateStyles(
								    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMDepartamentoAntSis", lfc_faces,
								    ld_documento.getIdDepartamento(), lb_focus
								);
							lb_focus     = validateStyles(
								    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMMunicipioAntSis", lfc_faces,
								    ld_documento.getIdMunicipio(), lb_focus
								);
							lb_focus     = validateStyles(
								    cs_ID_FORMULARIO_DETALLE_ANT + "idSOMOficinaOrigenAntSis", lfc_faces,
								    ld_documento.getIdOficinaOrigen(), lb_focus
								);
						}
					}
				}

				boolean lb_guardar;

				lb_guardar     = isGuardarDetalleAnt();

				ldas_detalle = irr_registroRemote.guardarDetallesAntSistema(
					    ldas_detalle, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), lb_guardar
					);

				if(ldas_detalle != null)
				{
					addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
					actualizarComponente(cs_ID_GROWL);

					cerrarDialogo("dlgConfirmacionSalvar", "fDialogConfirmacion");
					cerrarDialogo("detallePredio", "fDialogDetallePredio");

					if(!lb_guardar)
					{
						Collection<DetalleAntSistema> lcdas_detalles;
						String                        ls_idDetalleMod;
						boolean                       lb_found;

						lcdas_detalles      = getDetallesAntSistema();
						ls_idDetalleMod     = StringUtils.getStringNotNull(ldas_detalle.getIdDetalleAntSistema());
						lb_found            = false;

						if(CollectionUtils.isValidCollection(lcdas_detalles))
						{
							Iterator<DetalleAntSistema> lidas_iterador;

							lidas_iterador = lcdas_detalles.iterator();

							while(lidas_iterador.hasNext() && !lb_found)
							{
								DetalleAntSistema ldas_tmp;

								ldas_tmp = lidas_iterador.next();

								if(ldas_tmp != null)
								{
									String ls_idDetalle;

									ls_idDetalle = StringUtils.getStringNotNull(ldas_tmp.getIdDetalleAntSistema());

									if(ls_idDetalle.equals(ls_idDetalleMod))
									{
										ldas_tmp     = ldas_detalle;
										lb_found     = true;
									}
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDatosDetallesAntSistema", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Guardar datos direccion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosDireccion()
	    throws B2BException
	{
		AccPredioRegistro lapm_predio;

		lapm_predio = getAccPredioRegistro();

		validarDirecciones(true);

		lapm_predio.setDireccionesPredio(getDireccionesPredio());
		lapm_predio.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
		lapm_predio.setIdMatricula(getIdMatricula());
		lapm_predio.setIdCirculo(getIdCirculo());

		iasr_antiguoSistemaRemote.guardarDatosDireccion(
		    lapm_predio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
		);

		{
			BeanDireccion         lbd_beanDireccion;
			Collection<Direccion> lcd_direcciones;

			lbd_beanDireccion     = getBeanDireccion();
			lcd_direcciones       = lbd_beanDireccion.getDireccionesPredio();

			if(CollectionUtils.isValidCollection(lcd_direcciones))
			{
				for(Direccion ld_iterador : lcd_direcciones)
				{
					if((ld_iterador != null) && ld_iterador.isEliminar())
						lcd_direcciones.remove(ld_iterador);
				}
			}

			lbd_beanDireccion.setDireccionesPredio(lcd_direcciones);
		}

		addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
		actualizarComponente(cs_ID_GROWL);
	}

	/**
	 * Metodo encargado de guardar los datos del panel de Datos del documento para
	 * ejecución correcciones.
	 *
	 * @param ab_mostrarDialogo correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosDocumentoAnotacion(boolean ab_mostrarDialogo)
	    throws B2BException
	{
		try
		{
			String ls_copiarDocumento;
			String ls_copiarDocumentoSeleccionadas;

			boolean lb_copiaDocumentoValido;
			boolean lb_copiaDocumentoSeleValido;
			boolean lb_copiarDocumento;
			boolean lb_copiarDocumentoSeleccionadas;

			ls_copiarDocumento                  = getCopiarDocumento();
			ls_copiarDocumentoSeleccionadas     = getCopiarDocumentoSeleccionadas();

			lb_copiaDocumentoValido         = StringUtils.isValidString(ls_copiarDocumento);
			lb_copiaDocumentoSeleValido     = StringUtils.isValidString(ls_copiarDocumentoSeleccionadas);

			if(isCopiarMasivos() && !lb_copiaDocumentoValido)
				throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_COPIA_INFO);

			lb_copiarDocumento = lb_copiaDocumentoValido && ls_copiarDocumento.equalsIgnoreCase(EstadoCommon.S);

			if(lb_copiarDocumento && !lb_copiaDocumentoSeleValido)
				throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_TIPO_COPIA_INFO);

			lb_copiarDocumentoSeleccionadas = lb_copiaDocumentoSeleValido
					&& ls_copiarDocumentoSeleccionadas.equalsIgnoreCase(EstadoCommon.S);

			if(ab_mostrarDialogo && lb_copiarDocumento && lb_copiarDocumentoSeleccionadas)
				abrirDialogo("dlgMatriculasACopiarDocumento", "idFMatriculasACopiarDocumento");
			else
			{
				Documento ld_documento;

				ld_documento = getDocumento();

				if(ld_documento != null)
				{
					FacesContext lfc_context;

					lfc_context = FacesContext.getCurrentInstance();

					validarPanelDatosDocumento(ld_documento, true, cs_ID_FORMULARIO_TV, lfc_context);
					validarFechaDocumentoRadicacion(false);

					ld_documento.setCopiarDocumento(lb_copiarDocumento);
					ld_documento.setCopiarDocumentoSeleccionadas(lb_copiarDocumentoSeleccionadas);
					ld_documento.setMatriculasMasivasDocumento(getMatriculasMasivasDocumento());

					{
						AnotacionPredio lap_anotacionPredio;

						lap_anotacionPredio = getAnotacionPredio();

						if(lap_anotacionPredio != null)
						{
							if(lb_copiarDocumento)
							{
								String ls_naturalezaJuridicaSeleccionada;
								ls_naturalezaJuridicaSeleccionada = getNaturalezaJuridicaSeleccionada();

								validateStyles(
								    cs_ID_FORMULARIO_TV + ":idSOMTipoActo", lfc_context,
								    ls_naturalezaJuridicaSeleccionada, true
								);

								if(!StringUtils.isValidString(ls_naturalezaJuridicaSeleccionada))
									throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);

								lap_anotacionPredio.setIdNaturalezaJuridica(ls_naturalezaJuridicaSeleccionada);
							}

							ld_documento.setAnotacionPredio(lap_anotacionPredio);
						}
					}

					iasr_antiguoSistemaRemote.guardarDatosDocumentoAnotacion(
					    ld_documento, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
					actualizarComponente(cs_ID_GROWL);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDatosDocumentoAnotacion", lb2be_e);

			if(!ab_mostrarDialogo)
			{
				addMessage(lb2be_e);
				actualizarComponente(cs_ID_GROWL);
			}
			else
				throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de guardar los datos del panel de Especificación anotación
	 * para ejecución correcciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosEspecificacionAnotacion()
	    throws B2BException
	{
		try
		{
			AnotacionPredio lap_anotacionPredio;

			lap_anotacionPredio = getAnotacionPredio();

			if(lap_anotacionPredio != null)
			{
				TurnoHistoria lth_turnoHistoria;
				boolean       lb_validarAnotacionACancelar;

				lth_turnoHistoria                = new TurnoHistoria();
				lb_validarAnotacionACancelar     = false;

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

				lap_anotacionPredio.setIdAnotacion(getNumeroAnotacion());

				validarPanelEspecificacion(
				    lap_anotacionPredio, false, lb_validarAnotacionACancelar, cs_ID_FORMULARIO_TV,
				    FacesContext.getCurrentInstance()
				);

				lap_anotacionPredio.setValidarAnotacionACancelar(lb_validarAnotacionACancelar);
				lap_anotacionPredio.setAnotacionesACancelar(getAnotacionACancelar());

				iasr_antiguoSistemaRemote.guardarDatosEspecificacionAnotacion(
				    lap_anotacionPredio, getNumeroAnotacion(), getAnotacion(), lth_turnoHistoria, getUserId(),
				    getLocalIpAddress(), getRemoteIpAddress()
				);

				addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
				actualizarComponente(cs_ID_GROWL);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDatosEspecificacionAnotacion", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Guardar datos linderos.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosLinderos()
	    throws B2BException
	{
		validarPanelLinderos();

		CabidaLinderos              lcl_linderos;
		LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;
		Long                        ll_idTurnoHistoria;
		Long                        ll_idMatricula;
		RegistroCalificacion        lrc_data;
		String                      ls_idTurno;
		String                      ls_idCirculo;
		String                      ls_lindero;

		lcl_linderos                         = getCabidaLinderos();
		ll_idTurnoHistoria                   = NumericUtils.getLongWrapper(getIdTurnoHistoria());
		ll_idMatricula                       = getIdMatricula();
		llrc_linderoRegistroCalificacion     = getLinderoRegistroCalificacion();
		lrc_data                             = new RegistroCalificacion();
		ls_idTurno                           = getIdTurno();
		ls_idCirculo                         = getIdCirculo();
		ls_lindero                           = null;

		if((llrc_linderoRegistroCalificacion != null) && (lcl_linderos != null))
		{
			Collection<AccLinderoPredio> lcalp_accLinderoPredios;

			lcalp_accLinderoPredios     = llrc_linderoRegistroCalificacion.getLinderoPredios();
			ls_lindero                  = lcl_linderos.getLinderos();

			if(!CollectionUtils.isValidCollection(lcalp_accLinderoPredios))
			{
				AccLinderoPredio lalp_lindero;

				lcalp_accLinderoPredios     = new ArrayList<AccLinderoPredio>();
				lalp_lindero                = new AccLinderoPredio();

				lalp_lindero.setIdTurno(ls_idTurno);
				lalp_lindero.setIdTurnoHistoria(ll_idTurnoHistoria);
				lalp_lindero.setLindero(ls_lindero);
				lalp_lindero.setIdUsuarioCreacion(getUserId());
				lalp_lindero.setIpCreacion(getRemoteIpAddress());
				lalp_lindero.setIdMatricula(ll_idMatricula);
				lalp_lindero.setIdCirculo(ls_idCirculo);

				lcalp_accLinderoPredios.add(lalp_lindero);

				llrc_linderoRegistroCalificacion.setLinderoPredios(lcalp_accLinderoPredios);
			}

			llrc_linderoRegistroCalificacion.setLindero(ls_lindero);
		}

		lrc_data.setIdTurnoHistoria(ll_idTurnoHistoria);
		lrc_data.setTurno(ls_idTurno);
		lrc_data.setIdCirculoMatriz(ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ll_idMatricula);

		lrc_data.setLinderoRegistroCalificacion(llrc_linderoRegistroCalificacion);
		lrc_data.setLinderoCargado(true);

		irr_calificacionRemote.guardarDatosLinderos(lrc_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress());

		addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
		actualizarComponente(cs_ID_GROWL);
	}

	/**
	 * Guardar datos matriculas.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosMatriculas()
	    throws B2BException
	{
		DatosBasicos ldb_datosBasicos;

		ldb_datosBasicos = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			ldb_datosBasicos.setPredioSegregado(
			    irr_calificacionRemote.guardarDatosSegregacion(
			        ldb_datosBasicos.getPredioSegregado(), getIdTurnoHistoria(), getIdTurno(), getUserId(),
			        getLocalIpAddress(), getRemoteIpAddress(), getIdCirculo(), getIdMatricula(), false
			    )
			);

			addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Guardar datos segregacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosSegregacion()
	    throws B2BException
	{
		setPrediosSegregados(
		    irr_calificacionRemote.guardarDatosSegregacion(
		        getPrediosSegregados(), getIdTurnoHistoria(), getIdTurno(), getUserId(), getLocalIpAddress(),
		        getRemoteIpAddress(), getIdCirculo(), getIdMatricula(), true
		    )
		);

		addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
		actualizarComponente(cs_ID_GROWL);
	}

	/**
	 * Guardar datos ubicacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDatosUbicacion()
	    throws B2BException
	{
		DatosBasicos ldb_datosBasicos;

		ldb_datosBasicos = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			validarPanelDatosBasicosUbicacion();

			{
				TurnoHistoria lth_turnoHistoria;
				Long          ll_idTurnoHistoria;

				lth_turnoHistoria      = new TurnoHistoria();
				ll_idTurnoHistoria     = NumericUtils.getLongWrapper(getIdTurnoHistoria());

				lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

				lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
					ldb_datosBasicos.setTurnoHistoria(lth_turnoHistoria);
			}

			{
				Turno  lt_turno;
				String ls_idTurno;

				lt_turno       = new Turno();
				ls_idTurno     = getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					lt_turno.setIdTurno(ls_idTurno);

					lt_turno = ier_entregaRemote.findTurnoById(lt_turno, getUserId());

					if(lt_turno != null)
						ldb_datosBasicos.setTurno(lt_turno);
				}
			}

			iasr_antiguoSistemaRemote.salvarDatosBasicosUbicacion(
			    ldb_datosBasicos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de guardar los datos del panel de Datos Intervinientes
	 * anotación para ejecución correcciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarIntervinientesAnotacion()
	    throws B2BException
	{
		try
		{
			Collection<Anotacion> lca_intervinientesAgregados;
			Collection<Anotacion> lca_datos;
			AnotacionPredio       lap_anotacionPredio;
			Anotacion             la_anotacion;
			boolean               lb_conAnotacion;
			boolean               lb_bloqueo;
			long                  ll_anotacionTmp;
			long                  ll_idAnotacion;
			Long                  ll_numeroAnotacion;

			lca_intervinientesAgregados     = getIntervinientesAgregados();
			lca_datos                       = getAnotacionesAgregadas();
			lap_anotacionPredio             = getAnotacionPredio();
			la_anotacion                    = getAnotacion();
			ll_idAnotacion                  = getIdAnotacionGeneral();
			lb_conAnotacion                 = ll_idAnotacion > 0;
			lb_bloqueo                      = (lap_anotacionPredio != null) ? lap_anotacionPredio.isBloqueo() : false;
			ll_numeroAnotacion              = getNumeroAnotacion();
			ll_anotacionTmp                 = NumericUtils.getLong(ll_numeroAnotacion);

			validarPanelIntervinientes(
			    lca_datos, lca_intervinientesAgregados, lap_anotacionPredio, la_anotacion, lb_conAnotacion, lb_bloqueo,
			    ll_anotacionTmp, ll_numeroAnotacion, cs_ID_FORMULARIO_TV, FacesContext.getCurrentInstance()
			);

			if(CollectionUtils.isValidCollection(lca_intervinientesAgregados))
			{
				Iterator<Anotacion> lia_iterador;
				boolean             lb_secuencia;

				lia_iterador     = lca_intervinientesAgregados.iterator();
				lb_secuencia     = false;

				while(lia_iterador.hasNext() && !lb_secuencia)
				{
					Anotacion la_anotacionInterviniente;

					la_anotacionInterviniente = lia_iterador.next();

					if(la_anotacionInterviniente != null)
					{
						Persona lp_persona;

						lp_persona = la_anotacionInterviniente.getPersona();

						if(lp_persona != null)
						{
							String ls_tipoDocumento;

							ls_tipoDocumento     = lp_persona.getIdDocumentoTipo();

							lb_secuencia = StringUtils.isValidString(ls_tipoDocumento)
									&& ls_tipoDocumento.equalsIgnoreCase(EstadoCommon.SE);
						}
					}
				}

				setSecuenciaAgregada(lb_secuencia);
			}

			la_anotacion.setIntervinientesAgregados(lca_intervinientesAgregados);
			la_anotacion.setAnotacionIndividual(true);

			if(lap_anotacionPredio != null)
			{
				AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;

				lapc_anotacionPredioCiudadano = getAnotacionPredioCiudadano();

				lapc_anotacionPredioCiudadano.setIdCirculo(getIdCirculo());
				lapc_anotacionPredioCiudadano.setIdMatricula(NumericUtils.getLong(getIdMatricula()));
				lapc_anotacionPredioCiudadano.setIdAnotacion(ll_idAnotacion);
				lapc_anotacionPredioCiudadano.setIdTurno(getIdTurno());

				iasr_antiguoSistemaRemote.guardarIntervinientesAnotacion(
				    la_anotacion, lapc_anotacionPredioCiudadano, getIdSolicitud(),
				    lap_anotacionPredio.getIdAnotacionPredio(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}

			addMessage(MessagesKeys.DATOS_MODIFICADOS_CORRECTAMENTE);
			actualizarComponente(cs_ID_GROWL);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarIntervinientesAnotacion", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Guardar salvedades.
	 *
	 * @param ab_validar correspondiente al valor del tipo de objeto boolean
	 */
	public void guardarSalvedades(boolean ab_validar)
	{
		try
		{
			String ls_idCausal;

			ls_idCausal = getIdCausal();

			if(StringUtils.isValidString(ls_idCausal))
			{
				PermisosCorreccionesUI lpc_data;

				lpc_data = getPermisosCorrecciones();

				if(lpc_data != null)
				{
					String ls_salvedad;
					String ls_copiar;
					String ls_seleccionar;

					ls_salvedad        = null;
					ls_copiar          = null;
					ls_seleccionar     = null;

					switch(ls_idCausal)
					{
						case CausalCorreccionCommon.DATOS_BASICOS:
						{
							PanelUbicacion lp_panel;

							lp_panel = lpc_data.getUbicacion();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.INFORMACION_DE_APERTURA:
						{
							PanelApertura lp_panel;

							lp_panel = lpc_data.getApertura();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS:
						{
							PanelMatriculasAbiertas lp_panel;

							lp_panel = lpc_data.getMatriculasAbiertas();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.INFORMACION_CATASTRAL:
						{
							PanelCatastral lp_panel;

							lp_panel = lpc_data.getCatastral();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.MATRICULAS_SEGREGADAS:
						{
							PanelMatriculasSegregacion lp_panel;

							lp_panel = lpc_data.getMatriculasSegregacion();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.DATOS_ANT_SISTEMA:
						{
							PanelAntSistemaSolicitud lp_panel;

							lp_panel = lpc_data.getAntSistema();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.DETALLE_ANT_SISTEMA:
						{
							PanelDetalleAntSistemaSolicitud lp_panel;

							lp_panel = lpc_data.getDetalleAntSistema();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.LINDEROS:
						{
							PanelLinderos lp_panel;

							lp_panel = lpc_data.getLinderos();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.COMPLEMENTACION:
						{
							PanelComplementacion lp_panel;

							lp_panel = lpc_data.getComplementacion();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.AREA_DEL_PREDIO:
						{
							PanelAreaPredio lp_panel;

							lp_panel = lpc_data.getAreaPredio();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						case CausalCorreccionCommon.DIRECCION_DEL_PREDIO:
						{
							PanelDireccionPredio lp_panel;

							lp_panel = lpc_data.getDireccionPredio();

							if(lp_panel != null)
							{
								ls_salvedad        = lp_panel.getSalvedad();
								ls_copiar          = lp_panel.getCopiar();
								ls_seleccionar     = lp_panel.getCopiarSeleccionadas();
							}

							break;
						}

						default:
							break;
					}

					if(StringUtils.isValidString(ls_salvedad))
					{
						if(ls_salvedad.length() < 100)
							throw new B2BException(ErrorKeys.ERROR_SALVEDAD_TAM);

						boolean lb_salvar;

						lb_salvar = false;

						if(isCopiarMasivos())
						{
							if(StringUtils.isValidString(ls_copiar))
							{
								if(BooleanUtils.getBooleanValue(ls_copiar))
								{
									if(StringUtils.isValidString(ls_seleccionar))
									{
										if(BooleanUtils.getBooleanValue(ls_seleccionar))
										{
											Collection<SolicitudMatricula> lcsm_matriculas;

											lcsm_matriculas = getMatriculasMasivasSalvedades();

											if(CollectionUtils.isValidCollection(lcsm_matriculas))
											{
												if(ab_validar)
													abrirDialogo(
													    "dlgConfirmacionCopiaSalvedad",
													    "fDialogConfirmacionCopiaSalvedad"
													);
												else
												{
													boolean                      lb_seleccionado;
													Iterator<SolicitudMatricula> lism_iterator;

													lb_seleccionado     = false;
													lism_iterator       = lcsm_matriculas.iterator();

													while(lism_iterator.hasNext() && !lb_seleccionado)
													{
														SolicitudMatricula lsm_iterador;

														lsm_iterador = lism_iterator.next();

														if(lsm_iterador != null)
															lb_seleccionado = lsm_iterador.isSeleccionado();
													}

													if(!lb_seleccionado)
														throw new B2BException(
														    ErrorKeys.ERROR_SIN_SELECCIONAR_MATRICULA
														);
													else
														lb_salvar = true;
												}
											}
										}
										else
											lb_salvar = true;
									}
									else
										throw new B2BException(ErrorKeys.ERROR_COPIAR_SALVEDAD);
								}
								else
									lb_salvar = true;
							}
							else
								throw new B2BException(ErrorKeys.ERROR_COPIAR_SALVEDAD);
						}
						else
						{
							lb_salvar     = true;
							ls_copiar     = EstadoCommon.NO;
						}

						if(lb_salvar)
						{
							AccSalvedadPredio lsp_salvedad;

							lsp_salvedad = new AccSalvedadPredio();

							lsp_salvedad.setDescripcion(ls_salvedad);
							lsp_salvedad.setIdCausal(NumericUtils.getLongWrapper(ls_idCausal));
							lsp_salvedad.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
							lsp_salvedad.setIdCirculo(getIdCirculo());
							lsp_salvedad.setIdMatricula(getIdMatricula());
							lsp_salvedad.setCopiar(ls_copiar);
							lsp_salvedad.setCopiarSeleccionadas(ls_seleccionar);
							lsp_salvedad.setMatriculas(getMatriculasMasivasSalvedades());

							irr_calificacionRemote.guardarSalvedades(
							    lsp_salvedad, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

							addMessage(MessagesKeys.SALVEDAD_GUARDADA);
							cerrarDialogo("dlgConfirmacionCopiaSalvedad", "fDialogConfirmacionCopiaSalvedad");
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SALVEDAD);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Guardar salvedades anotacion.
	 *
	 * @param ab_validar correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarSalvedadesAnotacion(boolean ab_validar)
	    throws B2BException
	{
		try
		{
			Anotacion la_anotacion;

			la_anotacion = getAnotacion();

			if(la_anotacion != null)
			{
				boolean lb_salvar;
				String  ls_copiar;
				String  ls_seleccionar;
				String  ls_salvedad;

				lb_salvar          = false;
				ls_copiar          = la_anotacion.getCopiar();
				ls_seleccionar     = la_anotacion.getCopiarSeleccionadas();
				ls_salvedad        = la_anotacion.getSalvedad();

				if(StringUtils.isValidString(ls_salvedad))
				{
					if(ls_salvedad.length() < 100)
						throw new B2BException(ErrorKeys.ERROR_SALVEDAD_TAM);

					if(isCopiarMasivos())
					{
						if(StringUtils.isValidString(ls_copiar))
						{
							if(BooleanUtils.getBooleanValue(ls_copiar))
							{
								if(StringUtils.isValidString(ls_seleccionar))
								{
									if(BooleanUtils.getBooleanValue(ls_seleccionar))
									{
										Collection<SolicitudMatricula> lcsm_matriculas;

										lcsm_matriculas = getMatriculasMasivasSalvedades();

										if(CollectionUtils.isValidCollection(lcsm_matriculas))
										{
											if(ab_validar)
												abrirDialogo(
												    "dlgConfirmacionCopiaSalvedad", "fDialogConfirmacionCopiaSalvedad"
												);
											else
											{
												boolean                      lb_seleccionado;
												Iterator<SolicitudMatricula> lism_iterator;

												lb_seleccionado     = false;
												lism_iterator       = lcsm_matriculas.iterator();

												while(lism_iterator.hasNext() && !lb_seleccionado)
												{
													SolicitudMatricula lsm_iterador;

													lsm_iterador = lism_iterator.next();

													if(lsm_iterador != null)
														lb_seleccionado = lsm_iterador.isSeleccionado();
												}

												if(!lb_seleccionado)
													throw new B2BException(ErrorKeys.ERROR_SIN_SELECCIONAR_MATRICULA);
												else
													lb_salvar = true;
											}
										}
									}
									else
										lb_salvar = true;
								}
								else
									throw new B2BException(ErrorKeys.ERROR_COPIAR_SALVEDAD);
							}
							else
								lb_salvar = true;
						}
						else
							throw new B2BException(ErrorKeys.ERROR_COPIAR_SALVEDAD);
					}
					else
					{
						lb_salvar     = true;
						ls_copiar     = EstadoCommon.NO;
					}

					if(lb_salvar)
					{
						AccSalvedadAnotacion lsp_salvedad;

						lsp_salvedad = new AccSalvedadAnotacion();

						lsp_salvedad.setDescripcion(ls_salvedad);
						lsp_salvedad.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						lsp_salvedad.setIdCirculo(getIdCirculo());
						lsp_salvedad.setIdMatricula(getIdMatricula());
						lsp_salvedad.setAnotacion(la_anotacion);
						lsp_salvedad.setCopiar(ls_copiar);
						lsp_salvedad.setCopiarSeleccionadas(ls_seleccionar);
						lsp_salvedad.setMatriculas(getMatriculasMasivasSalvedades());

						irr_calificacionRemote.guardarSalvedadesAnotacion(
						    lsp_salvedad, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

						addMessage(MessagesKeys.SALVEDAD_GUARDADA);
						cerrarDialogo("dlgConfirmacionCopiaSalvedad", "fDialogConfirmacionCopiaSalvedad");
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SALVEDAD);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método encargado de abrir el dialogo con la información de la anotación.
	 *
	 * @param ab_accion Boolean que válida si es segregación o matrícula con base en.
	 */
	public void infoAnotacion(boolean ab_accion)
	{
		DatosBasicos ldb_datosBasicos;

		ldb_datosBasicos = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			MatriculaBase lmb_matriculaBase;

			lmb_matriculaBase = ldb_datosBasicos.getMatriculaBase();

			if(lmb_matriculaBase != null)
			{
				try
				{
					com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacion;
					Long                                                  ll_idAnotacion;
					Map<String, Object>                                   lmso_data;

					ll_idAnotacion     = ab_accion ? lmb_matriculaBase.getIdAnotacion()
						                           : lmb_matriculaBase.getIdAnotacion1();
					lap_anotacion      = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio();

					if(!NumericUtils.isValidLong(ll_idAnotacion))
						throw new B2BException(ErrorKeys.ANOTACION_VALIDA_SELECCIONAR);

					lap_anotacion.setIdAnotacion(ll_idAnotacion);
					lap_anotacion.setIdCirculo(
					    ab_accion ? lmb_matriculaBase.getIdCirculo() : lmb_matriculaBase.getIdCirculo1()
					);
					lap_anotacion.setIdMatricula(
					    ab_accion ? lmb_matriculaBase.getIdMatricula() : lmb_matriculaBase.getIdMatricula1()
					);

					lmso_data = irr_referenceRemote.findInfoAnotacionBng(lap_anotacion);

					if(CollectionUtils.isValidCollection(lmso_data))
					{
						setInfoAnotacion(lmso_data);

						abrirDialogo("idDlgInfoAnotacion", "idFormInfoAnotacion");
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_INFORMACION);
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error(lb2be_e);
					addMessage(lb2be_e);
				}
				finally
				{
					actualizarFormulario();
				}
			}
		}
	}

	/**
	 * Método encargado de abrir el dialogo con la información de la anotación.
	 */
	public void infoAnotacion()
	{
		infoAnotacion(true);
	}

	/**
	 * Método encargado de abrir el dialogo con la información de la anotación.
	 *
	 * @param aps_predio Objeto que contiene la información de la anotación.
	 * @param ab_accion Boolean que válida si es segregación o matrícula con base en.
	 */
	public void infoAnotacion(PredioSegregado aps_predio, boolean ab_accion)
	{
		if(aps_predio != null)
		{
			try
			{
				com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacion;
				Long                                                  ll_idAnotacion;
				Map<String, Object>                                   lmso_data;

				ll_idAnotacion     = ab_accion ? aps_predio.getIdAnotacion() : aps_predio.getIdAnotacion1();
				lap_anotacion      = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio();

				if(!NumericUtils.isValidLong(ll_idAnotacion))
					throw new B2BException(ErrorKeys.ANOTACION_VALIDA_SELECCIONAR);

				lap_anotacion.setIdAnotacion(ll_idAnotacion);
				lap_anotacion.setIdCirculo(ab_accion ? aps_predio.getIdCirculo() : aps_predio.getIdCirculo1());
				lap_anotacion.setIdMatricula(ab_accion ? aps_predio.getIdMatricula() : aps_predio.getIdMatricula1());

				if(aps_predio.isAnotacionesAcc())
					lmso_data = irr_referenceRemote.findInfoAnotacionAcc(lap_anotacion);
				else
					lmso_data = irr_referenceRemote.findInfoAnotacionBng(lap_anotacion);

				if(CollectionUtils.isValidCollection(lmso_data))
				{
					setInfoAnotacion(lmso_data);

					abrirDialogo("idDlgInfoAnotacion", "idFormInfoAnotacion");
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_INFORMACION);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
			}
			finally
			{
				actualizarFormulario();
			}
		}
	}

	/**
	 * Info anotacion.
	 *
	 * @param aps_predio correspondiente al valor de aps predio
	 */
	public void infoAnotacion(PredioSegregado aps_predio)
	{
		infoAnotacion(aps_predio, true);
	}

	/**
	 * Limpiar anotacion.
	 */
	public void limpiarAnotacion()
	{
		setAnotacion(null);
	}

	/**
	 * Limpiar causal.
	 */
	public void limpiarCausal()
	{
		setIdCausal(null);
	}

	/**
	 * Limpiar salvedades.
	 */
	public void limpiarSalvedades()
	{
		Collection<SolicitudMatricula> lcsm_data;

		lcsm_data = getMatriculasMasivasSalvedades();

		if(CollectionUtils.isValidCollection(lcsm_data))
		{
			for(SolicitudMatricula lsm_iterador : lcsm_data)
			{
				if(lsm_iterador != null)
					lsm_iterador.setSeleccionado(false);
			}
		}

		setAnotacion(null);
		setIdCausal(null);
	}

	/**
	 * Mostrar copia masiva documento.
	 */
	public void mostrarCopiaMasivaDocumento()
	{
	}

	/**
	 * On row edit.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 */
	public void onRowEdit()
	{
		// TODO
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

	/**
	 * Metodo encargado de procesar el cargue de excel de intervinientes.
	 *
	 * @param afue_event Argumento de tipo <code>FileUploadEvent</code> que contiene los bytes del archivo.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procesarExcelIntervinientes(FileUploadEvent afue_event)
	    throws B2BException
	{
		try
		{
			if(afue_event != null)
				procesarExcelIntervinientes(afue_event, true, getIntervinientesAgregados());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procesarExcelIntervinientes", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param afue_event correspondiente al valor del tipo de objeto FileUploadEvent
	 * @return devuelve el valor de String
	 *
	 */
	public String processFilePredialMasivo(FileUploadEvent afue_event)
	{
		try
		{
			Ajax         la_ajax;
			String       ls_mensaje;
			String       ls_solicitud;
			UploadedFile luf_file;

			la_ajax          = PrimeFaces.current().ajax();
			ls_mensaje       = null;
			ls_solicitud     = getIdSolicitud();
			luf_file         = afue_event.getFile();

			if(luf_file != null)
			{
				if(luf_file.getSize() <= 500000)
				{
					setDataInfoPredial(null);
					setFilePredial(null);

					byte[] lba_excelFile;

					lba_excelFile = luf_file.getContents();

					if((lba_excelFile != null) && (luf_file.getFileName() != null))
						lba_excelFile = FileLoadPredial(
							    lba_excelFile, luf_file.getFileName(), getUserId(), ls_solicitud, isAnotacionApertura()
							);

					if(lba_excelFile != null)
					{
						InputStream lis_stream = new ByteArrayInputStream(lba_excelFile);

						setFilePredial(
						    new DefaultStreamedContent(lis_stream, luf_file.getContentType(), "ARCHIVO_DE_SALIDA.xlsx")
						);
					}
				}
				else
					throw new B2BException("El Archivo de Cargue de Excel no puede exceder los 500 Kb.");
			}
			else
				ls_mensaje = "No se encontro un Archivo de Cargue  para procesar.";

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = "Procesamiento de Archivo Cargue  terminado. Por favor verifique si hay errores en el archivo de salida";

			addMessage("I", ls_mensaje);

			la_ajax.update("idDtActoAsociadoGeneral");
			la_ajax.update("idDataTablePredioAsociado");
			la_ajax.update("idClnkRespuesta");
			la_ajax.update("idexcelPredial");
			la_ajax.update("idMatriculasAperturar");
			la_ajax.update("idOPLogoExcel");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}
		catch(Exception lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage("E", lb2be_e.getMessage());
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
		}

		return null;
	}

	/**
	 * Metodo encargado de reordenar las anotaciones.
	 */
	public void reordenarAnotaciones()
	{
		try
		{
			Collection<Anotacion> lca_anotacionesAgregadas;

			lca_anotacionesAgregadas = getAnotacionesAgregadas();

			if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
			{
				Anotacion la_anotacion;

				la_anotacion = new Anotacion();

				la_anotacion.setAnotacionesAgregadas(lca_anotacionesAgregadas);

				iasr_antiguoSistemaRemote.reordenarAnotaciones(
				    la_anotacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				addMessage(MessagesKeys.ANOTACIONES_ORDENADAS_CORRECTAMENTE);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("reordenarAnotaciones", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Salvar anotacion correccion.
	 */
	public void salvarAnotacionCorreccion()
	{
		try
		{
			AnotacionPredio lap_anotacionPredio;

			lap_anotacionPredio = null;

			if(isNuevaAnotacion())
			{
				lap_anotacionPredio = new AnotacionPredio();

				lap_anotacionPredio.setIdNaturalezaJuridica(getCodigoNaturalezaJuridicaSeleccionada());
			}

			agregarAnotacion(true, false, getIdCirculo(), getIdMatricula());

			limpiarDetalleAnotaciones();

			setMostrarBandejaAnotaciones(true);
			setMostrarDetalleAnotaciones(false);
			setMostrarBotonSalvarAnotacion(false);

			if(isNuevaAnotacion())
			{
				Collection<Anotacion> lca_data;

				lca_data = getAnotacionesAgregadas();

				if(CollectionUtils.isValidCollection(lca_data))
				{
					Anotacion la_anotacion;

					la_anotacion = null;

					for(Anotacion la_iterador : lca_data)
						la_anotacion = la_iterador;

					validarAnotacionApertura(lap_anotacionPredio, la_anotacion);
				}
			}
			else
			{
				setAnotacion(null);
				setMatriculasCorreccion(null);
			}

			{
				Collection<Anotacion> lca_anotacionesAgregadas;

				lca_anotacionesAgregadas = getAnotacionesAgregadas();

				if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
				{
					AnotacionPredio lap_datos;

					lap_datos = new AnotacionPredio();

					lap_datos.setIdCirculo(getIdCirculo());
					lap_datos.setIdMatricula(getIdMatricula());
					lap_datos.setIdTurno(getIdTurno());

					setAnotacionesAgregadas(
					    idasr_digitadorAntiguoSistemaRemote.validarReordenamientoPorFechaAnotacion(
					        lca_anotacionesAgregadas, lap_datos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAnotacionCorreccion", lb2be_e);
		}
		finally
		{
			actualizarFormulario();
		}
	}

	/**
	 * Método encargado de actualizar las causales de correccion para solicitud,
	 * circulo y matricula seleccionados.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarCausalesCorreccion()
	    throws B2BException
	{
		try
		{
			Collection<CausalCorreccion> lccc_causales;

			lccc_causales = getCausales();

			if(CollectionUtils.isValidCollection(lccc_causales))
			{
				BigInteger lbi_idMatricula;
				String     ls_idSolicitud;
				String     ls_idCirculo;

				ls_idCirculo        = getIdCirculo();
				lbi_idMatricula     = NumericUtils.getBigInteger(getIdMatricula());
				ls_idSolicitud      = getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					for(CausalCorreccion lcc_causal : lccc_causales)
					{
						if((lcc_causal != null))
							abrirPanelCausal(lcc_causal.getIdCausalCorreccion(), lcc_causal.isSeleccionado(), true);
					}

					Long      ll_idMatricula;
					Anotacion la_anotacion;

					ll_idMatricula = NumericUtils.getLongWrapper(lbi_idMatricula);

					setComplementacionCalificacion(null);
					cargarTabs(ls_idCirculo, ll_idMatricula, true);

					la_anotacion = getAnotacion();

					if(la_anotacion != null)
					{
						setDatosAntSistemaAnotacion(la_anotacion.getDatosAntiguoSistema());

						Collection<Anotacion> lca_anotacionesAgregadas;

						lca_anotacionesAgregadas = la_anotacion.getAnotacionesAgregadas();

						if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
						{
							Collection<DetalleAntSistema> lcdas_detalles;

							lcdas_detalles = getDetallesAntSistema();

							for(Anotacion la_iterador : lca_anotacionesAgregadas)
							{
								if(la_iterador != null)
									la_iterador.setDetallesAntSistema(lcdas_detalles);
							}

							setAnotacionesAgregadas(lca_anotacionesAgregadas);
							setTotalAnotaciones(lca_anotacionesAgregadas.size());
						}

						cargarTiposOficinaAnotaciones(EstadoCommon.D);
					}

					{
						DatosBasicos ldb_data;

						ldb_data = getDatosBasicos();

						if(ldb_data != null)
						{
							AccPredioRegistro lapr_predio;

							lapr_predio = ldb_data.getAccPredioRegistro();

							if(lapr_predio != null)
							{
								PredioRegistro lpr_predio;

								lpr_predio = ldb_data.getPredioRegistro();

								if(lpr_predio == null)
									lpr_predio = new PredioRegistro();

								lpr_predio.setIdTipoPredio(lapr_predio.getIdTipoPredio());
								lpr_predio.setIdTipoUsoSuelo(lapr_predio.getIdTipoUsoSuelo());
							}
						}
					}

					cargarCheks(ls_idCirculo, ll_idMatricula, ls_idSolicitud);
					setMostrarBandeja(false);
					setMostrarDetalle(true);
					setMostrarBandejaAnotaciones(true);
					setMostrarDetalleAnotaciones(false);
					setCopiarComplementacion(null);
					setCopiarComplementacionSeleccionadas(null);
					setFormulario(cs_ID_FORMULARIO_TV);

					PrimeFaces.current().ajax().update("idFormTabs");
				}
				else
					throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
			}
			else
				throw new B2BException("No se encontraron causales registradas");

			PrimeFaces.current().executeScript("PF('dlgCorrecciones').hide();");

			addMessage(MessagesKeys.PROCESO_COMPLETADO);

			{
				SolicitudMatricula lsm_sm;

				lsm_sm = getMatriculaActual();

				if(lsm_sm != null)
				{
					Collection<SolicitudMatricula> lcsm_complementacion;
					Collection<SolicitudMatricula> lcsm_documento;
					Collection<SolicitudMatricula> lcsm_intervinientes;
					Collection<SolicitudMatricula> lcsm_salvedades;

					lcsm_complementacion     = getMatriculasMasivasComplementacion();
					lcsm_documento           = getMatriculasMasivasDocumento();
					lcsm_intervinientes      = getMatriculasMasivasIntervinientes();
					lcsm_salvedades          = getMatriculasMasivasSalvedades();

					if(CollectionUtils.isValidCollection(lcsm_complementacion))
						lcsm_complementacion.remove(lsm_sm);

					if(CollectionUtils.isValidCollection(lcsm_documento))
						lcsm_documento.remove(lsm_sm);

					if(CollectionUtils.isValidCollection(lcsm_intervinientes))
						lcsm_intervinientes.remove(lsm_sm);

					if(CollectionUtils.isValidCollection(lcsm_salvedades))
						lcsm_salvedades.remove(lsm_sm);
				}
			}

			{
				String ls_idCirculo;
				Long   ll_idMatricula;

				ls_idCirculo       = getIdCirculo();
				ll_idMatricula     = getIdMatricula();

				if(NumericUtils.isValidLong(ll_idMatricula) && StringUtils.isValidString(ls_idCirculo))
				{
					DatosBasicos    ldb_datosBasicos;
					MatriculaBase   lmb_matriculaBase;
					PredioSegregado lps_predioSegregadoAgregar;

					ldb_datosBasicos               = (getDatosBasicos() != null) ? getDatosBasicos() : new DatosBasicos();
					lmb_matriculaBase              = (ldb_datosBasicos.getMatriculaBase() != null)
						? ldb_datosBasicos.getMatriculaBase() : new MatriculaBase();
					lps_predioSegregadoAgregar     = new PredioSegregado();

					lps_predioSegregadoAgregar.setIdCirculo(ls_idCirculo);
					lps_predioSegregadoAgregar.setIdMatricula(ll_idMatricula);
					lps_predioSegregadoAgregar.setIdCirculo1(ls_idCirculo);
					lmb_matriculaBase.setIdCirculo1(ls_idCirculo);
					lmb_matriculaBase.setIdMatricula1(ll_idMatricula);
					lmb_matriculaBase.setIdCirculo(ls_idCirculo);
					ldb_datosBasicos.setMatriculaBase(lmb_matriculaBase);

					setDatosBasicos(ldb_datosBasicos);
					setPredioSegregadoAgregar(lps_predioSegregadoAgregar);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo encargado de salvar los datos de la pantalla ejecución correcciones
	 * por id causal.
	 *
	 * @param as_causal            Argumento de tipo <code>String</code> que contiene el id causal a
	 *            salvar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarPanel(String as_causal)
	    throws B2BException
	{
		salvarPanel(false, as_causal);
	}

	/**
	 * Salvar panel.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarPanel()
	    throws B2BException
	{
		salvarPanel(true, getIdCausal());
	}

	/**
	 * Metodo encargado de salvar los datos de la pantalla ejecución correcciones
	 * por id causal.
	 *
	 * @param ab_salvar            Argumento de tipo <code>boolean</code> que determina si se salva
	 *            definitivo <code>true</code> o se muestra mensaje de confirmación
	 *            <code>false</code>.
	 * @param as_causal            Argumento de tipo <code>String</code> que contiene el id causal a
	 *            salvar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarPanel(boolean ab_salvar, String as_causal)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_causal))
			{
				setIdCausal(as_causal);

				if(ab_salvar)
				{
					switch(as_causal)
					{
						case CausalCorreccionCommon.DATOS_BASICOS:
							guardarDatosUbicacion();

							break;

						case CausalCorreccionCommon.INFORMACION_DE_APERTURA:
							guardarDatosApertura();

							break;

						case CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS:
							guardarDatosMatriculas();

							break;

						case CausalCorreccionCommon.INFORMACION_CATASTRAL:
							guardarDatosCatastral();

							break;

						case CausalCorreccionCommon.MATRICULAS_SEGREGADAS:
							guardarDatosSegregacion();

							break;

						case CausalCorreccionCommon.DATOS_ANT_SISTEMA:
							guardarDatosAntSistema();

							break;

						case CausalCorreccionCommon.DETALLE_ANT_SISTEMA:
							guardarDatosDetallesAntSistema();

							break;

						case CausalCorreccionCommon.LINDEROS:
							guardarDatosLinderos();

							break;

						case CausalCorreccionCommon.COMPLEMENTACION:
							guardarDatosComplementacion(true);

							break;

						case CausalCorreccionCommon.AREA_DEL_PREDIO:
							guardarDatosArea();

							break;

						case CausalCorreccionCommon.DIRECCION_DEL_PREDIO:
							guardarDatosDireccion();

							break;

						case CausalCorreccionCommon.DATOS_ANOTACION:
							guardarDatosAnotacion();

							break;

						case CausalCorreccionCommon.DATOS_DEL_DOCUMENTO:
							guardarDatosDocumentoAnotacion(true);

							break;

						case CausalCorreccionCommon.ESPECIFICACION:
							guardarDatosEspecificacionAnotacion();

							break;

						case CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION:
							guardarDatosAntSistemaAnotacion();

							break;

						case CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES:
							guardarIntervinientesAnotacion();

							break;

						default:
							break;
					}
				}
				else
				{
					Object[] loa_oa;

					loa_oa = new String[1];

					switch(as_causal)
					{
						case CausalCorreccionCommon.DATOS_BASICOS:
							loa_oa[0] = CausalCorreccionCommon.DATOS_BASICOS_NOMBRE;

							break;

						case CausalCorreccionCommon.INFORMACION_DE_APERTURA:
							loa_oa[0] = CausalCorreccionCommon.INFORMACION_DE_APERTURA_NOMBRE;

							break;

						case CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS:
							loa_oa[0] = CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS_NOMBRE;

							break;

						case CausalCorreccionCommon.INFORMACION_CATASTRAL:
							loa_oa[0] = CausalCorreccionCommon.INFORMACION_CATASTRAL_NOMBRE;

							break;

						case CausalCorreccionCommon.MATRICULAS_SEGREGADAS:
							loa_oa[0] = CausalCorreccionCommon.MATRICULAS_SEGREGADAS_NOMBRE;

							break;

						case CausalCorreccionCommon.DATOS_ANT_SISTEMA:
							loa_oa[0] = CausalCorreccionCommon.DATOS_ANT_SISTEMA_NOMBRE;

							break;

						case CausalCorreccionCommon.DETALLE_ANT_SISTEMA:
							loa_oa[0] = CausalCorreccionCommon.DETALLE_ANT_SISTEMA_NOMBRE;

							break;

						case CausalCorreccionCommon.LINDEROS:
							loa_oa[0] = CausalCorreccionCommon.LINDEROS_NOMBRE;

							break;

						case CausalCorreccionCommon.COMPLEMENTACION:
							loa_oa[0] = CausalCorreccionCommon.COMPLEMENTACION_NOMBRE;

							break;

						case CausalCorreccionCommon.AREA_DEL_PREDIO:
							loa_oa[0] = CausalCorreccionCommon.AREA_DEL_PREDIO_NOMBRE;

							break;

						case CausalCorreccionCommon.DIRECCION_DEL_PREDIO:
							loa_oa[0] = CausalCorreccionCommon.DIRECCION_DEL_PREDIO_NOMBRE;

							break;

						case CausalCorreccionCommon.DATOS_ANOTACION:
							loa_oa[0] = CausalCorreccionCommon.DATOS_ANOTACION_NOMBRE;

							break;

						case CausalCorreccionCommon.DATOS_DEL_DOCUMENTO:
							loa_oa[0] = CausalCorreccionCommon.DATOS_DEL_DOCUMENTO_NOMBRE;

							break;

						case CausalCorreccionCommon.ESPECIFICACION:
							loa_oa[0] = CausalCorreccionCommon.ESPECIFICACION_NOMBRE;

							break;

						case CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION:
							loa_oa[0] = CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION_NOMBRE;

							break;

						case CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES:
							loa_oa[0] = CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES_NOMBRE;

							break;

						default:
							break;
					}

					setMensajeConfirmacionSalvar(getMessages().getString(MessagesKeys.CONFIRMAR_SALVAR, loa_oa));

					PrimeFaces.current().executeScript("PF('dlgConfirmacionSalvar').show();");
					actualizarComponente("fDialogConfirmacion");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarFormulario();
		}
	}

	/**
	 * Método encargado de terminar el englobe para la anotación.
	 *
	 * @throws B2BException
	 */
	public void terminarEnglobeAnotacion()
	    throws B2BException
	{
		try
		{
			Anotacion            la_anotacion;
			RegistroCalificacion lrc_data;

			la_anotacion     = getAnotacion();
			lrc_data         = getMatriculas();

			if((lrc_data != null) && (la_anotacion != null))
			{
				BeanDireccion                    lb_beanDireccion;
				Collection<DireccionDelPredio>   lcddp_direcciones;
				Long                             ll_idMatricula;
				HashMap<String, DireccionPredio> lhmsdp_direcciones;

				lb_beanDireccion       = getBeanDireccion();
				lcddp_direcciones      = lb_beanDireccion.getDireccionesPredio2();
				ll_idMatricula         = lrc_data.getIdMatricula();
				lhmsdp_direcciones     = lrc_data.getDireccionesSeleccionadas();

				if(!CollectionUtils.isValidCollection(lhmsdp_direcciones) && lrc_data.isPrimerEnglobe())
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
				lrc_data.setIdUsuario(getUserId());
				lrc_data.setIpAddress(getRemoteIpAddress());
				lrc_data.setObservaciones(getObservaciones());
				lrc_data.setEnglobeAnotacion(true);

				irr_calificacionRemote.salvarEnglobes(lrc_data);

				la_anotacion.setEnglobeTerminado(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(cs_ID_GROWL);
			actualizarFormulario();
		}
	}

	/**
	 * Método encargado de validar si la anotación contiene un acto que genere apertura de matrículas.
	 *
	 * @param aap_anotacionPredio Objeto que conitiene la información de la anotación del predio.
	 * @param aa_anotacion Objeto que contiene la información de la anotación.
	 * @throws B2BException
	 */
	public void validarAnotacionApertura(AnotacionPredio aap_anotacionPredio, Anotacion aa_anotacion)
	    throws B2BException
	{
		if((aap_anotacionPredio != null) && (aa_anotacion != null))
		{
			try
			{
				boolean lb_anotacionApertura;
				boolean lb_anotacionEnglobe;

				lb_anotacionApertura     = irr_calificacionRemote.validarAnotacionApertura(
					    aap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				lb_anotacionEnglobe      = irr_calificacionRemote.validarAnotacionEnglobe(
					    aap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lb_anotacionApertura || lb_anotacionEnglobe)
				{
					if(lb_anotacionEnglobe)
						aa_anotacion.setMatriculasCorreccion(getMatriculasCorreccion());

					consultarAnotacion(aa_anotacion);
					cerrarPanelesAnotacion();
				}
				else
				{
					setAnotacion(null);
					setMatriculasCorreccion(null);
				}

				setAnotacionApertura(lb_anotacionApertura);
				setAnotacionEnglobe(lb_anotacionEnglobe);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("validarAnotacionApertura", lb2be_e);

				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(cs_ID_GROWL);
			}
		}
	}

	/**
	 * Método encargado de validar si la anotacion genera segregacion o es de apertura.
	 *
	 * @param aps_predio Objeto que contiene la información de la anotación.
	 */
	public void validarAnotacionAperturaSegregacion(PredioSegregado aps_predio)
	{
		validarAnotacionAperturaSegregacion(aps_predio, true);
	}

	/**
	 * Método encargado de validar si la anotacion genera segregacion o es de apertura.
	 */
	public void validarAnotacionAperturaSegregacion()
	{
		validarAnotacionAperturaSegregacion(null, true);
	}

	/**
	 * Método encargado de validar si la anotacion genera segregacion o es de apertura.
	 *
	 * @param ab_accion boolean que indica si es la anotacion que apertura o la de segregación.
	 */
	public void validarAnotacionAperturaSegregacion(boolean ab_accion)
	{
		validarAnotacionAperturaSegregacion(null, ab_accion);
	}

	/**
	 * Método encargado de validar si la anotacion genera segregacion o es de apertura.
	 *
	 * @param aps_predio Objeto que contiene la información de la anotación.
	 * @param ab_accion boolean que indica si es la anotacion que apertura o la de segregación.
	 */
	public void validarAnotacionAperturaSegregacion(PredioSegregado aps_predio, boolean ab_accion)
	{
		PredioSegregado lps_predio;

		lps_predio = (aps_predio == null) ? getPredioSegregadoAgregar() : aps_predio;

		if(lps_predio != null)
		{
			try
			{
				Long ll_idAnotacion;

				ll_idAnotacion = ab_accion ? lps_predio.getIdAnotacion() : lps_predio.getIdAnotacion1();

				if(!NumericUtils.isValidLong(ll_idAnotacion))
					throw new B2BException(ErrorKeys.ANOTACION_VALIDA_SELECCIONAR);

				if(!irr_calificacionRemote.generaSegregacionOApertura(lps_predio, ab_accion))
				{
					setAnotacionMatriz(ab_accion);

					PrimeFaces.current().executeScript("PF('dlgNoAperturaSegregacion').show();");
					actualizarComponente("fNoAperturaSegregacion");
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
			}
			finally
			{
				actualizarFormulario();
			}
		}
	}

	/**
	 * Método encargado de validar la anotación de corecciones a crear.
	 *
	 * @throws B2BException
	 */
	public void validarAnotacionCorreccionCrear()
	    throws B2BException
	{
		if(isNuevaAnotacion())
		{
			AnotacionPredio lap_anotacionPredio;
			Anotacion       la_anotacion;

			lap_anotacionPredio     = getAnotacionPredio();
			la_anotacion            = getAnotacion();

			if((lap_anotacionPredio != null) && (la_anotacion != null))
			{
				try
				{
					Map<String, String> lmss_mensaje;

					lap_anotacionPredio.setIdCirculo(getIdCirculo());
					lap_anotacionPredio.setIdMatricula(getIdMatricula());
					lap_anotacionPredio.setIdNaturalezaJuridica(getCodigoNaturalezaJuridicaSeleccionada());

					lmss_mensaje = irr_calificacionRemote.validarAnotacionCorreccionCrear(
						    lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lmss_mensaje != null)
					{
						if(lmss_mensaje.containsValue(IdentificadoresCommon.ANOTACION))
							addMessage(MessagesKeys.MESSAGE_ANOTACION_ACTO_0361);

						if(lmss_mensaje.containsValue(IdentificadoresCommon.ENGLOBE))
							PrimeFaces.current().executeScript("PF('dlgConfirmacionEnglobe').show();");

						la_anotacion.setCierreFolio(lmss_mensaje.containsValue(IdentificadoresCommon.CIERRE_FOLIO));
					}

					setAnotacion(la_anotacion);
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error("validarAnotacionCorreccionCrear", lb2be_e);

					setCodigoNaturalezaJuridicaSeleccionada(null);

					addMessage(lb2be_e);
				}
				finally
				{
					PrimeFaces.current().ajax().update(cs_ID_GROWL);
				}
			}
		}
	}

	/**
	 * Método encargado de validar la fecha de la anotación a crear.
	 *
	 * @throws B2BException
	 */
	public void validarFechaAnotacionCrear()
	    throws B2BException
	{
		if(isNuevaAnotacion())
		{
			AnotacionPredio lap_anotacionPredio;

			lap_anotacionPredio = getAnotacionPredio();

			if(lap_anotacionPredio != null)
			{
				try
				{
					String ls_mensaje;

					lap_anotacionPredio.setIdCirculo(getIdCirculo());
					lap_anotacionPredio.setIdMatricula(getIdMatricula());
					lap_anotacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));

					ls_mensaje = irr_calificacionRemote.validarFechaAnotacionCrearCorrecciones(
						    lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(StringUtils.isValidString(ls_mensaje))
						addMessage(MessagesKeys.MESSAGE_FECHA_ANOTACION_POSTERIOR_RADICACION_ANT);
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error("validarFechaAnotacionCrear", lb2be_e);
					addMessage(lb2be_e);
				}
				finally
				{
					PrimeFaces.current().ajax().update(cs_ID_GROWL);
				}
			}
		}
	}

	/**
	 * Método encargado de validar el interviniente seleccionado.
	 *
	 * @param ap_persona Objeto que contiene la información de la persona.
	 * @throws B2BException
	 */
	public void validarInterviniente(Persona ap_persona)
	    throws B2BException
	{
		if(isNuevaAnotacion() && (ap_persona != null))
		{
			AnotacionPredio lap_anotacionPredio;

			lap_anotacionPredio = getAnotacionPredio();

			if(lap_anotacionPredio != null)
			{
				try
				{
					String ls_mensaje;

					lap_anotacionPredio.setIdNaturalezaJuridica(getCodigoNaturalezaJuridicaSeleccionada());

					ls_mensaje = irr_calificacionRemote.validarInterviniente(
						    ap_persona, lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(StringUtils.isValidString(ls_mensaje))
						addMessage(MessagesKeys.MESSAGE_INTERVINIENTE_TESTAMENTO);
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error("validarInterviniente", lb2be_e);

					ap_persona.setSeleccionado(false);

					addMessage(lb2be_e);
				}
				finally
				{
					PrimeFaces.current().ajax().update(cs_ID_GROWL);
				}
			}
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String validarSalvedades()
	{
		try
		{
			AccSalvedadPredio           lsp_salvedad;
			boolean                     lb_validarSegregacion;
			Collection<PredioSegregado> lcps_datos;
			PermisosCorreccionesUI      lpcui_data;
			TurnoHistoria               lth_turnoHistoria;
			String                      ls_idProceso;

			lsp_salvedad              = new AccSalvedadPredio();
			lb_validarSegregacion     = false;
			lcps_datos                = getPrediosSegregados();
			lpcui_data                = getPermisosCorrecciones();
			lth_turnoHistoria         = new TurnoHistoria();
			ls_idProceso              = null; 
			
			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lth_turnoHistoria      = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoria);
			if(lth_turnoHistoria != null)
				ls_idProceso = lth_turnoHistoria.getIdProceso();

			if(lpcui_data != null)
			{
				PanelMatriculasSegregacion lpms_panel;

				lpms_panel = lpcui_data.getMatriculasSegregacion();

				if(lpms_panel != null)
				{
					Collection<AreaPredio> lcap_matriculasInformacion;

					lb_validarSegregacion          = lpms_panel.isSeleccionado();
					lcap_matriculasInformacion     = lpms_panel.getMatriculasInformacion();

					if(CollectionUtils.isValidCollection(lcap_matriculasInformacion))
					{
						for(AreaPredio lap_iterador : lcap_matriculasInformacion)
						{
							if((lap_iterador != null) && !lap_iterador.isRevisado())
							{
								Object[] loa_arg;

								loa_arg        = new String[2];
								loa_arg[0]     = lap_iterador.getIdCirculo();
								loa_arg[1]     = StringUtils.getString(lap_iterador.getIdMatricula());

								throw new B2BException(
								    ErrorKeys.ERROR_REVISAR_MATRICULAS_SEGREGACION_APERTURA, loa_arg
								);
							}
						}
					}

					if(lb_validarSegregacion && !lpms_panel.isMatriculasAperturadas())
					{
						Collection<MatriculaSegregacion> lcms_data;

						lcms_data = lpms_panel.getMatriculasSegregacion();

						if(CollectionUtils.isValidCollection(lcms_data))
						{
							for(MatriculaSegregacion lms_iterador : lcms_data)
							{
								if(lms_iterador != null)
								{
									String ls_areaTerreno;
									String ls_nombrePredio;

									ls_areaTerreno      = lms_iterador.getAreaTerrenoLectura();
									ls_nombrePredio     = lms_iterador.getNombrePredio();

									if(
									    !StringUtils.isValidString(ls_areaTerreno)
										    || !StringUtils.isValidString(ls_nombrePredio)
									)
										throw new B2BException(ErrorKeys.ERROR_GENERAR_APERTURA_MATRICULAS);
								}
							}
						}
					}
				}
			}

			
			
			if(isCierreFolio() && StringUtils.isValidString(ls_idProceso) && ls_idProceso.equalsIgnoreCase("6"))
			{
				String ls_motivo;
				String ls_justificacion;

				ls_motivo            = getMotivoCambioEstado();
				ls_justificacion     = getJustificacionCambio();

				if(!StringUtils.isValidString(ls_motivo))
					throw new B2BException(ErrorKeys.ERROR_MOTIVO_CAMBIO_ESTADO);

				if(!StringUtils.isValidString(ls_justificacion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);

				lsp_salvedad.setCambioEstado(ls_motivo);
				lsp_salvedad.setJustificacion(ls_justificacion);
				lsp_salvedad.setCierreFolio(true);
			}

			lsp_salvedad.setIdCirculo(getIdCirculo());
			lsp_salvedad.setIdMatricula(getIdMatricula());

			irr_calificacionRemote.validarSalvedades(
			    NumericUtils.getLongWrapper(getIdTurnoHistoria()), lsp_salvedad, getObservaciones(), getUserId(),
			    getLocalIpAddress(), getRemoteIpAddress(), lb_validarSegregacion, lcps_datos
			);

			setMostrarDetalle(false);
			setMostrarBandeja(true);
			datosCorrecciones();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return null;
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
	 * Retorna el valor del objeto de String.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @return devuelve el valor de String
	 */
	private String camposAnotacion(Anotacion aa_anotacion)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(aa_anotacion != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			{
				PanelDatosAnotacion lp_panel;

				lp_panel = aa_anotacion.getPanelDatosAnotaciones();

				if(lp_panel != null)
				{
					if(lp_panel.isNumeroAnotacion())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDA_NUMERO_ANOTACION_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isFechaAnotacion())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDA_FECHA_ANOTACION_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isRadicacion())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDA_RADICACION_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isEstadoAnotacion())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDA_ESTADO_ANOTACION_NOMBRE);
						lsb_return.append(ls_coma);
					}
				}
			}

			{
				PanelDatosDocumento lp_panel;

				lp_panel = aa_anotacion.getPanelDatosDocumento();

				if(lp_panel != null)
				{
					if(lp_panel.isTipoDocumento())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_TIPO_DOCUMENTO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isNumeroDocumento())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_NUMERO_DOCUMENTO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isFechaDocumento())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_FECHA_DOCUMENTO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isTipoOficina())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_TIPO_OFICINA_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isTipoEntidad())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_TIPO_ENTIDAD_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isPais())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_PAIS_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isDepartamento())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_DEPARTAMENTO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isMunicipio())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_MUNICIPIO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isOficinaOrigen())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDD_OFICINA_ORIGEN_NOMBRE);
						lsb_return.append(ls_coma);
					}
				}
			}

			{
				PanelDetalleAntSistemaAnotacion lp_panel;

				lp_panel = aa_anotacion.getPanelDetalleAntSistemaAnotacion();

				if(lp_panel != null)
				{
					if(lp_panel.isSeleccionado())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDDASA_DETALLE_SELECCIONADO_NOMBRE);
						lsb_return.append(ls_coma);
					}
				}
			}

			{
				PanelDetalleIntervinientes lp_panel;

				lp_panel = aa_anotacion.getPanelDetalleIntervinientes();

				if(lp_panel != null)
				{
					if(lp_panel.isDatosPersona())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDBI_DATOS_PERSONA_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isRol())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDBI_ROL_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isPropietario())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDBI_PROPIETARIO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isPorcentaje())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDBI_PORCENTAJE_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isCalidadInterviniente())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDBI_CALIDAD_INTERVINIENTE_NOMBRE);
						lsb_return.append(ls_coma);
					}
				}
			}

			{
				PanelIntervinientes lp_panel;

				lp_panel = aa_anotacion.getPanelIntervinientes();

				if(lp_panel != null)
				{
					if(lp_panel.isAgregar())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PDBI_AGREGAR_NOMBRE);
						lsb_return.append(ls_coma);
					}
				}
			}

			{
				PanelEspecificacion lp_panel;

				lp_panel = aa_anotacion.getPanelEspecificacion();

				if(lp_panel != null)
				{
					if(lp_panel.isCodigoActo())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PE_CODIGO_ACTO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isDescripcionActo())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PE_DESCRIPCION_ACTO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isValorActo())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PE_VALOR_ACTO_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isAnotacionCancela())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PE_ANOTACION_CANCELADA_NOMBRE);
						lsb_return.append(ls_coma);
					}

					if(lp_panel.isComentario())
					{
						lsb_return.append(ls_espacio);
						lsb_return.append(CamposCorreccionCommon.PE_COMENTARIOS_NOMBRE);
						lsb_return.append(ls_coma);
					}
				}
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelAreaPredio
	 * @return devuelve el valor de String
	 */
	private String camposAreaPredio(PanelAreaPredio ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isAreaConstruida())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAP_AREA_CONSTRUIDA_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isAreaPrivada())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAP_AREA_PRIVADA_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isAreaTerreno())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAP_AREA_TERRENO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isCoeficiente())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAP_COEFICIENTE_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isUsoPredio())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAP_USO_PREDIO_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelComplementacion
	 * @return devuelve el valor de String
	 */
	private String camposComplementacion(PanelComplementacion ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isSeleccionado())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PC_COMPLEMENTACION_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelAntSistemaSolicitud
	 * @return devuelve el valor de String
	 */
	private String camposDatosAntSistema(PanelAntSistemaSolicitud ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isCirculoRegistral())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAS_CIRCULO_REGISTRAL_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isTipoPredio())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAS_TIPO_PREDIO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isNombrePredio())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAS_NOMBRE_PREDIO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isPais())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAS_PAIS_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isDepartamento())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAS_DEPARTAMENTO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isMunicipio())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAS_MUNICIPIO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isCantidadCertificados())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PAS_CANTIDAD_CERTIFICADOS_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelApertura
	 * @return devuelve el valor de String
	 */
	private String camposDatosBasicosApertura(PanelApertura ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isFechaApertura())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_FECHA_APERTURA_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isRadicacion())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_RADICACION_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isTipoDocumento())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_TIPO_DOCUMENTO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isNumeroDocumento())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_NUMERO_DOCUMENTO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isFechaDocumento())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_FECHA_DOCUMENTO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isTipoOficina())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_TIPO_OFICINA_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isPais())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_PAIS_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isDepartamento())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_DEPARTAMENTO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isMunicipio())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_MUNICIPIO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isOficinaOrigen())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PA_OFICINA_ORIGEN_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelCatastral
	 * @return devuelve el valor de String
	 */
	private String camposDatosBasicosCatastral(PanelCatastral ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isCodigoCatastral())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PIC_CODIGO_CATASTRAL_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isCodigoCatastralAnt())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PIC_CODIGO_CATASTRAL_ANT_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelUbicacion
	 * @return devuelve el valor de String
	 */
	private String camposDatosBasicosUbicacion(PanelUbicacion ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isMunicipio())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PU_MUNICIPIO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isVereda())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PU_VEREDA_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isEstadoPredio())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PU_ESTADO_PREDIO_NOMBRE);
				lsb_return.append(ls_coma);
			}

			if(ap_panel.isNupre())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PU_NUPRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelDetalleAntSistemaSolicitud
	 * @return devuelve el valor de String
	 */
	private String camposDetalleAntSistema(PanelDetalleAntSistemaSolicitud ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isAgregar())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PDAS_AGREGAR_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelDireccionPredio
	 * @return devuelve el valor de String
	 */
	private String camposDireccionPredio(PanelDireccionPredio ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isAgregar())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PDP_AGREGAR_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelLinderos
	 * @return devuelve el valor de String
	 */
	private String camposLinderos(PanelLinderos ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isSeleccionado())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PL_LINDEROS_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelMatriculasAbiertas
	 * @return devuelve el valor de String
	 */
	private String camposMatriculasAbiertas(PanelMatriculasAbiertas ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isAgregar())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PMAB_AGREGAR_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ap_panel correspondiente al valor del tipo de objeto PanelMatriculasSegregacion
	 * @return devuelve el valor de String
	 */
	private String camposMatriculasSegregacion(PanelMatriculasSegregacion ap_panel)
	{
		StringBuilder lsb_return;

		lsb_return = new StringBuilder();

		if(ap_panel != null)
		{
			String ls_espacio;
			String ls_coma;

			ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;
			ls_coma        = IdentificadoresCommon.SIMBOLO_COMA_TXT;

			if(ap_panel.isAgregarExistente())
			{
				lsb_return.append(ls_espacio);
				lsb_return.append(CamposCorreccionCommon.PMAB_AGREGAR_NOMBRE);
				lsb_return.append(ls_coma);
			}
		}

		return lsb_return.toString();
	}

	/**
	 * Cargar anotacion proceso.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarAnotacionProceso()
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = getMatriculas();

		if(lrc_data != null)
		{
			if(!StringUtils.isValidString(lrc_data.getTurno()))
				lrc_data.setTurno(getIdTurno());

			setAnotacionesProceso(irr_calificacionRemote.cargarAnotacionProceso(lrc_data));
		}
	}

	/**
	 * Cargar anotaciones englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarAnotacionesEnglobes()
	    throws B2BException
	{
		setMatriculasAHeredar(irr_calificacionRemote.cargarAnotacionesEnglobes(getMatriculas()));
	}

	/**
	 * Cargar datos area englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosAreaEnglobes()
	    throws B2BException
	{
		setAreaUI(null);
		setDetalleAreaTerreno(null);
		setBloquearAgregarAreaTerreno(false);

		try
		{
			AccAreaUI laui_dataArea;

			laui_dataArea = irr_calificacionRemote.cargarDatosAreaEnglobes(getMatriculas());

			if(laui_dataArea != null)
			{
				AccAreaPredio     laap_area;
				DetalleAreaPredio ldap_areaPrivada;
				DetalleAreaPredio ldap_areaConstruida;

				laap_area               = laui_dataArea.getAreaPredio();
				ldap_areaPrivada        = laui_dataArea.getDetalleAreaPrivada();
				ldap_areaConstruida     = laui_dataArea.getDetalleAreaConstruida();

				if(ldap_areaPrivada != null)
				{
					Double ld_area;

					ld_area = ldap_areaPrivada.getArea();

					if(NumericUtils.isValidDouble(ld_area))
						ldap_areaPrivada.setAreaLectura(StringUtils.getString(ld_area));
				}

				if(ldap_areaConstruida != null)
				{
					Double ld_area;

					ld_area = ldap_areaConstruida.getArea();

					if(NumericUtils.isValidDouble(ld_area))
						ldap_areaConstruida.setAreaLectura(StringUtils.getString(ld_area));
				}

				if(laap_area != null)
				{
					Double ld_coeficiente;

					ld_coeficiente = laap_area.getCoeficiente();

					if(NumericUtils.isValidDouble(ld_coeficiente))
						laap_area.setCoeficienteLectura(StringUtils.getString(ld_coeficiente));
				}
			}

			setAreaUI(laui_dataArea);
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
		finally
		{
			actualizarAreaTerreno();
		}
	}

	/**
	 * Cargar datos basicos englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosBasicosEnglobes()
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = irr_calificacionRemote.cargarDatosBasicosEnglobes(getMatriculas());

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

		lrc_data = getMatriculas();

		if(lrc_data != null)
		{
			boolean lb_primerEnglobe;

			lrc_data.setIdTurno(getIdTurno());

			lb_primerEnglobe = irr_calificacionRemote.validarPrimerEnglobeDireccion(lrc_data);

			if(lb_primerEnglobe)
				lrc_data = irr_calificacionRemote.cargarDireccionesEnglobes(lrc_data, true);

			lrc_data.setPrimerEnglobe(lb_primerEnglobe);

			if(lrc_data != null)
			{
				setDirecciones(lrc_data.getDirecciones());
				setMatriculas(lrc_data);
			}
		}
	}

	/**
	 * Cargar linderos complementacion englobes.
	 *
	 * @param ab_calificacion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarLinderosComplementacionEnglobes(boolean ab_calificacion)
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = getMatriculas();

		if(lrc_data != null)
		{
			ComplementacionCalificacion lcc_complementacion;
			String                      ls_idTurno;

			ls_idTurno = lrc_data.getIdTurno();

			if(!StringUtils.isValidString(ls_idTurno))
				ls_idTurno = getIdTurno();

			lrc_data.setTurno(ls_idTurno);
			lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			setLinderoRegistroCalificacion(irr_calificacionRemote.cargarLinderosEnglobes(lrc_data));

			lcc_complementacion = irr_calificacionRemote.cargarComplementacionEnglobes(lrc_data);

			if(lcc_complementacion != null)
			{
				String ls_tipoComplementacion;

				ls_tipoComplementacion = lcc_complementacion.getTipoComplementacion();

				if(!StringUtils.isValidString(ls_tipoComplementacion))
				{
					ls_tipoComplementacion = TipoComplementacionCommon.NUEVA;

					lcc_complementacion.setTipoComplementacion(ls_tipoComplementacion);
				}

				if(
				    StringUtils.isValidString(ls_tipoComplementacion)
					    && ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA)
				)
					setHabilitarComplementacion(true);
			}

			setComplementacionCalificacion(lcc_complementacion);

			if(ab_calificacion)
				setHabilitarComplementacion(true);
			else
				limpiarComplementacionData(false);
		}
	}

	/**
	 * Limpiar areas englobes.
	 */
	private void limpiarAreasEnglobes()
	{
		setBloquearAgregarAreaTerreno(false);
		setDetalleAreaTerreno(null);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param al_numeroMatricula correspondiente al valor del tipo de objeto Long
	 * @param aoms_oms correspondiente al valor del tipo de objeto MatriculaSegregacionUi
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_solicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */

	/* MODIFCAR PREDIAL */
	private String processCellPredial(
	    Long al_numeroMatricula, MatriculaSegregacionUi aoms_oms, String as_userId, String as_solicitud
	)
	    throws B2BException
	{
		StringBuilder                      lsb_msj;
		Collection<MatriculaSegregacionUi> lcms_mstp;
		Long                               ll_matricula;
		String                             ls_circuloRegistral;

		lsb_msj                 = new StringBuilder();
		lcms_mstp               = getDataInfoPredial();
		ll_matricula            = getIdMatricula();
		ls_circuloRegistral     = getIdCirculo();

		if(aoms_oms != null)
		{
			if(!NumericUtils.isValidLong(aoms_oms.getUnidad()))
				lsb_msj.append("El Número Unidad es inválido. ");

			if(!StringUtils.isValidString(aoms_oms.getNombrePredio()))
				lsb_msj.append("El Campo Nombre predio es inválido.");

			if(!NumericUtils.isValidDouble(aoms_oms.getAreaTerreno()))
				lsb_msj.append("El Campo Nombre predio es inválido.");

			if(!NumericUtils.isValidLong(aoms_oms.getCantidadCertificados()))
				lsb_msj.append("La cantidad de certificados es inválida. ");

			if(!StringUtils.isValidString(lsb_msj.toString()))
			{
				if(StringUtils.isValidString(ls_circuloRegistral) && NumericUtils.isValidLong(ll_matricula))
				{
					aoms_oms.setIdCirculoMatriz(ls_circuloRegistral);
					aoms_oms.setMatriculaMatriz(ll_matricula);
					aoms_oms.setCertificadoAsociado(true);
					aoms_oms.setCantidad(NumericUtils.getLongWrapper(1L));
					aoms_oms.setIdSolicitud((as_solicitud));
				}

				if(!CollectionUtils.isValidCollection(lcms_mstp))
					lcms_mstp = new ArrayList<MatriculaSegregacionUi>();

				lcms_mstp.add(aoms_oms);

				setDataInfoPredial(lcms_mstp);

				lsb_msj.append("Predio:" + aoms_oms.getNombrePredio() + ",Insertado correctamente.");
			}
		}

		return lsb_msj.toString();
	}

	/**
	 * Salvar area predio englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarAreaPredioEnglobes()
	    throws B2BException
	{
		try
		{
			if(!isBloquearAgregarAreaTerreno())
			{
				AccAreaUI            laaui_data;
				RegistroCalificacion lrc_data;

				laaui_data     = getAreaUI();
				lrc_data       = getMatriculas();

				if((laaui_data != null) && (lrc_data != null))
				{
					Collection<DetalleAreaPredio> lcadap_areasTerreno;

					lcadap_areasTerreno = laaui_data.getAreasTerreno();

					if(CollectionUtils.isValidCollection(lcadap_areasTerreno))
					{
						AccAreaPredio laap_areaPredio;
						boolean       lb_focus;
						FacesContext  lfc_context;

						laap_areaPredio     = laaui_data.getAreaPredio();
						lb_focus            = false;
						lfc_context         = FacesContext.getCurrentInstance();

						if(laap_areaPredio != null)
						{
							String ls_tipoUso;
							String ls_coeficiente;

							ls_tipoUso         = laap_areaPredio.getTipoSuelo();
							ls_coeficiente     = laap_areaPredio.getCoeficienteLectura();
							lb_focus           = validateStyles(
								    ":fEjecucionCorreccion:TvdetalleRegistroCalif:idSOMtipoUsoSueloTabs", lfc_context,
								    ls_tipoUso, lb_focus
								);

							if(!StringUtils.isValidString(ls_tipoUso))
								throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

							if(StringUtils.isValidString(ls_coeficiente))
							{
								Double ld_coeficiente;

								ld_coeficiente = NumericUtils.getDoubleWrapper(ls_coeficiente);

								if(NumericUtils.isValidDouble(ld_coeficiente, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
								{
									if(ld_coeficiente.compareTo(NumericUtils.getDoubleWrapper(100D)) > 0)
									{
										lb_focus = validateStyles(
											    ":fEjecucionCorreccion:TvdetalleRegistroCalif:idITcoeficienteTabs",
											    lfc_context, "", lb_focus
											);

										throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
									}
									else
									{
										laap_areaPredio.setCoeficiente(ld_coeficiente);
										lb_focus = validateStyles(
											    ":fEjecucionCorreccion:TvdetalleRegistroCalif:idITcoeficienteTabs",
											    lfc_context, ls_coeficiente, lb_focus
											);
									}
								}
								else
								{
									lb_focus = validateStyles(
										    ":fEjecucionCorreccion:TvdetalleRegistroCalif:idITcoeficienteTabs",
										    lfc_context, "", lb_focus
										);
									throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
								}
							}
						}

						{
							Collection<RegistroCalificacion> lcrc_matriculas;
							lcrc_matriculas = lrc_data.getAllMatriculas();

							if(
							    CollectionUtils.isValidCollection(lcrc_matriculas)
								    && CollectionUtils.isValidCollection(lcadap_areasTerreno)
							)
							{
								Iterator<DetalleAreaPredio> ldap_iterador;

								ldap_iterador = lcadap_areasTerreno.iterator();

								if(ldap_iterador.hasNext())
								{
									DetalleAreaPredio ldap_actual;
									Double            ls_area;
									String            ls_unidadMedida;

									ldap_actual         = ldap_iterador.next();
									ls_area             = ldap_actual.getArea();
									ls_unidadMedida     = ldap_actual.getIdUnidadMedida();

									if(
									    NumericUtils.isValidDouble(ls_area)
										    && UnidadMedidaAreaCommon.METROS_CUADRADOS.equals(ls_unidadMedida)
									)
									{
										Iterator<RegistroCalificacion> ldap_iter;
										Collection<String>             lcs_matriculas;

										ldap_iter          = lcrc_matriculas.iterator();
										lcs_matriculas     = new ArrayList<String>();

										while(ldap_iter.hasNext())
										{
											RegistroCalificacion lrc_actual;

											lrc_actual = ldap_iter.next();

											if(lrc_actual != null)
											{
												String ls_matricula;

												ls_matricula = lrc_actual.getIdCirculo();

												if(StringUtils.isValidString(ls_matricula))
													lcs_matriculas.add(ls_matricula);
											}
										}

										if(CollectionUtils.isValidCollection(lcs_matriculas))
										{
											if(!irr_registroRemote.validarSumaAreas(lcs_matriculas, ls_area, true))
												addMessage(MessagesKeys.AREAS_NO_EQUIVALENTES);
										}
									}
								}
							}
						}

						{
							DetalleAreaPredio ldap_area;

							ldap_area = laaui_data.getDetalleAreaConstruida();

							if(ldap_area != null)
							{
								String ls_area;

								ls_area = ldap_area.getAreaLectura();

								if(StringUtils.isValidString(ls_area))
								{
									Double ld_area;

									ld_area = NumericUtils.getDoubleWrapper(ls_area);

									if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
									{
										lb_focus = validateStyles(
											    ":fEjecucionCorreccion:TvdetalleRegistroCalif:idITareaConstruidaTabs",
											    lfc_context, ls_area, lb_focus
											);
										ldap_area.setArea(ld_area);
									}
									else
									{
										lb_focus = validateStyles(
											    ":fEjecucionCorreccion:TvdetalleRegistroCalif:idITareaConstruidaTabs",
											    lfc_context, "", lb_focus
											);
										throw new B2BException(ErrorKeys.ERROR_AREA_CONSTRUIDA);
									}
								}
							}
						}

						{
							DetalleAreaPredio ldap_area;

							ldap_area = laaui_data.getDetalleAreaPrivada();

							if(ldap_area != null)
							{
								String ls_area;

								ls_area = ldap_area.getAreaLectura();

								if(StringUtils.isValidString(ls_area))
								{
									Double ld_area;

									ld_area = NumericUtils.getDoubleWrapper(ls_area);

									if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
									{
										lb_focus = validateStyles(
											    ":fEjecucionCorreccion:TvdetalleRegistroCalif:idITareaPrivadaTabs",
											    lfc_context, ls_area, lb_focus
											);
										ldap_area.setArea(ld_area);
									}
									else
									{
										lb_focus = validateStyles(
											    ":fEjecucionCorreccion:TvdetalleRegistroCalif:idITareaPrivadaTabs",
											    lfc_context, "", lb_focus
											);
										throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
									}
								}
							}
						}

						{
							String ls_idCirculo;
							Long   ll_idMatricula;

							ls_idCirculo       = lrc_data.getIdCirculo();
							ll_idMatricula     = lrc_data.getIdMatricula();

							if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
							{
								laaui_data.setIdCirculo(ls_idCirculo);
								laaui_data.setIdMatricula(ll_idMatricula);

								laaui_data.setIdTurno(lrc_data.getTurno());
								laaui_data.setIdTurnoHistoria(lrc_data.getIdTurnoHistoria());

								irr_calificacionRemote.salvarAreaPredioEnglobes(
								    laaui_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_MODIFICAR);
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Salvar datos basicos englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarDatosBasicosEnglobes()
	    throws B2BException
	{
		try
		{
			RegistroCalificacion lrc_data;

			lrc_data = getMatriculas();

			if(lrc_data != null)
			{
				Collection<RegistroCalificacion> lcrc_matriculas;

				lcrc_matriculas = lrc_data.getAllMatriculas();

				if(CollectionUtils.isValidCollection(lcrc_matriculas))
				{
					boolean                        lb_matriculaSeleccionada;
					Iterator<RegistroCalificacion> lirc_iterator;

					lb_matriculaSeleccionada     = false;
					lirc_iterator                = lcrc_matriculas.iterator();

					while(lirc_iterator.hasNext())
					{
						RegistroCalificacion lrc_iterador;

						lrc_iterador = lirc_iterator.next();

						if(lrc_iterador != null)
						{
							if(lrc_iterador.isMatriculaSeleccionada())
								lb_matriculaSeleccionada = true;
						}
					}

					if(lb_matriculaSeleccionada)
					{
						lrc_data = irr_calificacionRemote.salvarDatosBasicosEnglobes(
							    lrc_data, getUserId(), getRemoteIpAddress()
							);

						setMatriculas(lrc_data);

						if(lrc_data.isMatriculaSeleccionada())
						{
							PrimeFaces.current().ajax().update("idMatriculaExitosaTabs");
							PrimeFaces.current().ajax().update("fMatriculaCreadaTabs");
							PrimeFaces.current().executeScript("PF('idMatriculaExitosaTabs').show();");
						}
						else
							lrc_data.setMatriculaSeleccionada(true);

						PrimeFaces.current().ajax()
							          .update("fEjecucionCorreccion:TvdetalleRegistroCalif:idOPFormsEnbglobes");
						PrimeFaces.current().ajax()
							          .update("fEjecucionCorreccion:TvdetalleRegistroCalif:idOLMatriculaTemporal");
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_SELECCIONAR_MATRICULA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Salvar linderos complementacion englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarLinderosComplementacionEnglobes()
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
				String ls_tipoComplementacion;
				String ls_lindero;

				ls_tipoComplementacion     = lcc_dataComplementacion.getTipoComplementacion();
				ls_lindero                 = llrc_dataLindero.getLindero();

				if(!StringUtils.isValidString(ls_lindero))
					throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);

				if(StringUtils.isValidString(ls_tipoComplementacion))
				{
					ComplementacionPredio lcp_complementacion;

					lcp_complementacion = lcc_dataComplementacion.getComplementacionPredio();

					if(lcp_complementacion != null)
					{
						String ls_complementacion;

						ls_complementacion = lcp_complementacion.getComplementacion();

						if(StringUtils.isValidString(ls_complementacion))
						{
							String ls_idTurno;

							ls_idTurno = lrc_data.getIdTurno();

							if(!StringUtils.isValidString(ls_idTurno))
								ls_idTurno = getIdTurno();

							lrc_data.setTurno(ls_idTurno);
							lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

							irr_calificacionRemote.salvarLinderosComplementacionEnglobes(
							    lcc_dataComplementacion, llrc_dataLindero, lrc_data, getUserId(), getLocalIpAddress(),
							    getRemoteIpAddress()
							);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_COMPLEMENTACION);
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
}
