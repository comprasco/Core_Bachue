package com.bachue.snr.prosnr01.model.consulta.reparto.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.List;



/**
 * Class que contiene todos las propiedades UsuariosConsultados.
 *
 * @author Julian Vaca
 */
public class UsuariosConsultados extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5370260466744965064L;

	/** Propiedad iluc usuarios seleccionados. */
	private final List<UsuarioConsulta> iluc_usuariosSeleccionados;

	/** Propiedad iluc usuarios total. */
	private final List<UsuarioConsulta> iluc_usuariosTotal;

	/** Propiedad is identificador. */
	private final String is_identificador;

	/**
	 * Instancia un nuevo objeto usuarios consultados.
	 *
	 * @param identificador de identificador
	 * @param usuariosTotal de usuarios total
	 * @param usuariosSeleccionados de usuarios seleccionados
	 */
	public UsuariosConsultados(
	    final String identificador, final List<UsuarioConsulta> usuariosTotal,
	    final List<UsuarioConsulta> usuariosSeleccionados
	)
	{
		iluc_usuariosSeleccionados     = usuariosSeleccionados;
		iluc_usuariosTotal             = usuariosTotal;
		is_identificador               = identificador;
	}

	/**
	 * Retorna el valor de identificador.
	 *
	 * @return el valor de identificador
	 */
	public String getIdentificador()
	{
		return is_identificador;
	}

	/**
	 * Retorna el valor de usuarios seleccionados.
	 *
	 * @return el valor de usuarios seleccionados
	 */
	public List<UsuarioConsulta> getUsuariosSeleccionados()
	{
		return iluc_usuariosSeleccionados;
	}

	/**
	 * Retorna el valor de usuarios total.
	 *
	 * @return el valor de usuarios total
	 */
	public List<UsuarioConsulta> getUsuariosTotal()
	{
		return iluc_usuariosTotal;
	}
}
