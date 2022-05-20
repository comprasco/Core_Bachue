package co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1;


/**
 * Clase que contiene todos las propiedades SUT_CR_EnvioCorrespondenciaFisicaProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class SUT_CR_EnvioCorrespondenciaFisicaProxy implements co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1.SUT_CR_EnvioCorrespondenciaFisica_PortType
{
	/** Propiedad s U T C R envio correspondencia fisica port type. */
	private co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1.SUT_CR_EnvioCorrespondenciaFisica_PortType sUT_CR_EnvioCorrespondenciaFisica_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SU T C R envio correspondencia fisica proxy.
	 */
	public SUT_CR_EnvioCorrespondenciaFisicaProxy()
	{
		_initSUT_CR_EnvioCorrespondenciaFisicaProxy();
	}

	/**
	 * Instancia un nuevo objeto SU T C R envio correspondencia fisica proxy.
	 *
	 * @param endpoint de endpoint
	 */
	public SUT_CR_EnvioCorrespondenciaFisicaProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CR_EnvioCorrespondenciaFisicaProxy();
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CR_EnvioCorrespondenciaFisica_PortType != null)
			((javax.xml.rpc.Stub)sUT_CR_EnvioCorrespondenciaFisica_PortType)._setProperty(
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
	 * Retorna Objeto o variable de valor SU T C R envio correspondencia fisica port type.
	 *
	 * @return el valor de SU T C R envio correspondencia fisica port type
	 */
	public co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1.SUT_CR_EnvioCorrespondenciaFisica_PortType getSUT_CR_EnvioCorrespondenciaFisica_PortType()
	{
		if(sUT_CR_EnvioCorrespondenciaFisica_PortType == null)
			_initSUT_CR_EnvioCorrespondenciaFisicaProxy();

		return sUT_CR_EnvioCorrespondenciaFisica_PortType;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cr.enviocorrespondenciafisica.enviarcorrespondenciafisica.v1.TipoSalidaEnviarCorrespondenciaFisica enviarCorrespondenciaFisica(
	    co.gov.supernotariado.www.schemas.bachue.cr.enviocorrespondenciafisica.enviarcorrespondenciafisica.v1.TipoEntradaEnviarCorrespondenciaFisica entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CR_EnvioCorrespondenciaFisica_PortType == null)
			_initSUT_CR_EnvioCorrespondenciaFisicaProxy();

		return sUT_CR_EnvioCorrespondenciaFisica_PortType.enviarCorrespondenciaFisica(entrada);
	}

	/**
	 * Inits the SU T C R envio correspondencia fisica proxy.
	 */
	private void _initSUT_CR_EnvioCorrespondenciaFisicaProxy()
	{
		try
		{
			sUT_CR_EnvioCorrespondenciaFisica_PortType = (new co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1.SUT_CR_EnvioCorrespondenciaFisica_ServiceLocator())
					.getSUT_CR_EnvioCorrespondenciaFisicaPort();

			if(sUT_CR_EnvioCorrespondenciaFisica_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CR_EnvioCorrespondenciaFisica_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CR_EnvioCorrespondenciaFisica_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
