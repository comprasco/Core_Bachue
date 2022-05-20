package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades ActoNuevaEntrada.
 *
 * @author Duvan Beltrán
 */
public class SolicitudTestamento implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1650801847786480814L;

	/** Propiedad ics calidad solicitante. */
	private CalidadSolicitante ics_calidadSolicitante;

	/**  propiedad id_documento. */
	private Documento id_documento;

	/**  propiedad id_documentoAnterior. */
	private Documento id_documentoAnterior;

	/** propiedad iidt interesado documento tipo. */
	private InteresadoDocumentoTipo iidt_interesadoDocumentoTipo;

	/** Propiedad ilt libro testamento. */
	/*propiedad ilt libro testameento**/
	private LibroTestamento ilt_libroTestamento;

	/** propiedad ip_persona. */
	private Persona ip_persona;

	/**  propiedad is_ssolicitud. */
	private Solicitud is_solicitud;

	/**  propiedad isi solicitud Interviniente. */
	private SolicitudInterviniente isi_solicitudInterviniente;

	/**  propiedad it_testamento. */
	private Testamento it_testamento;

	/**  propiedad it_testamento. */
	private Testamento it_testamentoAnterior;

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
	 * Método de obtención de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Método de asignación de la propiedad.
	 *
	 * @param as_s de as s
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return ip persona con el valor
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param ap_p con el valor a asignar
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return it_testamentoAnterior con el valor de la propiedad
	 */
	public Testamento getTestamentoAnterior()
	{
		return it_testamentoAnterior;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param at_ta con el valor a asignar
	 */
	public void setTestamentoAnterior(Testamento at_ta)
	{
		it_testamentoAnterior = at_ta;
	}

	/**
	 * *
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public SolicitudInterviniente getSolicitudInterviniente()
	{
		return isi_solicitudInterviniente;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param asi_si con el valor a asignar
	 */
	public void setSolicitudInterviniente(SolicitudInterviniente asi_si)
	{
		isi_solicitudInterviniente = asi_si;
	}

	/**
	 * *
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public InteresadoDocumentoTipo getInteresadoDocumentoTipo()
	{
		return iidt_interesadoDocumentoTipo;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param aidt_idt con el valor a asignar
	 */
	public void setInteresadoDocumentoTipo(InteresadoDocumentoTipo aidt_idt)
	{
		iidt_interesadoDocumentoTipo = aidt_idt;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public CalidadSolicitante getCalidadSolicitante()
	{
		return ics_calidadSolicitante;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param acs_cs con el valor a asignar
	 */
	public void setCalidadSolicitante(CalidadSolicitante acs_cs)
	{
		ics_calidadSolicitante = acs_cs;
	}

	/**
	 * Método de obtención del valor de la variable.
	 *
	 * @return con el valor de la variable
	 */
	public LibroTestamento getLibroTestamento()
	{
		return ilt_libroTestamento;
	}

	/**
	 * Método de asignación del valor de ela variable.
	 *
	 * @param alt_lt coon el valor a asignar
	 */
	public void setLibroTestamento(LibroTestamento alt_lt)
	{
		this.ilt_libroTestamento = alt_lt;
	}

	/**
	 * Método de obtención de valor de la propiedad.
	 *
	 * @return id_documentoAnterior de tipo Documento
	 */
	public Documento getDocumentoAnterior()
	{
		return id_documentoAnterior;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param ad_d de tipo Documento con el valor a asignar
	 */
	public void setDocumentoAnterior(Documento ad_d)
	{
		id_documentoAnterior = ad_d;
	}
}
