package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades Registro.
 *
 * @author ccalderon
 */
public class Registro extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6853625893166976999L;

	/** Propiedad id bgn documento. */
	private com.bachue.snr.prosnr01.model.sdb.bng.Documento id_bgnDocumento;

	/** Propiedad ia acto. */
	private Acto ia_acto;

	/** Propiedad ica actos. */
	private Collection<Acto> ica_actos;

	/** Propiedad icl info liquidacion rep constancia. */
	private Collection<Liquidacion> icl_infoLiquidacionRepConstancia;

	/** Propiedad icp listado persona. */
	private Collection<Persona> icp_listadoPersona;

	/** Propiedad icpce listado correo electronico. */
	private Collection<PersonaCorreoElectronico> icpce_listadoCorreoElectronico;

	/** Propiedad icpd listado direccion correspondencia. */
	private Collection<PersonaDireccion> icpd_listadoDireccionCorrespondencia;

	/** Propiedad icpd listado direccion residencia. */
	private Collection<PersonaDireccion> icpd_listadoDireccionResidencia;

	/** Propiedad icpt listado telefono fijo. */
	private Collection<PersonaTelefono> icpt_listadoTelefonoFijo;

	/** Propiedad icpt listado telefono movil. */
	private Collection<PersonaTelefono> icpt_listadoTelefonoMovil;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad ldd devolucion dinero. */
	private DevolucionDinero ldd_devolucionDinero;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad ids documento salida. */
	private DocumentosSalida ids_documentoSalida;

	/** Propiedad ii interviniente. */
	private Interviniente ii_interviniente;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad ioo oficina origen. */
	private OficinaOrigen ioo_oficinaOrigen;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ipce persona correo electronico. */
	private PersonaCorreoElectronico ipce_personaCorreoElectronico;

	/** Propiedad ipd direccion correspondencia. */
	private PersonaDireccion ipd_direccionCorrespondencia;

	/** Propiedad ipd direccion residencia. */
	private PersonaDireccion ipd_direccionResidencia;

	/** Propiedad ipt telefono fijo. */
	private PersonaTelefono ipt_telefonoFijo;

	/** Propiedad ipt telefono movil. */
	private PersonaTelefono ipt_telefonoMovil;

	/** Propiedad iso solicitud. */
	private Solicitud iso_solicitud;

	/** Propiedad isi solicitud interviniente. */
	private SolicitudInterviniente isi_solicitudInterviniente;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id condicion. */
	private String is_idCondicion;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id tipo mayor valor. */
	private String is_idTipoMayorValor;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is id turno nueva entrada. */
	private String is_idTurnoNuevaEntrada;

	/** Propiedad is ind condicion. */
	private String is_indCondicion;

	/** Propiedad is nir proceso. */
	private String is_nirProceso;

	/** Propiedad is numero recibo caja. */
	private String is_numeroReciboCaja;

	/** Propiedad is observaciones proceso. */
	private String is_observacionesProceso;

	/** Propiedad is tipo recibo. */
	private String is_tipoRecibo;

	/** Propiedad is url Capture. */
	private String is_urlCapture;

	/** Propiedad ib pdf liquidacion. */
	private byte[] ib_pdfLiquidacion;

	/** Propiedad iba pdf. */
	private byte[] iba_pdf;

	/** Propiedad iba pdf prorroga mayor valor. */
	private byte[] iba_pdfProrrogaMayorValor;

	/** Propiedad ib telefono movil cargado. */
	private boolean ib_calificacion;

	/** Propiedad ib correcciones registro. */
	private boolean ib_correccionesRegistro;

	/** Propiedad ib correo cargado. */
	private boolean ib_correoCargado;

	/** Propiedad ib direccion correspondencia cargada. */
	private boolean ib_direccionCorrespondenciaCargada;

	/** Propiedad ib direccion residencia cargada. */
	private boolean ib_direccionResidenciaCargada;

	/** Propiedad ib es articulo suspension. */
	private boolean ib_esArticuloSuspension;

	/** Propiedad ib es creacion matricula oficio. */
	private boolean ib_esCreacionMatriculaOficio;

	/** Propiedad ib es grabacion matriculas. */
	private boolean ib_esGrabacionMatriculas;

	/** Propiedad ib es impuesto registro gob. */
	private boolean ib_esImpuestoRegistroGob;

	/** Propiedad ib es proceso desistimiento. */
	private boolean ib_esProcesoDesistimiento;

	/** Propiedad ib es prorroga documentacion. */
	private boolean ib_esProrrogaDocumentacion;

	/** Propiedad ib es prorroga mayor valor. */
	private boolean ib_esProrrogaMayorValor;

	/** Propiedad ib_esRecepcionRecursos. */
	private boolean ib_esRecepcionRecursos;

	/** Propiedad ib es Restitución. */
	private boolean ib_esRestitucion;

	/** Propiedad ib_esSegundaInstancia. */
	private boolean ib_esSegundaInstancia;

	/** Propiedad ib es suspension entrega documentacion. */
	private boolean ib_esSuspensionEntregaDocumentacion;

	/** Propiedad ib grabacion correcciones. */
	private boolean ib_grabacionCorrecciones;

	/** Propiedad ib nueva solicitud. */
	private boolean ib_nuevaSolicitud;

	/** Propiedad ib persona cargada. */
	private boolean ib_personaCargada;

	/** Propiedad ib persona vinculada. */
	private boolean ib_personaVinculada;

	/** Propiedad ib proceso certificados. */
	private boolean ib_procesoCertificados;

	/** Propiedad ib proceso correcciones. */
	private boolean ib_procesoCorrecciones;

	/** Propiedad ib proceso devoluciones. */
	private boolean ib_procesoDevoluciones;

	/** Propiedad ib recepcion documentos. */
	private boolean ib_recepcionDocumentos;

	/** Propiedad ib seleccionado es apoderado. */
	private boolean ib_seleccionadoEsApoderado;

	/** Propiedad ib telefono fijo cargado. */
	private boolean ib_telefonoFijoCargado;

	/** Propiedad ib telefono movil cargado. */
	private boolean ib_telefonoMovilCargado;

	/** Propiedad ii consecutivo liquidacion. */
	private int ii_consecutivoLiquidacion;

	/**
	 * Modifica el valor de acto.
	 *
	 * @param aa_a asigna el valor a la propiedad acto
	 */
	public void setActo(Acto aa_a)
	{
		ia_acto = aa_a;
	}

	/**
	 * Retorna el valor de acto.
	 *
	 * @return el valor de acto
	 */
	public Acto getActo()
	{
		return ia_acto;
	}

	/**
	 * Modifica el valor de actos.
	 *
	 * @param aca_a asigna el valor a la propiedad actos
	 */
	public void setActos(Collection<Acto> aca_a)
	{
		ica_actos = aca_a;
	}

	/**
	 * Retorna el valor de actos.
	 *
	 * @return el valor de actos
	 */
	public Collection<Acto> getActos()
	{
		return ica_actos;
	}

	/**
	 * Modifica el valor de bgn documento.
	 *
	 * @param ad_d asigna el valor a la propiedad bgn documento
	 */
	public void setBgnDocumento(com.bachue.snr.prosnr01.model.sdb.bng.Documento ad_d)
	{
		id_bgnDocumento = ad_d;
	}

	/**
	 * Retorna el valor de bgn documento.
	 *
	 * @return el valor de bgn documento
	 */
	public com.bachue.snr.prosnr01.model.sdb.bng.Documento getBgnDocumento()
	{
		return id_bgnDocumento;
	}

	/**
	 * Modifica el valor de calificacion
	 *
	 * @param ab_b asigna el valor a la propiedad calificacion
	 */
	public void setCalificacion(boolean ab_calificacion)
	{
		ib_calificacion = ab_calificacion;
	}

	/**
	 * Valida la propiedad calificacion
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en telefono movil cargado
	 */
	public boolean isCalificacion()
	{
		return ib_calificacion;
	}

	/**
	 * @param ai_i Modifica el valor de la propiedad consecutivoLiquidacion
	 */
	public void setConsecutivoLiquidacion(int ai_i)
	{
		ii_consecutivoLiquidacion = ai_i;
	}

	/**
	 * @return Retorna el valor de la propiedad consecutivoLiquidacion
	 */
	public int getConsecutivoLiquidacion()
	{
		return ii_consecutivoLiquidacion;
	}

	/**
	 * Modifica el valor de CorreccionesRegistro.
	 *
	 * @param ab_b de ab b
	 */
	public void setCorreccionesRegistro(boolean ab_b)
	{
		ib_correccionesRegistro = ab_b;
	}

	/**
	 * Valida la propiedad correcciones registro.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public boolean isCorreccionesRegistro()
	{
		return ib_correccionesRegistro;
	}

	/**
	 * Modifica el valor de correo cargado.
	 *
	 * @param ab_b asigna el valor a la propiedad correo cargado
	 */
	public void setCorreoCargado(boolean ab_b)
	{
		ib_correoCargado = ab_b;
	}

	/**
	 * Valida la propiedad correo cargado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en correo cargado
	 */
	public boolean isCorreoCargado()
	{
		return ib_correoCargado;
	}

	/**
	 * Modifica el valor de DatosAntSistema.
	 *
	 * @param adas_das de adas das
	 */
	public void setDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_datosAntSistema = adas_das;
	}

	/**
	 * Retorna Objeto o variable de valor datos ant sistema.
	 *
	 * @return Retorna el valor de la propiedad datosAntSistema
	 */
	public DatosAntSistema getDatosAntSistema()
	{
		return idas_datosAntSistema;
	}

	/**
	 * Modifica el valor de DevolucionDinero.
	 *
	 * @param add_dd de add dd
	 */
	public void setDevolucionDinero(DevolucionDinero add_dd)
	{
		ldd_devolucionDinero = add_dd;
	}

	/**
	 * Retorna Objeto o variable de valor devolucion dinero.
	 *
	 * @return Retorna el valor de la propiedad devolucionDinero
	 */
	public DevolucionDinero getDevolucionDinero()
	{
		return ldd_devolucionDinero;
	}

	/**
	 * Modifica el valor de direccion correspondencia.
	 *
	 * @param apd_pd asigna el valor a la propiedad direccion correspondencia
	 */
	public void setDireccionCorrespondencia(PersonaDireccion apd_pd)
	{
		ipd_direccionCorrespondencia = apd_pd;
	}

	/**
	 * Retorna el valor de direccion correspondencia.
	 *
	 * @return el valor de direccion correspondencia
	 */
	public PersonaDireccion getDireccionCorrespondencia()
	{
		return ipd_direccionCorrespondencia;
	}

	/**
	 * Modifica el valor de direccion correspondencia cargada.
	 *
	 * @param ab_b asigna el valor a la propiedad direccion correspondencia cargada
	 */
	public void setDireccionCorrespondenciaCargada(boolean ab_b)
	{
		ib_direccionCorrespondenciaCargada = ab_b;
	}

	/**
	 * Valida la propiedad direccion correspondencia cargada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en direccion correspondencia cargada
	 */
	public boolean isDireccionCorrespondenciaCargada()
	{
		return ib_direccionCorrespondenciaCargada;
	}

	/**
	 * Modifica el valor de direccion residencia.
	 *
	 * @param apd_pd asigna el valor a la propiedad direccion residencia
	 */
	public void setDireccionResidencia(PersonaDireccion apd_pd)
	{
		ipd_direccionResidencia = apd_pd;
	}

	/**
	 * Retorna el valor de direccion residencia.
	 *
	 * @return el valor de direccion residencia
	 */
	public PersonaDireccion getDireccionResidencia()
	{
		return ipd_direccionResidencia;
	}

	/**
	 * Modifica el valor de direccion residencia cargada.
	 *
	 * @param ab_b asigna el valor a la propiedad direccion residencia cargada
	 */
	public void setDireccionResidenciaCargada(boolean ab_b)
	{
		ib_direccionResidenciaCargada = ab_b;
	}

	/**
	 * Valida la propiedad direccion residencia cargada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en direccion residencia cargada
	 */
	public boolean isDireccionResidenciaCargada()
	{
		return ib_direccionResidenciaCargada;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		return id_documento;
	}

	/**
	 * @param ads_ds Modifica el valor de la propiedad documentoSalida
	 */
	public void setDocumentoSalida(DocumentosSalida ads_ds)
	{
		ids_documentoSalida = ads_ds;
	}

	/**
	 * @return Retorna el valor de la propiedad documentoSalida
	 */
	public DocumentosSalida getDocumentoSalida()
	{
		return ids_documentoSalida;
	}

	/**
	 * Modifica el valor de es articulo suspension.
	 *
	 * @param ab_b asigna el valor a la propiedad es articulo suspension
	 */
	public void setEsArticuloSuspension(boolean ab_b)
	{
		ib_esArticuloSuspension = ab_b;
	}

	/**
	 * Valida la propiedad es articulo suspension.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es articulo suspension
	 */
	public boolean isEsArticuloSuspension()
	{
		return ib_esArticuloSuspension;
	}

	/**
	 * Modifica el valor de EsCreacionMatriculaOficio.
	 *
	 * @param ab_b Modifica el valor de la propiedad esCreacionMatriculaOficio
	 */
	public void setEsCreacionMatriculaOficio(boolean ab_b)
	{
		ib_esCreacionMatriculaOficio = ab_b;
	}

	/**
	 * Valida la propiedad es creacion matricula oficio.
	 *
	 * @return Retorna el valor de la propiedad esCreacionMatriculaOficio
	 */
	public boolean isEsCreacionMatriculaOficio()
	{
		return ib_esCreacionMatriculaOficio;
	}

	/**
	 * Modifica el valor de es grabacion matriculas.
	 *
	 * @param ab_b asigna el valor a la propiedad es grabacion matriculas
	 */
	public void setEsGrabacionMatriculas(boolean ab_b)
	{
		ib_esGrabacionMatriculas = ab_b;
	}

	/**
	 * Valida la propiedad es grabacion matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es grabacion matriculas
	 */
	public boolean isEsGrabacionMatriculas()
	{
		return ib_esGrabacionMatriculas;
	}

	/**
	 * Modifica el valor de EsImpuestoRegistroGob.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsImpuestoRegistroGob(boolean ab_b)
	{
		ib_esImpuestoRegistroGob = ab_b;
	}

	/**
	 * Valida la propiedad es impuesto registro gob.
	 *
	 * @return Retorna el valor de la propiedad esImpuestoRegistroGob
	 */
	public boolean isEsImpuestoRegistroGob()
	{
		return ib_esImpuestoRegistroGob;
	}

	/**
	 * Modifica el valor de es proceso desistimiento.
	 *
	 * @param ab_b asigna el valor a la propiedad es proceso desistimiento
	 */
	public void setEsProcesoDesistimiento(boolean ab_b)
	{
		ib_esProcesoDesistimiento = ab_b;
	}

	/**
	 * Valida la propiedad es proceso desistimiento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es proceso desistimiento
	 */
	public boolean isEsProcesoDesistimiento()
	{
		return ib_esProcesoDesistimiento;
	}

	/**
	 * Modifica el valor de es prorroga documentacion.
	 *
	 * @param ab_b asigna el valor a la propiedad es prorroga documentacion
	 */
	public void setEsProrrogaDocumentacion(boolean ab_b)
	{
		ib_esProrrogaDocumentacion = ab_b;
	}

	/**
	 * Valida la propiedad es prorroga documentacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es prorroga documentacion
	 */
	public boolean isEsProrrogaDocumentacion()
	{
		return ib_esProrrogaDocumentacion;
	}

	/**
	 * Modifica el valor de es prorroga mayor valor.
	 *
	 * @param ab_b asigna el valor a la propiedad es prorroga mayor valor
	 */
	public void setEsProrrogaMayorValor(boolean ab_b)
	{
		ib_esProrrogaMayorValor = ab_b;
	}

	/**
	 * Valida la propiedad es prorroga mayor valor.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es prorroga mayor valor
	 */
	public boolean isEsProrrogaMayorValor()
	{
		return ib_esProrrogaMayorValor;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsRecepcionRecursos(boolean ab_b)
	{
		ib_esRecepcionRecursos = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isEsRecepcionRecursos()
	{
		return ib_esRecepcionRecursos;
	}

	/**
	 * Modifica el valor de es Restitución.
	 *
	 * @param ab_b asigna el valor a la propiedad es Restitución.
	 */
	public void setEsRestitucion(boolean ab_b)
	{
		ib_esRestitucion = ab_b;
	}

	/**
	 * Valida la propiedad es Restitución.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es Restitución.
	 */
	public boolean isEsRestitucion()
	{
		return ib_esRestitucion;
	}

	/**
	 * Modifica el valor de EsSegundaInstancia.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsSegundaInstancia(boolean ab_b)
	{
		ib_esSegundaInstancia = ab_b;
	}

	/**
	 * Valida la propiedad es segunda instancia.
	 *
	 * @return Retorna el valor de la propiedad esSegundaInstancia
	 */
	public boolean isEsSegundaInstancia()
	{
		return ib_esSegundaInstancia;
	}

	/**
	 * Modifica el valor de es suspension entrega documentacion.
	 *
	 * @param ab_b asigna el valor a la propiedad es suspension entrega documentacion
	 */
	public void setEsSuspensionEntregaDocumentacion(boolean ab_b)
	{
		ib_esSuspensionEntregaDocumentacion = ab_b;
	}

	/**
	 * Valida la propiedad es suspension entrega documentacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es suspension entrega documentacion
	 */
	public boolean isEsSuspensionEntregaDocumentacion()
	{
		return ib_esSuspensionEntregaDocumentacion;
	}

	/**
	 * Modifica el valor de GrabacionCorrecciones.
	 *
	 * @param ab_b de ab b
	 */
	public void setGrabacionCorrecciones(boolean ab_b)
	{
		ib_grabacionCorrecciones = ab_b;
	}

	/**
	 * Valida la propiedad grabacion correcciones.
	 *
	 * @return Retorna el valor de la propiedad grabacionCorreciones
	 */
	public boolean isGrabacionCorrecciones()
	{
		return ib_grabacionCorrecciones;
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
	 * Modifica el valor de id condicion.
	 *
	 * @param as_s asigna el valor a la propiedad id condicion
	 */
	public void setIdCondicion(String as_s)
	{
		is_idCondicion = as_s;
	}

	/**
	 * Retorna el valor de id condicion.
	 *
	 * @return el valor de id condicion
	 */
	public String getIdCondicion()
	{
		return is_idCondicion;
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
	 * Modifica el valor de id tipo mayor valor.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo mayor valor
	 */
	public void setIdTipoMayorValor(String as_s)
	{
		is_idTipoMayorValor = as_s;
	}

	/**
	 * Retorna el valor de id tipo mayor valor.
	 *
	 * @return el valor de id tipo mayor valor
	 */
	public String getIdTipoMayorValor()
	{
		return is_idTipoMayorValor;
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
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de id turno nueva entrada.
	 *
	 * @param as_s asigna el valor a la propiedad id turno nueva entrada
	 */
	public void setIdTurnoNuevaEntrada(String as_s)
	{
		is_idTurnoNuevaEntrada = as_s;
	}

	/**
	 * Retorna el valor de id turno nueva entrada.
	 *
	 * @return el valor de id turno nueva entrada
	 */
	public String getIdTurnoNuevaEntrada()
	{
		return is_idTurnoNuevaEntrada;
	}

	/**
	 * Modifica el valor de ind condicion.
	 *
	 * @param as_s asigna el valor a la propiedad ind condicion
	 */
	public void setIndCondicion(String as_s)
	{
		is_indCondicion = as_s;
	}

	/**
	 * Retorna el valor de ind condicion.
	 *
	 * @return el valor de ind condicion
	 */
	public String getIndCondicion()
	{
		return is_indCondicion;
	}

	/**
	 * Modifica el valor de info liquidacion rep constancia.
	 *
	 * @param acl_cl asigna el valor a la propiedad info liquidacion rep constancia
	 */
	public void setInfoLiquidacionRepConstancia(Collection<Liquidacion> acl_cl)
	{
		icl_infoLiquidacionRepConstancia = acl_cl;
	}

	/**
	 * Retorna el valor de info liquidacion rep constancia.
	 *
	 * @return el valor de info liquidacion rep constancia
	 */
	public Collection<Liquidacion> getInfoLiquidacionRepConstancia()
	{
		return icl_infoLiquidacionRepConstancia;
	}

	/**
	 * Modifica el valor de interviniente.
	 *
	 * @param ai_i asigna el valor a la propiedad interviniente
	 */
	public void setInterviniente(Interviniente ai_i)
	{
		ii_interviniente = ai_i;
	}

	/**
	 * Retorna el valor de interviniente.
	 *
	 * @return el valor de interviniente
	 */
	public Interviniente getInterviniente()
	{
		return ii_interviniente;
	}

	/**
	 * Modifica el valor de listado correo electronico.
	 *
	 * @param acpce_pce asigna el valor a la propiedad listado correo electronico
	 */
	public void setListadoCorreoElectronico(Collection<PersonaCorreoElectronico> acpce_pce)
	{
		icpce_listadoCorreoElectronico = acpce_pce;
	}

	/**
	 * Retorna el valor de listado correo electronico.
	 *
	 * @return el valor de listado correo electronico
	 */
	public Collection<PersonaCorreoElectronico> getListadoCorreoElectronico()
	{
		return icpce_listadoCorreoElectronico;
	}

	/**
	 * Modifica el valor de listado direccion correspondencia.
	 *
	 * @param acpd_cpd asigna el valor a la propiedad listado direccion correspondencia
	 */
	public void setListadoDireccionCorrespondencia(Collection<PersonaDireccion> acpd_cpd)
	{
		icpd_listadoDireccionCorrespondencia = acpd_cpd;
	}

	/**
	 * Retorna el valor de listado direccion correspondencia.
	 *
	 * @return el valor de listado direccion correspondencia
	 */
	public Collection<PersonaDireccion> getListadoDireccionCorrespondencia()
	{
		return icpd_listadoDireccionCorrespondencia;
	}

	/**
	 * Modifica el valor de listado direccion residencia.
	 *
	 * @param acpd_pd asigna el valor a la propiedad listado direccion residencia
	 */
	public void setListadoDireccionResidencia(Collection<PersonaDireccion> acpd_pd)
	{
		icpd_listadoDireccionResidencia = acpd_pd;
	}

	/**
	 * Retorna el valor de listado direccion residencia.
	 *
	 * @return el valor de listado direccion residencia
	 */
	public Collection<PersonaDireccion> getListadoDireccionResidencia()
	{
		return icpd_listadoDireccionResidencia;
	}

	/**
	 * Modifica el valor de listado persona.
	 *
	 * @param acp_cp asigna el valor a la propiedad listado persona
	 */
	public void setListadoPersona(Collection<Persona> acp_cp)
	{
		icp_listadoPersona = acp_cp;
	}

	/**
	 * Retorna el valor de listado persona.
	 *
	 * @return el valor de listado persona
	 */
	public Collection<Persona> getListadoPersona()
	{
		return icp_listadoPersona;
	}

	/**
	 * Modifica el valor de listado telefono fijo.
	 *
	 * @param acpt_cpt asigna el valor a la propiedad listado telefono fijo
	 */
	public void setListadoTelefonoFijo(Collection<PersonaTelefono> acpt_cpt)
	{
		icpt_listadoTelefonoFijo = acpt_cpt;
	}

	/**
	 * Retorna el valor de listado telefono fijo.
	 *
	 * @return el valor de listado telefono fijo
	 */
	public Collection<PersonaTelefono> getListadoTelefonoFijo()
	{
		return icpt_listadoTelefonoFijo;
	}

	/**
	 * Modifica el valor de listado telefono movil.
	 *
	 * @param acpt_cpt asigna el valor a la propiedad listado telefono movil
	 */
	public void setListadoTelefonoMovil(Collection<PersonaTelefono> acpt_cpt)
	{
		icpt_listadoTelefonoMovil = acpt_cpt;
	}

	/**
	 * Retorna el valor de listado telefono movil.
	 *
	 * @return el valor de listado telefono movil
	 */
	public Collection<PersonaTelefono> getListadoTelefonoMovil()
	{
		return icpt_listadoTelefonoMovil;
	}

	/**
	 * Modifica el valor de nir proceso.
	 *
	 * @param as_s asigna el valor a la propiedad nir proceso
	 */
	public void setNirProceso(String as_s)
	{
		is_nirProceso = as_s;
	}

	/**
	 * Retorna el valor de nir proceso.
	 *
	 * @return el valor de nir proceso
	 */
	public String getNirProceso()
	{
		return is_nirProceso;
	}

	/**
	 * Modifica el valor de nueva solicitud.
	 *
	 * @param ab_b asigna el valor a la propiedad nueva solicitud
	 */
	public void setNuevaSolicitud(boolean ab_b)
	{
		ib_nuevaSolicitud = ab_b;
	}

	/**
	 * Checks if is nueva solicitud.
	 *
	 * @return true, if is nueva solicitud
	 */
	public boolean isNuevaSolicitud()
	{
		return ib_nuevaSolicitud;
	}

	/**
	 * Modifica el valor de numero recibo caja.
	 *
	 * @param as_s asigna el valor a la propiedad numero recibo caja
	 */
	public void setNumeroReciboCaja(String as_s)
	{
		is_numeroReciboCaja = as_s;
	}

	/**
	 * Retorna el valor de numero recibo caja.
	 *
	 * @return el valor de numero recibo caja
	 */
	public String getNumeroReciboCaja()
	{
		return is_numeroReciboCaja;
	}

	/**
	 * Modifica el valor de observaciones proceso.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones proceso
	 */
	public void setObservacionesProceso(String as_s)
	{
		is_observacionesProceso = as_s;
	}

	/**
	 * Retorna el valor de observaciones proceso.
	 *
	 * @return el valor de observaciones proceso
	 */
	public String getObservacionesProceso()
	{
		return is_observacionesProceso;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param aoo_oo asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(OficinaOrigen aoo_oo)
	{
		ioo_oficinaOrigen = aoo_oo;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public OficinaOrigen getOficinaOrigen()
	{
		return ioo_oficinaOrigen;
	}

	/**
	 * Modifica el valor de pdf.
	 *
	 * @param aba_ba asigna el valor a la propiedad pdf
	 */
	public void setPdf(byte[] aba_ba)
	{
		iba_pdf = aba_ba;
	}

	/**
	 * Retorna el valor de pdf.
	 *
	 * @return el valor de pdf
	 */
	public byte[] getPdf()
	{
		return iba_pdf;
	}

	/**
	 * Modifica el valor de pdf liquidacion.
	 *
	 * @param ab_b asigna el valor a la propiedad pdf liquidacion
	 */
	public void setPdfLiquidacion(byte[] ab_b)
	{
		ib_pdfLiquidacion = ab_b;
	}

	/**
	 * Retorna el valor de pdf liquidacion.
	 *
	 * @return el valor de pdf liquidacion
	 */
	public byte[] getPdfLiquidacion()
	{
		return ib_pdfLiquidacion;
	}

	/**
	 * Modifica el valor de pdf prorroga mayor valor.
	 *
	 * @param ab_b asigna el valor a la propiedad pdf prorroga mayor valor
	 */
	public void setPdfProrrogaMayorValor(byte[] ab_b)
	{
		iba_pdfProrrogaMayorValor = ab_b;
	}

	/**
	 * Retorna el valor de pdf prorroga mayor valor.
	 *
	 * @return el valor de pdf prorroga mayor valor
	 */
	public byte[] getPdfProrrogaMayorValor()
	{
		return iba_pdfProrrogaMayorValor;
	}

	/**
	 * Modifica el valor de persona.
	 *
	 * @param ap_p asigna el valor a la propiedad persona
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @return el valor de persona
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}

	/**
	 * Modifica el valor de persona cargada.
	 *
	 * @param ab_b asigna el valor a la propiedad persona cargada
	 */
	public void setPersonaCargada(boolean ab_b)
	{
		ib_personaCargada = ab_b;
	}

	/**
	 * Valida la propiedad persona cargada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en persona cargada
	 */
	public boolean isPersonaCargada()
	{
		return ib_personaCargada;
	}

	/**
	 * Modifica el valor de persona correo electronico.
	 *
	 * @param apce_pce asigna el valor a la propiedad persona correo electronico
	 */
	public void setPersonaCorreoElectronico(PersonaCorreoElectronico apce_pce)
	{
		ipce_personaCorreoElectronico = apce_pce;
	}

	/**
	 * Retorna el valor de persona correo electronico.
	 *
	 * @return el valor de persona correo electronico
	 */
	public PersonaCorreoElectronico getPersonaCorreoElectronico()
	{
		return ipce_personaCorreoElectronico;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setPersonaVinculada(boolean ab_b)
	{
		ib_personaVinculada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isPersonaVinculada()
	{
		return ib_personaVinculada;
	}

	/**
	 * Modifica el valor de proceso certificados.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso certificados
	 */
	public void setProcesoCertificados(boolean ab_b)
	{
		ib_procesoCertificados = ab_b;
	}

	/**
	 * Valida la propiedad proceso certificados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso certificados
	 */
	public boolean isProcesoCertificados()
	{
		return ib_procesoCertificados;
	}

	/**
	 * Modifica el valor de proceso correcciones.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso correcciones
	 */
	public void setProcesoCorrecciones(boolean ab_b)
	{
		ib_procesoCorrecciones = ab_b;
	}

	/**
	 * Valida la propiedad proceso correcciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso correcciones
	 */
	public boolean isProcesoCorrecciones()
	{
		return ib_procesoCorrecciones;
	}

	/**
	 * Modifica el valor de ProcesoDevoluciones.
	 *
	 * @param ab_b de ab b
	 */
	public void setProcesoDevoluciones(boolean ab_b)
	{
		ib_procesoDevoluciones = ab_b;
	}

	/**
	 * Valida la propiedad proceso devoluciones.
	 *
	 * @return Retorna el valor de la propiedad procesoDevoluciones
	 */
	public boolean isProcesoDevoluciones()
	{
		return ib_procesoDevoluciones;
	}

	/**
	 * Modifica el valor de recepcion documentos.
	 *
	 * @param ab_b asigna el valor a la propiedad recepcion documentos
	 */
	public void setRecepcionDocumentos(boolean ab_b)
	{
		ib_recepcionDocumentos = ab_b;
	}

	/**
	 * Checks if is recepcion documentos.
	 *
	 * @return true, if is recepcion documentos
	 */
	public boolean isRecepcionDocumentos()
	{
		return ib_recepcionDocumentos;
	}

	/**
	 * Modifica el valor de seleccionado es apoderado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado es apoderado
	 */
	public void setSeleccionadoEsApoderado(boolean ab_b)
	{
		ib_seleccionadoEsApoderado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado es apoderado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado es apoderado
	 */
	public boolean isSeleccionadoEsApoderado()
	{
		return ib_seleccionadoEsApoderado;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		iso_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return iso_solicitud;
	}

	/**
	 * Modifica el valor de solicitud interviniente.
	 *
	 * @param asi_si asigna el valor a la propiedad solicitud interviniente
	 */
	public void setSolicitudInterviniente(SolicitudInterviniente asi_si)
	{
		isi_solicitudInterviniente = asi_si;
	}

	/**
	 * Retorna el valor de solicitud interviniente.
	 *
	 * @return el valor de solicitud interviniente
	 */
	public SolicitudInterviniente getSolicitudInterviniente()
	{
		return isi_solicitudInterviniente;
	}

	/**
	 * Modifica el valor de telefono fijo.
	 *
	 * @param apt_pt asigna el valor a la propiedad telefono fijo
	 */
	public void setTelefonoFijo(PersonaTelefono apt_pt)
	{
		ipt_telefonoFijo = apt_pt;
	}

	/**
	 * Retorna el valor de telefono fijo.
	 *
	 * @return el valor de telefono fijo
	 */
	public PersonaTelefono getTelefonoFijo()
	{
		return ipt_telefonoFijo;
	}

	/**
	 * Modifica el valor de telefono fijo cargado.
	 *
	 * @param ab_b asigna el valor a la propiedad telefono fijo cargado
	 */
	public void setTelefonoFijoCargado(boolean ab_b)
	{
		ib_telefonoFijoCargado = ab_b;
	}

	/**
	 * Valida la propiedad telefono fijo cargado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en telefono fijo cargado
	 */
	public boolean isTelefonoFijoCargado()
	{
		return ib_telefonoFijoCargado;
	}

	/**
	 * Modifica el valor de telefono movil.
	 *
	 * @param apt_apt asigna el valor a la propiedad telefono movil
	 */
	public void setTelefonoMovil(PersonaTelefono apt_apt)
	{
		ipt_telefonoMovil = apt_apt;
	}

	/**
	 * Retorna el valor de telefono movil.
	 *
	 * @return el valor de telefono movil
	 */
	public PersonaTelefono getTelefonoMovil()
	{
		return ipt_telefonoMovil;
	}

	/**
	 * Modifica el valor de telefono movil cargado.
	 *
	 * @param ab_b asigna el valor a la propiedad telefono movil cargado
	 */
	public void setTelefonoMovilCargado(boolean ab_b)
	{
		ib_telefonoMovilCargado = ab_b;
	}

	/**
	 * Valida la propiedad telefono movil cargado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en telefono movil cargado
	 */
	public boolean isTelefonoMovilCargado()
	{
		return ib_telefonoMovilCargado;
	}

	/**
	 * Modifica el valor de tipo recibo.
	 *
	 * @param as_s asigna el valor a la propiedad tipo recibo
	 */
	public void setTipoRecibo(String as_s)
	{
		is_tipoRecibo = as_s;
	}

	/**
	 * Retorna el valor de tipo recibo.
	 *
	 * @return el valor de tipo recibo
	 */
	public String getTipoRecibo()
	{
		return is_tipoRecibo;
	}

	/**
	 * Modifica el valor de UrlCapture.
	 *
	 * @param as_s de as s
	 */
	public void setUrlCapture(String as_s)
	{
		is_urlCapture = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor url capture.
	 *
	 * @return Retorna el valor de la propiedad urlCapture
	 */
	public String getUrlCapture()
	{
		return is_urlCapture;
	}
}
