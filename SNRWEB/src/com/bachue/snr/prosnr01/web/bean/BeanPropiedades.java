package com.bachue.snr.prosnr01.web.bean;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.PropiedadesUI;

import org.primefaces.PrimeFaces;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades BeanPropiedades.
 *
 * @author  cvargas y nforero
 * Fecha de Creacion: 03/11/2021
 */
@ManagedBean(name = "beanPropiedades")
@SessionScoped
public class BeanPropiedades extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -353371005705551283L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanPropiedades.class);

	/** Propiedad icpu lista propiedades */
	private Collection<PropiedadesUI> icpu_listaPropiedades;

	/** Propiedad ib seleccione */
	private boolean ib_seleccione;

	/**
	 * Contructor de la clase BeanPropiedades
	 */
	public BeanPropiedades()
	{
		cargarPropiedadesBeanReference();
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	* Modifica el valor de lista propiedades.
	*
	* @param acpu_cpu asigna el valor a la propiedad lista propiedades
	*/
	public void setListaPropiedades(Collection<PropiedadesUI> acpu_cpu)
	{
		icpu_listaPropiedades = acpu_cpu;
	}

	/**
	 * Retorna el valor de lista propiedades.
	 *
	 * @return el valor de lista propiedades
	 */
	public Collection<PropiedadesUI> getListaPropiedades()
	{
		return icpu_listaPropiedades;
	}

	/**
	* Modifica el valor de seleccione.
	*
	* @param ab_b asigna el valor a la propiedad seleccione
	*/
	public void setSeleccione(boolean ab_b)
	{
		ib_seleccione = ab_b;
	}

	/**
	 * Retorna el valor de seleccione.
	 *
	 * @return el valor de seleccione
	 */
	public boolean isSeleccione()
	{
		return ib_seleccione;
	}

	/**
	 * Método para cargar propiedades bean reference.
	 *
	 */
	public void cargarPropiedadesBeanReference()
	{
		try
		{
			Class lc_temporal;

			lc_temporal = Class.forName("com.bachue.snr.prosnr01.web.bean.reference.BeanReference");

			if(lc_temporal != null)
			{
				Collection<PropiedadesUI> lcpu_listaPropiedades;
				Field[]                   lfa_nombrePropiedades;

				lcpu_listaPropiedades     = new ArrayList<PropiedadesUI>();
				lfa_nombrePropiedades     = lc_temporal.getDeclaredFields();

				for(Field lf_iterador : lfa_nombrePropiedades)
				{
					if(lf_iterador != null)
					{
						String ls_nombre;

						ls_nombre = lf_iterador.getName();

						if(
						    StringUtils.isValidString(ls_nombre) && !ls_nombre.equalsIgnoreCase("serialVersionUID")
							    && !ls_nombre.endsWith("Remote")
						)
						{
							String[] lsa_nombreCompleto;

							lsa_nombreCompleto = ls_nombre.split("_");

							if(CollectionUtils.isValidCollection(lsa_nombreCompleto) && (lsa_nombreCompleto.length > 1))
							{
								String ls_nombreParcial;

								ls_nombreParcial = lsa_nombreCompleto[1];

								if(StringUtils.isValidString(ls_nombreParcial))
								{
									PropiedadesUI lpu_propiedad;

									lpu_propiedad = new PropiedadesUI();

									lpu_propiedad.setNombrePropiedad(StringUtils.upperCaseFirst(ls_nombreParcial));

									lcpu_listaPropiedades.add(lpu_propiedad);
								}
							}
						}
					}
				}

				setListaPropiedades(lcpu_listaPropiedades);
			}
		}
		catch(ClassNotFoundException lcnfe_e)
		{
			clh_LOGGER.error("procesar", lcnfe_e);
			addMessage(lcnfe_e.getLocalizedMessage());
		}
	}

	public void clean()
	{
		setListaPropiedades(null);
		setSeleccione(false);
	}

	/**
	 * Método para limpiar caché
	 *
	 */
	public void limpiarCache()
	{
		try
		{
			Collection<PropiedadesUI> lcpu_listaPropiedades;

			lcpu_listaPropiedades = getListaPropiedades();

			if(CollectionUtils.isValidCollection(lcpu_listaPropiedades))
			{
				BeanReference lbr_beanReference;

				lbr_beanReference = getBeanReference();

				for(PropiedadesUI lpu_iterador : lcpu_listaPropiedades)
				{
					if(lpu_iterador != null)
					{
						String  ls_nombrePropiedad;
						boolean lb_seleccione;

						ls_nombrePropiedad     = lpu_iterador.getNombrePropiedad();
						lb_seleccione          = lpu_iterador.isSeleccione();

						if(lb_seleccione && StringUtils.isValidString(ls_nombrePropiedad))
						{
							Class lc_temporal;
							Class lc_retorno;

							lc_temporal     = Class.forName("com.bachue.snr.prosnr01.web.bean.reference.BeanReference");
							lc_retorno      = null;

							{
								StringBuilder lsb_cadena;
								Method        lm_metodoGet;

								lsb_cadena = new StringBuilder("get");

								lsb_cadena.append(ls_nombrePropiedad);

								lm_metodoGet = lc_temporal.getMethod(lsb_cadena.toString());

								if(lm_metodoGet != null)
									lc_retorno = lm_metodoGet.getReturnType();
							}

							if(lc_retorno != null)
							{
								StringBuilder lsb_cadena;
								Method        lm_metodo;
								Object        lo_objeto;

								lsb_cadena = new StringBuilder("set");

								lsb_cadena.append(ls_nombrePropiedad);

								lm_metodo     = lc_temporal.getMethod(lsb_cadena.toString(), lc_retorno);

								lo_objeto = null;

								lm_metodo.invoke(lbr_beanReference, lo_objeto);
							}
						}
					}
				}

				addMessage(MessagesKeys.CACHE_LIMPIADO);
			}
		}
		catch(Exception e)
		{
			addMessage(e.getLocalizedMessage());
		}
		finally
		{
			PrimeFaces.current().ajax().update("fBandejaPropiedades:globalMsg");
		}
	}

	/**
	 * Método para seleccionar todas las propiedades
	 *
	 */
	public void seleccionarTodos()
	{
		Collection<PropiedadesUI> lcpu_listaPropiedades;

		lcpu_listaPropiedades = getListaPropiedades();

		if(CollectionUtils.isValidCollection(lcpu_listaPropiedades))
		{
			for(PropiedadesUI lpu_iterador : lcpu_listaPropiedades)
			{
				if(lpu_iterador != null)
				{
					boolean lb_seleccione;

					lb_seleccione = lpu_iterador.isSeleccione();

					lpu_iterador.setSeleccione(true);

					if(lb_seleccione)
						lpu_iterador.setSeleccione(false);
				}
			}
		}
	}
}
