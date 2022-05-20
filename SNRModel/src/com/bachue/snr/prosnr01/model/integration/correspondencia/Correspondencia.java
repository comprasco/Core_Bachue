package com.bachue.snr.prosnr01.model.integration.correspondencia;

import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades Correspondencia.
 *
 * @author Carlos Calder�n
 */
public class Correspondencia implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5071628798475018701L;

	/** Propiedad Colecci�n de TipoDocumento ictd destinatarios. */
	private Collection<TipoDestinatario> ictd_destinatarios;

	/** Propiedad Colecci�n de String ictd documentos. */
	private Collection<String> ictd_documentos;

	/** Propiedad is canal. */
	private String is_canal;

	/** Propiedad is clasificacion. */
	private String is_clasificacion;

	/** Propiedad is id orip. */
	private String is_idOrip;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is numeroFolios. */
	private String is_numeroFolios;

	/** Propiedad is turno. */
	private String is_turno;

	/**
	 * Clasificaci�n de la comunicaci�n.
	 *
	 * @return String Clasificaci�n de la comunicaci�n.
	 */
	public String getClasificacion()
	{
		return is_clasificacion;
	}

	/**
	 * Clasificaci�n de la comunicaci�n.
	 *
	 * @param as_s de as s
	 */
	public void setClasificacion(String as_s)
	{
		is_clasificacion = as_s;
	}

	/**
	 * Canal de la comunicaci�n.
	 *
	 * @return String Clasificaci�n de la comunicaci�n.
	 */
	public String getCanal()
	{
		return is_canal;
	}

	/**
	 * Canal de la comunicaci�n.
	 *
	 * @param as_s de as s
	 */
	public void setCanal(String as_s)
	{
		is_canal = as_s;
	}

	/**
	 * C�digo ID del circulo registral de la oficina de registro de instrumentos p�blicos.
	 * ej: 425,50C.
	 *
	 * @return String C�digo ID del circulo registral de la oficina de registro de instrumentos p�blicos.
	 */
	public String getIdOrip()
	{
		return is_idOrip;
	}

	/**
	 * C�digo ID del circulo registral de la oficina de registro de instrumentos p�blicos.
	 * ej: 425,50C.
	 *
	 * @param as_s String C�digo ID del circulo registral de la oficina de registro de instrumentos p�blicos.
	 */
	public void setIdOrip(String as_s)
	{
		is_idOrip = as_s;
	}

	/**
	 * N�mero de identificaci�n registral al que est� asociado el documento.
	 * ej: SNR2019000004357
	 *
	 * @param as_s String N�mero de identificaci�n registral al que est� asociado el documento.
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * N�mero de identificaci�n registral al que est� asociado el documento.
	 * ej: SNR201900000XXXX
	 *
	 * @return String N�mero de identificaci�n registral al que est� asociado el documento.
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * N�mero de identificaci�n del turno al que est� asociado el documento.
	 * ej: 2019-425-6-XXXX
	 *
	 * @param as_s String N�mero de identificaci�n del turno al que est� asociado el documento.
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * N�mero de identificaci�n del turno al que est� asociado el documento.
	 * ej: 2019-425-6-XXXX
	 *
	 * @return String N�mero de identificaci�n del turno al que est� asociado el documento.
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * N�mero de folios o p�ginas de un documento.
	 * ej. 34
	 *
	 * @return el valor de string N�mero de folios o p�ginas de un documento.
	 */
	public String getNumeroFolios()
	{
		return is_numeroFolios;
	}

	/**
	 * Modifica el valor de N�mero de folios o p�ginas de un documento.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad Folio
	 */
	public void setNumeroFolios(String as_s)
	{
		is_numeroFolios = as_s;
	}

	/**
	 * Lista con la informaci�n de los documentos vinculados al NIR o Turno.
	 *
	 * @return ictd_documentos Colecci�n de tipo String con valores de la Colecci�n Documentos.
	 */
	public Collection<String> getDocumentos()
	{
		return ictd_documentos;
	}

	/**
	 * Lista con la informaci�n de los documentos vinculados al NIR o Turno.
	 *
	 * @param actd_ctd Colecci�n de tipo String que asigna valores a la Colecci�n Documentos.
	 */
	public void setDocumentos(Collection<String> actd_ctd)
	{
		ictd_documentos = actd_ctd;
	}

	/**
	 * Informaci�n de los destinatarios.
	 *
	 * @return ictd_destinatarios Colecci�n de tipo TipoDestinatario con valores de la Colecci�n Destinatarios.
	 */
	public Collection<TipoDestinatario> getDestinatarios()
	{
		return ictd_destinatarios;
	}

	/**
	 * Informaci�n de los destinatarios.
	 *
	 * @param actd_ctd Colecci�n de tipo TipoDestinatario que asigna valores a la Colecci�n Destinatarios.
	 */
	public void setDestinatarios(Collection<TipoDestinatario> actd_ctd)
	{
		ictd_destinatarios = actd_ctd;
	}
}
