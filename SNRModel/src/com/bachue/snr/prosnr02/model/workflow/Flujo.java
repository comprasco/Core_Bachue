package com.bachue.snr.prosnr02.model.workflow;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr02.model.acc.EtapaTrabajo;
import com.bachue.snr.prosnr02.model.acc.ProcesoTrabajo;



/**
 * Clase que contiene todos las propiedades Flujo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class Flujo extends Componente
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = 6975168594127817377L;
	
	/** Propiedad ib flujo defecto. */
	boolean                   ib_flujoDefecto;
	
	/** Propiedad iet destino. */
	private EtapaTrabajo      iet_destino;
	
	/** Propiedad iet origen. */
	private EtapaTrabajo      iet_origen;
	
	/** Propiedad ii motivo. */
	private Integer           ii_motivo;
	
	/** Propiedad ipt proceso. */
	private ProcesoTrabajo    ipt_proceso;
	
	/** Propiedad is id destino. */
	private String            is_idDestino;
	
	/** Propiedad is id origen. */
	private String            is_idOrigen;
	
	/** Propiedad is tipo compuerta llegada. */
	private String            is_tipoCompuertaLlegada;

	/**
	 * Instancia un nuevo objeto flujo.
	 */
	public Flujo()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto flujo.
	 *
	 * @param as_id de as id
	 */
	public Flujo(String as_id)
	{
		setFlujoDefecto(false);
		setId(as_id);
		setTipo(TIPO_FLUJO);
	}

	/**
	 * Modifica el valor de Destino.
	 *
	 * @param aet_et de aet et
	 */
	public void setDestino(EtapaTrabajo aet_et)
	{
		iet_destino                                   = aet_et;
	}

	/**
	 * Retorna Objeto o variable de valor destino.
	 *
	 * @return el valor de destino
	 */
	public EtapaTrabajo getDestino()
	{
		return iet_destino;
	}

	/**
	 * Modifica el valor de FlujoDefecto.
	 *
	 * @param ab_b de ab b
	 */
	public void setFlujoDefecto(boolean ab_b)
	{
		ib_flujoDefecto = ab_b;
	}

	/**
	 * Valida la propiedad flujo defecto.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en flujo defecto
	 */
	public boolean isFlujoDefecto()
	{
		return ib_flujoDefecto;
	}

	/**
	 * Modifica el valor de IdDestino.
	 *
	 * @param as_s de as s
	 */
	public void setIdDestino(String as_s)
	{
		is_idDestino = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor id destino.
	 *
	 * @return el valor de id destino
	 */
	public String getIdDestino()
	{
		return is_idDestino;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa destino.
	 *
	 * @return el valor de id etapa destino
	 */
	public Integer getIdEtapaDestino()
	{
		return (iet_destino != null) ? NumericUtils.getInteger(iet_destino.getIdEtapa()) : null;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa origen.
	 *
	 * @return el valor de id etapa origen
	 */
	public Integer getIdEtapaOrigen()
	{
		return (iet_origen != null) ? NumericUtils.getInteger(iet_origen.getIdEtapa()) : null;
	}

	/**
	 * Modifica el valor de IdOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setIdOrigen(String as_s)
	{
		is_idOrigen = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor id origen.
	 *
	 * @return el valor de id origen
	 */
	public String getIdOrigen()
	{
		return is_idOrigen;
	}

	/**
	 * Modifica el valor de Motivo.
	 *
	 * @param ai_i de ai i
	 */
	public void setMotivo(Integer ai_i)
	{
		ii_motivo = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor motivo.
	 *
	 * @return el valor de motivo
	 */
	public Integer getMotivo()
	{
		return ii_motivo;
	}

	/**
	 * Modifica el valor de Origen.
	 *
	 * @param aet_et de aet et
	 */
	public void setOrigen(EtapaTrabajo aet_et)
	{
		iet_origen = aet_et;
	}

	/**
	 * Retorna Objeto o variable de valor origen.
	 *
	 * @return el valor de origen
	 */
	public EtapaTrabajo getOrigen()
	{
		return iet_origen;
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
	 * Modifica el valor de TipoCompuertaLLegada.
	 *
	 * @param ai_i de ai i
	 */
	public void setTipoCompuertaLLegada(String ai_i)
	{
		is_tipoCompuertaLlegada = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor tipo compuerta llegada.
	 *
	 * @return el valor de tipo compuerta llegada
	 */
	public String getTipoCompuertaLlegada()
	{
		return is_tipoCompuertaLlegada;
	}

	/** {@inheritdoc} */
	public String toString()
	{
		StringBuilder lsb_sb;

		lsb_sb = new StringBuilder("Flujo :: (Etapa Origen: ");

		{
			EtapaTrabajo let_origen;

			let_origen = getOrigen();

			if(let_origen != null)
				lsb_sb.append(let_origen.getIdEtapa());
		}

		lsb_sb.append(") -> (Etapa Destino: ");

		{
			EtapaTrabajo let_destino;

			let_destino = getDestino();

			if(let_destino != null)
				lsb_sb.append(let_destino.getIdEtapa());
		}

		lsb_sb.append(") :: (Motivo: ");
		lsb_sb.append(getMotivo());
		lsb_sb.append(") :: (Id Origen: ");
		lsb_sb.append(getIdOrigen());
		lsb_sb.append(") :: (Id Destino: ");
		lsb_sb.append(getIdDestino());
		lsb_sb.append(") :: (Flujo Defecto: ");
		lsb_sb.append(isFlujoDefecto());
		lsb_sb.append(")");

		{
			String ls_tipoCompuertaLLegada;

			ls_tipoCompuertaLLegada = getTipoCompuertaLlegada();

			if(StringUtils.isValidString(ls_tipoCompuertaLLegada))
			{
				lsb_sb.append(" :: ( Tipo Compuerta Llegada: ");
				lsb_sb.append(ls_tipoCompuertaLLegada);
				lsb_sb.append(")");
			}
		}

		return lsb_sb.toString();
	}
}
