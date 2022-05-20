package com.bachue.snr.prosnr01.model.sdb.aut;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Clase de abstracción para la tabla SDB_AUT_ROL_OPCION.
 *
 * @author Duvan Beltrán
 */
public class RolOpcion extends Auditoria implements Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = 4602125375019494539L;

	/**
	 * Propiedad icro data rol opcion.
	 */
	private Collection<RolOpcion> icro_dataRolOpcion;

	/**
	 * Propiedad id fecha desde.
	 */
	private Date id_fechaDesde;

	/**
	 * Propiedad id fecha hasta.
	 */
	private Date id_fechaHasta;

	/**
	 * Propiedad is activo.
	 */
	private String is_activo;

	/**
	 * Propiedad is id opcion.
	 */
	private String is_idOpcion;

	/**
	 * Propiedad is id rol.
	 */
	private String is_idRol;

	/** Propiedad is nombre opcion. */
	private String is_nombreOpcion;

	/** Propiedad is nombre rol. */
	private String is_nombreRol;

	/**
	 * Propiedad is opcion.
	 */
	private String is_opcion;

	/**
	 * Constructor por defecto.
	 */
	public RolOpcion()
	{
	}

	/**
	 * Constructor de clase RolOpcion.
	 *
	 * @param as_idRol de as id rol
	 * @param as_idOpcion de as id opcion
	 * @param as_ip de as ip
	 * @param as_usuario de as usuario
	 * @param ad_fechaActual de ad fecha actual
	 * @param as_activo de as activo
	 */
	public RolOpcion(
	    String as_idRol, String as_idOpcion, String as_ip, String as_usuario, Date ad_fechaActual, String as_activo
	)
	{
		is_idRol        = as_idRol;
		is_idOpcion     = as_idOpcion;
		setIpModificacion(as_ip);
		setIdUsuarioModificacion(as_usuario);
		setIdUsuarioCreacion(as_usuario);
		setIpCreacion(as_ip);
		setFechaCreacion(ad_fechaActual);
		id_fechaDesde     = ad_fechaActual;
		is_activo         = as_activo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_a Objeto o Variable de tipo String que asigna un valor a la propiedad Activo
	 */
	public void setActivo(String as_a)
	{
		is_activo = as_a;
	}

	/**
	 * Get activo.
	 *
	 * @return el valor de string
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Data rol opcion.
	 *
	 * @param acro_dataRolOpciopn Objeto o Variable de tipo Collection que asigna un valor a la propiedad DataRolOpcion
	 */
	public void setDataRolOpcion(Collection<RolOpcion> acro_dataRolOpciopn)
	{
		icro_dataRolOpcion = acro_dataRolOpciopn;
	}

	/**
	 * Get data rol opcion.
	 *
	 * @return el valor de collection
	 */
	public Collection<RolOpcion> getDataRolOpcion()
	{
		return icro_dataRolOpcion;
	}

	/**
	 * Modifica el valor de Fecha desde.
	 *
	 * @param ad_a Objeto o Variable de tipo Date que asigna un valor a la propiedad FechaDesde
	 */
	public void setFechaDesde(Date ad_a)
	{
		id_fechaDesde = ad_a;
	}

	/**
	 * Get fecha desde.
	 *
	 * @return el valor de date
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de Fecha hasta.
	 *
	 * @param ad_a Objeto o Variable de tipo Date que asigna un valor a la propiedad FechaHasta
	 */
	public void setFechaHasta(Date ad_a)
	{
		id_fechaHasta = ad_a;
	}

	/**
	 * Get fecha hasta.
	 *
	 * @return el valor de date
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de Id opcion.
	 *
	 * @param as_a Objeto o Variable de tipo String que asigna un valor a la propiedad IdOpcion
	 */
	public void setIdOpcion(String as_a)
	{
		is_idOpcion = as_a;
	}

	/**
	 * Get id opcion.
	 *
	 * @return el valor de string
	 */
	public String getIdOpcion()
	{
		return is_idOpcion;
	}

	/**
	 * Modifica el valor de Id rol.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdRol
	 */
	public void setIdRol(String as_s)
	{
		is_idRol = as_s;
	}

	/**
	 * Get id rol.
	 *
	 * @return el valor de string
	 */
	public String getIdRol()
	{
		return is_idRol;
	}

	/**
	 * Retorna Objeto o variable de valor opcion.
	 *
	 * @return Retorna el valor de la propiedad opcion
	 */
	public String getOpcion()
	{
		return is_opcion;
	}

	/**
	 * Modifica el valor de Opcion.
	 *
	 * @param as_s de as s
	 */
	public void setOpcion(String as_s)
	{
		is_opcion = as_s;
	}

	/**
	 * Get nombre opcion.
	 *
	 * @return el valor de string
	 */
	public String getNombreOpcion()
	{
		return is_nombreOpcion;
	}

	/**
	 * Modifica el valor de nombre opcion.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad nombreOpcion
	 */
	public void setNombreOpcion(String as_s)
	{
		is_nombreOpcion = as_s;
	}

	/**
	 * Get nombre rol.
	 *
	 * @return el valor de string
	 */
	public String getNombreRol()
	{
		return is_nombreRol;
	}

	/**
	 * Modifica el valor de nombre rol.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad nombreRol
	 */
	public void setNombreRol(String as_s)
	{
		is_nombreRol = as_s;
	}
}
