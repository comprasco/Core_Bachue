package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.CuentaAnalista;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;

import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de la capa web para CuentaAnalista
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanCuentaAnalista")
@SessionScoped
public class BeanCuentaAnalista extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4417879768713343148L;

	/** Propiedad iccmv datos auditoria. */
	private Collection<CuentaAnalista> iccmv_datosAuditoria;

	/** Propiedad icerc entidadRecaudadoraCuenta*/
	private Collection<EntidadRecaudadoraCuenta> icerc_entidadRecaudadoraCuenta;

	/** Propiedad icu usuarios*/
	private Collection<Usuario> icu_usuarios;

	/** Propiedad ir CuentaAnalista. */
	private CuentaAnalista ir_CuentaAnalista;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/**Propiedad numero recaudadora*/
	private String is_idCuenta;

	/**Propiedad id entidad recaudadora*/
	private String is_idEntidadRecaudadora;

	/**Propiedad id usuario*/
	private String is_idUsuarioInicial;

	/**Propiedad numero recaudadora*/
	private String is_numeroCuenta;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ar_CuentaAnalista asigna el valor a la propiedad
	 */
	public void setCuentaAnalista(CuentaAnalista ar_CuentaAnalista)
	{
		ir_CuentaAnalista = ar_CuentaAnalista;
	}

	/**
	 * Método de obtencion del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public CuentaAnalista getCuentaAnalista()
	{
		return ir_CuentaAnalista;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param acmv_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<CuentaAnalista> acmv_datosAuditoria)
	{
		iccmv_datosAuditoria = acmv_datosAuditoria;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CuentaAnalista> getDatosAuditoria()
	{
		return iccmv_datosAuditoria;
	}

	/**
	 * @param Modifica el valor de la propiedad icerc_entidadRecaudadoraCuenta por icerc_entidadRecaudadoraCuenta
	 */
	public void setEntidadRecaudadoraCuenta(Collection<EntidadRecaudadoraCuenta> acerc_entidadRecaudadoraCuenta)
	{
		icerc_entidadRecaudadoraCuenta = acerc_entidadRecaudadoraCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad icerc_entidadRecaudadoraCuenta
	 */
	public Collection<EntidadRecaudadoraCuenta> getEntidadRecaudadoraCuenta()
	{
		return icerc_entidadRecaudadoraCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idCuenta por is_idCuenta
	 */
	public void setIdCuenta(String is_idCuenta)
	{
		this.is_idCuenta = is_idCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idEntidadRecaudadora por as_idEntidadRecaudadora
	 */
	public void setIdEntidadRecaudadora(String as_idEntidadRecaudadora)
	{
		is_idEntidadRecaudadora = as_idEntidadRecaudadora;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idEntidadRecaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idUsuario por is_idUsuario
	 */
	public void setIdUsuarioInicial(String as_idUsuario)
	{
		is_idUsuarioInicial = as_idUsuario;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idUsuario
	 */
	public String getIdUsuarioInicial()
	{
		return is_idUsuarioInicial;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_insertar asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_insertar)
	{
		ib_insertar = ab_insertar;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param Modifica el valor de la propiedad is_numeroCuenta por is_numeroCuenta
	 */
	public void setNumeroCuenta(String as_numeroCuenta)
	{
		is_numeroCuenta = as_numeroCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroCuenta
	 */
	public String getNumeroCuenta()
	{
		return is_numeroCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad icu_usuarios por icu_usuarios
	 */
	public void setUsuarios(Collection<Usuario> icu_usuarios)
	{
		this.icu_usuarios = icu_usuarios;
	}

	/**
	 * @return Retorna el valor de la propiedad icu_usuarios
	 */
	public Collection<Usuario> getUsuarios()
	{
		return icu_usuarios;
	}

	/**
	 * Método de actualización de la pantalla de Entidad Recaudadora Conciliacion
	 */
	public void actualizarCorreoAnalista()
	{
		Collection<Usuario> lcerc_collectionEntidad;

		CuentaAnalista      lca_cuentaAnalista;
		lca_cuentaAnalista = getCuentaAnalista();

		if(lca_cuentaAnalista != null)
		{
			String ls_idUsuario;
			ls_idUsuario                = lca_cuentaAnalista.getIdUsuario();
			lcerc_collectionEntidad     = getUsuarios();

			if(CollectionUtils.isValidCollection(lcerc_collectionEntidad) && StringUtils.isValidString(ls_idUsuario))
			{
				for(Usuario ilerc : lcerc_collectionEntidad)
				{
					if(ilerc != null)
					{
						if(ls_idUsuario.equals(ilerc.getIdUsuario()))
							lca_cuentaAnalista.setCorreoElectronicoAnalista(ilerc.getCorreoElectronico());
					}
				}
			}
		}
	}

	/**
	 *método para encontrar todos los usuario.
	 * @return Colección con datos solicitados
	 */
	public Collection<Usuario> buscarUsuariosRolCatalogo()
	{
		Collection<Usuario> lcuo_datos;

		try
		{
			lcuo_datos = ipr_parameterRemote.buscarUsuariosRolCatalogo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcuo_datos = null;
		}

		setUsuarios(lcuo_datos);

		return lcuo_datos;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo CuentaAnalista
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCuentaAnalista((new CuentaAnalista()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCuentaAnalista");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consulta detalla para un CuentaAnalista en especial
	 * @param ar_r de tipo CuentaAnalista
	 * @throws B2BException
	 */
	public void consultaDetallada(CuentaAnalista ar_r)
	    throws IOException
	{
		try
		{
			if(ar_r != null)
			{
				String         ls_idCuentaAnalista;
				CuentaAnalista latd_td;
				latd_td                 = new CuentaAnalista();
				ls_idCuentaAnalista     = ar_r.getIdCuentaAnalista();
				latd_td.setIdCuentaAnalista(ls_idCuentaAnalista);

				latd_td = ipr_parameterRemote.findCuentaAnalistaById(latd_td);

				if(latd_td != null)
				{
					validarEstadoCuenta(latd_td.getIdCuenta());

					Collection<CuentaAnalista> lctd_td;

					lctd_td = new ArrayList<CuentaAnalista>();

					lctd_td.add(latd_td);
					setIdUsuarioInicial(latd_td.getIdUsuario());
					setCuentaAnalista(latd_td);
					findAllEntidadRecaudadoraCuentaByidCuenta();

					setDatosAuditoria(lctd_td);
				}

				setInsertar(false);
				FacesContext.getCurrentInstance().getExternalContext().redirect("cuentaAnalistaDetalle.jsf");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCuentaAnalista:globalMsg");
		}
	}

	/**
	 * Método de consulta de todos los CuentaAnalistas
	 * @return una colleccion de tipo CuentaAnalista
	 */
	public Collection<CuentaAnalista> findAllCuentaAnalista()
	{
		Collection<CuentaAnalista> lccmv_cmv;
		lccmv_cmv = null;

		try
		{
			lccmv_cmv = ipr_parameterRemote.findAllCuentaAnalista();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccmv_cmv;
	}

	/**
	 * Método de consulta de todos los EntidadRecaudadoraConciliacions
	 * @return una colleccion de tipo EntidadRecaudadoraConciliacion
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcerc_erc;
		lcerc_erc = null;

		try
		{
			lcerc_erc = ipr_parameterRemote.findAllEntidadRecaudadoraConciliacion(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcerc_erc;
	}

	/**
	 * Método de consulta de todos los EntidadRecaudadoraCuenta por id entidad
	 * @return una colleccion de tipo EntidadRecaudadoraCuenta
	 */
	public Collection<EntidadRecaudadoraCuenta> findAllEntidadRecaudadoraCuentaByEntidadRecaudadora()
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_erc;
		lcerc_erc = null;

		try
		{
			lcerc_erc = ipr_parameterRemote.findAllEntidadRecaudadoraCuentaByEntidadRecaudadora(
				    getIdEntidadRecaudadora()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		setEntidadRecaudadoraCuenta(lcerc_erc);

		return lcerc_erc;
	}

	/**
	 * Método de consulta de todos los EntidadRecaudadoraCuenta por id entidad
	 */
	public void findAllEntidadRecaudadoraCuentaByidCuenta()
	{
		EntidadRecaudadoraCuenta lcerc_erc;
		lcerc_erc = new EntidadRecaudadoraCuenta();

		CuentaAnalista latd_td;
		latd_td = getCuentaAnalista();

		try
		{
			if(latd_td != null)
			{
				String ls_idcuenta;
				ls_idcuenta = latd_td.getIdCuenta();
				lcerc_erc.setIdCuenta(ls_idcuenta);
				lcerc_erc = ipr_parameterRemote.findEntidadRecaudadoraCuentaById(lcerc_erc);

				if(lcerc_erc != null)
				{
					setNumeroCuenta(lcerc_erc.getNumeroCuenta());
					setIdCuenta(ls_idcuenta);
					setIdEntidadRecaudadora(lcerc_erc.getIdEntidadRecaudadora());
					PrimeFaces.current().ajax().update("fEntidadRecaudadoraCuentaDetalle:idNombreEntidad");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para limpiar las variables del mundo
	 */
	public void limpiar()
	{
		setCuentaAnalista(null);
		setDatosAuditoria(null);
		setEntidadRecaudadoraCuenta(null);
		setIdCuenta(null);
		setIdEntidadRecaudadora(null);
		setIdUsuarioInicial(null);
		setInsertar(false);
	}

	/**
	 * Método para salvar la inserción o actualización.
	 *
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public void salvar()
	    throws IOException
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			CuentaAnalista lr_CuentaAnalista;

			lr_CuentaAnalista = getCuentaAnalista();

			if(lr_CuentaAnalista != null)
			{
				String ls_dato;

				if(isInsertar())
				{
					ls_dato     = lr_CuentaAnalista.getIdCuenta();

					lb_focus = validateStyles(":fCuentaAnalistaDetalle:idCuenta", lfc_context, ls_dato, lb_focus);
				}
				else
					lr_CuentaAnalista.setIdCuenta(getIdCuenta());

				{
					ls_dato     = lr_CuentaAnalista.getIdUsuario();

					lb_focus = validateStyles(":fCuentaAnalistaDetalle:idUsuario", lfc_context, ls_dato, lb_focus);
				}

				{
					ls_dato     = lr_CuentaAnalista.getActivo();

					lb_focus = validateStyles(":fCuentaAnalistaDetalle:idActivo", lfc_context, ls_dato, lb_focus);
				}

				{
					ls_dato     = lr_CuentaAnalista.getCorreoElectronicoAnalista();

					lb_focus = validateStyles(":fCuentaAnalistaDetalle:idITCorreo", lfc_context, ls_dato, lb_focus);
				}

				ipr_parameterRemote.salvarCuentaAnalista(
				    lr_CuentaAnalista, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				if(isInsertar())
					addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);
				else
					addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);

				setCuentaAnalista(null);

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

					FacesContext.getCurrentInstance().getExternalContext().redirect("cuentaAnalista.jsf");
					PrimeFaces.current().ajax().update("fCuentaAnalista:globalMsg");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCuentaAnalistaDetalle:globalMsg");
		}
	}

	/**
	 * Método de validación del activo para la actualización de registros
	 */
	public void validarActivo()
	    throws IOException
	{
		if(!isInsertar())
		{
			CuentaAnalista lca_cuentaAnalista;
			String         ls_activo;

			lca_cuentaAnalista = getCuentaAnalista();

			if(lca_cuentaAnalista != null)
			{
				ls_activo = lca_cuentaAnalista.getActivo();

				if(StringUtils.isValidString(ls_activo) && ls_activo.equals("N"))
					PrimeFaces.current().executeScript("PF('idDCofirmaTD').show();");
				else
					salvar();
			}
		}
		else
			salvar();
	}

	/**
	 * Método de validación del activo para la actualización de registros
	 */
	public void validarAnalista()
	    throws IOException
	{
		if(!isInsertar())
		{
			CuentaAnalista lca_cuentaAnalista;
			String         ls_idUsuario;

			lca_cuentaAnalista = getCuentaAnalista();

			if(lca_cuentaAnalista != null)
			{
				ls_idUsuario = lca_cuentaAnalista.getIdUsuario();

				if(StringUtils.isValidString(ls_idUsuario) && !ls_idUsuario.equals(getIdUsuarioInicial()))
					PrimeFaces.current().executeScript("PF('idDCofirmaAnalistaTD').show();");
				else
					validarActivo();
			}
		}
		else
			validarActivo();
	}

	/**
	 * Método de validacion del estado activo de la cuenta
	 * @param ls_adEntidadRecaudadoraCuenta con el id de la cuenta a validar
	 * @throws B2BException
	 */
	public void validarEstadoCuenta(String ls_adEntidadRecaudadoraCuenta)
	    throws B2BException
	{
		EntidadRecaudadoraCuenta lerc_erc;
		lerc_erc = new EntidadRecaudadoraCuenta();
		lerc_erc.setIdCuenta(ls_adEntidadRecaudadoraCuenta);

		lerc_erc = ipr_parameterRemote.findEntidadRecaudadoraCuentaById(lerc_erc, true);
	}
}
