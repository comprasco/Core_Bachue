package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_DIRECCION_PREDIO.
 *
 * @author asantos
 */
public class AnotacionPredioDireccion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID             = 1844080333604635923L;
	
	/** Propiedad ibd orden. */
	private BigDecimal        ibd_orden;
	
	/** Propiedad ibd valor. */
	private BigDecimal        ibd_valor;
	
	/** Propiedad id fecha radicacion. */
	private Date              id_fechaRadicacion;
	
	/** Propiedad id fecha registro. */
	private Date              id_fechaRegistro;
	
	/** Propiedad ii cantidad copias reproducir. */
	private Integer           ii_cantidadCopiasReproducir;
	
	/** Propiedad is anotaciones. */
	private String            is_anotaciones;
	
	/** Propiedad is comentario. */
	private String            is_comentario;
	
	/** Propiedad is direccion predio. */
	private String            is_direccionPredio;
	
	/** Propiedad is especificacion. */
	private String            is_especificacion;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id datos ant sistema. */
	private String            is_idDatosAntSistema;
	
	/** Propiedad is id documento. */
	private String            is_idDocumento;
	
	/** Propiedad is id estado anotacion. */
	private String            is_idEstadoAnotacion;
	
	/** Propiedad is id naturaleza juridica. */
	private String            is_idNaturalezaJuridica;
	
	/** Propiedad is id tipo anotacion. */
	private String            is_idTipoAnotacion;
	
	/** Propiedad is id usuario. */
	private String            is_idUsuario;
	
	/** Propiedad is radicacion. */
	private String            is_radicacion;
	
	/** Propiedad is testamento. */
	private Testamento        is_testamento;
	
	/** Propiedad ib bloquear seleccionado. */
	private boolean           ib_bloquearSeleccionado;
	
	/** Propiedad ib seleccionado. */
	private boolean           ib_seleccionado;
	
	/** Propiedad ib seleccionado rep constancia. */
	private boolean           ib_seleccionadoRepConstancia;
	
	/** Propiedad il id anotacion. */
	private long              il_idAnotacion;
	
	/** Propiedad il id matricula. */
	private long              il_idMatricula;
	
	/** Propiedad il version. */
	private long              il_version;
	
	/** Propiedad il version documento. */
	private long              il_versionDocumento;

	/**
	 * Modifica el valor de Anotaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnotaciones(String as_s)
	{
		this.is_anotaciones                                = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnotaciones()
	{
		return is_anotaciones;
	}

	/**
	 * Modifica el valor de BloquearSeleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setBloquearSeleccionado(boolean ab_b)
	{
		this.ib_bloquearSeleccionado = ab_b;
	}

	/**
	 * Valida la propiedad bloquear seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isBloquearSeleccionado()
	{
		return ib_bloquearSeleccionado;
	}

	/**
	 * Modifica el valor de CantidadCopiasReproducir.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setCantidadCopiasReproducir(Integer ai_i)
	{
		this.ii_cantidadCopiasReproducir = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad copias reproducir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getCantidadCopiasReproducir()
	{
		return ii_cantidadCopiasReproducir;
	}

	/**
	 * Modifica el valor de Comentario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComentario(String as_s)
	{
		this.is_comentario = as_s;
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
	 * Modifica el valor de DireccionPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDireccionPredio(String as_s)
	{
		this.is_direccionPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDireccionPredio()
	{
		return is_direccionPredio;
	}

	/**
	 * Modifica el valor de Especificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEspecificacion(String as_s)
	{
		this.is_especificacion = as_s;
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
		this.id_fechaRadicacion = ad_d;
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
		this.id_fechaRegistro = ad_d;
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
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion(long al_l)
	{
		this.il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		this.is_idCirculo = as_s;
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
		this.is_idDatosAntSistema = as_s;
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
	 * Modifica el valor de IdDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumento(String as_s)
	{
		this.is_idDocumento = as_s;
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
		this.is_idEstadoAnotacion = as_s;
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
	public void setIdMatricula(long al_l)
	{
		this.il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
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
		this.is_idNaturalezaJuridica = as_s;
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
		this.is_idTipoAnotacion = as_s;
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
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		this.is_idUsuario = as_s;
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
	 * Modifica el valor de Orden.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setOrden(BigDecimal abd_bd)
	{
		this.ibd_orden = abd_bd;
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
		this.is_radicacion = as_s;
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
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		this.ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de SeleccionadoRepConstancia.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionadoRepConstancia(boolean ab_b)
	{
		this.ib_seleccionadoRepConstancia = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado rep constancia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionadoRepConstancia()
	{
		return ib_seleccionadoRepConstancia;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValor(BigDecimal abd_bd)
	{
		this.ibd_valor = abd_bd;
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
		this.il_version = al_l;
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
		this.il_versionDocumento = al_l;
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

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Testamento getTestamento()
	{
		return is_testamento;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param as_s con el valor a asignar
	 */
	public void setTestamento(Testamento as_s)
	{
		is_testamento = as_s;
	}
}
