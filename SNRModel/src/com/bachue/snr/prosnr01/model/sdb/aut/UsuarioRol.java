package com.bachue.snr.prosnr01.model.sdb.aut;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_AUT_USUARIO_ROL.
 *
 * @author Julian Vaca
 */
public class UsuarioRol extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1317632740588510559L;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is estado usuario. */
	private String is_estadoUsuario;

	/** Propiedad is id rol. */
	private String is_idRol;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public UsuarioRol()
	{
	}

	/**
	 * Instancia un nuevo objeto usuario rol.
	 *
	 * @param as_usuarioLogin de as usuario login
	 * @param as_idRol de as id rol
	 * @param as_activo de as activo
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 */
	public UsuarioRol(String as_usuarioLogin, String as_idRol, String as_activo, String as_userId, String as_remoteIp)
	{
		setIdUsuario(as_usuarioLogin);
		setIdRol(as_idRol);
		setActivo(as_activo);
		setIdUsuarioModificacion(as_userId);
		setIdUsuarioCreacion(as_userId);
		setIpModificacion(as_remoteIp);
		setIpCreacion(as_remoteIp);
	}

	/**
	 * Constructor que recibe como parametro el id de usuario.
	 *
	 * @param as_idUsuario de as id usuario
	 */
	public UsuarioRol(String as_idUsuario)
	{
		setIdUsuario(as_idUsuario);
	}

	/**
	 * Constructor que recibe como parametro el id de usuario y id rol.
	 *
	 * @param as_idUsuario id del usuario
	 * @param as_idRol id del rol asociado al usuario
	 */
	public UsuarioRol(String as_idUsuario, String as_idRol)
	{
		setIdUsuario(as_idUsuario);
		setIdRol(as_idRol);
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		this.is_activo = activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de EstadoUsuario.
	 *
	 * @param estadoUsuario asigna el valor a la propiedad
	 */
	public void setEstadoUsuario(String estadoUsuario)
	{
		this.is_estadoUsuario = estadoUsuario;
	}

	/**
	 * Retorna Objeto o variable de valor estado usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoUsuario()
	{
		return is_estadoUsuario;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param fechaDesde asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date fechaDesde)
	{
		this.id_fechaDesde = fechaDesde;
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
	 * @param fechaHasta asigna el valor a la propiedad
	 */
	public void setFechaHasta(Date fechaHasta)
	{
		this.id_fechaHasta = fechaHasta;
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
	 * Modifica el valor de IdRol.
	 *
	 * @param idRol asigna el valor a la propiedad
	 */
	public void setIdRol(String idRol)
	{
		this.is_idRol = idRol;
	}

	/**
	 * Retorna Objeto o variable de valor id rol.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRol()
	{
		return is_idRol;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param idUsuario asigna el valor a la propiedad
	 */
	public void setIdUsuario(String idUsuario)
	{
		this.is_idUsuario = idUsuario;
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
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		this.is_nombre = nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
