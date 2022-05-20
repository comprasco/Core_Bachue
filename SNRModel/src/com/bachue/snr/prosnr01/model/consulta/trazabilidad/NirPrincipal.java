package com.bachue.snr.prosnr01.model.consulta.trazabilidad;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades NirPrincipal.
 *
 * @author mblanco
 */
public class NirPrincipal extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1536229205815237047L;

	/** Propiedad ia acto. */
	private Acto ia_acto;

	/** Propiedad ie etapa. */
	private Etapa ie_etapa;

	/** Propiedad if fases. */
	private Fases if_fases;

	/** Propiedad ip proceso. */
	private Proceso ip_proceso;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad it turno. */
	private Turno it_turno;

	/**
	 * Instancia un nuevo objeto nir principal.
	 */
	public NirPrincipal()
	{
	}

	/**
	 * Instancia un nuevo objeto nir principal.
	 *
	 * @param as_solicitud de as solicitud
	 * @param at_turno de at turno
	 * @param af_fase de af fase
	 */
	public NirPrincipal(Solicitud as_solicitud, Turno at_turno, Fases af_fase)
	{
		setFechaCreacion(as_solicitud.getFechaCreacion());
		is_solicitud     = as_solicitud;
		it_turno         = at_turno;
		if_fases         = af_fase;
	}

	/**
	 * Modifica el valor de acto.
	 *
	 * @param aa_a asigna el valor a la propiedad acto
	 */
	public void setActo(Acto aa_a)
	{
		ia_acto = aa_a;
	}

	/**
	 * Retorna el valor de acto.
	 *
	 * @return el valor de acto
	 */
	public Acto getActo()
	{
		return ia_acto;
	}

	/**
	 * Modifica el valor de etapa.
	 *
	 * @param ae_e asigna el valor a la propiedad etapa
	 */
	public void setEtapa(Etapa ae_e)
	{
		ie_etapa = ae_e;
	}

	/**
	 * Retorna el valor de etapa.
	 *
	 * @return el valor de etapa
	 */
	public Etapa getEtapa()
	{
		return ie_etapa;
	}

	/**
	 * Modifica el valor de fases.
	 *
	 * @param af_f asigna el valor a la propiedad fases
	 */
	public void setFases(Fases af_f)
	{
		if_fases = af_f;
	}

	/**
	 * Retorna el valor de fases.
	 *
	 * @return el valor de fases
	 */
	public Fases getFases()
	{
		return if_fases;
	}

	/**
	 * Modifica el valor de proceso.
	 *
	 * @param ap_p asigna el valor a la propiedad proceso
	 */
	public void setProceso(Proceso ap_p)
	{
		ip_proceso = ap_p;
	}

	/**
	 * Retorna el valor de proceso.
	 *
	 * @return el valor de proceso
	 */
	public Proceso getProceso()
	{
		return ip_proceso;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param lt_t asigna el valor a la propiedad turno
	 */
	public void setTurno(Turno lt_t)
	{
		it_turno = lt_t;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public Turno getTurno()
	{
		return it_turno;
	}
}
