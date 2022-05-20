package co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1;

public class SUT_CR_GeneracionIDsCorrespondenciaProxy implements co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_PortType
{
	private co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_PortType sUT_CR_GeneracionIDsCorrespondencia_PortType =
		null;
	private String                                                                                                                    _endpoint =
		null;

	public SUT_CR_GeneracionIDsCorrespondenciaProxy()
	{
		_initSUT_CR_GeneracionIDsCorrespondenciaProxy();
	}

	public SUT_CR_GeneracionIDsCorrespondenciaProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CR_GeneracionIDsCorrespondenciaProxy();
	}

	private void _initSUT_CR_GeneracionIDsCorrespondenciaProxy()
	{
		try
		{
			sUT_CR_GeneracionIDsCorrespondencia_PortType = (new co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_ServiceLocator())
					.getSUT_CR_GeneracionIDsCorrespondenciaPort();

			if(sUT_CR_GeneracionIDsCorrespondencia_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CR_GeneracionIDsCorrespondencia_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CR_GeneracionIDsCorrespondencia_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}

	public String getEndpoint()
	{
		return _endpoint;
	}

	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CR_GeneracionIDsCorrespondencia_PortType != null)
			((javax.xml.rpc.Stub)sUT_CR_GeneracionIDsCorrespondencia_PortType)._setProperty(
			    "javax.xml.rpc.service.endpoint.address", _endpoint
			);
	}

	public co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_PortType getSUT_CR_GeneracionIDsCorrespondencia_PortType()
	{
		if(sUT_CR_GeneracionIDsCorrespondencia_PortType == null)
			_initSUT_CR_GeneracionIDsCorrespondenciaProxy();

		return sUT_CR_GeneracionIDsCorrespondencia_PortType;
	}

	public co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoSalidaObtenerEECorrespondencia obtenerEECorrespondencia(
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondencia entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CR_GeneracionIDsCorrespondencia_PortType == null)
			_initSUT_CR_GeneracionIDsCorrespondenciaProxy();

		return sUT_CR_GeneracionIDsCorrespondencia_PortType.obtenerEECorrespondencia(entrada);
	}
}
