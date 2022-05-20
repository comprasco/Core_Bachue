package co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1;


/**
 * Clase que contiene todos las propiedades SDI_CI_ConsultaCatalogosProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class SDI_CI_ConsultaCatalogosProxy implements co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType
{
	/** Propiedad s D I C I consulta catalogos port type. */
	private co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType sDI_CI_ConsultaCatalogos_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SD I C I consulta catalogos proxy.
	 */
	public SDI_CI_ConsultaCatalogosProxy()
	{
		_initSDI_CI_ConsultaCatalogosProxy();
	}

	/**
	 * Instancia un nuevo objeto SD I C I consulta catalogos proxy.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public SDI_CI_ConsultaCatalogosProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSDI_CI_ConsultaCatalogosProxy();
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sDI_CI_ConsultaCatalogos_PortType != null)
			((javax.xml.rpc.Stub)sDI_CI_ConsultaCatalogos_PortType)._setProperty(
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
	 * Retorna Objeto o variable de valor SD I C I consulta catalogos port type.
	 *
	 * @return el valor de SD I C I consulta catalogos port type
	 */
	public co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType getSDI_CI_ConsultaCatalogos_PortType()
	{
		if(sDI_CI_ConsultaCatalogos_PortType == null)
			_initSDI_CI_ConsultaCatalogosProxy();

		return sDI_CI_ConsultaCatalogos_PortType;
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos consultarCatalogos(
	    co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sDI_CI_ConsultaCatalogos_PortType == null)
			_initSDI_CI_ConsultaCatalogosProxy();

		return sDI_CI_ConsultaCatalogos_PortType.consultarCatalogos(entrada);
	}

	/**
	 * Inits the SD I C I consulta catalogos proxy.
	 */
	private void _initSDI_CI_ConsultaCatalogosProxy()
	{
		try
		{
			sDI_CI_ConsultaCatalogos_PortType = (new co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_ServiceLocator())
					.getSDI_CI_ConsultaCatalogosPort();

			if(sDI_CI_ConsultaCatalogos_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sDI_CI_ConsultaCatalogos_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sDI_CI_ConsultaCatalogos_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
