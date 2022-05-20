package com.bachue.snr.prosnr01.model.consulta.reparto.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ConsultaRepartoCalificacion.
 *
 * @author Julian Vaca
 */
public class ConsultaRepartoCalificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4826546426586307534L;

	/** Propiedad icr circulo registral. */
	private CirculoRegistral icr_circuloRegistral;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/**
	 * Modifica el valor de circulo registral.
	 *
	 * @param acr_cr asigna el valor a la propiedad circulo registral
	 */
	public void setCirculoRegistral(CirculoRegistral acr_cr)
	{
		icr_circuloRegistral = acr_cr;
	}

	/**
	 * Retorna el valor de circulo registral.
	 *
	 * @return el valor de circulo registral
	 */
	public CirculoRegistral getCirculoRegistral()
	{
		return icr_circuloRegistral;
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
	 * Modifica el valor de turno historia.
	 *
	 * @param ath_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return ith_turnoHistoria;
	}
}
