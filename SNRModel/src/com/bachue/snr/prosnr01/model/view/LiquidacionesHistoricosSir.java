package com.bachue.snr.prosnr01.model.view;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades LiquidacionesHistoricosSir.
 *
 * @author  Sebastian Sanchez
 */
public class LiquidacionesHistoricosSir implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6677104535128824159L;

	/** Propiedad is cuantia. */
	private String is_cuantia;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nombre acto. */
	private String is_nombreActo;

	/** Propiedad is nro radica. */
	private String is_nroRadica;

	/** Propiedad is tipo liq. */
	private String is_tipoLiq;

	/** Propiedad is turno actualizado. */
	private String is_turnoActualizado;

	/** Propiedad is turno actualizado asociado. */
	private String is_turnoActualizadoAsociado;

	/** Propiedad id valor acto. */
	private double id_valorActo;

	/** Propiedad id valor conservacion documental. */
	private double id_valorConservacionDocumental;

	/** Propiedad id valor liquidacion. */
	private double id_valorLiquidacion;

	/**
	 * Retorna Objeto o variable de valor cuantia.
	 *
	 * @return Retorna el valor de la propiedad cuantia
	 */
	public String getCuantia()
	{
		return is_cuantia;
	}

	/**
	 * Modifica el valor de Cuantia.
	 *
	 * @param as_s de as s
	 */
	public void setCuantia(String as_s)
	{
		is_cuantia = as_s;
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
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return Retorna el valor de la propiedad idTipoActo
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoActo(String as_s)
	{
		is_idTipoActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo liq.
	 *
	 * @return Retorna el valor de la propiedad tipoLiq
	 */
	public String getTipoLiq()
	{
		return is_tipoLiq;
	}

	/**
	 * Modifica el valor de TipoLiq.
	 *
	 * @param as_s de as s
	 */
	public void setTipoLiq(String as_s)
	{
		is_tipoLiq = as_s;
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
	 * Retorna Objeto o variable de valor nombre acto.
	 *
	 * @return Retorna el valor de la propiedad nombreActo
	 */
	public String getNombreActo()
	{
		return is_nombreActo;
	}

	/**
	 * Modifica el valor de NombreActo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreActo(String as_s)
	{
		is_nombreActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor valor conservacion documental.
	 *
	 * @return Retorna el valor de la propiedad valorConservacionDocumental
	 */
	public double getValorConservacionDocumental()
	{
		return id_valorConservacionDocumental;
	}

	/**
	 * Modifica el valor de ValorConservacionDocumental.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorConservacionDocumental(double ad_d)
	{
		id_valorConservacionDocumental = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor acto.
	 *
	 * @return Retorna el valor de la propiedad valor acto
	 */
	public double getValorActo()
	{
		return id_valorActo;
	}

	/**
	 * Modifica el valor de Valor Acto.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorActo(double ad_d)
	{
		id_valorActo = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor liquidacion.
	 *
	 * @return Retorna el valor de la propiedad valor liquidacion
	 */
	public double getValorLiquidacion()
	{
		return id_valorLiquidacion;
	}

	/**
	 * Modifica el valor de Valor Liquidacion.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorLiquidacion(double ad_d)
	{
		id_valorLiquidacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return Retorna el valor de la propiedad descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}
}
