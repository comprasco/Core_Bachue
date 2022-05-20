package com.bachue.snr.prosnr01.model.sdb.pgn;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Logica de modelo Circulo Registral siendo una abstracción de la tabla SDB_PGN_CIRCULO_REGISTRAL en la base de datos.
 *
 * @author Heiner Castañeda
 */
public class CirculoRegistral extends OficinaOrigen implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7704699860425976029L;

	/** Propiedad ibd tope reparto calificador. */
	private BigDecimal ibd_topeRepartoCalificador;

	/** Propiedad ibd ultimo id matricula. */
	private BigDecimal ibd_ultimoIdMatricula;

	/** Propiedad ibd version. */
	private BigDecimal ibd_version;

	/** Propiedad id fecha produccion. */
	private Date id_fechaProduccion;

	/** Propiedad id fecha produccion bachue. */
	private Date id_fechaProduccionBachue;

	/** Propiedad il orden. */
	private Long il_orden;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is cobra impuesto. */
	private String is_cobraImpuesto;

	/** Propiedad is codigo nombre. */
	private String is_codigoNombre;

	/** Propiedad is habilitado. */
	private String is_habilitado;

	/** Propiedad is horario atencion. */
	private String is_horarioAtencion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id gobernacion. */
	private String is_idGobernacion;

	/** Propiedad is id oficina origen. */
	private String is_idOficinaOrigen;

	/** Propiedad is id regional. */
	private String is_idRegional;

	/** Propiedad is nit. */
	private String is_nit;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nombre circulo registral. */
	private String is_nombreCirculoRegistral;

	/** Propiedad is nombre oficina origen. */
	private String is_nombreOficinaOrigen;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is sistema origen. */
	private String is_sistemaOrigen;

	/** Propiedad is tipo oficina. */
	private String is_tipoOficina;

	/**
	 * Constructor por defecto.
	 */
	public CirculoRegistral()
	{
	}

	/**
	 * Constructor que recibe como parametro sin informacion.
	 *
	 * @param as_sinInformacion de as sin informacion
	 */
	public CirculoRegistral(String as_sinInformacion)
	{
		setNombre(as_sinInformacion);
	}

	/**
	 * Constructor que recibe como parametro sin informacion y id circulo.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param as_sinInformacion de as sin informacion
	 */
	public CirculoRegistral(String as_idCirculo, String as_sinInformacion)
	{
		setIdCirculo(as_idCirculo);
		setNombre(as_sinInformacion);
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo = as_s;
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
	 * Modifica el valor de CobraImpuesto.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCobraImpuesto(String as_s)
	{
		this.is_cobraImpuesto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cobra impuesto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCobraImpuesto()
	{
		return is_cobraImpuesto;
	}

	/**
	 * Modifica el valor de CodigoNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoNombre(String as_s)
	{
		this.is_codigoNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoNombre()
	{
		return is_codigoNombre;
	}

	/**
	 * Modifica el valor de FechaProduccion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaProduccion(Date ad_d)
	{
		this.id_fechaProduccion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha produccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaProduccion()
	{
		return id_fechaProduccion;
	}

	/**
	 * Modifica el valor de FechaProduccionBachue.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaProduccionBachue(Date ad_d)
	{
		this.id_fechaProduccionBachue = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha produccion bachue.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaProduccionBachue()
	{
		return id_fechaProduccionBachue;
	}

	/**
	 * Modifica el valor de Habilitado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHabilitado(String as_s)
	{
		this.is_habilitado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor habilitado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabilitado()
	{
		return is_habilitado;
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
	 * Modifica el valor de IdGobernacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdGobernacion(String as_s)
	{
		this.is_idGobernacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id gobernacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdGobernacion()
	{
		return is_idGobernacion;
	}

	/**
	 * Modifica el valor de IdOficinaOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdOficinaOrigen(String as_s)
	{
		this.is_idOficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdOficinaOrigen()
	{
		return is_idOficinaOrigen;
	}

	/**
	 * Modifica el valor de IdRegional.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdRegional(String as_s)
	{
		this.is_idRegional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id regional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRegional()
	{
		return is_idRegional;
	}

	/**
	 * Modifica el valor de Nit.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNit(String as_s)
	{
		this.is_nit = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nit.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNit()
	{
		return is_nit;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de NombreCirculoRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreCirculoRegistral(String as_s)
	{
		this.is_nombreCirculoRegistral = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCirculoRegistral()
	{
		return is_nombreCirculoRegistral;
	}

	/**
	 * Modifica el valor de NombreOficinaOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreOficinaOrigen(String as_s)
	{
		this.is_nombreOficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreOficinaOrigen()
	{
		return is_nombreOficinaOrigen;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		this.is_observaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de Orden.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setOrden(Long al_l)
	{
		this.il_orden = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getOrden()
	{
		return il_orden;
	}

	/**
	 * Modifica el valor de SistemaOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSistemaOrigen(String as_s)
	{
		this.is_sistemaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor sistema origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSistemaOrigen()
	{
		return is_sistemaOrigen;
	}

	/**
	 * Modifica el valor de TipoOficina.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoOficina(String as_s)
	{
		this.is_tipoOficina = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo oficina.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoOficina()
	{
		return is_tipoOficina;
	}

	/**
	 * Modifica el valor de TopeRepartoCalificador.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setTopeRepartoCalificador(BigDecimal abd_bd)
	{
		this.ibd_topeRepartoCalificador = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor tope reparto calificador.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getTopeRepartoCalificador()
	{
		return ibd_topeRepartoCalificador;
	}

	/**
	 * Modifica el valor de UltimoIdMatricula.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setUltimoIdMatricula(BigDecimal abd_bd)
	{
		this.ibd_ultimoIdMatricula = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor ultimo id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getUltimoIdMatricula()
	{
		return ibd_ultimoIdMatricula;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setVersion(BigDecimal abd_bd)
	{
		this.ibd_version = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getVersion()
	{
		return ibd_version;
	}

	/**
	 * Retorna Objeto o variable de valor horario atencion.
	 *
	 * @return Retorna el valor de la propiedad horarioAtencion
	 */
	public String getHorarioAtencion()
	{
		return is_horarioAtencion;
	}

	/**
	 * Modifica el valor de HorarioAtencion.
	 *
	 * @param as_s de as s
	 */
	public void setHorarioAtencion(String as_s)
	{
		is_horarioAtencion = as_s;
	}
}
