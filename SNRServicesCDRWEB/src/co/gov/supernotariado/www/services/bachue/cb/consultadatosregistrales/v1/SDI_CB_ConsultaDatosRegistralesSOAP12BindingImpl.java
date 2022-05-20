/**
 * SDI_CB_ConsultaDatosRegistralesSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoEntradaConsultarDatosRegistrales;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistrales;

import co.gov.supernotariado.www.services.bachue.cb.consultadatosregistrales.v1.SDI_CB_ConsultaDatosRegistrales_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


public class SDI_CB_ConsultaDatosRegistralesSOAP12BindingImpl extends BaseServices
    implements SDI_CB_ConsultaDatosRegistrales_PortType
{
	private static final long          serialVersionUID = 5820755804810032482L;
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(
			    SDI_CB_ConsultaDatosRegistralesSOAP12BindingImpl.class, ProyectosCommon.CONSULTA_DATOS_REGISTRALES_15
			);

	public TipoSalidaConsultarDatosRegistrales consultarDatosRegistrales(
	    TipoEntradaConsultarDatosRegistrales atecdr_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarDatosRegistrales ltsbs_return;

		ltsbs_return = null;

		try
		{
			ltsbs_return = getConsultaDatosRegistralesRemote()
					               .consultarDatosRegistrales(
					    atecdr_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDatosRegistrales", lb2be_e);
		}

		return ltsbs_return;
	}
}
