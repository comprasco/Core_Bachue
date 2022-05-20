package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.ComunidadesEtnicas;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase para el manejo de la capa web BeanComunidadesEtnicas.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanComunidadesEtnicas")
@SessionScoped
public class BeanComunidadesEtnicas extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8832709105550196165L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanComunidadesEtnicas.class);

	/** Propiedad icce comunidades etnicas agregados. */
	private Collection<ComunidadesEtnicas> icce_comunidadesEtnicasAgregados;

	/** Propiedad icce comunidades etnicas collection. */
	private Collection<ComunidadesEtnicas> icce_comunidadesEtnicasCollection;

	/** Propiedad ice comunidades etnicas eliminar */
	private ComunidadesEtnicas ice_comunidadesEtnicasEliminar;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ii comunidades iniciales */
	private int ii_comunidadesIniciales;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de comunidades etnicas collection.
	 *
	 * @param acce_cce asigna el valor a la propiedad comunidades etnicas collection
	 */
	public void setComunidadesEtnicasCollection(Collection<ComunidadesEtnicas> acce_cce)
	{
		icce_comunidadesEtnicasCollection = acce_cce;
	}

	/**
	 * Retorna el valor de comunidades etnicas collection.
	 *
	 * @return el valor de comunidades etnicas collection
	 */
	public Collection<ComunidadesEtnicas> getComunidadesEtnicasCollection()
	{
		return icce_comunidadesEtnicasCollection;
	}

	/**
	 * Modifica el valor de comunidades etnicas agregados.
	 *
	 * @param acce_cce asigna el valor a la propiedad comunidades etnicas agregados
	 */
	public void setComunidadesEtnicasAgregados(Collection<ComunidadesEtnicas> acce_cce)
	{
		icce_comunidadesEtnicasAgregados = acce_cce;
	}

	/**
	 * Retorna el valor de comunidades etnicas agregados.
	 *
	 * @return el valor de comunidades etnicas agregados
	 */
	public Collection<ComunidadesEtnicas> getComunidadesEtnicasAgregados()
	{
		return icce_comunidadesEtnicasAgregados;
	}

	/**
	 * Obtiene el valor de comunidades etnicas eliminar.
	 *
	 * @return el valor objeto guardado
	 */
	public ComunidadesEtnicas getComunidadesEtnicasEliminar()
	{
		return ice_comunidadesEtnicasEliminar;
	}

	/**
	 * Encargado de guardar la Comunidad Etnica Eliminar
	 *
	 * @param ace_ce ComunidadesEtnicas objeto que asigna el valor.
	 */
	public void setComunidadesEtnicasEliminar(ComunidadesEtnicas ace_ce)
	{
		ice_comunidadesEtnicasEliminar = ace_ce;
	}

	/**
	 * Modifica el valor de comunidades iniciales.
	 *
	 * @param ai_i asigna el valor a la propiedad comunidades iniciales
	 */
	public void setComunidadesIniciales(int ai_i)
	{
		ii_comunidadesIniciales = ai_i;
	}

	/**
	 * Retorna el valor de comunidades iniciales.
	 *
	 * @return el valor de comunidades iniciales
	 */
	public int getComunidadesIniciales()
	{
		return ii_comunidadesIniciales;
	}

	/**
	 * Método encargado de agregar un registro en la pantalla.
	 *
	 */
	public void agregarRegistro()
	{
		Collection<ComunidadesEtnicas> lcce_comunidadEtnicaActual;
		ComunidadesEtnicas             lce_comunidadEtnica;
		int                            li_numeroComunidad;

		li_numeroComunidad             = 0;
		lcce_comunidadEtnicaActual     = getComunidadesEtnicasCollection();
		lce_comunidadEtnica            = new ComunidadesEtnicas();

		if(CollectionUtils.isValidCollection(lcce_comunidadEtnicaActual))
		{
			for(ComunidadesEtnicas lta_temp : lcce_comunidadEtnicaActual)
			{
				if(lta_temp != null)
				{
					int li_numeroActual;

					li_numeroActual = lta_temp.getIdComunidad();

					if(li_numeroActual > li_numeroComunidad)
						li_numeroComunidad = li_numeroActual;
				}
			}

			li_numeroComunidad++;
			lce_comunidadEtnica.setIdComunidad(li_numeroComunidad);
			lcce_comunidadEtnicaActual.add(lce_comunidadEtnica);

			setComunidadesEtnicasCollection(lcce_comunidadEtnicaActual);
		}
		else
		{
			Collection<ComunidadesEtnicas> lcce_cce;

			lcce_cce = new ArrayList<ComunidadesEtnicas>();

			li_numeroComunidad++;
			lce_comunidadEtnica.setIdComunidad(li_numeroComunidad);
			lcce_cce.add(lce_comunidadEtnica);

			setComunidadesEtnicasCollection(lcce_cce);
		}
	}

	/**
	 * Método encargado de prender booleana del registro para editarlo e inactivar las demás
	 *
	 * @param ace_ce Objeto ComunidadesEtnicas a editar
	 */
	public void modificarRegistro(ComunidadesEtnicas ace_ce)
	{
		if(ace_ce != null)
		{
			Collection<ComunidadesEtnicas> lcce_comunidadesEtnicasActual;

			lcce_comunidadesEtnicasActual = getComunidadesEtnicasCollection();

			if(CollectionUtils.isValidCollection(lcce_comunidadesEtnicasActual))
			{
				for(ComunidadesEtnicas lce_temp : lcce_comunidadesEtnicasActual)
				{
					if(lce_temp != null)
					{
						if(lce_temp == ace_ce)
							lce_temp.setEditable(true);
						else
							lce_temp.setEditable(false);
					}
				}
			}
		}
	}

	/**
	 * Metodo que se encarga de cargar variables usado al entrar a la pantalla y al darle lick a cancelar.
	 */
	public void cargarData()
	{
		Collection<ComunidadesEtnicas> lcce_temp;

		lcce_temp = new ArrayList<ComunidadesEtnicas>();

		try
		{
			lcce_temp = ipr_parameterRemote.findAllComunidadesEtnicas();

			if(CollectionUtils.isValidCollection(lcce_temp))
				setComunidadesIniciales(lcce_temp.size());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		setComunidadesEtnicasCollection(lcce_temp);
		setComunidadesEtnicasAgregados(lcce_temp);
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setComunidadesEtnicasEliminar(null);
		setComunidadesEtnicasCollection(null);
		setComunidadesEtnicasAgregados(null);
		setComunidadesIniciales(0);
	}

	/**
	 * Metodo que se encarga de cargar la comunidad etnica a eliminar
	 *
	 * @param ace_comunidadesEtnicas Objeto ComunidadesEtnicas a eliminar
	 *
	 */
	public void comunidadAEliminar(ComunidadesEtnicas ace_comunidadesEtnicas)
	{
		setComunidadesEtnicasEliminar(ace_comunidadesEtnicas);
	}

	/**
	 * Metodo que se encarga de validar la comunidad editada
	 *
	 * @param ace_comunidadesEtnicas Objeto ComunidadesEtnicas a eliminar
	 *
	 */
	public void comunidadEditada(ComunidadesEtnicas ace_comunidadesEtnicas)
	{
		try
		{
			if(ace_comunidadesEtnicas != null)
			{
				Collection<ComunidadesEtnicas> lcce_comunidadesEtnicasActual;

				lcce_comunidadesEtnicasActual = getComunidadesEtnicasCollection();

				if(CollectionUtils.isValidCollection(lcce_comunidadesEtnicasActual))
				{
					int    li_idComunidadEditado;
					String ls_numeroEditado;
					String ls_nombreEditado;

					li_idComunidadEditado     = ace_comunidadesEtnicas.getIdComunidad();
					ls_nombreEditado          = ace_comunidadesEtnicas.getNombreComunidad();
					ls_numeroEditado          = ace_comunidadesEtnicas.getNumeroDocumento();

					for(ComunidadesEtnicas lce_temp : lcce_comunidadesEtnicasActual)
					{
						if(lce_temp != null)
						{
							int li_idComunidad;

							li_idComunidad = lce_temp.getIdComunidad();

							if(li_idComunidad == li_idComunidadEditado)
							{
								ComunidadesEtnicas lce_comunidadEtnica;

								lce_comunidadEtnica = ipr_parameterRemote.findComunidadesEtnicasById(
									    li_idComunidadEditado
									);

								if(lce_comunidadEtnica != null)
									lce_temp.setEditado(true);

								lce_temp.setNombreComunidad(ls_nombreEditado);
								lce_temp.setNumeroDocumento(ls_numeroEditado);
							}
						}
					}

					setComunidadesEtnicasCollection(lcce_comunidadesEtnicasActual);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de agregar un registro y modificar los demás.
	 *
	 */
	public void eliminarRegistro()
	{
		ComunidadesEtnicas lce_ce;

		lce_ce = getComunidadesEtnicasEliminar();

		if(lce_ce != null)
		{
			Collection<ComunidadesEtnicas> lcce_comunidadesEtnicasActual;

			lcce_comunidadesEtnicasActual = getComunidadesEtnicasCollection();

			if(CollectionUtils.isValidCollection(lcce_comunidadesEtnicasActual))
			{
				Collection<ComunidadesEtnicas> lcce_comunidadesEliminar;
				int                            li_eliminados;

				lcce_comunidadesEliminar     = new ArrayList<ComunidadesEtnicas>();
				li_eliminados                = 0;

				for(ComunidadesEtnicas lce_temp : lcce_comunidadesEtnicasActual)
				{
					if(lce_temp != null)
					{
						if(lce_temp != lce_ce)
						{
							li_eliminados++;
							lce_temp.setIdComunidad(li_eliminados);
							lcce_comunidadesEliminar.add(lce_temp);
						}
					}
				}

				setComunidadesEtnicasCollection(lcce_comunidadesEliminar);
				setComunidadesEtnicasAgregados(lcce_comunidadesEliminar);
			}
		}

		addMessage(MessagesKeys.REGISTRO_ELIMINADO_CORRECTAMENTE);
		PrimeFaces.current().ajax().update("fComunidadesEtnicas");
	}

	/**
	 * Metodo encargado de guardar el proceso.
	 */
	public void guardar()
	{
		try
		{
			Collection<ComunidadesEtnicas> lcce_cce;
			boolean                        lb_modificar;

			lcce_cce         = getComunidadesEtnicasCollection();
			lb_modificar     = false;

			if(CollectionUtils.isValidCollection(lcce_cce))
			{
				for(ComunidadesEtnicas lce_temp : lcce_cce)
				{
					if(lce_temp != null)
					{
						String ls_numeroDocumento;
						String ls_nombreComunidad;

						ls_nombreComunidad     = lce_temp.getNombreComunidad();
						ls_numeroDocumento     = lce_temp.getNumeroDocumento();

						lce_temp.setEditable(false);

						if(
						    StringUtils.isValidString(ls_numeroDocumento)
							    && StringUtils.isValidString(ls_nombreComunidad)
						)
						{
							Collection<ComunidadesEtnicas> lcce_comunidadesNumero;

							lcce_comunidadesNumero = getComunidadesEtnicasCollection();

							if(CollectionUtils.isValidCollection(lcce_comunidadesNumero))
							{
								int li_duplicados;

								li_duplicados = 0;

								for(ComunidadesEtnicas lce_tempNumeros : lcce_cce)
								{
									if(lce_tempNumeros != null)
									{
										String ls_numero;

										ls_numero = lce_tempNumeros.getNumeroDocumento();

										if(
										    StringUtils.isValidString(ls_numero)
											    && ls_numero.equalsIgnoreCase(ls_numeroDocumento)
										)
											li_duplicados++;
									}
								}

								if(li_duplicados >= 2)
									throw new B2BException(ErrorKeys.ERROR_COMUNIDADES_ETNICAS_NO_VALIDO);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_COMUNIDADES_ETNICAS_NO_VALIDO);

						lb_modificar = lce_temp.getEditado();
					}
				}

				ipr_parameterRemote.salvarComunidadesEtnicas(
				    lcce_cce, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				if(lb_modificar)
				{
					addMessage(MessagesKeys.SALVAR_COMUNIDADES_ETNICAS_MODIFICAR);
					PrimeFaces.current().ajax().update("fComunidadesEtnicas");
				}

				{
					Collection<ComunidadesEtnicas> lcce_comunidadesEtnicasAgregados;

					lcce_comunidadesEtnicasAgregados = getComunidadesEtnicasAgregados();

					if(CollectionUtils.isValidCollection(lcce_comunidadesEtnicasAgregados))
					{
						int li_ceAgregados;
						int li_ceFinal;

						li_ceAgregados     = lcce_comunidadesEtnicasAgregados.size();
						li_ceFinal         = getComunidadesIniciales();

						if(li_ceAgregados < li_ceFinal)
						{
							addMessage(MessagesKeys.SALVAR_COMUNIDADES_ETNICAS_GUARDAR);
							PrimeFaces.current().ajax().update("fComunidadesEtnicas");
						}
					}
					else
					{
						addMessage(MessagesKeys.SALVAR_COMUNIDADES_ETNICAS_GUARDAR);
						PrimeFaces.current().ajax().update("fComunidadesEtnicas");
					}
				}
			}
			else
			{
				ipr_parameterRemote.salvarComunidadesEtnicas(
				    null, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fComunidadesEtnicas");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fComunidadesEtnicas");
		}
	}
}
