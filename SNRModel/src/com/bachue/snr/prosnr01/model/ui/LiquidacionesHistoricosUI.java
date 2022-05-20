package com.bachue.snr.prosnr01.model.ui;

import com.bachue.snr.prosnr01.model.view.LiquidacionesHistoricosFolio;
import com.bachue.snr.prosnr01.model.view.LiquidacionesHistoricosSir;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades LiquidacionesHistoricosUI.
 *
 * @author  Sebastian Sanchez
 */
public class LiquidacionesHistoricosUI implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1998530537270251990L;

	/** Propiedad is cuantia. */
	private String is_cuantia;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is nombre acto. */
	private String is_nombreActo;

	/** Propiedad is tipo liq. */
	private String is_tipoLiq;

	/** Propiedad id valor acto. */
	private double id_valorActo;

	/** Propiedad id valor conservacion documental. */
	private double id_valorConservacionDocumental;

	/** Propiedad id valor liquidacion. */
	private double id_valorLiquidacion;

	/**
	 * Instancia un nuevo objeto liquidaciones historicos UI.
	 *
	 * @param alhs_lhs de alhs lhs
	 */
	public LiquidacionesHistoricosUI(LiquidacionesHistoricosSir alhs_lhs)
	{
		is_idTipoActo                      = alhs_lhs.getIdTipoActo();
		is_cuantia                         = alhs_lhs.getCuantia();
		is_descripcion                     = alhs_lhs.getDescripcion();
		is_nombreActo                      = alhs_lhs.getNombreActo();
		is_tipoLiq                         = alhs_lhs.getTipoLiq();
		id_valorActo                       = alhs_lhs.getValorActo();
		id_valorConservacionDocumental     = alhs_lhs.getValorConservacionDocumental();
		id_valorLiquidacion                = alhs_lhs.getValorLiquidacion();
	}

	/**
	 * Instancia un nuevo objeto liquidaciones historicos UI.
	 *
	 * @param alhf_lhf de alhf lhf
	 */
	public LiquidacionesHistoricosUI(LiquidacionesHistoricosFolio alhf_lhf)
	{
		is_idTipoActo                      = alhf_lhf.getIdTipoActo();
		is_cuantia                         = alhf_lhf.getCuantia();
		is_descripcion                     = alhf_lhf.getDescripcion();
		is_nombreActo                      = alhf_lhf.getNombreActo();
		is_tipoLiq                         = alhf_lhf.getTipoLiq();
		id_valorActo                       = alhf_lhf.getValorActo();
		id_valorConservacionDocumental     = alhf_lhf.getValorConservacionDocumental();
		id_valorLiquidacion                = alhf_lhf.getValorLiquidacion();
	}

	/**
	 * Retorna Objeto o variable de valor cuantia.
	 *
	 * @return Retorna el valor de la propiedad cuantia
	 */
	public String getCuantia()
	{
		return is_cuantia;
	}

	/**
	 * Modifica el valor de Cuantia.
	 *
	 * @param as_s de as s
	 */
	public void setCuantia(String as_s)
	{
		is_cuantia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return Retorna el valor de la propiedad idTipoActo
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoActo(String as_s)
	{
		is_idTipoActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo liq.
	 *
	 * @return Retorna el valor de la propiedad tipoLiq
	 */
	public String getTipoLiq()
	{
		return is_tipoLiq;
	}

	/**
	 * Modifica el valor de TipoLiq.
	 *
	 * @param as_s de as s
	 */
	public void setTipoLiq(String as_s)
	{
		is_tipoLiq = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre acto.
	 *
	 * @return Retorna el valor de la propiedad nombreActo
	 */
	public String getNombreActo()
	{
		return is_nombreActo;
	}

	/**
	 * Modifica el valor de NombreActo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreActo(String as_s)
	{
		is_nombreActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor valor conservacion documental.
	 *
	 * @return Retorna el valor de la propiedad valorConservacionDocumental
	 */
	public double getValorConservacionDocumental()
	{
		return id_valorConservacionDocumental;
	}

	/**
	 * Modifica el valor de ValorConservacionDocumental.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorConservacionDocumental(double ad_d)
	{
		id_valorConservacionDocumental = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor acto.
	 *
	 * @return Retorna el valor de la propiedad valor acto
	 */
	public double getValorActo()
	{
		return id_valorActo;
	}

	/**
	 * Modifica el valor de Valor Acto.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorActo(double ad_d)
	{
		id_valorActo = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor liquidacion.
	 *
	 * @return Retorna el valor de la propiedad valor liquidacion
	 */
	public double getValorLiquidacion()
	{
		return id_valorLiquidacion;
	}

	/**
	 * Modifica el valor de Valor Liquidacion.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorLiquidacion(double ad_d)
	{
		id_valorLiquidacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return Retorna el valor de la propiedad descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}
}
