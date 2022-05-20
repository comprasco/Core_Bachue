package co.gov.supernotariado.www.services.bachue.co.relacionesdocumento.v1;

import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoEntradaRelacionarDocumento;
import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoSalidaRelacionarDocumento;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;


public class SUT_CO_RelacionesDocumentoProxy implements SUT_CO_RelacionesDocumento_PortType
{
	private SUT_CO_RelacionesDocumento_PortType sUT_CO_RelacionesDocumento_PortType = null;
	private String                              _endpoint                           = null;

	public SUT_CO_RelacionesDocumentoProxy()
	{
		_initSUT_CO_RelacionesDocumentoProxy();
	}

	public SUT_CO_RelacionesDocumentoProxy(String as_endpoint)
	{
		_endpoint = as_endpoint;
		_initSUT_CO_RelacionesDocumentoProxy();
	}

	public void setEndpoint(String as_endpoint)
	{
		_endpoint = as_endpoint;

		if(sUT_CO_RelacionesDocumento_PortType != null)
			((Stub)sUT_CO_RelacionesDocumento_PortType)._setProperty(
			    "javax.xml.rpc.service.endpoint.address", _endpoint
			);
	}

	public String getEndpoint()
	{
		return _endpoint;
	}

	public SUT_CO_RelacionesDocumento_PortType getSUT_CO_RelacionesDocumento_PortType()
	{
		if(sUT_CO_RelacionesDocumento_PortType == null)
			_initSUT_CO_RelacionesDocumentoProxy();

		return sUT_CO_RelacionesDocumento_PortType;
	}

	public TipoSalidaRelacionarDocumento relacionarDocumento(TipoEntradaRelacionarDocumento aterd_entrada)
	    throws RemoteException
	{
		if(sUT_CO_RelacionesDocumento_PortType == null)
			_initSUT_CO_RelacionesDocumentoProxy();

		return sUT_CO_RelacionesDocumento_PortType.relacionarDocumento(aterd_entrada);
	}

	private void _initSUT_CO_RelacionesDocumentoProxy()
	{
		try
		{
			sUT_CO_RelacionesDocumento_PortType = (new SUT_CO_RelacionesDocumento_ServiceLocator())
					.getSUT_CO_RelacionesDocumentoPort();

			if(sUT_CO_RelacionesDocumento_PortType != null)
			{
				if(_endpoint != null)
					((Stub)sUT_CO_RelacionesDocumento_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((Stub)sUT_CO_RelacionesDocumento_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(ServiceException serviceException)
		{
			serviceException.printStackTrace();
		}
	}
}
