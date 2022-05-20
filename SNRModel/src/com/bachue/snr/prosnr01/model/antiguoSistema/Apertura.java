package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Class que contiene todos las propiedades Apertura.
 *
 * @author lchacon
 */
public class Apertura implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8472266330745535352L;

	/** Propiedad ibd version. */
	private BigDecimal ibd_version;

	/** Propiedad id fecha apertura. */
	private Date id_fechaApertura;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad as id tipo oficina. */
	private String as_idTipoOficina;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id oficina origen. */
	private String is_idOficinaOrigen;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is radicacion. */
	private String is_radicacion;

	/** Propiedad ls id tipo entidad. */
	private String ls_idTipoEntidad;

	/** Propiedad ib es acto con ejecutoria. */
	private boolean ib_esActoConEjecutoria;

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
	 * Modifica el valor de es acto con ejecutoria.
	 *
	 * @param ab_b asigna el valor a la propiedad es acto con ejecutoria
	 */
	public void setEsActoConEjecutoria(boolean ab_b)
	{
		ib_esActoConEjecutoria = ab_b;
	}

	/**
	 * Valida la propiedad es acto con ejecutoria.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es acto con ejecutoria
	 */
	public boolean isEsActoConEjecutoria()
	{
		return ib_esActoConEjecutoria;
	}

	/**
	 * Modifica el valor de fecha apertura.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha apertura
	 */
	public void setFechaApertura(Date ad_d)
	{
		id_fechaApertura = ad_d;
	}

	/**
	 * Retorna el valor de fecha apertura.
	 *
	 * @return el valor de fecha apertura
	 */
	public Date getFechaApertura()
	{
		return id_fechaApertura;
	}

	/**
	 * Modifica el valor de id departamento.
	 *
	 * @param as_s asigna el valor a la propiedad id departamento
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna el valor de id departamento.
	 *
	 * @return el valor de id departamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de id municipio.
	 *
	 * @param as_s asigna el valor a la propiedad id municipio
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna el valor de id municipio.
	 *
	 * @return el valor de id municipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de id oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad id oficina origen
	 */
	public void setIdOficinaOrigen(String as_s)
	{
		is_idOficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de id oficina origen.
	 *
	 * @return el valor de id oficina origen
	 */
	public String getIdOficinaOrigen()
	{
		return is_idOficinaOrigen;
	}

	/**
	 * Modifica el valor de id pais.
	 *
	 * @param as_s asigna el valor a la propiedad id pais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna el valor de id pais.
	 *
	 * @return el valor de id pais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de id tipo entidad.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo entidad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		ls_idTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de id tipo entidad.
	 *
	 * @return el valor de id tipo entidad
	 */
	public String getIdTipoEntidad()
	{
		return ls_idTipoEntidad;
	}

	/**
	 * Modifica el valor de id tipo oficina.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo oficina
	 */
	public void setIdTipoOficina(String as_s)
	{
		as_idTipoOficina = as_s;
	}

	/**
	 * Retorna el valor de id tipo oficina.
	 *
	 * @return el valor de id tipo oficina
	 */
	public String getIdTipoOficina()
	{
		return as_idTipoOficina;
	}

	/**
	 * Modifica el valor de radicacion.
	 *
	 * @param as_s asigna el valor a la propiedad radicacion
	 */
	public void setRadicacion(String as_s)
	{
		is_radicacion = as_s;
	}

	/**
	 * Retorna el valor de radicacion.
	 *
	 * @return el valor de radicacion
	 */
	public String getRadicacion()
	{
		return is_radicacion;
	}

	/**
	 * Modifica el valor de version.
	 *
	 * @param abd_bd asigna el valor a la propiedad version
	 */
	public void setVersion(BigDecimal abd_bd)
	{
		ibd_version = abd_bd;
	}

	/**
	 * Retorna el valor de version.
	 *
	 * @return el valor de version
	 */
	public BigDecimal getVersion()
	{
		return ibd_version;
	}
}
