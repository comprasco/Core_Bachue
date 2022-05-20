package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Class que contiene todos las propiedades DocumentoData.
 *
 * @author hcastaneda
 */
public class DocumentoData extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 238040178783167080L;

	/** Propiedad icd listado documentos. */
	private Collection<Documento> icd_listadoDocumentos;

	/** Propiedad ld fecha documento. */
	private Date ld_fechaDocumento;

	/** Propiedad ls departamento. */
	private String ls_departamento;

	/** Propiedad ls entidad exenta. */
	private String ls_entidadExenta;

	/** Propiedad ls municipio. */
	private String ls_municipio;

	/** Propiedad ls numero documento. */
	private String ls_numeroDocumento;

	/** Propiedad ls oficina origen. */
	private String ls_oficinaOrigen;

	/** Propiedad ls pais. */
	private String ls_pais;

	/** Propiedad ls tipo documento. */
	private String ls_tipoDocumento;

	/** Propiedad ls tipo entidad. */
	private String ls_tipoEntidad;

	/** Propiedad ls tipo oficina. */
	private String ls_tipoOficina;

	/**
	 * Modifica el valor de departamento.
	 *
	 * @param as_s asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(String as_s)
	{
		ls_departamento = as_s;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public String getDepartamento()
	{
		return ls_departamento;
	}

	/**
	 * Modifica el valor de entidad exenta.
	 *
	 * @param as_s asigna el valor a la propiedad entidad exenta
	 */
	public void setEntidadExenta(String as_s)
	{
		ls_entidadExenta = as_s;
	}

	/**
	 * Retorna el valor de entidad exenta.
	 *
	 * @return el valor de entidad exenta
	 */
	public String getEntidadExenta()
	{
		return ls_entidadExenta;
	}

	/**
	 * Modifica el valor de fecha documento.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha documento
	 */
	public void setFechaDocumento(Date ad_d)
	{
		ld_fechaDocumento = ad_d;
	}

	/**
	 * Retorna el valor de fecha documento.
	 *
	 * @return el valor de fecha documento
	 */
	public Date getFechaDocumento()
	{
		return ld_fechaDocumento;
	}

	/**
	 * Modifica el valor de listado documentos.
	 *
	 * @param acd_cd asigna el valor a la propiedad listado documentos
	 */
	public void setListadoDocumentos(Collection<Documento> acd_cd)
	{
		icd_listadoDocumentos = acd_cd;
	}

	/**
	 * Retorna el valor de listado documentos.
	 *
	 * @return el valor de listado documentos
	 */
	public Collection<Documento> getListadoDocumentos()
	{
		return icd_listadoDocumentos;
	}

	/**
	 * Modifica el valor de municipio.
	 *
	 * @param as_s asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(String as_s)
	{
		ls_municipio = as_s;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @return el valor de municipio
	 */
	public String getMunicipio()
	{
		return ls_municipio;
	}

	/**
	 * Modifica el valor de numero documento.
	 *
	 * @param as_s asigna el valor a la propiedad numero documento
	 */
	public void setNumeroDocumento(String as_s)
	{
		ls_numeroDocumento = as_s;
	}

	/**
	 * Retorna el valor de numero documento.
	 *
	 * @return el valor de numero documento
	 */
	public String getNumeroDocumento()
	{
		return ls_numeroDocumento;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(String as_s)
	{
		ls_oficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public String getOficinaOrigen()
	{
		return ls_oficinaOrigen;
	}

	/**
	 * Modifica el valor de pais.
	 *
	 * @param as_s asigna el valor a la propiedad pais
	 */
	public void setPais(String as_s)
	{
		ls_pais = as_s;
	}

	/**
	 * Retorna el valor de pais.
	 *
	 * @return el valor de pais
	 */
	public String getPais()
	{
		return ls_pais;
	}

	/**
	 * Modifica el valor de tipo documento.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(String as_s)
	{
		ls_tipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de tipo documento.
	 *
	 * @return el valor de tipo documento
	 */
	public String getTipoDocumento()
	{
		return ls_tipoDocumento;
	}

	/**
	 * Modifica el valor de tipo entidad.
	 *
	 * @param as_s asigna el valor a la propiedad tipo entidad
	 */
	public void setTipoEntidad(String as_s)
	{
		ls_tipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de tipo entidad.
	 *
	 * @return el valor de tipo entidad
	 */
	public String getTipoEntidad()
	{
		return ls_tipoEntidad;
	}

	/**
	 * Modifica el valor de tipo oficina.
	 *
	 * @param as_s asigna el valor a la propiedad tipo oficina
	 */
	public void setTipoOficina(String as_s)
	{
		ls_tipoOficina = as_s;
	}

	/**
	 * Retorna el valor de tipo oficina.
	 *
	 * @return el valor de tipo oficina
	 */
	public String getTipoOficina()
	{
		return ls_tipoOficina;
	}
}
