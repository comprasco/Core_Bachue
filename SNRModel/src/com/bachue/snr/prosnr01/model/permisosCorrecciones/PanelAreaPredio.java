package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelAreaPredio.
 *
 * @author garias
 */
public class PanelAreaPredio implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8504581520743495564L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.AREA_DEL_PREDIO;

	/** Propiedad is copiar. */
	private String is_copiar;

	/** Propiedad is copiar seleccionadas. */
	private String is_copiarSeleccionadas;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is salvedad. */
	private String is_salvedad;

	/** Propiedad ib area construida. */
	private boolean ib_areaConstruida;

	/** Propiedad ib area privada. */
	private boolean ib_areaPrivada;

	/** Propiedad ib area terreno. */
	private boolean ib_areaTerreno;

	/** Propiedad ib coeficiente. */
	private boolean ib_coeficiente;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ib uso predio. */
	private boolean ib_usoPredio;

	/**
	 * Modifica el valor de area construida.
	 *
	 * @param ab_b asigna el valor a la propiedad area construida
	 */
	public void setAreaConstruida(boolean ab_b)
	{
		ib_areaConstruida = ab_b;
	}

	/**
	 * Valida la propiedad area construida.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en area construida
	 */
	public boolean isAreaConstruida()
	{
		return ib_areaConstruida;
	}

	/**
	 * Modifica el valor de area privada.
	 *
	 * @param ab_b asigna el valor a la propiedad area privada
	 */
	public void setAreaPrivada(boolean ab_b)
	{
		ib_areaPrivada = ab_b;
	}

	/**
	 * Valida la propiedad area privada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en area privada
	 */
	public boolean isAreaPrivada()
	{
		return ib_areaPrivada;
	}

	/**
	 * Modifica el valor de area terreno.
	 *
	 * @param ab_b asigna el valor a la propiedad area terreno
	 */
	public void setAreaTerreno(boolean ab_b)
	{
		ib_areaTerreno = ab_b;
	}

	/**
	 * Valida la propiedad area terreno.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en area terreno
	 */
	public boolean isAreaTerreno()
	{
		return ib_areaTerreno;
	}

	/**
	 * Modifica el valor de coeficiente.
	 *
	 * @param ab_b asigna el valor a la propiedad coeficiente
	 */
	public void setCoeficiente(boolean ab_b)
	{
		ib_coeficiente = ab_b;
	}

	/**
	 * Valida la propiedad coeficiente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en coeficiente
	 */
	public boolean isCoeficiente()
	{
		return ib_coeficiente;
	}

	/**
	 * Modifica el valor de copiar.
	 *
	 * @param as_s asigna el valor a la propiedad copiar
	 */
	public void setCopiar(String as_s)
	{
		this.is_copiar = as_s;
	}

	/**
	 * Retorna el valor de copiar.
	 *
	 * @return el valor de copiar
	 */
	public String getCopiar()
	{
		return is_copiar;
	}

	/**
	 * Modifica el valor de copiar seleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad copiar seleccionadas
	 */
	public void setCopiarSeleccionadas(String as_s)
	{
		this.is_copiarSeleccionadas = as_s;
	}

	/**
	 * Retorna el valor de copiar seleccionadas.
	 *
	 * @return el valor de copiar seleccionadas
	 */
	public String getCopiarSeleccionadas()
	{
		return is_copiarSeleccionadas;
	}

	/**
	 * Modifica el valor de justificacion.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna el valor de justificacion.
	 *
	 * @return el valor de justificacion
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * Modifica el valor de salvedad.
	 *
	 * @param as_s asigna el valor a la propiedad salvedad
	 */
	public void setSalvedad(String as_s)
	{
		is_salvedad = as_s;
	}

	/**
	 * Retorna el valor de salvedad.
	 *
	 * @return el valor de salvedad
	 */
	public String getSalvedad()
	{
		return is_salvedad;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de uso predio.
	 *
	 * @param ab_b asigna el valor a la propiedad uso predio
	 */
	public void setUsoPredio(boolean ab_b)
	{
		ib_usoPredio = ab_b;
	}

	/**
	 * Valida la propiedad uso predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en uso predio
	 */
	public boolean isUsoPredio()
	{
		return ib_usoPredio;
	}
}
