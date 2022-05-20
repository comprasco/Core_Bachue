package com.bachue.snr.prosnr01.business;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoAcceso;
import co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaCanal;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaClasificacion;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoVariable;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.file.excel.ExcelUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.mail.SendMail;

import com.b2bsg.common.messages.Messages;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.ByteArrayUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.bioclient.padFirmas.ClientePadFirmas;
import com.bachue.prosnr01.integracion.cliente.correspondencia.generacionIDsCorrespondencia.ClienteGeneracionIDsCorrespondencia;
import com.bachue.prosnr01.integracion.cliente.owcc.ParametrosDocumento;
import com.bachue.prosnr01.integracion.cliente.owcc.consultarDocumentos.ClienteConsultarDocumentos;
import com.bachue.prosnr01.integracion.cliente.owcc.consultarDocumentos.ClienteObtenerArchivo;

import com.bachue.prosnr21.integracionConciliaciones.cliente.consultaCatalogos.ConsumoCatalogo;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.business.actuaciones.administrativas.ActuacionesAdministrativasBusiness;
import com.bachue.snr.prosnr01.business.antiguo.sistema.AntiguoSistemaBusiness;
import com.bachue.snr.prosnr01.business.aprobacion.AprobacionBusiness;
import com.bachue.snr.prosnr01.business.calificacion.CalificacionBusiness;
import com.bachue.snr.prosnr01.business.calificacion.RegistroCalificacionBusiness;
import com.bachue.snr.prosnr01.business.calificacion.SuspensionBusiness;
import com.bachue.snr.prosnr01.business.certificados.CertificadosBusiness;
import com.bachue.snr.prosnr01.business.consulta.trazabilidad.ConsultaTrazabilidadBusiness;
import com.bachue.snr.prosnr01.business.devolucionesDinero.DevolucionDineroBusiness;
import com.bachue.snr.prosnr01.business.digitalizacion.DigitalizacionBusiness;
import com.bachue.snr.prosnr01.business.firma.FirmaMasivaBusiness;
import com.bachue.snr.prosnr01.business.grabacion.GrabacionBusiness;
import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;
import com.bachue.snr.prosnr01.business.liquidacion.LiquidacionBusiness;
import com.bachue.snr.prosnr01.business.parameter.ParameterBusiness;
import com.bachue.snr.prosnr01.business.reference.ReferenceBusiness;
import com.bachue.snr.prosnr01.business.registro.ConstanciaReproduccionBusiness;
import com.bachue.snr.prosnr01.business.registro.CriteriosConsultaBusiness;
import com.bachue.snr.prosnr01.business.registro.RegistroBusiness;
import com.bachue.snr.prosnr01.business.segundaInstancia.SegundaInstanciaBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.BookMarkCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.LineaProduccionCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;
import com.bachue.snr.prosnr01.common.utils.FTPUtils;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccPredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraBloqueoDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DireccionPredioAccDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudAsociadaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.aut.RolDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioRolDAO;
import com.bachue.snr.prosnr01.dao.bng.DireccionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.bng.PropietarioDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoDocumentoTipoDAO;
import com.bachue.snr.prosnr01.dao.col.ParteInteresadaDAO;
import com.bachue.snr.prosnr01.dao.col.TipoRrrDAO;
import com.bachue.snr.prosnr01.dao.copias.SolicitudCopiasDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoActAdminDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentalDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoPersonaDAO;
import com.bachue.snr.prosnr01.dao.png.CoordenadaDAO;
import com.bachue.snr.prosnr01.dao.png.LetraEjeDAO;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.TipoEjeDAO;

import com.bachue.snr.prosnr01.model.CodigoError;
import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.copias.SolicitudCopias;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.integration.correspondencia.Correspondencia;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraProceso;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.FirmaMasiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaEntrega;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroMayorValor;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.Propietario;
import com.bachue.snr.prosnr01.model.sdb.col.DominioRrr;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.ParteInteresada;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoActAdmin;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOperacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;
import com.bachue.snr.prosnr01.model.sdb.png.DominioNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import com.bachue.snr.prosnr09.business.gestion.alertas.titulares.GestionAlertasTitularesBusiness;

import com.bachue.snr.prosnr10.common.constants.TipoIdentificacionPredioCommon;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;
import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import com.bachue.snr.prosnr16.common.constants.TipoParametroCommon;

import com.bachue.snr.prosnr16.model.sdb.acc.MensajeAdjunto;
import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;

import com.bachue.snr.prosnr21.common.constants.InconsistenciasCommon;
import com.bachue.snr.prosnr21.common.constants.PlantillaMensajeCommon;

import com.bachue.snr.prosnr21.model.pgn.ConArchivo;
import com.bachue.snr.prosnr21.model.pgn.ConInconsistencia;
import com.bachue.snr.prosnr21.model.pgn.ConPlantillaMensaje;
import com.bachue.snr.prosnr21.model.pgn.CuentaAnalista;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.IncAviAleMenNot;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import com.itextpdf.text.pdf.PdfReader;

import com.jcraft.jsch.ChannelSftp;

import org.apache.commons.lang3.math.NumberUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import java.util.Map.Entry;

import java.util.Set;

import javax.imageio.ImageIO;

import javax.mail.MessagingException;

import javax.mail.internet.AddressException;


/**
 * Clase que contiene todos la fucionalidades para la base de todos los business.
 *
 * @author hcastaneda
 */
public class BaseBusiness
{
	/** Constante CONFIG_MESSAGES. */
	public static final String CONFIG_MESSAGES = "com.bachue.snr.prosnr01.web.bean.resources.Messages";

	/** Constante CONFIG_MESSAGES_22. */
	public static final String CONFIG_MESSAGES_22 = "com.bachue.snr.prosnr22.web.bean.resources.Messages";

	/** Constante CONFIG_ERROR_MESSAGES. */
	public static final String CONFIG_ERROR_MESSAGES = "com.bachue.snr.prosnr01.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_EP. */
	public static final String CONFIG_ERROR_MESSAGES_EP = "com.bachue.snr.prosnr08.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_CD. */
	public static final String CONFIG_ERROR_MESSAGES_CD = "com.bachue.snr.prosnr11.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_CHSP. */
	public static final String CONFIG_ERROR_MESSAGES_CHSP = "com.bachue.snr.prosnr07.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_CDR. */
	public static final String CONFIG_ERROR_MESSAGES_CDR = "com.bachue.snr.prosnr15.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_AT. */
	public static final String CONFIG_ERROR_MESSAGES_AT = "com.bachue.snr.prosnr19.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_SC. */
	public static final String CONFIG_ERROR_MESSAGES_SC = "com.bachue.snr.prosnr17.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_SDC. */
	public static final String CONFIG_ERROR_MESSAGES_SDC = "com.bachue.snr.prosnr18.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_CT. */
	public static final String CONFIG_ERROR_MESSAGES_CT = "com.bachue.snr.prosnr10.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_CX. */
	public static final String CONFIG_ERROR_MESSAGES_CX = "com.bachue.snr.prosnr14.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_GCC. */
	public static final String CONFIG_ERROR_MESSAGES_GCC = "com.bachue.snr.prosnr12.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_NP. */
	public static final String CONFIG_ERROR_MESSAGES_NP = "com.bachue.snr.prosnr04.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_CYN. */
	public static final String CONFIG_ERROR_MESSAGES_CYN = "com.bachue.snr.prosnr16.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_N. */
	public static final String CONFIG_ERROR_MESSAGES_N = "com.bachue.snr.prosnr20.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_CMF. */
	public static final String CONFIG_ERROR_MESSAGES_CMF = "com.bachue.snr.prosnr21.web.bean.resources.Errors";

	/** Constante CONFIG_ERROR_MESSAGES_NDC. */
	public static final String CONFIG_ERROR_MESSAGES_NDC = "com.bachue.snr.prosnr22.web.bean.resources.Errors";

	/** Constante CONFIG_ERRORS_CA.*/
	public static final String CONFIG_ERROR_MESSAGES_CA = "com.bachue.snr.prosnr03.web.bean.resources.Errors";

	/** Constante CONFIG_ERRORS_GS.*/
	public static final String CONFIG_ERROR_MESSAGES_GS = "com.bachue.snr.prosnr13.web.bean.resources.Errors";

	/** Constante cs_IOEXCEPTION. */
	protected static final String cs_IOEXCEPTION = "IOException";

	/** Constante cs_BAD_ELEMENT_EXCEPTION. */
	protected static final String cs_BAD_ELEMENT_EXCEPTION = "BadElementException";

	/** Constante cs_DOCUMENT_EXCEPTION. */
	protected static final String cs_DOCUMENT_EXCEPTION = "DocumentException";

	/** Constante cs_SQL_ERROR. */
	protected static final String cs_SQL_ERROR = "errorSql";

	/** Propiedad im messages. */
	private static Messages im_messages;

	/** Propiedad im messages 22. */
	private static Messages im_messages22;

	/** Propiedad im error messages. */
	private static Messages im_errorMessages;

	/** Propiedad im error messages EP. */
	private static Messages im_errorMessagesEP;

	/** Propiedad im error messages CA. */
	private static Messages im_errorMessagesCA;

	/** Propiedad im error messages GS. */
	private static Messages im_errorMessagesGS;

	/** Propiedad im error messages CD. */
	private static Messages im_errorMessagesCD;

	/** Propiedad im error messages CHSP. */
	private static Messages im_errorMessagesCHSP;

	/** Propiedad im error messages CDR. */
	private static Messages im_errorMessagesCDR;

	/** Propiedad im error messages AT. */
	private static Messages im_errorMessagesAT;

	/** Propiedad im error messages SC. */
	private static Messages im_errorMessagesSC;

	/** Propiedad im error messages SDC. */
	private static Messages im_errorMessagesSDC;

	/** Propiedad im error messages CT. */
	private static Messages im_errorMessagesCT;

	/** Propiedad im error messages CX. */
	private static Messages im_errorMessagesCX;

	/** Propiedad im error messages GCC. */
	private static Messages im_errorMessagesGCC;

	/** Propiedad im error messages NP. */
	private static Messages im_errorMessagesNP;

	/** Propiedad im error messages CYN. */
	private static Messages im_errorMessagesCYN;

	/** Propiedad im error messages N. */
	private static Messages im_errorMessagesN;

	/** Propiedad im error messages NDC. */
	private static Messages im_errorMessagesNDC;

	/** Propiedad im error messages CMF. */
	private static Messages im_errorMessagesCMF;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BaseBusiness.class);

	/** Propiedad iaab actuaciones administrativas. */
	private ActuacionesAdministrativasBusiness iaab_actuacionesAdministrativas;

	/** Propiedad iasb antiguoSistema. */
	private AntiguoSistemaBusiness iasb_antiguoSistema;

	/** Propiedad iab aprobacion. */
	private AprobacionBusiness iab_aprobacion;

	/** Propiedad icb calificacion. */
	private CalificacionBusiness icb_calificacion;

	/** Propiedad icb certificados. */
	private CertificadosBusiness icb_certificados;

	/** Propiedad icrb constancia reproduccion. */
	private ConstanciaReproduccionBusiness icrb_constanciaReproduccion;

	/** Propiedad ictb consulta trazabilidad. */
	private ConsultaTrazabilidadBusiness ictb_consultaTrazabilidad;

	/** Propiedad iccb criterios consulta. */
	private CriteriosConsultaBusiness iccb_criteriosConsulta;

	/** Propiedad idd devolucionDinero. */
	private DevolucionDineroBusiness idd_devolucionDinero;

	/** Propiedad idb digitalizacion. */
	private DigitalizacionBusiness idb_digitalizacion;

	/** Propiedad iegdb envio gestor documental. */
	private EnvioGestorDocumentalBusiness iegdb_envioGestorDocumental;

	/** Propiedad ifmb firma masiva. */
	private FirmaMasivaBusiness ifmb_firmaMasiva;

	/** Propiedad igatb gestion alertas bussines. */
	private GestionAlertasTitularesBusiness igatb_gestionAlertasBussines;

	/** Propiedad igb grabacion. */
	private GrabacionBusiness igb_grabacion;

	/** Propiedad ili liquidacion. */
	private LiquidacionBusiness ili_liquidacion;

	/** Propiedad ipb parameter. */
	private ParameterBusiness ipb_parameter;

	/** Propiedad irb reference. */
	private ReferenceBusiness irb_reference;

	/** Propiedad irb registro. */
	private RegistroBusiness irb_registro;

	/** Propiedad ircb registro calificacion. */
	private RegistroCalificacionBusiness ircb_registroCalificacion;

	/** Propiedad iaab actuaciones administrativas. */
	private SegundaInstanciaBusiness isib_segundaInstanciaBusiness;

	/** Propiedad isb suspension. */
	private SuspensionBusiness isb_suspension;

	/** Propiedad ci HEIGH T IMAGE. */
	private final int ci_HEIGHT_IMAGE = 85;

	/** Propiedad ci WIDT H IMAGE. */
	private final int ci_WIDTH_IMAGE = 223;

	/**
	 * Retorna el valor de error messages.
	 *
	 * @return el valor de error messages
	 */
	public static Messages getErrorMessages()
	{
		if(im_errorMessages == null)
			im_errorMessages = new Messages(CONFIG_ERROR_MESSAGES);

		return im_errorMessages;
	}

	/**
	 * Retorna Objeto o variable de valor error messages AT.
	 *
	 * @return el valor de error messages AT
	 */
	public static Messages getErrorMessagesAT()
	{
		if(im_errorMessagesAT == null)
			im_errorMessagesAT = new Messages(CONFIG_ERROR_MESSAGES_AT);

		return im_errorMessagesAT;
	}

	/**
	 * Retorna Objeto o variable de valor error messages CA.
	 *
	 * @return el valor de error messages CA
	 */
	public static Messages getErrorMessagesCA()
	{
		if(im_errorMessagesCA == null)
			im_errorMessagesCA = new Messages(CONFIG_ERROR_MESSAGES_CA);

		return im_errorMessagesCA;
	}

	/**
	 * Retorna el valor de error messages CD.
	 *
	 * @return el valor de error messages CD
	 */
	public static Messages getErrorMessagesCD()
	{
		if(im_errorMessagesCD == null)
			im_errorMessagesCD = new Messages(CONFIG_ERROR_MESSAGES_CD);

		return im_errorMessagesCD;
	}

	/**
	 * Retorna el valor de error messages CDR.
	 *
	 * @return el valor de error messages CDR
	 */
	public static Messages getErrorMessagesCDR()
	{
		if(im_errorMessagesCDR == null)
			im_errorMessagesCDR = new Messages(CONFIG_ERROR_MESSAGES_CDR);

		return im_errorMessagesCDR;
	}

	/**
	 * Retorna el valor de error messages CHSP.
	 *
	 * @return el valor de error messages CHSP
	 */
	public static Messages getErrorMessagesCHSP()
	{
		if(im_errorMessagesCHSP == null)
			im_errorMessagesCHSP = new Messages(CONFIG_ERROR_MESSAGES_CHSP);

		return im_errorMessagesCHSP;
	}

	/**
	 * Retorna Objeto o variable de valor error messages C.
	 *
	 * @return el valor de error messages C
	 */
	public static Messages getErrorMessagesCMF()
	{
		if(im_errorMessagesCMF == null)
			im_errorMessagesCMF = new Messages(CONFIG_ERROR_MESSAGES_CMF);

		return im_errorMessagesCMF;
	}

	/**
	 * Retorna Objeto o variable de valor error messages CT.
	 *
	 * @return el valor de error messages CT
	 */
	public static Messages getErrorMessagesCT()
	{
		if(im_errorMessagesCT == null)
			im_errorMessagesCT = new Messages(CONFIG_ERROR_MESSAGES_CT);

		return im_errorMessagesCT;
	}

	/**
	 * Retorna Objeto o variable de valor error messages CX.
	 *
	 * @return el valor de error messages CX
	 */
	public static Messages getErrorMessagesCX()
	{
		if(im_errorMessagesCX == null)
			im_errorMessagesCX = new Messages(CONFIG_ERROR_MESSAGES_CX);

		return im_errorMessagesCX;
	}

	/**
	 * Retorna Objeto o variable de valor error messages CYN.
	 *
	 * @return el valor de error messages CYN
	 */
	public static Messages getErrorMessagesCYN()
	{
		if(im_errorMessagesCYN == null)
			im_errorMessagesCYN = new Messages(CONFIG_ERROR_MESSAGES_CYN);

		return im_errorMessagesCYN;
	}

	/**
	 * Retorna el valor de error messages EP.
	 *
	 * @return el valor de error messages EP
	 */
	public static Messages getErrorMessagesEP()
	{
		if(im_errorMessagesEP == null)
			im_errorMessagesEP = new Messages(CONFIG_ERROR_MESSAGES_EP);

		return im_errorMessagesEP;
	}

	/**
	 * Retorna el valor de error messages GCC.
	 *
	 * @return el valor de error messages GCC
	 */
	public static Messages getErrorMessagesGCC()
	{
		if(im_errorMessagesGCC == null)
			im_errorMessagesGCC = new Messages(CONFIG_ERROR_MESSAGES_GCC);

		return im_errorMessagesGCC;
	}

	/**
	 * Retorna Objeto o variable de valor error messages GS.
	 *
	 * @return el valor de error messages GS
	 */
	public static Messages getErrorMessagesGS()
	{
		if(im_errorMessagesGS == null)
			im_errorMessagesGS = new Messages(CONFIG_ERROR_MESSAGES_GS);

		return im_errorMessagesGS;
	}

	/**
	 * Retorna Objeto o variable de valor error messages N.
	 *
	 * @return el valor de error messages N
	 */
	public static Messages getErrorMessagesN()
	{
		if(im_errorMessagesN == null)
			im_errorMessagesN = new Messages(CONFIG_ERROR_MESSAGES_N);

		return im_errorMessagesN;
	}

	/**
	 * Retorna Objeto o variable de valor error messages N.
	 *
	 * @return el valor de error messages N
	 */
	public static Messages getErrorMessagesNDC()
	{
		if(im_errorMessagesNDC == null)
			im_errorMessagesNDC = new Messages(CONFIG_ERROR_MESSAGES_NDC);

		return im_errorMessagesNDC;
	}

	/**
	 * Retorna el valor de error messages NP.
	 *
	 * @return el valor de error messages NP
	 */
	public static Messages getErrorMessagesNP()
	{
		if(im_errorMessagesNP == null)
			im_errorMessagesNP = new Messages(CONFIG_ERROR_MESSAGES_NP);

		return im_errorMessagesNP;
	}

	/**
	 * Retorna el valor de error messages SC.
	 *
	 * @return el valor de error messages SC
	 */
	public static Messages getErrorMessagesSC()
	{
		if(im_errorMessagesSC == null)
			im_errorMessagesSC = new Messages(CONFIG_ERROR_MESSAGES_SC);

		return im_errorMessagesSC;
	}

	/**
	 * Retorna el valor de error messages SDC.
	 *
	 * @return el valor de error messages SDC
	 */
	public static Messages getErrorMessagesSDC()
	{
		if(im_errorMessagesSDC == null)
			im_errorMessagesSDC = new Messages(CONFIG_ERROR_MESSAGES_SDC);

		return im_errorMessagesSDC;
	}

	/**
	 * Obtiene el valor de gestion alertas bussines.
	 *
	 * @return el valor de gestion alertas titulares business
	 */
	public GestionAlertasTitularesBusiness getGestionAlertasBussines()
	{
		if(igatb_gestionAlertasBussines == null)
			igatb_gestionAlertasBussines = new GestionAlertasTitularesBusiness();

		return igatb_gestionAlertasBussines;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @param as_matricula correspondiente al valor del tipo de objeto String
	 * @return el valor de id circulo
	 */
	public static String getIdCirculo(String as_matricula)
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_matricula))
		{
			String[] ls_datos;

			ls_datos = StringUtils.getStringArray(as_matricula, IdentificadoresCommon.SIMBOLO_GUION);

			if(CollectionUtils.isValidCollection(ls_datos))
				ls_return = ls_datos[0];
		}

		return ls_return;
	}

	/**
	 * Retorna el valor de id matricula.
	 *
	 * @param as_matricula correspondiente al valor del tipo de objeto String
	 * @return el valor de id matricula
	 */
	public static Long getIdMatricula(String as_matricula)
	{
		Long ll_return;

		ll_return = null;

		if(StringUtils.isValidString(as_matricula))
		{
			String[] ls_datos;

			ls_datos = StringUtils.getStringArray(as_matricula, IdentificadoresCommon.SIMBOLO_GUION);

			if(CollectionUtils.isValidCollection(ls_datos))
				ll_return = NumericUtils.getLongWrapper(ls_datos[1]);
		}

		return ll_return;
	}

	/**
	 * Retorna el valor de messages.
	 *
	 * @return el valor de messages
	 */
	public static Messages getMessages()
	{
		if(im_messages == null)
			im_messages = new Messages(CONFIG_MESSAGES);

		return im_messages;
	}

	/**
	 * Retorna el valor de messages.
	 *
	 * @return el valor de messages
	 */
	public static Messages getMessagesNDC()
	{
		if(im_messages22 == null)
			im_messages22 = new Messages(CONFIG_MESSAGES_22);

		return im_messages22;
	}

	/**
	 * Gets the lines from byte array.
	 *
	 * @param aba_archivo the lba archivo
	 * @return the lines from byte array
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public synchronized Collection<String> getLinesFromByteArray(byte[] aba_archivo)
	    throws IOException
	{
		Collection<String> lcs_lineas;

		lcs_lineas = new ArrayList<String>();

		if(aba_archivo != null)
		{
			BufferedReader    lbr_archivo;
			InputStream       lis_archivo;
			InputStreamReader lisr_archivo;
			String            ls_linea;
			String            ls_charset;

			{
				CharsetDetector lcsd_detector;
				CharsetMatch    lcm_detector;

				lcsd_detector = new CharsetDetector();

				lcsd_detector.setText(aba_archivo);

				lcm_detector     = lcsd_detector.detect();
				ls_charset       = (lcm_detector != null) ? lcm_detector.getName() : null;
			}

			lis_archivo      = new ByteArrayInputStream(aba_archivo);
			lisr_archivo     = new InputStreamReader(lis_archivo, ls_charset);
			lbr_archivo      = new BufferedReader(lisr_archivo);

			while((ls_linea = lbr_archivo.readLine()) != null)
			{
				if(!ls_linea.trim().isEmpty())
					lcs_lineas.add(ls_linea);
			}

			lbr_archivo.close();
			lisr_archivo.close();
			lis_archivo.close();
		}

		if(lcs_lineas.isEmpty())
			lcs_lineas = null;

		return lcs_lineas;
	}

	/**
	 * Retorna Objeto o variable de valor TXT from string collection.
	 *
	 * @param acs_data de acs data
	 * @return el valor de TXT from string collection
	 */
	public byte[] getTXTFromStringCollection(Collection<String> acs_data)
	{
		StringBuilder lsb_csv;

		lsb_csv = new StringBuilder();

		for(String ls_data : acs_data)
		{
			lsb_csv.append(ls_data);
			lsb_csv.append(IdentificadoresCommon.SALTO_LINEA);
		}

		return lsb_csv.toString().getBytes();
	}

	/**
	 * Retorna Objeto o variable de valor XSL from string collection.
	 *
	 * @param acs_data de acs data
	 * @return el valor de XSL from string collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public byte[] getXSLFromStringCollection(Collection<String> acs_data)
	    throws B2BException, IOException
	{
		byte[] lba_result;

		lba_result = null;

		if(CollectionUtils.isValidCollection(acs_data))
		{
			int       li_registros;
			int       li_rowCounter;
			CellStyle lcs_headerStyle;
			Row       lr_header;
			Sheet     ls_sheet;
			Workbook  lw_book;

			li_rowCounter       = 1;
			li_registros        = 0;
			lw_book             = new XSSFWorkbook();
			ls_sheet            = lw_book.createSheet();
			lcs_headerStyle     = lw_book.createCellStyle();
			lr_header           = ls_sheet.createRow(0);

			lcs_headerStyle.setFillBackgroundColor(CellStyle.SOLID_FOREGROUND);

			for(String ls_data : acs_data)
			{
				if(StringUtils.isValidString(ls_data))
				{
					if(li_registros == 0)
					{
						int      li_cell;
						String[] lsa_cabeceras;

						li_cell           = 0;
						lsa_cabeceras     = ls_data.split(IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);

						for(String ls_cabecera : lsa_cabeceras)
						{
							Cell lc_cell;

							lc_cell = lr_header.createCell(li_cell);

							lc_cell.setCellValue(ls_cabecera);
							lc_cell.setCellStyle(lcs_headerStyle);

							li_cell++;
						}
					}
					else
					{
						int      li_columCounter;
						Row      lr_row;
						String[] lsa_data;

						li_columCounter     = 0;
						lr_row              = ls_sheet.createRow(li_rowCounter);
						lsa_data            = ls_data.split(IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);

						for(String ls_dato : lsa_data)
						{
							if(!StringUtils.isValidString(ls_dato))
								ls_dato = "";

							try
							{
								ExcelUtils.creaCelda(lr_row, li_columCounter, ls_dato, null);

								li_columCounter++;
							}
							catch(IllegalArgumentException lite_e)
							{
								clh_LOGGER.error("getXSLFromCollection", lite_e);
							}
						}

						li_rowCounter++;
					}

					li_registros++;
				}
			}

			{
				ByteArrayOutputStream lbaos_output;

				lbaos_output = new ByteArrayOutputStream();

				ExcelUtils.write(lw_book, lbaos_output);

				lba_result = lbaos_output.toByteArray();
			}
		}

		return lba_result;
	}

	/**
	 * Abrir una conexión a un servidor FTP extrallendo los datos de ip, nombre y clave a partir de constantes en la BD.
	 *
	 * @param acd_constante DAO de constantes embebido en transacción
	 * @param aci_param de aci param
	 * @param as_ftpClave the as ftp clave
	 * @param as_ftpUsuario the as ftp usuario
	 * @param as_ftpIp the as ftp ip
	 * @param as_ftpPuerto the as ftp puerto
	 * @return Conexión a servidor FTP
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public synchronized ChannelSftp abrirConexionFTP(
	    ConstantesDAO acd_constante, ConInconsistencia aci_param, String as_ftpClave, String as_ftpUsuario,
	    String as_ftpIp, String as_ftpPuerto
	)
	    throws B2BException
	{
		ChannelSftp lcs_canal;

		lcs_canal = null;

		try
		{
			String ls_clave;
			String ls_servidor;
			String ls_usuario;
			String ls_puerto;

			ls_clave        = obtenerConstanteCaracter(acd_constante, as_ftpClave);
			ls_usuario      = obtenerConstanteCaracter(acd_constante, as_ftpUsuario);
			ls_servidor     = obtenerConstanteCaracter(acd_constante, as_ftpIp);
			ls_puerto       = obtenerConstanteCaracter(acd_constante, as_ftpPuerto, false);
			lcs_canal       = FTPUtils.abrirConexionFTP(ls_servidor, ls_usuario, ls_clave, ls_puerto);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("abrirConexionFTP", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if((lcs_canal == null) && (aci_param != null))
				generarInconsistencia(aci_param, InconsistenciasCommon.INCONSISTENCIA_1);
		}

		return lcs_canal;
	}

	/**
	 * Abrir una conexión a un servidor FTP extrallendo los datos de ip, nombre y clave a partir de constantes en la BD.
	 *
	 * @param acd_constante DAO de constantes embebido en transacción
	 * @param aci_param de aci param
	 * @param as_ftpClave the as ftp clave
	 * @param as_ftpUsuario the as ftp usuario
	 * @param as_ftpIp the as ftp ip
	 * @return Conexión a servidor FTP
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ChannelSftp abrirConexionFTP(
	    ConstantesDAO acd_constante, ConInconsistencia aci_param, String as_ftpClave, String as_ftpUsuario,
	    String as_ftpIp
	)
	    throws B2BException
	{
		ChannelSftp lcs_canal;

		lcs_canal = null;

		try
		{
			lcs_canal = abrirConexionFTP(acd_constante, aci_param, as_ftpClave, as_ftpUsuario, as_ftpIp, null);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("abrirConexionFTP", lb2be_e);

			throw lb2be_e;
		}

		return lcs_canal;
	}

	/**
	 * Metodo encargado de consultar los tipos documentales de un apoderado o tercero.
	 * @param ldm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param as_solicitud Argumento de tipo <code>Solicitud</code> que contiene la calidad solicitante para saber si es apoderado o tercero.
	 * @param actd_tiposDocumentales Argumento de tipo <code>Collection</code> que contiene los tipos documentales cargados por pantalla.
	 * @param as_constanteApoderado Argumento de tipo <code>String</code> que contiene la constante a usar para el apoderado.
	 * @param as_constanteTercero Argumento de tipo <code>String</code> que contiene la constante a usar para el tercero.
	 * @return <code>Collection</code> que contiene los tipos documentales cargados por constante.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoDocumental> actualizarTiposDocumentales(
	    DAOManager ldm_manager, Solicitud as_solicitud, Collection<TipoDocumental> actd_tiposDocumentales,
	    String as_constanteApoderado, String as_constanteTercero
	)
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_tiposRetorno;

		lctd_tiposRetorno = null;

		if(as_solicitud != null)
		{
			try
			{
				String ls_idCalidadSolicitante;
				String ls_idConstante;

				ls_idCalidadSolicitante     = as_solicitud.getIdCalidadSolicitante();
				ls_idConstante              = null;

				if(StringUtils.isValidString(ls_idCalidadSolicitante))
				{
					lctd_tiposRetorno = new ArrayList<TipoDocumental>();

					switch(ls_idCalidadSolicitante)
					{
						case CalidadSolicitanteCommon.APODERADO:
							ls_idConstante = as_constanteApoderado;

							break;

						case CalidadSolicitanteCommon.TERCERO:
							ls_idConstante = as_constanteTercero;

							break;

						default:
							break;
					}

					if(CollectionUtils.isValidCollection(actd_tiposDocumentales))
					{
						for(TipoDocumental ltd_temp : actd_tiposDocumentales)
						{
							if((ltd_temp != null) && ltd_temp.isAgregadoPantalla())
								lctd_tiposRetorno.add(ltd_temp);
						}
					}

					if(StringUtils.isValidString(ls_idConstante))
					{
						Constantes lc_constante;

						lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_idConstante);

						if(lc_constante != null)
						{
							Collection<String> lcc_codigosTiposDocumentales;

							lcc_codigosTiposDocumentales = ListadoCodigosConstantes.generarCodigosCollection(
								    lc_constante.getCaracter()
								);

							if(CollectionUtils.isValidCollection(lcc_codigosTiposDocumentales))
							{
								TipoDocumentalDAO ltdd_DAO;

								ltdd_DAO = DaoCreator.getTipoDocumentalDAO(ldm_manager);

								for(String ls_temp : lcc_codigosTiposDocumentales)
								{
									if(StringUtils.isValidString(ls_temp))
									{
										TipoDocumental ltd_temp;

										ltd_temp = ltdd_DAO.findById(ls_temp);

										if(ltd_temp != null)
											lctd_tiposRetorno.add(ltd_temp);
									}
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("actualizarTiposDocumentales", lb2be_e);

				throw lb2be_e;
			}
		}

		return lctd_tiposRetorno;
	}

	/**
	 * Adds the error AT.
	 *
	 * @param as_mensaje de as mensaje
	 * @param loa_oa de loa oa
	 * @return el valor de string
	 */
	public String addErrorAT(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.ALERTA_TIERRAS_19);
	}

	/**
	 * Adds the error AT.
	 *
	 * @param as_mensaje de as mensaje
	 * @return el valor de string
	 */
	public String addErrorAT(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.ALERTA_TIERRAS_19);
	}

	/**
	 * Adds the error CA.
	 *
	 * @param as_mensaje de as mensaje
	 * @return el valor de string
	 */
	public String addErrorCA(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.GESTION_DE_USUARIOS_03);
	}

	/**
	 * Adds the error CA.
	 *
	 * @param as_mensaje de as mensaje
	 * @param loa_oa de loa oa
	 * @return el valor de string
	 */
	public String addErrorCA(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.GESTION_DE_USUARIOS_03);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto array
	 * @return devuelve el valor de String
	 */
	public String addErrorCD(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.CONTROL_DIGITALIZACION_11);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String addErrorCD(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.CONTROL_DIGITALIZACION_11);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String addErrorCDR(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.CONSULTA_DATOS_REGISTRALES_15);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String addErrorCHSP(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.CONSULTA_HISTORIAL_SOLICITUDES_PAGADASA_07);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String addErrorCMF(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.CONCILIACIONES_21);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @return devuelve el valor de String
	 *
	 */
	public String addErrorCMF(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.CONCILIACIONES_21);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @return el valor de string
	 */
	public String addErrorCTO(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.CATASTRO_10);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return el valor de string
	 */
	public String addErrorCTO(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.CATASTRO_10);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje de as mensaje
	 * @return el valor de string
	 */
	public String addErrorCX(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.COEXISTENCIA_14);
	}

	/**
	 * Adds the error CX.
	 *
	 * @param as_mensaje de as mensaje
	 * @param loa_oa de loa oa
	 * @return el valor de string
	 */
	public String addErrorCX(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.COEXISTENCIA_14);
	}

	/**
	 * Adds the error CYN.
	 *
	 * @param as_mensaje de as mensaje
	 * @param loa_oa de loa oa
	 * @return el valor de string
	 */
	public String addErrorCYN(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);
	}

	/**
	 * Adds the error CYN.
	 *
	 * @param as_mensaje de as mensaje
	 * @return el valor de string
	 */
	public String addErrorCYN(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String addErrorEP(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.ENTREGAR_PRODUCTO_08);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @return devuelve el valor de String
	 *
	 */
	public String addErrorEP(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.ENTREGAR_PRODUCTO_08);
	}

	/**
	 * Adds the error N.
	 *
	 * @param as_mensaje de as mensaje
	 * @param loa_oa de loa oa
	 * @return el valor de string
	 */
	public String addErrorN(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.NOTIFICADOR_20);
	}

	/**
	 * Adds the error N.
	 *
	 * @param as_mensaje de as mensaje
	 * @return el valor de string
	 */
	public String addErrorN(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.NOTIFICADOR_20);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String addErrorNDC(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @return devuelve el valor de String
	 *
	 */
	public String addErrorNDC(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @return devuelve el valor de String
	 *
	 */
	public String addErrorNP(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, true, ProyectosCommon.NOTIFICACION_PAGOS_04);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 *
	 */
	public String addErrorNP(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.NOTIFICACION_PAGOS_04);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String addErrorSC(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.SOLICITUD_DE_CORRECCION_17);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String addErrorSDC(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.SOLICITUD_DE_COPIAS_18);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessage(String as_mensaje)
	{
		return addMessage(as_mensaje, false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessage(String as_mensaje, boolean ab_error)
	{
		return addMessage(as_mensaje, null, ab_error, null);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @param as_proyect correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessage(String as_mensaje, boolean ab_error, String as_proyect)
	{
		return addMessage(as_mensaje, null, ab_error, as_proyect);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String addMessage(String as_mensaje, Object[] loa_oa, boolean ab_error)
	{
		return addMessage(as_mensaje, loa_oa, ab_error, null);
	}

	/**
	 * Añade el mensaje.
	 *
	 * @param as_mensaje de as mensaje
	 * @param loa_oa de loa oa
	 * @return el valor de string
	 */
	public String addMessage(String as_mensaje, Object[] loa_oa)
	{
		return addMessage(as_mensaje, loa_oa, false, null);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @param as_proyect correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessage(String as_mensaje, Object[] loa_oa, boolean ab_error, String as_proyect)
	{
		String ls_mensaje;

		ls_mensaje = null;

		if(StringUtils.isValidString(as_mensaje))
		{
			Messages lm_messages;

			if(ab_error)
			{
				if(StringUtils.isValidString(as_proyect))
				{
					switch(as_proyect)
					{
						case ProyectosCommon.ENTREGAR_PRODUCTO_08:
							lm_messages = getErrorMessagesEP();

							break;

						case ProyectosCommon.NOTIFICACION_PAGOS_04:
							lm_messages = getErrorMessagesNP();

							break;

						case ProyectosCommon.CONTROL_DIGITALIZACION_11:
							lm_messages = getErrorMessagesCD();

							break;

						case ProyectosCommon.CONSULTA_HISTORIAL_SOLICITUDES_PAGADASA_07:
							lm_messages = getErrorMessagesCHSP();

							break;

						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							lm_messages = getErrorMessagesGCC();

							break;

						case ProyectosCommon.GESTION_DE_USUARIOS_03:
							lm_messages = getErrorMessagesCA();

							break;

						case ProyectosCommon.CATASTRO_10:
							lm_messages = getErrorMessagesCT();

							break;

						case ProyectosCommon.GENERACION_SOLICITUD_13:
							lm_messages = getErrorMessagesGS();

							break;

						case ProyectosCommon.COEXISTENCIA_14:
							lm_messages = getErrorMessagesCX();

							break;

						case ProyectosCommon.CONSULTA_DATOS_REGISTRALES_15:
							lm_messages = getErrorMessagesCDR();

							break;

						case ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16:
							lm_messages = getErrorMessagesCYN();

							break;

						case ProyectosCommon.SOLICITUD_DE_CORRECCION_17:
							lm_messages = getErrorMessagesSC();

							break;

						case ProyectosCommon.SOLICITUD_DE_COPIAS_18:
							lm_messages = getErrorMessagesSDC();

							break;

						case ProyectosCommon.ALERTA_TIERRAS_19:
							lm_messages = getErrorMessagesAT();

							break;

						case ProyectosCommon.NOTIFICADOR_20:
							lm_messages = getErrorMessagesN();

							break;

						case ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22:
							lm_messages = getErrorMessagesNDC();

						case ProyectosCommon.CONCILIACIONES_21:
							lm_messages = getErrorMessagesCMF();

							break;

						default:
							lm_messages = getErrorMessages();
					}
				}
				else
					lm_messages = getErrorMessages();
			}
			else
				lm_messages = (StringUtils.isValidString(as_proyect)
						&& as_proyect.equalsIgnoreCase(ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22))
					? getMessagesNDC() : getMessages();

			if(lm_messages != null)
			{
				if(loa_oa != null)
					ls_mensaje = lm_messages.getString(as_mensaje, loa_oa);
				else
					ls_mensaje = lm_messages.getString(as_mensaje);
			}

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = as_mensaje;
		}

		return ls_mensaje;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageCD(String as_mensaje, boolean ab_error)
	{
		return addMessage(as_mensaje, null, ab_error, ProyectosCommon.CONTROL_DIGITALIZACION_11);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageCD(String as_mensaje, Object[] loa_oa, boolean ab_error)
	{
		return addMessage(as_mensaje, loa_oa, ab_error, ProyectosCommon.CONTROL_DIGITALIZACION_11);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageEP(String as_mensaje, Object[] loa_oa, boolean ab_error)
	{
		return addMessage(as_mensaje, loa_oa, ab_error, ProyectosCommon.ENTREGAR_PRODUCTO_08);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageEP(String as_mensaje, boolean ab_error)
	{
		return addMessage(as_mensaje, null, ab_error, ProyectosCommon.ENTREGAR_PRODUCTO_08);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param aoa_args correspondiente al valor del tipo de objeto Object array
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageGCC(String as_mensaje, Object[] aoa_args)
	{
		return addMessage(as_mensaje, aoa_args, true, ProyectosCommon.GESTION_CUENTA_CUPOS_12);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageGCC(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.GESTION_CUENTA_CUPOS_12);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageGS(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.GENERACION_SOLICITUD_13);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param aoa_args correspondiente al valor del tipo de objeto Object array
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageGS(String as_mensaje, Object[] aoa_args)
	{
		return addMessage(as_mensaje, aoa_args, true, ProyectosCommon.GENERACION_SOLICITUD_13);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageNDC(String as_mensaje)
	{
		return addMessage(as_mensaje, null, true, ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageNDC(String as_mensaje, boolean ab_error)
	{
		return addMessage(as_mensaje, null, ab_error, ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param aoa_args correspondiente al valor del tipo de objeto Object array
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageNDC(String as_mensaje, Object[] aoa_args)
	{
		return addMessage(as_mensaje, aoa_args, true, ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageNDC(String as_mensaje, Object[] loa_oa, boolean ab_error)
	{
		return addMessage(as_mensaje, loa_oa, ab_error, ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageNP(String as_mensaje, boolean ab_error)
	{
		return addMessage(as_mensaje, null, ab_error, ProyectosCommon.NOTIFICACION_PAGOS_04);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param loa_oa correspondiente al valor del tipo de objeto Object[]
	 * @param ab_error correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String addMessageNP(String as_mensaje, Object[] loa_oa, boolean ab_error)
	{
		return addMessage(as_mensaje, loa_oa, ab_error, ProyectosCommon.NOTIFICACION_PAGOS_04);
	}

	/**
	 * Agregar datos mapa permisos roles.
	 *
	 * @param amsb_permisosRoles de amsb permisos roles
	 * @param as_idRol de as id rol
	 * @param as_userId de as user id
	 * @param as_nombreRol de as nombre rol
	 * @param ard_DAO de ard DAO
	 * @param aurd_DAO de aurd DAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void agregarDatosMapaPermisosRoles(
	    Map<String, Boolean> amsb_permisosRoles, String as_idRol, String as_userId, String as_nombreRol, RolDAO ard_DAO,
	    UsuarioRolDAO aurd_DAO
	)
	    throws B2BException
	{
		if(
		    (amsb_permisosRoles != null) && StringUtils.isValidString(as_idRol) && StringUtils.isValidString(as_userId)
			    && StringUtils.isValidString(as_nombreRol) && (ard_DAO != null) && (aurd_DAO != null)
		)
		{
			try
			{
				Rol lr_rol;

				lr_rol = ard_DAO.findById(as_idRol, true);

				if(lr_rol != null)
				{
					UsuarioRol lur_usuarioRol;

					lur_usuarioRol     = new UsuarioRol(as_userId, lr_rol.getIdRol());
					lur_usuarioRol     = aurd_DAO.findById(lur_usuarioRol);

					amsb_permisosRoles.put(as_nombreRol, Boolean.valueOf(lur_usuarioRol != null));
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("agregarDatosMapaPermisosRoles", lb2be_e);

				throw lb2be_e;
			}
		}
	}

	/**
	 * Método de transacciones con la base de datos para encontrar una fecha de vencimiento.
	 *
	 * @param afvtui_parametros tipo de fecha para consultar en una funcion
	 * @return devuelve el valor de Date
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Date calcularFechaVencimiento(FechaVenceTerminosUI afvtui_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Date       lte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lte_datos       = null;

		try
		{
			lte_datos = DaoCreator.getUtilDAO(ldm_manager).funcionFechaVenceTerminos(afvtui_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("calcularFechaVencimiento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lte_datos;
	}

	/**
	 * Cargar datos panel tramites A realizar.
	 *
	 * @param adm_manager de adm manager
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, Boolean> cargarDatosRolesUsuarios(DAOManager adm_manager, String as_userId)
	    throws B2BException
	{
		Map<String, Boolean> lmsb_roles;

		lmsb_roles = null;

		if(adm_manager != null)
		{
			try
			{
				Usuario lu_user;

				lu_user     = new Usuario(as_userId);
				lu_user     = DaoCreator.getUsuarioDAO(adm_manager).findById(lu_user);

				if(lu_user != null)
				{
					RolDAO        lrd_DAO;
					UsuarioRolDAO lurd_DAO;

					lrd_DAO        = DaoCreator.getRolDAO(adm_manager);
					lurd_DAO       = DaoCreator.getUsuarioRolDAO(adm_manager);
					lmsb_roles     = new HashMap<String, Boolean>(1);
					agregarDatosMapaPermisosRoles(
					    lmsb_roles, RolCommon.CB_ROL_LIDER_VIGILANCIA_CONTROL, as_userId,
					    RolCommon.CB_ROL_LIDER_VIGILANCIA_CONTROL_TXT, lrd_DAO, lurd_DAO
					);
					agregarDatosMapaPermisosRoles(
					    lmsb_roles, RolCommon.CB_ROL_DELEGADO_REGISTRO, as_userId,
					    RolCommon.CB_ROL_DELEGADO_REGISTRO_TXT, lrd_DAO, lurd_DAO
					);
					agregarDatosMapaPermisosRoles(
					    lmsb_roles, RolCommon.CB_ROL_VISITADOR, as_userId, RolCommon.CB_ROL_VISITADOR_TXT, lrd_DAO,
					    lurd_DAO
					);
					agregarDatosMapaPermisosRoles(
					    lmsb_roles, RolCommon.CB_ROL_RESPONSABLE_SEGUIMIENTO_VISITAS, as_userId,
					    RolCommon.CB_ROL_RESPONSABLE_SEGUIMIENTO_VISITAS_TXT, lrd_DAO, lurd_DAO
					);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarDatosRolesUsuarios", lb2be_e);

				throw lb2be_e;
			}
		}

		return lmsb_roles;
	}

	/**
	 * Método encargado de validar si el tipo acto gener segregación o es de apertura.
	 *
	 * @param adm_manager DaoManager que controla la operación.
	 * @param as_idTipoActo Variable de tipo String que contiene el id del tipo acto a validar.
	 * @return Variable de tipo boolean que indica si el tipo acto genera segregación o es de apertura.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static boolean generaSegregacionOApertura(DAOManager adm_manager, String as_idTipoActo)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(StringUtils.isValidString(as_idTipoActo))
		{
			TipoActo lta_tipoActo;

			lta_tipoActo = DaoCreator.getTipoActoDAO(adm_manager).findById(as_idTipoActo);

			if(lta_tipoActo != null)
				lb_return = BooleanUtils.getBooleanValue(lta_tipoActo.getGeneraSegregacion())
						|| BooleanUtils.getBooleanValue(lta_tipoActo.getAperturaMatricula());
		}

		return lb_return;
	}

	/**
	 * Método encargado de dar formato decimal a un valor dado.
	 *
	 * @param ad_valor valor a dar formato
	 * @param adf_format tipo de formato
	 * @return String con formato ya establecido
	 *
	 */
	public synchronized String conversionCientifica(Double ad_valor, DecimalFormat adf_format)
	{
		String ls_conversion;

		ls_conversion = null;

		if((ad_valor != null) && (adf_format != null))
			ls_conversion = adf_format.format(ad_valor);

		return ls_conversion;
	}

	/**
	 * Método encargado de dar formato decimal a un valor dado.
	 *
	 * @param ad_valor valor a dar formato
	 * @return String con formato ya establecido
	 *
	 */
	public synchronized String conversionCientifica(Double ad_valor)
	{
		return conversionCientifica(ad_valor, new DecimalFormat("#,###.00"));
	}

	/**
	 * Método encargado de dar formato decimal a un valor dado.
	 *
	 * @param abd_valor valor a dar formato
	 * @return String con formato ya establecido
	 *
	 */
	public synchronized String conversionCientifica(BigDecimal abd_valor)
	{
		Double ld_tmp;
		String ls_tmp;

		ld_tmp     = NumericUtils.getDoubleWrapper(abd_valor);
		ls_tmp     = StringUtils.getStringNotNull(
			    StringUtils.getString(conversionCientifica(ld_tmp, new DecimalFormat("#,###.00")))
			);

		return ls_tmp;
	}

	/**
	 * Método encargado de consultar una constante dependiendo del id.
	 *
	 * @param as_idConstante con el cual se realizará la búsqueda de la constante.
	 * @return <code>Constantes</code> lleno con la infromación de la BD.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes descargarPlantilla(String as_idConstante)
	    throws B2BException
	{
		Constantes lct_datos;

		lct_datos = null;

		if(StringUtils.isValidString(as_idConstante))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lct_datos = DaoCreator.getConstantesDAO(ldm_manager).findByIdWithImage(as_idConstante);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("descargarPlantilla", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lct_datos;
	}

	/**
	 * Enviar correo inconsistencia.
	 *
	 * @param as_idInconsistencia the as id inconsistencia
	 * @param aca_analista the aca analista
	 * @param aerc_cuenta the aerc cuenta
	 * @param aerc_entidad the aerc entidad
	 * @param ad_fecha the ad fecha
	 * @param as_idPlantilla the as id plantilla
	 * @param adm_managerConciliaiciones the adm manager
	 * @param apb_business the apb business
	 * @throws B2BException the b 2 B exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public synchronized void enviarCorreoInconsistencia(
	    ConInconsistencia aci_inconsistencia, CuentaAnalista aca_analista, EntidadRecaudadoraCuenta aerc_cuenta,
	    EntidadRecaudadoraConciliacion aerc_entidad, Date ad_fecha, String as_idPlantilla,
	    DAOManager adm_managerConciliaiciones,
	    com.bachue.snr.prosnr21.business.conciliaciones.ParameterBusiness apb_business
	)
	    throws B2BException, AddressException, MessagingException
	{
		if(StringUtils.isValidString(as_idPlantilla))
		{
			try
			{
				String ls_jndiJavaMail;
				ls_jndiJavaMail = DaoCreator.getConstantesDAO(adm_managerConciliaiciones)
						                        .findString(
						    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.JOB_MOTOR_ENVIO_ELECTRONICO_JNDI
						);

				if(StringUtils.isValidString(ls_jndiJavaMail))
				{
					ConPlantillaMensaje lcpm_plantilla;
					lcpm_plantilla = DaoCreator.getConPlantillaMensajeDAO(adm_managerConciliaiciones)
							                       .findById(as_idPlantilla);

					if(lcpm_plantilla != null)
					{
						SendMail lse_emailPorEnviar;
						String   ls_cuerpo;
						String   ls_correo;

						lse_emailPorEnviar     = new SendMail(ls_jndiJavaMail, false, false, false);
						ls_cuerpo              = lcpm_plantilla.getCuerpo();
						ls_correo              = null;

						lse_emailPorEnviar.setSubject(lcpm_plantilla.getAsunto());

						if(StringUtils.isValidString(ls_cuerpo))
						{
							String ls_tag;
							ls_tag = "<TAG_BANCO>";

							if(ls_cuerpo.contains(ls_tag) && (aerc_entidad != null))
							{
								ls_cuerpo = ls_cuerpo.replace(ls_tag, aerc_entidad.getNombreEntidadRecaudadora());

								if(aerc_cuenta != null)
									ls_correo = aerc_cuenta.getCorreoElectronicoContacto();
							}

							ls_tag = "<TAG_FECHA_CONCILIACION>";

							if(ls_cuerpo.contains(ls_tag) && (ad_fecha != null))
								ls_cuerpo = ls_cuerpo.replace(
									    ls_tag, new SimpleDateFormat("dd/MM/yyyy").format(ad_fecha)
									);

							ls_tag = "<TAG_NUMERO_CUENTA>";

							if(ls_cuerpo.contains(ls_tag) && (aerc_cuenta != null))
								ls_cuerpo = ls_cuerpo.replace(ls_tag, aerc_cuenta.getNumeroCuenta());

							ls_tag = "<TAG_ANALISTA_CONCILIACION>";

							if(ls_cuerpo.contains(ls_tag) && (aca_analista != null))
							{
								// TODO Validar
								DAOManager ldm_manager;

								ldm_manager = DaoManagerFactory.getDAOManager();

								try
								{
									Usuario lu_analista;

									ls_correo       = aca_analista.getCorreoElectronicoAnalista();
									lu_analista     = DaoCreator.getUsuarioDAO(ldm_manager)
											                        .findById(aca_analista.getIdUsuario());

									if(lu_analista != null)
										ls_cuerpo = ls_cuerpo.replace(ls_tag, lu_analista.getNombreCompleto());
								}
								catch(B2BException lb2be_e)
								{
									ldm_manager.setRollbackOnly();

									clh_LOGGER.error("enviarCorreoInconsistencia", lb2be_e);

									throw lb2be_e;
								}
								finally
								{
									ldm_manager.close();
								}
							}

							if((aci_inconsistencia != null) && (apb_business != null))
							{
								aci_inconsistencia = apb_business.fillTagsObservaciones(
									    aci_inconsistencia, adm_managerConciliaiciones
									);

								if(aci_inconsistencia != null)
								{
									String ls_mensaje;

									ls_mensaje = aci_inconsistencia.getMensaje();

									if(StringUtils.isValidString(ls_mensaje))
									{
										ls_tag = "<TAG_MENSAJE_AVISO>";

										if(ls_cuerpo.contains(ls_tag))
											ls_cuerpo = ls_cuerpo.replace(ls_tag, ls_mensaje);

										ls_tag = "<TAG_MENSAJE_ALERTA>";

										if(ls_cuerpo.contains(ls_tag))
											ls_cuerpo = ls_cuerpo.replace(ls_tag, ls_mensaje);
									}

									ls_tag = "<TAG_OBSERVACIONES_ALERTA>";

									if(ls_cuerpo.contains(ls_tag))
										ls_cuerpo = ls_cuerpo.replace(ls_tag, aci_inconsistencia.getObservaciones());
								}
							}
						}

						lse_emailPorEnviar.setBody(ls_cuerpo);
						lse_emailPorEnviar.setTo(ls_correo);
						lse_emailPorEnviar.sendMailEvent();
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("enviarCorreoInconsistencia", lb2be_e);

				throw lb2be_e;
			}
		}
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_AUT_USUARIO que coincidan con un ID_USUARIO
	 * específico.
	 *
	 * @param ate_parametros
	 *                           objeto del cual se extrae el ID_USUARIO para la
	 *                           consulta en la base de datos
	 * @return devuelve el valor de Usuario
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public synchronized Usuario findUserById(Usuario ate_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Usuario    lte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lte_datos       = null;

		try
		{
			lte_datos = DaoCreator.getUsuarioDAO(ldm_manager).findById(ate_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUserById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lte_datos;
	}

	/**
	 * Metodo para poder generar zip de las relaciones.
	 *
	 * @param aba_ba El objeto aprobación donde se extrae la información necesaria para el zip
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarArchivoZipRelacion(Map<String, byte[]> aba_ba)
	    throws B2BException
	{
		byte[] lba_zipFinal;

		lba_zipFinal = null;

		if(CollectionUtils.isValidCollection(aba_ba))
		{
			Collection<ZipEntryUtil>        lczeu_zip;
			Iterator<Entry<String, byte[]>> liesb_iterador;
			String                          ls_nombreDocumento;

			lczeu_zip              = new ArrayList<ZipEntryUtil>();
			liesb_iterador         = aba_ba.entrySet().iterator();
			ls_nombreDocumento     = ConstanteCommon.NOMBRE_ARCHIVO_RELACION;

			while(liesb_iterador.hasNext())
			{
				Entry<String, byte[]> le_objeto;

				le_objeto = liesb_iterador.next();

				if(le_objeto != null)
				{
					String ls_key;
					byte[] lba_value;

					ls_key        = le_objeto.getKey();
					lba_value     = le_objeto.getValue();

					if(StringUtils.isValidString(ls_key) && (lba_value != null))
					{
						ZipEntryUtil lzeu_pdf;
						lzeu_pdf = new ZipEntryUtil(
							    ls_nombreDocumento + ls_key + IdentificadoresCommon.PUNTO + ExtensionCommon.PDF_MAYUS,
							    new ByteArrayInputStream(lba_value)
							);

						lczeu_zip.add(lzeu_pdf);
					}
				}
			}

			lba_zipFinal = ZipUtils.generateZip(lczeu_zip);
		}

		return lba_zipFinal;
	}

	/**
	 * Generar inconsistencia.
	 *
	 * @param aci_param the aci param
	 * @param as_codInconsistencia the as cod inconsistencia
	 * @return the con inconsistencia
	 * @throws B2BException the b 2 B exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public ConInconsistencia generarInconsistencia(ConInconsistencia aci_param, String as_codInconsistencia)
	    throws B2BException
	{
		ConInconsistencia lci_return;

		lci_return = null;

		try
		{
			lci_return = generarInconsistencia(aci_param, as_codInconsistencia, null, null, null, null);
		}
		catch(MessagingException e)
		{
		}

		return lci_return;
	}

	/**
	 * Generar inconsistencia.
	 *
	 * @param aci_param the aci param
	 * @param as_codInconsistencia the as cod inconsistencia
	 * @param apb_business the apb business
	 * @param aca_analista the aca analista
	 * @return the con inconsistencia
	 * @throws B2BException the b 2 B exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public ConInconsistencia generarInconsistencia(
	    ConInconsistencia aci_param, String as_codInconsistencia,
	    com.bachue.snr.prosnr21.business.conciliaciones.ParameterBusiness apb_business, CuentaAnalista aca_analista
	)
	    throws B2BException, AddressException, MessagingException
	{
		return generarInconsistencia(aci_param, as_codInconsistencia, null, null, apb_business, aca_analista);
	}

	/**
	 * Generar inconsistencia.
	 *
	 * @param aci_param the aci param
	 * @param as_codInconsistencia the as cod inconsistencia
	 * @param al_idRegistro the al id registro
	 * @param as_columna the as columna
	 * @param apb_business the apb business
	 * @param aca_analista the aca analista
	 * @return the con inconsistencia
	 * @throws B2BException the b 2 B exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public ConInconsistencia generarInconsistencia(
	    ConInconsistencia aci_param, String as_codInconsistencia, Long al_idRegistro, String as_columna,
	    com.bachue.snr.prosnr21.business.conciliaciones.ParameterBusiness apb_business, CuentaAnalista aca_analista
	)
	    throws B2BException, AddressException, MessagingException
	{
		DAOManager        ldm_manager;
		ConInconsistencia lci_return;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lci_return      = null;

		try
		{
			IncAviAleMenNot liaamn_data;

			liaamn_data = DaoCreator.getIncAviAleMenNotDAO(ldm_manager).findByIdActivo(as_codInconsistencia);

			if(liaamn_data != null)
			{
				lci_return = new ConInconsistencia(aci_param);

				lci_return.setCodigoInconsistencia(as_codInconsistencia);
				lci_return.setIdRegistro(al_idRegistro);
				lci_return.setColumna(as_columna);

				lci_return = DaoCreator.getConInconsistenciaDAO(ldm_manager).insert(lci_return);

				lci_return.setObservaciones(liaamn_data.getObservacion());
				lci_return.setMensaje(liaamn_data.getMensaje());

				if(
				    (apb_business != null) && (aca_analista != null)
					    && !StringUtils.getStringNotNull(liaamn_data.getTipo())
					                       .equals(
					        com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.INCONSISTENCIA_INFORMAIVO
					    )
				)
					enviarCorreoInconsistencia(
					    lci_return, aca_analista, null, null, null, PlantillaMensajeCommon.PLANTILLA_3, ldm_manager,
					    apb_business
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarInconsistencia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lci_return;
	}

	/**
	 * Metodo encargado de insertar en firma masiva.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_idTurnoHistoria Argumento de tipo <code>String</code> que contiene el turno historia.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized void insertarFirmaMasiva(DAOManager adm_manager, String as_usuario, String as_idTurnoHistoria)
	    throws B2BException
	{
		try
		{
			FirmaMasiva lfm_firmaMasiva;

			lfm_firmaMasiva = new FirmaMasiva();

			lfm_firmaMasiva.setUsuario(as_usuario);
			lfm_firmaMasiva.setTipoFirma(1);
			lfm_firmaMasiva.setLlave1(as_idTurnoHistoria);
			lfm_firmaMasiva.setIdUsuarioCreacion(as_usuario);

			DaoCreator.getFirmaMasivaDAO(adm_manager).insertFirmaMasiva(lfm_firmaMasiva);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("insertarFirmaMasiva", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Filtra la información a mostrar en pantalla del interesado con una bandera(Flag) marcada como origenBachue de cada Persona.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param ap_personaConsulta Objeto contenedor de la información a valdiar
	 * @return <code>Collection<Persona></code> de objetos tipo Persona con la información filtrada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> listarPersonaConFlag(DAOManager adm_manager, Persona ap_personaConsulta)
	    throws B2BException
	{
		Collection<Persona> lcp_return;

		lcp_return = new ArrayList<Persona>();

		if(ap_personaConsulta != null)
		{
			Collection<Persona> lcp_cllBusqueda;

			lcp_cllBusqueda = DaoCreator.getPersonaDAO(adm_manager).findByDocument(ap_personaConsulta);

			if(CollectionUtils.isValidCollection(lcp_cllBusqueda))
			{
				boolean lb_flagEncontrada;

				lb_flagEncontrada = false;

				for(Persona lp_personaTemp : lcp_cllBusqueda)
				{
					if(!lb_flagEncontrada && (lp_personaTemp != null))
					{
						String ls_origenBachue;

						ls_origenBachue = lp_personaTemp.getOrigenBachue();

						if(
						    StringUtils.isValidString(ls_origenBachue)
							    && ls_origenBachue.equalsIgnoreCase(EstadoCommon.S)
						)
						{
							lcp_return.add(lp_personaTemp);
							lb_flagEncontrada = true;
						}
					}
				}

				if(lcp_return.isEmpty())
					lcp_return = lcp_cllBusqueda;
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_DATOS_INTERESADO);

			if(lcp_return.isEmpty())
				lcp_return = lcp_cllBusqueda;
		}

		if(lcp_return.isEmpty())
			lcp_return = null;

		return lcp_return;
	}

	/**
	 * Llenar asunto cuerpo plantilla.
	 *
	 * @param as_idMensaje de as id mensaje
	 * @param ab_notificacion de ab notificacion
	 * @param ap_plantilla de ap plantilla
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void llenarAsuntoCuerpoPlantilla(
	    String as_idMensaje, boolean ab_notificacion, Plantilla ap_plantilla, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_idMensaje) && (ap_plantilla != null))
			{
				String ls_asunto;
				String ls_cuerpo;

				ls_asunto     = ap_plantilla.getAsunto();
				ls_cuerpo     = ap_plantilla.getCuerpo();

				{
					Map<String, String> lms_llaveValorAsunto;

					lms_llaveValorAsunto = DaoCreator.getParametrosMensajeDAO(adm_manager)
							                             .findLlaveValorByIdMensajeTipo(
							    as_idMensaje, TipoParametroCommon.ASUNTO
							);

					if(!StringUtils.isValidString(ls_asunto))
					{
						Constantes lc_c;

						lc_c = DaoCreator.getConstantesDAO(adm_manager)
								             .findById(
								    ab_notificacion
								    ? com.bachue.snr.prosnr16.common.constants.ConstanteCommon.ASUNTO_NOTIFICACION_DEFAULT
								        : com.bachue.snr.prosnr16.common.constants.ConstanteCommon.ASUNTO_COMUNICACION_DEFAULT
								);

						if(lc_c != null)
							ls_asunto = lc_c.getCaracter();
					}

					ls_asunto = reemplazarTags(ls_asunto, lms_llaveValorAsunto);
				}

				if(StringUtils.isValidString(ls_cuerpo))
				{
					Map<String, String> lms_llaveValorCuerpo;

					lms_llaveValorCuerpo     = DaoCreator.getParametrosMensajeDAO(adm_manager)
							                                 .findLlaveValorByIdMensajeTipo(
							    as_idMensaje, TipoParametroCommon.CUERPO
							);

					ls_cuerpo = reemplazarTags(ls_cuerpo, lms_llaveValorCuerpo);
				}
				else
					throw new B2BException(
					    addErrorCYN(
					        "No se encontro asunto parametrizado para el id plantilla " + ap_plantilla.getIdPlantilla()
					    )
					);

				ap_plantilla.setAsunto(ls_asunto);
				ap_plantilla.setCuerpo(ls_cuerpo);
			}
			else
				throw new B2BException(addErrorCYN("No se encontro plantilla parametrizada"));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("llenarAsuntoCuerpoPlantilla", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Guarda la información ingresada en pantalla del interesado con una bandera(Flag) marcada como origenBachue de cada Persona;
	 * consulta todos los registros por tipoDocumento y documento: compara campo a campo los registros actuales con el nuevo o seleccionado en pantalla.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param ap_personaConsulta Objeto contenedor de la información a validar
	 * @param as_usuario de as usuario
	 * @param as_remoteIp de as remote ip
	 * @return String con idPersona de la persona maracada con Flag
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String marcarFlagPersona(
	    DAOManager adm_manager, Persona ap_personaConsulta, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		return marcarFlagPersona(adm_manager, ap_personaConsulta, as_usuario, as_remoteIp, false);
	}

	/**
	 * Guarda la información ingresada en pantalla del interesado con una bandera(Flag) marcada como origenBachue de cada Persona;
	 * consulta todos los registros por tipoDocumento y documento: compara campo a campo los registros actuales con el nuevo o seleccionado en pantalla.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param ap_personaConsulta Objeto contenedor de la información a validar
	 * @param as_usuario de as usuario
	 * @param as_remoteIp de as remote ip
	 * @param ab_soloValidar Argumento de tipo <code>boolean</code> que determina si solo se debe validar o no.
	 * @return String con idPersona de la persona maracada con Flag
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String marcarFlagPersona(
	    DAOManager adm_manager, Persona ap_personaConsulta, String as_usuario, String as_remoteIp,
	    boolean ab_soloValidar
	)
	    throws B2BException
	{
		String ls_idPersonaMarcado;

		ls_idPersonaMarcado = null;

		if(ap_personaConsulta != null)
		{
			String     ls_tipoDocumento;
			String     ls_numeroDocumento;
			String     ls_primerNombre;
			String     ls_segundoNombre;
			String     ls_primerApellido;
			String     ls_segundoApellido;
			String     ls_razonSocial;
			boolean    lb_personaEncontrada;
			PersonaDAO lpd_DAO;

			ls_tipoDocumento         = ap_personaConsulta.getIdDocumentoTipo();
			ls_numeroDocumento       = ap_personaConsulta.getNumeroDocumento();
			ls_primerNombre          = ap_personaConsulta.getPrimerNombre();
			ls_segundoNombre         = ap_personaConsulta.getSegundoNombre();
			ls_primerApellido        = ap_personaConsulta.getPrimerApellido();
			ls_segundoApellido       = ap_personaConsulta.getSegundoApellido();
			ls_razonSocial           = ap_personaConsulta.getRazonSocial();
			lb_personaEncontrada     = false;
			lpd_DAO                  = DaoCreator.getPersonaDAO(adm_manager);

			if(StringUtils.isValidString(ls_tipoDocumento) && StringUtils.isValidString(ls_numeroDocumento))
			{
				Collection<Persona> lcp_cllBusqueda;

				lcp_cllBusqueda = lpd_DAO.findByDocumentoAndTipoDocumento(ls_numeroDocumento, ls_tipoDocumento);

				if(CollectionUtils.isValidCollection(lcp_cllBusqueda))
				{
					boolean lb_esNit;

					lb_esNit = false;

					if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
						lb_esNit = true;

					for(Persona lp_personaTemp : lcp_cllBusqueda)
					{
						if(lp_personaTemp != null)
						{
							if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
								lp_personaTemp.setIdTipoPersona(EstadoCommon.J);
							else
								lp_personaTemp.setIdTipoPersona(EstadoCommon.N);

							if(!lb_personaEncontrada)
							{
								String               ls_tipoDocumentoTemp;
								String               ls_numeroDocumentoTemp;
								String               ls_primerNombreTemp;
								String               ls_segundoNombreTemp;
								String               ls_primerApellidoTemp;
								String               ls_segundoApellidoTemp;
								String               ls_razonSocialTemp;
								Map<String, Boolean> lm_mapaValidacionBooleana;

								ls_tipoDocumentoTemp          = lp_personaTemp.getIdDocumentoTipo();
								ls_numeroDocumentoTemp        = lp_personaTemp.getNumeroDocumento();
								ls_primerNombreTemp           = lp_personaTemp.getPrimerNombre();
								ls_segundoNombreTemp          = lp_personaTemp.getSegundoNombre();
								ls_primerApellidoTemp         = lp_personaTemp.getPrimerApellido();
								ls_segundoApellidoTemp        = lp_personaTemp.getSegundoApellido();
								ls_razonSocialTemp            = lp_personaTemp.getRazonSocial();
								lm_mapaValidacionBooleana     = new HashMap<String, Boolean>();

								lm_mapaValidacionBooleana.put(IdentificadoresCommon.TIPO_DOCUMENTO, Boolean.FALSE);
								lm_mapaValidacionBooleana.put(IdentificadoresCommon.NUMERO_DOCUMENTO, Boolean.FALSE);

								if(!lb_esNit)
									lm_mapaValidacionBooleana.put(IdentificadoresCommon.PRIMER_NOMBRE, Boolean.FALSE);

								if(!lb_esNit)
									lm_mapaValidacionBooleana.put(IdentificadoresCommon.SEGUNDO_NOMBRE, Boolean.FALSE);

								if(!lb_esNit)
									lm_mapaValidacionBooleana.put(IdentificadoresCommon.PRIMER_APELLIDO, Boolean.FALSE);

								if(!lb_esNit)
									lm_mapaValidacionBooleana.put(
									    IdentificadoresCommon.SEGUNDO_APELLIDO, Boolean.FALSE
									);

								if(lb_esNit)
									lm_mapaValidacionBooleana.put(IdentificadoresCommon.RAZON_SOCIAL, Boolean.FALSE);

								if(
								    StringUtils.isValidString(ls_tipoDocumentoTemp)
									    && StringUtils.isValidString(ls_numeroDocumentoTemp)
								)
								{
									if(ls_tipoDocumento.equalsIgnoreCase(ls_tipoDocumentoTemp))
										lm_mapaValidacionBooleana.replace(
										    IdentificadoresCommon.TIPO_DOCUMENTO, Boolean.FALSE, Boolean.TRUE
										);

									if(ls_numeroDocumento.equalsIgnoreCase(ls_numeroDocumentoTemp))
										lm_mapaValidacionBooleana.replace(
										    IdentificadoresCommon.NUMERO_DOCUMENTO, Boolean.FALSE, Boolean.TRUE
										);

									if(!lb_esNit)
									{
										if(StringUtils.isValidString(ls_primerNombreTemp))
										{
											if(ls_primerNombreTemp.equalsIgnoreCase(ls_primerNombre))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.PRIMER_NOMBRE, Boolean.FALSE, Boolean.TRUE
												);
										}
										else
										{
											if(!StringUtils.isValidString(ls_primerNombre))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.PRIMER_NOMBRE, Boolean.FALSE, Boolean.TRUE
												);
										}

										if(StringUtils.isValidString(ls_segundoNombreTemp))
										{
											if(ls_segundoNombreTemp.equalsIgnoreCase(ls_segundoNombre))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.SEGUNDO_NOMBRE, Boolean.FALSE, Boolean.TRUE
												);
										}
										else
										{
											if(!StringUtils.isValidString(ls_segundoNombre))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.SEGUNDO_NOMBRE, Boolean.FALSE, Boolean.TRUE
												);
										}

										if(StringUtils.isValidString(ls_primerApellidoTemp))
										{
											if(ls_primerApellidoTemp.equalsIgnoreCase(ls_primerApellido))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.PRIMER_APELLIDO, Boolean.FALSE, Boolean.TRUE
												);
										}
										else
										{
											if(!StringUtils.isValidString(ls_primerApellido))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.PRIMER_APELLIDO, Boolean.FALSE, Boolean.TRUE
												);
										}

										if(StringUtils.isValidString(ls_segundoApellidoTemp))
										{
											if(ls_segundoApellidoTemp.equalsIgnoreCase(ls_segundoApellido))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.SEGUNDO_APELLIDO, Boolean.FALSE, Boolean.TRUE
												);
										}
										else
										{
											if(!StringUtils.isValidString(ls_segundoApellido))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.SEGUNDO_APELLIDO, Boolean.FALSE, Boolean.TRUE
												);
										}
									}
									else
									{
										if(StringUtils.isValidString(ls_razonSocialTemp))
										{
											if(ls_razonSocialTemp.equalsIgnoreCase(ls_razonSocial))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.RAZON_SOCIAL, Boolean.FALSE, Boolean.TRUE
												);
										}
										else
										{
											if(!StringUtils.isValidString(ls_razonSocial))
												lm_mapaValidacionBooleana.replace(
												    IdentificadoresCommon.RAZON_SOCIAL, Boolean.FALSE, Boolean.TRUE
												);
										}
									}

									if(lm_mapaValidacionBooleana.containsValue(Boolean.FALSE))
										lp_personaTemp.setOrigenBachue(EstadoCommon.N);
									else
									{
										ls_idPersonaMarcado = lp_personaTemp.getIdPersona();

										lp_personaTemp.setOrigenBachue(EstadoCommon.S);
										ap_personaConsulta.setOrigenBachue(EstadoCommon.S);

										lb_personaEncontrada = true;
									}

									lp_personaTemp.setIdUsuarioModificacion(as_usuario);
									lp_personaTemp.setIpModificacion(as_remoteIp);

									if(!ab_soloValidar)
										lpd_DAO.insertOrUpdate(lp_personaTemp, false);
								}
							}
							else
							{
								lp_personaTemp.setOrigenBachue(EstadoCommon.N);
								lp_personaTemp.setIdUsuarioModificacion(as_usuario);
								lp_personaTemp.setIpModificacion(as_remoteIp);

								if(!ab_soloValidar)
									lpd_DAO.insertOrUpdate(lp_personaTemp, false);
							}
						}
					}
				}

				if(!lb_personaEncontrada)
				{
					Persona lp_personaInsertada;

					lp_personaInsertada = null;

					if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
						ap_personaConsulta.setIdTipoPersona(EstadoCommon.J);
					else
						ap_personaConsulta.setIdTipoPersona(EstadoCommon.N);

					ap_personaConsulta.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					ap_personaConsulta.setOrigenBachue(EstadoCommon.S);
					ap_personaConsulta.setIdUsuarioCreacion(as_usuario);
					ap_personaConsulta.setIpCreacion(as_remoteIp);

					if(!ab_soloValidar)
						lp_personaInsertada = lpd_DAO.insertOrUpdate(ap_personaConsulta, true);

					if(lp_personaInsertada != null)
					{
						String ls_idPersonaInsertado;

						ls_idPersonaInsertado = lp_personaInsertada.getIdPersona();

						if(StringUtils.isValidString(ls_idPersonaInsertado))
							ls_idPersonaMarcado = ls_idPersonaInsertado;
					}
				}
				else
				{
					ap_personaConsulta.setIdUsuarioModificacion(as_usuario);
					ap_personaConsulta.setIpModificacion(as_remoteIp);

					if(!ab_soloValidar)
						lpd_DAO.insertOrUpdate(ap_personaConsulta, false);
				}
			}
		}

		return ls_idPersonaMarcado;
	}

	/**
	 * Método encargado de modificar una fecha; modifica la fecha dependiendo del campo y valor que se ingresa.
	 *
	 * @param ad_fecha Objeto Date contenedor de fecha
	 * @param ai_campo int que especifica el campo que se desea cambiar - Posibles valores: Calendar.HOUR, Calendar.MONTH, Calendar.YEAR
	 * @param ai_valor int con el valor a cambiar
	 * @return Date modificada
	 */
	public static Date modificarFecha(Date ad_fecha, int ai_campo, int ai_valor)
	{
		if(ai_valor == 0)
			return ad_fecha;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ad_fecha);
		calendar.add(ai_campo, ai_valor);

		return calendar.getTime();
	}

	/**
	 * Notificar catastro .
	 *
	 * @param as_idCirculo de id circulo
	 * @param al_idMatricula de id matricula
	 * @param amsss_listaMatriculasANotificar de lista matriculas A notificar
	 * @param abpd_DAO de BitacoraProcesoDAO
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemota Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return lista matriculas A notificar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, Set<String>> notificarCatastro(
	    String as_idCirculo, Long al_idMatricula, Map<String, Set<String>> amsss_listaMatriculasANotificar,
	    BitacoraProcesoDAO abpd_DAO, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		try
		{
			if(
			    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
				    && (amsss_listaMatriculasANotificar != null)
			)
			{
				Set<String> lss_matriculas;

				lss_matriculas = amsss_listaMatriculasANotificar.get(as_idCirculo);

				if(lss_matriculas == null)
					lss_matriculas = new HashSet<String>();

				lss_matriculas.add(al_idMatricula.toString());

				amsss_listaMatriculasANotificar.put(as_idCirculo, lss_matriculas);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
		}
		catch(B2BException lb2be_e)
		{
			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.NOTIFICACION_CATASTRO, lb2be_e.getMessage(), as_userId, as_ipRemota,
			    as_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + al_idMatricula
			);

			throw lb2be_e;
		}

		return amsss_listaMatriculasANotificar;
	}

	/**
	 * Obtener archivos adjuntos.
	 *
	 * @param as_idMensaje de as id mensaje
	 * @param adm_manager de adm manager
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, byte[]> obtenerArchivosAdjuntos(String as_idMensaje, DAOManager adm_manager)
	    throws B2BException
	{
		Map<String, byte[]> lmsb_archivosAdjuntos;

		lmsb_archivosAdjuntos = new LinkedHashMap<String, byte[]>();

		try
		{
			if(StringUtils.isValidString(as_idMensaje))
			{
				Collection<MensajeAdjunto> lcma_mensajeAdjunto;

				lcma_mensajeAdjunto = DaoCreator.getMensajeAdjuntoDAO(adm_manager).findAllByIdMensaje(as_idMensaje);

				if(CollectionUtils.isValidCollection(lcma_mensajeAdjunto))
				{
					Constantes lc_constante;

					lc_constante = DaoCreator.getConstantesDAO(adm_manager)
							                     .findById(
							    com.bachue.snr.prosnr01.common.constants.ConstanteCommon.OBTENER_ARCHIVO_OWCC_ENDPOINT
							);

					if(lc_constante != null)
					{
						for(MensajeAdjunto lma_tmp : lcma_mensajeAdjunto)
						{
							if(lma_tmp != null)
							{
								byte[] lba_archivo;

								lba_archivo = ClienteObtenerArchivo.obtenerArchivo(
									    lma_tmp.getDId(), lma_tmp.getDocName(), lc_constante.getCaracter()
									);

								if(lba_archivo != null)
									lmsb_archivosAdjuntos.put(
									    lma_tmp.getIdAdjunto() + IdentificadoresCommon.SIMBOLO_GUION_BAJO
									    + lma_tmp.getDocName(), lba_archivo
									);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_OPERACION_NO_EXITOSA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerArchivosAdjuntos", lb2be_e);
			throw lb2be_e;
		}

		if(lmsb_archivosAdjuntos.isEmpty())
			lmsb_archivosAdjuntos = null;

		return lmsb_archivosAdjuntos;
	}

	/**
	 * Obtener el valor del campo CARACTER de una constante en base de datos.
	 *
	 * @param acd_constante DAO de constantes embebido en transacción
	 * @param as_idConstante Id de la constante a extraer
	 * @param ab_validar the ab validar
	 * @return Campo CARACTER de la constante identificada con as_idConstante
	 * @throws B2BException si la constante no existe en la base de datos
	 */
	public synchronized String obtenerConstanteCaracter(
	    ConstantesDAO acd_constante, String as_idConstante, boolean ab_validar
	)
	    throws B2BException
	{
		String ls_valorConstante;

		ls_valorConstante = null;

		try
		{
			Constantes lc_constante;

			lc_constante          = acd_constante.findById(as_idConstante);
			ls_valorConstante     = (lc_constante != null) ? lc_constante.getCaracter() : null;

			if(!StringUtils.isValidString(ls_valorConstante) && ab_validar)
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = as_idConstante;

				throw new B2BException(addErrorCMF(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs));
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerArchivosAdjuntos", lb2be_e);
			throw lb2be_e;
		}

		return ls_valorConstante;
	}

	/**
	 * Obtener el valor del campo CARACTER de una constante en base de datos.
	 *
	 * @param acd_constante DAO de constantes embebido en transacción
	 * @param as_idConstante Id de la constante a extraer
	 * @return Campo CARACTER de la constante identificada con as_idConstante
	 * @throws B2BException si la constante no existe en la base de datos
	 */
	public synchronized String obtenerConstanteCaracter(ConstantesDAO acd_constante, String as_idConstante)
	    throws B2BException
	{
		String ls_valorConstante;

		ls_valorConstante = null;

		try
		{
			ls_valorConstante = obtenerConstanteCaracter(acd_constante, as_idConstante, true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerArchivosAdjuntos", lb2be_e);
			throw lb2be_e;
		}

		return ls_valorConstante;
	}

	/**
	 * Metodo encargado de obtener la llave primaria de un id oficina origen.
	 *
	 * @param as_idOficinaOrigen Argumento de tipo <code>String</code> que contiene el id oficina origen.
	 * @param adm_manager Argumento de tipo <code>DaoManager</code> que permite la conexión a la base de datos.
	 * @return Argumento de tipo <code>OficinaOrigen</code> que contiene los resultados de la búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficinaOrigen
	 */
	public OficinaOrigen obtenerLlavePrimariaOficinaOrigen(String as_idOficinaOrigen, DAOManager adm_manager)
	    throws B2BException
	{
		OficinaOrigen loo_oficinaOrigen;

		loo_oficinaOrigen = null;

		try
		{
			if(StringUtils.isValidString(as_idOficinaOrigen))
			{
				BigDecimal lbd_version;

				lbd_version     = obtenerVersionMaximaOficinaOrigen(as_idOficinaOrigen, adm_manager);

				loo_oficinaOrigen = new OficinaOrigen();

				loo_oficinaOrigen.setIdOficinaOrigen(as_idOficinaOrigen);
				loo_oficinaOrigen.setVersion((lbd_version != null) ? lbd_version : new BigDecimal(1));
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerLlavePrimariaOficinaOrigen", lb2be_e);

			throw lb2be_e;
		}

		return loo_oficinaOrigen;
	}

	/**
	 * Obtener NIRs vinculados.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @param adm_manager de DAOManager
	 * @return el valor de string con NIRs vinculados separados por ;
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String obtenerNIRsVinculados(String as_idSolicitud, DAOManager adm_manager)
	    throws B2BException
	{
		return obtenerNIRsVinculados(as_idSolicitud, true, adm_manager);
	}

	/**
	 * Obtener NIRs vinculados.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @param ab_indVinculado Argumento de tipo <code>boolean</code> que determina si se debe consultar indicador vinculado.
	 * @param adm_manager de DAOManager
	 * @return el valor de string con NIRs vinculados separados por ;
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String obtenerNIRsVinculados(
	    String as_idSolicitud, boolean ab_indVinculado, DAOManager adm_manager
	)
	    throws B2BException
	{
		String ls_nirVinculado;

		ls_nirVinculado = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Collection<Solicitud> lcsa_solicitudAsociada;

				lcsa_solicitudAsociada = obtenerSolicitudesVinculadas(as_idSolicitud, ab_indVinculado, adm_manager);

				if(CollectionUtils.isValidCollection(lcsa_solicitudAsociada))
				{
					for(Solicitud lsa_tmp : lcsa_solicitudAsociada)
					{
						if(lsa_tmp != null)
						{
							if(!StringUtils.isValidString(ls_nirVinculado))
								ls_nirVinculado = lsa_tmp.getNir();
							else
								ls_nirVinculado = ls_nirVinculado + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
									+ lsa_tmp.getNir();
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerNIRsVinculados", lb2be_e);

			throw lb2be_e;
		}

		return ls_nirVinculado;
	}

	/**
	 * Obtener nombre tipo operacion notificar catastro.
	 *
	 * @param as_idTipoOperacion de id tipo Operacion
	 * @param adm_manager Argumento de tipo <code>DaoManager</code> que permite la conexión a la base de datos.
	 * @return retorno de tipo <code>String</code> que contiene el resultado de la búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String obtenerNombreTipoOperacionNotificarCatastro(String as_idTipoOperacion, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_nombre;

		ls_nombre = null;

		try
		{
			if(StringUtils.isValidString(as_idTipoOperacion))
			{
				TipoOperacion lto_tipoOperacion;

				lto_tipoOperacion = DaoCreator.getTipoOperacionDAO(adm_manager).findById(as_idTipoOperacion);

				if(lto_tipoOperacion != null)
					ls_nombre = lto_tipoOperacion.getNombre();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerNombreTipoOperacionNotificarCatastro", lb2be_e);

			throw lb2be_e;
		}

		return ls_nombre;
	}

	/**
	 * Obtener la cadena de texto de un ruta de archivos a partir de una rota base y un subdirectorio.
	 *
	 * @param as_rutaBase Ruta base
	 * @param as_subdirectorio Subdirectorio
	 * @return cadena de texto de la ruta completa
	 */
	public synchronized String obtenerRutaCompleta(String as_rutaBase, String as_subdirectorio)
	{
		String ls_ruta;

		ls_ruta = null;

		if(StringUtils.isValidString(as_rutaBase) && StringUtils.isValidString(as_subdirectorio))
		{
			String        ls_separador;
			StringBuilder lsb_ruta;

			ls_separador     = com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_SEPARADOR_DIRECTORIO;
			lsb_ruta         = new StringBuilder(as_rutaBase);

			lsb_ruta.append(ls_separador);
			lsb_ruta.append(as_subdirectorio);

			ls_ruta = lsb_ruta.toString();

			{
				String        ls_reemplazar;
				StringBuilder lsb_reemplazar;

				lsb_reemplazar = new StringBuilder(ls_separador);
				lsb_reemplazar.append(ls_separador);

				ls_reemplazar = lsb_reemplazar.toString();

				while(ls_ruta.indexOf(ls_reemplazar) >= 0)
					ls_ruta = ls_ruta.replaceAll(ls_reemplazar, ls_separador);
			}
		}

		return ls_ruta;
	}

	/**
	 * Obtener NIRs vinculados.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @param adm_manager de DAOManager
	 * @return el valor de string con NIRs vinculados separados por ;
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Solicitud> obtenerSolicitudesVinculadas(
	    String as_idSolicitud, DAOManager adm_manager
	)
	    throws B2BException
	{
		return obtenerSolicitudesVinculadas(as_idSolicitud, true, adm_manager);
	}

	/**
	 * Obtener NIRs vinculados.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @param ab_validarIndicadorVinculado correspondiente al valor de validar indicador vinculado
	 * @param adm_manager de DAOManager
	 * @return el valor de string con NIRs vinculados separados por ;
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Solicitud> obtenerSolicitudesVinculadas(
	    String as_idSolicitud, boolean ab_validarIndicadorVinculado, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<Solicitud> lcs_solicitudAsociada;

		lcs_solicitudAsociada = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Collection<SolicitudAsociada> lcsa_solicitudAsociada;
				SolicitudDAO                  lsd_DAO;

				lsd_DAO                    = DaoCreator.getSolicitudDAO(adm_manager);
				lcsa_solicitudAsociada     = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
						                                   .findAllByIdSolicitud(
						    as_idSolicitud, ab_validarIndicadorVinculado
						);

				if(CollectionUtils.isValidCollection(lcsa_solicitudAsociada))
				{
					lcs_solicitudAsociada = new ArrayList<Solicitud>();

					for(SolicitudAsociada lsa_tmp : lcsa_solicitudAsociada)
					{
						if(lsa_tmp != null)
						{
							Solicitud ls_solTmp;

							ls_solTmp = lsd_DAO.findById(lsa_tmp.getIdSolicitud1());

							if(ls_solTmp != null)
								lcs_solicitudAsociada.add(ls_solTmp);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerSolicitudesVinculadas", lb2be_e);

			throw lb2be_e;
		}

		if((lcs_solicitudAsociada != null) && lcs_solicitudAsociada.isEmpty())
			lcs_solicitudAsociada = null;

		return lcs_solicitudAsociada;
	}

	/**
	 * Obtener el valor String de un cadena de caracteres Decimal.
	 *
	 * @param as_valor Cadena de caracteres con valor Decimal
	 * @return Valor String de la cadena de caracteres
	 */
	public synchronized String obtenerStringDecimal(String as_valor)
	{
		return as_valor.replace(",", ".");
	}

	/**
	 * Obtener turnos vinculados.
	 *
	 * @param as_idTurno de id turno
	 * @param adm_manager de DAOManager
	 * @return el valor de string con turnos vinculados separados por ;
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String obtenerTurnosVinculados(String as_idTurno, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_turnoVinculado;

		ls_turnoVinculado = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				if(StringUtils.isValidString(as_idTurno) && !as_idTurno.equalsIgnoreCase(EstadoCommon.NA))
				{
					Collection<TurnoDerivado> lctd_turnoDerivado;

					lctd_turnoDerivado = DaoCreator.getTurnoDerivadoDAO(adm_manager)
							                           .findByIdTurnoPadreVinculados(as_idTurno);

					if(CollectionUtils.isValidCollection(lctd_turnoDerivado))
					{
						for(TurnoDerivado ltd_tmp : lctd_turnoDerivado)
						{
							if(ltd_tmp != null)
							{
								if(!StringUtils.isValidString(ls_turnoVinculado))
									ls_turnoVinculado = ltd_tmp.getIdTurnoHijo();
								else
									ls_turnoVinculado = ls_turnoVinculado
										+ IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT + ltd_tmp.getIdTurnoHijo();
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerTurnosVinculados", lb2be_e);

			throw lb2be_e;
		}

		return ls_turnoVinculado;
	}

	/**
	 * Obtener el valor Double de un cadena de caracteres.
	 *
	 * @param as_valor Cadena de caracteres con valor Double
	 * @return Valor Double de la cadena de caracteres o null en caso de que no se pueda hacer la conversion
	 */
	public synchronized Double obtenerValorDoble(String as_valor)
	{
		Double ld_valor;

		try
		{
			ld_valor = Double.valueOf(as_valor);
		}
		catch(NumberFormatException lnfe_e)
		{
			ld_valor = null;
		}

		return ld_valor;
	}

	/**
	 * Obtener el valor Integer de un cadena de caracteres.
	 *
	 * @param as_valor Cadena de caracteres con valor Integer
	 * @return Valor Integer de la cadena de caracteres o null en caso de que no se pueda hacer la conversion
	 */
	public synchronized Integer obtenerValorEntero(String as_valor)
	{
		Integer li_valor;

		try
		{
			li_valor = Integer.valueOf(as_valor);
		}
		catch(NumberFormatException lnfe_e)
		{
			li_valor = null;
		}

		return li_valor;
	}

	/**
	 * Metodo encargado de obtener la versión máxima de un id oficina origen.
	 *
	 * @param as_idOficinaOrigen Argumento de tipo <code>String</code> que contiene el id oficina origen.
	 * @param adm_manager Argumento de tipo <code>DaoManager</code> que permite la conexión a la base de datos.
	 * @return Argumento de tipo <code>BigDecimal</code> que contiene los resultados de la búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see BigDecimal
	 */
	public BigDecimal obtenerVersionMaximaOficinaOrigen(String as_idOficinaOrigen, DAOManager adm_manager)
	    throws B2BException
	{
		BigDecimal lbd_versionMaxima;

		lbd_versionMaxima = null;

		try
		{
			if(StringUtils.isValidString(as_idOficinaOrigen))
			{
				lbd_versionMaxima = DaoCreator.getOficinaOrigenDAO(adm_manager).findMaxVersion(as_idOficinaOrigen);

				if(lbd_versionMaxima == null)
					lbd_versionMaxima = BigDecimal.ONE;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerVersionMaxima", lb2be_e);

			throw lb2be_e;
		}

		return lbd_versionMaxima;
	}

	/**
	 * Reemplazar tags.
	 *
	 * @param as_plantilla de as plantilla
	 * @param amss_llaveValor de ams llave valor
	 * @return el valor de string
	 */
	public synchronized String reemplazarTags(String as_plantilla, Map<String, String> amss_llaveValor)
	{
		if(StringUtils.isValidString(as_plantilla) && (CollectionUtils.isValidCollection(amss_llaveValor)))
		{
			for(Map.Entry<String, String> lmess_entry : amss_llaveValor.entrySet())
			{
				if(lmess_entry != null)
				{
					String ls_llave;
					String ls_valor;

					ls_llave     = lmess_entry.getKey();
					ls_valor     = lmess_entry.getValue();

					if(StringUtils.isValidString(ls_llave) && StringUtils.isValidString(ls_valor))
						as_plantilla = as_plantilla.replace(ls_llave, ls_valor);
				}
			}
		}

		return as_plantilla;
	}

	/**
	 * Resolver.
	 *
	 * @param as_base the as base
	 * @param aerc_entidad the aerc entidad
	 * @param aerc_cuenta the aerc cuenta
	 * @param ad_fecha the ad fecha
	 * @param as_tipoExtracto the as tipo extracto
	 * @return the string
	 */
	public synchronized String resolver(
	    String as_base, EntidadRecaudadoraConciliacion aerc_entidad, EntidadRecaudadoraCuenta aerc_cuenta, Date ad_fecha,
	    String as_tipoExtracto
	)
	{
		return resolver(as_base, aerc_entidad, aerc_cuenta, ad_fecha, null, as_tipoExtracto);
	}

	/**
	 * Resolver.
	 *
	 * @param as_base the as base
	 * @param aerc_entidad the aerc entidad
	 * @param aerc_cuenta the aerc cuenta
	 * @param ad_fecha the ad fecha
	 * @return the string
	 */
	public synchronized String resolver(
	    String as_base, EntidadRecaudadoraConciliacion aerc_entidad, EntidadRecaudadoraCuenta aerc_cuenta, Date ad_fecha
	)
	{
		return resolver(as_base, aerc_entidad, aerc_cuenta, ad_fecha, null, null);
	}

	/**
	 * Resolver.
	 *
	 * @param as_base the as base
	 * @param aerc_entidad the aerc entidad
	 * @param aerc_cuenta the aerc cuenta
	 * @param ad_fecha the ad fecha
	 * @param aca_archivo the aca archivo
	 * @return the string
	 */
	public synchronized String resolver(
	    String as_base, EntidadRecaudadoraConciliacion aerc_entidad, EntidadRecaudadoraCuenta aerc_cuenta, Date ad_fecha,
	    ConArchivo aca_archivo
	)
	{
		return resolver(as_base, aerc_entidad, aerc_cuenta, ad_fecha, aca_archivo, null);
	}

	/**
	 * Reemplaza todas las ocurrencias de TAG de formato en una cadena de texto.
	 *
	 * @param as_base the as base
	 * @param aerc_entidad the aerc entidad
	 * @param aerc_cuenta the aerc cuenta
	 * @param ad_fecha the ad fecha
	 * @param aca_archivo the aca archivo
	 * @param as_tipoExtracto the as tipo extracto
	 * @return the string
	 */
	public synchronized String resolver(
	    String as_base, EntidadRecaudadoraConciliacion aerc_entidad, EntidadRecaudadoraCuenta aerc_cuenta, Date ad_fecha,
	    ConArchivo aca_archivo, String as_tipoExtracto
	)
	{
		boolean lb_cuenta;
		boolean lb_entidad;
		boolean lb_fecha;
		boolean lb_archivo;
		boolean lb_extracto;
		String  ls_respuesta;

		lb_cuenta       = aerc_cuenta != null;
		lb_entidad      = aerc_entidad != null;
		lb_fecha        = ad_fecha != null;
		lb_extracto     = StringUtils.isValidString(as_tipoExtracto);
		lb_archivo      = aca_archivo != null;

		if(StringUtils.isValidString(as_base) && (lb_entidad || lb_cuenta || lb_fecha || lb_archivo || lb_extracto))
		{
			StringBuilder lsb_base;

			lsb_base = new StringBuilder(as_base);

			if(lb_entidad)
			{
				lsb_base     = resolver(
					    lsb_base, "NOMBRE_ENTIDAD_FINANCIERA", aerc_entidad.getNombreEntidadRecaudadora()
					);
				lsb_base     = resolver(
					    lsb_base, "CODIGO_ENTIDAD_FINANCIERA", aerc_entidad.getCodigoEntidadRecaudadora()
					);
			}

			if(lb_cuenta)
			{
				lsb_base     = resolver(lsb_base, "TIPO_CUENTA", aerc_cuenta.getTipoCuenta());
				lsb_base     = resolver(lsb_base, "NUMERO_CUENTA", aerc_cuenta.getNumeroCuenta());
			}

			if(lb_archivo)
			{
				lsb_base     = resolver(lsb_base, "TIPO_ARCHIVO", aca_archivo.getTipoArchivo());
				lsb_base     = resolver(lsb_base, "VERSION_ARCHIVO", StringUtils.getString(aca_archivo.getVersion()));
			}

			if(lb_fecha)
				lsb_base = resolver(lsb_base, "FECHA", ad_fecha);

			if(lb_extracto)
				resolver(lsb_base, "TIPO_EXTRACTO", as_tipoExtracto);

			ls_respuesta = lsb_base.toString();
		}
		else
			ls_respuesta = new String();

		return ls_respuesta;
	}

	/**
	 * Método que escala una imagen que este en un <code>byte[]</code>.
	 *
	 * @param aba_file <code>byte[]</code> lleno con la imagen a escalar
	 * @param ai_width <code>int</code> numero de píxeles para el ancho de la imagen
	 * @param ai_height <code>int</code> numero de píxeles para el alto de la imagen
	 * @return <code>byte[]</code> con la imagen escalada a las medidas de los paramétros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] scaleImage(byte[] aba_file, int ai_width, int ai_height)
	    throws B2BException
	{
		ByteArrayOutputStream lbaos_buffer;

		lbaos_buffer = new ByteArrayOutputStream();

		if(aba_file != null)
		{
			try
			{
				ByteArrayInputStream lbais_inputStream;
				BufferedImage        lbi_img;

				lbais_inputStream     = new ByteArrayInputStream(aba_file);
				lbi_img               = ImageIO.read(lbais_inputStream);

				if(lbi_img != null)
				{
					if(ai_height == 0)
						ai_height = (ai_width * lbi_img.getHeight()) / lbi_img.getWidth();

					if(ai_width == 0)
						ai_width = (ai_height * lbi_img.getWidth()) / lbi_img.getHeight();

					{
						Image         li_imagenEscalada;
						BufferedImage lbi_imagenBuff;

						li_imagenEscalada     = lbi_img.getScaledInstance(ai_width, ai_height, Image.SCALE_SMOOTH);
						lbi_imagenBuff        = new BufferedImage(ai_width, ai_height, BufferedImage.TYPE_INT_RGB);

						if((li_imagenEscalada != null) && (lbi_imagenBuff != null))
						{
							Graphics lg_graphics;

							lg_graphics = lbi_imagenBuff.getGraphics();

							if(lg_graphics != null)
							{
								lg_graphics.drawImage(li_imagenEscalada, 0, 0, new Color(0, 0, 0), null);
								ImageIO.write(lbi_imagenBuff, "jpg", lbaos_buffer);
							}
						}
					}
				}
			}
			catch(IOException lioe_ioe)
			{
				throw new B2BException(cs_IOEXCEPTION, lioe_ioe);
			}
		}

		return lbaos_buffer.toByteArray();
	}

	/**
	 * Separar por simbolo coma.
	 *
	 * @param as_texto de as texto
	 * @return el valor de collection
	 */
	public Collection<String> separarPorSimboloComa(String as_texto)
	{
		return separarPorSimboloComa(as_texto, false);
	}

	/**
	 * Método encargado de separar por SIMBOLO_COMA de identificadoresCommon.
	 *
	 * @param as_texto Texto a separar
	 * @param ab_comaTXT de ab coma TXT
	 * @return Colección de String separados
	 */
	public Collection<String> separarPorSimboloComa(String as_texto, boolean ab_comaTXT)
	{
		Collection<String> lcs_return;

		lcs_return = new ArrayList<String>();

		if(StringUtils.isValidString(as_texto))
		{
			String[] lsa_array;

			lsa_array = as_texto.split(
				    ab_comaTXT ? IdentificadoresCommon.SIMBOLO_COMA_TXT : IdentificadoresCommon.SIMBOLO_COMA
				);

			if(CollectionUtils.isValidCollection(lsa_array))
			{
				for(String ls_temp : lsa_array)
					lcs_return.add(ls_temp);
			}
		}

		if(lcs_return.isEmpty())
			lcs_return = null;

		return lcs_return;
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Tipo Documental Acto.
	 *
	 * @param atd_parametros            objeto a insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void updateNumeracionResolucionCirculo(
	    NumeracionResolucionCirculo atd_parametros, String as_usuario, String as_ip, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(atd_parametros != null)
			{
				atd_parametros.setIdUsuarioModificacion(as_usuario);
				atd_parametros.setIpModificacion(as_ip);

				DaoCreator.getNumeracionResolucionCirculoDAO(adm_manager).update(atd_parametros);
			}
			else
				throw new B2BException(ErrorKeys.TESTAMENTO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("updateTestamento", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método para extraer actos de una constante.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param as_constante correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validacionActosContraConstante(String as_idSolicitud, String as_constante)
	    throws B2BException
	{
		return validacionActosContraConstante(as_idSolicitud, null, as_constante);
	}

	/**
	 * Método para extraer actos de una constante.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param as_idAnotacionPredio correspondiente al identificador de anotación predio
	 * @param as_constante correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean validacionActosContraConstante(
	    String as_idSolicitud, String as_idAnotacionPredio, String as_constante
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_validacionActoContraConstante;

		ldm_manager                          = DaoManagerFactory.getDAOManager();
		lb_validacionActoContraConstante     = false;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_constante))
			{
				ActoDAO          la_DAO;
				Constantes       lc_constante;
				Collection<Acto> lca_ca;

				la_DAO           = DaoCreator.getActoDAO(ldm_manager);
				lc_constante     = DaoCreator.getConstantesDAO(ldm_manager).findById(as_constante);

				if(!StringUtils.isValidString(as_idAnotacionPredio))
					lca_ca = la_DAO.findByIdSolicitud(as_idSolicitud);
				else
					lca_ca = la_DAO.findBySolicitudAntPredio(as_idSolicitud, as_idAnotacionPredio);

				if((lc_constante != null) && CollectionUtils.isValidCollection(lca_ca))
				{
					String ls_caracter;

					ls_caracter = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_caracter))
					{
						Map<String, String> lmss_temp;

						lmss_temp = ListadoCodigosConstantes.generarCodigos(ls_caracter);

						if(CollectionUtils.isValidCollection(lmss_temp))
						{
							Iterator<Acto> lia_iterator;

							lia_iterator = lca_ca.iterator();

							while(lia_iterator.hasNext() && !lb_validacionActoContraConstante)
							{
								Acto la_temp;

								la_temp = lia_iterator.next();

								if(la_temp != null)
								{
									String ls_idTipoActo;

									ls_idTipoActo = la_temp.getIdTipoActo();

									if(StringUtils.isValidString(ls_idTipoActo))
										lb_validacionActoContraConstante = lmss_temp.containsKey(ls_idTipoActo);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("lb_validacionActoContraConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_validacionActoContraConstante;
	}

	/**
	 * Método encargado de validar si la anotación contiene un acto que genere apertura de matrículas.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @param adm_manager Conexión a la base de datos.
	 * @return Variable de tipo boolean que indica si la anotación contiene un acto que genere apertura de matrículas.
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized boolean validarAnotacionApertura(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, DAOManager adm_manager
	)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			if(aap_anotacionPredio != null)
			{
				String ls_idTipoActo;

				ls_idTipoActo = aap_anotacionPredio.getIdNaturalezaJuridica();

				if(StringUtils.isValidString(ls_idTipoActo))
				{
					String[] lsa_data;
					TipoActo lta_data;

					lsa_data          = ls_idTipoActo.split(IdentificadoresCommon.SIMBOLO_GUION);
					ls_idTipoActo     = lsa_data[0];
					lta_data          = DaoCreator.getTipoActoDAO(adm_manager).findById(ls_idTipoActo);
					lb_return         = (lta_data != null)
							&& BooleanUtils.getBooleanValue(lta_data.getAperturaMatricula());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarAnotacionApertura", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}

	/**
	 * Método encargado de validar si para el turno historia acutal se debe generar código QR.
	 *
	 * @param ath_param Objeto que contiene la información del turno historia.
	 * @param adm_manager DaoManager que controla el acceso a la base de datos.
	 * @return Variable booleana que valida si el códio QR se debe generar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean validarQR(TurnoHistoria ath_param, DAOManager adm_manager)
	    throws B2BException
	{
		return validarQR(ath_param, adm_manager, true);
	}

	/**
	 * Método encargado de validar si para el turno historia acutal se debe generar código QR.
	 *
	 * @param ath_param Objeto que contiene la información del turno historia.
	 * @param adm_manager DaoManager que controla el acceso a la base de datos.
	 * @param ab_qr Variable boolean que valida si el códio QR se genera.
	 * @return Variable booleana que valida si el códio QR se debe generar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean validarQR(TurnoHistoria ath_param, DAOManager adm_manager, boolean ab_qr)
	    throws B2BException
	{
		ath_param = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(ath_param);

		if(ath_param != null)
		{
			Etapa le_etapa;
			Turno lt_turno;

			le_etapa     = DaoCreator.getEtapaDAO(adm_manager).findById(NumericUtils.getLong(ath_param.getIdEtapa()));
			lt_turno     = DaoCreator.getTurnoDAO(adm_manager).findById(ath_param.getIdTurno());

			if((le_etapa != null) && ab_qr)
				ab_qr = ab_qr && le_etapa.isGeneraQR();
			else
				ab_qr = false;

			if((lt_turno != null) && ab_qr)
			{
				String ls_idProceso;

				ls_idProceso     = lt_turno.getIdProceso();
				ab_qr            = StringUtils.isValidString(ls_idProceso)
						&& (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
						|| ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
						|| ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6));
			}
			else
				ab_qr = false;
		}

		return ab_qr;
	}

	/**
	 * Método para obtener actuaciones administrativas bussines.
	 *
	 * @return el valor de actuaciones administrativas business
	 */
	protected ActuacionesAdministrativasBusiness getActuacionesAdministrativasBusiness()
	{
		if(iaab_actuacionesAdministrativas == null)
			iaab_actuacionesAdministrativas = new ActuacionesAdministrativasBusiness();

		return iaab_actuacionesAdministrativas;
	}

	/**
	 * Método para obtener antiguo bussines.
	 *
	 * @return el valor de antiguo business
	 */
	protected AntiguoSistemaBusiness getAntiguoSistemaBusiness()
	{
		if(iasb_antiguoSistema == null)
			iasb_antiguoSistema = new AntiguoSistemaBusiness();

		return iasb_antiguoSistema;
	}

	/**
	 * Método para obtener aprobacion bussines.
	 *
	 * @return el valor de aprobacion business
	 */
	protected AprobacionBusiness getAprobacionBusiness()
	{
		if(iab_aprobacion == null)
			iab_aprobacion = new AprobacionBusiness();

		return iab_aprobacion;
	}

	/**
	 * Método para obtener calificacion bussines.
	 *
	 * @return el valor de calificacion business
	 */
	protected CalificacionBusiness getCalificacionBusiness()
	{
		if(icb_calificacion == null)
			icb_calificacion = new CalificacionBusiness();

		return icb_calificacion;
	}

	/**
	 * Método para obtener certificados bussines.
	 *
	 * @return el valor de certificados business
	 */
	protected CertificadosBusiness getCertificadosBusiness()
	{
		if(icb_certificados == null)
			icb_certificados = new CertificadosBusiness();

		return icb_certificados;
	}

	/**
	 * Método para obtener constancia reproduccion bussines.
	 *
	 * @return el valor de constancia reproduccion business
	 */
	protected ConstanciaReproduccionBusiness getConstanciaReproduccionBusiness()
	{
		if(icrb_constanciaReproduccion == null)
			icrb_constanciaReproduccion = new ConstanciaReproduccionBusiness();

		return icrb_constanciaReproduccion;
	}

	/**
	 * Método para obtener consulta trazabilidad business.
	 *
	 * @return el valor de consulta trazabilidad business
	 */
	protected ConsultaTrazabilidadBusiness getConsultaTrazabilidadBusiness()
	{
		if(ictb_consultaTrazabilidad == null)
			ictb_consultaTrazabilidad = new ConsultaTrazabilidadBusiness();

		return ictb_consultaTrazabilidad;
	}

	/**
	 * Método para obtener criterios consulta bussines.
	 *
	 * @return el valor de criterios consulta business
	 */
	protected CriteriosConsultaBusiness getCriteriosConsultaBusiness()
	{
		if(iccb_criteriosConsulta == null)
			iccb_criteriosConsulta = new CriteriosConsultaBusiness();

		return iccb_criteriosConsulta;
	}

	/**
	 * Método para obtener idgitalizacion bussines.
	 *
	 * @return el valor de digitalizacion business
	 */
	protected DevolucionDineroBusiness getDevolucionDineroBusiness()
	{
		if(idd_devolucionDinero == null)
			idd_devolucionDinero = new DevolucionDineroBusiness();

		return idd_devolucionDinero;
	}

	/**
	 * Método para obtener idgitalizacion bussines.
	 *
	 * @return el valor de digitalizacion business
	 */
	protected DigitalizacionBusiness getDigitalizacionBusiness()
	{
		if(idb_digitalizacion == null)
			idb_digitalizacion = new DigitalizacionBusiness();

		return idb_digitalizacion;
	}

	/**
	 * Método para obtener envio gestor documental bussines.
	 *
	 * @return el valor de envio gestor documental business
	 */
	protected EnvioGestorDocumentalBusiness getEnvioGestorDocumentalBusiness()
	{
		if(iegdb_envioGestorDocumental == null)
			iegdb_envioGestorDocumental = new EnvioGestorDocumentalBusiness();

		return iegdb_envioGestorDocumental;
	}

	/**
	 * Get error key.
	 *
	 * @param ab2be_e de ab 2 be e
	 * @return el valor de string
	 */
	protected String getErrorMessage(B2BException ab2be_e)
	{
		return getErrorMessage(ab2be_e, CONFIG_ERROR_MESSAGES);
	}

	/**
	 * Get error key.
	 *
	 * @param ab2be_e de ab 2 be e
	 * @param as_configError de as config error
	 * @return el valor de string
	 */
	protected String getErrorMessage(B2BException ab2be_e, String as_configError)
	{
		String ls_error;

		ls_error = null;

		if(ab2be_e != null)
		{
			B2BException.setBundle(as_configError);
			ls_error = ab2be_e.getMessage();
		}

		return ls_error;
	}

	/**
	 * Método para obtener firma masiva bussines.
	 *
	 * @return el valor de firma masiva business
	 */
	protected FirmaMasivaBusiness getFirmaMasivaBusiness()
	{
		if(ifmb_firmaMasiva == null)
			ifmb_firmaMasiva = new FirmaMasivaBusiness();

		return ifmb_firmaMasiva;
	}

	/**
	 * Método para obtener grabacion bussines.
	 *
	 * @return el valor de grabacion business
	 */
	protected GrabacionBusiness getGrabacionBusiness()
	{
		if(igb_grabacion == null)
			igb_grabacion = new GrabacionBusiness();

		return igb_grabacion;
	}

	/**
	 * Método para obtener liquidacion bussines.
	 *
	 * @return el valor de liquidacion business
	 */
	protected LiquidacionBusiness getLiquidacionBusiness()
	{
		if(ili_liquidacion == null)
			ili_liquidacion = new LiquidacionBusiness();

		return ili_liquidacion;
	}

	/**
	 * Método para obtener parameter bussines.
	 *
	 * @return el valor de parameter business
	 */
	protected ParameterBusiness getParameterBusiness()
	{
		if(ipb_parameter == null)
			ipb_parameter = new ParameterBusiness();

		return ipb_parameter;
	}

	/**
	 * Método para obtener reference bussines.
	 *
	 * @return el valor de reference business
	 */
	protected ReferenceBusiness getReferenceBusiness()
	{
		if(irb_reference == null)
			irb_reference = new ReferenceBusiness();

		return irb_reference;
	}

	/**
	 * Método para obtener registro bussines.
	 *
	 * @return el valor de registro business
	 */
	protected RegistroBusiness getRegistroBusiness()
	{
		if(irb_registro == null)
			irb_registro = new RegistroBusiness();

		return irb_registro;
	}

	/**
	 * Método para obtener registro calificacion bussines.
	 *
	 * @return el valor de registro calificacion business
	 */
	protected RegistroCalificacionBusiness getRegistroCalificacionBusiness()
	{
		if(ircb_registroCalificacion == null)
			ircb_registroCalificacion = new RegistroCalificacionBusiness();

		return ircb_registroCalificacion;
	}

	/**
	 * Método para obtener segunda instancia business.
	 *
	 * @return el valor de segunda instancia business
	 */
	protected SegundaInstanciaBusiness getSegundaInstanciaBusiness()
	{
		if(isib_segundaInstanciaBusiness == null)
			isib_segundaInstanciaBusiness = new SegundaInstanciaBusiness();

		return isib_segundaInstanciaBusiness;
	}

	/**
	 * Método para obtener suspension bussines.
	 *
	 * @return el valor de suspension business
	 */
	protected SuspensionBusiness getSuspensionBusiness()
	{
		if(isb_suspension == null)
			isb_suspension = new SuspensionBusiness();

		return isb_suspension;
	}

	/**
	 * Retorna Objeto o variable de valor system user.
	 *
	 * @param as_constanteUsuario de as constante usuario
	 * @param ldm_usuario de ldm usuario
	 * @return el valor de system user
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized String getSystemUser(String as_constanteUsuario, DAOManager ldm_usuario)
	    throws B2BException
	{
		return getSystemUser(as_constanteUsuario, false, ldm_usuario);
	}

	/**
	 * Retorna el valor de system user.
	 *
	 * @param as_constanteUsuario correspondiente al valor del tipo de objeto String
	 * @param ab_validarUsuario de ab validar usuario
	 * @param ldm_usuario correspondiente al valor del tipo de objeto DAOManager
	 * @return el valor de system user
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected synchronized String getSystemUser(
	    String as_constanteUsuario, boolean ab_validarUsuario, DAOManager ldm_usuario
	)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			Constantes lce_constant;

			lce_constant = DaoCreator.getConstantesDAO(ldm_usuario).findById(as_constanteUsuario);

			if(lce_constant != null)
			{
				String ls_usuario;

				ls_usuario = lce_constant.getCaracter();

				if(StringUtils.isValidString(ls_usuario) && ab_validarUsuario)
				{
					Usuario lu_usuario;

					lu_usuario = DaoCreator.getUsuarioDAO(ldm_usuario).findById(new Usuario(ls_usuario));

					if(lu_usuario != null)
						ls_return = lu_usuario.getIdUsuario();
					else
					{
						Object[] loa_arg;

						loa_arg        = new String[1];
						loa_arg[0]     = ls_usuario;

						throw new B2BException(ErrorKeys.ERROR_USUARIO_NO_ENCONTRADO, loa_arg);
					}
				}
				else if(!ab_validarUsuario)
					ls_return = ls_usuario;
				else
					throw new B2BException(ErrorKeys.ERROR_USUARIO_INEXISTENTE_E1);
			}
			else
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = as_constanteUsuario;

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("getSystemUser", lb2be_e);

			throw lb2be_e;
		}

		return ls_return;
	}

	/**
	 * Actualizar constante bloqueo.
	 *
	 * @param adm_manager de adm manager
	 * @param as_ipRemote de as ip remote
	 * @param as_usuario de as usuario
	 * @param as_idConstante de as id constante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized void actualizarConstanteBloqueo(
	    DAOManager adm_manager, String as_ipRemote, String as_usuario, String as_idConstante
	)
	    throws B2BException
	{
		try
		{
			Constantes lc_param;

			lc_param = new Constantes();

			lc_param.setIdUsuarioModificacion(as_usuario);
			lc_param.setIpModificacion(as_ipRemote);
			lc_param.setCaracter(EstadoCommon.S);
			lc_param.setIdConstante(as_idConstante);

			DaoCreator.getConstantesDAO(adm_manager).insertOrUpdate(lc_param, false);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarConstanteBloqueo", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de actualizar la dirección completa.
	 *
	 * @param ld_direccion Objeto que contiene la información de la dirección.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Variable de tipo String que contiene la dirección completa.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String actualizarDireccionPredioCompleta(DireccionPredioAcc ld_direccion, DAOManager adm_manager)
	    throws B2BException
	{
		StringBuilder ls_direccionCompleta;

		ls_direccionCompleta = new StringBuilder();

		if(ld_direccion != null)
		{
			try
			{
				CoordenadaDAO lc_DAO;
				LetraEjeDAO   lle_DAO;
				TipoEjeDAO    lpe_DAO;
				String        ls_espacio;

				lc_DAO         = DaoCreator.getCoordenadaDAO(adm_manager);
				lle_DAO        = DaoCreator.getLetraEjeDAO(adm_manager);
				lpe_DAO        = DaoCreator.getTipoEjeDAO(adm_manager);
				ls_espacio     = IdentificadoresCommon.ESPACIO_VACIO;

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdTipoEjePrincipal();

					if(StringUtils.isValidString(ls_dato))
					{
						TipoEje lte_tmp;

						lte_tmp = lpe_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getDatoEjePrincipal();

					if(StringUtils.isValidString(ls_dato))
						ls_direccionCompleta.append(ls_dato + ls_espacio);
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdLetraEjePrincipal();

					if(StringUtils.isValidString(ls_dato))
					{
						LetraEje lte_tmp;

						lte_tmp = lle_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getLetra()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdComplementoEjePrincipal();

					if(StringUtils.isValidString(ls_dato))
					{
						TipoEje lte_tmp;

						lte_tmp = lpe_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdCoordenadaEjePrincipal();

					if(StringUtils.isValidString(ls_dato))
					{
						Coordenada lc_tmp;

						lc_tmp = lc_DAO.findById(ls_dato);

						if(lc_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lc_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdTipoEjeSecundario();

					if(StringUtils.isValidString(ls_dato))
					{
						TipoEje lte_tmp;

						lte_tmp = lpe_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getDatoEjeSecundario();

					if(StringUtils.isValidString(ls_dato))
						ls_direccionCompleta.append(ls_dato + ls_espacio);
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdLetra1EjeSecundario();

					if(StringUtils.isValidString(ls_dato))
					{
						LetraEje lte_tmp;

						lte_tmp = lle_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getLetra()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdComplemento1EjeSecundario();

					if(StringUtils.isValidString(ls_dato))
					{
						TipoEje lte_tmp;

						lte_tmp = lpe_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdCoordenada1EjeSecundario();

					if(StringUtils.isValidString(ls_dato))
					{
						Coordenada lc_tmp;

						lc_tmp = lc_DAO.findById(ls_dato);

						if(lc_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lc_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getDato2EjeSecundario();

					if(StringUtils.isValidString(ls_dato))
						ls_direccionCompleta.append(ls_dato + ls_espacio);
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdLetra2EjeSecundario();

					if(StringUtils.isValidString(ls_dato))
					{
						LetraEje lte_tmp;

						lte_tmp = lle_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getLetra()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdComplemento2EjeSecundario();

					if(StringUtils.isValidString(ls_dato))
					{
						TipoEje lte_tmp;

						lte_tmp = lpe_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdCoordenada2EjeSecundario();

					if(StringUtils.isValidString(ls_dato))
					{
						Coordenada lc_tmp;

						lc_tmp = lc_DAO.findById(ls_dato);

						if(lc_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lc_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getIdComplementoDireccion();

					if(StringUtils.isValidString(ls_dato))
					{
						TipoEje lte_tmp;

						lte_tmp = lpe_DAO.findById(ls_dato);

						if(lte_tmp != null)
							ls_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + ls_espacio);
					}
				}

				{
					String ls_dato;

					ls_dato = ld_direccion.getComplementoDireccion();

					if(StringUtils.isValidString(ls_dato))
						ls_direccionCompleta.append(ls_dato + ls_espacio);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("actualizarDireccionPredioCompleta", lb2be_e);

				throw lb2be_e;
			}
		}

		return ls_direccionCompleta.toString();
	}

	/**
	 * Actualizar intento envio.
	 *
	 * @param ath_turno de ath turno
	 * @param as_mensaje de as mensaje
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de Conexión a la base de datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized void actualizarIntentoEnvio(
	    TurnoHistoria ath_turno, String as_mensaje, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(ath_turno != null)
		{
			{
				long ll_numeroIntentos;

				ll_numeroIntentos = ath_turno.getIntentosFallidosEjecucionAutomatica();

				ath_turno.setIntentosFallidosEjecucionAutomatica(ll_numeroIntentos + 1);
			}

			ath_turno.setRespuestaEjecucionAutomatica(as_mensaje);
			ath_turno.setIdUsuarioModificacion(as_userId);
			ath_turno.setIpModificacion(as_remoteIp);

			DaoCreator.getTurnoHistoriaDAO(adm_manager).updateIntentoEnvios(ath_turno);
		}
	}

	/**
	 * Concatena una cadena de caracteres en un string builder con un espacio vacio al final.
	 *
	 * @param asb_nombreCompleto String builder donde se va a almacenar el resultado de la concatenación
	 * @param as_parte Parte a concatenar con un espacio vacio
	 */
	protected void agregarParteDeNombre(StringBuilder asb_nombreCompleto, String as_parte)
	{
		if(StringUtils.isValidString(as_parte) && (asb_nombreCompleto != null))
		{
			asb_nombreCompleto.append(as_parte);
			asb_nombreCompleto.append(IdentificadoresCommon.ESPACIO_VACIO);
		}
	}

	/**
	 * Agregar tipo documental en documento salida.
	 *
	 * @param ads_documento de ads documento
	 * @param ab_etapa230 de ab etapa 230
	 * @param ab_etapa232 de ab etapa 232
	 * @param ab_notificacion de ab notificacion
	 * @param ab_etapa206 de ab etapa 206
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void agregarTipoDocumentalEnDocumentoSalida(
	    DocumentosSalida ads_documento, boolean ab_etapa230, boolean ab_etapa232, boolean ab_notificacion,
	    boolean ab_etapa206
	)
	    throws B2BException
	{
		if(ads_documento == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		if(ab_etapa230)
		{
			ads_documento.setTipo(TipoArchivoCommon.NOTIFICACION_AVISO);
			ads_documento.setIdTipoDocumental(TipoDocumentalCommon.NOTIFICACION_AVISO);
		}
		else if(ab_etapa232)
		{
			ads_documento.setTipo(TipoArchivoCommon.FIJACION_AVISO);
			ads_documento.setIdTipoDocumental(TipoDocumentalCommon.FIJACION_AVISO);
		}
		else
		{
			if(ab_notificacion)
			{
				ads_documento.setTipo(
				    ab_etapa206 ? TipoArchivoCommon.ACTA_NOTIFICACION_CORREO : TipoArchivoCommon.ACTA_NOTIFICACION
				);
				ads_documento.setIdTipoDocumental(TipoDocumentalCommon.ACTA_NOTIFICACION);
			}
			else
			{
				ads_documento.setTipo(TipoArchivoCommon.CITACION_NOTIFICACION);
				ads_documento.setIdTipoDocumental(TipoDocumentalCommon.CITACION_NOTIFICACION);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de OficinaOrigen.
	 *
	 * @param as_idOficinaOrigen correspondiente al valor del tipo de objeto String
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de OficinaOrigen
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficinaOrigen
	 */
	protected OficinaOrigen buscarOficinaOrigenPorId(String as_idOficinaOrigen, DAOManager adm_manager)
	    throws B2BException
	{
		OficinaOrigen loo_oficinaOrigen;

		loo_oficinaOrigen = null;

		try
		{
			if(StringUtils.isValidString(as_idOficinaOrigen))
			{
				loo_oficinaOrigen = obtenerLlavePrimariaOficinaOrigen(as_idOficinaOrigen, adm_manager);

				if(loo_oficinaOrigen != null)
					loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager).findById(loo_oficinaOrigen);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarOficinaOrigenPorId", lb2be_e);

			throw lb2be_e;
		}

		return loo_oficinaOrigen;
	}

	/**
	 * Calcular id motivo recursos.
	 *
	 * @param adm_manager de adm manager
	 * @param as_idSolicitud de as id solicitud
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected long calcularIdMotivoRecursos(DAOManager adm_manager, String as_idSolicitud)
	    throws B2BException
	{
		long ll_idMotivo;

		ll_idMotivo = 0L;

		try
		{
			SolicitudAsociadaDAO lsad_DAO;
			TurnoHistoriaDAO     lthd_DAO;
			SolicitudAsociada    lsa_asociada;
			boolean              lb_asociadaValida;

			lsad_DAO              = DaoCreator.getSolicitudAsociadaDAO(adm_manager);
			lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(adm_manager);
			lsa_asociada          = lsad_DAO.findLastByIdSolicitudProceso(as_idSolicitud, ProcesoCommon.ID_PROCESO_48);
			lb_asociadaValida     = false;

			if(lsa_asociada != null)
			{
				TurnoHistoria lth_historia;

				lth_historia = lthd_DAO.findBySolicitudEtapa(
					    lsa_asociada.getIdSolicitud1(), null, EtapaCommon.ID_ETAPA_430, EstadoCommon.TERMINADA
					);

				if(lth_historia != null)
				{
					Long ll_idMotivoTua;

					ll_idMotivoTua = lth_historia.getIdMotivo();

					if(
					    NumericUtils.isValidLong(ll_idMotivoTua)
						    && (ll_idMotivoTua.longValue() == MotivoTramiteCommon.PROCESO_DE_REGISTRO_FINALIZADO)
					)
					{
						ll_idMotivo           = MotivoTramiteCommon.COMUNICACION_AUTO_DE_PRUEBAS_ENVIADA;
						lb_asociadaValida     = true;
					}
				}
			}
			else if(!lb_asociadaValida)
			{
				lsa_asociada = lsad_DAO.findLastByIdSolicitudProceso(as_idSolicitud, ProcesoCommon.ID_PROCESO_47);

				if(lsa_asociada != null)
				{
					TurnoHistoria lth_historia;

					lth_historia = lthd_DAO.findBySolicitudEtapa(
						    lsa_asociada.getIdSolicitud1(), null,
						    EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS, EstadoCommon.TERMINADA
						);

					if(lth_historia != null)
					{
						Long ll_idMotivoTua;

						ll_idMotivoTua = lth_historia.getIdMotivo();

						if(
						    NumericUtils.isValidLong(ll_idMotivoTua)
							    && (ll_idMotivoTua.longValue() == MotivoTramiteCommon.PROCESO_DE_REGISTRO_FINALIZADO)
						)
							ll_idMotivo = MotivoTramiteCommon.COMUNICACION_AUTO_DE_PRUEBAS_ENVIADA_288;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("calcularIdMotivoRecursos", lb2be_e);

			throw lb2be_e;
		}

		return ll_idMotivo;
	}

	/**
	 * Metodo encargado de cargar motivos por linea de producción.
	 *
	 * @param al_idEtapa Argumento de tipo <code>long</code> que contiene el id de la etapa a validar.
	 * @param as_idLineaProduccion Argumento de tipo <code>String</code> que contiene el id de la linea de producción a validar.
	 * @return Variable de tipo <code>long</code> que contiene el id motivo calculado.
	 */
	protected long calcularMotivoPorLinea(long al_idEtapa, String as_idLineaProduccion)
	{
		long ll_idMotivo;

		if(al_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
			ll_idMotivo = as_idLineaProduccion.equalsIgnoreCase(LineaProduccionCommon.RECURSO_NOTA_DEVOLUTIVA)
				? MotivoTramiteCommon.ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_NOTA_DEVOLUTIVA
				: (as_idLineaProduccion.equalsIgnoreCase(LineaProduccionCommon.RECURSO_ACTUACION_ADMINISTRATIVA)
				? MotivoTramiteCommon.ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_ACTUACION_ADMINISTRATIVA
				: (as_idLineaProduccion.equalsIgnoreCase(LineaProduccionCommon.RECURSO_DEVOLUCION_DE_DINERO)
				? MotivoTramiteCommon.ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_DEVOLUCION_DE_DINERO
				: (as_idLineaProduccion.equalsIgnoreCase(
				    LineaProduccionCommon.RECURSO_ACTOS_DE_INSCRIPCION_O_ANOTACION
				) ? MotivoTramiteCommon.ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_ACTOS_DE_INSCRIPCION_O_ANOTACION : 0L)));
		else if(al_idEtapa == EtapaCommon.ID_ETAPA_460)
			ll_idMotivo = as_idLineaProduccion.equalsIgnoreCase(
				    LineaProduccionCommon.SEGUNDA_INSTANCIA_NOTA_DEVOLUTIVA
				) ? MotivoTramiteCommon.ANALISIS_EXPEDIENTE_RECURSO_NOTA_DEVOLUTIVA
				  : (as_idLineaProduccion.equalsIgnoreCase(
				    LineaProduccionCommon.SEGUNDA_INSTANCIA_ACTUACION_ADMINISTRATIVA
				) ? MotivoTramiteCommon.ANALISIS_EXPEDIENTE_RECURSO_ACTUACION_ADMINISTRATIVA
				  : (as_idLineaProduccion.equalsIgnoreCase(
				    LineaProduccionCommon.SEGUNDA_INSTANCIA_DEVOLUCION_DE_DINERO
				) ? MotivoTramiteCommon.ANALISIS_EXPEDIENTE_RECURSO_DEVOLUCION_DE_DINERO
				  : (as_idLineaProduccion.equalsIgnoreCase(
				    LineaProduccionCommon.SEGUNDA_INSTANCIA_ACTOS_DE_INSCRIPCION_O_ANOTACION
				) ? MotivoTramiteCommon.ANALISIS_EXPEDIENTE_RECURSO_ACTOS_DE_INSCRIPCION_O_ANOTACION : 0L)));
		else
			ll_idMotivo = as_idLineaProduccion.equalsIgnoreCase(LineaProduccionCommon.NOTA_DEVOLUTIVA)
				? MotivoTramiteCommon.ANALISIS_EXPEDIENTE_NOTA_DEVOLUTIVA
				: (as_idLineaProduccion.equalsIgnoreCase(LineaProduccionCommon.ACTUACION_ADMINISTRATIVA)
				? MotivoTramiteCommon.ANALISIS_EXPEDIENTE_ACTUACION_ADMINISTRATIVA
				: (as_idLineaProduccion.equalsIgnoreCase(LineaProduccionCommon.DEVOLUCION_DE_DINERO)
				? MotivoTramiteCommon.ANALISIS_EXPEDIENTE_DEVOLUCION_DE_DINERO
				: (as_idLineaProduccion.equalsIgnoreCase(LineaProduccionCommon.ACTOS_DE_INSCRIPCION_O_ANOTACION)
				? MotivoTramiteCommon.ANALISIS_EXPEDIENTE_ACTOS_DE_INSCRIPCION_O_ANOTACION : 0L)));

		return ll_idMotivo;
	}

	/**
	 * Cargar documentos notificaciones.
	 *
	 * @param adm_manager de adm manager
	 * @param as_idTurno de as id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected Collection<DocumentosSalida> cargarDocumentosNotificaciones(DAOManager adm_manager, String as_idTurno)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentos;

		lcds_documentos = null;

		if(adm_manager != null)
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				try
				{
					DocumentosSalidaDAO ldsd_DAO;

					ldsd_DAO            = DaoCreator.getDocumentosSalidaDAO(adm_manager);
					lcds_documentos     = new ArrayList<DocumentosSalida>();

					{
						DocumentosSalida lds_documentoTemp;

						lds_documentoTemp = ldsd_DAO.findByIdTurnoTipo(as_idTurno, TipoArchivoCommon.ACTA_NOTIFICACION);

						if(lds_documentoTemp != null)
							lcds_documentos.add(reemplazarTipoDocsSalida(lds_documentoTemp));
					}

					{
						DocumentosSalida lds_documentoTemp;

						lds_documentoTemp = ldsd_DAO.findByIdTurnoTipo(
							    as_idTurno, TipoArchivoCommon.ACTA_NOTIFICACION_CORREO
							);

						if(lds_documentoTemp != null)
							lcds_documentos.add(reemplazarTipoDocsSalida(lds_documentoTemp));
					}

					{
						DocumentosSalida lds_documentoTemp;

						lds_documentoTemp = ldsd_DAO.findByIdTurnoTipo(
							    as_idTurno, TipoArchivoCommon.ACTA_NOTIFICACION_PERSONAL
							);

						if(lds_documentoTemp != null)
							lcds_documentos.add(reemplazarTipoDocsSalida(lds_documentoTemp));
					}

					{
						DocumentosSalida lds_documentoTemp;

						lds_documentoTemp = ldsd_DAO.findByIdTurnoTipo(
							    as_idTurno, TipoArchivoCommon.CITACION_NOTIFICACION
							);

						if(lds_documentoTemp != null)
							lcds_documentos.add(reemplazarTipoDocsSalida(lds_documentoTemp));
					}

					{
						DocumentosSalida lds_documentoTemp;

						lds_documentoTemp = ldsd_DAO.findByIdTurnoTipo(as_idTurno, TipoArchivoCommon.DESFIJACION_AVISO);

						if(lds_documentoTemp != null)
							lcds_documentos.add(reemplazarTipoDocsSalida(lds_documentoTemp));
					}

					{
						DocumentosSalida lds_documentoTemp;

						lds_documentoTemp = ldsd_DAO.findByIdTurnoTipo(as_idTurno, TipoArchivoCommon.FIJACION_AVISO);

						if(lds_documentoTemp != null)
							lcds_documentos.add(reemplazarTipoDocsSalida(lds_documentoTemp));
					}

					{
						DocumentosSalida lds_documentoTemp;

						lds_documentoTemp = ldsd_DAO.findByIdTurnoTipo(
							    as_idTurno, TipoArchivoCommon.NOTIFICACION_AVISO
							);

						if(lds_documentoTemp != null)
							lcds_documentos.add(reemplazarTipoDocsSalida(lds_documentoTemp));
					}
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error("cargarDocumentosNotificaciones", lb2be_e);

					throw lb2be_e;
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
		}
		else
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		return lcds_documentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto long
	 * @param al_idAnotacion correspondiente al valor del tipo de objeto long
	 * @param ab_acc correspondiente al valor del tipo de objeto boolean
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected Collection<Anotacion> cargarIntervinientes(
	    String as_idCirculo, long al_idMatricula, long al_idAnotacion, boolean ab_acc, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<Anotacion>                lca_anotacionesIntervinientes;
		Collection<AnotacionPredioCiudadano> lca_intervinientes;

		lca_anotacionesIntervinientes     = new ArrayList<Anotacion>();
		lca_intervinientes                = new ArrayList<AnotacionPredioCiudadano>();

		if(ab_acc)
		{
			Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lca_intervinientesAcc;
			com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano             lapc_apc;
			lca_intervinientesAcc     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano>();
			lapc_apc                  = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano();

			lapc_apc.setIdCirculo(as_idCirculo);
			lapc_apc.setIdMatricula(al_idMatricula);
			lapc_apc.setIdAnotacion(al_idAnotacion);

			lca_intervinientesAcc = DaoCreator.getAccAnotacionPredioCiudadanoDAO(adm_manager)
					                              .findByCirculoMatricula(lapc_apc);

			if(CollectionUtils.isValidCollection(lca_intervinientesAcc))
			{
				for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lac_tmp : lca_intervinientesAcc)
				{
					if(lac_tmp != null)
						lca_intervinientes.add(new AnotacionPredioCiudadano(lac_tmp));
				}
			}
		}
		else
		{
			AnotacionPredioCiudadano lapc_apc;

			lapc_apc = new AnotacionPredioCiudadano();

			lapc_apc.setIdCirculo(as_idCirculo);
			lapc_apc.setIdMatricula(al_idMatricula);
			lapc_apc.setIdAnotacion(al_idAnotacion);

			lca_intervinientes = DaoCreator.getAnotacionPredioCiudadanoDAO(adm_manager).findByCirculoMatricula(
				    lapc_apc
				);
		}

		if(CollectionUtils.isValidCollection(lca_intervinientes))
		{
			long ll_contadorInterviniente;

			ll_contadorInterviniente = 1L;

			for(AnotacionPredioCiudadano lapc_actual : lca_intervinientes)
			{
				if(lapc_actual != null)
				{
					Anotacion la_Interviniente;

					la_Interviniente = new Anotacion();

					{
						Persona lp_persona;

						lp_persona = new Persona();

						lp_persona.setIdPersona(lapc_actual.getIdPersona());

						lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(lp_persona);

						if(lp_persona != null)
							la_Interviniente.setPersona(lp_persona);
					}

					{
						SolicitudInterviniente lsi_solicitudInterviniente;

						lsi_solicitudInterviniente = new SolicitudInterviniente();

						lsi_solicitudInterviniente.setRolPersona(lapc_actual.getRolPersona());

						la_Interviniente.setSolicitudInterviniente(lsi_solicitudInterviniente);
					}

					{
						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_ciudadano;
						ParteInteresada                                                lpi_data;

						lapc_ciudadano     = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano(
							    lapc_actual
							);
						lpi_data           = DaoCreator.getParteInteresadaDAO(adm_manager)
								                           .findById(lapc_actual.getIdInteresadaRrr());

						if(lpi_data != null)
							lapc_ciudadano.setNombreInteresadaRRR(lpi_data.getDescripcion());

						la_Interviniente.setAnotacionPredioCiudadano(lapc_ciudadano);
					}

					la_Interviniente.setIdAnotacion(lapc_actual.getIdAnotacion());
					la_Interviniente.setContadorInterviniente(ll_contadorInterviniente++);

					lca_anotacionesIntervinientes.add(la_Interviniente);
				}
			}
		}

		return lca_anotacionesIntervinientes;
	}

	/**
	 * Consultar anotaciones circulo matricula anotacion.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized Collection<AnotacionPredio> consultarAnotacionesCirculoMatricula(
	    String as_idCirculo, Long al_idMatricula, DAOManager adm_manager
	)
	    throws B2BException
	{
		return consultarAnotacionesCirculoMatriculaRRR(as_idCirculo, al_idMatricula, null, adm_manager);
	}

	/**
	 * Consultar anotaciones circulo matricula RRR.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param as_tipoRRR de as tipo RRR
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized Collection<AnotacionPredio> consultarAnotacionesCirculoMatriculaRRR(
	    String as_idCirculo, Long al_idMatricula, String as_tipoRRR, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<AnotacionPredio> lca_anotaciones;
		lca_anotaciones = new ArrayList<AnotacionPredio>();

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
				lca_anotaciones = DaoCreator.getAnotacionPredioDAO(adm_manager)
						                        .findByCirculoMatricula(as_idCirculo, al_idMatricula, true, as_tipoRRR);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAnotacionesCirculoMatriculaRRR", lb2be_e);
			throw lb2be_e;
		}

		return lca_anotaciones;
	}

	/**
	 * Método generico de consulta de catalogos conciliaciones.
	 *
	 * @param as_catalogo con el nombre del catalogo
	 * @param as_modulo con el modulo (por defecto SEDE_ELECTRONICA)
	 * @param as_parametro con el parametro de la consulta
	 * @param adm_manager the adm manager
	 * @return de TipoSalidaConsultarCatalogos con los datos de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized TipoSalidaConsultarCatalogos consultarCatalogos(
	    String as_catalogo, String as_modulo, String as_parametro, DAOManager adm_manager
	)
	    throws B2BException
	{
		TipoSalidaConsultarCatalogos ltscc_return;

		ltscc_return = null;

		try
		{
			if(StringUtils.isValidString(as_catalogo))
			{
				if(!StringUtils.isValidString(as_modulo))
					as_modulo = ConstanteCommon.MODULO_SEDE_ELECTRONICA;

				ltscc_return = ConsumoCatalogo.obtenerConsultaCatalogo(
					    as_catalogo, as_modulo, as_parametro,
					    DaoCreator.getConstantesDAO(adm_manager).findString(ConstanteCommon.CATALOGO_ENDPOINT)
					);
			}
			else
				throw new B2BException(
				    addErrorCMF(com.bachue.snr.prosnr21.common.constants.ErrorKeys.ERROR_CATALOGO_NO_VALIDO)
				);
		}
		catch(B2BException lb2be_e)
		{
			adm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarCatalogos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			adm_manager.close();
		}

		return ltscc_return;
	}

	/**
	 * Consulta la dirección de una matrícula.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param as_idMatricula id de la matrícula a consultar
	 * @param adm_manager Conexión a la base de datos
	 * @return Cadena de caracteres con la dirección resultante
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected String consultarDireccionCirculoMatricula(
	    String as_idCirculo, String as_idMatricula, DAOManager adm_manager
	)
	    throws B2BException
	{
		String ls_direccion;

		ls_direccion = null;

		if(StringUtils.isValidString(as_idCirculo) && StringUtils.isValidString(as_idMatricula))
		{
			DireccionPredioDAO ldpd_direccionPredioDAO;
			long               ll_idMatricula;
			String             ls_maxIdDireccion;
			DireccionPredio    ldp_direccionPredio;

			ldpd_direccionPredioDAO     = DaoCreator.getDireccionPredioDAO(adm_manager);
			ll_idMatricula              = NumericUtils.getLong(as_idMatricula);
			ls_maxIdDireccion           = ldpd_direccionPredioDAO.findMaxIdDireccion(as_idCirculo, ll_idMatricula);

			ldp_direccionPredio = ldpd_direccionPredioDAO.findByIdCirculoMatriculaIdDireccion(
				    as_idCirculo, ll_idMatricula, ls_maxIdDireccion
				);

			if(ldp_direccionPredio != null)
			{
				StringBuilder lsb_direccion;

				lsb_direccion = new StringBuilder();

				obtenerEjeDireccion(lsb_direccion, ldp_direccionPredio.getIdTipoEjePrincipal(), adm_manager);
				agregarTextoAStringBuilder(lsb_direccion, ldp_direccionPredio.getDatoEjePrincipal());
				obtenerEjeDireccion(lsb_direccion, ldp_direccionPredio.getIdTipoEjeSecundario(), adm_manager);
				agregarTextoAStringBuilder(lsb_direccion, ldp_direccionPredio.getDatoEjeSecundario());
				agregarTextoAStringBuilder(lsb_direccion, ldp_direccionPredio.getComplementoDireccion());

				ls_direccion = lsb_direccion.toString();
			}
		}

		return ls_direccion;
	}

	/**
	 * Método para realizar una búsqueda en el gestor documental por medio de WebService.
	 *
	 * @param adm_manager <code>DAOManager</code> que permite realizar la conexión a la base de datos.
	 * @param ado_parametrosBusqueda <code>DocumentoOWCC</code> que contiene los parametros para realizar la búsqueda.
	 * @param as_repositorio <code>String</code> que contiene el repositorio que se usará para el consumo del servicio.
	 * @return <code>Collection</code> llena de <code>DocumentoOWCC</code> resultantes de la invocación del WebService.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized Collection<DocumentoOWCC> consultarDocumentosOWCC(
	    DAOManager adm_manager, DocumentoOWCC ado_parametrosBusqueda, String as_repositorio
	)
	    throws B2BException
	{
		return consultarDocumentosOWCC(adm_manager, ado_parametrosBusqueda, as_repositorio, true);
	}

	/**
	 * Método para realizar una búsqueda en el gestor documental por medio de WebService.
	 *
	 * @param adm_manager <code>DAOManager</code> que permite realizar la conexión a la base de datos.
	 * @param ado_parametrosBusqueda <code>DocumentoOWCC</code> que contiene los parametros para realizar la búsqueda.
	 * @param as_repositorio <code>String</code> que contiene el repositorio que se usará para el consumo del servicio.
	 * @param ab_validarDatos <code>boolean</code> que determina si se debe validar si se encotraron registros en el OWCC
	 * <code>true</code> de lo contrario <code>false</code>
	 * @return <code>Collection</code> llena de <code>DocumentoOWCC</code> resultantes de la invocación del WebService.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized Collection<DocumentoOWCC> consultarDocumentosOWCC(
	    DAOManager adm_manager, DocumentoOWCC ado_parametrosBusqueda, String as_repositorio, boolean ab_validarDatos
	)
	    throws B2BException
	{
		Collection<DocumentoOWCC> lcdo_return;

		lcdo_return = null;

		try
		{
			if((ado_parametrosBusqueda != null) && StringUtils.isValidString(as_repositorio))
			{
				Constantes lc_constante;

				lc_constante     = null;

				lc_constante = DaoCreator.getConstantesDAO(adm_manager)
						                     .findById(ConstanteCommon.CONSULTA_DOCUMENTOS_OWCC_ENDPOINT);

				if(lc_constante != null)
				{
					boolean lb_conDocumentos;

					lb_conDocumentos = false;

					try
					{
						lcdo_return = ClienteConsultarDocumentos.consultarDocumentos(
							    SistemaOrigenCommon.CORE,
							    ParametrosDocumento.generarParametrosBusquedaOWCC(ado_parametrosBusqueda, true),
							    as_repositorio, lc_constante.getCaracter()
							);
					}
					catch(B2BException lb2be_e)
					{
						clh_LOGGER.error("consultarDocumentosOWCC", lb2be_e);

						if(ab_validarDatos)
							throw lb2be_e;
					}

					lb_conDocumentos = CollectionUtils.isValidCollection(lcdo_return);

					if(lb_conDocumentos)
					{
						TipoDocumentalDAO ltdd_DAO;

						ltdd_DAO = DaoCreator.getTipoDocumentalDAO(adm_manager);

						for(DocumentoOWCC ldo_iterador : lcdo_return)
						{
							if(ldo_iterador != null)
							{
								String ls_docType;

								ls_docType = ldo_iterador.getDocType();

								if(StringUtils.isValidString(ls_docType))
									ldo_iterador.setTipoDocumental(ltdd_DAO.findByTipologiasBachue(ls_docType));
							}
						}
					}
					else if(ab_validarDatos)
						throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDocumentosOWCC", lb2be_e);

			throw lb2be_e;
		}

		return lcdo_return;
	}

	/**
	 * Consultar estado predio.
	 *
	 * @param as_idEstadoPredio de as id estado predio
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String consultarEstadoPredio(String as_idEstadoPredio, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_estadoPredio;

		ls_estadoPredio = null;

		if(StringUtils.isValidString(as_idEstadoPredio))
		{
			EstadoPredio lep_estadoPredio;

			lep_estadoPredio = DaoCreator.getEstadoPredioDao(adm_manager).findById(as_idEstadoPredio);

			if(lep_estadoPredio != null)
				ls_estadoPredio = lep_estadoPredio.getNombre();
		}

		return ls_estadoPredio;
	}

	/**
	 * Consultar informacion matricula.
	 *
	 * @param ap_datos de ac datos
	 * @param adm_manager de adm manager
	 * @return el valor de predio registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected PredioRegistro consultarInformacionMatricula(Predio ap_datos, DAOManager adm_manager)
	    throws B2BException
	{
		return consultarInformacionMatricula(ap_datos, ProyectosCommon.CATASTRO_10, adm_manager);
	}

	/**
	 * Busca la información del predio con los datos ingresados.
	 *
	 * @param ap_datos Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param as_proyecto de as proyecto
	 * @param adm_manager Conexión a la base de datos
	 * @return PredioRegistro resultante de la consulta
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	protected PredioRegistro consultarInformacionMatricula(Predio ap_datos, String as_proyecto, DAOManager adm_manager)
	    throws B2BException
	{
		return consultarInformacionMatricula(ap_datos, as_proyecto, adm_manager, false);
	}

	/**
	 * Busca la información del predio con los datos ingresados.
	 *
	 * @param ap_datos Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param as_proyecto de as proyecto
	 * @param adm_manager Conexión a la base de datos
	 * @param ab_error448 the ab error 448
	 * @return PredioRegistro resultante de la consulta
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	protected PredioRegistro consultarInformacionMatricula(
	    Predio ap_datos, String as_proyecto, DAOManager adm_manager, boolean ab_error448
	)
	    throws B2BException
	{
		PredioRegistro lpr_return;

		lpr_return = null;

		if((ap_datos != null) && StringUtils.isValidString(as_proyecto))
		{
			PredioRegistroDAO lprd_predioRegistroDAO;

			lprd_predioRegistroDAO = DaoCreator.getPredioRegistroDAO(adm_manager);

			if(ap_datos.isFolioMatricula())
			{
				String           ls_idCirculo;
				CirculoRegistral lcr_circulo;

				ls_idCirculo     = ap_datos.getIdCirculo();
				lcr_circulo      = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo);

				if(lcr_circulo != null)
				{
					lpr_return = lprd_predioRegistroDAO.findByFolioMatricula(
						    ls_idCirculo, NumericUtils.getLong(ap_datos.getIdMatricula())
						);

					if(lpr_return == null)
					{
						switch(as_proyecto)
						{
							case ProyectosCommon.CATASTRO_10:
								throw new B2BException(
								    addErrorCTO(
								        ab_error448
								        ? com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_MATRICULA_SIN_RELACION
								        : com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO
								    )
								);

							case ProyectosCommon.COEXISTENCIA_14:
								throw new B2BException(
								    addErrorCX(
								        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CONSULTA_RESULTADOS
								    )
								);

							default:
								break;
						}
					}
				}
				else
				{
					switch(as_proyecto)
					{
						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_CIRCULO_REGISTRAL_NO_VALIDO_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_ORIP_INVALIDA)
							);

						default:
							break;
					}
				}
			}
			else if(ap_datos.isNupre())
			{
				lpr_return = lprd_predioRegistroDAO.findByNupre(ap_datos.getNumeroIdentificacionPredio());

				if(lpr_return == null)
				{
					switch(as_proyecto)
					{
						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        ab_error448
							        ? com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_MATRICULA_SIN_RELACION
							        : com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(
							        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CONSULTA_RESULTADOS
							    )
							);

						default:
							break;
					}
				}
			}
			else if(ap_datos.isNumeroPredial())
			{
				lpr_return = lprd_predioRegistroDAO.findByNumeroPredial(
					    ap_datos.getNumeroIdentificacionPredio(), false
					);

				if(lpr_return == null)
				{
					switch(as_proyecto)
					{
						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        ab_error448
							        ? com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_MATRICULA_SIN_RELACION
							        : com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(
							        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CONSULTA_RESULTADOS
							    )
							);

						default:
							break;
					}
				}
			}
			else if(ap_datos.isNumeroPredialAnterior())
			{
				lpr_return = lprd_predioRegistroDAO.findByNumeroPredial(ap_datos.getNumeroIdentificacionPredio());

				if(lpr_return == null)
				{
					switch(as_proyecto)
					{
						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        ab_error448
							        ? com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_MATRICULA_SIN_RELACION
							        : com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(
							        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CONSULTA_RESULTADOS
							    )
							);

						default:
							break;
					}
				}
			}
		}

		return lpr_return;
	}

	/**
	 * Corregir firma documento.
	 *
	 * @param aba_file de aba file
	 * @param as_plantilla de as plantilla
	 * @param adm_manager de adm manager
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized byte[] corregirFirmaDocumento(byte[] aba_file, String as_plantilla, DAOManager adm_manager)
	    throws B2BException
	{
		if((aba_file != null) && StringUtils.isValidString(as_plantilla))
		{
			try
			{
				boolean lb_estado;
				int     li_paginas;
				int     li_saltos;

				lb_estado     = false;
				li_saltos     = 0;

				{
					String ls_saltos;

					ls_saltos = as_plantilla;

					while(ls_saltos.indexOf(TagCommon.TAG_SALTO) > -1)
					{
						ls_saltos = ls_saltos.substring(
							    ls_saltos.indexOf(TagCommon.TAG_SALTO) + TagCommon.TAG_SALTO.length(),
							    ls_saltos.length()
							);
						li_saltos++;
					}
				}

				if(
				    as_plantilla.contains(TagCommon.TAG_INICIO_SECCION)
					    && as_plantilla.contains(TagCommon.TAG_FIN_SECCION)
				)
				{
					byte[]    lb_pagina;
					PdfReader lpr_pdfReader;
					int       li_salto;

					lb_pagina         = null;
					lpr_pdfReader     = null;
					li_salto          = 1;

					while(lb_estado != true)
					{
						lpr_pdfReader     = new PdfReader(aba_file);

						li_paginas = lpr_pdfReader.getNumberOfPages();

						for(int li_pagina = li_paginas; li_pagina >= 1; li_pagina--)
						{
							lb_pagina = lpr_pdfReader.getPageContent(li_pagina);

							if(lb_pagina != null)
							{
								String ls_pagina;

								ls_pagina = new String(lb_pagina);

								if(ls_pagina != null)
								{
									if(ls_pagina.contains(TagCommon.TAG_INICIO_SECCION))
									{
										if(ls_pagina.contains(TagCommon.TAG_FIN_SECCION))
										{
											li_pagina     = -1;
											lb_estado     = true;
										}
										else
										{
											as_plantilla     = as_plantilla.replace(
												    TagCommon.TAG_SALTO + li_salto + IdentificadoresCommon.MAYOR_QUE,
												    " \\par " + TagCommon.TAG_SALTO + li_salto
												    + IdentificadoresCommon.MAYOR_QUE
												);

											aba_file = new PDFGenerator().convertirRTFToPDF(
												    as_plantilla.getBytes(), adm_manager
												);

											li_salto++;
											li_pagina = -1;

											if(li_saltos == li_salto)
												li_salto = 0;
										}
									}
								}
							}
						}
					}

					lpr_pdfReader.close();
				}

				as_plantilla     = limpiarTags(as_plantilla);
				aba_file         = new PDFGenerator().convertirRTFToPDF(as_plantilla.getBytes(), adm_manager);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("corregirFirmaDocumento", lb2be_e);

				throw lb2be_e;
			}
			catch(IOException lioe_e)
			{
				B2BException lb2be_e;

				lb2be_e = new B2BException("corregirFirmaDocumento", lioe_e);

				clh_LOGGER.error("corregirFirmaDocumento", lb2be_e);

				throw lb2be_e;
			}
		}

		return aba_file;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @param adm_manager de adm manager
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	protected synchronized String crearNir(String as_userId, String as_remoteIp, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_nir;

		ls_nir = null;

		try
		{
			Constantes lc_constantes;
			String     ls_constante;
			int        li_anio;

			lc_constantes     = new Constantes();
			li_anio           = Calendar.getInstance().get(Calendar.YEAR);
			ls_constante      = "CONSECUTIVO_NIR_" + li_anio;

			{
				ConstantesDAO lcd_DAO;
				BigInteger    lbi_consecutivo;
				boolean       lb_insert;

				lcd_DAO           = DaoCreator.getConstantesDAO(adm_manager);
				lc_constantes     = lcd_DAO.findById(ls_constante);

				if(lc_constantes != null)
				{
					lb_insert           = false;
					lbi_consecutivo     = lc_constantes.getEntero();

					lc_constantes.setIdUsuarioModificacion(as_userId);
					lc_constantes.setIpModificacion(as_remoteIp);
				}
				else
				{
					lc_constantes       = new Constantes();
					lb_insert           = true;
					lbi_consecutivo     = null;

					lc_constantes.setIdConstante(ls_constante);
					lc_constantes.setIdUsuarioCreacion(as_userId);
					lc_constantes.setIpCreacion(as_remoteIp);
				}

				if(lbi_consecutivo == null)
					lbi_consecutivo = BigInteger.ZERO;

				lbi_consecutivo = lbi_consecutivo.add(BigInteger.ONE);

				lc_constantes.setEntero(lbi_consecutivo);

				lcd_DAO.insertOrUpdate(lc_constantes, lb_insert);

				{
					StringBuilder lsb_sb;

					lsb_sb = new StringBuilder();

					lsb_sb.append("SNR");
					lsb_sb.append(li_anio);
					lsb_sb.append(String.format("%09d", lbi_consecutivo));

					ls_nir = lsb_sb.toString();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("crearNir", lb2be_e);

			throw lb2be_e;
		}

		return ls_nir;
	}

	/**
	 * Construye en una cadena de caracteres la información correspondiente a una colección de anotaciones específica.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param acap_anotaciones Colección de anotaciones a verificar y escribir su información en la cadena de caracteres
	 * @param as_idCirculo Id del círculo al cual pertenecen las anotaciones
	 * @param al_idMatricula Id de la matrícula a la cual pertenecen las anotaciones
	 * @param ab_certificadoInmediato correspondiente al valor del tipo de objeto boolean
	 * @return Cadena de caracteres con la información encontrada para todas las anotaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirAnotaciones(
	    DAOManager adm_manager, Collection<AnotacionPredio> acap_anotaciones, String as_idCirculo, long al_idMatricula,
	    boolean ab_certificadoInmediato
	)
	    throws B2BException
	{
		return escribirAnotaciones(
		    adm_manager, acap_anotaciones, as_idCirculo, al_idMatricula, ab_certificadoInmediato, true, null
		);
	}

	/**
	 * Construye en una cadena de caracteres la información correspondiente a una colección de anotaciones específica.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param acap_anotaciones Colección de anotaciones a verificar y escribir su información en la cadena de caracteres
	 * @param as_idCirculo Id del círculo al cual pertenecen las anotaciones
	 * @param al_idMatricula Id de la matrícula a la cual pertenecen las anotaciones
	 * @param ab_certificadoInmediato correspondiente al valor del tipo de objeto boolean
	 * @param ab_bng true para realizar las consultas de bng, false para acc
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @return Cadena de caracteres con la información encontrada para todas las anotaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirAnotaciones(
	    DAOManager adm_manager, Collection<AnotacionPredio> acap_anotaciones, String as_idCirculo, long al_idMatricula,
	    boolean ab_certificadoInmediato, boolean ab_bng, String as_idTurno
	)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(CollectionUtils.isValidCollection(acap_anotaciones))
		{
			StringBuilder lsb_anotaciones;
			Integer       li_idAnotacion;
			int           li_totalAnotaciones;

			lsb_anotaciones         = new StringBuilder();
			li_idAnotacion          = null;
			li_totalAnotaciones     = 0;

			for(AnotacionPredio lap_iterador : acap_anotaciones)
			{
				if(lap_iterador != null)
				{
					BigDecimal lbd_bd;
					String     ls_grupoNaturaleza;

					lbd_bd                 = lap_iterador.getOrden();
					ls_grupoNaturaleza     = null;

					li_totalAnotaciones     = NumericUtils.getInt(lbd_bd);
					li_idAnotacion          = NumericUtils.getInteger(lap_iterador.getIdAnotacion());

					lsb_anotaciones.append("{\\pard ");

					if(ab_certificadoInmediato)
						lsb_anotaciones.append(
						    "________________________________________________________________________________________________"
						);
					else
						lsb_anotaciones.append(
						    "_______________________________________________________________________________________________________________________"
						);

					lsb_anotaciones.append("\\par}");

					lsb_anotaciones.append("{\\pard \\par}");

					lsb_anotaciones.append("{\\pard ");

					lsb_anotaciones.append(
					    "{\\b ANOTACIÓN: Nro. " + String.format("%03d", NumericUtils.getIntegerWrapper(lbd_bd))
					);
					lsb_anotaciones.append(
					    " Fecha: } " + StringUtils.getString(lap_iterador.getFechaRadicacion(), "dd-MM-yyyy")
					);
					lsb_anotaciones.append(" {\\b Radicación: }" + lap_iterador.getRadicacion());
					lsb_anotaciones.append("\\par}");

					lsb_anotaciones.append("{\\pard ");

					{
						Documento ld_documento;

						ld_documento = DaoCreator.getDocumentoDAO(adm_manager)
								                     .findByIdDocumentoVersion(
								    lap_iterador.getIdDocumento(),
								    NumericUtils.getLongWrapper(lap_iterador.getVersionDocumento())
								);

						if(ld_documento != null)
						{
							TipoDocumentoPublico ltdp_tipoDocPublico;

							ltdp_tipoDocPublico = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager)
									                            .findById(ld_documento.getIdTipoDocumento());

							if(ltdp_tipoDocPublico != null)
								lsb_anotaciones.append("Doc: " + ltdp_tipoDocPublico.getNombre());

							lsb_anotaciones.append(" " + ld_documento.getNumero() + " DE FECHA ");
							lsb_anotaciones.append(
							    StringUtils.getString(ld_documento.getFechaDocumento(), "dd-MM-yyyy")
							);

							{
								OficinaOrigen loo_oficinaOrigen;

								loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
										                          .findById(
										    ld_documento.getIdOficinaOrigen(), ld_documento.getVersion()
										);

								if(loo_oficinaOrigen != null)
								{
									String ls_pais;
									String ls_departamento;
									String ls_municipio;

									ls_pais             = loo_oficinaOrigen.getIdPais();
									ls_departamento     = loo_oficinaOrigen.getIdDepartamento();
									ls_municipio        = loo_oficinaOrigen.getIdMunicipio();

									lsb_anotaciones.append(" DE ");

									lsb_anotaciones.append(" " + loo_oficinaOrigen.getNombre());

									if(
									    StringUtils.isValidString(ls_pais)
										    && StringUtils.isValidString(ls_departamento)
										    && StringUtils.isValidString(ls_municipio)
									)
									{
										Municipio lm_municipio;

										lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
												                     .findById(ls_pais, ls_departamento, ls_municipio);

										if(lm_municipio != null)
											lsb_anotaciones.append(" " + lm_municipio.getNombre());
									}
								}
								else
								{
									String ls_comentario;

									ls_comentario = ld_documento.getComentario();

									if(StringUtils.isValidString(ls_comentario))
										lsb_anotaciones.append(" " + ls_comentario);
									else
										lsb_anotaciones.append(" " + ConstanteCommon.SIN_INFORMACION);
								}
							}
						}
					}

					{
						BigDecimal lbd_valor;

						lbd_valor = lap_iterador.getValor();

						lsb_anotaciones.append("{\\tab VALOR ACTO: $");

						if(lbd_valor != null)
						{
							NumberFormat lnf_numbreFormat;
							String       ls_valor;

							lnf_numbreFormat     = NumberFormat.getNumberInstance(Locale.getDefault());
							ls_valor             = lnf_numbreFormat.format(lbd_valor);

							lsb_anotaciones.append(ls_valor);
						}

						lsb_anotaciones.append("}");
					}

					lsb_anotaciones.append("\\par}");

					lsb_anotaciones.append("{\\pard ");

					{
						NaturalezaJuridica lnj_naturaleza;

						lnj_naturaleza = DaoCreator.getNaturalezaJuridicaDAO(adm_manager)
								                       .findById(
								    lap_iterador.getIdNaturalezaJuridica(), lap_iterador.getVersion()
								);

						if(lnj_naturaleza != null)
						{
							GrupoNaturalezaJuridica lgnj_grupoNatJur;

							lgnj_grupoNatJur = DaoCreator.getGrupoNaturalezaJuridicaDAO(adm_manager)
									                         .findById(lnj_naturaleza.getIdGrupoNatJur());

							if(lgnj_grupoNatJur != null)
							{
								ls_grupoNaturaleza = lgnj_grupoNatJur.getIdGrupoNatJuridica();

								lsb_anotaciones.append("ESPECIFICACIÓN: " + lgnj_grupoNatJur.getNombre() + ": ");
							}
							else
								lsb_anotaciones.append(ConstanteCommon.SIN_INFORMACION);

							{
								String ls_idNatJuridica;
								ls_idNatJuridica = lnj_naturaleza.getIdNaturalezaJuridica();

								if(StringUtils.isValidString(ls_idNatJuridica))
									lsb_anotaciones.append(ls_idNatJuridica + " ");
							}

							{
								String ls_nombreNatJuridica;
								ls_nombreNatJuridica = lnj_naturaleza.getNombre();

								if(StringUtils.isValidString(ls_nombreNatJuridica))
									lsb_anotaciones.append(ls_nombreNatJuridica);
							}
						}
					}

					lsb_anotaciones.append("\\par}");

					lsb_anotaciones.append("{\\pard ");
					lsb_anotaciones.append(
					    "{\\b COMENTARIO:} " + StringUtils.getStringNotEmpty(lap_iterador.getComentario())
					);
					lsb_anotaciones.append("\\par}");

					if(StringUtils.getStringNotNull(ls_grupoNaturaleza).equalsIgnoreCase("0700"))
					{
						StringBuilder                    lsb_cancelaciones;
						Collection<AnotacionCancelacion> lcac_data;

						lsb_cancelaciones     = new StringBuilder();
						lcac_data             = new LinkedList<AnotacionCancelacion>();

						if(ab_bng)
							lcac_data = DaoCreator.getBngAnotacionCancelacionDAO(adm_manager)
									                  .findByAnotaciones(
									    NumericUtils.getLong(li_idAnotacion), as_idCirculo, al_idMatricula
									);
						else
						{
							Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion> lcac_accAnotacionCancelacion;

							lcac_accAnotacionCancelacion = DaoCreator.getAnotacionCancelacionDAO(adm_manager)
									                                     .findByAnotaciones(
									    NumericUtils.getLong(li_idAnotacion), as_idCirculo, al_idMatricula, as_idTurno
									);

							if(CollectionUtils.isValidCollection(lcac_accAnotacionCancelacion))
							{
								for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion lac_tmp : lcac_accAnotacionCancelacion)
								{
									if(lac_tmp != null)
										lcac_data.add(new AnotacionCancelacion(lac_tmp));
								}
							}
						}

						if(CollectionUtils.isValidCollection(lcac_data))
						{
							for(AnotacionCancelacion loac_tmp : lcac_data)
								lsb_cancelaciones.append(loac_tmp.getIdAnotacion1() + ",");

							lsb_anotaciones.append("{\\pard ");
							lsb_anotaciones.append(
							    "{\\b  CANCELA LAS ANOTACIONES:} " + " " + lsb_cancelaciones.toString()
							);

							lsb_anotaciones.append("\\par}");
						}
					}

					lsb_anotaciones.append("{\\pard ");
					lsb_anotaciones.append(
					    "{\\b PERSONAS QUE INTERVINIENEN EN EL ACTO (X-Titular de derecho real de dominio, I-Titular de dominio incompleto)}"
					);

					lsb_anotaciones.append("\\par}");

					lsb_anotaciones.append("{\\pard \\par}");

					{
						Collection<AnotacionPredioCiudadano> lcapc_intervinientes;

						lcapc_intervinientes = new LinkedList<AnotacionPredioCiudadano>();

						if(ab_bng)
							lcapc_intervinientes = DaoCreator.getAnotacionPredioCiudadanoDAO(adm_manager)
									                             .findByCirculoMatricula(
									    as_idCirculo, al_idMatricula, NumericUtils.getLong(li_idAnotacion)
									);
						else
						{
							Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano> lcapc_accAnotacionPredioCiud;

							lcapc_accAnotacionPredioCiud = DaoCreator.getAccAnotacionPredioCiudadanoDAO(adm_manager)
									                                     .findByCirculoMatricula(
									    as_idCirculo, al_idMatricula, NumericUtils.getLong(li_idAnotacion)
									);

							if(CollectionUtils.isValidCollection(lcapc_accAnotacionPredioCiud))
							{
								for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano lapc_tmp : lcapc_accAnotacionPredioCiud)
								{
									if(lapc_tmp != null)
										lcapc_intervinientes.add(new AnotacionPredioCiudadano(lapc_tmp));
								}
							}
						}

						if(CollectionUtils.isValidCollection(lcapc_intervinientes))
						{
							String ls_infoInterviniente;
							String ls_titulosInterviniente;

							ls_titulosInterviniente     = "  \\trowd\\cellx800\\cellx4000\\cellx3000\\cellx1000\\cellx500\\cellx3000\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
								+ "Rol" + " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}" + "Nombre"
								+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}" + "Identificación"
								+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} " + "Propietario"
								+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}" + "%"
								+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}}" + "Calidad Interviniente"
								+ "\\cell\\row ";

							ls_titulosInterviniente = "{\\pard \\s3\\f1 \\b" + ls_titulosInterviniente + "}";

							lsb_anotaciones.append(ls_titulosInterviniente);

							lsb_anotaciones.append("{\\rtf1");

							for(AnotacionPredioCiudadano lapc_iterador : lcapc_intervinientes)
							{
								if(lapc_iterador != null)
								{
									String  ls_rol;
									String  ls_nombrePersona;
									String  ls_tipoDocumento;
									String  ls_numeroDocumento;
									String  ls_propietario;
									String  ls_porcentaje;
									String  ls_descripcion;
									Persona lp_persona;

									ls_rol                 = lapc_iterador.getRolPersona();
									ls_nombrePersona       = "";
									ls_tipoDocumento       = "";
									ls_numeroDocumento     = "";
									ls_descripcion         = "";

									lp_persona = DaoCreator.getPersonaDAO(adm_manager)
											                   .findById(lapc_iterador.getIdPersona());

									if(lp_persona != null)
									{
										ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

										String ls_tipoPersona;
										ls_tipoPersona = StringUtils.getStringNotNull(lp_persona.getIdTipoPersona());

										if(StringUtils.isValidString(ls_tipoDocumento))
										{
											if(
											    ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
												    && ls_tipoPersona.equals(EstadoCommon.J)
											)
												ls_nombrePersona = lp_persona.getRazonSocial();
											else
											{
												String ls_primerNombre;
												String ls_segundoNombre;
												String ls_primerApellido;
												String ls_segundoApellido;

												ls_primerNombre        = lp_persona.getPrimerNombre();
												ls_segundoNombre       = lp_persona.getSegundoNombre();
												ls_primerApellido      = lp_persona.getPrimerApellido();
												ls_segundoApellido     = lp_persona.getSegundoApellido();

												ls_primerNombre     = StringUtils.isValidString(ls_primerNombre)
													? ls_primerNombre : "";

												ls_segundoNombre     = StringUtils.isValidString(ls_segundoNombre)
													? (" " + ls_segundoNombre) : "";

												ls_primerApellido     = StringUtils.isValidString(ls_primerApellido)
													? (" " + ls_primerApellido) : "";

												ls_segundoApellido     = StringUtils.isValidString(ls_segundoApellido)
													? (" " + ls_segundoApellido) : "";

												ls_nombrePersona = ls_primerNombre + " " + ls_segundoNombre + " "
													+ ls_primerApellido + " " + ls_segundoApellido;

												if(
												    ls_tipoDocumento.equalsIgnoreCase("SE")
													    && ls_tipoPersona.equals("I")
												)
												{
													String ls_razonSocial;

													ls_razonSocial = lp_persona.getRazonSocial();

													ls_nombrePersona += (StringUtils.isValidString(ls_razonSocial)
														? (" " + ls_razonSocial) : "");
												}
											}

											ls_numeroDocumento = lp_persona.getNumeroDocumento();
										}
									}

									ls_propietario     = lapc_iterador.getPropietario();
									ls_porcentaje      = lapc_iterador.getPorcentajeParticipacion();

									{
										String ls_interesadaRrr;
										ls_interesadaRrr = lapc_iterador.getIdInteresadaRrr();

										if(StringUtils.isValidString(ls_interesadaRrr))
										{
											ParteInteresada lpi_parametros;

											lpi_parametros = DaoCreator.getParteInteresadaDAO(adm_manager)
													                       .findById(ls_interesadaRrr);

											if(lpi_parametros != null)
												ls_descripcion = lpi_parametros.getDescripcion();
										}
									}

									if(StringUtils.isValidString(ls_porcentaje))
										ls_porcentaje = ls_porcentaje + " %" + "   ";

									ls_infoInterviniente = " \\trowd\\cellx800\\cellx4000\\cellx3000\\cellx1000\\cellx500\\cellx3000\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
										+ ls_rol + "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
										+ StringUtils.getStringNotNull(ls_nombrePersona)
										+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} " + ls_tipoDocumento
										+ "  #" + ls_numeroDocumento
										+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
										+ StringUtils.getStringNotNull(ls_propietario)
										+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
										+ StringUtils.getStringNotNull(ls_porcentaje)
										+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
										+ StringUtils.getStringNotNull(ls_descripcion) + "\\cell\\row ";

									lsb_anotaciones.append(ls_infoInterviniente);
								}
							}

							lsb_anotaciones.append("}");
						}
					}
				}
			}

			lsb_anotaciones.append("{\\pard \\par}");
			lsb_anotaciones.append("{\\pard \\b");
			lsb_anotaciones.append(
			    " NRO TOTAL DE ANOTACIONES: *" + StringUtils.getString(NumericUtils.getDouble(li_totalAnotaciones))
			    + "*"
			);
			lsb_anotaciones.append("\\par}");

			ls_return = lsb_anotaciones.toString();
		}

		return ls_return;
	}

	/**
	 * Escribe la complementación de un predio.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idComplementacionPredio id de la complementación relacionada al predio
	 * @return Cadena de caracteres con la información hallada
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirComplementacionPredio(DAOManager adm_manager, String as_idComplementacionPredio)
	    throws B2BException
	{
		String ls_return;

		ls_return = ConstanteCommon.SIN_INFORMACION;

		if(StringUtils.isValidString(as_idComplementacionPredio))
		{
			ComplementacionPredio lcp_complementacionPredio;

			lcp_complementacionPredio = DaoCreator.getComplementacionPredioDAO(adm_manager)
					                                  .findById(as_idComplementacionPredio);

			if(lcp_complementacionPredio != null)
			{
				String ls_complementacion;

				ls_complementacion = lcp_complementacionPredio.getComplementacion();

				if(StringUtils.isValidString(ls_complementacion))
					ls_return = ls_complementacion;
			}
		}

		return ls_return;
	}

	/**
	 * Escribe la información de antiguo sistema de un predio.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idDatosAntSis id del predio a consultar
	 * @return Cadena de caracteres con la información consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirDatosAntSistema(DAOManager adm_manager, String as_idDatosAntSis)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_idDatosAntSis))
		{
			DatosAntSistema ldas_datosAntSistema;

			ldas_datosAntSistema = DaoCreator.getDatosAntSistemaDAO(adm_manager).findById(as_idDatosAntSis);

			if(ldas_datosAntSistema != null)
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder();

				{
					CirculoRegistral lcr_tmp;

					lcr_tmp = DaoCreator.getCirculoRegistralDAO(adm_manager)
							                .findById(ldas_datosAntSistema.getIdCirculo());

					if(lcr_tmp != null)
					{
						lsb_sb.append("Círculo registral: ");
						lsb_sb.append(lcr_tmp.getNombre());
					}
				}

				{
					PredioTipo lpt_tmp;

					lpt_tmp = DaoCreator.getPredioTipoDao(adm_manager).findById(ldas_datosAntSistema.getIdTipoPredio());

					if(lpt_tmp != null)
					{
						lsb_sb.append(" ");
						lsb_sb.append("Tipo predio: ");
						lsb_sb.append(lpt_tmp.getDescripcion());
					}
				}

				{
					String ls_nombrePredio;
					ls_nombrePredio = ldas_datosAntSistema.getNombrePredio();

					if(StringUtils.isValidString(ls_nombrePredio))
					{
						lsb_sb.append(" ");
						lsb_sb.append("Nombre del predio: ");
						lsb_sb.append(ls_nombrePredio);
					}
				}

				{
					String ls_pais;
					String ls_departamento;
					String ls_municipio;

					ls_pais             = ldas_datosAntSistema.getIdPais();
					ls_departamento     = ldas_datosAntSistema.getIdDepartamento();
					ls_municipio        = ldas_datosAntSistema.getIdMunicipio();

					if(
					    StringUtils.isValidString(ls_pais) && StringUtils.isValidString(ls_departamento)
						    && StringUtils.isValidString(ls_municipio)
					)
					{
						Pais         lp_pais;
						Departamento ld_departamento;
						Municipio    lm_municipio;

						lp_pais = DaoCreator.getPaisDAO(adm_manager).findById(ls_pais);

						if(lp_pais != null)
						{
							String ls_nombrePais;
							ls_nombrePais = lp_pais.getNombre();

							if(StringUtils.isValidString(ls_nombrePais))
							{
								lsb_sb.append(" ");
								lsb_sb.append("Pais: ");
								lsb_sb.append(ls_nombrePais);
							}
						}

						ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager).findById(ls_pais, ls_departamento);

						if(ld_departamento != null)
						{
							String ls_nombreDepartamento;
							ls_nombreDepartamento = ld_departamento.getNombre();

							if(StringUtils.isValidString(ls_nombreDepartamento))
							{
								lsb_sb.append(" ");
								lsb_sb.append("Departamento: ");
								lsb_sb.append(ls_nombreDepartamento);
							}
						}

						lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
								                     .findById(ls_pais, ls_departamento, ls_municipio);

						if(lm_municipio != null)
						{
							String ls_nombreMun;
							ls_nombreMun = lm_municipio.getNombre();

							if(StringUtils.isValidString(ls_nombreMun))
							{
								lsb_sb.append(" ");
								lsb_sb.append("Municipio: ");
								lsb_sb.append(ls_nombreMun);
							}
						}
					}
				}

				ls_return = lsb_sb.toString();
			}
		}

		return ls_return;
	}

	/**
	 * Escribe el departamento interesado y el municipio interesado en una plantilla.
	 *
	 * @param as_plantilla plantilla el departamento interesado y el municipio interesado escritos
	 * @param as_tagMunicipio de as tag municipio
	 * @param as_municipio contiene el municipio que será escrito en la plantilla
	 * @param as_tagDepartamento de as tag departamento
	 * @param as_departamento contiene el departamento que será reemplazado
	 * @return plantilla con los tags reemplazados
	 */
	protected String escribirDepartamentoMunicipioInteresado(
	    String as_plantilla, String as_tagMunicipio, String as_municipio, String as_tagDepartamento,
	    String as_departamento
	)
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			StringBuilder lsb_builder;
			StringBuilder lsb_sbSalto;

			lsb_builder     = new StringBuilder(as_departamento);
			lsb_sbSalto     = new StringBuilder(as_municipio);

			lsb_builder.append(IdentificadoresCommon.SIMBOLO_COMA);
			lsb_sbSalto.append(IdentificadoresCommon.SALTO_LINEA_RTF);
			as_plantilla     = as_plantilla.replace(as_tagDepartamento, lsb_builder.toString());
			as_plantilla     = as_plantilla.replace(as_tagMunicipio, lsb_sbSalto.toString());
		}

		return as_plantilla;
	}

	/**
	 * Escribe el departamento interesado y el municipio interesado en una plantilla.
	 *
	 * @param as_plantilla plantilla el departamento interesado y el municipio interesado escritos
	 * @param as_municipio contiene el municipio que será escrito en la plantilla
	 * @param as_tagDepmun de as tag depmun
	 * @param as_departamento contiene el departamento que será reemplazado
	 * @return plantilla con los tags reemplazados
	 */
	protected String escribirDepartamentoMunicipioInteresadoUnTag(
	    String as_plantilla, String as_municipio, String as_tagDepmun, String as_departamento
	)
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			StringBuilder lsb_builder;

			lsb_builder = new StringBuilder();

			if(StringUtils.isValidString(as_departamento))
				lsb_builder.append(as_departamento);

			if(StringUtils.isValidString(as_municipio))
			{
				lsb_builder.append(IdentificadoresCommon.SIMBOLO_COMA);
				lsb_builder.append(as_municipio);
			}

			lsb_builder.append(IdentificadoresCommon.SALTO_LINEA_RTF);

			as_plantilla = as_plantilla.replace(as_tagDepmun, lsb_builder.toString());
		}

		return as_plantilla;
	}

	/**
	 * Escribe los detalles de antiguo sistema para un predio.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idDatosAntSis id del predio a consultar
	 * @return Cadena de caracteres con la información consultada
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirDetalleAntSistema(DAOManager adm_manager, String as_idDatosAntSis)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_idDatosAntSis))
		{
			Collection<DetalleAntSistema> lcdas_detalleAntSistema;

			lcdas_detalleAntSistema = DaoCreator.getDetalleAntSistemaDAO(adm_manager).findByDatosAntSis(
				    as_idDatosAntSis
				);

			if(CollectionUtils.isValidCollection(lcdas_detalleAntSistema))
			{
				String        ls_table;
				StringBuilder lsb_sbf;

				ls_table     = " {\\rtf1\\ansi\\deff0\\trowd\\cellx1388\\cellx2884\\cellx4380\\cellx5876\\cellx7373\\cellx8870"
					+ "\\intbl\\fs15  " + "NOMBRE LIBRO" + "\\cell" + "\\intbl\\fs15  " + "TOMO" + "\\cell"
					+ "\\intbl\\fs15  " + "FOLIO" + " \\cell" + "\\intbl\\fs15  " + "PARTIDA" + "\\cell"
					+ "\\intbl\\fs15  " + "NÚMERO PARTIDA" + "\\cell" + "\\intbl\\fs15  " + "AÑO" + "\\cell\\row ";

				lsb_sbf = new StringBuilder(ls_table);

				for(DetalleAntSistema ldas_tmp : lcdas_detalleAntSistema)
				{
					if(ldas_tmp != null)
					{
						Long   ll_idLibro;
						Long   ll_numeroPartida;
						String ls_anio;
						String ls_folio;
						String ls_nombreLibro;
						String ls_numeroPartida;
						String ls_partida;
						String ls_tomo;

						ll_idLibro           = ldas_tmp.getIdLibroAntSistema();
						ll_numeroPartida     = ldas_tmp.getNumeroPartida();
						ls_anio              = ldas_tmp.getAnio();
						ls_folio             = ldas_tmp.getFolio();
						ls_nombreLibro       = null;
						ls_numeroPartida     = NumericUtils.isValidLong(ll_numeroPartida) ? ll_numeroPartida.toString()
							                                                              : null;
						ls_partida           = ldas_tmp.getPartida();
						ls_tomo              = ldas_tmp.getTomo();

						if(NumericUtils.isValidLong(ll_idLibro))
						{
							LibroAntiguoSistema llas_libro;

							llas_libro = new LibroAntiguoSistema();

							llas_libro.setIdLibroAntiguoSistema(NumericUtils.getLong(ll_idLibro));

							llas_libro = DaoCreator.getLibroAntiguoSistemaDAO(adm_manager).findById(llas_libro);

							if(llas_libro != null)
								ls_nombreLibro = llas_libro.getNombre();
						}

						if(!StringUtils.isValidString(ls_anio))
							ls_anio = IdentificadoresCommon.SIN_INFORMACION;

						if(!StringUtils.isValidString(ls_folio))
							ls_folio = IdentificadoresCommon.SIN_INFORMACION;

						if(!StringUtils.isValidString(ls_nombreLibro))
							ls_nombreLibro = IdentificadoresCommon.SIN_INFORMACION;

						if(!StringUtils.isValidString(ls_numeroPartida))
							ls_numeroPartida = IdentificadoresCommon.SIN_INFORMACION;

						if(!StringUtils.isValidString(ls_partida))
							ls_partida = IdentificadoresCommon.SIN_INFORMACION;

						if(!StringUtils.isValidString(ls_tomo))
							ls_tomo = IdentificadoresCommon.SIN_INFORMACION;

						ls_table = " \\trowd\\cellx1388\\cellx2884\\cellx4380\\cellx5876\\cellx7373\\cellx8870\\intbl\\fs15 "
							+ ls_nombreLibro + "\\cell\\intbl\\fs15  " + ls_tomo + "\\cell\\intbl\\fs15 " + ls_folio
							+ " \\cell\\intbl\\fs15  " + ls_partida + "\\cell\\intbl\\fs15  " + ls_numeroPartida
							+ "\\cell\\intbl\\fs15 " + ls_anio + "\\cell\\row ";

						lsb_sbf.append(ls_table);
					}
				}

				lsb_sbf.append(IdentificadoresCommon.CERRAR_LLAVE);

				ls_return = lsb_sbf.toString();
			}
		}

		return ls_return;
	}

	/**
	 * Construye en una cadena de caracteres la información correspondiente a una colección de direcciones específica.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param acdp_direcciones Colección de direcciones a veríficar y escribir la información
	 * @return Cadena de caracteres con la información de las direcciones
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirDireccionesPredio(DAOManager adm_manager, Collection<DireccionPredio> acdp_direcciones)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(CollectionUtils.isValidCollection(acdp_direcciones))
		{
			StringBuilder lsb_direcciones;

			lsb_direcciones = new StringBuilder();

			for(DireccionPredio ldp_iterador : acdp_direcciones)
			{
				if(ldp_iterador != null)
				{
					lsb_direcciones.append("{\\pard ");
					lsb_direcciones.append(ldp_iterador.getIdDireccion() + ") ");
					lsb_direcciones.append(ldp_iterador.getDireccion());
					lsb_direcciones.append("\\par}");
				}
			}

			ls_return = lsb_direcciones.toString();
		}

		return ls_return;
	}

	/**
	 * Permite registrar un error en tiempo de ejecución en la base de datos.
	 *
	 * @param abpd_bitacoraProcesoDAO Conexión a la tabla bitacora proceso
	 * @param as_proceso Nombre o identificador del proceso donde ocurrió el error
	 * @param as_descripcion Causal del error
	 * @param as_userId Id del usuario que ejecutó la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecutó la acción
	 * @param as_llave1 Identificador del caso en el cual ocurrió el error
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void escribirEnBitacoraProceso(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, String as_proceso, String as_descripcion, String as_userId,
	    String as_remoteIp, String as_llave1
	)
	    throws B2BException
	{
		if(
		    (abpd_bitacoraProcesoDAO != null) && StringUtils.isValidString(as_proceso)
			    && StringUtils.isValidString(as_descripcion) && StringUtils.isValidString(as_userId)
		)
		{
			BitacoraProceso lbp_bitacora;

			lbp_bitacora = new BitacoraProceso();

			lbp_bitacora.setProceso(as_proceso);
			lbp_bitacora.setDescripcion(as_descripcion);
			lbp_bitacora.setLlave1(as_llave1);
			lbp_bitacora.setIdUsuarioCreacion(as_userId);
			lbp_bitacora.setIpCreacion(as_remoteIp);

			abpd_bitacoraProcesoDAO.insert(lbp_bitacora);
		}
	}

	/**
	 * Escribe en la plantilla la información del círculo registral.
	 *
	 * @param acr_circulo correspondiente al valor del tipo de objeto CirculoRegistral
	 * @param as_plantilla Cadena de caracteres contenedora de la plantilla
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirInfoCirculoRegistral(CirculoRegistral acr_circulo, String as_plantilla)
	    throws B2BException
	{
		if(acr_circulo == null)
			throw new B2BException(ErrorKeys.ERROR_CIRCULO_NO_EXISTE);

		if(!StringUtils.isValidString(as_plantilla))
			throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

		String ls_tipoOficina;
		String ls_tag;

		ls_tipoOficina = acr_circulo.getTipoOficina();

		if(StringUtils.isValidString(ls_tipoOficina))
		{
			if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
				ls_tipoOficina = "PRINCIPAL";
			else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
				ls_tipoOficina = "SECCIONAL";

			ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

			if(as_plantilla.contains(ls_tag))
				as_plantilla = as_plantilla.replace(ls_tag, ls_tipoOficina);
		}

		ls_tag = "<TAG_NOMBRE_CIRCULO_REGISTRAL>";

		if(as_plantilla.contains(ls_tag))
		{
			String ls_destinatario;
			ls_destinatario = acr_circulo.getNombre();

			if(StringUtils.isValidString(ls_destinatario))
				as_plantilla = as_plantilla.replace(ls_tag, ls_destinatario);
		}

		return as_plantilla;
	}

	/**
	 * Construye una cadena de caracteres con la información de las matrículas segregadas.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param acps_predioSegregado Coleccion de predios segregados para extraer los datos de estos
	 * @param ab_conDireccion true para indicar si que se debe relacionar la dirección de cada predio segregado
	 * @return cadena de caracteres con la información encontrada
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirMatriculasSegregadas(
	    DAOManager adm_manager, Collection<PredioSegregado> acps_predioSegregado, boolean ab_conDireccion
	)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(CollectionUtils.isValidCollection(acps_predioSegregado))
		{
			StringBuilder      lsb_sb;
			DireccionPredioDAO ldpd_direccionPredioDAO;

			lsb_sb                      = new StringBuilder();
			ldpd_direccionPredioDAO     = DaoCreator.getDireccionPredioDAO(adm_manager);

			for(PredioSegregado lps_iterador : acps_predioSegregado)
			{
				if(lps_iterador != null)
				{
					String ls_idCirculo;
					long   ll_idMatricula;

					ls_idCirculo       = lps_iterador.getIdCirculo1();
					ll_idMatricula     = NumericUtils.getLong(lps_iterador.getIdMatricula1());

					lsb_sb.append(ls_idCirculo);
					lsb_sb.append(" - ");
					lsb_sb.append(ll_idMatricula);

					if(ab_conDireccion)
					{
						DireccionPredio ldp_direccion;
						String          ls_maxIdDireccion;

						ldp_direccion = new DireccionPredio();

						ldp_direccion.setIdCirculo(ls_idCirculo);
						ldp_direccion.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

						ls_maxIdDireccion = ldpd_direccionPredioDAO.findMaxIdDireccion(ldp_direccion);

						if(StringUtils.isValidString(ls_maxIdDireccion))
						{
							ldp_direccion.setIdDireccion(ls_maxIdDireccion);

							Collection<DireccionPredio> lcdp_direcciones;

							lcdp_direcciones = ldpd_direccionPredioDAO.findByCirculoMatriculaIdDireccion(
								    ldp_direccion, true
								);

							if(CollectionUtils.isValidCollection(lcdp_direcciones))
							{
								String ls_direccion;

								ls_direccion = escribirDireccionesPredio(adm_manager, lcdp_direcciones);

								if(StringUtils.isValidString(ls_direccion))
								{
									lsb_sb.append(", ");
									lsb_sb.append(ls_direccion);
								}
							}
						}
					}

					lsb_sb.append("{\\pard \\par}");
				}
			}

			ls_return = lsb_sb.toString();
		}

		return ls_return;
	}

	/**
	 * Escribe la fecha y hora actual en el documento.
	 *
	 * @param as_plantilla Cadena de caracteres contenedora de la plantilla
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String escribirTagFechaHora(String as_plantilla)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_plantilla))
			throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

		String ls_tag;

		ls_tag = "<TAG_FECHA_HORA>";

		if(as_plantilla.contains(ls_tag))
			as_plantilla = as_plantilla.replace(ls_tag, StringUtils.getString(new Date(), "dd/MM/yyyy HH:mm"));

		return as_plantilla;
	}

	/**
	 * Escribe la fecha larga en una plantilla.
	 *
	 * @param as_plantilla plantilla con la fecha larga escrita
	 * @return devuelve el valor de String
	 *
	 */
	protected String escribirTagFechaLarga(String as_plantilla)
	{
		return escribirTagFechaLarga(as_plantilla, null, null);
	}

	/**
	 * Escribe la fecha larga en una plantilla.
	 *
	 * @param as_plantilla plantilla con la fecha larga escrita
	 * @param as_tag tag a reemplazar en la plantilla
	 * @param ad_date fecha a colocar en la plantilla
	 * @return devuelve el valor de String
	 */
	protected String escribirTagFechaLarga(String as_plantilla, String as_tag, Date ad_date)
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			String ls_tagFechaLarga;

			ls_tagFechaLarga = StringUtils.isValidString(as_tag) ? as_tag : "<TAG_FECHA_LARGA>";

			if(as_plantilla.contains(ls_tagFechaLarga))
			{
				Date   ld_fecha;
				String ls_fechaActual;

				ld_fecha           = (ad_date != null) ? ad_date : new Date();
				ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

				if(StringUtils.isValidString(ls_fechaActual))
					as_plantilla = as_plantilla.replace(ls_tagFechaLarga, ls_fechaActual.toUpperCase());
			}
		}

		return as_plantilla;
	}

	/**
	 * Elimina de una plantilla todos los tags que no se hayan resuelto y remplaza caracteres UTF8.
	 *
	 * @param as_plantilla Cadena de caracteres con la información de la plantilla
	 * @param al_idTurnoHistoria correspondiente al valor del tipo de objeto Long
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	protected static Map<String, String> finalizarPlantilla(
	    String as_plantilla, Long al_idTurnoHistoria, DAOManager adm_manager
	)
	    throws B2BException
	{
		return finalizarPlantilla(as_plantilla, al_idTurnoHistoria, true, adm_manager);
	}

	/**
	 * Elimina de una plantilla todos los tags que no se hayan resuelto y remplaza caracteres UTF8.
	 *
	 * @param as_plantilla Cadena de caracteres con la información de la plantilla
	 * @param al_idTurnoHistoria correspondiente al valor del tipo de objeto Long
	 * @param ab_definitivo de ab definitivo
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	protected static Map<String, String> finalizarPlantilla(
	    String as_plantilla, Long al_idTurnoHistoria, boolean ab_definitivo, DAOManager adm_manager
	)
	    throws B2BException
	{
		Map<String, String> lmss_return;

		lmss_return = new HashMap<String, String>();

		if(StringUtils.isValidString(as_plantilla))
		{
			String ls_codigo;
			String ls_tag;

			ls_codigo     = null;
			ls_tag        = "<TAG_CODIGO_VERIFICACION>";

			if(as_plantilla.contains(ls_tag) && NumericUtils.isValidLong(al_idTurnoHistoria))
			{
				ls_codigo = DaoCreator.getImagenesDAO(adm_manager).generarCodigoConvenio(al_idTurnoHistoria);

				if(StringUtils.isValidString(ls_codigo))
					as_plantilla = as_plantilla.replace(ls_tag, ls_codigo);
			}

			as_plantilla = StringUtils.reemplazarCaracteresUTF8(as_plantilla);

			if(ab_definitivo)
				as_plantilla = limpiarTags(as_plantilla);

			lmss_return.put(IdentificadoresCommon.CODIGO_VERIFICACION, ls_codigo);
		}

		lmss_return.put(IdentificadoresCommon.PLANTILLA, as_plantilla);

		return lmss_return;
	}

	/**
	 * Método encargado de limpiar los tags de la plantilla que no se lograron resolver.
	 *
	 * @param as_plantilla Variable que contiene el texto a limpiar.
	 * @return Texto sin tags.
	 */
	protected static String limpiarTags(String as_plantilla)
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			while(as_plantilla.contains("<") && as_plantilla.contains(">"))
			{
				int    li_inicioTag;
				int    li_finalTag;
				String ls_tagSinCerrar;

				li_inicioTag        = as_plantilla.indexOf("<");
				li_finalTag         = as_plantilla.substring(li_inicioTag).indexOf(">");
				li_finalTag         = li_finalTag + li_inicioTag + 1;
				ls_tagSinCerrar     = as_plantilla.substring(li_inicioTag, li_finalTag);

				if(StringUtils.isValidString(ls_tagSinCerrar))
					as_plantilla = as_plantilla.replace(ls_tagSinCerrar, "");
			}
		}

		return as_plantilla;
	}

	/**
	 * Método de sobrecarga obtención del valor actual de la Numeracion de Resolucion Circulo.
	 *
	 * @param ath_th Turno historia actual
	 * @param adm_manager con el manejo de la transacción
	 * @param as_userId con el usuario  que realiza la petición
	 * @param as_remoteIp con la dirección ip del usuario de la peticion
	 * @return de tipo Numeracion Resolucion Circulo con el valor de la solicitud.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized NumeracionOficiosCirculo findNumeracionOficiosCirculo(
	    TurnoHistoria ath_th, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		return findNumeracionOficiosCirculo(ath_th, adm_manager, as_userId, as_remoteIp, false);
	}

	/**
	 * Método de obtención del valor actual de la Numeracion de Resolucion Circulo.
	 *
	 * @param ath_th Turno historia actual
	 * @param adm_manager con el manejo de la transacción
	 * @param as_userId con el usuario  que realiza la petición
	 * @param as_remoteIp con la dirección ip del usuario de la peticion
	 * @param ab_mayorValor de ab mayor valor
	 * @return de tipo Numeracion Resolucion Circulo con el valor de la solicitud.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized NumeracionOficiosCirculo findNumeracionOficiosCirculo(
	    TurnoHistoria ath_th, DAOManager adm_manager, String as_userId, String as_remoteIp, boolean ab_mayorValor
	)
	    throws B2BException
	{
		return findNumeracionOficiosCirculo(ath_th, adm_manager, as_userId, as_remoteIp, ab_mayorValor, false);
	}

	/**
	 * Método de obtención del valor actual de la Numeracion de Resolucion Circulo.
	 *
	 * @param ath_th Turno historia actual
	 * @param adm_manager con el manejo de la transacción
	 * @param as_userId con el usuario  que realiza la petición
	 * @param as_remoteIp con la dirección ip del usuario de la peticion
	 * @param ab_mayorValor de ab mayor valor
	 * @param ab_dependenciaSAJR correspondiente al valor de ab dependencia SAJR
	 * @return de tipo Numeracion Resolucion Circulo con el valor de la solicitud.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized NumeracionOficiosCirculo findNumeracionOficiosCirculo(
	    TurnoHistoria ath_th, DAOManager adm_manager, String as_userId, String as_remoteIp, boolean ab_mayorValor,
	    boolean ab_dependenciaSAJR
	)
	    throws B2BException
	{
		NumeracionOficiosCirculo lnoc_NumeracionOficiosCirculo;

		lnoc_NumeracionOficiosCirculo = null;

		try
		{
			if(ath_th != null)
			{
				NumeracionOficiosCirculo ltnoc_tempNumeracionOficioCirculo;
				String                   ls_idCirculo;
				String                   ls_date;

				ltnoc_tempNumeracionOficioCirculo     = new NumeracionOficiosCirculo();
				ls_idCirculo                          = null;

				{
					Turno lt_turno;

					lt_turno         = DaoCreator.getTurnoDAO(adm_manager).findById(ath_th.getIdTurno());
					ls_idCirculo     = ((lt_turno != null) && !ab_mayorValor) ? lt_turno.getIdCirculo() : "SNR";
				}

				{
					SimpleDateFormat lsdf_formatter;

					lsdf_formatter     = new SimpleDateFormat("yyyy");

					ls_date = StringUtils.getStringNotNull(lsdf_formatter.format(new Date()));
				}

				if(StringUtils.isValidString(ls_idCirculo))
				{
					String ls_dependencia;
					String ls_consecutivoGenerado;

					ltnoc_tempNumeracionOficioCirculo.setAnoVigente(ls_date);
					ltnoc_tempNumeracionOficioCirculo.setIdCirculo(ls_idCirculo);
					ltnoc_tempNumeracionOficioCirculo.setIdDependencia(ab_dependenciaSAJR ? "SAJR" : "DR");
					lnoc_NumeracionOficiosCirculo         = DaoCreator.getNumeracionOficiosCirculoDAO(adm_manager)
							                                              .findById(ltnoc_tempNumeracionOficioCirculo);
					ltnoc_tempNumeracionOficioCirculo     = null;

					if(lnoc_NumeracionOficiosCirculo != null)
					{
						ltnoc_tempNumeracionOficioCirculo = lnoc_NumeracionOficiosCirculo;
						lnoc_NumeracionOficiosCirculo.setConsecutivoOficio(
						    NumericUtils.getLongWrapper(
						        NumericUtils.getLong(lnoc_NumeracionOficiosCirculo.getConsecutivoOficio()) + 1L
						    )
						);
						ls_dependencia             = lnoc_NumeracionOficiosCirculo.getIdDependencia();
						ls_consecutivoGenerado     = String.format(
							    "%06d", lnoc_NumeracionOficiosCirculo.getConsecutivoOficio()
							);
						lnoc_NumeracionOficiosCirculo.setConsecutivoGenerado(ls_dependencia + ls_consecutivoGenerado);

						lnoc_NumeracionOficiosCirculo.setIdUsuarioModificacion(as_userId);
						lnoc_NumeracionOficiosCirculo.setIpModificacion(as_remoteIp);
						DaoCreator.getNumeracionOficiosCirculoDAO(adm_manager).update(lnoc_NumeracionOficiosCirculo);
					}
					else
					{
						lnoc_NumeracionOficiosCirculo = new NumeracionOficiosCirculo();
						lnoc_NumeracionOficiosCirculo.setIdCirculo(ls_idCirculo);
						lnoc_NumeracionOficiosCirculo.setAnoVigente(ls_date);
						lnoc_NumeracionOficiosCirculo.setConsecutivoOficio(NumericUtils.getLongWrapper(1L));
						lnoc_NumeracionOficiosCirculo.setActivo("S");
						lnoc_NumeracionOficiosCirculo.setIdDependencia(ab_dependenciaSAJR ? "SAJR" : "DR");
						lnoc_NumeracionOficiosCirculo.setIdUsuarioCreacion(as_userId);
						lnoc_NumeracionOficiosCirculo.setIpCreacion(as_remoteIp);
						ls_dependencia             = lnoc_NumeracionOficiosCirculo.getIdDependencia();
						ls_consecutivoGenerado     = String.format(
							    "%06d", lnoc_NumeracionOficiosCirculo.getConsecutivoOficio()
							);
						lnoc_NumeracionOficiosCirculo.setConsecutivoGenerado(ls_dependencia + ls_consecutivoGenerado);
						DaoCreator.getNumeracionOficiosCirculoDAO(adm_manager).insert(lnoc_NumeracionOficiosCirculo);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findNumeracionResolucionCirculo", lb2be_e);
			throw lb2be_e;
		}

		return lnoc_NumeracionOficiosCirculo;
	}

	/**
	 * Método de obtención del valor actual de la Numeracion de Resolucion Circulo.
	 *
	 * @param ath_th Turno historia actual
	 * @param adm_manager con el manejo de la transacción
	 * @param as_userId con el usuario  que realiza la petición
	 * @param as_remoteIp con la dirección ip del usuario de la peticion
	 * @return de tipo Numeracion Resolucion Circulo con el valor de la solicitud.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized NumeracionResolucionCirculo findNumeracionResolucionCirculo(
	    TurnoHistoria ath_th, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		NumeracionResolucionCirculo lnrc_numeracionResolucionCirculo;
		lnrc_numeracionResolucionCirculo = null;

		try
		{
			if(ath_th != null)
			{
				NumeracionResolucionCirculo ltnrc_tempNumeracionResolucionCirculo;
				Date                        ld_date      = new Date();
				SimpleDateFormat            formatter    = new SimpleDateFormat("yyyy");
				String                      ls_idCirculo;
				String                      ls_date      = StringUtils.getStringNotNull(formatter.format(ld_date));

				ltnrc_tempNumeracionResolucionCirculo    = new NumeracionResolucionCirculo();
				ls_idCirculo                             = ath_th.getIdCirculoUsuario();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					ltnrc_tempNumeracionResolucionCirculo.setAnoVigente(ls_date);
					ltnrc_tempNumeracionResolucionCirculo.setIdCirculo(ls_idCirculo);
					lnrc_numeracionResolucionCirculo          = DaoCreator.getNumeracionResolucionCirculoDAO(
						    adm_manager
						).findById(ltnrc_tempNumeracionResolucionCirculo);
					ltnrc_tempNumeracionResolucionCirculo     = null;

					if(lnrc_numeracionResolucionCirculo != null)
					{
						ltnrc_tempNumeracionResolucionCirculo = lnrc_numeracionResolucionCirculo;
						lnrc_numeracionResolucionCirculo.setConsecutivoResolucion(
						    NumericUtils.getLongWrapper(
						        NumericUtils.getLong(lnrc_numeracionResolucionCirculo.getConsecutivoResolucion()) + 1L
						    )
						);
						lnrc_numeracionResolucionCirculo.setIdUsuarioModificacion(as_userId);
						lnrc_numeracionResolucionCirculo.setIpModificacion(as_remoteIp);
						lnrc_numeracionResolucionCirculo.setConsecutivogenerado(
						    String.format("%06d", lnrc_numeracionResolucionCirculo.getConsecutivoResolucion())
						);
						DaoCreator.getNumeracionResolucionCirculoDAO(adm_manager)
							          .update(lnrc_numeracionResolucionCirculo);
					}
					else
					{
						lnrc_numeracionResolucionCirculo = new NumeracionResolucionCirculo();
						lnrc_numeracionResolucionCirculo.setIdCirculo(ls_idCirculo);
						lnrc_numeracionResolucionCirculo.setAnoVigente(ls_date);
						lnrc_numeracionResolucionCirculo.setConsecutivoResolucion(NumericUtils.getLongWrapper(1L));
						lnrc_numeracionResolucionCirculo.setConsecutivogenerado(
						    String.format("%06d", lnrc_numeracionResolucionCirculo.getConsecutivoResolucion())
						);
						lnrc_numeracionResolucionCirculo.setActivo("S");
						lnrc_numeracionResolucionCirculo.setIdUsuarioCreacion(as_userId);
						lnrc_numeracionResolucionCirculo.setIpCreacion(as_remoteIp);
						DaoCreator.getNumeracionResolucionCirculoDAO(adm_manager)
							          .insert(lnrc_numeracionResolucionCirculo);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findNumeracionResolucionCirculo", lb2be_e);
			throw lb2be_e;
		}

		return lnrc_numeracionResolucionCirculo;
	}

	/**
	 * Retorna el valor del objeto de Map.
	 *
	 * @param as_idConstante correspondiente al valor del tipo de objeto String
	 * @param adm_dm correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	protected Map<String, String> generarCodigos(String as_idConstante, DAOManager adm_dm)
	    throws B2BException
	{
		Map<String, String> lmss_mss;

		lmss_mss = new HashMap<String, String>();

		try
		{
			if(as_idConstante != null)
			{
				Constantes lc_constante;

				lc_constante = new Constantes();

				lc_constante.setIdConstante(as_idConstante);

				lc_constante = DaoCreator.getConstantesDAO(adm_dm).findById(lc_constante);

				if(lc_constante != null)
				{
					String ls_caracter;

					ls_caracter = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_caracter))
						lmss_mss = ListadoCodigosConstantes.generarCodigos(ls_caracter);
				}
			}
		}
		catch(B2BException lb2b_b2b)
		{
			throw lb2b_b2b;
		}

		return lmss_mss;
	}

	/**
	 * Construye el documento pdf de aprobación o rechazo de solicitud de cuenta cupo.
	 *
	 * @param ath_turnoHistoria id de la solicitud asociada al proceso
	 * @param as_solicitud de as solicitud
	 * @param adp_direccion de adp direccion
	 * @param ads_documento de ads documento
	 * @param as_nombrePlantilla nombre de la plantilla a generar
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	protected synchronized void generarDocumentoCitatorio(
	    TurnoHistoria ath_turnoHistoria, Solicitud as_solicitud, DireccionPredio adp_direccion,
	    DocumentosSalida ads_documento, String as_nombrePlantilla, String as_userId, String as_remoteIp,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		generarDocumentoCitatorio(
		    ath_turnoHistoria, as_solicitud, adp_direccion, ads_documento, as_nombrePlantilla, as_userId, as_remoteIp,
		    adm_manager, null
		);
	}

	/**
	 * Generar documento citatorio.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_solicitud de as solicitud
	 * @param adp_direccion de adp direccion
	 * @param ads_documento de ads documento
	 * @param as_nombrePlantilla de as nombre plantilla
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @param aofd_param de aofd param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized void generarDocumentoCitatorio(
	    TurnoHistoria ath_turnoHistoria, Solicitud as_solicitud, DireccionPredio adp_direccion,
	    DocumentosSalida ads_documento, String as_nombrePlantilla, String as_userId, String as_remoteIp,
	    DAOManager adm_manager, ObtenerFirmaDTO aofd_param
	)
	    throws B2BException
	{
		generarDocumentoCitatorio(
		    ath_turnoHistoria, as_solicitud, adp_direccion, ads_documento, as_nombrePlantilla, as_userId, as_remoteIp,
		    adm_manager, aofd_param, false
		);
	}

	/**
	 * Generar documento citatorio.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_solicitud de as solicitud
	 * @param adp_direccion de adp direccion
	 * @param ads_documento de ads documento
	 * @param as_nombrePlantilla de as nombre plantilla
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @param aofd_param de aofd param
	 * @param ab_proceso48 the ab proceso 48
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized void generarDocumentoCitatorio(
	    TurnoHistoria ath_turnoHistoria, Solicitud as_solicitud, DireccionPredio adp_direccion,
	    DocumentosSalida ads_documento, String as_nombrePlantilla, String as_userId, String as_remoteIp,
	    DAOManager adm_manager, ObtenerFirmaDTO aofd_param, boolean ab_proceso48
	)
	    throws B2BException
	{
		if(ath_turnoHistoria == null)
			throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

		if(as_solicitud == null)
			throw new B2BException(ErrorKeys.ERROR_NO_ID_SOLICITUD);

		if(ads_documento == null)
			throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE);

		String        ls_plantilla;
		boolean       lb_reemplazarFirma;
		ConstantesDAO lccd_DAO;
		String        ls_idPersona;
		String        ls_idDocumento;

		ls_idPersona           = as_solicitud.getIdPersona();
		ls_plantilla           = obtenerPlantillaDeConstante(adm_manager, as_nombrePlantilla);
		lb_reemplazarFirma     = aofd_param != null;
		lccd_DAO               = DaoCreator.getConstantesDAO(adm_manager);
		ls_idDocumento         = as_solicitud.getIdDocumento();

		if(!StringUtils.isValidString(ls_plantilla))
			throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

		ls_plantilla = resolverTagsBasicos(
			    ath_turnoHistoria, ads_documento, ls_plantilla, as_userId, as_remoteIp, adm_manager, ab_proceso48
			);

		{
			CalidadSolicitante lcs_calidadSolicitante;

			lcs_calidadSolicitante = DaoCreator.getCalidadSolicitanteDAO(adm_manager)
					                               .findById(as_solicitud.getIdCalidadSolicitante());

			if(lcs_calidadSolicitante != null)
				ls_plantilla = reemplazarTagEnPlantilla(
					    ls_plantilla, TagCommon.TAG_CALIDAD_ACTUA, lcs_calidadSolicitante.getNombre()
					);
		}

		ls_plantilla = reemplazarTagEnPlantilla(
			    ls_plantilla, TagCommon.TAG_NOMBRE_INTERESADO,
			    obtenerNombrePersonaOficinaOrigen(ls_idPersona, adm_manager, ls_idDocumento)
			);

		{
			Persona lp_persona;

			lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);

			if(lp_persona != null)
			{
				ls_plantilla = reemplazarTagEnPlantilla(
					    ls_plantilla, TagCommon.TAG_NUMERO_DOCUMENTO, lp_persona.getNumeroDocumento()
					);

				InteresadoDocumentoTipo lidt_tipoDoc;

				lidt_tipoDoc = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager)
						                     .findById(lp_persona.getIdDocumentoTipo());

				if(lidt_tipoDoc != null)
					ls_plantilla = reemplazarTagEnPlantilla(
						    ls_plantilla, TagCommon.TAG_TIPO_DOCUMENTO, lidt_tipoDoc.getDescripcion()
						);
			}
		}

		ls_plantilla = reemplazarTagEnPlantilla(
			    ls_plantilla, TagCommon.TAG_CORREO_ELECTRONICO,
			    obtenerCorreoPersona(ls_idPersona, as_solicitud.getIdCorreoElectronico(), adm_manager)
			);

		if(adp_direccion != null)
		{
			String ls_idPais;
			String ls_idDepartamento;
			String ls_direccion;
			ls_idPais             = adp_direccion.getIdPais();
			ls_idDepartamento     = adp_direccion.getIdDepartamento();

			ls_direccion = obtenerNombrePersonaOficinaOrigen(ls_idPersona, adm_manager, ls_idDocumento, true);

			if(StringUtils.isValidString(ls_direccion))
				ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, TagCommon.TAG_DIR_INTERESADO, ls_direccion);
			else
				ls_plantilla = reemplazarTagEnPlantilla(
					    ls_plantilla, TagCommon.TAG_DIR_INTERESADO, adp_direccion.getDireccion()
					);

			{
				Departamento ld_departamento;

				ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager).findById(ls_idPais, ls_idDepartamento);

				if(ld_departamento != null)
					ls_plantilla = reemplazarTagEnPlantilla(
						    ls_plantilla, TagCommon.TAG_DEPAR_INTERESADO, ld_departamento.getNombre()
						);
			}

			{
				Municipio lm_municipio;

				lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
						                     .findById(ls_idPais, ls_idDepartamento, adp_direccion.getIdMunicipio());

				if(lm_municipio != null)
					ls_plantilla = reemplazarTagEnPlantilla(
						    ls_plantilla, TagCommon.TAG_MUNICIPIO_INTERESADO, lm_municipio.getNombre()
						);
			}
		}

		{
			Constantes lc_tagNoRenuncia;

			lc_tagNoRenuncia = lccd_DAO.findById(ConstanteCommon.TAG_NO_RENUNCIA_TERMINOS);

			if(lc_tagNoRenuncia != null)
				ls_plantilla = reemplazarTagEnPlantilla(
					    ls_plantilla, lc_tagNoRenuncia.getCaracter(), lc_tagNoRenuncia.getTexto()
					);
		}

		if(ls_plantilla.contains(TagCommon.TAG_PERSONA_NOTIFICACION))
		{
			String ls_idDocumentosSalida;

			ls_idDocumentosSalida = ath_turnoHistoria.getIdDocumentoSalida();

			if(StringUtils.isValidString(ls_idDocumentosSalida))
			{
				DocumentosSalida lds_documentoSalida;

				lds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(adm_manager)
						                            .findById(NumericUtils.getLong(ls_idDocumentosSalida));

				if(lds_documentoSalida != null)
					ls_plantilla = reemplazarTagEnPlantilla(
						    ls_plantilla, TagCommon.TAG_PERSONA_NOTIFICACION, lds_documentoSalida.getDestinatario()
						);
			}
		}

		if(ls_plantilla.contains(TagCommon.TAG_PERSONA_ENTREGA))
		{
			String ls_idTurno;

			ls_idTurno = ath_turnoHistoria.getIdTurno();

			if(StringUtils.isValidString(ls_idTurno))
			{
				PersonaEntrega lpe_persona;
				String         ls_idPersonaEntrega;
				String         ls_proceso;
				boolean        lb_procesoVinculado;

				ls_idPersonaEntrega     = null;
				lpe_persona             = null;
				ls_proceso              = StringUtils.getStringNotNull(as_solicitud.getIdProceso());
				lb_procesoVinculado     = ls_proceso.equals(ProcesoCommon.ID_PROCESO_47)
						|| ls_proceso.equals(ProcesoCommon.ID_PROCESO_48);

				if(!lb_procesoVinculado)
					lpe_persona = DaoCreator.getPersonaEntregaDAO(adm_manager).findByIdTurno(ls_idTurno);
				else
					ls_idPersonaEntrega = ls_idPersona;

				if(lpe_persona != null)
					ls_idPersonaEntrega = lpe_persona.getIdPersona();

				if(StringUtils.isValidString(ls_idPersonaEntrega))
				{
					Persona lp_persona;

					lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersonaEntrega);

					if(lp_persona != null)
					{
						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_PERSONA_ENTREGA, lp_persona.getNombreCompleto()
							);
						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_TIPO_DOCUMENTO_PERSONA_ENTREGA,
							    lp_persona.getIdDocumentoTipo()
							);
						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_NUMERO_DOCUMENTO_PERSONA_ENTREGA,
							    lp_persona.getNumeroDocumento()
							);
					}
					else
					{
						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_PERSONA_ENTREGA, IdentificadoresCommon.DATO_INVALIDO
							);
						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_TIPO_DOCUMENTO_PERSONA_ENTREGA,
							    IdentificadoresCommon.DATO_INVALIDO
							);
						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_NUMERO_DOCUMENTO_PERSONA_ENTREGA,
							    IdentificadoresCommon.DATO_INVALIDO
							);
					}
				}
				else
				{
					ls_plantilla     = reemplazarTagEnPlantilla(
						    ls_plantilla, TagCommon.TAG_PERSONA_ENTREGA, IdentificadoresCommon.DATO_INVALIDO
						);
					ls_plantilla     = reemplazarTagEnPlantilla(
						    ls_plantilla, TagCommon.TAG_TIPO_DOCUMENTO_PERSONA_ENTREGA,
						    IdentificadoresCommon.DATO_INVALIDO
						);
					ls_plantilla     = reemplazarTagEnPlantilla(
						    ls_plantilla, TagCommon.TAG_NUMERO_DOCUMENTO_PERSONA_ENTREGA,
						    IdentificadoresCommon.DATO_INVALIDO
						);
				}
			}
		}

		if(ls_plantilla.contains(TagCommon.TAG_RENUNCIA_TERMINOS))
		{
			String ls_renunciaTerminos;

			ls_renunciaTerminos = ath_turnoHistoria.getRenunciaTerminos();

			if(StringUtils.isValidString(ls_renunciaTerminos))
			{
				if(BooleanUtils.getBooleanValue(ls_renunciaTerminos))
				{
					Constantes lc_constante;

					lc_constante = lccd_DAO.findById(ConstanteCommon.TAG_SI_RENUNCIA_TERMINOS);

					if(lc_constante != null)
						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_RENUNCIA_TERMINOS, lc_constante.getTexto()
							);
				}
				else
				{
					Constantes lc_constante;

					lc_constante = lccd_DAO.findById(ConstanteCommon.TAG_NO_RENUNCIA_TERMINOS);

					if(lc_constante != null)
						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_RENUNCIA_TERMINOS, lc_constante.getTexto()
							);
				}
			}
			else
				ls_plantilla = reemplazarTagEnPlantilla(
					    ls_plantilla, TagCommon.TAG_RENUNCIA_TERMINOS, IdentificadoresCommon.DATO_INVALIDO
					);
		}

		if(lb_reemplazarFirma)
			ls_plantilla = reemplazarTagPadFirmas(ls_plantilla);

		{
			Map<String, String> lmss_datos;

			lmss_datos     = finalizarPlantilla(ls_plantilla, null, adm_manager);

			ls_plantilla = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
		}

		{
			byte[] lba_archivoPdf;

			lba_archivoPdf = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

			if(lba_archivoPdf == null)
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

			Constantes lc_usuarioFirma;
			int        li_incrX = 0;
			int        li_incrY = 0;

			lc_usuarioFirma     = new Constantes();

			lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

			lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(lc_usuarioFirma);

			byte[] lba_grafo;

			lba_grafo = null;

			if(lc_usuarioFirma != null)
			{
				lba_grafo     = lc_usuarioFirma.getImagenes();
				li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
				li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
			}

			lba_archivoPdf = getFirmaMasivaBusiness()
					                 .reemplazarBookmarsFirma(
					    lba_archivoPdf, lba_grafo, li_incrX, li_incrY, "ESPACIO_FIRMA_CARGO", true
					);

			if(lb_reemplazarFirma)
				lba_archivoPdf = solucionarBookMarkPadFirmas(adm_manager, lba_archivoPdf, aofd_param);

			{
				long ll_idDocumentoSalida;

				ll_idDocumentoSalida = insertarDocumentoSalida(
					    lba_archivoPdf, ads_documento, as_userId, as_remoteIp, adm_manager
					);

				ath_turnoHistoria.setIdDocumentoSalida(StringUtils.getString(ll_idDocumentoSalida));
				ath_turnoHistoria.setIdUsuarioModificacion(as_userId);
				ath_turnoHistoria.setIpModificacion(as_remoteIp);

				DaoCreator.getTurnoHistoriaDAO(adm_manager).updateIdDocumentoSalida(ath_turnoHistoria);

				ads_documento.setIdDocumentoSalida(ll_idDocumentoSalida);
			}
		}
	}

	/**
	 * Generar documento salida base.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_solicitud de as solicitud
	 * @param ab_notificacion de ab notificacion
	 * @param ab_repositorioFinal de ab repositorio final
	 * @param adm_manager de adm manager
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected DocumentosSalida generarDocumentoSalidaBase(
	    TurnoHistoria ath_turnoHistoria, Solicitud as_solicitud, boolean ab_notificacion, boolean ab_repositorioFinal,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		if(ath_turnoHistoria == null)
			throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

		if(as_solicitud == null)
			throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

		DocumentosSalida lds_documento;
		String           ls_idSolicitud;
		String           ls_idPersona;

		lds_documento      = new DocumentosSalida();
		ls_idSolicitud     = as_solicitud.getIdSolicitud();
		ls_idPersona       = as_solicitud.getIdPersona();

		lds_documento.setIdTurnoHistoria(NumericUtils.getInteger(ath_turnoHistoria.getIdTurnoHistoria()));
		lds_documento.setIdSolicitud(ls_idSolicitud);
		lds_documento.setIdTurno(ath_turnoHistoria.getIdTurno());
		lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
		lds_documento.setDestinatario(obtenerNombrePersona(ls_idPersona, adm_manager));

		if(ab_notificacion)
			lds_documento.setCorreoElectronico(
			    obtenerCorreoPersona(ls_idPersona, as_solicitud.getIdCorreoElectronico(), adm_manager)
			);
		else
		{
			String ls_idDireccion;

			ls_idDireccion = as_solicitud.getIdDireccion();

			if(StringUtils.isValidString(ls_idDireccion))
			{
				PersonaDireccion lpd_direccion;

				lpd_direccion = DaoCreator.getPersonaDireccionDAO(adm_manager).findById(ls_idPersona, ls_idDireccion);

				if(lpd_direccion != null)
				{
					lds_documento.setDireccion(lpd_direccion.getDireccion());
					lds_documento.setIdPais(lpd_direccion.getIdPais());
					lds_documento.setIdDepartamento(lpd_direccion.getIdDepartamento());
					lds_documento.setIdMunicipio(lpd_direccion.getIdMunicipio());
				}
			}
			else
			{
				Collection<SolicitudMatricula> lcsm_matriculas;

				lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
						                        .findByIdSolicitud(ls_idSolicitud, true);

				if(CollectionUtils.isValidCollection(lcsm_matriculas))
				{
					boolean                      lb_found;
					Iterator<SolicitudMatricula> lism_iterador;
					DireccionPredioDAO           ldpd_direccionPredioDAO;

					lb_found                    = false;
					lism_iterador               = lcsm_matriculas.iterator();
					ldpd_direccionPredioDAO     = DaoCreator.getDireccionPredioDAO(adm_manager);

					while(lism_iterador.hasNext() && !lb_found)
					{
						SolicitudMatricula lsm_matricula;

						lsm_matricula = lism_iterador.next();

						if(lsm_matricula != null)
						{
							long   ll_idMatricula;
							String ls_idCirculo;

							ll_idMatricula     = lsm_matricula.getIdMatricula();
							ls_idCirculo       = lsm_matricula.getIdCirculo();

							{
								DireccionPredio ldp_direccion;

								ldp_direccion = new DireccionPredio();

								ldp_direccion.setIdCirculo(ls_idCirculo);
								ldp_direccion.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

								ldp_direccion = ldpd_direccionPredioDAO.findById(ldp_direccion);

								if(ldp_direccion != null)
								{
									lb_found = true;

									lds_documento.setDireccion(ldp_direccion.getDireccion());
								}
							}

							{
								PredioRegistro lpr_predioRegistro;

								lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(adm_manager)
										                           .findByCirculoMatricula(
										    ls_idCirculo, ll_idMatricula
										);

								if(lpr_predioRegistro != null)
								{
									ZonaRegistral lzr_zonaRegistral;

									lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(adm_manager)
											                          .findById(
											    lpr_predioRegistro.getIdZonaRegistral()
											);

									if(lzr_zonaRegistral != null)
									{
										lds_documento.setIdPais(lzr_zonaRegistral.getIdPais());
										lds_documento.setIdDepartamento(lzr_zonaRegistral.getIdDepartamento());
										lds_documento.setIdMunicipio(lzr_zonaRegistral.getIdMunicipio());
									}
								}
							}
						}
					}
				}
			}
		}

		lds_documento.setEstado(EstadoCommon.ACTIVO);
		lds_documento.setDocumentoAutomatico(EstadoCommon.S);
		lds_documento.setRepositorio(ab_repositorioFinal ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL);
		lds_documento.setDocumentoFinal(EstadoCommon.S);

		return lds_documento;
	}

	/**
	 * Método encargado de generar el código de expediente.
	 *
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param at_turno Objeto que contiene los datos del turno.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return String que contiene el código de expediente generado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized String generarExpediente(
	    String as_userId, String as_remoteIp, Turno at_turno, DAOManager adm_manager
	)
	    throws B2BException
	{
		String ls_expediente;

		ls_expediente = null;

		try
		{
			if(at_turno != null)
			{
				int                li_anio;
				CirculoActAdmin    lcaa_temp;
				CirculoActAdminDAO lcaa_DAO;
				Long               ll_consecutivo;
				String             ls_idCirculo;
				String             ls_nomenclaturaExpediente;

				li_anio                       = Calendar.getInstance().get(Calendar.YEAR);
				lcaa_temp                     = new CirculoActAdmin();
				lcaa_DAO                      = DaoCreator.getCirculoActAdminDAO(adm_manager);
				ll_consecutivo                = null;
				ls_idCirculo                  = at_turno.getIdCirculo();
				ls_nomenclaturaExpediente     = at_turno.getNomemclaturaExpedienteAA();

				if(!StringUtils.isValidString(ls_idCirculo))
					throw new B2BException(ErrorKeys.ERROR_NO_CIRCULO_REGISTRAL);

				if(!StringUtils.isValidString(ls_nomenclaturaExpediente))
					throw new B2BException(ErrorKeys.ERROR_TIPO_EXPEDIENTE);

				lcaa_temp.setVigencia(StringUtils.getString(li_anio));
				lcaa_temp.setIdCirculo(ls_idCirculo);
				lcaa_temp.setTipoExpediente(ls_nomenclaturaExpediente);

				lcaa_temp = DaoCreator.getCirculoActAdminDAO(adm_manager).findById(lcaa_temp);

				if(lcaa_temp != null)
				{
					ll_consecutivo = lcaa_temp.getConsecutivo();

					if(NumericUtils.isValidLong(ll_consecutivo))
					{
						ll_consecutivo = NumericUtils.getLongWrapper(NumericUtils.getLong(ll_consecutivo) + 1L);

						lcaa_temp.setConsecutivo(ll_consecutivo);
						lcaa_temp.setIdUsuarioModificacion(as_userId);
						lcaa_temp.setIpModificacion(as_remoteIp);

						lcaa_DAO.insertOrUpdate(lcaa_temp, false);
					}
				}
				else
				{
					ll_consecutivo     = NumericUtils.getLongWrapper(1L);
					lcaa_temp          = new CirculoActAdmin();

					lcaa_temp.setVigencia(StringUtils.getString(li_anio));
					lcaa_temp.setIdCirculo(ls_idCirculo);
					lcaa_temp.setTipoExpediente(ls_nomenclaturaExpediente);
					lcaa_temp.setConsecutivo(ll_consecutivo);
					lcaa_temp.setActivo(true);
					lcaa_temp.setIdUsuarioCreacion(as_remoteIp);
					lcaa_temp.setIpCreacion(as_remoteIp);

					lcaa_DAO.insertOrUpdate(lcaa_temp, true);
				}

				ls_expediente = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ls_nomenclaturaExpediente
					+ IdentificadoresCommon.SIMBOLO_GUION + li_anio + IdentificadoresCommon.SIMBOLO_GUION
					+ String.format("%05d", ll_consecutivo);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("crearNir", lb2be_e);

			throw lb2be_e;
		}

		return ls_expediente;
	}

	/**
	 * Método de obtención del valor del id Correspondencia.
	 *
	 * @param lth_turnoHistoria con el parametro del trno historia
	 * @param adm_manager con el daoManager de la transaccion
	 * @param ab_accion para saber que endpoint debe usar
	 * @return n String con el id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized String generarIdCorrespondencia(
	    TurnoHistoria lth_turnoHistoria, DAOManager adm_manager, boolean ab_accion
	)
	    throws B2BException
	{
		String ls_return;
		ls_return = null;

		try
		{
			if(lth_turnoHistoria != null)
			{
				Correspondencia     lc_correspondencia;
				Constantes          lc_constante;
				String              ls_endpoint;
				String              ls_turno;
				DocumentosSalida    lds_documentoSalida;
				DocumentosSalidaDAO ldsd_dao;
				TipoDocumentalDAO   ltdd_daoTipoDocumental;
				Collection<String>  lc_documentos;
				TipoVariable[]      lctv_ctv;
				ldsd_dao                   = DaoCreator.getDocumentosSalidaDAO(adm_manager);
				ltdd_daoTipoDocumental     = DaoCreator.getTipoDocumentalDAO(adm_manager);

				Collection<DocumentosSalida> lcds_documentoSalida;
				Collection<TipoDestinatario> lctd_tipoDestinatario;
				lc_correspondencia         = new Correspondencia();

				ls_endpoint      = null;
				lc_constante     = null;
				ls_turno         = lth_turnoHistoria.getIdTurno();

				if(!StringUtils.isValidString(ls_turno))
					ls_turno = IdentificadoresCommon.NA;

				lds_documentoSalida       = new DocumentosSalida();
				lcds_documentoSalida      = new LinkedList<DocumentosSalida>();
				lctd_tipoDestinatario     = new LinkedList<TipoDestinatario>();
				lc_correspondencia.setTurno(ls_turno);
				lc_correspondencia.setClasificacion(TipoEntradaObtenerEECorrespondenciaClasificacion._notificacion);
				lc_correspondencia.setCanal(TipoEntradaObtenerEECorrespondenciaCanal._fisico);
				lc_documentos     = new LinkedList<String>();
				lctv_ctv          = new TipoVariable[1];

				lds_documentoSalida.setIdTurnoHistoria(NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria()));

				lcds_documentoSalida = ldsd_dao.findByIdTurnoHistoria(lds_documentoSalida);

				if(CollectionUtils.isValidCollection(lcds_documentoSalida) && ab_accion)
				{
					for(DocumentosSalida ilds_iterador : lcds_documentoSalida)
					{
						if(ilds_iterador != null)
						{
							String ls_tipoDocumental;
							ls_tipoDocumental = ilds_iterador.getIdTipoDocumental();

							if(StringUtils.isValidString(ls_tipoDocumental))
							{
								TipoDocumental ltd_tipoDocumental;
								ltd_tipoDocumental = ltdd_daoTipoDocumental.findById(ls_tipoDocumental);

								if(ltd_tipoDocumental != null)
									lc_documentos.add(ltd_tipoDocumental.getNombre());
							}

							TipoDestinatario lt_destinatario;

							String           ls_nombre;
							TipoVariable     ltv_tv;
							ltv_tv              = new TipoVariable();
							ls_nombre           = ilds_iterador.getDestinatario();
							lt_destinatario     = new TipoDestinatario();
							lt_destinatario.setNumeroDocDestinatario(IdentificadoresCommon.NA);
							lt_destinatario.setTipoDocDestinatario(IdentificadoresCommon.NA);
							ltv_tv.setLlave(IdentificadoresCommon.NA);
							ltv_tv.setValor(IdentificadoresCommon.NA);

							if(StringUtils.isValidString(ls_nombre))
							{
								lt_destinatario.setPrimerNombreDestinatario(ls_nombre);
								lt_destinatario.setSegundoApellidoDestinatario(ls_nombre);
								lt_destinatario.setSegundoNombreDestinatario(ls_nombre);
								lt_destinatario.setPrimerApellidoDestinatario(ls_nombre);
							}
							else
							{
								lt_destinatario.setPrimerNombreDestinatario(IdentificadoresCommon.NA);
								lt_destinatario.setPrimerApellidoDestinatario(IdentificadoresCommon.NA);
								lt_destinatario.setSegundoApellidoDestinatario(IdentificadoresCommon.NA);
								lt_destinatario.setSegundoNombreDestinatario(IdentificadoresCommon.NA);
							}

							lctd_tipoDestinatario.add(lt_destinatario);
							lctv_ctv[0] = ltv_tv;
							lt_destinatario.setVariables(lctv_ctv);
						}
					}
				}

				lc_correspondencia.setIdOrip(IdentificadoresCommon.NA);
				lc_correspondencia.setNumeroFolios(IdentificadoresCommon.NA);
				lc_correspondencia.setNir(IdentificadoresCommon.NA);

				if(!ab_accion)
				{
					lc_documentos.add(IdentificadoresCommon.NA);

					TipoDestinatario lt_destinatario;

					TipoVariable     ltv_tv;
					ltv_tv              = new TipoVariable();
					lt_destinatario     = new TipoDestinatario();
					lt_destinatario.setNumeroDocDestinatario(IdentificadoresCommon.NA);
					lt_destinatario.setTipoDocDestinatario(IdentificadoresCommon.NA);
					ltv_tv.setLlave(IdentificadoresCommon.NA);
					ltv_tv.setValor(IdentificadoresCommon.NA);
					lt_destinatario.setPrimerNombreDestinatario(IdentificadoresCommon.NA);
					lt_destinatario.setPrimerApellidoDestinatario(IdentificadoresCommon.NA);
					lt_destinatario.setSegundoApellidoDestinatario(IdentificadoresCommon.NA);
					lt_destinatario.setSegundoNombreDestinatario(IdentificadoresCommon.NA);

					lctd_tipoDestinatario.add(lt_destinatario);
					lctv_ctv[0] = ltv_tv;
					lt_destinatario.setVariables(lctv_ctv);
				}

				lc_correspondencia.setDestinatarios(lctd_tipoDestinatario);
				lc_correspondencia.setDocumentos(lc_documentos);

				lc_constante = DaoCreator.getConstantesDAO(adm_manager)
						                     .findById(ConstanteCommon.GENERACION_IDS_CORRESPONDENCIA_ENDPOINT);

				if(lc_constante != null)
				{
					ls_endpoint = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_endpoint))
						ls_return = ClienteGeneracionIDsCorrespondencia.generacionIDsCorrespondencia(
							    lc_correspondencia, ls_endpoint
							);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarIdCorrespondencia", lb2be_e);
			throw lb2be_e;
		}

		return ls_return;
	}

	/**
	 * Guarda la imagen de un documento en la base de datos y la relaciona a un registro de documentos salida.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param al_turnoHistoria id del turno historia al cual pertenece el documento
	 * @param aba_archivo arreglo de bytes a guardar como imagen del documento
	 * @param as_tipo Tipo de documento a relacionar al archivo
	 * @param as_estado Estado del registro en documentos salida
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void guardarDocumentoPdf(
	    DAOManager adm_manager, Long al_turnoHistoria, byte[] aba_archivo, String as_tipo, String as_estado,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(!NumericUtils.isValidLong(al_turnoHistoria))
			throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

		TurnoHistoria lth_turnoHistoria;

		lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(al_turnoHistoria);

		if(lth_turnoHistoria != null)
		{
			Imagenes         li_imagenRelacion;
			long             ll_idImagen;
			DocumentosSalida lds_documento;
			Integer          lbi_idTurnoHistoria;

			li_imagenRelacion       = new Imagenes();
			lds_documento           = new DocumentosSalida();
			lbi_idTurnoHistoria     = NumericUtils.getInteger(al_turnoHistoria);

			li_imagenRelacion.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
			li_imagenRelacion.setIdUsuarioCreacion(as_userId);
			li_imagenRelacion.setIpCreacion(as_remoteIp);
			li_imagenRelacion.setImagenBlob(aba_archivo);

			ll_idImagen = DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(li_imagenRelacion, true);

			if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idImagen), 1))
				throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

			lds_documento.setIdTurnoHistoria(lbi_idTurnoHistoria);
			lds_documento.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
			lds_documento.setIdTurno(lth_turnoHistoria.getIdTurno());
			lds_documento.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
			lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
			lds_documento.setTipo(as_tipo);
			lds_documento.setEstado(as_estado);
			lds_documento.setIdUsuarioCreacion(as_userId);
			lds_documento.setIpCreacion(as_remoteIp);

			DaoCreator.getDocumentosSalidaDAO(adm_manager).insertOrUpdate(lds_documento, true);
		}
		else
			throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
	}

	/**
	 * Guardar documento salida visitas.
	 *
	 * @param aba_documento de aba documento
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param ab_auto de lb auto
	 * @param as_idSolicitud de as id solicitud
	 * @param ab_aprobacion the ab aprobacion
	 * @param adm_manager de adm manager
	 * @param ath_historia the ath historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void guardarDocumentoSalidaVisitas(
	    byte[] aba_documento, String as_userId, String as_remoteIp, boolean ab_auto, String as_idSolicitud,
	    boolean ab_aprobacion, DAOManager adm_manager, TurnoHistoria ath_historia
	)
	    throws B2BException
	{
		if((aba_documento != null))
		{
			try
			{
				DocumentosSalida lds_documento;

				lds_documento = new DocumentosSalida();

				lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
				lds_documento.setIdTipoDocumental(
				    ab_auto ? TipoDocumentalCommon.AUTO : TipoDocumentalCommon.RESOLUCION
				);
				lds_documento.setTipo(
				    ab_auto ? TipoArchivoCommon.AUTO_ORDENA_VISITA : TipoArchivoCommon.RESOLUCION_INTERVENCION
				);
				lds_documento.setEstado(EstadoCommon.ACTIVO);
				lds_documento.setDocumentoAutomatico(EstadoCommon.S);
				lds_documento.setRepositorio(ab_aprobacion ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL);
				lds_documento.setDocumentoFinal(EstadoCommon.N);
				lds_documento.setIdSolicitud(as_idSolicitud);

				if(!ab_aprobacion)
				{
					lds_documento.setIdEcm(IdentificadoresCommon.X);
					lds_documento.setIdNombreDocumento(IdentificadoresCommon.X);
				}

				if((ath_historia != null))
				{
					lds_documento.setIdTurnoHistoria(NumericUtils.getInteger(ath_historia.getIdTurnoHistoria()));
					lds_documento.setIdTurno(ath_historia.getIdTurno());
				}

				insertarDocumentoSalida(aba_documento, lds_documento, as_userId, as_remoteIp, adm_manager);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("guardarDocumentoSalida", lb2be_e);

				throw lb2be_e;
			}
		}
	}

	/**
	 * Método que se encarga de inactivar los documentos activos del turno según el tipo.
	 *
	 * @param al_idTurnoHistoria Variable String que contiene el id del turno.
	 * @param as_tipoArchivo Variable String que contiene el tipo archivo.
	 * @param adm_manager Conexión a la base de datos
	 * @param as_remoteIp Variable String que contiene la ip del usuario.
	 * @param as_usuarioProceso Variable String que contiene el id del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void inactivarDocumentosActivosTurnoHistoriaTipo(
	    Long al_idTurnoHistoria, String as_tipoArchivo, DAOManager adm_manager, String as_remoteIp,
	    String as_usuarioProceso
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentos2;

		lcds_documentos2 = DaoCreator.getDocumentosSalidaDAO(adm_manager)
				                         .findByIdTurnoHistoriaTipo(
				    NumericUtils.getInteger(al_idTurnoHistoria), as_tipoArchivo, true
				);

		if(CollectionUtils.isValidCollection(lcds_documentos2))
		{
			for(DocumentosSalida lds_documento2 : lcds_documentos2)
			{
				if(lds_documento2 != null)
				{
					lds_documento2.setEstado(EstadoCommon.I);
					lds_documento2.setIdUsuarioModificacion(as_usuarioProceso);
					lds_documento2.setIpModificacion(as_remoteIp);

					DaoCreator.getDocumentosSalidaDAO(adm_manager).insertOrUpdate(lds_documento2, false);
				}
			}
		}
	}

	/**
	 * Método encargado de inactivar los predios por turno.
	 *
	 * @param as_idTurno Variable que contiene el id del turno
	 * @param adm_manager DAOManager que controla la transacción.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_ipRemota Variable que contiene la ip del servidor.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void inactivarPredioRegistro(
	    String as_idTurno, DAOManager adm_manager, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				Collection<AccPredioRegistro> lcpr_data;
				AccPredioRegistroDAO          lapr_DAO;

				lapr_DAO      = DaoCreator.getAccPredioRegistroDAO(adm_manager);
				lcpr_data     = lapr_DAO.findByTurno(as_idTurno, false);

				if(CollectionUtils.isValidCollection(lcpr_data))
				{
					for(AccPredioRegistro lpr_iterador : lcpr_data)
					{
						if(lpr_iterador != null)
						{
							lpr_iterador.setEstadoPredio(EstadoCommon.INACTIVO);
							lpr_iterador.setIdUsuarioModificacion(as_userId);
							lpr_iterador.setIpModificacion(as_ipRemota);

							lapr_DAO.updateById(lpr_iterador);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("inactivarPredioRegistro", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de inactivar las matriculas de la solicitud.
	 *
	 * @param as_idSolicitud Variable que contiene el id de la solicitud.
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param adm_manager DAOManager que controla la transacción.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_ipRemota Variable que contiene la ip del servidor.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void inactivarSolicitudMatricula(
	    String as_idSolicitud, String as_idCirculo, DAOManager adm_manager, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo))
			{
				Collection<SolicitudMatricula> lcsm_data;
				SolicitudMatriculaDAO          lsm_DAO;

				lsm_DAO       = DaoCreator.getSolicitudMatriculaDAO(adm_manager);
				lcsm_data     = lsm_DAO.findByIdSolicitudCirculo(as_idSolicitud, as_idCirculo);

				if(CollectionUtils.isValidCollection(lcsm_data))
				{
					for(SolicitudMatricula lsm_iterador : lcsm_data)
					{
						if(lsm_iterador != null)
						{
							lsm_iterador.setEstado(EstadoCommon.INACTIVO);
							lsm_iterador.setIdUsuarioModificacion(as_userId);
							lsm_iterador.setIpModificacion(as_ipRemota);

							lsm_DAO.insertOrUpdate(lsm_iterador, false);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("inactivarSolicitudMatricula", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Inserta o actualiza el registro de un correo electrónico.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idPersona id de la persona asociada al correo electrónico
	 * @param as_idCorreoElectronico id del correo electrónico a actualizar
	 * @param as_correo correo electrónico a ser guardado en el registro
	 * @param as_userId id del usuario que realiza la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return -1 si se actualizó un registro, o un número mayor a cero con el id del correo electrónico insertado
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected long insertarActualizarCorreoElectronico(
	    DAOManager adm_manager, String as_idPersona, String as_idCorreoElectronico, String as_correo, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		long ll_idCorreo;

		ll_idCorreo = -1;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_correo))
		{
			PersonaCorreoElectronicoDAO lpced_personaCorreoElectronicoDAO;
			PersonaCorreoElectronico    lpce_correo;

			lpced_personaCorreoElectronicoDAO     = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager);
			lpce_correo                           = lpced_personaCorreoElectronicoDAO.findById(
				    as_idPersona, as_idCorreoElectronico
				);

			if(lpce_correo != null)
			{
				lpce_correo.setCorreoElectronico(as_correo);
				lpce_correo.setIdUsuarioModificacion(as_userId);
				lpce_correo.setIpModificacion(as_remoteIp);

				lpced_personaCorreoElectronicoDAO.insertOrUpdate(lpce_correo, false);
			}
			else
			{
				Collection<PersonaCorreoElectronico> lcpce_correos;

				lcpce_correos = lpced_personaCorreoElectronicoDAO.findByIdPersonaCorreo(as_idPersona, as_correo);

				if(CollectionUtils.isValidCollection(lcpce_correos))
				{
					Iterator<PersonaCorreoElectronico> lipce_iterador;
					boolean                            lb_found;

					lipce_iterador     = lcpce_correos.iterator();
					lb_found           = false;

					while(lipce_iterador.hasNext() && !lb_found)
					{
						PersonaCorreoElectronico lpce_correoTmp;

						lpce_correoTmp = lipce_iterador.next();

						if(lpce_correoTmp != null)
						{
							String ls_idCorreoTmp;

							ls_idCorreoTmp = lpce_correoTmp.getIdCorreoElectronico();

							if(StringUtils.isValidString(ls_idCorreoTmp))
							{
								lb_found     = true;

								ll_idCorreo = NumericUtils.getLong(ls_idCorreoTmp);
							}
						}
					}
				}
				else
				{
					lpce_correo = new PersonaCorreoElectronico();

					lpce_correo.setIdPersona(as_idPersona);
					lpce_correo.setCorreoElectronico(as_correo);
					lpce_correo.setFechaDesde(new Date());
					lpce_correo.setIdUsuarioCreacion(as_userId);
					lpce_correo.setIpCreacion(as_remoteIp);

					ll_idCorreo = lpced_personaCorreoElectronicoDAO.insertOrUpdate(lpce_correo, true);

					if(ll_idCorreo <= 0)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_CORREO);
				}
			}
		}

		return ll_idCorreo;
	}

	/**
	 * Inserta o actualiza el registro de un teléfono.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idPersona id de la persona asociada al teléfono
	 * @param as_idTelefono id del teléfono a actualizar
	 * @param as_tipoTelefono Identificador de si es movil o fijo el teléfono a insertar
	 * @param as_telefono teléfono a ser guardado en el registro
	 * @param as_userId id del usuario que realiza la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return -1 si se actualizó un registro, o un número mayor a cero con el id del teléfono insertado
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected long insertarActualizarTelefono(
	    DAOManager adm_manager, String as_idPersona, String as_idTelefono, String as_tipoTelefono, String as_telefono,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		long ll_idTelefono;

		ll_idTelefono = -1;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_telefono))
		{
			PersonaTelefonoDAO lptd_personaTelefonoDAO;
			PersonaTelefono    lpt_telefono;

			lptd_personaTelefonoDAO     = DaoCreator.getPersonaTelefonoDAO(adm_manager);
			lpt_telefono                = lptd_personaTelefonoDAO.findById(as_idPersona, as_idTelefono);

			if(lpt_telefono != null)
			{
				lpt_telefono.setTelefono(as_telefono);
				lpt_telefono.setIpModificacion(as_remoteIp);
				lpt_telefono.setIdUsuarioModificacion(as_userId);

				lptd_personaTelefonoDAO.insertOrUpdate(lpt_telefono, false);
			}
			else
			{
				Collection<PersonaTelefono> lcpt_telefonos;

				lcpt_telefonos = lptd_personaTelefonoDAO.findByIdPersonaTipoTelefono(
					    as_idPersona, as_tipoTelefono, as_telefono
					);

				if(CollectionUtils.isValidCollection(lcpt_telefonos))
				{
					Iterator<PersonaTelefono> lipt_telefonos;
					boolean                   lb_found;

					lipt_telefonos     = lcpt_telefonos.iterator();
					lb_found           = false;

					while(lipt_telefonos.hasNext() && !lb_found)
					{
						PersonaTelefono lpt_telefonoTmp;

						lpt_telefonoTmp = lipt_telefonos.next();

						if(lpt_telefonoTmp != null)
						{
							String ls_idTelefono;

							ls_idTelefono = lpt_telefonoTmp.getIdTelefono();

							if(StringUtils.isValidString(ls_idTelefono))
							{
								lb_found     = true;

								ll_idTelefono = NumericUtils.getLong(ls_idTelefono);
							}
						}
					}
				}
				else
				{
					lpt_telefono = new PersonaTelefono(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT, as_telefono);

					lpt_telefono.setIdPersona(as_idPersona);
					lpt_telefono.setFechaDesde(new Date());
					lpt_telefono.setTipoTelefono(as_tipoTelefono);
					lpt_telefono.setIpCreacion(as_remoteIp);
					lpt_telefono.setIdUsuarioCreacion(as_userId);

					ll_idTelefono = lptd_personaTelefonoDAO.insertOrUpdate(lpt_telefono, true);

					if(ll_idTelefono <= 0)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
				}
			}
		}

		return ll_idTelefono;
	}

	/**
	 * Inserta el correo electrónico al representante de la entidad externa.
	 *
	 * @param as_idPersona id de la persona representante
	 * @param as_correo correo a insertar
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param apced_personaCorreoDAO Objeto contenedor de la conexión a la tabla de inserción
	 * @return el valor de long
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	protected long insertarCorreoPersonaRepresentanteEntidad(
	    String as_idPersona, String as_correo, String as_userId, String as_remoteIp,
	    PersonaCorreoElectronicoDAO apced_personaCorreoDAO
	)
	    throws B2BException
	{
		long ll_idCorreoElectronico;

		ll_idCorreoElectronico = 0;

		if(
		    StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_correo)
			    && StringUtils.isValidString(as_userId) && StringUtils.isValidString(as_remoteIp)
		)
		{
			PersonaCorreoElectronico lpce_personaCorreo;

			lpce_personaCorreo = new PersonaCorreoElectronico();

			lpce_personaCorreo.setIdPersona(as_idPersona);
			lpce_personaCorreo.setCorreoElectronico(as_correo);
			lpce_personaCorreo.setFechaDesde(new Date());
			lpce_personaCorreo.setIdUsuarioCreacion(as_userId);
			lpce_personaCorreo.setIpCreacion(as_remoteIp);

			ll_idCorreoElectronico = apced_personaCorreoDAO.insertOrUpdate(lpce_personaCorreo, true);
		}

		return ll_idCorreoElectronico;
	}

	/**
	 * Inserta un registro en imagenes y documentos salida, correspondientes a un documento.
	 *
	 * @param aba_archivo arreglo de bytes del documento
	 * @param ads_documento Objeto contenedor de la información del documento
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param adm_manager Conexión a la base de datos
	 * @return id del documento salida insertado
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	protected synchronized long insertarDocumentoSalida(
	    byte[] aba_archivo, DocumentosSalida ads_documento, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		long ll_idDocumentoSalida;

		ll_idDocumentoSalida = 0L;

		if((ads_documento != null) && (aba_archivo != null))
		{
			long ll_idImagen;

			{
				Imagenes li_imagenRelacion;

				li_imagenRelacion = new Imagenes();

				li_imagenRelacion.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
				li_imagenRelacion.setIdUsuarioCreacion(as_userId);
				li_imagenRelacion.setIpCreacion(as_remoteIp);
				li_imagenRelacion.setImagenBlob(aba_archivo);

				ll_idImagen = DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(li_imagenRelacion, true);

				if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idImagen), 1))
					throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);
			}

			ads_documento.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
			ads_documento.setIdUsuarioCreacion(as_userId);
			ads_documento.setIpCreacion(as_remoteIp);

			ll_idDocumentoSalida = DaoCreator.getDocumentosSalidaDAO(adm_manager).insertOrUpdate(ads_documento, true);
		}
		else
			throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

		return ll_idDocumentoSalida;
	}

	/**
	 * Inserta el teléfono de un representante de entidad externa en la base de datos.
	 *
	 * @param as_idPersona id de la persona representante
	 * @param as_telefono teléfono a insertar
	 * @param as_departamento departamento de la persona representante
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param aptd_personaTelefonoDAO Objeto contenedor de la conexión a la tabla de inserción
	 * @return el valor de long
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	protected long insertarTelefonoPersonaRepresentanteEntidad(
	    String as_idPersona, String as_telefono, String as_departamento, String as_userId, String as_remoteIp,
	    PersonaTelefonoDAO aptd_personaTelefonoDAO
	)
	    throws B2BException
	{
		long ll_idTelefono;

		ll_idTelefono = 0;

		if(
		    StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_telefono)
			    && StringUtils.isValidString(as_departamento) && StringUtils.isValidString(as_userId)
			    && StringUtils.isValidString(as_remoteIp)
		)
		{
			PersonaTelefono lpt_personaTelefono;

			lpt_personaTelefono = new PersonaTelefono();

			lpt_personaTelefono.setIdPersona(as_idPersona);
			lpt_personaTelefono.setTipoTelefono((as_telefono.length() == 7) ? EstadoCommon.F : EstadoCommon.M);
			lpt_personaTelefono.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			lpt_personaTelefono.setIdDepartamento(as_departamento);
			lpt_personaTelefono.setTelefono(as_telefono);
			lpt_personaTelefono.setFechaDesde(new Date());
			lpt_personaTelefono.setIdUsuarioCreacion(as_userId);
			lpt_personaTelefono.setIpCreacion(as_remoteIp);

			ll_idTelefono = aptd_personaTelefonoDAO.insertOrUpdate(lpt_personaTelefono, true);
		}

		return ll_idTelefono;
	}

	/**
	 * Mensajes informativos devolucion dinero.
	 *
	 * @param ld_fechaConsignacionErrada de ld fecha consignacion errada
	 * @param ls_numeroConsignacionErrada de ls numero consignacion errada
	 * @param ls_turno correspondiente al valor de turno
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized Map<String, String> mensajesInformativosDevolucionDinero(
	    Date ld_fechaConsignacionErrada, String ls_numeroConsignacionErrada, String ls_turno, DAOManager adm_manager
	)
	    throws B2BException
	{
		Map<String, String> lmsss_mensajesInformativos;

		lmsss_mensajesInformativos = new HashMap<String, String>();

		try
		{
			if(ld_fechaConsignacionErrada != null)
			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(adm_manager)
						                     .findById(ConstanteCommon.PLAZO_CONSIGNACION_ERRADA);

				if(lc_constante != null)
				{
					BigInteger lbi_entero;

					lbi_entero = lc_constante.getEntero();

					if(lbi_entero != null)
					{
						Date ld_fechaPlazoConsignacionErrada;

						ld_fechaPlazoConsignacionErrada = modificarFecha(
							    ld_fechaConsignacionErrada, Calendar.YEAR,
							    DateUtils.getYear(ld_fechaConsignacionErrada) + NumericUtils.getInt(lbi_entero)
							);

						if(ld_fechaPlazoConsignacionErrada != null)
						{
							Date ld_fechaActual;

							ld_fechaActual = new Date();

							if(ld_fechaActual.after(ld_fechaPlazoConsignacionErrada))
								lmsss_mensajesInformativos.put(
								    "FECHA_CONSIGNACION_ERRADA",
								    addMessage(MessagesKeys.PLAZO_SOLICITUD_DEVOLUCION_DINERO_VENCIDO)
								);
						}
					}
				}
			}

			if(StringUtils.isValidString(ls_numeroConsignacionErrada))
			{
				Collection<DevolucionDinero> lcdd_devolucionDinero;

				lcdd_devolucionDinero = DaoCreator.getDevolucionDineroDAO(adm_manager)
						                              .findAllByNumeroConsignacionErrada(ls_numeroConsignacionErrada);

				if(CollectionUtils.isValidCollection(lcdd_devolucionDinero))
					lmsss_mensajesInformativos.put(
					    "NUMERO_CONSIGNACION_ERRADA",
					    addMessage(MessagesKeys.EXISTE_SOLICITUD_DEVOLUCION_CONSIGNACION_RELACIONADA)
					);
			}

			if(StringUtils.isValidString(ls_turno))
			{
				TurnoHistoria lt_turnoHistoria;
				Date          ld_fechaComparacion;
				String        ls_constante;
				String        ls_constanteMensaje;

				lt_turnoHistoria        = DaoCreator.getTurnoHistoriaDAO(adm_manager)
						                                .findByIdTurnoEtapasDevDinero(ls_turno);
				ld_fechaComparacion     = null;
				ls_constante            = null;
				ls_constanteMensaje     = null;

				if(lt_turnoHistoria != null)
				{
					int ll_idEtapa;

					ll_idEtapa = NumericUtils.getInt(lt_turnoHistoria.getIdEtapa());

					if(ll_idEtapa > NumericUtils.DEFAULT_INT_VALUE)
					{
						switch(ll_idEtapa)
						{
							case 501:
							case 502:
							{
								DocumentosSalida lds_ds;

								lds_ds = DaoCreator.getDocumentosSalidaDAO(adm_manager)
										               .findByIdTurnoTipo(ls_turno, TipoArchivoCommon.NOTA_DEVOLUTIVA);

								if(lds_ds != null)
								{
									ls_constante            = ConstanteCommon.TIEMPO_DEV_DINERO_NOTA_DEVOLUTIVA;
									ls_constanteMensaje     = ConstanteCommon.MSN_DEV_DINERO_FUERA_TERMINOS;
									ld_fechaComparacion     = lds_ds.getFechaNotificacion();
								}

								break;
							}

							case 515:
							{
								ls_constante            = ConstanteCommon.TIEMPO_DEV_DINERO_CERTIFICADO;
								ls_constanteMensaje     = ConstanteCommon.MSN_DEV_DINERO_FUERA_TERMINOS;
								ld_fechaComparacion     = lt_turnoHistoria.getFechaCreacion();

								break;
							}

							case 500:
							{
								Collection<RegistroMayorValor> lrmv_mayorValor;

								lrmv_mayorValor = DaoCreator.getRegistroMayorValorDAO(adm_manager)
										                        .findByIdTurno(ls_turno);

								if(CollectionUtils.isValidCollection(lrmv_mayorValor))
								{
									ls_constante            = ConstanteCommon.TIEMPO_DEV_DINERO_PAGO_MAYOR_VALOR;
									ls_constanteMensaje     = ConstanteCommon.MSN_DEV_DINERO_FUERA_TERMINOS;
									ld_fechaComparacion     = lt_turnoHistoria.getFechaCreacion();
								}

								break;
							}

							default:
							{
								ls_constanteMensaje = ConstanteCommon.MSN_DEV_DINERO_EXITISO;

								break;
							}
						}
					}

					if(
					    StringUtils.isValidString(ls_constante) && StringUtils.isValidString(ls_constanteMensaje)
						    && (ld_fechaComparacion != null)
					)
					{
						Constantes lc_constante;

						lc_constante = DaoCreator.getConstantesDAO(adm_manager).findById(ls_constante);

						if(lc_constante != null)
						{
							BigInteger lbi_entero;

							lbi_entero = lc_constante.getEntero();

							if(lbi_entero != null)
							{
								Date ld_fechaComparacionModificada;

								ld_fechaComparacionModificada = modificarFecha(
									    ld_fechaComparacion, Calendar.MONTH,
									    DateUtils.getYear(ld_fechaComparacion) + NumericUtils.getInt(lbi_entero)
									);

								if(ld_fechaComparacionModificada != null)
								{
									Date ld_fechaActual;

									ld_fechaActual = new Date();

									if(ld_fechaActual.after(ld_fechaComparacionModificada))
										lmsss_mensajesInformativos.put(
										    "TURNO",
										    DaoCreator.getConstantesDAO(adm_manager).findString(ls_constanteMensaje)
										);
								}
							}
						}
					}
					else
						lmsss_mensajesInformativos.put(
						    "TURNO",
						    DaoCreator.getConstantesDAO(adm_manager)
							              .findString(
							        StringUtils.isValidString(ls_constanteMensaje) ? ls_constanteMensaje
							                                                           : ConstanteCommon.MSN_DEV_DINERO_EXITISO
							    )
						);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("mensajesInformativosDevolucionDinero", lb2be_e);

			throw lb2be_e;
		}

		if(lmsss_mensajesInformativos.isEmpty())
			lmsss_mensajesInformativos = null;

		return lmsss_mensajesInformativos;
	}

	/**
	 * Obtiene un objeto Calendar desde un objeto Date.
	 *
	 * @param ad_fecha de ad fecha
	 * @return Calendar resultante de la conversión
	 */
	protected Calendar obtenerCalendarDesdeDate(Date ad_fecha)
	{
		return obtenerCalendarDesdeLocalDateTime(obtenerLocalDateTime(ad_fecha));
	}

	/**
	 * Obtiene un objeto Calendar desde un objeto LocalDateTime.
	 *
	 * @param aldt_fecha Objeto contenedor de la fecha
	 * @return Calendar resultante de la conversión
	 */
	protected Calendar obtenerCalendarDesdeLocalDateTime(LocalDateTime aldt_fecha)
	{
		return (aldt_fecha != null)
		? GregorianCalendar.from(
		    LocalDateTime.ofInstant(aldt_fecha.toInstant(ZoneOffset.UTC), ZoneId.systemDefault())
			                 .atZone(ZoneId.systemDefault())
		) : null;
	}

	/**
	 * Consulta los turnos que estén bloqueando una matrícula.
	 *
	 * @param as_idTurno Id del turno a consultar las matrículas
	 * @param adm_manager Conexión a la base de datos
	 * @return Colección de turnos contenidos en cadenas de caracteres
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected Collection<String> obtenerColeccionTurnosBitacoraBloqueo(String as_idTurno, DAOManager adm_manager)
	    throws B2BException
	{
		Collection<String> lcs_return;

		lcs_return = new LinkedList<String>();

		if(StringUtils.isValidString(as_idTurno))
		{
			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(as_idTurno);

			if(lt_turno != null)
			{
				Collection<SolicitudMatricula> lcsm_matriculas;

				lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
						                        .findByIdSolicitudCirculo(
						    lt_turno.getIdSolicitud(), lt_turno.getIdCirculo()
						);

				if(CollectionUtils.isValidCollection(lcsm_matriculas))
				{
					BitacoraBloqueoDAO lbbd_bitacoraBloqueoDAO;

					lbbd_bitacoraBloqueoDAO = DaoCreator.getBitacoraBloqueoDAO(adm_manager);

					for(SolicitudMatricula lsm_matricula : lcsm_matriculas)
					{
						if(lsm_matricula != null)
						{
							Collection<String> lcs_tmp;

							lcs_tmp = lbbd_bitacoraBloqueoDAO.findTurnoBloqueoByCirculoMatriculaDifTurno(
								    lsm_matricula.getIdCirculo(), lsm_matricula.getIdMatricula(), as_idTurno
								);

							if(CollectionUtils.isValidCollection(lcs_tmp))
								lcs_return.addAll(lcs_tmp);
						}
					}
				}
			}
		}

		if(lcs_return.isEmpty())
			lcs_return = null;

		return lcs_return;
	}

	/**
	 * Obtener correo persona.
	 *
	 * @param as_idPersona de as id persona
	 * @param as_idCorreoElectronico de as id correo electronico
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String obtenerCorreoPersona(String as_idPersona, String as_idCorreoElectronico, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		{
			PersonaCorreoElectronico lpce_correo;

			lpce_correo = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
					                    .findById(as_idPersona, as_idCorreoElectronico);

			if(lpce_correo != null)
				ls_return = lpce_correo.getCorreoElectronico();
		}

		return ls_return;
	}

	/**
	 * Obtener date desde calendar.
	 *
	 * @param lc_calendar de lc calendar
	 * @return el valor de date
	 */
	protected Date obtenerDateDesdeCalendar(Calendar lc_calendar)
	{
		return obtenerDateDesdeLocalDateTime(obtenerLocalDateTime(lc_calendar));
	}

	/**
	 * Obtiene un objeto Date desde un objeto LocalDateTime.
	 *
	 * @param aldt_fecha Objeto contenedor de la fecha
	 * @return Date resultante de la conversión
	 */
	protected Date obtenerDateDesdeLocalDateTime(LocalDateTime aldt_fecha)
	{
		return (aldt_fecha != null) ? Date.from((aldt_fecha.atZone(ZoneId.systemDefault())).toInstant()) : null;
	}

	/**
	 * Obtener datos anotacion intervinientes.
	 *
	 * @param aap_anotacionPredio de aap anotacion predio
	 * @param adm_manager de adm manager
	 * @return el valor de anotacion catastro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized AnotacionCatastro obtenerDatosAnotacionIntervinientes(
	    AnotacionPredio aap_anotacionPredio, DAOManager adm_manager
	)
	    throws B2BException
	{
		return obtenerDatosAnotacionIntervinientes(aap_anotacionPredio, true, adm_manager);
	}

	/**
	 * Obtener datos intervinientes.
	 *
	 * @param aap_anotacionPredio de aap anotacion predio
	 * @param ab_obtenerDatosAnotacion de ab obtener datos anotacion
	 * @param adm_manager de adm manager
	 * @return el valor de anotacion catastro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized AnotacionCatastro obtenerDatosAnotacionIntervinientes(
	    AnotacionPredio aap_anotacionPredio, boolean ab_obtenerDatosAnotacion, DAOManager adm_manager
	)
	    throws B2BException
	{
		return obtenerDatosAnotacionIntervinientes(aap_anotacionPredio, ab_obtenerDatosAnotacion, false, adm_manager);
	}

	/**
	 * Obtener datos intervinientes.
	 *
	 * @param aap_anotacionPredio de aap anotacion predio
	 * @param ab_obtenerDatosAnotacion de ab obtener datos anotacion
	 * @param ab_buscarPropietario the ab buscar propietario
	 * @param adm_manager de adm manager
	 * @return el valor de anotacion catastro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized AnotacionCatastro obtenerDatosAnotacionIntervinientes(
	    AnotacionPredio aap_anotacionPredio, boolean ab_obtenerDatosAnotacion, boolean ab_buscarPropietario,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		AnotacionCatastro lca_respuesta;

		lca_respuesta = null;

		try
		{
			if(aap_anotacionPredio != null)
			{
				String ls_idCirculo;
				Long   ll_idMatricula;
				Long   ll_idAnotacion;

				ls_idCirculo       = aap_anotacionPredio.getIdCirculo();
				ll_idMatricula     = aap_anotacionPredio.getIdMatricula();
				ll_idAnotacion     = aap_anotacionPredio.getIdAnotacion();

				if(
				    StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula)
					    && NumericUtils.isValidLong(ll_idAnotacion)
				)
				{
					Collection<AnotacionPredioCiudadano> lcapc_anotacionPredioCiudadano;
					NaturalezaJuridica                   lnj_naturalezaJuridica;
					PersonaDAO                           lpd_DAO;
					PropietarioDAO                       lptd_DAO;
					NaturalezaJuridicaDAO                lnjd_DAO;
					ParteInteresadaDAO                   lpid_DAO;
					InteresadoDocumentoTipoDAO           lidtd_DAO;
					TipoPersonaDAO                       ltpd_DAO;
					TipoRrrDAO                           ltrrrd_DAO;

					lpd_DAO                            = DaoCreator.getPersonaDAO(adm_manager);
					lptd_DAO                           = DaoCreator.getPropietarioDAO(adm_manager);
					lnjd_DAO                           = DaoCreator.getNaturalezaJuridicaDAO(adm_manager);
					lpid_DAO                           = DaoCreator.getParteInteresadaDAO(adm_manager);
					lidtd_DAO                          = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager);
					ltpd_DAO                           = DaoCreator.getTipoPersonaDAO(adm_manager);
					ltrrrd_DAO                         = DaoCreator.getTipoRrrDAO(adm_manager);
					lcapc_anotacionPredioCiudadano     = DaoCreator.getAnotacionPredioCiudadanoDAO(adm_manager)
							                                           .findByCirculoMatricula(
							    ls_idCirculo, NumericUtils.getLong(ll_idMatricula), NumericUtils.getLong(
							        ll_idAnotacion
							    )
							);
					lnj_naturalezaJuridica             = lnjd_DAO.findById(
						    aap_anotacionPredio.getIdNaturalezaJuridica(), aap_anotacionPredio.getVersion()
						);

					if(CollectionUtils.isValidCollection(lcapc_anotacionPredioCiudadano))
					{
						Collection<PropietarioCatastro> lcic_intervinientes;
						Collection<Propietario>         lcp_propietarios;

						lcic_intervinientes     = new ArrayList<PropietarioCatastro>();
						lcp_propietarios        = null;

						if(ab_buscarPropietario)
							lcp_propietarios = lptd_DAO.findByIdCirculoMatriculaAnotacion(
								    ls_idCirculo, NumericUtils.getLong(ll_idMatricula),
								    NumericUtils.getLong(ll_idAnotacion)
								);

						for(AnotacionPredioCiudadano lpc_actual : lcapc_anotacionPredioCiudadano)
						{
							if(lpc_actual != null)
							{
								String ls_idPersona;

								ls_idPersona = lpc_actual.getIdPersona();

								if(ab_buscarPropietario)
								{
									if(CollectionUtils.isValidCollection(lcp_propietarios))
									{
										boolean               lb_encontroPropietario;
										Iterator<Propietario> lip_ip;

										lb_encontroPropietario     = false;
										lip_ip                     = lcp_propietarios.iterator();

										while(lip_ip.hasNext() && !lb_encontroPropietario)
										{
											Propietario lp_propietario;

											lp_propietario = lip_ip.next();

											if(lp_propietario != null)
											{
												String ls_idPersonaPropietario;

												ls_idPersonaPropietario = lp_propietario.getIdPersona();

												if(
												    StringUtils.isValidString(ls_idPersonaPropietario)
													    && ls_idPersonaPropietario.equalsIgnoreCase(ls_idPersona)
												)
													lb_encontroPropietario = true;
											}
										}

										if(!lb_encontroPropietario)
											ls_idPersona = null;
									}
								}

								if(StringUtils.isValidString(ls_idPersona))
								{
									Persona lp_persona;

									lp_persona = lpd_DAO.findById(ls_idPersona);

									if(lp_persona != null)
									{
										InteresadoDocumentoTipo lidt_interesadoDocumentoTipo;
										ParteInteresada         lpi_parteInteresada;
										TipoPersona             lidt_tipoPersona;
										String                  ls_descripcionRRR;

										lpi_parteInteresada              = lpid_DAO.findById(
											    lpc_actual.getIdInteresadaRrr()
											);
										lidt_interesadoDocumentoTipo     = lidtd_DAO.findById(
											    lp_persona.getIdDocumentoTipo()
											);
										lidt_tipoPersona                 = ltpd_DAO.findById(
											    new TipoPersona(lp_persona.getIdTipoPersona())
											);
										ls_descripcionRRR                = null;

										if(lnj_naturalezaJuridica != null)
										{
											TipoRrr ltrrr_rrr;

											ltrrr_rrr = ltrrrd_DAO.findById(lnj_naturalezaJuridica.getIdtipoRrr());

											if(ltrrr_rrr != null)
												ls_descripcionRRR = ltrrr_rrr.getDescripcion();
										}

										lcic_intervinientes.add(
										    new PropietarioCatastro(
										        ((lidt_tipoPersona != null)
										        ? StringUtils.getStringNotNull(lidt_tipoPersona.getDescripcion()) : null),
										        ((lidt_interesadoDocumentoTipo != null)
										        ? StringUtils.getStringNotNull(
										            lidt_interesadoDocumentoTipo.getDescripcion()
										        ) : null), lp_persona.getNumeroDocumento(), lp_persona.getPrimerNombre(),
										        lp_persona.getSegundoNombre(), lp_persona.getPrimerApellido(),
										        lp_persona.getSegundoApellido(), lp_persona.getRazonSocial(),
										        lpc_actual.getRolPersona(),
										        ((lpi_parteInteresada != null)
										        ? StringUtils.getStringNotNull(lpi_parteInteresada.getDescripcion())
										        : null), ls_descripcionRRR, lpc_actual.getPorcentajeParticipacion(),
										        lp_persona.getIdNaturalGenero(), lpc_actual.getPropietario()
										    )
										);
									}
								}
							}
						}

						if(ab_obtenerDatosAnotacion)
						{
							Calendar lc_fechaCreacion;
							String   ls_idNaturalezaJuridica;
							String   ls_nombreNaturaleza;
							String   ls_descripcionDominioRRR;
							String   ls_nir;
							String   is_codDominioActoJuridico;
							String   ls_nomDominioActoJuridico;

							is_codDominioActoJuridico     = null;
							ls_nomDominioActoJuridico     = null;
							ls_idNaturalezaJuridica       = null;
							ls_nombreNaturaleza           = null;
							ls_descripcionDominioRRR      = null;
							ls_nir                        = null;

							if(lnj_naturalezaJuridica != null)
							{
								DominioRrr ldr_dominioRRR;
								String     ls_dominioRRR;
								String     ls_tipoRRR;

								ls_dominioRRR                = StringUtils.getStringNotNull(
									    lnj_naturalezaJuridica.getIdDominioRrr()
									);
								ls_tipoRRR                   = StringUtils.getStringNotNull(
									    lnj_naturalezaJuridica.getIdtipoRrr()
									);
								ls_idNaturalezaJuridica      = StringUtils.getStringNotNull(
									    lnj_naturalezaJuridica.getIdNaturalezaJuridica()
									);
								ls_nombreNaturaleza          = StringUtils.getStringNotNull(
									    lnj_naturalezaJuridica.getNombre()
									);
								ldr_dominioRRR               = DaoCreator.getDominioRrrDAO(adm_manager)
										                                     .findByIdTipo(ls_dominioRRR, ls_tipoRRR);
								ls_descripcionDominioRRR     = (ldr_dominioRRR != null)
									? StringUtils.getStringNotNull(ldr_dominioRRR.getDescripcion()) : null;

								{
									DominioNaturalezaJuridica ldnj_dominioNaturalezaJuridica;

									ldnj_dominioNaturalezaJuridica = DaoCreator.getDominioNaturalezaJuridicaDAO(
										    adm_manager
										).findById(lnj_naturalezaJuridica.getIdDominioNatJur());

									if(ldnj_dominioNaturalezaJuridica != null)
									{
										ls_nomDominioActoJuridico     = ldnj_dominioNaturalezaJuridica
												.getIdDominioNatJur();
										ls_nomDominioActoJuridico     = ldnj_dominioNaturalezaJuridica.getNombre();
									}
								}
							}

							lc_fechaCreacion     = obtenerCalendarDesdeDate(aap_anotacionPredio.getFechaRegistro());

							ls_nir = DaoCreator.getSolicitudDAO(adm_manager)
									               .findNirByIdTurno(aap_anotacionPredio.getRadicacion());

							String ls_codTipoDocumentoPublico;
							String ls_nomDocumentoPublico;
							Date   ld_fechaDocumento;

							ls_codTipoDocumentoPublico     = null;
							ls_nomDocumentoPublico         = null;
							ld_fechaDocumento              = null;

							{
								Documento ld_documento;

								ld_documento = DaoCreator.getDocumentoDAO(adm_manager)
										                     .findByIdDocumentoVersionNombres(
										    aap_anotacionPredio.getIdDocumento(),
										    aap_anotacionPredio.getVersionDocumento()
										);

								if(ld_documento != null)
								{
									ls_codTipoDocumentoPublico     = ld_documento.getIdTipoDocumento();
									ls_nomDocumentoPublico         = ld_documento.getNombreTipoDocumento();
									ld_fechaDocumento              = ld_documento.getFechaDocumento();
								}
							}

							lca_respuesta = new AnotacionCatastro(
								    StringUtils.getString(aap_anotacionPredio.getOrden()),
								    StringUtils.getStringNotNull(aap_anotacionPredio.getComentario()), lc_fechaCreacion,
								    ls_idNaturalezaJuridica, ls_nombreNaturaleza, ls_descripcionDominioRRR,
								    lcic_intervinientes, aap_anotacionPredio.getRadicacion(), ls_nir,
								    aap_anotacionPredio.getIdEstadoAnotacion(), aap_anotacionPredio.getEspecificacion(),
								    is_codDominioActoJuridico, ls_nomDominioActoJuridico, ls_codTipoDocumentoPublico,
								    ls_nomDocumentoPublico, obtenerCalendarDesdeDate(ld_fechaDocumento)
								);
						}
						else
							lca_respuesta = new AnotacionCatastro(lcic_intervinientes);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerDatosAnotacionIntervinientes", lb2be_e);
		}

		return lca_respuesta;
	}

	/**
	 * Obtener error servicios.
	 *
	 * @param as_message de as message
	 * @return el valor de map
	 */
	protected Map<String, String> obtenerErrorServicios(String as_message)
	{
		Map<String, String> lmss_llaveDescripcion;

		lmss_llaveDescripcion = new HashMap<String, String>();

		if(StringUtils.isValidString(as_message))
		{
			long   ll_key;
			String ls_key;
			String ls_subkey;
			ls_subkey     = as_message.substring(0, 3);
			ls_key        = as_message.substring(5);
			ll_key        = NumericUtils.getLong(ls_subkey, ConstanteCommon.DEFAULT_ID);

			lmss_llaveDescripcion.put(
			    IdentificadoresCommon.CODIGO_MENSAJE,
			    String.valueOf((ll_key == ConstanteCommon.DEFAULT_ID) ? CodigoMensajeCommon.CODIGO_409 : ll_key)
			);

			{
				String ls_tmp;

				if(ll_key == ConstanteCommon.DEFAULT_ID)
				{
					ls_tmp = addMessage(as_message, null, true, null);

					if(!StringUtils.isValidString(ls_tmp))
						ls_tmp = as_message;
				}
				else
					ls_tmp = ls_key.trim();

				lmss_llaveDescripcion.put(IdentificadoresCommon.DESCRIPCION_MENSAJE, ls_tmp);
			}
		}
		else
		{
			lmss_llaveDescripcion.put(
			    IdentificadoresCommon.CODIGO_MENSAJE, String.valueOf(CodigoMensajeCommon.CODIGO_500)
			);
			lmss_llaveDescripcion.put(IdentificadoresCommon.DESCRIPCION_MENSAJE, as_message);
		}

		return lmss_llaveDescripcion;
	}

	/**
	 * Consulta la información de una zona registral.
	 *
	 * @param ap_datos Objeto donde se almacenará la información consultada
	 * @param as_idZonaRegistral Id de la zona registral a consultar
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	protected void obtenerInfoZonaRegistralPredio(Predio ap_datos, String as_idZonaRegistral, DAOManager adm_manager)
	    throws B2BException
	{
		if(ap_datos != null)
		{
			ZonaRegistral lzr_zonaRegistral;

			lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(adm_manager).findById(as_idZonaRegistral);

			if(lzr_zonaRegistral != null)
			{
				ap_datos.setCodDepartamento(lzr_zonaRegistral.getIdDepartamento());
				ap_datos.setCodMunicipio(lzr_zonaRegistral.getIdMunicipio());
				ap_datos.setCodCirculoRegistral(lzr_zonaRegistral.getIdCirculo());
				ap_datos.setIdPais(lzr_zonaRegistral.getIdPais());
				ap_datos.setIdVereda(lzr_zonaRegistral.getIdVereda());
			}
		}
	}

	/**
	 * Obtiene un objeto LocalDateTime a partir de un objeto Date.
	 *
	 * @param ad_fecha Objeto contenedor de la fecha
	 * @return LocalDateTime resultante de la conversión
	 */
	protected LocalDateTime obtenerLocalDateTime(Date ad_fecha)
	{
		return (ad_fecha != null) ? LocalDateTime.ofInstant(ad_fecha.toInstant(), ZoneId.systemDefault()) : null;
	}

	/**
	 * Obtiene un objeto LocalDateTime a partir de un objeto Calendar.
	 *
	 * @param ac_fecha Objeto contenedor de la fecha
	 * @return LocalDateTime resultante de la conversión
	 */
	protected LocalDateTime obtenerLocalDateTime(Calendar ac_fecha)
	{
		return (ac_fecha != null) ? LocalDateTime.ofInstant(ac_fecha.toInstant(), ZoneOffset.UTC) : null;
	}

	/**
	 * Obtener matriculas by solicitud circulo.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idCirculo de as id circulo
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized String obtenerMatriculasBySolicitudCirculo(
	    String as_idSolicitud, String as_idCirculo, DAOManager adm_manager
	)
	    throws B2BException
	{
		return obtenerMatriculasBySolicitudCirculo(as_idSolicitud, as_idCirculo, false, adm_manager);
	}

	/**
	 * Obtener matriculas by solicitud circulo.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @param as_idCirculo de id circulo
	 * @param ab_separadorComa true se el separador es ',' de lo contrario sera ';'
	 * @param adm_manager de DAOManager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized String obtenerMatriculasBySolicitudCirculo(
	    String as_idSolicitud, String as_idCirculo, boolean ab_separadorComa, DAOManager adm_manager
	)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			Collection<SolicitudMatricula> lcsm_matriculas;
			StringBuilder                  lsb_matriculas;

			lcsm_matriculas     = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
					                            .findByIdSolicitudCirculo(as_idSolicitud, as_idCirculo);
			lsb_matriculas      = new StringBuilder();

			if(CollectionUtils.isValidCollection(lcsm_matriculas))
			{
				for(SolicitudMatricula lsm_actual : lcsm_matriculas)
				{
					if(lsm_actual != null)
						lsb_matriculas.append(
						    lsm_actual.getIdCirculo() + IdentificadoresCommon.SIMBOLO_GUION
						    + lsm_actual.getIdMatricula()
						    + ((ab_separadorComa) ? IdentificadoresCommon.SIMBOLO_COMA_TXT
						                          : IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT)
						);
				}
			}

			String ls_matriculas;

			ls_matriculas = lsb_matriculas.toString();

			if(
			    ls_matriculas.endsWith(
				        (ab_separadorComa) ? IdentificadoresCommon.SIMBOLO_COMA_TXT
				                               : IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
				    )
			)
				ls_matriculas = ls_matriculas.substring(0, ls_matriculas.length() - 1);

			if(StringUtils.isValidString(ls_matriculas))
				ls_return = ls_matriculas;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerMatriculas", lb2be_e);
			throw lb2be_e;
		}

		return ls_return;
	}

	/**
	 * Obtener nombre persona.
	 *
	 * @param as_idPersona de as id persona
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String obtenerNombrePersona(String as_idPersona, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		{
			Persona lp_persona;

			lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(as_idPersona);

			if(lp_persona != null)
			{
				String ls_tipoDocumento;

				ls_tipoDocumento = StringUtils.getStringNotNull(lp_persona.getIdDocumentoTipo());

				if(ls_tipoDocumento.equals(IdentificadoresCommon.NIT))
					ls_return = lp_persona.getRazonSocial();
				else
				{
					StringBuilder lsb_nombre;

					lsb_nombre = new StringBuilder();

					agregarParteDeNombre(lsb_nombre, lp_persona.getPrimerNombre());
					agregarParteDeNombre(lsb_nombre, lp_persona.getSegundoNombre());
					agregarParteDeNombre(lsb_nombre, lp_persona.getPrimerApellido());
					agregarParteDeNombre(lsb_nombre, lp_persona.getSegundoApellido());

					ls_return = lsb_nombre.toString();
				}
			}
		}

		return ls_return;
	}

	/**
	 * Obtener nombre persona oficina origen.
	 *
	 * @param as_idPersona correspondiente al valor de as id persona
	 * @param adm_manager correspondiente al valor de adm manager
	 * @param as_idDocumento correspondiente al valor de as id documento
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String obtenerNombrePersonaOficinaOrigen(
	    String as_idPersona, DAOManager adm_manager, String as_idDocumento
	)
	    throws B2BException
	{
		return obtenerNombrePersonaOficinaOrigen(as_idPersona, adm_manager, as_idDocumento, false);
	}

	/**
	 * Obtener nombre persona oficina origen.
	 *
	 * @param as_idPersona correspondiente al valor de as id persona
	 * @param adm_manager correspondiente al valor de adm manager
	 * @param as_idDocumento correspondiente al valor de as id documento
	 * @param ab_direccion the ab direccion
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String obtenerNombrePersonaOficinaOrigen(
	    String as_idPersona, DAOManager adm_manager, String as_idDocumento, boolean ab_direccion
	)
	    throws B2BException
	{
		String        ls_return;
		Documento     ld_documento;
		OficinaOrigen loo_oficinaOrigen;

		ls_return             = null;
		loo_oficinaOrigen     = null;
		ld_documento          = DaoCreator.getDocumentoDAO(adm_manager).findById(as_idDocumento);

		if(ld_documento != null)
			loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
					                          .findByIdWithMaxVersion(ld_documento.getIdOficinaOrigen());

		if(loo_oficinaOrigen != null)
		{
			if(loo_oficinaOrigen.getNotificarCorrespondencia().equals(IdentificadoresCommon.S))
				ls_return = loo_oficinaOrigen.getNombre();
			else
			{
				Persona lp_persona;

				lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(as_idPersona);

				if(lp_persona != null)
				{
					String ls_tipoDocumento;

					ls_tipoDocumento = StringUtils.getStringNotNull(lp_persona.getIdDocumentoTipo());

					if(ls_tipoDocumento.equals(IdentificadoresCommon.NIT))
						ls_return = lp_persona.getRazonSocial();
					else
					{
						StringBuilder lsb_nombre;

						lsb_nombre = new StringBuilder();

						agregarParteDeNombre(lsb_nombre, lp_persona.getPrimerNombre());
						agregarParteDeNombre(lsb_nombre, lp_persona.getSegundoNombre());
						agregarParteDeNombre(lsb_nombre, lp_persona.getPrimerApellido());
						agregarParteDeNombre(lsb_nombre, lp_persona.getSegundoApellido());

						ls_return = lsb_nombre.toString();
					}
				}
			}

			if(ab_direccion)
				ls_return = loo_oficinaOrigen.getDireccion();
		}

		return ls_return;
	}

	/**
	 * Obtener plantilla de constante.
	 *
	 * @param as_nombrePlantilla de as nombre plantilla
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized String obtenerPlantillaDeConstante(String as_nombrePlantilla, DAOManager adm_manager)
	    throws B2BException
	{
		String     ls_plantilla;
		Constantes lc_constante;

		lc_constante = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(as_nombrePlantilla);

		if(lc_constante != null)
		{
			byte[] lba_plantilla;

			lba_plantilla = lc_constante.getImagenes();

			if(lba_plantilla == null)
				throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE);

			ls_plantilla = new String(lba_plantilla);
		}
		else
		{
			Object[] loa_args;

			loa_args     = new String[1];

			loa_args[0] = as_nombrePlantilla;

			throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
		}

		return ls_plantilla;
	}

	/**
	 * Busca la constante y extrae su imagen para ser tratada como una cadena de caracteres.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idConstante Id de la constante a buscar
	 * @return Cadena de caracteres con el contenido de la imagen de la constante
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	protected String obtenerPlantillaDeConstante(DAOManager adm_manager, String as_idConstante)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_idConstante))
		{
			Constantes lc_constante;

			lc_constante = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(as_idConstante);

			if(lc_constante == null)
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = as_idConstante;

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
			}

			{
				byte[] lba_return;

				lba_return = lc_constante.getImagenes();

				if(!ByteArrayUtils.isValidArray(lba_return))
				{
					Object[] loa_messageArgs = new String[1];
					loa_messageArgs[0] = as_idConstante;

					throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
				}
				else
					ls_return = new String(lba_return);
			}
		}
		else
		{
			Object[] loa_messageArgs;

			loa_messageArgs        = new String[1];
			loa_messageArgs[0]     = as_idConstante;

			throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
		}

		return ls_return;
	}

	/**
	 * Obtiene un turno asociado a una solicitud.
	 *
	 * @param as_idSolicitud id de la slicitud a utilizar como filtro en la busqueda
	 * @param adm_manager Conexión a la base de datos
	 * @return Turno resultante de la busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String obtenerTurnoDeSolicitud(String as_idSolicitud, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			Collection<Turno> lct_turnos;

			lct_turnos = DaoCreator.getTurnoDAO(adm_manager).findByIdSolicitud(as_idSolicitud);

			if(CollectionUtils.isValidCollection(lct_turnos))
			{
				Iterator<Turno> lit_iterador;
				boolean         lb_found;

				lit_iterador     = lct_turnos.iterator();
				lb_found         = false;

				while(lit_iterador.hasNext() && !lb_found)
				{
					Turno lt_turno;

					lt_turno = lit_iterador.next();

					if(lt_turno != null)
					{
						String ls_idTurno;

						ls_idTurno = lt_turno.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							ls_return     = ls_idTurno;

							lb_found = true;
						}
					}
				}
			}
		}

		return ls_return;
	}

	/**
	 * Consulta los turnos que estén bloqueando una matrícula.
	 *
	 * @param as_idTurno Id del turno a consultar las matrículas
	 * @param adm_manager Conexión a la base de datos
	 * @return Colección de turnos contenidos en cadenas de caracteres
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String obtenerTurnosBitacoraBloqueo(String as_idTurno, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		{
			Collection<String> lcs_turnosBloqueo;

			lcs_turnosBloqueo = obtenerColeccionTurnosBitacoraBloqueo(as_idTurno, adm_manager);

			if(CollectionUtils.isValidCollection(lcs_turnosBloqueo))
			{
				Iterator<String> lis_iterador;
				StringBuilder    lsb_turnos;

				lis_iterador     = lcs_turnosBloqueo.iterator();
				lsb_turnos       = new StringBuilder();

				while(lis_iterador.hasNext())
				{
					String ls_turno;

					ls_turno = lis_iterador.next();

					if(StringUtils.isValidString(ls_turno))
					{
						lsb_turnos.append(ls_turno);

						if(lis_iterador.hasNext())
							lsb_turnos.append(IdentificadoresCommon.SIMBOLO_COMA);
					}
				}

				ls_return = lsb_turnos.toString();
			}
		}

		return ls_return;
	}

	/**
	 * Obtener turnos job.
	 *
	 * @param as_idConstanteInvoke ID de la constante de invocación del Job a ejecutar
	 * @param as_idConstanteLimiteIntentos ID de la constante de limite de intentos del Job a ejecutar
	 * @param al_idEtapa de al id etapa ID de la etapa de donde se van a buscar los casos a procesar
	 * @param adm_manager de adm manager Conexión a la base de datos
	 * @return Colección de turno historia resultante de la busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized Collection<TurnoHistoria> obtenerTurnosJob(
	    String as_idConstanteInvoke, String as_idConstanteLimiteIntentos, long al_idEtapa, DAOManager adm_manager
	)
	    throws B2BException
	{
		return obtenerTurnosJob(as_idConstanteInvoke, as_idConstanteLimiteIntentos, al_idEtapa, false, adm_manager);
	}

	/**
	 * Obtener turnos job.
	 *
	 * @param as_idConstanteInvoke ID de la constante de invocación del Job a ejecutar
	 * @param as_idConstanteLimiteIntentos ID de la constante de limite de intentos del Job a ejecutar
	 * @param al_idEtapa de al id etapa ID de la etapa de donde se van a buscar los casos a procesar
	 * @param ab_documentoEnviado indica si se deben buscar casos con un documento salida ya enviado a owcc
	 * @param adm_manager de adm manager Conexión a la base de datos
	 * @return Colección de turno historia resultante de la busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized Collection<TurnoHistoria> obtenerTurnosJob(
	    String as_idConstanteInvoke, String as_idConstanteLimiteIntentos, long al_idEtapa, boolean ab_documentoEnviado,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_return;

		lcth_return = null;

		{
			ConstantesDAO lcd_constantesDAO;
			Constantes    lc_constant;

			lcd_constantesDAO     = DaoCreator.getConstantesDAO(adm_manager);
			lc_constant           = lcd_constantesDAO.findById(as_idConstanteInvoke);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
			{
				long ll_cantidadIntentos;

				ll_cantidadIntentos     = lcd_constantesDAO.findEnteroById(as_idConstanteLimiteIntentos);

				lcth_return = DaoCreator.getTurnoHistoriaDAO(adm_manager)
						                    .findAllByEtapaAut(al_idEtapa, ll_cantidadIntentos, ab_documentoEnviado);
			}
		}

		return lcth_return;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para realizar el proceso de liquidación.
	 *
	 * @param al_liquidacion Argumento de tipo Liquidacion que contiene los datos necesarios
	 * para realizar la liquidación.
	 * @param adm_manager Argumento de tipo DAOManager que permite realizar la conexión a la base de datos.
	 * @param as_userId Argumento de tipo String que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo String que contiene el la ip desde donde se realiza la operación.
	 * @return Objeto de tipo Registro que contiene los archivos de la liquidación generados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	protected synchronized Registro procLiquidacion(
	    Liquidacion al_liquidacion, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		Registro lr_parametros;

		lr_parametros = new Registro();

		try
		{
			if(al_liquidacion != null)
			{
				Solicitud as_s;

				as_s = al_liquidacion.getSolicitud();

				if(as_s != null)
				{
					Collection<Liquidacion> lcl_tmp;
					ActoDAO                 la_DAO;
					boolean                 lb_detalleReproduccion;

					la_DAO                     = DaoCreator.getActoDAO(adm_manager);
					lcl_tmp                    = as_s.getInfoLiquidacion();
					lb_detalleReproduccion     = true;

					if(CollectionUtils.isValidCollection(lcl_tmp))
					{
						for(Liquidacion lol_tmp : lcl_tmp)
						{
							if(lol_tmp != null)
							{
								lb_detalleReproduccion = lol_tmp.isDetalleReproduccionConstancia();

								if(!lol_tmp.isSinCantidadActos())
								{
									Acto ldp_tempDatos;
									Long ll_cantidadActos;

									ll_cantidadActos     = lol_tmp.getCantidadActos();
									ldp_tempDatos        = new Acto();

									if(
									    NumericUtils.isValidLong(ll_cantidadActos)
										    && (NumericUtils.getLong(ll_cantidadActos) > 0)
									)
									{
										ldp_tempDatos.setIdActo(lol_tmp.getIdActo());
										ldp_tempDatos.setCantidadActos(
										    NumericUtils.getBigDecimal(NumericUtils.getLong(ll_cantidadActos))
										);
										ldp_tempDatos.setIdUsuarioCreacion(as_userId);
										ldp_tempDatos.setIpCreacion(as_remoteIp);
										ldp_tempDatos.setIdUsuarioModificacion(as_userId);
										ldp_tempDatos.setIpModificacion(as_remoteIp);

										la_DAO.updateCantidadActos(ldp_tempDatos, true);
									}
									else
									{
										Object[] aoa_messageArgs = new String[1];

										aoa_messageArgs[0] = lol_tmp.getNombreCirculo();

										throw new B2BException(ErrorKeys.CANTIDAD_ACTOS_INVALIDO, aoa_messageArgs);
									}
								}
							}
						}

						adm_manager.commit();
					}

					{
						String ls_usuarioCreacion;
						Turno  lt_turno;
						String ls_idCondicion;

						ls_idCondicion         = StringUtils.getStringNotNull(al_liquidacion.getIdCondicion());
						ls_usuarioCreacion     = as_s.getIdUsuarioCreacion();
						lt_turno               = DaoCreator.getTurnoDAO(adm_manager)
								                               .findById(al_liquidacion.getIdTurno());

						if(ls_idCondicion.equalsIgnoreCase(IdentificadoresCommon.PRELIQUIDAR))
						{
							Collection<Liquidacion> lcl_liquidacionItem;
							lcl_liquidacionItem = al_liquidacion.getLiquidacionItemCondicion();

							if(CollectionUtils.isValidCollection(lcl_liquidacionItem))
								DaoCreator.getAccLiquidacionDAO(adm_manager)
									          .actualizaCondiciones(lcl_liquidacionItem, false, as_userId, as_remoteIp);
						}

						if(lt_turno == null)
							al_liquidacion.setIdTurno(null);

						al_liquidacion.setIdSolicitud(as_s.getIdSolicitud());
						al_liquidacion.setIdUsuarioCreacion(ls_usuarioCreacion);
						al_liquidacion.setIpCreacion(as_remoteIp);

						al_liquidacion = DaoCreator.getProcedimientosDAO(adm_manager).procLiquidacion(al_liquidacion);

						if(al_liquidacion != null)
						{
							String ls_retorno;

							ls_retorno = al_liquidacion.getCodigoRespuesta();

							if(StringUtils.getStringNotNull(ls_retorno).equalsIgnoreCase("0"))
							{
								as_s = DaoCreator.getSolicitudDAO(adm_manager).findById(as_s);

								if(!ls_idCondicion.equalsIgnoreCase(IdentificadoresCommon.LIQUIDAR))
								{
									lr_parametros.setIdUsuarioCreacion(ls_usuarioCreacion);
									lr_parametros.setSolicitud(as_s);
									lr_parametros.setIdCondicion(ls_idCondicion);
									lr_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);
									lr_parametros.setProcesoCertificados(al_liquidacion.isProcesoCertificados());
									clh_LOGGER.error(
									    "procLiquidacion", "se inicia creación del documento ReciboLiquidacion"
									);
									lr_parametros = getRegistroBusiness()
											                .generarReciboLiquidacion(
											    lr_parametros, true, ls_usuarioCreacion, null, null, adm_manager
											);
									clh_LOGGER.error(
									    "procLiquidacion", "se termina creación del documento ReciboLiquidacion"
									);
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
						}
					}

					{
						String ls_idSolicitud;

						ls_idSolicitud = as_s.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Collection<Liquidacion> lc_tmp;
							String                  ls_idCondicion;

							lc_tmp             = new ArrayList<Liquidacion>();
							ls_idCondicion     = (al_liquidacion != null)
								? StringUtils.getStringNotNull(al_liquidacion.getIdCondicion()) : new String();

							if(ls_idCondicion.equalsIgnoreCase(IdentificadoresCommon.LIQUIDAR))
								lc_tmp = DaoCreator.getAccLiquidacionDAO(adm_manager)
										               .liquidacionReproduccionConstanciaTerminar(
										    ls_idSolicitud, false
										);
							else
								lc_tmp = DaoCreator.getAccLiquidacionDAO(adm_manager)
										               .liquidacionReproduccionConstancia(ls_idSolicitud, false);

							if(CollectionUtils.isValidCollection(lc_tmp))
							{
								for(Liquidacion lol_tmp : lc_tmp)
								{
									if(lol_tmp != null)
									{
										if(lb_detalleReproduccion)
											lol_tmp.setSinCantidadActos(lb_detalleReproduccion);
										else
											lol_tmp.setSinCantidadActos(
											    DaoCreator.getAccLiquidacionItemDAO(adm_manager)
												              .findEjecucionLiquidacion(lol_tmp)
											);
									}
								}
							}

							lr_parametros.setInfoLiquidacionRepConstancia(lc_tmp);
						}
					}

					lr_parametros.setNuevaSolicitud(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procLiquidacion", lb2be_e);

			throw lb2be_e;
		}

		return lr_parametros;
	}

	/**
	 * Método encargado de realizar la lógica del procedimiento procMatriculaInformacion.
	 *
	 * @param arc_data Objeto que contiene la información del proceso.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param al_idTurnoHistoria Variable que contiene el id del turno historia.
	 * @param ab_devolucion Variable boolean que valida si es una devolución.
	 * @param ab_segregacionAnotacion Variable boolean que valida si es una apertura de matrículas a partir de una anotación.
	 * @param ab_circuloMatricula the ab circulo matricula
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param adm_manager DAOManager que controla la transacción.
	 * @return Objeto que contiene la información del proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized RegistroCalificacion procMatriculasInformacion(
	    RegistroCalificacion arc_data, String as_idTurno, Long al_idTurnoHistoria, boolean ab_devolucion,
	    boolean ab_segregacionAnotacion, boolean ab_circuloMatricula, String as_userId, String as_remoteIp,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_idTurno) && NumericUtils.isValidLong(al_idTurnoHistoria))
			{
				Collection<AreaPredio> lcap_ap;
				DireccionPredioAccDAO  lodp_DAO;

				lodp_DAO     = DaoCreator.getDireccionPredioAccDAO(adm_manager);
				lcap_ap      = new ArrayList<AreaPredio>();

				arc_data.setIdTurnoHistoria(al_idTurnoHistoria);
				arc_data.setIdUsuarioModificacion(as_userId);
				arc_data.setIpModificacion(as_remoteIp);

				if(ab_segregacionAnotacion)
					DaoCreator.getProcedimientosDAO(adm_manager).crearMariculasInformacionAnotacion(arc_data);
				else
					DaoCreator.getProcedimientosDAO(adm_manager).crearMariculasInformacion(arc_data);

				if(ab_devolucion)
					lcap_ap = DaoCreator.getAccPredioRegistroDAO(adm_manager).findMatriculasInfByTurno(as_idTurno);
				else if(ab_segregacionAnotacion)
					lcap_ap = DaoCreator.getAccPredioRegistroDAO(adm_manager)
							                .findMatriculasInformacion(
							    al_idTurnoHistoria, arc_data.getIdCirculo(), arc_data.getIdMatricula(),
							    arc_data.getIdAnotacionApertura()
							);
				else if(ab_circuloMatricula)
					lcap_ap = DaoCreator.getAccPredioRegistroDAO(adm_manager)
							                .findMatriculasInformacion(
							    al_idTurnoHistoria, arc_data.getIdCirculo(), arc_data.getIdMatricula(), null
							);
				else
					lcap_ap = DaoCreator.getAccPredioRegistroDAO(adm_manager)
							                .findMatriculasInformacion(al_idTurnoHistoria);

				if(!ab_devolucion && CollectionUtils.isValidCollection(lcap_ap))
				{
					for(AreaPredio loap_tmp : lcap_ap)
					{
						if(loap_tmp != null)
						{
							long               ll_Matricula;
							DireccionPredioAcc lodpacc_tmp;
							DireccionPredioAcc lodp_tmp;
							String             ls_nombrePredio;
							String             ls_complemento;
							String             ls_circulo;

							lodp_tmp            = new DireccionPredioAcc();
							ll_Matricula        = loap_tmp.getIdMatricula();
							ls_circulo          = loap_tmp.getIdCirculo();
							ls_nombrePredio     = StringUtils.getStringNotNull(loap_tmp.getNombrePredio());
							ls_complemento      = loap_tmp.getComplementoDireccion() + " " + ls_nombrePredio;

							lodp_tmp.setIdCirculo(ls_circulo);
							lodp_tmp.setIdMatricula(NumericUtils.getLongWrapper(ll_Matricula));
							lodp_tmp.setIdTurno(as_idTurno);

							lodpacc_tmp = lodp_DAO.findByIdCirculoMatriculaTurno(lodp_tmp);

							if(lodpacc_tmp != null)
							{
								String ls_direccionCompleta;

								ls_direccionCompleta = lodpacc_tmp.getDireccion();

								lodpacc_tmp.setDireccion(ls_complemento);
								lodpacc_tmp.setIdTurno(as_idTurno);
								lodpacc_tmp.setIdTurnoHistoria(al_idTurnoHistoria);
								lodpacc_tmp.setIdUsuarioModificacion(as_userId);
								lodpacc_tmp.setIpModificacion(as_remoteIp);
								lodpacc_tmp.setComplementoDireccion(ls_nombrePredio);

								if(
								    StringUtils.isValidString(ls_direccionCompleta)
									    && (!ls_direccionCompleta.contains(ls_nombrePredio))
								)
									lodp_DAO.updateById(lodpacc_tmp, true);
							}
						}
					}

					if(ab_devolucion)
						lcap_ap = DaoCreator.getAccPredioRegistroDAO(adm_manager).findMatriculasInfByTurno(as_idTurno);
					else if(ab_segregacionAnotacion)
						lcap_ap = DaoCreator.getAccPredioRegistroDAO(adm_manager)
								                .findMatriculasInformacion(
								    al_idTurnoHistoria, arc_data.getIdCirculo(), arc_data.getIdMatricula(),
								    arc_data.getIdAnotacionApertura()
								);
					else if(ab_circuloMatricula)
						lcap_ap = DaoCreator.getAccPredioRegistroDAO(adm_manager)
								                .findMatriculasInformacion(
								    al_idTurnoHistoria, arc_data.getIdCirculo(), arc_data.getIdMatricula(), null
								);
					else
						lcap_ap = DaoCreator.getAccPredioRegistroDAO(adm_manager)
								                .findMatriculasInformacion(al_idTurnoHistoria);
				}

				if(CollectionUtils.isValidCollection(lcap_ap))
					arc_data.setMatriculasInformacion(lcap_ap);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procMatriculasInformacion", lb2be_e);

			throw lb2be_e;
		}

		return arc_data;
	}

	/**
	 * Método encargado de poner el bookMark.
	 *
	 * @param as_plantilla String que contiene la plantilla.
	 * @param as_tag String que contiene el tag.
	 * @param as_nombre String que contiene el nombre del bookMark.
	 * @param ac_constante Objeto que contiene la constante.
	 * @return el valor de string
	 */
	protected String putCustomBookMark(String as_plantilla, String as_tag, String as_nombre, Constantes ac_constante)
	{
		return putCustomBookMark(as_plantilla, as_tag, as_nombre, ac_constante, false);
	}

	/**
	 * Método encargado de poner el bookMark.
	 *
	 * @param as_plantilla String que contiene la plantilla.
	 * @param as_tag String que contiene el tag.
	 * @param as_nombre String que contiene el nombre del bookMark.
	 * @param ac_constante Objeto que contiene la constante.
	 * @param ab_saltoLinea boolean que valida si debe tener salto de linea el bookmark.
	 * @return el valor de string
	 */
	protected String putCustomBookMark(
	    String as_plantilla, String as_tag, String as_nombre, Constantes ac_constante, boolean ab_saltoLinea
	)
	{
		if(
		    StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_tag)
			    && StringUtils.isValidString(as_nombre) && (ac_constante != null)
		)
		{
			String ls_caracter;

			ls_caracter = ac_constante.getCaracter();

			if(as_plantilla.contains(as_tag))
			{
				if(StringUtils.isValidString(ls_caracter))
					as_plantilla = as_plantilla.replace(as_tag, ls_caracter);
			}

			if(StringUtils.isValidString(ls_caracter))
			{
				int li_finalTag;

				li_finalTag = as_plantilla.lastIndexOf(ls_caracter);

				if(li_finalTag > 0)
				{
					String ls_textoMitadSuperior;
					String ls_textoMitadInferior;
					String ls_tagNew;

					ls_textoMitadSuperior     = as_plantilla.substring(0, li_finalTag);
					ls_textoMitadInferior     = as_plantilla.substring(li_finalTag + ls_caracter.length());
					ls_tagNew                 = "{\\*\\bkmkstart " + as_nombre + "}{\\*\\bkmkend " + as_nombre + "}"
						+ (ab_saltoLinea ? "\\line " : "") + ls_caracter;
					as_plantilla              = ls_textoMitadSuperior + " " + ls_tagNew + " " + ls_textoMitadInferior;
				}
			}
		}

		return as_plantilla;
	}

	/**
	 * Reemplazar guiones al piso.
	 *
	 * @param as_texto de as texto
	 * @return el valor de string
	 */
	protected String reemplazarGuionesAlPiso(String as_texto)
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_texto))
		{
			if(as_texto.contains(IdentificadoresCommon.SIMBOLO_GUION_BAJO))
				ls_return = as_texto.replace(
					    IdentificadoresCommon.SIMBOLO_GUION_BAJO, IdentificadoresCommon.ESPACIO_VACIO
					);
			else
				ls_return = as_texto;
		}

		return ls_return;
	}

	/**
	 * Reemplaza en la plantilla un tag por un valor específico.
	 *
	 * @param as_plantilla Objeto contenedor de la plantilla
	 * @param as_tag Tag a reemplazar en la plantilla
	 * @param ad_valor Objeto contenedor del valor a colocar en la plantilla
	 * @return Plantilla resultante
	 */
	protected String reemplazarTagEnPlantilla(String as_plantilla, String as_tag, Date ad_valor)
	{
		if(
		    StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_tag)
			    && as_plantilla.contains(as_tag)
		)
			as_plantilla = as_plantilla.replace(
				    as_tag,
				    (ad_valor != null) ? StringUtils.getString(ad_valor, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO)
				                       : ConstanteCommon.SIN_INFORMACION
				);

		return as_plantilla;
	}

	/**
	 * Reemplaza en la plantilla un tag por un valor específico.
	 *
	 * @param as_plantilla Objeto contenedor de la plantilla
	 * @param as_tag Tag a reemplazar en la plantilla
	 * @param as_valor Objeto contenedor del valor a colocar en la plantilla
	 * @return Plantilla resultante
	 */
	protected String reemplazarTagEnPlantilla(String as_plantilla, String as_tag, String as_valor)
	{
		if(
		    StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_tag)
			    && as_plantilla.contains(as_tag)
		)
			as_plantilla = as_plantilla.replace(
				    as_tag, StringUtils.isValidString(as_valor) ? as_valor : ConstanteCommon.SIN_INFORMACION
				);

		return as_plantilla;
	}

	/**
	 * Reemplaza en la plantilla un tag por un valor específico.
	 *
	 * @param as_plantilla Objeto contenedor de la plantilla
	 * @param as_tag Tag a reemplazar en la plantilla
	 * @param al_valor Objeto contenedor del valor a colocar en la plantilla
	 * @return Plantilla resultante
	 */
	protected String reemplazarTagEnPlantilla(String as_plantilla, String as_tag, long al_valor)
	{
		if(
		    StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_tag)
			    && as_plantilla.contains(as_tag)
		)
			as_plantilla = as_plantilla.replace(as_tag, IdentificadoresCommon.SIGNO_PESOS + al_valor);

		return as_plantilla;
	}

	/**
	 * Reemplaza en la plantilla un tag por un valor específico.
	 *
	 * @param as_plantilla Objeto contenedor de la plantilla
	 * @param as_tag Tag a reemplazar en la plantilla
	 * @param abd_valor Objeto contenedor del valor a colocar en la plantilla
	 * @return Plantilla resultante
	 */
	protected String reemplazarTagEnPlantilla(String as_plantilla, String as_tag, BigDecimal abd_valor)
	{
		if(
		    StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_tag)
			    && as_plantilla.contains(as_tag)
		)
			as_plantilla = as_plantilla.replace(
				    as_tag,
				    IdentificadoresCommon.SIGNO_PESOS
				    + (NumericUtils.isValidBigDecimal(abd_valor) ? abd_valor : BigDecimal.ZERO)
				);

		return as_plantilla;
	}

	/**
	 * Reemplazar tag pad firmas.
	 *
	 * @param as_plantilla de as plantilla
	 * @return el valor de string
	 */
	protected String reemplazarTagPadFirmas(String as_plantilla)
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			String ls_tag;

			ls_tag = TagCommon.TAG_PAD_FIRMAS;

			if(as_plantilla.contains(ls_tag))
			{
				int li_finalTag;

				li_finalTag = as_plantilla.lastIndexOf(ls_tag);

				if(li_finalTag > 0)
				{
					String ls_textoMitadSuperior;
					String ls_textoMitadInferior;
					String ls_tagNew;

					ls_textoMitadSuperior     = as_plantilla.substring(0, li_finalTag);
					ls_textoMitadInferior     = as_plantilla.substring(li_finalTag + ls_tag.length());

					ls_tagNew     = BookMarkCommon.PAD_FIRMAS_BKM + ls_tag;

					as_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
						+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
				}
			}
		}

		return as_plantilla;
	}

	/**
	 * Método encargado de reemplazar tags de documento en una plantilla.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite realizar la conexión a la base de datos.
	 * @param as_solicitud Argumento de tipo <code>Solicitud</code> que contiene el id solicitud a consultar.
	 * @param at_turno Argumento de tipo <code>Turno</code> que contiene el id turno a consultar.
	 * @param as_plantilla Argumento de tipo <code>String</code> que contiene la plantilla a modificar.
	 * @return el valor de string
	 * @throws B2BException Se presenta cuando se genera una excepción.
	 */
	protected synchronized String reemplazarTagsDocumento(
	    DAOManager adm_manager, Solicitud as_solicitud, Turno at_turno, String as_plantilla
	)
	    throws B2BException
	{
		try
		{
			String ls_idDocumento;

			ls_idDocumento = as_solicitud.getIdDocumento();

			if(StringUtils.isValidString(ls_idDocumento))
			{
				Documento ld_documento;

				ld_documento = new Documento();
				ld_documento.setIdDocumento(ls_idDocumento);

				ld_documento = DaoCreator.getDocumentoDAO(adm_manager).findById(ld_documento);

				if(ld_documento != null)
				{
					String     ls_tag;
					String     ls_tipoDocumento;
					String     ls_numero;
					String     ls_oficinaOrigen;
					BigDecimal lbd_version;
					Date       ld_fecha;

					ls_tipoDocumento     = ld_documento.getIdTipoDocumento();
					ls_numero            = ld_documento.getNumero();
					ls_oficinaOrigen     = ld_documento.getIdOficinaOrigen();
					lbd_version          = ld_documento.getVersion();
					ld_fecha             = ld_documento.getFechaDocumento();

					ls_tag = "<TAG_BQN_DOC_NUMERO>";

					if(as_plantilla.contains(ls_tag))
					{
						if(StringUtils.isValidString(ls_numero))
							as_plantilla = as_plantilla.replace(ls_tag, ls_numero);
					}

					ls_tag = "<TAG_BQN_DOC_FECHA>";

					if(as_plantilla.contains(ls_tag))
					{
						String ls_fecha;
						ls_fecha = StringUtils.getString(ld_fecha, FormatoFechaCommon.DIA_MES_ANIO);

						if(StringUtils.isValidString(ls_fecha))
							as_plantilla = as_plantilla.replace(ls_tag, ls_fecha);
					}

					{
						OficinaOrigen loo_oficinaOrigen;
						loo_oficinaOrigen = new OficinaOrigen();

						loo_oficinaOrigen.setIdOficinaOrigen(ls_oficinaOrigen);
						loo_oficinaOrigen.setVersion(lbd_version);

						loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager).findById(loo_oficinaOrigen);

						if(loo_oficinaOrigen != null)
						{
							String ls_nombreOficina;
							String ls_direccion;
							String ls_pais;
							String ls_departamento;
							String ls_municipio;

							ls_nombreOficina     = loo_oficinaOrigen.getNombre();
							ls_direccion         = loo_oficinaOrigen.getDireccion();
							ls_pais              = loo_oficinaOrigen.getIdPais();
							ls_departamento      = loo_oficinaOrigen.getIdDepartamento();
							ls_municipio         = loo_oficinaOrigen.getIdMunicipio();

							if(
							    StringUtils.isValidString(ls_direccion) && StringUtils.isValidString(ls_pais)
								    && StringUtils.isValidString(ls_departamento)
								    && StringUtils.isValidString(ls_municipio)
							)
							{
								StringBuilder lsb_sb;
								String        ls_nombreMun;

								lsb_sb           = new StringBuilder(ls_direccion);
								ls_nombreMun     = null;

								{
									Municipio lm_municipio;
									lm_municipio = new Municipio();

									lm_municipio.setIdPais(ls_pais);
									lm_municipio.setIdDepartamento(ls_departamento);
									lm_municipio.setIdMunicipio(ls_municipio);

									lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findById(lm_municipio);

									if(lm_municipio != null)
									{
										ls_nombreMun = lm_municipio.getNombre();

										if(StringUtils.isValidString(ls_nombreMun))
										{
											lsb_sb.append(" ");
											lsb_sb.append(ls_nombreMun);
										}
									}
								}

								{
									Departamento ld_departamento;
									ld_departamento = new Departamento();

									ld_departamento.setIdPais(ls_pais);
									ld_departamento.setIdDepartamento(ls_departamento);

									ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
											                        .findById(ld_departamento);

									if(ld_departamento != null)
									{
										String ls_nombreDep;
										ls_nombreDep = ld_departamento.getNombre();

										if(StringUtils.isValidString(ls_nombreDep))
										{
											lsb_sb.append(", ");
											lsb_sb.append(ls_nombreDep);
											ls_tag = "<TAG_DEP_OFI_ORIGEN>";

											if(as_plantilla.contains(ls_tag))
												as_plantilla = as_plantilla.replace(ls_tag, ls_nombreDep);
										}
									}
								}

								ls_tag = "<TAG_DIR_OFI_ORIGEN>";

								if(as_plantilla.contains(ls_tag))
									as_plantilla = as_plantilla.replace(ls_tag, lsb_sb.toString());

								ls_tag = "<TAG_ID_OFI_ORIGEN>";

								if(as_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_nombreOficina))
									as_plantilla = as_plantilla.replace(
										    ls_tag, ls_nombreOficina + " de " + ls_nombreMun
										);
							}
						}

						String ls_idCirculo;

						ls_tag           = "<TAG_MUN_OFI_ORIGEN>";
						ls_idCirculo     = (at_turno != null) ? at_turno.getIdCirculo() : null;

						if(as_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
						{
							Municipio lm_municipio;

							lm_municipio     = new Municipio();

							lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findByIdCirculo(ls_idCirculo);

							if(lm_municipio != null)
							{
								String ls_munOficinaOrigen;
								ls_munOficinaOrigen = lm_municipio.getNombre();

								if(as_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_munOficinaOrigen))
									as_plantilla = as_plantilla.replace(ls_tag, ls_munOficinaOrigen);
							}
						}
					}

					if(StringUtils.isValidString(ls_tipoDocumento))
					{
						TipoDocumentoPublico ltdp_tipoDocPublico;
						ltdp_tipoDocPublico = new TipoDocumentoPublico();

						ltdp_tipoDocPublico.setIdTipoDocumento(ls_tipoDocumento);

						ltdp_tipoDocPublico = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager)
								                            .findById(ltdp_tipoDocPublico);

						if(ltdp_tipoDocPublico != null)
						{
							String ls_nombreTipoDoc;
							ls_nombreTipoDoc     = ltdp_tipoDocPublico.getNombre();

							ls_tag = "<TAG_BQN_DOC_ID_TIPO_DOC>";

							if(as_plantilla.contains(ls_tag))
							{
								if(StringUtils.isValidString(ls_nombreTipoDoc))
									as_plantilla = as_plantilla.replace(ls_tag, ls_nombreTipoDoc);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("reemplazarTagsDocumento", lb2be_e);

			throw lb2be_e;
		}

		return as_plantilla;
	}

	/**
	 * Método encargado de reemplazar textos en plantilla con tags de matricula.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite realizar la conexión a la base de datos.
	 * @param as_plantilla Argumento de tipo <code>String</code> que contiene los valores de la plantilla.
	 * @param as_tag Argumento de tipo <code>String</code> que contiene el tag a reemplazar.
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id solicitud a consultar.
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene el id circulo a consultar.
	 * @param ab_traslado de ab traslado
	 * @return el valor de string
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	protected String resolverTagMatricula(
	    DAOManager adm_manager, String as_plantilla, String as_tag, String as_idSolicitud, String as_idCirculo,
	    boolean ab_traslado
	)
	    throws B2BException
	{
		try
		{
			if(as_plantilla.contains(as_tag))
			{
				if(ab_traslado)
				{
					Collection<TrasladoMatricula> lctm_datos;

					lctm_datos = DaoCreator.getTrasladoMatriculaDAO(adm_manager).findByIdSolicitud(as_idSolicitud);

					if(CollectionUtils.isValidCollection(lctm_datos))
					{
						StringBuilder lsb_sb;
						int           li_contador;
						long          ll_repetida;

						lsb_sb          = new StringBuilder();
						li_contador     = 1;
						ll_repetida     = 0L;

						for(TrasladoMatricula lctm_iterador : lctm_datos)
						{
							if(lctm_iterador != null)
							{
								long ll_idMatricula;

								ll_idMatricula = NumericUtils.getLong(lctm_iterador.getIdMatriculaOrigen());

								if(ll_repetida != ll_idMatricula)
								{
									int    li_size;
									String ls_signo;

									li_size      = lctm_datos.size();
									ls_signo     = "";

									if(li_size > 1)
										ls_signo = ", ";

									if(li_contador == li_size)
										ls_signo = IdentificadoresCommon.PUNTO;

									lsb_sb.append(lctm_iterador.getIdCirculoOrigen());
									lsb_sb.append("-");
									lsb_sb.append(ll_idMatricula);
									lsb_sb.append(ls_signo);

									li_contador++;

									ll_repetida = ll_idMatricula;
								}
							}
						}

						as_plantilla = as_plantilla.replace(as_tag, lsb_sb.toString());
					}
				}
				else
				{
					Collection<SolicitudMatricula> lcsm_datos;

					lcsm_datos = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
							                   .findByIdSolicitudCirculo(as_idSolicitud, as_idCirculo);

					if(CollectionUtils.isValidCollection(lcsm_datos))
					{
						StringBuilder lsb_sb;
						int           li_contador;
						long          ll_repetida;

						lsb_sb          = new StringBuilder();
						li_contador     = 1;
						ll_repetida     = 0;

						for(SolicitudMatricula lsm_iterador : lcsm_datos)
						{
							if(lsm_iterador != null)
							{
								long ll_idMatricula;

								ll_idMatricula = lsm_iterador.getIdMatricula();

								if(ll_repetida != ll_idMatricula)
								{
									int    li_size;
									String ls_signo;

									li_size      = lcsm_datos.size();
									ls_signo     = "";

									if(li_size > 1)
										ls_signo = ", ";

									if(li_contador == li_size)
										ls_signo = IdentificadoresCommon.PUNTO;

									lsb_sb.append(lsm_iterador.getIdCirculo());
									lsb_sb.append("-");
									lsb_sb.append(ll_idMatricula);
									lsb_sb.append(ls_signo);

									li_contador++;

									ll_repetida = ll_idMatricula;
								}
							}
						}

						as_plantilla = as_plantilla.replace(as_tag, lsb_sb.toString());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("resolverTagMatricula", lb2be_e);

			throw lb2be_e;
		}

		return as_plantilla;
	}

	/**
	 * Resolver tags basicos.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param ads_documento de ads documento
	 * @param as_plantilla de as plantilla
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String resolverTagsBasicos(
	    TurnoHistoria ath_turnoHistoria, DocumentosSalida ads_documento, String as_plantilla, String as_userId,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		return resolverTagsBasicos(
		    ath_turnoHistoria, ads_documento, as_plantilla, as_userId, as_remoteIp, adm_manager, false
		);
	}

	/**
	 * Resolver tags basicos.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param ads_documento de ads documento
	 * @param as_plantilla de as plantilla
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @param ab_proceso48 the ab proceso 48
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected String resolverTagsBasicos(
	    TurnoHistoria ath_turnoHistoria, DocumentosSalida ads_documento, String as_plantilla, String as_userId,
	    String as_remoteIp, DAOManager adm_manager, boolean ab_proceso48
	)
	    throws B2BException
	{
		if(ath_turnoHistoria == null)
			throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

		if(ads_documento == null)
			throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE);

		if(!StringUtils.isValidString(as_plantilla))
			throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

		{
			DocumentosSalida lds_documento;

			lds_documento = DaoCreator.getDocumentosSalidaDAO(adm_manager)
					                      .findByIdTurnoTipo(
					    ath_turnoHistoria.getIdTurno(), TipoArchivoCommon.NOTA_DEVOLUTIVA
					);

			if(lds_documento != null)
				as_plantilla = reemplazarTagEnPlantilla(
					    as_plantilla, TagCommon.TAG_FECHA_NOTA_DEVOLUTIVA, lds_documento.getFechaCreacion()
					);
		}

		as_plantilla     = getFirmaMasivaBusiness()
				                   .reemplazarUsuarioFirmaCargo(
				    as_plantilla,
				    DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(
				        ConstanteCommon.USUARIO_FIRMA_SUSPENSION
				    ), TagCommon.TAG_USUARIO_FIRMA_SUSPENSION, TagCommon.TAG_CARGO_FIRMA_SUSPENSION
				);

		as_plantilla     = reemplazarTagEnPlantilla(
			    as_plantilla, TagCommon.TAG_OBSERVACIONES, ath_turnoHistoria.getObservaciones()
			);

		as_plantilla = reemplazarTagEnPlantilla(as_plantilla, TagCommon.TAG_USUARIO, as_userId);

		{
			String ls_tag;

			ls_tag = TagCommon.TAG_OFICIO;

			if(as_plantilla.contains(ls_tag))
			{
				NumeracionOficiosCirculo lnoc_numeracionOficios;

				lnoc_numeracionOficios = findNumeracionOficiosCirculo(
					    ath_turnoHistoria, adm_manager, as_userId, as_remoteIp, false, ab_proceso48
					);

				if(lnoc_numeracionOficios != null)
				{
					String ls_consecutivoOficio;

					ls_consecutivoOficio     = lnoc_numeracionOficios.getConsecutivoGenerado();

					as_plantilla = reemplazarTagEnPlantilla(as_plantilla, ls_tag, ls_consecutivoOficio);

					ads_documento.setConsecutivoOficio(ls_consecutivoOficio);
					ads_documento.setFechaOficio(new Date());
				}
			}
		}

		{
			String ls_tag;

			ls_tag = TagCommon.TAG_REFERENCIA_SALIDA;

			if(as_plantilla.contains(ls_tag))
			{
				String ls_idReferencia;

				ls_idReferencia     = generarIdCorrespondencia(ath_turnoHistoria, adm_manager, false);

				as_plantilla = reemplazarTagEnPlantilla(as_plantilla, ls_tag, ls_idReferencia);

				ads_documento.setReferenciaSalida(ls_idReferencia);
			}
		}

		as_plantilla = reemplazarTagEnPlantilla(
			    as_plantilla, TagCommon.TAG_FECHA_LARGA, DateUtils.convertirLetrasLarga(new Date())
			);

		{
			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ath_turnoHistoria.getIdTurno());

			if(lt_turno != null)
			{
				CirculoRegistral lcr_circulo;

				lcr_circulo = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(lt_turno.getIdCirculo());

				if(lcr_circulo != null)
				{
					{
						String ls_tipoOficina;

						ls_tipoOficina = lcr_circulo.getTipoOficina();

						if(StringUtils.isValidString(ls_tipoOficina))
						{
							String ls_descripcionTipo;

							if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
								ls_descripcionTipo = "PRINCIPAL";
							else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
								ls_descripcionTipo = "SECCIONAL";
							else
								ls_descripcionTipo = null;

							as_plantilla = reemplazarTagEnPlantilla(
								    as_plantilla, TagCommon.TAG_PRINCIPAL_SECCIONAL, ls_descripcionTipo
								);
						}
					}

					as_plantilla     = reemplazarTagEnPlantilla(
						    as_plantilla, TagCommon.TAG_NOMBRE_ORIP, lcr_circulo.getNombre()
						);

					as_plantilla = reemplazarTagEnPlantilla(
						    as_plantilla, TagCommon.TAG_HORARIO_ATENCION, lcr_circulo.getHorarioAtencion()
						);

					OficinaOrigen loo_oficinaOrigen;

					loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
							                          .findById(
							    lcr_circulo.getIdOficinaOrigen(), lcr_circulo.getVersion()
							);

					if(loo_oficinaOrigen != null)
					{
						as_plantilla = reemplazarTagEnPlantilla(
							    as_plantilla, TagCommon.TAG_DIRECCION_OFICINA_ORIGEN_ORIP,
							    loo_oficinaOrigen.getDireccion()
							);

						Municipio lm_municipio;

						lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
								                     .findById(
								    loo_oficinaOrigen.getIdPais(), loo_oficinaOrigen.getIdDepartamento(),
								    loo_oficinaOrigen.getIdMunicipio()
								);

						if(lm_municipio != null)
							as_plantilla = reemplazarTagEnPlantilla(
								    as_plantilla, TagCommon.TAG_MUN_OFI_ORIGEN, lm_municipio.getNombre()
								);
					}
				}

				as_plantilla = reemplazarTagEnPlantilla(as_plantilla, TagCommon.TAG_ID_TURNO, lt_turno.getIdTurno());
			}
		}

		return as_plantilla;
	}

	/**
	 * Resuleve los tags de información y contacto de la snr en el recibo de liquidación.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_plantilla Objeto contenedor de la información de la plantilla
	 * @return Plantilla resultante
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	protected synchronized String resolverTagsSNRReciboLiquidacion(DAOManager adm_manager, String as_plantilla)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			ConstantesDAO lc_constantesDAO;
			Constantes    lc_constante;
			String        ls_tag;

			lc_constantesDAO     = DaoCreator.getConstantesDAO(adm_manager);

			lc_constante     = lc_constantesDAO.findById(ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_NIT);
			ls_tag           = "<TAG_NIT_SNR>";

			if((lc_constante != null) && as_plantilla.contains(ls_tag))
				as_plantilla = as_plantilla.replace(ls_tag, StringUtils.getStringNotNull(lc_constante.getCaracter()));

			lc_constante     = lc_constantesDAO.findById(ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_DIRECCION);
			ls_tag           = "<TAG_DIRECCION_SNR>";

			if((lc_constante != null) && as_plantilla.contains(ls_tag))
				as_plantilla = as_plantilla.replace(ls_tag, StringUtils.getStringNotNull(lc_constante.getCaracter()));

			lc_constante     = lc_constantesDAO.findById(ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_CONMUTADOR);
			ls_tag           = "<TAG_CONMUTADOR_SNR>";

			if((lc_constante != null) && as_plantilla.contains(ls_tag))
				as_plantilla = as_plantilla.replace(ls_tag, StringUtils.getStringNotNull(lc_constante.getCaracter()));

			lc_constante     = lc_constantesDAO.findById(
				    ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_URL_SUPERNOTARIADO
				);
			ls_tag           = "<TAG_URL_SUPERNOTARIADO>";

			if((lc_constante != null) && as_plantilla.contains(ls_tag))
				as_plantilla = as_plantilla.replace(ls_tag, StringUtils.getStringNotNull(lc_constante.getCaracter()));

			lc_constante     = lc_constantesDAO.findById(
				    ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_EXT_LINEA_ATENCION
				);
			ls_tag           = "<TAG_EXT_LINEA>";

			if((lc_constante != null) && as_plantilla.contains(ls_tag))
				as_plantilla = as_plantilla.replace(ls_tag, StringUtils.getStringNotNull(lc_constante.getCaracter()));

			lc_constante     = lc_constantesDAO.findById(ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_LINEA_ATENCION);
			ls_tag           = "<TAG_NUMERO_LINEA>";

			if((lc_constante != null) && as_plantilla.contains(ls_tag))
				as_plantilla = as_plantilla.replace(ls_tag, StringUtils.getStringNotNull(lc_constante.getCaracter()));

			lc_constante     = lc_constantesDAO.findById(ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_INFO_SEGUIMIENTO);
			ls_tag           = "<TAG_INFORMACION_SEGUIMIENTO>";

			if((lc_constante != null) && as_plantilla.contains(ls_tag))
				as_plantilla = as_plantilla.replace(ls_tag, StringUtils.getStringNotNull(lc_constante.getCaracter()));
		}

		return StringUtils.isValidString(as_plantilla) ? as_plantilla : "";
	}

	/**
	 * Realiza un salto de carro en las plantilla.
	 *
	 * @param as_plantilla plantilla pdf a modificar
	 * @param as_tag valor del tag al que se le aplicará el salto de línea
	 * @param as_direccion parametro en el cual se aplicará el salto de carro
	 * @return el valor de string
	 */
	protected String saltoDeCarroDespues(String as_plantilla, String as_tag, String as_direccion)
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			StringBuilder lsb_builder;

			lsb_builder = new StringBuilder(as_direccion);
			lsb_builder.append(IdentificadoresCommon.SALTO_LINEA_RTF);
			as_plantilla = as_plantilla.replace(as_tag, lsb_builder.toString());
		}

		return as_plantilla;
	}

	/**
	 * Método encargado de guardar la información del interesado.
	 *
	 * @param adm_manager Objeto que permite realizar la conexión a la base de datos.
	 * @param ar_registro Objeto que contiene los datos del interesado que se desea insertar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param ab_salvarSolicitud de ab salvar solicitud
	 * @return Objeto que contiene la información del interesado inserado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	protected synchronized Registro salvarInteresado(
	    DAOManager adm_manager, Registro ar_registro, String as_userId, boolean ab_salvarSolicitud
	)
	    throws B2BException
	{
		return salvarInteresado(adm_manager, ar_registro, as_userId, ab_salvarSolicitud, true);
	}

	/**
	 * Método encargado de guardar la información del interesado.
	 *
	 * @param adm_manager Objeto que permite realizar la conexión a la base de datos.
	 * @param ar_registro Objeto que contiene los datos del interesado que se desea insertar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param ab_salvarSolicitud de ab salvar solicitud
	 * @param ab_validarDatosPersona Argumento de tipo <code>boolean</code> que determina si se deben validar los datos de la persona.
	 * @return Objeto que contiene la información del interesado inserado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	protected synchronized Registro salvarInteresado(
	    DAOManager adm_manager, Registro ar_registro, String as_userId, boolean ab_salvarSolicitud,
	    boolean ab_validarDatosPersona
	)
	    throws B2BException
	{
		return salvarInteresado(adm_manager, ar_registro, as_userId, ab_salvarSolicitud, ab_validarDatosPersona, false);
	}

	/**
	 * Método encargado de guardar la información del interesado.
	 *
	 * @param adm_manager Objeto que permite realizar la conexión a la base de datos.
	 * @param ar_registro Objeto que contiene los datos del interesado que se desea insertar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param ab_salvarSolicitud de ab salvar solicitud
	 * @param ab_validarDatosPersona Argumento de tipo <code>boolean</code> que determina si se deben validar los datos de la persona.
	 * @param ab_validarTelefono Argumento de tipo <code>boolean</code> que determina si se deben validar el telefono de la persona.
	 * @param ab_soloValidar Argumento de tipo <code>boolean</code> que determina si solo se debe validar o no.
	 * @return Objeto que contiene la información del interesado inserado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	protected synchronized Registro salvarInteresado(
	    DAOManager adm_manager, Registro ar_registro, String as_userId, boolean ab_salvarSolicitud,
	    boolean ab_validarDatosPersona, boolean ab_validarTelefono, boolean ab_soloValidar
	)
	    throws B2BException
	{
		try
		{
			if(ar_registro != null)
			{
				Persona    lp_parametros;
				Solicitud  lso_solicitud;
				PersonaDAO lpd_DAO;
				boolean    lb_procesoCorrecciones;
				boolean    lb_grabacionCorreciones;

				lp_parametros               = ar_registro.getPersona();
				lso_solicitud               = ar_registro.isPersonaVinculada()
					? ((lp_parametros != null) ? lp_parametros.getSolicitud() : null) : ar_registro.getSolicitud();
				lpd_DAO                     = DaoCreator.getPersonaDAO(adm_manager);
				lb_procesoCorrecciones      = ar_registro.isProcesoCorrecciones();
				lb_grabacionCorreciones     = ar_registro.isGrabacionCorrecciones();

				if((lp_parametros != null) && (lso_solicitud != null))
				{
					boolean                  lb_procesoCertificados;
					boolean                  lb_procesoTraslados;
					boolean                  lb_notifOrip;
					PersonaDireccion         lpd_dirCor;
					PersonaDireccion         lpd_dirRe;
					PersonaCorreoElectronico lpc_correo;
					PersonaTelefono          lpt_telFi;
					PersonaTelefono          lpt_telMo;
					String                   ls_tipoPersona;
					String                   ls_tipoDocumento;
					String                   ls_idAutorizacionNotificacion;
					String                   ls_idAutorizacionComunicacion;
					String                   ls_idProceso;
					String                   ls_dirCor;
					String                   ls_dirRe;
					String                   ls_telFi;
					String                   ls_telMo;
					String                   ls_correo1;
					String                   ls_idCorreoNo;
					String                   ls_idCorreoCo;
					String                   ls_idDireccionNo;
					String                   ls_idDireccionCo;
					String                   ls_idTelefonoNo;
					String                   ls_idTelefonoCo;

					lb_procesoCertificados     = ar_registro.isProcesoCertificados();
					lb_notifOrip               = false;
					ls_idProceso               = lso_solicitud.getIdProceso();
					lb_procesoTraslados        = StringUtils.isValidString(ls_idProceso)
							&& ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38);
					ls_tipoPersona             = lp_parametros.getIdTipoPersona();
					ls_tipoDocumento           = lp_parametros.getIdDocumentoTipo();
					ls_idCorreoNo              = null;
					ls_idCorreoCo              = null;
					ls_idDireccionCo           = null;
					ls_idDireccionNo           = null;
					ls_idTelefonoNo            = null;
					ls_idTelefonoCo            = null;

					if(lb_procesoCorrecciones)
					{
						ls_idAutorizacionNotificacion     = MedioNotificarCommon.ORIP;
						ls_idAutorizacionComunicacion     = MedioNotificarCommon.ORIP;
					}
					else
					{
						ls_idAutorizacionNotificacion     = StringUtils.getStringNotNull(
							    lso_solicitud.getIdAutorizacionNotificacion()
							);
						ls_idAutorizacionComunicacion     = StringUtils.getStringNotNull(
							    lso_solicitud.getIdAutorizacionComunicacion()
							);
					}

					if(
					    ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.ORIP)
						    || ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.ORIP)
					)
						lb_notifOrip = true;

					lpd_dirCor     = ar_registro.getDireccionCorrespondencia();
					lpd_dirRe      = ar_registro.getDireccionResidencia();

					if(lpd_dirCor != null)
						ls_dirCor = lpd_dirCor.getDireccion();
					else
						ls_dirCor = null;

					if(lpd_dirRe != null)
						ls_dirRe = lpd_dirRe.getDireccion();
					else
						ls_dirRe = null;

					lpt_telFi     = ar_registro.getTelefonoFijo();
					lpt_telMo     = ar_registro.getTelefonoMovil();

					lpc_correo = ar_registro.getPersonaCorreoElectronico();

					if(lpt_telFi != null)
						ls_telFi = lpt_telFi.getTelefono();
					else
						ls_telFi = null;

					if(lpt_telMo != null)
						ls_telMo = lpt_telMo.getTelefono();
					else
						ls_telMo = null;

					if(lpc_correo != null)
						ls_correo1 = lpc_correo.getCorreoElectronico();
					else
						ls_correo1 = null;

					if(
					    !StringUtils.isValidString(ls_dirCor) && !StringUtils.isValidString(ls_dirRe)
						    && !StringUtils.isValidString(ls_telFi) && !StringUtils.isValidString(ls_telMo)
						    && !StringUtils.isValidString(ls_correo1) && !lb_notifOrip && !(lb_procesoCorrecciones)
						    && ab_validarDatosPersona
					)
						throw new B2BException(ErrorKeys.DEBE_DILIGENCIAR_FORMULARIO);

					if(StringUtils.isValidString(ls_dirRe))
						validarDireccion(lpd_dirRe, true);

					if(StringUtils.isValidString(ls_dirCor))
						validarDireccion(lpd_dirCor, false);

					if(!StringUtils.isValidString(ls_tipoPersona) || ls_tipoPersona.equalsIgnoreCase("-1"))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

					if(!StringUtils.isValidString(ls_tipoDocumento) || ls_tipoDocumento.equalsIgnoreCase("-1"))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

					{
						String ls_documento;
						ls_documento = lp_parametros.getNumeroDocumento();

						if(!StringUtils.isValidString(ls_documento))
							throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

						{
							Constantes lc_constante;
							String     ls_caracter;

							lc_constante     = new Constantes();
							ls_caracter      = ConstanteCommon.CARACTERES_ESPECIALES;
							lc_constante.setIdConstante(ls_caracter);

							lc_constante = DaoCreator.getConstantesDAO(adm_manager).findById(lc_constante);

							if(lc_constante != null)
							{
								String  ls_constantes;
								boolean lb_valido;

								ls_constantes     = lc_constante.getCaracter();
								lb_valido         = false;

								if(!StringUtils.isValidString(ls_constantes))
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = ls_caracter;

									throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
								}

								lb_valido = StringUtils.isValidCharacters(ls_documento, ls_constantes);

								if(!lb_valido)
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_DOC_SIN_CARACTERES);
							}
						}
					}

					if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.N))
					{
						if(
						    !ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
							    && !ls_tipoDocumento.equalsIgnoreCase("-1")
						)
						{
							{
								String ls_nacionalidad;
								ls_nacionalidad = lp_parametros.getIdPais();

								if(!StringUtils.isValidString(ls_nacionalidad))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
							}

							if(!(lb_procesoCorrecciones) && !lb_grabacionCorreciones)
							{
								{
									String ls_genero;
									ls_genero = lp_parametros.getIdNaturalGenero();

									if(!StringUtils.isValidString(ls_genero))
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
								}
							}

							if(!ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_10))
							{
								String ls_calidadSol;
								ls_calidadSol = lso_solicitud.getIdCalidadSolicitante();

								if(!StringUtils.isValidString(ls_calidadSol) && !(lb_procesoCorrecciones))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CALIDAD_SOL);
							}
							else
							{
								String ls_calidadInteresado;
								ls_calidadInteresado = lso_solicitud.getIdCalidadSolicitante();

								if(!StringUtils.isValidString(ls_calidadInteresado))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CALIDAD_INTERESADO);
							}

							{
								String ls_primerNombre;
								ls_primerNombre = lp_parametros.getPrimerNombre();

								if(!StringUtils.isValidString(ls_primerNombre))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
							}

							{
								String ls_primerApellido;
								ls_primerApellido = lp_parametros.getPrimerApellido();

								if(!StringUtils.isValidString(ls_primerApellido))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
							}
						}
						else
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURAL);
					}

					if(StringUtils.getStringNotNull(ls_tipoPersona).equalsIgnoreCase(EstadoCommon.J))
					{
						if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
						{
							{
								String ls_nacionalidad;
								ls_nacionalidad = lp_parametros.getIdPais();

								if(!StringUtils.isValidString(ls_nacionalidad))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
							}

							{
								String ls_razonSocial;
								ls_razonSocial = lp_parametros.getRazonSocial();

								if(!StringUtils.isValidString(ls_razonSocial))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
							}
						}
						else
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
					}

					if(
					    !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_10)
						    && !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
						    && !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5)
						    && !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_37)
						    && !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4)
						    && !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_44)
					)
					{
						String ls_interviene;
						ls_interviene = lso_solicitud.getParticipaInterviniente();

						if(!StringUtils.isValidString(ls_interviene))
						{
							if(lb_procesoCertificados || lb_procesoTraslados)
								lso_solicitud.setParticipaInterviniente(EstadoCommon.N);
							else
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_INTERVIENE);
						}
					}
					else
						lso_solicitud.setParticipaInterviniente(EstadoCommon.N);

					if(!ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2) && ab_validarDatosPersona)
					{
						if(!StringUtils.isValidString(ls_idAutorizacionComunicacion) && !lb_procesoCertificados)
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_COMUNICAR);

						if(
						    ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
							    || ls_idAutorizacionComunicacion.equalsIgnoreCase(
							        MedioNotificarCommon.DIRECCION_RESIDENCIA
							    )
						)
						{
							if(lpd_dirRe != null)
							{
								String ls_direccionResidencia;

								ls_direccionResidencia = lpd_dirRe.getDireccion();

								if(!StringUtils.isValidString(ls_direccionResidencia))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIRECCION_RESIDENCIA);

								if(
								    ls_idAutorizacionNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ls_idDireccionNo = lpd_dirRe.getIdDireccion();

								if(
								    ls_idAutorizacionComunicacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ls_idDireccionCo = lpd_dirRe.getIdDireccion();
							}
						}
						else if(
						    ls_idAutorizacionNotificacion.equalsIgnoreCase(
							        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
							    )
						)
						{
							PersonaDireccion lpd_correspondencia;

							lpd_correspondencia = ar_registro.getDireccionCorrespondencia();

							if(lpd_correspondencia != null)
							{
								String ls_direccionCorrespondencia;

								ls_direccionCorrespondencia = lpd_correspondencia.getDireccion();

								if(!StringUtils.isValidString(ls_direccionCorrespondencia))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIRECCION_CORRESPONDENCIA);

								ls_idDireccionNo = lpd_correspondencia.getIdDireccion();
							}
						}

						if(
						    ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
							    || ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
						)
						{
							if(lpd_dirRe != null)
							{
								String ls_direccionCompleta;

								ls_direccionCompleta = lpd_dirRe.getDireccion();

								if(StringUtils.isValidString(ls_direccionCompleta))
								{
									if(ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA))
										ls_idDireccionNo = lpd_dirRe.getIdDireccion();

									if(ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA))
										ls_idDireccionCo = lpd_dirRe.getIdDireccion();

									validarDireccion(lpd_dirRe, true);
								}
							}
						}

						if(ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA))
						{
							if(lpd_dirCor != null)
							{
								String ls_direccionCompleta;

								ls_direccionCompleta = lpd_dirCor.getDireccion();

								if(
								    (StringUtils.isValidString(ls_idAutorizacionComunicacion)
									    && ls_idAutorizacionComunicacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )) || StringUtils.isValidString(ls_direccionCompleta)
								)
								{
									if(
									    StringUtils.isValidString(ls_idAutorizacionComunicacion)
										    && ls_idAutorizacionComunicacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										    )
									)
										ls_idDireccionCo = lpd_dirCor.getIdDireccion();

									ls_idDireccionNo = lpd_dirCor.getIdDireccion();

									validarDireccion(lpd_dirCor, false);
								}
							}
						}
					}

					if(lb_notifOrip && !lb_procesoCorrecciones && !lb_grabacionCorreciones)
					{
						if((lpt_telFi != null) && (lpt_telMo != null) && (lpc_correo != null))
						{
							String ls_numFijo;
							String ls_numMovil;
							String ls_correoElec;

							ls_numFijo        = lpt_telFi.getTelefono();
							ls_numMovil       = lpt_telMo.getTelefono();
							ls_correoElec     = lpc_correo.getCorreoElectronico();

							if(
							    !(StringUtils.isValidString(ls_numFijo) || StringUtils.isValidString(ls_numMovil)
								    || StringUtils.isValidString(ls_correoElec))
							)
								throw new B2BException(ErrorKeys.ERROR_DATOS_CONTACTO);
							else
							{
								if(ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.ORIP))
								{
									ls_idCorreoNo       = lpc_correo.getIdCorreoElectronico();
									ls_idTelefonoNo     = lpt_telMo.getIdTelefono();
								}

								if(ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.ORIP))
								{
									ls_idCorreoCo       = lpc_correo.getIdCorreoElectronico();
									ls_idTelefonoCo     = lpt_telMo.getIdTelefono();
								}

								if(StringUtils.isValidString(ls_numFijo))
									validarTelefono(adm_manager, lpt_telFi, true);

								if(StringUtils.isValidString(ls_numMovil))
									validarTelefono(adm_manager, lpt_telMo, false);

								if(
								    StringUtils.isValidString(ls_correoElec)
									    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correoElec)
								)
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}
					else
					{
						boolean lb_sinTelefonoFijo;
						boolean lb_sinTelefonoMovil;

						lb_sinTelefonoFijo      = false;
						lb_sinTelefonoMovil     = false;

						if(ab_validarTelefono)
						{
							boolean lb_sinTelefonos;

							lb_sinTelefonoFijo      = (lpt_telFi == null)
									|| !StringUtils.isValidString(lpt_telFi.getTelefono());
							lb_sinTelefonoMovil     = (lpt_telMo == null)
									|| !StringUtils.isValidString(lpt_telMo.getTelefono());
							lb_sinTelefonos         = lb_sinTelefonoFijo && lb_sinTelefonoMovil;

							if(lb_sinTelefonos)
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_AL_MENOS_UN_TELEFONO);
						}

						if(lpt_telFi != null)
						{
							String ls_telefono;
							ls_telefono = lpt_telFi.getTelefono();

							if(StringUtils.isValidString(ls_telefono) || lb_sinTelefonoMovil)
								validarTelefono(adm_manager, lpt_telFi, true);
						}

						if(lpt_telMo != null)
						{
							String ls_telefono;

							ls_telefono = lpt_telMo.getTelefono();

							if(
							    (StringUtils.isValidString(ls_idAutorizacionNotificacion)
								    && ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.SMS))
								    || (StringUtils.isValidString(ls_idAutorizacionComunicacion)
								    && ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.SMS))
								    || StringUtils.isValidString(ls_telefono) || lb_sinTelefonoFijo
							)
							{
								if(
								    StringUtils.isValidString(ls_idAutorizacionComunicacion)
									    && ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.SMS)
								)
									ls_idTelefonoCo = lpt_telMo.getIdTelefono();

								if(
								    StringUtils.isValidString(ls_idAutorizacionNotificacion)
									    && ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.SMS)
								)
									ls_idTelefonoNo = lpt_telMo.getIdTelefono();

								validarTelefono(adm_manager, lpt_telMo, false);
							}
						}

						if(lpc_correo != null)
						{
							String ls_correo;

							ls_correo = lpc_correo.getCorreoElectronico();

							if(
							    StringUtils.isValidString(ls_correo)
								    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correo)
							)
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
						}
					}

					if(
					    ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO)
						    || ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO)
					)
					{
						String ls_correo;

						ls_correo = lpc_correo.getCorreoElectronico();

						if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correo))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

						if(ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
							ls_idCorreoNo = lpc_correo.getIdCorreoElectronico();

						if(ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
							ls_idCorreoCo = lpc_correo.getIdCorreoElectronico();
					}

					if(ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.SMS))
					{
						PersonaTelefono lpt_movil;
						lpt_movil = ar_registro.getTelefonoMovil();

						if(lpt_movil != null)
						{
							String ls_telefonoMovil;
							ls_telefonoMovil = lpt_movil.getTelefono();

							if(!StringUtils.isValidString(ls_telefonoMovil))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL);

							ls_idTelefonoCo = lpt_movil.getIdTelefono();
						}
					}

					{
						String ls_idPersona;

						ls_idPersona = marcarFlagPersona(
							    adm_manager, lp_parametros, as_userId, ar_registro.getIpCreacion(), ab_soloValidar
							);

						if(ar_registro.isPersonaCargada())
						{
							Persona lp_temp;

							lp_temp = lpd_DAO.findDataCalificador(lp_parametros);

							if(lp_temp != null)
							{
								lp_temp     = lpd_DAO.findById(lp_temp);

								ls_idPersona = lp_temp.getIdPersona();
							}
						}

						{
							PersonaDireccion lpd_direccionResidencia;
							lpd_direccionResidencia = ar_registro.getDireccionResidencia();

							if(
							    (lpd_direccionResidencia != null)
								    && StringUtils.isValidString(lpd_direccionResidencia.getDireccion())
								    && !ab_soloValidar
							)
							{
								PersonaDireccionDAO lpdd_DAO;
								PersonaDireccion    lpd_direccion;
								boolean             lb_crearDireccion;

								lpdd_DAO              = DaoCreator.getPersonaDireccionDAO(adm_manager);
								lpd_direccion         = null;
								lb_crearDireccion     = false;

								if(
								    StringUtils.isValidString(ls_idPersona)
									    && StringUtils.isValidString(lpd_direccionResidencia.getIdDireccion())
								)
								{
									lpd_direccionResidencia.setIdPersona(ls_idPersona);

									lpd_direccion = lpdd_DAO.findById(lpd_direccionResidencia);
								}

								if(lpd_direccion == null)
								{
									long ll_maxDir;

									lb_crearDireccion = true;

									{
										String ls_idTipoPredio;

										ls_idTipoPredio = lpd_direccionResidencia.getIdTipoPredio();

										if(!StringUtils.isValidString(ls_idTipoPredio))
											throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
									}

									lpd_direccionResidencia.setIdPersona(ls_idPersona);
									lpd_direccionResidencia.setFechaDesde(new Date());
									lpd_direccionResidencia.setIdUsuarioCreacion(as_userId);
									lpd_direccionResidencia.setIpCreacion(ar_registro.getIpCreacion());

									ll_maxDir = lpdd_DAO.insertOrUpdate(lpd_direccionResidencia, lb_crearDireccion);

									if(ll_maxDir > 0)
										lpd_direccionResidencia.setIdDireccion(StringUtils.getString(ll_maxDir));
									else
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
								}
								else if(!ar_registro.isDireccionResidenciaCargada())
								{
									lpd_direccionResidencia.setIdUsuarioModificacion(as_userId);
									lpd_direccionResidencia.setIpModificacion(ar_registro.getIpCreacion());

									lpdd_DAO.insertOrUpdate(lpd_direccionResidencia, lb_crearDireccion);
								}
							}
						}

						{
							PersonaDireccion lpd_direccionCorrespondencia;
							lpd_direccionCorrespondencia = ar_registro.getDireccionCorrespondencia();

							if(
							    (lpd_direccionCorrespondencia != null)
								    && StringUtils.isValidString(lpd_direccionCorrespondencia.getDireccion())
								    && !ab_soloValidar
							)
							{
								PersonaDireccionDAO lpdd_DAO;
								PersonaDireccion    lpd_direccion;
								boolean             lb_crearDireccion;

								lpdd_DAO              = DaoCreator.getPersonaDireccionDAO(adm_manager);
								lpd_direccion         = null;
								lb_crearDireccion     = false;

								if(
								    StringUtils.isValidString(ls_idPersona)
									    && StringUtils.isValidString(lpd_direccionCorrespondencia.getIdDireccion())
								)
								{
									lpd_direccionCorrespondencia.setIdPersona(ls_idPersona);

									lpd_direccion = lpdd_DAO.findById(lpd_direccionCorrespondencia);
								}

								if(lpd_direccion == null)
								{
									long ll_maxDir;

									lb_crearDireccion = true;

									{
										String ls_idTipoPredio;

										ls_idTipoPredio = lpd_direccionCorrespondencia.getIdTipoPredio();

										if(!StringUtils.isValidString(ls_idTipoPredio))
											throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
									}

									lpd_direccionCorrespondencia.setIdPersona(ls_idPersona);
									lpd_direccionCorrespondencia.setFechaDesde(new Date());
									lpd_direccionCorrespondencia.setIdUsuarioCreacion(as_userId);
									lpd_direccionCorrespondencia.setIpCreacion(ar_registro.getIpCreacion());

									ll_maxDir = lpdd_DAO.insertOrUpdate(
										    lpd_direccionCorrespondencia, lb_crearDireccion
										);

									if(ll_maxDir > 0)
										lpd_direccionCorrespondencia.setIdDireccion(StringUtils.getString(ll_maxDir));
									else
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
								}
								else if(!ar_registro.isDireccionCorrespondenciaCargada())
									lpdd_DAO.insertOrUpdate(lpd_direccionCorrespondencia, lb_crearDireccion);
							}
						}

						if((lpt_telFi != null) && StringUtils.isValidString(lpt_telFi.getTelefono()) && !ab_soloValidar)
						{
							PersonaTelefonoDAO lptd_DAO;
							PersonaTelefono    lpt_telefono;
							boolean            lb_crearTelefono;

							lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(adm_manager);
							lpt_telefono         = null;
							lb_crearTelefono     = false;

							if(
							    StringUtils.isValidString(ls_idPersona)
								    && StringUtils.isValidString(lpt_telFi.getIdTelefono())
							)
							{
								lpt_telFi.setIdPersona(ls_idPersona);

								lpt_telefono = lptd_DAO.findById(lpt_telFi);
							}

							lpt_telFi.setIdUsuarioCreacion(as_userId);
							lpt_telFi.setIpCreacion(ar_registro.getIpCreacion());
							lpt_telFi.setIdUsuarioModificacion(as_userId);
							lpt_telFi.setIpModificacion(ar_registro.getIpCreacion());

							if(lpt_telefono == null)
							{
								long ll_maxTel;

								lb_crearTelefono = true;
								lpt_telFi.setIdPersona(ls_idPersona);
								lpt_telFi.setTipoTelefono("F");
								lpt_telFi.setFechaDesde(new Date());

								ll_maxTel = lptd_DAO.insertOrUpdate(lpt_telFi, lb_crearTelefono);

								if(ll_maxTel > 0)
									lpt_telFi.setIdTelefono(StringUtils.getString(ll_maxTel));
								else
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
							}
							else if(!ar_registro.isTelefonoFijoCargado())
								lptd_DAO.insertOrUpdate(lpt_telFi, lb_crearTelefono);
						}

						if((lpt_telMo != null) && StringUtils.isValidString(lpt_telMo.getTelefono()) && !ab_soloValidar)
						{
							PersonaTelefonoDAO lptd_DAO;
							PersonaTelefono    lpt_telefono;
							boolean            lb_crearTelefono;

							lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(adm_manager);
							lpt_telefono         = null;
							lb_crearTelefono     = false;

							if(
							    StringUtils.isValidString(ls_idPersona)
								    && StringUtils.isValidString(lpt_telMo.getIdTelefono())
							)
							{
								lpt_telMo.setIdPersona(ls_idPersona);

								lpt_telefono = lptd_DAO.findById(lpt_telMo);
							}

							lpt_telMo.setIdUsuarioCreacion(as_userId);
							lpt_telMo.setIpCreacion(ar_registro.getIpCreacion());
							lpt_telMo.setIdUsuarioModificacion(as_userId);
							lpt_telMo.setIpModificacion(ar_registro.getIpCreacion());

							if(lpt_telefono == null)
							{
								long ll_maxTel;

								lb_crearTelefono = true;
								lpt_telMo.setIdPersona(ls_idPersona);
								lpt_telMo.setTipoTelefono("M");
								lpt_telMo.setFechaDesde(new Date());
								lpt_telMo.setIdUsuarioCreacion(as_userId);
								lpt_telMo.setIpCreacion(ar_registro.getIpCreacion());

								{
									Departamento ld_departamento;

									ld_departamento = new Departamento();
									ld_departamento.setIdPais(lpt_telMo.getIdPais());

									if(ld_departamento != null)
									{
										Collection<Departamento> lcd_departamento;

										lcd_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
												                         .findAllByPais(ld_departamento);

										if(CollectionUtils.isValidCollection(lcd_departamento))
										{
											boolean lb_departamento;

											lb_departamento = false;

											Iterator<Departamento> lid_iterator;

											lid_iterator = lcd_departamento.iterator();

											while(lid_iterator.hasNext() && !lb_departamento)
											{
												Departamento ld_departamentoEncontrado;

												ld_departamentoEncontrado = lid_iterator.next();

												if(ld_departamentoEncontrado != null)
												{
													String ls_idDepartamento;

													ls_idDepartamento = ld_departamentoEncontrado.getIdDepartamento();

													if(StringUtils.isValidString(ls_idDepartamento))
													{
														lpt_telMo.setIdDepartamento(ls_idDepartamento);
														lb_departamento = true;
													}
												}
											}
										}
									}
								}

								ll_maxTel = lptd_DAO.insertOrUpdate(lpt_telMo, lb_crearTelefono);

								if(ll_maxTel > 0)
									lpt_telMo.setIdTelefono(StringUtils.getString(ll_maxTel));
								else
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
							}
							else if(!ar_registro.isTelefonoMovilCargado())
								lptd_DAO.insertOrUpdate(lpt_telMo, lb_crearTelefono);
						}

						if(
						    (lpc_correo != null) && StringUtils.isValidString(lpc_correo.getCorreoElectronico())
							    && !ab_soloValidar
						)
						{
							PersonaCorreoElectronicoDAO lpced_DAO;
							PersonaCorreoElectronico    lpc_datos;
							boolean                     lb_crearCorreo;

							lpced_DAO          = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager);
							lpc_datos          = null;
							lb_crearCorreo     = false;

							if(
							    StringUtils.isValidString(ls_idPersona)
								    && StringUtils.isValidString(lpc_correo.getIdCorreoElectronico())
							)
							{
								lpc_correo.setIdPersona(ls_idPersona);

								lpc_datos = lpced_DAO.findById(lpc_correo);
							}

							if(lpc_datos == null)
							{
								long ll_maxCorreo;

								lb_crearCorreo = true;
								lpc_correo.setIdPersona(ls_idPersona);
								lpc_correo.setFechaDesde(new Date());
								lpc_correo.setIdUsuarioCreacion(as_userId);
								lpc_correo.setIpCreacion(ar_registro.getIpCreacion());

								ll_maxCorreo = lpced_DAO.insertOrUpdate(lpc_correo, lb_crearCorreo);

								if(ll_maxCorreo > 0)
									lpc_correo.setIdCorreoElectronico(StringUtils.getString(ll_maxCorreo));
								else
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_CORREO);
							}
							else if(!ar_registro.isCorreoCargado())
							{
								lpc_correo.setIdUsuarioCreacion(as_userId);
								lpc_correo.setIpCreacion(ar_registro.getIpCreacion());

								lpced_DAO.insertOrUpdate(lpc_correo, lb_crearCorreo);
							}
						}

						if(ab_salvarSolicitud)
						{
							SolicitudAsociadaDAO lsa_DAO;
							SolicitudDAO         lsd_DAO;
							Solicitud            lso_datosSol;
							String               ls_solicitudPadre;
							boolean              lb_insertar;
							boolean              lb_solicitudHija;

							lsa_DAO               = DaoCreator.getSolicitudAsociadaDAO(adm_manager);
							lsd_DAO               = DaoCreator.getSolicitudDAO(adm_manager);
							lso_datosSol          = null;
							ls_solicitudPadre     = null;
							lb_insertar           = false;
							lb_solicitudHija      = false;

							if(StringUtils.isValidString(lso_solicitud.getIdSolicitud()))
								lso_datosSol = lsd_DAO.findById(lso_solicitud);

							if((lb_procesoCorrecciones) && (lso_datosSol != null))
							{
								SolicitudAsociada lsa_solicitudAsociada;

								lsa_solicitudAsociada     = new SolicitudAsociada();

								ls_solicitudPadre = lso_datosSol.getIdSolicitud();

								lsa_solicitudAsociada.setIdSolicitud(ls_solicitudPadre);

								lsa_solicitudAsociada = lsa_DAO.findByIdSolicitud(lsa_solicitudAsociada);

								if(lsa_solicitudAsociada != null)
								{
									String ls_solicitudHija;

									ls_solicitudHija = lsa_solicitudAsociada.getIdSolicitud1();

									if(StringUtils.isValidString(ls_solicitudHija))
									{
										Solicitud ls_solicitudCalificacion;

										ls_solicitudCalificacion = new Solicitud();

										ls_solicitudCalificacion.setIdSolicitud(ls_solicitudHija);

										ls_solicitudCalificacion = lsd_DAO.findById(ls_solicitudCalificacion);

										ar_registro.setSolicitud(ls_solicitudCalificacion);
									}
								}
								else
									lb_solicitudHija = true;
							}

							{
								boolean lb_proceso5;
								boolean lb_proceso1;
								boolean lb_entidadExenta;
								String  ls_entidadExenta;

								lb_proceso5          = ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5);
								lb_proceso1          = ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1);
								ls_entidadExenta     = (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
										|| lb_proceso5 || lb_proceso1) ? lso_solicitud.getEntidadExenta() : EstadoCommon.N;
								lb_entidadExenta     = ls_entidadExenta.equalsIgnoreCase(EstadoCommon.S);

								if(!StringUtils.isValidString(ls_entidadExenta))
									throw new B2BException(ErrorKeys.ERROR_SIN_ENTIDAD_EXENTA);

								lso_solicitud.setEntidadExenta(ls_entidadExenta);

								if(lb_proceso5)
								{
									String ls_idSubProceso;

									ls_idSubProceso = null;

									if(lb_entidadExenta)
										ls_idSubProceso = SubProcesoCommon.COPIAS_COPIAS_EXENTAS;
									else
									{
										if(
										    ls_idAutorizacionNotificacion.equalsIgnoreCase(
											        MedioNotificarCommon.CORREO_ELECTRONICO
											    )
										)
											ls_idSubProceso = SubProcesoCommon.COPIAS_COPIA_MEDIO_MAGNETICO;
										else if(
										    ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP)
										)
											ls_idSubProceso = SubProcesoCommon.COPIAS_COPIA_MEDIO_FISICO;
									}

									lso_solicitud.setIdSubproceso(ls_idSubProceso);
									lso_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.ORIP_DE_ORIGEN);
								}

								if(lb_entidadExenta && !lb_proceso5 && !lb_proceso1)
								{
									{
										String ls_motivoConsulta;

										ls_motivoConsulta = lso_solicitud.getMotivoConsulta();

										if(!StringUtils.isValidString(ls_motivoConsulta))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MOTIVO_CONSULTA);
									}

									{
										String ls_referenciaMotivoConsulta;

										ls_referenciaMotivoConsulta = lso_solicitud.getReferenciaMotivoConsulta();

										if(!StringUtils.isValidString(ls_referenciaMotivoConsulta))
											throw new B2BException(ErrorKeys.DEBE_DIGITAR_REFERENCIA_MOTIVO_CONSULTA);
									}
								}
							}

							if((lso_datosSol == null) || lb_solicitudHija)
							{
								int li_secuencia;
								li_secuencia     = lsd_DAO.findSecuence();

								lb_insertar = true;
								lso_solicitud.setIdSolicitud(StringUtils.getString(li_secuencia));
								lso_solicitud.setIdPersona(ls_idPersona);
								lso_solicitud.setDerechoPeticion(EstadoCommon.N);
								lso_solicitud.setEstadoSolicitud(
								    NumericUtils.getLongWrapper(IdentificadoresCommon.NUM1)
								);
								lso_solicitud.setDigitalizada(EstadoCommon.N);
								lso_solicitud.setVersionDocumento(NumericUtils.getLongWrapper(1L));
								lso_solicitud.setIdUsuarioCreacion(as_userId);
							}

							if(!lb_procesoCorrecciones)
							{
								if(lpc_correo != null)
								{
									if(
									    ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.CORREO_ELECTRONICO
										    )
										    || ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.ORIP
										    )
									)
										ls_idCorreoNo = lpc_correo.getIdCorreoElectronico();

									if(
									    ls_idAutorizacionComunicacion.equalsIgnoreCase(
										        MedioNotificarCommon.CORREO_ELECTRONICO
										    )
										    || ls_idAutorizacionComunicacion.equalsIgnoreCase(
										        MedioNotificarCommon.ORIP
										    )
									)
										ls_idCorreoCo = lpc_correo.getIdCorreoElectronico();
								}

								if(lpd_dirRe != null)
								{
									if(
									    ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_RESIDENCIA
										    )
									)
										ls_idDireccionNo = lpd_dirRe.getIdDireccion();

									if(
									    ls_idAutorizacionComunicacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_RESIDENCIA
										    )
									)
										ls_idDireccionCo = lpd_dirRe.getIdDireccion();
								}

								if(lpd_dirCor != null)
								{
									if(
									    ls_idAutorizacionNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										    )
									)
										ls_idDireccionNo = lpd_dirCor.getIdDireccion();
								}

								if(lpt_telFi != null)
								{
									String ls_telefonoFijo;
									ls_telefonoFijo = lpt_telFi.getTelefono();

									if(StringUtils.isValidString(ls_telefonoFijo))
									{
										if(ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
											ls_idTelefonoNo = lpt_telFi.getIdTelefono();

										if(ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
											ls_idTelefonoCo = lpt_telFi.getIdTelefono();
									}
								}

								if(lpt_telMo != null)
								{
									String ls_telefonoMovil;
									ls_telefonoMovil = lpt_telMo.getTelefono();

									if(StringUtils.isValidString(ls_telefonoMovil))
									{
										if(ls_idAutorizacionNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
											ls_idTelefonoNo = lpt_telMo.getIdTelefono();

										if(
										    ls_idAutorizacionComunicacion.equalsIgnoreCase(MedioNotificarCommon.ORIP)
											    || ls_idAutorizacionComunicacion.equalsIgnoreCase(
											        MedioNotificarCommon.SMS
											    )
										)
											ls_idTelefonoCo = lpt_telMo.getIdTelefono();
									}
								}

								lso_solicitud.setIdUsuarioModificacion(as_userId);
							}

							if(lb_procesoCorrecciones && lb_insertar)
							{
								lso_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_3);
								lso_solicitud.setIdSubproceso(ProcesoCommon.ID_SUBPROCESO_2);
								lso_solicitud.setIdTurnoAnt(ar_registro.getIdTurno());
								lso_solicitud.setIdAutorizacionComunicacion(MedioNotificarCommon.ORIP);
								lso_solicitud.setIdAutorizacionNotificacion(MedioNotificarCommon.ORIP);
								lso_solicitud.setIdTipoRecepcion(null);
								lso_solicitud.setNir(null);
							}

							lso_solicitud.setIdDireccion(ls_idDireccionNo);
							lso_solicitud.setIdTelefono(ls_idTelefonoNo);
							lso_solicitud.setIdCorreoElectronico(ls_idCorreoNo);
							lso_solicitud.setIdDireccionComunicacion(ls_idDireccionCo);
							lso_solicitud.setIdTelefonoComunicacion(ls_idTelefonoCo);
							lso_solicitud.setIdCorreoElectronicoComunicacion(ls_idCorreoCo);
							lso_solicitud.setIdPersona(ls_idPersona);

							if(lb_procesoTraslados)
								lso_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.ORIP_DE_ORIGEN);

							lsd_DAO.insertOrUpdate(lso_solicitud, lb_insertar);

							if(lb_solicitudHija)
							{
								SolicitudAsociada lsa_solicitudNueva;

								lsa_solicitudNueva = new SolicitudAsociada();

								if(lb_procesoCorrecciones)
								{
									lsa_solicitudNueva.setIdSolicitud(lso_solicitud.getIdSolicitud());
									lsa_solicitudNueva.setIdSolicitud1(ls_solicitudPadre);
									lsa_solicitudNueva.setIndVinculado(EstadoCommon.A);
								}
								else
								{
									lsa_solicitudNueva.setIdSolicitud(ls_solicitudPadre);
									lsa_solicitudNueva.setIdSolicitud1(lso_solicitud.getIdSolicitud());
								}

								lsa_solicitudNueva.setIdUsuarioCreacion(as_userId);

								lsa_DAO.insertOrUpdate(lsa_solicitudNueva, true);
							}
						}
						else
						{
							Persona lp_persona;

							lp_persona = ar_registro.getPersona();

							if(lp_persona != null)
								lp_persona.setIdPersona(ls_idPersona);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarInteresado", lb2be_e);

			throw lb2be_e;
		}

		return ar_registro;
	}

	/**
	 * Método encargado de guardar la información del interesado.
	 *
	 * @param adm_manager Objeto que permite realizar la conexión a la base de datos.
	 * @param ar_registro Objeto que contiene los datos del interesado que se desea insertar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el proceso.
	 * @param ab_salvarSolicitud de ab salvar solicitud
	 * @param ab_validarDatosPersona Argumento de tipo <code>boolean</code> que determina si se deben validar los datos de la persona.
	 * @param ab_validarTelefono Argumento de tipo <code>boolean</code> que determina si se deben validar el telefono de la persona.
	 * @return Objeto que contiene la información del interesado inserado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Registro
	 */
	protected synchronized Registro salvarInteresado(
	    DAOManager adm_manager, Registro ar_registro, String as_userId, boolean ab_salvarSolicitud,
	    boolean ab_validarDatosPersona, boolean ab_validarTelefono
	)
	    throws B2BException
	{
		return salvarInteresado(
		    adm_manager, ar_registro, as_userId, ab_salvarSolicitud, ab_validarDatosPersona, ab_validarTelefono, false
		);
	}

	/**
	 * Método encargado de salvar los datos para solicitud de copias.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param acdo_documentosOWCC Argumento de tipo <code>Collection</code> que contiene los documentos a salvar en solicitud copias.
	 * @param as_idSolicitud objeto que contiene el id_solicitud para solicitud de copias.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException error si ocurre una excepción
	 */
	protected synchronized void salvarSolicitudCopias(
	    DAOManager adm_manager, Collection<DocumentoOWCC> acdo_documentosOWCC, String as_idSolicitud, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		salvarSolicitudCopias(adm_manager, acdo_documentosOWCC, as_idSolicitud, null, false, as_userId, as_remoteIp);
	}

	/**
	 * Método encargado de salvar los datos para solicitud de copias.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param acdo_documentosOWCC Argumento de tipo <code>Collection</code> que contiene los documentos a salvar en solicitud copias.
	 * @param as_idSolicitud objeto que contiene el id_solicitud para solicitud de copias.
	 * @param al_numeroCopias de al numero copias
	 * @param ab_validarNumeroFolios de ab validar numero folios
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException error si ocurre una excepción
	 */
	protected synchronized void salvarSolicitudCopias(
	    DAOManager adm_manager, Collection<DocumentoOWCC> acdo_documentosOWCC, String as_idSolicitud,
	    Long al_numeroCopias, boolean ab_validarNumeroFolios, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if(
			    CollectionUtils.isValidCollection(acdo_documentosOWCC) && StringUtils.isValidString(as_idSolicitud)
				    && (adm_manager != null)
			)
			{
				SolicitudCopiasDAO  lscd_DAO;
				TipoDocumentalDAO   ltdd_DAO;
				TurnoDAO            ltd_DAO;
				CirculoRegistralDao lcrd_DAO;
				int                 li_contador;

				lscd_DAO     = DaoCreator.getSolicitudCopiasDAO(adm_manager);
				ltdd_DAO     = DaoCreator.getTipoDocumentalDAO(adm_manager);
				ltd_DAO      = DaoCreator.getTurnoDAO(adm_manager);
				lcrd_DAO     = DaoCreator.getCirculoRegistralDAO(adm_manager);

				li_contador = 0;

				for(DocumentoOWCC ldo_iterador : acdo_documentosOWCC)
				{
					if((ldo_iterador != null) && ldo_iterador.isSeleccione())
					{
						SolicitudCopias lsc_solicitudCopias;

						lsc_solicitudCopias = new SolicitudCopias();

						lsc_solicitudCopias.setNir(ldo_iterador.getNir());

						{
							String ls_turno;

							ls_turno = ldo_iterador.getTurno();

							if(StringUtils.isValidString(ls_turno))
							{
								Turno lt_turno;

								lt_turno     = ltd_DAO.findById(ls_turno);

								ls_turno = (lt_turno != null) ? lt_turno.getIdTurno() : null;
							}

							lsc_solicitudCopias.setTurno(ls_turno);
						}

						lsc_solicitudCopias.setIdSolicitud(as_idSolicitud);

						lsc_solicitudCopias.setIdEcmOriginal(ldo_iterador.getiD());

						{
							long ll_numeroFolios;

							ll_numeroFolios = NumericUtils.getLong(ldo_iterador.getnumeroFolios());

							if((ll_numeroFolios <= 0) && ab_validarNumeroFolios)
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_NUMERO_FOLIOS);

							lsc_solicitudCopias.setNumeroFolios(NumericUtils.getLongWrapper(ll_numeroFolios));
						}

						{
							String ls_docType;
							Long   ll_numeroCopias;

							ls_docType          = ldo_iterador.getDocType();
							ll_numeroCopias     = NumericUtils.isValidLong(al_numeroCopias) ? al_numeroCopias
								                                                            : ldo_iterador
									.getNumeroCopias();

							if(StringUtils.isValidString(ls_docType))
							{
								TipoDocumental ltd_tipoDocumental;

								ltd_tipoDocumental = ltdd_DAO.findByTipologiasBachue(ls_docType);

								if(ltd_tipoDocumental != null)
									lsc_solicitudCopias.setIdTipoDocumental(ltd_tipoDocumental.getIdTipoDocumental());
								else
								{
									Object[] loa_object;

									loa_object     = new String[1];

									loa_object[0] = ls_docType;

									throw new B2BException(
									    ErrorKeys.ERROR_NO_SE_ENCONTRARON_DOCUMENTOS_TIPOLOGIA_BACHUE, loa_object
									);
								}
							}

							if(!NumericUtils.isValidLong(ll_numeroCopias, 1L))
							{
								Object[] loa_object;

								loa_object     = new String[2];

								loa_object[0]     = ls_docType;
								loa_object[1]     = StringUtils.getString(li_contador + 1);

								throw new B2BException(
								    ErrorKeys.DEBE_DIGITAR_UN_NUMERO_DE_COPIAS_MAYOR_CERO, loa_object
								);
							}

							lsc_solicitudCopias.setNumeroCopias(ll_numeroCopias);
						}

						lsc_solicitudCopias.setDocNameOriginal(ldo_iterador.getDocName());

						{
							String ls_idCirculo;

							ls_idCirculo = ldo_iterador.getIdOrip();

							if(StringUtils.isValidString(ls_idCirculo))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral     = lcrd_DAO.findById(ls_idCirculo);

								ls_idCirculo = (lcr_circuloRegistral != null) ? lcr_circuloRegistral.getIdCirculo() : null;
							}

							lsc_solicitudCopias.setIdCirculo(ls_idCirculo);
						}

						{
							String ls_matriculas;

							ls_matriculas = ldo_iterador.getMatriculas();

							if(StringUtils.isValidString(ls_matriculas))
							{
								int li_tamano;

								li_tamano = 4000;

								if(ls_matriculas.length() > li_tamano)
									ls_matriculas = ls_matriculas.substring(0, li_tamano - 1);

								lsc_solicitudCopias.setMatriculaOwcc(ls_matriculas);
							}
						}

						lsc_solicitudCopias.setActivo(EstadoCommon.S);
						lsc_solicitudCopias.setRepositorio(RepositorioCommon.FINAL);
						lsc_solicitudCopias.setIdUsuarioCreacion(as_userId);
						lsc_solicitudCopias.setIpCreacion(as_remoteIp);

						lscd_DAO.insertOrUpdate(lsc_solicitudCopias, true);

						li_contador++;
					}
				}

				if(li_contador == 0)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_AL_MENOS_UN_DOCUMENTO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDatosCopiasOWCC", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Solucionar book mark pad firmas.
	 *
	 * @param adm_manager de adm manager
	 * @param aba_documento de aba documento
	 * @param aofd_param de aofd param
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized byte[] solucionarBookMarkPadFirmas(
	    DAOManager adm_manager, byte[] aba_documento, ObtenerFirmaDTO aofd_param
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if((adm_manager != null) && ByteArrayUtils.isValidArray(aba_documento) && (aofd_param != null))
		{
			try
			{
				ConstantesDAO lcd_DAO;
				Constantes    lc_coordenadasFirma;

				lcd_DAO                 = DaoCreator.getConstantesDAO(adm_manager);
				lc_coordenadasFirma     = lcd_DAO.findById(ConstanteCommon.COORDENADAS_PAD_FIRMAS_ENTREGA);

				if(lc_coordenadasFirma != null)
				{
					Constantes lc_constante;

					lc_constante = lcd_DAO.findById(ConstanteCommon.PAD_DE_FIRMAS_ENDPOINT);

					if(lc_constante != null)
						lba_return = getFirmaMasivaBusiness()
								             .reemplazarBookmarsFirma(
								    aba_documento,
								    scaleImage(
								        ClientePadFirmas.obtenerFirma(aofd_param, lc_constante.getCaracter()),
								        ci_WIDTH_IMAGE, ci_HEIGHT_IMAGE
								    ), NumericUtils.getInt(lc_coordenadasFirma.getEntero()),
								    NumericUtils.getInt(lc_coordenadasFirma.getReal()), BookMarkCommon.PAD_FIRMAS
								);
					else
					{
						Object[] loa_arguments;

						loa_arguments     = new String[1];

						loa_arguments[0] = ConstanteCommon.PAD_DE_FIRMAS_ENDPOINT;

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_arguments);
					}
				}
				else
				{
					Object[] loa_arguments;

					loa_arguments     = new String[1];

					loa_arguments[0] = ConstanteCommon.COORDENADAS_PAD_FIRMAS_ENTREGA;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_arguments);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("solucionarBookMarkPadFirmas", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_return;
	}

	/**
	 * Coloca el estado actividad del turno historia en terminado e invoca al proc crear etapa con el mismo
	 * turno historia.
	 *
	 * @param ath_parametros correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param adm_manager Objeto contenedor de la conexión a la base de datos
	 * @param amt_motivoTramite correspondiente al valor del tipo de objeto MotivoTramite
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param as_estadoActividad estado actividad con el que se va a terminar el turno historia
	 * @return el valor de turno historia
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected TurnoHistoria terminarTurnoHistoriaYCrearEtapa(
	    TurnoHistoria ath_parametros, DAOManager adm_manager, MotivoTramite amt_motivoTramite, String as_usuario,
	    String as_remoteIp, String as_estadoActividad
	)
	    throws B2BException
	{
		return terminarTurnoHistoriaYCrearEtapa(
		    ath_parametros, adm_manager, amt_motivoTramite, as_usuario, as_remoteIp, as_estadoActividad, true, true
		);
	}

	/**
	 * Coloca el estado actividad del turno historia en terminado e invoca al proc crear etapa con el mismo
	 * turno historia.
	 *
	 * @param ath_parametros correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param adm_manager Objeto contenedor de la conexión a la base de datos
	 * @param amt_motivoTramite correspondiente al valor del tipo de objeto MotivoTramite
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param as_estadoActividad estado actividad con el que se va a terminar el turno historia
	 * @param ab_validarUsuario Argumento de tipo <code>boolean</code> que determina si se debe validar usuario anterior.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void terminarTurnoHistoriaYCrearEtapa(
	    TurnoHistoria ath_parametros, DAOManager adm_manager, MotivoTramite amt_motivoTramite, String as_usuario,
	    String as_remoteIp, String as_estadoActividad, boolean ab_validarUsuario
	)
	    throws B2BException
	{
		terminarTurnoHistoriaYCrearEtapa(
		    ath_parametros, adm_manager, amt_motivoTramite, as_usuario, as_remoteIp, as_estadoActividad,
		    ab_validarUsuario, true
		);
	}

	/**
	 * Coloca el estado actividad del turno historia en terminado e invoca al proc crear etapa con el mismo
	 * turno historia.
	 *
	 * @param ath_parametros correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param adm_manager Objeto contenedor de la conexión a la base de datos
	 * @param amt_motivoTramite correspondiente al valor del tipo de objeto MotivoTramite
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param as_estadoActividad estado actividad con el que se va a terminar el turno historia
	 * @param ab_validarUsuario Argumento de tipo <code>boolean</code> que determina si se debe validar usuario anterior.
	 * @param ab_invocarProcCrearEtapa Argumento de tipo <code>boolean</code> que determina si se debe invocar el procedimiento de crear etapa.
	 * @param ab_usuario Argumento de tipo <code>boolean</code> que determina si se debe actualizar el usuario.
	 * @return el valor de turno historia
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected TurnoHistoria terminarTurnoHistoriaYCrearEtapa(
	    TurnoHistoria ath_parametros, DAOManager adm_manager, MotivoTramite amt_motivoTramite, String as_usuario,
	    String as_remoteIp, String as_estadoActividad, boolean ab_validarUsuario, boolean ab_invocarProcCrearEtapa,
	    boolean ab_usuario
	)
	    throws B2BException
	{
		return terminarTurnoHistoriaYCrearEtapa(
		    ath_parametros, adm_manager, amt_motivoTramite, as_usuario, as_remoteIp, as_estadoActividad,
		    ab_validarUsuario, ab_invocarProcCrearEtapa, true, ab_usuario
		);
	}

	/**
	 * Terminar turno historia Y crear etapa.
	 *
	 * @param ath_parametros de ath parametros
	 * @param adm_manager de adm manager
	 * @param amt_motivoTramite de amt motivo tramite
	 * @param as_usuario de as usuario
	 * @param as_remoteIp de as remote ip
	 * @param as_estadoActividad de as estado actividad
	 * @param ab_validarUsuario de ab validar usuario
	 * @param ab_invocarProcCrearEtapa de ab invocar proc crear etapa
	 * @param ab_validarTer de ab validar ter
	 * @param ab_usuario de ab usuario
	 * @return el valor de turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected TurnoHistoria terminarTurnoHistoriaYCrearEtapa(
	    TurnoHistoria ath_parametros, DAOManager adm_manager, MotivoTramite amt_motivoTramite, String as_usuario,
	    String as_remoteIp, String as_estadoActividad, boolean ab_validarUsuario, boolean ab_invocarProcCrearEtapa,
	    boolean ab_validarTer, boolean ab_usuario
	)
	    throws B2BException
	{
		TurnoHistoria lth_return;

		lth_return = null;

		if(ath_parametros != null)
		{
			if(!StringUtils.isValidString(as_usuario))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_USUARIO);

			TurnoHistoriaDAO lthd_DAO;
			TurnoHistoria    lth_turnoHistoria;
			String           ls_observaciones;
			String           ls_observacionesNoTramite;

			lthd_DAO                      = DaoCreator.getTurnoHistoriaDAO(adm_manager);
			ls_observaciones              = ath_parametros.getObservaciones();
			ls_observacionesNoTramite     = ath_parametros.getObservacionesNoTramite();
			lth_turnoHistoria             = lthd_DAO.findById(ath_parametros.getIdTurnoHistoria());

			if(lth_turnoHistoria != null)
			{
				if(amt_motivoTramite != null)
				{
					long   ll_idEtapa;
					long   ll_idMotivo;
					String ls_idProceso;
					String ls_idSubProceso;
					Long   ll_versionSubProceso;

					ll_idEtapa               = amt_motivoTramite.getIdEtapaOrigen();
					ll_idMotivo              = amt_motivoTramite.getIdMotivo();
					ls_idProceso             = lth_turnoHistoria.getIdProceso();
					ls_idSubProceso          = lth_turnoHistoria.getIdSubproceso();
					ll_versionSubProceso     = lth_turnoHistoria.getVersionSubproceso();

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.ID_PROCESO_INVALIDO);

					if(!StringUtils.isValidString(ls_idSubProceso))
						throw new B2BException(ErrorKeys.ID_SUB_PROCESO_INVALIDO);

					if(StringUtils.isValidString(as_estadoActividad))
					{
						if(ab_usuario)
							lth_turnoHistoria.setIdUsuario(as_usuario);

						if(
						    (ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_INSCRIPCION)
							    || (ll_idEtapa == EtapaCommon.ID_ETAPA_IMPRESION_DOCUMENTOS_PARA_ENTREGA_CORRESPONDENCIA)
							    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
							    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_MEDIDAS_CAUTELARES)
							    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_NOTIFICACIO_A_ENTIDADES)
							    || (ll_idEtapa == EtapaCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA)
						)
						{
							String ls_usuario;

							ls_usuario = lth_turnoHistoria.getIdUsuario();

							if(!StringUtils.isValidString(ls_usuario))
								lth_turnoHistoria.setIdUsuario(as_usuario);
						}

						if(as_estadoActividad.equals(EstadoCommon.TERMINADA))
						{
							String ls_estadoActividad;

							ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

							if(StringUtils.isValidString(ls_estadoActividad))
							{
								if(ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA) && ab_validarTer)
									throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
								else if(ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA))
								{
									String ls_usuarioActividad;

									ls_usuarioActividad = StringUtils.getStringNotNull(
										    lth_turnoHistoria.getIdUsuario()
										);

									if(ab_validarUsuario && !as_usuario.equalsIgnoreCase(ls_usuarioActividad))
										throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
								}
								else if(
								    ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ESPERA)
									    || ls_estadoActividad.equalsIgnoreCase(EstadoCommon.AUTOMATICA)
								)
									as_usuario = ConstanteCommon.CORE_BACHUE;
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ESTADO_ACTIVIDAD_NO_VALIDO);

					amt_motivoTramite = DaoCreator.getMotivoTramiteDAO(adm_manager)
							                          .findByKeyActive(
							    ls_idProceso, ls_idSubProceso, ll_idEtapa, ll_idMotivo, ll_versionSubProceso
							);

					if(amt_motivoTramite != null)
					{
						{
							TurnoHistoria lth_turnoHistAnt;

							lth_turnoHistAnt = lthd_DAO.findById(
								    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
								);

							if(lth_turnoHistAnt != null)
								lth_turnoHistoria.setMotivoNoTramite(lth_turnoHistAnt.getMotivo());
						}

						lth_turnoHistoria.setEstadoActividad(as_estadoActividad);
						lth_turnoHistoria.setMotivo(amt_motivoTramite.getDescripcion());
						lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(amt_motivoTramite.getIdMotivo()));
						lth_turnoHistoria.setObservaciones(ls_observaciones);
						lth_turnoHistoria.setObservacionesNoTramite(ls_observacionesNoTramite);
						lth_turnoHistoria.setIndicadorDevolucion(amt_motivoTramite.getIndicadorDevolucion());
						lth_turnoHistoria.setIdUsuarioModificacion(as_usuario);
						lth_turnoHistoria.setIpModificacion(as_remoteIp);

						lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

						if(ath_parametros.isInvocarProcedimientoGobernaciones())
						{
							Solicitud ls_solicitud;

							ls_solicitud = new Solicitud();

							ls_solicitud.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
							ls_solicitud.setIdUsuarioModificacion(as_usuario);
							ls_solicitud.setIpModificacion(as_remoteIp);

							DaoCreator.getProcedimientosDAO(adm_manager)
								          .procLlaCrearEtapaTrg(
								    ls_solicitud, "", IdentificadoresCommon.GOBERNACIONES_JOB,
								    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_11)
								);
						}
						else if(ab_invocarProcCrearEtapa)
							lth_return = DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_turnoHistoria);
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs     = new String[2];

						loa_messageArgs[0]     = StringUtils.getString(ll_idEtapa);
						loa_messageArgs[1]     = StringUtils.getString(ll_idMotivo);

						throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO, loa_messageArgs);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
		}
		else
			throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

		return lth_return;
	}

	/**
	 * Coloca el estado actividad del turno historia en terminado e invoca al proc crear etapa con el mismo
	 * turno historia.
	 *
	 * @param ath_parametros correspondiente al valor del tipo de objeto TurnoHistoria
	 * @param adm_manager Objeto contenedor de la conexión a la base de datos
	 * @param amt_motivoTramite correspondiente al valor del tipo de objeto MotivoTramite
	 * @param as_usuario correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param as_estadoActividad estado actividad con el que se va a terminar el turno historia
	 * @param ab_validarUsuario Argumento de tipo <code>boolean</code> que determina si se debe validar usuario anterior.
	 * @param ab_invocarProcCrearEtapa Argumento de tipo <code>boolean</code> que determina si se debe invocar el procedimiento de crear etapa.
	 * @return el valor de turno historia
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected TurnoHistoria terminarTurnoHistoriaYCrearEtapa(
	    TurnoHistoria ath_parametros, DAOManager adm_manager, MotivoTramite amt_motivoTramite, String as_usuario,
	    String as_remoteIp, String as_estadoActividad, boolean ab_validarUsuario, boolean ab_invocarProcCrearEtapa
	)
	    throws B2BException
	{
		return terminarTurnoHistoriaYCrearEtapa(
		    ath_parametros, adm_manager, amt_motivoTramite, as_usuario, as_remoteIp, as_estadoActividad,
		    ab_validarUsuario, ab_invocarProcCrearEtapa, true, false
		);
	}

	/**
	 * Verifica que el correo electrónico sea válido.
	 *
	 * @param as_correoElectronico Objeto contenedor del correo a validar
	 * @return true si el correo es válido, false si no lo es
	 */
	protected boolean validarCorreoElectronico(String as_correoElectronico)
	{
		return StringUtils.isValidString(as_correoElectronico)
			&& ExpresionRegular.getExpresionRegular().esCorreoElectronico(as_correoElectronico)
			&& (as_correoElectronico.length() < 100);
	}

	/**
	 * Verifica que el id cuenta cupo ingresado en la operación sea válido, exista y esté activo.
	 *
	 * @param as_idCuentaCupo Id del cuenta cupo a verificar
	 * @param ace_codigoError Objeto contenedor del código de respuesta de la operación
	 * @param adm_manager Conexión a la base de datos
	 * @return el valor de cuenta cupo
	 * @throws B2BException Si no se cumplen las validaciones para el servicio
	 */
	protected CuentaCupo validarCuentaCupo(String as_idCuentaCupo, CodigoError ace_codigoError, DAOManager adm_manager)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_idCuentaCupo))
			throw new B2BException(
			    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_ID_CUENTA_CUPO_NO_VALIDO)
			);

		CuentaCupo lcc_cuentaCupo;

		lcc_cuentaCupo = DaoCreator.getCuentaCupoDAO(adm_manager).findById(as_idCuentaCupo);

		if(lcc_cuentaCupo != null)
		{
			String ls_estado;

			ls_estado = lcc_cuentaCupo.getEstado();

			if(!(StringUtils.isValidString(ls_estado) && ls_estado.equals(EstadoCommon.ACTIVO_TXT)))
			{
				ace_codigoError.setCodigoError(BigInteger.valueOf(417L));

				Object[] loa_args;

				loa_args     = new String[1];

				loa_args[0] = ls_estado;

				throw new B2BException(
				    addMessageGCC(
				        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_CUENTA_CUPO_INACTIVA, loa_args
				    )
				);
			}
		}
		else
		{
			ace_codigoError.setCodigoError(BigInteger.valueOf(416L));

			throw new B2BException(
			    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_CUENTA_CUPO_NO_EXISTE)
			);
		}

		return lcc_cuentaCupo;
	}

	/**
	 * Validar datos auditoria.
	 *
	 * @param as_idUsuario de as id usuario
	 * @param as_ipRemota de as ip remota
	 * @return true, si tiene éxito
	 * @throws B2BException de b 2 B exception
	 */
	protected boolean validarDatosAuditoria(String as_idUsuario, String as_ipRemota)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idUsuario) && StringUtils.isValidString(as_ipRemota))
			return true;
		else
			throw new B2BException(ErrorKeys.DATOS_AUDITORIA_INVALIDOS);
	}

	/**
	 * Valida los campos obligatorios de dirección de residencia o correspondencia.
	 *
	 * @param apd_dir Objeto contenedor de los datos a validar
	 * @param ab_residencia true para indicar que es una dirección de residencia, false para correspondencia
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void validarDireccion(PersonaDireccion apd_dir, boolean ab_residencia)
	    throws B2BException
	{
		if(apd_dir != null)
		{
			{
				String ls_tipoDireccion;
				ls_tipoDireccion = apd_dir.getTipoDireccion();

				if(!StringUtils.isValidString(ls_tipoDireccion))
				{
					if(ab_residencia)
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DIRECCION);
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DIRECCION_COR);
				}
			}

			{
				String ls_pais;
				ls_pais = apd_dir.getIdPais();

				if(!StringUtils.isValidString(ls_pais))
				{
					if(ab_residencia)
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_CORRESPONDENCIA);
				}

				if(
				    !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
					    && !StringUtils.isValidString(apd_dir.getComplementoDireccion())
				)
				{
					if(ab_residencia)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_COMPLEMENTO_RESIDENCIA);
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_COMPLEMENTO_CORRESPONDENCIA);
				}
			}

			{
				String ls_idDepartamento;

				ls_idDepartamento = apd_dir.getIdDepartamento();

				if(StringUtils.isValidString(ls_idDepartamento))
				{
					String ls_idMunicipio;

					ls_idMunicipio = apd_dir.getIdMunicipio();

					if(!StringUtils.isValidString(ls_idMunicipio))
					{
						if(ab_residencia)
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUN_RESIDENCIA);
						else
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUN_CORRESPONDENCIA);
					}
				}
				else if(ab_residencia)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEP_RESIDENCIA);
				else
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEP_CORRESPONDENCIA);
			}

			{
				String ls_tipoEje;
				ls_tipoEje = apd_dir.getIdTipoEjePrincipal();

				if(!StringUtils.isValidString(ls_tipoEje))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);
			}

			{
				String ls_ejePrincipal;
				ls_ejePrincipal = apd_dir.getDatoEjePrincipal();

				if(!StringUtils.isValidString(ls_ejePrincipal))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_EJE_PRINCIPAL);
			}
		}
		else
			throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);
	}

	/**
	 * Validar matricula coexistencia numerica.
	 *
	 * @param as_numeroIdentificacionPredio de as numero identificacion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void validarMatriculaCoexistenciaNumerica(String as_numeroIdentificacionPredio)
	    throws B2BException
	{
		validarMatriculaCoexistenciaNumerica(as_numeroIdentificacionPredio, ProyectosCommon.COEXISTENCIA_14);
	}

	/**
	 * Validar matricula coexistencia numerica.
	 *
	 * @param as_numeroIdentificacionPredio de numero identificacion predio
	 * @param as_proyecto correspondiente al valor de proyecto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void validarMatriculaCoexistenciaNumerica(String as_numeroIdentificacionPredio, String as_proyecto)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_numeroIdentificacionPredio) && (as_numeroIdentificacionPredio.length() >= 3))
		{
			String ls_idMatricula;

			ls_idMatricula = as_numeroIdentificacionPredio.substring(3);

			if(
			    !StringUtils.isValidString(ls_idMatricula)
				    || (StringUtils.isValidString(ls_idMatricula) && !StringUtils.isNumeric(ls_idMatricula))
			)
			{
				switch(as_proyecto)
				{
					case ProyectosCommon.CATASTRO_10:
						throw new B2BException(
						    addErrorCTO(
						        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_TIPO_ID_CATASTRAL_NO_VALIDO_CODIGO
						    )
						);

					case ProyectosCommon.COEXISTENCIA_14:

						Object[] loa_arg;
						loa_arg = new String[1];
						loa_arg[0] = ls_idMatricula;

						throw new B2BException(
						    addErrorCX(
						        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_MATRICULA_INVALIDA, loa_arg
						    )
						);

					default:
						break;
				}
			}
		}
		else
		{
			switch(as_proyecto)
			{
				case ProyectosCommon.CATASTRO_10:
					throw new B2BException(
					    addErrorCTO(
					        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_TIPO_ID_CATASTRAL_NO_VALIDO_CODIGO
					    )
					);

				case ProyectosCommon.COEXISTENCIA_14:
					throw new B2BException(
					    addErrorCX(com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_MATRICULA_INVALIDA_FORMATO)
					);

				default:
					break;
			}
		}
	}

	/**
	 * Verifica que el modulo sea de sede electrónica.
	 *
	 * @param as_modulo Objeto contenedor del modulo a validar
	 * @return true si el modulo es de sede electrónica
	 */
	protected boolean validarModuloSE(String as_modulo)
	{
		return StringUtils.isValidString(as_modulo) && as_modulo.equals(SistemaOrigenCommon.SEDE_ELECTRONICA);
	}

	/**
	 * Validar numero identificacion predio.
	 *
	 * @param lc_datos de lc datos
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected boolean validarNumeroIdentificacionPredio(Predio lc_datos)
	    throws B2BException
	{
		return validarNumeroIdentificacionPredio(lc_datos, ProyectosCommon.CATASTRO_10);
	}

	/**
	 * Validar numero identificacion predio.
	 *
	 * @param lc_datos de lc datos
	 * @param as_proyecto de as proyecto
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected boolean validarNumeroIdentificacionPredio(Predio lc_datos, String as_proyecto)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if((lc_datos != null) && StringUtils.isValidString(as_proyecto))
		{
			String ls_numeroIdentificacion;

			ls_numeroIdentificacion = lc_datos.getNumeroIdentificacionPredio();

			if(
			    StringUtils.isValidString(ls_numeroIdentificacion) && (ls_numeroIdentificacion.length() <= 50)
				    && (as_proyecto.equalsIgnoreCase(ProyectosCommon.CATASTRO_10)
				    || as_proyecto.equalsIgnoreCase(ProyectosCommon.COEXISTENCIA_14))
			)
			{
				lb_return = true;

				if(lc_datos.isFolioMatricula())
				{
					Matricula lm_matricula;
					validarMatriculaCoexistenciaNumerica(lc_datos.getNumeroIdentificacionPredio(), as_proyecto);

					try
					{
						lm_matricula = Matricula.getMatriculaCTandCX(ls_numeroIdentificacion);

						if(lm_matricula != null)
						{
							lc_datos.setIdCirculo(lm_matricula.getCirculo());
							lc_datos.setIdMatricula(lm_matricula.getMatricula());
						}
					}
					catch(Exception lb2be_e)
					{
						lb_return = false;

						switch(as_proyecto)
						{
							case ProyectosCommon.CATASTRO_10:
								throw new B2BException(
								    addErrorCTO(
								        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_TIPO_ID_CATASTRAL_NO_VALIDO_CODIGO
								    )
								);

							case ProyectosCommon.COEXISTENCIA_14:
								throw new B2BException(
								    addErrorCX(
								        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_INFORMACION_NUMERO_IDENTIFICACION_PREDIO
								    )
								);

							default:
								break;
						}
					}
				}
			}
			else
			{
				switch(as_proyecto)
				{
					case ProyectosCommon.CATASTRO_10:
						throw new B2BException(
						    addErrorCTO(
						        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_VALIDO_CODIGO
						    )
						);

					case ProyectosCommon.COEXISTENCIA_14:
						throw new B2BException(
						    addErrorCX(
						        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_INFORMACION_NUMERO_IDENTIFICACION_PREDIO
						    )
						);

					default:
						break;
				}
			}
		}

		return lb_return;
	}

	/**
	 * Verifica que la información de ubicación sea válida.
	 *
	 * @param as_idPais id del país a validar
	 * @param as_idDepartamento id del departamento a validar
	 * @param as_idMunicipio id del municipio a validar
	 * @param ab_validarPais true para indicar que se debe validar el país
	 * @param ab_validarDepto true para indicar que se debe validar el departamento
	 * @param ab_validarMunicipio true para indicar que se debe validar el municipio
	 * @param ls_proyecto de ls proyecto
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	protected void validarPaisDepMun(
	    String as_idPais, String as_idDepartamento, String as_idMunicipio, boolean ab_validarPais,
	    boolean ab_validarDepto, boolean ab_validarMunicipio, String ls_proyecto, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(ls_proyecto))
		{
			boolean lb_idPaisValido;
			boolean lb_idDepartamentoValido;
			boolean lb_idMunicipioValido;

			lb_idPaisValido             = StringUtils.isValidString(as_idPais);
			lb_idDepartamentoValido     = StringUtils.isValidString(as_idDepartamento);
			lb_idMunicipioValido        = StringUtils.isValidString(as_idMunicipio);

			if(ab_validarPais)
			{
				if(!lb_idPaisValido)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_PAIS)
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_SIN_PAIS_CODIGO)
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_PAIS_DIRECCION
							    )
							);

						default:
							break;
					}
				}

				Pais lp_pais;

				lp_pais = DaoCreator.getPaisDAO(adm_manager).findById(as_idPais);

				if(lp_pais == null)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_PAIS_NO_VALIDO)
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_PAIS_NO_VALIDO_CODIGO
							    )
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_PAIS_DIRECCION_NO_VALIDO
							    )
							);

						default:
							break;
					}
				}
			}

			if(ab_validarDepto)
			{
				if(!lb_idPaisValido)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_PAIS)
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_SIN_PAIS_CODIGO)
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_PAIS_DIRECCION
							    )
							);

						default:
							break;
					}
				}

				if(!lb_idDepartamentoValido)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_DEPARTAMENTO
							    )
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_CODIGO_DEPARTAMENTO_NO_VALIDO_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(
							        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CODIGO_DEPARTAMENTO
							    )
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_DEPARTAMENTO_DIRECCION
							    )
							);

						default:
							break;
					}
				}

				Departamento ld_departamento;

				ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager).findById(as_idPais, as_idDepartamento);

				if(ld_departamento == null)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_DEPARTAMENTO_NO_VALIDO
							    )
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_CODIGO_DEPARTAMENTO_NO_VALIDO_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(
							        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CODIGO_DEPARTAMENTO
							    )
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_DEPARTAMENTO_DIRECCION_NO_VALIDO
							    )
							);

						default:
							break;
					}
				}
			}

			if(ab_validarMunicipio)
			{
				if(!lb_idPaisValido)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_PAIS)
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_SIN_PAIS_CODIGO)
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_PAIS_DIRECCION
							    )
							);

						default:
							break;
					}
				}

				if(!lb_idDepartamentoValido)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_DEPARTAMENTO
							    )
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_CODIGO_DEPARTAMENTO_NO_VALIDO_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(
							        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CODIGO_DEPARTAMENTO
							    )
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_DEPARTAMENTO_DIRECCION
							    )
							);

						default:
							break;
					}
				}

				if(!lb_idMunicipioValido)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_MUNICIPIO)
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_CODIGO_MUNICIPIO_NO_VALIDO_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CODIGO_MUNICIPIO)
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_MUNICIPIO_DIRECCION
							    )
							);

						default:
							break;
					}
				}

				Municipio lm_municipio;

				lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
						                     .findById(as_idPais, as_idDepartamento, as_idMunicipio);

				if(lm_municipio == null)
				{
					switch(ls_proyecto)
					{
						case ProyectosCommon.GESTION_CUENTA_CUPOS_12:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_MUNICIPIO_NO_VALIDO
							    )
							);

						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_CODIGO_MUNICIPIO_NO_VALIDO_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_CODIGO_MUNICIPIO)
							);

						case ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION:
							throw new B2BException(
							    addMessageGCC(
							        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_MUNICIPIO_DIRECCION_NO_VALIDO
							    )
							);

						default:
							break;
					}
				}
			}
		}
	}

	/**
	 * Verifica que la información de ubicación sea válida.
	 *
	 * @param as_idPais id del país a validar
	 * @param as_idDepartamento id del departamento a validar
	 * @param as_idMunicipio id del municipio a validar
	 * @param ls_proyecto de ls proyecto
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	protected void validarPaisDepMun(
	    String as_idPais, String as_idDepartamento, String as_idMunicipio, String ls_proyecto, DAOManager adm_manager
	)
	    throws B2BException
	{
		validarPaisDepMun(as_idPais, as_idDepartamento, as_idMunicipio, true, true, true, ls_proyecto, adm_manager);
	}

	/**
	 * Verifica que el identificador copias SE sea válido y exista.
	 *
	 * @param as_identificadorCopiasSE Identificador copias SE a verificar.
	 * @param ace_codigoError Objeto contenedor del código de respuesta de la operación.
	 * @param adm_manager Conexión a la base de datos.
	 * @throws B2BException Si no se cumplen las validaciones para el servicio
	 */
	protected void validarSolicitudCopias(
	    String as_identificadorCopiasSE, CodigoError ace_codigoError, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_identificadorCopiasSE))
			throw new B2BException(
			    addMessageGCC(
			        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_IDENTIFICADOR_COPIAS_SE_NO_VALIDO
			    )
			);

		Collection<SolicitudCopias> lcsc_return;

		lcsc_return = DaoCreator.getSolicitudCopiasDAO(adm_manager).findByIdentificadorCopiasSE(
			    as_identificadorCopiasSE
			);

		if(!CollectionUtils.isValidCollection(lcsc_return))
		{
			ace_codigoError.setCodigoError(BigInteger.valueOf(416L));

			throw new B2BException(
			    addMessageGCC(
			        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_IDENTIFICADOR_COPIAS_SE_NO_EXISTE
			    )
			);
		}
	}

	/**
	 * Verifica la información obligatoria para el teléfono de una persona.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param apt_tel Objeto contenedor de la infrmación a valdiar
	 * @param ab_fijo true para indicar que el teléfono es fijo, false para movil
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void validarTelefono(DAOManager adm_manager, PersonaTelefono apt_tel, boolean ab_fijo)
	    throws B2BException
	{
		if(apt_tel != null)
		{
			String ls_pais;

			ls_pais = apt_tel.getIdPais();

			if(!StringUtils.isValidString(ls_pais))
				throw new B2BException(
				    ab_fijo ? ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_FIJO : ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_MOVIL
				);

			if(ab_fijo)
			{
				{
					String ls_departamento;

					ls_departamento = apt_tel.getIdDepartamento();

					if(!StringUtils.isValidString(ls_departamento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEP_TEL_FIJO);
				}

				{
					String ls_indicativo;

					ls_indicativo = apt_tel.getIndicativo();

					if(!StringUtils.isValidString(ls_indicativo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_INDICATIVO);
				}
			}

			{
				String ls_num;
				Long   ll_telefono;

				ls_num          = apt_tel.getTelefono();
				ll_telefono     = NumericUtils.getLongWrapper(ls_num);

				if(!StringUtils.isValidString(ls_num))
					throw new B2BException(
					    ab_fijo ? ErrorKeys.DEBE_DIGITAR_TELEFONO_FIJO : ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL
					);

				if(!NumericUtils.isValidLong(ll_telefono, 1))
					throw new B2BException(
					    ab_fijo ? ErrorKeys.DEBE_DIGITAR_TELEFONO_FIJO_VALIDO
					            : ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL_VALIDO
					);

				{
					String     ls_idConstante;
					Constantes lc_digitos;

					if(ab_fijo)
						ls_idConstante = ConstanteCommon.DIGITOS_TEL_FIJO_COL;
					else
						ls_idConstante = ConstanteCommon.DIGITOS_TEL_MOVIL_COL;

					lc_digitos = DaoCreator.getConstantesDAO(adm_manager).findById(ls_idConstante);

					if(lc_digitos == null)
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ls_idConstante;

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
					}

					BigInteger lbi_temp;
					int        li_digitos;

					lbi_temp       = lc_digitos.getEntero();
					li_digitos     = 0;

					if(NumericUtils.isValidBigInteger(lbi_temp))
						li_digitos = lbi_temp.intValue();

					if(ls_pais.equals(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT) && (ls_num.length() != li_digitos))
						throw new B2BException(
						    ab_fijo ? ErrorKeys.ERROR_TELEFONO_FIJO_MALFORMADO : ErrorKeys.ERROR_TELEFONO_MOVIL_MALFORMADO
						);
				}
			}
		}
		else
			throw new B2BException(
			    ab_fijo ? ErrorKeys.DEBE_DIGITAR_TELEFONO_FIJO : ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL
			);
	}

	/**
	 * Validar tipo identificacion predio.
	 *
	 * @param lc_datos de lc datos
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected boolean validarTipoIdentificacionPredio(Predio lc_datos)
	    throws B2BException
	{
		return validarTipoIdentificacionPredio(lc_datos, ProyectosCommon.CATASTRO_10);
	}

	/**
	 * Validar tipo identificacion predio.
	 *
	 * @param lc_datos de lc datos
	 * @param as_proyecto de as proyecto
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected boolean validarTipoIdentificacionPredio(Predio lc_datos, String as_proyecto)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if((lc_datos != null) && StringUtils.isValidString(as_proyecto))
		{
			String ls_tipoIdentificacion;

			ls_tipoIdentificacion = lc_datos.getTipoIdentificacionPredio();

			if(
			    (StringUtils.isValidString(ls_tipoIdentificacion) && (ls_tipoIdentificacion.length() <= 50)
				    && as_proyecto.equalsIgnoreCase(ProyectosCommon.CATASTRO_10))
				    || (as_proyecto.equalsIgnoreCase(ProyectosCommon.COEXISTENCIA_14))
			)
			{
				if(ls_tipoIdentificacion.equals(TipoIdentificacionPredioCommon.MATRICULA))
				{
					lc_datos.setFolioMatricula(true);

					lb_return = true;
				}
				else if(ls_tipoIdentificacion.equals(TipoIdentificacionPredioCommon.NUPRE))
				{
					lc_datos.setNupre(true);

					lb_return = true;
				}
				else if(ls_tipoIdentificacion.equals(TipoIdentificacionPredioCommon.NUMERO_PREDIAL))
				{
					lc_datos.setNumeroPredial(true);

					lb_return = true;
				}
				else if(ls_tipoIdentificacion.equals(TipoIdentificacionPredioCommon.NUMERO_PREDIAL_ANTERIOR))
				{
					lc_datos.setNumeroPredialAnterior(true);

					lb_return = true;
				}
				else
				{
					switch(as_proyecto)
					{
						case ProyectosCommon.CATASTRO_10:
							throw new B2BException(
							    addErrorCTO(
							        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_TIPO_ID_CATASTRAL_NO_EXISTENTE_CODIGO
							    )
							);

						case ProyectosCommon.COEXISTENCIA_14:
							throw new B2BException(
							    addErrorCX(
							        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_VALORES_TIPO_IDENTIFICACION_PREDIO
							    )
							);

						default:
							break;
					}
				}
			}
			else
			{
				switch(as_proyecto)
				{
					case ProyectosCommon.CATASTRO_10:
						throw new B2BException(
						    addErrorCTO(
						        com.bachue.snr.prosnr10.common.constants.ErrorKeys.ERROR_TIPO_ID_CATASTRAL_NO_VALIDO_CODIGO
						    )
						);

					case ProyectosCommon.COEXISTENCIA_14:
						throw new B2BException(
						    addErrorCX(
						        com.bachue.snr.prosnr14.common.constants.ErrorKeys.ERROR_VALORES_TIPO_IDENTIFICACION_PREDIO
						    )
						);

					default:
						break;
				}
			}
		}

		return lb_return;
	}

	/**
	 * Agrega un string a un stringbuilder y posterior agrega un espacio vacio.
	 *
	 * @param asb_builder Stringbuilder a agregar cadena
	 * @param as_cadena cadena a agregar
	 */
	private void agregarTextoAStringBuilder(StringBuilder asb_builder, String as_cadena)
	{
		if((asb_builder != null) && StringUtils.isValidString(as_cadena))
		{
			asb_builder.append(as_cadena);
			asb_builder.append(IdentificadoresCommon.ESPACIO_VACIO);
		}
	}

	/**
	 * Método para armar el menú de conciliaciones y Workflow.
	 *
	 * @param ac_tipoAcceso the ac tipo acceso
	 * @return the collection
	 */

	//TODO revisar por que no se usa este método
	@SuppressWarnings("unused")
	private Collection<Opcion> armarOpcionesComponente(Collection<TipoAcceso> ac_tipoAcceso)
	{
		Collection<Opcion> lco_coReturn;
		lco_coReturn = new LinkedList<Opcion>();

		if(CollectionUtils.isValidCollection(ac_tipoAcceso))
		{
			for(TipoAcceso ita_tipoAcceso : ac_tipoAcceso)
			{
				if(ita_tipoAcceso != null)
				{
					Opcion lo_opcion;
					lo_opcion = new Opcion();

					String ls_iterador;
					ls_iterador     = null;
					ls_iterador     = ita_tipoAcceso.getIdOpcion();

					if(StringUtils.isValidString(ls_iterador))
						lo_opcion.setIdOpcion(ls_iterador);

					ls_iterador = ita_tipoAcceso.getOpcion();

					if(StringUtils.isValidString(ls_iterador))
						lo_opcion.setOpcion(ls_iterador);

					ls_iterador = ita_tipoAcceso.getDescripcion();

					if(StringUtils.isValidString(ls_iterador))
						lo_opcion.setDescripcion(ls_iterador);

					ls_iterador = ita_tipoAcceso.getOpcionPadre();

					if(StringUtils.isValidString(ls_iterador))
						lo_opcion.setIdOpcionPadre(ls_iterador);

					ls_iterador = ita_tipoAcceso.getTipo();

					if(!StringUtils.isValidString(ls_iterador))
						ls_iterador = "MENU";

					lo_opcion.setTipo(ls_iterador);
					ls_iterador = ita_tipoAcceso.getUrl();

					if(StringUtils.isValidString(ls_iterador))
						lo_opcion.setUrl(ls_iterador);

					ls_iterador = ita_tipoAcceso.getActivo();

					if(StringUtils.isValidString(ls_iterador))
						lo_opcion.setActivo(ls_iterador);

					if(ls_iterador.equals("A"))
						lco_coReturn.add(lo_opcion);
				}
			}
		}

		return lco_coReturn;
	}

	/**
	 * Consulta el nombre de un tipo ele por medio de su identificador y lo agrega a una cadena de caracteres.
	 *
	 * @param asb_direccion Objeto contenedor de la cadena de caracteres
	 * @param as_idTipoEje id del tipo eje a consultar
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void obtenerEjeDireccion(StringBuilder asb_direccion, String as_idTipoEje, DAOManager adm_manager)
	    throws B2BException
	{
		if((asb_direccion != null) && StringUtils.isValidString(as_idTipoEje))
		{
			TipoEje lte_tipoEje;

			lte_tipoEje = DaoCreator.getTipoEjeDAO(adm_manager).findById(as_idTipoEje);

			if(lte_tipoEje != null)
			{
				String ls_nombre;

				ls_nombre = lte_tipoEje.getNombre();

				if(StringUtils.isValidString(ls_nombre))
				{
					asb_direccion.append(ls_nombre);
					asb_direccion.append(IdentificadoresCommon.ESPACIO_VACIO);
				}
			}
		}
	}

	/**
	 * Reemplazar tipo docs salida.
	 *
	 * @param ads_documento de ads documento
	 * @return el valor de documentos salida
	 */
	private DocumentosSalida reemplazarTipoDocsSalida(DocumentosSalida ads_documento)
	{
		if(ads_documento != null)
		{
			String ls_tipo;

			ls_tipo = ads_documento.getTipo();

			if(StringUtils.isValidString(ls_tipo))
				ads_documento.setTipo(reemplazarGuionesAlPiso(ls_tipo));

			if(ads_documento.getFechaEnvioComponente() == null)
				ads_documento.setFechaEnvioComponente(ads_documento.getFechaPublicacionAvisoWeb());

			if(ads_documento.getFechaAcuseReciboComponente() == null)
				ads_documento.setFechaAcuseReciboComponente(ads_documento.getFechaDesfijacionAviso());
		}

		return ads_documento;
	}

	/**
	 * Resuelve la fecha, en el formato establecido por parametrización, en la base de ruta y nombre de archivo.
	 *
	 * @param asb_base Plantilla de base de ruta o nombre de archivo
	 * @param as_tag Elemento que identifica la posición donde debe reemplazarse la fecha
	 * @param ad_fecha Fecha a remplazar en el formato establecido por parametrización
	 * @return el valor de string builder
	 */
	private StringBuilder resolver(StringBuilder asb_base, String as_tag, Date ad_fecha)
	{
		if((asb_base != null) && (asb_base.length() > 0) && StringUtils.isValidString(as_tag) && (ad_fecha != null))
		{
			int    li_posicionInicial;
			String ls_tag;

			{
				StringBuilder lsb_tag;

				lsb_tag = new StringBuilder("<");

				lsb_tag.append(as_tag);
				lsb_tag.append("_");

				ls_tag                 = lsb_tag.toString();
				li_posicionInicial     = asb_base.indexOf(ls_tag);
			}

			while(li_posicionInicial >= 0)
			{
				int    li_posicionFinal;
				int    li_posicionRelleno;
				String ls_reemplazo;

				li_posicionFinal       = asb_base.indexOf(">", li_posicionInicial);
				li_posicionRelleno     = li_posicionInicial + ls_tag.length();
				ls_reemplazo           = new String();

				if(li_posicionFinal < li_posicionInicial)
					li_posicionFinal = asb_base.length() - 1;

				if(li_posicionRelleno < li_posicionFinal)
				{
					String ls_formato;

					ls_formato = asb_base.substring(li_posicionRelleno, li_posicionFinal);

					if(StringUtils.isValidString(ls_formato))
						ls_reemplazo = StringUtils.getString(ad_fecha, ls_formato);
				}

				asb_base.replace(li_posicionInicial, li_posicionFinal + 1, ls_reemplazo);

				li_posicionInicial = asb_base.indexOf(ls_tag);
			}
		}

		return asb_base;
	}

	/**
	 * Resuelve un elemento, en el formato establecido por parametrización, en la base de ruta y nombre de archivo.
	 *
	 * @param asb_base Plantilla de base de ruta o nombre de archivo
	 * @param as_tag Elemento que identifica la posición donde debe reemplazarse la fecha
	 * @param as_reemplazo de as reemplazo
	 * @return el valor de string builder
	 */
	private StringBuilder resolver(StringBuilder asb_base, String as_tag, String as_reemplazo)
	{
		if(
		    (asb_base != null) && (asb_base.length() > 0) && StringUtils.isValidString(as_tag)
			    && StringUtils.isValidString(as_reemplazo)
		)
		{
			int    li_posicionInicial;
			String ls_tag;

			{
				StringBuilder lsb_tag;

				lsb_tag = new StringBuilder("<");

				lsb_tag.append(as_tag);

				ls_tag                 = lsb_tag.toString();
				li_posicionInicial     = asb_base.indexOf(ls_tag);
			}

			while(li_posicionInicial >= 0)
			{
				int           li_posicionFinal;
				int           li_posicionRelleno;
				StringBuilder lsb_reemplazo;

				li_posicionFinal       = asb_base.indexOf(">", li_posicionInicial);
				li_posicionRelleno     = li_posicionInicial + ls_tag.length() + 1;
				lsb_reemplazo          = new StringBuilder(as_reemplazo);

				if(li_posicionFinal < li_posicionInicial)
					li_posicionFinal = asb_base.length() - 1;

				if(li_posicionRelleno < li_posicionFinal)
				{
					boolean lb_tamano;
					boolean lb_tamanoFijo;
					int     li_tamano;
					String  ls_relleno;

					lb_tamano         = false;
					lb_tamanoFijo     = false;
					li_tamano         = 0;
					ls_relleno        = null;

					{
						String[] lsa_parametros;

						{
							String ls_parametros;

							ls_parametros      = asb_base.substring(li_posicionRelleno, li_posicionFinal);
							lsa_parametros     = ls_parametros.split("_");
						}

						if(lsa_parametros != null)
						{
							int li_parametros;

							li_parametros = lsa_parametros.length;

							if(li_parametros > 0)
							{
								String ls_tmp;

								ls_tmp         = lsa_parametros[0];
								ls_relleno     = (ls_tmp != null) ? ls_tmp.substring(0, 1) : null;
							}

							if(li_parametros > 1)
							{
								String ls_tmp;
								ls_tmp        = lsa_parametros[1];
								lb_tamano     = NumberUtils.isCreatable(ls_tmp);

								if(lb_tamano)
									li_tamano = Integer.parseInt(ls_tmp);
							}

							if(li_parametros > 2)
							{
								String ls_tmp;

								ls_tmp            = lsa_parametros[2];
								lb_tamanoFijo     = (ls_tmp != null) && ls_tmp.startsWith("F");
							}
						}
					}

					if(StringUtils.isValidString(ls_relleno) && lb_tamano)
					{
						boolean lb_insertar;
						int     li_iteraciones;

						lb_insertar        = li_tamano > 0;
						li_tamano          = Math.abs(li_tamano);
						li_iteraciones     = li_tamano - lsb_reemplazo.length();

						if(li_iteraciones > 0)
						{
							for(int li_i = 0; li_i < li_iteraciones; li_i++)
							{
								if(lb_insertar)
									lsb_reemplazo.insert(0, ls_relleno);
								else
									lsb_reemplazo.append(ls_relleno);
							}
						}

						if(lb_tamanoFijo)
						{
							if(lb_insertar)
								lsb_reemplazo.delete(0, lsb_reemplazo.length() - li_tamano);
							else
								lsb_reemplazo.delete(li_tamano, lsb_reemplazo.length());
						}
					}
				}

				asb_base.replace(li_posicionInicial, li_posicionFinal + 1, lsb_reemplazo.toString());

				li_posicionInicial = asb_base.indexOf(ls_tag);
			}
		}

		return asb_base;
	}
}
