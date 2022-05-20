package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_USUARIO_LINEA.
 *
 * @author Julian Vaca
 */
public class UsuarioLinea extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5186698406337196555L;
	
	/** Propiedad linea produccion. */
	private LineaProduccion   lineaProduccion;
	
	/** Propiedad activo. */
	private String            activo;
	
	/** Propiedad usuario. */
	private Usuario           usuario;
	
	/** Propiedad seleccionado. */
	private boolean           seleccionado;

	/**
	 * Constructor por defecto, instancia un objecto tipo Usuario
	 * y un objeto tipo LineaProduccion.
	 *
	 * @see com.bachue.snr.prosnr01.model.sdb.aut.Usuario.Usuario()
	 * @see com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion
	 */
	public UsuarioLinea()
	{
		usuario                                = new Usuario();
		lineaProduccion                        = new LineaProduccion();
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		this.activo = activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return activo;
	}

	/**
	 * Modifica el valor de LineaProduccion.
	 *
	 * @param lineaProduccion asigna el valor a la propiedad
	 */
	public void setLineaProduccion(LineaProduccion lineaProduccion)
	{
		this.lineaProduccion = lineaProduccion;
	}

	/**
	 * Retorna Objeto o variable de valor linea produccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public LineaProduccion getLineaProduccion()
	{
		return lineaProduccion;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param seleccionado asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean seleccionado)
	{
		this.seleccionado = seleccionado;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return seleccionado;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param usuario asigna el valor a la propiedad
	 */
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Usuario getUsuario()
	{
		return usuario;
	}
}
