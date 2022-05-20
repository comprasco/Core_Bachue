package com.bachue.snr.prosnr01.model.integration.correspondencia;

import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades Correspondencia.
 *
 * @author Carlos Calderón
 */
public class Correspondencia implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5071628798475018701L;

	/** Propiedad Colección de TipoDocumento ictd destinatarios. */
	private Collection<TipoDestinatario> ictd_destinatarios;

	/** Propiedad Colección de String ictd documentos. */
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
	 * Clasificación de la comunicación.
	 *
	 * @return String Clasificación de la comunicación.
	 */
	public String getClasificacion()
	{
		return is_clasificacion;
	}

	/**
	 * Clasificación de la comunicación.
	 *
	 * @param as_s de as s
	 */
	public void setClasificacion(String as_s)
	{
		is_clasificacion = as_s;
	}

	/**
	 * Canal de la comunicación.
	 *
	 * @return String Clasificación de la comunicación.
	 */
	public String getCanal()
	{
		return is_canal;
	}

	/**
	 * Canal de la comunicación.
	 *
	 * @param as_s de as s
	 */
	public void setCanal(String as_s)
	{
		is_canal = as_s;
	}

	/**
	 * Código ID del circulo registral de la oficina de registro de instrumentos públicos.
	 * ej: 425,50C.
	 *
	 * @return String Código ID del circulo registral de la oficina de registro de instrumentos públicos.
	 */
	public String getIdOrip()
	{
		return is_idOrip;
	}

	/**
	 * Código ID del circulo registral de la oficina de registro de instrumentos públicos.
	 * ej: 425,50C.
	 *
	 * @param as_s String Código ID del circulo registral de la oficina de registro de instrumentos públicos.
	 */
	public void setIdOrip(String as_s)
	{
		is_idOrip = as_s;
	}

	/**
	 * Número de identificación registral al que está asociado el documento.
	 * ej: SNR2019000004357
	 *
	 * @param as_s String Número de identificación registral al que está asociado el documento.
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Número de identificación registral al que está asociado el documento.
	 * ej: SNR201900000XXXX
	 *
	 * @return String Número de identificación registral al que está asociado el documento.
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Número de identificación del turno al que está asociado el documento.
	 * ej: 2019-425-6-XXXX
	 *
	 * @param as_s String Número de identificación del turno al que está asociado el documento.
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * Número de identificación del turno al que está asociado el documento.
	 * ej: 2019-425-6-XXXX
	 *
	 * @return String Número de identificación del turno al que está asociado el documento.
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * Número de folios o páginas de un documento.
	 * ej. 34
	 *
	 * @return el valor de string Número de folios o páginas de un documento.
	 */
	public String getNumeroFolios()
	{
		return is_numeroFolios;
	}

	/**
	 * Modifica el valor de Número de folios o páginas de un documento.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad Folio
	 */
	public void setNumeroFolios(String as_s)
	{
		is_numeroFolios = as_s;
	}

	/**
	 * Lista con la información de los documentos vinculados al NIR o Turno.
	 *
	 * @return ictd_documentos Colección de tipo String con valores de la Colección Documentos.
	 */
	public Collection<String> getDocumentos()
	{
		return ictd_documentos;
	}

	/**
	 * Lista con la información de los documentos vinculados al NIR o Turno.
	 *
	 * @param actd_ctd Colección de tipo String que asigna valores a la Colección Documentos.
	 */
	public void setDocumentos(Collection<String> actd_ctd)
	{
		ictd_documentos = actd_ctd;
	}

	/**
	 * Información de los destinatarios.
	 *
	 * @return ictd_destinatarios Colección de tipo TipoDestinatario con valores de la Colección Destinatarios.
	 */
	public Collection<TipoDestinatario> getDestinatarios()
	{
		return ictd_destinatarios;
	}

	/**
	 * Información de los destinatarios.
	 *
	 * @param actd_ctd Colección de tipo TipoDestinatario que asigna valores a la Colección Destinatarios.
	 */
	public void setDestinatarios(Collection<TipoDestinatario> actd_ctd)
	{
		ictd_destinatarios = actd_ctd;
	}
}
