package com.bachue.snr.prosnr04.model.npa;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase apra el manejo de datos basicos para la creación de una solicitud.
 *
 * @author Manuel Blanco
 */
public class DatosCreacionSolicitud implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 4603212286222692827L;

	/** Propiedad ibd valor recarga. */
	private BigDecimal ibd_valorRecarga;

	/** Propiedad ibd valor recarga. */
	private Long il_identificadorCopiasSE;

	/**  Propiedad is_correoElectronico. */
	private String is_correoElectronico;

	/**  Propiedad is_idCanalOrigen. */
	private String is_idCanalOrigen;

	/** Propiedad is id cuenta cupo. */
	private String is_idCuentaCupo;

	/**  Propiedad is_idPersona. */
	private String is_idPersona;

	/** Propiedad is id sucursal origen. */
	private String is_idSucursalOrigen;

	/**  Propiedad is_telefono. */
	private String is_telefono;

	/**  Propiedad is_tipoServicio. */
	private String is_tipoServicio;

	/**  Propiedad is_tipoSubservicio. */
	private String is_tipoSubservicio;

	/**  Propiedad ib_entidadExenta. */
	private boolean ib_entidadExenta;

	/**
	 * Constructor por defecto.
	 */
	public DatosCreacionSolicitud()
	{
	}

	/**
	 * Constructor para inicializar todas las propiedades del objeto.
	 *
	 * @param as_tipoServicio Objeto contenedor del tipo servicio
	 * @param as_tipoSubservicio Objeto contenedor del tipo subservicio
	 * @param as_idPersona Objeto contenedor del id persona
	 * @param as_idCanalOrigen Objeto contenedor del id canal origen
	 * @param as_idSucursalOrigen de as id sucursal origen
	 * @param as_correoElectronico Objeto contenedor del correo electrónico
	 * @param as_telefono Objeto contenedor del teléfono
	 * @param as_idCuentaCupo Objeto contenedor del id cuenta cupo
	 * @param abd_valorRecarga Objeto contenedor del valor recarga
	 * @param al_identificadorCopiasSE de al identificador copias SE
	 * @param ab_entidadExenta valor para indicar si es entidad exenta o no
	 */
	public DatosCreacionSolicitud(
	    String as_tipoServicio, String as_tipoSubservicio, String as_idPersona, String as_idCanalOrigen,
	    String as_idSucursalOrigen, String as_correoElectronico, String as_telefono, String as_idCuentaCupo,
	    BigDecimal abd_valorRecarga, Long al_identificadorCopiasSE, boolean ab_entidadExenta
	)
	{
		is_correoElectronico         = as_correoElectronico;
		is_idCanalOrigen             = as_idCanalOrigen;
		is_idSucursalOrigen          = as_idSucursalOrigen;
		is_idPersona                 = as_idPersona;
		is_telefono                  = as_telefono;
		is_tipoServicio              = as_tipoServicio;
		is_tipoSubservicio           = as_tipoSubservicio;
		is_idCuentaCupo              = as_idCuentaCupo;
		ibd_valorRecarga             = abd_valorRecarga;
		ib_entidadExenta             = ab_entidadExenta;
		il_identificadorCopiasSE     = al_identificadorCopiasSE;
	}

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de EntidadExenta.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEntidadExenta(boolean ab_b)
	{
		ib_entidadExenta = ab_b;
	}

	/**
	 * Valida la propiedad entidad exenta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isEntidadExenta()
	{
		return ib_entidadExenta;
	}

	/**
	 * Modifica el valor de IdCanalOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCanalOrigen(String as_s)
	{
		is_idCanalOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id canal origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCanalOrigen()
	{
		return is_idCanalOrigen;
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
	 * Modifica el valor de IdSucursalOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setIdSucursalOrigen(String as_s)
	{
		is_idSucursalOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sucursal origen.
	 *
	 * @return Retorna el valor de la propiedad idSucursalOrigen
	 */
	public String getIdSucursalOrigen()
	{
		return is_idSucursalOrigen;
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
	 * Modifica el valor de Telefono.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTelefono(String as_s)
	{
		is_telefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor telefono.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTelefono()
	{
		return is_telefono;
	}

	/**
	 * Modifica el valor de TipoServicio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoServicio(String as_s)
	{
		is_tipoServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoServicio()
	{
		return is_tipoServicio;
	}

	/**
	 * Modifica el valor de TipoSubservicio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoSubservicio(String as_s)
	{
		is_tipoSubservicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo subservicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoSubservicio()
	{
		return is_tipoSubservicio;
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
}
