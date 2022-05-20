package com.bachue.snr.prosnr01.model.entrega;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Class que contiene todos las propiedades DatosPredioTurnoEntrega.
 *
 * @author Julian Vaca
 */
public class DatosPredioTurnoEntrega extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3598253228823568878L;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad is departamento. */
	private String is_departamento;

	/** Propiedad is entidad exenta. */
	private String is_entidadExenta;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is municipio. */
	private String is_municipio;

	/** Propiedad is nombre oficina. */
	private String is_nombreOficina;

	/** Propiedad is numero. */
	private String is_numero;

	/** Propiedad is pais. */
	private String is_pais;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/** Propiedad is tipo entidad. */
	private String is_tipoEntidad;

	/** Propiedad is tipo oficina. */
	private String is_tipoOficina;

	/**
	 * Modifica el valor de departamento.
	 *
	 * @param as_s asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(String as_s)
	{
		is_departamento = as_s;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public String getDepartamento()
	{
		return is_departamento;
	}

	/**
	 * Modifica el valor de entidad exenta.
	 *
	 * @param as_s asigna el valor a la propiedad entidad exenta
	 */
	public void setEntidadExenta(String as_s)
	{
		is_entidadExenta = as_s;
	}

	/**
	 * Retorna el valor de entidad exenta.
	 *
	 * @return el valor de entidad exenta
	 */
	public String getEntidadExenta()
	{
		return is_entidadExenta;
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
	 * Modifica el valor de municipio.
	 *
	 * @param as_s asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(String as_s)
	{
		is_municipio = as_s;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @return el valor de municipio
	 */
	public String getMunicipio()
	{
		return is_municipio;
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
	 * Modifica el valor de numero.
	 *
	 * @param as_s asigna el valor a la propiedad numero
	 */
	public void setNumero(String as_s)
	{
		is_numero = as_s;
	}

	/**
	 * Retorna el valor de numero.
	 *
	 * @return el valor de numero
	 */
	public String getNumero()
	{
		return is_numero;
	}

	/**
	 * Modifica el valor de pais.
	 *
	 * @param as_s asigna el valor a la propiedad pais
	 */
	public void setPais(String as_s)
	{
		is_pais = as_s;
	}

	/**
	 * Retorna el valor de pais.
	 *
	 * @return el valor de pais
	 */
	public String getPais()
	{
		return is_pais;
	}

	/**
	 * Modifica el valor de tipo documento.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de tipo documento.
	 *
	 * @return el valor de tipo documento
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
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
