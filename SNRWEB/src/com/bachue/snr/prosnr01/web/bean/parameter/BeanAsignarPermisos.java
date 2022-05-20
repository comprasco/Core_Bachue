package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.ComponenteUI;

import org.primefaces.PrimeFaces;

import org.primefaces.component.picklist.PickList;

import org.primefaces.event.TransferEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanAsignarPermisos.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanAsignarPermisos")
@SessionScoped
public class BeanAsignarPermisos extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4611154076036577738L;

	/** Propiedad idlm doble lista rol. */
	private Collection<ComponenteUI> icc_componentes;

	/** Propiedad il titulos. */
	private Map<String, String> imss_opcionesInactivar;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir rol actual. */
	private Rol ir_rolActual;

	/** Propiedad is id componente. */
	private String is_idComponente;

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Rol getRolActual()
	{
		return ir_rolActual;
	}

	/**
	 * @param ar_r asigna el valor a la propiedad
	 */
	public void setRolActual(Rol ar_r)
	{
		ir_rolActual = ar_r;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<ComponenteUI> getComponentes()
	{
		return icc_componentes;
	}

	/**
	 * @param acc_cc asigna el valor a la propiedad
	 */
	public void setComponentes(Collection<ComponenteUI> acc_cc)
	{
		icc_componentes = acc_cc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComponente()
	{
		return is_idComponente;
	}

	/**
	 * @param is_idComponente asigna el valor a la propiedad
	 */
	public void setIdComponente(String is_idComponente)
	{
		this.is_idComponente = is_idComponente;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Map<String, String> getOpcionesInactivar()
	{
		return imss_opcionesInactivar;
	}

	/**
	 * @param imss_opcionesInactivar asigna el valor a la propiedad
	 */
	public void setOpcionesInactivar(Map<String, String> imss_opcionesInactivar)
	{
		this.imss_opcionesInactivar = imss_opcionesInactivar;
	}

	/**
	 * Metodo para limpiar la informacion de la pantalla y retornar a la pagina anterior.
	 *
	 * @return enlace a la pagina de rol detalle
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String returnPages()
	    throws B2BException
	{
		String ls_result;

		ls_result = null;

		{
			clear();
			ls_result = NavegacionCommon.ROL_DETALLE;
		}

		return ls_result;
	}

	/**
	 * Metodo guardar los cambios de los permisos.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String guardar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			Rol                      lr_rol;
			Collection<ComponenteUI> lccui_componenteUI;
			Collection<Componente>   lcc_componente;

			lccui_componenteUI     = getComponentes();
			lr_rol                 = getRolActual();
			lcc_componente         = new ArrayList<Componente>();

			if(lr_rol != null)
			{
				String ls_rol;

				ls_rol = lr_rol.getIdRol();

				if(StringUtils.isValidString(ls_rol))
				{
					for(ComponenteUI lc_temp : lccui_componenteUI)
					{
						if(lc_temp != null)
						{
							Collection<Opcion> lcs_opciones;

							lcs_opciones = lc_temp.getOpciones().getTarget();

							if(CollectionUtils.isValidCollection(lcs_opciones))
							{
								lc_temp.setOpcionesRolTarget(lcs_opciones);
								lcc_componente.add(lc_temp);
							}
						}
					}

					ipr_parameterRemote.salvarRolOpcionComponente(
					    ls_rol, lcc_componente, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					ls_result = NavegacionCommon.ROL;
					addMessage(MessagesKeys.PROCESO_COMPLETADO);

					{
						ExternalContext lec_externalContext;

						lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

						if(lec_externalContext != null)
						{
							Flash lf_flash;

							lf_flash = lec_externalContext.getFlash();

							if(lf_flash != null)
								lf_flash.setKeepMessages(true);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fAsignarPermisos:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Método para retornar los permisos
	 */
	public void retornoPermisos(TransferEvent lte_te)
	{
		if(lte_te != null)
		{
			if(lte_te.isRemove())
			{
				Collection<?> lcu_items;

				lcu_items = lte_te.getItems();

				if(CollectionUtils.isValidCollection(lcu_items))
				{
					PickList lpl_pickList;

					lpl_pickList = (PickList)lte_te.getSource();

					if(lpl_pickList != null)
					{
						String ls_idComponente;

						ls_idComponente = lpl_pickList.getLabel();

						if(StringUtils.isValidString(ls_idComponente))
						{
							Map<String, String> lmss_opcionesInactivar;
							boolean             lb_mapaNuevo;

							lmss_opcionesInactivar     = getOpcionesInactivar();
							lb_mapaNuevo               = !CollectionUtils.isValidCollection(lmss_opcionesInactivar);

							if(lb_mapaNuevo)
								lmss_opcionesInactivar = new HashMap<String, String>();

							for(Object lu_temp : lcu_items)
							{
								if(lu_temp != null)
								{
									Integer li_opcion;

									li_opcion = NumericUtils.getInteger(lu_temp.toString());

									if(NumericUtils.isValidInteger(li_opcion))
									{
										if(lb_mapaNuevo)
										{
											lmss_opcionesInactivar.put(
											    ls_idComponente, StringUtils.getString(li_opcion)
											);
											lb_mapaNuevo = false;
										}
										else
										{
											if(lmss_opcionesInactivar.containsKey(ls_idComponente))
											{
												String ls_value;

												ls_value = lmss_opcionesInactivar.get(ls_idComponente);

												if(StringUtils.isValidString(ls_value))
												{
													StringBuilder lsb_builder;

													lsb_builder = new StringBuilder(ls_value);
													lsb_builder.append(IdentificadoresCommon.SIMBOLO_COMA_TXT);
													lsb_builder.append(li_opcion);
													lmss_opcionesInactivar.replace(
													    ls_idComponente, lsb_builder.toString()
													);
												}
											}
											else
												lmss_opcionesInactivar.put(
												    ls_idComponente, StringUtils.getString(li_opcion)
												);
										}
									}
								}
							}

							setOpcionesInactivar(lmss_opcionesInactivar);
						}
					}
				}
			}
		}
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setComponentes(null);
		setOpcionesInactivar(null);
		setRolActual(null);
		setIdComponente(null);
	}

	/**
	 * Método para cargar permisos
	 */
	public void cargarPermisos()
	{
		try
		{
			Collection<Componente> lcc_componentes;
			String                 ls_idRol;
			Rol                    lr_rol;

			lr_rol = getRolActual();

			if(lr_rol != null)
			{
				ls_idRol = lr_rol.getIdRol();

				if(StringUtils.isValidString(ls_idRol))
				{
					lcc_componentes = ipr_parameterRemote.findAllAdministracionComponentesPermisos(ls_idRol);

					if(CollectionUtils.isValidCollection(lcc_componentes))
					{
						Collection<ComponenteUI> lccui_componentes;

						lccui_componentes = new ArrayList<ComponenteUI>();

						for(Componente lc_temp : lcc_componentes)
						{
							if(lc_temp != null)
								lccui_componentes.add(new ComponenteUI(lc_temp));
						}

						setComponentes(lccui_componentes);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}
}
