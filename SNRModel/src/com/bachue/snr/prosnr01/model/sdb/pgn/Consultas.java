package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de Consultas.
 *
 * @author Julian Vaca
 */
public class Consultas extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = 7998467498346390260L;
	
	/** Propiedad is descripcion consulta. */
	private String            is_descripcionConsulta;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is nombre consulta. */
	private String            is_nombreConsulta;
	
	/** Propiedad is nombre procedimiento. */
	private String            is_nombreProcedimiento;
	
	/** Propiedad il id consulta. */
	private long              il_idConsulta;

	/**
	 * Constructor por defecto.
	 */
	public Consultas()
	{
	}

	/**
	 * Modifica el valor de DescripcionConsulta.
	 *
	 * @param descripcionConsulta asigna el valor a la propiedad
	 */
	public void setDescripcionConsulta(String descripcionConsulta)
	{
		this.is_descripcionConsulta                  = descripcionConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionConsulta()
	{
		return is_descripcionConsulta;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param estado asigna el valor a la propiedad
	 */
	public void setEstado(String estado)
	{
		this.is_estado = estado;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de IdConsulta.
	 *
	 * @param idConsulta asigna el valor a la propiedad
	 */
	public void setIdConsulta(long idConsulta)
	{
		this.il_idConsulta = idConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor id consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdConsulta()
	{
		return il_idConsulta;
	}

	/**
	 * Modifica el valor de NombreConsulta.
	 *
	 * @param nombreConsulta asigna el valor a la propiedad
	 */
	public void setNombreConsulta(String nombreConsulta)
	{
		this.is_nombreConsulta = nombreConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor nombre consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreConsulta()
	{
		return is_nombreConsulta;
	}

	/**
	 * Modifica el valor de NombreProcedimiento.
	 *
	 * @param nombreProcedimiento asigna el valor a la propiedad
	 */
	public void setNombreProcedimiento(String nombreProcedimiento)
	{
		this.is_nombreProcedimiento = nombreProcedimiento;
	}

	/**
	 * Retorna Objeto o variable de valor nombre procedimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreProcedimiento()
	{
		return is_nombreProcedimiento;
	}
}
