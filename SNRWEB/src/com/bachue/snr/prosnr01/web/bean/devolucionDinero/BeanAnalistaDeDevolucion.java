package com.bachue.snr.prosnr01.web.bean.devolucionDinero;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades BeanAnalistaDeDevolucion.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 25/08/2020
 */
@SessionScoped
@ManagedBean(name = "beanAnalistaDeDevolucion")
public class BeanAnalistaDeDevolucion extends BeanCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5540656327714556646L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fAnalistaDeDevolucion";

	/**
	 *  {@inheritdoc}.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(CS_ID_FORM, "" + EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO, true);
	}
}
