package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase para la abstracción de la tabla SDB_ACC_MOD_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 */
public class ModCuentaCupo extends Auditoria implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 5647976007546872843L;

	/** Propiedad ibd valor maximo. */
	private BigDecimal ibd_valorMaximo;

	/** Propiedad ibd valor minimo. */
	private BigDecimal ibd_valorMinimo;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id cuenta cupo. */
	private String is_idCuentaCupo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud creacion. */
	private String is_idSolicitudCreacion;

	/**
	 * Obtiene el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s el valor de id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Obtiene el valor de id solicitud creacion.
	 *
	 * @return el valor de id solicitud creacion
	 */
	public String getIdSolicitudCreacion()
	{
		return is_idSolicitudCreacion;
	}

	/**
	 * Modifica el valor de id solicitud creacion.
	 *
	 * @param as_s el valor de id solicitud creacion
	 */
	public void setIdSolicitudCreacion(String as_s)
	{
		is_idSolicitudCreacion = as_s;
	}

	/**
	 * Obtiene el valor de id cuenta cupo.
	 *
	 * @return el valor de id cuenta cupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Modifica el valor de id cuenta cupo.
	 *
	 * @param as_s el valor de id cuenta cupo
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Obtiene el valor de valor minimo.
	 *
	 * @return el valor de valor minimo
	 */
	public BigDecimal getValorMinimo()
	{
		return ibd_valorMinimo;
	}

	/**
	 * Modifica el valor de valor minimo.
	 *
	 * @param abd_bd el valor de valor minimo
	 */
	public void setValorMinimo(BigDecimal abd_bd)
	{
		ibd_valorMinimo = abd_bd;
	}

	/**
	 * Obtiene el valor de valor maximo.
	 *
	 * @return el valor de valor maximo
	 */
	public BigDecimal getValorMaximo()
	{
		return ibd_valorMaximo;
	}

	/**
	 * Modifica el valor de valor maximo.
	 *
	 * @param abd_bd el valor de valor maximo
	 */
	public void setValorMaximo(BigDecimal abd_bd)
	{
		ibd_valorMaximo = abd_bd;
	}

	/**
	 * Obtiene el valor de estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param as_s el valor de estado
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}
}
