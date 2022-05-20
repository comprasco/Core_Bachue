package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_DETALLE_AREA_PREDIO.
 *
 * @author mblanco
 */
public class DetalleAreaPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = -372274483111333850L;
	
	/** Propiedad id area. */
	private Double            id_area;
	
	/** Propiedad il id matricula. */
	private Long              il_idMatricula;
	
	/** Propiedad is area lectura. */
	private String            is_areaLectura;
	
	/** Propiedad is id area predio. */
	private String            is_idAreaPredio;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id detalle area predio. */
	private String            is_idDetalleAreaPredio;
	
	/** Propiedad is id tipo area. */
	private String            is_idTipoArea;
	
	/** Propiedad is id unidad medida. */
	private String            is_idUnidadMedida;

	/**
	 * Constructor que recibe como parametros id circulo, matricula, area lectura y id tipo area.
	 *
	 * @param as_idCirculo id circulo
	 * @param al_matricula matricula
	 * @param as_areaLectura area lectura
	 * @param as_idTipoArea id tipo area
	 */
	public DetalleAreaPredio(String as_idCirculo, Long al_matricula, String as_areaLectura, String as_idTipoArea)
	{
		is_idCirculo                                 = as_idCirculo;
		il_idMatricula                               = al_matricula;
		is_areaLectura                               = as_areaLectura;
		is_idTipoArea                                = as_idTipoArea;
	}

	/**
	 * Constructor que recibe como parametros id circulo, matricula, area lectura, id area predio y id tipo area.
	 *
	 * @param as_idCirculo id circulo
	 * @param al_matricula matricula
	 * @param as_areaLectura area lectura
	 * @param as_idAreaPredio id area predio
	 * @param as_idTipoArea id tipo area
	 */
	public DetalleAreaPredio(
	    String as_idCirculo, Long al_matricula, String as_areaLectura, String as_idAreaPredio, String as_idTipoArea
	)
	{
		is_idCirculo        = as_idCirculo;
		il_idMatricula      = al_matricula;
		is_idAreaPredio     = as_idAreaPredio;
		is_areaLectura      = as_areaLectura;
		is_idTipoArea       = as_idTipoArea;
	}

	/**
	 * Constructor por defecto.
	 */
	public DetalleAreaPredio()
	{
	}

	/**
	 * Modifica el valor de Area.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setArea(Double abi_bi)
	{
		id_area = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getArea()
	{
		return id_area;
	}

	/**
	 * Modifica el valor de AreaLectura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAreaLectura(String as_s)
	{
		is_areaLectura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor area lectura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAreaLectura()
	{
		return is_areaLectura;
	}

	/**
	 * Modifica el valor de IdAreaPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAreaPredio(String as_s)
	{
		is_idAreaPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id area predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAreaPredio()
	{
		return is_idAreaPredio;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdDetalleAreaPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDetalleAreaPredio(String as_s)
	{
		is_idDetalleAreaPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id detalle area predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDetalleAreaPredio()
	{
		return is_idDetalleAreaPredio;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param abi_bi de abi bi
	 */
	public void setIdMatricula(Long abi_bi)
	{
		il_idMatricula = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdTipoArea.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoArea(String as_s)
	{
		is_idTipoArea = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoArea()
	{
		return is_idTipoArea;
	}

	/**
	 * Modifica el valor de IdUnidadMedida.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUnidadMedida(String as_s)
	{
		is_idUnidadMedida = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id unidad medida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUnidadMedida()
	{
		return is_idUnidadMedida;
	}
}
