package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades CabidaLinderos.
 *
 * @author asantos
 */
public class CabidaLinderos extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6402737504544099832L;

	/** Propiedad ic complementacion. */
	private Complementacion ic_complementacion;

	/** Propiedad icp complementacion predio. */
	private ComplementacionPredio icp_complementacionPredio;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is linderos. */
	private String is_linderos;

	/** Propiedad is tipo complementacion. */
	private String is_tipoComplementacion;

	/** Propiedad is tipo lindero. */
	private String is_tipoLindero;

	/**
	 * Modifica el valor de complementacion.
	 *
	 * @param ac_c asigna el valor a la propiedad complementacion
	 */
	public void setComplementacion(Complementacion ac_c)
	{
		ic_complementacion = ac_c;
	}

	/**
	 * Retorna el valor de complementacion.
	 *
	 * @return el valor de complementacion
	 */
	public Complementacion getComplementacion()
	{
		if(ic_complementacion == null)
			ic_complementacion = new Complementacion();

		return ic_complementacion;
	}

	/**
	 * Modifica el valor de complementacion predio.
	 *
	 * @param acp_cp asigna el valor a la propiedad complementacion predio
	 */
	public void setComplementacionPredio(ComplementacionPredio acp_cp)
	{
		icp_complementacionPredio = acp_cp;
	}

	/**
	 * Retorna el valor de complementacion predio.
	 *
	 * @return el valor de complementacion predio
	 */
	public ComplementacionPredio getComplementacionPredio()
	{
		if(icp_complementacionPredio == null)
			icp_complementacionPredio = new ComplementacionPredio();

		return icp_complementacionPredio;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id matricula.
	 *
	 * @param al_l asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna el valor de id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de linderos.
	 *
	 * @param as_s asigna el valor a la propiedad linderos
	 */
	public void setLinderos(String as_s)
	{
		is_linderos = as_s;
	}

	/**
	 * Retorna el valor de linderos.
	 *
	 * @return el valor de linderos
	 */
	public String getLinderos()
	{
		return is_linderos;
	}

	/**
	 * Modifica el valor de tipo complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad tipo complementacion
	 */
	public void setTipoComplementacion(String as_s)
	{
		is_tipoComplementacion = as_s;
	}

	/**
	 * Retorna el valor de tipo complementacion.
	 *
	 * @return el valor de tipo complementacion
	 */
	public String getTipoComplementacion()
	{
		return is_tipoComplementacion;
	}

	/**
	 * Modifica el valor de tipo lindero.
	 *
	 * @param as_s asigna el valor a la propiedad tipo lindero
	 */
	public void setTipoLindero(String as_s)
	{
		is_tipoLindero = as_s;
	}

	/**
	 * Retorna el valor de tipo lindero.
	 *
	 * @return el valor de tipo lindero
	 */
	public String getTipoLindero()
	{
		return is_tipoLindero;
	}
}
