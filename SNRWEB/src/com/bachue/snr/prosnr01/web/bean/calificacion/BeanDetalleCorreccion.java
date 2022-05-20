package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanDetalleCorreccion.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanDetalleCorreccion")
public class BeanDetalleCorreccion extends BeanCorrectivo implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3910203154788974508L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "confrontacion:idGrowl";

	/** {@inheritdoc} */
	public void cargarDireccionPredio(PredioActoIU aae_ae)
	{
		super.cargarDireccionPredio(aae_ae, is_messageIdGrowl);
	}

	/**
	 * Retorna el valor del objeto de String con el valor de la pagina a regresar.
	 *
	 * @return devuelve el valor de String
	 */
	public String regresarDetalleCorreccion()
	{
		String       ls_return;
		FacesContext lfc_context;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = "";

		if(isEsCertificadosEspeciales())
		{
			BeanDetalleAnalistaDeCertificadosEspeciales lbt_bean;
			lbt_bean = (BeanDetalleAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
					                                                               .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_ANALISTA_CERTIFICADOS,
					    BeanDetalleAnalistaDeCertificadosEspeciales.class
					);

			if(lbt_bean != null)
			{
				lbt_bean.setConfrontacion(false);
				lbt_bean.setInsertaMatricula(false);
				lbt_bean.setEliminaMatricula(false);
			}

			ls_return = NavegacionCommon.CERTIFICADOS_ESPECIALES;
		}
		else
			ls_return = NavegacionCommon.DETALLE_ACTO;

		return ls_return;
	}
}
