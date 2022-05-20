package co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1;

import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoEntradaConsultar;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoSalidaConsultar;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoEntradaObtenerArchivo;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoSalidaObtenerArchivo;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;


/**
 * Clase que contiene todos las propiedades SUT_CO_BusquedaDocumentosProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class SUT_CO_BusquedaDocumentosProxy implements SUT_CO_BusquedaDocumentos_PortType
{
	/** Propiedad s U T C O busqueda documentos port type. */
	private SUT_CO_BusquedaDocumentos_PortType sUT_CO_BusquedaDocumentos_PortType = null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SU T C O busqueda documentos proxy.
	 */
	public SUT_CO_BusquedaDocumentosProxy()
	{
		_initSUT_CO_BusquedaDocumentosProxy();
	}

	/**
	 * Instancia un nuevo objeto SU T C O busqueda documentos proxy.
	 *
	 * @param as_endpoint correspondiente al valor de as endpoint
	 */
	public SUT_CO_BusquedaDocumentosProxy(String as_endpoint)
	{
		_endpoint = as_endpoint;
		_initSUT_CO_BusquedaDocumentosProxy();
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param as_endpoint correspondiente al valor de as endpoint
	 */
	public void setEndpoint(String as_endpoint)
	{
		_endpoint = as_endpoint;

		if(sUT_CO_BusquedaDocumentos_PortType != null)
			((Stub)sUT_CO_BusquedaDocumentos_PortType)._setProperty(
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
	 * Retorna Objeto o variable de valor SU T C O busqueda documentos port type.
	 *
	 * @return el valor de SU T C O busqueda documentos port type
	 */
	public SUT_CO_BusquedaDocumentos_PortType getSUT_CO_BusquedaDocumentos_PortType()
	{
		if(sUT_CO_BusquedaDocumentos_PortType == null)
			_initSUT_CO_BusquedaDocumentosProxy();

		return sUT_CO_BusquedaDocumentos_PortType;
	}

	/** {@inheritDoc} */
	public TipoSalidaConsultar consultar(TipoEntradaConsultar atec_entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CO_BusquedaDocumentos_PortType == null)
			_initSUT_CO_BusquedaDocumentosProxy();

		return sUT_CO_BusquedaDocumentos_PortType.consultar(atec_entrada);
	}

	/** {@inheritDoc} */
	public TipoSalidaObtenerArchivo obtenerArchivo(TipoEntradaObtenerArchivo ateoa_entrada)
	    throws RemoteException
	{
		if(sUT_CO_BusquedaDocumentos_PortType == null)
			_initSUT_CO_BusquedaDocumentosProxy();

		return sUT_CO_BusquedaDocumentos_PortType.obtenerArchivo(ateoa_entrada);
	}

	/**
	 * Inits the SU T C O busqueda documentos proxy.
	 */
	private void _initSUT_CO_BusquedaDocumentosProxy()
	{
		try
		{
			sUT_CO_BusquedaDocumentos_PortType = (new SUT_CO_BusquedaDocumentos_ServiceLocator())
					.getSUT_CO_BusquedaDocumentosPort();

			if(sUT_CO_BusquedaDocumentos_PortType != null)
			{
				if(_endpoint != null)
					((Stub)sUT_CO_BusquedaDocumentos_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((Stub)sUT_CO_BusquedaDocumentos_PortType)._getProperty(
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
