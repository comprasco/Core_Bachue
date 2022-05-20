package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCamposCorreccion;

import java.io.Serializable;

import java.util.Map;



/**
 * Class que contiene todos las propiedades PanelAntSistemaSolicitud.
 *
 * @author garias
 */
public class PanelAntSistemaSolicitud implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 478835339571678464L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.MATRICULAS_SEGREGADAS;

	/** Propiedad imsscc checks. */
	private Map<String, SolicitudCamposCorreccion> imsscc_checks;

	/** Propiedad is copiar. */
	private String is_copiar;

	/** Propiedad is copiar seleccionadas. */
	private String is_copiarSeleccionadas;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is salvedad. */
	private String is_salvedad;

	/** Propiedad ib cantidad certificados. */
	private boolean ib_cantidadCertificados;

	/** Propiedad ib circulo registral. */
	private boolean ib_circuloRegistral;

	/** Propiedad ib departamento. */
	private boolean ib_departamento;

	/** Propiedad ib municipio. */
	private boolean ib_municipio;

	/** Propiedad ib nombre predio. */
	private boolean ib_nombrePredio;

	/** Propiedad ib pais. */
	private boolean ib_pais;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ib tipo predio. */
	private boolean ib_tipoPredio;

	/**
	 * Modifica el valor de cantidad certificados.
	 *
	 * @param ab_b asigna el valor a la propiedad cantidad certificados
	 */
	public void setCantidadCertificados(boolean ab_b)
	{
		ib_cantidadCertificados = ab_b;
	}

	/**
	 * Valida la propiedad cantidad certificados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cantidad certificados
	 */
	public boolean isCantidadCertificados()
	{
		return ib_cantidadCertificados;
	}

	/**
	 * Sets the checks.
	 *
	 * @param amsps_mscc de amsps mscc
	 */
	public void setChecks(Map<String, SolicitudCamposCorreccion> amsps_mscc)
	{
		imsscc_checks = amsps_mscc;
	}

	/**
	 * Retorna el valor de checks.
	 *
	 * @return el valor de checks
	 */
	public Map<String, SolicitudCamposCorreccion> getChecks()
	{
		return imsscc_checks;
	}

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
	 * Modifica el valor de nombre predio.
	 *
	 * @param ab_b asigna el valor a la propiedad nombre predio
	 */
	public void setNombrePredio(boolean ab_b)
	{
		ib_nombrePredio = ab_b;
	}

	/**
	 * Valida la propiedad nombre predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nombre predio
	 */
	public boolean isNombrePredio()
	{
		return ib_nombrePredio;
	}

	/**
	 * Modifica el valor de pais.
	 *
	 * @param ab_b asigna el valor a la propiedad pais
	 */
	public void setPais(boolean ab_b)
	{
		ib_pais = ab_b;
	}

	/**
	 * Valida la propiedad pais.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pais
	 */
	public boolean isPais()
	{
		return ib_pais;
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
	 * Modifica el valor de tipo predio.
	 *
	 * @param ab_b asigna el valor a la propiedad tipo predio
	 */
	public void setTipoPredio(boolean ab_b)
	{
		ib_tipoPredio = ab_b;
	}

	/**
	 * Valida la propiedad tipo predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tipo predio
	 */
	public boolean isTipoPredio()
	{
		return ib_tipoPredio;
	}
}
