package com.bachue.snr.prosnr01.model.sdb.acc;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_BITACORA_BLOQUEO.
 *
 * @author Julian Vaca
 */
public class BitacoraBloqueo implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = 904608893405616945L;
	
	/** Propiedad ibi id bitacora bloqueo. */
	private BigInteger        ibi_idBitacoraBloqueo;
	
	/** Propiedad ibi id matricula. */
	private BigInteger        ibi_idMatricula;
	
	/** Propiedad id fecha final. */
	private Date              id_fechaFinal;
	
	/** Propiedad id fecha inicial. */
	private Date              id_fechaInicial;
	
	/** Propiedad id fecha insercion. */
	private Date              id_fechaInsercion;
	
	/** Propiedad id fecha modificacion. */
	private Date              id_fechaModificacion;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id turno bloqueo. */
	private String            is_idTurnoBloqueo;
	
	/** Propiedad is ip insercion. */
	private String            is_ipInsercion;
	
	/** Propiedad is ip modificacion. */
	private String            is_ipModificacion;
	
	/** Propiedad is usuario insercion. */
	private String            is_usuarioInsercion;
	
	/** Propiedad is usuario modificacion. */
	private String            is_usuarioModificacion;

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_estado                                    = as_s;
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
	 * Modifica el valor de FechaFinal.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaFinal(Date ad_d)
	{
		id_fechaFinal = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha final.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaFinal()
	{
		return id_fechaFinal;
	}

	/**
	 * Modifica el valor de FechaInicial.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaInicial(Date ad_d)
	{
		id_fechaInicial = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inicial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaInicial()
	{
		return id_fechaInicial;
	}

	/**
	 * Modifica el valor de FechaInsercion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaInsercion(Date ad_d)
	{
		id_fechaInsercion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha insercion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaInsercion()
	{
		return id_fechaInsercion;
	}

	/**
	 * Modifica el valor de FechaModificacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaModificacion(Date ad_d)
	{
		id_fechaModificacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaModificacion()
	{
		return id_fechaModificacion;
	}

	/**
	 * Modifica el valor de IdBitacoraBloqueo.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setIdBitacoraBloqueo(BigInteger abi_bi)
	{
		ibi_idBitacoraBloqueo = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor id bitacora bloqueo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getIdBitacoraBloqueo()
	{
		return ibi_idBitacoraBloqueo;
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
	 * @param abi_bi asigna el valor a la propiedad
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
	 * Modifica el valor de IdTurnoBloqueo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoBloqueo(String as_s)
	{
		is_idTurnoBloqueo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno bloqueo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoBloqueo()
	{
		return is_idTurnoBloqueo;
	}

	/**
	 * Modifica el valor de IpInsercion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIpInsercion(String as_s)
	{
		is_ipInsercion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ip insercion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIpInsercion()
	{
		return is_ipInsercion;
	}

	/**
	 * Modifica el valor de IpModificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIpModificacion(String as_s)
	{
		is_ipModificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ip modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIpModificacion()
	{
		return is_ipModificacion;
	}

	/**
	 * Modifica el valor de UsuarioInsercion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuarioInsercion(String as_s)
	{
		is_usuarioInsercion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario insercion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuarioInsercion()
	{
		return is_usuarioInsercion;
	}

	/**
	 * Modifica el valor de UsuarioModificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuarioModificacion(String as_s)
	{
		is_usuarioModificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuarioModificacion()
	{
		return is_usuarioModificacion;
	}
}
