package com.bachue.snr.prosnr01.web.bean.segundaInstancia;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.web.bean.trasladoMatriculas.BeanDetalleProyectaResolucion;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanNoProcedeSegundaInstancia.
 *
 * @author Gabriel Arias
 */
@SessionScoped
@ManagedBean(name = "beanNoProcedeSegundaInstancia")
public class BeanNoProcedeSegundaInstancia extends BeanDetalleProyectaResolucion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4104685194502011911L;

	/**
	 * Constructor por defecto.
	 */
	public BeanNoProcedeSegundaInstancia()
	    throws B2BException
	{
	}
}
