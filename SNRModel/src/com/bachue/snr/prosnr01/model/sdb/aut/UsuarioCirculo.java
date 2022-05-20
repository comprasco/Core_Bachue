package com.bachue.snr.prosnr01.model.sdb.aut;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_AUT_USUARIO_CIRCULO.
 *
 * @author Julian Vaca
 */
public class UsuarioCirculo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6545024591021920374L;

	/** Propiedad icr circulo. */
	private CirculoRegistral icr_circulo;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad iu usuario. */
	private Usuario iu_usuario;

	/**
	 * Constructor por defecto.
	 */
	public UsuarioCirculo()
	{
	}

	/**
	 * Instancia un nuevo objeto usuario circulo.
	 *
	 * @param as_usuarioLogin de as usuario login
	 * @param as_idCirculo de as id circulo
	 * @param as_activo de as activo
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 */
	public UsuarioCirculo(
	    String as_usuarioLogin, String as_idCirculo, String as_activo, String as_userId, String as_remoteIp
	)
	{
		setIdUsuario(as_usuarioLogin);
		setIdCirculo(as_idCirculo);
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
	public UsuarioCirculo(String as_idUsuario)
	{
		setIdUsuario(as_idUsuario);
	}

	/**
	 * Constructor que recibe como parametro el id de usuario y id circulo.
	 *
	 * @param as_idUsuario id usuario
	 * @param as_idCirculo id circulo
	 */
	public UsuarioCirculo(String as_idUsuario, String as_idCirculo)
	{
		setIdUsuario(as_idUsuario);
		setIdCirculo(as_idCirculo);
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo = as_s;
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
	 * Modifica el valor de Circulo.
	 *
	 * @param acr_cr asigna el valor a la propiedad
	 */
	public void setCirculo(CirculoRegistral acr_cr)
	{
		this.icr_circulo = acr_cr;
	}

	/**
	 * Retorna Objeto o variable de valor circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public CirculoRegistral getCirculo()
	{
		if(icr_circulo == null)
			icr_circulo = new CirculoRegistral();

		return icr_circulo;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		this.id_fechaDesde = ad_d;
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
		this.id_fechaHasta = ad_d;
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
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		this.is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		this.is_idUsuario = as_s;
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
	 * Modifica el valor de Usuario.
	 *
	 * @param usuario asigna el valor a la propiedad
	 */
	public void setUsuario(Usuario usuario)
	{
		this.iu_usuario = usuario;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Usuario getUsuario()
	{
		return iu_usuario;
	}
}
