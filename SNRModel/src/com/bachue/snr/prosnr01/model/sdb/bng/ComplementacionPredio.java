package com.bachue.snr.prosnr01.model.sdb.bng;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;

import java.io.Serializable;



/**
 *
 * Clase modelo que contiene todos los atributos de ComplementacionPredio.java
 * @author garias
 *
 */
public class ComplementacionPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = 5737113135199573309L;
	
	/** Propiedad is complementacion. */
	private String            is_complementacion;
	
	/** Propiedad is id complementacion. */
	private String            is_idComplementacion;
	
	/** Propiedad is id datos ant sistema. */
	private String            is_idDatosAntSistema;
	
	/** Propiedad is tipo complementacion. */
	private String            is_tipoComplementacion;
	
	/** Propiedad ib copiar editar. */
	private boolean           ib_copiarEditar;

	/**
	 * Constructor por defecto.
	 */
	public ComplementacionPredio()
	{
	}

	/**
	 * Constructor que recibe como parametro AccComplementacionPredio.
	 *
	 * @param aacp_acp parametro tipo AccComplementacionPredio
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio
	 */
	public ComplementacionPredio(AccComplementacionPredio aacp_acp)
	{
		if(aacp_acp != null)
		{
			is_complementacion                       = aacp_acp.getComplementacion();
			is_idComplementacion                     = String.valueOf(
				    NumericUtils.getLong(aacp_acp.getIdComplementacion())
				);
			is_tipoComplementacion                   = aacp_acp.getTipoComplementacion();
		}
	}

	/**
	 * Modifica el valor de Complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComplementacion(String as_s)
	{
		this.is_complementacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComplementacion()
	{
		return is_complementacion;
	}

	/**
	 * Modifica el valor de CopiarEditar.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCopiarEditar(boolean ab_b)
	{
		this.ib_copiarEditar = ab_b;
	}

	/**
	 * Valida la propiedad copiar editar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCopiarEditar()
	{
		return ib_copiarEditar;
	}

	/**
	 * Modifica el valor de IdComplementacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdComplementacion(String as_s)
	{
		this.is_idComplementacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComplementacion()
	{
		return is_idComplementacion;
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
	 * Modifica el valor de TipoComplementacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoComplementacion(String as_s)
	{
		this.is_tipoComplementacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoComplementacion()
	{
		return is_tipoComplementacion;
	}

	/**
	 * Valida la complementacion.
	 *
	 * @return verdadero soi es una complementacion valida o falso si la complementacion no es valida
	 */
	public boolean isValidComplementacion()
	{
		return StringUtils.isValidString(getComplementacion());
	}
}
