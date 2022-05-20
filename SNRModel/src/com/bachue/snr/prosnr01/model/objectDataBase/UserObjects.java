package com.bachue.snr.prosnr01.model.objectDataBase;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades UserObjects.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class UserObjects extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6063665078178738000L;
	
	/** Propiedad is column name. */
	private String            is_columnName;
	
	/** Propiedad is object name. */
	private String            is_objectName;

	/**
	 * Modifica el valor de ObjectName.
	 *
	 * @param as_s de as s
	 */
	public void setObjectName(String as_s)
	{
		is_objectName                          = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor object name.
	 *
	 * @return el valor de object name
	 */
	public String getObjectName()
	{
		return is_objectName;
	}

	/**
	 * Retorna Objeto o variable de valor column name.
	 *
	 * @return el valor de column name
	 */
	public String getcolumnName()
	{
		return is_columnName;
	}

	/**
	 * Modifica el valor de columnName.
	 *
	 * @param as_columnName de as column name
	 */
	public void setcolumnName(String as_columnName)
	{
		is_columnName = as_columnName;
	}
}
