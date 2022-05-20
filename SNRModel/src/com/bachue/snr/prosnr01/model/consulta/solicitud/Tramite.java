package com.bachue.snr.prosnr01.model.consulta.solicitud;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades Tramite.
 *
 * @author Julian Vaca
 */
public class Tramite extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7633864409098941574L;

	/** Propiedad is derecho peticion. */
	private String is_derechoPeticion;

	/** Propiedad is fecha radicado. */
	private String is_fechaRadicado;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre procedencia. */
	private String is_nombreProcedencia;

	/** Propiedad is nombre tramite. */
	private String is_nombreTramite;

	/** Propiedad is radicado. */
	private String is_radicado;

	/** Propiedad is turno anterior. */
	private String is_turnoAnterior;

	/**
	 * Modifica el valor de derecho petcicion.
	 *
	 * @param as_s asigna el valor a la propiedad derecho petcicion
	 */
	public void setDerechoPetcicion(String as_s)
	{
		is_derechoPeticion = as_s;
	}

	/**
	 * Retorna el valor de derecho peticion.
	 *
	 * @return el valor de derecho peticion
	 */
	public String getDerechoPeticion()
	{
		return is_derechoPeticion;
	}

	/**
	 * Modifica el valor de fecha radicado.
	 *
	 * @param as_s asigna el valor a la propiedad fecha radicado
	 */
	public void setFechaRadicado(String as_s)
	{
		is_fechaRadicado = as_s;
	}

	/**
	 * Retorna el valor de fecha radicado.
	 *
	 * @return el valor de fecha radicado
	 */
	public String getFechaRadicado()
	{
		return is_fechaRadicado;
	}

	/**
	 * Modifica el valor de checks if is radicado.
	 *
	 * @param as_s asigna el valor a la propiedad checks if is radicado
	 */
	public void setIs_radicado(String as_s)
	{
		is_radicado = as_s;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de nombre procedencia.
	 *
	 * @param as_s asigna el valor a la propiedad nombre procedencia
	 */
	public void setNombreProcedencia(String as_s)
	{
		is_nombreProcedencia = as_s;
	}

	/**
	 * Retorna el valor de nombre procedencia.
	 *
	 * @return el valor de nombre procedencia
	 */
	public String getNombreProcedencia()
	{
		return is_nombreProcedencia;
	}

	/**
	 * Modifica el valor de nombre tramite.
	 *
	 * @param as_s asigna el valor a la propiedad nombre tramite
	 */
	public void setNombreTramite(String as_s)
	{
		is_nombreTramite = as_s;
	}

	/**
	 * Retorna el valor de nombre tramite.
	 *
	 * @return el valor de nombre tramite
	 */
	public String getNombreTramite()
	{
		return is_nombreTramite;
	}

	/**
	 * Retorna el valor de radicado.
	 *
	 * @return el valor de radicado
	 */
	public String getRadicado()
	{
		return is_radicado;
	}

	/**
	 * Modifica el valor de turno anterior.
	 *
	 * @param as_s asigna el valor a la propiedad turno anterior
	 */
	public void setTurnoAnterior(String as_s)
	{
		is_turnoAnterior = as_s;
	}

	/**
	 * Retorna el valor de turno anterior.
	 *
	 * @return el valor de turno anterior
	 */
	public String getTurnoAnterior()
	{
		return is_turnoAnterior;
	}
}
