package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_COMPLEMENTACION_PREDIO.
 *
 * @author Julian Vaca
 */
public class AccComplementacionPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID             = -6451818880432753351L;
	
	/** Propiedad il id complementacion. */
	private Long              il_idComplementacion;
	
	/** Propiedad il id complementacion original. */
	private Long              il_idComplementacionOriginal;
	
	/** Propiedad il id complementacion predio. */
	private Long              il_idComplementacionPredio;
	
	/** Propiedad is complementacion. */
	private String            is_complementacion;
	
	/** Propiedad is id datos ant sistema. */
	private String            is_idDatosAntSistema;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is tipo complementacion. */
	private String            is_tipoComplementacion;
	
	/** Propiedad ib devolucion digitador masivo. */
	private boolean           ib_devolucionDigitadorMasivo;
	
	/** Propiedad il id turno historia. */
	private long              il_idTurnoHistoria;

	/**
	 * Constructor por defecto.
	 */
	public AccComplementacionPredio()
	{
		ib_devolucionDigitadorMasivo                       = true;
	}

	/**
	 * Constructor que recibe por parametro Complementacion Predio.
	 *
	 * @param adp_dp objeto ComplementacionPredio
	 * @see com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio
	 */
	public AccComplementacionPredio(ComplementacionPredio adp_dp)
	{
		if(adp_dp != null)
		{
			il_idComplementacionOriginal     = NumericUtils.getLongWrapper(adp_dp.getIdComplementacion());
			is_complementacion               = adp_dp.getComplementacion();
		}
	}

	/**
	 * Modifica el valor de Complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComplementacion(String as_s)
	{
		is_complementacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComplementacion()
	{
		return is_complementacion;
	}

	/**
	 * Modifica el valor de DevolucionDigitadorMasivo.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDevolucionDigitadorMasivo(boolean ab_b)
	{
		ib_devolucionDigitadorMasivo = ab_b;
	}

	/**
	 * Valida la propiedad devolucion digitador masivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDevolucionDigitadorMasivo()
	{
		return ib_devolucionDigitadorMasivo;
	}

	/**
	 * Modifica el valor de IdComplementacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdComplementacion(Long al_l)
	{
		il_idComplementacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdComplementacion()
	{
		return il_idComplementacion;
	}

	/**
	 * Modifica el valor de IdComplementacionOriginal.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdComplementacionOriginal(Long al_l)
	{
		il_idComplementacionOriginal = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id complementacion original.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdComplementacionOriginal()
	{
		return il_idComplementacionOriginal;
	}

	/**
	 * Modifica el valor de IdComplementacionPredio.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdComplementacionPredio(Long al_l)
	{
		il_idComplementacionPredio = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id complementacion predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdComplementacionPredio()
	{
		return il_idComplementacionPredio;
	}

	/**
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
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
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de TipoComplementacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoComplementacion(String as_s)
	{
		is_tipoComplementacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoComplementacion()
	{
		return is_tipoComplementacion;
	}
}
