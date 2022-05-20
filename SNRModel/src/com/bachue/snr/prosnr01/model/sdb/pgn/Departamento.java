package com.bachue.snr.prosnr01.model.sdb.pgn;

import java.io.Serializable;



/**
 * Logica de modelo Circulo Registral siendo una abstracción de la tabla SDB_PGN_DEPARTAMENTO en la base de datos.
 *
 * @author Heiner Castañeda
 */
public class Departamento extends Municipio implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = -6230634836986340533L;
	
	/** Propiedad is indicativo telefonico. */
	private String            is_indicativoTelefonico;
	
	/** Propiedad is nombre departamento. */
	private String            is_nombreDepartamento;

	/**
	 * Constructor por defecto.
	 */
	public Departamento()
	{
	}

	/**
	 * Constructor que recibe como parametro sin informacion.
	 *
	 * @param as_s de as s
	 */
	public Departamento(String as_s)
	{
		setNombre(as_s);
	}

	/**
	 * Modifica el valor de IndicativoTelefonico.
	 *
	 * @param as_s de as s
	 */
	public void setIndicativoTelefonico(String as_s)
	{
		is_indicativoTelefonico                       = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicativo telefonico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicativoTelefonico()
	{
		return is_indicativoTelefonico;
	}

	/**
	 * Modifica el valor de NombreDepartamento.
	 *
	 * @param as_s de as s
	 */
	public void setNombreDepartamento(String as_s)
	{
		is_nombreDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreDepartamento()
	{
		return is_nombreDepartamento;
	}
}
