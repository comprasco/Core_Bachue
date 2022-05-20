package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;


/**
 * Clase que representa un registro en la vista SDB_CON_SIIF_DETALLE del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class ConSiifDetalle extends Auditoria implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7374729881355760570L;

	/** Propiedad id valor moneda extranjera. */
	private double id_valorMonedaExtranjera;

	/** Propiedad id valor pesos. */
	private double id_valorPesos;

	/** Propiedad il consecutivo detalle. */
	private Long il_consecutivoDetalle;

	/** Propiedad il consecutivo maestro. */
	private Long il_consecutivoMaestro;

	/** Propiedad is dependencia afectacion ing. */
	private String is_dependenciaAfectacionIng;

	/** Propiedad is id siif detalle. */
	private String is_idSiifDetalle;

	/** Propiedad is posicion catalogo ing. */
	private String is_posicionCatalogoIng;

	/**
	 * Modifica el valor de ConsecutivoDetalle.
	 *
	 * @param as_consecutivoDetalle de as consecutivo detalle
	 */
	public void setConsecutivoDetalle(Long as_consecutivoDetalle)
	{
		il_consecutivoDetalle = as_consecutivoDetalle;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo detalle.
	 *
	 * @return el valor de consecutivo detalle
	 */
	public Long getConsecutivoDetalle()
	{
		return il_consecutivoDetalle;
	}

	/**
	 * Modifica el valor de ConsecutivoMaestro.
	 *
	 * @param as_consecutivoMaestro de as consecutivo maestro
	 */
	public void setConsecutivoMaestro(Long as_consecutivoMaestro)
	{
		il_consecutivoMaestro = as_consecutivoMaestro;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo maestro.
	 *
	 * @return el valor de consecutivo maestro
	 */
	public Long getConsecutivoMaestro()
	{
		return il_consecutivoMaestro;
	}

	/**
	 * Modifica el valor de DependenciaAfectacionIng.
	 *
	 * @param as_dependenciaAfectacionIng de as dependencia afectacion ing
	 */
	public void setDependenciaAfectacionIng(String as_dependenciaAfectacionIng)
	{
		is_dependenciaAfectacionIng = as_dependenciaAfectacionIng;
	}

	/**
	 * Retorna Objeto o variable de valor dependencia afectacion ing.
	 *
	 * @return el valor de dependencia afectacion ing
	 */
	public String getDependenciaAfectacionIng()
	{
		return is_dependenciaAfectacionIng;
	}

	/**
	 * Modifica el valor de IdSiifDetalle.
	 *
	 * @param as_idSiifDetalle de as id siif detalle
	 */
	public void setIdSiifDetalle(String as_idSiifDetalle)
	{
		is_idSiifDetalle = as_idSiifDetalle;
	}

	/**
	 * Retorna Objeto o variable de valor id siif detalle.
	 *
	 * @return el valor de id siif detalle
	 */
	public String getIdSiifDetalle()
	{
		return is_idSiifDetalle;
	}

	/**
	 * Modifica el valor de PosicionCatalogoIng.
	 *
	 * @param as_posicionCatalogoIng de as posicion catalogo ing
	 */
	public void setPosicionCatalogoIng(String as_posicionCatalogoIng)
	{
		is_posicionCatalogoIng = as_posicionCatalogoIng;
	}

	/**
	 * Retorna Objeto o variable de valor posicion catalogo ing.
	 *
	 * @return el valor de posicion catalogo ing
	 */
	public String getPosicionCatalogoIng()
	{
		return is_posicionCatalogoIng;
	}

	/**
	 * Modifica el valor de ValorMonedaExtranjera.
	 *
	 * @param ad_valorMonedaExtranjera de ad valor moneda extranjera
	 */
	public void setValorMonedaExtranjera(double ad_valorMonedaExtranjera)
	{
		id_valorMonedaExtranjera = ad_valorMonedaExtranjera;
	}

	/**
	 * Retorna Objeto o variable de valor valor moneda extranjera.
	 *
	 * @return el valor de valor moneda extranjera
	 */
	public double getValorMonedaExtranjera()
	{
		return id_valorMonedaExtranjera;
	}

	/**
	 * Modifica el valor de ValorPesos.
	 *
	 * @param ad_valorPesos de ad valor pesos
	 */
	public void setValorPesos(double ad_valorPesos)
	{
		id_valorPesos = ad_valorPesos;
	}

	/**
	 * Retorna Objeto o variable de valor valor pesos.
	 *
	 * @return el valor de valor pesos
	 */
	public double getValorPesos()
	{
		return id_valorPesos;
	}
}
