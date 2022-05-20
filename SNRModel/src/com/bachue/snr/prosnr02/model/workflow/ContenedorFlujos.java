package com.bachue.snr.prosnr02.model.workflow;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr02.model.pgn.MotivoTramiteTrabajo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



/**
 * Clase que contiene todos las propiedades ContenedorFlujos.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public abstract class ContenedorFlujos extends Componente
{
	
	/** Constante serialVersionUID. */
	private static final long                 serialVersionUID = -1413675125577470641L;
	
	/** Propiedad imsmtt flujos. */
	private Map<String, MotivoTramiteTrabajo> imsmtt_flujos;

	/**
	 * Retorna Objeto o variable de valor coleccion flujos.
	 *
	 * @return el valor de coleccion flujos
	 */
	public Collection<MotivoTramiteTrabajo> getColeccionFlujos()
	{
		return CollectionUtils.isValidCollection(imsmtt_flujos) ? CollectionUtils.sort(imsmtt_flujos.values()) : null;
	}

	/**
	 * Retorna Objeto o variable de valor flujo.
	 *
	 * @param as_id de as id
	 * @return el valor de flujo
	 */
	public MotivoTramiteTrabajo getFlujo(String as_id)
	{
		String ls_id;

		ls_id                                                  = StringUtils.getString(as_id);

		return ((imsmtt_flujos != null) && (ls_id != null)) ? imsmtt_flujos.get(ls_id) : null;
	}

	/**
	 * Cambia el valor de flujos.
	 *
	 * @param amsmtt_flujos de amsmtt flujos
	 */
	public void setFlujos(Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		imsmtt_flujos = amsmtt_flujos;
	}

	/**
	 * Retorna Objeto o variable de valor flujos.
	 *
	 * @return el valor de flujos
	 */
	public Map<String, MotivoTramiteTrabajo> getFlujos()
	{
		return imsmtt_flujos;
	}

	/**
	 * Adicionar flujo.
	 *
	 * @param amtt_flujo de amtt flujo
	 */
	public void adicionarFlujo(MotivoTramiteTrabajo amtt_flujo)
	{
		boolean lb_flujo;
		String  ls_id;

		lb_flujo     = amtt_flujo != null;
		ls_id        = lb_flujo ? amtt_flujo.getId() : null;

		if(imsmtt_flujos == null)
			imsmtt_flujos = new HashMap<String, MotivoTramiteTrabajo>();

		if(StringUtils.isValidString(ls_id))
			imsmtt_flujos.put(ls_id, amtt_flujo);
	}

	/**
	 * Adicionar flujos.
	 *
	 * @param amsmtt_flujos de amsmtt flujos
	 */
	public void adicionarFlujos(Map<String, MotivoTramiteTrabajo> amsmtt_flujos)
	{
		if(imsmtt_flujos == null)
			imsmtt_flujos = new HashMap<String, MotivoTramiteTrabajo>();

		if(CollectionUtils.isValidCollection(amsmtt_flujos))
			imsmtt_flujos.putAll(amsmtt_flujos);
	}

	/**
	 * Eliminar flujo.
	 *
	 * @param amtt_flujo de amtt flujo
	 */
	public void eliminarFlujo(MotivoTramiteTrabajo amtt_flujo)
	{
		if(amtt_flujo != null)
			eliminarFlujo(amtt_flujo.getId());
	}

	/**
	 * Eliminar flujo.
	 *
	 * @param as_idFlujo de as id flujo
	 */
	public void eliminarFlujo(String as_idFlujo)
	{
		if(CollectionUtils.isValidCollection(imsmtt_flujos) && StringUtils.isValidString(as_idFlujo))
			imsmtt_flujos.remove(as_idFlujo);
	}
}
