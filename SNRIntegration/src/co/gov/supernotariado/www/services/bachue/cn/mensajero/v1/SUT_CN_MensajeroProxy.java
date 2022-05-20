package co.gov.supernotariado.www.services.bachue.cn.mensajero.v1;


/**
 * Clase que contiene todos las propiedades SUT_CN_MensajeroProxy.
 *
 * @author  Manuel Blanco
 *
 */
public class SUT_CN_MensajeroProxy implements co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType
{
	/** Propiedad s U T C N mensajero port type. */
	private co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType sUT_CN_Mensajero_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SU T C N mensajero proxy.
	 */
	public SUT_CN_MensajeroProxy()
	{
		_initSUT_CN_MensajeroProxy();
	}

	/**
	 * Instancia un nuevo objeto SU T C N mensajero proxy.
	 *
	 * @param endpoint de endpoint
	 */
	public SUT_CN_MensajeroProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CN_MensajeroProxy();
	}

	/**
	 * Inits the SU T C N mensajero proxy.
	 */
	private void _initSUT_CN_MensajeroProxy()
	{
		try
		{
			sUT_CN_Mensajero_PortType = (new co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_ServiceLocator())
					.getSUT_CN_MensajeroPort();

			if(sUT_CN_Mensajero_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CN_Mensajero_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CN_Mensajero_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
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

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CN_Mensajero_PortType != null)
			((javax.xml.rpc.Stub)sUT_CN_Mensajero_PortType)._setProperty(
			    "javax.xml.rpc.service.endpoint.address", _endpoint
			);
	}

	/**
	 * Retorna Objeto o variable de valor SU T C N mensajero port type.
	 *
	 * @return el valor de SU T C N mensajero port type
	 */
	public co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType getSUT_CN_Mensajero_PortType()
	{
		if(sUT_CN_Mensajero_PortType == null)
			_initSUT_CN_MensajeroProxy();

		return sUT_CN_Mensajero_PortType;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje enviarMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CN_Mensajero_PortType == null)
			_initSUT_CN_MensajeroProxy();

		return sUT_CN_Mensajero_PortType.enviarMensaje(entrada);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio consultarEnvio(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CN_Mensajero_PortType == null)
			_initSUT_CN_MensajeroProxy();

		return sUT_CN_Mensajero_PortType.consultarEnvio(entrada);
	}
}
