package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_RUBRO_HOMOLOGACION
 * @author dbeltran
 *
 */
public class RubroHomologacion extends Auditoria implements Serializable
{
	/** Propiedad serialVersionUID*/
	private static final long serialVersionUID = -3991441362141856745L;

	/** Propiedad activo*/
	private String is_activo;

	/** Propiedad is_idProceso*/
	private String is_idProceso;

	/** Propiedad id rubro*/
	private String is_idRubro;

	/** Propiedad id rubros homologacion*/
	private String is_idRubrosHomologacion;

	/** Propiedad is_idSubproceso*/
	private String is_idSubproceso;

	/** Propiedad is_nombreProceso*/
	private String is_nombreProceso;

	/** Propiedad nombre rubro*/
	private String is_nombreRubro;

	/** Propiedad is_nombreSubproceso*/
	private String is_nombreSubproceso;

	/** Propiedad observacion*/
	private String is_observacion;

	/** Propiedad otros conceptos*/
	private String is_otrosConceptos;

	/** Propiedad tipo operación*/
	private String is_tipoOperacion;

	/**
	 * @return Retorna el valor de la propiedad is_idRubrosHomologacion
	 */
	public String getIdRubrosHomologacion()
	{
		return is_idRubrosHomologacion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idRubrosHomologacion por as_s
	 */
	public void setIdRubrosHomologacion(String as_s)
	{
		is_idRubrosHomologacion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idRubro
	 */
	public String getIdRubro()
	{
		return is_idRubro;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idRubro por as_s
	 */
	public void setIdRubro(String as_s)
	{
		is_idRubro = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idProceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idProceso por as_s
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idSubproceso
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idSubproceso por as_s
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_otrosConceptos
	 */
	public String getOtrosConceptos()
	{
		return is_otrosConceptos;
	}

	/**
	 * @param Modifica el valor de la propiedad is_otrosConceptos por as_s
	 */
	public void setOtrosConceptos(String as_s)
	{
		is_otrosConceptos = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_observacion
	 */
	public String getObservacion()
	{
		return is_observacion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_observacion por as_s
	 */
	public void setObservacion(String as_s)
	{
		is_observacion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_activo por as_s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_tipoOperacion
	 */
	public String getTipoOperacion()
	{
		return is_tipoOperacion;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_tipoOperacion
	 */
	public void setTipoOperacion(String as_s)
	{
		is_tipoOperacion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreProceso
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_nombreProceso
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreSubproceso
	 */
	public String getNombreSubproceso()
	{
		return is_nombreSubproceso;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_nombreSubproceso
	 */
	public void setNombreSubproceso(String as_s)
	{
		is_nombreSubproceso = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreRubro
	 */
	public String getNombreRubro()
	{
		return is_nombreRubro;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_nombreRubro
	 */
	public void setNombreRubro(String as_s)
	{
		is_nombreRubro = as_s;
	}
}
