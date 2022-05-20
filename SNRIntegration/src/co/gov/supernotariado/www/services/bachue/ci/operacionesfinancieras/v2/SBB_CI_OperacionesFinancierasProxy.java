package co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2;


/**
 * Clase que contiene todos las propiedades SBB_CI_OperacionesFinancierasProxy.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class SBB_CI_OperacionesFinancierasProxy implements co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_PortType
{
	/** Propiedad s B B C I operaciones financieras port type. */
	private co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_PortType sBB_CI_OperacionesFinancieras_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SB B C I operaciones financieras proxy.
	 */
	public SBB_CI_OperacionesFinancierasProxy()
	{
		_initSBB_CI_OperacionesFinancierasProxy();
	}

	/**
	 * Instancia un nuevo objeto SB B C I operaciones financieras proxy.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public SBB_CI_OperacionesFinancierasProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSBB_CI_OperacionesFinancierasProxy();
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint correspondiente al valor de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sBB_CI_OperacionesFinancieras_PortType != null)
			((javax.xml.rpc.Stub)sBB_CI_OperacionesFinancieras_PortType)._setProperty(
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
	 * Retorna Objeto o variable de valor SB B C I operaciones financieras port type.
	 *
	 * @return el valor de SB B C I operaciones financieras port type
	 */
	public co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_PortType getSBB_CI_OperacionesFinancieras_PortType()
	{
		if(sBB_CI_OperacionesFinancieras_PortType == null)
			_initSBB_CI_OperacionesFinancierasProxy();

		return sBB_CI_OperacionesFinancieras_PortType;
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion registrarAnulacion(
	    co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sBB_CI_OperacionesFinancieras_PortType == null)
			_initSBB_CI_OperacionesFinancierasProxy();

		return sBB_CI_OperacionesFinancieras_PortType.registrarAnulacion(entrada);
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion registrarLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sBB_CI_OperacionesFinancieras_PortType == null)
			_initSBB_CI_OperacionesFinancierasProxy();

		return sBB_CI_OperacionesFinancieras_PortType.registrarLiquidacion(entrada);
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarrecibocaja.v2.TipoSalidaRegistrarReciboCaja registrarReciboCaja(
	    co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarrecibocaja.v2.TipoEntradaRegistrarReciboCaja entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sBB_CI_OperacionesFinancieras_PortType == null)
			_initSBB_CI_OperacionesFinancierasProxy();

		return sBB_CI_OperacionesFinancieras_PortType.registrarReciboCaja(entrada);
	}

	/**
	 * Inits the SB B C I operaciones financieras proxy.
	 */
	private void _initSBB_CI_OperacionesFinancierasProxy()
	{
		try
		{
			sBB_CI_OperacionesFinancieras_PortType = (new co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_ServiceLocator())
					.getSBB_CI_OperacionesFinancierasPort();

			if(sBB_CI_OperacionesFinancieras_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sBB_CI_OperacionesFinancieras_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sBB_CI_OperacionesFinancieras_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
