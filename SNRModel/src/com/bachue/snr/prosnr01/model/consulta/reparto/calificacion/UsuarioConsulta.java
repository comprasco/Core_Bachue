package com.bachue.snr.prosnr01.model.consulta.reparto.calificacion;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades UsuarioConsulta.
 *
 * @author Julian Vaca
 */
public class UsuarioConsulta extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2704596646011779416L;

	/** Propiedad iccrc consulta reparto calificacion. */
	private Collection<ConsultaRepartoCalificacion> iccrc_consultaRepartoCalificacion;

	/** Propiedad ii identidicador. */
	private Integer ii_identidicador;

	/** Propiedad is nombre completo. */
	private String is_nombreCompleto;

	/** Propiedad iu usuario. */
	private Usuario iu_usuario;

	/**
	 * Retorna el valor de cantidad turnos.
	 *
	 * @return el valor de cantidad turnos
	 */
	public long getCantidadTurnos()
	{
		return CollectionUtils.isValidCollection(iccrc_consultaRepartoCalificacion)
		? iccrc_consultaRepartoCalificacion.size() : 0;
	}

	/**
	 * Modifica el valor de consulta reparto calificacion.
	 *
	 * @param accrc_ccrc asigna el valor a la propiedad consulta reparto calificacion
	 */
	public void setConsultaRepartoCalificacion(Collection<ConsultaRepartoCalificacion> accrc_ccrc)
	{
		iccrc_consultaRepartoCalificacion = accrc_ccrc;
	}

	/**
	 * Retorna el valor de consulta reparto calificacion.
	 *
	 * @return el valor de consulta reparto calificacion
	 */
	public Collection<ConsultaRepartoCalificacion> getConsultaRepartoCalificacion()
	{
		return iccrc_consultaRepartoCalificacion;
	}

	/**
	 * Modifica el valor de identidicador.
	 *
	 * @param ai_i asigna el valor a la propiedad identidicador
	 */
	public void setIdentidicador(Integer ai_i)
	{
		ii_identidicador = ai_i;
	}

	/**
	 * Retorna el valor de identidicador.
	 *
	 * @return el valor de identidicador
	 */
	public Integer getIdentidicador()
	{
		return ii_identidicador;
	}

	/**
	 * Modifica el valor de nombre completo.
	 *
	 * @param as_s asigna el valor a la propiedad nombre completo
	 */
	public void setNombreCompleto(String as_s)
	{
		this.is_nombreCompleto = as_s;
	}

	/**
	 * Retorna el valor de nombre completo.
	 *
	 * @return el valor de nombre completo
	 */
	public String getNombreCompleto()
	{
		return is_nombreCompleto;
	}

	/**
	 * Modifica el valor de usuario.
	 *
	 * @param au_u asigna el valor a la propiedad usuario
	 */
	public void setUsuario(Usuario au_u)
	{
		iu_usuario = au_u;
	}

	/**
	 * Retorna el valor de usuario.
	 *
	 * @return el valor de usuario
	 */
	public Usuario getUsuario()
	{
		return iu_usuario;
	}
}
