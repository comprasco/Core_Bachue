package com.bachue.snr.prosnr02.model.acc;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Subproceso SDB_ACC_SUBPROCESO.
 *
 * @author jpatino
 */
public class SubProcesoTrabajo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long        serialVersionUID = -7291711994960567658L;
	
	/** Propiedad is id proceso. */
	private String                   is_idProceso;
	
	/** Propiedad is id subproceso. */
	private String                   is_idSubproceso;
	
	/** Propiedad is nombre. */
	private String                   is_nombre;
	
	/** Propiedad ispvt version. */
	private SubProcesoVersionTrabajo ispvt_version;

	/**
	 * Constructor por defecto.
	 */
	public SubProcesoTrabajo()
	{
	}

	/**
	 * Constructor que recibe como parametro del id del proceso y el id del subproceso.
	 *
	 * @param as_idProceso id del proceso
	 * @param as_idSubproceso id del subproceso
	 */
	public SubProcesoTrabajo(String as_idProceso, String as_idSubproceso)
	{
		setIdProceso(as_idProceso);
		setIdSubproceso(as_idSubproceso);
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso                                  = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso int.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getIdSubprocesoInt()
	{
		return NumericUtils.getInteger(getIdSubproceso());
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = StringUtils.getString(as_s);
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
	 * Modifica el valor de Version.
	 *
	 * @param aspvt_spvt de aspvt spvt
	 */
	public void setVersion(SubProcesoVersionTrabajo aspvt_spvt)
	{
		ispvt_version = aspvt_spvt;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return el valor de version
	 */
	public SubProcesoVersionTrabajo getVersion()
	{
		return ispvt_version;
	}
}
