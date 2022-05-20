package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_FIRMA_MASIVA.
 *
 * @author Julian Vaca
 */
public class FirmaMasiva extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8552409762668252278L;
	
	/** Constante TIPO_FIRMA_1. */
	public static final int   TIPO_FIRMA_1     = 1;
	
	/** Propiedad is llave 1. */
	private String            is_llave1;
	
	/** Propiedad is llave 2. */
	private String            is_llave2;
	
	/** Propiedad is llave 3. */
	private String            is_llave3;
	
	/** Propiedad is llave 4. */
	private String            is_llave4;
	
	/** Propiedad is usuario. */
	private String            is_usuario;
	
	/** Propiedad ii id firma. */
	private int               ii_idFirma;
	
	/** Propiedad ii tipo firma. */
	private int               ii_tipoFirma;

	/**
	 * Modifica el valor de IdFirma.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setIdFirma(int ai_i)
	{
		ii_idFirma                             = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor id firma.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getIdFirma()
	{
		return ii_idFirma;
	}

	/**
	 * Modifica el valor de Llave1.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave1(String as_s)
	{
		is_llave1 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave1()
	{
		return is_llave1;
	}

	/**
	 * Modifica el valor de Llave2.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave2(String as_s)
	{
		is_llave2 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 2.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave2()
	{
		return is_llave2;
	}

	/**
	 * Modifica el valor de Llave3.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave3(String as_s)
	{
		is_llave3 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 3.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave3()
	{
		return is_llave3;
	}

	/**
	 * Modifica el valor de Llave4.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave4(String as_s)
	{
		is_llave4 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave 4.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave4()
	{
		return is_llave4;
	}

	/**
	 * Modifica el valor de TipoFirma.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setTipoFirma(int ai_i)
	{
		ii_tipoFirma = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor tipo firma.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getTipoFirma()
	{
		return ii_tipoFirma;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuario()
	{
		return is_usuario;
	}
}
