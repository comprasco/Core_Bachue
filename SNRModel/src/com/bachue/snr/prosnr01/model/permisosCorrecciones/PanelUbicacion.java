package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelUbicacion.
 *
 * @author garias
 */
public class PanelUbicacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5792827048407473299L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.DATOS_BASICOS;

	/** Propiedad is copiar. */
	private String is_copiar;

	/** Propiedad is copiar seleccionadas. */
	private String is_copiarSeleccionadas;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is salvedad. */
	private String is_salvedad;

	/** Propiedad ib circulo registral. */
	private boolean ib_circuloRegistral;

	/** Propiedad ib departamento. */
	private boolean ib_departamento;

	/** Propiedad ib estado predio. */
	private boolean ib_estadoPredio;

	/** Propiedad ib municipio. */
	private boolean ib_municipio;

	/** Propiedad ib nombre circulo registral. */
	private boolean ib_nombreCirculoRegistral;

	/** Propiedad ib nupre. */
	private boolean ib_nupre;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ib vereda. */
	private boolean ib_vereda;

	/**
	 * Modifica el valor de circulo registral.
	 *
	 * @param ab_b asigna el valor a la propiedad circulo registral
	 */
	public void setCirculoRegistral(boolean ab_b)
	{
		ib_circuloRegistral = ab_b;
	}

	/**
	 * Valida la propiedad circulo registral.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en circulo registral
	 */
	public boolean isCirculoRegistral()
	{
		return ib_circuloRegistral;
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
	 * Modifica el valor de departamento.
	 *
	 * @param ab_b asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(boolean ab_b)
	{
		ib_departamento = ab_b;
	}

	/**
	 * Valida la propiedad departamento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en departamento
	 */
	public boolean isDepartamento()
	{
		return ib_departamento;
	}

	/**
	 * Modifica el valor de estado predio.
	 *
	 * @param ab_b asigna el valor a la propiedad estado predio
	 */
	public void setEstadoPredio(boolean ab_b)
	{
		ib_estadoPredio = ab_b;
	}

	/**
	 * Valida la propiedad estado predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en estado predio
	 */
	public boolean isEstadoPredio()
	{
		return ib_estadoPredio;
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
	 * Modifica el valor de municipio.
	 *
	 * @param ab_b asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(boolean ab_b)
	{
		ib_municipio = ab_b;
	}

	/**
	 * Valida la propiedad municipio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en municipio
	 */
	public boolean isMunicipio()
	{
		return ib_municipio;
	}

	/**
	 * Modifica el valor de nombre circulo registral.
	 *
	 * @param ab_b asigna el valor a la propiedad nombre circulo registral
	 */
	public void setNombreCirculoRegistral(boolean ab_b)
	{
		ib_nombreCirculoRegistral = ab_b;
	}

	/**
	 * Valida la propiedad nombre circulo registral.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nombre circulo registral
	 */
	public boolean isNombreCirculoRegistral()
	{
		return ib_nombreCirculoRegistral;
	}

	/**
	 * Modifica el valor de nupre.
	 *
	 * @param ab_b asigna el valor a la propiedad nupre
	 */
	public void setNupre(boolean ab_b)
	{
		ib_nupre = ab_b;
	}

	/**
	 * Valida la propiedad nupre.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nupre
	 */
	public boolean isNupre()
	{
		return ib_nupre;
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
	 * Modifica el valor de vereda.
	 *
	 * @param ab_b asigna el valor a la propiedad vereda
	 */
	public void setVereda(boolean ab_b)
	{
		ib_vereda = ab_b;
	}

	/**
	 * Valida la propiedad vereda.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en vereda
	 */
	public boolean isVereda()
	{
		return ib_vereda;
	}
}
