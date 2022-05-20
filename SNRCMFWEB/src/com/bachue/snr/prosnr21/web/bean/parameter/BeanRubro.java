package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.Rubro;

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
 * Clase para el manejo de la capa web para Rubro
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanRubro")
@SessionScoped
public class BeanRubro extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4867857576700130451L;

	/** Propiedad iccmv datos auditoria. */
	private Collection<Rubro> iccmv_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir rubro. */
	private Rubro ir_rubro;

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
	public void setDatosAuditoria(Collection<Rubro> acmv_datosAuditoria)
	{
		iccmv_datosAuditoria = acmv_datosAuditoria;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Rubro> getDatosAuditoria()
	{
		return iccmv_datosAuditoria;
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
	 * Método de asignación del valor de la propiedad
	 * @param ar_rubro asigna el valor a la propiedad
	 */
	public void setRubro(Rubro ar_rubro)
	{
		ir_rubro = ar_rubro;
	}

	/**
	 * Método de obtencion del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public Rubro getRubro()
	{
		return ir_rubro;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Rubro
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setRubro((new Rubro()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarRubro");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consulta detalla para un Rubro en especial
	 * @param ar_r de tipo rubro
	 * @throws B2BException
	 */
	public void consultaDetallada(Rubro ar_r)
	    throws B2BException
	{
		if(ar_r != null)
		{
			String ll_idCausalMayorValor;
			Rubro  latd_td;
			latd_td                   = new Rubro();
			ll_idCausalMayorValor     = ar_r.getIdRubro();
			latd_td.setIdRubro(ll_idCausalMayorValor);

			latd_td = ipr_parameterRemote.findRubroById(latd_td);

			if(latd_td != null)
			{
				Collection<Rubro> lctd_td;

				lctd_td = new ArrayList<Rubro>();

				lctd_td.add(latd_td);
				setRubro(latd_td);

				setDatosAuditoria(lctd_td);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método de consulta de todos los rubros
	 * @return una colleccion de tipo rubro
	 */
	public Collection<Rubro> findAllRubro()
	{
		Collection<Rubro> lcr_cr;
		lcr_cr = null;

		try
		{
			lcr_cr = ipr_parameterRemote.findAllRubro();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_cr;
	}

	/**
	 * Método para salvar la inserción o actualización
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
			Rubro lr_rubro;

			lr_rubro = getRubro();

			if(lr_rubro != null)
			{
				String ls_activo;
				String ls_nombre;
				String ls_numeroRubro;

				ls_nombre          = lr_rubro.getNombre();
				ls_activo          = lr_rubro.getActivo();
				ls_numeroRubro     = lr_rubro.getNumeroRubro();

				lb_focus     = validateStyles(":fRubroDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

				lb_focus     = validateStyles(":fRubroDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				lb_focus = validateStyles(":fRubroDetalle:idNumeroRubro", lfc_context, ls_numeroRubro, lb_focus);

				ipr_parameterRemote.salvarRubro(
				    lr_rubro, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				PrimeFaces.current().executeScript("PF('idDCofirmaTD').hide();");

				if(!isInsertar())
					addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);
				else
					addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);

				setRubro(null);

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

					FacesContext.getCurrentInstance().getExternalContext().redirect("rubro.jsf");
					PrimeFaces.current().ajax().update("fRubro:globalMsg");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fRubroDetalle:globalMsg");
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
			Rubro  lr_rubro;
			String ls_activo;

			lr_rubro = getRubro();

			if(lr_rubro != null)
			{
				ls_activo = lr_rubro.getActivo();

				if(ls_activo.equals("N"))
					PrimeFaces.current().executeScript("PF('idDCofirmaTD').show();");
				else
					salvar();
			}
		}
		else
			salvar();
	}
}
