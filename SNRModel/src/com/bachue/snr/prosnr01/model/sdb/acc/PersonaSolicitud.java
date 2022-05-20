package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de PersonaSolicitud.
 *
 * @author ccalderon
 */
public class PersonaSolicitud extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 6236460489674717011L;
	
	/** Propiedad id fecha entrega. */
	private Date              id_fechaEntrega;
	
	/** Propiedad is id documento tipo. */
	private String            is_idDocumentoTipo;
	
	/** Propiedad is id persona entrega. */
	private String            is_idPersonaEntrega;
	
	/** Propiedad is id persona tercero. */
	private String            is_idPersonaTercero;
	
	/** Propiedad is nombre calidad. */
	private String            is_nombreCalidad;
	
	/** Propiedad is numero documento. */
	private String            is_numeroDocumento;
	
	/** Propiedad is primer apellido. */
	private String            is_primerApellido;
	
	/** Propiedad is primer nombre. */
	private String            is_primerNombre;
	
	/** Propiedad is razon social. */
	private String            is_razonSocial;
	
	/** Propiedad is segundo apellido. */
	private String            is_segundoApellido;
	
	/** Propiedad is segundo nombre. */
	private String            is_segundoNombre;
	
	/** Propiedad is turno. */
	private String            is_turno;

	/**
	 * Instancia un nuevo objeto persona solicitud.
	 */
	public PersonaSolicitud()
	{
	}

	/**
	 * Modifica el valor de FechaEntrega.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaEntrega(Date ad_d)
	{
		id_fechaEntrega                           = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaEntrega()
	{
		return id_fechaEntrega;
	}

	/**
	 * Modifica el valor de IdDocumentoTipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		is_idDocumentoTipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Modifica el valor de IdPersonaEntrega.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersonaEntrega(String as_s)
	{
		is_idPersonaEntrega = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersonaEntrega()
	{
		return is_idPersonaEntrega;
	}

	/**
	 * Modifica el valor de IdPersonaTercero.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersonaTercero(String as_s)
	{
		is_idPersonaTercero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona tercero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersonaTercero()
	{
		return is_idPersonaTercero;
	}

	/**
	 * Modifica el valor de NombreCalidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreCalidad(String as_s)
	{
		is_nombreCalidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre calidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCalidad()
	{
		return is_nombreCalidad;
	}

	/**
	 * Modifica el valor de NumeroDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
	}

	/**
	 * Modifica el valor de PrimerApellido.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPrimerApellido(String as_s)
	{
		is_primerApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor primer apellido.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPrimerApellido()
	{
		return is_primerApellido;
	}

	/**
	 * Modifica el valor de PrimerNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPrimerNombre(String as_s)
	{
		is_primerNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor primer nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPrimerNombre()
	{
		return is_primerNombre;
	}

	/**
	 * Modifica el valor de RazonSocial.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRazonSocial(String as_s)
	{
		is_razonSocial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor razon social.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
	}

	/**
	 * Modifica el valor de SegundoApellido.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSegundoApellido(String as_s)
	{
		is_segundoApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo apellido.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSegundoApellido()
	{
		return is_segundoApellido;
	}

	/**
	 * Modifica el valor de SegundoNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSegundoNombre(String as_s)
	{
		is_segundoNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSegundoNombre()
	{
		return is_segundoNombre;
	}

	/**
	 * Modifica el valor de Turno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTurno()
	{
		return is_turno;
	}
}
