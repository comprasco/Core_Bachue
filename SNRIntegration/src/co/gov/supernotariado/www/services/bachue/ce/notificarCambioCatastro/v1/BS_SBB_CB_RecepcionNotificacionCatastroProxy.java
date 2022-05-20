package co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1;

/**
 * Clase que contiene todos las propiedades BS_SBB_CB_RecepcionNotificacionCatastroProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class BS_SBB_CB_RecepcionNotificacionCatastroProxy implements co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_PortType
{
	
	/** Propiedad b S SB B C B recepcion notificacion catastro port type. */
	private co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_PortType bS_SBB_CB_RecepcionNotificacionCatastro_PortType =
		null;
	
	/** Propiedad endpoint. */
	private String                                                                                                                   _endpoint =
		null;

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion catastro proxy.
	 */
	public BS_SBB_CB_RecepcionNotificacionCatastroProxy()
	{
		_initBS_SBB_CB_RecepcionNotificacionCatastroProxy();
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion catastro proxy.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public BS_SBB_CB_RecepcionNotificacionCatastroProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initBS_SBB_CB_RecepcionNotificacionCatastroProxy();
	}

	/**
	 * Retorna Objeto o variable de valor b S SB B C B recepcion notificacion catastro port type.
	 *
	 * @return el valor de b S SB B C B recepcion notificacion catastro port type
	 */
	public co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_PortType getBS_SBB_CB_RecepcionNotificacionCatastro_PortType()
	{
		if(bS_SBB_CB_RecepcionNotificacionCatastro_PortType == null)
			_initBS_SBB_CB_RecepcionNotificacionCatastroProxy();

		return bS_SBB_CB_RecepcionNotificacionCatastro_PortType;
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(bS_SBB_CB_RecepcionNotificacionCatastro_PortType != null)
			((javax.xml.rpc.Stub)bS_SBB_CB_RecepcionNotificacionCatastro_PortType)._setProperty(
			    "javax.xml.rpc.service.endpoint.address", _endpoint
			);
	}

	/**
	 * Retorna Objeto o variable de valor endpoint.
	 *
	 * @return el valor de endpoint
	 */
	public String getEndpoint()
	{
		return _endpoint;
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoSalidaNotificarCambioCatastro notificarCambioCatastro(
	    co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoEntradaNotificarCambioCatastro entrada
	)
	    throws java.rmi.RemoteException
	{
		if(bS_SBB_CB_RecepcionNotificacionCatastro_PortType == null)
			_initBS_SBB_CB_RecepcionNotificacionCatastroProxy();

		return bS_SBB_CB_RecepcionNotificacionCatastro_PortType.notificarCambioCatastro(entrada);
	}

	/**
	 * Inits the B S SB B C B recepcion notificacion catastro proxy.
	 */
	private void _initBS_SBB_CB_RecepcionNotificacionCatastroProxy()
	{
		try
		{
			bS_SBB_CB_RecepcionNotificacionCatastro_PortType = (new co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_ServiceLocator())
					.getBS_SBB_CB_RecepcionNotificacionCatastroPort();

			if(bS_SBB_CB_RecepcionNotificacionCatastro_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)bS_SBB_CB_RecepcionNotificacionCatastro_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)bS_SBB_CB_RecepcionNotificacionCatastro_PortType)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
