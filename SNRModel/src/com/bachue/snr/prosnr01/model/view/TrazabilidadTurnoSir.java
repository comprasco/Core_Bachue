package com.bachue.snr.prosnr01.model.view;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades TrazabilidadTurnoSir.
 *
 * @author  Sebastian Sanchez
 */
public class TrazabilidadTurnoSir implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1168727450541685429L;

	/** Propiedad id fecentra. */
	private Date id_fecentra;

	/** Propiedad is descripcion estado. */
	private String is_descripcionEstado;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is log certi correc. */
	private String is_logCertiCorrec;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nro radica. */
	private String is_nroRadica;

	/** Propiedad is turno actualizado. */
	private String is_turnoActualizado;

	/** Propiedad is turno actualizado asociado. */
	private String is_turnoActualizadoAsociado;

	/** Propiedad is usuario. */
	private String is_usuario;

	/**
	 * Retorna Objeto o variable de valor fecentra.
	 *
	 * @return Retorna el valor de la propiedad fecentra
	 */
	public Date getFecentra()
	{
		return id_fecentra;
	}

	/**
	 * Modifica el valor de Fecentra.
	 *
	 * @param ad_d de ad d
	 */
	public void setFecentra(Date ad_d)
	{
		id_fecentra = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return Retorna el valor de la propiedad nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s de as s
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nro radica.
	 *
	 * @return Retorna el valor de la propiedad nroRadica
	 */
	public String getNroRadica()
	{
		return is_nroRadica;
	}

	/**
	 * Modifica el valor de NroRadica.
	 *
	 * @param as_s de as s
	 */
	public void setNroRadica(String as_s)
	{
		is_nroRadica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno actualizado.
	 *
	 * @return Retorna el valor de la propiedad turnoActualizado
	 */
	public String getTurnoActualizado()
	{
		return is_turnoActualizado;
	}

	/**
	 * Modifica el valor de TurnoActualizado.
	 *
	 * @param as_s de as s
	 */
	public void setTurnoActualizado(String as_s)
	{
		is_turnoActualizado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno actualizado asociado.
	 *
	 * @return Retorna el valor de la propiedad turnoActualizadoAsociado
	 */
	public String getTurnoActualizadoAsociado()
	{
		return is_turnoActualizadoAsociado;
	}

	/**
	 * Modifica el valor de TurnoActualizadoAsociado.
	 *
	 * @param as_s de as s
	 */
	public void setTurnoActualizadoAsociado(String as_s)
	{
		is_turnoActualizadoAsociado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion estado.
	 *
	 * @return Retorna el valor de la propiedad descripcion estado
	 */
	public String getDescripcionEstado()
	{
		return is_descripcionEstado;
	}

	/**
	 * Modifica el valor de Descripcion Estado.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionEstado(String as_s)
	{
		is_descripcionEstado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return Retorna el valor de la propiedad usuario
	 */
	public String getUsuario()
	{
		return is_usuario;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param as_s de as s
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor log certi correc.
	 *
	 * @return Retorna el valor de la propiedad log certi correc
	 */
	public String getLogCertiCorrec()
	{
		return is_logCertiCorrec;
	}

	/**
	 * Modifica el valor de LogCertiCorrec.
	 *
	 * @param as_s de as s
	 */
	public void setLogCertiCorrec(String as_s)
	{
		is_logCertiCorrec = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return Retorna el valor de la propiedad estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s de as s
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}
}
