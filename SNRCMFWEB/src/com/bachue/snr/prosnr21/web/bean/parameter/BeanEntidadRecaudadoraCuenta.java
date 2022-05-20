package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;

import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

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
 * Clase para el manejo de la capa web para EntidadRecaudadoraCuenta
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanEntidadRecaudadoraCuenta")
@SessionScoped
public class BeanEntidadRecaudadoraCuenta extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4417879768713343148L;

	/** Propiedad iccmv datos auditoria. */
	private Collection<EntidadRecaudadoraCuenta> iccmv_datosAuditoria;

	/** Propiedad ir EntidadRecaudadoraCuenta. */
	private EntidadRecaudadoraCuenta ir_EntidadRecaudadoraCuenta;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

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
	 * @param acmv_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<EntidadRecaudadoraCuenta> acmv_datosAuditoria)
	{
		iccmv_datosAuditoria = acmv_datosAuditoria;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<EntidadRecaudadoraCuenta> getDatosAuditoria()
	{
		return iccmv_datosAuditoria;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ar_EntidadRecaudadoraCuenta asigna el valor a la propiedad
	 */
	public void setEntidadRecaudadoraCuenta(EntidadRecaudadoraCuenta ar_EntidadRecaudadoraCuenta)
	{
		ir_EntidadRecaudadoraCuenta = ar_EntidadRecaudadoraCuenta;
	}

	/**
	 * Método de obtencion del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public EntidadRecaudadoraCuenta getEntidadRecaudadoraCuenta()
	{
		return ir_EntidadRecaudadoraCuenta;
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
	 * Método para cambiar estado para saber si se desea insertar un nuevo EntidadRecaudadoraCuenta
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEntidadRecaudadoraCuenta((new EntidadRecaudadoraCuenta()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEntidadRecaudadoraCuenta");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consulta detalla para un EntidadRecaudadoraCuenta en especial
	 * @param aerc_erc de tipo EntidadRecaudadoraCuenta
	 * @throws B2BException
	 */
	public void consultaDetallada(EntidadRecaudadoraCuenta aerc_erc)
	    throws B2BException
	{
		if(aerc_erc != null)
		{
			String                   ls_idEntidadRecaudadoraCuenta;
			EntidadRecaudadoraCuenta lerc_erc;
			lerc_erc                          = new EntidadRecaudadoraCuenta();
			ls_idEntidadRecaudadoraCuenta     = aerc_erc.getIdCuenta();
			lerc_erc.setIdCuenta(ls_idEntidadRecaudadoraCuenta);

			lerc_erc = ipr_parameterRemote.findEntidadRecaudadoraCuentaById(lerc_erc);

			if(lerc_erc != null)
			{
				Collection<EntidadRecaudadoraCuenta> lctd_td;

				lctd_td = new ArrayList<EntidadRecaudadoraCuenta>();

				lctd_td.add(lerc_erc);
				setEntidadRecaudadoraCuenta(lerc_erc);

				setDatosAuditoria(lctd_td);
			}

			setInsertar(false);
		}
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
	 * Método de consulta de todos los EntidadRecaudadoraCuentas
	 * @return una colleccion de tipo EntidadRecaudadoraCuenta
	 */
	public Collection<EntidadRecaudadoraCuenta> findAllEntidadRecaudadoraCuenta()
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_erc;
		lcerc_erc = null;

		try
		{
			lcerc_erc = ipr_parameterRemote.findAllEntidadRecaudadoraCuenta();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcerc_erc;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return String con la url
	 * @throws IOException
	 */
	public String salvar()
	    throws IOException
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			EntidadRecaudadoraCuenta lr_EntidadRecaudadoraCuenta;

			lr_EntidadRecaudadoraCuenta = getEntidadRecaudadoraCuenta();

			if(lr_EntidadRecaudadoraCuenta != null)
			{
				String ls_dato;

				{
					ls_dato     = lr_EntidadRecaudadoraCuenta.getNumeroCuenta();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraCuentaDetalle:idNumeroCuenta", lfc_context, ls_dato, lb_focus
						);
				}

				{
					ls_dato     = lr_EntidadRecaudadoraCuenta.getTipoCuenta();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraCuentaDetalle:idTipoCuenta", lfc_context, ls_dato, lb_focus
						);
				}

				{
					ls_dato     = lr_EntidadRecaudadoraCuenta.getNombreContacto();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraCuentaDetalle:idNombreContacto", lfc_context, ls_dato, lb_focus
						);
				}

				{
					ls_dato     = lr_EntidadRecaudadoraCuenta.getNumeroCelContacto();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraCuentaDetalle:idNumeroCelContacto", lfc_context, ls_dato, lb_focus
						);
				}

				{
					ls_dato     = lr_EntidadRecaudadoraCuenta.getCorreoElectronicoContacto();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraCuentaDetalle:idCorreoElectronicoContacto", lfc_context, ls_dato,
						    lb_focus
						);
				}

				{
					ls_dato     = lr_EntidadRecaudadoraCuenta.getTipoArchivo();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraCuentaDetalle:idTipoArchivo", lfc_context, ls_dato, lb_focus
						);
				}

				{
					ls_dato     = lr_EntidadRecaudadoraCuenta.getActivo();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraCuentaDetalle:idActivo", lfc_context, ls_dato, lb_focus
						);
				}

				ipr_parameterRemote.salvarEntidadRecaudadoraCuenta(
				    lr_EntidadRecaudadoraCuenta, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				if(isInsertar())
					addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);
				else
					addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);

				setEntidadRecaudadoraCuenta(null);

				ls_result = NavegacionCommon.ENTIDAD_RECAUDADORA_CUENTA;

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

					FacesContext.getCurrentInstance().getExternalContext().redirect("entidadRecaudadoraCuenta.jsf");
					PrimeFaces.current().ajax().update("fEntidadRecaudadoraCuenta:globalMsg");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEntidadRecaudadoraCuentaDetalle:globalMsg");
		}

		return ls_result;
	}
}
