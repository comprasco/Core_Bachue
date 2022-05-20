package com.bachue.snr.prosnr01.model.consulta.documento;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ConsultaDocumento.
 *
 * @author Julian Vaca
 */
public class ConsultaDocumento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6191045781907993857L;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad if fases. */
	private Fases if_fases;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		return id_documento;
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
	 * Modifica el valor de persona.
	 *
	 * @param ap_p asigna el valor a la propiedad persona
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @return el valor de persona
	 */
	public Persona getPersona()
	{
		return ip_persona;
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
}
