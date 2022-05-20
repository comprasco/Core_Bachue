package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.SalarioMinimo;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import org.primefaces.event.RowEditEvent;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanSalarioMinimo.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanSalarioMinimo")
@SessionScoped
public class BeanSalarioMinimo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4903603478073131491L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "SalarioMinimoForm:idDtListado";

	/** Propiedad ictp all pagos. */
	private Collection<SalarioMinimo> ictp_allPagos;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iotp all tipos. */
	private SalarioMinimo iotp_allTipos;

	/** Propiedad iotp detalle salario. */
	private SalarioMinimo iotp_detalleSalario;

	/**
	 * Modifica el valor de all pagos.
	 *
	 * @param aoctp_tp asigna el valor a la propiedad all pagos
	 */
	public void setAllPagos(Collection<SalarioMinimo> aoctp_tp)
	{
		ictp_allPagos = aoctp_tp;
	}

	/**
	 * Retorna el valor de all pagos.
	 *
	 * @return el valor de all pagos
	 */
	public Collection<SalarioMinimo> getAllPagos()
	{
		if(!CollectionUtils.isValidCollection(ictp_allPagos))
		{
			SalarioMinimo lottr_ttr;

			try
			{
				setAllTipos(
				    ipr_parameterRemote.findSalarioMinimo(
				        null, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);
				lottr_ttr = getAllTipos();
				setDetalleSalario(null);

				if((lottr_ttr != null) && CollectionUtils.isValidCollection(lottr_ttr.getInfoAll()))
					ictp_allPagos = lottr_ttr.getInfoAll();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return ictp_allPagos;
	}

	/**
	 * Modifica el valor de all tipos.
	 *
	 * @param aotp_tp asigna el valor a la propiedad all tipos
	 */
	public void setAllTipos(SalarioMinimo aotp_tp)
	{
		iotp_allTipos = aotp_tp;
	}

	/**
	 * Retorna el valor de all tipos.
	 *
	 * @return el valor de all tipos
	 */
	public SalarioMinimo getAllTipos()
	{
		if(iotp_allTipos == null)
			iotp_allTipos = new SalarioMinimo();

		return iotp_allTipos;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de detalle salario.
	 *
	 * @param aotp_tp asigna el valor a la propiedad detalle salario
	 */
	public void setDetalleSalario(SalarioMinimo aotp_tp)
	{
		iotp_detalleSalario = aotp_tp;
	}

	/**
	 * Retorna el valor de detalle salario.
	 *
	 * @return el valor de detalle salario
	 */
	public SalarioMinimo getDetalleSalario()
	{
		if(iotp_detalleSalario == null)
			iotp_detalleSalario = new SalarioMinimo();

		return iotp_detalleSalario;
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setAllPagos(null);
		setDetalleSalario(null);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String insertarRegistro()
	    throws B2BException
	{
		SalarioMinimo lotp_tp;
		boolean       lb_focus;
		FacesContext  lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();
		lotp_tp         = getDetalleSalario();

		if(lotp_tp != null)
		{
			lotp_tp.setIdUsuarioCreacion(getUserId());

			try
			{
				{
					String ls_idSalario;
					ls_idSalario     = lotp_tp.getIdSalario();

					lb_focus = validateStyles(":SalarioMinimoForm:idITIdSalario", lfc_context, ls_idSalario, lb_focus);

					if(!StringUtils.isValidString(ls_idSalario))
					{
						PrimeFaces.current().ajax().update(is_messageIdGrowl);
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_SALARIO);
					}
				}

				{
					String ls_vigencia;
					ls_vigencia     = lotp_tp.getVigencia();

					lb_focus = validateStyles(":SalarioMinimoForm:idITVigencia", lfc_context, ls_vigencia, lb_focus);

					if(!StringUtils.isValidString(ls_vigencia))
					{
						PrimeFaces.current().ajax().update(is_messageIdGrowl);
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_VIGENCIA);
					}
				}

				{
					BigDecimal lbd_valorSalario;
					lbd_valorSalario = lotp_tp.getValorSalario();

					Double ld_valorSalario;
					ld_valorSalario     = NumericUtils.getDoubleWrapper(lbd_valorSalario);

					lb_focus = validateStyles(
						    ":SalarioMinimoForm:idItCuantiaActo", lfc_context, ld_valorSalario, lb_focus
						);

					if(!NumericUtils.isValidDouble(ld_valorSalario))
					{
						PrimeFaces.current().ajax().update(is_messageIdGrowl);
						throw new B2BException(ErrorKeys.ERROR_VALOR_NO_NUMERICO);
					}
				}

				lotp_tp.setAccion(true);
				ipr_parameterRemote.gestionSalarioMinimo(
				    lotp_tp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clean();

				PrimeFaces.current().executeScript("PF('idDataSalario').hide();");
				PrimeFaces.current().ajax().update("fSalario:idDtListado");

				addMessage(MessagesKeys.REGISTRO_INSERTADO);
			}
			catch(B2BException lbe_lbe)
			{
				PrimeFaces.current().executeScript("PF('idDataSalario').show();");
				addMessage(lbe_lbe);
				PrimeFaces.current().ajax().update("fSalario:globalMsg");
			}
		}

		return null;
	}

	/**
	 * On row edit.
	 *
	 * @param event correspondiente al valor del tipo de objeto RowEditEvent
	 */
	public void onRowEdit(RowEditEvent event)
	{
		SalarioMinimo lorc_rc;
		boolean       lb_focus;
		FacesContext  lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();
		lorc_rc         = (SalarioMinimo)event.getObject();

		if(lorc_rc != null)
		{
			try
			{
				{
					String ls_vigencia;
					ls_vigencia     = lorc_rc.getVigencia();

					lb_focus = validateStyles(":fSalario:idDtListado:idITVigencia", lfc_context, ls_vigencia, lb_focus);

					if(!StringUtils.isValidString(ls_vigencia))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_VIGENCIA);
				}

				{
					BigDecimal lbd_valorSalario;
					lbd_valorSalario = lorc_rc.getValorSalario();

					Double ld_valorSalario;
					ld_valorSalario     = NumericUtils.getDoubleWrapper(lbd_valorSalario);

					lb_focus = validateStyles(
						    ":fSalario:idDtListado:idINValorSalario", lfc_context, ld_valorSalario, lb_focus
						);

					if(!NumericUtils.isValidDouble(ld_valorSalario))
						throw new B2BException(ErrorKeys.ERROR_VALOR_NO_NUMERICO);
				}

				lorc_rc.setAccion(false);
				ipr_parameterRemote.gestionSalarioMinimo(
				    lorc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				addMessage(MessagesKeys.REGISTRO_MODIFICADO);
			}
			catch(B2BException lbe_lbe)
			{
				addMessage(lbe_lbe);
				PrimeFaces.current().ajax().update("fSalario:globalMsg");
			}
		}
	}

	/**
	 * Show dialog.
	 */
	public void showDialog()
	{
		clean();
		PrimeFaces.current().executeScript("PF('idDataSalario').show();");
		PrimeFaces.current().ajax().update("DialogSalario");
	}
}
