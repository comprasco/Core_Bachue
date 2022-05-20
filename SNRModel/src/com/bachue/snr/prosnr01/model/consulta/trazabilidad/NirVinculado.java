package com.bachue.snr.prosnr01.model.consulta.trazabilidad;

import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades NirVinculado.
 *
 * @author mblanco
 */
public class NirVinculado extends NirPrincipal implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6178305202193251802L;

	/** Propiedad ia tramite vinculado. */
	private Acto ia_tramiteVinculado;

	/**
	 * Instancia un nuevo objeto nir vinculado.
	 */
	public NirVinculado()
	{
	}

	/**
	 * Instancia un nuevo objeto nir vinculado.
	 *
	 * @param anp_nirPrincipal de anp nir principal
	 */
	public NirVinculado(NirPrincipal anp_nirPrincipal)
	{
		if(anp_nirPrincipal != null)
		{
			setActo(anp_nirPrincipal.getActo());
			setEtapa(anp_nirPrincipal.getEtapa());
			setFases(anp_nirPrincipal.getFases());
			setProceso(anp_nirPrincipal.getProceso());
			setSolicitud(anp_nirPrincipal.getSolicitud());
			setTurno(anp_nirPrincipal.getTurno());
			setFechaCreacion(anp_nirPrincipal.getFechaCreacion());
		}
	}

	/**
	 * Instancia un nuevo objeto nir vinculado.
	 *
	 * @param as_solicitud de as solicitud
	 * @param at_turno de at turno
	 * @param af_fase de af fase
	 */
	public NirVinculado(Solicitud as_solicitud, Turno at_turno, Fases af_fase)
	{
		setFechaCreacion(as_solicitud.getFechaCreacion());
		setSolicitud(as_solicitud);
		setTurno(at_turno);
		setFases(af_fase);
	}

	/**
	 * Modifica el valor de tramite vinculado.
	 *
	 * @param at_t asigna el valor a la propiedad tramite vinculado
	 */
	public void setTramiteVinculado(Acto at_t)
	{
		ia_tramiteVinculado = at_t;
	}

	/**
	 * Retorna el valor de tramite vinculado.
	 *
	 * @return el valor de tramite vinculado
	 */
	public Acto getTramiteVinculado()
	{
		return ia_tramiteVinculado;
	}
}
