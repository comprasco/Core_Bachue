package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.png.UsuarioEtapa;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad del Bean de usuario etapa
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanUsuarioEtapas")
@SessionScoped
public class BeanUsuarioEtapas extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3871855396659200998L;

	/** Propiedad icu usuarios bandeja. */
	private Collection<Usuario> icu_usuariosBandeja;

	/** Propiedad icue data usuario etapas. */
	private Collection<UsuarioEtapa> icue_dataUsuarioEtapas;

	/** Propiedad icue etapas por usuario. */
	private Collection<UsuarioEtapa> icue_etapasPorUsuario;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad iue usuario etapa. */
	private UsuarioEtapa iue_usuarioEtapa;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de data usuario etapas.
	 *
	 * @param accr_ccr asigna el valor a la propiedad data usuario etapas
	 */
	public void setDataUsuarioEtapas(Collection<UsuarioEtapa> accr_ccr)
	{
		icue_dataUsuarioEtapas = accr_ccr;
	}

	/**
	 * Retorna el valor de data usuario etapas.
	 *
	 * @return el valor de data usuario etapas
	 */
	public Collection<UsuarioEtapa> getDataUsuarioEtapas()
	{
		if(icue_dataUsuarioEtapas == null)
			icue_dataUsuarioEtapas = new LinkedList<UsuarioEtapa>();

		return icue_dataUsuarioEtapas;
	}

	/**
	 * Modifica el valor de etapas por usuario.
	 *
	 * @param acul_cul asigna el valor a la propiedad etapas por usuario
	 */
	public void setEtapasPorUsuario(Collection<UsuarioEtapa> acul_cul)
	{
		icue_etapasPorUsuario = acul_cul;
	}

	/**
	 * Retorna el valor de etapas por usuario.
	 *
	 * @return el valor de etapas por usuario
	 */
	public Collection<UsuarioEtapa> getEtapasPorUsuario()
	{
		return icue_etapasPorUsuario;
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
	 * Modifica el valor de usuario etapa.
	 *
	 * @param acr_cr asigna el valor a la propiedad usuario etapa
	 */
	public void setUsuarioEtapa(UsuarioEtapa acr_cr)
	{
		iue_usuarioEtapa = acr_cr;
	}

	/**
	 * Retorna el valor de usuario etapa.
	 *
	 * @return el valor de usuario etapa
	 */
	public UsuarioEtapa getUsuarioEtapa()
	{
		return iue_usuarioEtapa;
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
	 * Retorna el valor del objeto de String.
	 *
	 * @param acr_UsuarioEtapaseleccionado correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(Usuario acr_UsuarioEtapaseleccionado)
	{
		String ls_return;
		ls_return = null;

		UsuarioEtapa lul_temp;
		lul_temp = new UsuarioEtapa();

		if(acr_UsuarioEtapaseleccionado == null)
			acr_UsuarioEtapaseleccionado = new Usuario();

		lul_temp.setUsuario(acr_UsuarioEtapaseleccionado);

		setUsuarioEtapa(lul_temp);

		setEtapasPorUsuario(findEtapasPorUsuario());

		ls_return = NavegacionCommon.USUARIO_ETAPAS_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioEtapa> cargarUsuarioEtapas()
	{
		Collection<UsuarioEtapa> lccr_UsuarioEtapas;
		lccr_UsuarioEtapas = new LinkedList<UsuarioEtapa>();

		try
		{
			lccr_UsuarioEtapas = ipr_parameterRemote.findAllUsuarioEtapas(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataUsuarioEtapas(lccr_UsuarioEtapas);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_UsuarioEtapas;
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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Etapa> findAllEtapasActivos()
	{
		Collection<Etapa> lccr_datos;
		lccr_datos = null;

		try
		{
			lccr_datos = irr_referenceRemote.findAllEtapasActivo(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioEtapa> findEtapasPorUsuario()
	{
		Collection<UsuarioEtapa> lcul_datos;
		lcul_datos = null;

		try
		{
			lcul_datos = ipr_parameterRemote.findEtapasPorUsuario(
				    getUsuarioEtapa(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcul_datos;
	}

	/**
	 * Método que instancia algunos objetos de la clase.
	 */
	public void iniciar()
	{
		iue_usuarioEtapa = new UsuarioEtapa();
		setInsert(false);
		consultarUsuarios();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param aul_param correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @param ab_insertar correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String insertUpdateUsuarioEtapa(UsuarioEtapa aul_param, boolean ab_insertar)
	{
		FacesContext lfc_context;
		UsuarioEtapa lcr_UsuarioEtapaInsertUpdate;
		boolean      lb_focus;

		if(aul_param == null)
			lcr_UsuarioEtapaInsertUpdate = getUsuarioEtapa();
		else
		{
			lcr_UsuarioEtapaInsertUpdate = aul_param;
			lcr_UsuarioEtapaInsertUpdate.setUsuario(getUsuarioEtapa().getUsuario());
		}

		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			if(ab_insertar)
			{
				{
					long ll_idEtapa;
					ll_idEtapa = lcr_UsuarioEtapaInsertUpdate.getEtapa().getIdEtapa();

					if(ll_idEtapa <= 0)
					{
						validateStyles(":fUsuarioEtapasDetalle:idSOMEtapaInsert", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ETAPA);
					}
					else
						lb_focus = validateStyles(
							    ":fUsuarioEtapasDetalle:idSOMEtapaInsert", lfc_context,
							    StringUtils.getString(ll_idEtapa), lb_focus
							);
				}

				{
					String ls_habilitado;
					ls_habilitado = lcr_UsuarioEtapaInsertUpdate.getActivo();

					if(!validateStyles(":fUsuarioEtapasDetalle:idSOMHabilitado", lfc_context, ls_habilitado, lb_focus))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_HABILITADO);
				}
			}
			else
			{
				{
					String ls_habilitado;
					ls_habilitado = lcr_UsuarioEtapaInsertUpdate.getActivo();

					if(
					    !validateStyles(
						        ":fUsuarioEtapasDetalle:idDtListado:idSOMHabilitado", lfc_context, ls_habilitado,
						        lb_focus
						    )
					)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_HABILITADO);
				}
			}

			ipr_parameterRemote.insertUpdateUsuarioEtapa(
			    lcr_UsuarioEtapaInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fUsuarioEtapas:gUsuarioEtapasMsg");

			consultarUsuarios();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.USUARIO_ETAPAS;
	}
}
