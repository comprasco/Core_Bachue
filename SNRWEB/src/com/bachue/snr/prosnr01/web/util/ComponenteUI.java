package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;

import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;


/**
 * Clase que contiene todos las propiedades de ComponenteIU.
 *
 * @author ssanchez
 */
public class ComponenteUI extends Componente implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8193321120352894464L;

	/** Propiedad idlmo opciones. */
	private DualListModel<Opcion> idlmo_opciones;

	/** Propiedad ib vacios. */
	private StreamedContent isc_icono;

	/** Propiedad is titulos. */
	private String is_titulos;

	/** Propiedad ib vacios. */
	private boolean ib_vacios;

	/**
	 *
	 * Constructor por defecto.
	 */
	public ComponenteUI()
	{
	}

	/**
	 *
	 * Constructor de la clase ComponenteUI.
	 *
	 * @param ac_c Argumento de tipo <code>Componente</code> que contiene los criterios necesarios para construir la clase.
	 */
	public ComponenteUI(Componente ac_c)
	{
		if(ac_c != null)
		{
			setFechaDesde(ac_c.getFechaDesde());
			setFechaHasta(ac_c.getFechaHasta());
			setActivo(ac_c.getActivo());
			setIdComponente(ac_c.getIdComponente());
			setNombre(ac_c.getNombre());
			setImagen(ac_c.getImagen());
			setBotonComponente(ac_c.getBotonComponente());
			setEsParte(ac_c.isEsParte());
			setValorEncontrado(ac_c.isValorEncontrado());
			setUrl(ac_c.getUrl());

			{
				String ls_nombreTitulo;

				ls_nombreTitulo = ac_c.getNombre();

				if(StringUtils.isValidString(ls_nombreTitulo))
				{
					ls_nombreTitulo = ls_nombreTitulo.replace(
						    IdentificadoresCommon.SIMBOLO_GUION_BAJO, IdentificadoresCommon.ESPACIO_VACIO
						);
					setTitulos(ls_nombreTitulo);
				}
			}

			{
				Collection<Opcion> lcr_opcionesRol;
				Collection<Opcion> lcr_opcionesTarget;

				lcr_opcionesRol        = ac_c.getOpcionesRol();
				lcr_opcionesTarget     = ac_c.getOpcionesRolTarget();

				if(
				    (CollectionUtils.isValidCollection(lcr_opcionesRol) && lcr_opcionesRol instanceof List)
					    && (CollectionUtils.isValidCollection(lcr_opcionesTarget) && lcr_opcionesTarget instanceof List)
				)
				{
					setOpciones(
					    new DualListModel<Opcion>((List<Opcion>)lcr_opcionesRol, (List<Opcion>)lcr_opcionesTarget)
					);
					setVacios(false);
				}
				else if(
				    (CollectionUtils.isValidCollection(lcr_opcionesRol) && lcr_opcionesRol instanceof List)
					    && !(CollectionUtils.isValidCollection(lcr_opcionesTarget)
					    && lcr_opcionesTarget instanceof List)
				)
				{
					setOpciones(new DualListModel<Opcion>((List<Opcion>)lcr_opcionesRol, new ArrayList<Opcion>()));
					setVacios(false);
				}

				else if(
				    !(CollectionUtils.isValidCollection(lcr_opcionesRol) && lcr_opcionesRol instanceof List)
					    && (CollectionUtils.isValidCollection(lcr_opcionesTarget) && lcr_opcionesTarget instanceof List)
				)
				{
					setOpciones(new DualListModel<Opcion>(new ArrayList<Opcion>(), (List<Opcion>)lcr_opcionesTarget));
					setVacios(false);
				}

				else
				{
					setOpciones(new DualListModel<Opcion>(new ArrayList<Opcion>(), new ArrayList<Opcion>()));
					setVacios(true);
				}
			}
		}
	}

	/**
	 * Modifica el valor de Icono.
	 *
	 * @param asc_icono Argumento de tipo <code>StreamedContent</code> que contiene el nuevo ícono.
	 */
	public void setIcono(StreamedContent asc_icono)
	{
		isc_icono = asc_icono;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public String getIcono()
	{
		StreamedContent lsc_return;

		lsc_return = null;

		if(isc_icono == null)
			isc_icono = lsc_return;

		byte[] encoded = Base64.getEncoder().encode(getImagen());

		return new String(encoded);
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param adlmo_dlmo Argumento que modifica el valor de la propiedad.
	 */
	public void setOpciones(DualListModel<Opcion> adlmo_dlmo)
	{
		idlmo_opciones = adlmo_dlmo;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public DualListModel<Opcion> getOpciones()
	{
		return idlmo_opciones;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setTitulos(String as_s)
	{
		is_titulos = as_s;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public String getTitulos()
	{
		return is_titulos;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param ab_b Argumento que modifica el valor de la propiedad.
	 */
	public void setVacios(boolean ab_b)
	{
		ib_vacios = ab_b;
	}

	/**
	 * Método encargado de obtener el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public boolean isVacios()
	{
		return ib_vacios;
	}
}
