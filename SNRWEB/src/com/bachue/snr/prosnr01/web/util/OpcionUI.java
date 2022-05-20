package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import org.primefaces.model.DualListModel;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Clase que contiene todos las propiedades de OpcionUI.
 *
 * @author ssanchez
 */
public class OpcionUI extends Opcion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4626088996843268722L;

	/** Propiedad idlmo etapas. */
	private DualListModel<Etapa> idlmo_etapas;

	/**
	 *
	 * Constructor por defecto.
	 */
	public OpcionUI()
	{
	}

	/**
	 *
	 * Constructor OpcionUI.
	 *
	 * @param ao_o correspondiente al valor del tipo de objeto Componente
	 */
	public OpcionUI(Opcion ao_o)
	{
		if(ao_o != null)
		{
			setFechaDesde(ao_o.getFechaDesde());
			setFechaHasta(ao_o.getFechaHasta());
			setActivo(ao_o.getActivo());
			setDescripcion(ao_o.getDescripcion());
			setIdComponente(ao_o.getIdComponente());
			setIdOpcion(ao_o.getIdOpcion());
			setIdOpcionPadre(ao_o.getIdOpcionPadre());
			setNombreOpcionPadre(ao_o.getNombreOpcionPadre());
			setOpcion(ao_o.getOpcion());
			setTipo(ao_o.getTipo());
			setUrl(ao_o.getUrl());
			setVentanaNueva(ao_o.getVentanaNueva());

			{
				Collection<Etapa> lce_opcionesSource;
				Collection<Etapa> lce_opcionesTarget;

				lce_opcionesSource     = ao_o.getEtapaSource();
				lce_opcionesTarget     = ao_o.getEtapaTarget();

				if(
				    (CollectionUtils.isValidCollection(lce_opcionesSource) && lce_opcionesSource instanceof List)
					    && (CollectionUtils.isValidCollection(lce_opcionesTarget) && lce_opcionesTarget instanceof List)
				)
					setEtapas(
					    new DualListModel<Etapa>((List<Etapa>)lce_opcionesSource, (List<Etapa>)lce_opcionesTarget)
					);
				else if(
				    (CollectionUtils.isValidCollection(lce_opcionesSource) && lce_opcionesSource instanceof List)
					    && !(CollectionUtils.isValidCollection(lce_opcionesTarget)
					    && lce_opcionesTarget instanceof List)
				)
					setEtapas(new DualListModel<Etapa>((List<Etapa>)lce_opcionesSource, new ArrayList<Etapa>()));

				else if(
				    !(CollectionUtils.isValidCollection(lce_opcionesSource) && lce_opcionesSource instanceof List)
					    && (CollectionUtils.isValidCollection(lce_opcionesTarget) && lce_opcionesTarget instanceof List)
				)
					setEtapas(new DualListModel<Etapa>(new ArrayList<Etapa>(), (List<Etapa>)lce_opcionesTarget));

				else
					setEtapas(new DualListModel<Etapa>(new ArrayList<Etapa>(), new ArrayList<Etapa>()));
			}
		}
	}

	/**
	 * Get etapas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public DualListModel<Etapa> getEtapas()
	{
		return idlmo_etapas;
	}

	/**
	 * Modifica el valor de etapas.
	 *
	 * @param adlme_dlme asigna el valor a la propiedad
	 */
	public void setEtapas(DualListModel<Etapa> adlme_dlme)
	{
		idlmo_etapas = adlme_dlme;
	}
}
