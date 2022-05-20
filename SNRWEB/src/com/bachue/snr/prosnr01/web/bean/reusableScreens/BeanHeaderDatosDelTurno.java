package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * Clase que contiene todos las propiedades y acciones de beanHeaderDatosDelTurno.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 2/11/2021
 */
@ViewScoped
@ManagedBean(name = "beanHeaderDatosDelTurno")
public class BeanHeaderDatosDelTurno extends BeanPredioDocumentoActo implements Serializable,
	HeaderDatosDelTurnoInterface
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1652419764910166689L;
}
