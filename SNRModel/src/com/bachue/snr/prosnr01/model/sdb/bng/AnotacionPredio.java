package com.bachue.snr.prosnr01.model.sdb.bng;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_ANOTACION_PREDIO.
 *
 * @author hcastaneda
 */
public class AnotacionPredio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 761575623573843163L;

	/** Propiedad ibd orden. */
	private BigDecimal ibd_orden;

	/** Propiedad ibd valor. */
	private BigDecimal ibd_valor;

	/** Propiedad id fecha radicacion. */
	private Date id_fechaRadicacion;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad il id anotacion. */
	private Long il_idAnotacion;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad is anotacion cancelada. */
	private String is_anotacionCancelada;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is dominio naturaleza. */
	private String is_dominioNaturaleza;

	/** Propiedad is especificacion. */
	private String is_especificacion;

	/** Propiedad is estado anotacion. */
	private String is_estadoAnotacion;

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

	/** Propiedad is id naturaleza juridica. */
	private String is_idNaturalezaJuridica;

	/** Propiedad is id tipo anotacion. */
	private String is_idTipoAnotacion;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is nombre naturaleza. */
	private String is_nombreNaturaleza;

	/** Propiedad is radicacion. */
	private String is_radicacion;

	/** Propiedad il version. */
	private long il_version;

	/** Propiedad il version documento. */
	private long il_versionDocumento;

	/**
	 * Constructor por defecto.
	 */
	public AnotacionPredio()
	{
	}

	/**
	 * Instancia un nuevo objeto anotacion predio.
	 *
	 * @param aac_ac Argumento de tipo <code>AnotacionCatastro</code> que contiene la información básica de la anotación de catastro.
	 */
	public AnotacionPredio(AnotacionCatastro aac_ac)
	{
		setIdCirculo(aac_ac.getIdCirculo());
		setIdMatricula(NumericUtils.getLongWrapper(aac_ac.getIdMatricula()));
		setIdNaturalezaJuridica(aac_ac.getCodNaturalezaJuridicaFolio());
		setVersion(aac_ac.getVersionCodNaturalezaJuridicaFolio());
	}

	/**
	 * Constructor que recibe como parametro un objeto del mismo tipo.
	 *
	 * @param aap_ap de aap ap
	 */
	public AnotacionPredio(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_ap)
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
	 * Constructor que recibe como parametro id circulo y id matricula.
	 *
	 * @param as_idCirculo id circulo
	 * @param al_idMatricula id matricula
	 */
	public AnotacionPredio(String as_idCirculo, Long al_idMatricula)
	{
		setIdCirculo(as_idCirculo);
		setIdMatricula(al_idMatricula);
	}

	/**
	 * Constructor que recibe como parametro id circulo, id matricula y id anotacion.
	 *
	 * @param as_idCirculo id circulo
	 * @param al_idMatricula id matricula
	 * @param al_idAnotacion id anotacion
	 */
	public AnotacionPredio(String as_idCirculo, Long al_idMatricula, Long al_idAnotacion)
	{
		setIdCirculo(as_idCirculo);
		setIdMatricula(al_idMatricula);
		setIdAnotacion(al_idAnotacion);
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
	 * Modifica el valor de EstadoAnotacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoAnotacion(String as_s)
	{
		is_estadoAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoAnotacion()
	{
		return is_estadoAnotacion;
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
	 * @param as_idAnotacionPredio de as id anotacion predio
	 */
	public void setIdAnotacionPredio(String as_idAnotacionPredio)
	{
		is_idAnotacionPredio = as_idAnotacionPredio;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion predio.
	 *
	 * @return Retorna el valor de la propiedad is_idAnotacionPredio
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
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 */
	public void setIdTurnoHistoria(String as_idTurnoHistoria)
	{
		is_idTurnoHistoria = as_idTurnoHistoria;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return Retorna el valor de la propiedad is_idTurnoHistoria
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
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
		il_versionDocumento = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getVersionDocumento()
	{
		return il_versionDocumento;
	}
}
