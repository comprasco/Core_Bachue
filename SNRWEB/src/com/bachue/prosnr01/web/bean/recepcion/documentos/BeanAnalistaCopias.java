package com.bachue.prosnr01.web.bean.recepcion.documentos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanAnalistaCopias.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanAnalistaCopias")
public class BeanAnalistaCopias extends BeanCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7007822186231603878L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fAnalistaDeCopias";


	/** {@inheritdoc} */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(CS_ID_FORM, "" + EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS, true);
	}

}
