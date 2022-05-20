package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.registro.Direccion;

import java.io.Serializable;



/**
 * Abstracción de la tabla SDB_ACC_SOLICITUD_DIRECCION_CERTIFICADO.
 *
 * @author Manuel Blanco
 */
public class SolicitudDireccionCertificado extends Direccion implements Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = 4294212910940429914L;

	/**
	 * Propiedad il id turno historia.
	 */
	private Long il_idTurnoHistoria;

	/**
	 * Propiedad is id solicitud.
	 */
	private String is_idSolicitud;

	/**
	 * Propiedad is id turno.
	 */
	private String is_idTurno;

	/**
	 * Propiedad is id vereda.
	 */
	private String is_idVereda;

	/**
	 * Propiedad is nombre predio.
	 */
	private String is_nombrePredio;

	/**
	 * Propiedad ib activo.
	 */
	private boolean ib_activo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param ab_b Objeto o Variable de tipo boolean que asigna un valor a la propiedad Activo
	 */
	public void setActivo(boolean ab_b)
	{
		ib_activo = ab_b;
	}

	/**
	 * Valida la propiedad activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isActivo()
	{
		return ib_activo;
	}

	/**
	 * Modifica el valor de Id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Get id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de Id turno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Get id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de Id turno historia.
	 *
	 * @param al_l Objeto o Variable de tipo Long que asigna un valor a la propiedad IdTurnoHistoria
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Get id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de Id vereda.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdVereda(String as_s)
	{
		is_idVereda = as_s;
	}

	/**
	 * Get id vereda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdVereda()
	{
		return is_idVereda;
	}

	/**
	 * Modifica el valor de Nombre predio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombrePredio(String as_s)
	{
		is_nombrePredio = as_s;
	}

	/**
	 * Get nombre predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
	}
}
