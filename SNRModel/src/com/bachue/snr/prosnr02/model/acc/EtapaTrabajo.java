package com.bachue.snr.prosnr02.model.acc;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import java.io.Serializable;


/**
 * Abstraccion de tabla de Subproceso SDB_PGN_ETAPA_TRABAJO.
 *
 * @author jpatino
 */
public class EtapaTrabajo extends Etapa implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5719100582353673521L;

	/** Propiedad ipt proceso. */
	private ProcesoTrabajo ipt_proceso;

	/** Propiedad is estado actividad. */
	private String is_estadoActividad;

	/** Propiedad is tiempo espera. */
	private String is_tiempoEspera;

	/** Propiedad ib fin etapa. */
	private boolean ib_finEtapa;

	/** Propiedad ib fin proceso. */
	private boolean ib_finProceso;

	/** Propiedad ib inicio proceso. */
	private boolean ib_inicioProceso;

	/** Propiedad ib recepcion. */
	private boolean ib_recepcion;

	/** Propiedad ib servicio. */
	private boolean ib_servicio;

	/** Propiedad ib usuario. */
	private boolean ib_usuario;

	/**
	 * Instancia un nuevo objeto etapa trabajo.
	 */
	public EtapaTrabajo()
	{
	}

	/**
	 * Instancia un nuevo objeto etapa trabajo.
	 *
	 * @param as_id de as id
	 */
	public EtapaTrabajo(String as_id)
	{
		setFinProceso(false);
		setId(as_id);
		setInicioProceso(false);
		setRecepcion(false);
		setServicio(false);
		setTipo(TIPO_ETAPA);
		setUsuario(false);
	}

	/**
	 * Modifica el valor de EstadoActividad.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoActividad(String as_s)
	{
		is_estadoActividad = as_s;
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
	 * Modifica el valor de FinEtapa.
	 *
	 * @param ab_b de ab b
	 */
	public void setFinEtapa(boolean ab_b)
	{
		ib_finEtapa = ab_b;
	}

	/**
	 * Valida la propiedad fin etapa.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en fin etapa
	 */
	public boolean isFinEtapa()
	{
		return ib_finEtapa;
	}

	/**
	 * Modifica el valor de FinProceso.
	 *
	 * @param ab_b de ab b
	 */
	public void setFinProceso(boolean ab_b)
	{
		ib_finProceso = ab_b;
	}

	/**
	 * Valida la propiedad fin proceso.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en fin proceso
	 */
	public boolean isFinProceso()
	{
		return ib_finProceso;
	}

	/**
	 * Modifica el valor de InicioProceso.
	 *
	 * @param ab_b de ab b
	 */
	public void setInicioProceso(boolean ab_b)
	{
		ib_inicioProceso = ab_b;
	}

	/**
	 * Valida la propiedad inicio proceso.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en inicio proceso
	 */
	public boolean isInicioProceso()
	{
		return ib_inicioProceso;
	}

	/**
	 * Modifica el valor de Proceso.
	 *
	 * @param apt_pt de apt pt
	 */
	public void setProceso(ProcesoTrabajo apt_pt)
	{
		ipt_proceso = apt_pt;
	}

	/**
	 * Retorna Objeto o variable de valor proceso.
	 *
	 * @return el valor de proceso
	 */
	public ProcesoTrabajo getProceso()
	{
		return ipt_proceso;
	}

	/**
	 * Modifica el valor de Recepcion.
	 *
	 * @param ab_b de ab b
	 */
	public void setRecepcion(boolean ab_b)
	{
		ib_recepcion = ab_b;
	}

	/**
	 * Valida la propiedad recepcion.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en recepcion
	 */
	public boolean isRecepcion()
	{
		return ib_recepcion;
	}

	/**
	 * Modifica el valor de Servicio.
	 *
	 * @param ab_b de ab b
	 */
	public void setServicio(boolean ab_b)
	{
		ib_servicio = ab_b;
	}

	/**
	 * Valida la propiedad servicio.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en servicio
	 */
	public boolean isServicio()
	{
		return ib_servicio;
	}

	/**
	 * Modifica el valor de TiempoEspera.
	 *
	 * @param as_s de as s
	 */
	public void setTiempoEspera(String as_s)
	{
		is_tiempoEspera = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor tiempo espera.
	 *
	 * @return el valor de tiempo espera
	 */
	public String getTiempoEspera()
	{
		return is_tiempoEspera;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param ab_b de ab b
	 */
	public void setUsuario(boolean ab_b)
	{
		ib_usuario = ab_b;
	}

	/**
	 * Valida la propiedad usuario.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en usuario
	 */
	public boolean isUsuario()
	{
		return ib_usuario;
	}

	/**
	 * Compare to.
	 *
	 * @param aet_etapa de aet etapa
	 * @return el valor de int
	 */
	public int compareTo(EtapaTrabajo aet_etapa)
	{
		int li_comparacion;

		if(aet_etapa == null)
			li_comparacion = -1;
		else if(this == aet_etapa)
			li_comparacion = 0;
		else if(this.equals(aet_etapa))
			li_comparacion = 0;
		else
		{
			li_comparacion = getTipo() - aet_etapa.getTipo();

			if(li_comparacion == 0)
				li_comparacion = NumericUtils.getInt(getIdEtapa() - aet_etapa.getIdEtapa());
		}

		return li_comparacion;
	}

	/** {@inheritdoc} */
	public String toString()
	{
		StringBuilder lsb_sb;

		lsb_sb = new StringBuilder(super.toString());

		lsb_sb.append(" :: ");
		lsb_sb.append(getIdEtapa());
		lsb_sb.append(" :: ");
		lsb_sb.append(getNombre());
		lsb_sb.append(" :: (isInicioProceso -> ");
		lsb_sb.append(isInicioProceso());
		lsb_sb.append(") :: (isFinProceso -> ");
		lsb_sb.append(isFinProceso());
		lsb_sb.append(") :: (isFinProceso -> ");
		lsb_sb.append(isFinEtapa());
		lsb_sb.append(") :: (isRecepcion -> ");
		lsb_sb.append(isRecepcion());
		lsb_sb.append(") :: (isServicio -> ");
		lsb_sb.append(isServicio());
		lsb_sb.append(") :: (isUsuario -> ");
		lsb_sb.append(isUsuario());
		lsb_sb.append(")");

		{
			String ls_tiempoEspera;

			ls_tiempoEspera = getTiempoEspera();

			if(StringUtils.isValidString(ls_tiempoEspera))
			{
				lsb_sb.append(" :: (getTiempoEspera -> ");
				lsb_sb.append(ls_tiempoEspera);
				lsb_sb.append(")");
			}
		}

		return lsb_sb.toString();
	}
}
