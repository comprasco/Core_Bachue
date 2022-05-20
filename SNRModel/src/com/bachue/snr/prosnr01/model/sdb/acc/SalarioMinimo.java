package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de SalarioMinimo.
 *
 * @author Julian Vaca
 */
public class SalarioMinimo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long         serialVersionUID = -5806150779614342485L;
	
	/** Propiedad ibd valor salario. */
	private BigDecimal                ibd_valorSalario;
	
	/** Propiedad icsm info all. */
	private Collection<SalarioMinimo> icsm_infoAll;
	
	/** Propiedad is id salario. */
	private String                    is_idSalario;
	
	/** Propiedad is vigencia. */
	private String                    is_vigencia;
	
	/** Propiedad ib accion. */
	private boolean                   ib_accion;

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAccion(boolean ab_b)
	{
		ib_accion                                      = ab_b;
	}

	/**
	 * Valida la propiedad accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAccion()
	{
		return ib_accion;
	}

	/**
	 * Modifica el valor de IdSalario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSalario(String as_s)
	{
		is_idSalario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id salario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSalario()
	{
		return is_idSalario;
	}

	/**
	 * Modifica el valor de InfoAll.
	 *
	 * @param acsm_csm asigna el valor a la propiedad
	 */
	public void setInfoAll(Collection<SalarioMinimo> acsm_csm)
	{
		icsm_infoAll = acsm_csm;
	}

	/**
	 * Retorna Objeto o variable de valor info all.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<SalarioMinimo> getInfoAll()
	{
		return icsm_infoAll;
	}

	/**
	 * Modifica el valor de ValorSalario.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorSalario(BigDecimal abd_bd)
	{
		ibd_valorSalario = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor salario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorSalario()
	{
		return ibd_valorSalario;
	}

	/**
	 * Modifica el valor de Vigencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setVigencia(String as_s)
	{
		is_vigencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor vigencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getVigencia()
	{
		return is_vigencia;
	}
}
