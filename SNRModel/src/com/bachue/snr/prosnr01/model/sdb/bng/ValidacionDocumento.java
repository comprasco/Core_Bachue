package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de ValidacionDocumento.
 *
 * @author Julian Vaca
 */
public class ValidacionDocumento extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long  serialVersionUID = -1876827757418107528L;
	
	/** Propiedad ics nirs. */
	private Collection<String> ics_nirs;
	
	/** Propiedad id documento. */
	private Documento          id_documento;
	
	/** Propiedad is nir. */
	private String             is_nir;
	
	/** Propiedad is turno. */
	private String             is_turno;
	
	/** Propiedad ib validacion. */
	private boolean            ib_validacion;

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setDocumento(Documento ad_d)
	{
		this.id_documento                       = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Documento getDocumento()
	{
		return id_documento;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		this.is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de Nirs.
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setNirs(Collection<String> ac_c)
	{
		this.ics_nirs = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor nirs.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<String> getNirs()
	{
		return ics_nirs;
	}

	/**
	 * Modifica el valor de Turno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTurno(String as_s)
	{
		this.is_turno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * Modifica el valor de Validacion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidacion(boolean ab_b)
	{
		this.ib_validacion = ab_b;
	}

	/**
	 * Valida la propiedad validacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidacion()
	{
		return ib_validacion;
	}
}
