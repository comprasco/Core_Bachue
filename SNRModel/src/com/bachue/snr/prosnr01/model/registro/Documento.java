package com.bachue.snr.prosnr01.model.registro;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades Documento.
 *
 * @author Julian Vaca
 */
public class Documento implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4918368075222303718L;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad id fecha ejecutoria. */
	private Date id_fechaEjecutoria;

	/** Propiedad il departamento. */
	private Long il_departamento;

	/** Propiedad il municipio. */
	private Long il_municipio;

	/** Propiedad is documento. */
	private String is_documento;

	/** Propiedad is escritura. */
	private String is_escritura;

	/** Propiedad is nombre oficina. */
	private String is_nombreOficina;

	/** Propiedad is oficina origen. */
	private String is_oficinaOrigen;

	/** Propiedad is tipo entidad. */
	private String is_tipoEntidad;

	/** Propiedad is tipo oficina. */
	private String is_tipoOficina;

	/** Propiedad ib entidad exenta. */
	private boolean ib_entidadExenta;

	/** Propiedad ib oficina internacional. */
	private boolean ib_oficinaInternacional;

	/**
	 * Modifica el valor de departamento.
	 *
	 * @param al_l asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(Long al_l)
	{
		il_departamento = al_l;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public Long getDepartamento()
	{
		return il_departamento;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param as_s asigna el valor a la propiedad documento
	 */
	public void setDocumento(String as_s)
	{
		is_documento = as_s;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public String getDocumento()
	{
		return is_documento;
	}

	/**
	 * Modifica el valor de entidad exenta.
	 *
	 * @param ab_b asigna el valor a la propiedad entidad exenta
	 */
	public void setEntidadExenta(boolean ab_b)
	{
		ib_entidadExenta = ab_b;
	}

	/**
	 * Valida la propiedad entidad exenta.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en entidad exenta
	 */
	public boolean isEntidadExenta()
	{
		return ib_entidadExenta;
	}

	/**
	 * Modifica el valor de escritura.
	 *
	 * @param as_s asigna el valor a la propiedad escritura
	 */
	public void setEscritura(String as_s)
	{
		is_escritura = as_s;
	}

	/**
	 * Retorna el valor de escritura.
	 *
	 * @return el valor de escritura
	 */
	public String getEscritura()
	{
		return is_escritura;
	}

	/**
	 * Modifica el valor de fecha documento.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha documento
	 */
	public void setFechaDocumento(Date ad_d)
	{
		id_fechaDocumento = ad_d;
	}

	/**
	 * Retorna el valor de fecha documento.
	 *
	 * @return el valor de fecha documento
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Modifica el valor de fecha ejecutoria.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha ejecutoria
	 */
	public void setFechaEjecutoria(Date ad_d)
	{
		id_fechaEjecutoria = ad_d;
	}

	/**
	 * Retorna el valor de fecha ejecutoria.
	 *
	 * @return el valor de fecha ejecutoria
	 */
	public Date getFechaEjecutoria()
	{
		return id_fechaEjecutoria;
	}

	/**
	 * Modifica el valor de municipio.
	 *
	 * @param al_l asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(Long al_l)
	{
		il_municipio = al_l;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @return el valor de municipio
	 */
	public Long getMunicipio()
	{
		return il_municipio;
	}

	/**
	 * Modifica el valor de nombre oficina.
	 *
	 * @param as_s asigna el valor a la propiedad nombre oficina
	 */
	public void setNombreOficina(String as_s)
	{
		is_nombreOficina = as_s;
	}

	/**
	 * Retorna el valor de nombre oficina.
	 *
	 * @return el valor de nombre oficina
	 */
	public String getNombreOficina()
	{
		return is_nombreOficina;
	}

	/**
	 * Modifica el valor de oficina internacional.
	 *
	 * @param ab_b asigna el valor a la propiedad oficina internacional
	 */
	public void setOficinaInternacional(boolean ab_b)
	{
		ib_oficinaInternacional = ab_b;
	}

	/**
	 * Valida la propiedad oficina internacional.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en oficina internacional
	 */
	public boolean isOficinaInternacional()
	{
		return ib_oficinaInternacional;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(String as_s)
	{
		is_oficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public String getOficinaOrigen()
	{
		return is_oficinaOrigen;
	}

	/**
	 * Modifica el valor de tipo entidad.
	 *
	 * @param as_s asigna el valor a la propiedad tipo entidad
	 */
	public void setTipoEntidad(String as_s)
	{
		is_tipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de tipo entidad.
	 *
	 * @return el valor de tipo entidad
	 */
	public String getTipoEntidad()
	{
		return is_tipoEntidad;
	}

	/**
	 * Modifica el valor de tipo oficina.
	 *
	 * @param as_s asigna el valor a la propiedad tipo oficina
	 */
	public void setTipoOficina(String as_s)
	{
		is_tipoOficina = as_s;
	}

	/**
	 * Retorna el valor de tipo oficina.
	 *
	 * @return el valor de tipo oficina
	 */
	public String getTipoOficina()
	{
		return is_tipoOficina;
	}
}
