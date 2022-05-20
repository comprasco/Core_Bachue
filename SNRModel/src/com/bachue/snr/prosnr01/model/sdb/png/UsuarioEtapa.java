package com.bachue.snr.prosnr01.model.sdb.png;

import com.b2bsg.common.util.NumericUtils;
import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de UsuarioEtapa.
 *
 * @author Julian Vaca
 */
public class UsuarioEtapa extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7299446119190230849L;

	/** Propiedad ie etapa. */
	private Etapa ie_etapa;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad id etapa. */
	private String is_idEtapa;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad iu usuario. */
	private Usuario iu_usuario;

	/** Propiedad il acumulado. */
	private long il_acumulado;

	/**
	 * Instancia un nuevo objeto usuario etapa.
	 */
	public UsuarioEtapa()
	{
	}

	/**
	 * Instancia un nuevo objeto usuario etapa.
	 *
	 * @param as_UsuarioLogin de as usuario login
	 * @param as_idEtapa de as id etapa
	 * @param as_activo de as activo
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 */
	public UsuarioEtapa(
	    String as_UsuarioLogin, String as_idEtapa, String as_activo, String as_userId, String as_remoteIp
	)
	{
		setIdUsuario(as_UsuarioLogin);setUsuario(new Usuario(as_UsuarioLogin));
		setIdEtapa(as_idEtapa);setEtapa(new Etapa(NumericUtils.getLong(as_idEtapa)));
		setAcumulado(0);
		setActivo(as_activo);
		setIdUsuarioCreacion(as_userId);
		setIdUsuarioModificacion(as_userId);
		setIpCreacion(as_remoteIp);
		setIpModificacion(as_remoteIp);
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Modifica el valor de Acumulado.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setAcumulado(long al_l)
	{
		il_acumulado = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor acumulado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getAcumulado()
	{
		return il_acumulado;
	}

	/**
	 * Modifica el valor de Etapa.
	 *
	 * @param ae_e asigna el valor a la propiedad
	 */
	public void setEtapa(Etapa ae_e)
	{
		ie_etapa = ae_e;
	}

	/**
	 * Retorna Objeto o variable de valor etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Etapa getEtapa()
	{
		if(ie_etapa == null)
			ie_etapa = new Etapa();

		return ie_etapa;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param as_s de as s
	 */
	public void setIdEtapa(String as_s)
	{
		is_idEtapa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public String getIdEtapa()
	{
		return is_idEtapa;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s de as s
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return el valor de id usuario
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param au_u asigna el valor a la propiedad
	 */
	public void setUsuario(Usuario au_u)
	{
		iu_usuario = au_u;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Usuario getUsuario()
	{
		if(iu_usuario == null)
			iu_usuario = new Usuario();

		return iu_usuario;
	}
}
