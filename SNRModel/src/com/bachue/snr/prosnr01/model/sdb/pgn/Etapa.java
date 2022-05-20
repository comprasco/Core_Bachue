package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr02.model.workflow.Nodo;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * Logica de modelo Etapa siendo una abstracción de la tabla SDB_PGN_ETAPA en la base de datos.
 *
 * @author Nicolas Guaneme
 */
public class Etapa extends Nodo implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6076883715307107865L;

	/** Propiedad ibd cantidad tiempo espera. */
	private BigDecimal ibd_cantidadTiempoEspera;

	/** Propiedad ibd dias habiles normal. */
	private BigDecimal ibd_diasHabilesNormal;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is generar qr string. */
	private String is_generarQRString;

	/** Propiedad is id unidad tiempo espera. */
	private String is_idUnidadTiempoEspera;

	/** Propiedad is indicador bloqueo. */
	private String is_indicadorBloqueo;

	/** Propiedad is indicador desborde. */
	private String is_indicadorDesborde;

	/** Propiedad is indicador peso. */
	private String is_indicadorPeso;

	/** Propiedad is indicador tope. */
	private String is_indicadorTope;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is tipo reparto. */
	private String is_tipoReparto;

	/** Propiedad ib generar qr. */
	private boolean ib_generaQR;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/** Propiedad il id fase. */
	private long il_idFase;

	/**
	 * Constructor que recibe como parametro el id de la etapa.
	 *
	 * @param al_idEtapa id de la etapa
	 */
	public Etapa(long al_idEtapa)
	{
		il_idEtapa = al_idEtapa;
	}

	/**
	 * Constructor por defecto.
	 */
	public Etapa()
	{
	}

	/**
	 * Modifica el valor de CantidadTiempoEspera.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setCantidadTiempoEspera(BigDecimal abd_bd)
	{
		this.ibd_cantidadTiempoEspera = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad tiempo espera.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getCantidadTiempoEspera()
	{
		return ibd_cantidadTiempoEspera;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de DiasHabilesNormal.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setDiasHabilesNormal(BigDecimal abd_bd)
	{
		this.ibd_diasHabilesNormal = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor dias habiles normal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getDiasHabilesNormal()
	{
		return ibd_diasHabilesNormal;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		this.is_estado = as_s;
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
	 * Modifica el valor de GeneraQR.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setGeneraQR(boolean ab_b)
	{
		this.ib_generaQR = ab_b;
	}

	/**
	 * Valida la propiedad genera QR.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isGeneraQR()
	{
		return ib_generaQR;
	}

	/**
	 * Modifica el valor de GeneraQRString.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setGenerarQRString(String as_s)
	{
		this.is_generarQRString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor generar qr string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getGenerarQRString()
	{
		return is_generarQRString;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapa(long al_l)
	{
		this.il_idEtapa = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de IdFase.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdFase(long al_l)
	{
		this.il_idFase = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id fase.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdFase()
	{
		return il_idFase;
	}

	/**
	 * Modifica el valor de IdUnidadTiempoEspera.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUnidadTiempoEspera(String as_s)
	{
		this.is_idUnidadTiempoEspera = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id unidad tiempo espera.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUnidadTiempoEspera()
	{
		return is_idUnidadTiempoEspera;
	}

	/**
	 * Modifica el valor de IndicadorBloqueo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicadorBloqueo(String as_s)
	{
		this.is_indicadorBloqueo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador bloqueo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicadorBloqueo()
	{
		return is_indicadorBloqueo;
	}

	/**
	 * Modifica el valor de IndicadorDesborde.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicadorDesborde(String as_s)
	{
		this.is_indicadorDesborde = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador desborde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicadorDesborde()
	{
		return is_indicadorDesborde;
	}

	/**
	 * Modifica el valor de IndicadorPeso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicadorPeso(String as_s)
	{
		this.is_indicadorPeso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador peso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicadorPeso()
	{
		return is_indicadorPeso;
	}

	/**
	 * Modifica el valor de IndicadorTope.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicadorTope(String as_s)
	{
		this.is_indicadorTope = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador tope.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicadorTope()
	{
		return is_indicadorTope;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de TipoReparto.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoReparto(String as_s)
	{
		this.is_tipoReparto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo reparto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoReparto()
	{
		return is_tipoReparto;
	}
}
