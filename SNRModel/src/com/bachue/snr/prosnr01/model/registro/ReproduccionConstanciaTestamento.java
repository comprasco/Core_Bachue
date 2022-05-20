package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades ReproduccionConstanciaTestamento.
 *
 * @author Duvan Beltrán
 */
public class ReproduccionConstanciaTestamento implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7010118184159368712L;

	/**  propiedad id_documento. */
	private Documento id_documento;

	/**  propiedad it_testamento. */
	private Testamento it_testamento;

	/** propiedad it turno. */
	private Turno it_turno;

	/**
	 * Método de obtención de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public Testamento getTestamento()
	{
		return it_testamento;
	}

	/**
	 * Método de asignación de la propiedad.
	 *
	 * @param at_t con el valor de la propiedad a asignar
	 */
	public void setTestamento(Testamento at_t)
	{
		it_testamento = at_t;
	}

	/**
	 * Método de obtención de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public Documento getDocumento()
	{
		return id_documento;
	}

	/**
	 * Método de asignación de la propiedad.
	 *
	 * @param ad_d de ad d
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return it_turno del tipo Turno
	 */
	public Turno getTurno()
	{
		return it_turno;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param at_t de Tipo Turno con el valor a asignar
	 */
	public void setTurno(Turno at_t)
	{
		it_turno = at_t;
	}
}
