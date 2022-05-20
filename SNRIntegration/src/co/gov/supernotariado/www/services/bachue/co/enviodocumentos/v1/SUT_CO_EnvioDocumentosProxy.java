package co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1;

public class SUT_CO_EnvioDocumentosProxy implements co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_PortType
{
	private co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_PortType sUT_CO_EnvioDocumentos_PortType =
		null;
	private String                                                                                          _endpoint = null;

	public SUT_CO_EnvioDocumentosProxy()
	{
		_initSUT_CO_EnvioDocumentosProxy();
	}

	public SUT_CO_EnvioDocumentosProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CO_EnvioDocumentosProxy();
	}

	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CO_EnvioDocumentos_PortType != null)
			((javax.xml.rpc.Stub)sUT_CO_EnvioDocumentos_PortType)._setProperty(
			    "javax.xml.rpc.service.endpoint.address", _endpoint
			);
	}

	public String getEndpoint()
	{
		return _endpoint;
	}

	public co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_PortType getSUT_CO_EnvioDocumentos_PortType()
	{
		if(sUT_CO_EnvioDocumentos_PortType == null)
			_initSUT_CO_EnvioDocumentosProxy();

		return sUT_CO_EnvioDocumentos_PortType;
	}

	public co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoSalidaEnviarDocumento enviarDocumento(
	    co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumento entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CO_EnvioDocumentos_PortType == null)
			_initSUT_CO_EnvioDocumentosProxy();

		return sUT_CO_EnvioDocumentos_PortType.enviarDocumento(entrada);
	}

	private void _initSUT_CO_EnvioDocumentosProxy()
	{
		try
		{
			sUT_CO_EnvioDocumentos_PortType = (new co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_ServiceLocator())
					.getSUT_CO_EnvioDocumentosPort();

			if(sUT_CO_EnvioDocumentos_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CO_EnvioDocumentos_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CO_EnvioDocumentos_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
			serviceException.printStackTrace();
		}
	}
}
