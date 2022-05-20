package com.bachue.snr.prosnr21.web.bean.parameter;

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

import org.primefaces.PrimeFaces;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.SIIFCatalogo;
import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.util.FacesUtils;


/**
 * Clase para el manejo de la capa web para SIIF Catalogo
 *
 * @author Gabriel Arias
 */
@ManagedBean(name = "beanSIIFCatalogo")
@SessionScoped
public class BeanSIIFCatalogo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8438000893949595093L;

	/** Propiedad icerc entidadRecaudadoraCuenta */
	private Collection<EntidadRecaudadoraCuenta> icerc_entidadRecaudadoraCuenta;

	/** Propiedad iccmv datos auditoria. */
	private Collection<SIIFCatalogo> icsc_datosAuditoria;

	/** Propiedad icu usuarios */
	private Collection<Usuario> icu_usuarios;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir CuentaAnalista. */
	private SIIFCatalogo ir_SIIFCatalogo;

	/** Propiedad numero recaudadora */
	private String is_idCuenta;

	/** Propiedad id entidad recaudadora */
	private String is_idEntidadRecaudadora;

	/** Propiedad numero recaudadora */
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
	 *
	 * @param acsc_datosAuditoria
	 *            asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<SIIFCatalogo> acsc_datosAuditoria)
	{
		icsc_datosAuditoria = acsc_datosAuditoria;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<SIIFCatalogo> getDatosAuditoria()
	{
		return icsc_datosAuditoria;
	}

	/**
	 * @param Modifica
	 *            el valor de la propiedad icerc_entidadRecaudadoraCuenta por
	 *            icerc_entidadRecaudadoraCuenta
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
	 * @param Modifica
	 *            el valor de la propiedad is_idCuenta por is_idCuenta
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
	 * @param Modifica
	 *            el valor de la propiedad is_idEntidadRecaudadora por
	 *            as_idEntidadRecaudadora
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
	 * Método de asignación del valor de la propiedad
	 *
	 * @param ab_insertar
	 *            asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_insertar)
	{
		ib_insertar = ab_insertar;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param Modifica
	 *            el valor de la propiedad is_numeroCuenta por is_numeroCuenta
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
	 * Método de asignación del valor de la propiedad
	 *
	 * @param ar_SIIFCatalogo
	 *            asigna el valor a la propiedad
	 */
	public void setSIIFCatalogo(SIIFCatalogo ar_SIIFCatalogo)
	{
		ir_SIIFCatalogo = ar_SIIFCatalogo;
	}

	/**
	 * Método de obtencion del valor de la propiedad
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public SIIFCatalogo getSIIFCatalogo()
	{
		return ir_SIIFCatalogo;
	}

	/**
	 * @param Modifica
	 *            el valor de la propiedad icu_usuarios por icu_usuarios
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
	 * método para encontrar todos los usuario.
	 *
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
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * CuentaAnalista
	 */
	public void cambiarEstado()
	{
		// TODO
		setInsertar(true);
		setSIIFCatalogo((new SIIFCatalogo()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarSIIFCatalogo");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consulta detalla para un CuentaAnalista en especial
	 *
	 * @param asf_param
	 *            de tipo CuentaAnalista
	 * @throws B2BException
	 */
	public void consultaDetallada(SIIFCatalogo asf_param)
	    throws IOException
	{
		// TODO
		try
		{
			if(asf_param != null)
			{
				String       ls_idCuentaAnalista;
				SIIFCatalogo lsc_data;

				lsc_data                = new SIIFCatalogo();
				ls_idCuentaAnalista     = asf_param.getIdSiifCatalogo();

				lsc_data.setIdSiifCatalogo(ls_idCuentaAnalista);

				lsc_data = ipr_parameterRemote.findSIIFCatalogoById(lsc_data);

				if(lsc_data != null)
				{
					Collection<SIIFCatalogo> lctd_td;

					lctd_td = new ArrayList<SIIFCatalogo>();

					lctd_td.add(lsc_data);
					setSIIFCatalogo(lsc_data);

					setDatosAuditoria(lctd_td);
				}

				setInsertar(false);
				FacesContext.getCurrentInstance().getExternalContext().redirect("siifCatalogoDetalle.jsf");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fSiifCatalogo:globalMsg");
		}
	}

	/**
	 * Método de consulta de todos los EntidadRecaudadoraConciliacions
	 *
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
	 *
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
	 * Método de consulta de todos los SIIFCatalogo
	 *
	 * @return una colleccion de tipo SIIFCatalogo
	 */
	public Collection<SIIFCatalogo> findAllSIIFCatalogo()
	{
		// TODO
		Collection<SIIFCatalogo> lcsc_data;

		lcsc_data = null;

		try
		{
			lcsc_data = ipr_parameterRemote.findAllSIIFCatalogo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcsc_data;
	}

	/**
	 * Find tipo archivo.
	 *
	 * @return the collection
	 * @throws B2BException
	 */
	public Collection<String> findTipoArchivo()
	    throws B2BException
	{
		Collection<String> lcs_result;
		Collection<String> lcs_tipoArchivo;
		
		lcs_result = new ArrayList<>(0);
		lcs_tipoArchivo = ipr_parameterRemote.findCaracteresConstanteById(
			    ConstanteCommon.TIPO_ARCHIVO_SIIF, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
		
		
		if (CollectionUtils.isValidCollection(lcs_tipoArchivo)) 
		{
			for (String ls_string : lcs_tipoArchivo) {
				
				ls_string = ls_string.trim();
			
				if(ls_string.equalsIgnoreCase(ConstanteCommon.ARCHIVO_MAESTRO)) 
				{
					ls_string= ConstanteCommon.MAESTRO; 
				}else if (ls_string.equalsIgnoreCase(ConstanteCommon.ARCHIVO_DETALLE)) {
					ls_string= ConstanteCommon.DETALLE;
				}else {
					ls_string = "";
				}
				
				lcs_result.add(ls_string);
			}
				
		}
		
		return lcs_result;
	}

	/**
	 * Método para limpiar las variables del mundo
	 */
	public void limpiar()
	{
		setSIIFCatalogo(null);
		setDatosAuditoria(null);
		setEntidadRecaudadoraCuenta(null);
		setIdCuenta(null);
		setIdEntidadRecaudadora(null);
		setInsertar(false);
	}

	/**
	 * Método para salvar la inserción o actualización.
	 *
	 * @throws IOException
	 *             Objeto de tipo IOException, se produce cuando se encuentra algun
	 *             error de tipo I/O.
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
			SIIFCatalogo lsc_data;

			lsc_data = getSIIFCatalogo();

			if(lsc_data != null)
			{
				String ls_dato;

				{
					ls_dato      = lsc_data.getNemotecnico();
					lb_focus     = validateStyles(
						    ":fSiifCatalogoDetalle:idNemotecnico", lfc_context, ls_dato, lb_focus
						);
				}

				{
					ls_dato      = lsc_data.getNombre();
					lb_focus     = validateStyles(":fSiifCatalogoDetalle:idNombre", lfc_context, ls_dato, lb_focus);
				}

				{
					ls_dato      = lsc_data.getCodigo();
					lb_focus     = validateStyles(":fSiifCatalogoDetalle:idCodigo", lfc_context, ls_dato, lb_focus);
				}

				{
					ls_dato      = lsc_data.getDescripcion();
					lb_focus     = validateStyles(
						    ":fSiifCatalogoDetalle:idDescripcion", lfc_context, ls_dato, lb_focus
						);
				}

				{
					ls_dato      = lsc_data.getArchivo();
					lb_focus     = validateStyles(":fSiifCatalogoDetalle:idArchivo", lfc_context, ls_dato, lb_focus);
				}

				{
					ls_dato      = lsc_data.getActivo();
					lb_focus     = validateStyles(":fSiifCatalogoDetalle:idActivo", lfc_context, ls_dato, lb_focus);
				}

				ipr_parameterRemote.salvarSIIFCatalogo(
				    lsc_data, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				if(isInsertar())
					addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);
				else
					addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);

				setSIIFCatalogo(null);

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

					FacesContext.getCurrentInstance().getExternalContext().redirect("siifCatalogo.jsf");
					PrimeFaces.current().ajax().update("fSiifCatalogo:globalMsg");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fSiifCatalogoDetalle:globalMsg");
		}
	}

	/**
	 * Método de validacion del estado activo de la cuenta
	 *
	 * @param ls_adEntidadRecaudadoraCuenta
	 *            con el id de la cuenta a validar
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
