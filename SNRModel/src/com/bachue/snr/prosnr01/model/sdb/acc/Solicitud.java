package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_SOLICITUD.
 *
 * @author hcastaneda
 */
public class Solicitud extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 918946956685909311L;

	/** Propiedad ibd valor recarga. */
	private BigDecimal ibd_valorRecarga;

	/** Propiedad icacd completitud documental. */
	private Collection<AccCompletitudDocumental> icacd_completitudDocumental;

	/** Propiedad icl info liquidacion. */
	private Collection<Liquidacion> icl_infoLiquidacion;

	/** Propiedad ict info turnos impuestos gobernacion. */
	private Collection<Turno> ict_infoTurnosImpuestosGobernacion;

	/** Propiedad icta actos details. */
	private Collection<TipoActo> icta_actosDetails;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad id fecha radicado PQRS. */
	private Date id_fechaRadicadoPQRS;

	/** Propiedad id fecha solicitud. */
	private Date id_fechaSolicitud;

	/** Propiedad id fecha ultimo estado. */
	private Date id_fechaUltimoEstado;

	/** Propiedad id cantidad matriculas. */
	private Double id_cantidadMatriculas;

	/** Propiedad ii version sub proceso. */
	private Integer ii_versionSubProceso;

	/** Propiedad il estado solicitud. */
	private Long il_estadoSolicitud;

	/** Propiedad il id etapa actual. */
	private Long il_idEtapaActual;

	/** Propiedad il identificador copias SE. */
	private Long il_identificadorCopiasSE;

	/** Propiedad il numero copias. */
	private Long il_numeroCopias;

	/** Propiedad il version documento. */
	private Long il_versionDocumento;

	/** Propiedad il version documento inicial. */
	private Long il_versionDocumentoInicial;

	/** Propiedad imss tipos documentales restitucion. */
	private Map<String, String> imss_tiposDocumentalesRestitucion;

	/** Propiedad is anio ant. */
	private String is_anioAnt;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is derecho peticion. */
	private String is_derechoPeticion;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is digitalizada. */
	private String is_digitalizada;

	/** Propiedad is entidad exenta. */
	private String is_entidadExenta;

	/** Propiedad is fecha creacion string. */
	private String is_fechaCreacionString;

	/** Propiedad is id autorizacion comunicacion. */
	private String is_idAutorizacionComunicacion;

	/** Propiedad is id autorizacion notificacion. */
	private String is_idAutorizacionNotificacion;

	/** Propiedad is id calidad solicitante. */
	private String is_idCalidadSolicitante;

	/** Propiedad is id canal origen. */
	private String is_idCanalOrigen;

	/** Propiedad is id circulo ant. */
	private String is_idCirculoAnt;

	/** Propiedad is id correo electronico. */
	private String is_idCorreoElectronico;

	/** Propiedad is id correo electronico comunicacion. */
	private String is_idCorreoElectronicoComunicacion;

	/** Propiedad is id cuenta cupo. */
	private String is_idCuentaCupo;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

	/** Propiedad is id direccion. */
	private String is_idDireccion;

	/** Propiedad is id direccion comunicacion. */
	private String is_idDireccionComunicacion;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id persona entrega. */
	private String is_idPersonaEntrega;

	/** Propiedad is id procedencia. */
	private String is_idProcedencia;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id proceso ant. */
	private String is_idProcesoAnt;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud previo correccion. */
	private String is_idSolicitudPrevioCorreccion;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is id sucursal canal origen. */
	private String is_idSucursalCanalOrigen;

	/** Propiedad is id telefono. */
	private String is_idTelefono;

	/** Propiedad is id telefono comunicacion. */
	private String is_idTelefonoComunicacion;

	/** Propiedad is id testamento. */
	private String is_idTestamento;

	/** Propiedad is id tipo recepcion. */
	private String is_idTipoRecepcion;

	/** Propiedad is id turno ant. */
	private String is_idTurnoAnt;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is ind vinculacion. */
	private String is_indVinculacion;

	/** Propiedad is motivo consulta. */
	private String is_motivoConsulta;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nir asociado. */
	private String is_nirAsociado;

	/** Propiedad is nombre calidad solicitante. */
	private String is_nombreCalidadSolicitante;

	/** Propiedad is nombre estado solicitud. */
	private String is_nombreEstadoSolicitud;

	/** Propiedad is observaciones datos acto. */
	private String is_observacionesDatosActo;

	/** Propiedad is observaciones restitucion. */
	private String is_observacionesRestitucion;

	/** Propiedad is participa interviniente. */
	private String is_participaInterviniente;

	/** Propiedad is radicado PQRS. */
	private String is_radicadoPQRS;

	/** Propiedad is referencia motivo consulta. */
	private String is_referenciaMotivoConsulta;

	/** Propiedad is reproduccion testamento. */
	private String is_reproduccionTestamento;

	/** Propiedad is tipo registro certificado. */
	private String is_tipoRegistroCertificado;

	/** Propiedad is tramite asociado. */
	private String is_tramiteAsociado;

	/** Propiedad is turno reproduccion. */
	private String is_turnoReproduccion;

	/** Propiedad is usuario ant. */
	private String is_usuarioAnt;

	/** Propiedad is usuario orip. */
	private String is_usuarioOrip;

	/** Propiedad ib documento. */
	private boolean ib_documento;

	/** Propiedad ib es copias. */
	private boolean ib_esCopias;

	/** Propiedad ib mayor valor. */
	private boolean ib_mayorValor;

	/** Propiedad ib nueva solicitud. */
	private boolean ib_nuevaSolicitud;

	/** Propiedad ib recibo impuestos. */
	private boolean ib_reciboImpuestos;

	/** Propiedad ib validar tipos doc obligatorios. */
	private boolean ib_validarTiposDocObligatorios;

	/** Propiedad is creacion solicitud. */
	private boolean is_creacionSolicitud;

	/**
	 * Constructor por defecto.
	 */
	public Solicitud()
	{
	}

	/**
	 * Constructor que recibe por parametro el id de solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 */
	public Solicitud(String as_idSolicitud)
	{
		setIdSolicitud(as_idSolicitud);
	}

	/**
	 * Constructor que recibe por parametro el id de solicitud y el nir.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_NIR de as NIR
	 */
	public Solicitud(String as_idSolicitud, String as_NIR)
	{
		setIdSolicitud(as_idSolicitud);
		setNir(as_NIR);
	}

	/**
	 * Modifica el valor de ActosDetails.
	 *
	 * @param acta_ta asigna el valor a la propiedad
	 */
	public void setActosDetails(Collection<TipoActo> acta_ta)
	{
		icta_actosDetails = acta_ta;
	}

	/**
	 * Retorna Objeto o variable de valor actos details.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoActo> getActosDetails()
	{
		return icta_actosDetails;
	}

	/**
	 * Modifica el valor de AnioAnt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnioAnt(String as_s)
	{
		is_anioAnt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anio ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnioAnt()
	{
		return is_anioAnt;
	}

	/**
	 * Valida la propiedad ant sistema.
	 *
	 * @return verdadero si el id del subproceso es igual a 2 de lo contrario retorna falso
	 */
	public boolean isAntSistema()
	{
		return StringUtils.getStringNotNull(is_idSubproceso).equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2);
	}

	/**
	 * Modifica el valor de CantidadMatriculas.
	 *
	 * @param ad_d de ad d
	 */
	public void setCantidadMatriculas(Double ad_d)
	{
		id_cantidadMatriculas = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getCantidadMatriculas()
	{
		return id_cantidadMatriculas;
	}

	/**
	 * Modifica el valor de Comentario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor comentario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de CompletitudDocumental.
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setCompletitudDocumental(Collection<AccCompletitudDocumental> ac_c)
	{
		icacd_completitudDocumental = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor completitud documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AccCompletitudDocumental> getCompletitudDocumental()
	{
		return icacd_completitudDocumental;
	}

	/**
	 * Modifica el valor de CreacionSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCreacionSolicitud(boolean as_s)
	{
		is_creacionSolicitud = as_s;
	}

	/**
	 * Valida la propiedad creacion solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCreacionSolicitud()
	{
		return is_creacionSolicitud;
	}

	/**
	 * Modifica el valor de DerechoPeticion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDerechoPeticion(String as_s)
	{
		is_derechoPeticion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor derecho peticion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDerechoPeticion()
	{
		return is_derechoPeticion;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Digitalizada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDigitalizada(String as_s)
	{
		is_digitalizada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor digitalizada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDigitalizada()
	{
		return is_digitalizada;
	}

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDocumento(boolean ab_b)
	{
		ib_documento = ab_b;
	}

	/**
	 * Valida la propiedad documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDocumento()
	{
		return ib_documento;
	}

	/**
	 * Modifica el valor de EntidadExenta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEntidadExenta(String as_s)
	{
		is_entidadExenta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor entidad exenta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEntidadExenta()
	{
		return is_entidadExenta;
	}

	/**
	 * Modifica el valor de EsCopias.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsCopias(boolean ab_b)
	{
		ib_esCopias = ab_b;
	}

	/**
	 * Valida la propiedad es copias.
	 *
	 * @return Retorna el valor de la propiedad esCopias
	 */
	public boolean isEsCopias()
	{
		return ib_esCopias;
	}

	/**
	 * Modifica el valor de EstadoSolicitud.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setEstadoSolicitud(Long al_l)
	{
		il_estadoSolicitud = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor estado solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getEstadoSolicitud()
	{
		return il_estadoSolicitud;
	}

	/**
	 * Modifica el valor de FechaCreacionString.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setFechaCreacionString(String as_s)
	{
		is_fechaCreacionString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha creacion string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFechaCreacionString()
	{
		return is_fechaCreacionString;
	}

	/**
	 * Modifica el valor de FechaDocumento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDocumento(Date ad_d)
	{
		id_fechaDocumento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Modifica el valor de FechaRadicadoPQRS.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRadicadoPQRS(Date ad_d)
	{
		id_fechaRadicadoPQRS = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha radicado PQRS.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRadicadoPQRS()
	{
		return id_fechaRadicadoPQRS;
	}

	/**
	 * Modifica el valor de FechaSolicitud.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaSolicitud(Date ad_d)
	{
		id_fechaSolicitud = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaSolicitud()
	{
		return id_fechaSolicitud;
	}

	/**
	 * Modifica el valor de FechaUltimoEstado.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaUltimoEstado(Date ad_d)
	{
		id_fechaUltimoEstado = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha ultimo estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaUltimoEstado()
	{
		return id_fechaUltimoEstado;
	}

	/**
	 * Modifica el valor de IdAutorizacionComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAutorizacionComunicacion(String as_s)
	{
		is_idAutorizacionComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id autorizacion comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAutorizacionComunicacion()
	{
		return is_idAutorizacionComunicacion;
	}

	/**
	 * Modifica el valor de IdAutorizacionNotificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAutorizacionNotificacion(String as_s)
	{
		is_idAutorizacionNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id autorizacion notificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAutorizacionNotificacion()
	{
		return is_idAutorizacionNotificacion;
	}

	/**
	 * Modifica el valor de IdCalidadSolicitante.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCalidadSolicitante(String as_s)
	{
		is_idCalidadSolicitante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id calidad solicitante.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCalidadSolicitante()
	{
		return is_idCalidadSolicitante;
	}

	/**
	 * Modifica el valor de IdCanalOrigen.
	 *
	 * @param a_s de a s
	 */
	public void setIdCanalOrigen(String a_s)
	{
		is_idCanalOrigen = a_s;
	}

	/**
	 * Retorna Objeto o variable de valor id canal origen.
	 *
	 * @return Retorna el valor de la propiedad idCanalOrigen
	 */
	public String getIdCanalOrigen()
	{
		return is_idCanalOrigen;
	}

	/**
	 * Modifica el valor de IdCirculoAnt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoAnt(String as_s)
	{
		is_idCirculoAnt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoAnt()
	{
		return is_idCirculoAnt;
	}

	/**
	 * Modifica el valor de IdCorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCorreoElectronico(String as_s)
	{
		is_idCorreoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCorreoElectronico()
	{
		return is_idCorreoElectronico;
	}

	/**
	 * Modifica el valor de IdCorreoElectronicoComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCorreoElectronicoComunicacion(String as_s)
	{
		is_idCorreoElectronicoComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id correo electronico comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCorreoElectronicoComunicacion()
	{
		return is_idCorreoElectronicoComunicacion;
	}

	/**
	 * Modifica el valor de IdCuentaCupo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id cuenta cupo.
	 *
	 * @return Retorna el valor de la propiedad idCuentaCupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de IdDireccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDireccion(String as_s)
	{
		is_idDireccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccion()
	{
		return is_idDireccion;
	}

	/**
	 * Modifica el valor de IdDireccionComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDireccionComunicacion(String as_s)
	{
		is_idDireccionComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccionComunicacion()
	{
		return is_idDireccionComunicacion;
	}

	/**
	 * Modifica el valor de IdDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de idEntidadExterna.
	 *
	 * @param as_s de as s
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad externa.
	 *
	 * @return el valor de id entidad externa
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de IdEtapaActual.
	 *
	 * @param al_l de al l
	 */
	public void setIdEtapaActual(Long al_l)
	{
		il_idEtapaActual = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa actual.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdEtapaActual()
	{
		return il_idEtapaActual;
	}

	/**
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de IdPersonaEntrega.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersonaEntrega(String as_s)
	{
		is_idPersonaEntrega = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersonaEntrega()
	{
		return is_idPersonaEntrega;
	}

	/**
	 * Modifica el valor de IdProcedencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProcedencia(String as_s)
	{
		is_idProcedencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id procedencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProcedencia()
	{
		return is_idProcedencia;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdProcesoAnt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProcesoAnt(String as_s)
	{
		is_idProcesoAnt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProcesoAnt()
	{
		return is_idProcesoAnt;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSolicitudPrevioCorreccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitudPrevioCorreccion(String as_s)
	{
		is_idSolicitudPrevioCorreccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud previo correccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitudPrevioCorreccion()
	{
		return is_idSolicitudPrevioCorreccion;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdSucursalCanalOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setIdSucursalCanalOrigen(String as_s)
	{
		is_idSucursalCanalOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sucursal canal origen.
	 *
	 * @return Retorna el valor de la propiedad idSucursalCanalOrigen
	 */
	public String getIdSucursalCanalOrigen()
	{
		return is_idSucursalCanalOrigen;
	}

	/**
	 * Modifica el valor de IdTelefono.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTelefono(String as_s)
	{
		is_idTelefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id telefono.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTelefono()
	{
		return is_idTelefono;
	}

	/**
	 * Modifica el valor de IdTelefonoComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTelefonoComunicacion(String as_s)
	{
		is_idTelefonoComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id telefono comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTelefonoComunicacion()
	{
		return is_idTelefonoComunicacion;
	}

	/**
	 * Modifica el valor de IdTestamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTestamento(String as_s)
	{
		is_idTestamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id testamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTestamento()
	{
		return is_idTestamento;
	}

	/**
	 * Modifica el valor de IdTipoRecepcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoRecepcion(String as_s)
	{
		is_idTipoRecepcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo recepcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoRecepcion()
	{
		return is_idTipoRecepcion;
	}

	/**
	 * Modifica el valor de IdTurnoAnt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoAnt(String as_s)
	{
		is_idTurnoAnt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoAnt()
	{
		return is_idTurnoAnt;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de IdentificadorCopiasSE.
	 *
	 * @param al_l de al l
	 */
	public void setIdentificadorCopiasSE(Long al_l)
	{
		il_identificadorCopiasSE = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor identificador copias SE.
	 *
	 * @return Retorna el valor de la propiedad identificadorCopiasSE
	 */
	public Long getIdentificadorCopiasSE()
	{
		return il_identificadorCopiasSE;
	}

	/**
	 * Modifica el valor de IndVinculacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndVinculacion(String as_s)
	{
		is_indVinculacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ind vinculacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndVinculacion()
	{
		return is_indVinculacion;
	}

	/**
	 * Modifica el valor de InfoLiquidacion.
	 *
	 * @param acl_cl asigna el valor a la propiedad
	 */
	public void setInfoLiquidacion(Collection<Liquidacion> acl_cl)
	{
		icl_infoLiquidacion = acl_cl;
	}

	/**
	 * Retorna Objeto o variable de valor info liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Liquidacion> getInfoLiquidacion()
	{
		return icl_infoLiquidacion;
	}

	/**
	 * Modifica el valor de InfoTurnosImpuestosGobernacion.
	 *
	 * @param ac_c de ac c
	 */
	public void setInfoTurnosImpuestosGobernacion(Collection<Turno> ac_c)
	{
		ict_infoTurnosImpuestosGobernacion = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor info turnos impuestos gobernacion.
	 *
	 * @return Retorna el valor de la propiedad infoTurnosImpuestosGobernacion
	 */
	public Collection<Turno> getInfoTurnosImpuestosGobernacion()
	{
		return ict_infoTurnosImpuestosGobernacion;
	}

	/**
	 * Modifica el valor de MayorValor.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMayorValor(boolean ab_b)
	{
		ib_mayorValor = ab_b;
	}

	/**
	 * Valida la propiedad mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMayorValor()
	{
		return ib_mayorValor;
	}

	/**
	 * Modifica el valor de MotivoConsulta.
	 *
	 * @param as_s de as s
	 */
	public void setMotivoConsulta(String as_s)
	{
		is_motivoConsulta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor motivo consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMotivoConsulta()
	{
		return is_motivoConsulta;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s de as s
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NirAsociado.
	 *
	 * @param as_s de as s
	 */
	public void setNirAsociado(String as_s)
	{
		is_nirAsociado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir asociado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNirAsociado()
	{
		return is_nirAsociado;
	}

	/**
	 * Modifica el valor de NombreCalidadSolicitante.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreCalidadSolicitante(String as_s)
	{
		is_nombreCalidadSolicitante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre calidad solicitante.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCalidadSolicitante()
	{
		return is_nombreCalidadSolicitante;
	}

	/**
	 * Modifica el valor de NombreEstadoSolicitud.
	 *
	 * @param as_n asigna el valor a la propiedad
	 */
	public void setNombreEstadoSolicitud(String as_n)
	{
		is_nombreEstadoSolicitud = as_n;
	}

	/**
	 * Retorna Objeto o variable de valor nombre estado solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreEstadoSolicitud()
	{
		return is_nombreEstadoSolicitud;
	}

	/**
	 * Modifica el valor de NuevaSolicitud.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setNuevaSolicitud(boolean ab_b)
	{
		ib_nuevaSolicitud = ab_b;
	}

	/**
	 * Valida la propiedad nueva solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isNuevaSolicitud()
	{
		return ib_nuevaSolicitud;
	}

	/**
	 * Modifica el valor de NumeroCopias.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setNumeroCopias(Long al_l)
	{
		il_numeroCopias = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor numero copias.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getNumeroCopias()
	{
		return il_numeroCopias;
	}

	/**
	 * Modifica el valor de ObservacionesDatosActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesDatosActo(String as_s)
	{
		is_observacionesDatosActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones datos acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesDatosActo()
	{
		return is_observacionesDatosActo;
	}

	/**
	 * Modifica el valor de ObservacionesRestitucion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesRestitucion(String as_s)
	{
		is_observacionesRestitucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones restitucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesRestitucion()
	{
		return is_observacionesRestitucion;
	}

	/**
	 * Modifica el valor de ParticipaInterviniente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setParticipaInterviniente(String as_s)
	{
		is_participaInterviniente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor participa interviniente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getParticipaInterviniente()
	{
		return is_participaInterviniente;
	}

	/**
	 * Modifica el valor de RadicadoPQRS.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRadicadoPQRS(String as_s)
	{
		is_radicadoPQRS = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor radicado PQRS.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRadicadoPQRS()
	{
		return is_radicadoPQRS;
	}

	/**
	 * Modifica el valor de ReciboImpuestos.
	 *
	 * @param ab_b de ab b
	 */
	public void setReciboImpuestos(boolean ab_b)
	{
		ib_reciboImpuestos = ab_b;
	}

	/**
	 * Valida la propiedad recibo impuestos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isReciboImpuestos()
	{
		return ib_reciboImpuestos;
	}

	/**
	 * Modifica el valor de ReferenciaMotivoConsulta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setReferenciaMotivoConsulta(String as_s)
	{
		is_referenciaMotivoConsulta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor referencia motivo consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getReferenciaMotivoConsulta()
	{
		return is_referenciaMotivoConsulta;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param as_s con el valor a asignar
	 */
	public void setReproduccionTestamento(String as_s)
	{
		is_reproduccionTestamento = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getReproduccionTestamento()
	{
		return is_reproduccionTestamento;
	}

	/**
	 * Modifica el valor de TipoRegistroCertificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoRegistroCertificado(String as_s)
	{
		is_tipoRegistroCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo registro certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoRegistroCertificado()
	{
		return is_tipoRegistroCertificado;
	}

	/**
	 * Cambia el valor de tipos documentales restitucion.
	 *
	 * @param amss_mss asigna el valor a la propiedad
	 */
	public void setTiposDocumentalesRestitucion(Map<String, String> amss_mss)
	{
		imss_tiposDocumentalesRestitucion = amss_mss;
	}

	/**
	 * Retorna Objeto o variable de valor tipos documentales restitucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Map<String, String> getTiposDocumentalesRestitucion()
	{
		return imss_tiposDocumentalesRestitucion;
	}

	/**
	 * Modifica el valor de TramiteAsociado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTramiteAsociado(String as_s)
	{
		is_tramiteAsociado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tramite asociado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTramiteAsociado()
	{
		return is_tramiteAsociado;
	}

	/**
	 * Modifica el valor de TurnoReproduccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTurnoReproduccion(String as_s)
	{
		is_turnoReproduccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno reproduccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTurnoReproduccion()
	{
		return is_turnoReproduccion;
	}

	/**
	 * Modifica el valor de UsuarioAnt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuarioAnt(String as_s)
	{
		is_usuarioAnt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuarioAnt()
	{
		return is_usuarioAnt;
	}

	/**
	 * Modifica el valor de UsuarioOrip.
	 *
	 * @param as_s Modifica el valor de la propiedad usuarioOrip
	 */
	public void setUsuarioOrip(String as_s)
	{
		is_usuarioOrip = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario orip.
	 *
	 * @return Retorna el valor de la propiedad usuarioOrip
	 */
	public String getUsuarioOrip()
	{
		return is_usuarioOrip;
	}

	/**
	 * Modifica el valor de ValidarTiposDocObligatorios.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidarTiposDocObligatorios(boolean ab_b)
	{
		ib_validarTiposDocObligatorios = ab_b;
	}

	/**
	 * Valida la propiedad validar tipos doc obligatorios.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidarTiposDocObligatorios()
	{
		return ib_validarTiposDocObligatorios;
	}

	/**
	 * Modifica el valor de ValorRecarga.
	 *
	 * @param abd_bd de abd bd
	 */
	public void setValorRecarga(BigDecimal abd_bd)
	{
		ibd_valorRecarga = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor recarga.
	 *
	 * @return Retorna el valor de la propiedad valorRecarga
	 */
	public BigDecimal getValorRecarga()
	{
		return ibd_valorRecarga;
	}

	/**
	 * Modifica el valor de VersionDocumento.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersionDocumento(Long al_l)
	{
		il_versionDocumento = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersionDocumento()
	{
		return il_versionDocumento;
	}

	/**
	 * Modifica el valor de VersionDocumentoInicial.
	 *
	 * @param ll_l asigna el valor a la propiedad
	 */
	public void setVersionDocumentoInicial(Long ll_l)
	{
		il_versionDocumentoInicial = ll_l;
	}

	/**
	 * Retorna Objeto o variable de valor version documento inicial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersionDocumentoInicial()
	{
		return il_versionDocumentoInicial;
	}

	/**
	 * Modifica el valor de VersionSubProceso.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setVersionSubProceso(Integer ai_i)
	{
		ii_versionSubProceso = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getVersionSubProceso()
	{
		return ii_versionSubProceso;
	}
}
