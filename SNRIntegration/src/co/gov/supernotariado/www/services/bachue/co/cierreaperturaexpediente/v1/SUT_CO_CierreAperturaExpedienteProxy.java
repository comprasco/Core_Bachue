package co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1;


/**
 * Clase que contiene todos las propiedades SUT_CO_CierreAperturaExpedienteProxy.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 4/05/2020
 */
public class SUT_CO_CierreAperturaExpedienteProxy implements co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType
{
	/** Propiedad s U T C O cierre apertura expediente port type. */
	private co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType sUT_CO_CierreAperturaExpediente_PortType =
		null;

	/** Propiedad endpoint. */
	private String _endpoint = null;

	/**
	 * Instancia un nuevo objeto SU T C O cierre apertura expediente proxy.
	 */
	public SUT_CO_CierreAperturaExpedienteProxy()
	{
		_initSUT_CO_CierreAperturaExpedienteProxy();
	}

	/**
	 * Instancia un nuevo objeto SU T C O cierre apertura expediente proxy.
	 *
	 * @param endpoint de endpoint
	 */
	public SUT_CO_CierreAperturaExpedienteProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CO_CierreAperturaExpedienteProxy();
	}

	/**
	 * Modifica el valor de Endpoint.
	 *
	 * @param endpoint de endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CO_CierreAperturaExpediente_PortType != null)
			((javax.xml.rpc.Stub)sUT_CO_CierreAperturaExpediente_PortType)._setProperty(
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
	 * Retorna Objeto o variable de valor SU T C O cierre apertura expediente port type.
	 *
	 * @return el valor de SU T C O cierre apertura expediente port type
	 */
	public co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType getSUT_CO_CierreAperturaExpediente_PortType()
	{
		if(sUT_CO_CierreAperturaExpediente_PortType == null)
			_initSUT_CO_CierreAperturaExpedienteProxy();

		return sUT_CO_CierreAperturaExpediente_PortType;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoSalidaAperturaTurno aperturaTurno(
	    co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoEntradaAperturaTurno entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CO_CierreAperturaExpediente_PortType == null)
			_initSUT_CO_CierreAperturaExpedienteProxy();

		return sUT_CO_CierreAperturaExpediente_PortType.aperturaTurno(entrada);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoSalidaCierreTurno cierreTurno(
	    co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoEntradaCierreTurno entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CO_CierreAperturaExpediente_PortType == null)
			_initSUT_CO_CierreAperturaExpedienteProxy();

		return sUT_CO_CierreAperturaExpediente_PortType.cierreTurno(entrada);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoSalidaObtenerIndiceElectronico obtenerIndiceElectronico(
	    co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoEntradaObtenerIndiceElectronico entrada
	)
	    throws java.rmi.RemoteException
	{
		if(sUT_CO_CierreAperturaExpediente_PortType == null)
			_initSUT_CO_CierreAperturaExpedienteProxy();

		return sUT_CO_CierreAperturaExpediente_PortType.obtenerIndiceElectronico(entrada);
	}

	/**
	 * Inits the SU T C O cierre apertura expediente proxy.
	 */
	private void _initSUT_CO_CierreAperturaExpedienteProxy()
	{
		try
		{
			sUT_CO_CierreAperturaExpediente_PortType = (new co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_ServiceLocator())
					.getSUT_CO_CierreAperturaExpedientePort();

			if(sUT_CO_CierreAperturaExpediente_PortType != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CO_CierreAperturaExpediente_PortType)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CO_CierreAperturaExpediente_PortType)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
