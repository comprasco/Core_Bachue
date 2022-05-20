package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * Clase de abstraccion de la base de datos para la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
 *
 * @author Sebastian Tafur
 */
public class CausalAprobacionDevolucion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7879201132788603632L;

	/** Propiedad ibd id etapa devolucion. */
	private BigDecimal ibd_idEtapaDevolucion;

	/** Propiedad is accion. */
	private String is_accion;

	/** Propiedad is causal devolucion. */
	private String is_causalDevolucion;

	/** Propiedad is codigo. */
	private String is_codigo;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id causal aprobacion devolucion. */
	private String is_idCausalAprobacionDevolucion;

	/** Propiedad lb es aprobador asesoria juridica. */
	private boolean ib_esAprobadorAsesoriaJuridica;

	/** Propiedad lb es aprobador resolución. */
	private boolean ib_esAprobadorResolucion;

	/** Propiedad lb es aprobador secuencia. */
	private boolean lb_esAprobadorSecuencia;

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAccion(String as_s)
	{
		is_accion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAccion()
	{
		return is_accion;
	}

	/**
	 * Modifica el valor de CausalDevolucion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCausalDevolucion(String as_s)
	{
		is_causalDevolucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor causal devolucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCausalDevolucion()
	{
		return is_causalDevolucion;
	}

	/**
	 * Modifica el valor de Codigo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigo(String as_s)
	{
		is_codigo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * @param Modifica el valor de la propiedad esAprobadorAsesoriaJuridica por esAprobadorAsesoriaJuridica
	 */
	public void setEsAprobadorAsesoriaJuridica(boolean ab_b)
	{
		ib_esAprobadorAsesoriaJuridica = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esAprobadorAsesoriaJuridica
	 */
	public boolean isEsAprobadorAsesoriaJuridica()
	{
		return ib_esAprobadorAsesoriaJuridica;
	}

	/**
	 * @param Modifica el valor de la propiedad esAprobadorResolucion por esAprobadorResolucion
	 */
	public void setEsAprobadorResolucion(boolean ab_b)
	{
		ib_esAprobadorResolucion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esAprobadorResolucion
	 */
	public boolean isEsAprobadorResolucion()
	{
		return ib_esAprobadorResolucion;
	}

	/**
	 * Modifica el valor de EsAprobadorSecuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEsAprobadorSecuencia(boolean ab_b)
	{
		lb_esAprobadorSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad es aprobador secuencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isEsAprobadorSecuencia()
	{
		return lb_esAprobadorSecuencia;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
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
	 * Modifica el valor de IdCausalAprobacionDevolucion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCausalAprobacionDevolucion(String as_s)
	{
		is_idCausalAprobacionDevolucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id causal aprobacion devolucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCausalAprobacionDevolucion()
	{
		return is_idCausalAprobacionDevolucion;
	}

	/**
	 * Modifica el valor de IdEtapaDevolucion.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdEtapaDevolucion(BigDecimal abd_bd)
	{
		ibd_idEtapaDevolucion = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa devolucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdEtapaDevolucion()
	{
		return ibd_idEtapaDevolucion;
	}
}
