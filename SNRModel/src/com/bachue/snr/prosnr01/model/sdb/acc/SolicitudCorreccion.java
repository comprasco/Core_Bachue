package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigInteger;


/**
 * Clase que contiene todos las propiedades SolicitudCorreccion.
 *
 * @author garias
 * Fecha de Creacion: 22/09/2020
 */
public class SolicitudCorreccion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2508400517786858323L;

	/** Propiedad ibi id causal correccion. */
	private BigInteger ibi_idCausalCorreccion;

	/** Propiedad ibi id matricula. */
	private BigInteger ibi_idMatricula;

	/** Propiedad ibi id solicitud correccion. */
	private BigInteger ibi_idSolicitudCorreccion;

	/** Propiedad is grupo causal. */
	private String is_grupoCausal;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is observacion. */
	private String is_observacion;

	/** Propiedad is seleccionado. */
	private String is_seleccionado;

	/** Propiedad ib find only solicitud. */
	private boolean ib_findOnlySolicitud;

	/**
	 * Modifica el valor de FindOnlySolicitud.
	 *
	 * @param ab_b de ab b
	 */
	public void setFindOnlySolicitud(boolean ab_b)
	{
		ib_findOnlySolicitud = ab_b;
	}

	/**
	 * Valida la propiedad find only solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isFindOnlySolicitud()
	{
		return ib_findOnlySolicitud;
	}

	/**
	 * Modifica el valor de IdCausalCorreccion.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setIdCausalCorreccion(BigInteger abi_bi)
	{
		ibi_idCausalCorreccion = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor id causal correccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getIdCausalCorreccion()
	{
		return ibi_idCausalCorreccion;
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
	 * Modifica el valor de IdMatricula.
	 *
	 * @param abi_bi de abi bi
	 */
	public void setIdMatricula(BigInteger abi_bi)
	{
		ibi_idMatricula = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getIdMatricula()
	{
		return ibi_idMatricula;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSolicitudCorreccion.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setIdSolicitudCorreccion(BigInteger abi_bi)
	{
		ibi_idSolicitudCorreccion = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud correccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getIdSolicitudCorreccion()
	{
		return ibi_idSolicitudCorreccion;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de Observacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacion(String as_s)
	{
		is_observacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacion()
	{
		return is_observacion;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSeleccionado(String as_s)
	{
		is_seleccionado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSeleccionado()
	{
		return is_seleccionado;
	}

	/**
	 * Retorna Objeto o variable de valor grupo causal.
	 *
	 * @return Retorna el valor de la propiedad grupoCausal
	 */
	public String getGrupoCausal()
	{
		return is_grupoCausal;
	}

	/**
	 * Modifica el valor de GrupoCausal.
	 *
	 * @param as_s Modifica el valor de la propiedad grupoCausal
	 */
	public void setGrupoCausal(String as_s)
	{
		is_grupoCausal = as_s;
	}
}
