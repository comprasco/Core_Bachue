package co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1;


/**
 * Clase que contiene todos las propiedades SUT_CE_MensajeroSmsProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class SUT_CE_MensajeroSmsProxy implements co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType
{
	/** Propiedad s U T C E mensajero sms port type. */
	private co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType sUT_CE_MensajeroSms_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SU T C E mensajero sms proxy.
	 */
	public SUT_CE_MensajeroSmsProxy()
	{
		_initSUT_CE_MensajeroSmsProxy();
	}

	/**
	 * Instancia un nuevo objeto SU T C E mensajero sms proxy.
	 *
	 * @param endpoint de endpoint
	 */
	public SUT_CE_MensajeroSmsProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CE_MensajeroSmsProxy();
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CE_MensajeroSms_PortType != null)
			((javax.xml.rpc.Stub)sUT_CE_MensajeroSms_PortType)._setProperty(
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

	/**
	 * Retorna Objeto o variable de valor SU T C E mensajero sms port type.
	 *
	 * @return el valor de SU T C E mensajero sms port type
	 */
	public co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType getSUT_CE_MensajeroSms_PortType()
	{
		if(sUT_CE_MensajeroSms_PortType == null)
			_initSUT_CE_MensajeroSmsProxy();

		return sUT_CE_MensajeroSms_PortType;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.ce.mensajerosms.enviarmensajetexto.v1.TipoSalidaEnviarMensajeTexto enviarMensajeTexto(
	    co.gov.supernotariado.www.schemas.bachue.ce.mensajerosms.enviarmensajetexto.v1.TipoEntradaEnviarMensajeTexto entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CE_MensajeroSms_PortType == null)
			_initSUT_CE_MensajeroSmsProxy();

		return sUT_CE_MensajeroSms_PortType.enviarMensajeTexto(entrada);
	}

	/**
	 * Inits the SU T C E mensajero sms proxy.
	 */
	private void _initSUT_CE_MensajeroSmsProxy()
	{
		try
		{
			sUT_CE_MensajeroSms_PortType = (new co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_ServiceLocator())
					.getSUT_CE_MensajeroSmsPort();

			if(sUT_CE_MensajeroSms_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CE_MensajeroSms_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CE_MensajeroSms_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
