package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ComplementacionCalificacion.
 *
 * @author garias
 */
public class ComplementacionCalificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4833619192635009352L;

	/** Propiedad ic complementacion. */
	private Complementacion ic_complementacion;

	/** Propiedad icp complementacion predio. */
	private ComplementacionPredio icp_complementacionPredio;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is tipo complementacion. */
	private String is_tipoComplementacion;

	/** Propiedad ib copiar editar. */
	private boolean ib_copiarEditar;

	/**
	 * Modifica el valor de complementacion.
	 *
	 * @param complementacion asigna el valor a la propiedad complementacion
	 */
	public void setComplementacion(Complementacion complementacion)
	{
		ic_complementacion = complementacion;
	}

	/**
	 * Retorna el valor de complementacion.
	 *
	 * @return el valor de complementacion
	 */
	public Complementacion getComplementacion()
	{
		if(ic_complementacion == null)
			ic_complementacion = new Complementacion();

		return ic_complementacion;
	}

	/**
	 * Modifica el valor de complementacion predio.
	 *
	 * @param complementacionPredio asigna el valor a la propiedad complementacion predio
	 */
	public void setComplementacionPredio(ComplementacionPredio complementacionPredio)
	{
		icp_complementacionPredio = complementacionPredio;
	}

	/**
	 * Retorna el valor de complementacion predio.
	 *
	 * @return el valor de complementacion predio
	 */
	public ComplementacionPredio getComplementacionPredio()
	{
		if(icp_complementacionPredio == null)
			icp_complementacionPredio = new ComplementacionPredio();

		return icp_complementacionPredio;
	}

	/**
	 * Modifica el valor de copiar editar.
	 *
	 * @param copiarEditar asigna el valor a la propiedad copiar editar
	 */
	public void setCopiarEditar(boolean copiarEditar)
	{
		ib_copiarEditar = copiarEditar;
	}

	/**
	 * Valida la propiedad copiar editar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en copiar editar
	 */
	public boolean isCopiarEditar()
	{
		return ib_copiarEditar;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de tipo complementacion.
	 *
	 * @param tipoComplementacion asigna el valor a la propiedad tipo complementacion
	 */
	public void setTipoComplementacion(String tipoComplementacion)
	{
		is_tipoComplementacion = tipoComplementacion;
	}

	/**
	 * Retorna el valor de tipo complementacion.
	 *
	 * @return el valor de tipo complementacion
	 */
	public String getTipoComplementacion()
	{
		return is_tipoComplementacion;
	}
}
