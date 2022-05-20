package com.bachue.snr.prosnr04.business.operacionesFinancieras;


/**
 * Clase que contiene todos las propiedades se servicio de generar la liquidacion
 *
 * @author Julian Vaca
 */
public class ServicioGenerarLiquidacion
{
	/** Propiedad is campo. */
	private String is_campo;

	/** Propiedad is criterio. */
	private String is_criterio;

	/** Propiedad is servicio. */
	private String is_servicio;

	/** Propiedad is subservicio. */
	private String is_subservicio;

	/** Propiedad is tabla. */
	private String is_tabla;

	/**
	 * Modifica el valor de campo.
	 *
	 * @param as_s asigna el valor a la propiedad campo
	 */
	public void setCampo(String as_s)
	{
		is_campo = as_s;
	}

	/**
	 * Retorna el valor de campo.
	 *
	 * @return el valor de campo
	 */
	public String getCampo()
	{
		return is_campo;
	}

	/**
	 * Modifica el valor de criterio.
	 *
	 * @param as_s asigna el valor a la propiedad criterio
	 */
	public void setCriterio(String as_s)
	{
		is_criterio = as_s;
	}

	/**
	 * Retorna el valor de criterio.
	 *
	 * @return el valor de criterio
	 */
	public String getCriterio()
	{
		return is_criterio;
	}

	/**
	 * Modifica el valor de servicio.
	 *
	 * @param as_s asigna el valor a la propiedad servicio
	 */
	public void setServicio(String as_s)
	{
		is_servicio = as_s;
	}

	/**
	 * Retorna el valor de servicio.
	 *
	 * @return el valor de servicio
	 */
	public String getServicio()
	{
		return is_servicio;
	}

	/**
	 * Modifica el valor de subservicio.
	 *
	 * @param as_s asigna el valor a la propiedad subservicio
	 */
	public void setSubservicio(String as_s)
	{
		is_subservicio = as_s;
	}

	/**
	 * Retorna el valor de subservicio.
	 *
	 * @return el valor de subservicio
	 */
	public String getSubservicio()
	{
		return is_subservicio;
	}

	/**
	 * Modifica el valor de tabla.
	 *
	 * @param as_s asigna el valor a la propiedad tabla
	 */
	public void setTabla(String as_s)
	{
		is_tabla = as_s;
	}

	/**
	 * Retorna el valor de tabla.
	 *
	 * @return el valor de tabla
	 */
	public String getTabla()
	{
		return is_tabla;
	}
}
