package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.io.Serializable;



/**
 * Objeto para el manejo de diferentes fuentes de información a cargar en una pantalla provenientes de
 * una etapa anterior.
 *
 * @author Manuel Blanco
 */
public class DatosEtapaAnterior implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2588226833055587906L;

	/** Propiedad iot oficios texto. */
	private OficiosTexto iot_oficiosTexto;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/**
	 * Modifica el valor de oficios texto.
	 *
	 * @param aot_ot asigna el valor a la propiedad oficios texto
	 */
	public void setOficiosTexto(OficiosTexto aot_ot)
	{
		iot_oficiosTexto = aot_ot;
	}

	/**
	 * Retorna el valor de oficios texto.
	 *
	 * @return el valor de oficios texto
	 */
	public OficiosTexto getOficiosTexto()
	{
		return iot_oficiosTexto;
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
