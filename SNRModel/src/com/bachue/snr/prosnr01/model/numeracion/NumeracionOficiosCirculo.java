package com.bachue.snr.prosnr01.model.numeracion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos usados en la auditoria.
 * @author jvaca
 *
 */
public class NumeracionOficiosCirculo extends Auditoria implements Serializable
{
	
	/**  * Propiedad serial. */
	private static final long serialVersionUID = -1245629258847658035L;

	/** Propiedad consecutivo resolucion. */
	private Long il_consecutivoOficio;

	/** Propiedad activo. */
	private String is_activo;

	/** Propiedad Ano vigente. */
	private String is_anoVigente;

	/** Propiedad is consecutivo generado. */
	private String is_consecutivoGenerado;

	/** Propiedad id Circulo. */
	private String is_idCirculo;

	/** Propiedad id Dependencia. */
	private String is_idDependencia;

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
	public Long getConsecutivoOficio()
	{
		return il_consecutivoOficio;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad consecutivo Resolucion de tipo Long.
	 *
	 * @param al_l con el valor a asignar
	 */
	public void setConsecutivoOficio(Long al_l)
	{
		il_consecutivoOficio = al_l;
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
	 * M�todo de obtenci�n del valor de la propiedad id Dependencia.
	 *
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getIdDependencia()
	{
		return is_idDependencia;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad id Dependencia.
	 *
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setIdDependencia(String as_s)
	{
		is_idDependencia = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad consecutivo generado.
	 *
	 * @return el valor de la propiedad de tipo String
	 */
	public String getConsecutivoGenerado()
	{
		return is_consecutivoGenerado;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad Consecutivo generado.
	 *
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setConsecutivoGenerado(String as_s)
	{
		is_consecutivoGenerado = as_s;
	}
}
