package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de transaccion de ingresos
 * @author dbeltran
 *
 */
public class TransaccionIngresos extends Auditoria implements Serializable
{
	/**Propiedad serialVersionUID*/
	private static final long serialVersionUID = -6354423010923030519L;

	/**Propiedad activo*/
	private String is_activo;

	/**Propiedad con Prestacion De Ingresos*/
	private String is_conPrestacionDeIngresos;

	/**Propiedad id codigo*/
	private String is_sinPrestacionDeIngresos;

	/**Propiedad nombre*/
	private String is_totalFecha;

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_activo por is_activo
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_conPrestacionDeIngresos
	 */
	public String getConPrestacionDeIngresos()
	{
		return is_conPrestacionDeIngresos;
	}

	/**
	 * @param Modifica el valor de la propiedad is_conPrestacionDeIngresos por is_conPrestacionDeIngresos
	 */
	public void setConPrestacionDeIngresos(String as_conPrestacionDeIngresos)
	{
		is_conPrestacionDeIngresos = as_conPrestacionDeIngresos;
	}

	/**
	 * @return Retorna el valor de la propiedad is_sinPrestacionDeIngresos
	 */
	public String getSinPrestacionDeIngresos()
	{
		return is_sinPrestacionDeIngresos;
	}

	/**
	 * @param Modifica el valor de la propiedad is_sinPrestacionDeIngresos por is_sinPrestacionDeIngresos
	 */
	public void setSinPrestacionDeIngresos(String as_sinPrestacionDeIngresos)
	{
		is_sinPrestacionDeIngresos = as_sinPrestacionDeIngresos;
	}

	/**
	 * @return Retorna el valor de la propiedad is_totalFecha
	 */
	public String getTotalFecha()
	{
		return is_totalFecha;
	}

	/**
	 * @param Modifica el valor de la propiedad is_totalFecha por is_totalFecha
	 */
	public void setTotalFecha(String as_totalFecha)
	{
		is_totalFecha = as_totalFecha;
	}
}
