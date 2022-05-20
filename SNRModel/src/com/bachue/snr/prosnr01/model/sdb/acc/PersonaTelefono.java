package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PERSONA_TELEFONO.
 *
 * @author hcastaneda
 */
public class PersonaTelefono extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID  = 3587080323082329791L;
	
	/** Propiedad id fecha desde. */
	private Date              id_fechaDesde;
	
	/** Propiedad id fecha hasta. */
	private Date              id_fechaHasta;
	
	/** Propiedad is id departamento. */
	private String            is_idDepartamento;
	
	/** Propiedad is id pais. */
	private String            is_idPais;
	
	/** Propiedad is id persona. */
	private String            is_idPersona;
	
	/** Propiedad is id telefono. */
	private String            is_idTelefono;
	
	/** Propiedad is id usuario. */
	private String            is_idUsuario;
	
	/** Propiedad is indicativo. */
	private String            is_indicativo;
	
	/** Propiedad is telefono. */
	private String            is_telefono;
	
	/** Propiedad is tipo telefono. */
	private String            is_tipoTelefono;

	/**
	 * Constructor por defecto.
	 */
	public PersonaTelefono()
	{
	}

	/**
	 * Constructor que recibe como parametro el id del pais y el telefono.
	 *
	 * @param as_idPais de as id pais
	 * @param as_telefono de as telefono
	 */
	public PersonaTelefono(String as_idPais, String as_telefono)
	{
		is_idPais                               = as_idPais;
		is_telefono                             = as_telefono;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de IdTelefono.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTelefono(String as_s)
	{
		is_idTelefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id telefono.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTelefono()
	{
		return is_idTelefono;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de Indicativo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicativo(String as_s)
	{
		is_indicativo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicativo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicativo()
	{
		return is_indicativo;
	}

	/**
	 * Modifica el valor de Telefono.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTelefono(String as_s)
	{
		is_telefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor telefono.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTelefono()
	{
		return is_telefono;
	}

	/**
	 * Modifica el valor de TipoTelefono.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoTelefono(String as_s)
	{
		is_tipoTelefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo telefono.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoTelefono()
	{
		return is_tipoTelefono;
	}
}
