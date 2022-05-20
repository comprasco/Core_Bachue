package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad del Bean de BeanUsuarioCirculos.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanUsuarioCirculos")
@SessionScoped
public class BeanUsuarioCirculos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8983392885331917222L;

	/** Propiedad icu usuarios bandeja. */
	private Collection<Usuario> icu_usuariosBandeja;

	/** Propiedad icuc circulos por usuario. */
	private Collection<UsuarioCirculo> icuc_circulosPorUsuario;

	/** Propiedad icuc data usuario circulos. */
	private Collection<UsuarioCirculo> icuc_dataUsuarioCirculos;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad iuc usuario circulo. */
	private UsuarioCirculo iuc_usuarioCirculo;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de circulos por usuario.
	 *
	 * @param acul_cul asigna el valor a la propiedad circulos por usuario
	 */
	public void setCirculosPorUsuario(Collection<UsuarioCirculo> acul_cul)
	{
		icuc_circulosPorUsuario = acul_cul;
	}

	/**
	 * Retorna el valor de circulos por usuario.
	 *
	 * @return el valor de circulos por usuario
	 */
	public Collection<UsuarioCirculo> getCirculosPorUsuario()
	{
		return icuc_circulosPorUsuario;
	}

	/**
	 * Modifica el valor de data usuario circulos.
	 *
	 * @param accr_ccr asigna el valor a la propiedad data usuario circulos
	 */
	public void setDataUsuarioCirculos(Collection<UsuarioCirculo> accr_ccr)
	{
		icuc_dataUsuarioCirculos = accr_ccr;
	}

	/**
	 * Retorna el valor de data usuario circulos.
	 *
	 * @return el valor de data usuario circulos
	 */
	public Collection<UsuarioCirculo> getDataUsuarioCirculos()
	{
		if(icuc_dataUsuarioCirculos == null)
			icuc_dataUsuarioCirculos = new LinkedList<UsuarioCirculo>();

		return icuc_dataUsuarioCirculos;
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
	 * Modifica el valor de usuario circulo.
	 *
	 * @param acr_cr asigna el valor a la propiedad usuario circulo
	 */
	public void setUsuarioCirculo(UsuarioCirculo acr_cr)
	{
		iuc_usuarioCirculo = acr_cr;
	}

	/**
	 * Retorna el valor de usuario circulo.
	 *
	 * @return el valor de usuario circulo
	 */
	public UsuarioCirculo getUsuarioCirculo()
	{
		return iuc_usuarioCirculo;
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
	 * Retorna el valor del objeto de String de la vista.
	 *
	 * @param acr_UsuarioCirculoseleccionado correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(Usuario acr_UsuarioCirculoseleccionado)
	{
		String ls_return;
		ls_return = null;

		UsuarioCirculo lul_temp;
		lul_temp = new UsuarioCirculo();

		if(acr_UsuarioCirculoseleccionado == null)
			acr_UsuarioCirculoseleccionado = new Usuario();

		lul_temp.setUsuario(acr_UsuarioCirculoseleccionado);

		setUsuarioCirculo(lul_temp);

		setCirculosPorUsuario(findCirculosPorUsuario());

		ls_return = NavegacionCommon.USUARIO_CIRCULOS_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioCirculo> cargarUsuarioCirculos()
	{
		Collection<UsuarioCirculo> lccr_UsuarioCirculos;
		lccr_UsuarioCirculos = new LinkedList<UsuarioCirculo>();

		try
		{
			lccr_UsuarioCirculos = ipr_parameterRemote.findAllUsuarioCirculos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataUsuarioCirculos(lccr_UsuarioCirculos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_UsuarioCirculos;
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
	public Collection<CirculoRegistral> findAllCirculosActivos()
	{
		Collection<CirculoRegistral> lccr_datos;
		lccr_datos = null;

		try
		{
			lccr_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
				    false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
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
	public Collection<UsuarioCirculo> findCirculosPorUsuario()
	{
		Collection<UsuarioCirculo> lcul_datos;
		lcul_datos = null;

		try
		{
			lcul_datos = ipr_parameterRemote.findCirculosPorUsuario(
				    getUsuarioCirculo(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
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
		iuc_usuarioCirculo = new UsuarioCirculo();
		setInsert(false);
		consultarUsuarios();
	}

	/**
	 * Retorna el valor del objeto de String de la vista
	 *
	 * @param aul_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @param ab_insertar correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String insertUpdateUsuarioCirculo(UsuarioCirculo aul_param, boolean ab_insertar)
	{
		FacesContext   lfc_context;
		UsuarioCirculo lcr_UsuarioCirculoInsertUpdate;
		boolean        lb_focus;

		if(aul_param == null)
			lcr_UsuarioCirculoInsertUpdate = getUsuarioCirculo();
		else
			lcr_UsuarioCirculoInsertUpdate = aul_param;

		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			if(ab_insertar)
			{
				{
					String ls_idCirculo;
					ls_idCirculo     = lcr_UsuarioCirculoInsertUpdate.getCirculo().getIdCirculo();

					lb_focus = validateStyles(
						    ":fUsuarioCirculosDetalle:idSOMCirculoInsert", lfc_context, ls_idCirculo, lb_focus
						);

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				{
					String ls_activo;
					ls_activo     = lcr_UsuarioCirculoInsertUpdate.getActivo();

					lb_focus = validateStyles(":fUsuarioCirculosDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					Date ld_fechaDesde;
					ld_fechaDesde     = lcr_UsuarioCirculoInsertUpdate.getFechaDesde();

					lb_focus = validateStyles(
						    ":fUsuarioCirculosDetalle:idCalFechaDesde", lfc_context, ld_fechaDesde, lb_focus
						);

					if(ld_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}
			}
			else
			{
				{
					Date ld_fechaDesde;
					ld_fechaDesde     = lcr_UsuarioCirculoInsertUpdate.getFechaDesde();

					lb_focus = validateStyles(
						    ":fUsuarioCirculosDetalle:idDtListado:idCalFechaDesde", lfc_context, ld_fechaDesde, lb_focus
						);

					if(ld_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				{
					String ls_activo;
					ls_activo     = lcr_UsuarioCirculoInsertUpdate.getActivo();

					lb_focus = validateStyles(
						    ":fUsuarioCirculosDetalle:idDtListado:idSOMActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}
			}

			lcr_UsuarioCirculoInsertUpdate.setUsuario(getUsuarioCirculo().getUsuario());
			ipr_parameterRemote.insertUpdateUsuarioCirculo(
			    lcr_UsuarioCirculoInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fUsuarioCirculos:gUsuarioCirculosMsg");

			consultarUsuarios();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.USUARIO_CIRCULOS;
	}
}
