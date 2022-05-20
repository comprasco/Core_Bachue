package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 *
 * Clase modelo que contiene todos los atributos de TarifaAlerta.java
 * @author nguaneme
 *
 */
public class TarifaAlerta extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID                 = 9002454537087431666L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id tarifa. */
	private String            is_idTarifa;
	
	/** Propiedad is valor tarifa string. */
	private String            is_valorTarifaString;
	
	/** Propiedad ib bloquear casilla ultimo registro. */
	private boolean           ib_bloquearCasillaUltimoRegistro;
	
	/** Propiedad ib editable. */
	private boolean           ib_editable;
	
	/** Propiedad ib mostrar campos cantidad F agregar. */
	private boolean           ib_mostrarCamposCantidadFAgregar;
	
	/** Propiedad ib ultimo registro. */
	private boolean           ib_ultimoRegistro;
	
	/** Propiedad id valor tarifa. */
	private double            id_valorTarifa;
	
	/** Propiedad il cantidad final matriculas. */
	private long              il_cantidadFinalMatriculas;
	
	/** Propiedad il cantidad inicial matriculas. */
	private long              il_cantidadInicialMatriculas;
	
	/** Propiedad il version. */
	private long              il_version;

	/**
	 * Modifica el valor de BloquearCasillaUltimoRegistro.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setBloquearCasillaUltimoRegistro(boolean ab_b)
	{
		this.ib_bloquearCasillaUltimoRegistro                  = ab_b;
	}

	/**
	 * Valida la propiedad bloquear casilla ultimo registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isBloquearCasillaUltimoRegistro()
	{
		return ib_bloquearCasillaUltimoRegistro;
	}

	/**
	 * Modifica el valor de CantidadFinalMatriculas.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCantidadFinalMatriculas(long al_l)
	{
		this.il_cantidadFinalMatriculas = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad final matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getCantidadFinalMatriculas()
	{
		return il_cantidadFinalMatriculas;
	}

	/**
	 * Modifica el valor de CantidadInicialMatriculas.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCantidadInicialMatriculas(long al_l)
	{
		this.il_cantidadInicialMatriculas = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad inicial matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getCantidadInicialMatriculas()
	{
		return il_cantidadInicialMatriculas;
	}

	/**
	 * Modifica el valor de IdTarifa.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTarifa(String as_s)
	{
		this.is_idTarifa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tarifa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTarifa()
	{
		return is_idTarifa;
	}

	/**
	 * Modifica el valor de MostrarCamposCantidadFAgregar.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMostrarCamposCantidadFAgregar(boolean ab_b)
	{
		this.ib_mostrarCamposCantidadFAgregar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar campos cantidad F agregar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMostrarCamposCantidadFAgregar()
	{
		return ib_mostrarCamposCantidadFAgregar;
	}

	/**
	 * Modifica el valor de UltimoRegistro.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setUltimoRegistro(boolean ab_b)
	{
		this.ib_ultimoRegistro = ab_b;
	}

	/**
	 * Valida la propiedad ultimo registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isUltimoRegistro()
	{
		return ib_ultimoRegistro;
	}

	/**
	 * Modifica el valor de ValorTarifa.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setValorTarifa(double ad_d)
	{
		this.id_valorTarifa = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor tarifa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public double getValorTarifa()
	{
		return id_valorTarifa;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersion(long al_l)
	{
		this.il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getVersion()
	{
		return il_version;
	}

	/**
	 * Retorna Objeto o variable de valor valor tarifa string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValorTarifaString()
	{
		return is_valorTarifaString;
	}

	/**
	 * Modifica el valor de ValorTarifaString.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setValorTarifaString(String as_s)
	{
		is_valorTarifaString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Valida la propiedad editable.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isEditable()
	{
		return ib_editable;
	}

	/**
	 * Modifica el valor de Editable.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEditable(boolean ab_b)
	{
		ib_editable = ab_b;
	}
}
