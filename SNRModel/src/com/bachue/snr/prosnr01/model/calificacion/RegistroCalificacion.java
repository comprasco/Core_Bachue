package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Class que contiene todos las propiedades RegistroCalificacion.
 *
 * @author hcastaneda
 */
public class RegistroCalificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3501510832167651845L;

	/** Propiedad iaaui datos area. */
	private AccAreaUI iaaui_datosArea;

	/** Propiedad iodc data anotacion cancelacion. */
	private AnotacionCancelacion iodc_dataAnotacionCancelacion;

	/** Propiedad ioda data anotacion predio. */
	private AnotacionPredio ioda_dataAnotacionPredio;

	/** Propiedad ioap data anotacion predio ciudadano. */
	private AnotacionPredioCiudadano ioap_dataAnotacionPredioCiudadano;

	/** Propiedad iap apertura. */
	private Apertura iap_apertura;

	/** Propiedad ibd valor. */
	private BigDecimal ibd_valor;

	/** Propiedad ic data confrontacion. */
	private Calificacion ic_dataConfrontacion;

	/** Propiedad icep cambio estado predio. */
	private CambioEstadoPredio icep_cambioEstadoPredio;

	/** Propiedad ica info intervinientes. */
	private Collection<Anotacion> ica_infoIntervinientes;

	/** Propiedad ica intervinientes. */
	private Collection<Anotacion> ica_intervinientes;

	/**  Propiedad icap_anotacionesPredioEliminadas. */
	private Collection<AnotacionPredio> icap_anotacionesPredioEliminadas;

	/** Propiedad icap matriculas informacion. */
	private Collection<AreaPredio> icap_matriculasInformacion;

	/** Propiedad icap matriculas segregadas. */
	private Collection<AreaPredio> icap_matriculasSegregadas;

	/** Propiedad icddp direcciones del predio. */
	private Collection<DireccionDelPredio> icddp_direccionesDelPredio;

	/** Propiedad icdp direcciones. */
	private Collection<DireccionPredio> icdp_direcciones;

	/** Propiedad icdpa direcciones acc. */
	private Collection<DireccionPredioAcc> icdpa_direccionesAcc;

	/** Propiedad icl anotaciones parciales. */
	private Collection<Long> icl_anotacionesParciales;

	/** Propiedad icms info matriculas segregacion. */
	private Collection<MatriculaSegregacion> icms_infoMatriculasSegregacion;

	/** Propiedad icrc all matriculas. */
	private Collection<RegistroCalificacion> icrc_allMatriculas;

	/** Propiedad icrc anotaciones cancelacion. */
	private Collection<RegistroCalificacion> icrc_anotacionesCancelacion;

	/** Propiedad icrc anotaciones heredar. */
	private Collection<RegistroCalificacion> icrc_anotacionesHeredar;

	/** Propiedad icrc info file. */
	private Collection<RegistroCalificacion> icrc_infoFile;

	/** Propiedad icsi intervinientes ingresados. */
	private Collection<SolicitudInterviniente> icsi_intervinientesIngresados;

	/** Propiedad icsm matriculas copiar. */
	private Collection<SolicitudMatricula> icsm_matriculasCopiar;

	/** Propiedad icc complementacion calificacion. */
	private ComplementacionCalificacion icc_complementacionCalificacion;

	/** Propiedad id fecha apertura. */
	private Date id_fechaApertura;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad id fecha radicacion. */
	private Date id_fechaRadicacion;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad iodas antiguo sistema data. */
	private DatosAntSistema iodas_antiguoSistemaData;

	/** Propiedad idb datos basicos. */
	private DatosBasicos idb_datosBasicos;

	/** Propiedad iodp data direccion predio. */
	private DireccionDelPredio iodp_dataDireccionPredio;

	/** Propiedad idp direccion guardar. */
	private DireccionPredio idp_direccionGuardar;

	/** Propiedad iod data documento. */
	private Documento iod_dataDocumento;

	/** Propiedad id porcentaje. */
	private Double id_porcentaje;

	/** Propiedad ihmsdp direcciones seleccionadas. */
	private HashMap<String, DireccionPredio> ihmsdp_direccionesSeleccionadas;

	/** Propiedad ilrc lindero registro calificacion. */
	private LinderoRegistroCalificacion ilrc_linderoRegistroCalificacion;

	/** Propiedad il id anotacion. */
	private Long il_idAnotacion;

	/** Propiedad il id image. */
	private Long il_idImage;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad il id matricula matriz. */
	private Long il_idMatriculaMatriz;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad il matriculas aperturar. */
	private Long il_matriculasAperturar;

	/** Propiedad il orden. */
	private Long il_orden;

	/** Propiedad il valor anotacion. */
	private Long il_valorAnotacion;

	/** Propiedad il version. */
	private Long il_version;

	/** Propiedad ilhm datos parcial. */
	private Map<String, Boolean> ilhm_datosParcial;

	/** Propiedad ioo oficina origen medida cautelar. */
	private OficinaOrigen ioo_oficinaOrigenMedidaCautelar;

	/** Propiedad iop data persona. */
	private Persona iop_dataPersona;

	/** Propiedad iorc datos documento. */
	private RegistroCalificacion iorc_datosDocumento;

	/** Propiedad iorc detalle matricula. */
	private RegistroCalificacion iorc_detalleMatricula;

	/** Propiedad irc matriculas A heredar. */
	private RegistroCalificacion irc_matriculasAHeredar;

	/** Propiedad irpc registro parcial calificacion. */
	private RegistroParcialCalificacion irpc_registroParcialCalificacion;

	/** Propiedad is anotacion cancelacion. */
	private String is_anotacionCancelacion;

	/** Propiedad is cod acto. */
	private String is_codActo;

	/** Propiedad is cod documento. */
	private String is_codDocumento;

	/** Propiedad is cod estado anotacion. */
	private String is_codEstadoAnotacion;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is documento. */
	private String is_documento;

	/** Propiedad is especificacion. */
	private String is_especificacion;

	/** Propiedad is estado matricula. */
	private String is_estadoMatricula;

	/** Propiedad is estado predio. */
	private String is_estadoPredio;

	/** Propiedad is fase turno. */
	private String is_faseTurno;

	/** Propiedad is fecha documento str. */
	private String is_fechaDocumentoStr;

	/** Propiedad is id anotacion apertura. */
	private String is_idAnotacionApertura;

	/** Propiedad is id anotacion predio. */
	private String is_idAnotacionPredio;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id circulo matriz. */
	private String is_idCirculoMatriz;

	/** Propiedad is id ciudadano. */
	private String is_idCiudadano;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id detalle ant sistema. */
	private String is_idDetalleAntSistema;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id estado anotacion. */
	private String is_idEstadoAnotacion;

	/** Propiedad is id estado registro. */
	private String is_idEstadoRegistro;

	/** Propiedad is id intervinientes. */
	private String is_idIntervinientes;

	/** Propiedad is id motivo. */
	private String is_idMotivo;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id naturalez juridica. */
	private String is_idNaturalezJuridica;

	/** Propiedad is id oficina origen. */
	private String is_idOficinaOrigen;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tipo anotacion. */
	private String is_idTipoAnotacion;

	/** Propiedad is id tipo documento. */
	private String is_idTipoDocumento;

	/** Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/** Propiedad is id tipo ofina. */
	private String is_idTipoOfina;

	/** Propiedad is id tipo persona. */
	private String is_idTipoPersona;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is interesada rrr. */
	private String is_interesadaRrr;

	/** Propiedad is interesada rrr nombre. */
	private String is_interesadaRrrNombre;

	/** Propiedad is ip address. */
	private String is_ipAddress;

	/** Propiedad is mensaje. */
	private String is_mensaje;

	/** Propiedad is municipio documento. */
	private String is_municipioDocumento;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre acto. */
	private String is_nombreActo;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is nombre departamento. */
	private String is_nombreDepartamento;

	/** Propiedad is nombre doc. */
	private String is_nombreDoc;

	/** Propiedad is nombre grupo acto. */
	private String is_nombreGrupoActo;

	/** Propiedad is nombre municipio. */
	private String is_nombreMunicipio;

	/** Propiedad is nombre oficina. */
	private String is_nombreOficina;

	/** Propiedad is nombre oficina origen. */
	private String is_nombreOficinaOrigen;

	/** Propiedad is nombre pais. */
	private String is_nombrePais;

	/** Propiedad is nombre persona. */
	private String is_nombrePersona;

	/** Propiedad is nombre tipo doc. */
	private String is_nombreTipoDoc;

	/** Propiedad is nombre tipo entidad. */
	private String is_nombreTipoEntidad;

	/** Propiedad is nombre tipo oficina. */
	private String is_nombreTipoOficina;

	/** Propiedad is numero. */
	private String is_numero;

	/** Propiedad is numero proceso. */
	private String is_numeroProceso;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is porcentaje str. */
	private String is_porcentajeStr;

	/** Propiedad is primer apellido. */
	private String is_primerApellido;

	/** Propiedad is primer nombre. */
	private String is_primerNombre;

	/** Propiedad is proceso A validar. */
	private String is_procesoAValidar;

	/** Propiedad is radicacion. */
	private String is_radicacion;

	/** Propiedad is razon social. */
	private String is_razonSocial;

	/** Propiedad is referencia. */
	private String is_referencia;

	/** Propiedad is revision. */
	private String is_revision;

	/** Propiedad is rol persona. */
	private String is_rolPersona;

	/** Propiedad is segundo apellido. */
	private String is_segundoApellido;

	/** Propiedad is segundo nombre. */
	private String is_segundoNombre;

	/** Propiedad is tipo doc. */
	private String is_tipoDoc;

	/** Propiedad is tipo predio. */
	private String is_tipoPredio;

	/** Propiedad is tramite. */
	private String is_tramite;

	/** Propiedad is turno. */
	private String is_turno;

	/** Propiedad is user id. */
	private String is_userId;

	/** Propiedad is value interviniente. */
	private String is_valueInterviniente;

	/** Propiedad is value propietario. */
	private String is_valuePropietario;

	/** Propiedad izr zona registral. */
	private ZonaRegistral izr_zonaRegistral;

	/** Propiedad ib acto segregacion. */
	private boolean ib_actoSegregacion;

	/** Propiedad ib anotacion antiguo sistema. */
	private boolean ib_anotacionAntiguoSistema;

	/** Propiedad ib anotacion invalida. */
	private boolean ib_anotacionInvalida;

	/** Propiedad ib apertura matricula. */
	private boolean ib_aperturaMatricula;

	/** Propiedad ib area revision. */
	private boolean ib_areaRevision;

	/** Propiedad ib area validada. */
	private boolean ib_areaValidada;

	/** Propiedad ib baldios. */
	private boolean ib_baldios;

	/** Propiedad ib calificacion. */
	private boolean ib_calificacion;

	/** Propiedad ib cancelacion. */
	private boolean ib_cancelacion;

	/** Propiedad ib cementerio. */
	private boolean ib_cementerio;

	/** Propiedad ib cierre folio. */
	private boolean ib_cierreFolio;

	/** Propiedad ib copiar. */
	private boolean ib_copiar;

	/** Propiedad ib datos validos ant sistema. */
	private boolean ib_datosValidosAntSistema;

	/** Propiedad ib desenglobe. */
	private boolean ib_desenglobe;

	/** Propiedad ib devolucion. */
	private boolean ib_devolucion;

	/** Propiedad ib digitador masivo. */
	private boolean ib_digitadorMasivo;

	/** Propiedad ib division material. */
	private boolean ib_divisionMaterial;

	/** Propiedad ib englobe. */
	private boolean ib_englobe;

	/** Propiedad ib englobe anotacion. */
	private boolean ib_englobeAnotacion;

	/** Propiedad ib envio calificador. */
	private boolean ib_envioCalificador;

	/** Propiedad ib envio digitador. */
	private boolean ib_envioDigitador;

	/** Propiedad ib_generarArchivoNotaExcesoPago. */
	private boolean ib_generarArchivoNotaExcesoPago;

	/** Propiedad ib generar archivo parcial. */
	private boolean ib_generarArchivoParcial;

	/** Propiedad ib generarPDF. */
	private boolean ib_generarPDFCorrespondenciaRegistro;

	/** Propiedad ib image valid. */
	private boolean ib_imageValid;

	/** Propiedad ib ind dummy. */
	private boolean ib_indDummy;

	/** Propiedad ib ind segregacion. */
	private boolean ib_indSegregacion;

	/** Propiedad ib ind vinculado. */
	private boolean ib_indVinculado;

	/** Propiedad ib inscripcion adicional. */
	private boolean ib_inscripcionAdicional;

	/** Propiedad ib job 190. */
	private boolean ib_job190;

	/** Propiedad ib lindero cargado. */
	private boolean ib_linderoCargado;

	/** Propiedad ib loteo. */
	private boolean ib_loteo;

	/** Propiedad ib matricula matriz. */
	private boolean ib_matriculaMatriz;

	/** Propiedad ib matricula seleccionada. */
	private boolean ib_matriculaSeleccionada;

	/** Propiedad ib matriculas add. */
	private boolean ib_matriculasAdd;

	/** Propiedad ib medida cautelar. */
	private boolean ib_medidaCautelar;

	/** Propiedad ib modificar anotacion documento. */
	private boolean ib_modificarAnotacionDocumento;

	/** Propiedad ib nota devolutiva. */
	private boolean ib_notaDevolutiva;

	/** Propiedad ib nota devolutiva medida cautelar. */
	private boolean ib_notaDevolutivaMedidaCautelar;

	/** Propiedad ib primer englobe. */
	private boolean ib_primerEnglobe;

	/** Propiedad ib reloteo. */
	private boolean ib_reloteo;

	/** Propiedad ib remanente. */
	private boolean ib_remanente;

	/** Propiedad ib reproduccion constancia. */
	private boolean ib_reproduccionConstancia;

	/** Propiedad ib revision. */
	private boolean ib_revision;

	/** Propiedad ib salvar. */
	private boolean ib_salvar;

	/** Propiedad ib salvar cierre folio. */
	private boolean ib_salvarCierreFolio;

	/** Propiedad ib salvar definitivo. */
	private boolean ib_salvarDefinitivo;

	/** Propiedad ib salvar venta parcial. */
	private boolean ib_salvarVentaParcial;

	/** Propiedad ib seleccionar. */
	private boolean ib_seleccionar;

	/** Propiedad ib selected. */
	private boolean ib_selected;

	/** Propiedad ib validar area. */
	private boolean ib_validarArea;

	/** Propiedad ib venta parcial. */
	private boolean ib_ventaParcial;

	/** Propiedad ii tab index. */
	private int ii_tabIndex;

	/** Propiedad ii total anotaciones. */
	private int ii_totalAnotaciones;

	/** Propiedad ii total matriculas revision. */
	private int ii_totalMatriculasRevision;

	/** Propiedad ii total revision. */
	private int ii_totalRevision;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Instancia un nuevo objeto registro calificacion.
	 */
	public RegistroCalificacion()
	{
	}

	/**
	 * Instancia un nuevo objeto registro calificacion.
	 *
	 * @param asi_si de asi si
	 */
	public RegistroCalificacion(SolicitudInterviniente asi_si)
	{
		if(asi_si != null)
		{
			Persona lp_persona;

			is_porcentajeStr         = asi_si.getPorcentaje();
			is_idAnotacionPredio     = asi_si.getIdAnotacionPredio();
			is_rolPersona            = asi_si.getRolPersona();
			is_valuePropietario      = asi_si.getValorPropietario();
			is_interesadaRrr         = asi_si.getInteresadaRrr();
			is_idAnotacionPredio     = asi_si.getIdAnotacionPredio();
			lp_persona               = asi_si.getPersona();

			if(lp_persona != null)
			{
				is_idPersona           = lp_persona.getIdPersona();
				is_primerNombre        = lp_persona.getPrimerNombre();
				is_segundoNombre       = lp_persona.getSegundoNombre();
				is_primerApellido      = lp_persona.getPrimerApellido();
				is_segundoApellido     = lp_persona.getSegundoApellido();
				is_codDocumento        = lp_persona.getIdDocumentoTipo();
				is_documento           = lp_persona.getNumeroDocumento();
			}
		}
	}

	/**
	 * Modifica el valor de acto segregacion.
	 *
	 * @param ab_b asigna el valor a la propiedad acto segregacion
	 */
	public void setActoSegregacion(boolean ab_b)
	{
		ib_actoSegregacion = ab_b;
	}

	/**
	 * Valida la propiedad acto segregacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en acto segregacion
	 */
	public boolean isActoSegregacion()
	{
		return ib_actoSegregacion;
	}

	/**
	 * Modifica el valor de all matriculas.
	 *
	 * @param acrc_rc asigna el valor a la propiedad all matriculas
	 */
	public void setAllMatriculas(Collection<RegistroCalificacion> acrc_rc)
	{
		icrc_allMatriculas = acrc_rc;
	}

	/**
	 * Retorna el valor de all matriculas.
	 *
	 * @return el valor de all matriculas
	 */
	public Collection<RegistroCalificacion> getAllMatriculas()
	{
		return icrc_allMatriculas;
	}

	/**
	 * Modifica el valor de anotacion antiguo sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad anotacion antiguo sistema
	 */
	public void setAnotacionAntiguoSistema(boolean ab_b)
	{
		ib_anotacionAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad anotacion antiguo sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en anotacion antiguo sistema
	 */
	public boolean isAnotacionAntiguoSistema()
	{
		return ib_anotacionAntiguoSistema;
	}

	/**
	 * Modifica el valor de anotacion cancelacion.
	 *
	 * @param as_s asigna el valor a la propiedad anotacion cancelacion
	 */
	public void setAnotacionCancelacion(String as_s)
	{
		is_anotacionCancelacion = as_s;
	}

	/**
	 * Retorna el valor de anotacion cancelacion.
	 *
	 * @return el valor de anotacion cancelacion
	 */
	public String getAnotacionCancelacion()
	{
		return is_anotacionCancelacion;
	}

	/**
	 * Modifica el valor de anotacion invalida.
	 *
	 * @param ab_b asigna el valor a la propiedad anotacion invalida
	 */
	public void setAnotacionInvalida(boolean ab_b)
	{
		ib_anotacionInvalida = ab_b;
	}

	/**
	 * Valida la propiedad anotacion invalida.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en anotacion invalida
	 */
	public boolean isAnotacionInvalida()
	{
		return ib_anotacionInvalida;
	}

	/**
	 * Modifica el valor de anotaciones cancelacion.
	 *
	 * @param acrc_acrc asigna el valor a la propiedad anotaciones cancelacion
	 */
	public void setAnotacionesCancelacion(Collection<RegistroCalificacion> acrc_acrc)
	{
		icrc_anotacionesCancelacion = acrc_acrc;
	}

	/**
	 * Retorna el valor de anotaciones cancelacion.
	 *
	 * @return el valor de anotaciones cancelacion
	 */
	public Collection<RegistroCalificacion> getAnotacionesCancelacion()
	{
		return icrc_anotacionesCancelacion;
	}

	/**
	 * Modifica el valor de anotaciones heredar.
	 *
	 * @param acrc_crc asigna el valor a la propiedad anotaciones heredar
	 */
	public void setAnotacionesHeredar(Collection<RegistroCalificacion> acrc_crc)
	{
		icrc_anotacionesHeredar = acrc_crc;
	}

	/**
	 * Retorna el valor de anotaciones heredar.
	 *
	 * @return el valor de anotaciones heredar
	 */
	public Collection<RegistroCalificacion> getAnotacionesHeredar()
	{
		return icrc_anotacionesHeredar;
	}

	/**
	 * Modifica el valor de anotaciones parciales.
	 *
	 * @param acl_cl asigna el valor a la propiedad anotaciones parciales
	 */
	public void setAnotacionesParciales(Collection<Long> acl_cl)
	{
		icl_anotacionesParciales = acl_cl;
	}

	/**
	 * Retorna el valor de anotaciones parciales.
	 *
	 * @return el valor de anotaciones parciales
	 */
	public Collection<Long> getAnotacionesParciales()
	{
		return icl_anotacionesParciales;
	}

	/**
	 * Modifica el valor de AnotacionesPredioEliminadas.
	 *
	 * @param ac_c de ac c
	 */
	public void setAnotacionesPredioEliminadas(Collection<AnotacionPredio> ac_c)
	{
		icap_anotacionesPredioEliminadas = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones predio eliminadas.
	 *
	 * @return Retorna el valor de la propiedad anotacionesPredioEliminadas
	 */
	public Collection<AnotacionPredio> getAnotacionesPredioEliminadas()
	{
		return icap_anotacionesPredioEliminadas;
	}

	/**
	 * Modifica el valor de antiguo sistema data.
	 *
	 * @param aoda_das asigna el valor a la propiedad antiguo sistema data
	 */
	public void setAntiguoSistemaData(DatosAntSistema aoda_das)
	{
		iodas_antiguoSistemaData = aoda_das;
	}

	/**
	 * Retorna el valor de antiguo sistema data.
	 *
	 * @return el valor de antiguo sistema data
	 */
	public DatosAntSistema getAntiguoSistemaData()
	{
		return iodas_antiguoSistemaData;
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

	/**
	 * Modifica el valor de apertura matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad apertura matricula
	 */
	public void setAperturaMatricula(boolean ab_b)
	{
		ib_aperturaMatricula = ab_b;
	}

	/**
	 * Valida la propiedad apertura matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en apertura matricula
	 */
	public boolean isAperturaMatricula()
	{
		return ib_aperturaMatricula;
	}

	/**
	 * Modifica el valor de area revision.
	 *
	 * @param ab_b asigna el valor a la propiedad area revision
	 */
	public void setAreaRevision(boolean ab_b)
	{
		ib_areaRevision = ab_b;
	}

	/**
	 * Valida la propiedad area revision.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en area revision
	 */
	public boolean isAreaRevision()
	{
		return ib_areaRevision;
	}

	/**
	 * Modifica el valor de area validada.
	 *
	 * @param ab_b asigna el valor a la propiedad area validada
	 */
	public void setAreaValidada(boolean ab_b)
	{
		ib_areaValidada = ab_b;
	}

	/**
	 * Valida la propiedad area validada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en area validada
	 */
	public boolean isAreaValidada()
	{
		return ib_areaValidada;
	}

	/**
	 * Modifica el valor de baldios.
	 *
	 * @param ab_b asigna el valor a la propiedad baldios
	 */
	public void setBaldios(boolean ab_b)
	{
		ib_baldios = ab_b;
	}

	/**
	 * Valida la propiedad baldios.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en baldios
	 */
	public boolean isBaldios()
	{
		return ib_baldios;
	}

	/**
	 * Modifica el valor de cambio estado predio.
	 *
	 * @param acep_cep asigna el valor a la propiedad cambio estado predio
	 */
	public void setCambioEstadoPredio(CambioEstadoPredio acep_cep)
	{
		icep_cambioEstadoPredio = acep_cep;
	}

	/**
	 * Retorna el valor de cambio estado predio.
	 *
	 * @return el valor de cambio estado predio
	 */
	public CambioEstadoPredio getCambioEstadoPredio()
	{
		if(icep_cambioEstadoPredio == null)
			icep_cambioEstadoPredio = new CambioEstadoPredio();

		return icep_cambioEstadoPredio;
	}

	/**
	 * Modifica el valor de cancelacion.
	 *
	 * @param ab_b asigna el valor a la propiedad cancelacion
	 */
	public void setCancelacion(boolean ab_b)
	{
		ib_cancelacion = ab_b;
	}

	/**
	 * Valida la propiedad cancelacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cancelacion
	 */
	public boolean isCancelacion()
	{
		return ib_cancelacion;
	}

	/**
	 * Modifica el valor de cementerio.
	 *
	 * @param ab_b asigna el valor a la propiedad cementerio
	 */
	public void setCementerio(boolean ab_b)
	{
		ib_cementerio = ab_b;
	}

	/**
	 * Valida la propiedad cementerio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cementerio
	 */
	public boolean isCementerio()
	{
		return ib_cementerio;
	}

	/**
	 * Modifica el valor de cierre folio.
	 *
	 * @param ab_b asigna el valor a la propiedad cierre folio
	 */
	public void setCierreFolio(boolean ab_b)
	{
		ib_cierreFolio = ab_b;
	}

	/**
	 * Valida la propiedad cierre folio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cierre folio
	 */
	public boolean isCierreFolio()
	{
		return ib_cierreFolio;
	}

	/**
	 * Modifica el valor de cod acto.
	 *
	 * @param as_s asigna el valor a la propiedad cod acto
	 */
	public void setCodActo(String as_s)
	{
		is_codActo = as_s;
	}

	/**
	 * Retorna el valor de cod acto.
	 *
	 * @return el valor de cod acto
	 */
	public String getCodActo()
	{
		return is_codActo;
	}

	/**
	 * Modifica el valor de cod documento.
	 *
	 * @param as_s asigna el valor a la propiedad cod documento
	 */
	public void setCodDocumento(String as_s)
	{
		is_codDocumento = as_s;
	}

	/**
	 * Retorna el valor de cod documento.
	 *
	 * @return el valor de cod documento
	 */
	public String getCodDocumento()
	{
		return is_codDocumento;
	}

	/**
	 * Modifica el valor de cod estado anotacion.
	 *
	 * @param as_s asigna el valor a la propiedad cod estado anotacion
	 */
	public void setCodEstadoAnotacion(String as_s)
	{
		is_codEstadoAnotacion = as_s;
	}

	/**
	 * Retorna el valor de cod estado anotacion.
	 *
	 * @return el valor de cod estado anotacion
	 */
	public String getCodEstadoAnotacion()
	{
		return is_codEstadoAnotacion;
	}

	/**
	 * Modifica el valor de comentario.
	 *
	 * @param as_s asigna el valor a la propiedad comentario
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}

	/**
	 * Retorna el valor de comentario.
	 *
	 * @return el valor de comentario
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de complementacion calificacion.
	 *
	 * @param acc_cc asigna el valor a la propiedad complementacion calificacion
	 */
	public void setComplementacionCalificacion(ComplementacionCalificacion acc_cc)
	{
		icc_complementacionCalificacion = acc_cc;
	}

	/**
	 * Retorna el valor de complementacion calificacion.
	 *
	 * @return el valor de complementacion calificacion
	 */
	public ComplementacionCalificacion getComplementacionCalificacion()
	{
		if(icc_complementacionCalificacion == null)
			icc_complementacionCalificacion = new ComplementacionCalificacion();

		return icc_complementacionCalificacion;
	}

	/**
	 * Modifica el valor de copiar.
	 *
	 * @param ab_b asigna el valor a la propiedad copiar
	 */
	public void setCopiar(boolean ab_b)
	{
		ib_copiar = ab_b;
	}

	/**
	 * Valida la propiedad copiar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en copiar
	 */
	public boolean isCopiar()
	{
		return ib_copiar;
	}

	/**
	 * Modifica el valor de data anotacion cancelacion.
	 *
	 * @param aoac_ac asigna el valor a la propiedad data anotacion cancelacion
	 */
	public void setDataAnotacionCancelacion(AnotacionCancelacion aoac_ac)
	{
		iodc_dataAnotacionCancelacion = aoac_ac;
	}

	/**
	 * Retorna el valor de data anotacion cancelacion.
	 *
	 * @return el valor de data anotacion cancelacion
	 */
	public AnotacionCancelacion getDataAnotacionCancelacion()
	{
		return iodc_dataAnotacionCancelacion;
	}

	/**
	 * Modifica el valor de data anotacion predio.
	 *
	 * @param aoap_ap asigna el valor a la propiedad data anotacion predio
	 */
	public void setDataAnotacionPredio(AnotacionPredio aoap_ap)
	{
		ioda_dataAnotacionPredio = aoap_ap;
	}

	/**
	 * Retorna el valor de data anotacion predio.
	 *
	 * @return el valor de data anotacion predio
	 */
	public AnotacionPredio getDataAnotacionPredio()
	{
		return ioda_dataAnotacionPredio;
	}

	/**
	 * Modifica el valor de data anotacion predio ciudadano.
	 *
	 * @param aoap_ap asigna el valor a la propiedad data anotacion predio ciudadano
	 */
	public void setDataAnotacionPredioCiudadano(AnotacionPredioCiudadano aoap_ap)
	{
		ioap_dataAnotacionPredioCiudadano = aoap_ap;
	}

	/**
	 * Retorna el valor de data anotacion predio ciudadano.
	 *
	 * @return el valor de data anotacion predio ciudadano
	 */
	public AnotacionPredioCiudadano getDataAnotacionPredioCiudadano()
	{
		return ioap_dataAnotacionPredioCiudadano;
	}

	/**
	 * Modifica el valor de data confrontacion.
	 *
	 * @param ac_c asigna el valor a la propiedad data confrontacion
	 */
	public void setDataConfrontacion(Calificacion ac_c)
	{
		ic_dataConfrontacion = ac_c;
	}

	/**
	 * Retorna el valor de data confrontacion.
	 *
	 * @return el valor de data confrontacion
	 */
	public Calificacion getDataConfrontacion()
	{
		if(ic_dataConfrontacion == null)
			ic_dataConfrontacion = new Calificacion();

		return ic_dataConfrontacion;
	}

	/**
	 * Modifica el valor de data direccion predio.
	 *
	 * @param aodp_odp asigna el valor a la propiedad data direccion predio
	 */
	public void setDataDireccionPredio(DireccionDelPredio aodp_odp)
	{
		iodp_dataDireccionPredio = aodp_odp;
	}

	/**
	 * Retorna el valor de data direccion predio.
	 *
	 * @return el valor de data direccion predio
	 */
	public DireccionDelPredio getDataDireccionPredio()
	{
		if(iodp_dataDireccionPredio == null)
			iodp_dataDireccionPredio = new DireccionDelPredio();

		return iodp_dataDireccionPredio;
	}

	/**
	 * Modifica el valor de data documento.
	 *
	 * @param aod_od asigna el valor a la propiedad data documento
	 */
	public void setDataDocumento(Documento aod_od)
	{
		iod_dataDocumento = aod_od;
	}

	/**
	 * Retorna el valor de data documento.
	 *
	 * @return el valor de data documento
	 */
	public Documento getDataDocumento()
	{
		if(iod_dataDocumento == null)
			iod_dataDocumento = new Documento();

		return iod_dataDocumento;
	}

	/**
	 * Modifica el valor de data persona.
	 *
	 * @param aop_op asigna el valor a la propiedad data persona
	 */
	public void setDataPersona(Persona aop_op)
	{
		iop_dataPersona = aop_op;
	}

	/**
	 * Retorna el valor de data persona.
	 *
	 * @return el valor de data persona
	 */
	public Persona getDataPersona()
	{
		return iop_dataPersona;
	}

	/**
	 * Modifica el valor de datos area.
	 *
	 * @param aaaui_aaui asigna el valor a la propiedad datos area
	 */
	public void setDatosArea(AccAreaUI aaaui_aaui)
	{
		iaaui_datosArea = aaaui_aaui;
	}

	/**
	 * Retorna el valor de datos area.
	 *
	 * @return el valor de datos area
	 */
	public AccAreaUI getDatosArea()
	{
		return iaaui_datosArea;
	}

	/**
	 * Modifica el valor de datos basicos.
	 *
	 * @param adb_db asigna el valor a la propiedad datos basicos
	 */
	public void setDatosBasicos(DatosBasicos adb_db)
	{
		idb_datosBasicos = adb_db;
	}

	/**
	 * Retorna el valor de datos basicos.
	 *
	 * @return el valor de datos basicos
	 */
	public DatosBasicos getDatosBasicos()
	{
		return idb_datosBasicos;
	}

	/**
	 * Modifica el valor de datos documento.
	 *
	 * @param aorc_rc asigna el valor a la propiedad datos documento
	 */
	public void setDatosDocumento(RegistroCalificacion aorc_rc)
	{
		iorc_datosDocumento = aorc_rc;
	}

	/**
	 * Retorna el valor de datos documento.
	 *
	 * @return el valor de datos documento
	 */
	public RegistroCalificacion getDatosDocumento()
	{
		if(iorc_datosDocumento == null)
			iorc_datosDocumento = new RegistroCalificacion();

		return iorc_datosDocumento;
	}

	/**
	 * Sets the datos parcial.
	 *
	 * @param alhm_lhm de alhm lhm
	 */
	public void setDatosParcial(Map<String, Boolean> alhm_lhm)
	{
		ilhm_datosParcial = alhm_lhm;
	}

	/**
	 * Retorna el valor de datos parcial.
	 *
	 * @return el valor de datos parcial
	 */
	public Map<String, Boolean> getDatosParcial()
	{
		return ilhm_datosParcial;
	}

	/**
	 * Modifica el valor de datos validos ant sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad datos validos ant sistema
	 */
	public void setDatosValidosAntSistema(boolean ab_b)
	{
		ib_datosValidosAntSistema = ab_b;
	}

	/**
	 * Valida la propiedad datos validos ant sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos validos ant sistema
	 */
	public boolean isDatosValidosAntSistema()
	{
		return ib_datosValidosAntSistema;
	}

	/**
	 * Modifica el valor de desenglobe.
	 *
	 * @param ab_b asigna el valor a la propiedad desenglobe
	 */
	public void setDesenglobe(boolean ab_b)
	{
		ib_desenglobe = ab_b;
	}

	/**
	 * Valida la propiedad desenglobe.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en desenglobe
	 */
	public boolean isDesenglobe()
	{
		return ib_desenglobe;
	}

	/**
	 * Modifica el valor de detalle matricula.
	 *
	 * @param aorc_rc asigna el valor a la propiedad detalle matricula
	 */
	public void setDetalleMatricula(RegistroCalificacion aorc_rc)
	{
		iorc_detalleMatricula = aorc_rc;
	}

	/**
	 * Retorna el valor de detalle matricula.
	 *
	 * @return el valor de detalle matricula
	 */
	public RegistroCalificacion getDetalleMatricula()
	{
		if(iorc_detalleMatricula == null)
			iorc_detalleMatricula = new RegistroCalificacion();

		return iorc_detalleMatricula;
	}

	/**
	 * Modifica el valor de devolucion.
	 *
	 * @param ab_b asigna el valor a la propiedad devolucion
	 */
	public void setDevolucion(boolean ab_b)
	{
		ib_devolucion = ab_b;
	}

	/**
	 * Valida la propiedad devolucion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en devolucion
	 */
	public boolean isDevolucion()
	{
		return ib_devolucion;
	}

	/**
	 * Modifica el valor de digitador masivo.
	 *
	 * @param ab_b asigna el valor a la propiedad digitador masivo
	 */
	public void setDigitadorMasivo(boolean ab_b)
	{
		ib_digitadorMasivo = ab_b;
	}

	/**
	 * Valida la propiedad digitador masivo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en digitador masivo
	 */
	public boolean isDigitadorMasivo()
	{
		return ib_digitadorMasivo;
	}

	/**
	 * Modifica el valor de direccion guardar.
	 *
	 * @param adp_dp asigna el valor a la propiedad direccion guardar
	 */
	public void setDireccionGuardar(DireccionPredio adp_dp)
	{
		idp_direccionGuardar = adp_dp;
	}

	/**
	 * Retorna el valor de direccion guardar.
	 *
	 * @return el valor de direccion guardar
	 */
	public DireccionPredio getDireccionGuardar()
	{
		return idp_direccionGuardar;
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
	 * Modifica el valor de direcciones acc.
	 *
	 * @param acdpa_cdpa asigna el valor a la propiedad direcciones acc
	 */
	public void setDireccionesAcc(Collection<DireccionPredioAcc> acdpa_cdpa)
	{
		icdpa_direccionesAcc = acdpa_cdpa;
	}

	/**
	 * Retorna el valor de direcciones acc.
	 *
	 * @return el valor de direcciones acc
	 */
	public Collection<DireccionPredioAcc> getDireccionesAcc()
	{
		return icdpa_direccionesAcc;
	}

	/**
	 * Modifica el valor de DireccionesDelPredio.
	 *
	 * @param acddp_cddp Modifica el valor de la propiedad direccionesDelPredio
	 */
	public void setDireccionesDelPredio(Collection<DireccionDelPredio> acddp_cddp)
	{
		icddp_direccionesDelPredio = acddp_cddp;
	}

	/**
	 * Retorna Objeto o variable de valor direcciones del predio.
	 *
	 * @return Retorna el valor de la propiedad direccionesDelPredio
	 */
	public Collection<DireccionDelPredio> getDireccionesDelPredio()
	{
		return icddp_direccionesDelPredio;
	}

	/**
	 * Sets the direcciones seleccionadas.
	 *
	 * @param ahmsdp_hmsdp de ahmsdp hmsdp
	 */
	public void setDireccionesSeleccionadas(HashMap<String, DireccionPredio> ahmsdp_hmsdp)
	{
		ihmsdp_direccionesSeleccionadas = ahmsdp_hmsdp;
	}

	/**
	 * Retorna el valor de direcciones seleccionadas.
	 *
	 * @return el valor de direcciones seleccionadas
	 */
	public HashMap<String, DireccionPredio> getDireccionesSeleccionadas()
	{
		return ihmsdp_direccionesSeleccionadas;
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
	 * Modifica el valor de documento.
	 *
	 * @param as_s asigna el valor a la propiedad documento
	 */
	public void setDocumento(String as_s)
	{
		is_documento = as_s;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public String getDocumento()
	{
		return is_documento;
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
	 * Modifica el valor de EnglobeAnotacion.
	 *
	 * @param ab_b Modifica el valor de la propiedad englobeAnotacion
	 */
	public void setEnglobeAnotacion(boolean ab_b)
	{
		ib_englobeAnotacion = ab_b;
	}

	/**
	 * Valida la propiedad englobe anotacion.
	 *
	 * @return Retorna el valor de la propiedad englobeAnotacion
	 */
	public boolean isEnglobeAnotacion()
	{
		return ib_englobeAnotacion;
	}

	/**
	 * Modifica el valor de envio calificador.
	 *
	 * @param ab_b asigna el valor a la propiedad envio calificador
	 */
	public void setEnvioCalificador(boolean ab_b)
	{
		ib_envioCalificador = ab_b;
	}

	/**
	 * Valida la propiedad envio calificador.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en envio calificador
	 */
	public boolean isEnvioCalificador()
	{
		return ib_envioCalificador;
	}

	/**
	 * Modifica el valor de envio digitador.
	 *
	 * @param ab_b asigna el valor a la propiedad envio digitador
	 */
	public void setEnvioDigitador(boolean ab_b)
	{
		ib_envioDigitador = ab_b;
	}

	/**
	 * Valida la propiedad envio digitador.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en envio digitador
	 */
	public boolean isEnvioDigitador()
	{
		return ib_envioDigitador;
	}

	/**
	 * Modifica el valor de especificacion.
	 *
	 * @param as_s asigna el valor a la propiedad especificacion
	 */
	public void setEspecificacion(String as_s)
	{
		is_especificacion = as_s;
	}

	/**
	 * Retorna el valor de especificacion.
	 *
	 * @return el valor de especificacion
	 */
	public String getEspecificacion()
	{
		return is_especificacion;
	}

	/**
	 * Modifica el valor de estado matricula.
	 *
	 * @param as_s asigna el valor a la propiedad estado matricula
	 */
	public void setEstadoMatricula(String as_s)
	{
		is_estadoMatricula = as_s;
	}

	/**
	 * Retorna el valor de estado matricula.
	 *
	 * @return el valor de estado matricula
	 */
	public String getEstadoMatricula()
	{
		return is_estadoMatricula;
	}

	/**
	 * Modifica el valor de estado predio.
	 *
	 * @param as_s asigna el valor a la propiedad estado predio
	 */
	public void setEstadoPredio(String as_s)
	{
		is_estadoPredio = as_s;
	}

	/**
	 * Retorna el valor de estado predio.
	 *
	 * @return el valor de estado predio
	 */
	public String getEstadoPredio()
	{
		return is_estadoPredio;
	}

	/**
	 * Modifica el valor de fase turno.
	 *
	 * @param as_s asigna el valor a la propiedad fase turno
	 */
	public void setFaseTurno(String as_s)
	{
		is_faseTurno = as_s;
	}

	/**
	 * Retorna el valor de fase turno.
	 *
	 * @return el valor de fase turno
	 */
	public String getFaseTurno()
	{
		return is_faseTurno;
	}

	/**
	 * Modifica el valor de fecha apertura.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha apertura
	 */
	public void setFechaApertura(Date ad_d)
	{
		id_fechaApertura = ad_d;
	}

	/**
	 * Retorna el valor de fecha apertura.
	 *
	 * @return el valor de fecha apertura
	 */
	public Date getFechaApertura()
	{
		return id_fechaApertura;
	}

	/**
	 * Modifica el valor de fecha documento.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha documento
	 */
	public void setFechaDocumento(Date ad_d)
	{
		id_fechaDocumento = ad_d;
	}

	/**
	 * Retorna el valor de fecha documento.
	 *
	 * @return el valor de fecha documento
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Modifica el valor de fecha documento str.
	 *
	 * @param as_s asigna el valor a la propiedad fecha documento str
	 */
	public void setFechaDocumentoStr(String as_s)
	{
		is_fechaDocumentoStr = as_s;
	}

	/**
	 * Retorna el valor de fecha documento str.
	 *
	 * @return el valor de fecha documento str
	 */
	public String getFechaDocumentoStr()
	{
		return is_fechaDocumentoStr;
	}

	/**
	 * Modifica el valor de fecha radicacion.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha radicacion
	 */
	public void setFechaRadicacion(Date ad_d)
	{
		id_fechaRadicacion = ad_d;
	}

	/**
	 * Retorna el valor de fecha radicacion.
	 *
	 * @return el valor de fecha radicacion
	 */
	public Date getFechaRadicacion()
	{
		return id_fechaRadicacion;
	}

	/**
	 * Modifica el valor de fecha registro.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha registro
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna el valor de fecha registro.
	 *
	 * @return el valor de fecha registro
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de GenerarArchivoNotaExcesoPago.
	 *
	 * @param ab_b de ab b
	 */
	public void setGenerarArchivoNotaExcesoPago(boolean ab_b)
	{
		ib_generarArchivoNotaExcesoPago = ab_b;
	}

	/**
	 * Valida la propiedad generar archivo nota exceso pago.
	 *
	 * @return Retorna el valor de la propiedad generarArchivoNotaExcesoPago
	 */
	public boolean isGenerarArchivoNotaExcesoPago()
	{
		return ib_generarArchivoNotaExcesoPago;
	}

	/**
	 * Modifica el valor de generar archivo parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad generar archivo parcial
	 */
	public void setGenerarArchivoParcial(boolean ab_b)
	{
		ib_generarArchivoParcial = ab_b;
	}

	/**
	 * Valida la propiedad generar archivo parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en generar archivo parcial
	 */
	public boolean isGenerarArchivoParcial()
	{
		return ib_generarArchivoParcial;
	}

	/**
	 * Modifica el valor de GenerarPDFCorrespondenciaRegistro.
	 *
	 * @param ab_b de ab b
	 */
	public void setGenerarPDFCorrespondenciaRegistro(boolean ab_b)
	{
		ib_generarPDFCorrespondenciaRegistro = ab_b;
	}

	/**
	 * Valida la propiedad generar PDF correspondencia registro.
	 *
	 * @return Retorna el valor de la propiedad generarPDFCorrespondenciaRegistro
	 */
	public boolean isGenerarPDFCorrespondenciaRegistro()
	{
		return ib_generarPDFCorrespondenciaRegistro;
	}

	/**
	 * Modifica el valor de id anotacion.
	 *
	 * @param al_l asigna el valor a la propiedad id anotacion
	 */
	public void setIdAnotacion(Long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna el valor de id anotacion.
	 *
	 * @return el valor de id anotacion
	 */
	public Long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdAnotacionApertura.
	 *
	 * @param as_s Modifica el valor de la propiedad idAnotacionApertura
	 */
	public void setIdAnotacionApertura(String as_s)
	{
		is_idAnotacionApertura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion apertura.
	 *
	 * @return Retorna el valor de la propiedad idAnotacionApertura
	 */
	public String getIdAnotacionApertura()
	{
		return is_idAnotacionApertura;
	}

	/**
	 * Modifica el valor de id anotacion predio.
	 *
	 * @param as_s asigna el valor a la propiedad id anotacion predio
	 */
	public void setIdAnotacionPredio(String as_s)
	{
		is_idAnotacionPredio = as_s;
	}

	/**
	 * Retorna el valor de id anotacion predio.
	 *
	 * @return el valor de id anotacion predio
	 */
	public String getIdAnotacionPredio()
	{
		return is_idAnotacionPredio;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id circulo matriz.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo matriz
	 */
	public void setIdCirculoMatriz(String as_s)
	{
		is_idCirculoMatriz = as_s;
	}

	/**
	 * Retorna el valor de id circulo matriz.
	 *
	 * @return el valor de id circulo matriz
	 */
	public String getIdCirculoMatriz()
	{
		return is_idCirculoMatriz;
	}

	/**
	 * Modifica el valor de id ciudadano.
	 *
	 * @param as_s asigna el valor a la propiedad id ciudadano
	 */
	public void setIdCiudadano(String as_s)
	{
		is_idCiudadano = as_s;
	}

	/**
	 * Retorna el valor de id ciudadano.
	 *
	 * @return el valor de id ciudadano
	 */
	public String getIdCiudadano()
	{
		return is_idCiudadano;
	}

	/**
	 * Modifica el valor de id datos ant sistema.
	 *
	 * @param as_s asigna el valor a la propiedad id datos ant sistema
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna el valor de id datos ant sistema.
	 *
	 * @return el valor de id datos ant sistema
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de id departamento.
	 *
	 * @param as_s asigna el valor a la propiedad id departamento
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna el valor de id departamento.
	 *
	 * @return el valor de id departamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de id detalle ant sistema.
	 *
	 * @param as_s asigna el valor a la propiedad id detalle ant sistema
	 */
	public void setIdDetalleAntSistema(String as_s)
	{
		is_idDetalleAntSistema = as_s;
	}

	/**
	 * Retorna el valor de id detalle ant sistema.
	 *
	 * @return el valor de id detalle ant sistema
	 */
	public String getIdDetalleAntSistema()
	{
		return is_idDetalleAntSistema;
	}

	/**
	 * Modifica el valor de id documento.
	 *
	 * @param as_s asigna el valor a la propiedad id documento
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna el valor de id documento.
	 *
	 * @return el valor de id documento
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de id estado anotacion.
	 *
	 * @param as_s asigna el valor a la propiedad id estado anotacion
	 */
	public void setIdEstadoAnotacion(String as_s)
	{
		is_idEstadoAnotacion = as_s;
	}

	/**
	 * Retorna el valor de id estado anotacion.
	 *
	 * @return el valor de id estado anotacion
	 */
	public String getIdEstadoAnotacion()
	{
		return is_idEstadoAnotacion;
	}

	/**
	 * Modifica el valor de id estado registro.
	 *
	 * @param as_s asigna el valor a la propiedad id estado registro
	 */
	public void setIdEstadoRegistro(String as_s)
	{
		is_idEstadoRegistro = as_s;
	}

	/**
	 * Retorna el valor de id estado registro.
	 *
	 * @return el valor de id estado registro
	 */
	public String getIdEstadoRegistro()
	{
		return is_idEstadoRegistro;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id image.
	 *
	 * @param al_l asigna el valor a la propiedad id image
	 */
	public void setIdImage(Long al_l)
	{
		il_idImage = al_l;
	}

	/**
	 * Retorna el valor de id image.
	 *
	 * @return el valor de id image
	 */
	public Long getIdImage()
	{
		return il_idImage;
	}

	/**
	 * Modifica el valor de id intervinientes.
	 *
	 * @param as_s asigna el valor a la propiedad id intervinientes
	 */
	public void setIdIntervinientes(String as_s)
	{
		is_idIntervinientes = as_s;
	}

	/**
	 * Retorna el valor de id intervinientes.
	 *
	 * @return el valor de id intervinientes
	 */
	public String getIdIntervinientes()
	{
		return is_idIntervinientes;
	}

	/**
	 * Modifica el valor de id matricula.
	 *
	 * @param al_l asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna el valor de id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de id matricula matriz.
	 *
	 * @param al_l asigna el valor a la propiedad id matricula matriz
	 */
	public void setIdMatriculaMatriz(Long al_l)
	{
		il_idMatriculaMatriz = al_l;
	}

	/**
	 * Retorna el valor de id matricula matriz.
	 *
	 * @return el valor de id matricula matriz
	 */
	public Long getIdMatriculaMatriz()
	{
		return il_idMatriculaMatriz;
	}

	/**
	 * Modifica el valor de id motivo.
	 *
	 * @param as_s asigna el valor a la propiedad id motivo
	 */
	public void setIdMotivo(String as_s)
	{
		is_idMotivo = as_s;
	}

	/**
	 * Retorna el valor de id motivo.
	 *
	 * @return el valor de id motivo
	 */
	public String getIdMotivo()
	{
		return is_idMotivo;
	}

	/**
	 * Modifica el valor de id municipio.
	 *
	 * @param as_s asigna el valor a la propiedad id municipio
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna el valor de id municipio.
	 *
	 * @return el valor de id municipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de id naturalez juridica.
	 *
	 * @param as_s asigna el valor a la propiedad id naturalez juridica
	 */
	public void setIdNaturalezJuridica(String as_s)
	{
		is_idNaturalezJuridica = as_s;
	}

	/**
	 * Retorna el valor de id naturalez juridica.
	 *
	 * @return el valor de id naturalez juridica
	 */
	public String getIdNaturalezJuridica()
	{
		return is_idNaturalezJuridica;
	}

	/**
	 * Modifica el valor de id oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad id oficina origen
	 */
	public void setIdOficinaOrigen(String as_s)
	{
		is_idOficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de id oficina origen.
	 *
	 * @return el valor de id oficina origen
	 */
	public String getIdOficinaOrigen()
	{
		return is_idOficinaOrigen;
	}

	/**
	 * Modifica el valor de id pais.
	 *
	 * @param as_s asigna el valor a la propiedad id pais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna el valor de id pais.
	 *
	 * @return el valor de id pais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de id persona.
	 *
	 * @param as_s asigna el valor a la propiedad id persona
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna el valor de id persona.
	 *
	 * @return el valor de id persona
	 */
	public String getIdPersona()
	{
		return is_idPersona;
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
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id tipo anotacion.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo anotacion
	 */
	public void setIdTipoAnotacion(String as_s)
	{
		is_idTipoAnotacion = as_s;
	}

	/**
	 * Retorna el valor de id tipo anotacion.
	 *
	 * @return el valor de id tipo anotacion
	 */
	public String getIdTipoAnotacion()
	{
		return is_idTipoAnotacion;
	}

	/**
	 * Modifica el valor de id tipo documento.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo documento
	 */
	public void setIdTipoDocumento(String as_s)
	{
		is_idTipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de id tipo documento.
	 *
	 * @return el valor de id tipo documento
	 */
	public String getIdTipoDocumento()
	{
		return is_idTipoDocumento;
	}

	/**
	 * Modifica el valor de id tipo entidad.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo entidad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de id tipo entidad.
	 *
	 * @return el valor de id tipo entidad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de id tipo oficina.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo oficina
	 */
	public void setIdTipoOficina(String as_s)
	{
		is_idTipoOfina = as_s;
	}

	/**
	 * Retorna el valor de id tipo oficina.
	 *
	 * @return el valor de id tipo oficina
	 */
	public String getIdTipoOficina()
	{
		return is_idTipoOfina;
	}

	/**
	 * Modifica el valor de id tipo persona.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo persona
	 */
	public void setIdTipoPersona(String as_s)
	{
		is_idTipoPersona = as_s;
	}

	/**
	 * Retorna el valor de id tipo persona.
	 *
	 * @return el valor de id tipo persona
	 */
	public String getIdTipoPersona()
	{
		return is_idTipoPersona;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s Modifica el valor de la propiedad idTurno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
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
	 * Modifica el valor de id usuario.
	 *
	 * @param as_s asigna el valor a la propiedad id usuario
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna el valor de id usuario.
	 *
	 * @return el valor de id usuario
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de image valid.
	 *
	 * @param ab_b asigna el valor a la propiedad image valid
	 */
	public void setImageValid(boolean ab_b)
	{
		ib_imageValid = ab_b;
	}

	/**
	 * Valida la propiedad image valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en image valid
	 */
	public boolean isImageValid()
	{
		return ib_imageValid;
	}

	/**
	 * Modifica el valor de ind dummy.
	 *
	 * @param ab_b asigna el valor a la propiedad ind dummy
	 */
	public void setIndDummy(boolean ab_b)
	{
		ib_indDummy = ab_b;
	}

	/**
	 * Valida la propiedad ind dummy.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ind dummy
	 */
	public boolean isIndDummy()
	{
		return ib_indDummy;
	}

	/**
	 * Modifica el valor de ind segregacion.
	 *
	 * @param ab_b asigna el valor a la propiedad ind segregacion
	 */
	public void setIndSegregacion(boolean ab_b)
	{
		ib_indSegregacion = ab_b;
	}

	/**
	 * Valida la propiedad ind segregacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ind segregacion
	 */
	public boolean isIndSegregacion()
	{
		return ib_indSegregacion;
	}

	/**
	 * Modifica el valor de ind vinculado.
	 *
	 * @param ab_b asigna el valor a la propiedad ind vinculado
	 */
	public void setIndVinculado(boolean ab_b)
	{
		ib_indVinculado = ab_b;
	}

	/**
	 * Valida la propiedad ind vinculado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ind vinculado
	 */
	public boolean isIndVinculado()
	{
		return ib_indVinculado;
	}

	/**
	 * Modifica el valor de info file.
	 *
	 * @param acrc_rc asigna el valor a la propiedad info file
	 */
	public void setInfoFile(Collection<RegistroCalificacion> acrc_rc)
	{
		icrc_infoFile = acrc_rc;
	}

	/**
	 * Retorna el valor de info file.
	 *
	 * @return el valor de info file
	 */
	public Collection<RegistroCalificacion> getInfoFile()
	{
		return icrc_infoFile;
	}

	/**
	 * Modifica el valor de info intervinientes.
	 *
	 * @param aca_ca asigna el valor a la propiedad info intervinientes
	 */
	public void setInfoIntervinientes(Collection<Anotacion> aca_ca)
	{
		ica_infoIntervinientes = aca_ca;
	}

	/**
	 * Retorna el valor de info intervinientes.
	 *
	 * @return el valor de info intervinientes
	 */
	public Collection<Anotacion> getInfoIntervinientes()
	{
		return ica_infoIntervinientes;
	}

	/**
	 * Modifica el valor de info matriculas segregacion.
	 *
	 * @param acms_cms asigna el valor a la propiedad info matriculas segregacion
	 */
	public void setInfoMatriculasSegregacion(Collection<MatriculaSegregacion> acms_cms)
	{
		icms_infoMatriculasSegregacion = acms_cms;
	}

	/**
	 * Retorna el valor de info matriculas segregacion.
	 *
	 * @return el valor de info matriculas segregacion
	 */
	public Collection<MatriculaSegregacion> getInfoMatriculasSegregacion()
	{
		return icms_infoMatriculasSegregacion;
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

	/**
	 * Modifica el valor de interesada rrr.
	 *
	 * @param as_s asigna el valor a la propiedad interesada rrr
	 */
	public void setInteresadaRrr(String as_s)
	{
		is_interesadaRrr = as_s;
	}

	/**
	 * Retorna el valor de interesada rrr.
	 *
	 * @return el valor de interesada rrr
	 */
	public String getInteresadaRrr()
	{
		return is_interesadaRrr;
	}

	/**
	 * Modifica el valor de interesada rrr nombre.
	 *
	 * @param is_interesadaRrrNombre asigna el valor a la propiedad interesada rrr nombre
	 */
	public void setInteresadaRrrNombre(String is_interesadaRrrNombre)
	{
		this.is_interesadaRrrNombre = is_interesadaRrrNombre;
	}

	/**
	 * Retorna el valor de interesada rrr nombre.
	 *
	 * @return el valor de interesada rrr nombre
	 */
	public String getInteresadaRrrNombre()
	{
		return is_interesadaRrrNombre;
	}

	/**
	 * Modifica el valor de intervinientes.
	 *
	 * @param aca_ca asigna el valor a la propiedad intervinientes
	 */
	public void setIntervinientes(Collection<Anotacion> aca_ca)
	{
		ica_intervinientes = aca_ca;
	}

	/**
	 * Retorna el valor de intervinientes.
	 *
	 * @return el valor de intervinientes
	 */
	public Collection<Anotacion> getIntervinientes()
	{
		return ica_intervinientes;
	}

	/**
	 * Modifica el valor de intervinientesIngresados.
	 *
	 * @param acsi_si asigna el valor a la propiedad intervinientesIngresados
	 */
	public void setIntervinientesIngresados(Collection<SolicitudInterviniente> acsi_si)
	{
		icsi_intervinientesIngresados = acsi_si;
	}

	/**
	 * Retorna el valor de intervinientesIngresados.
	 *
	 * @return el valor de intervinientesIngresados
	 */
	public Collection<SolicitudInterviniente> getIntervinientesIngresados()
	{
		return icsi_intervinientesIngresados;
	}

	/**
	 * Modifica el valor de ip address.
	 *
	 * @param as_s asigna el valor a la propiedad ip address
	 */
	public void setIpAddress(String as_s)
	{
		is_ipAddress = as_s;
	}

	/**
	 * Retorna el valor de ip address.
	 *
	 * @return el valor de ip address
	 */
	public String getIpAddress()
	{
		return is_ipAddress;
	}

	/**
	 * Modifica el valor de job 190.
	 *
	 * @param ab_b asigna el valor a la propiedad job 190
	 */
	public void setJob190(boolean ab_b)
	{
		ib_job190 = ab_b;
	}

	/**
	 * Valida la propiedad job 190.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en job 190
	 */
	public boolean isJob190()
	{
		return ib_job190;
	}

	/**
	 * Modifica el valor de lindero cargado.
	 *
	 * @param ab_b asigna el valor a la propiedad lindero cargado
	 */
	public void setLinderoCargado(boolean ab_b)
	{
		ib_linderoCargado = ab_b;
	}

	/**
	 * Valida la propiedad lindero cargado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en lindero cargado
	 */
	public boolean isLinderoCargado()
	{
		return ib_linderoCargado;
	}

	/**
	 * Modifica el valor de lindero registro calificacion.
	 *
	 * @param linderoRegistroCalificacion asigna el valor a la propiedad lindero registro calificacion
	 */
	public void setLinderoRegistroCalificacion(LinderoRegistroCalificacion linderoRegistroCalificacion)
	{
		ilrc_linderoRegistroCalificacion = linderoRegistroCalificacion;
	}

	/**
	 * Retorna el valor de lindero registro calificacion.
	 *
	 * @return el valor de lindero registro calificacion
	 */
	public LinderoRegistroCalificacion getLinderoRegistroCalificacion()
	{
		return ilrc_linderoRegistroCalificacion;
	}

	/**
	 * Modifica el valor de loteo.
	 *
	 * @param ab_b asigna el valor a la propiedad loteo
	 */
	public void setLoteo(boolean ab_b)
	{
		ib_loteo = ab_b;
	}

	/**
	 * Valida la propiedad loteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en loteo
	 */
	public boolean isLoteo()
	{
		return ib_loteo;
	}

	/**
	 * Modifica el valor de matricula matriz.
	 *
	 * @param ab_b asigna el valor a la propiedad matricula matriz
	 */
	public void setMatriculaMatriz(boolean ab_b)
	{
		ib_matriculaMatriz = ab_b;
	}

	/**
	 * Valida la propiedad matricula matriz.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matricula matriz
	 */
	public boolean isMatriculaMatriz()
	{
		return ib_matriculaMatriz;
	}

	/**
	 * Modifica el valor de matricula seleccionada.
	 *
	 * @param ab_b asigna el valor a la propiedad matricula seleccionada
	 */
	public void setMatriculaSeleccionada(boolean ab_b)
	{
		ib_matriculaSeleccionada = ab_b;
	}

	/**
	 * Valida la propiedad matricula seleccionada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matricula seleccionada
	 */
	public boolean isMatriculaSeleccionada()
	{
		return ib_matriculaSeleccionada;
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
		return irc_matriculasAHeredar;
	}

	/**
	 * Modifica el valor de matriculas add.
	 *
	 * @param ab_b asigna el valor a la propiedad matriculas add
	 */
	public void setMatriculasAdd(boolean ab_b)
	{
		ib_matriculasAdd = ab_b;
	}

	/**
	 * Valida la propiedad matriculas add.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matriculas add
	 */
	public boolean isMatriculasAdd()
	{
		return ib_matriculasAdd;
	}

	/**
	 * Modifica el valor de matriculas aperturar.
	 *
	 * @param al_l asigna el valor a la propiedad matriculas aperturar
	 */
	public void setMatriculasAperturar(Long al_l)
	{
		il_matriculasAperturar = al_l;
	}

	/**
	 * Retorna el valor de matriculas aperturar.
	 *
	 * @return el valor de matriculas aperturar
	 */
	public Long getMatriculasAperturar()
	{
		return il_matriculasAperturar;
	}

	/**
	 * Modifica el valor de matriculas copiar.
	 *
	 * @param acsm_csm asigna el valor a la propiedad matriculas copiar
	 */
	public void setMatriculasCopiar(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculasCopiar = acsm_csm;
	}

	/**
	 * Retorna el valor de matriculas copiar.
	 *
	 * @return el valor de matriculas copiar
	 */
	public Collection<SolicitudMatricula> getMatriculasCopiar()
	{
		return icsm_matriculasCopiar;
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
	 * Modifica el valor de matriculas segregadas.
	 *
	 * @param acap_cap asigna el valor a la propiedad matriculas segregadas
	 */
	public void setMatriculasSegregadas(Collection<AreaPredio> acap_cap)
	{
		icap_matriculasSegregadas = acap_cap;
	}

	/**
	 * Retorna el valor de matriculas segregadas.
	 *
	 * @return el valor de matriculas segregadas
	 */
	public Collection<AreaPredio> getMatriculasSegregadas()
	{
		return icap_matriculasSegregadas;
	}

	/**
	 * Modifica el valor de medida cautelar.
	 *
	 * @param ab_b asigna el valor a la propiedad medida cautelar
	 */
	public void setMedidaCautelar(boolean ab_b)
	{
		ib_medidaCautelar = ab_b;
	}

	/**
	 * Valida la propiedad medida cautelar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en medida cautelar
	 */
	public boolean isMedidaCautelar()
	{
		return ib_medidaCautelar;
	}

	/**
	 * Modifica el valor de mensaje.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje
	 */
	public void setMensaje(String as_s)
	{
		is_mensaje = as_s;
	}

	/**
	 * Retorna el valor de mensaje.
	 *
	 * @return el valor de mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}

	/**
	 * Modifica el valor de modificar anotacion documento.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar anotacion documento
	 */
	public void setModificarAnotacionDocumento(boolean ab_b)
	{
		ib_modificarAnotacionDocumento = ab_b;
	}

	/**
	 * Valida la propiedad modificar anotacion documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar anotacion documento
	 */
	public boolean isModificarAnotacionDocumento()
	{
		return ib_modificarAnotacionDocumento;
	}

	/**
	 * Modifica el valor de municipio documento.
	 *
	 * @param as_s asigna el valor a la propiedad municipio documento
	 */
	public void setMunicipioDocumento(String as_s)
	{
		is_municipioDocumento = as_s;
	}

	/**
	 * Retorna el valor de municipio documento.
	 *
	 * @return el valor de municipio documento
	 */
	public String getMunicipioDocumento()
	{
		return is_municipioDocumento;
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
	 * Modifica el valor de nombre acto.
	 *
	 * @param as_s asigna el valor a la propiedad nombre acto
	 */
	public void setNombreActo(String as_s)
	{
		is_nombreActo = as_s;
	}

	/**
	 * Retorna el valor de nombre acto.
	 *
	 * @return el valor de nombre acto
	 */
	public String getNombreActo()
	{
		return is_nombreActo;
	}

	/**
	 * Modifica el valor de nombre circulo.
	 *
	 * @param as_s asigna el valor a la propiedad nombre circulo
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna el valor de nombre circulo.
	 *
	 * @return el valor de nombre circulo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de nombre departamento.
	 *
	 * @param as_s asigna el valor a la propiedad nombre departamento
	 */
	public void setNombreDepartamento(String as_s)
	{
		is_nombreDepartamento = as_s;
	}

	/**
	 * Retorna el valor de nombre departamento.
	 *
	 * @return el valor de nombre departamento
	 */
	public String getNombreDepartamento()
	{
		return is_nombreDepartamento;
	}

	/**
	 * Modifica el valor de nombre doc.
	 *
	 * @param as_s asigna el valor a la propiedad nombre doc
	 */
	public void setNombreDoc(String as_s)
	{
		is_nombreDoc = as_s;
	}

	/**
	 * Retorna el valor de nombre doc.
	 *
	 * @return el valor de nombre doc
	 */
	public String getNombreDoc()
	{
		return is_nombreDoc;
	}

	/**
	 * Modifica el valor de nombre grupo acto.
	 *
	 * @param as_s asigna el valor a la propiedad nombre grupo acto
	 */
	public void setNombreGrupoActo(String as_s)
	{
		is_nombreGrupoActo = as_s;
	}

	/**
	 * Retorna el valor de nombre grupo acto.
	 *
	 * @return el valor de nombre grupo acto
	 */
	public String getNombreGrupoActo()
	{
		return is_nombreGrupoActo;
	}

	/**
	 * Modifica el valor de nombre municipio.
	 *
	 * @param as_s asigna el valor a la propiedad nombre municipio
	 */
	public void setNombreMunicipio(String as_s)
	{
		is_nombreMunicipio = as_s;
	}

	/**
	 * Retorna el valor de nombre municipio.
	 *
	 * @return el valor de nombre municipio
	 */
	public String getNombreMunicipio()
	{
		return is_nombreMunicipio;
	}

	/**
	 * Modifica el valor de nombre oficina.
	 *
	 * @param as_s asigna el valor a la propiedad nombre oficina
	 */
	public void setNombreOficina(String as_s)
	{
		is_nombreOficina = as_s;
	}

	/**
	 * Retorna el valor de nombre oficina.
	 *
	 * @return el valor de nombre oficina
	 */
	public String getNombreOficina()
	{
		return is_nombreOficina;
	}

	/**
	 * Modifica el valor de nombre oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad nombre oficina origen
	 */
	public void setNombreOficinaOrigen(String as_s)
	{
		is_nombreOficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de nombre oficina origen.
	 *
	 * @return el valor de nombre oficina origen
	 */
	public String getNombreOficinaOrigen()
	{
		return is_nombreOficinaOrigen;
	}

	/**
	 * Modifica el valor de nombre pais.
	 *
	 * @param as_s asigna el valor a la propiedad nombre pais
	 */
	public void setNombrePais(String as_s)
	{
		is_nombrePais = as_s;
	}

	/**
	 * Retorna el valor de nombre pais.
	 *
	 * @return el valor de nombre pais
	 */
	public String getNombrePais()
	{
		return is_nombrePais;
	}

	/**
	 * Modifica el valor de nombre persona.
	 *
	 * @param as_s asigna el valor a la propiedad nombre persona
	 */
	public void setNombrePersona(String as_s)
	{
		is_nombrePersona = as_s;
	}

	/**
	 * Retorna el valor de nombre persona.
	 *
	 * @return el valor de nombre persona
	 */
	public String getNombrePersona()
	{
		return is_nombrePersona;
	}

	/**
	 * Modifica el valor de nombre tipo doc.
	 *
	 * @param as_s asigna el valor a la propiedad nombre tipo doc
	 */
	public void setNombreTipoDoc(String as_s)
	{
		is_nombreTipoDoc = as_s;
	}

	/**
	 * Retorna el valor de nombre tipo doc.
	 *
	 * @return el valor de nombre tipo doc
	 */
	public String getNombreTipoDoc()
	{
		return is_nombreTipoDoc;
	}

	/**
	 * Modifica el valor de nombre tipo entidad.
	 *
	 * @param as_s asigna el valor a la propiedad nombre tipo entidad
	 */
	public void setNombreTipoEntidad(String as_s)
	{
		is_nombreTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de nombre tipo entidad.
	 *
	 * @return el valor de nombre tipo entidad
	 */
	public String getNombreTipoEntidad()
	{
		return is_nombreTipoEntidad;
	}

	/**
	 * Modifica el valor de nombre tipo oficina.
	 *
	 * @param as_s asigna el valor a la propiedad nombre tipo oficina
	 */
	public void setNombreTipoOficina(String as_s)
	{
		is_nombreTipoOficina = as_s;
	}

	/**
	 * Retorna el valor de nombre tipo oficina.
	 *
	 * @return el valor de nombre tipo oficina
	 */
	public String getNombreTipoOficina()
	{
		return is_nombreTipoOficina;
	}

	/**
	 * Modifica el valor de nota devolutiva.
	 *
	 * @param ab_b asigna el valor a la propiedad nota devolutiva
	 */
	public void setNotaDevolutiva(boolean ab_b)
	{
		ib_notaDevolutiva = ab_b;
	}

	/**
	 * Valida la propiedad nota devolutiva.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nota devolutiva
	 */
	public boolean isNotaDevolutiva()
	{
		return ib_notaDevolutiva;
	}

	/**
	 * Modifica el valor de nota devolutiva medida cautelar.
	 *
	 * @param ab_b asigna el valor a la propiedad nota devolutiva medida cautelar
	 */
	public void setNotaDevolutivaMedidaCautelar(boolean ab_b)
	{
		ib_notaDevolutivaMedidaCautelar = ab_b;
	}

	/**
	 * Valida la propiedad nota devolutiva medida cautelar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nota devolutiva medida cautelar
	 */
	public boolean isNotaDevolutivaMedidaCautelar()
	{
		return ib_notaDevolutivaMedidaCautelar;
	}

	/**
	 * Modifica el valor de numero.
	 *
	 * @param as_s asigna el valor a la propiedad numero
	 */
	public void setNumero(String as_s)
	{
		is_numero = as_s;
	}

	/**
	 * Retorna el valor de numero.
	 *
	 * @return el valor de numero
	 */
	public String getNumero()
	{
		return is_numero;
	}

	/**
	 * Modifica el valor de numero proceso.
	 *
	 * @param as_s asigna el valor a la propiedad numero proceso
	 */
	public void setNumeroProceso(String as_s)
	{
		is_numeroProceso = as_s;
	}

	/**
	 * Retorna el valor de numero proceso.
	 *
	 * @return el valor de numero proceso
	 */
	public String getNumeroProceso()
	{
		return is_numeroProceso;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_observaciones asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_observaciones)
	{
		is_observaciones = as_observaciones;
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
	 * Modifica el valor de oficina origen medida cautelar.
	 *
	 * @param aoo_oo asigna el valor a la propiedad oficina origen medida cautelar
	 */
	public void setOficinaOrigenMedidaCautelar(OficinaOrigen aoo_oo)
	{
		ioo_oficinaOrigenMedidaCautelar = aoo_oo;
	}

	/**
	 * Retorna el valor de oficina origen medida cautelar.
	 *
	 * @return el valor de oficina origen medida cautelar
	 */
	public OficinaOrigen getOficinaOrigenMedidaCautelar()
	{
		return ioo_oficinaOrigenMedidaCautelar;
	}

	/**
	 * Modifica el valor de orden.
	 *
	 * @param al_l asigna el valor a la propiedad orden
	 */
	public void setOrden(Long al_l)
	{
		il_orden = al_l;
	}

	/**
	 * Retorna el valor de orden.
	 *
	 * @return el valor de orden
	 */
	public Long getOrden()
	{
		return il_orden;
	}

	/**
	 * Modifica el valor de porcentaje.
	 *
	 * @param ad_d asigna el valor a la propiedad porcentaje
	 */
	public void setPorcentaje(Double ad_d)
	{
		id_porcentaje = ad_d;
	}

	/**
	 * Retorna el valor de porcentaje.
	 *
	 * @return el valor de porcentaje
	 */
	public Double getPorcentaje()
	{
		return id_porcentaje;
	}

	/**
	 * Modifica el valor de porcentaje str.
	 *
	 * @param as_s asigna el valor a la propiedad porcentaje str
	 */
	public void setPorcentajeStr(String as_s)
	{
		is_porcentajeStr = as_s;
	}

	/**
	 * Retorna el valor de porcentaje str.
	 *
	 * @return el valor de porcentaje str
	 */
	public String getPorcentajeStr()
	{
		return is_porcentajeStr;
	}

	/**
	 * Modifica el valor de primer apellido.
	 *
	 * @param as_s asigna el valor a la propiedad primer apellido
	 */
	public void setPrimerApellido(String as_s)
	{
		is_primerApellido = as_s;
	}

	/**
	 * Retorna el valor de primer apellido.
	 *
	 * @return el valor de primer apellido
	 */
	public String getPrimerApellido()
	{
		return is_primerApellido;
	}

	/**
	 * Modifica el valor de PrimerEnglobe.
	 *
	 * @param ab_b Modifica el valor de la propiedad primerEnglobe
	 */
	public void setPrimerEnglobe(boolean ab_b)
	{
		ib_primerEnglobe = ab_b;
	}

	/**
	 * Valida la propiedad primer englobe.
	 *
	 * @return Retorna el valor de la propiedad primerEnglobe
	 */
	public boolean isPrimerEnglobe()
	{
		return ib_primerEnglobe;
	}

	/**
	 * Modifica el valor de primer nombre.
	 *
	 * @param as_s asigna el valor a la propiedad primer nombre
	 */
	public void setPrimerNombre(String as_s)
	{
		is_primerNombre = as_s;
	}

	/**
	 * Retorna el valor de primer nombre.
	 *
	 * @return el valor de primer nombre
	 */
	public String getPrimerNombre()
	{
		return is_primerNombre;
	}

	/**
	 * Modifica el valor de proceso A validar.
	 *
	 * @param as_s asigna el valor a la propiedad proceso A validar
	 */
	public void setProcesoAValidar(String as_s)
	{
		is_procesoAValidar = as_s;
	}

	/**
	 * Retorna el valor de proceso A validar.
	 *
	 * @return el valor de proceso A validar
	 */
	public String getProcesoAValidar()
	{
		return is_procesoAValidar;
	}

	/**
	 * Modifica el valor de radicacion.
	 *
	 * @param as_s asigna el valor a la propiedad radicacion
	 */
	public void setRadicacion(String as_s)
	{
		is_radicacion = as_s;
	}

	/**
	 * Retorna el valor de radicacion.
	 *
	 * @return el valor de radicacion
	 */
	public String getRadicacion()
	{
		return is_radicacion;
	}

	/**
	 * Modifica el valor de razon social.
	 *
	 * @param as_s asigna el valor a la propiedad razon social
	 */
	public void setRazonSocial(String as_s)
	{
		is_razonSocial = as_s;
	}

	/**
	 * Retorna el valor de razon social.
	 *
	 * @return el valor de razon social
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
	}

	/**
	 * Modifica el valor de referencia.
	 *
	 * @param as_s asigna el valor a la propiedad referencia
	 */
	public void setReferencia(String as_s)
	{
		is_referencia = as_s;
	}

	/**
	 * Retorna el valor de referencia.
	 *
	 * @return el valor de referencia
	 */
	public String getReferencia()
	{
		return is_referencia;
	}

	/**
	 * Modifica el valor de registro parcial calificacion.
	 *
	 * @param arpc_rpc asigna el valor a la propiedad registro parcial calificacion
	 */
	public void setRegistroParcialCalificacion(RegistroParcialCalificacion arpc_rpc)
	{
		irpc_registroParcialCalificacion = arpc_rpc;
	}

	/**
	 * Retorna el valor de registro parcial calificacion.
	 *
	 * @return el valor de registro parcial calificacion
	 */
	public RegistroParcialCalificacion getRegistroParcialCalificacion()
	{
		return irpc_registroParcialCalificacion;
	}

	/**
	 * Modifica el valor de reloteo.
	 *
	 * @param ab_b asigna el valor a la propiedad reloteo
	 */
	public void setReloteo(boolean ab_b)
	{
		ib_reloteo = ab_b;
	}

	/**
	 * Valida la propiedad reloteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en reloteo
	 */
	public boolean isReloteo()
	{
		return ib_reloteo;
	}

	/**
	 * Modifica el valor de remanente.
	 *
	 * @param ab_b asigna el valor a la propiedad remanente
	 */
	public void setRemanente(boolean ab_b)
	{
		ib_remanente = ab_b;
	}

	/**
	 * Valida la propiedad remanente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en remanente
	 */
	public boolean isRemanente()
	{
		return ib_remanente;
	}

	/**
	 * Modifica el valor de ReproduccionConstancia.
	 *
	 * @param ab_b Modifica el valor de la propiedad reproduccionConstancia
	 */
	public void setReproduccionConstancia(boolean ab_b)
	{
		ib_reproduccionConstancia = ab_b;
	}

	/**
	 * Valida la propiedad reproduccion constancia.
	 *
	 * @return Retorna el valor de la propiedad reproduccionConstancia
	 */
	public boolean isReproduccionConstancia()
	{
		return ib_reproduccionConstancia;
	}

	/**
	 * Modifica el valor de revision.
	 *
	 * @param as_s asigna el valor a la propiedad revision
	 */
	public void setRevision(String as_s)
	{
		is_revision = as_s;
	}

	/**
	 * Modifica el valor de revision.
	 *
	 * @param ab_b asigna el valor a la propiedad revision
	 */
	public void setRevision(boolean ab_b)
	{
		ib_revision = ab_b;
	}

	/**
	 * Retorna el valor de revision.
	 *
	 * @return el valor de revision
	 */
	public String getRevision()
	{
		return is_revision;
	}

	/**
	 * Valida la propiedad revision.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en revision
	 */
	public boolean isRevision()
	{
		return ib_revision;
	}

	/**
	 * Modifica el valor de rol persona.
	 *
	 * @param as_s asigna el valor a la propiedad rol persona
	 */
	public void setRolPersona(String as_s)
	{
		is_rolPersona = as_s;
	}

	/**
	 * Retorna el valor de rol persona.
	 *
	 * @return el valor de rol persona
	 */
	public String getRolPersona()
	{
		return is_rolPersona;
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
	 * Modifica el valor de salvar cierre folio.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar cierre folio
	 */
	public void setSalvarCierreFolio(boolean ab_b)
	{
		ib_salvarCierreFolio = ab_b;
	}

	/**
	 * Valida la propiedad salvar cierre folio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar cierre folio
	 */
	public boolean isSalvarCierreFolio()
	{
		return ib_salvarCierreFolio;
	}

	/**
	 * Modifica el valor de salvar definitivo.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar definitivo
	 */
	public void setSalvarDefinitivo(boolean ab_b)
	{
		ib_salvarDefinitivo = ab_b;
	}

	/**
	 * Valida la propiedad salvar definitivo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar definitivo
	 */
	public boolean isSalvarDefinitivo()
	{
		return ib_salvarDefinitivo;
	}

	/**
	 * Modifica el valor de salvar venta parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar venta parcial
	 */
	public void setSalvarVentaParcial(boolean ab_b)
	{
		ib_salvarVentaParcial = ab_b;
	}

	/**
	 * Valida la propiedad salvar venta parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar venta parcial
	 */
	public boolean isSalvarVentaParcial()
	{
		return ib_salvarVentaParcial;
	}

	/**
	 * Modifica el valor de segundo apellido.
	 *
	 * @param as_s asigna el valor a la propiedad segundo apellido
	 */
	public void setSegundoApellido(String as_s)
	{
		is_segundoApellido = as_s;
	}

	/**
	 * Retorna el valor de segundo apellido.
	 *
	 * @return el valor de segundo apellido
	 */
	public String getSegundoApellido()
	{
		return is_segundoApellido;
	}

	/**
	 * Modifica el valor de segundo nombre.
	 *
	 * @param as_s asigna el valor a la propiedad segundo nombre
	 */
	public void setSegundoNombre(String as_s)
	{
		is_segundoNombre = as_s;
	}

	/**
	 * Retorna el valor de segundo nombre.
	 *
	 * @return el valor de segundo nombre
	 */
	public String getSegundoNombre()
	{
		return is_segundoNombre;
	}

	/**
	 * Modifica el valor de seleccionar.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionar
	 */
	public void setSeleccionar(boolean ab_b)
	{
		ib_seleccionar = ab_b;
	}

	/**
	 * Valida la propiedad seleccionar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionar
	 */
	public boolean isSeleccionar()
	{
		return ib_seleccionar;
	}

	/**
	 * Modifica el valor de selected.
	 *
	 * @param ab_b asigna el valor a la propiedad selected
	 */
	public void setSelected(boolean ab_b)
	{
		ib_selected = ab_b;
	}

	/**
	 * Valida la propiedad selected.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en selected
	 */
	public boolean isSelected()
	{
		return ib_selected;
	}

	/**
	 * Modifica el valor de tab index.
	 *
	 * @param ai_i asigna el valor a la propiedad tab index
	 */
	public void setTabIndex(int ai_i)
	{
		ii_tabIndex = ai_i;
	}

	/**
	 * Retorna el valor de tab index.
	 *
	 * @return el valor de tab index
	 */
	public int getTabIndex()
	{
		return ii_tabIndex;
	}

	/**
	 * Modifica el valor de tipo doc.
	 *
	 * @param as_s asigna el valor a la propiedad tipo doc
	 */
	public void setTipoDoc(String as_s)
	{
		is_tipoDoc = as_s;
	}

	/**
	 * Retorna el valor de tipo doc.
	 *
	 * @return el valor de tipo doc
	 */
	public String getTipoDoc()
	{
		return is_tipoDoc;
	}

	/**
	 * Modifica el valor de tipo predio.
	 *
	 * @param as_s asigna el valor a la propiedad tipo predio
	 */
	public void setTipoPredio(String as_s)
	{
		is_tipoPredio = as_s;
	}

	/**
	 * Retorna el valor de tipo predio.
	 *
	 * @return el valor de tipo predio
	 */
	public String getTipoPredio()
	{
		return is_tipoPredio;
	}

	/**
	 * Modifica el valor de total anotaciones.
	 *
	 * @param totalAnotaciones asigna el valor a la propiedad total anotaciones
	 */
	public void setTotalAnotaciones(int totalAnotaciones)
	{
		this.ii_totalAnotaciones = totalAnotaciones;
	}

	/**
	 * Retorna el valor de total anotaciones.
	 *
	 * @return el valor de total anotaciones
	 */
	public int getTotalAnotaciones()
	{
		return ii_totalAnotaciones;
	}

	/**
	 * Modifica el valor de total matriculas revision.
	 *
	 * @param ai_i asigna el valor a la propiedad total matriculas revision
	 */
	public void setTotalMatriculasRevision(int ai_i)
	{
		ii_totalMatriculasRevision = ai_i;
	}

	/**
	 * Retorna el valor de total matriculas revision.
	 *
	 * @return el valor de total matriculas revision
	 */
	public int getTotalMatriculasRevision()
	{
		return ii_totalMatriculasRevision;
	}

	/**
	 * Modifica el valor de total revision.
	 *
	 * @param ai_i asigna el valor a la propiedad total revision
	 */
	public void setTotalRevision(int ai_i)
	{
		ii_totalRevision = ai_i;
	}

	/**
	 * Retorna el valor de total revision.
	 *
	 * @return el valor de total revision
	 */
	public int getTotalRevision()
	{
		return ii_totalRevision;
	}

	/**
	 * Modifica el valor de tramite.
	 *
	 * @param as_s asigna el valor a la propiedad tramite
	 */
	public void setTramite(String as_s)
	{
		is_tramite = as_s;
	}

	/**
	 * Retorna el valor de tramite.
	 *
	 * @return el valor de tramite
	 */
	public String getTramite()
	{
		return is_tramite;
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
	 * Modifica el valor de user id.
	 *
	 * @param as_s asigna el valor a la propiedad user id
	 */
	public void setUserId(String as_s)
	{
		is_userId = as_s;
	}

	/**
	 * Retorna el valor de user id.
	 *
	 * @return el valor de user id
	 */
	public String getUserId()
	{
		return is_userId;
	}

	/**
	 * Modifica el valor de validar area.
	 *
	 * @param ab_b asigna el valor a la propiedad validar area
	 */
	public void setValidarArea(boolean ab_b)
	{
		ib_validarArea = ab_b;
	}

	/**
	 * Valida la propiedad validar area.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validar area
	 */
	public boolean isValidarArea()
	{
		return ib_validarArea;
	}

	/**
	 * Modifica el valor de valor.
	 *
	 * @param abd_bd asigna el valor a la propiedad valor
	 */
	public void setValor(BigDecimal abd_bd)
	{
		ibd_valor = abd_bd;
	}

	/**
	 * Retorna el valor de valor.
	 *
	 * @return el valor de valor
	 */
	public BigDecimal getValor()
	{
		return ibd_valor;
	}

	/**
	 * Modifica el valor de valor anotacion.
	 *
	 * @param al_l asigna el valor a la propiedad valor anotacion
	 */
	public void setValorAnotacion(Long al_l)
	{
		il_valorAnotacion = al_l;
	}

	/**
	 * Retorna el valor de valor anotacion.
	 *
	 * @return el valor de valor anotacion
	 */
	public Long getValorAnotacion()
	{
		return il_valorAnotacion;
	}

	/**
	 * Modifica el valor de value interviniente.
	 *
	 * @param as_s asigna el valor a la propiedad value interviniente
	 */
	public void setValueInterviniente(String as_s)
	{
		is_valueInterviniente = as_s;
	}

	/**
	 * Retorna el valor de value interviniente.
	 *
	 * @return el valor de value interviniente
	 */
	public String getValueInterviniente()
	{
		return is_valueInterviniente;
	}

	/**
	 * Modifica el valor de value propietario.
	 *
	 * @param as_s asigna el valor a la propiedad value propietario
	 */
	public void setValuePropietario(String as_s)
	{
		is_valuePropietario = as_s;
	}

	/**
	 * Retorna el valor de value propietario.
	 *
	 * @return el valor de value propietario
	 */
	public String getValuePropietario()
	{
		return is_valuePropietario;
	}

	/**
	 * Modifica el valor de venta parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad venta parcial
	 */
	public void setVentaParcial(boolean ab_b)
	{
		ib_ventaParcial = ab_b;
	}

	/**
	 * Valida la propiedad venta parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en venta parcial
	 */
	public boolean isVentaParcial()
	{
		return ib_ventaParcial;
	}

	/**
	 * Modifica el valor de version.
	 *
	 * @param al_l asigna el valor a la propiedad version
	 */
	public void setVersion(Long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna el valor de version.
	 *
	 * @return el valor de version
	 */
	public Long getVersion()
	{
		return il_version;
	}

	/**
	 * Modifica el valor de zona registral.
	 *
	 * @param azr_zr asigna el valor a la propiedad zona registral
	 */
	public void setZonaRegistral(ZonaRegistral azr_zr)
	{
		izr_zonaRegistral = azr_zr;
	}

	/**
	 * Retorna el valor de zona registral.
	 *
	 * @return el valor de zona registral
	 */
	public ZonaRegistral getZonaRegistral()
	{
		return izr_zonaRegistral;
	}

	/**
	 * Valida la propiedad calificacion.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en calificacion
	 */
	public boolean isCalificacion()
	{
		return ib_calificacion;
	}

	/**
	 * Modifica el valor de Calificacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setCalificacion(boolean ab_b)
	{
		ib_calificacion = ab_b;
	}
}
