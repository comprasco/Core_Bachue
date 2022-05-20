package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.LineaProduccionCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoRegionalOrip;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.UsuarioLinea;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidades de BeanUsuarioLineas.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanUsuarioLineas")
@SessionScoped
public class BeanUsuarioLineas extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8280562662370075060L;

	/** Propiedad icu usuarios bandeja. */
	private Collection<Usuario> icu_usuariosBandeja;

	/** Propiedad icul all lineas por usuario. */
	private Collection<UsuarioLinea> icul_allLineasPorUsuario;

	/** Propiedad icul data usuario lineas. */
	private Collection<UsuarioLinea> icul_dataUsuarioLineas;

	/** Propiedad icul lineas por usuario. */
	private Collection<UsuarioLinea> icul_lineasPorUsuario;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id circulo orip. */
	private String is_idCirculoOrip;

	/** Propiedad iul usuario linea. */
	private UsuarioLinea iul_usuarioLinea;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** Propiedad ib viene de aprobacion apoyo nacional. */
	private boolean ib_vieneDeAprobacionApoyoNacional;

	/**
	 * Modifica el valor de all lineas por usuario.
	 *
	 * @param ac_a asigna el valor a la propiedad all lineas por usuario
	 */
	public void setAllLineasPorUsuario(Collection<UsuarioLinea> ac_a)
	{
		icul_allLineasPorUsuario = ac_a;
	}

	/**
	 * Retorna el valor de all lineas por usuario.
	 *
	 * @return el valor de all lineas por usuario
	 */
	public Collection<UsuarioLinea> getAllLineasPorUsuario()
	{
		return icul_allLineasPorUsuario;
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
	 * Modifica el valor de data usuario lineas.
	 *
	 * @param accr_ccr asigna el valor a la propiedad data usuario lineas
	 */
	public void setDataUsuarioLineas(Collection<UsuarioLinea> accr_ccr)
	{
		icul_dataUsuarioLineas = accr_ccr;
	}

	/**
	 * Retorna el valor de data usuario lineas.
	 *
	 * @return el valor de data usuario lineas
	 */
	public Collection<UsuarioLinea> getDataUsuarioLineas()
	{
		if(icul_dataUsuarioLineas == null)
			icul_dataUsuarioLineas = new LinkedList<UsuarioLinea>();

		return icul_dataUsuarioLineas;
	}

	/**
	 * Modifica el valor de IdCirculoOrip.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculoOrip(String as_s)
	{
		is_idCirculoOrip = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo orip.
	 *
	 * @return Retorna el valor de la propiedad idCirculoOrip
	 */
	public String getIdCirculoOrip()
	{
		return is_idCirculoOrip;
	}

	/**
	 * Modifica el valor de insert.
	 *
	 * @param insert asigna el valor a la propiedad insert
	 */
	public void setInsert(boolean insert)
	{
		this.ib_insert = insert;
	}

	/**
	 * Valida la propiedad insert.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insert
	 */
	public boolean isInsert()
	{
		return ib_insert;
	}

	/**
	 * Modifica el valor de lineas por usuario.
	 *
	 * @param acul_cul asigna el valor a la propiedad lineas por usuario
	 */
	public void setLineasPorUsuario(Collection<UsuarioLinea> acul_cul)
	{
		icul_lineasPorUsuario = acul_cul;
	}

	/**
	 * Retorna el valor de lineas por usuario.
	 *
	 * @return el valor de lineas por usuario
	 */
	public Collection<UsuarioLinea> getLineasPorUsuario()
	{
		return icul_lineasPorUsuario;
	}

	/**
	 * Modifica el valor de usuario linea.
	 *
	 * @param acr_cr asigna el valor a la propiedad usuario linea
	 */
	public void setUsuarioLinea(UsuarioLinea acr_cr)
	{
		iul_usuarioLinea = acr_cr;
	}

	/**
	 * Retorna el valor de usuario linea.
	 *
	 * @return el valor de usuario linea
	 */
	public UsuarioLinea getUsuarioLinea()
	{
		return iul_usuarioLinea;
	}

	/**
	 * Modifica el valor de usuarios bandeja.
	 *
	 * @param acu_cu asigna el valor a la propiedad usuarios bandeja
	 */
	public void setUsuariosBandeja(Collection<Usuario> acu_cu)
	{
		icu_usuariosBandeja = acu_cu;
	}

	/**
	 * Retorna el valor de usuarios bandeja.
	 *
	 * @return el valor de usuarios bandeja
	 */
	public Collection<Usuario> getUsuariosBandeja()
	{
		return icu_usuariosBandeja;
	}

	/**
	 * Modifica el valor de VieneDeAprobacionApoyoNacional.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeAprobacionApoyoNacional(boolean ab_b)
	{
		ib_vieneDeAprobacionApoyoNacional = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad vieneDeAprobacionApoyoNacional
	 */
	public boolean isVieneDeAprobacionApoyoNacional()
	{
		return ib_vieneDeAprobacionApoyoNacional;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param acr_UsuarioLineaseleccionado correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor de String
	 *
	 */
	public String botonInsertar(Usuario acr_UsuarioLineaseleccionado)
	{
		String ls_return;
		ls_return = null;

		UsuarioLinea lul_temp;
		lul_temp = new UsuarioLinea();

		if(acr_UsuarioLineaseleccionado == null)
			acr_UsuarioLineaseleccionado = new Usuario();

		lul_temp.setUsuario(acr_UsuarioLineaseleccionado);

		setUsuarioLinea(lul_temp);

		setLineasPorUsuario(findLineasPorUsuario());

		findAllLineasProduccion();

		ls_return = NavegacionCommon.USUARIO_LINEAS_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<UsuarioLinea> cargarUsuarioLineas()
	{
		Collection<UsuarioLinea> lccr_UsuarioLineas;
		lccr_UsuarioLineas = new LinkedList<UsuarioLinea>();

		try
		{
			lccr_UsuarioLineas = ipr_parameterRemote.findAllUsuarioLineas(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataUsuarioLineas(lccr_UsuarioLineas);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_UsuarioLineas;
	}

	/**
	 * Cargar usuario lineas orip calificador.
	 */
	public void cargarUsuarioLineasOripCalificador()
	{
		Collection<Usuario> lcu_usuario;
		lcu_usuario = null;

		try
		{
			lcu_usuario = irr_referenceRemote.findUsersByRolOripActivos(
				    getIdCirculoOrip(), RolCommon.CB_ROL_CALIFICADOR, isVieneDeAprobacionApoyoNacional(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		setUsuariosBandeja(lcu_usuario);
	}

	/**
	 * Método para limpiar los datos.
	 */
	public void clear()
	{
		setUsuariosBandeja(null);
		setInsert(false);
		setVieneDeAprobacionApoyoNacional(false);
		setAllLineasPorUsuario(null);
		setDataUsuarioLineas(null);
	}

	/**
	 * Consultar usuarios.
	 */
	public void consultarUsuarios()
	{
		Collection<Usuario> lcu_usuario;
		lcu_usuario = new LinkedList<Usuario>();

		try
		{
			lcu_usuario = irr_referenceRemote.findAllUsersActivos(
				    "", getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		setUsuariosBandeja(lcu_usuario);
	}

	/**
	 * Find all lineas produccion.
	 */
	public void findAllLineasProduccion()
	{
		Collection<LineaProduccion> lclp_datos;
		lclp_datos = null;

		Collection<UsuarioLinea> lcul_datos;
		lcul_datos = new ArrayList<UsuarioLinea>();

		try
		{
			lclp_datos = ipr_parameterRemote.findAllLineasProduccion();

			if(CollectionUtils.isValidCollection(lclp_datos))
			{
				for(LineaProduccion llp_temp : lclp_datos)
				{
					UsuarioLinea lul_tmp;
					lul_tmp = new UsuarioLinea();

					lul_tmp.setLineaProduccion(llp_temp);
					lcul_datos.add(lul_tmp);
				}
			}

			setAllLineasPorUsuario(lcul_datos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<UsuarioLinea> findLineasPorUsuario()
	{
		Collection<UsuarioLinea> lcul_datos;
		lcul_datos = null;

		try
		{
			lcul_datos = ipr_parameterRemote.findLineasPorUsuario(
				    getUsuarioLinea(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcul_datos;
	}

	/**
	 * Regresar a la pagina anterior.
	 *
	 * @return el valor de string
	 */
	public String regresar()
	{
		String ls_return;
		ls_return = NavegacionCommon.USUARIO_LINEAS;
		
		if(isVieneDeAprobacionApoyoNacional())
			cargarUsuarioLineasOripCalificador();
		
		return ls_return;
	}
	/**
	 * Método que instancia algunos objetos de la clase e invoca a consultarUsuarios.
	 */
	public void iniciar()
	{
		clear();
		iul_usuarioLinea = new UsuarioLinea();
		consultarUsuarios();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param aul_param correspondiente al valor del tipo de objeto UsuarioLinea
	 * @param ab_insertar correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 *
	 */
	public String insertUpdateUsuarioLinea(UsuarioLinea aul_param, boolean ab_insertar)
	{
		FacesContext lfc_context;
		boolean      lb_focus;
		UsuarioLinea lcr_UsuarioLineaInsertUpdate;
		lb_focus = true;

		if(aul_param == null)
			lcr_UsuarioLineaInsertUpdate = getUsuarioLinea();
		else
		{
			lcr_UsuarioLineaInsertUpdate = aul_param;
			lcr_UsuarioLineaInsertUpdate.setUsuario(getUsuarioLinea().getUsuario());
		}

		lfc_context = FacesContext.getCurrentInstance();

		try
		{
			if(!ab_insertar)
			{
				{
					String ls_activo;
					ls_activo = lcr_UsuarioLineaInsertUpdate.getActivo();

					validateStyles(
					    ":fUsuarioLineasDetalle:idDtListadoTable:idSOMActivo", lfc_context, ls_activo, lb_focus
					);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}
			}

			ipr_parameterRemote.insertUpdateUsuarioLinea(
			    lcr_UsuarioLineaInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fUsuarioLineas:gUsuarioLineasMsg");

			consultarUsuarios();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.USUARIO_LINEAS;
	}

	/**
	 * Metodo encargado de insertar los usuarios usuarios seleccionados.
	 */
	public void insertUsuarios()
	{
		Collection<UsuarioLinea> lcul_datos;
		Collection<UsuarioLinea> lcul_usuariosLineas;

		lcul_datos              = getAllLineasPorUsuario();
		lcul_usuariosLineas     = new ArrayList<UsuarioLinea>();

		try
		{
			if(CollectionUtils.isValidCollection(lcul_datos))
			{
				for(UsuarioLinea lul_tmp : lcul_datos)
				{
					if(lul_tmp.isSeleccionado())
					{
						if(lul_tmp.getActivo() != null)
							lcul_usuariosLineas.add(lul_tmp);
						else
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
					}
				}
			}

			if(CollectionUtils.isValidCollection(lcul_usuariosLineas))
			{
				Collection<UsuarioLinea> lcul_usuariosRegistrados;

				lcul_usuariosRegistrados = getLineasPorUsuario();

				for(UsuarioLinea lul_tmpUsuario : lcul_usuariosLineas)
				{
					if(lul_tmpUsuario != null)
					{
						if(CollectionUtils.isValidCollection(lcul_usuariosRegistrados))
						{
							for(UsuarioLinea lul_temp : lcul_usuariosRegistrados)
							{
								if(lul_temp != null)
								{
									LineaProduccion llp_lineaProduccion;
									LineaProduccion llp_lineaProducccionInsertada;

									llp_lineaProduccion               = lul_tmpUsuario.getLineaProduccion();
									llp_lineaProducccionInsertada     = lul_temp.getLineaProduccion();

									if((llp_lineaProduccion != null) && (llp_lineaProducccionInsertada != null))
									{
										String ls_idLineaProduccion;
										String ls_idLineaProduccionInsertada;

										ls_idLineaProduccion              = llp_lineaProduccion.getIdLineaProduccion();
										ls_idLineaProduccionInsertada     = llp_lineaProducccionInsertada
												.getIdLineaProduccion();

										if(
										    StringUtils.isValidString(ls_idLineaProduccion)
											    && ls_idLineaProduccion.equalsIgnoreCase(ls_idLineaProduccionInsertada)
										)
										{
											String       ls_usuario;
											UsuarioLinea lul_usuarioLinea;

											lul_usuarioLinea     = getUsuarioLinea();
											ls_usuario           = null;

											if(lul_usuarioLinea != null)
											{
												Usuario lu_usuarioNombre;

												lu_usuarioNombre = lul_usuarioLinea.getUsuario();

												if(lu_usuarioNombre != null)
													ls_usuario = StringUtils.isValidString(
														    lu_usuarioNombre.getIdUsuario()
														) ? lu_usuarioNombre.getIdUsuario()
														  : IdentificadoresCommon.DATO_INVALIDO;
											}

											Object[] loa_messageArgs;

											loa_messageArgs        = new String[2];
											loa_messageArgs[0]     = llp_lineaProduccion.getNombre();
											loa_messageArgs[1]     = ls_usuario;

											throw new B2BException(
											    ErrorKeys.ERROR_ID_USUARIO_LINEA_REPETIDO, loa_messageArgs
											);
										}
									}
								}
							}
						}

						insertUpdateUsuarioLinea(lul_tmpUsuario, true);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LINEA_PRODUCCION);

			setLineasPorUsuario(findLineasPorUsuario());
			findAllLineasProduccion();

			PrimeFaces.current().ajax().update("fUsuarioLineasDetalle");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Terminar parametrización.
	 *
	 * @return el valor de string
	 */
	public String terminarParametrizacion()
	{
		String              ls_return;
		Collection<Usuario> lcu_usuario;

		lcu_usuario     = null;
		ls_return       = null;

		try
		{
			lcu_usuario = irr_referenceRemote.findUsersLineaProduccionByRolOripActivos(
				    getIdCirculoOrip(), RolCommon.CB_ROL_CALIFICADOR, LineaProduccionCommon.APOYO_NACIONAL, getUserId(),
				    getLocalIpAddress(), getRemoteIpAddress()
				);

			if(!CollectionUtils.isValidCollection(lcu_usuario))
				throw new B2BException(ErrorKeys.ERROR_HABILITAR_CALIFICADOR_LINEA_APOYO_NACIONAL);
			else
			{
				FacesContext            lfc_context;
				BeanPredioDocumentoActo lbpdab_bean;

				lfc_context     = FacesUtils.getFacesContext();

				lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
						                                              .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
						);

				if(lbpdab_bean != null)
				{
					Collection<SolicitudApoyoRegionalOrip> lcsaro_solicitudesOrip;
					lcsaro_solicitudesOrip = lbpdab_bean.getSolicitudRegionalOrip();

					if(CollectionUtils.isValidCollection(lcsaro_solicitudesOrip))
					{
						for(SolicitudApoyoRegionalOrip lsaro_tmp : lcsaro_solicitudesOrip)
						{
							if(lsaro_tmp != null)
							{
								String ls_idCirculoOripTMP;
								ls_idCirculoOripTMP = StringUtils.getStringNotNull(lsaro_tmp.getIdCirculoRegional());

								if(
								    ls_idCirculoOripTMP.equalsIgnoreCase(
									        StringUtils.getStringNotNull(getIdCirculoOrip())
									    )
								) {
									lsaro_tmp.setParametrizacionCalificadores(IdentificadoresCommon.S);
									lbpdab_bean.setHabilitarApoyoNacional(true);
								}
							}
						}
					}

					ls_return = NavegacionCommon.DETALLE_ACTO;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}
}
