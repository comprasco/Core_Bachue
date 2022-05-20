package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelDatosDocumento.
 *
 * @author hcastaneda
 */
public class PanelDatosDocumento implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1351879933886788747L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.DATOS_DEL_DOCUMENTO;

	/** Propiedad ib departamento. */
	private boolean ib_departamento;

	/** Propiedad ib fecha documento. */
	private boolean ib_fechaDocumento;

	/** Propiedad ib municipio. */
	private boolean ib_municipio;

	/** Propiedad ib numero documento. */
	private boolean ib_numeroDocumento;

	/** Propiedad ib oficina origen. */
	private boolean ib_oficinaOrigen;

	/** Propiedad ib pais. */
	private boolean ib_pais;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ib tipo documento. */
	private boolean ib_tipoDocumento;

	/** Propiedad ib tipo entidad. */
	private boolean ib_tipoEntidad;

	/** Propiedad ib tipo oficina. */
	private boolean ib_tipoOficina;

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
	 * Modifica el valor de fecha documento.
	 *
	 * @param ab_b asigna el valor a la propiedad fecha documento
	 */
	public void setFechaDocumento(boolean ab_b)
	{
		ib_fechaDocumento = ab_b;
	}

	/**
	 * Valida la propiedad fecha documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en fecha documento
	 */
	public boolean isFechaDocumento()
	{
		return ib_fechaDocumento;
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
	 * Modifica el valor de numero documento.
	 *
	 * @param ab_b asigna el valor a la propiedad numero documento
	 */
	public void setNumeroDocumento(boolean ab_b)
	{
		ib_numeroDocumento = ab_b;
	}

	/**
	 * Valida la propiedad numero documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en numero documento
	 */
	public boolean isNumeroDocumento()
	{
		return ib_numeroDocumento;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param ab_b asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(boolean ab_b)
	{
		ib_oficinaOrigen = ab_b;
	}

	/**
	 * Valida la propiedad oficina origen.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en oficina origen
	 */
	public boolean isOficinaOrigen()
	{
		return ib_oficinaOrigen;
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
	 * Modifica el valor de tipo documento.
	 *
	 * @param ab_b asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(boolean ab_b)
	{
		ib_tipoDocumento = ab_b;
	}

	/**
	 * Valida la propiedad tipo documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tipo documento
	 */
	public boolean isTipoDocumento()
	{
		return ib_tipoDocumento;
	}

	/**
	 * Modifica el valor de tipo entidad.
	 *
	 * @param ab_b asigna el valor a la propiedad tipo entidad
	 */
	public void setTipoEntidad(boolean ab_b)
	{
		ib_tipoEntidad = ab_b;
	}

	/**
	 * Valida la propiedad tipo entidad.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tipo entidad
	 */
	public boolean isTipoEntidad()
	{
		return ib_tipoEntidad;
	}

	/**
	 * Modifica el valor de tipo oficina.
	 *
	 * @param ab_b asigna el valor a la propiedad tipo oficina
	 */
	public void setTipoOficina(boolean ab_b)
	{
		ib_tipoOficina = ab_b;
	}

	/**
	 * Valida la propiedad tipo oficina.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tipo oficina
	 */
	public boolean isTipoOficina()
	{
		return ib_tipoOficina;
	}
}
