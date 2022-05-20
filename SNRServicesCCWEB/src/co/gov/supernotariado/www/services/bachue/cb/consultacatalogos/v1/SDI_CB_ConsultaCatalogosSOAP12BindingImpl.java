/**
 * SDI_CB_ConsultaCatalogosSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogosCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr04.web.services.BaseServices;


public class SDI_CB_ConsultaCatalogosSOAP12BindingImpl extends BaseServices implements SDI_CB_ConsultaCatalogos
{
	private static final long          serialVersionUID = -2563595120245547804L;
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SDI_CB_ConsultaCatalogosSOAP12BindingImpl.class);

	public TipoSalidaConsultarCatalogos consultarCatalogos(TipoEntradaConsultarCatalogos atecc_entrada)
	    throws java.rmi.RemoteException
	{
		TiposCatalogos[]                          ltca_consulta;
		TipoSalidaConsultarCatalogosCodigoMensaje ltscccm_codigo;
		String                                    ls_descripcionMensaje;

		ltca_consulta             = null;
		ltscccm_codigo            = TipoSalidaConsultarCatalogosCodigoMensaje.value1;
		ls_descripcionMensaje     = getMessageKey(MessagesKeys.OK);

		try
		{
			ltca_consulta = getConsultaCatalogosRemote()
					                .consultarCatalogos(
					    atecc_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarCatalogos", lb2be_e);

			ltscccm_codigo            = TipoSalidaConsultarCatalogosCodigoMensaje.value2;
			ls_descripcionMensaje     = getErrorKey(lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarCatalogos", le_e);

			ltscccm_codigo            = TipoSalidaConsultarCatalogosCodigoMensaje.value3;
			ls_descripcionMensaje     = le_e.getMessage();
		}

		if(ltca_consulta == null)
		{
			ltca_consulta        = new TiposCatalogos[1];
			ltca_consulta[0]     = new TiposCatalogos("", null);
		}

		return new TipoSalidaConsultarCatalogos(ltca_consulta, ltscccm_codigo, ls_descripcionMensaje);
	}
}
