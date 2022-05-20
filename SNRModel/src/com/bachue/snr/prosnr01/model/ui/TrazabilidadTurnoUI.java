package com.bachue.snr.prosnr01.model.ui;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;

import com.bachue.snr.prosnr01.model.view.TrazabilidadTurnoFolio;
import com.bachue.snr.prosnr01.model.view.TrazabilidadTurnoSir;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades TrazabilidadTurnoUI.
 *
 * @author  Sebastian Sanchez
 */
public class TrazabilidadTurnoUI implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4244996754226830550L;

	/** Propiedad id fecentra. */
	private Date id_fecentra;

	/** Propiedad is descripcion estado. */
	private String is_descripcionEstado;

	/** Propiedad is estado string. */
	private String is_estadoString;

	/** Propiedad is fechaString. */
	private String is_fechaString;

	/** Propiedad is log certi correc. */
	private String is_logCertiCorrec;

	/** Propiedad is usuario. */
	private String is_usuario;

	/** Propiedad id estado. */
	private double id_estado;

	/**
	 * Instancia un nuevo objeto trazabilidad turno UI.
	 *
	 * @param atts_tts de atts tts
	 */
	public TrazabilidadTurnoUI(TrazabilidadTurnoSir atts_tts)
	{
		id_fecentra              = atts_tts.getFecentra();
		is_descripcionEstado     = atts_tts.getDescripcionEstado();
		is_logCertiCorrec        = atts_tts.getLogCertiCorrec();
		is_usuario               = atts_tts.getUsuario();
		is_estadoString          = atts_tts.getEstado();

		{
			if(id_fecentra != null)
				is_fechaString = StringUtils.getString(id_fecentra, FormatoFechaCommon.DIA_MES_ANIO);
		}
	}

	/**
	 * Instancia un nuevo objeto trazabilidad turno UI.
	 *
	 * @param attf_ttf de attf ttf
	 */
	public TrazabilidadTurnoUI(TrazabilidadTurnoFolio attf_ttf)
	{
		id_fecentra              = attf_ttf.getFecentra();
		is_descripcionEstado     = attf_ttf.getDescripcionEstado();
		is_logCertiCorrec        = attf_ttf.getLogCertiCorrec();
		is_usuario               = attf_ttf.getUsuario();
		id_estado                = attf_ttf.getEstado();

		{
			if(id_fecentra != null)
				is_fechaString = StringUtils.getString(id_fecentra, FormatoFechaCommon.DIA_MES_ANIO);
		}
	}

	/**
	 * Retorna Objeto o variable de valor fecha string.
	 *
	 * @return Retorna el valor de la propiedad fechaString
	 */
	public String getFechaString()
	{
		return is_fechaString;
	}

	/**
	 * Modifica el valor de Fecha String.
	 *
	 * @param as_s de as s
	 */
	public void setFechaString(String as_s)
	{
		is_fechaString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecentra.
	 *
	 * @return Retorna el valor de la propiedad fecentra
	 */
	public Date getFecentra()
	{
		return id_fecentra;
	}

	/**
	 * Modifica el valor de Fecentra.
	 *
	 * @param ad_d de ad d
	 */
	public void setFecentra(Date ad_d)
	{
		id_fecentra = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion estado.
	 *
	 * @return Retorna el valor de la propiedad descripcion estado
	 */
	public String getDescripcionEstado()
	{
		return is_descripcionEstado;
	}

	/**
	 * Modifica el valor de Descripcion Estado.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionEstado(String as_s)
	{
		is_descripcionEstado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return Retorna el valor de la propiedad usuario
	 */
	public String getUsuario()
	{
		return is_usuario;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param as_s de as s
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor log certi correc.
	 *
	 * @return Retorna el valor de la propiedad log certi correc
	 */
	public String getLogCertiCorrec()
	{
		return is_logCertiCorrec;
	}

	/**
	 * Modifica el valor de LogCertiCorrec.
	 *
	 * @param as_s de as s
	 */
	public void setLogCertiCorrec(String as_s)
	{
		is_logCertiCorrec = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return Retorna el valor de la propiedad estado
	 */
	public double getEstado()
	{
		return id_estado;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param ad_d de ad d
	 */
	public void setEstado(double ad_d)
	{
		id_estado = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor estado string.
	 *
	 * @return Retorna el valor de la propiedad estado string
	 */
	public String getEstadoString()
	{
		return is_estadoString;
	}

	/**
	 * Modifica el valor de Estado String.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoString(String as_s)
	{
		is_estadoString = as_s;
	}
}
