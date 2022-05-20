package com.bachue.snr.prosnr01.web.bean.copias;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanLiquidacionCopias.
 *
 * @author hcastaneda
 */
@SessionScoped
@ManagedBean(name = "beanLiquidacionCopias")
public class BeanLiquidacionCopias extends BeanDigitalizacionCopias implements Serializable
{
	private static final long serialVersionUID = -4903901732466070642L;

	/**
	 * Metodo encargado de preliquidar la información consultada del owcc.
	 */
	public void preliquidarGenerarLiquidacion()
	{
		preliquidar(true);
	}
}
