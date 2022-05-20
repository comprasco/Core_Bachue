package com.bachue.snr.prosnr01.model.numeracion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos usados en la auditoria.
 * @author jvaca
 *
 */
public class NumeracionResolucionCirculo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8821698147982191382L;

	/** Propiedad consecutivo resolucion. */
	private Long il_consecutivoResolucion;

	/** Propiedad activo. */
	private String is_activo;

	/** Propiedad Ano vigente. */
	private String is_anoVigente;

	/** Propiedad is consecutivogenerado. */
	private String is_consecutivogenerado;

	/** Propiedad id Circulo. */
	private String is_idCirculo;

	/**
	 * M�todo de obtenci�n del valor de la propiedad id Circulo.
	 *
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * M�todo de  asignacion del valor de la propiedad id Circulo.
	 *
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad Ano vigente.
	 *
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getAnoVigente()
	{
		return is_anoVigente;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad An o vigente.
	 *
	 * @param as_s con el valor a asignar de tipo String
	 */
	public void setAnoVigente(String as_s)
	{
		is_anoVigente = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad consecutivo resoluci�n.
	 *
	 * @return de Tipo Long con el valor de la propiedad
	 */
	public Long getConsecutivoResolucion()
	{
		return il_consecutivoResolucion;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad consecutivo Resolucion de tipo Long.
	 *
	 * @param al_l con el valor a asignar
	 */
	public void setConsecutivoResolucion(Long al_l)
	{
		il_consecutivoResolucion = al_l;
	}

	/**
	 * M�toodo de obtenci�n del valor de la propiedad Activo.
	 *
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad Activo.
	 *
	 * @param as_s de tipo string con el valor a asignar
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad.
	 *
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getConsecutivogenerado()
	{
		return is_consecutivogenerado;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad.
	 *
	 * @param as_s de as s
	 */
	public void setConsecutivogenerado(String as_s)
	{
		is_consecutivogenerado = as_s;
	}
}
