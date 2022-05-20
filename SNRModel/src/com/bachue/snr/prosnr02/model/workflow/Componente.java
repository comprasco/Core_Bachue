package com.bachue.snr.prosnr02.model.workflow;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades Componente.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public abstract class Componente extends Auditoria implements Serializable, Comparable<Componente>
{
	
	/** Constante TIPO_PROCESO. */
	public static final int   TIPO_PROCESO                                    = 1;
	
	/** Constante TIPO_ETAPA. */
	public static final int   TIPO_ETAPA                                      = 2;
	
	/** Constante TIPO_FLUJO. */
	public static final int   TIPO_FLUJO                                      = 4;
	
	/** Constante TIPO_COMPUERTA_EXCLUSIVA. */
	public static final int   TIPO_COMPUERTA_EXCLUSIVA                        = 8;
	
	/** Constante TIPO_COMPUERTA_PARALELA. */
	public static final int   TIPO_COMPUERTA_PARALELA                         = 16;
	
	/** Constante TIPO_COMPUERTA_EXCLUSIVA_TERMINACION_AUTOMATICA. */
	public static final int   TIPO_COMPUERTA_EXCLUSIVA_TERMINACION_AUTOMATICA = 32;
	
	/** Constante TIPO_EVENTO_INICIO_PROCESO. */
	public static final int   TIPO_EVENTO_INICIO_PROCESO                      = 64;
	
	/** Constante TIPO_EVENTO_FIN_PROCESO. */
	public static final int   TIPO_EVENTO_FIN_PROCESO                         = 128;
	
	/** Constante TIPO_EVENTO_FIN_ETAPA. */
	public static final int   TIPO_EVENTO_FIN_ETAPA                           = 256;
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID                                = 5508816907453452099L;
	
	/** Propiedad io equals. */
	private Object            io_equals                                       = null;
	
	/** Propiedad is id. */
	private String            is_id;
	
	/** Propiedad ib hash code. */
	private boolean           ib_hashCode                                     = false;
	
	/** Propiedad ii tipo. */
	private int               ii_tipo;

	/**
	 * Modifica el valor de Id.
	 *
	 * @param as_s de as s
	 */
	public void setId(String as_s)
	{
		is_id                                                                 = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor id.
	 *
	 * @return el valor de id
	 */
	public String getId()
	{
		return is_id;
	}

	/**
	 * Retorna Objeto o variable de valor tipo.
	 *
	 * @return el valor de tipo
	 */
	public int getTipo()
	{
		return ii_tipo;
	}

	/** {@inheritdoc} */
	public int compareTo(Componente ac_componente)
	{
		int li_comparacion;

		if(ac_componente == null)
			li_comparacion = -1;
		else if(this == ac_componente)
			li_comparacion = 0;
		else if(this.equals(ac_componente))
			li_comparacion = 0;
		else
		{
			li_comparacion = getTipo() - ac_componente.getTipo();

			if(li_comparacion == 0)
			{
				String ls_id1;
				String ls_id2;

				ls_id1     = getId();
				ls_id2     = ac_componente.getId();

				if((ls_id1 == null) && (ls_id2 == null))
					li_comparacion = 0;
				else if((ls_id1 != null) && (ls_id2 == null))
					li_comparacion = -1;
				else if((ls_id1 == null) && (ls_id2 != null))
					li_comparacion = 1;
				else
					li_comparacion = ls_id1.compareTo(ls_id2);
			}
		}

		return li_comparacion;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(Object ao_o)
	{
		boolean lb_equals;

		if((ao_o == null) || !(ao_o instanceof Componente))
			lb_equals = false;
		else if(this == ao_o)
			lb_equals = true;
		else if(io_equals != null)
			lb_equals = (io_equals == ao_o);
		else if(ao_o.getClass() != getClass())
			lb_equals = false;
		else
		{
			Componente lc_componente;

			io_equals         = ao_o;
			lc_componente     = (Componente)ao_o;
			lb_equals         = true;

			if(lb_equals)
			{
				int li_i;

				li_i          = lc_componente.getTipo();
				lb_equals     = (ii_tipo == li_i);
			}

			if(lb_equals)
			{
				String ls_s;

				ls_s          = lc_componente.getId();
				lb_equals     = ((is_id == null) && (ls_s == null)) || ((is_id != null) && is_id.equals(ls_s));
			}

			io_equals = null;
		}

		return lb_equals;
	}

	/** {@inheritdoc} */
	public synchronized int hashCode()
	{
		int li_hashCode;

		if(ib_hashCode)
			li_hashCode = 0;
		else
		{
			ib_hashCode     = true;
			li_hashCode     = 1;

			{
				String ls_s;

				ls_s = getId();

				if(ls_s != null)
					li_hashCode += ls_s.hashCode();
			}

			ib_hashCode = false;
		}

		return li_hashCode;
	}

	/** {@inheritdoc} */
	public String toString()
	{
		StringBuilder lsb_sb;

		lsb_sb = new StringBuilder();

		{
			String ls_id;

			ls_id = getId();

			if(StringUtils.isValidString(ls_id))
			{
				lsb_sb.append(getClass().getName());
				lsb_sb.append("@");
				lsb_sb.append(ls_id);
			}
			else
				lsb_sb.append(super.toString());
		}

		return lsb_sb.toString();
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param ai_i de ai i
	 */
	protected void setTipo(int ai_i)
	{
		ii_tipo = ai_i;
	}
}
