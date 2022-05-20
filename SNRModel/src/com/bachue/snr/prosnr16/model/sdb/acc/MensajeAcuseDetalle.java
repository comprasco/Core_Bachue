package com.bachue.snr.prosnr16.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Abstraccion de tabla SDB_ACC_MENSAJE_ACUSE_DETALLE.
 *
 * @author Sebastian Sanchez
 */
public class MensajeAcuseDetalle extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5700178196239538338L;

	/** Propiedad id fecha acuse. */
	private Date id_fechaAcuse;

	/** Propiedad id fecha envio correspondencia. */
	private Date id_fechaEnvioCorrespondencia;

	/** Propiedad is descripcion acuse fallido. */
	private String is_descripcionAcuseFallido;

	/** Propiedad is guia correspondencia fisica. */
	private String is_guiaCorrespondenciaFisica;

	/** Propiedad is id acuse detalle. */
	private String is_idAcuseDetalle;

	/** Propiedad is id mensaje. */
	private String is_idMensaje;

	/**
	 * Constructor por defecto.
	 */
	public MensajeAcuseDetalle()
	{
	}

	/**
	 * Modifica el valor de DescripcionAcuseFallido.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionAcuseFallido(String as_s)
	{
		is_descripcionAcuseFallido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion acuse fallido.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionAcuseFallido()
	{
		return is_descripcionAcuseFallido;
	}

	/**
	 * Modifica el valor de FechaAcuseDetalle.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaAcuseDetalle(Date ad_d)
	{
		id_fechaAcuse = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha acuse detalle.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaAcuseDetalle()
	{
		return id_fechaAcuse;
	}

	/**
	 * Modifica el valor de FechaEnvioCorrespondencia.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaEnvioCorrespondencia(Date ad_d)
	{
		id_fechaEnvioCorrespondencia = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha envio correspondencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaEnvioCorrespondencia()
	{
		return id_fechaEnvioCorrespondencia;
	}

	/**
	 * Modifica el valor de GuiaCorrespondenciaFisica.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setGuiaCorrespondenciaFisica(String as_s)
	{
		is_guiaCorrespondenciaFisica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor guia correspondencia fisica.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getGuiaCorrespondenciaFisica()
	{
		return is_guiaCorrespondenciaFisica;
	}

	/**
	 * Modifica el valor de IdAcuseDetalle.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAcuseDetalle(String as_s)
	{
		is_idAcuseDetalle = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id acuse detalle.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAcuseDetalle()
	{
		return is_idAcuseDetalle;
	}

	/**
	 * Modifica el valor de IdMensaje.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMensaje(String as_s)
	{
		is_idMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMensaje()
	{
		return is_idMensaje;
	}
}
