package com.bachue.snr.prosnr01.web.util;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades de ColumnModel para el manejo de archivos en excel
 *
 * @author Julian Vaca
 */
public class ColumnModel implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 9032350101231817972L;

	/** Propiedad ib property. */
	private Boolean ib_property;

	/** Propiedad select all. */
	private Boolean selectAll;

	/** Propiedad is cuantia. */
	private String is_cuantia;

	/** Propiedad is header. */
	private String is_header;

	/** Propiedad is id acto. */
	private String is_idActo;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is name. */
	private String is_name;

	/** Propiedad ib deshabilitar. */
	private boolean ib_deshabilitar;

	/** Propiedad ib validar area. */
	private boolean ib_validarArea;

	/**
	 * Instancia un nuevo objeto column model.
	 *
	 * @param as_header correspondiente al valor del tipo de objeto String
	 * @param ab_property correspondiente al valor del tipo de objeto Boolean
	 */
	public ColumnModel(String as_header, Boolean ab_property)
	{
		is_header       = as_header;
		ib_property     = ab_property;
		selectAll       = new Boolean(false);
	}

	/**
	 * Instancia un nuevo objeto column model.
	 *
	 * @param as_header correspondiente al valor del tipo de objeto String
	 * @param ab_property correspondiente al valor del tipo de objeto Boolean
	 * @param as_name correspondiente al valor del tipo de objeto String
	 * @param as_cuantia correspondiente al valor del tipo de objeto String
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public ColumnModel(
	    String as_header, Boolean ab_property, String as_name, String as_cuantia, String as_idActo,
	    String as_idSolicitud
	)
	{
		is_header          = as_header;
		ib_property        = ab_property;
		is_name            = as_name;
		is_cuantia         = as_cuantia;
		is_idActo          = as_idActo;
		selectAll          = new Boolean(false);
		is_idSolicitud     = as_idSolicitud;
	}

	/**
	 * Instancia un nuevo objeto column model.
	 *
	 * @param as_header correspondiente al valor del tipo de objeto String
	 * @param ab_property correspondiente al valor del tipo de objeto Boolean
	 * @param as_name correspondiente al valor del tipo de objeto String
	 * @param as_cuantia correspondiente al valor del tipo de objeto String
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param ab_validarArea correspondiente al valor del tipo de objeto boolean
	 */
	public ColumnModel(
	    String as_header, Boolean ab_property, String as_name, String as_cuantia, String as_idActo,
	    boolean ab_validarArea
	)
	{
		is_header          = as_header;
		ib_property        = ab_property;
		is_name            = as_name;
		is_cuantia         = as_cuantia;
		is_idActo          = as_idActo;
		ib_validarArea     = ab_validarArea;
		selectAll          = new Boolean(false);
	}

	/**
	 * Instancia un nuevo objeto column model.
	 *
	 * @param as_header correspondiente al valor del tipo de objeto String
	 * @param ab_property correspondiente al valor del tipo de objeto Boolean
	 * @param as_name correspondiente al valor del tipo de objeto String
	 * @param as_cuantia correspondiente al valor del tipo de objeto String
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param ab_validarArea correspondiente al valor del tipo de objeto boolean
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param ab_deshabilitar correspondiente al valor del tipo de objeto boolean
	 */
	public ColumnModel(
	    String as_header, Boolean ab_property, String as_name, String as_cuantia, String as_idActo,
	    boolean ab_validarArea, String as_idCirculo, boolean ab_deshabilitar
	)
	{
		is_header           = as_header;
		ib_property         = ab_property;
		is_name             = as_name;
		is_cuantia          = as_cuantia;
		is_idActo           = as_idActo;
		ib_validarArea      = ab_validarArea;
		is_idCirculo        = as_idCirculo;
		selectAll           = new Boolean(false);
		ib_deshabilitar     = ab_deshabilitar;
	}

	/**
	 * Modifica el valor de cuantia.
	 *
	 * @param as_s asigna el valor a la propiedad cuantia
	 */
	public void setCuantia(String as_s)
	{
		is_cuantia = as_s;
	}

	/**
	 * Retorna el valor de cuantia.
	 *
	 * @return el valor de cuantia
	 */
	public String getCuantia()
	{
		return is_cuantia;
	}

	/**
	 * Modifica el valor de deshabilitar.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar
	 */
	public void setDeshabilitar(boolean ab_b)
	{
		ib_deshabilitar = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar
	 */
	public boolean isDeshabilitar()
	{
		return ib_deshabilitar;
	}

	/**
	 * Modifica el valor de header.
	 *
	 * @param as_s asigna el valor a la propiedad header
	 */
	public void setHeader(String as_s)
	{
		is_header = as_s;
	}

	/**
	 * Retorna el valor de header.
	 *
	 * @return el valor de header
	 */
	public String getHeader()
	{
		return is_header;
	}

	/**
	 * Modifica el valor de id acto.
	 *
	 * @param as_s asigna el valor a la propiedad id acto
	 */
	public void setIdActo(String as_s)
	{
		is_idActo = as_s;
	}

	/**
	 * Retorna el valor de id acto.
	 *
	 * @return el valor de id acto
	 */
	public String getIdActo()
	{
		return is_idActo;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de name.
	 *
	 * @param as_s asigna el valor a la propiedad name
	 */
	public void setName(String as_s)
	{
		is_name = as_s;
	}

	/**
	 * Retorna el valor de name.
	 *
	 * @return el valor de name
	 */
	public String getName()
	{
		return is_name;
	}

	/**
	 * Modifica el valor de property.
	 *
	 * @param ab_b asigna el valor a la propiedad property
	 */
	public void setProperty(Boolean ab_b)
	{
		ib_property = ab_b;
	}

	/**
	 * Retorna el valor de property.
	 *
	 * @return el valor de property
	 */
	public Boolean getProperty()
	{
		return ib_property;
	}

	/**
	 * Modifica el valor de select all.
	 *
	 * @param ab_b asigna el valor a la propiedad select all
	 */
	public void setSelectAll(Boolean ab_b)
	{
		selectAll = ab_b;
	}

	/**
	 * Retorna el valor de select all.
	 *
	 * @return el valor de select all
	 */
	public Boolean getSelectAll()
	{
		return selectAll;
	}

	/**
	 * Modifica el valor de validar area.
	 *
	 * @param ab_b asigna el valor a la propiedad validar area
	 */
	public void setValidarArea(boolean ab_b)
	{
		ib_validarArea = ab_b;
	}

	/**
	 * Valida la propiedad validar area.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validar area
	 */
	public boolean isValidarArea()
	{
		return ib_validarArea;
	}
}
