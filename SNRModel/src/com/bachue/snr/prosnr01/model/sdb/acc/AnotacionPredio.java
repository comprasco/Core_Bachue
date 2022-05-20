package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_ANOTACION_PREDIO.
 *
 * @author garias
 */
public class AnotacionPredio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 761575623573843163L;

	/** Propiedad ibd orden. */
	private BigDecimal ibd_orden;

	/** Propiedad ibd valor. */
	private BigDecimal ibd_valor;

	/** Propiedad icrc anotaciones A cancelar. */
	private Collection<RegistroCalificacion> icrc_anotacionesACancelar;

	/** Propiedad id fecha radicacion. */
	private Date id_fechaRadicacion;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad il id anotacion. */
	private Long il_idAnotacion;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is anotacion cancelada. */
	private String is_anotacionCancelada;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is dominio naturaleza. */
	private String is_dominioNaturaleza;

	/** Propiedad is especificacion. */
	private String is_especificacion;

	/** Propiedad is grupo naturaleza. */
	private String is_grupoNaturaleza;

	/** Propiedad is id anotacion predio. */
	private String is_idAnotacionPredio;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

	/** Propiedad is id detalle ant sistema. */
	private String is_idDetalleAntSistema;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id estado anotacion. */
	private String is_idEstadoAnotacion;

	/** Propiedad is id estado registro. */
	private String is_idEstadoRegistro;

	/** Propiedad is id naturaleza juridica. */
	private String is_idNaturalezaJuridica;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tipo anotacion. */
	private String is_idTipoAnotacion;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is indicador predio ciudadano. */
	private String is_indicadorPredioCiudadano;

	/** Propiedad is nombre naturaleza. */
	private String is_nombreNaturaleza;

	/** Propiedad is radicacion. */
	private String is_radicacion;

	/** Propiedad is revision calificador. */
	private String is_revisionCalificador;

	/** Propiedad ib bloqueo. */
	private boolean ib_bloqueo;

	/** Propiedad ib correccion. */
	private boolean ib_correccion;

	/** Propiedad ib validar anotacion A cancelar. */
	private boolean ib_validarAnotacionACancelar;

	/** Propiedad ii cantidad aperturar. */
	private int ii_cantidadAperturar;

	/** Propiedad al version documento. */
	private long al_versionDocumento;

	/** Propiedad il id turno historia. */
	private long il_idTurnoHistoria;

	/** Propiedad il version. */
	private long il_version;

	/**
	 * Constructor por defecto.
	 */
	public AnotacionPredio()
	{
	}

	/**
	 * Constructor que recibe por parametro un objeto de Anotacion Predio.
	 *
	 * @param aap_ap objeto anotacion predio
	 */
	public AnotacionPredio(com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio aap_ap)
	{
		if(aap_ap != null)
		{
			setIdCirculo(aap_ap.getIdCirculo());
			setIdMatricula(aap_ap.getIdMatricula());
			setIdAnotacion(aap_ap.getIdAnotacion());
			setComentario(aap_ap.getComentario());
			setFechaRegistro(aap_ap.getFechaRegistro());
			setValor(aap_ap.getValor());
			setIdDocumento(aap_ap.getIdDocumento());
			setIdNaturalezaJuridica(aap_ap.getIdNaturalezaJuridica());
			setVersion(aap_ap.getVersion());
			setIdTipoAnotacion(aap_ap.getIdTipoAnotacion());
			setFechaRadicacion(aap_ap.getFechaRadicacion());
			setRadicacion(aap_ap.getRadicacion());
			setIdEstadoAnotacion(aap_ap.getIdEstadoAnotacion());
			setOrden(aap_ap.getOrden());
			setIdDatosAntSistema(aap_ap.getIdDatosAntSistema());
			setVersionDocumento(aap_ap.getVersionDocumento());
			setAnotacionCancelada(aap_ap.getAnotacionCancelada());
			setDominioNaturaleza(aap_ap.getDominioNaturaleza());
			setEspecificacion(aap_ap.getEspecificacion());
			setIdDetalleAntSistema(aap_ap.getIdDetalleAntSistema());
		}
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de AnotacionCancelada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnotacionCancelada(String as_s)
	{
		is_anotacionCancelada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anotacion cancelada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnotacionCancelada()
	{
		return is_anotacionCancelada;
	}

	/**
	 * Modifica el valor de AnotacionesACancelar.
	 *
	 * @param acrc_crc asigna el valor a la propiedad
	 */
	public void setAnotacionesACancelar(Collection<RegistroCalificacion> acrc_crc)
	{
		icrc_anotacionesACancelar = acrc_crc;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones A cancelar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<RegistroCalificacion> getAnotacionesACancelar()
	{
		return icrc_anotacionesACancelar;
	}

	/**
	 * Modifica el valor de Bloqueo.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setBloqueo(boolean ab_b)
	{
		ib_bloqueo = ab_b;
	}

	/**
	 * Valida la propiedad bloqueo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isBloqueo()
	{
		return ib_bloqueo;
	}

	/**
	 * @param Modifica el valor de la propiedad cantidadAperturar por cantidadAperturar
	 */
	public void setCantidadAperturar(int ai_i)
	{
		ii_cantidadAperturar = ai_i;
	}

	/**
	 * @return Retorna el valor de la propiedad cantidadAperturar
	 */
	public int getCantidadAperturar()
	{
		return ii_cantidadAperturar;
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
	 * Modifica el valor de Correccion.
	 *
	 * @param ab_b de ab b
	 */
	public void setCorreccion(boolean ab_b)
	{
		ib_correccion = ab_b;
	}

	/**
	 * Valida la propiedad correccion.
	 *
	 * @return Retorna el valor de la propiedad correccion
	 */
	public boolean isCorreccion()
	{
		return ib_correccion;
	}

	/**
	 * Modifica el valor de DominioNaturaleza.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDominioNaturaleza(String as_s)
	{
		is_dominioNaturaleza = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor dominio naturaleza.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDominioNaturaleza()
	{
		return is_dominioNaturaleza;
	}

	/**
	 * Modifica el valor de Especificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEspecificacion(String as_s)
	{
		is_especificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor especificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEspecificacion()
	{
		return is_especificacion;
	}

	/**
	 * Modifica el valor de FechaRadicacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRadicacion(Date ad_d)
	{
		id_fechaRadicacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha radicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRadicacion()
	{
		return id_fechaRadicacion;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de GrupoNaturaleza.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setGrupoNaturaleza(String as_s)
	{
		is_grupoNaturaleza = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor grupo naturaleza.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getGrupoNaturaleza()
	{
		return is_grupoNaturaleza;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion(Long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdAnotacionPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAnotacionPredio(String as_s)
	{
		is_idAnotacionPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAnotacionPredio()
	{
		return is_idAnotacionPredio;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
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
	 * Modifica el valor de IdDetalleAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDetalleAntSistema(String as_s)
	{
		is_idDetalleAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id detalle ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDetalleAntSistema()
	{
		return is_idDetalleAntSistema;
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
	 * Modifica el valor de IdEstadoAnotacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoAnotacion(String as_s)
	{
		is_idEstadoAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoAnotacion()
	{
		return is_idEstadoAnotacion;
	}

	/**
	 * Modifica el valor de IdEstadoRegistro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoRegistro(String as_s)
	{
		is_idEstadoRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoRegistro()
	{
		return is_idEstadoRegistro;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdNaturalezaJuridica.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdNaturalezaJuridica(String as_s)
	{
		is_idNaturalezaJuridica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id naturaleza juridica.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNaturalezaJuridica()
	{
		return is_idNaturalezaJuridica;
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
	 * Modifica el valor de IdTipoAnotacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoAnotacion(String as_s)
	{
		is_idTipoAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoAnotacion()
	{
		return is_idTipoAnotacion;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
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
	 * Modifica el valor de IndicadorPredioCiudadano.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicadorPredioCiudadano(String as_s)
	{
		is_indicadorPredioCiudadano = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador predio ciudadano.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicadorPredioCiudadano()
	{
		return is_indicadorPredioCiudadano;
	}

	/**
	 * Modifica el valor de NombreNaturaleza.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreNaturaleza(String as_s)
	{
		is_nombreNaturaleza = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre naturaleza.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreNaturaleza()
	{
		return is_nombreNaturaleza;
	}

	/**
	 * Modifica el valor de Orden.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setOrden(BigDecimal abd_bd)
	{
		ibd_orden = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getOrden()
	{
		return ibd_orden;
	}

	/**
	 * Modifica el valor de Radicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRadicacion(String as_s)
	{
		is_radicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor radicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRadicacion()
	{
		return is_radicacion;
	}

	/**
	 * Modifica el valor de RevisionCalificador.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRevisionCalificador(String as_s)
	{
		is_revisionCalificador = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor revision calificador.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRevisionCalificador()
	{
		return is_revisionCalificador;
	}

	/**
	 * Modifica el valor de ValidarAnotacionACancelar.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidarAnotacionACancelar(boolean ab_b)
	{
		ib_validarAnotacionACancelar = ab_b;
	}

	/**
	 * Valida la propiedad validar anotacion A cancelar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidarAnotacionACancelar()
	{
		return ib_validarAnotacionACancelar;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValor(BigDecimal abd_bd)
	{
		ibd_valor = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValor()
	{
		return ibd_valor;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersion(long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getVersion()
	{
		return il_version;
	}

	/**
	 * Modifica el valor de VersionDocumento.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersionDocumento(long al_l)
	{
		al_versionDocumento = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getVersionDocumento()
	{
		return al_versionDocumento;
	}
}
