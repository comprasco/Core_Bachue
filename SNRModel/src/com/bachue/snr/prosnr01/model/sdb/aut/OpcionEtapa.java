package com.bachue.snr.prosnr01.model.sdb.aut;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_AUT_OPCION_ETAPA.
 *
 * @author ssanchez
 */
public class OpcionEtapa extends Auditoria implements Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = 7915755390358981691L;

	/**
	 * Propiedad is id opcion.
	 */
	private String is_idOpcion;

	/**
	 * Propiedad il id etapa.
	 */
	private long il_idEtapa;

	/**
	 * Constructor por defecto.
	 */
	public OpcionEtapa()
	{
	}

	/**
	 * Constructor de clase RolOpcion.
	 *
	 * @param as_idEtapa de as id etapa
	 * @param as_idOpcion de as id opcion
	 * @param as_ip de as ip
	 * @param as_usuario de as usuario
	 */
	public OpcionEtapa(long as_idEtapa, String as_idOpcion, String as_ip, String as_usuario)
	{
		il_idEtapa      = as_idEtapa;
		is_idOpcion     = as_idOpcion;
		setIpModificacion(as_ip);
		setIdUsuarioModificacion(as_usuario);
		setIdUsuarioCreacion(as_usuario);
		setIpCreacion(as_ip);
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
	 * Modifica el valor de Id etapa.
	 *
	 * @param al_l Objeto o Variable de tipo String que asigna un valor a la propiedad IdEtapa
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Get id etapa.
	 *
	 * @return el valor de long
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}
}
