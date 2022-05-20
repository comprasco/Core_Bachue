package com.bachue.snr.prosnr02.model.pgn;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr02.model.workflow.Flujo;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PGN_MOTIVO_TRAMITE_TRABAJO.
 *
 * @author Nicolas Guaneme
 */
public class MotivoTramiteTrabajo extends Flujo implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = -5782808493508512568L;
	
	/** Propiedad is decision calificacion. */
	private String            is_decisionCalificacion;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is descriptor fin. */
	private String            is_descriptorFin;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is estado actividad. */
	private String            is_estadoActividad;
	
	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id sub proceso. */
	private String            is_idSubProceso;
	
	/** Propiedad is indicador devolucion. */
	private String            is_indicadorDevolucion;
	
	/** Propiedad ii version. */
	private int               ii_version;

	/**
	 * Instancia un nuevo objeto motivo tramite trabajo.
	 */
	public MotivoTramiteTrabajo()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto motivo tramite trabajo.
	 *
	 * @param as_id de as id
	 */
	public MotivoTramiteTrabajo(String as_id)
	{
		super(as_id);
	}

	/**
	 * Modifica el valor de DecisionCalificacion.
	 *
	 * @param as_s de as s
	 */
	public void setDecisionCalificacion(String as_s)
	{
		is_decisionCalificacion                       = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor decision calificacion.
	 *
	 * @return el valor de decision calificacion
	 */
	public String getDecisionCalificacion()
	{
		return is_decisionCalificacion;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return el valor de descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de DescriptorFin.
	 *
	 * @param as_s de as s
	 */
	public void setDescriptorFin(String as_s)
	{
		is_descriptorFin = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor descriptor fin.
	 *
	 * @return el valor de descriptor fin
	 */
	public String getDescriptorFin()
	{
		return is_descriptorFin;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s de as s
	 */
	public void setEstado(String as_s)
	{
		is_estado = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de EstadoActividad.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoActividad(String as_s)
	{
		is_estadoActividad = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor estado actividad.
	 *
	 * @return el valor de estado actividad
	 */
	public String getEstadoActividad()
	{
		return is_estadoActividad;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return el valor de id proceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return el valor de id sub proceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de IndicadorDevolucion.
	 *
	 * @param as_s de as s
	 */
	public void setIndicadorDevolucion(String as_s)
	{
		is_indicadorDevolucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador devolucion.
	 *
	 * @return el valor de indicador devolucion
	 */
	public String getIndicadorDevolucion()
	{
		return is_indicadorDevolucion;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param ai_i de ai i
	 */
	public void setVersion(int ai_i)
	{
		ii_version = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return el valor de version
	 */
	public int getVersion()
	{
		return ii_version;
	}

	/** {@inheritdoc} */
	public String toString()
	{
		StringBuilder lsb_sb;

		lsb_sb = new StringBuilder(super.toString());

		lsb_sb.append(" :: (Descripcion: ");
		lsb_sb.append(getDescripcion());
		lsb_sb.append(")");
		lsb_sb.append(" :: (Descriptor Fin: ");
		lsb_sb.append(getDescriptorFin());
		lsb_sb.append(")");

		return lsb_sb.toString();
	}
}
