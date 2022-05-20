package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ConsultaDatosDocumento.
 *
 * @author hcastaneda
 */
public class ConsultaDatosDocumento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5844698985129206919L;

	/** Propiedad icr circulo registral. */
	private CirculoRegistral icr_circuloRegistral;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad ioo oficina origen. */
	private OficinaOrigen ioo_oficinaOrigen;

	/** Propiedad ls solicitud. */
	private Solicitud ls_solicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad ib ind vinculacion. */
	private boolean ib_indVinculacion;

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
		if(icr_circuloRegistral == null)
			icr_circuloRegistral = new CirculoRegistral();

		return icr_circuloRegistral;
	}

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
		if(id_documento == null)
			id_documento = new Documento();

		return id_documento;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de ind vinculacion.
	 *
	 * @param ab_b asigna el valor a la propiedad ind vinculacion
	 */
	public void setIndVinculacion(boolean ab_b)
	{
		ib_indVinculacion = ab_b;
	}

	/**
	 * Valida la propiedad ind vinculacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ind vinculacion
	 */
	public boolean isIndVinculacion()
	{
		return ib_indVinculacion;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param aoo_oo asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(OficinaOrigen aoo_oo)
	{
		ioo_oficinaOrigen = aoo_oo;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public OficinaOrigen getOficinaOrigen()
	{
		if(ioo_oficinaOrigen == null)
			ioo_oficinaOrigen = new OficinaOrigen();

		return ioo_oficinaOrigen;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		ls_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		if(ls_solicitud == null)
			ls_solicitud = new Solicitud();

		return ls_solicitud;
	}
}
