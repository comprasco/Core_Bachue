package com.bachue.snr.prosnr21.web.bean.dispositivos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EventoCommon;
import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.bean.login.BeanLogin;
import com.bachue.snr.prosnr21.web.util.FacesUtils;

import java.io.IOException;
import java.io.Serializable;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de los dispositivos.
 *
 * @author  Jorge Patino
 * Fecha de Creacion: 27/12/2019
 */
@SessionScoped
@ManagedBean(name = "beanDispositivos")
public class BeanDispositivos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3244630843171054832L;
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDispositivos.class);

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Evento de error cuando el BioClient responde con un protocolo de error en el segundo factor de autenticacion.
	 *
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public void errorSegundoFactor()
	    throws IOException
	{
		String[] loa_evento;

		loa_evento = obtenerParametrosEvento("event");

		{
			B2BException lb2be_e;
			BeanLogin    lbl_beanLogin;
			lbl_beanLogin = (BeanLogin)FacesUtils.getFacesContext().getApplication()
					                                 .evaluateExpressionGet(
					    FacesUtils.getFacesContext(), BeanNameCommon.BEAN_LOGIN, BeanLogin.class
					);

			lbl_beanLogin.cerrarSesion();
			lb2be_e = new B2BException(ErrorKeys.OCURRIO_UN_ERROR_FUNCION, traducirMensajes(loa_evento));

			addMessage(lb2be_e);
			clh_LOGGER.error(lb2be_e);
		}
	}

	/**
	 * Obtener parametros evento.
	 *
	 * @param as_llave de as llave
	 * @return el valor de string[]
	 */
	private String[] obtenerParametrosEvento(String as_llave)
	{
		String[] loa_evento;

		loa_evento = null;

		if(StringUtils.isValidString(as_llave))
		{
			Map<String, String> lmss_params;

			lmss_params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

			if(CollectionUtils.isValidCollection(lmss_params))
			{
				loa_evento        = new String[1];
				loa_evento[0]     = lmss_params.get(as_llave);
			}
		}

		return loa_evento;
	}

	/**
	 * Traducir mensajes.
	 *
	 * @param aoa_param de aoa param
	 * @return el valor de object[]
	 */
	private Object[] traducirMensajes(String[] aoa_param)
	{
		if(aoa_param != null)
		{
			String ls_evento;

			ls_evento = aoa_param[0];

			if(StringUtils.isValidString(ls_evento))
			{
				switch(ls_evento)
				{
					case EventoCommon.SIGN:
						aoa_param[0] = EventoCommon.FIRMA;

						break;

					case EventoCommon.ENROLL:
						aoa_param[0] = EventoCommon.ENROLAMIENTO;

						break;

					case EventoCommon.VERIFY:
						aoa_param[0] = EventoCommon.VERIFICACION;

						break;

					case EventoCommon.RESET:
						aoa_param[0] = EventoCommon.REINICIO_CLAVE;

						break;

					case EventoCommon.PDF:
						aoa_param[0] = EventoCommon.IMPRESION;

						break;

					default:
						break;
				}
			}
		}

		return aoa_param;
	}
}
